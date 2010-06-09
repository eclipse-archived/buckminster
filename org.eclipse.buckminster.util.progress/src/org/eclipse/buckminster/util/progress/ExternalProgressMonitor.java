/*******************************************************************************
 * Copyright (c) 2009 Cloudsmith Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cloudsmith Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.buckminster.util.progress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * An ExternalProgressMonitor is a IProgressMonitorWithBlocking implementation that adapts a "local" progress monitor
 * to handle progress monitoring from an external process. Subclasses of this abstract class provide concrete
 * implementations for different communication protocols.
 * 
 * <h4>Usage</h4>
 * Instantiate this class with the parent (i.e. real) progress monitor that would have been used if the work
 * had been performed directly in the same process. 
 * Then, set the command string to the values expected by {@link Runtime#exec(String[])}.
 * Optionally set a prefix String (that is prefixed to messages in the communication) to enable filtering out other
 * content on the same channel. This is primarily useful for running shell or bat scripts via Stdin/Stdout and where
 * these scripts also produce other output that should be ignored. A suitable prefix is "___". By default, no prefix
 * is used.
 * Subclasses may require additional setup.
 * Finally, when everything is setup, call {@link #execute()} to run and monitor the external command.
 * 
 * Note that it is possible to cancel the external job by simply calling {@link #setCanceled(boolean)} if the
 * externally executing process is using an instance of {@link ExternalProgressProxyMonitor} or is compatible with
 * how the cancelation is performed. See {@link ExternalProgressProxyMonitor} for more information.
 * 
 * See {@link StdInOutProgressMonitor} and {@link SocketProgressMonitor} for two concrete 
 * ExternalProgressMonitor classes.
 * 
 * @author henrik.lindberg@cloudsmith.com
 *
 */
public abstract class ExternalProgressMonitor implements IProgressMonitor, IProgressMonitorWithBlocking, ExternalProgressMonitorConstants {
	private IProgressMonitor monitor;
	private boolean cancelSent;
	protected OutputStreamWriter out;
	protected String[] cmd;
	protected String prefix = DEFAULT_PREFIX;

	/**
	 * Create an external monitor for the real monitor (that would have been used if the work was performed
	 * directly). The parent monitor should not have been used. 
	 * @param parentMonitor
	 */
	public ExternalProgressMonitor(IProgressMonitor parentMonitor) {
		monitor = parentMonitor;
		cancelSent = false;
	}

	/**
	 * Sets up filtering in the communication so only lines received with a matching prefix 
	 * will be parsed. (All other lines are ignored. Naturally, the proxy (or schell script) must be aware of the
	 * prefix. A suitable prefix is "___".
	 * @param prefixString
	 */
	public void setPrefix(String prefixString) {
		prefix = prefixString;
	}

	/**
	 * Sets the arguments to pass to {@link Runtime#exec(String[])}. This describes what will be executed.
	 * @param execArgs
	 */
	public void setCmd(String[] execArgs) {
		cmd = execArgs;
	}

	/**
	 * Reads monitor instructions from the input stream and adapts them to this 
	 * instance IProgressMonitor API.
	 * @return
	 */
	public abstract int execute();

	/**
	 * Reads an processes the stream from the executing process.
	 * @param inputStream
	 */
	protected void processStream(InputStream inputStream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			String data;
			while ((data = reader.readLine()) != null) {
				if (isCanceled() && !cancelSent)
					processCancelation();
				if (data.startsWith(prefix))
					data = data.substring(prefix.length());
				else
					continue; // filter out non matching strings
				try {
					// parse and act
					switch (data.charAt(0)) {
						case WORKED :
							worked(Integer.parseInt(data.substring(1)));
							break;
						case INTERNAL_WORKED :
							internalWorked(Double.parseDouble(data.substring(1)));
							break;
						case WORKED_ONE :
							worked(1);
							break;
						case SET_TASK_NAME :
							setTaskName(data.substring(1));
							break;
						case SUB_TASK :
							subTask(data.substring(1));
							break;
						case CLEAR_BLOCKED :
							clearBlocked();
							break;
						case SET_BLOCKED :
							setBlocked(new Status(IStatus.OK, Activator.PLUGIN_ID, data.substring(1)));
							break;
						case DONE :
							done();
							break;
						case BEGIN :
							int commaPos = data.indexOf(',');
							int totalWork = Integer.parseInt(data.substring(1, commaPos));
							String name = data.substring(commaPos + 1);
							beginTask(name, totalWork);
							break;
						case SET_CANCELED :
							setCanceled(data.charAt(1) == 't');
							break;
						case CHECK_CANCEL :

						default :
							// text that is not part of the protocol - assume monitoring is broken
					}
				} catch (NumberFormatException e) {
					// ignore - bad input
				} catch (IndexOutOfBoundsException e) {
					// ignore - bad input
				}
			}
		} catch (IOException e) {
			// ignore
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}

	/**
	 * Sinks an input stream (i.e. reads it and throws away what it reads). 
	 * Compare to directing output to /dev/null on a UN*X box.
	 * The sinking is done in a separate thread which terminates when the input is closed.
	 * 
	 * @param inputStream
	 */
	public static void sinkStream(final InputStream inputStream) {
		Runnable sinker = new Runnable() {

			// read stream and ignore until stream is closed
			public void run() {
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				try {
					while (reader.readLine() != null) {
						// do nothing
					}
				} catch (IOException e) {
					// ignore
				} finally {
					try {
						reader.close();
					} catch (IOException e) {
						// ignore
					}
				}
			}
		};
		new Thread(sinker).start();
	}

	public void beginTask(String name, int totalWork) {
		monitor.beginTask(name, totalWork);
	}

	public void done() {
		monitor.done();

	}

	public void internalWorked(double work) {
		monitor.internalWorked(work);
	}

	public boolean isCanceled() {
		return monitor.isCanceled();
	}

	public void setCanceled(boolean value) {
		monitor.setCanceled(value);
		if (isCanceled() && !cancelSent)
			processCancelation();
	}

	/**
	 * Sends cancellation message to client.
	 */
	private void processCancelation() {
		Runnable canceler = new Runnable() {

			public void run() {
				try {
					synchronized (out) {
						// System.err.print("Sending CANCEL\n");
						if (!cancelSent) {
							out.write(prefix + SET_CANCELED + (isCanceled() ? "t" : "f") + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
							out.flush();
						}
						cancelSent = true;
					}
				} catch (IOException e) {
					/* ignore - can't cancel */
				}

			}

		};
		new Thread(canceler).start();
	}

	public void setTaskName(String name) {
		monitor.setTaskName(name);
	}

	public void subTask(String name) {
		monitor.subTask(name);
	}

	public void worked(int work) {
		monitor.worked(work);
	}

	public void clearBlocked() {
		if (monitor instanceof IProgressMonitorWithBlocking)
			((IProgressMonitorWithBlocking) monitor).clearBlocked();
	}

	public void setBlocked(IStatus reason) {
		if (monitor instanceof IProgressMonitorWithBlocking)
			((IProgressMonitorWithBlocking) monitor).setBlocked(reason);
	}

}

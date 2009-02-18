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
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.core.runtime.IStatus;

/**
 * Implementation of the {@link IProgressMonitorWithBlocking} interface that communicates with an instance
 * of an {@link ExternalProgressMonitor} via a communication protocol. This implementation can be used
 * either via arbitrary streams that has to be setup (e.g. can be used with System.out, System.in), or via
 * a socket port. The executing process must get the port from the parent process via the command line.
 * 
 * If a prefix is used in the matching {@link ExternalProgressMonitor} the same prefix must be specified
 * when creating the proxy.
 * 
 * <h4>Usage</h4>
 * Instantiate with a port, or two streams, and optionally set the prefix.
 * Then use this monitor as any other {@link IProgressMonitorWithBlocking}
 * 
 * All operations on the proxy are send via the communication channel to the matching external monitor.
 * The external monitor can be canceled, and the running task should (as always) check {@link IProgressMonitor#isCanceled()}
 * and stop work when it returns true. The proxy will get the cancel flag updated each time the proxy reports
 * progress, but due to lag in the communication the flag may not have been set until the next time progress is
 * reported.
 * 
 * @author henrik.lindberg@cloudsmith.com
 *
 */
public class ExternalProgressProxyMonitor implements IProgressMonitor, IProgressMonitorWithBlocking, ExternalProgressMonitorConstants {

	private boolean canceled;
	private PrintStream out;
	private Socket server;
	private BufferedReader reader;
	private String prefix;
	private boolean closeStreamsOnDone;

	/**
	 * Create a socket based proxy on the port (obtained from the matching external monitor).
	 * Does not use a prefix in the communication.
	 * @param port
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public ExternalProgressProxyMonitor(int port) throws UnknownHostException, IOException {
		this(port, DEFAULT_PREFIX);
	}

	/**
	 * Create a socket based proxy on the port (obtained from the matching external monitor), and
	 * sets a prefix to use in communication. The same prefix must be used in the matching external monitor.
	 * @param port
	 * @throws UnknownHostException
	 * @throws IOException
	 */

	public ExternalProgressProxyMonitor(int port, String prefixString) throws UnknownHostException, IOException {
		canceled = false;
		prefix = prefixString;
		server = new Socket(InetAddress.getLocalHost(), port);
		out = new PrintStream(server.getOutputStream());
		closeStreamsOnDone = true;
		reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
		process();
	}

	/**
	 * Creates a proxy that communicates over the two streams passed as arguments.
	 * No prefix is used.
	 * @param outStream
	 * @param inStream
	 */
	public ExternalProgressProxyMonitor(PrintStream outStream, InputStream inStream) {
		this(outStream, inStream, DEFAULT_PREFIX);
	}

	/**
	 * Creates a proxy that communicates over the two streams passed as arguments, and sets the 
	 * defined prefix. Note that the same prefix must be used in the matching external monitor.
	 * @param outStream
	 * @param inStream
	 */
	public ExternalProgressProxyMonitor(PrintStream outStream, final InputStream inStream, String prefixString) {
		canceled = false;
		prefix = prefixString;
		out = outStream;
		closeStreamsOnDone = false;
		reader = new BufferedReader(new InputStreamReader(inStream));
		process();
	}

	/**
	 * Called internally to handle the communication. Runs in a separate thread.
	 */
	private void process() {

		// Read the input stream to listen for cancel message
		Runnable canceler = new Runnable() {

			public void run() {
				// System.err.print("Proxy command reader started\n");
				String cancelTrue = prefix + SET_CANCELED + "t"; //$NON-NLS-1$
				String cancelFalse = prefix + SET_CANCELED + "f"; //$NON-NLS-1$
				try {
					String data;
					while ((data = reader.readLine()) != null) {
						// System.err.print("Proxy got: " + data + "\n");
						if (data.equals(cancelTrue))
							internalCancel(true);
						else if (data.equals(cancelFalse))
							internalCancel(false);
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
				// System.err.print("Proxy command reader stopped\n");

			}
		};

		new Thread(canceler).start();
	}

	protected void internalCancel(boolean value) {
		canceled = value;
	}

	public void beginTask(String name, int totalWork) {
		report(BEGIN, totalWork, name);
	}

	public void done() {
		report(DONE);
		if (!closeStreamsOnDone)
			return;
		out.close();
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void internalWorked(double work) {
		report(INTERNAL_WORKED, work);
	}

	public boolean isCanceled() {
		return canceled;
	}

	/**
	 * Sends set cancel to parent process and sets this monitor to canceled.
	 */
	public void setCanceled(boolean value) {
		if (value != canceled) {
			canceled = value;
			report(SET_CANCELED, value);
		}
	}

	public void setTaskName(String name) {
		report(SET_TASK_NAME, name);

	}

	public void subTask(String name) {
		report(SUB_TASK, name);

	}

	public void worked(int work) {
		if (work == 1)
			report(WORKED_ONE);
		else
			report(WORKED, work);
	}

	public void clearBlocked() {
		report(CLEAR_BLOCKED);
	}

	public void setBlocked(IStatus reason) {
		report(SET_BLOCKED, reason.getMessage());

	}

	private void report(char type, int value, String message) {
		message = message == null ? "" : message; //$NON-NLS-1$
		synchronized (out) {
			out.print(prefix);
			out.print(type + Integer.toString(value) + "," + message + '\n'); //$NON-NLS-1$
			out.flush();
		}
	}

	private void report(char type) {
		synchronized (out) {
			out.print(prefix);
			out.print(type + "\n"); //$NON-NLS-1$
			out.flush();
		}
	}

	private void report(char type, double value) {
		synchronized (out) {
			out.print(prefix);
			out.print(type + Double.toString(value) + '\n');
			out.flush();
		}
	}

	private void report(char type, int value) {
		synchronized (out) {
			out.print(prefix);
			out.print(type + Integer.toString(value) + '\n');
			out.flush();
		}
	}

	private void report(char type, String value) {
		value = value == null ? "" : value; //$NON-NLS-1$
		synchronized (out) {
			out.print(prefix);
			out.print(type + value + '\n');
			out.flush();
		}
	}

	private void report(char type, boolean value) {
		synchronized (out) {
			out.print(prefix);
			out.print(type + (value ? "t" : "f"));
			out.flush();
		}
	}
}

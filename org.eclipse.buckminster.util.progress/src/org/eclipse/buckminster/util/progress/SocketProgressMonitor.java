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

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * An implementation of {@link ExternalProgressMonitor} that communicates over a socket.
 * <h4>Usage</h4>
 * Instantiate with the parent (real) monitor. Then call {@link #createSocket()} to create the
 * socket and get the port number (as it needs to be passed to the executed process).
 * Then construct the command and call {@link #setCmd(String[]} (and pass the port). Finally call
 * {@link #execute()} to start processing.
 * 
 * @author henrik.lindberg@cloudsmith.com
 *
 */
public class SocketProgressMonitor extends ExternalProgressMonitor {
	private static final int BACKLOG = 1;
	private ServerSocket server;

	public SocketProgressMonitor(IProgressMonitor parentMonitor) {
		super(parentMonitor);
	}

	protected void finalize() throws Throwable {
		// in case the instance got created but never executed, the created socket should be closed.
		try {
			server.close();
		} catch (IOException e) {
			// ignore - it is too late to do anything, and the socket may have been closed already
		} finally {
			super.finalize();
		}
	}

	/**
	 * Creates the server socket to use for communication with the executed process. The executed process
	 * must be informed about the port (hint an argument on the command line).
	 * 
	 * @return the port number for the socket
	 * @throws IOException
	 */
	public int createSocket() throws IOException {
		server = new ServerSocket(0, BACKLOG);
		return server.getLocalPort();
	}

	/**
	 * Execute with communication over the created socket.
	 * 
	 * @param cmd
	 *            array of arguments to {@link Runtime#exec(String[])}. If the first argument is the special LOOPBACK_COMMAND, no external process is started
	 * @return exit status of process, or -1 if it failed to execute
	 */
	public int execute() {

		Runtime r = Runtime.getRuntime();
		try {
			// launch process
			Process process = null;
			if (!cmd[0].equals(LOOPBACK_COMMAND))
				process = r.exec(cmd);
			// wait for it to connect
			Socket client = server.accept();

			out = new OutputStreamWriter(client.getOutputStream());
			processStream(client.getInputStream());
			try {
				if (process != null)
					return process.waitFor();
			} catch (InterruptedException e) {
				// mark thread interrupted and continue
				Thread.currentThread().interrupt();
			}
		} catch (IOException e) {
			// ignore
		}
		return -1; // failed
	}

}

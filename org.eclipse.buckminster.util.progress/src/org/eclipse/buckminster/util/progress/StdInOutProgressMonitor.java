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

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * An {@link ExternalProgressMonitor} implementation that communicates over System.out, System.in
 * It is useful when an external monitor is wanted for batch scripts that are written to echo/output
 * monitoring communication strings.
 * 
 * @author henrik.lindberg@cloudsmith.com
 *
 */
public class StdInOutProgressMonitor extends ExternalProgressMonitor {

	public StdInOutProgressMonitor(IProgressMonitor parentMonitor) {
		super(parentMonitor);
	}

	/**
	 * Execute with communication over stdin/stdout. This is suitable for running scripts that can not use a tcp
	 * connection, but can print output.
	 * 
	 * @param cmd
	 *            array of arguments to {@link Runtime#exec(String[])}
	 * @return exit status of process, or -1 if it failed to execute
	 */
	public int execute() {
		Runtime r = Runtime.getRuntime();
		try {
			Process process = r.exec(cmd);
			sinkStream(process.getErrorStream());
			processStream(process.getInputStream());
			out = new OutputStreamWriter(process.getOutputStream());
			try {
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

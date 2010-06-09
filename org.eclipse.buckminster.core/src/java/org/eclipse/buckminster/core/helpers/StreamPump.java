/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author kolwing
 */
public class StreamPump extends Thread {
	private final InputStream is;

	private final OutputStream os;

	private final boolean flush;

	public StreamPump(InputStream is, OutputStream os) {
		this(is, os, false);
	}

	public StreamPump(InputStream is, OutputStream os, boolean flush) {
		super("StreamPump"); //$NON-NLS-1$
		this.is = is;
		this.os = os;
		this.flush = flush;
	}

	@Override
	public void run() {
		try {
			int c;
			while ((c = is.read()) != -1) {
				os.write(c);
				if (flush || c == '\n')
					os.flush();
			}
		} catch (IOException e) {
			// ignore
		}
	}
}

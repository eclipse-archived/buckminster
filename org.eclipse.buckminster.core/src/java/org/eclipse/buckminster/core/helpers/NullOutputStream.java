/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.io.IOException;
import java.io.OutputStream;

public class NullOutputStream extends OutputStream {
	public static NullOutputStream INSTANCE = new NullOutputStream();

	private NullOutputStream() {
		// all we need is the singleton INSTANCE
	}

	@Override
	public void write(byte[] b) {
		// throw away
	}

	@Override
	public void write(byte[] b, int off, int len) {
		// throw away
	}

	@Override
	public void write(int b) throws IOException {
		// throw away
	}
}

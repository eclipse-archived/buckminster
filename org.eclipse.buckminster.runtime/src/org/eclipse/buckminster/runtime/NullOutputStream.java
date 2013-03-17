/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.runtime;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Thomas Hallgren
 */
public class NullOutputStream extends OutputStream {
	public static NullOutputStream INSTANCE = new NullOutputStream();

	@Override
	public void write(byte[] b, int off, int len) {
		// throw away
	}

	@Override
	public void write(int b) throws IOException {
		// throw away
	}
}

/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.io.Closeable;
import java.io.IOException;

import org.eclipse.buckminster.core.helpers.FileHandle;
import org.eclipse.buckminster.rmap.util.ICatalogReader;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author thhal
 */
public class RemoteFile implements Closeable {
	private final ICatalogReader reader;

	private final String fileName;

	public RemoteFile(ICatalogReader reader, String fileName) {
		this.reader = reader;
		this.fileName = fileName;
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

	public FileHandle getContents(IProgressMonitor monitor) throws CoreException, IOException {
		return ((AbstractCatalogReader) reader).getContents(fileName, monitor);
	}

	@Override
	public String toString() {
		return reader.toString() + ',' + fileName;
	}
}

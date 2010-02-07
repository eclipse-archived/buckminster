/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.jarprocessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public abstract class ProducerThread extends Thread {
	private PipedInputStream reader;

	private PipedOutputStream writer;

	private Throwable exception;

	public ProducerThread(String name) {
		super(name);
	}

	public void drain(JarInfo jarInfo, IOException second) throws CoreException {
		try {
			byte[] drainBuffer = new byte[0x800];
			while (reader.read(drainBuffer) > 0)
				;
		} catch (IOException e) {
			// Ignore during draining. File is probably closed.
		} finally {
			IOUtils.close(reader);
		}

		try {
			join();
		} catch (InterruptedException e) {
			// Ignore
		}

		if (exception != null) {
			if (second != null)
				throw new CoreException(new MultiStatus(Buckminster.PLUGIN_ID, IStatus.OK, new IStatus[] {
						BuckminsterException.createStatus(exception), BuckminsterException.createStatus(second) }, jarInfo.toString(), null));
			throw BuckminsterException.fromMessage(exception, jarInfo.toString());
		}
		if (second != null)
			throw BuckminsterException.fromMessage(second, jarInfo.toString());
	}

	public synchronized InputStream getReaderStream() throws IOException {
		reader = new PipedInputStream();
		notify();
		try {
			wait(2000);
		} catch (InterruptedException e) {
		}
		if (writer == null)
			throw new IOException("No writer"); //$NON-NLS-1$
		return reader;
	}

	@Override
	public void run() {
		try {
			synchronized (this) {
				if (reader == null) {
					try {
						wait(2000);
						if (reader == null)
							throw new IOException("No reader"); //$NON-NLS-1$
					} catch (InterruptedException e) {
					}
				}
				writer = new PipedOutputStream(reader);
				notify();
			}
			internalRun(writer);
		} catch (Throwable e) {
			exception = e;
		} finally {
			IOUtils.close(writer);
		}
	}

	protected abstract void internalRun(OutputStream output) throws Exception;
}

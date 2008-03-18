/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.cache;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.URL;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ecf.filetransfer.FileTransferJob;
import org.eclipse.ecf.filetransfer.IFileTransferListener;
import org.eclipse.ecf.filetransfer.IRetrieveFileTransferContainerAdapter;
import org.eclipse.ecf.filetransfer.events.IFileTransferEvent;
import org.eclipse.ecf.filetransfer.events.IIncomingFileTransferReceiveDoneEvent;
import org.eclipse.ecf.filetransfer.events.IIncomingFileTransferReceiveStartEvent;
import org.eclipse.ecf.filetransfer.identity.FileIDFactory;
import org.eclipse.ecf.filetransfer.identity.IFileID;

/**
 * @author Thomas Hallgren
 */
public class SmallFileReader extends FileTransferJob implements IFileTransferListener
{
	private OutputStream m_outputStream;

	private boolean m_closeWhenDone = false;

	private Exception m_exception;

	public SmallFileReader()
	{
		super("Small file reader");
	}

	public synchronized void handleTransferEvent(IFileTransferEvent event)
	{
		if(event instanceof IIncomingFileTransferReceiveStartEvent)
		{
			try
			{
				((IIncomingFileTransferReceiveStartEvent)event).receive(m_outputStream, this);
			}
			catch(IOException e)
			{
				if(m_closeWhenDone)
					IOUtils.close(m_outputStream);
				m_exception = e;
			}
			return;
		}
		if(event instanceof IIncomingFileTransferReceiveDoneEvent)
		{
			if(m_closeWhenDone)
				IOUtils.close(m_outputStream);

			if(m_exception == null)
				m_exception = ((IIncomingFileTransferReceiveDoneEvent)event).getException();
		}
	}

	public static InputStream readURL(URL url) throws CoreException
	{
		SmallFileReader sfr = new SmallFileReader();
		return sfr.readURLAsync(url);
	}

	public InputStream readURLAsync(URL url) throws CoreException
	{
		IRetrieveFileTransferContainerAdapter adapter = Activator.getDefault().createRetrieveFileTransfer();
		IFileID fileID = FileIDFactory.getDefault().createFileID(adapter.getRetrieveNamespace(), url);
		final PipedInputStream input = new PipedInputStream();
		try
		{
			m_outputStream = new PipedOutputStream(input);
			m_closeWhenDone = true;
		}
		catch(IOException e)
		{
			// Ignore any "already connected" exception. It won't be
		}
		adapter.sendRetrieveRequest(fileID, this, null);
		return new InputStream()
		{
			@Override
			public int read() throws IOException
			{
				checkException();
				return input.read();
			}

			@Override
			public int read(byte b[]) throws IOException
			{
				checkException();
				return input.read(b);
			}

			@Override
			public int read(byte b[], int off, int len) throws IOException
			{
				checkException();
				return input.read(b, off, len);
			}

			@Override
			public long skip(long n) throws IOException
			{
				checkException();
				return input.skip(n);
			}

			@Override
			public int available() throws IOException
			{
				checkException();
				return input.available();
			}

			@Override
			public void close() throws IOException
			{
				checkException();
				input.close();
			}

			@Override
			public synchronized void mark(int readlimit)
			{
				input.mark(readlimit);
			}

			@Override
			public synchronized void reset() throws IOException
			{
				checkException();
				input.reset();
			}

			@Override
			public boolean markSupported()
			{
				return input.markSupported();
			}

			private void checkException() throws IOException
			{
				if(m_exception == null)
					return;

				IOException e;
				Throwable t = BuckminsterException.unwind(m_exception);
				if(t instanceof IOException)
					e = (IOException)t;
				else
				{
					e = new IOException(t.getMessage());
					e.initCause(t);
				}
				throw e;
			}
		};
	}

	public void readURL(URL url, OutputStream outputStream) throws CoreException
	{
		IRetrieveFileTransferContainerAdapter adapter = Activator.getDefault().createRetrieveFileTransfer();
		IFileID fileID = FileIDFactory.getDefault().createFileID(adapter.getRetrieveNamespace(), url);
		try
		{
			m_exception = null;
			m_outputStream = outputStream;
			adapter.sendRetrieveRequest(fileID, this, null);
			if(m_exception == null)
				join();
		}
		catch(InterruptedException e)
		{
		}

		if(m_exception != null)
			throw new CoreException(new Status(IStatus.ERROR, (String)null, IStatus.OK, m_exception.getMessage(),
					m_exception));
	}
}

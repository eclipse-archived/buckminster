/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.cache.download;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.URL;
import java.util.Date;

import org.eclipse.buckminster.cache.Activator;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.FileInfoBuilder;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ecf.filetransfer.FileTransferJob;
import org.eclipse.ecf.filetransfer.IFileTransferListener;
import org.eclipse.ecf.filetransfer.IIncomingFileTransfer;
import org.eclipse.ecf.filetransfer.IRetrieveFileTransferContainerAdapter;
import org.eclipse.ecf.filetransfer.IncomingFileTransferException;
import org.eclipse.ecf.filetransfer.events.IFileTransferEvent;
import org.eclipse.ecf.filetransfer.events.IIncomingFileTransferEvent;
import org.eclipse.ecf.filetransfer.events.IIncomingFileTransferReceiveDataEvent;
import org.eclipse.ecf.filetransfer.events.IIncomingFileTransferReceiveDoneEvent;
import org.eclipse.ecf.filetransfer.events.IIncomingFileTransferReceiveStartEvent;
import org.eclipse.ecf.filetransfer.identity.FileIDFactory;
import org.eclipse.ecf.filetransfer.identity.IFileID;

/**
 * @author Thomas Hallgren
 */
public class FileReader extends FileTransferJob implements IFileTransferListener
{
	private boolean m_closeStreamWhenFinished = false;

	private Exception m_exception;

	private IFileInfo m_fileInfo;

	private long m_lastProgressCount;

	private long m_lastStatsCount;

	private IProgressMonitor m_monitor;

	private boolean m_onlyGetInfo = false;

	private OutputStream m_outputStream;

	private ProgressStatistics m_statistics;

	public FileReader()
	{
		super("URL reader");

		// Hide this job.
		setSystem(true);
		setUser(false);
	}

	public IFileInfo getLastFileInfo()
	{
		return m_fileInfo;
	}

	public synchronized void handleTransferEvent(IFileTransferEvent event)
	{
		IIncomingFileTransfer source = ((IIncomingFileTransferEvent)event).getSource();
		if(event instanceof IIncomingFileTransferReceiveStartEvent)
		{
			try
			{
				FileInfoBuilder fi = new FileInfoBuilder();
				Date lastModified = source.getRemoteLastModified();
				if(lastModified != null)
					fi.setLastModified(lastModified.getTime());
				fi.setName(source.getRemoteFileName());
				fi.setSize(source.getFileLength());
				m_fileInfo = fi;

				if(m_onlyGetInfo)
					((IIncomingFileTransferReceiveStartEvent)event).cancel();
				else
					((IIncomingFileTransferReceiveStartEvent)event).receive(m_outputStream, this);
			}
			catch(IOException e)
			{
				m_exception = e;
				return;
			}

			if(m_monitor != null)
			{
				long fileLength = source.getFileLength();
				m_statistics = new ProgressStatistics(source.getRemoteFileName(), fileLength);
				m_monitor.beginTask(null, 1000);
				m_monitor.subTask(m_statistics.report());
				m_lastStatsCount = 0;
				m_lastProgressCount = 0;
			}
		}
		else if(event instanceof IIncomingFileTransferReceiveDataEvent)
		{
			if(m_monitor != null)
			{
				if(m_monitor.isCanceled())
				{
					source.cancel();
					return;
				}

				long br = source.getBytesReceived();
				long count = br - m_lastStatsCount;
				m_lastStatsCount = br;
				m_statistics.increase(count);
				if(m_statistics.shouldReport())
				{
					count = br - m_lastProgressCount;
					m_lastProgressCount = br;
					m_monitor.subTask(m_statistics.report());
					m_monitor.worked((int)(1000 * count / m_statistics.getTotal()));
				}
			}
		}
		else if(event instanceof IIncomingFileTransferReceiveDoneEvent)
		{
			if(m_closeStreamWhenFinished)
				IOUtils.close(m_outputStream);

			if(m_exception == null)
				m_exception = ((IIncomingFileTransferReceiveDoneEvent)event).getException();
		}
	}

	public InputStream read(URL url) throws CoreException, FileNotFoundException
	{
		final PipedInputStream input = new PipedInputStream();
		PipedOutputStream output;
		try
		{
			output = new PipedOutputStream(input);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		sendRetrieveRequest(url, output, true, false, null);

		return new InputStream()
		{
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
			public boolean markSupported()
			{
				return input.markSupported();
			}

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
			public synchronized void reset() throws IOException
			{
				checkException();
				input.reset();
			}

			@Override
			public long skip(long n) throws IOException
			{
				checkException();
				return input.skip(n);
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

	public IFileInfo readInfo(URL url) throws CoreException, FileNotFoundException
	{
		sendRetrieveRequest(url, null, false, true, null);
		return getLastFileInfo();
	}

	public void readInto(URL url, OutputStream outputStream, IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		try
		{
			sendRetrieveRequest(url, outputStream, false, false, monitor);
			join();
		}
		catch(InterruptedException e)
		{
			monitor.setCanceled(true);
			throw new OperationCanceledException();
		}
		finally
		{
			if(monitor != null)
			{
				if(m_statistics == null)
					//
					// Monitor was never started. See to that it's balanced
					//
					monitor.beginTask(null, 1);
				else
					m_statistics = null;
				monitor.done();
			}
		}
	}

	protected void sendRetrieveRequest(URL url, OutputStream outputStream, boolean closeStreamWhenFinished, boolean onlyGetInfo, IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		IRetrieveFileTransferContainerAdapter adapter = Activator.getDefault().createRetrieveFileTransfer();
		m_exception = null;
		m_closeStreamWhenFinished = closeStreamWhenFinished;
		m_onlyGetInfo = onlyGetInfo;
		m_fileInfo = null;
		m_statistics = null;
		m_lastProgressCount = 0L;
		m_lastStatsCount = 0L;
		m_monitor = monitor;
		m_outputStream = outputStream;

		try
		{
			IFileID fileID = FileIDFactory.getDefault().createFileID(adapter.getRetrieveNamespace(), url);
			adapter.sendRetrieveRequest(fileID, this, null);
		}
		catch(IncomingFileTransferException e)
		{
			m_exception = e;
		}
		if(m_exception != null)
		{
			Throwable t = BuckminsterException.unwind(m_exception);
			if(t instanceof CoreException)
				throw (CoreException)t;
	
			if(t instanceof FileNotFoundException)
				throw (FileNotFoundException)t;
	
			throw BuckminsterException.wrap(m_exception);
		}
	}
}

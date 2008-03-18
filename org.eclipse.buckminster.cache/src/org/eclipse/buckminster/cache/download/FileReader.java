/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.cache.download;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import org.eclipse.buckminster.cache.Activator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ecf.filetransfer.FileTransferJob;
import org.eclipse.ecf.filetransfer.IFileTransferListener;
import org.eclipse.ecf.filetransfer.IIncomingFileTransfer;
import org.eclipse.ecf.filetransfer.IRetrieveFileTransferContainerAdapter;
import org.eclipse.ecf.filetransfer.events.IFileTransferEvent;
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
	private OutputStream m_outputStream;

	private IProgressMonitor m_monitor;

	private ProgressStatistics m_statistics;

	private IIncomingFileTransfer m_incomingFileTransfer;

	private Exception m_exception;

	private long m_lastStatsCount;

	private long m_lastProgressCount;

	public FileReader()
	{
		super("URL reader");

		// Hide this job.
		setSystem(true);
		setUser(false);
	}

	public String getRemoteName()
	{
		return m_incomingFileTransfer.getRemoteFileName();
	}

	public synchronized void handleTransferEvent(IFileTransferEvent event)
	{
		if(event instanceof IIncomingFileTransferReceiveStartEvent)
		{
			try
			{
				m_incomingFileTransfer = ((IIncomingFileTransferReceiveStartEvent)event).receive(m_outputStream, this);
			}
			catch(IOException e)
			{
				m_exception = e;
				return;
			}

			if(m_monitor != null)
			{
				long fileLength = m_incomingFileTransfer.getFileLength();
				m_statistics = new ProgressStatistics(m_incomingFileTransfer.getRemoteFileName(), fileLength);
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
					m_incomingFileTransfer.cancel();
					return;
				}

				long br = m_incomingFileTransfer.getBytesReceived();
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
			if(m_exception == null)
				m_exception = ((IIncomingFileTransferReceiveDoneEvent)event).getException();
		}
	}

	public void readURL(URL url, OutputStream outputStream, IProgressMonitor monitor) throws CoreException
	{
		IRetrieveFileTransferContainerAdapter adapter = Activator.getDefault().createRetrieveFileTransfer();	
		IFileID fileID = FileIDFactory.getDefault().createFileID(adapter.getRetrieveNamespace(), url);
		m_monitor = monitor;
		try
		{
			m_outputStream = outputStream;
			adapter.sendRetrieveRequest(fileID, this, null);
			if(m_exception != null)
				throw new CoreException(new Status(IStatus.ERROR, (String)null, IStatus.OK, m_exception.getMessage(),
						m_exception));

			join();
		}
		catch(InterruptedException e)
		{
		}
		finally
		{
			if(monitor != null)
			{
				m_monitor = null;
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
}

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
				m_exception = e;
			}
			return;
		}
		if(event instanceof IIncomingFileTransferReceiveDoneEvent)
		{
			if(m_exception == null)
				m_exception = ((IIncomingFileTransferReceiveDoneEvent)event).getException();
		}
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

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

public abstract class ProducerThread extends Thread
{
	private PipedInputStream m_reader;

	private PipedOutputStream m_writer;

	private Throwable m_exception;

	private boolean m_okToDrainReader = false;

	public ProducerThread(String name)
	{
		super(name);
	}

	public void drain(JarInfo jarInfo, IOException second) throws CoreException
	{
		try
		{
			while(isAlive())
			{
				join(10);
				if(m_reader.available() > 0)
				{
					// Drain the reader.
					//
					if(m_okToDrainReader || m_exception != null)
						m_reader.skip(m_reader.available());
					else
						m_reader.close();
				}
			}
		}
		catch(IOException e)
		{
		}
		catch(InterruptedException e)
		{
		}
		finally
		{
			IOUtils.close(m_reader);
		}
		if(m_exception != null)
		{
			if(second != null)
				throw new CoreException(new MultiStatus(Buckminster.PLUGIN_ID, IStatus.OK, new IStatus[] {
						BuckminsterException.createStatus(m_exception), BuckminsterException.createStatus(second) },
						jarInfo.toString(), null));
			throw BuckminsterException.fromMessage(m_exception, jarInfo.toString());
		}
		if(second != null)
			throw BuckminsterException.fromMessage(second, jarInfo.toString());
	}

	public synchronized InputStream getReaderStream() throws IOException
	{
		m_reader = new PipedInputStream();
		notify();
		try
		{
			wait(2000);
		}
		catch(InterruptedException e)
		{
		}
		if(m_writer == null)
			throw new IOException("No writer"); //$NON-NLS-1$
		return m_reader;
	}

	public void okToDrainReader()
	{
		m_okToDrainReader = true;
	}

	@Override
	public void run()
	{
		try
		{
			synchronized(this)
			{
				if(m_reader == null)
				{
					try
					{
						wait(2000);
						if(m_reader == null)
							throw new IOException("No reader"); //$NON-NLS-1$
					}
					catch(InterruptedException e)
					{
					}
				}
				m_writer = new PipedOutputStream(m_reader);
				notify();
			}
			internalRun(m_writer);
		}
		catch(Throwable e)
		{
			m_exception = e;
		}
		finally
		{
			IOUtils.close(m_writer);
		}
	}

	protected abstract void internalRun(OutputStream output) throws Exception;
}

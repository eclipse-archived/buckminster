/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Thomas Hallgren
 *
 */
public class ZipArchiveReader extends AbstractCatalogReader
{
	private final IFileReader m_zipFileReader;

	public ZipArchiveReader(IFileReader fileReader)
	{
		super(fileReader.getReaderType(), fileReader.getProviderMatch());
		m_zipFileReader = fileReader;
	}

	@Override
	protected boolean innerExists(String fileName, IProgressMonitor monitor) throws CoreException
	{
		ZipInputStream zi = null;
		try
		{
			ZipEntry ze;
			zi = new ZipInputStream(m_zipFileReader.open(monitor));
			while((ze = zi.getNextEntry()) != null)
				if(ze.getName().equals(fileName))
					return true;
			return false;
		}
		catch(FileNotFoundException e)
		{
			return false;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(zi);
		}
	}

	@Override
	protected <T> T innerReadFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor)
			throws CoreException, IOException
	{
		ZipInputStream zi = null;
		try
		{
			ZipEntry ze;
			zi = new ZipInputStream(m_zipFileReader.open(monitor));
			while((ze = zi.getNextEntry()) != null)
				if(ze.getName().equals(fileName))
					return consumer.consumeStream(this, fileName, zi, new NullProgressMonitor());

			throw new FileNotFoundException(fileName);
		}
		finally
		{
			IOUtils.close(zi);
		}
	}

	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException
	{
		throw new UnsupportedOperationException("ZipArchiveReader cannot materialize");
	}

	@Override
	protected void innerList(List<String> files, IProgressMonitor monitor) throws CoreException
	{
		ZipInputStream zi = null;
		try
		{
			ZipEntry ze;
			zi = new ZipInputStream(m_zipFileReader.open(monitor));
			while((ze = zi.getNextEntry()) != null)
			{
				String name = ze.getName();
				if(name.endsWith("/"))
					name = name.substring(name.length() - 1);
				if(name.indexOf('/', 1) < 0)
				{
					if(ze.isDirectory())
						name = name + "/";
					files.add(name);
				}
			}
		}
		catch(IOException e)
		{
		}
		finally
		{
			IOUtils.close(zi);
		}
	}
}

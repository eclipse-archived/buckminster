/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.internal.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.IEditableModel;
import org.eclipse.pde.internal.core.product.ProductModel;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class EditableProductModel extends ProductModel implements IEditableModel
{
	private static final long serialVersionUID = 4616363133302718206L;

	private final File m_productConfigFile;

	private boolean m_dirty;

	public EditableProductModel(File productConfigFile) throws CoreException
	{
		m_productConfigFile = productConfigFile;
		m_dirty = false;

		InputStream input = null;
		try
		{
			input = new BufferedInputStream(new FileInputStream(productConfigFile));
			load(input, false);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	public boolean isDirty()
	{
		return m_dirty;
	}

	public void save()
	{
		save(m_productConfigFile);
	}

	public void save(File outputFile)
	{
		PrintWriter out = null;
		try
		{
			out = new PrintWriter(new FileWriter(outputFile));
			save(out);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			IOUtils.close(out);
		}
	}

	public void save(PrintWriter writer)
	{
		if(isLoaded())
		{
			writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //$NON-NLS-1$
			writer.println("<?pde version=\"3.5\"?>"); //$NON-NLS-1$
			writer.println();
			getProduct().write("", writer); //$NON-NLS-1$
		}
		setDirty(false);
	}

	public void setDirty(boolean dirty)
	{
		m_dirty = dirty;
	}
}

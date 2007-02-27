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
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.IEditableModel;
import org.eclipse.pde.internal.core.feature.ExternalFeatureModel;

/**
 * The Eclipse external model is not editable.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class ExternalEditableFeatureModel extends ExternalFeatureModel implements IEditableModel
{
	private static final long serialVersionUID = 5818223312516456482L;

	private boolean m_dirty;
	private final File m_externalFile;

	public ExternalEditableFeatureModel(File externalFile)
	{
		m_externalFile = externalFile;
	}

	@Override
	public boolean isEditable()
	{
		return true;
	}

	@Override
	public void load() throws CoreException
	{
		InputStream input = null;
		try
		{
			input = new BufferedInputStream(new FileInputStream(m_externalFile));
			this.load(input, true);
		}
		catch(FileNotFoundException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	public void save()
	{
		PrintWriter output = null;
		try
		{
			output = new PrintWriter(m_externalFile, "UTF-8");
			this.save(output);
		}
		catch(FileNotFoundException e)
		{
			PDEPlugin.getLogger().error("Unable to save feature model", e);
		}
		catch(UnsupportedEncodingException e)
		{
			PDEPlugin.getLogger().error("UTF-8 is not supported", e);
		}
		finally
		{
			IOUtils.close(output);
		}
	}

	public boolean isDirty()
	{
		return m_dirty;
	}

	public void save(PrintWriter writer)
	{
		if(isLoaded())
		{
			writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			feature.write("", writer);
		}
		setDirty(false);
	}

	public void setDirty(boolean dirty)
	{
		m_dirty = dirty;
	}
}

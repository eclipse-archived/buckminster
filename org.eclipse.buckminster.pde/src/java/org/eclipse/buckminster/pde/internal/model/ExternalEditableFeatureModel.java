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
import java.util.Scanner;
import java.util.regex.Pattern;

import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.IEditableModel;
import org.eclipse.pde.internal.core.feature.ExternalFeatureModel;

/**
 * The Eclipse external model is not editable but this subclass is.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class ExternalEditableFeatureModel extends ExternalFeatureModel implements IEditableModel
{
	private static final long serialVersionUID = 5818223312516456482L;

	private int m_contextQualifierLength = -1;
	private boolean m_dirty;

	private final File m_externalFile;

	public ExternalEditableFeatureModel(File externalFile)
	{
		m_externalFile = externalFile;
	}

	public int getContextQualifierLength()
	{
		return m_contextQualifierLength;
	}

	public boolean isDirty()
	{
		return m_dirty;
	}

	@Override
	public boolean isEditable()
	{
		return true;
	}

	private static final Pattern s_ctxQualLenPattern = Pattern.compile("\\scontextQualifierLength\\s*=\\s*(\\d+)\\s");

	public static int getContextQualifierLength(InputStream input)
	{
		int ctxQualLen = -1;
		Scanner scanner = new Scanner(input);
		if(scanner.findWithinHorizon(s_ctxQualLenPattern, 100) != null)
			ctxQualLen = Integer.parseInt(scanner.match().group(1));
		return ctxQualLen;
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

		int ctxQualLen = -1; 
		if(getFeature().getVersion().indexOf('-') > 0)
		{
			try
			{
				ctxQualLen = getContextQualifierLength(new FileInputStream(m_externalFile));
			}
			catch(FileNotFoundException e)
			{
				throw BuckminsterException.wrap(e);
			}
			finally
			{
				IOUtils.close(input);
			}
			m_contextQualifierLength = ctxQualLen;
		}
	}

	public void save()
	{
		try
		{
			save(m_externalFile);
		}
		catch(FileNotFoundException e)
		{
			PDEPlugin.getLogger().error(e, "Unable to save feature model");
		}
	}

	public void save(File outputFile) throws FileNotFoundException
	{
		PrintWriter output = null;
		try
		{
			output = new PrintWriter(outputFile, "UTF-8");
			this.save(output);
		}
		catch(UnsupportedEncodingException e)
		{
			PDEPlugin.getLogger().error(e, "UTF-8 is not supported");
			throw new RuntimeException(e);
		}
		finally
		{
			IOUtils.close(output);
		}
	}

	public void save(PrintWriter writer)
	{
		if(isLoaded())
		{
			writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			if(getFeature().getVersion().indexOf('-') > 0 && m_contextQualifierLength != -1)
				writer.println("<!-- contextQualifierLength=" + m_contextQualifierLength + " -->");
			feature.write("", writer);
		}
		setDirty(false);
	}

	public void setContextQualifierLength(int contextQualifierLength)
	{
		m_contextQualifierLength = contextQualifierLength;
	}

	public void setDirty(boolean dirty)
	{
		m_dirty = dirty;
	}
}

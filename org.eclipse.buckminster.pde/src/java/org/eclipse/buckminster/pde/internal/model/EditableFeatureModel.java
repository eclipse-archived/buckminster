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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.HostSpecification;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.eclipse.pde.core.IEditableModel;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginImport;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.internal.core.feature.ExternalFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeatureImport;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;
import org.eclipse.pde.internal.core.plugin.PluginBase;
import org.eclipse.pde.internal.core.util.VersionUtil;
import org.osgi.framework.Version;

/**
 * The Eclipse external model is not editable but this subclass is.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class EditableFeatureModel extends ExternalFeatureModel implements IEditableModel
{
	private static final long serialVersionUID = 5818223312516456482L;

	private int m_contextQualifierLength = -1;

	private boolean m_dirty;

	private final File m_externalFile;

	public EditableFeatureModel(File externalFile)
	{
		m_externalFile = externalFile;
	}

	public void computeRequiredPlugins() throws CoreException
	{
		ArrayList<IFeatureImport> seen = new ArrayList<IFeatureImport>(feature.getImports().length);
		for(IFeaturePlugin fp : feature.getPlugins())
		{
			IPluginModelBase model = findModel(fp.getId(), fp.getVersion());
			if(model == null)
				continue;

			addRequirements(seen, model.getPluginBase());
			if(model.isFragmentModel())
			{
				HostSpecification hostSpec = model.getBundleDescription().getHost();
				String id = hostSpec.getName();
				String version = null;
				int match = IMatchRules.NONE;
				VersionRange versionRange = hostSpec.getVersionRange();
				if(!(versionRange == null || VersionRange.emptyRange.equals(versionRange)))
				{
					version = versionRange.getMinimum() != null
							? versionRange.getMinimum().toString()
							: null;
					match = PluginBase.getMatchRule(versionRange);
				}
				addRequirement(id, version, match, seen);
			}
		}
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
			load(input, true);
		}
		catch(FileNotFoundException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}

		if(feature == null)
		{
			// Parsing failed but AbstractFeatureParser silently ignores
			// SAXExceptions
			//
			throw BuckminsterException.fromMessage("Unable to parse feature: %s", m_externalFile);
		}

		int ctxQualLen = -1;
		String version = feature.getVersion();
		if(version != null && version.indexOf('-') > 0)
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
		OutputStream output = null;
		try
		{
			output = new FileOutputStream(outputFile);
			this.save(output);
		}
		finally
		{
			IOUtils.close(output);
		}
	}

	public void save(OutputStream output)
	{
		try
		{
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"));
			save(writer);
			writer.flush();
		}
		catch(UnsupportedEncodingException e)
		{
			PDEPlugin.getLogger().error(e, "UTF-8 is not supported");
			throw new RuntimeException(e);
		}
	}

	public void save(PrintWriter writer)
	{
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		if(getFeature().getVersion().indexOf('-') > 0 && m_contextQualifierLength != -1)
			writer.println("<!-- contextQualifierLength=" + m_contextQualifierLength + " -->");
		feature.write("", writer);
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

	private void addRequirement(String id, String version, int match, List<IFeatureImport> seen)
			throws CoreException
	{
		for(IFeatureImport bundle : seen)
			if(bundle.getId().equals(id)
					&& (version == null || (version.equals(bundle.getVersion()) && match == bundle.getMatch())))
				return;

		for(IFeaturePlugin bundle : feature.getPlugins())
			if(VersionUtil.compare(bundle.getId(), bundle.getVersion(), id, version, match))
				return;

		IFeatureImport bundle = feature.getModel().getFactory().createImport();
		bundle.setId(id);
		bundle.setVersion(version);
		bundle.setMatch(match);
		feature.addImports(new IFeatureImport[] { bundle });
		seen.add(bundle);
	}

	private void addRequirements(List<IFeatureImport> seen, IPluginBase plugin)
			throws CoreException
	{
		for(IPluginImport bundle : plugin.getImports())
			addRequirement(bundle.getId(), bundle.getVersion(), bundle.getMatch(), seen);
	}

	private static IPluginModelBase findModel(String id, String version)
	{
		IPluginModelBase unversioned = null;
		for(IPluginModelBase model : PluginRegistry.getActiveModels())
		{
			BundleDescription desc = model.getBundleDescription();
			if(desc == null)
				continue;

			if(desc.getSymbolicName().equals(id))
			{
				Version v = desc.getVersion();
				if(v == null)
				{
					if(version == null)
						return model;
					unversioned = model;
					continue;
				}

				if(version == null || version.equals(v.toString()))
					return model;
			}
		}
		return unversioned;
	}
}
/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.buckminster.pde.internal.FeatureModelReader;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureChild;
import org.eclipse.pde.internal.core.ifeature.IFeatureInfo;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class JNLPGenerator
{
	public static String convertArch(String arch)
	{
		if(arch == null)
			return null;

		if("x86".equalsIgnoreCase(arch)) //$NON-NLS-1$
			return "x86"; //$NON-NLS-1$
		if("PA_RISC".equalsIgnoreCase(arch)) //$NON-NLS-1$
			return "PA_RISC"; //$NON-NLS-1$
		if("ppc".equalsIgnoreCase(arch)) //$NON-NLS-1$
			return "ppc"; //$NON-NLS-1$
		if("sparc".equalsIgnoreCase(arch)) //$NON-NLS-1$
			return "sparc"; //$NON-NLS-1$
		if("x86_64".equalsIgnoreCase(arch)) //$NON-NLS-1$
			return "x86_64"; //$NON-NLS-1$
		if("ia64".equalsIgnoreCase(arch)) //$NON-NLS-1$
			return "ia64"; //$NON-NLS-1$
		if("ia64_32".equalsIgnoreCase(arch)) //$NON-NLS-1$
			return "ia64_32"; //$NON-NLS-1$

		return arch;
	}

	public static String convertOS(String os)
	{
		if(os == null)
			return null;
		if("win32".equalsIgnoreCase(os)) //$NON-NLS-1$
			return "Windows"; //$NON-NLS-1$
		if("macosx".equalsIgnoreCase(os)) //$NON-NLS-1$
			return "Mac"; //$NON-NLS-1$
		if("linux".equalsIgnoreCase(os)) //$NON-NLS-1$
			return "Linux"; //$NON-NLS-1$
		if("solaris".equalsIgnoreCase(os)) //$NON-NLS-1$
			return "Solaris"; //$NON-NLS-1$
		if("hpux".equalsIgnoreCase(os)) //$NON-NLS-1$
			return "HP-UX"; //$NON-NLS-1$
		if("aix".equalsIgnoreCase(os)) //$NON-NLS-1$
			return "AIX"; //$NON-NLS-1$
		return os;
	}

	private String m_codeBase = "$$codebase"; //$NON-NLS-1$

	private final IFeature m_feature;

	private String m_href;

	private boolean m_offLineAllowed = true;

	private String m_specVersion = "1.5+"; //$NON-NLS-1$

	private boolean m_useVersions = true;

	public JNLPGenerator(File featureFile) throws CoreException, FileNotFoundException
	{
		InputStream input = null;
		try
		{
			input = new BufferedInputStream(new FileInputStream(featureFile));
			IFeatureModel featureModel = FeatureModelReader.readFeatureModel(input);
			m_feature = featureModel.getFeature();
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	public JNLPGenerator(IFeature feature)
	{
		m_feature = feature;
	}

	public JNLPModel generateJNLP() throws CoreException
	{
		JNLPModel jnlp = new JNLPModel();
		jnlp.setOffLineAllowed(m_offLineAllowed);
		jnlp.setCodeBase(m_codeBase);
		jnlp.setSpecVersion(m_specVersion);

		if(m_href == null)
			m_href = createFileName(m_feature.getId(), m_feature.getVersion().toString(), ".jnlp"); //$NON-NLS-1$
		jnlp.setHref(m_href);

		jnlp.setTitle(m_feature.getLabel());
		jnlp.setVendor(m_feature.getProviderName());

		if(m_useVersions)
			jnlp.setVersion(m_feature.getVersion().toString());

		IFeatureInfo description = m_feature.getFeatureInfo(IFeature.INFO_DESCRIPTION);
		if(description != null)
			jnlp.setDescription(description.getDescription());

		Map<String, String> j2se = jnlp.createResource(null, null, null, "j2se"); //$NON-NLS-1$
		j2se.put("version", "1.5+"); //$NON-NLS-1$ //$NON-NLS-2$

		for(IFeatureChild include : m_feature.getIncludedFeatures())
		{
			String os = include.getOS();
			if(os == null)
				os = include.getWS();
			Map<String, String> extension = jnlp.createResource(include.getId() + "_F", convertOS(os), //$NON-NLS-1$
					convertArch(include.getArch()), "extension"); //$NON-NLS-1$
			String name = createFileName(include.getId(), include.getVersion(), ".jnlp"); //$NON-NLS-1$
			extension.put("name", name); //$NON-NLS-1$
			extension.put("href", "features/" + name); //$NON-NLS-1$ //$NON-NLS-2$
			if(m_useVersions)
				extension.put("version", include.getVersion()); //$NON-NLS-1$
		}

		for(IFeaturePlugin plugin : m_feature.getPlugins())
		{
			String os = plugin.getOS();
			if(os == null)
				os = plugin.getWS();
			Map<String, String> jar = jnlp.createResource(plugin.getId() + "_B", convertOS(os), convertArch(plugin //$NON-NLS-1$
					.getArch()), "jar"); //$NON-NLS-1$
			jar.put("href", "plugins/" + createFileName(plugin.getId(), plugin.getVersion(), ".jar")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			if(m_useVersions)
				jar.put("version", plugin.getVersion()); //$NON-NLS-1$
		}
		return jnlp;
	}

	public String getCodeBase()
	{
		return m_codeBase;
	}

	public String getHref()
	{
		return m_href;
	}

	public String getSpecVersion()
	{
		return m_specVersion;
	}

	public boolean isOffLineAllowed()
	{
		return m_offLineAllowed;
	}

	public void setCodeBase(String codeBase)
	{
		m_codeBase = codeBase;
	}

	public void setHref(String href)
	{
		m_href = href;
	}

	public void setOffLineAllowed(boolean offLineAllowed)
	{
		m_offLineAllowed = offLineAllowed;
	}

	public void setSpecVersion(String specVersion)
	{
		m_specVersion = specVersion;
	}

	private String createFileName(String id, String version, String extension)
	{
		StringBuilder bld = new StringBuilder();
		bld.append(id);

		// If we use JNLP versioning, we skip the version from the actual file.
		//
		if(!m_useVersions)
		{
			bld.append('_');
			bld.append(version);
		}
		bld.append(extension);
		return bld.toString();
	}
}

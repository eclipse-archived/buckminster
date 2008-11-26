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

		if("x86".equalsIgnoreCase(arch))
			return "x86";
		if("PA_RISC".equalsIgnoreCase(arch))
			return "PA_RISC";
		if("ppc".equalsIgnoreCase(arch))
			return "ppc";
		if("sparc".equalsIgnoreCase(arch))
			return "sparc";
		if("x86_64".equalsIgnoreCase(arch))
			return "x86_64";
		if("ia64".equalsIgnoreCase(arch))
			return "ia64";
		if("ia64_32".equalsIgnoreCase(arch))
			return "ia64_32";

		return arch;
	}

	public static String convertOS(String os)
	{
		if(os == null)
			return null;
		if("win32".equalsIgnoreCase(os))
			return "Windows";
		if("macosx".equalsIgnoreCase(os))
			return "Mac";
		if("linux".equalsIgnoreCase(os))
			return "Linux";
		if("solaris".equalsIgnoreCase(os))
			return "Solaris";
		if("hpux".equalsIgnoreCase(os))
			return "HP-UX";
		if("aix".equalsIgnoreCase(os))
			return "AIX";
		return os;
	}

	private String m_codeBase = "$$codebase";

	private final IFeature m_feature;

	private String m_href;

	private boolean m_offLineAllowed = true;

	private String m_specVersion = "1.5+";

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
			m_href = createFileName(m_feature.getId(), m_feature.getVersion().toString(), ".jnlp");
		jnlp.setHref(m_href);

		jnlp.setTitle(m_feature.getLabel());
		jnlp.setVendor(m_feature.getProviderName());

		if(m_useVersions)
			jnlp.setVersion(m_feature.getVersion().toString());

		IFeatureInfo description = m_feature.getFeatureInfo(IFeature.INFO_DESCRIPTION);
		if(description != null)
			jnlp.setDescription(description.getDescription());

		Map<String, String> j2se = jnlp.createResource(null, null, null, "j2se");
		j2se.put("version", "1.5+");

		for(IFeatureChild include : m_feature.getIncludedFeatures())
		{
			String os = include.getOS();
			if(os == null)
				os = include.getWS();
			Map<String, String> extension = jnlp.createResource(include.getId() + "_F", convertOS(os),
					convertArch(include.getArch()), "extension");
			String name = createFileName(include.getId(), include.getVersion(), ".jnlp");
			extension.put("name", name);
			extension.put("href", "features/" + name);
			if(m_useVersions)
				extension.put("version", include.getVersion());
		}

		for(IFeaturePlugin plugin : m_feature.getPlugins())
		{
			String os = plugin.getOS();
			if(os == null)
				os = plugin.getWS();
			Map<String, String> jar = jnlp.createResource(plugin.getId() + "_B", convertOS(os), convertArch(plugin
					.getArch()), "jar");
			jar.put("href", "plugins/" + createFileName(plugin.getId(), plugin.getVersion(), ".jar"));
			if(m_useVersions)
				jar.put("version", plugin.getVersion());
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

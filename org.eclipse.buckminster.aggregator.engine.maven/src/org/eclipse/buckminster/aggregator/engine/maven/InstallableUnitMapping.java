/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.engine.maven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.MavenItem;
import org.eclipse.buckminster.aggregator.MavenMapping;
import org.eclipse.buckminster.aggregator.engine.maven.pom.DependenciesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Dependency;
import org.eclipse.buckminster.aggregator.engine.maven.pom.License;
import org.eclipse.buckminster.aggregator.engine.maven.pom.LicensesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Model;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Parent;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomFactory;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.p2.metadata.InstallableUnit;
import org.eclipse.equinox.internal.p2.metadata.RequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment;
import org.eclipse.equinox.internal.provisional.p2.metadata.ILicense;
import org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType;
import org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor;

/**
 * @author Filip Hrbek (filip.hrbek@cloudsmith.com)
 * 
 */
// Implement parent subtraction on all relevant getters (as an example, dependencies are implemented that way)
// However, repo optimization is not implemented yet, so it does not make much sense to do it now

public class InstallableUnitMapping implements IInstallableUnit
{
	public enum Type
	{
		TOP, GROUP, IU, PROXY;
	}

	private static final Version DUMMY_VERSION = Version.parseVersion("1");

	private static MavenItem map(String id, List<MavenMapping> mappings) throws CoreException
	{
		MavenItem item = null;

		for(MavenMapping mapping : mappings)
			if((item = mapping.map(id)) != null)
				return item;

		StringBuilder mappingDescriptor = new StringBuilder();
		boolean first = true;
		for(MavenMapping mapping : mappings)
		{
			if(first)
				first = false;
			else
				mappingDescriptor.append(',');
			mappingDescriptor.append(mapping.toString());
		}

		throw BuckminsterException.fromMessage("Unable to map IU to maven artifact: id=%s, mappings=%s", id,
				mappingDescriptor.toString());
	}

	private Type m_type;

	private IInstallableUnit m_installableUnit;

	private List<MavenMapping> m_mappings;

	private MavenItem m_mapped;

	private InstallableUnitMapping m_parent;

	private List<InstallableUnitMapping> m_children = new ArrayList<InstallableUnitMapping>();

	private List<InstallableUnitMapping> m_siblings = new ArrayList<InstallableUnitMapping>();

	private boolean m_transient;

	private IArtifactKey m_mainArtifact;

	public InstallableUnitMapping()
	{
		this((String)null);
	}

	public InstallableUnitMapping(IInstallableUnit iu)
	{
		this(iu, Collections.<MavenMapping> emptyList());
	}

	public InstallableUnitMapping(IInstallableUnit iu, List<MavenMapping> mappings)
	{
		m_mappings = new ArrayList<MavenMapping>(mappings.size() + 1);
		m_mappings.addAll(mappings);
		m_mappings.add(MavenMapping.DEFAULT_MAPPING);

		switch(iu.getArtifacts().length)
		{
		case 1:
			m_mainArtifact = iu.getArtifacts()[0];
			// no break here, we really want to continue initialization
		case 0:
			m_type = Type.IU;
			m_installableUnit = iu;
			break;
		default:
			// We have more than one artifact - we need to make a proxy depending on the artifacts
			InstallableUnitOverrider proxy = new InstallableUnitOverrider(iu);
			IRequiredCapability[] dependencies = new IRequiredCapability[iu.getArtifacts().length];
			int idx = 0;
			for(IArtifactKey artifact : iu.getArtifacts())
			{
				String genId = artifact.getId() + ".artifact-" + (idx + 1);
				dependencies[idx++] = new RequiredCapability(NAMESPACE_IU_ID, genId, new VersionRange(iu.getVersion(),
						true, iu.getVersion(), true), null, false, false);
				InstallableUnitOverrider sibling = new InstallableUnitOverrider(iu);
				sibling.overrideId(genId);
				sibling.overrideArtifacts(new IArtifactKey[] { artifact });

				m_siblings.add(new InstallableUnitMapping(sibling));
			}

			proxy.overrideRequiredCapabilities(dependencies);
			proxy.overrideArtifacts(new IArtifactKey[0]);
			m_installableUnit = proxy;
		}
	}

	public InstallableUnitMapping(String name)
	{
		String groupId = name;
		if(groupId == null)
			groupId = "_top";
		String artifactId = name == null
				? "_common"
				: "_group-common";

		m_type = name == null
				? Type.TOP
				: Type.GROUP;

		InstallableUnit installableUnit = new InstallableUnit();
		installableUnit.setId(artifactId);
		installableUnit.setVersion(DUMMY_VERSION);

		m_installableUnit = installableUnit;
		m_mapped = AggregatorFactory.eINSTANCE.createMavenItem();
		m_mapped.setGroupId(groupId);
		m_mapped.setArtifactId(artifactId);
	}

	@SuppressWarnings("unchecked")
	public POM asPOM() throws CoreException
	{
		POM pom = new POM();
		Model model = pom.getProject();
		if(m_parent != null && !m_parent.isTransient())
		{
			Parent parent = PomFactory.eINSTANCE.createParent();
			parent.setGroupId(m_parent.map().getGroupId());
			parent.setArtifactId(m_parent.map().getArtifactId());
			parent.setVersion(m_parent.getVersion().toString());
			model.setParent(parent);
		}
		model.setGroupId(map().getGroupId());
		model.setArtifactId(map().getArtifactId());
		if(getMainArtifact() == null)
			model.setPackaging("pom");

		if(getVersion() != null && !getVersion().equals(Version.emptyVersion))
			// TODO When version enables "maven-compatible-tostring", use that one instead!
			model.setVersion(getVersion().toString());

		IRequiredCapability[] requiredCapabilities = getRequiredCapabilities();
		if(requiredCapabilities.length > 0)
		{
			DependenciesType dependencies = PomFactory.eINSTANCE.createDependenciesType();
			for(IRequiredCapability requiredCapability : requiredCapabilities)
			{
				// Only dependencies on IUs and OSGi bundles are considered in maven
				if(IInstallableUnit.NAMESPACE_IU_ID.equals(requiredCapability.getNamespace())
						|| "osgi.bundle".equals(requiredCapability.getNamespace()))
				{
					Dependency dependency = PomFactory.eINSTANCE.createDependency();
					dependencies.getDependency().add(dependency);

					MavenItem dependencyMapping = map(requiredCapability.getName(), m_mappings);
					dependency.setGroupId(dependencyMapping.getGroupId());
					dependency.setArtifactId(dependencyMapping.getArtifactId());

					if(requiredCapability.getRange() != null
							&& !requiredCapability.getRange().equals(VersionRange.emptyRange))
						// TODO When version enables "maven-compatible-tostring", use that one instead!
						dependency.setVersion(requiredCapability.getRange().toString());

					if(requiredCapability.isOptional())
						dependency.setOptional(true);
				}
			}

			// it is still possible that no dependency has been found since only dependencies on IUs and OSGi bundles
			// are considered
			// if no mavanizable dependency was found, don't create the dependencies section at all
			if(dependencies.getDependency().size() > 0)
				model.setDependencies(dependencies);
		}

		Map<String, String> iuProperties = new HashMap<String, String>(m_installableUnit.getProperties());
		String name = extractProperty(iuProperties, IInstallableUnit.PROP_NAME);
		String description = extractProperty(iuProperties, IInstallableUnit.PROP_DESCRIPTION);

		if(name != null || description != null)
		{
			if(name != null)
			{
				if(description != null)
				{
					name = name + "\n\n";
				}
			}
			else
				name = "";

			if(description == null)
				description = "";

			model.setDescription(name + description);
		}

		LicensesType licenses = PomFactory.eINSTANCE.createLicensesType();

		ILicense iuLicense = m_installableUnit.getLicense();
		if(iuLicense != null)
		{
			License license = PomFactory.eINSTANCE.createLicense();
			boolean licenseSet = false;
			if(iuLicense.getLocation() != null)
			{
				licenseSet = true;
				license.setUrl(iuLicense.getLocation().toString());
			}
			if(iuLicense.getBody() != null)
			{
				licenseSet = true;
				license.setComments(iuLicense.getBody());
			}
			if(licenseSet)
				licenses.getLicense().add(license);
		}

		ICopyright iuCopyright = m_installableUnit.getCopyright();
		if(iuCopyright != null)
		{
			License copyright = PomFactory.eINSTANCE.createLicense();
			boolean copyrightSet = false;
			if(iuCopyright.getLocation() != null)
			{
				copyrightSet = true;
				copyright.setUrl(iuCopyright.getLocation().toString());
			}
			if(iuCopyright.getBody() != null)
			{
				copyrightSet = true;
				copyright.setComments(iuCopyright.getBody());
			}
			if(copyrightSet)
				licenses.getLicense().add(copyright);
		}

		if(licenses.getLicense().size() > 0)
			model.setLicenses(licenses);

		return pom;
	}

	@SuppressWarnings("unchecked")
	public int compareTo(Object o)
	{
		return m_installableUnit.compareTo(o);
	}

	public IArtifactKey[] getArtifacts()
	{
		return m_installableUnit.getArtifacts();
	}

	public List<InstallableUnitMapping> getChildren()
	{
		return m_children;
	}

	public ICopyright getCopyright()
	{
		return m_installableUnit.getCopyright();
	}

	public String getFilter()
	{
		return m_installableUnit.getFilter();
	}

	public IInstallableUnitFragment[] getFragments()
	{
		return m_installableUnit.getFragments();
	}

	public String getId()
	{
		return m_installableUnit.getId();
	}

	public ILicense getLicense()
	{
		return m_installableUnit.getLicense();
	}

	public IArtifactKey getMainArtifact()
	{
		return m_mainArtifact;
	}

	public IRequiredCapability[] getMetaRequiredCapabilities()
	{
		return m_installableUnit.getMetaRequiredCapabilities();
	}

	public InstallableUnitMapping getParent()
	{
		return m_parent;
	}

	public String getPomName() throws CoreException
	{
		return getFileName("pom");
	}

	@SuppressWarnings("unchecked")
	public Map getProperties()
	{
		return m_installableUnit.getProperties();
	}

	public String getProperty(String key)
	{
		return m_installableUnit.getProperty(key);
	}

	public IProvidedCapability[] getProvidedCapabilities()
	{
		return m_installableUnit.getProvidedCapabilities();
	}

	public String getRelativeFullPath() throws CoreException
	{
		return getRelativePath() + "/" + getArtifactFileName();
	}

	public String getRelativePath() throws CoreException
	{
		// TODO Use mavenized toString
		return map().getGroupId().replace('.', '/') + "/" + map().getArtifactId() + "/" + getVersion().toString();
	}

	public IRequiredCapability[] getRequiredCapabilities()
	{
		if(m_parent != null)
		{
			List<IRequiredCapability> myList = new ArrayList<IRequiredCapability>();
			List<IRequiredCapability> parentList = Arrays.asList(m_parent.m_installableUnit.getRequiredCapabilities());
			for(IRequiredCapability my : m_installableUnit.getRequiredCapabilities())
				if(!parentList.contains(my))
					myList.add(my);

			return myList.toArray(new IRequiredCapability[myList.size()]);
		}

		return m_installableUnit.getRequiredCapabilities();
	}

	public List<InstallableUnitMapping> getSiblings()
	{
		return m_siblings;
	}

	public ITouchpointData[] getTouchpointData()
	{
		return m_installableUnit.getTouchpointData();
	}

	public ITouchpointType getTouchpointType()
	{
		return m_installableUnit.getTouchpointType();
	}

	public Type getType()
	{
		return m_type;
	}

	public IUpdateDescriptor getUpdateDescriptor()
	{
		return m_installableUnit.getUpdateDescriptor();
	}

	public Version getVersion()
	{
		return m_installableUnit.getVersion();
	}

	public boolean isFragment()
	{
		return m_installableUnit.isFragment();
	}

	public boolean isResolved()
	{
		return m_installableUnit.isResolved();
	}

	public boolean isSingleton()
	{
		return m_installableUnit.isSingleton();
	}

	public boolean isTransient()
	{
		return m_transient;
	}

	public MavenItem map() throws CoreException
	{
		if(m_mapped != null)
			return m_mapped;

		return m_mapped = map(getId(), m_mappings);
	}

	public boolean satisfies(IRequiredCapability candidate)
	{
		return m_installableUnit.satisfies(candidate);
	}

	public void setParent(InstallableUnitMapping parent)
	{
		if(m_parent != null)
			m_parent.m_children.remove(this);
		(m_parent = parent).m_children.add(this);
	}

	public void setTransient(boolean isTransient)
	{
		m_transient = isTransient;
	}

	public IInstallableUnit unresolved()
	{
		return m_installableUnit.unresolved();
	}

	private String extractProperty(Map<String, String> iuProperties, String key)
	{
		String value = iuProperties.remove(key);

		if(value != null)
		{
			if(value.startsWith("%"))
			{
				String localizedKey = "df_LT." + value.substring(1);
				String localizedValue = iuProperties.remove(localizedKey);

				if(localizedValue != null)
					value = localizedValue;
			}
		}

		return trimOrNull(value);
	}

	private String getArtifactFileName() throws CoreException
	{
		return getFileName(null);
	}

	private String getFileName(String extension) throws CoreException
	{
		String fileId = getId();
		StringBuilder fileName = new StringBuilder(fileId);
		fileName.append('-');
		// TODO Use mavenized toString
		fileName.append(getVersion().toString());

		if(extension == null)
		{
			if(!(getMainArtifact() != null && "binary".equals(getMainArtifact().getClassifier())))
			{
				fileName.append(".jar");
			}
		}
		else
		{
			fileName.append('.');
			fileName.append(extension);
		}

		return fileName.toString();
	}

	private String trimOrNull(String value)
	{
		if(value != null)
		{
			value = value.trim();
			if(value.length() == 0)
				value = null;
		}
		return value;
	}
}

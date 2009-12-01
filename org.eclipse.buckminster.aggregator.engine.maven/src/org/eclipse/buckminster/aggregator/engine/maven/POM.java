/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.engine.maven;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.buckminster.aggregator.engine.maven.pom.DocumentRoot;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Model;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Parent;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomFactory;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage;
import org.eclipse.buckminster.aggregator.engine.maven.pom.impl.ModelImpl;
import org.eclipse.buckminster.aggregator.engine.maven.pom.util.PomResourceFactoryImpl;
import org.eclipse.buckminster.aggregator.util.GeneralUtils;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;

/**
 * @author Filip Hrbek (filip.hrbek@cloudsmith.com)
 * 
 */
public class POM
{
	private static final int MAX_CACHE_SIZE = 10;

	private static final HashMap<URI, POM> s_pomCacheLRU = new LinkedHashMap<URI, POM>(MAX_CACHE_SIZE, 0.75f, true)
	{
		private static final long serialVersionUID = 1L;

		protected boolean removeEldestEntry(Map.Entry<URI, POM> entry)
		{
			return size() >= MAX_CACHE_SIZE;
		}
	};

	public static final String XML_SCHEMA_LOCATION = "http://maven.apache.org/maven-v4_0_0.xsd";

	public static String expandProperties(String str, Map<String, String> propertyMap)
	{
		if(str == null)
			return "";

		int pos;
		int from = 0;
		while((pos = str.indexOf('$', from)) != -1)
		{
			int len = str.length();
			if(len > pos && str.charAt(pos + 1) == '{')
			{
				int end = str.indexOf('}', pos + 2);
				if(end != -1)
				{
					String varValue = propertyMap.get(str.substring(pos + 2, end));
					if(varValue != null)
					{
						StringBuilder newStr = new StringBuilder(str.substring(0, pos));
						newStr.append(varValue);
						newStr.append(str.substring(end));
						str = newStr.toString();
					}
					from = end + 1;
				}
				else
					break;
			}
			else
				from = pos + 1;
		}

		return str;
	}

	synchronized public static POM getPOM(String repoLocation, String groupId, String artifactId, String version)
			throws CoreException
	{
		URI uri = URI.createURI(repoLocation + "/" + createRelativePath(groupId, artifactId, version));
		POM pom = s_pomCacheLRU.get(uri);
		if(pom != null)
			return pom;

		s_pomCacheLRU.put(uri, pom = new POM(uri, repoLocation));

		return pom;
	}

	private static String createRelativePath(String groupId, String artifactId, String version)
	{
		groupId = GeneralUtils.trimmedOrNull(groupId);
		artifactId = GeneralUtils.trimmedOrNull(artifactId);
		version = GeneralUtils.trimmedOrNull(version);
		return (groupId != null
				? groupId.replace('.', '/')
				: groupId) + "/" + artifactId + "/" + version + "/" + artifactId + "-" + version + ".pom";
	}

	private String m_repoRoot;

	private ResolvedModel m_resolvedModel;

	private DocumentRoot m_documentRoot;

	private POM m_parentPOM;

	public POM()
	{
		m_documentRoot = PomFactory.eINSTANCE.createDocumentRoot();
		m_documentRoot.getXSISchemaLocation().put(PomPackage.eNS_URI, XML_SCHEMA_LOCATION);
		m_documentRoot.setProject(PomFactory.eINSTANCE.createModel());
	}

	private POM(URI uri, String repoRoot) throws CoreException
	{
		Resource resource = getResourceSet().getResource(uri, true);
		EList<EObject> content = resource.getContents();
		if(content.size() != 1)
			throw BuckminsterException.fromMessage("ECore Resource did not contain one resource. It had %d",
					Integer.valueOf(content.size()));

		m_documentRoot = (DocumentRoot)content.get(0);
		Diagnostic diag = Diagnostician.INSTANCE.validate(m_documentRoot);

		int modifiedSeverity = Diagnostic.OK;
		if(diag.getSeverity() >= Diagnostic.ERROR)
		{
			Logger log = Buckminster.getLogger();
			for(Diagnostic childDiag : diag.getChildren())
			{
				String childMessage = childDiag.getMessage();
				if(!childMessage.matches("The feature 'mixed' of 'org.eclipse.buckminster.aggregator.engine.maven.pom.impl.DocumentRootImpl@"
						+ "[0-9a-f]+\\{[^\\}]+.pom#/\\}' with 2 element values must have exactly 1 element value"))
				{
					log.error(childDiag.getMessage());
					if(modifiedSeverity < childDiag.getSeverity())
						modifiedSeverity = childDiag.getSeverity();
				}
			}
		}

		if(modifiedSeverity >= Diagnostic.ERROR)
			throw BuckminsterException.fromMessage("Maven POM model validation failed: %s", diag.getMessage());

		m_repoRoot = repoRoot;
		Model resolvedModel = getResolvedProject();
		String relativePath = "/"
				+ createRelativePath(resolvedModel.getGroupId(), resolvedModel.getArtifactId(),
						resolvedModel.getVersion());
		String uriStr = uri.toString();
		if(!uriStr.endsWith(relativePath))
			throw BuckminsterException.fromMessage("Invalid path, %s should end with %s", uriStr, relativePath);
	}

	public String getArtifactId() throws CoreException
	{
		return getResolvedProject().getArtifactId();
	}

	public String getGroupId() throws CoreException
	{
		return getResolvedProject().getGroupId();
	}

	public POM getParentPOM() throws CoreException
	{
		if(m_parentPOM != null)
			return m_parentPOM;

		Model model = getProject();
		Parent parent = model.getParent();
		if(parent != null)
			return getPOM(m_repoRoot, parent.getGroupId(), parent.getArtifactId(), parent.getVersion());

		return null;
	}

	public Model getProject() throws CoreException
	{
		if(m_documentRoot == null || m_documentRoot.getProject() == null)
			throw BuckminsterException.fromMessage("No project available");

		return m_documentRoot.getProject();
	}

	public Map<String, String> getProperties() throws CoreException
	{
		return getResolvedProject().getPropertyMap();
	}

	public ResolvedModel getResolvedProject() throws CoreException
	{
		if(m_resolvedModel == null)
			m_resolvedModel = new ResolvedModel(m_repoRoot, (ModelImpl)getProject());

		return m_resolvedModel;
	}

	public String getVersion() throws CoreException
	{
		return getResolvedProject().getVersion();
	}

	public void save() throws CoreException
	{
		save((Resource)null);
	}

	public void save(Resource resource) throws CoreException
	{
		Resource targetResource = resource;

		if(targetResource == null)
			targetResource = m_documentRoot.eResource();

		if(targetResource == null)
			throw BuckminsterException.fromMessage("No resource to store Maven POM");

		if(resource != null && !targetResource.equals(m_documentRoot.eResource()))
			targetResource.getContents().add(m_documentRoot);

		try
		{
			targetResource.save(Collections.emptyMap());
		}
		catch(IOException e)
		{
			throw BuckminsterException.fromMessage(e, "Unable to save Maven POM");
		}
	}

	public void save(URI uri) throws CoreException
	{
		save(getResourceSet().createResource(uri));
	}

	private ResourceSet getResourceSet()
	{
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.setResourceFactoryRegistry(new ResourceFactoryRegistryImpl()
		{
			@Override
			public Resource.Factory getFactory(URI uri)
			{
				return new PomResourceFactoryImpl();
			}

			@Override
			public Resource.Factory getFactory(URI uri, String contentType)
			{
				return getFactory(uri);
			}
		});

		return resourceSet;
	}
}

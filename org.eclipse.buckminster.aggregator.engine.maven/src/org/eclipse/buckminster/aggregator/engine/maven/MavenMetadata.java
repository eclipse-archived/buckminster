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

import org.eclipse.buckminster.aggregator.engine.maven.metadata.DocumentRoot;
import org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData;
import org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataFactory;
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
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;

/**
 * @author Filip Hrbek (filip.hrbek@cloudsmith.com)
 * 
 */
public class MavenMetadata
{
	private DocumentRoot m_documentRoot;

	public MavenMetadata()
	{
		m_documentRoot = MetadataFactory.eINSTANCE.createDocumentRoot();
		m_documentRoot.setMetadata(MetadataFactory.eINSTANCE.createMetaData());
	}

	public MavenMetadata(URI uri) throws CoreException
	{
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(uri, true);
		EList<EObject> content = resource.getContents();
		if(content.size() != 1)
			throw BuckminsterException.fromMessage("ECore Resource did not contain one resource. It had %d",
					Integer.valueOf(content.size()));

		m_documentRoot = (DocumentRoot)content.get(0);
		Diagnostic diag = Diagnostician.INSTANCE.validate(m_documentRoot);
		if(diag.getSeverity() == Diagnostic.ERROR)
		{
			Logger log = Buckminster.getLogger();
			for(Diagnostic childDiag : diag.getChildren())
				log.error(childDiag.getMessage());
			throw BuckminsterException.fromMessage("Maven Metadata model validation failed: %s", diag.getMessage());
		}
	}

	public MetaData getMetaData() throws CoreException
	{
		if(m_documentRoot == null || m_documentRoot.getMetadata() == null)
			throw BuckminsterException.fromMessage("No metadata available");

		return m_documentRoot.getMetadata();
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
			throw BuckminsterException.fromMessage("No resource to store Maven Metadata");

		if(resource != null && !targetResource.equals(m_documentRoot.eResource()))
			targetResource.getContents().add(m_documentRoot);

		try
		{
			targetResource.save(Collections.emptyMap());
		}
		catch(IOException e)
		{
			throw BuckminsterException.fromMessage(e, "Unable to save Maven Metadata");
		}
	}

	public void save(URI uri) throws CoreException
	{
		ResourceSet resourceSet = new ResourceSetImpl();
		save(resourceSet.createResource(uri));
	}
}

/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.model;

import java.util.Map;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.core.mspec.IMaterializationDirective;
import org.eclipse.buckminster.core.mspec.builder.MaterializationDirectiveBuilder;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public abstract class MaterializationDirective extends AbstractSaxableElement implements IMaterializationDirective
{
	public static final String ATTR_INSTALL_LOCATION = "installLocation"; //$NON-NLS-1$

	public static final String ATTR_WORKSPACE_LOCATION = "workspaceLocation"; //$NON-NLS-1$

	public static final String ATTR_MATERIALIZER = "materializer"; //$NON-NLS-1$

	public static final String ATTR_CONFLICT_RESOLUTION = "conflictResolution"; //$NON-NLS-1$

	public static final String ATTR_MAX_PARALLEL_JOBS = "maxParallelJobs"; //$NON-NLS-1$

	private final int m_maxParallelJobs;

	private final Map<String, String> m_properties;

	private final IPath m_installLocation;

	private final IPath m_workspaceLocation;

	private final String m_materializer;

	private final ConflictResolution m_conflictResolution;

	private final Documentation m_documentation;

	public MaterializationDirective(MaterializationDirectiveBuilder builder)
	{
		m_documentation = builder.getDocumentation();

		IPath tmp = builder.getInstallLocation();
		m_installLocation = (tmp == null)
				? null
				: tmp.addTrailingSeparator();

		tmp = builder.getWorkspaceLocation();
		m_workspaceLocation = (tmp == null)
				? null
				: tmp.addTrailingSeparator();

		m_materializer = builder.getMaterializerID();
		m_conflictResolution = builder.getConflictResolution();
		m_properties = ExpandingProperties.createUnmodifiableProperties(builder.getProperties());
		m_maxParallelJobs = builder.getMaxParallelJobs();
	}

	public ConflictResolution getConflictResolution()
	{
		return m_conflictResolution;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public IPath getInstallLocation()
	{
		return m_installLocation;
	}

	public String getMaterializerID()
	{
		return m_materializer;
	}

	public int getMaxParallelJobs()
	{
		return m_maxParallelJobs;
	}

	public Map<String, String> getProperties()
	{
		return m_properties;
	}

	public IPath getWorkspaceLocation()
	{
		return m_workspaceLocation;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		if(m_installLocation != null)
			Utils.addAttribute(attrs, ATTR_INSTALL_LOCATION, m_installLocation.toPortableString());
		if(m_workspaceLocation != null)
			Utils.addAttribute(attrs, ATTR_WORKSPACE_LOCATION, m_workspaceLocation.toPortableString());
		if(m_materializer != null)
			Utils.addAttribute(attrs, ATTR_MATERIALIZER, m_materializer);
		if(m_conflictResolution != null)
			Utils.addAttribute(attrs, ATTR_CONFLICT_RESOLUTION, m_conflictResolution.name());
		if(m_maxParallelJobs != -1)
			Utils.addAttribute(attrs, ATTR_MAX_PARALLEL_JOBS, Integer.toString(m_maxParallelJobs));
	}

	@Override
	protected void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException
	{
		if(m_documentation != null)
			m_documentation.toSax(receiver, namespace, prefix, m_documentation.getDefaultTag());
		SAXEmitter.emitProperties(receiver, m_properties, namespace, prefix, true, false);
	}
}

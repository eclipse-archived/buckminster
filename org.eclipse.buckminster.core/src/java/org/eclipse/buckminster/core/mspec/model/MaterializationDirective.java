/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
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
public abstract class MaterializationDirective extends AbstractSaxableElement implements IMaterializationDirective {
	public static final String ATTR_INSTALL_LOCATION = "installLocation"; //$NON-NLS-1$

	public static final String ATTR_WORKSPACE_LOCATION = "workspaceLocation"; //$NON-NLS-1$

	public static final String ATTR_MATERIALIZER = "materializer"; //$NON-NLS-1$

	public static final String ATTR_CONFLICT_RESOLUTION = "conflictResolution"; //$NON-NLS-1$

	public static final String ATTR_MAX_PARALLEL_JOBS = "maxParallelJobs"; //$NON-NLS-1$

	private final int maxParallelJobs;

	private final Map<String, String> properties;

	private final IPath installLocation;

	private final IPath workspaceLocation;

	private final String materializer;

	private final ConflictResolution conflictResolution;

	private final Documentation documentation;

	public MaterializationDirective(MaterializationDirectiveBuilder builder) {
		documentation = builder.getDocumentation();

		IPath tmp = builder.getInstallLocation();
		installLocation = (tmp == null) ? null : tmp.addTrailingSeparator();

		tmp = builder.getWorkspaceLocation();
		workspaceLocation = (tmp == null) ? null : tmp.addTrailingSeparator();

		materializer = builder.getMaterializerID();
		conflictResolution = builder.getConflictResolution();
		properties = ExpandingProperties.createUnmodifiableProperties(builder.getProperties());
		maxParallelJobs = builder.getMaxParallelJobs();
	}

	@Override
	public ConflictResolution getConflictResolution() {
		return conflictResolution;
	}

	@Override
	public Documentation getDocumentation() {
		return documentation;
	}

	@Override
	public IPath getInstallLocation() {
		return installLocation;
	}

	@Override
	public String getMaterializerID() {
		return materializer;
	}

	@Override
	public int getMaxParallelJobs() {
		return maxParallelJobs;
	}

	@Override
	public Map<String, String> getProperties() {
		return properties;
	}

	@Override
	public IPath getWorkspaceLocation() {
		return workspaceLocation;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		if (installLocation != null)
			Utils.addAttribute(attrs, ATTR_INSTALL_LOCATION, installLocation.toPortableString());
		if (workspaceLocation != null)
			Utils.addAttribute(attrs, ATTR_WORKSPACE_LOCATION, workspaceLocation.toPortableString());
		if (materializer != null)
			Utils.addAttribute(attrs, ATTR_MATERIALIZER, materializer);
		if (conflictResolution != null)
			Utils.addAttribute(attrs, ATTR_CONFLICT_RESOLUTION, conflictResolution.name());
		if (maxParallelJobs != -1)
			Utils.addAttribute(attrs, ATTR_MAX_PARALLEL_JOBS, Integer.toString(maxParallelJobs));
	}

	@Override
	protected void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException {
		if (documentation != null)
			documentation.toSax(receiver, namespace, prefix, documentation.getDefaultTag());
		SAXEmitter.emitProperties(receiver, properties, namespace, prefix, true, false);
	}
}

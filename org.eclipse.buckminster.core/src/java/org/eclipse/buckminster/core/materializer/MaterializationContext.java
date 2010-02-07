/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import java.util.Map;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.IComponentName;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.UnmodifiableMapUnion;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.IMaterializationNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class MaterializationContext extends RMContext {
	private final BillOfMaterials bom;

	private final MaterializationSpec materializationSpec;

	private final MaterializationStatistics statistics = new MaterializationStatistics();

	private boolean rebootNeeded = false;

	private boolean tagsInitialized = false;

	public MaterializationContext(BillOfMaterials bom, MaterializationSpec mspec) {
		super(mspec.getProperties());
		this.bom = bom;
		this.materializationSpec = mspec;
	}

	public MaterializationContext(BillOfMaterials bom, MaterializationSpec mspec, RMContext context) {
		super(new UnmodifiableMapUnion<String, Object>(mspec.getProperties(), context), context);
		this.bom = bom;
		this.materializationSpec = mspec;
	}

	/**
	 * Returns the designated full path to the installed artifact for the
	 * resolution. This is a shortcut for
	 * 
	 * <pre>
	 * getInstallLocation(resolution).append(getLeafArtifact(resolution))
	 * </pre>
	 * 
	 * @param resolution
	 *            The resolution for which we want the artifact location
	 * @return An absolute path in the local file system.
	 * @throws CoreException
	 */
	public IPath getArtifactLocation(Resolution resolution) throws CoreException {
		IPath installLocation = getInstallLocation(resolution);
		IPath leafArtifact = getLeafArtifact(resolution);
		if (leafArtifact == null)
			installLocation = installLocation.addTrailingSeparator();
		else
			installLocation = installLocation.append(leafArtifact);
		return installLocation;
	}

	public BillOfMaterials getBillOfMaterials() {
		return bom;
	}

	@Override
	public ComponentQuery getComponentQuery() {
		return bom.getQuery();
	}

	/**
	 * Returns the install location for the resolution as specified in the
	 * {@link MaterializationSpec} or the default location if it is not
	 * specified.
	 * 
	 * @param resolution
	 *            The resolution for which we want the install location
	 * @return An absolute path in the local file system.
	 * @throws CoreException
	 */
	public IPath getInstallLocation(Resolution resolution) throws CoreException {
		IReaderType rd = materializationSpec.getMaterializer(resolution).getMaterializationReaderType(resolution);
		IPath relativeLocation = rd.getInstallLocation(resolution, this);

		if (relativeLocation == null) {
			IComponentType cType = resolution.getComponentType();
			if (cType != null)
				relativeLocation = cType.getRelativeLocation();
		}

		if (relativeLocation != null) {
			IPath tmp = expand(relativeLocation);
			if (tmp.isAbsolute())
				return tmp;
		}

		IMaterializationNode node = materializationSpec.getMatchingNode(resolution);
		IPath nodeLocation = null;
		boolean useRootDefault = true;
		if (node != null) {
			nodeLocation = node.getInstallLocation();
			String materializerId = node.getMaterializerID();
			useRootDefault = (materializerId == null || materializerId.equals(materializationSpec.getMaterializerID()));

			if (nodeLocation != null) {
				if (relativeLocation != null)
					relativeLocation = nodeLocation.append(relativeLocation);
				else
					relativeLocation = nodeLocation;

				IPath tmp = expand(relativeLocation);
				if (tmp.isAbsolute())
					return tmp;
			}
		}

		IPath location = null;
		if (useRootDefault)
			location = materializationSpec.getInstallLocation();

		if (location == null)
			location = materializationSpec.getMaterializer(resolution).getDefaultInstallRoot(this, resolution);

		if (relativeLocation != null)
			location = location.append(relativeLocation);
		return expand(location);
	}

	public IPath getLeafArtifact(Resolution resolution) throws CoreException {
		MaterializationSpec mspec = getMaterializationSpec();
		IPath leaf = mspec.getLeafArtifact(resolution);
		boolean isExpand = mspec.isExpand(resolution);

		if (leaf != null) {
			// MSpec always take precedence
			//
			if (isExpand)
				leaf = leaf.addTrailingSeparator();
			return leaf;
		}

		IReaderType rd = mspec.getMaterializer(resolution).getMaterializationReaderType(resolution);
		if (isExpand)
			//
			// We only name files, not expanded folders
			//
			return null;

		leaf = rd.getLeafArtifact(resolution, this);
		if (leaf == null) {
			// No filename is available, let's use a name built from
			// <componentname>_<version>
			//
			IComponentIdentifier ci = resolution.getComponentIdentifier();
			StringBuilder nameBld = new StringBuilder(ci.getName());
			Version version = ci.getVersion();
			if (version != null) {
				nameBld.append('_');
				nameBld.append(version);
			}
			nameBld.append(".dat"); //$NON-NLS-1$
			leaf = Path.fromPortableString(nameBld.toString());
			if (leaf.segmentCount() > 1)
				leaf = leaf.removeFirstSegments(leaf.segmentCount() - 1);
		}
		return leaf;
	}

	public MaterializationSpec getMaterializationSpec() {
		return materializationSpec;
	}

	public MaterializationStatistics getMaterializationStatistics() {
		return statistics;
	}

	public int getMaxParallelJobs() {
		int maxParallelJobs = materializationSpec.getMaxParallelJobs();
		if (maxParallelJobs == -1)
			maxParallelJobs = MaterializationJob.getMaxParallelJobs();
		return maxParallelJobs;
	}

	@Override
	public Map<String, ? extends Object> getProperties(ComponentName cName) {
		Map<String, ? extends Object> p = super.getProperties(cName);
		IMaterializationNode node = materializationSpec.getMatchingNode(cName);
		return node == null ? p : new UnmodifiableMapUnion<String, Object>(node.getProperties(), p);
	}

	public Map<String, ? extends Object> getProperties(Resolution resolution) {
		Map<String, ? extends Object> p = super.getProperties(resolution.getComponentIdentifier());
		IMaterializationNode node = materializationSpec.getMatchingNode(resolution);
		return node == null ? p : new UnmodifiableMapUnion<String, Object>(node.getProperties(), p);
	}

	public String getSuffixedName(Resolution resolution, String remoteName) throws CoreException {
		MaterializationSpec mspec = getMaterializationSpec();
		IComponentName cName = resolution.getComponentIdentifier();
		if (!(resolution.isUnpack() || mspec.isUnpack(resolution)))
			return null;

		String name = mspec.getSuffix(resolution);
		if (name == null)
			name = remoteName;

		if (name == null) {
			IReaderType rd = resolution.getProvider().getReaderType();
			IPath leaf = rd.getLeafArtifact(resolution, this);
			if (leaf == null || leaf.segmentCount() == 0)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.Unable_to_determine_suffix_for_unpack_of_0, cName));
			name = leaf.segment(0);
		}
		return name;
	}

	public IPath getWorkspaceLocation(Resolution resolution) throws CoreException {
		IPath nodeLocation = null;
		ComponentIdentifier ci = resolution.getComponentIdentifier();
		IMaterializationNode node = materializationSpec.getMatchingNode(resolution);
		if (node != null) {
			nodeLocation = node.getWorkspaceLocation();
			if (nodeLocation != null) {
				nodeLocation = Path.fromOSString(ExpandingProperties.expand(getProperties(resolution), nodeLocation.toOSString(), 0));
				IPath tmp = expand(nodeLocation);
				if (tmp.isAbsolute())
					return tmp;
			}
		}

		IPath rootLocation = materializationSpec.getWorkspaceLocation();
		if (rootLocation == null) {
			if (nodeLocation != null)
				//
				// At this point the nodeLocation must be relative so this
				// is illegal.
				//
				throw BuckminsterException.fromMessage(NLS.bind(Messages.WorkspaceLocation_0_in_node_matching_1_cannot_be_relative_unless,
						nodeLocation, ci));

			// Default to location of current workspace
			//
			return ResourcesPlugin.getWorkspace().getRoot().getLocation();
		}

		return expand((nodeLocation == null) ? rootLocation : rootLocation.append(nodeLocation));
	}

	/**
	 * If the target platform materializer installs things into the current
	 * runtime, this flag will be set to <code>true</code>.
	 * 
	 * @return <code>true</code> if a materializer altered the current runtime
	 *         platform.
	 */
	public boolean isRebootNeeded() {
		return rebootNeeded;
	}

	/**
	 * Set by the target platform materializer when it installs new features
	 * into the default target platform (the one currently in use).
	 * 
	 * @param flag
	 */
	public void setRebootNeeded(boolean flag) {
		rebootNeeded = flag;
	}

	@Override
	protected void initializeAllTagInfos() {
		if (!tagsInitialized) {
			addTagInfosFromBom(null);
			tagsInitialized = true;
		}
	}

	@Override
	protected void initializeTagInfo(IComponentRequest request) {
		addTagInfosFromBom(request);
	}

	private void addTagInfosFromBom(IComponentRequest request) {
		addTagInfosFromNode(bom.getQuery().getTagInfo(), bom, request);
	}

	private void addTagInfosFromNode(String tagInfo, BOMNode node, IComponentRequest request) {
		ComponentRequest nodeRequest = node.getRequest();
		if (hasTagInfo(nodeRequest))
			// Tag info already generated
			return;

		Resolution res = node.getResolution();
		if (res == null || IReaderType.ECLIPSE_PLATFORM.equals(res.getProvider().getReaderTypeId()))
			return;

		addTagInfo(nodeRequest, tagInfo);
		if (node.getRequest().equals(request))
			return;
		String childTagInfo = res.getCSpec().getTagInfo(tagInfo);
		for (BOMNode child : node.getChildren())
			addTagInfosFromNode(childTagInfo, child, request);
	}

	private IPath expand(IPath path) {
		return Path.fromOSString(ExpandingProperties.expand(this, path.toOSString(), 0));
	}
}

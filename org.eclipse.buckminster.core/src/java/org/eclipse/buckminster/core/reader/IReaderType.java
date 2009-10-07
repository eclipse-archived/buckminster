/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.IBuckminsterExtension;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author thhal
 */
public interface IReaderType extends IBuckminsterExtension
{
	public static final String ECLIPSE_PLATFORM = "eclipse.platform"; //$NON-NLS-1$

	public static final String ECLIPSE_IMPORT = "eclipse.import"; //$NON-NLS-1$

	public static final String ECLIPSE_SITE_FEATURE = "site.feature"; //$NON-NLS-1$

	public static final String LOCAL = "local"; //$NON-NLS-1$

	public static final String URL = "url"; //$NON-NLS-1$

	public static final String URL_ZIPPED = "url.zipped"; //$NON-NLS-1$

	public static final String URL_CATALOG = "url.catalog"; //$NON-NLS-1$

	/**
	 * Some reader types have characteristics that makes it convenient to create a default materialization node. A
	 * typical example is the 'url.zipped' that will add a node that will unzip the node when it is materialized.
	 * 
	 * @param bld
	 *            The spec builder
	 * @param res
	 *            The resolution
	 * @throws CoreException
	 */
	void addMaterializationNode(MaterializationSpecBuilder bld, Resolution res) throws CoreException;

	/**
	 * There might be minor difference in how repository locators are defined in a team repository provider and what
	 * whould be expected by the PDE IFetchFactory. This method expects the latter as input and will convert it to the
	 * former.
	 * 
	 * @param fetchFactoryLocator
	 *            The locator in IFetchFactory format.
	 * @param componentName
	 *            The name of the component
	 * @return The locator in team repository format.
	 * @throws CoreException
	 */
	String convertFetchFactoryLocator(Map<String, String> fetchFactoryLocator, String componentName)
			throws CoreException;

	/**
	 * Convert a team project set style locator into a URL.
	 * 
	 * @param repositoryLocator
	 *            The locator to convert
	 * @param versionSelector
	 *            The version selector used
	 * @return The URL or <code>null</code> if this reader type does not support URL retrieval.
	 * @throws CoreException
	 */
	URL convertToURL(String repositoryLocator, VersionMatch versionSelector) throws CoreException;

	/**
	 * The team ProjectSetCapability does not expose any method for extracting the actual repository reference (i.e. the
	 * URL in case of SVN or the CVSROOT in case of CVS).
	 * 
	 * @param reference
	 *            The provider specific PSF project reference
	 * @return The locator in a format suitable for the uri of this reader type
	 * @throws CoreException
	 */
	ReferenceInfo extractReferenceInfo(String reference) throws CoreException;

	/**
	 * Returns the <code>URL</code> of the remote artifact for the given <code>resolution</code> or <code>null</code> if
	 * no such <code>URL</code> can be presented.
	 * 
	 * @param resolution
	 *            The artifact resolution
	 * @param context
	 *            The materialization context for the materialization
	 * @return The <code>URL</code> of the remote artifact or <code>null</code> if no such URL can be presented
	 * @throws CoreException
	 */
	URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException;

	/**
	 * Some components, such as the ones present in the target platform, are not materialized. Instead, they have a
	 * fixed location.
	 * 
	 * @param cr
	 *            The component resoltion denoting the component in question.
	 * @return The fixed location or <code>null</code> if not applicable.
	 */
	IPath getFixedLocation(Resolution cr) throws CoreException;

	/**
	 * Returns the default install location for the materialized component. The returned location is a the folder where
	 * the leaf artifact will be materialized. The location can be absolute or relative to the root location for the
	 * materialization context.
	 * 
	 * @param resolution
	 *            The resolution for the component that should be materialized.
	 * @param context
	 *            The materialization context
	 * @return The preferred install location or null if the reader type has no opinion.
	 * 
	 * @throws CoreException
	 */
	IPath getInstallLocation(Resolution resolution, MaterializationContext context) throws CoreException;

	/**
	 * Returns the last modification date for the working copy or -1 if that cannot be determined.
	 * 
	 * @param workingCopy
	 * @param monitor
	 *            The monitor used for progress reporting
	 * @return The last revision
	 * @throws CoreException
	 */
	Date getLastModification(File workingCopy, IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns the last modification date for the repository or null if that cannot be determined.
	 * 
	 * @param repositoryLocation
	 * @param versionSelector
	 *            the desired version to check for or <code>null</code> if none.
	 * @param monitor
	 *            The monitor used for progress reporting
	 * @return The last modification timestamp.
	 * @throws CoreException
	 */
	Date getLastModification(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor)
			throws CoreException;

	/**
	 * Returns the last revision for the working copy or -1 if that cannot be determined.
	 * 
	 * @param workingCopy
	 * @param monitor
	 *            The monitor used for progress reporting
	 * @return The last revision
	 * @throws CoreException
	 */
	long getLastRevision(File workingCopy, IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns the last revision for the repository location or -1 if that cannot be determined.
	 * 
	 * @param repositoryLocation
	 * @param versionSelector
	 *            the desired version to check for or <code>null</code> if none.
	 * @param monitor
	 *            The monitor used for progress reporting
	 * @return The last revision
	 * @throws CoreException
	 */
	long getLastRevision(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor)
			throws CoreException;

	/**
	 * Returns a suggestion for the leaf artifact (file or folder) for the materialized component.
	 * 
	 * @param resolution
	 *            The resolution for the component that should be materialized.
	 * @param context
	 *            The materialization context
	 * @return The leaf artifact for the materialization in the form of a one element path or null if such a path cannot
	 *         be determined.
	 * @throws CoreException
	 */
	IPath getLeafArtifact(Resolution resolution, MaterializationContext context) throws CoreException;

	/**
	 * Returns a reader type that can read a local component materialized by this a reader of this type.
	 * 
	 * @return A local reader type that corresponds to this type.
	 * @throws CoreException
	 */
	IReaderType getLocalReaderType(boolean isFile) throws CoreException;

	/**
	 * Returns a reader that is maps to the corresponding provider and properties
	 * 
	 * @param provider
	 *            The provider that the reader should use
	 * @param query
	 *            The node query.
	 * @param version
	 *            The version that the reader should read.
	 * @param monitor
	 *            The monitor used for progress reporting
	 * @return A reader configured accordring to the provider and properties.
	 */
	IComponentReader getReader(Provider provider, IComponentType componentType, NodeQuery query, VersionMatch version,
			IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns a reader that is maps to the corresponding providerMatch
	 * 
	 * @param providerMatch
	 *            Information for the reader such as URI, provider, componentType, etc.
	 * @param monitor
	 *            The monitor used for progress reporting
	 * @return A suitable reader.
	 */
	IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns a reader that is maps to the corresponding component info and query
	 * 
	 * @param cr
	 *            The component resoltion
	 * @param context
	 *            The resolution context.
	 * @param monitor
	 *            The monitor used for progress reporting
	 * @return A reader configured accordring to the provider and properties.
	 */
	IComponentReader getReader(Resolution cr, RMContext context, IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns the materializer that this reader type recommends for materializing
	 * 
	 * @return A recommended reader type
	 */
	String getRecommendedMaterializer();

	/**
	 * Returns the remote location of the workin copy or null if that cannot be determined
	 * 
	 * @param workingCopy
	 * @param monitor
	 * @return
	 * @throws CoreException
	 */
	String getRemoteLocation(File workingCopy, IProgressMonitor monitor) throws CoreException;

	/**
	 * Attempts to extract the closest resemblance to a path to the remote file from the repositoryLocation. For some
	 * repository locations this is not possible (such as for cgi scripts taking a numeric id).
	 * 
	 * @param repositoryLocation
	 * @return The path of the remote file or folder or <code>null</code> if that cannot be extracted
	 */
	String getRemotePath(String repositoryLocation) throws CoreException;

	/**
	 * Returns a version finder that can obtain known versions for the readerType/provider combination.
	 * 
	 * @param provider
	 *            The provider that the finder should use
	 * @param componentType
	 *            The component type
	 * @param nodeQuery
	 *            The node query.
	 * @param monitor
	 *            The monitor used for progress reporting
	 * @return A version finder for the readerType/provider combination.
	 */
	IVersionFinder getVersionFinder(Provider provider, IComponentType componentType, NodeQuery nodeQuery,
			IProgressMonitor monitor) throws CoreException;

	/**
	 * This method will return <code>true</code> if this reader type will read one singleton file such as a jar file or
	 * shared object.
	 * 
	 * @return <code>true</code> if the reader reads one singleton file, <code>false</code> otherwise.
	 */
	boolean isFileReader();

	/**
	 * Called when all reader types have performed their materialization
	 * 
	 * @param context
	 *            The materialization context
	 * @param monitor
	 *            The monitor used for progress reporting
	 * @throws CoreException
	 */
	void postMaterialization(MaterializationContext context, IProgressMonitor monitor) throws CoreException;

	/**
	 * Prepare to materialize the given locations. Typically a reader type will use this information to set up some kind
	 * of "view" of the repository that it represents.
	 * 
	 * @param locations
	 *            The locations that will be materialized
	 * @param context
	 *            The materialization context
	 * @param monitor
	 * @throws CoreException
	 */
	void prepareMaterialization(List<Materialization> locations, MaterializationContext context,
			IProgressMonitor monitor) throws CoreException;

	/**
	 * Share project with a repository provider if possible. This method is called by the binder service just after a
	 * project is bound to a workspace. Its intended use is to share the project with a known repository provider.
	 * 
	 * @param project
	 *            The project to share
	 * @param cr
	 *            The resolution for the shared project
	 * @param context
	 *            The materialization context.
	 * @param monitor
	 *            The monitor used for progress reporting
	 * @param monitor
	 */
	void shareProject(IProject project, Resolution cr, RMContext context, IProgressMonitor monitor)
			throws CoreException;
}

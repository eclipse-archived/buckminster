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

import java.net.URL;
import java.util.List;

import org.eclipse.buckminster.core.IBuckminsterExtension;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author thhal
 */
public interface IReaderType extends IBuckminsterExtension
{
	public static final String ECLIPSE_PLATFORM = "eclipse.platform";
	public static final String ECLIPSE_IMPORT = "eclipse.import";
	public static final String ECLIPSE_SITE_FEATURE = "site.feature";
	public static final String LOCAL = "local";
	public static final String URL = "url";
	public static final String URL_ZIPPED = "url.zipped";
	public static final String URL_CATALOG = "url.catalog";

	/**
	 * Returns a suggestion for where this reader type would like the component
	 * to be materialized or <code>null</code> if the reader has no opinion
	 * about that. The parameter <code>optional</code> must be a one element
	 * array. On return, the element is set to <code>true</code> to indicate
	 * that the suggestion is optional and may be overridden or
	 * <code>false</code> to indicate that the location is where the
	 * materialization has to take place.
	 * @param resolution The resolution for the component that should be
	 *            materialized.
	 * @param context The resolution and materialization context
	 * @param optional A one element array that will carry the optional status
	 *            return value.
	 * @return A location for materialization or <code>null</code> if this
	 *         reader type has no opinion.
	 * @throws CoreException
	 */
	IPath getMaterializationLocation(Resolution resolution, RMContext context, boolean[] optional) throws CoreException;

	/**
	 * Prepare to materialize the given locations. Typically a reader type will
	 * use this information to set up some kind of "view" of the repository that
	 * it represents.
	 * @param locations The locations that will be materialized
	 * @param context The resolution and materialization context
	 * @param monitor
	 * @throws CoreException
	 */
	void prepareMaterialization(List<Materialization> locations, RMContext context, IProgressMonitor monitor)
		throws CoreException;

	/**
	 * Share project with a repository provider if possible. This method is
	 * called by the binder service just after a project is bound to a
	 * workspace. Its intended use is to share the project with a known
	 * repository provider.
	 * @param project The project to share
	 * @param cr The resolution for the shared project
	 * @param context The resolution context.
	 * @param monitor The monitor used for progress reporting
	 * @param monitor
	 */
	void shareProject(IProject project, Resolution cr, RMContext context, IProgressMonitor monitor) throws CoreException;

	/**
	 * Some components, such as the ones present in the target platform, are not materialized. Instead,
	 * they have a fixed location.
	 *
	 * @param cr The component resoltion denoting the component in question.
	 * @return The fixed location or <code>null</code> if not applicable.
	 */
	IPath getFixedLocation(Resolution cr) throws CoreException;

	/**
	 * Returns a reader that is maps to the corresponding component info and query
	 * @param cr The component resoltion
	 * @param context The resolution context.
	 * @param monitor The monitor used for progress reporting
	 * @return A reader configured accordring to the provider and properties.
	 */
	IComponentReader getReader(Resolution cr, RMContext context, IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns a reader that is maps to the corresponding provider and
	 * properties
	 * @param provider The provider that the reader should use
	 * @param query The node query.
	 * @param version The version that the reader should read.
	 * @param monitor The monitor used for progress reporting
	 * @return A reader configured accordring to the provider and properties.
	 */
	IComponentReader getReader(Provider provider, NodeQuery query, VersionMatch version, IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns a reader that is maps to the corresponding providerMatch
	 * @param providerMatch Information for the reader such as URI, provider, componentType, etc.
	 * @param monitor The monitor used for progress reporting
	 * @return A suitable reader.
	 */
	IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns a version finder that can obtain known versions for the readerType/provider
	 * combination.
	 * @param provider The provider that the reader should use
	 * @param nodeQuery The node query.
	 * @param monitor The monitor used for progress reporting
	 * @return A version finder for the readerType/provider combination.
	 */
	IVersionFinder getVersionFinder(Provider provider, NodeQuery nodeQuery, IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns a reader type that can read a local component materialized by this
	 * a reader of this type.
	 * @return A local reader type that corresponds to this type.
	 */
	IReaderType getLocalReaderType(boolean isFile) throws CoreException;

	/**
	 * This method will return <code>true</code> if this reader type will read
	 * one singleton file such as a jar file or shared object.
	 * @return <code>true</code> if the reader reads one singleton file, <code>false</code> otherwise.
	 */
	boolean isFileReader();

	/**
	 * Called when all reader types have performed their materialization
	 */
	void postMaterialization(IProgressMonitor monitor) throws CoreException;

	/**
	 * Convert a team project set style locator into a URL.
	 * @param repositoryLocator The locator to convert
	 * @return The URL or <code>null</code> if this reader type does not support URL retrieval.
	 * @throws CoreException
	 */
	URL convertToURL(String repositoryLocator, IVersionSelector versionSelector) throws CoreException;

	/**
	 * There might be minor difference in how repository locators are defined in a team
	 * repository provider and what whould be expected by the PDE IFetchFactory. This
	 * method expects the latter as input and will convert it to the former.
	 * @param fetchFactoryLocator The locator in IFetchFactory format.
	 * @param componentName The name of the component
	 * @return The locator in team repository format.
	 */
	String convertFetchFactoryLocator(String fetchFactoryLocator, String componentName) throws CoreException;
}

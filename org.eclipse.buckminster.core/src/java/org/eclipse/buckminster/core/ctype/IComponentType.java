/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.ctype;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.IBuckminsterExtension;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A IComponentType knows how to find dependency information in the components that it represents,
 * often in combination with a {@link IResolutionBuilder}. A good example is the
 * <code>eclipse.project</code> type that will choose a <code>IResolutionBuilder</code> depending on
 * the natures it finds in the .project file.
 * @see org.eclipse.buckminster.core.CorePlugin#getComponentType(String)
 * @author Thomas Hallgren
 */
public interface IComponentType extends IBuckminsterExtension
{
	public static final String OSGI_BUNDLE = "osgi.bundle";

	public static final String ECLIPSE_FEATURE = "eclipse.feature";

	// Some well known component types included in the core distro
	//
	public static final String ECLIPSE_INSTALLED = "eclipse.installed";

	public static final String ECLIPSE_PROJECT = "eclipse.project";

	public static final String BUCKMINSTER = "buckminster";

	public static final String UNKNOWN = "unknown";

	public static final String ECLIPSE_SITE_FEATURE = "site.feature";

	public static final String PREF_METADATA_FOLDER = CorePlugin.getID() + ".metadata.folder";

	public static final String PREF_CSPEC_FILE = CorePlugin.getID() + ".cspec.file";

	public static final String PREF_CSPEX_FILE = CorePlugin.getID() + ".cspex.file";

	public static final String PREF_CQUERY_FILE = CorePlugin.getID() + ".cquery.file";

	public static final String PREF_OPML_FILE = CorePlugin.getID() + ".opml.file";

	/**
	 * Extracts the component version from artifacts found inside of the component. Component
	 * types where this is not applicable will return <code>null</code>.
	 * @param providerMatch The information needed to find the source.
	 * @param monitor monitor for cancellation and progress reporting
	 * @return the component version or <code>null</code> if not applicable
	 * @throws CoreException
	 */
	IVersion getComponentVersion(ProviderMatch providerMatch, IProgressMonitor monitor)
	throws CoreException;

	/**
	 * Creates a {@link DepNode} based on the dependency information in
	 * <code>providerMatch</code>. A provider will normally create a node where only the
	 * top element is resolved but a provider can also find a previously resolved
	 * {@link org.eclipse.buckminster.core.metadata.model.BillOfMaterials BillOfMaterials}. When it
	 * does that instance will be returned.
	 * @param providerMatch The information needed to find the source.
	 * @param monitor monitor for cancellation and progress reporting
	 * @return The resolved node. This entry is never <code>null</code>.
	 * @throws CoreException If the node could not be resolved.
	 */
	DepNode getResolution(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns the builder used when building the Resolution
	 */
	IResolutionBuilder getResolutionBuilder(IComponentReader reader, IProgressMonitor monitor)
	throws CoreException;

	/**
	 * A regular expression that should match the name of the component. The project name
	 * will be subject to name substitution using the {@link #getSubstituteNamePattern()}
	 * and {@link #getNameSubstitution()} when the name does not match.
	 * @return The regular expression that controls if name substitution is needed or not.
	 */
	Pattern getDesiredNamePattern();

	/**
	 * Returns the meta files that this component make use of when creating the resolution
	 * @return An array of meta files
	 */
	IMetaFile[] getMetaFiles();

	/**
	 * The substitution string for the pattern returned by {@link #getSubstituteNamePattern()}
	 * @return The substitution string
	 */
	String getNameSubstitution();

	/**
	 * Suggested Workspace relative location to use when materializing, i.e. suggest
	 * &amp;lt;workspace&amp;gt;/&amp;lt;relativeLocation&amp;gt;/&amp;lt;component name&amp;gt;
	 * @return The suggested workspace relative location
	 */
	IPath getRelativeLocation();

	/**
	 * A regular expression used for substitution when the pattern returned by {@link #getDesiredNamePattern()}
	 * does not match.
	 * @return The expression to use for the name substitution
	 */
	Pattern getSubstituteNamePattern();

	/**
	 * Returns true if the component type can find all required meta files under
	 * the given <code>path</code>.
	 * @param productPath The folder to scan for meta files
	 * @return <code>true</code> if all required meta files are found.
	 */
	boolean hasAllRequiredMetaFiles(IPath path);

	/**
	 * Returns true if this component type is producing a cspec based on other
	 * meta-data files found in the component.
	 * @return true if a cspec is produced based on meta-files.
	 */
	boolean isMetaFileBased();
}

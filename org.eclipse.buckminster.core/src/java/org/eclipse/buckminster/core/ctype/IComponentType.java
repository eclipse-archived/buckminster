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
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.rmap.util.IComponentReader;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;

/**
 * A IComponentType knows how to find dependency information in the components
 * that it represents, often in combination with a {@link IResolutionBuilder}. A
 * good example is the <code>eclipse.project</code> type that will choose a
 * <code>IResolutionBuilder</code> depending on the natures it finds in the
 * .project file.
 * 
 * @see org.eclipse.buckminster.core.CorePlugin#getComponentType(String)
 * @author Thomas Hallgren
 */
public interface IComponentType extends IBuckminsterExtension {
	public static final String OSGI_BUNDLE = "osgi.bundle"; //$NON-NLS-1$

	public static final String ECLIPSE_FEATURE = "eclipse.feature"; //$NON-NLS-1$

	// Some well known component types included in the core distro
	//
	public static final String ECLIPSE_INSTALLED = "eclipse.installed"; //$NON-NLS-1$

	public static final String ECLIPSE_PROJECT = "eclipse.project"; //$NON-NLS-1$

	public static final String BUCKMINSTER = "buckminster"; //$NON-NLS-1$

	public static final String UNKNOWN = "unknown"; //$NON-NLS-1$

	public static final String ECLIPSE_SITE_FEATURE = "site.feature"; //$NON-NLS-1$

	public static final String PREF_METADATA_FOLDER = CorePlugin.getID() + ".metadata.folder"; //$NON-NLS-1$

	public static final String PREF_CSPEC_FILE = CorePlugin.getID() + ".cspec.file"; //$NON-NLS-1$

	public static final String PREF_CSPEX_FILE = CorePlugin.getID() + ".cspex.file"; //$NON-NLS-1$

	public static final String PREF_CQUERY_FILE = CorePlugin.getID() + ".cquery.file"; //$NON-NLS-1$

	/**
	 * Extracts the component version from artifacts found inside of the
	 * component. Component types where this is not applicable will return
	 * <code>null</code>.
	 * 
	 * @param providerMatch
	 *            The information needed to find the source.
	 * @param monitor
	 *            monitor for cancellation and progress reporting
	 * @return the component version or <code>null</code> if not applicable
	 * @throws CoreException
	 */
	Version getComponentVersion(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException;

	/**
	 * A regular expression that should match the name of the component. The
	 * project name will be subject to name substitution using the
	 * {@link #getSubstituteNamePattern()} and {@link #getNameSubstitution()}
	 * when the name does not match.
	 * 
	 * @return The regular expression that controls if name substitution is
	 *         needed or not.
	 */
	Pattern getDesiredNamePattern();

	/**
	 * Returns the meta files that this component make use of when creating the
	 * resolution
	 * 
	 * @return An array of meta files
	 */
	IMetaFile[] getMetaFiles();

	/**
	 * The substitution string for the pattern returned by
	 * {@link #getSubstituteNamePattern()}
	 * 
	 * @return The substitution string
	 */
	String getNameSubstitution();

	/**
	 * Returns what this component type would like to call a project containing
	 * the component named componentName.
	 * 
	 * @param componentName
	 *            The name of the component
	 * @return The suggested name for the project
	 */
	String getProjectName(String componentName) throws CoreException;

	/**
	 * Suggested Workspace relative location to use when materializing, i.e.
	 * suggest
	 * &amp;lt;workspace&amp;gt;/&amp;lt;relativeLocation&amp;gt;/&amp;lt
	 * ;component name&amp;gt;
	 * 
	 * @return The suggested workspace relative location
	 */
	IPath getRelativeLocation();

	/**
	 * Creates a {@link BOMNode} based on the dependency information in
	 * <code>providerMatch</code>. A provider will normally create a node where
	 * only the top element is resolved but a provider can also find a
	 * previously resolved
	 * {@link org.eclipse.buckminster.core.metadata.model.BillOfMaterials
	 * BillOfMaterials}. When it does that instance will be returned.
	 * 
	 * @param providerMatch
	 *            The information needed to find the source.
	 * @param monitor
	 *            monitor for cancellation and progress reporting
	 * @return The resolved node. This entry is never <code>null</code>.
	 * @throws CoreException
	 *             If the node could not be resolved.
	 */
	BOMNode getResolution(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns the builder used when building the Resolution
	 */
	IResolutionBuilder getResolutionBuilder(IComponentReader reader, IProgressMonitor monitor) throws CoreException;

	/**
	 * A regular expression used for substitution when the pattern returned by
	 * {@link #getDesiredNamePattern()} does not match.
	 * 
	 * @return The expression to use for the name substitution
	 */
	Pattern getSubstituteNamePattern();

	/**
	 * Returns a designator that has been converted to suit the specific
	 * component type. Some component types put special meaning to the qualifier
	 * of a version that affects the semantics of the designator (a typical
	 * example is Mavens use of the keyword SNAPSHOT).
	 * 
	 * @return The converted designator
	 */
	VersionRange getTypeSpecificDesignator(VersionRange designator);

	/**
	 * Returns true if the component type can find all required meta files under
	 * the given <code>path</code>.
	 * 
	 * @param productPath
	 *            The folder to scan for meta files
	 * @return <code>true</code> if all required meta files are found.
	 */
	boolean hasAllRequiredMetaFiles(IPath path);

	/**
	 * Returns true if this component type is producing a cspec based on other
	 * meta-data files found in the component.
	 * 
	 * @return true if a cspec is produced based on meta-files.
	 */
	boolean isMetaFileBased();
}

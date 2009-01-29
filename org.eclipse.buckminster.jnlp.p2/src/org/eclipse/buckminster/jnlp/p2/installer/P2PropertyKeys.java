/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.p2.installer;

/**
 * @author Karel Brezina
 *
 */
public class P2PropertyKeys
{
	public static final String PROP_AGENT_LOCATION = "eclipse.p2.agentLocation"; //$NON-NLS-1$

	public static final String PROP_ARTIFACT_REPOSITORY_COUNT = "eclipse.p2.artifact.repository.count";

	public static final String PROP_ARTIFACT_REPOSITORY_PREFIX = "eclipse.p2.artifact.repository";

	public static final String PROP_BUNDLE_LOCATION = "eclipse.p2.bundleLocation";//$NON-NLS-1$

	public static final String PROP_FLAVOR = "eclipse.p2.flavor";//$NON-NLS-1$

	public static final String PROP_INSTALL_LOCATION = "eclipse.p2.installLocation";//$NON-NLS-1$

	public static final String PROP_IS_AUTO_START = "eclipse.p2.autoStart";//$NON-NLS-1$

	public static final String PROP_LAUNCHER_NAME = "eclipse.p2.launcherName";//$NON-NLS-1$

	public static final String PROP_METADATA_REPOSITORY = "eclipse.p2.metadata";//$NON-NLS-1$

	public static final String PROP_PROFILE_NAME = "eclipse.p2.profileName";//$NON-NLS-1$

	public static final String PROP_ROOT_ID = "eclipse.p2.rootId";//$NON-NLS-1$

	public static final String PROP_ROOT_VERSION = "eclipse.p2.rootVersion";//$NON-NLS-1$

	public static final String PROP_ROOTS = "eclipse.p2.roots";//$NON-NLS-1$
	
	public static String composeArtifactRepositoryProperty(int repoIdx)
	{
		return String.format("%s.%d", PROP_ARTIFACT_REPOSITORY_PREFIX, Integer.valueOf(repoIdx));
	}
}

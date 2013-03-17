/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.version;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.reader.AbstractReaderType;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * This class will generate qualifiers based on component revisions. The
 * revision is obtained using the same @ IReaderType} that was used when the
 * component was first materialized
 * 
 * @author Thomas Hallgren
 */
public class RevisionQualifierGenerator extends AbstractQualifierGenerator {
	public static String FORMAT_PROPERTY = "generator.lastRevision.format"; //$NON-NLS-1$

	public static String DEFAULT_FORMAT = "r{0,number,##################}"; //$NON-NLS-1$

	@Override
	public Version generateQualifier(IActionContext context, ComponentIdentifier cid, List<ComponentIdentifier> dependencies) throws CoreException {
		final Version currentVersion = cid.getVersion();
		if (currentVersion == null)
			return null;

		try {
			IPath location = WorkspaceInfo.getComponentLocation(cid);
			IReaderType readerType = AbstractReaderType.getTypeForResource(WorkspaceInfo.getProject(cid));
			if (readerType == null)
				return currentVersion;

			long revision = readerType.getLastRevision(location.toFile(), context.getCancellationMonitor());
			if (revision == -1)
				return currentVersion;

			Map<String, ? extends Object> props = context.getProperties();
			String format = (String) props.get(FORMAT_PROPERTY);
			if (format == null)
				format = DEFAULT_FORMAT;

			boolean subBuildId = false;
			MessageFormat mf = new MessageFormat(format);
			for (IComponentIdentifier dependency : dependencies) {
				Version depVer = dependency.getVersion();
				if (depVer == null)
					continue;

				String qualifier = VersionHelper.getQualifier(depVer);
				if (qualifier == null)
					continue;

				long depRev = 0;
				try {
					depRev = ((Number) (mf.parse(qualifier)[0])).longValue();
				} catch (Exception e) {
					// We might have encountered something that ends with a
					// build id. Let's try parsing it
					String tmp = qualifier;
					int lastDash = tmp.lastIndexOf('-');
					while (lastDash > 0) {
						tmp = tmp.substring(0, lastDash);
						try {
							depRev = ((Number) (mf.parse(tmp)[0])).longValue();
							subBuildId = true;
							break;
						} catch (Exception e2) {
							// expected. Continue in case the build id itself
							// contains dashes.
							lastDash = tmp.lastIndexOf('-');
						}
					}
				}
				if (depRev > revision)
					revision = depRev;
			}

			String buildId = (String) props.get("build.id"); //$NON-NLS-1$
			String newQual = mf.format(new Object[] { new Long(revision) });
			if (buildId != null && subBuildId)
				// A dependency had a build id appended to the revision. This
				// means we must have that too.
				newQual = newQual + '-' + buildId;

			newQual = VersionHelper.getQualifier(currentVersion).replace("qualifier", newQual); //$NON-NLS-1$
			Version newVersion = VersionHelper.replaceQualifier(currentVersion, newQual);
			if (buildId == null || subBuildId)
				return newVersion;

			// Check if a previous build contained a generated build id in which
			// case we need to append that to the revision number
			IInstallableUnit prevIU = obtainFromReferenceRepo(cid, null);
			if (prevIU == null)
				return newVersion;

			String oldBuildId = prevIU.getProperty(KeyConstants.BUILD_ID);
			if (oldBuildId == null || oldBuildId.equals(buildId))
				return newVersion;

			Version oldVer = prevIU.getVersion();
			String oldQual = VersionHelper.getQualifier(oldVer);
			if (oldQual.equals(newQual) || (oldQual.startsWith(newQual) && oldQual.charAt(newQual.length()) == '-')) {
				// Exactly the same revision has been generated before
				// Component contains a generated build id that differs
				// from the current one. Append '-<buildId>'
				newQual = newQual + '-' + buildId;
				newQual = VersionHelper.getQualifier(currentVersion).replace("qualifier", newQual); //$NON-NLS-1$
				newVersion = VersionHelper.replaceQualifier(currentVersion, newQual);
			}
			return newVersion;
		} catch (MissingComponentException e) {
			return currentVersion;
		}
	}
}

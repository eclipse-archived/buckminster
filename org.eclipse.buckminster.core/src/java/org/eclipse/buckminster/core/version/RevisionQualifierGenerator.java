/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.version;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.reader.AbstractReaderType;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;

/**
 * This class will generate qualifiers based on component revisions. The revision is obtained using the same @
 * IReaderType} that was used when the component was first materialized
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class RevisionQualifierGenerator extends AbstractExtension implements IQualifierGenerator
{
	public static String FORMAT_PROPERTY = "generator.lastRevision.format"; //$NON-NLS-1$

	public static String DEFAULT_FORMAT = "r{0,number,##################}"; //$NON-NLS-1$

	public Version generateQualifier(IActionContext context, ComponentIdentifier cid,
			List<ComponentIdentifier> dependencies) throws CoreException
	{
		Version currentVersion = cid.getVersion();
		if(currentVersion == null)
			return null;

		try
		{
			IPath location = WorkspaceInfo.getComponentLocation(cid);
			IReaderType readerType = AbstractReaderType.getTypeForResource(WorkspaceInfo.getProject(cid));
			if(readerType == null)
				return currentVersion;

			long revision = readerType.getLastRevision(location.toFile(), context.getCancellationMonitor());
			if(revision == -1)
				return currentVersion;

			Map<String, ? extends Object> props = context.getProperties();
			String format = (String)props.get(FORMAT_PROPERTY);
			if(format == null)
				format = DEFAULT_FORMAT;

			MessageFormat mf = new MessageFormat(format);
			for(IComponentIdentifier dependency : dependencies)
			{
				Version depVer = dependency.getVersion();
				if(depVer == null)
					continue;

				String qualifier = depVer.getQualifier();
				if(qualifier == null)
					continue;

				try
				{
					long depRev = ((Number)(mf.parse(qualifier)[0])).longValue();
					if(depRev > revision)
						revision = depRev;
				}
				catch(Exception e)
				{
				}
			}
			String newQual = mf.format(new Object[] { new Long(revision) });
			newQual = currentVersion.getQualifier().replace("qualifier", newQual); //$NON-NLS-1$
			return VersionHelper.replaceQualifier(currentVersion, newQual);
		}
		catch(MissingComponentException e)
		{
			return currentVersion;
		}
	}
}

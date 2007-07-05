/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.version;

import java.text.MessageFormat;

import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * This class will generate qualifiers based on component revisions. The revision is obtained using the same {@ IReaderType}
 * that was used when the component was first materialized
 *
 * @author Thomas Hallgren
 */
public class RevisionQualifierGenerator extends AbstractExtension implements IQualifierGenerator
{
	private static MessageFormat s_defaultFormat = new MessageFormat("r{0,number,integer}");

	public IVersion generateQualifier(IActionContext context, ComponentIdentifier cid,
			ComponentIdentifier[] dependencies) throws CoreException
	{
		IVersion currentVersion = cid.getVersion();
		if(currentVersion == null)
			return null;

		try
		{
			IPath location = WorkspaceInfo.getComponentLocation(cid);
			IReaderType readerType = WorkspaceInfo.getResolution(cid).getProvider().getReaderType();
			long revision = readerType.getLastRevision(location.toFile(), context.getCancellationMonitor());
			if(revision == -1)
				return null;

			MessageFormat mf = s_defaultFormat;
			for(ComponentIdentifier dependency : dependencies)
			{
				IVersion depVer = dependency.getVersion();
				if(depVer == null)
					continue;

				String qualifier = depVer.getQualifier();
				if(qualifier == null)
					continue;

				try
				{
					long depRev;
					synchronized(mf)
					{
						depRev = ((Number)(mf.parse(qualifier)[0])).longValue();
					}
					if(depRev > revision)
						revision = depRev;
				}
				catch(Exception e)
				{
				}
			}
			return currentVersion.replaceQualifier(Long.toString(revision));
		}
		catch(MissingComponentException e)
		{
			return currentVersion;
		}
	}
}

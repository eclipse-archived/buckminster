/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.version;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * This class will generate qualifiers based on the last modification timestamp. The timestamp is obtained using the same
 * {@ IReaderType} that was used when the component was first materialized
 * 
 * @author Thomas Hallgren
 */
public class TimestampQualifierGenerator extends AbstractExtension implements IQualifierGenerator
{
	public static String FORMAT_PROPERTY = "generator.lastModified.format";
	public static String DEFAULT_FORMAT = "yyyyMMddHHmm";

	public IVersion generateQualifier(IActionContext context, ComponentIdentifier cid,
			List<ComponentIdentifier> dependencies) throws CoreException
	{
		IVersion currentVersion = cid.getVersion();
		if(currentVersion == null)
			return null;

		try
		{
			IPath location = WorkspaceInfo.getComponentLocation(cid);
			IReaderType readerType = WorkspaceInfo.getResolution(cid).getProvider().getReaderType();
			Date lastMod = readerType.getLastModification(location.toFile(), context.getCancellationMonitor());
			if(lastMod == null)
				return null;

			Map<String,String> props = context.getProperties();
			String format = props.get(FORMAT_PROPERTY);
			if(format == null)
				format = DEFAULT_FORMAT;
	
			DateFormat mf = new SimpleDateFormat(format);
			mf.setTimeZone(DateAndTimeUtils.UTC);

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
					Date depLastMod = mf.parse(qualifier);
					if(depLastMod.compareTo(lastMod) > 0)
						lastMod = depLastMod;
				}
				catch(Exception e)
				{
				}
			}
			return currentVersion.replaceQualifier(mf.format(lastMod));
		}
		catch(MissingComponentException e)
		{
			return currentVersion;
		}
	}
}

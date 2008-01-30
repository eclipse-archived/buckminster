/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.version;

import java.text.DateFormat;
import java.text.ParseException;
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
import org.eclipse.buckminster.core.reader.AbstractReaderType;
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
	public static final String FORMAT_PROPERTY = "generator.lastModified.format";
	public static final String DEFAULT_FORMAT = "'v'yyyyMMddHHmm";
	public static final String[] commonFormats = new String[] { DEFAULT_FORMAT, "'v'yyyyMMdd-HHmm", "'v'yyyyMMdd", "'I'yyyyMMddHHmm", "'I'yyyyMMdd-HHmm", "'I'yyyyMMdd" };
	public static final DateFormat[] commonFormatters;

	static
	{
		int idx = commonFormats.length;
		commonFormatters = new DateFormat[idx];
		while(--idx >= 0)
		{
			DateFormat dm = new SimpleDateFormat(commonFormats[idx]);
			dm.setTimeZone(DateAndTimeUtils.UTC);
			dm.setLenient(false);
			commonFormatters[idx] = dm;
		}
	}

	public IVersion generateQualifier(IActionContext context, ComponentIdentifier cid,
			List<ComponentIdentifier> dependencies) throws CoreException
	{
		IVersion currentVersion = cid.getVersion();
		if(currentVersion == null)
			return null;

		try
		{
			IPath location = WorkspaceInfo.getComponentLocation(cid);
			IReaderType readerType = AbstractReaderType.getTypeForResource(WorkspaceInfo.getProject(cid));
			if(readerType == null)
				return currentVersion;

			Date lastMod = readerType.getLastModification(location.toFile(), context.getCancellationMonitor());
			if(lastMod == null)
				return currentVersion;

			Map<String,String> props = context.getProperties();
			String format = props.get(FORMAT_PROPERTY);
			if(format == null)
				format = DEFAULT_FORMAT;

			DateFormat mf = new SimpleDateFormat(format);
			mf.setTimeZone(DateAndTimeUtils.UTC);
			mf.setLenient(false);

			for(ComponentIdentifier dependency : dependencies)
			{
				IVersion depVer = dependency.getVersion();
				if(depVer == null)
					continue;

				String qualifier = depVer.getQualifier();
				if(qualifier == null)
					continue;

				Date depLastMod = null;
				try
				{
					depLastMod = parseSaneDate(mf, qualifier);
				}
				catch(ParseException e)
				{
					// Try the common formats. Use the first one that succeeds
					//
					synchronized(commonFormatters)
					{
						for(int idx = 0; idx < commonFormatters.length; ++idx)
						{
							try
							{
								depLastMod = parseSaneDate(commonFormatters[idx], qualifier);
								break;
							}
							catch(ParseException e1)
							{
							}
						}
					}
				}
				if(depLastMod != null && depLastMod.compareTo(lastMod) > 0)
					lastMod = depLastMod;
			}
			return currentVersion.replaceQualifier(mf.format(lastMod));
		}
		catch(MissingComponentException e)
		{
			return currentVersion;
		}
	}

	// Milliseconds corresponding to approximately 10 years
	//
	private static final long SANITY_THRESHOLD = (10L * 365L + 5L) * 24L * 60L * 60L * 1000L;

	private static Date parseSaneDate(DateFormat mf, String str) throws ParseException
	{
		long now = System.currentTimeMillis();
		long sanePast = now - SANITY_THRESHOLD;
		Date dt = mf.parse(str);
		long tm = dt.getTime();
		if(tm > now || tm < sanePast)
			throw new ParseException("Bogus", 0);
		return dt;
	}
}

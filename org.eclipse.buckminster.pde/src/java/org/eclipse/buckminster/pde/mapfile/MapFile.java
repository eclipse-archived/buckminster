/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.pde.mapfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionSyntaxException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.build.IFetchFactory;
import org.eclipse.pde.internal.build.FetchTaskFactoriesRegistry;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class MapFile
{
	private static final Pattern s_pattern = Pattern.compile("^"
			+ "\\s*([^@=,\\s]+)\\s*@" // The type, i.e. bundle, feature, plugin, or fragment
			+ "\\s*([^@,=\\s]+)\\s*" // Element ID
			+ "(?:,\\s*([^@,=\\s]+)\\s*)?=" // Optional version
			+ "(?:\\s*([A-Za-z_][A-Za-z0-9_-]*)\\s*,)?\\s*" // Optional fetch type specifier (default is CVS)
			+ "\\s*([^\\s]+)\\s*$"); // Fetch type specific field

	private static FetchTaskFactoriesRegistry s_fetchTaskFactories;

	public static void parse(InputStream inputStream, String streamName, List<MapFileEntry> receivingList)
			throws IOException
	{
		CorePlugin core = CorePlugin.getDefault();
		Logger logger = CorePlugin.getLogger();

		if(s_fetchTaskFactories == null)
			s_fetchTaskFactories = new FetchTaskFactoriesRegistry();
		AccessibleByteArrayOutputStream buffer = new AccessibleByteArrayOutputStream();
		FileUtils.substituteParameters(inputStream, buffer, '@', Collections.singletonMap("CVSTag", "HEAD"));
		BufferedReader input = new BufferedReader(new InputStreamReader(buffer.getInputStream()));
		String line;
		while((line = input.readLine()) != null)
		{
			if(line.startsWith("#"))
				//
				// Comment
				//
				continue;

			Matcher m = s_pattern.matcher(line);
			if(!m.matches())
			{
				line = line.trim();
				if(line.length() > 0)
					logger.debug("NO MATCH FOR %s", line);
				continue;
			}

			String fetchType = m.group(4);
			String fetchTypeSpecific = m.group(5);
			if(fetchType == null)
				fetchType = "CVS";
			else if(fetchType.equals("COPY"))
			{
				logger.warning("Fetch type COPY is not supported. Map file %s", streamName);
				continue;
			}

			IFetchFactory ff = s_fetchTaskFactories.getFactory(fetchType);
			if(ff == null)
			{
				// Assume that the fetchType that we encountered is part of the
				// fetchTypeSpecific string and that the real fetchType is CVS.
				//
				fetchTypeSpecific = fetchType + ',' + fetchTypeSpecific;
				fetchType = "CVS";

				ff = s_fetchTaskFactories.getFactory(fetchType);
				if(ff == null)
				{
					logger.warning("No fetch factory found for id '%s' in PDE map file %s", fetchType, streamName);
					continue;
				}
			}


			String type = m.group(1);
			String ctypeId;
			if("plugin".equals(type) || "bundle".equals(type) || "fragment".equals(type))
				ctypeId = IComponentType.OSGI_BUNDLE;
			else if("feature".equals(type))
				ctypeId = IComponentType.ECLIPSE_FEATURE;
			else
			{
				// We don't recognize this type
				//
				logger.warning("Unregognized component type '%s' in PDE map file %s", type, streamName);
				continue;
			}

			String vstr = m.group(3);
			IVersion version;
			try
			{
				version = vstr == null
						? null
						: VersionFactory.OSGiType.fromString(vstr);
			}
			catch(VersionSyntaxException e)
			{
				// Version is corrupt. Skip this line
				//
				logger.warning("Badly formatted version '%s' in PDE map file %s", vstr, streamName);
				continue;
			}

			String identifier = m.group(2);

			Map<String, String> props = new HashMap<String,String>();
			try
			{
				ff.parseMapFileEntry(fetchTypeSpecific, null, props);
			}
			catch(Exception e)
			{
				logger.warning("Fetch factory %s was unable to parse '%s' in PDE map file %s", fetchType, fetchTypeSpecific, streamName);
				continue;
			}

			String readerTypeID = fetchType.toLowerCase();
			if("get".equals(readerTypeID))
			{
				readerTypeID = "url";

				// Extract a more exact version from the file name if possible
				//
				String src = props.get("src");
				if(src.endsWith(".jar") || src.endsWith(".zip"))
				{
					int lastSlash = src.lastIndexOf('/');
					if(lastSlash < 0)
						lastSlash = 0;
					String vcName = src.substring(lastSlash + 1, src.length() - 4);
					if(vcName.startsWith(identifier + '_'))
					{
						try
						{
							version = VersionFactory.OSGiType.fromString(vcName, identifier.length() + 1, new int[1]);
						}
						catch(VersionSyntaxException e)
						{
							// Ignore. For some reason this version was not a
							// valid OSGi version
						}
					}
				}
			}

			ComponentIdentifier cid = new ComponentIdentifier(identifier, ctypeId, version);
			IReaderType readerType;
			try
			{
				readerType = core.getReaderType(readerTypeID);
			}
			catch(CoreException e)
			{
				logger.warning("Unable to obtain reader type for fetch type '%s' in PDE map file %s", fetchType, streamName);
				continue;
			}
			receivingList.add(new MapFileEntry(cid, readerType, props));
		}
	}
}

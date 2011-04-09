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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.Replace;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.build.IFetchFactory;
import org.eclipse.pde.internal.build.FetchTaskFactoriesRegistry;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class MapFile {
	private static final Pattern pattern = Pattern.compile("^" + "\\s*([^@=,\\s]+)\\s*@" // The type, i.e. bundle, //$NON-NLS-1$ //$NON-NLS-2$
			// feature, plugin, or
			// fragment
			+ "\\s*([^@,=\\s]+)\\s*" // Element ID //$NON-NLS-1$
			+ "(?:,\\s*([^@,=\\s]+)\\s*)?=" // Optional version //$NON-NLS-1$
			+ "(?:\\s*([A-Za-z_][A-Za-z0-9_-]*)\\s*,)?\\s*" // Optional fetch type specifier (default is CVS) //$NON-NLS-1$
			+ "\\s*([^\\s]+)\\s*$"); // Fetch type specific field //$NON-NLS-1$

	private static FetchTaskFactoriesRegistry fetchTaskFactories;

	public static void parse(InputStream inputStream, String streamName, Replace replace, Map<String, ? extends Object> queryProps,
			List<MapFileEntry> receivingList) throws IOException {
		CorePlugin core = CorePlugin.getDefault();
		Logger logger = CorePlugin.getLogger();

		if (fetchTaskFactories == null)
			fetchTaskFactories = new FetchTaskFactoriesRegistry();
		BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		nextLine: while ((line = input.readLine()) != null) {
			// find first non-whitespace character on the line
			int len = line.length();
			int idx;
			for (idx = 0; idx < len; ++idx) {
				char c = line.charAt(idx);
				if (Character.isWhitespace(c))
					continue;
				if (c == '!' || c == '#')
					//
					// Comment
					//
					continue nextLine;
				break;
			}
			if (idx == len)
				// Just whitespace
				continue;

			if (replace != null) {
				line = replace.replace(line);

				// Expand since the replacement might have introduced
				// property expansion constructs.
				line = ExpandingProperties.expand(queryProps, line, 0);
			}

			Matcher m = pattern.matcher(line);
			if (!m.matches())
				continue;

			String fetchType = m.group(4);
			String fetchTypeSpecific = m.group(5);
			if (fetchType == null)
				fetchType = "CVS"; //$NON-NLS-1$
			else if (fetchType.equals("COPY")) //$NON-NLS-1$
			{
				logger.warning(NLS.bind(Messages.fetch_type_COPY_not_supported_map_0, streamName));
				continue;
			}

			IFetchFactory ff = fetchTaskFactories.getFactory(fetchType);
			if (ff == null) {
				// Assume that the fetchType that we encountered is part of the
				// fetchTypeSpecific string and use the CVS IFetchFactory
				// to parse the string.
				//
				fetchTypeSpecific = fetchType + ',' + fetchTypeSpecific;

				ff = fetchTaskFactories.getFactory("CVS"); //$NON-NLS-1$
				if (ff == null) {
					logger.warning(NLS.bind(Messages.no_factory_found_for_0_in_PDEmap_1, fetchType, streamName));
					continue;
				}
			}

			String type = m.group(1);
			String ctypeId;
			if ("plugin".equals(type) || "bundle".equals(type) || "fragment".equals(type)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				ctypeId = IComponentType.OSGI_BUNDLE;
			else if ("feature".equals(type)) //$NON-NLS-1$
				ctypeId = IComponentType.ECLIPSE_FEATURE;
			else {
				// We don't recognize this type
				//
				logger.warning(NLS.bind(Messages.unrecognized_component_type_0_in_PDEmap_1, type, streamName));
				continue;
			}

			String vstr = m.group(3);
			Version version;
			try {
				version = vstr == null ? null : Version.parseVersion(vstr);
			} catch (IllegalArgumentException e) {
				// Version is corrupt. Skip this line
				//
				logger.warning(NLS.bind(Messages.badly_formatted_version_0_in_PDEmap_1, vstr, streamName));
				continue;
			}

			String identifier = m.group(2);

			Map<String, String> props = new HashMap<String, String>();
			try {
				ff.parseMapFileEntry(fetchTypeSpecific, null, props);
			} catch (Exception e) {
				logger.warning(NLS.bind(Messages.fetch_factory_0_unable_to_parse_1_in_PDEmap_2, new Object[] { fetchType, fetchTypeSpecific,
						streamName }));
				continue;
			}

			String readerTypeID = fetchType.toLowerCase();
			if ("get".equals(readerTypeID)) //$NON-NLS-1$
			{
				readerTypeID = "url"; //$NON-NLS-1$

				// Extract a more exact version from the file name if possible
				//
				String src = props.get("src"); //$NON-NLS-1$
				if (src.endsWith(".jar") || src.endsWith(".zip")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					int lastSlash = src.lastIndexOf('/');
					if (lastSlash < 0)
						lastSlash = 0;
					String vcName = src.substring(lastSlash + 1, src.length() - 4);
					if (vcName.startsWith(identifier + '_')) {
						try {
							version = Version.parseVersion(vcName.substring(identifier.length() + 1));
						} catch (IllegalArgumentException e) {
							// Ignore. For some reason this version was not a
							// valid OSGi version
						}
					}
				}
			} else if (readerTypeID.equals("p2iu")) //$NON-NLS-1$
				readerTypeID = "p2"; //$NON-NLS-1$

			ComponentIdentifier cid = new ComponentIdentifier(identifier, ctypeId, version);
			IReaderType readerType;
			try {
				readerType = core.getReaderType(readerTypeID);
			} catch (CoreException e) {
				logger.warning(NLS.bind(Messages.Unable_to_obtain_readertype_for_fetchtype_0_in_PDEmap_1, fetchType, streamName));
				continue;
			}
			receivingList.add(new MapFileEntry(cid, readerType, props));
		}
	}
}

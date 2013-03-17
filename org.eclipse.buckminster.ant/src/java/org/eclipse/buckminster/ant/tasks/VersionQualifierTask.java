/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.ant.tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.ant.Messages;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.UnmodifiableMapUnion;
import org.eclipse.buckminster.core.version.IQualifierGenerator;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 * 
 */
public class VersionQualifierTask {
	public static final String GENERATOR_PREFIX = "generator:"; //$NON-NLS-1$

	public static final String QUALIFIER_SUFFIX = "qualifier"; //$NON-NLS-1$

	public static final String PROPERTY_CONTEXT = "context"; //$NON-NLS-1$

	public static final String PROPERTY_NONE = "none"; //$NON-NLS-1$

	private static final String QUALIFIER_REPLACEMENT_PREFIX = "qualifier.replacement."; //$NON-NLS-1$

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm"); //$NON-NLS-1$

	private final Map<String, ? extends Object> properties;

	private final String qualifier;

	public VersionQualifierTask(File propertiesFile, String qualifier) throws CoreException {
		this.qualifier = qualifier;

		Map<String, ? extends Object> globalProps = AbstractActor.getActiveContext().getProperties();

		if (propertiesFile == null)
			properties = globalProps;
		else {
			InputStream input = null;
			try {
				input = new BufferedInputStream(new FileInputStream(propertiesFile));
				properties = new UnmodifiableMapUnion<String, Object>(new BMProperties(input), globalProps);
			} catch (IOException e) {
				throw BuckminsterException.fromMessage(e, NLS.bind(Messages.VersionQualifierTask_Unable_to_read_properties_from_0, propertiesFile));
			} finally {
				IOUtils.close(input);
			}
		}
	}

	public Map<String, ? extends Object> getProperties() {
		return properties;
	}

	public boolean isContextReplacement() {
		return qualifier == null || qualifier.equalsIgnoreCase(PROPERTY_CONTEXT);
	}

	public boolean isUsingGenerator(ComponentIdentifier ci) {
		Version version = ci.getVersion();
		if (version == null)
			return false;

		String vq = VersionHelper.getQualifier(version);
		if (vq == null || !vq.endsWith(QUALIFIER_SUFFIX))
			return false;

		String newQualifier = getQualifierReplacement(ci);
		return newQualifier != null && newQualifier.startsWith(GENERATOR_PREFIX);
	}

	public Version replaceQualifier(ComponentIdentifier ci, List<ComponentIdentifier> deps) {
		Version version = ci.getVersion();
		if (version == null)
			return version;

		String vq = VersionHelper.getQualifier(version);
		if (vq == null)
			return version;

		if (!vq.endsWith(QUALIFIER_SUFFIX))
			return version;

		String newQualifier = getQualifierReplacement(ci);
		if (newQualifier == null)
			return VersionHelper.replaceQualifier(version, null);

		if (newQualifier.startsWith(GENERATOR_PREFIX)) {
			String generatorId = newQualifier.substring(GENERATOR_PREFIX.length());
			try {
				IQualifierGenerator generator = CorePlugin.getDefault().getQualifierGenerator(generatorId);
				version = generator.generateQualifier(AbstractActor.getActiveContext(), ci, deps);
			} catch (CoreException e) {
				CorePlugin.getLogger().error(e, Messages.VersionQualifierTask_Unable_to_qualify_version);
			}
		} else {
			newQualifier = vq.replaceFirst(QUALIFIER_SUFFIX, newQualifier);
			if (!vq.equals(newQualifier))
				version = VersionHelper.replaceQualifier(version, newQualifier);
		}
		return version;
	}

	private String getQualifierReplacement(ComponentIdentifier id) {
		String newQualifier = null;
		if (isContextReplacement()) {
			if (properties.size() != 0) {
				// First we check to see if there is a match for a precise
				// version
				//
				StringBuilder bld = new StringBuilder();
				bld.append(id.getName());
				bld.append(',');
				int lenWithId = bld.length();

				// Lookup using id,<version without the .qualifier suffix>
				//
				String versionStr = id.getVersion().toString();
				bld.append(versionStr, 0, versionStr.length() - QUALIFIER_SUFFIX.length() - 1);
				String idName = bld.toString();

				bld.setLength(lenWithId);
				bld.append("0.0.0"); //$NON-NLS-1$
				String idNameWithEmptyVersion = bld.toString();

				int bestMatchLength = -1;
				for (Map.Entry<String, ? extends Object> entry : properties.entrySet()) {
					String key = entry.getKey();
					if (!key.startsWith(QUALIFIER_REPLACEMENT_PREFIX))
						continue;

					int idx = QUALIFIER_REPLACEMENT_PREFIX.length();
					int top = key.length();
					int exprLength = top - idx;
					if (exprLength <= bestMatchLength)
						continue;

					StringBuilder matchBld = new StringBuilder();
					matchBld.append('^');
					while (idx < top) {
						char c = key.charAt(idx++);
						switch (c) {
							case '?':
								matchBld.append('.');
								continue;
							case '*':
								matchBld.append(".*"); //$NON-NLS-1$
								continue;
							case '(':
							case ')':
							case '[':
							case ']':
							case '{':
							case '}':
							case '.':
							case '$':
							case '|':
								matchBld.append('\\');
								matchBld.append(c);
								continue;
							default:
								matchBld.append(c);
						}
					}
					matchBld.append('$');
					Pattern pattern = Pattern.compile(matchBld.toString());
					Matcher m = pattern.matcher(idName);
					if (!m.matches()) {
						m = pattern.matcher(idNameWithEmptyVersion);
						if (!m.matches())
							continue;
					}
					bestMatchLength = exprLength;
					newQualifier = entry.getValue().toString();
				}
			}

			if (newQualifier == null) {
				synchronized (dateFormat) {
					newQualifier = dateFormat.format(new Date());
				}
			}
		} else if (!qualifier.equalsIgnoreCase(PROPERTY_NONE))
			newQualifier = qualifier;
		return newQualifier;
	}
}

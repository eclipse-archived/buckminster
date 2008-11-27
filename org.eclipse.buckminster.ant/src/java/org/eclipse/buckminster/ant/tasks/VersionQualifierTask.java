/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
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

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.version.IQualifierGenerator;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 * 
 */
public class VersionQualifierTask
{
	public static final String GENERATOR_PREFIX = "generator:";

	public static final String QUALIFIER_SUFFIX = "qualifier";

	public static final String PROPERTY_CONTEXT = "context";

	public static final String PROPERTY_NONE = "none";

	private static final String QUALIFIER_REPLACEMENT_PREFIX = "qualifier.replacement.";

	private static final String MATCH_ALL = QUALIFIER_REPLACEMENT_PREFIX + '*';

	private static final SimpleDateFormat s_dateFormat = new SimpleDateFormat("yyyyMMddHHmm");

	private final Map<String, String> m_properties;

	private final String m_qualifier;

	public VersionQualifierTask(File propertiesFile, String qualifier) throws CoreException
	{
		m_qualifier = qualifier;

		Map<String, String> globalProps = AbstractActor.getActiveContext().getProperties();

		if(propertiesFile == null)
			m_properties = globalProps;
		else
		{
			InputStream input = null;
			try
			{
				input = new BufferedInputStream(new FileInputStream(propertiesFile));
				m_properties = new BMProperties(input);
				m_properties.putAll(globalProps);
			}
			catch(IOException e)
			{
				throw BuckminsterException.fromMessage(e, "Unable to read properties from %s", propertiesFile);
			}
			finally
			{
				IOUtils.close(input);
			}
		}
	}

	public Map<String, String> getProperties()
	{
		return m_properties;
	}

	private String getQualifierReplacement(ComponentIdentifier id)
	{
		String newQualifier = null;
		if(isContextReplacement())
		{
			if(m_properties.size() != 0)
			{
				// First we check to see if there is a match for a precise version
				//
				StringBuilder bld = new StringBuilder(QUALIFIER_REPLACEMENT_PREFIX);
				bld.append(id.getName());
				bld.append(',');
				int lenWithId = bld.length();

				// Lookup using id,<version without the .qualifier suffix>
				//
				String versionStr = id.getVersion().toString();
				bld.append(versionStr, 0, versionStr.length() - QUALIFIER_SUFFIX.length() - 1);
				newQualifier = m_properties.get(bld.toString());

				if(newQualifier == null)
				{
					// If not found, then lookup for the id,0.0.0
					//
					bld.setLength(lenWithId);
					bld.append("0.0.0");
					newQualifier = m_properties.get(bld.toString());
					if(newQualifier == null)
						newQualifier = m_properties.get(MATCH_ALL);
				}
			}

			if(newQualifier == null)
			{
				synchronized(s_dateFormat)
				{
					newQualifier = s_dateFormat.format(new Date());
				}
			}
		}
		else if(!m_qualifier.equalsIgnoreCase(PROPERTY_NONE))
			newQualifier = m_qualifier;
		return newQualifier;
	}

	public boolean isContextReplacement()
	{
		return m_qualifier == null || m_qualifier.equalsIgnoreCase(PROPERTY_CONTEXT);
	}

	public boolean isUsingGenerator(ComponentIdentifier ci)
	{
		IVersion version = ci.getVersion();
		if(version == null)
			return false;

		String qualifier = version.getQualifier();
		if(qualifier == null || !qualifier.endsWith(QUALIFIER_SUFFIX))
			return false;

		String newQualifier = getQualifierReplacement(ci);
		return newQualifier != null && newQualifier.startsWith(GENERATOR_PREFIX);
	}

	public IVersion replaceQualifier(ComponentIdentifier ci, List<ComponentIdentifier> deps)
	{
		IVersion version = ci.getVersion();
		if(version == null)
			return version;

		String qualifier = version.getQualifier();
		if(qualifier == null)
			return version;

		if(!qualifier.endsWith(QUALIFIER_SUFFIX))
			return version;

		String newQualifier = getQualifierReplacement(ci);
		if(newQualifier == null)
			return version.replaceQualifier(null);

		if(newQualifier.startsWith(GENERATOR_PREFIX))
		{
			String generatorId = newQualifier.substring(GENERATOR_PREFIX.length());
			try
			{
				IQualifierGenerator generator = CorePlugin.getDefault().getQualifierGenerator(generatorId);
				version = generator.generateQualifier(AbstractActor.getActiveContext(), ci, deps);
			}
			catch(CoreException e)
			{
				CorePlugin.getLogger().warning(e, "Unable to qualify version");
			}
		}
		else
		{
			newQualifier = qualifier.replaceFirst(QUALIFIER_SUFFIX, newQualifier);
			if(!qualifier.equals(newQualifier))
				version = version.replaceQualifier(newQualifier);
		}
		return version;
	}
}

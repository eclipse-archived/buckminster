/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cmdline.prefs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferenceFilter;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.core.runtime.preferences.PreferenceFilterEntry;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractPreferencesCommand extends AbstractCommand
{
	static private final OptionDescriptor SCOPE_OPTION = new OptionDescriptor('S', "scope", OptionValueType.REQUIRED);

	static private final OptionDescriptor FILE_OPTION = new OptionDescriptor('F', "file", OptionValueType.REQUIRED);

	class PreferenceFilter implements IPreferenceFilter
	{
		private final String[] m_scopes;

		private final Map m_mapping;

		PreferenceFilter(String[] scopes, Map mapping)
		{
			m_scopes = scopes;
			m_mapping = mapping;
		}

		public String[] getScopes()
		{
			return m_scopes;
		}

		public Map getMapping(String scope)
		{
			return m_mapping;
		}
	}

	private IScopeContext m_scope;

	private HashMap m_includes;

	private File m_prefsFile;

	protected OptionDescriptor[] getOptionDescriptors() throws Exception
	{
		return new OptionDescriptor[] { SCOPE_OPTION, FILE_OPTION };
	}

	protected void handleOption(Option option) throws Exception
	{
		if(option.is(SCOPE_OPTION))
		{
			if(m_scope != null)
				throw new UsageException("Only one scope can be given");

			String scopeName = option.getValue();
			if(scopeName.equalsIgnoreCase(InstanceScope.SCOPE))
				m_scope = new InstanceScope();
			else if(scopeName.equalsIgnoreCase(ConfigurationScope.SCOPE))
				m_scope = new ConfigurationScope();
			else
				throw new UsageException("Invalid scope. Valid scopes are " + ConfigurationScope.SCOPE + " and " + InstanceScope.SCOPE);
		}
		else if(option.is(FILE_OPTION))
		{
			if(m_prefsFile != null)
				throw new UsageException("Only one file can be given");
			m_prefsFile = new File(option.getValue());
		}
	}

	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		if(unparsed.length == 0)
			return;
		
		if(m_includes == null)
			m_includes = new HashMap();

		for(int idx = 0; idx < unparsed.length; ++idx)
		{
			String include = unparsed[idx];
			String rootKey = null;
			String[] subKeys = null;
			int sepIdx = include.indexOf('#');
			if(sepIdx < 0 || sepIdx >= 1)
			{
				if(sepIdx < 0)
					rootKey = include.trim();
				else
				{
					rootKey = include.substring(0, sepIdx).trim();
					subKeys = include.substring(sepIdx + 1).split(",");
					if(subKeys.length == 0)
						subKeys = null;
				}
			}
			if(rootKey == null || rootKey.length() == 0)
				throw new UsageException("Illegal include: '" + include
						+ "' Must be in the form <rootKey>[#<subKey>[,subKey...]]]");

			if(subKeys == null)
			{
				m_includes.put(rootKey, null);
				continue;
			}

			ArrayList pfes = (ArrayList)m_includes.get(rootKey);
			if(pfes == null)
				pfes = new ArrayList();
			
			for(int subIdx = 0; subIdx < subKeys.length; ++subIdx)
			{
				String subKey = subKeys[subIdx];
				subKey = subKey.trim();
				if(subKey.length() == 0)
					continue;

				// As a convenience, for a key like 'org.eclipse.jdt.core#compiler.codegen' we will
				// add both 'compiler.codegen' and 'org.eclipse.jdt.core.compiler.codegen'
				//
				pfes.add(new PreferenceFilterEntry(subKey));
				if(!subKey.startsWith(rootKey))
					pfes.add(new PreferenceFilterEntry(rootKey + '.' + subKey));
			}
			if(pfes.size() > 0)
				m_includes.put(rootKey, pfes);
		}
	}

	IEclipsePreferences getNode()
	{
		IPreferencesService prefService = Platform.getPreferencesService();
		IEclipsePreferences node;
		if(m_scope == null)
			node = prefService.getRootNode();
		else
			node = (IEclipsePreferences)prefService.getRootNode().node(m_scope.getName());
		return node;
	}

	File getFile()
	{
		return m_prefsFile;
	}

	IPreferenceFilter[] getFilter()
	{
		HashMap pfess = null;
		if(m_includes != null)
		{
			// Qualify the filter with specific includes
			//
			pfess = new HashMap();
			Iterator entries = m_includes.entrySet().iterator();
			while(entries.hasNext())
			{
				Map.Entry entry = (Map.Entry)entries.next();
				ArrayList pfes = (ArrayList)entry.getValue();
				if(pfes != null)
					pfess.put(entry.getKey(), pfes.toArray(new PreferenceFilterEntry[pfes.size()]));
				else
					pfess.put(entry.getKey(), null);
			}
		}

		PreferenceFilter filter;
		if(m_scope == null)
			filter = new PreferenceFilter(new String[] { InstanceScope.SCOPE, ConfigurationScope.SCOPE }, pfess);
		else
			filter = new PreferenceFilter(new String[] { m_scope.getName() }, pfess);
		return new IPreferenceFilter[] { filter };
	}
}

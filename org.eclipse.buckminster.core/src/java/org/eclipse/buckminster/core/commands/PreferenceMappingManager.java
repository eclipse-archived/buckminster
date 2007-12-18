/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.commands;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * @author kolwing
 * 
 */
public class PreferenceMappingManager
{
	static private final String PREFMAPPINGS_EXTPOINT = "org.eclipse.buckminster.cmdline.prefmappings";

	static private final String CLASS_ATTRIBUTE = "class";

	static private final String TEST_PREFIX = "org.eclipse.buckminster.cmdline.test.";

	private static WeakReference<PreferenceMappingManager> s_instanceRef;

	public static synchronized PreferenceMappingManager getInstance(boolean includeTests) throws CoreException
	{
		PreferenceMappingManager pmm = null;
		if (s_instanceRef != null)
			pmm = s_instanceRef.get();
		if (pmm == null || pmm.m_includeTests != includeTests)
		{
			pmm = new PreferenceMappingManager(includeTests);
			s_instanceRef = new WeakReference<PreferenceMappingManager>(pmm);
		}
		return pmm;
	}

	private final boolean m_includeTests;

	private final List<BasicPreferenceHandler> m_mappings;

	private PreferenceMappingManager(boolean includeTests) throws CoreException
	{
		m_includeTests = includeTests;
		m_mappings = this.findAllMappings();
	}

	private List<BasicPreferenceHandler> findAllMappings() throws CoreException
	{
		List<BasicPreferenceHandler> mappings = new ArrayList<BasicPreferenceHandler>();

		IExtensionRegistry er = Platform.getExtensionRegistry();
		IConfigurationElement[] elems = er.getConfigurationElementsFor(PREFMAPPINGS_EXTPOINT);
		int idx = elems.length;
		while(--idx >= 0)
		{
			IConfigurationElement elem = elems[idx];
			BasicPreferenceHandler bph;
			if (elem.getAttribute(CLASS_ATTRIBUTE) != null)
				bph = (BasicPreferenceHandler)elem.createExecutableExtension(CLASS_ATTRIBUTE);
			else
			{
				bph = new BasicPreferenceHandler();
				((IExecutableExtension)bph).setInitializationData(elem, CLASS_ATTRIBUTE, null);
			}
			String name = bph.getName();
			if (name.startsWith(TEST_PREFIX) && !m_includeTests)
				continue;
			mappings.add(bph);
		}
		return mappings;
	}

	public BasicPreferenceHandler getHandler(String name) throws UsageException
	{
		ArrayList<BasicPreferenceHandler> matches = null;
		int idx = m_mappings.size();
		while(--idx >= 0)
		{
			BasicPreferenceHandler mapping = m_mappings.get(idx);
			String prefName = mapping.getName();
			for(;;)
			{
				if(name.equals(prefName))
				{
					if(matches == null)
						matches = new ArrayList<BasicPreferenceHandler>();
					matches.add(mapping);
					break;
				}
				int dotIdx = prefName.indexOf('.');
				if(dotIdx < 0)
					break;
				prefName = prefName.substring(dotIdx + 1);
			}
		}

		if(matches == null)
			throw new UsageException("No preference matches " + name);

		int foundMatches = matches.size();
		if(foundMatches == 1)
			return matches.get(0);

		StringBuilder bld = new StringBuilder(80);
		bld.append("Preference ");
		bld.append(name);
		bld.append(" is ambigous. It matches ");
		for (int i = 0; i < foundMatches; i++)
		{
			if(i > 0)
			{
				bld.append(", ");
				if(i + 1 == foundMatches)
					bld.append("and ");
			}
			bld.append(matches.get(i).getName());
		}
		throw new UsageException(bld.toString());
	}

	public List<BasicPreferenceHandler> getAllHandlers(String pattern)
	{
		Pattern rx = (pattern == null) ? null : Pattern.compile(pattern);

		ArrayList<BasicPreferenceHandler> handlers = new ArrayList<BasicPreferenceHandler>();
		int top = m_mappings.size();
		for(int idx = 0; idx < top; ++idx)
		{
			BasicPreferenceHandler bph = m_mappings.get(idx);
			if (rx == null || rx.matcher(bph.getName()).find())
				handlers.add(bph);
		}

		Comparator<BasicPreferenceHandler> bphComparator = new Comparator<BasicPreferenceHandler>()
		{
			public int compare(BasicPreferenceHandler o1, BasicPreferenceHandler o2)
			{
				return o1.getName().compareTo(o2.getName());
			}
		};
		Collections.sort(handlers, bphComparator);
		return handlers;
	}
}

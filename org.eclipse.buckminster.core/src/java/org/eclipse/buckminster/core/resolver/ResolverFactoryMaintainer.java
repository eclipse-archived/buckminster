/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.resolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.IBuckminsterPreferenceConstants;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;

/**
 * The maintainer of all component query resolver factories and their sort order
 * @author Thomas Hallgren
 */
public class ResolverFactoryMaintainer implements IPreferenceChangeListener
{
	public static final String QUERY_RESOLVERS_POINT = CorePlugin.CORE_NAMESPACE + ".queryResolvers";
	static final String FACTORY_ELEM = "factory";

	private static final ResolverFactoryMaintainer s_instance;

	static
	{
		s_instance = new ResolverFactoryMaintainer();
		BuckminsterPreferences.addListener(s_instance);
	}

	public static ResolverFactoryMaintainer getInstance()
	{
		return s_instance;
	}

	private IResolverFactory[] m_resolverFactories;

	public synchronized void preferenceChange(PreferenceChangeEvent event)
	{
		if(IBuckminsterPreferenceConstants.QUERY_RESOLVER_SORT_ORDER.equals(event.getKey()))
		{
			m_resolverFactories = null;
		}
	}

	public synchronized IResolverFactory[] getResolverFactories()
	{
		if(m_resolverFactories == null)
			m_resolverFactories = ResolverFactoryMaintainer.createFactoriesByExtension();
		return m_resolverFactories;
	}

	public synchronized void setResolverFactories(IResolverFactory[] resolverFactories)
	{
		m_resolverFactories = resolverFactories;
	}

	public static String[] getRegisterFactoryIDs()
	{
		IConfigurationElement[] elems = Platform.getExtensionRegistry().getConfigurationElementsFor(ResolverFactoryMaintainer.QUERY_RESOLVERS_POINT);
		int idx = elems.length;
		String[] factoryIDs = new String[idx];
		while(--idx >= 0)
			factoryIDs[idx] = elems[idx].getAttribute("id");
		return factoryIDs;
	}

	private static IResolverFactory[] createFactoriesByExtension()
	{
		IConfigurationElement[] elems = Platform.getExtensionRegistry().getConfigurationElementsFor(ResolverFactoryMaintainer.QUERY_RESOLVERS_POINT);
		HashMap<String,IConfigurationElement> elemsById = new HashMap<String, IConfigurationElement>();
		for(IConfigurationElement elem : elems)
			elemsById.put(elem.getAttribute("id"), elem);
	
		Logger logger = CorePlugin.getLogger();
		String[] sortOrder = BuckminsterPreferences.getQueryResolverSortOrder();
		int numFactories = sortOrder.length;
	
		ArrayList<IResolverFactory> factories = new ArrayList<IResolverFactory>(numFactories);
		for(String factoryName : sortOrder)
		{
			IConfigurationElement elem = elemsById.remove(factoryName);
			if(elem == null)
			{
				logger.warning("Resolver factory " + factoryName +
					" defined in the Query Resolver Sort Order preference was skipped" +
					" since it has not been registered by any extension point.");
				continue;
			}
			try
			{
				factories.add((IResolverFactory)elem.createExecutableExtension(ResolverFactoryMaintainer.FACTORY_ELEM));
			}
			catch(CoreException e)
			{
				logger.error("Unable to instantiate Query Resolver Factory " + factoryName, e);
			}
		}
		int numNotInSortOrder = elemsById.size();
		if(numNotInSortOrder > 0)
		{
			Iterator<String> keys = elemsById.keySet().iterator();
			StringBuilder bld = new StringBuilder();
			bld.append("Resolver factor");
			boolean first = true;
			boolean plural = false;
			for(boolean hasNext = true; hasNext;)
			{
				String factoryName = keys.next();
				hasNext = keys.hasNext();
				if(first)
				{
					if(hasNext)
						bld.append("ies ");
					else
						bld.append("y ");
					first = false;
				}
				else
				{
					if(!hasNext)
					{
						if(plural)
							bld.append(',');
						bld.append(" and ");
					}
					else
						bld.append(", ");
					plural = true;
				}
				bld.append(factoryName);
			}
			bld.append(plural ? " were" : " was");
			bld.append(" skipped since ");
			bld.append(plural ? "they were" : "it was");
			bld.append(" not included in the Query Resolver Sort Order preference");
			logger.info(bld.toString());
		}
		return factories.toArray(new IResolverFactory[factories.size()]);
	}
}
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
 * 
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
			m_resolverFactories = createFactoriesByExtension();
		return m_resolverFactories;
	}

	public synchronized void setResolverFactories(IResolverFactory[] resolverFactories)
	{
		m_resolverFactories = resolverFactories;
	}

	public static String[] getRegisterFactoryIDs()
	{
		IConfigurationElement[] elems = Platform.getExtensionRegistry().getConfigurationElementsFor(
				ResolverFactoryMaintainer.QUERY_RESOLVERS_POINT);
		int idx = elems.length;
		String[] factoryIDs = new String[idx];
		while(--idx >= 0)
			factoryIDs[idx] = elems[idx].getAttribute("id");
		return factoryIDs;
	}

	private static IResolverFactory[] createFactoriesByExtension()
	{
		Logger logger = CorePlugin.getLogger();
		IConfigurationElement[] elems = Platform.getExtensionRegistry().getConfigurationElementsFor(
				ResolverFactoryMaintainer.QUERY_RESOLVERS_POINT);

		ArrayList<IResolverFactory> factories = new ArrayList<IResolverFactory>(elems.length);
		for(IConfigurationElement elem : elems)
		{
			try
			{
				factories.add((IResolverFactory)elem.createExecutableExtension(ResolverFactoryMaintainer.FACTORY_ELEM));
			}
			catch(CoreException e)
			{
				logger.error(e, "Unable to instantiate Query Resolver Factory %s", elem.getAttribute("id"));
			}
		}
		return factories.toArray(new IResolverFactory[factories.size()]);
	}

	public IResolverFactory[] getActiveResolverFactories()
	{
		IResolverFactory[] allFactories = getResolverFactories();
		HashMap<String, IResolverFactory> factoriesById = new HashMap<String, IResolverFactory>();
		for(IResolverFactory factory : allFactories)
			factoriesById.put(factory.getId(), factory);

		String[] sortOrder = BuckminsterPreferences.getQueryResolverSortOrder();
		int numFactories = sortOrder.length;

		ArrayList<IResolverFactory> factories = new ArrayList<IResolverFactory>(numFactories);
		for(String factoryName : sortOrder)
		{
			IResolverFactory factory = factoriesById.remove(factoryName);
			if(factory != null)
				factories.add(factory);
		}
		return factories.toArray(new IResolverFactory[factories.size()]);
	}
}

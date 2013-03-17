/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.resolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.IBuckminsterPreferenceConstants;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.osgi.util.NLS;

/**
 * The maintainer of all component query resolver factories and their sort order
 * 
 * @author Thomas Hallgren
 */
public class ResolverFactoryMaintainer implements IPreferenceChangeListener {
	public static final String QUERY_RESOLVERS_POINT = CorePlugin.CORE_NAMESPACE + ".queryResolvers"; //$NON-NLS-1$

	static final String FACTORY_ELEM = "factory"; //$NON-NLS-1$

	private static final ResolverFactoryMaintainer instance;

	static {
		instance = new ResolverFactoryMaintainer();
		BuckminsterPreferences.addListener(instance);
	}

	public static ResolverFactoryMaintainer getInstance() {
		return instance;
	}

	public static String[] getRegisterFactoryIDs() {
		IConfigurationElement[] elems = Platform.getExtensionRegistry().getConfigurationElementsFor(ResolverFactoryMaintainer.QUERY_RESOLVERS_POINT);
		int idx = elems.length;
		String[] factoryIDs = new String[idx];
		while (--idx >= 0)
			factoryIDs[idx] = elems[idx].getAttribute("id"); //$NON-NLS-1$
		return factoryIDs;
	}

	private static IResolverFactory[] createFactoriesByExtension() {
		Logger logger = CorePlugin.getLogger();
		IConfigurationElement[] elems = Platform.getExtensionRegistry().getConfigurationElementsFor(ResolverFactoryMaintainer.QUERY_RESOLVERS_POINT);

		ArrayList<IResolverFactory> factories = new ArrayList<IResolverFactory>(elems.length);
		for (IConfigurationElement elem : elems) {
			try {
				IResolverFactory factory = (IResolverFactory) elem.createExecutableExtension(ResolverFactoryMaintainer.FACTORY_ELEM);
				factories.add(factory);
			} catch (CoreException e) {
				logger.error(e, NLS.bind(Messages.Unable_to_instantiate_Query_Resolver_Factory_0, elem.getAttribute("id"))); //$NON-NLS-1$
			}
		}
		return factories.toArray(new IResolverFactory[factories.size()]);
	}

	private IResolverFactory[] resolverFactories;

	public IResolverFactory[] getActiveResolverFactories() {
		IResolverFactory[] allFactories = getResolverFactories();
		HashMap<String, IResolverFactory> factoriesById = new HashMap<String, IResolverFactory>();
		for (IResolverFactory factory : allFactories)
			factoriesById.put(factory.getId(), factory);

		String[] sortOrder = BuckminsterPreferences.getQueryResolverSortOrder();
		int numFactories = sortOrder.length;

		ArrayList<IResolverFactory> factories = new ArrayList<IResolverFactory>(numFactories);
		for (String factoryName : sortOrder) {
			IResolverFactory factory = factoriesById.remove(factoryName);
			if (factory != null)
				factories.add(factory);
		}
		return factories.toArray(new IResolverFactory[factories.size()]);
	}

	public synchronized IResolverFactory[] getResolverFactories() {
		if (resolverFactories == null) {
			IResolverFactory[] fcs = createFactoriesByExtension();
			resolverFactories = fcs;
			if (!BuckminsterPreferences.isCustomQuerySortOrder()) {
				setDefaultResolutionOrder();
				resolverFactories = fcs; // Restore since they are cleared by
											// the pref change
			}
		}
		return resolverFactories;
	}

	@Override
	public synchronized void preferenceChange(PreferenceChangeEvent event) {
		if (IBuckminsterPreferenceConstants.QUERY_RESOLVER_SORT_ORDER.equals(event.getKey())) {
			resolverFactories = null;
		}
	}

	public void setDefaultResolutionOrder() {
		Map<Integer, String> activeFactories = new TreeMap<Integer, String>();
		for (IResolverFactory factory : getResolverFactories()) {
			int prio = factory.getResolutionPriority();
			if (prio >= 0)
				activeFactories.put(new Integer(prio), factory.getId());
		}
		BuckminsterPreferences.setQueryResolverSortOrder(activeFactories.values().toArray(new String[activeFactories.size()]));
		BuckminsterPreferences.setCustomQueryResolverSortOrder(false);
	}

	public synchronized void setResolverFactories(IResolverFactory[] resolverFactories) {
		this.resolverFactories = resolverFactories;
	}
}

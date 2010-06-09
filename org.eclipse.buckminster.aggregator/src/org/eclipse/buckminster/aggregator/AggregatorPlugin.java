/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.loader.IRepositoryLoader;
import org.eclipse.buckminster.aggregator.p2.util.MetadataRepositoryResourceFactoryImpl;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.BundleContext;

/**
 * This is the central singleton for the Aggregator model plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public final class AggregatorPlugin extends EMFPlugin
{
	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public static class Implementation extends EclipsePlugin
	{
		private Map<String, Object> repositoryResourceFactories;

		private List<String> supportedNatures;

		/**
		 * Creates an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		public Implementation()
		{
			super();

			// Remember the static instance.
			//
			plugin = this;
		}

		public List<String> getSupportedRepositoryNatureList()
		{
			if(supportedNatures == null)
			{
				List<String> aux = new ArrayList<String>(repositoryResourceFactories.keySet());
				Collections.sort(aux);
				supportedNatures = Collections.unmodifiableList(aux);
			}
			return supportedNatures;
		}

		@Override
		public void start(BundleContext context) throws Exception
		{
			super.start(context);
			repositoryResourceFactories = new HashMap<String, Object>();

			for(IConfigurationElement extension : Platform.getExtensionRegistry().getConfigurationElementsFor(
					IRepositoryLoader.EXTENSION_POINT_ID))
				repositoryResourceFactories.put(
						extension.getAttribute(IRepositoryLoader.EXTENSION_POINT_ATTRIBUTE_NATURE),
						new MetadataRepositoryResourceFactoryImpl());
			Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().putAll(repositoryResourceFactories);
		}

		@Override
		public void stop(BundleContext context) throws Exception
		{
			super.stop(context);
			Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().keySet().removeAll(
					repositoryResourceFactories.keySet());
			repositoryResourceFactories = null;
			supportedNatures = null;
		}
	}

	/**
	 * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final AggregatorPlugin INSTANCE = new AggregatorPlugin();

	/**
	 * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static Implementation plugin;

	/**
	 * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the singleton instance.
	 * @generated
	 */
	public static Implementation getPlugin()
	{
		return plugin;
	}

	public static String getPluginID()
	{
		return plugin.getSymbolicName();
	}

	/**
	 * Create the instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AggregatorPlugin()
	{
		super(new ResourceLocator[] {});
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the singleton instance.
	 * @generated
	 */
	@Override
	public ResourceLocator getPluginResourceLocator()
	{
		return plugin;
	}

}

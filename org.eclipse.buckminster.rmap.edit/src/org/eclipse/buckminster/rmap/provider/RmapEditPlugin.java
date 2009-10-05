/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.buckminster.model.common.provider.CommonEditPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.common.EMFPlugin;

import org.eclipse.emf.common.util.ResourceLocator;

/**
 * This is the central singleton for the Rmap edit plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public final class RmapEditPlugin extends EMFPlugin
{
	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static class Implementation extends EclipsePlugin
	{
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
	}

	/**
	 * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final RmapEditPlugin INSTANCE = new RmapEditPlugin();

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

	private List<ProviderExtension> providerFactories;

	/**
	 * Create the instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RmapEditPlugin()
	{
		super(new ResourceLocator[] { CommonEditPlugin.INSTANCE, });
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

	public List<ProviderExtension> getProviderFactories()
	{
		if(providerFactories == null)
		{
			try
			{
				IExtensionRegistry registry = Platform.getExtensionRegistry();
				for(IConfigurationElement providerConf : registry.getConfigurationElementsFor("org.eclipse.buckminster.rmap.edit.rmapProviders"))
				{
					ProviderExtension pf = (ProviderExtension)providerConf.createExecutableExtension("class");
					if(providerFactories == null)
						providerFactories = new ArrayList<ProviderExtension>();
					providerFactories.add(pf);
				}
			}
			catch(CoreException e)
			{
				throw new RuntimeException(e);
			}
			if(providerFactories == null)
				providerFactories = Collections.emptyList();
		}
		return providerFactories;
	}

}

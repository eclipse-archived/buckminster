/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.generic.plugin.PluginClassHandle;
import org.eclipse.buckminster.generic.utils.PluginUtils;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.opml.model.Outline;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.ui.adapters.BrowseableAdapterFactory;
import org.eclipse.buckminster.ui.adapters.CSpecAdapterFactory;
import org.eclipse.buckminster.ui.adapters.CSpecDataNode;
import org.eclipse.buckminster.ui.adapters.OPMLAdapterFactory;
import org.eclipse.buckminster.ui.adapters.OPMLDataNode;
import org.eclipse.buckminster.ui.adapters.OutlineDataNode;
import org.eclipse.buckminster.ui.adapters.ResolutionAdapterFactory;
import org.eclipse.buckminster.ui.adapters.ResolutionDataNode;
import org.eclipse.buckminster.ui.adapters.ResourceAdapterFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class UiPlugin extends AbstractUIPlugin
{
	private static UiPlugin s_plugin;

	private ResourceBundle m_resourceBundle;

	private ScopedPreferenceStore m_preferenceStore;

	private static ResourceAdapterFactory s_resourceAdapterFactory;

	private static OPMLAdapterFactory s_opmlAdapterFactory;

	private static CSpecAdapterFactory s_cspecAdapterFactory;

	private static BrowseableAdapterFactory s_adapterFactory;
	
	private static ResolutionAdapterFactory s_resolutionAdapterFactory;

	// must be the same as the id in plugin.xml
	//
	static private final String s_id = "org.eclipse.buckminster.ui"; //$NON-NLS-1$

	static public final String s_themeId = s_id + ".theme";

	public static final String BUILDER_EDITORS_POINT = s_id + ".incrementalBuilderEditors";

	public static final String EPOINT_OPEN_FEED = s_id + ".OpenFeedAction";

	public static final String ATT_CLASS = "class";

	static public String getID()
	{
		return s_id;
	}

	/**
	 * The constructor.
	 */
	public UiPlugin()
	{
		super();
		s_plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	@Override
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		
		// register factory to convert an Outline to browseable URLs
		s_adapterFactory = new BrowseableAdapterFactory();
		s_cspecAdapterFactory = new CSpecAdapterFactory();
		s_opmlAdapterFactory = new OPMLAdapterFactory();
		s_resolutionAdapterFactory = new ResolutionAdapterFactory();
		s_resourceAdapterFactory = new ResourceAdapterFactory();
		
		IAdapterManager adapterManager = Platform.getAdapterManager();
		adapterManager.registerAdapters(s_adapterFactory, Outline.class);
		adapterManager.registerAdapters(s_adapterFactory, OutlineDataNode.class);
		adapterManager.registerAdapters(s_cspecAdapterFactory, CSpec.class);
		adapterManager.registerAdapters(s_cspecAdapterFactory, CSpecDataNode.class);

		adapterManager.registerAdapters(s_opmlAdapterFactory, Resolution.class);		
		adapterManager.registerAdapters(s_opmlAdapterFactory, ResolutionDataNode.class);		
		adapterManager.registerAdapters(s_opmlAdapterFactory, OPML.class);		
		adapterManager.registerAdapters(s_opmlAdapterFactory, OPMLDataNode.class);		
		adapterManager.registerAdapters(s_opmlAdapterFactory, Outline.class);		
		adapterManager.registerAdapters(s_opmlAdapterFactory, OutlineDataNode.class);		
			
		adapterManager.registerAdapters(s_resolutionAdapterFactory, Resolution.class);		
		adapterManager.registerAdapters(s_resolutionAdapterFactory, ResolutionDataNode.class);		

		adapterManager.registerAdapters(s_resourceAdapterFactory, IResource.class);
		adapterManager.registerAdapters(s_resourceAdapterFactory, IProject.class);
		adapterManager.registerAdapters(s_resourceAdapterFactory, IFile.class);
		
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	@Override
	public void stop(BundleContext context) throws Exception
	{
		IAdapterManager adapterManager = Platform.getAdapterManager();
		adapterManager.unregisterAdapters(s_adapterFactory);
		adapterManager.unregisterAdapters(s_cspecAdapterFactory);
		adapterManager.unregisterAdapters(s_opmlAdapterFactory);
		adapterManager.unregisterAdapters(s_resolutionAdapterFactory);
		adapterManager.unregisterAdapters(s_resolutionAdapterFactory);
		
		super.stop(context);
		s_plugin = null;
		m_resourceBundle = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static UiPlugin getDefault()
	{
		return s_plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not found.
	 */
	public static String getResourceString(String key)
	{
		ResourceBundle bundle = UiPlugin.getDefault().getResourceBundle();
		try
		{
			return (bundle != null)
					? bundle.getString(key)
					: key;
		}
		catch(MissingResourceException e)
		{
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle()
	{
		try
		{
			if(m_resourceBundle == null)
				m_resourceBundle = ResourceBundle.getBundle("org.eclipse.buckminster.ui.UiPluginResources"); //$NON-NLS-1$
		}
		catch(MissingResourceException x)
		{
			m_resourceBundle = null;
		}
		return m_resourceBundle;
	}

	public IPreferenceStore getBuckminsterPreferenceStore()
	{
		if(m_preferenceStore == null)
			m_preferenceStore = new ScopedPreferenceStore(new InstanceScope(), Buckminster.PLUGIN_ID);
		return m_preferenceStore;
	}

	public IPreferenceStore getBuckminsterPreferenceStore(String subKey)
	{
		return new ScopedPreferenceStore(new InstanceScope(), Buckminster.PLUGIN_ID + '/' + subKey);
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.buckminster.ui", path); //$NON-NLS-1$
	}

	public static IStatus toStatus(Throwable t)
	{
		IStatus status = null;
		if(t instanceof CoreException)
			status = ((CoreException)t).getStatus();
		else
			status = new Status(IStatus.ERROR, getID(), -1, t.getMessage(), t);
		return status;
	}
	private static OpenRssFeedActionHandle s_openRssFeedActionHandle;

	public  IObjectActionDelegate getOpenRssFeedAction()
	{
		// make sure we have the handle to the operation
		if(s_openRssFeedActionHandle == null)
		{
			IExtensionPoint epoint = Platform.getExtensionRegistry().getExtensionPoint(EPOINT_OPEN_FEED);
			IExtension[] extensions = epoint.getExtensions();

			for(int i = 0; i < extensions.length; i++)
			{
				IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
				for(int j = 0; j < configElements.length; j++)
				{
					IConfigurationElement configElement = configElements[j];

					if(s_openRssFeedActionHandle != null)
					{
						// duplicate
						getLog().log(new Status(IStatus.ERROR, this.getBundle().getSymbolicName(),
								"Duplicate OpenFeedAction found in plugin: "
								+ configElement.getDeclaringExtension().getNamespaceIdentifier()
								+ ". (duplicate ignored)."));
					}
					s_openRssFeedActionHandle = new OpenRssFeedActionHandle(configElement);
				}
			}
		}
		
		return s_openRssFeedActionHandle == null ? null : s_openRssFeedActionHandle.getHandle();
	}

	private static class OpenRssFeedActionHandle extends PluginClassHandle<IObjectActionDelegate>
	{
		private final String m_operationId;

		public OpenRssFeedActionHandle(IConfigurationElement configElement)
		{
			super(s_plugin, configElement, IObjectActionDelegate.class, UiPlugin.EPOINT_OPEN_FEED);
			m_operationId = PluginUtils.getAttribute(configElement, UiPlugin.ATT_CLASS, null);
		}

		public String getOperationId()
		{
			return m_operationId;
		}
	}
	
}

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

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.generic.plugin.PluginClassHandle;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.ui.adapters.CSpecAdapterFactory;
import org.eclipse.buckminster.ui.adapters.CSpecDataNode;
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
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class UiPlugin extends AbstractUIPlugin {
	private static class OpenRssFeedActionHandle extends PluginClassHandle<IObjectActionDelegate> {
		public OpenRssFeedActionHandle(IConfigurationElement configElement) {
			super(plugin, configElement, IObjectActionDelegate.class, UiPlugin.EPOINT_OPEN_FEED);
		}
	}

	private static UiPlugin plugin;

	private ScopedPreferenceStore preferenceStore;

	private static ResourceAdapterFactory resourceAdapterFactory;

	private static CSpecAdapterFactory cspecAdapterFactory;

	private static ResolutionAdapterFactory resolutionAdapterFactory;

	// must be the same as the id in plugin.xml
	//
	static private final String id = "org.eclipse.buckminster.ui"; //$NON-NLS-1$

	static public final String themeId = id + ".theme"; //$NON-NLS-1$

	public static final String BUILDER_EDITORS_POINT = id + ".incrementalBuilderEditors"; //$NON-NLS-1$

	public static final String EPOINT_OPEN_FEED = id + ".OpenFeedAction"; //$NON-NLS-1$

	public static final String ATT_CLASS = "class"; //$NON-NLS-1$

	private static OpenRssFeedActionHandle openRssFeedActionHandle;

	/**
	 * Returns the shared instance.
	 */
	public static UiPlugin getDefault() {
		return plugin;
	}

	static public String getID() {
		return id;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.buckminster.ui", path); //$NON-NLS-1$
	}

	/**
	 * Returns the standard display to be used. The method first checks, if the
	 * thread calling this method has an associated display. If so, this display
	 * is returned. Otherwise the method returns the default display.
	 */
	public static Display getStandardDisplay() {
		Display display = Display.getCurrent();
		if (display == null) {
			display = Display.getDefault();
		}
		return display;
	}

	public static IStatus toStatus(Throwable t) {
		IStatus status = null;
		if (t instanceof CoreException)
			status = ((CoreException) t).getStatus();
		else
			status = new Status(IStatus.ERROR, getID(), -1, t.getMessage(), t);
		return status;
	}

	/**
	 * The constructor.
	 */
	public UiPlugin() {
		super();
		plugin = this;
	}

	public IPreferenceStore getBuckminsterPreferenceStore() {
		if (preferenceStore == null)
			preferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, Buckminster.PLUGIN_ID);
		return preferenceStore;
	}

	public IPreferenceStore getBuckminsterPreferenceStore(String subKey) {
		return new ScopedPreferenceStore(InstanceScope.INSTANCE, Buckminster.PLUGIN_ID + '/' + subKey);
	}

	public IObjectActionDelegate getOpenRssFeedAction() {
		// make sure we have the handle to the operation
		if (openRssFeedActionHandle == null) {
			IExtensionPoint epoint = Platform.getExtensionRegistry().getExtensionPoint(EPOINT_OPEN_FEED);
			IExtension[] extensions = epoint.getExtensions();

			for (int i = 0; i < extensions.length; i++) {
				IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
				for (int j = 0; j < configElements.length; j++) {
					IConfigurationElement configElement = configElements[j];

					if (openRssFeedActionHandle != null) {
						// duplicate
						getLog().log(
								new Status(IStatus.ERROR, this.getBundle().getSymbolicName(), NLS.bind(Messages.duplicate_0_found_in_plugin_1,
										"OpenFeedAction", //$NON-NLS-1$
										configElement.getDeclaringExtension().getNamespaceIdentifier())));
					}
					openRssFeedActionHandle = new OpenRssFeedActionHandle(configElement);
				}
			}
		}

		return openRssFeedActionHandle == null ? null : openRssFeedActionHandle.getHandle();
	}

	/**
	 * This method is called upon plug-in activation
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);

		// register factory to convert an Outline to browseable URLs
		cspecAdapterFactory = new CSpecAdapterFactory();
		resolutionAdapterFactory = new ResolutionAdapterFactory();
		resourceAdapterFactory = new ResourceAdapterFactory();

		IAdapterManager adapterManager = Platform.getAdapterManager();
		adapterManager.registerAdapters(cspecAdapterFactory, CSpec.class);
		adapterManager.registerAdapters(cspecAdapterFactory, CSpecDataNode.class);

		adapterManager.registerAdapters(resolutionAdapterFactory, Resolution.class);
		adapterManager.registerAdapters(resolutionAdapterFactory, ResolutionDataNode.class);

		adapterManager.registerAdapters(resourceAdapterFactory, IResource.class);
		adapterManager.registerAdapters(resourceAdapterFactory, IProject.class);
		adapterManager.registerAdapters(resourceAdapterFactory, IFile.class);

	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		IAdapterManager adapterManager = Platform.getAdapterManager();
		adapterManager.unregisterAdapters(cspecAdapterFactory);
		adapterManager.unregisterAdapters(resolutionAdapterFactory);
		adapterManager.unregisterAdapters(resolutionAdapterFactory);

		super.stop(context);
		plugin = null;
	}
}

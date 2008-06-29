/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/
package org.eclipse.equinox.p2.authoring;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.TouchpointTypeBuilder;
import org.eclipse.equinox.p2.authoring.internal.touchpoints.EclipseTouchpoint_1_0;
import org.eclipse.equinox.p2.authoring.internal.touchpoints.NativeTouchpoint_1_0;
import org.eclipse.equinox.p2.authoring.internal.touchpoints.NullTouchpoint;
import org.eclipse.equinox.p2.authoring.spi.ITouchpointTypeDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class P2AuthoringUIPlugin extends AbstractUIPlugin
{

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.buckminster.distro.ui"; //$NON-NLS-1$

	// The shared instance
	private static P2AuthoringUIPlugin plugin;

	// Form colors shared by all forms in this plugin
	private FormColors m_formColors;

	private Map<String, ITouchpointTypeDescriptor> m_touchpointTypeDescs;

	private NullTouchpoint m_none;

	/**
	 * The constructor
	 */
	public P2AuthoringUIPlugin()
	{
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		if(m_formColors != null)
			m_formColors.dispose();
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static P2AuthoringUIPlugin getDefault()
	{
		return plugin;
	}

	public FormColors getFormColors(Display display)
	{
		if(m_formColors == null)
		{
			m_formColors = new FormColors(display);
			m_formColors.markShared();
		}
		return m_formColors;
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry registry)
	{
		registerImage(registry, P2AuthoringImages.IMG_HORIZONTAL, "th_horizontal.gif"); //$NON-NLS-1$
		registerImage(registry, P2AuthoringImages.IMG_VERTICAL, "th_vertical.gif"); //$NON-NLS-1$

		registerImage(registry, P2AuthoringImages.IMG_IU, "iu_obj.gif"); //$NON-NLS-1$

		registerImage(registry, P2AuthoringImages.IMG_FRAGMENT, "frgmt_obj.gif"); //$NON-NLS-1$
		registerImage(registry, P2AuthoringImages.IMG_FRAGMENTS, "frgmts_obj.gif"); //$NON-NLS-1$

		registerImage(registry, P2AuthoringImages.IMG_BUNDLE, "bundle_obj.gif"); //$NON-NLS-1$

		registerImage(registry, P2AuthoringImages.IMG_REQ_CAPABILITY, "req_plugin_obj.gif"); //$NON-NLS-1$
		registerImage(registry, P2AuthoringImages.IMG_REQ_CAPABILITIES, "req_plugins_obj.gif"); //$NON-NLS-1$

		registerImage(registry, P2AuthoringImages.IMG_PROV_CAPABILITY, "prov_plugin_obj.gif"); //$NON-NLS-1$
		registerImage(registry, P2AuthoringImages.IMG_PROV_CAPABILITIES, "prov_plugins_obj.gif"); //$NON-NLS-1$

		registerImage(registry, P2AuthoringImages.IMG_OVERVIEW, "overview_obj.gif"); //$NON-NLS-1$

		registerImage(registry, P2AuthoringImages.IMG_JAR, "jar010_obj.gif"); //$NON-NLS-1$

		registerImage(registry, P2AuthoringImages.IMG_LIB, "java_lib_obj.gif"); //$NON-NLS-1$

		registerImage(registry, P2AuthoringImages.IMG_PLUGIN, "plugins.gif"); //$NON-NLS-1$
		registerImage(registry, P2AuthoringImages.IMG_FEATURE, "feature_obj.gif"); //$NON-NLS-1$

		registerImage(registry, P2AuthoringImages.IMG_CATEGORY, "category_obj.gif"); //$NON-NLS-1$	
		registerImage(registry, P2AuthoringImages.IMG_FILE, "file_obj.gif"); //$NON-NLS-1$	
		registerImage(registry, P2AuthoringImages.IMG_FOLDER, "fldr_obj.gif"); //$NON-NLS-1$	
		registerImage(registry, P2AuthoringImages.IMG_PROJECT, "prj_obj.gif"); //$NON-NLS-1$	
		registerImage(registry, P2AuthoringImages.IMG_RSS, "rsslink.gif"); //$NON-NLS-1$	
		registerImage(registry, P2AuthoringImages.IMG_PACKAGE, "package_obj.gif"); //$NON-NLS-1$	

	}

	private void registerImage(ImageRegistry registry, String key, String fileName)
	{
		try
		{
			IPath path = new Path("icons/" + fileName); //$NON-NLS-1$
			URL url = FileLocator.find(getBundle(), path, null);
			if(url != null)
			{
				ImageDescriptor desc = ImageDescriptor.createFromURL(url);
				registry.put(key, desc);
			}
		}
		catch(Exception e)
		{
		}
	}
	private synchronized void initializeTouchpointType()
	{
		if(m_touchpointTypeDescs != null)
			return;
		m_touchpointTypeDescs = new LinkedHashMap<String, ITouchpointTypeDescriptor>(2);

		m_none = new NullTouchpoint();
		m_touchpointTypeDescs.put(m_none.getTypeId() + " " + m_none.getVersionString(), m_none);
		
		EclipseTouchpoint_1_0 e = new EclipseTouchpoint_1_0();
		m_touchpointTypeDescs.put(e.getTypeId() + " " + e.getVersionString(), e);
		
		NativeTouchpoint_1_0 n = new NativeTouchpoint_1_0();
		m_touchpointTypeDescs.put(n.getTypeId() + " " + n.getVersionString(), n);	
		
		// TODO: Pick up from extension point
	}
	public ITouchpointTypeDescriptor getTouchpointType(String typeKey, String versionString)
	{
		if(m_touchpointTypeDescs == null)
			initializeTouchpointType();
		
		String key = typeKey.trim() + " " + versionString.trim();
		return m_touchpointTypeDescs.get(key);
	}
	/**
	 * Returns descriptor for the touchpoint type. If type is null, the "null type" is returned. Null
	 * is returned for unknown types.
	 * @param builder
	 * @return null if type is unknown, else a type, or the special "null type"
	 */
	public ITouchpointTypeDescriptor getTouchpointType(TouchpointTypeBuilder builder)
	{
		if(builder == null)
			return m_none;
		return getTouchpointType(builder.getTypeid(), builder.getVersion());
	}
	ITouchpointTypeDescriptor[] getTouchpointTypes()
	{
		if(m_touchpointTypeDescs == null)
			initializeTouchpointType();
		return m_touchpointTypeDescs.values().toArray(new ITouchpointTypeDescriptor[m_touchpointTypeDescs.size()]);
	}
}

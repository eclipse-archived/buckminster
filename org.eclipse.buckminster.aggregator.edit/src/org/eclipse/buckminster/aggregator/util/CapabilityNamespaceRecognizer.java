/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.aggregator.provider.AggregatorEditPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * @author Karel Brezina
 * 
 */
public class CapabilityNamespaceRecognizer
{
	static class LabelDescriptor
	{
		String labelKey;

		String imageKey;

		public LabelDescriptor(String labelKey, String imageKey)
		{
			this.labelKey = labelKey;
			this.imageKey = imageKey;
		}
	}

	private static Map<String, LabelDescriptor> m_namespaceMap;

	private static Map<Pattern, LabelDescriptor> m_namespaceMatchMap;

	static
	{
		m_namespaceMap = new HashMap<String, LabelDescriptor>();
		m_namespaceMap.put("org.eclipse.equinox.p2.iu", new LabelDescriptor("_UI_Capability_IU_type",
				"full/obj16/InstallableUnit"));
		m_namespaceMap.put("org.eclipse.equinox.p2.eclipse.type", new LabelDescriptor("_UI_Capability_Type_type", null));
		m_namespaceMap.put("org.eclipse.equinox.p2.localization", new LabelDescriptor(
				"_UI_Capability_Localization_type", null));
		m_namespaceMap.put("org.eclipse.equinox.p2.flavor", new LabelDescriptor("_UI_Capability_Flavor_type", null));
		m_namespaceMap.put("org.eclipse.update.feature", new LabelDescriptor("_UI_Capability_Feature_type",
				"full/obj16/Feature"));
		m_namespaceMap.put("osgi.bundle", new LabelDescriptor("_UI_Capability_Bundle_type", "full/obj16/Bundle"));
		m_namespaceMap.put("osgi.fragment", new LabelDescriptor("_UI_Capability_Fragment_type", "full/obj16/Fragment"));
		m_namespaceMap.put("java.package", new LabelDescriptor("_UI_Capability_JavaPackage_type",
				"full/obj16/JavaPackage"));

		m_namespaceMatchMap = new HashMap<Pattern, LabelDescriptor>();
		m_namespaceMatchMap.put(Pattern.compile("^tooling.*"), new LabelDescriptor("_UI_Capability_Tooling_type", null));
	}

	public static Object getImage(String namespace)
	{
		LabelDescriptor ld = getLabelDescriptor(namespace);

		if(ld == null || ld.imageKey == null)
			return null;

		return getResourceLocator().getImage(ld.imageKey);
	}

	public static String getLabel(String namespace)
	{
		LabelDescriptor ld = getLabelDescriptor(namespace);

		if(ld == null || ld.labelKey == null)
			return null;

		return getResourceLocator().getString(ld.labelKey);
	}

	private static LabelDescriptor getLabelDescriptor(String namespace)
	{
		LabelDescriptor ld = m_namespaceMap.get(namespace);

		if(ld == null)
			for(Pattern pattern : m_namespaceMatchMap.keySet())
				if(pattern.matcher(namespace).matches())
					return m_namespaceMatchMap.get(pattern);

		return ld;
	}

	private static ResourceLocator getResourceLocator()
	{
		return AggregatorEditPlugin.INSTANCE;
	}

}

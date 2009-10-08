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

import org.eclipse.buckminster.aggregator.AggregatorPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * @author Karel Brezina
 * 
 */
public enum CapabilityNamespace
{
	IU("_UI_Capability_IU_type"), TYPE("_UI_Capability_Type_type"), LOCALIZATION("_UI_Capability_Localization_type"), FLAVOR(
			"_UI_Capability_Flavor_type"), FEATURE("_UI_Capability_Feature_type"), BUNDLE("_UI_Capability_Bundle_type"), FRAGMENT(
			"_UI_Capability_Fragment_type"), JAVA_PACKAGE("_UI_Capability_JavaPackage_type"), TOOLING(
			"_UI_Capability_Tooling_type"), UNKNOWN("_UI_Capability_Unknown_type");

	private static Map<String, CapabilityNamespace> m_namespaceMap;

	private static Map<Pattern, CapabilityNamespace> m_namespaceMatchMap;

	static
	{
		m_namespaceMap = new HashMap<String, CapabilityNamespace>();
		m_namespaceMap.put("org.eclipse.equinox.p2.iu", IU);
		m_namespaceMap.put("org.eclipse.equinox.p2.eclipse.type", TYPE);
		m_namespaceMap.put("org.eclipse.equinox.p2.localization", LOCALIZATION);
		m_namespaceMap.put("org.eclipse.equinox.p2.flavor", FLAVOR);
		m_namespaceMap.put("org.eclipse.update.feature", FEATURE);
		m_namespaceMap.put("osgi.bundle", BUNDLE);
		m_namespaceMap.put("osgi.fragment", FRAGMENT);
		m_namespaceMap.put("java.package", JAVA_PACKAGE);

		m_namespaceMatchMap = new HashMap<Pattern, CapabilityNamespace>();
		m_namespaceMatchMap.put(Pattern.compile("^tooling.*"), TOOLING);
	}

	public static CapabilityNamespace byId(String namespaceId)
	{
		CapabilityNamespace namespace = m_namespaceMap.get(namespaceId);

		if(namespace == null)
			for(Pattern pattern : m_namespaceMatchMap.keySet())
				if(pattern.matcher(namespaceId).matches())
					return m_namespaceMatchMap.get(pattern);

		if(namespace == null)
		{
			namespace = UNKNOWN;
			namespace.m_label = namespaceId + ":";
		}

		return namespace;
	}

	private static ResourceLocator getResourceLocator()
	{
		return AggregatorPlugin.INSTANCE;
	}

	private String m_label;

	private CapabilityNamespace(String label)
	{
		m_label = getResourceLocator().getString(label);
	}

	public String getLabel()
	{
		return m_label;
	}

}

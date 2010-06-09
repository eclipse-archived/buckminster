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

import org.eclipse.buckminster.aggregator.provider.AggregatorEditPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * @author Karel Brezina
 * 
 */
public class CapabilityNamespaceImageProvider
{
	private static Map<CapabilityNamespace, String> m_namespaceMap;

	static
	{
		m_namespaceMap = new HashMap<CapabilityNamespace, String>();
		m_namespaceMap.put(CapabilityNamespace.FEATURE, "full/obj16/Feature");
		m_namespaceMap.put(CapabilityNamespace.BUNDLE, "full/obj16/Bundle");
		m_namespaceMap.put(CapabilityNamespace.FRAGMENT, "full/obj16/Fragment");
		m_namespaceMap.put(CapabilityNamespace.JAVA_PACKAGE, "full/obj16/JavaPackage");
		m_namespaceMap.put(CapabilityNamespace.IU, "full/obj16/InstallableUnit");
	}

	public static Object getImage(String namespace)
	{
		CapabilityNamespace cn = CapabilityNamespace.byId(namespace);
		String imageKey = m_namespaceMap.get(cn);
		return imageKey == null
				? null
				: getResourceLocator().getImage(imageKey);
	}

	private static ResourceLocator getResourceLocator()
	{
		return AggregatorEditPlugin.INSTANCE;
	}

}

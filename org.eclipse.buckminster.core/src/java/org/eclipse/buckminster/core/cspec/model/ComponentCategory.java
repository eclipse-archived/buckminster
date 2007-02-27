/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

/**
 * @author Thomas Hallgren
 */
public class ComponentCategory
{
	private static final String COMPONENT_CATEGORIES_POINT = CorePlugin.CORE_NAMESPACE + ".componentCategories";

	public static ComponentCategory getCategory(String name)
	{
		if(name != null)
			for(IConfigurationElement elem : getElements())
				if(elem.getAttribute("name").equals(name))
					return new ComponentCategory(elem);
		return null;
	}

	public static String[] getCategoryNames(boolean includeEmptyEntry)
	{
		IConfigurationElement[] elems = getElements();
		int idx = elems.length;
		ArrayList<String> names = new ArrayList<String>(idx+1);
		if(includeEmptyEntry)
			names.add("");
		while(--idx >= 0)
			names.add(elems[idx].getAttribute("name"));
		Collections.sort(names);
		return names.toArray(new String[names.size()]);
	}

	private static IConfigurationElement[] getElements()
	{
		IExtensionRegistry exReg = Platform.getExtensionRegistry();
		return exReg.getConfigurationElementsFor(COMPONENT_CATEGORIES_POINT);
	}

	private final String m_name;

	private final IPath m_relativeLocation;

	private final Pattern m_desiredNamePattern;

	private final Pattern m_substituteNamePattern;

	private final String m_nameSubstitution;

	private ComponentCategory(IConfigurationElement elem)
	{
		m_name = elem.getAttribute("name");
		String tmp = elem.getAttribute("relativeLocation");
		m_relativeLocation = tmp == null ? null : Path.fromPortableString(tmp);
		tmp = elem.getAttribute("desiredNamePattern");
		m_desiredNamePattern = tmp == null ? null : Pattern.compile(tmp);
		tmp = elem.getAttribute("substituteNamePattern");
		m_substituteNamePattern = tmp == null ? null : Pattern.compile(tmp);
		m_nameSubstitution = elem.getAttribute("nameSubstitution");
	}

	public Pattern getDesiredNamePattern()
	{
		return m_desiredNamePattern;
	}

	public String getName()
	{
		return m_name;
	}

	public String getNameSubstitution()
	{
		return m_nameSubstitution;
	}

	public IPath getRelativeLocation()
	{
		return m_relativeLocation;
	}

	public Pattern getSubstituteNamePattern()
	{
		return m_substituteNamePattern;
	}
}

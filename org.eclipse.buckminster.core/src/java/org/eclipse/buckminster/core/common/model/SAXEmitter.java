/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.common.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.buckminster.core.helpers.MapUnion;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public abstract class SAXEmitter
{
	public static void emitProperties(ContentHandler handler, Map<String, String> props, String namespace,
			String prefix, boolean raw, boolean includeDefaults) throws SAXException
	{
		if(raw && props instanceof ExpandingProperties)
		{
			((ExpandingProperties)props).emitProperties(handler, namespace, prefix, includeDefaults);
			return;
		}

		TreeSet<String> sorted = new TreeSet<String>();
		if(includeDefaults)
		{
			for(String name : props.keySet())
			{
				// We don't include the system properties here. Perhaps we
				// should? But
				// then again, in order to have everything, we'd need the
				// environment,
				// and the OS configuration info as well (at least).
				//
				String sysValue = System.getProperty(name);
				if(sysValue != null && sysValue.equals(props.get(name)))
					continue;

				sorted.add(name);
			}
		}
		else
		{
			Set<String> keySet;
			if(props instanceof IProperties)
				keySet = ((IProperties)props).overlayKeySet();
			else if(props instanceof MapUnion)
				keySet = ((MapUnion<String, String>)props).overlayKeySet();
			else
				keySet = props.keySet();
			for(String name : keySet)
				sorted.add(name);
		}

		String plName = "property"; //$NON-NLS-1$
		String pqName = Utils.makeQualifiedName(prefix, plName);

		AttributesImpl attrs = new AttributesImpl();
		boolean withMutableAttr = (props instanceof IProperties) && ((IProperties)props).supportsMutability();
		for(String name : sorted)
		{
			String value = props.get(name);
			if(value == null || value.length() == 0)
				continue;

			attrs.clear();
			Utils.addAttribute(attrs, "key", name); //$NON-NLS-1$
			Utils.addAttribute(attrs, "value", value); //$NON-NLS-1$
			if(withMutableAttr && ((IProperties)props).isMutable(name))
				Utils.addAttribute(attrs, "mutable", "true"); //$NON-NLS-1$ //$NON-NLS-2$
			handler.startElement(namespace, plName, pqName, attrs);
			handler.endElement(namespace, plName, pqName);
		}
	}
}

/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.preferences;

import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.xml.sax.helpers.AttributesImpl;


/**
 * @author Thomas Hallgren
 */
public abstract class NodeWrapper extends AbstractSaxableElement
{
	private final Preferences m_nodePrefs;

	NodeWrapper(Preferences nodePrefs)
	{
		m_nodePrefs = nodePrefs;
	}

	protected final void putString(String name, String value)
	{
		if (value == null)
			m_nodePrefs.remove(name);
		else
			m_nodePrefs.put(name, value);
	}

	public void clear() throws BackingStoreException
	{
		m_nodePrefs.clear();
		for(String child : m_nodePrefs.childrenNames())
			m_nodePrefs.node(child).removeNode();
	}

	protected static void deepCopy(Preferences thisPrefs, Preferences copyPrefs) throws BackingStoreException
	{
		for(String key : thisPrefs.keys())
		{
			String value = thisPrefs.get(key, null);
			if(value != null)
				copyPrefs.put(key, value);
		}

		for(String child : thisPrefs.childrenNames())
		{
			Preferences thisChildPrefs = thisPrefs.node(child);
			Preferences copyChildPrefs = copyPrefs.node(child);
			deepCopy(thisChildPrefs, copyChildPrefs);
		}
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;

		if (!(o instanceof NodeWrapper))
			return false;
		NodeWrapper that = (NodeWrapper)o;

		if (!this.getName().equals(that.getName()))
			return false;

		return true;
	}

	public final String getName()
	{
		return m_nodePrefs.name();
	}

	@Override
	public int hashCode()
	{
		return this.getName().hashCode();
	}

	final Preferences getPreferences()
	{
		return m_nodePrefs;
	}

	public void remove() throws BackingStoreException
	{
		m_nodePrefs.removeNode();
	}

	public final void save() throws BackingStoreException
	{
		m_nodePrefs.flush();
	}

	protected static final void addAttribute(AttributesImpl attrs, String name, String value)
	{
		if(value != null)
			Utils.addAttribute(attrs, name, value);
	}
}

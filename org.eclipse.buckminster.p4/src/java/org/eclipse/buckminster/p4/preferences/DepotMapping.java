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

import java.util.regex.Pattern;

import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class DepotMapping extends NodeWrapper
{
	public static final String TAG = "depotMapping"; //$NON-NLS-1$

	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	public static final String ATTR_DEPOT_PATTERN = "depotPattern"; //$NON-NLS-1$

	public static final String ATTR_LOCAL_REPLACEMENT = "localReplacement"; //$NON-NLS-1$

	private final Client m_client;

	DepotMapping(Client client, Preferences depotMappingNode)
	{
		super(depotMappingNode);
		m_client = client;
	}

	public DepotMapping createCopy(String newName) throws BackingStoreException
	{
		DepotMapping copy = m_client.addDepotMapping(newName);
		deepCopy(getPreferences(), copy.getPreferences());
		return copy;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;

		if(!super.equals(o))
			return false;

		if(!(o instanceof DepotMapping))
			return false;
		DepotMapping that = (DepotMapping)o;

		if(m_client != that.m_client)
			return false;

		return true;
	}

	public final Client getClient()
	{
		return m_client;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public final Pattern getDepotPattern()
	{
		String localPathExp = getPreferences().get(DepotMapping.ATTR_DEPOT_PATTERN, null);
		return (localPathExp == null)
				? null
				: Pattern.compile(localPathExp);
	}

	public final String getLocalReplacement()
	{
		return getPreferences().get(DepotMapping.ATTR_LOCAL_REPLACEMENT, null);
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		hc = 37 * hc + m_client.hashCode();

		return hc;
	}

	public void setDepotPattern(Pattern pattern)
	{
		putString(DepotMapping.ATTR_DEPOT_PATTERN, pattern == null
				? null
				: pattern.pattern());
	}

	public void setLocalReplacement(String replacement)
	{
		putString(DepotMapping.ATTR_LOCAL_REPLACEMENT, replacement);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		addAttribute(attrs, ATTR_NAME, getName());
		addAttribute(attrs, ATTR_DEPOT_PATTERN, getDepotPattern().toString());
		addAttribute(attrs, ATTR_LOCAL_REPLACEMENT, getLocalReplacement());
	}

	@Override
	protected void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException
	{
	}
}

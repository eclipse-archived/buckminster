/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cmdline;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.osgi.util.NLS;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * @author kolwing
 * 
 */
public class BasicPreferenceHandler implements IExecutableExtension
{
	private static final String NAME_ATTRIB = "name"; //$NON-NLS-1$

	private static final String KEY_ATTRIB = "key"; //$NON-NLS-1$

	private static final String DESC_ATTRIB = "description"; //$NON-NLS-1$

	private static final char SLASH = '/';

	private String m_name;

	private String m_key;

	private String m_description;

	private static final IEclipsePreferences s_eclipsePrefs = Platform.getPreferencesService().getRootNode();

	public String get(String defaultValue) throws CoreException
	{
		String[] prefNodeAndKey = pathAsNodeAndKey(getKey());
		return s_eclipsePrefs.node(prefNodeAndKey[0]).get(prefNodeAndKey[1], defaultValue);
	}

	public final String getDescription()
	{
		return m_description;
	}

	public final String getKey()
	{
		return m_key;
	}

	public final String getName()
	{
		return m_name;
	}

	public void set(String value) throws BackingStoreException
	{
		String key = getKey();
		if(key == null)
			throw new IllegalArgumentException(NLS.bind(Messages.No_handler_registered_for_preference_0,
					getName()));
		String[] prefNodeAndKey = pathAsNodeAndKey(getKey());
		Preferences node = s_eclipsePrefs.node(prefNodeAndKey[0]);
		node.put(prefNodeAndKey[1], value);
		node.flush();
	}

	public final void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException
	{
		m_name = new StringBuffer(config.getDeclaringExtension().getNamespaceIdentifier()).append('.').append(
				config.getAttribute(NAME_ATTRIB)).toString();
		m_key = config.getAttribute(KEY_ATTRIB);
		m_description = config.getAttribute(DESC_ATTRIB);
	}

	public void unset() throws BackingStoreException
	{
		String[] prefNodeAndKey = pathAsNodeAndKey(getKey());
		Preferences node = s_eclipsePrefs.node(prefNodeAndKey[0]);
		node.remove(prefNodeAndKey[1]);
		node.flush();
	}

	protected final String[] pathAsNodeAndKey(String path)
	{
		int lastSlash = path.lastIndexOf(SLASH);
		if(lastSlash == -1)
			throw new IllegalArgumentException(Messages.BasicPreferenceHandler_No_slash_in_preference_path);
		return new String[] { path.substring(0, lastSlash), path.substring(lastSlash + 1) };
	}
}

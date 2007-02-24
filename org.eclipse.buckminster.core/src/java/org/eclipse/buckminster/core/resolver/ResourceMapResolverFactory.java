/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.resolver;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.IllegalParameterException;
import org.eclipse.buckminster.core.prefs.IPreferenceDescriptor;
import org.eclipse.buckminster.core.prefs.PreferenceDescriptor;
import org.eclipse.buckminster.core.prefs.PreferenceType;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;

/**
 * @author Thomas Hallgren
 */
public class ResourceMapResolverFactory extends AbstractExtension implements IResolverFactory
{
	private static final IEclipsePreferences s_prefsNode = new InstanceScope().getNode(Buckminster.PLUGIN_ID);
	private static final IEclipsePreferences s_defaultNode = new DefaultScope().getNode(Buckminster.PLUGIN_ID);

	public static void addListener(IPreferenceChangeListener listener)
	{
		s_prefsNode.addPreferenceChangeListener(listener);
	}

	public static void removeListener(IPreferenceChangeListener listener)
	{
		s_prefsNode.removePreferenceChangeListener(listener);
	}

	public static final String RESOURCE_MAP_URL_PARAM = "resourceMapURL";

	public static final String OVERRIDE_QUERY_URL_PARAM = "overrideQueryURL";

	public static final boolean OVERRIDE_QUERY_URL_DEFAULT = false;

	public static final String LOCAL_RESOLVE_PARAM = "localResolve";

	public static final boolean LOCAL_RESOLVE_DEFAULT = true;

	public static final String RESOLVER_THREADS_MAX_PARAM = "resolverThreadsMax";

	public static final int RESOLVER_THREADS_MAX_DEFAULT = 4;

	private IEclipsePreferences m_prefsNode;

	private String m_resourceMapURL;

	private boolean m_overrideQueryURL = OVERRIDE_QUERY_URL_DEFAULT;

	private boolean m_localResolve = LOCAL_RESOLVE_DEFAULT;

	private int m_resolverThreadsMax = RESOLVER_THREADS_MAX_DEFAULT;

	public synchronized IEclipsePreferences getPreferences()
	{
		if(m_prefsNode == null)
		{
			m_prefsNode = (IEclipsePreferences)s_prefsNode.node(getId());
			initDefaultPreferences();
		}
		return m_prefsNode;
	}

	public IResolver createResolver(RMContext context) throws CoreException
	{
		ComponentQuery query = context.getComponentQuery();
		URL url;
		if(isOverrideQueryURL())
			url = getResourceMapURL();
		else
		{
			url = query.getResourceMapURL();
			if(url == null)
				url = getResourceMapURL();
		}
		return (url == null)
				? new LocalResolver(context)
				: new ResourceMapResolver(this, context);
	}

	public IPreferenceDescriptor[] getPreferenceDescriptors()
	{
		PreferenceDescriptor[] pds = new PreferenceDescriptor[4];
		pds[0] = new PreferenceDescriptor(RESOURCE_MAP_URL_PARAM, PreferenceType.String, "Resource map URL");
		pds[1] = new PreferenceDescriptor(OVERRIDE_QUERY_URL_PARAM, PreferenceType.Boolean,
				"Override URL in Component Query");
		pds[2] = new PreferenceDescriptor(LOCAL_RESOLVE_PARAM, PreferenceType.Boolean, "Perform local resolution");
		pds[3] = new PreferenceDescriptor(RESOLVER_THREADS_MAX_PARAM, PreferenceType.Integer,
				"Maximum number of resolver threads");
		pds[3].setTextWidth(2);
		return pds;
	}

	public ResourceMap getResourceMap(URL url) throws CoreException
	{
		if(isOverrideQueryURL())
			url = getResourceMapURL();
		return ResourceMapCache.getResourceMap(url);
	}

	public int getResolverThreadsMax()
	{
		return getPreferences().getInt(RESOLVER_THREADS_MAX_PARAM, m_resolverThreadsMax);
	}

	/**
	 * Obtains the {@link #RESOURCE_MAP_URL_PARAM} setting for this factory from the preference store. If not found
	 * there, it defaults to the value set in the extension definition.
	 * 
	 * @return The URL or <code>null</code> if it has not been set.
	 */
	public URL getResourceMapURL() throws CoreException
	{
		try
		{
			return URLUtils.normalizeToURL(getPreferences().get(RESOURCE_MAP_URL_PARAM, m_resourceMapURL));
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	/**
	 * Obtains the {@link #LOCAL_RESOLVE_PARAM} setting for this factory from the preference store. If not found there,
	 * it defaults to the value set in the extension definition.
	 * 
	 * @return <code>true</code>ue if local resolutions should be performed.
	 */
	public boolean isLocalResolve()
	{
		return getPreferences().getBoolean(LOCAL_RESOLVE_PARAM, m_localResolve);
	}

	/**
	 * Obtains the {@link #OVERRIDE_QUERY_URL_PARAM} setting for this factory from the preference store. If not found
	 * there, it defaults to the value set in the extension definition.
	 * 
	 * @return the overrideQueryURL
	 */
	public boolean isOverrideQueryURL()
	{
		return getPreferences().getBoolean(OVERRIDE_QUERY_URL_PARAM, m_overrideQueryURL);
	}

	@Override
	public void setExtensionParameter(String key, String value) throws CoreException
	{
		if(RESOURCE_MAP_URL_PARAM.equalsIgnoreCase(key))
		{
			m_resourceMapURL = value;
		}
		else if(OVERRIDE_QUERY_URL_PARAM.equalsIgnoreCase(key))
		{
			m_overrideQueryURL = Boolean.parseBoolean(value);
		}
		else if(LOCAL_RESOLVE_PARAM.equalsIgnoreCase(key))
		{
			m_localResolve = Boolean.parseBoolean(value);
		}
		else if(RESOLVER_THREADS_MAX_PARAM.equalsIgnoreCase(key))
		{
			m_resolverThreadsMax = Integer.parseInt(value);
		}
		else
			throw new IllegalParameterException(ResolverFactoryMaintainer.QUERY_RESOLVERS_POINT, this.getId(), key);
	}

	public void setLocalResolve(boolean localResolve)
	{
		getPreferences().putBoolean(LOCAL_RESOLVE_PARAM, localResolve);
	}

	public void setOverrideQueryURL(boolean overrideQueryURL)
	{
		getPreferences().putBoolean(OVERRIDE_QUERY_URL_PARAM, overrideQueryURL);
	}

	public void setResolverThreadsMax(int resolverThreadsMax)
	{
		getPreferences().putInt(RESOLVER_THREADS_MAX_PARAM, resolverThreadsMax);
	}

	public void setResourceMapURL(URL resourceMapURL)
	{
		getPreferences().put(RESOURCE_MAP_URL_PARAM, resourceMapURL.toExternalForm());
	}

	public void initDefaultPreferences()
	{
		IEclipsePreferences defaultNode = (IEclipsePreferences)s_defaultNode.node(getId());
		if(defaultNode.getInt(RESOLVER_THREADS_MAX_PARAM, 0) == 0)
		{
			// Defaults not initialized. Do it now
			//
			defaultNode.putBoolean(OVERRIDE_QUERY_URL_PARAM, OVERRIDE_QUERY_URL_DEFAULT);
			defaultNode.putBoolean(LOCAL_RESOLVE_PARAM, LOCAL_RESOLVE_DEFAULT);
			defaultNode.putInt(RESOLVER_THREADS_MAX_PARAM, RESOLVER_THREADS_MAX_DEFAULT);
		}
	}
}

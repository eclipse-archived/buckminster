/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.remote.resolver;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.helpers.IllegalParameterException;
import org.eclipse.buckminster.core.prefedit.IPreferenceDescriptor;
import org.eclipse.buckminster.core.prefedit.PreferenceDescriptor;
import org.eclipse.buckminster.core.prefedit.PreferenceType;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.IResolverFactory;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.resolver.ResolverFactoryMaintainer;
import org.eclipse.buckminster.remote.IServiceProvider;
import org.eclipse.buckminster.remote.NoSuchProviderException;
import org.eclipse.buckminster.remote.ProviderUtil;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;

/**
 * Factory that enables access to a <code>RemoteResolver</code>
 * 
 * @author Filip Hrbek
 */
public abstract class AbstractRemoteResolverFactory extends AbstractExtension implements IResolverFactory
{
	private static final IEclipsePreferences s_prefsNode = new InstanceScope().getNode(Buckminster.PLUGIN_ID);

	public static final String PROVIDER_PARAM = "provider";

	public static final String PRIORITY_ATTRIBUTE = "priority";

	public static final String LOGIN_PARAM = "login";

	public static final String PASSWORD_PARAM = "password";

	public static void addListener(IPreferenceChangeListener listener)
	{
		s_prefsNode.addPreferenceChangeListener(listener);
	}

	public static void removeListener(IPreferenceChangeListener listener)
	{
		s_prefsNode.removePreferenceChangeListener(listener);
	}

	private IEclipsePreferences m_prefsNode;

	private String m_providerID;

	private int m_priority = -1;

	public IResolver createResolver(ResolutionContext context) throws CoreException
	{
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(LOGIN_PARAM, getPreferences().get(LOGIN_PARAM, null));
		properties.put(PASSWORD_PARAM, getPreferences().get(PASSWORD_PARAM, null));

		IResolutionServiceConnection resolutionServiceConnection = createResolutionServiceConnection(m_providerID,
				properties);
		return new RemoteResolver(resolutionServiceConnection, context.getComponentQuery());
	}

	public IPreferenceDescriptor[] getPreferenceDescriptors()
	{
		IServiceProvider provider;
		try
		{
			provider = ProviderUtil.findProvider(m_providerID);

			if(provider.isLoginSupported())
				return new PreferenceDescriptor[] {
						new PreferenceDescriptor(LOGIN_PARAM, PreferenceType.String, "Login"),
						new PreferenceDescriptor(PASSWORD_PARAM, PreferenceType.Password, "Password") };
		}
		catch(NoSuchProviderException e)
		{
			// no provider - login is not supported
		}

		return new PreferenceDescriptor[] {};
	}

	public synchronized IEclipsePreferences getPreferences()
	{
		if(m_prefsNode == null)
		{
			m_prefsNode = (IEclipsePreferences)s_prefsNode.node(getId());
		}
		return m_prefsNode;
	}

	public int getResolutionPriority()
	{
		return m_priority;
	}

	public void initDefaultPreferences()
	{
		// We have no defaults
	}

	@Override
	public void setExtensionParameter(String key, String value) throws CoreException
	{
		if(PROVIDER_PARAM.equalsIgnoreCase(key))
			m_providerID = value;
		else
			throw new IllegalParameterException(ResolverFactoryMaintainer.QUERY_RESOLVERS_POINT, this.getId(), key);
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException
	{
		super.setInitializationData(config, propertyName, data);
		String prio = config.getAttribute(PRIORITY_ATTRIBUTE);
		if(prio != null)
		{
			try
			{
				m_priority = Integer.parseInt(prio);
			}
			catch(NumberFormatException e)
			{
			}
		}
	}

	protected abstract IResolutionServiceConnection createResolutionServiceConnection(String providerID,
			Map<String, Object> properties) throws CoreException;
}

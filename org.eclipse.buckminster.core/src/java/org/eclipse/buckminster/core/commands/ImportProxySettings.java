/*****************************************************************************
 * Copyright (c) 2008-2012, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.commands;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.internal.net.Activator;
import org.eclipse.core.internal.net.PreferenceManager;
import org.eclipse.core.net.proxy.IProxyData;
import org.eclipse.core.net.proxy.IProxyService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

@SuppressWarnings("restriction")
public class ImportProxySettings extends WorkspaceCommand {

	protected abstract static class AbstractHttpProxySettingsImporter extends AbstractProxySettingsImporter {

		private StringBuilder propertiesPrefix;

		private int prefixLength;

		protected String getPrefixedPropetyName(String propertyName) {
			propertiesPrefix.setLength(prefixLength);

			return propertiesPrefix.append(propertyName).toString();
		}

		protected void importNonProxiedHostsSetting(String nonProxiedHostsPropertyName, Set<String> nonProxiedHosts) {
			String nonProxiedHostsString = savedProxyProperties.getProperty(nonProxiedHostsPropertyName);
			if (nonProxiedHostsString == null)
				return;

			for (String nonProxiedHost : nonProxiedHostsString.split("\\|")) { //$NON-NLS-1$
				if ((nonProxiedHost = nonProxiedHost.trim()).length() > 0)
					nonProxiedHosts.add(nonProxiedHost);
			}
		}

		@Override
		public boolean importProxySettings(IProxyData proxyDatum, Set<String> nonProxiedHosts) {
			importNonProxiedHostsSetting(getPrefixedPropetyName("nonProxyHosts"), nonProxiedHosts); //$NON-NLS-1$

			String proxyHost = savedProxyProperties.getProperty(getPrefixedPropetyName("proxyHost")); //$NON-NLS-1$
			if (proxyHost == null || (proxyHost = proxyHost.trim()).length() == 0)
				return false;

			proxyDatum.setHost(proxyHost);

			importProxyPortSetting(getPrefixedPropetyName("proxyPort"), proxyDatum); //$NON-NLS-1$

			String proxyUser = savedProxyProperties.getProperty(getPrefixedPropetyName("proxyUser")); //$NON-NLS-1$
			if (proxyUser == null)
				proxyUser = savedProxyProperties.getProperty(getPrefixedPropetyName("proxyUserName")); //$NON-NLS-1$

			if (proxyUser != null) {
				proxyDatum.setUserid(proxyUser);

				String proxyPassword = savedProxyProperties.getProperty(getPrefixedPropetyName("proxyPassword")); //$NON-NLS-1$
				if (proxyPassword != null)
					proxyDatum.setPassword(proxyPassword);
			}

			return true;
		}

		@Override
		protected void init() {
			propertiesPrefix = new StringBuilder(getProxyType().toLowerCase());
			propertiesPrefix.append('.');
			prefixLength = propertiesPrefix.length();
		}

		@Override
		protected void saveProxySettingsSystemProperties() {
			saveProxySystemProperty(getPrefixedPropetyName("proxySet")); //$NON-NLS-1$
			saveProxySystemProperty(getPrefixedPropetyName("proxyHost")); //$NON-NLS-1$
			saveProxySystemProperty(getPrefixedPropetyName("proxyPort")); //$NON-NLS-1$
			saveProxySystemProperty(getPrefixedPropetyName("nonProxyHosts")); //$NON-NLS-1$
			saveProxySystemProperty(getPrefixedPropetyName("proxyUser")); //$NON-NLS-1$
			saveProxySystemProperty(getPrefixedPropetyName("proxyUserName")); //$NON-NLS-1$
			saveProxySystemProperty(getPrefixedPropetyName("proxyPassword")); //$NON-NLS-1$
		}

		@Override
		protected void setProxySettingsSystemPropertiesImpl(IProxyData proxyDatum) {
			Properties systemProperties = System.getProperties();
			String proxyHost = proxyDatum.getHost();

			if (proxyHost != null) {
				systemProperties.setProperty(getPrefixedPropetyName("proxySet"), Boolean.TRUE.toString()); //$NON-NLS-1$
				systemProperties.setProperty(getPrefixedPropetyName("proxyHost"), proxyHost); //$NON-NLS-1$
			} else {
				systemProperties.remove(getPrefixedPropetyName("proxySet")); //$NON-NLS-1$
				systemProperties.remove(getPrefixedPropetyName("proxyHost")); //$NON-NLS-1$
			}

			setProxyPortSystemProperty(getPrefixedPropetyName("proxyPort"), proxyDatum); //$NON-NLS-1$
		}

	}

	protected abstract static class AbstractProxySettingsImporter {

		protected Properties savedProxyProperties = new Properties();

		public AbstractProxySettingsImporter() {
			init();
			saveProxySettingsSystemProperties();
		}

		public abstract String getProxyType();

		public boolean hasSavedProxyProperties() {
			return !savedProxyProperties.isEmpty();
		}

		protected void importProxyPortSetting(String proxyPortPropertyName, IProxyData proxyDatum) {
			String proxyPortString = savedProxyProperties.getProperty(proxyPortPropertyName);

			if (proxyPortString != null) {
				try {
					proxyDatum.setPort(Integer.parseInt(proxyPortString));
					return;
				} catch (NumberFormatException e) {
					// fall through
				}
			}

			proxyDatum.setPort(-1);
		}

		/**
		 * Imports the proxy settings from the properties saved by
		 * {@link AbstractProxySettingsImporter#saveProxySettingsSystemProperties()
		 * saveProxySettingsSystemProperties()} into the supplied
		 * {@code proxyDatum} and potentially adds any non proxied hosts defined
		 * in the properties to the {@code nonProxiedHostsList}.
		 */
		public abstract boolean importProxySettings(IProxyData proxyDatum, Set<String> nonProxiedHosts);

		protected void init() {
		}

		public void restoreProxySettingsSystemProperties() {
			System.getProperties().putAll(savedProxyProperties);
			savedProxyProperties.clear();
		}

		/**
		 * Saves all proxy settings properties recognized by this
		 * {@link AbstractProxySettingsImporter}. I.e. removes them from the
		 * system properties and saves them in a private {@link Properties}
		 * object.
		 */
		protected abstract void saveProxySettingsSystemProperties();

		protected void saveProxySystemProperty(String proxyPropertyName) {
			Properties systemProperties = System.getProperties();
			String proxyPropertyValue = systemProperties.getProperty(proxyPropertyName);

			if (proxyPropertyValue == null)
				return;

			systemProperties.remove(proxyPropertyName);
			savedProxyProperties.setProperty(proxyPropertyName, proxyPropertyValue);
		}

		protected void setProxyPortSystemProperty(String proxyPortPropertyName, IProxyData proxyDatum) {
			int proxyPort = proxyDatum.getPort();

			if (proxyPort != -1)
				System.getProperties().setProperty(proxyPortPropertyName, Integer.toString(proxyPort));
			else
				System.getProperties().remove(proxyPortPropertyName);
		}

		public void setProxySettingsSystemProperties(IProxyData proxyDatum) {
			setProxySettingsSystemPropertiesImpl(proxyDatum);
			savedProxyProperties.clear();
		}

		/**
		 * Sets appropriate system properties to reflect the proxy settings in
		 * the Platform preferences. But it only sets as few properties as
		 * absolutely necessary to prevent warnings from the Platform proxy code
		 * when comparing the values in the preferences with the values in the
		 * system properties. I.e. the properties set by this function do not
		 * necessarily reflect the entire proxy settings information in the
		 * preferences.
		 */
		protected abstract void setProxySettingsSystemPropertiesImpl(IProxyData proxyDatum);

	}

	/**
	 * Creating this class suspends/disables proxies by directly manipulating
	 * the Pltform's proxy preferences.
	 * <p>
	 * Note that this class partially duplicates functionality provided by
	 * {@link org.eclipse.core.internal.net.PreferenceManager}. The reason for
	 * not using that class is to avoid starting/initialization of the
	 * {@code org.eclipse.core.net} bundle.
	 */
	protected static class DirectProxiesSuspender {

		public static final String PREF_ENABLED = "proxiesEnabled"; //$NON-NLS-1$

		protected IEclipsePreferences proxyPreferencesScope = ConfigurationScope.INSTANCE.getNode(Activator.ID);

		protected Boolean proxiesEnabled;

		public DirectProxiesSuspender() {
			proxiesEnabled = getBoolean(PREF_ENABLED);
			putBoolean(PREF_ENABLED, Boolean.FALSE);
		}

		public void ensureRestoreEnablesProxies() {
			proxiesEnabled = Boolean.TRUE;
		}

		protected Boolean getBoolean(String key) {
			String valueString = proxyPreferencesScope.node(PreferenceManager.ROOT).get(key, null);

			return valueString == null ? null : Boolean.valueOf(valueString);
		}

		protected void putBoolean(String key, Boolean value) {
			if (value != null)
				proxyPreferencesScope.node(PreferenceManager.ROOT).put(key, value.toString());
			else
				proxyPreferencesScope.node(PreferenceManager.ROOT).remove(key);
		}

		/**
		 * Restores the proxiesEnabled preference value that had been effective
		 * at the time this {@code DirectProxiesSuspender} was created. (Or set
		 * it to true if the
		 * {@link DirectProxiesSuspender#ensureRestoreEnablesProxies()
		 * ensureRestoreEnablesProxies()} was called).
		 */
		public void restoreProxies() {
			if (proxiesEnabled != Boolean.FALSE) {
				putBoolean(PREF_ENABLED, proxiesEnabled);
				proxiesEnabled = Boolean.FALSE;
			}
		}

	}

	protected static class HttpProxySettingsImporter extends AbstractHttpProxySettingsImporter {

		@Override
		public String getProxyType() {
			return IProxyData.HTTP_PROXY_TYPE;
		}

	}

	protected static class HttpsProxySettingsImporter extends AbstractHttpProxySettingsImporter {

		@Override
		public String getProxyType() {
			return IProxyData.HTTPS_PROXY_TYPE;
		}

	}

	protected static class SocksProxySettingsImporter extends AbstractProxySettingsImporter {

		@Override
		public String getProxyType() {
			return IProxyData.SOCKS_PROXY_TYPE;
		}

		@Override
		public boolean importProxySettings(IProxyData proxyDatum, Set<String> nonProxiedHosts) {
			String proxyHost = savedProxyProperties.getProperty("socksProxyHost"); //$NON-NLS-1$
			if (proxyHost == null)
				return false;

			proxyDatum.setHost(proxyHost);

			importProxyPortSetting("socksProxyPort", proxyDatum); //$NON-NLS-1$

			return true;
		}

		@Override
		protected void saveProxySettingsSystemProperties() {
			saveProxySystemProperty("socksProxyHost"); //$NON-NLS-1$
			saveProxySystemProperty("socksProxyPort"); //$NON-NLS-1$
		}

		@Override
		protected void setProxySettingsSystemPropertiesImpl(IProxyData proxyDatum) {
			String proxyHost = proxyDatum.getHost();

			if (proxyHost != null)
				System.getProperties().setProperty("socksProxyHost", proxyHost); //$NON-NLS-1$
			else
				System.getProperties().remove("socksProxyHost"); //$NON-NLS-1$

			setProxyPortSystemProperty("socksProxyPort", proxyDatum); //$NON-NLS-1$
		}

	}

	private Map<String, AbstractProxySettingsImporter> buildProxySettingsImportersMap(AbstractProxySettingsImporter... proxySettingsImporters) {
		HashMap<String, AbstractProxySettingsImporter> proxySettingsImportersMap = new HashMap<String, AbstractProxySettingsImporter>();
		boolean hasSavedProxyProperties = false;

		for (AbstractProxySettingsImporter proxySettingsImporter : proxySettingsImporters) {
			proxySettingsImportersMap.put(proxySettingsImporter.getProxyType(), proxySettingsImporter);
			hasSavedProxyProperties |= proxySettingsImporter.hasSavedProxyProperties();
		}

		return hasSavedProxyProperties ? proxySettingsImportersMap : null;
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		Map<String, AbstractProxySettingsImporter> proxySettingsImportersMap = buildProxySettingsImportersMap(new HttpProxySettingsImporter(),
				new HttpsProxySettingsImporter(), new SocksProxySettingsImporter());
		if (proxySettingsImportersMap == null) {
			Buckminster.getLogger().warning(Messages.No_proxy_settings_to_import);
			return 0;
		}

		try {
			// suspend the proxies
			DirectProxiesSuspender proxiesSuspender = new DirectProxiesSuspender();

			try {
				BundleContext bundleContext = CorePlugin.getDefault().getBundle().getBundleContext();
				ServiceReference<IProxyService> proxyServiceReference;
				IProxyService proxyService;
				Throwable t;

				try {
					proxyServiceReference = bundleContext.getServiceReference(IProxyService.class);
					proxyService = bundleContext.getService(proxyServiceReference);
					t = null;
				} catch (Exception e) {
					// If we don't even have the classes for this (i.e. the
					// org.eclipse.core.net plugin not available)
					// then we simply log and ignore
					proxyServiceReference = null;
					proxyService = null;
					t = e;
				} catch (NoClassDefFoundError e) {
					proxyServiceReference = null;
					proxyService = null;
					t = e;
				}

				if (t != null) {
					Buckminster.getLogger().warning(t, Messages.Platform_proxy_API_not_available);
					return 0;
				}

				// Only do this if platform service exists
				if (proxyService != null) {
					try {
						IProxyData[] proxyData = proxyService.getProxyData();
						Set<String> nonProxiedHosts = new LinkedHashSet<String>();
						boolean settingsImported = false;

						for (IProxyData proxyDatum : proxyData) {
							AbstractProxySettingsImporter proxySettingsImporter = proxySettingsImportersMap.get(proxyDatum.getType());
							if (proxySettingsImporter != null)
								settingsImported |= proxySettingsImporter.importProxySettings(proxyDatum, nonProxiedHosts);
						}

						if (!nonProxiedHosts.isEmpty())
							proxyService.setNonProxiedHosts(nonProxiedHosts.toArray(new String[nonProxiedHosts.size()]));

						if (settingsImported) {
							// disable the native proxy provider
							proxyService.setSystemProxiesEnabled(false);
							// set the proxy data
							proxyService.setProxyData(proxyData);

							// update the system properties to prevent warnings
							for (IProxyData proxyDatum : proxyData) {
								AbstractProxySettingsImporter proxySettingsImporter = proxySettingsImportersMap.get(proxyDatum.getType());
								if (proxySettingsImporter != null)
									proxySettingsImporter.setProxySettingsSystemProperties(proxyDatum);
							}

							// and finally ensure that the suspender will enable
							// the proxies upon restore
							proxiesSuspender.ensureRestoreEnablesProxies();
						}
					} finally {
						bundleContext.ungetService(proxyServiceReference);
					}
				} else
					Buckminster.getLogger().warning(Messages.Platform_proxy_service_not_registered);
			} finally {
				// restore the value of the proxiesEnabled preference
				proxiesSuspender.restoreProxies();
			}
		} finally {
			// restore any system properties that we didn't override
			for (AbstractProxySettingsImporter proxySettingsImporter : proxySettingsImportersMap.values())
				proxySettingsImporter.restoreProxySettingsSystemProperties();
		}

		return 0;
	}

}

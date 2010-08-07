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

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.helpers.IllegalParameterException;
import org.eclipse.buckminster.core.prefedit.IPreferenceDescriptor;
import org.eclipse.buckminster.core.prefedit.PreferenceDescriptor;
import org.eclipse.buckminster.core.prefedit.PreferenceType;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class ResourceMapResolverFactory extends AbstractExtension implements IResourceMapResolverFactory {
	private static final IEclipsePreferences preferencesNode = new InstanceScope().getNode(Buckminster.PLUGIN_ID);

	private static final IEclipsePreferences defaultNode = new DefaultScope().getNode(Buckminster.PLUGIN_ID);

	public static final String RESOURCE_MAP_URL_PARAM = "resourceMapURL"; //$NON-NLS-1$

	public static final String OVERRIDE_QUERY_URL_PARAM = "overrideQueryURL"; //$NON-NLS-1$

	public static final boolean OVERRIDE_QUERY_URL_DEFAULT = false;

	public static final String LOCAL_RESOLVE_PARAM = "localResolve"; //$NON-NLS-1$

	public static final boolean LOCAL_RESOLVE_DEFAULT = true;

	public static final String RESOLVER_THREADS_MAX_PARAM = "resolverThreadsMax"; //$NON-NLS-1$

	public static final int RESOLVER_THREADS_MAX_DEFAULT = 4;

	public static void addListener(IPreferenceChangeListener listener) {
		preferencesNode.addPreferenceChangeListener(listener);
	}

	public static void removeListener(IPreferenceChangeListener listener) {
		preferencesNode.removePreferenceChangeListener(listener);
	}

	private IEclipsePreferences prefsNode;

	private String resourceMapURL;

	private boolean overrideQueryURL = OVERRIDE_QUERY_URL_DEFAULT;

	private boolean localResolve = LOCAL_RESOLVE_DEFAULT;

	private int resolverThreadsMax = RESOLVER_THREADS_MAX_DEFAULT;

	@Override
	public IResolver createResolver(ResolutionContext context) throws CoreException {
		ComponentQuery query = context.getComponentQuery();
		URL url;
		if (isOverrideQueryURL())
			url = getResourceMapURL();
		else {
			url = query.getResolvedResourceMapURL();
			if (url == null)
				url = getResourceMapURL();
		}
		return (url == null) ? new LocalResolver(context) : new ResourceMapResolver(this, context, false);
	}

	@Override
	public IPreferenceDescriptor[] getPreferenceDescriptors() {
		PreferenceDescriptor[] pds = new PreferenceDescriptor[4];
		pds[0] = new PreferenceDescriptor(RESOURCE_MAP_URL_PARAM, PreferenceType.String, Messages.Resource_map_URL);
		pds[1] = new PreferenceDescriptor(OVERRIDE_QUERY_URL_PARAM, PreferenceType.Boolean, Messages.Override_URL_in_Component_Query);
		pds[2] = new PreferenceDescriptor(LOCAL_RESOLVE_PARAM, PreferenceType.Boolean, Messages.Perform_local_resolution);
		pds[3] = new PreferenceDescriptor(RESOLVER_THREADS_MAX_PARAM, PreferenceType.Integer, Messages.Maximum_number_of_resolver_threads);
		pds[3].setTextWidth(2);
		pds[3].setIntegerRange(1, 12);
		return pds;
	}

	public synchronized IEclipsePreferences getPreferences() {
		if (prefsNode == null) {
			prefsNode = (IEclipsePreferences) preferencesNode.node(getId());
			initDefaultPreferences();
		}
		return prefsNode;
	}

	@Override
	public int getResolutionPriority() {
		return 0;
	}

	@Override
	public int getResolverThreadsMax() {
		return getPreferences().getInt(RESOLVER_THREADS_MAX_PARAM, resolverThreadsMax);
	}

	@Override
	public ResourceMap getResourceMap(ResolutionContext context, URL url, IConnectContext cctx) throws CoreException {
		String stream = url.toExternalForm();
		Resource resource;
		try {
			ResourceSet rs = new ResourceSetImpl();
			// TODO: Figure out how to use the IConnectionContext with EMF load
			resource = rs.getResource(URI.createURI(url.toString()), true);
		} catch (WrappedException e) {
			throw BuckminsterException.wrap(e.getCause());
		}
		EList<EObject> content = resource.getContents();
		if (content.size() != 1)
			throw BuckminsterException.fromMessage(NLS.bind("Unable to parse rmap file from {0}", stream)); //$NON-NLS-1$

		return (ResourceMap) content.get(0);
	}

	/**
	 * Obtains the {@link #RESOURCE_MAP_URL_PARAM} setting for this factory from
	 * the preference store. If not found there, it defaults to the value set in
	 * the extension definition.
	 * 
	 * @return The URL or <code>null</code> if it has not been set.
	 */
	@Override
	public URL getResourceMapURL() throws CoreException {
		try {
			return URLUtils.normalizeToURL(getPreferences().get(RESOURCE_MAP_URL_PARAM, resourceMapURL));
		} catch (MalformedURLException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public void initDefaultPreferences() {
		IEclipsePreferences dfltNode = (IEclipsePreferences) defaultNode.node(getId());
		if (dfltNode.getInt(RESOLVER_THREADS_MAX_PARAM, 0) == 0) {
			// Defaults not initialized. Do it now
			//
			dfltNode.putBoolean(OVERRIDE_QUERY_URL_PARAM, OVERRIDE_QUERY_URL_DEFAULT);
			dfltNode.putBoolean(LOCAL_RESOLVE_PARAM, LOCAL_RESOLVE_DEFAULT);
			dfltNode.putInt(RESOLVER_THREADS_MAX_PARAM, RESOLVER_THREADS_MAX_DEFAULT);
		}
	}

	/**
	 * Obtains the {@link #LOCAL_RESOLVE_PARAM} setting for this factory from
	 * the preference store. If not found there, it defaults to the value set in
	 * the extension definition.
	 * 
	 * @return <code>true</code>ue if local resolutions should be performed.
	 */
	@Override
	public boolean isLocalResolve() {
		return getPreferences().getBoolean(LOCAL_RESOLVE_PARAM, localResolve);
	}

	/**
	 * Obtains the {@link #OVERRIDE_QUERY_URL_PARAM} setting for this factory
	 * from the preference store. If not found there, it defaults to the value
	 * set in the extension definition.
	 * 
	 * @return the overrideQueryURL
	 */
	@Override
	public boolean isOverrideQueryURL() {
		return getPreferences().getBoolean(OVERRIDE_QUERY_URL_PARAM, overrideQueryURL);
	}

	@Override
	public void setExtensionParameter(String key, String value) throws CoreException {
		if (RESOURCE_MAP_URL_PARAM.equalsIgnoreCase(key)) {
			resourceMapURL = value;
		} else if (OVERRIDE_QUERY_URL_PARAM.equalsIgnoreCase(key)) {
			overrideQueryURL = Boolean.parseBoolean(value);
		} else if (LOCAL_RESOLVE_PARAM.equalsIgnoreCase(key)) {
			localResolve = Boolean.parseBoolean(value);
		} else if (RESOLVER_THREADS_MAX_PARAM.equalsIgnoreCase(key)) {
			resolverThreadsMax = Integer.parseInt(value);
		} else
			throw new IllegalParameterException(ResolverFactoryMaintainer.QUERY_RESOLVERS_POINT, this.getId(), key);
	}

	public void setLocalResolve(boolean localResolve) {
		getPreferences().putBoolean(LOCAL_RESOLVE_PARAM, localResolve);
	}

	public void setOverrideQueryURL(boolean overrideQueryURL) {
		getPreferences().putBoolean(OVERRIDE_QUERY_URL_PARAM, overrideQueryURL);
	}

	public void setResolverThreadsMax(int resolverThreadsMax) {
		getPreferences().putInt(RESOLVER_THREADS_MAX_PARAM, resolverThreadsMax);
	}

	public void setResourceMapURL(URL resourceMapURL) {
		getPreferences().put(RESOURCE_MAP_URL_PARAM, resourceMapURL.toExternalForm());
	}
}

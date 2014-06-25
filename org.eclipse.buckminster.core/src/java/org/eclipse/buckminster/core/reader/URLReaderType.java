/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 * 
 * Contributors:
 *  Lorenzo Bettini - https://bugs.eclipse.org/bugs/show_bug.cgi?id=428301
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.MapUtils;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 */
public class URLReaderType extends AbstractReaderType {
	static IComponentReader getDirectReader(URL url, String readerType, IProgressMonitor monitor) throws CoreException {
		String urlString = url.toString();
		ComponentRequest rq = new ComponentRequest(urlString, null, null);
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(rq);
		queryBld.setPlatformAgnostic(true);
		ResolutionContext context = new ResolutionContext(queryBld.createComponentQuery());
		NodeQuery nq = new NodeQuery(context, rq, null);

		IComponentType ctype = CorePlugin.getDefault().getComponentType(IComponentType.UNKNOWN);
		Provider provider = Provider.immutableProvider(readerType, ctype.getId(), urlString);
		ProviderMatch pm = new ProviderMatch(provider, ctype, VersionMatch.DEFAULT, ProviderScore.GOOD, nq);
		return pm.getReader(monitor);
	}

	public static IComponentReader getReader(URL externalFile, IProgressMonitor monitor) throws CoreException {
		return getDirectReader(externalFile, IReaderType.URL, monitor);
	}

	@Override
	public String convertFetchFactoryLocator(Map<String, Object> fetchFactoryLocator, String componentName) throws CoreException {
		return MapUtils.getString(fetchFactoryLocator, "src"); //$NON-NLS-1$
	}

	@Override
	public URL convertToURL(String repositoryLocator, VersionMatch versionSelector) throws CoreException {
		try {
			return URLUtils.normalizeToURL(repositoryLocator);
		} catch (MalformedURLException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException {
		try {
			return new URI(resolution.getRepository());
		} catch (URISyntaxException e) {
			return null;
		}
	}

	@Override
	public Map<String, String> getFetchFactoryProviderProps(Map<String, Object> properties, Provider delegee) {
		Map<String, String> props = new HashMap<String, String>();
		props.put(KeyConstants.IS_SOURCE, Boolean.FALSE.toString());
		props.put(KeyConstants.IS_MUTABLE, Boolean.FALSE.toString());
		return props;
	}

	@Override
	public IPath getLeafArtifact(Resolution resolution, MaterializationContext context) throws CoreException {
		String name = resolution.getRemoteName();
		if (name != null)
			return Path.fromPortableString(name);

		URI uri = getURI(resolution.getRepository());
		Map<String, String> params = URLUtils.queryAsParameters(uri.getQuery());
		String pathStr = params.get("file"); //$NON-NLS-1$
		if (pathStr == null)
			pathStr = uri.getPath();

		IPath path = Path.fromPortableString(pathStr);
		int segCount = path.segmentCount();
		if (segCount > 1)
			path = path.removeFirstSegments(segCount - 1);
		return path;
	}

	public IReaderType getLocalReaderType() {
		return this;
	}

	@Override
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException {
		MonitorUtils.complete(monitor);
		return new URLFileReader(this, providerMatch, getURI(providerMatch));
	}

	@Override
	public IComponentReader getReader(Resolution resolution, RMContext context, IProgressMonitor monitor) throws CoreException {
		MonitorUtils.complete(monitor);
		return new URLFileReader(this, resolution.getProviderMatch(context), getURI(resolution.getRepository()));
	}

	@Override
	public String getRemotePath(String repositoryLocation) throws CoreException {
		return getURI(repositoryLocation).getPath();
	}

	public URI getURI(ProviderMatch providerMatch) throws CoreException {
		return getURI(providerMatch.getRepositoryURI());
	}

	public URI getURI(String repository) throws CoreException {
		return URLUtils.normalizeToURI(repository, false);
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery query, IProgressMonitor monitor) throws CoreException {
		return new DefaultVersionFinder(provider, ctype, query);
	}

	@Override
	public boolean isFileReader() {
		return true;
	}
}

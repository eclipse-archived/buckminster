/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.rmap.psf.extension;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.query.builder.AdvisorNodeBuilder;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.AbstractReaderType;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IFileReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.reader.ReferenceInfo;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.rmap.model.SearchPath;
import org.eclipse.buckminster.core.rmap.model.URIMatcher;
import org.eclipse.buckminster.core.rmap.model.VersionConverterDesc;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.buckminster.team.psf.PSF;
import org.eclipse.buckminster.team.psf.Project;
import org.eclipse.buckminster.team.psf.RepositoryProvider;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class PSFProvider extends Provider {
	public static final String BM_PFS_PROVIDER_NS = XMLConstants.BM_PREFIX + "PSFProvider-1.0"; //$NON-NLS-1$

	public static final String BM_PFS_PROVIDER_PREFIX = "psf"; //$NON-NLS-1$

	public static final String ATTR_PSF_FILE = "psfFile"; //$NON-NLS-1$

	private final String psfFile;

	public PSFProvider(SearchPath searchPath, String remoteReaderType, String[] componentTypeIDs, VersionConverterDesc versionConverterDesc,
			Format uri, Format digest, String digestAlgorithm, Filter resolutionFilter, Map<String, String> properties, URIMatcher uriMatcher,
			Documentation documentation, String pfsFile) {
		super(searchPath, remoteReaderType, componentTypeIDs, versionConverterDesc, uri, digest, digestAlgorithm, resolutionFilter, properties,
				uriMatcher, documentation);
		this.psfFile = pfsFile;
	}

	@Override
	public ProviderMatch findMatch(NodeQuery query, MultiStatus problemCollector, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask("", 100); //$NON-NLS-1$
		try {
			URI providerURI = URI.createURI(getURI(query.getProperties()));
			ProviderScore score = query.getProviderScore(isMutable(), hasSource());
			if (score == ProviderScore.REJECTED) {
				String msg = NLS.bind("Provider {0} for {1} score_below_treshold", getReaderTypeId(), providerURI);
				problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, msg, null));
				return null;
			}

			PSF psf = getPSF(query, problemCollector, MonitorUtils.subMonitor(monitor, 10));
			if (psf == null)
				return null;

			ComponentRequest cr = query.getComponentRequest();
			ProviderMatch found = null;
			for (RepositoryProvider provider : psf.getProviders()) {
				IReaderType rt = AbstractReaderType.getTypeForRepositoryProvider(provider.getId());
				if (rt == null) {
					query.logDecision(ResolverDecisionType.READER_TYPE_NOT_FOUND, provider.getId());
					continue;
				}

				for (Project project : provider.getProjects()) {
					String ref = project.getReference();
					ReferenceInfo refInfo = rt.extractReferenceInfo(ref);
					String repoLocation = refInfo.getRepositoryLocation();
					if (!(cr.getProjectName().equals(refInfo.getProjectName()) || cr.getName().equals(refInfo.getProjectName()))) {
						// Check if the leaf part of the repository location
						// contains the name.
						//
						int idx = repoLocation.lastIndexOf('/');
						if (idx < 0)
							continue;

						++idx;
						boolean startsWithProjName = repoLocation.indexOf(cr.getProjectName(), idx) == idx;
						boolean startsWithName = repoLocation.indexOf(cr.getName(), idx) == idx;
						if (!(startsWithProjName || startsWithName))
							continue;

						if (startsWithProjName)
							idx += cr.getProjectName().length();
						else
							idx += cr.getName().length();

						char endChar = idx < repoLocation.length() ? repoLocation.charAt(idx) : 0;
						if ((endChar >= '0' && endChar <= '9') || (endChar >= 'A' && endChar <= 'Z') || (endChar >= 'a' && endChar <= 'z')
								|| endChar == '.')
							continue;
					}

					if (!query.isMatch(refInfo.getSelector()))
						continue;

					Format uri = new Format(repoLocation);
					Provider delegated = new Provider(getSearchPath(), rt.getId(), getComponentTypeIDs(), getVersionConverterDesc(), uri, null, null,
							getResolutionFilter(), getProviderProperties(), null, null);

					NodeQuery tmpQuery = query;
					VersionSelector vs = refInfo.getSelector();
					if (vs != null && query.getBranchTagPath().length == 0) {
						ComponentQuery cquery = query.getComponentQuery();
						ComponentQueryBuilder cqTmp = new ComponentQueryBuilder();
						cqTmp.initFrom(cquery);
						AdvisorNodeBuilder foundNode = null;
						for (AdvisorNodeBuilder node : cqTmp.getAdvisoryNodes()) {
							Pattern pattern = node.getNamePattern();
							if (!(pattern == null || pattern.matcher(cr.getName()).find()))
								continue;

							String matchingType = node.getComponentTypeID();
							if (!(matchingType == null || matchingType.equals(cr.getComponentTypeID())))
								continue;

							Filter filter = node.getFilter();
							if (filter == null || filter.matches(query.getContext())) {
								foundNode = node;
								break;
							}
						}
						if (foundNode == null) {
							foundNode = cqTmp.addAdvisorNode();
							foundNode.setNamePattern(Pattern.compile(Pattern.quote(cr.getName())));
						}
						foundNode.setBranchTagPath(new VersionSelector[] { vs });
						ResolutionContext tmpContext = new ResolutionContext(cqTmp.createComponentQuery(), query.getResolutionContext());
						tmpQuery = new NodeQuery(tmpContext, query.getQualifiedDependency());
					}

					ProviderMatch candidate = delegated.findMatch(tmpQuery, problemCollector, monitor);
					if (candidate == null)
						continue;

					if (found == null)
						found = candidate;
					else if (found.compareTo(candidate) < 0) {
						query.logDecision(ResolverDecisionType.MATCH_REJECTED, found.getVersionMatch(), NLS.bind("{0} is a better match", candidate
								.getVersionMatch()));
						found = candidate;
					}
				}
			}
			return found;
		} finally {
			monitor.done();
		}
	}

	public PSF getPSF(NodeQuery query, MultiStatus problemCollector, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 700);
		Map<UUID, Object> userCache = query.getContext().getUserCache();
		synchronized (userCache) {
			PSF psf = getCachedPSF(userCache);
			if (psf != null)
				return psf;

			try {
				VersionSelector[] btPath = query.getBranchTagPath();
				if (btPath.length == 0)
					psf = getPSF(null, query, MonitorUtils.subMonitor(monitor, 100));
				else {
					CoreException lastException = null;
					for (VersionSelector bt : btPath) {
						try {
							psf = getPSF(bt, query, MonitorUtils.subMonitor(monitor, 100));
							lastException = null;
						} catch (CoreException e) {
							lastException = e;
						}
					}
					if (lastException != null)
						throw lastException;
				}
				cachePSF(userCache, psf);
				return psf;
			} catch (CoreException e) {
				problemCollector.add(e.getStatus());
				Buckminster.getLogger().debug(e.getMessage());
				return null;
			} finally {
				monitor.done();
			}
		}
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		super.addAttributes(attrs);
		attrs.addAttribute(javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "type", "xsi:type", "CDATA", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				BM_PFS_PROVIDER_PREFIX + ":PDEMapProvider"); //$NON-NLS-1$
		if (psfFile != null)
			Utils.addAttribute(attrs, ATTR_PSF_FILE, psfFile);
	}

	private void cachePSF(Map<UUID, Object> userCache, PSF psf) {
		userCache.put(getId(), psf);
	}

	private PSF getCachedPSF(Map<UUID, Object> userCache) {
		return (PSF) userCache.get(getId());
	}

	private PSF getPSF(VersionSelector vs, NodeQuery query, IProgressMonitor monitor) throws CoreException {
		ProviderMatch match = new ProviderMatch(this, CorePlugin.getDefault().getComponentType(IComponentType.UNKNOWN), new VersionMatch(null, vs,
				-1, new Date(), null), ProviderScore.GOOD, query);

		IComponentReader reader = match.getReader(MonitorUtils.subMonitor(monitor, 10));
		IStreamConsumer<PSF> psfReader = new IStreamConsumer<PSF>() {
			@Override
			public PSF consumeStream(IComponentReader rdr, String streamName, InputStream stream, IProgressMonitor consumerMon) throws CoreException,
					IOException {
				File tempFile = File.createTempFile("bm-", ".psf"); //$NON-NLS-1$ //$NON-NLS-2$
				try {
					OutputStream out = null;
					try {
						out = new FileOutputStream(tempFile);
						IOUtils.copy(stream, out, consumerMon);
					} finally {
						IOUtils.close(out);
					}
					ResourceSet rs = new ResourceSetImpl();
					Resource resource = rs.getResource(URI.createFileURI(tempFile.getAbsolutePath()), true);
					EList<EObject> content = resource.getContents();
					if (content.size() != 1)
						throw BuckminsterException.fromMessage(NLS.bind("Unable to parse psf file from {0}", streamName));

					return (PSF) content.get(0);
				} finally {
					tempFile.delete();
				}
			}
		};

		try {
			if (reader instanceof ICatalogReader) {
				if (psfFile == null)
					throw BuckminsterException.fromMessage(NLS.bind("The psfFile attribute is mandatory when using reader of type {0}",
							getReaderTypeId()));

				return ((ICatalogReader) reader).readFile(psfFile, psfReader, MonitorUtils.subMonitor(monitor, 100));
			}

			if (psfFile != null)
				throw BuckminsterException.fromMessage(NLS.bind("The psfFile attribute cannot be used in conjunction with reader of type {0}",
						getReaderTypeId()));
			return ((IFileReader) reader).readFile(psfReader, MonitorUtils.subMonitor(monitor, 100));
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}
	}
}

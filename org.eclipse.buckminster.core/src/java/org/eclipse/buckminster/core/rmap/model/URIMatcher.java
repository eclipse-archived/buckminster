/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.rmap.model;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.RxAssembly;
import org.eclipse.buckminster.core.common.model.RxPart;
import org.eclipse.buckminster.core.common.model.TaggedRxPattern;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.URLCatalogReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Filter;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 * 
 */
public class URIMatcher extends RxAssembly
{
	@SuppressWarnings("hiding")
	public static final String TAG = "matcher";

	public static final String ATTR_BASE = "base";

	public static final String ATTR_VERSION_TYPE = "versionType";

	public static final String COMPONENT_NAME_PARAM = TaggedRxPattern.TAGGED_PREFIX + "name";

	public static final String COMPONENT_VERSION_PARAM = TaggedRxPattern.TAGGED_PREFIX + "version";

	public static final String BRANCH_PARAM = TaggedRxPattern.TAGGED_PREFIX + "branch";

	public static final String TAG_PARAM = TaggedRxPattern.TAGGED_PREFIX + "tag";

	public static final String SPACE_PARAM = TaggedRxPattern.TAGGED_PREFIX + "space";

	public static final String REVISION_PARAM = TaggedRxPattern.TAGGED_PREFIX + "revision";

	public static final String TIMESTAMP_PARAM = TaggedRxPattern.TAGGED_PREFIX + "timestamp";

	public static final String OS_PARAM = TaggedRxPattern.TAGGED_PREFIX + "os";

	public static final String WS_PARAM = TaggedRxPattern.TAGGED_PREFIX + "ws";

	public static final String ARCH_PARAM = TaggedRxPattern.TAGGED_PREFIX + "arch";

	public static final String NL_PARAM = TaggedRxPattern.TAGGED_PREFIX + "nl";

	public static final String ARTIFACT_INFO_PREFIX = "URIMetaData:";

	private final String m_base;

	private final IVersionType m_versionType;

	public URIMatcher(List<RxPart> parts, String base, IVersionType versionType) throws CoreException,
			PatternSyntaxException
	{
		super(parts);
		m_base = base;
		m_versionType = versionType == null
				? VersionFactory.OSGiType
				: versionType;
	}

	@Override
	public String getDefaultTag()
	{
		return TAG;
	}

	public String getBase()
	{
		return m_base;
	}

	public IVersionType getVersionType()
	{
		return m_versionType;
	}

	public ProviderMatch getMatch(Provider provider, NodeQuery query, IProgressMonitor monitor) throws CoreException
	{
		Logger logger = CorePlugin.getLogger();
		ComponentRequest cq = query.getComponentRequest();
		VersionMatch candidate = null;
		Map<String,String> candidateMap = null;

		URL baseURL;
		try
		{
			baseURL = new URL(ExpandingProperties.expand(query.getProperties(), m_base, 0));
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}

		for(URL urlToMatch : URLCatalogReaderType.list(baseURL, monitor))
		{
			Map<String, String> matchMap = getMatchMap(urlToMatch.toString());
			if(matchMap == null)
				continue;

			String matchedName = matchMap.get(COMPONENT_NAME_PARAM);
			if(!cq.getName().equals(matchedName))
			{
				logger.debug("URI name %s does not match %s", matchedName, cq.getName());
				continue;
			}

			IVersion version = null;
			String tmp = matchMap.get(COMPONENT_VERSION_PARAM);
			if(tmp != null)
			{
				try
				{
					version = m_versionType.fromString(tmp);
					IVersionDesignator vd = cq.getVersionDesignator();
					if(!(vd == null || vd.designates(version)))
					{
						logger.debug("URI version %s is not designated by %s", version, vd);
						continue;
					}
				}
				catch(CoreException e)
				{
					logger.warning(e, e.getMessage());
					continue;
				}
			}

			Filter filter = getFilter(matchMap);
			if(!FilterUtils.isMatch(filter, query.getProperties()))
			{
				logger.debug("URI filter %s does not match current environment", filter);
				continue;
			}

			VersionSelector vs = null;
			tmp = matchMap.get(BRANCH_PARAM);
			if(tmp != null)
				vs = VersionSelector.branch(tmp);
			else
			{
				tmp = matchMap.get(TAG_PARAM);
				if(tmp != null)
					vs = VersionSelector.tag(tmp);
			}

			String space = matchMap.get(SPACE_PARAM);	
			long revision = -1;
			tmp = matchMap.get(REVISION_PARAM);
			if(tmp != null)
			{
				try
				{
					revision = Long.parseLong(tmp);
				}
				catch(NumberFormatException e)
				{
					logger.warning(e, e.getMessage());
				}
			}

			Date timestamp = null;
			tmp = matchMap.get(TIMESTAMP_PARAM);
			if(tmp != null)
			{
				try
				{
					timestamp = DateAndTimeUtils.fromString(tmp);
				}
				catch(ParseException e)
				{
					logger.warning(e, e.getMessage());
				}
			}

			VersionMatch vm =  new VersionMatch(version, vs, space, revision, timestamp, null);
			if(candidate == null || query.compare(vm, candidate) > 0)
			{
				// Verify that the URI created using this matchMap is readable
				//
				candidate = vm;
				candidateMap = matchMap;
			}
		}
		if(candidate == null)
			return null;

		query = query.getContext().getNodeQuery(query.getQualifiedDependency());
		query.getProperties().putAll(candidateMap);
		ProviderMatch pm = new ProviderMatch(provider, CorePlugin.getDefault().getComponentType(IComponentType.UNKNOWN), candidate, query);
		pm.setMatcherMap(candidateMap);
		return pm;
	}

	public Resolution createResolution(ProviderMatch pm) throws CoreException
	{
		Map<String, String> matchMap = pm.getMatcherMap();
		if(matchMap == null)
			return null;

		CSpecBuilder bld = new CSpecBuilder();
		bld.setName(matchMap.get(COMPONENT_NAME_PARAM));
		String tmp = matchMap.get(COMPONENT_VERSION_PARAM);
		if(tmp != null)
			bld.setVersion(m_versionType.fromString(tmp));

		IComponentType ctype = pm.getComponentType();
		bld.setComponentTypeID(ctype.getId());
		bld.setFilter(getFilter(matchMap));

		try
		{
			IFileInfo info = DownloadManager.readInfo(URLUtils.normalizeToURL(pm.getRepositoryURI()));
			return new Resolution(bld.createCSpec(), pm.getNodeQuery(), pm.getProvider(), ctype.getId(), pm.getVersionMatch(), info);
		}
		catch(FileNotFoundException e)
		{
			return null;
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		super.addAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_BASE, m_base);
		if(m_versionType != VersionFactory.OSGiType)
			Utils.addAttribute(attrs, ATTR_VERSION_TYPE, m_versionType.getId());
	}

	private static Filter getFilter(Map<String,String> matchMap)
	{
		return FilterUtils.createFilter(
				matchMap.get(OS_PARAM),
				matchMap.get(WS_PARAM),
				matchMap.get(ARCH_PARAM),
				matchMap.get(NL_PARAM));
	}
}

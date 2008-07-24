/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.MapUnion;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.parser.IParserFactory;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecision;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Filter;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class ResourceMap extends AbstractSaxableElement implements ISaxable
{
	public static final String TAG = "rmap";

	private final ArrayList<Matcher> m_matchers = new ArrayList<Matcher>();

	private final HashMap<String, SearchPath> m_searchPaths = new HashMap<String, SearchPath>();

	private final Map<String, String> m_properties = new ExpandingProperties(null);

	private Documentation m_documentation;

	public static ResourceMap fromURL(URL url) throws CoreException
	{
		IParserFactory pf = CorePlugin.getDefault().getParserFactory();
		IParser<ResourceMap> rmapParser = pf.getResourceMapParser(true);
		InputStream input = null;
		try
		{
			input = DownloadManager.read(url);
			return rmapParser.parse(url.toString(), input);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	public void addMatcher(Matcher matcher)
	{
		m_matchers.add(matcher);
	}

	public void addPrefixMappings(HashMap<String, String> prefixMappings)
	{
		prefixMappings.put("xsi", javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
		prefixMappings.put(XMLConstants.BM_RMAP_PREFIX, XMLConstants.BM_RMAP_NS);
		for(SearchPath searchPath : m_searchPaths.values())
			searchPath.addPrefixMappings(prefixMappings);
	}

	public void addSearchPath(SearchPath searchPath)
	{
		m_searchPaths.put(searchPath.getName(), searchPath);
	}

	public void clear()
	{
		m_matchers.clear();
		m_searchPaths.clear();
		m_properties.clear();
		m_documentation = null;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public Map<String,String> getProperties()
	{
		return m_properties;
	}

	public Map<String,String> getProperties(Map<String,String> properties)
	{
		if(!m_properties.isEmpty())
			properties = new MapUnion<String, String>(properties, m_properties);
		return properties;
	}

	public Collection<SearchPath> getSearchPaths()
	{
		return m_searchPaths.values();
	}

	public void removeMatcher(Matcher matcher)
	{
		m_matchers.remove(matcher);
	}

	public DepNode resolve(NodeQuery query, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 2000);
		ArrayList<Provider> noGoodList = new ArrayList<Provider>();
		SearchPath searchPath = getSearchPath(query);
		MultiStatus problemCollector = new MultiStatus(CorePlugin.getID(), IStatus.ERROR,
				String.format("No suitable provider for component %s was found in searchPath %s",
						query.getComponentRequest(), searchPath.getName()), null);

		try
		{
			for(boolean first = true;; first = false)
			{
				ProviderMatch providerMatch = searchPath.getProvider(query, noGoodList, problemCollector, MonitorUtils.subMonitor(monitor, first ? 1000 : 0));
				MonitorUtils.testCancelStatus(monitor);

				Provider provider = providerMatch.getProvider();
				IComponentType cType = providerMatch.getComponentType();
				try
				{
					DepNode node = cType.getResolution(providerMatch, MonitorUtils.subMonitor(monitor, first ? 1000 : 0));
					Resolution resolution = node.getResolution();
					MonitorUtils.testCancelStatus(monitor);
					Filter[] filterHandle = new Filter[1];
					if(!resolution.isFilterMatchFor(query, filterHandle))
					{
						ResolverDecision decision = query.logDecision(ResolverDecisionType.FILTER_MISMATCH, filterHandle[0]);						
						noGoodList.add(providerMatch.getOriginalProvider());
						problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), null));
						continue;
					}

					CSpec cspec = resolution.getCSpec();

					// Assert that the cspec can handle required actions and
					// exports
					//
					IVersion version = cspec.getVersion();
					IVersionDesignator versionDesignator = query.getVersionDesignator();
					if(versionDesignator != null && provider.getVersionConverterDesc() == null)
					{
						// A missing version converter means that the actual version check is deferred
						// and later performed on the retreived CSpec. Later is now ...
						//
						if(!versionDesignator.designates(version))
						{
							ResolverDecision decision = query.logDecision(ResolverDecisionType.VERSION_REJECTED, version, String.format("not designated by %s", versionDesignator));						
							noGoodList.add(providerMatch.getOriginalProvider());
							problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), null));
							continue;
						}
					}

					// Verify that all required attributes are in this cspec
					//
					cspec.getAttributes(query.getRequiredAttributes());
					if(version != null)
					{
						// Replace the resolution version if it is default and
						// a non default version is present in the CSpec
						//
						VersionMatch vm = resolution.getVersionMatch();
						if(vm.getVersion() == null)
							node = new ResolvedNode(new Resolution(version, resolution), node.getChildren());
					}
					return node;
				}
				catch(CoreException e)
				{
					ResolverDecision decision = query.logDecision(ResolverDecisionType.EXCEPTION, e.getMessage());						
					problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), e));
					noGoodList.add(providerMatch.getOriginalProvider());
				}
			}
		}
		finally
		{
			monitor.done();
		}
	}

	public SearchPath getLocalSearchPath(String componentName) throws CoreException
	{
		for(Matcher matcher : m_matchers)
		{
			if(matcher instanceof Locator && matcher.matches(componentName))
				return matcher.getSearchPath(null);
		}
		return null;
	}

	public SearchPath getSearchPath(NodeQuery query) throws CoreException
	{
		ComponentRequest request = query.getComponentRequest();
		String componentName = request.getName();
		for(Matcher matcher : m_matchers)
		{
			if(matcher.matches(componentName))
			{
				SearchPath sp = matcher.getSearchPath(query);
				query.logDecision(ResolverDecisionType.USING_SEARCH_PATH, sp.getName());
				return sp;
			}
		}
		query.logDecision(ResolverDecisionType.SEARCH_PATH_NOT_FOUND, (Object)null);
		throw new SearchPathNotFoundException("component " + componentName);
	}

	public SearchPath getSearchPathByReference(String searchPathRef) throws SearchPathNotFoundException
	{
		SearchPath sp = m_searchPaths.get(searchPathRef);
		if(sp == null)
			throw new SearchPathNotFoundException("reference " + searchPathRef);
		return sp;
	}

	public void setDocumentation(Documentation documentation)
	{
		m_documentation = documentation;
	}

	public List<Matcher> getMatchers()
	{
		return m_matchers;
	}

	/**
	 * Emit the SAX events that forms the XML representation for this instance
	 * 
	 * @param handler
	 *            The handler that will receive the events.
	 * @throws SAXException
	 *             if the handler throws an exception when receiving the events
	 */
	public void toSax(ContentHandler handler) throws SAXException
	{
		handler.startDocument();
		toSax(handler, XMLConstants.BM_RMAP_NS, XMLConstants.BM_RMAP_PREFIX, getDefaultTag());
		handler.endDocument();
	}

	@Override
	public void toSax(ContentHandler handler, String namespace, String prefix, String localName) throws SAXException
	{
		HashMap<String,String> prefixMappings = new HashMap<String,String>();
		addPrefixMappings(prefixMappings);
		Set<Map.Entry<String,String>> pfxMappings = prefixMappings.entrySet();
		for(Map.Entry<String,String> pfxMapping : pfxMappings)
			handler.startPrefixMapping(pfxMapping.getKey(), pfxMapping.getValue());
		super.toSax(handler, namespace, prefix, localName);
		for(Map.Entry<String,String> pfxMapping : pfxMappings)
			handler.endPrefixMapping(pfxMapping.getKey());
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		if(m_documentation != null)
			m_documentation.toSax(handler, namespace, prefix, m_documentation.getDefaultTag());

		SAXEmitter.emitProperties(handler, m_properties, namespace, prefix, true, false);

		for(SearchPath searchPath : m_searchPaths.values())
			searchPath.toSax(handler, namespace, prefix, searchPath.getDefaultTag());

		for(Matcher matcher : m_matchers)
			matcher.toSax(handler, namespace, prefix, matcher.getDefaultTag());
	}
}

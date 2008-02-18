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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.MultiStatus;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * @author Thomas Hallgren
 */
public class SearchPath extends AbstractSaxableElement
{
	public static final String TAG = "searchPath";

	public static final String ATTR_NAME = "name";

	private final String m_name;

	private final ArrayList<Provider> m_providers = new ArrayList<Provider>();
	
	private final ResourceMap m_resourceMap;

	public SearchPath(ResourceMap rmap, String name)
	{
		m_resourceMap = rmap;
		m_name = name;
	}

	public final void addProvider(Provider provider)
	{
		m_providers.add(provider);
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	/**
	 * @return Returns the name.
	 */
	public final String getName()
	{
		return m_name;
	}

	/**
	 * Returns the resource map in which this search path is defined
	 * @return the resource map
	 */
	public ResourceMap getResourceMap()
	{
		return m_resourceMap;
	}

	/**
	 * Find a provider that meets the requirements as closely as possible. All
	 * requirements has to be met but too much capabilities is considered a
	 * minus.
	 */
	public ProviderMatch getProvider(NodeQuery query, List<Provider> noGoodList, MultiStatus problemCollector, IProgressMonitor monitor) throws CoreException
	{
		int provCnt = m_providers.size();
		if(provCnt == 0)
			throw new CoreException(problemCollector);

		monitor.beginTask("Matching providers", provCnt * 1000);
		try
		{
			ProviderMatch bestMatch = null;
			IVersionDesignator desiredVersion = query.getVersionDesignator();

			for(Provider provider : m_providers)
			{
				if(noGoodList.contains(provider))
					continue;

				query.logDecision(ResolverDecisionType.TRYING_PROVIDER, provider.getReaderTypeId(), provider.getURI());
				ProviderMatch match = provider.findMatch(query, problemCollector, MonitorUtils.subMonitor(monitor, 1000));
				if(match == null)
				{
					noGoodList.add(provider);
					continue;
				}

				String readerType = provider.getReaderTypeId();
				ProviderScore score = match.getProviderScore();

				if((score.ordinal() >= ProviderScore.FAIR.ordinal() && IReaderType.LOCAL.equals(readerType))
				|| (score.ordinal() >= ProviderScore.GOOD.ordinal() && desiredVersion != null && desiredVersion.isIdeal(match.getVersionMatch().getVersion())))
				{
					// No use continuing the search. It won't get better
					// than this.
					//
					bestMatch = match;
					break;
				}

				if(bestMatch == null || match.compareTo(bestMatch) > 0)
				{
					if(bestMatch != null)
					{
						Provider rejected = bestMatch.getOriginalProvider();
						query.logDecision(ResolverDecisionType.REJECTING_PROVIDER,
							rejected.getReaderTypeId(), rejected.getURI(),
							String.format("%s(%s) is producing a better match", provider.getReaderTypeId(), provider.getURI()));
					}
					bestMatch = match;
					continue;
				}

				if(bestMatch != null)
				{
					Provider best = bestMatch.getOriginalProvider();
					query.logDecision(ResolverDecisionType.REJECTING_PROVIDER,
							provider.getReaderTypeId(), provider.getURI(),
							String.format("%s(%s) is producing a better match", best.getReaderTypeId(), best.getURI()));
				}
			}
			if(bestMatch == null)
			{
				query.logDecision(ResolverDecisionType.PROVIDER_NOT_FOUND);
				throw new CoreException(problemCollector);
			}

			Provider best = bestMatch.getOriginalProvider();
			query.logDecision(ResolverDecisionType.USING_PROVIDER, best.getReaderTypeId(), best.getURI());
			return bestMatch;
		}
		finally
		{
			monitor.done();
		}
	}

	public List<Provider> getProviders()
	{
		return m_providers;
	}

	public void addPrefixMappings(HashMap<String, String> prefixMappings)
	{
		for(Provider provider : m_providers)
			provider.addPrefixMappings(prefixMappings);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		Utils.addAttribute(attrs, ATTR_NAME, m_name);
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		for(Provider provider : m_providers)
			provider.toSax(handler, namespace, prefix, provider.getDefaultTag());
	}
}

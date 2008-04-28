/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.eclipse.buckminster.generic.ui.actions.IBrowseable;
import org.eclipse.buckminster.generic.ui.actions.IBrowseableFeed;
import org.eclipse.buckminster.opml.model.Outline;
import org.eclipse.buckminster.opml.model.OutlineType;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterFactory;

/**
 * @author Henrik Lindberg
 *
 */
public class BrowseableAdapterFactory implements IAdapterFactory
{
	@SuppressWarnings("unchecked")
	private static Class[] s_supported = { IBrowseable.class, IBrowseableFeed.class };

	@SuppressWarnings("unchecked")
	public Object getAdapter(Object adaptableObject, Class adapterType)
	{
		// Adapt an Outline or adaptable to Outline
		//
		Outline outline = null;
		if(adaptableObject instanceof IAdaptable)
			outline = (Outline)((IAdaptable)adaptableObject).getAdapter(Outline.class);
		if(outline == null && adaptableObject instanceof Outline)
			outline = (Outline)adaptableObject;
		
		// Convert outline to URL - refuse if there is no HTML URI,
		// or if this URI is not adaptable to a URL
		//
		if(outline != null)
		{
			URL url = null;
			URI uri = null;
			Class returnClass = null;
			if(adapterType.isAssignableFrom(IBrowseable.class))
			{
				returnClass = IBrowseable.class;
				// outline can be just a link
				if(outline.getType() == OutlineType.LINK)
					uri = outline.getUrl();
				else
					// or a feed - in which case the html url is what we want to browse
					uri = outline.getHtmlUrl();
			}
			else if(adapterType.isAssignableFrom(IBrowseableFeed.class))
			{
				returnClass = IBrowseableFeed.class;
				// a request to get the feed
				uri = outline.getXmlUrl();
			}

			if(uri == null)
				return null;
			try 
			{
				url = new URL(uri.toString());
			}
			catch(MalformedURLException e)
			{
				// no, nope, nix, could not adapt it - malformed url
				return null;
			}
			if(returnClass == IBrowseable.class)
				return new BrowseableURL(uri, url, outline.getText(), outline.getTitle());
			if(returnClass == IBrowseableFeed.class)
				return new BrowseableFeedURL(uri, url, outline.getText(), outline.getTitle());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Class[] getAdapterList()
	{
		return s_supported;
	}
	public static class BrowseableURL implements IBrowseable
	{
		private final URI m_uri;
		private final URL m_url;
		private String m_name;
		private String m_tooltip;
		BrowseableURL(URI uri, URL url, String name, String tooltip)
		{
			m_uri = uri;
			m_url = url;
			m_name = name;
			m_tooltip = tooltip;
		}
		public URL getBrowseableURL()
		{
			return m_url;
		}
		public URI getBrowseableURI()
		{
			return m_uri;
		}
		public String getName()
		{
		return m_name;
		}
		public String getTooltip()
		{
		return m_tooltip;
		}
	}
	public static class BrowseableFeedURL extends BrowseableURL implements IBrowseableFeed
	{
		public BrowseableFeedURL(URI uri, URL url, String name, String tooltip)
		{
			super(uri, url, name, tooltip);
		}
	}
}
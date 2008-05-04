/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.rssowl.ui;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.rssowl.ui.spi.IRewriteResult;
import org.rssowl.ui.spi.IRewriter;
import org.rssowl.ui.spi.Result;

/**
 * Rewrites URLs for buckminster: scheme, and buckminster artifacts on display in the RSS OWL browser.
 * 
 * @author Henrik Lindberg
 * 
 */
public class BuckminsterURIRewriter implements IRewriter
{
	private static final IRewriteResult s_continue = new Result(false);

	public IRewriteResult rewrite(final String location)
	{
		if(location == null || location.length() < 1)
			return s_continue;

		URI uri = null;
		try
		{
			uri = new URI(location);
		}
		catch(URISyntaxException e)
		{
			return s_continue; // let someone else worry about the malformed URI
		}
		rule:
		{
			// This rule adds an "eclipse=1" parameter to a URL that points to cloudsmith materialization page
			// to allow it to render the materialization using the buckminster: scheme
			//
			if(uri.getHost() != null && uri.getHost().contains("cloudsmith.com"))
			{
				String path = uri.getRawPath();
				if(path == null || path.length() > 0 || !path.contains("dynamic/view") || !path.contains("component"))
					break rule;

				StringBuilder bld = new StringBuilder(location + 10);
				bld.append(location);
				// if there was a query string already, the added parameter must have & separator
				bld.append(uri.getRawQuery() == null
						? "?"
						: "&");
				bld.append("eclipse=1");
				return new Result(bld.toString());
			}
		}
		rule:
		{
			// testing rule - TODO: remove when code before production
			if(!location.contains("www.eclipse.org"))
				break rule;
			return new Result("buckminster:materialize:file:///Users/henrik/Sites/index.html");
		}
		rule:
		{
			if(uri.getScheme() == null || !uri.getScheme().equals("buckminster"))
				break rule;

			// parse the buckminster scheme specific part into Action and parameters
			//
			String schemeSpecificPart = uri.getRawSchemeSpecificPart();
			try
			{
				URI schemeURI = new URI(schemeSpecificPart);
				String actionName = schemeURI.getScheme();
				String actionData = schemeURI.getSchemeSpecificPart();

				// buckminster:materialize:URL
				if("materialize".equals(actionName))
					return new Result(new MaterializeAction(actionData),true);
			}
			catch(URISyntaxException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return s_continue;
			}
		}

		return s_continue;
	}
	private static class MaterializeAction extends Action
	{
		public MaterializeAction(String actionData)
		{
			MessageDialog.openInformation(null, "Materialize", "You clicked on materialization of: " + actionData);
			
		}
	}
}

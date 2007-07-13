/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.bootstrap;

import java.applet.Applet;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.jnlp.cache.JNLPResource;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Filip Hrbek
 *
 * This class provides an entry point for invocation the product from an applet.
 * In fact, no real JNLP software is used when using this entry.
 * The main advantage of using this approach is avoiding problems with convenient start
 * from a web page, such as java version discovery, initial (ugly) splash screens etc.
 */
public class AppletEntry extends Applet
{
	private static final long serialVersionUID = -4734226134244547756L;

	@Override
	public void init()
	{
		try
		{
			String jnlpLink = getParameter("jnlpLink");
			
			/* Parse JNLP descriptor which would normally be used for launching the application
			   with Java Web Start. Extract only the most important information - command line
			   arguments and use them for launching the application.
			*/
			JNLPResource resource = new JNLPResource(new URL(jnlpLink));
			NodeList arguments = resource.getDocument().getElementsByTagName("argument");
			
			int len = arguments.getLength();
			List<String> args = new ArrayList<String>();
			
			for (int i=0; i < len; i++)
			{
				Node argument = arguments.item(i);
				args.add(argument.getTextContent());
			}
			
			Main.launch(args.toArray(new String[0]), true);
		}
		catch(Exception e)
		{
			throw new Error("Unable to initialize applet: " + e.getMessage(), e);
		}
	}
}

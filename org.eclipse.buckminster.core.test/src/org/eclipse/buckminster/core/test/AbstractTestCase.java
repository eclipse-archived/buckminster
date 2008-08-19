/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.query.builder.AdvisorNodeBuilder;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;


/**
 * @author Thomas Hallgren
 */
public abstract class AbstractTestCase extends TestCase
{
	public AbstractTestCase()
	{
	}

	public AbstractTestCase(String name)
	{
		super(name);
	}

	@Override
	public void setUp()
	throws Exception
	{
		BuckminsterPreferences.setLogLevelConsole(Logger.DEBUG);
		BuckminsterPreferences.setLogLevelEclipseLogger(Logger.SILENT);
	}

	protected IResolver createResolver(String componentName, String componentType) throws Exception
	{
		ComponentRequest request = new ComponentRequest(componentName, componentType, null);
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(request);
		queryBld.setResourceMapURL(getRMAP().toString());
		AdvisorNodeBuilder node = queryBld.addAdvisorNode();
		node.setNamePattern(Pattern.compile("(subclipse)|(subversive)|(slf4j)|(buckminster)"));
		node.setUseTargetPlatform(false);
		ComponentQuery query = queryBld.createComponentQuery();
		return new MainResolver(new ResolutionContext(query));
	}

	@Override
	protected void runTest() throws Throwable
	{
		try
		{
			super.runTest();
		}
		catch(CoreException e)
		{
			BuckminsterException.deeplyPrint(e, System.err, true);
			throw e;
		}
	}

	protected URL getRMAP()
	{
		try
		{
			return new URL("http://www.eclipse.org/buckminster/samples/rmaps/dogfood2.rmap");
		}
		catch(MalformedURLException e)
		{
			return null;
		}
	}

	protected CorePlugin getPlugin()
	throws Exception
	{
		CorePlugin plugin = CorePlugin.getDefault();
		if(plugin == null)
			throw new Exception("This test must be run as a \"JUnit Plug-in Test\"");
		return plugin;
	}
}


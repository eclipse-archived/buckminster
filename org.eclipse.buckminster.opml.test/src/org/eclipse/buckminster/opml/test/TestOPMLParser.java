/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.opml.test;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.opml.parser.OPMLParser;
import org.eclipse.buckminster.sax.Utils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author Thomas Hallgren
 *
 */
public class TestOPMLParser extends TestCase
{
	public TestOPMLParser(String methodName)
	{
		super(methodName);
	}

	public static Test suite() throws Exception
	{
		TestSuite suite = new TestSuite();
		suite.addTest(new TestOPMLParser("testParser"));
		return suite;
	}

	public void testParser() throws Exception
	{
		OPMLParser parser = new OPMLParser(true);
		OutputStream out = System.out;
		Utils.serialize(parseURL("http://hosting.opml.org/dave/spec/subscriptionList.opml", parser), out);
		Utils.serialize(parseURL("http://hosting.opml.org/dave/spec/states.opml", parser), out);
		Utils.serialize(parseURL("http://hosting.opml.org/dave/spec/simpleScript.opml", parser), out);
		Utils.serialize(parseURL("http://hosting.opml.org/dave/spec/placesLived.opml", parser), out);
		Utils.serialize(parseURL("http://hosting.opml.org/dave/spec/directory.opml", parser), out);
		Utils.serialize(parseURL("http://hosting.opml.org/dave/spec/category.opml", parser), out);
	}

	public static OPML parseURL(String urlString, OPMLParser parser) throws Exception
	{
		URL url = new URL(urlString);
		InputStream input = null;
		try
		{
			input = new BufferedInputStream(url.openStream());
			return parser.parse(urlString, input);
		}
		finally
		{
			if(input != null)
				input.close();
		}
	}
}

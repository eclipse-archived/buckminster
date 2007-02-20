/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.test.property;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.eclipse.buckminster.core.common.model.CircularExpansionException;
import org.eclipse.buckminster.core.common.model.Constant;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.common.model.IProperties;
import org.eclipse.buckminster.core.common.model.PropertyRef;
import org.eclipse.buckminster.core.common.model.Replace;
import org.eclipse.buckminster.core.common.model.Split;
import org.eclipse.buckminster.core.common.model.ValueHolder;
import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.TopHandler;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class PropertyFormatTestCase extends TestCase
{
	static void log(String message, Object ...args)
	{
		System.out.format(message + "%n", args);
	}

	public void testSystemProperties()
	{
		Format fmt = new Format("You are {0}, the parent of your home is {1} and you run Java version {2}");
		fmt.addValueHolder(new PropertyRef("user.name"));
		Replace rpl1 = new Replace();
		rpl1.addMatch(new Replace.Match(Pattern.quote("\\"), "/", false));
		rpl1.addValueHolder(new PropertyRef("user.home"));
		Replace rpl2 = new Replace();
		rpl2.addMatch(new Replace.Match("^(.*)/[^/]+$", "$1", false));
		rpl2.addValueHolder(rpl1);
		fmt.addValueHolder(rpl2);
		fmt.addValueHolder(new PropertyRef("java.version"));

		IProperties props = BMProperties.getSystemProperties();
		String result = fmt.getValue(props);
		String expected =
			"You are " + props.get("user.name") + 
			", the parent of your home is " +
			(new File(props.get("user.home"))).getParent().replace('\\', '/') +
			" and you run Java version " + props.get("java.version");

		log(result);
		assertEquals(expected, result);
	}

	public void testExpandingProperties()
	{
		IProperties props = new ExpandingProperties(BMProperties.getSystemProperties());
		props.put("salut", "Hello ${user.name}!");
		props.put("salut.home", "${salut} Your \\${user.home} is ${user.home}");

		String result = props.get("salut.home");
		String expected =
			"Hello " + System.getProperty("user.name") +
			"! Your ${user.home} is " + System.getProperty("user.home");

		log(result);
		assertEquals(expected, result);
	}

	public void testCircularExpansionTrap()
	{
		IProperties props = new ExpandingProperties();
		props.put("some.text", "text is ${some.text.again}!");
		props.put("some.other.text", "Hello ${some.text}!");
		props.put("some.text.again", "Ouch! ${some.other.text}");

		try
		{
			log(props.get("some.text.again"));
			assertFalse(true);
		}
		catch(CircularExpansionException e)
		{
			log("OK: \"%s\" resulted in a CircularExpansionException", e.getMessage());
		}
	}

	public void testExpressions()
	{
		ValueHolder cvsRoot = new Constant(":pserver:${user.name}@buckminster.tigris.org:/cvs");
		String fmtString = "First \"{0}\", second \"{1}\", third \"{2}\", and fourth \"{3}\"";

		Format fmt = new Format(fmtString);
		Split split = new Split(":|@", 0);
		Replace rpl = new Replace();
		rpl.addMatch(new Replace.Match("^:(.*)$", "$1", false));
		rpl.addValueHolder(cvsRoot);
		split.addValueHolder(rpl);
		fmt.addValueHolder(split);

		String expected = new MessageFormat(fmtString).format(new String[]
		{
			"pserver",
			System.getProperty("user.name"),
			"buckminster.tigris.org",
			"/cvs"
		});

		IProperties props = BMProperties.getSystemProperties();
		String result = fmt.getValue(props);
		log(result);
		assertEquals(expected, result);
	}

	class DummyParser extends TopHandler
	{
		private final IProperties m_properties;

		protected DummyParser(IProperties properties)
		throws SAXException
		{
			super(Utils.createXMLReader(false, true));
			m_properties = properties;
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attrs)
		throws SAXException
		{
			if("testElement".equals(localName))
			{
				PropertyManagerHandler pmh = new PropertyManagerHandler(this, "root")
				{
					@Override
					public ExpandingProperties getProperties()
					{
						return (ExpandingProperties)m_properties;
					}
				};
				this.pushHandler(pmh, attrs);
			}
			else
				super.startElement(uri, localName, qName, attrs);
		}

		public void parse(URL url) throws IOException, SAXException
		{
			InputStream input = URLUtils.openStream(url, null);
			try
			{
				InputSource source = new InputSource(new BufferedInputStream(input));
				source.setSystemId(url.toString());
				this.getXMLReader().parse(source);
			}
			finally
			{
				try { input.close(); } catch(IOException e) {}
			}
		}
	}

	public void testPropertyParser()
	throws Exception
	{
		IProperties props = new ExpandingProperties(BMProperties.getSystemProperties());
		props.put("buckminster.component.target", "ant-optional");
		props.put("buckminster.component.name", "org.apache.tools.ant");
		props.put("buckminster.component.version", "1.7.0beta1");

		DummyParser parser = new DummyParser(props);
		parser.parse(this.getClass().getResource("valuetest.xml"));

		String result = props.get("maven.url");
		String verboseResult = props.get("verbose.maven.url");

		log(result);
		log(verboseResult);

		assertEquals("http://www.ibiblio.org/maven/ant/jars/ant-optional-1.7.0.jar", result);
		assertEquals("The created URL is \"" + result + '"', verboseResult);
	}
}


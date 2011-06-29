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
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.sax.TopHandler;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class PropertyFormatTest extends TestCase {
	class DummyParser extends TopHandler {
		final IProperties<String> properties;

		protected DummyParser(IProperties<String> properties) throws SAXException {
			super(Utils.createXMLReader(false, true));
			this.properties = properties;
		}

		public void parse(URL url) throws IOException, SAXException, CoreException {
			InputStream input = DownloadManager.read(url, null);
			try {
				InputSource source = new InputSource(new BufferedInputStream(input));
				source.setSystemId(url.toString());
				this.getXMLReader().parse(source);
			} finally {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
			if ("testElement".equals(localName)) { //$NON-NLS-1$
				PropertyManagerHandler pmh = new PropertyManagerHandler(this, "root") { //$NON-NLS-1$
					@Override
					public IProperties<String> getProperties() {
						return properties;
					}
				};
				this.pushHandler(pmh, attrs);
			} else
				super.startElement(uri, localName, qName, attrs);
		}
	}

	static void log(String message, Object... args) {
		System.out.format(message + "%n", args); //$NON-NLS-1$
	}

	public void testCircularExpansionTrap() {
		IProperties<String> props = new ExpandingProperties<String>();
		props.put("some.text", "text is ${some.text.again}!"); //$NON-NLS-1$ //$NON-NLS-2$
		props.put("some.other.text", "Hello ${some.text}!"); //$NON-NLS-1$ //$NON-NLS-2$
		props.put("some.text.again", "Ouch! ${some.other.text}"); //$NON-NLS-1$ //$NON-NLS-2$

		try {
			log(props.get("some.text.again")); //$NON-NLS-1$
			assertFalse(true);
		} catch (CircularExpansionException e) {
			log("OK: \"%s\" resulted in a CircularExpansionException", e.getMessage()); //$NON-NLS-1$
		}
	}

	public void testExpandingEnvVarProperties() {
		IProperties<String> props = new ExpandingProperties<String>(BMProperties.getSystemProperties());
		props.put("env.salut", "Hello ${env_var:LOGNAME}!");
		props.put("env.salut.home", "${env.salut} Your $${env_var:PATH} is ${env_var:PATH}");
		String result = props.get("env.salut.home"); //$NON-NLS-1$
		String expected = "Hello " + System.getenv("LOGNAME") + "! Your ${env_var:PATH} is " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ System.getenv("PATH"); //$NON-NLS-1$
		log(result);
		assertEquals(expected, result);
	}

	public void testExpandingProperties() {
		IProperties<String> props = new ExpandingProperties<String>(BMProperties.getSystemProperties());
		props.put("salut", "Hello ${user.name}!"); //$NON-NLS-1$ //$NON-NLS-2$
		props.put("salut.home", "${salut} Your $${user.home} is ${user.home}"); //$NON-NLS-1$ //$NON-NLS-2$

		String result = props.get("salut.home"); //$NON-NLS-1$
		String expected = "Hello " + System.getProperty("user.name") + "! Your ${user.home} is " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ System.getProperty("user.home"); //$NON-NLS-1$

		log(result);
		assertEquals(expected, result);
	}

	public void testExpressions() {
		ValueHolder<String> cvsRoot = new Constant<String>(":pserver:${user.name}@buckminster.tigris.org:/cvs"); //$NON-NLS-1$
		String fmtString = "First \"{0}\", second \"{1}\", third \"{2}\", and fourth \"{3}\""; //$NON-NLS-1$

		Format fmt = new Format(fmtString);
		Split split = new Split(":|@", 0); //$NON-NLS-1$
		Replace rpl = new Replace();
		rpl.addMatch(new Replace.Match("^:(.*)$", "$1", false)); //$NON-NLS-1$ //$NON-NLS-2$
		rpl.addValueHolder(cvsRoot);
		split.addValueHolder(rpl);
		fmt.addValueHolder(split);

		String expected = new MessageFormat(fmtString).format(new String[] { "pserver", //$NON-NLS-1$
				System.getProperty("user.name"), "buckminster.tigris.org", "/cvs" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		IProperties<String> props = BMProperties.getSystemProperties();
		String result = fmt.getValue(props);
		log(result);
		assertEquals(expected, result);
	}

	public void testPropertyParser() throws Exception {
		IProperties<String> props = new ExpandingProperties<String>(BMProperties.getSystemProperties());
		props.put("buckminster.component.target", "ant-optional"); //$NON-NLS-1$ //$NON-NLS-2$
		props.put("buckminster.component.name", "org.apache.tools.ant"); //$NON-NLS-1$ //$NON-NLS-2$
		props.put("buckminster.component.version", "1.7.0beta1"); //$NON-NLS-1$ //$NON-NLS-2$

		DummyParser parser = new DummyParser(props);
		parser.parse(this.getClass().getResource("/testData/misc/valuetest.xml")); //$NON-NLS-1$

		String result = props.get("maven.url"); //$NON-NLS-1$
		String verboseResult = props.get("verbose.maven.url"); //$NON-NLS-1$

		log(result);
		log(verboseResult);

		assertEquals("http://www.ibiblio.org/maven/ant/jars/ant-optional-1.7.0.jar", result); //$NON-NLS-1$
		assertEquals("The created URL is \"" + result + '"', verboseResult); //$NON-NLS-1$
	}

	public void testSystemProperties() {
		Format fmt = new Format("You are {0}, the parent of your home is {1} and you run Java version {2}"); //$NON-NLS-1$
		fmt.addValueHolder(new PropertyRef<String>(String.class, "user.name")); //$NON-NLS-1$
		Replace rpl1 = new Replace();
		rpl1.addMatch(new Replace.Match(Pattern.quote("\\"), "/", false)); //$NON-NLS-1$ //$NON-NLS-2$
		rpl1.addValueHolder(new PropertyRef<String>(String.class, "user.home")); //$NON-NLS-1$
		Replace rpl2 = new Replace();
		rpl2.addMatch(new Replace.Match("^(.*)/[^/]+$", "$1", false)); //$NON-NLS-1$ //$NON-NLS-2$
		rpl2.addValueHolder(rpl1);
		fmt.addValueHolder(rpl2);
		fmt.addValueHolder(new PropertyRef<String>(String.class, "java.version")); //$NON-NLS-1$

		IProperties<String> props = BMProperties.getSystemProperties();
		String result = fmt.getValue(props);
		String expected = "You are " + props.get("user.name") + ", the parent of your home is " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ (new File(props.get("user.home"))).getParent().replace('\\', '/') + " and you run Java version " //$NON-NLS-1$ //$NON-NLS-2$
				+ props.get("java.version"); //$NON-NLS-1$

		log(result);
		assertEquals(expected, result);
	}
}

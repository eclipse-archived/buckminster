/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.test.cspec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Date;

import junit.framework.TestCase;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.parser.IParserFactory;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.sax.Utils;

public class CSpecBuilderTestCase extends TestCase
{
	public void testSaxGenerator()
	throws Exception
	{
		CorePlugin plugin = CorePlugin.getDefault();
		if(plugin == null)
			throw new Exception("This test must be run as a \"JUnit Plug-in Test\"");

		CSpecBuilder cspecBld = new CSpecBuilder();
		cspecBld.setName("my.test.project");
		cspecBld.setVersion(VersionFactory.OSGiType.fromString("1.2.3"));
		ComponentRequest c1 = new ComponentRequest("org.apache.ant", IComponentType.OSGI_BUNDLE, "[1.6.2,2.0.0)", IVersionType.OSGI, null);
		cspecBld.addDependency(c1);
		cspecBld.addDependency(new ComponentRequest("se.tada.util.sax", null, null, null, null));
		cspecBld.addDependency(new ComponentRequest("org.eclipse.team.core", IComponentType.OSGI_BUNDLE, "3.1.0", IVersionType.OSGI, null));
		cspecBld.addDependency(new ComponentRequest("org.junit", null, "3.1.8", IVersionType.OSGI, null));

		CSpec c = cspecBld.createCSpec();

		ComponentRequest request = new ComponentRequest("test", null, null);
		IVersion vs = VersionFactory.OSGiType.fromString("1.0.0");
		VersionMatch fixed = new VersionMatch(vs, null, null, -1, new Date(), null);
		Provider provider = new Provider("svn", new String[] { IComponentType.BUCKMINSTER }, "svn://foo.bar.com/foobar");
		Resolution resolution = new Resolution(c, IComponentType.BUCKMINSTER, fixed, provider, true, request,
			Collections.<String>emptyList(), provider.getURI(Collections.<String,String>emptyMap()), null, null, 0L, -1L);

		ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		Utils.serialize(resolution, ostream);
		String content = new String(ostream.toByteArray(), "UTF-8");
		System.out.println(content);

		IParserFactory parserFactory = CorePlugin.getDefault().getParserFactory();
		IParser<Resolution> parser = parserFactory.getResolutionParser();
		ByteArrayInputStream ba = new ByteArrayInputStream(content.getBytes("UTF-8"));
		Resolution res2 = parser.parse(null, ba);
		assertEquals(resolution, res2);

		ostream = new ByteArrayOutputStream();
		Utils.serialize(res2, ostream);
		String content2 = new String(ostream.toByteArray(), "UTF-8");
		assertEquals(content, content2);
	}
}

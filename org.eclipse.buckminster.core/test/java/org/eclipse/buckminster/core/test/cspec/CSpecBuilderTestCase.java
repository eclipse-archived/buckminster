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
import java.util.UUID;

import junit.framework.TestCase;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.parser.IParserFactory;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;

public class CSpecBuilderTestCase extends TestCase
{
	private static IVersionDesignator createOSGI(String designator) throws CoreException
	{
		return VersionFactory.createDesignator(VersionFactory.OSGiType, designator);
	}

	public void testSaxGenerator()
	throws Exception
	{
		CorePlugin plugin = CorePlugin.getDefault();
		if(plugin == null)
			throw new Exception("This test must be run as a \"JUnit Plug-in Test\"");

		CSpecBuilder cspecBld = new CSpecBuilder();
		cspecBld.setName("my.test.project");
		cspecBld.setVersion(VersionFactory.OSGiType.fromString("1.2.3"));
		ComponentRequest c1 = new ComponentRequest("org.apache.ant", KeyConstants.PLUGIN_CATEGORY, createOSGI("[1.6.2,2.0.0)"));
		cspecBld.addDependency(c1);
		cspecBld.addDependency(new ComponentRequest("se.tada.util.sax", null, null));
		cspecBld.addDependency(new ComponentRequest("org.eclipse.team.core", KeyConstants.PLUGIN_CATEGORY, createOSGI("3.1.0")));
		cspecBld.addDependency(new ComponentRequest("org.junit", null, createOSGI("3.1.8")));

		CSpec c = cspecBld.createCSpec();

		ComponentRequest request = new ComponentRequest("test", null, null);
		IVersion vs = VersionFactory.OSGiType.fromString("1.0.0");
		IVersionSelector fixed = VersionSelectorFactory.timestamp(System.currentTimeMillis());
		Provider provider = new Provider("svn", IComponentType.ECLIPSE_PROJECT, Trivial.EMPTY_STRING_ARRAY, null, new Format("svn://foo.bar.com/foobar"), true, true, null);
		UUID providerId = provider.getId();
		
		Resolution resolution = new Resolution(c.getId(), vs, fixed, providerId, true, request,
			Collections.<String>emptySet(), provider.getURI(Collections.<String,String>emptyMap()));

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

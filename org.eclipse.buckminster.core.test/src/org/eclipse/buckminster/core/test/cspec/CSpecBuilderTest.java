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
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.parser.IParserFactory;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionType;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.equinox.p2.metadata.Version;

public class CSpecBuilderTest extends TestCase {
	public void testSaxGenerator() throws Exception {
		CorePlugin plugin = CorePlugin.getDefault();
		if (plugin == null)
			throw new Exception("This test must be run as a \"JUnit Plug-in Test\""); //$NON-NLS-1$

		CSpecBuilder cspecBld = new CSpecBuilder();
		cspecBld.setName("my.test.project"); //$NON-NLS-1$
		cspecBld.setVersion(Version.parseVersion("1.2.3")); //$NON-NLS-1$
		ComponentRequest c1 = new ComponentRequest("org.apache.ant", IComponentType.OSGI_BUNDLE, "[1.6.2,2.0.0)", //$NON-NLS-1$ //$NON-NLS-2$
				VersionType.OSGI, null);
		cspecBld.addDependency(c1);
		cspecBld.addDependency(new ComponentRequest("se.tada.util.sax", null, null, null, null)); //$NON-NLS-1$
		cspecBld.addDependency(new ComponentRequest("org.eclipse.team.core", IComponentType.OSGI_BUNDLE, "3.1.0", //$NON-NLS-1$ //$NON-NLS-2$
				VersionType.OSGI, null));
		cspecBld.addDependency(new ComponentRequest("org.junit", null, "3.1.8", VersionType.OSGI, null)); //$NON-NLS-1$ //$NON-NLS-2$

		CSpec c = cspecBld.createCSpec();

		StorageManager sm = StorageManager.getDefault();
		ComponentRequest request = new ComponentRequest("test", null, null); //$NON-NLS-1$
		Version vs = Version.parseVersion("1.0.0"); //$NON-NLS-1$
		VersionMatch fixed = new VersionMatch(vs, null, -1, new Date(), null);
		Provider provider = new Provider(null, "svn", new String[] { IComponentType.BUCKMINSTER }, //$NON-NLS-1$
				null, new Format("svn://foo.bar.com/foobar"), null, null, null, BMProperties.getSystemProperties(), null, null); //$NON-NLS-1$
		sm.getProviders().putElement(provider);
		sm.getCSpecs().putElement(c);
		Resolution resolution = new Resolution(c, IComponentType.BUCKMINSTER, fixed, provider, true, request, Collections.<String> emptyList(), null,
				provider.getURI(Collections.<String, String> emptyMap()), null, null, 0L, -1L, false);

		ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		Utils.serialize(resolution, ostream);
		String content = new String(ostream.toByteArray(), "UTF-8"); //$NON-NLS-1$
		System.out.println(content);

		IParserFactory parserFactory = CorePlugin.getDefault().getParserFactory();
		IParser<Resolution> parser = parserFactory.getResolutionParser();
		ByteArrayInputStream ba = new ByteArrayInputStream(content.getBytes("UTF-8")); //$NON-NLS-1$
		Resolution res2 = parser.parse(null, ba);
		assertEquals(resolution, res2);

		ostream = new ByteArrayOutputStream();
		Utils.serialize(res2, ostream);
		String content2 = new String(ostream.toByteArray(), "UTF-8"); //$NON-NLS-1$
		System.out.println(content2);
		assertEquals(content, content2);
	}
}

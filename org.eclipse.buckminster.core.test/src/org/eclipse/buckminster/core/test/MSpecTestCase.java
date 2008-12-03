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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.parser.IParserFactory;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.Path;

/**
 * Abstract test case that will materialize a workspace with the test components build_a, build_b, build_c, and build_d.
 * 
 * @author Thomas Hallgren
 */
public class MSpecTestCase extends TestCase
{
	public MSpecTestCase()
	{
		super();
	}

	public MSpecTestCase(String name)
	{
		super(name);
	}

	@Override
	public void setUp() throws Exception
	{
	}

	public void testSerialize() throws Exception
	{
		MaterializationSpecBuilder builder = new MaterializationSpecBuilder();
		builder.setName("Buckys download spec"); //$NON-NLS-1$
		builder.setURL("http://www.eclipse.org/buckminster/samples/queries/build_a.cquery"); //$NON-NLS-1$
		builder.setShortDesc("Buckminster materialization spec"); //$NON-NLS-1$
		builder.setInstallLocation(Path.fromOSString(System.getProperty("user.home") //$NON-NLS-1$
				+ System.getProperty("file.separator") + "bucky")); //$NON-NLS-1$ //$NON-NLS-2$
		builder.setMaterializerID("workspace"); //$NON-NLS-1$

		MaterializationNodeBuilder node = builder.addNodeBuilder();
		node.setNamePattern(Pattern.compile(".*")); //$NON-NLS-1$
		node.setComponentTypeID("plugin"); //$NON-NLS-1$
		node.setConflictResolution(ConflictResolution.KEEP);
		node.setInstallLocation(Path.fromPortableString("plugins")); //$NON-NLS-1$

		node = builder.addNodeBuilder();
		node.setNamePattern(Pattern.compile(".*")); //$NON-NLS-1$
		node.setComponentTypeID("feature"); //$NON-NLS-1$
		node.setConflictResolution(ConflictResolution.REPLACE);
		node.setInstallLocation(Path.fromPortableString("features")); //$NON-NLS-1$

		node = builder.addNodeBuilder();
		node.setNamePattern(Pattern.compile("i.don.not.want.this.one")); //$NON-NLS-1$
		node.setComponentTypeID("plugin"); //$NON-NLS-1$
		node.setExclude(true);

		MaterializationSpec spec = new MaterializationSpec(builder);

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		Utils.serialize(spec, byteOut);

		byte[] image = byteOut.toByteArray();
		System.out.println(new String(image, "UTF8")); //$NON-NLS-1$

		IParserFactory pf = CorePlugin.getDefault().getParserFactory();
		ByteArrayInputStream byteIn = new ByteArrayInputStream(image);
		IParser<MaterializationSpec> parser = pf.getMaterializationSpecParser(true);

		MaterializationSpec spec2 = parser.parse("byte image", byteIn); //$NON-NLS-1$

		byteOut = new ByteArrayOutputStream();
		Utils.serialize(spec2, byteOut);

		byte[] image2 = byteOut.toByteArray();
		assertTrue(Arrays.equals(image, image2));
	}
}

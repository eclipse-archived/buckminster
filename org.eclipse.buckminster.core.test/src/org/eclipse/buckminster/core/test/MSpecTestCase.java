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
 * Abstract test case that will materialize a workspace with the
 * test components build_a, build_b, build_c, and build_d.
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
		builder.setName("Buckys download spec");
		builder.setURL("http://www.eclipse.org/buckminster/samples/queries/build_a.cquery");
		builder.setShortDesc("Buckminster materialization spec");
		builder.setInstallLocation(Path.fromOSString(System.getProperty("user.home") + System.getProperty("file.separator") + "bucky"));
		builder.setMaterializerID("workspace");

		MaterializationNodeBuilder node = builder.addNodeBuilder();
		node.setNamePattern(Pattern.compile(".*"));
		node.setComponentTypeID("plugin");
		node.setConflictResolution(ConflictResolution.KEEP);
		node.setInstallLocation(Path.fromPortableString("plugins"));

		node = builder.addNodeBuilder();
		node.setNamePattern(Pattern.compile(".*"));
		node.setComponentTypeID("feature");
		node.setConflictResolution(ConflictResolution.REPLACE);
		node.setInstallLocation(Path.fromPortableString("features"));

		node = builder.addNodeBuilder();
		node.setNamePattern(Pattern.compile("i.don.not.want.this.one"));
		node.setComponentTypeID("plugin");
		node.setExclude(true);

		MaterializationSpec spec = new MaterializationSpec(builder);

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		Utils.serialize(spec, byteOut);
		
		byte[] image = byteOut.toByteArray();
		System.out.println(new String(image, "UTF8"));

		IParserFactory pf = CorePlugin.getDefault().getParserFactory();
		ByteArrayInputStream byteIn = new ByteArrayInputStream(image);
		IParser<MaterializationSpec> parser = pf.getMaterializationSpecParser(true);
		
		MaterializationSpec spec2 = parser.parse("byte image", byteIn);

		byteOut = new ByteArrayOutputStream();
		Utils.serialize(spec2, byteOut);

		byte[] image2 = byteOut.toByteArray();
		assertTrue(Arrays.equals(image, image2));
	}
}

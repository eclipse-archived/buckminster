package org.eclipse.buckminster.core.test.rmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.sax.Utils;

public class BillOfMaterialsTest extends AbstractMnBTestCase
{
	public void testBOM() throws Exception
	{
		BillOfMaterials bom = m_bom;

		// Create an exported graph, i.e. a graph that does not rely
		// on the meta-data storage.
		//
		IParser<BillOfMaterials> parser = CorePlugin.getDefault().getParserFactory().getBillOfMaterialsParser(true);

		// Test serialization/de-serialization
		//
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Utils.serialize(bom, out);
		byte[] image = out.toByteArray();
		System.out.write(image);

		ByteArrayInputStream in = new ByteArrayInputStream(image);
		BillOfMaterials parsed = parser.parse("byte image", in);

		assertEquals(parsed, bom);
		Utils.serialize(parsed, System.out);
	}
}

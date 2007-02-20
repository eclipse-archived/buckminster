package org.eclipse.buckminster.core.test.rmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.ExportedBillOfMaterials;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.sax.Utils;

public class BillOfMaterialsTest extends AbstractMnBTestCase
{
	public void testBOM() throws Exception
	{
		BillOfMaterials bom = m_bom;
		bom.store();
		
		// Create an exported graph, i.e. a graph that does not rely
		// on the meta-data storage.
		//
		IParser<BillOfMaterials> parser = CorePlugin.getDefault().getParserFactory().getBillOfMaterialsParser(true);
		ExportedBillOfMaterials exported = bom.exportGraph();
		
		// Test serialization/de-serialization
		//
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Utils.serialize(exported, out);
		byte[] image = out.toByteArray();
		System.out.write(image);

		ByteArrayInputStream in = new ByteArrayInputStream(image);
		BillOfMaterials parsed = parser.parse("byte image", in);
		parsed = BillOfMaterials.importGraph((ExportedBillOfMaterials)parsed);

		assertEquals(parsed, bom);
		exported = parsed.exportGraph();
		Utils.serialize(exported, System.out);
	}
}

package org.eclipse.buckminster.core.test.rmap;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.ExportedBillOfMaterials;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.test.AbstractTestCase;
import org.eclipse.buckminster.sax.Utils;

public class ImportBOMTest extends AbstractTestCase
{
	public void testBOM() throws Exception
	{
		// Create an exported graph, i.e. a graph that does not rely
		// on the meta-data storage.
		//
		InputStream in = getClass().getResourceAsStream("exported.bom");
		IParser<BillOfMaterials> parser = CorePlugin.getDefault().getParserFactory().getBillOfMaterialsParser(true);
		ExportedBillOfMaterials exported = (ExportedBillOfMaterials)parser.parse("exported.bom", in);
		in.close();

		BillOfMaterials bom = BillOfMaterials.importGraph(exported);
		exported = bom.exportGraph();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Utils.serialize(exported, out);
	}
}

/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.test.cspec;

import java.io.InputStream;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.test.AbstractTestCase;
import org.eclipse.buckminster.sax.Utils;

/**
 * @author Thomas Hallgren
 * 
 */
public class CSpecParserTest extends AbstractTestCase {
	public void testParser() throws Exception {
		IParser<CSpec> parser = this.getPlugin().getParserFactory().getCSpecParser(true);
		InputStream input = getClass().getResourceAsStream("/testData/cspecs/test.cspec"); //$NON-NLS-1$
		CSpec cspec = parser.parse("test.cspec", input); //$NON-NLS-1$
		input.close();
		Documentation doc = cspec.getDocumentation();
		System.out.println("Documentation = '" + doc + '\''); //$NON-NLS-1$
		Utils.serialize(doc, System.out);
		Utils.serialize(cspec, System.out);
	}
}

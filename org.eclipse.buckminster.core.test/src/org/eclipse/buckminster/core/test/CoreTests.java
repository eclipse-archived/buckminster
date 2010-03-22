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

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.buckminster.core.test.command.CommandsTest;
import org.eclipse.buckminster.core.test.cspec.CSpecBuilderTest;
import org.eclipse.buckminster.core.test.cspec.CSpecParserTest;
import org.eclipse.buckminster.core.test.property.PropertyFormatTest;
import org.eclipse.buckminster.core.test.rmap.RMapTest;

/**
 * @author thhal
 */
public class CoreTests {
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.buckminster.core"); //$NON-NLS-1$
		suite.addTestSuite(SimpleLoaderTest.class);
		suite.addTestSuite(CommandsTest.class);
		suite.addTestSuite(CSpecBuilderTest.class);
		suite.addTestSuite(CSpecParserTest.class);
		suite.addTestSuite(PropertyFormatTest.class);
		suite.addTestSuite(RMapTest.class);
		return suite;
	}
}

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

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.test.cspec.CSpecBuilderTestCase;
import org.eclipse.buckminster.core.test.property.PropertyFormatTestCase;
import org.eclipse.buckminster.core.test.rmap.RMapTestCase;
import org.eclipse.buckminster.core.test.version.VersionTestCase;

/**
 * @author thhal
 */
public class CoreTests
{
	public static Test suite()
	{
		TestSuite suite = new TestSuite("Test for org.eclipse.buckminster.core"); //$NON-NLS-1$
		System.out.println(CorePlugin.getDefault().getStateLocation().toOSString());
		suite.addTestSuite(PropertyFormatTestCase.class);
		suite.addTestSuite(CSpecBuilderTestCase.class);
		suite.addTestSuite(RMapTestCase.class);
		suite.addTestSuite(VersionTestCase.class);
		return suite;
	}
}

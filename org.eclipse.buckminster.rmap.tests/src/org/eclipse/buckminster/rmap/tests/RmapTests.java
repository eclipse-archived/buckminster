/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>rmap</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class RmapTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new RmapTests("rmap Tests");
		suite.addTestSuite(DocumentRootTest.class);
		suite.addTestSuite(LocatorTest.class);
		suite.addTestSuite(ProviderTest.class);
		suite.addTestSuite(RedirectTest.class);
		suite.addTestSuite(ResourceMapTest.class);
		suite.addTestSuite(TransformTest.class);
		suite.addTestSuite(URIMatcherTest.class);
		suite.addTestSuite(VersionConverterTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RmapTests(String name) {
		super(name);
	}

} //RmapTests

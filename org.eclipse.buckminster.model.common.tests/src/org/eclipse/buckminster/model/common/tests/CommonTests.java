/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test suite for the '<em><b>common</b></em>'
 * package. <!-- end-user-doc -->
 * 
 * @generated
 */
public class CommonTests extends TestSuite {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new CommonTests("common Tests");
		suite.addTestSuite(ComponentIdentifierTest.class);
		suite.addTestSuite(ComponentRequestTest.class);
		suite.addTestSuite(ConstantTest.class);
		suite.addTestSuite(DocumentationTest.class);
		suite.addTestSuite(FormatTest.class);
		suite.addTestSuite(MatchTest.class);
		suite.addTestSuite(PropertyRefTest.class);
		suite.addTestSuite(ReplaceTest.class);
		suite.addTestSuite(RxAssemblyTest.class);
		suite.addTestSuite(RxGroupTest.class);
		suite.addTestSuite(RxPatternTest.class);
		suite.addTestSuite(SplitTest.class);
		suite.addTestSuite(ToLowerTest.class);
		suite.addTestSuite(ToUpperTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CommonTests(String name) {
		super(name);
	}

} // CommonTests

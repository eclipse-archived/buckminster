/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.tests;

import junit.textui.TestRunner;

import org.eclipse.buckminster.rmap.Locator;
import org.eclipse.buckminster.rmap.RmapFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Locator</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class LocatorTest extends MatcherTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(LocatorTest.class);
	}

	/**
	 * Constructs a new Locator test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocatorTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Locator test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Locator getFixture() {
		return (Locator)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(RmapFactory.eINSTANCE.createLocator());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //LocatorTest

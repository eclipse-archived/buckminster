/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.RxGroup;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Rx Group</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.RxGroup#getRxParts() <em>Rx Parts</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class RxGroupTest extends RxPartTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(RxGroupTest.class);
	}

	/**
	 * Constructs a new Rx Group test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RxGroupTest(String name) {
		super(name);
	}


	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.RxGroup#getRxParts()
	 * <em>Rx Parts</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.RxGroup#getRxParts()
	 * @generated NOT
	 */
	public void testGetRxParts() {
	}

	/**
	 * Returns the fixture for this Rx Group test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected RxGroup getFixture() {
		return (RxGroup)fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createRxGroup());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} // RxGroupTest

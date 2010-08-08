/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import java.util.Collections;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.Constant;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Constant</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class ConstantTest extends ValueTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ConstantTest.class);
	}

	/**
	 * Constructs a new Constant test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ConstantTest(String name) {
		super(name);
	}

	@Override
	public void testGetValue__Map() {
		assertEquals("testval", fixture.getValue(Collections.<String, String> emptyMap()));
	}

	/**
	 * Returns the fixture for this Constant test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Constant getFixture() {
		return (Constant) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createConstant());
		((Constant) fixture).setValue("testval");
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} // ConstantTest

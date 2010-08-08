/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import java.util.Map;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Value;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Property Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link java.util.Map.Entry#getTypedValue() <em>Value</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PropertyElementTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PropertyElementTest.class);
	}

	/**
	 * The fixture for this Property Element test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Map.Entry<String, Value> fixture = null;

	/**
	 * Constructs a new Property Element test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PropertyElementTest(String name) {
		super(name);
	}

	/**
	 * Tests the '{@link java.util.Map.Entry#getTypedValue() <em>Value</em>}'
	 * feature getter. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.util.Map.Entry#getTypedValue()
	 * @generated
	 */
	public void testGetTypedValue() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '
	 * {@link java.util.Map.Entry#setTypedValue(org.eclipse.buckminster.model.common.Value)
	 * <em>Value</em>}' feature setter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see java.util.Map.Entry#setTypedValue(org.eclipse.buckminster.model.common.Value)
	 * @generated
	 */
	public void testSetTypedValue() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Returns the fixture for this Property Element test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Map.Entry<String, Value> getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Property Element test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(Map.Entry<String, Value> fixture) {
		this.fixture = fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void setUp() throws Exception {
		setFixture((Map.Entry<String, Value>) CommonFactory.eINSTANCE.create(CommonPackage.Literals.PROPERTY_ELEMENT));
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

} // PropertyElementTest

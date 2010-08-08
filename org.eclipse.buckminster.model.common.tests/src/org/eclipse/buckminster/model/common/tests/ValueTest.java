/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import junit.framework.TestCase;

import org.eclipse.buckminster.model.common.Value;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Value</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.Value#getValue(java.util.Map)
 * <em>Get Value</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.Value#isMultiValued() <em>Is
 * Multi Valued</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class ValueTest extends TestCase {

	/**
	 * The fixture for this Value test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected Value fixture = null;

	/**
	 * Constructs a new Value test case with the given name. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ValueTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.Value#getValue(java.util.Map)
	 * <em>Get Value</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.model.common.Value#getValue(java.util.Map)
	 * @generated NOT
	 */
	public abstract void testGetValue__Map();

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.Value#isMultiValued()
	 * <em>Is Multi Valued</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.Value#isMultiValued()
	 * @generated NOT
	 */
	public void testIsMultiValued() {
		assertFalse(fixture.isMultiValued());
	}

	/**
	 * Returns the fixture for this Value test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Value getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Value test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(Value fixture) {
		this.fixture = fixture;
	}

} // ValueTest

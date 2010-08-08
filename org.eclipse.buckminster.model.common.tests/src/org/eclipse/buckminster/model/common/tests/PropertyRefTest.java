/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import java.util.Collections;
import java.util.Map;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.PropertyRef;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Property Ref</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class PropertyRefTest extends ValueTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PropertyRefTest.class);
	}

	/**
	 * Constructs a new Property Ref test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PropertyRefTest(String name) {
		super(name);
	}

	@Override
	public void testGetValue__Map() {
		Map<String, String> properties = Collections.singletonMap("osgi.os", "linux");
		assertEquals("linux", fixture.getValue(properties));
	}

	/**
	 * Returns the fixture for this Property Ref test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected PropertyRef getFixture() {
		return (PropertyRef) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createPropertyRef());
		((PropertyRef) fixture).setKey("osgi.os");
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

} // PropertyRefTest

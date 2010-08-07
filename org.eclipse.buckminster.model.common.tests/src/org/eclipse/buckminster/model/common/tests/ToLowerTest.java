/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import java.util.Collections;
import java.util.List;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.Constant;
import org.eclipse.buckminster.model.common.Replace;
import org.eclipse.buckminster.model.common.ToLower;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>To Lower</b></em>'. <!-- end-user-doc -->
 * @generated
 */
public class ToLowerTest extends ValueFilterTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ToLowerTest.class);
	}

	/**
	 * Constructs a new To Lower test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ToLowerTest(String name) {
		super(name);
	}

	@Override
	public void testGetValue__Map() {
		assertEquals("someuppercase", fixture.getValue(Collections.<String,String>emptyMap()));
	}

	@Override
	public void testGetValues__Map() {
		List<String> result = getFixture().getValues(Collections.<String, String> emptyMap());
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("someuppercase", result.get(0));
	}

	@Override
	public void testGetValues() {
		assertEquals(1, ((ToLower) fixture).getValues().size());
	}

	/**
	 * Returns the fixture for this To Lower test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ToLower getFixture() {
		return (ToLower)fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		CommonFactory factory = CommonFactory.eINSTANCE;
		setFixture(factory.createToLower());
		Constant c = factory.createConstant();
		c.setValue("SomeUpperCase");
		((ToLower)fixture).getValues().add(c);
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

} // ToLowerTest

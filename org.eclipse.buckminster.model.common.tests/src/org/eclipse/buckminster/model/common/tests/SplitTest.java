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
import org.eclipse.buckminster.model.common.Split;
import org.eclipse.buckminster.model.common.SplitType;
import org.eclipse.buckminster.model.common.Value;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Split</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.Split#getCompiledPattern()
 * <em>Compiled Pattern</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class SplitTest extends ValueFilterTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(SplitTest.class);
	}

	/**
	 * Constructs a new Split test case with the given name. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public SplitTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.Split#getCompiledPattern()
	 * <em>Compiled Pattern</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.Split#getCompiledPattern()
	 * @generated NOT
	 */
	public void testGetCompiledPattern() {
	}

	@Override
	public void testGetValue__Map() {
		assertEquals("test.component_one1.23.qualifier", getFixture().getValue(Collections.<String, String> emptyMap()));
	}

	@Override
	public void testGetValues() {
		List<Value> values = getFixture().getValues();
		assertNotNull(values);
		assertEquals(1, values.size());
	}

	@Override
	public void testGetValues__Map() {
		List<String> result = getFixture().getValues(Collections.<String, String> emptyMap());
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("test.component_one", result.get(0));
		assertEquals("1.23.qualifier", result.get(1));
	}

	@Override
	public void testIsMultiValued() {
		assertTrue(fixture.isMultiValued());
	}

	/**
	 * Returns the fixture for this Split test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Split getFixture() {
		return (Split) fixture;
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
		Split split = factory.createSplit();
		split.setStyle(SplitType.GROUPS);
		split.setPattern(".*/([a-zA-Z0-9._-]+)_([0-9]+(?:\\.[0-9]+)?(?:\\.[0-9]+)?(?:\\.[a-zA-Z0-9_-]+)?)\\.tar\\.gz");
		Constant constant = factory.createConstant();
		constant.setValue("http://www.test.org/downloads/test.component_one_1.23.qualifier.tar.gz");
		split.getValues().add(constant);
		setFixture(split);
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

} // SplitTest

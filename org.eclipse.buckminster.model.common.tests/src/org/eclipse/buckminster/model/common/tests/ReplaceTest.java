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

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Replace</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.Replace#getCompiledPattern() <em>Compiled Pattern</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ReplaceTest extends ValueFilterTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ReplaceTest.class);
	}

	/**
	 * Constructs a new Replace test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ReplaceTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.Replace#getCompiledPattern()
	 * <em>Compiled Pattern</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.Replace#getCompiledPattern()
	 * @generated NOT
	 */
	public void testGetCompiledPattern() {
		assertEquals("(foo)\\.(bar)", ((Replace) fixture).getCompiledPattern().toString());
	}

	@Override
	public void testGetValue__Map() {
		assertEquals("foos/bars", fixture.getValue(Collections.<String, String> emptyMap()));
	}

	@Override
	public void testGetValues__Map() {
		List<String> result = getFixture().getValues(Collections.<String, String> emptyMap());
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("foos/bars", result.get(0));
	}

	@Override
	public void testGetValues() {
		assertEquals(1, ((Replace) fixture).getValues().size());
	}

	/**
	 * Returns the fixture for this Replace test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Replace getFixture() {
		return (Replace)fixture;
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
		setFixture(factory.createReplace());
		((Replace) fixture).setPattern("(foo)\\.(bar)");
		((Replace) fixture).setReplacement("$1s/$2s");
		Constant constant = factory.createConstant();
		constant.setValue("foo.bar");
		((Replace) fixture).getValues().add(constant);
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

} // ReplaceTest

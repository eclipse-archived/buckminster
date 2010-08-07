/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.Documentation;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Documentation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.Documentation#getAny() <em>Any</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class DocumentationTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(DocumentationTest.class);
	}

	/**
	 * The fixture for this Documentation test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Documentation fixture = null;

	/**
	 * Constructs a new Documentation test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DocumentationTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.Documentation#getAny()
	 * <em>Any</em>}' feature getter. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.model.common.Documentation#getAny()
	 * @generated NOT
	 */
	public void testGetAny() {
	}

	/**
	 * Returns the fixture for this Documentation test case.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected Documentation getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Documentation test case.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Documentation fixture) {
		this.fixture = fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createDocumentation());
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

} // DocumentationTest

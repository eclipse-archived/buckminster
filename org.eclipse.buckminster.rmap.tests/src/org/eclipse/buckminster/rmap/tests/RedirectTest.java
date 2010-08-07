/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.tests;

import junit.textui.TestRunner;

import org.eclipse.buckminster.rmap.Redirect;
import org.eclipse.buckminster.rmap.RmapFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Redirect</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class RedirectTest extends MatcherTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(RedirectTest.class);
	}

	/**
	 * Constructs a new Redirect test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RedirectTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Redirect test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Redirect getFixture() {
		return (Redirect)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(RmapFactory.eINSTANCE.createRedirect());
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

} //RedirectTest

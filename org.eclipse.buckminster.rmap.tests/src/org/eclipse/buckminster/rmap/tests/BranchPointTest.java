/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.buckminster.rmap.BranchPoint;
import org.eclipse.buckminster.rmap.RmapFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Branch Point</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class BranchPointTest extends TestCase {

	/**
	 * The fixture for this Branch Point test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BranchPoint fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(BranchPointTest.class);
	}

	/**
	 * Constructs a new Branch Point test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BranchPointTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Branch Point test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(BranchPoint fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Branch Point test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BranchPoint getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(RmapFactory.eINSTANCE.createBranchPoint());
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

} //BranchPointTest

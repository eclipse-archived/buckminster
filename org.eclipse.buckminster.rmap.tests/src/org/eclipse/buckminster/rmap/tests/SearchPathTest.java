/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.SearchPath;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Search Path</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class SearchPathTest extends TestCase {

	/**
	 * The fixture for this Search Path test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SearchPath fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(SearchPathTest.class);
	}

	/**
	 * Constructs a new Search Path test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SearchPathTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Search Path test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(SearchPath fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Search Path test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SearchPath getFixture() {
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
		setFixture(RmapFactory.eINSTANCE.createSearchPath());
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

} //SearchPathTest

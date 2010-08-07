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
import org.eclipse.buckminster.model.common.Match;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Match</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.Match#getCompiledPattern() <em>Compiled Pattern</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.Match#match(java.lang.String) <em>Match</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class MatchTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(MatchTest.class);
	}

	/**
	 * The fixture for this Match test case.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected Match fixture = null;

	/**
	 * Constructs a new Match test case with the given name.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	public MatchTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.Match#getCompiledPattern()
	 * <em>Compiled Pattern</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.Match#getCompiledPattern()
	 * @generated NOT
	 */
	public void testGetCompiledPattern() {
		assertEquals("(foo)\\.(bar)", fixture.getCompiledPattern().toString());
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.Match#match(java.lang.String)
	 * <em>Match</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.Match#match(java.lang.String)
	 * @generated NOT
	 */
	public void testMatch__String() {
		assertEquals("foos/bars", fixture.match("foo.bar"));
	}

	/**
	 * Returns the fixture for this Match test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Match getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Match test case.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Match fixture) {
		this.fixture = fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createMatch());
		fixture.setPattern("(foo)\\.(bar)");
		fixture.setReplacement("$1s/$2s");
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

} // MatchTest

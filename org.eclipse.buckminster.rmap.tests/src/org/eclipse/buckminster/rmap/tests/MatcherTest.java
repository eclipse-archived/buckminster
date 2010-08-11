/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.tests;

import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.rmap.Matcher;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Matcher</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.rmap.Matcher#matches(java.lang.String) <em>Matches</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class MatcherTest extends TestCase {

	/**
	 * The fixture for this Matcher test case.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected Matcher fixture = null;

	/**
	 * Constructs a new Matcher test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MatcherTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.Matcher#matches(java.lang.String)
	 * <em>Matches</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Matcher#matches(java.lang.String)
	 * @generated NOT
	 */
	public void testMatches__String() {
		fixture.setPattern((Pattern) EcoreUtil.createFromString(CommonPackage.Literals.PATTERN, "this\\.pattern"));
		assertTrue(fixture.matches("this.pattern"));
	}

	/**
	 * Returns the fixture for this Matcher test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Matcher getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Matcher test case.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Matcher fixture) {
		this.fixture = fixture;
	}

} // MatcherTest

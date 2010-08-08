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
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Component Identifier</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link java.lang.Comparable#compareTo(java.lang.Object) <em>Compare To
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ComponentIdentifierTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ComponentIdentifierTest.class);
	}

	/**
	 * The fixture for this Component Identifier test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComponentIdentifier fixture = null;

	/**
	 * Constructs a new Component Identifier test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ComponentIdentifierTest(String name) {
		super(name);
	}

	/**
	 * Tests the '{@link java.lang.Comparable#compareTo(java.lang.Object)
	 * <em>Compare To</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @generated NOT
	 */
	public void testCompareTo__Object() {
		ComponentIdentifier other = CommonFactory.eINSTANCE.createComponentIdentifier();
		other.setId("org.eclipse.buckminster.model.common.tests");
		other.setType("osgi.bundle");
		other.setVersion(Version.create("1.2.3"));
		assertEquals(fixture, other);
		assertEquals(fixture.hashCode(), other.hashCode());
		assertEquals(0, fixture.compareTo(other));

		other.setVersion(Version.create("1.2.4"));
		assertTrue(fixture.compareTo(other) < 0);

		other.setId("com.cloudsmith.tests");
		assertTrue(fixture.compareTo(other) > 0);

		other.setId("org.eclipse.buckminster.model.common.tests");
		other.setVersion(Version.create("1.2.3"));
		other.setType("osgi.bundle");
		assertEquals(fixture, other);

		other.setType("eclipse.feature");
		assertTrue(fixture.compareTo(other) > 0);
	}

	/**
	 * Returns the fixture for this Component Identifier test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComponentIdentifier getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Component Identifier test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(ComponentIdentifier fixture) {
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
		setFixture(CommonFactory.eINSTANCE.createComponentIdentifier());
		fixture.setId("org.eclipse.buckminster.model.common.tests");
		fixture.setType("osgi.bundle");
		fixture.setVersion(Version.create("1.2.3"));
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

} // ComponentIdentifierTest

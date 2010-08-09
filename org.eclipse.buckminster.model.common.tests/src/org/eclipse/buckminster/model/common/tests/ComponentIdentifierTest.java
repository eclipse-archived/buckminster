/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import java.util.Map;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonConstants;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Component Identifier</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.ComponentIdentifier#matches(org.eclipse.buckminster.model.common.ComponentIdentifier)
 * <em>Matches</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ComponentIdentifierTest extends ComponentNameTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ComponentIdentifierTest.class);
	}

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
	@Override
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

	@Override
	public void testGetProperties() {
		Map<String, String> map = getFixture().getProperties();
		assertNotNull(map);
		assertEquals("org.eclipse.buckminster.model.common.tests", map.get(CommonConstants.COMPONENT_NAME));
		assertEquals("osgi.bundle", map.get(CommonConstants.COMPONENT_TYPE));
		assertEquals("1.2.3", map.get(CommonConstants.COMPONENT_VERSION));
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.ComponentIdentifier#matches(org.eclipse.buckminster.model.common.ComponentIdentifier)
	 * <em>Matches</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.model.common.ComponentIdentifier#matches(org.eclipse.buckminster.model.common.ComponentIdentifier)
	 * @generated NOT
	 */
	public void testMatches__ComponentIdentifier() {
		ComponentIdentifier ci = CommonFactory.eINSTANCE.createComponentIdentifier();
		ci.setId("org.eclipse.buckminster.model.common.tests");
		ci.setType("osgi.bundle");
		ci.setVersion(Version.create("1.2.3"));
		assertTrue(getFixture().matches(ci));
		ci.setVersion(null);
		assertTrue(getFixture().matches(ci));
		ci.setType(null);
		assertTrue(getFixture().matches(ci));
		ci.setType("eclipse.feature");
		assertFalse(getFixture().matches(ci));
		ci.setType("osgi.bundle");
		assertTrue(getFixture().matches(ci));
		ci.setVersion(Version.create("1.2.4"));
		assertFalse(getFixture().matches(ci));
		ci.setVersion(Version.create("1.2.3"));
		assertTrue(getFixture().matches(ci));
		ci.setId("org.eclipse.buckminster.model.common.flubb");
		assertFalse(getFixture().matches(ci));
	}

	/**
	 * Returns the fixture for this Component Identifier test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected ComponentIdentifier getFixture() {
		return (ComponentIdentifier) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		ComponentIdentifier ci = CommonFactory.eINSTANCE.createComponentIdentifier();
		ci.setId("org.eclipse.buckminster.model.common.tests");
		ci.setType("osgi.bundle");
		ci.setVersion(Version.create("1.2.3"));
		setFixture(ci);
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

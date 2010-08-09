/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import java.util.Map;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonConstants;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.ComponentName;
import org.eclipse.buckminster.model.common.impl.ComponentNameImpl;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Component Name</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.ComponentName#getProperties()
 * <em>Get Properties</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.ComponentName#toPureComponentName()
 * <em>To Pure Component Name</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.ComponentName#toString(java.lang.StringBuilder)
 * <em>To String</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.ComponentName#matches(org.eclipse.buckminster.model.common.ComponentName)
 * <em>Matches</em>}</li>
 * <li>{@link java.lang.Comparable#compareTo(java.lang.Object) <em>Compare To
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ComponentNameTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ComponentNameTest.class);
	}

	/**
	 * The fixture for this Component Name test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComponentName fixture = null;

	/**
	 * Constructs a new Component Name test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ComponentNameTest(String name) {
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
		ComponentName cn = CommonFactory.eINSTANCE.createComponentName();
		cn.setId("org.eclipse.buckminster.model.common.tests");
		cn.setType("osgi.bundle");
		assertEquals(0, getFixture().compareTo(cn));
		cn.setId("org.eclipse.buckminster.model.common.flubb");
		assertTrue(getFixture().compareTo(cn) > 0);
		cn.setId(null);
		assertTrue(getFixture().compareTo(cn) > 0);
		cn.setId("org.eclipse.buckminster.model.common.tests");
		assertEquals(getFixture(), cn);
		cn.setType("eclipse.feature");
		assertTrue(getFixture().compareTo(cn) > 0);
		cn.setType(null);
		assertTrue(getFixture().compareTo(cn) > 0);

		// Test that component type has higher prio then name
		cn.setId("org.eclipse.buckminster.model.common.flubb"); // less
		cn.setType("unknown"); // greater
		assertTrue(getFixture().compareTo(cn) < 0);

		cn.setId("org.eclipse.buckminster.model.common.uvva"); // greater
		cn.setType("eclipse.feature"); // less
		assertTrue(getFixture().compareTo(cn) > 0);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.ComponentName#getProperties()
	 * <em>Get Properties</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.ComponentName#getProperties()
	 * @generated NOT
	 */
	public void testGetProperties() {
		Map<String, String> map = getFixture().getProperties();
		assertNotNull(map);
		assertEquals("org.eclipse.buckminster.model.common.tests", map.get(CommonConstants.COMPONENT_NAME));
		assertEquals("osgi.bundle", map.get(CommonConstants.COMPONENT_TYPE));
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.ComponentName#matches(org.eclipse.buckminster.model.common.ComponentName)
	 * <em>Matches</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.model.common.ComponentName#matches(org.eclipse.buckminster.model.common.ComponentName)
	 * @generated NOT
	 */
	public void testMatches__ComponentName() {
		ComponentName cn = CommonFactory.eINSTANCE.createComponentName();
		cn.setId("org.eclipse.buckminster.model.common.tests");
		cn.setType("osgi.bundle");
		assertTrue(getFixture().matches(cn));
		cn.setType(null);
		assertTrue(getFixture().matches(cn));
		cn.setType("eclipse.feature");
		assertFalse(getFixture().matches(cn));
		cn.setType("osgi.bundle");
		assertTrue(getFixture().matches(cn));
		cn.setId("org.eclipse.buckminster.model.common.flubb");
		assertFalse(getFixture().matches(cn));
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.ComponentName#toPureComponentName()
	 * <em>To Pure Component Name</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.ComponentName#toPureComponentName()
	 * @generated NOT
	 */
	public void testToPureComponentName() {
		ComponentName cn = getFixture().toPureComponentName();
		assertEquals(ComponentNameImpl.class, cn.getClass());
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.ComponentName#toString(java.lang.StringBuilder)
	 * <em>To String</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.model.common.ComponentName#toString(java.lang.StringBuilder)
	 * @generated NOT
	 */
	public void testToString__StringBuilder() {
		StringBuilder result = new StringBuilder();
		getFixture().toString(result);
		assertTrue(result.toString().startsWith("org.eclipse.buckminster.model.common.tests"));
	}

	/**
	 * Returns the fixture for this Component Name test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComponentName getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Component Name test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(ComponentName fixture) {
		this.fixture = fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createComponentName());
		getFixture().setId("org.eclipse.buckminster.model.common.tests");
		getFixture().setType("osgi.bundle");
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

} // ComponentNameTest

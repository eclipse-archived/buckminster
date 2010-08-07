/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import java.util.Collections;
import java.util.Map;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonConstants;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Component Request</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.ComponentRequest#designates(org.eclipse.buckminster.model.common.ComponentIdentifier) <em>Designates</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.ComponentRequest#isOptional() <em>Is Optional</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.ComponentRequest#isEnabled(java.util.Map) <em>Is Enabled</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ComponentRequestTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ComponentRequestTest.class);
	}

	/**
	 * The fixture for this Component Request test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentRequest fixture = null;

	/**
	 * Constructs a new Component Request test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ComponentRequestTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.ComponentRequest#designates(org.eclipse.buckminster.model.common.ComponentIdentifier)
	 * <em>Designates</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.ComponentRequest#designates(org.eclipse.buckminster.model.common.ComponentIdentifier)
	 * @generated NOT
	 */
	public void testDesignates__ComponentIdentifier() {
		ComponentIdentifier cid = CommonFactory.eINSTANCE.createComponentIdentifier();
		cid.setId("org.eclipse.buckminster.model.common.tests");
		cid.setType("osgi.bundle");
		cid.setVersion(Version.create("1.2.3"));
		assertTrue(fixture.designates(cid));
		cid.setVersion(Version.create("1.4.0"));
		assertFalse(fixture.designates(cid));
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.ComponentRequest#isEnabled(java.util.Map)
	 * <em>Is Enabled</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.ComponentRequest#isEnabled(java.util.Map)
	 * @generated NOT
	 */
	public void testIsEnabled__Map() {
		Map<String, String> properties = Collections.singletonMap("osgi.os", "windows");
		assertTrue(fixture.isEnabled(properties));
		fixture.setFilter((Filter) EcoreUtil.createFromString(CommonPackage.Literals.FILTER, "(osgi.os=windows)"));
		assertTrue(fixture.isEnabled(properties));
		fixture.setFilter((Filter) EcoreUtil.createFromString(CommonPackage.Literals.FILTER, "(osgi.os=linux)"));
		assertFalse(fixture.isEnabled(properties));
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.ComponentRequest#isOptional()
	 * <em>Is Optional</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.ComponentRequest#isOptional()
	 * @generated NOT
	 */
	public void testIsOptional() {
		fixture.setFilter((Filter) EcoreUtil.createFromString(CommonPackage.Literals.FILTER, CommonConstants.FILTER_ECLIPSE_P2_OPTIONAL));
		assertTrue(fixture.isOptional());
		fixture.setFilter(null);
		assertFalse(fixture.isOptional());
	}

	/**
	 * Returns the fixture for this Component Request test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComponentRequest getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Component Request test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(ComponentRequest fixture) {
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
		setFixture(CommonFactory.eINSTANCE.createComponentRequest());
		fixture.setId("org.eclipse.buckminster.model.common.tests");
		fixture.setType("osgi.bundle");
		fixture.setRange((VersionRange) EcoreUtil.createFromString(CommonPackage.Literals.VERSION_RANGE, "[1.2.0,1.4.0)"));
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

} // ComponentRequestTest

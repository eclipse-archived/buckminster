/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import java.util.Collections;
import java.util.Map;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonConstants;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.model.common.util.ComponentRequestConflictException;
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
 * <li>
 * {@link org.eclipse.buckminster.model.common.ComponentRequest#appendViewName(java.lang.StringBuilder)
 * <em>Append View Name</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.ComponentRequest#designates(org.eclipse.buckminster.model.common.ComponentIdentifier)
 * <em>Designates</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.ComponentRequest#getViewName()
 * <em>Get View Name</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.ComponentRequest#isEnabled(java.util.Map)
 * <em>Is Enabled</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.ComponentRequest#isOptional()
 * <em>Is Optional</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.ComponentRequest#merge(org.eclipse.buckminster.model.common.ComponentRequest)
 * <em>Merge</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ComponentRequestTest extends ComponentNameTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ComponentRequestTest.class);
	}

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
	 * {@link org.eclipse.buckminster.model.common.ComponentRequest#appendViewName(java.lang.StringBuilder)
	 * <em>Append View Name</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.ComponentRequest#appendViewName(java.lang.StringBuilder)
	 * @generated NOT
	 */
	public void testAppendViewName__StringBuilder() {
		StringBuilder bld = new StringBuilder();
		getFixture().appendViewName(bld);
		assertEquals("org.eclipse.buckminster.model.common.tests:osgi.bundle", bld.toString());
	}

	@Override
	public void testCompareTo__Object() {
		ComponentRequest cr = EcoreUtil.copy(getFixture());
		assertEquals(0, getFixture().compareTo(cr));
		cr.setId("org.eclipse.buckminster.model.common.flubb");
		assertTrue(getFixture().compareTo(cr) > 0);
		cr.setId(null);
		assertTrue(getFixture().compareTo(cr) > 0);
		cr.setId("org.eclipse.buckminster.model.common.tests");
		assertEquals(getFixture(), cr);
		cr.setType("eclipse.feature");
		assertTrue(getFixture().compareTo(cr) > 0);
		cr.setType(null);
		assertTrue(getFixture().compareTo(cr) > 0);

		// Test that component type has higher prio then name
		cr.setId("org.eclipse.buckminster.model.common.flubb"); // less
		cr.setType("unknown"); // greater
		assertTrue(getFixture().compareTo(cr) < 0);

		cr.setId("org.eclipse.buckminster.model.common.uvva"); // greater
		cr.setType("eclipse.feature"); // less
		assertTrue(getFixture().compareTo(cr) > 0);

		cr = EcoreUtil.copy(getFixture());
		cr.setRange(new VersionRange("[1.1.0,1.4.0)"));
		assertTrue(getFixture().compareTo(cr) > 0);
		cr.setRange(new VersionRange("[1.3.0,1.4.0)"));
		assertTrue(getFixture().compareTo(cr) < 0);
		cr.setRange(new VersionRange("[1.2.0,1.3.0)"));
		assertTrue(getFixture().compareTo(cr) > 0);
		cr.setRange(new VersionRange("[1.2.0,1.5.0)"));
		assertTrue(getFixture().compareTo(cr) < 0);
		cr.setRange(new VersionRange("[1.2.0,1.4.0]"));
		assertTrue(getFixture().compareTo(cr) < 0);
		cr.setRange(new VersionRange("(1.2.0,1.4.0)"));
		assertTrue(getFixture().compareTo(cr) < 0);
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
		assertTrue(getFixture().designates(cid));
		cid.setVersion(Version.create("1.4.0"));
		assertFalse(getFixture().designates(cid));
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.ComponentRequest#getViewName()
	 * <em>Get View Name</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.ComponentRequest#getViewName()
	 * @generated NOT
	 */
	public void testGetViewName() {
		assertEquals("org.eclipse.buckminster.model.common.tests:osgi.bundle", getFixture().getViewName());
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
		assertTrue(getFixture().isEnabled(properties));
		getFixture().setFilter((Filter) EcoreUtil.createFromString(CommonPackage.Literals.FILTER, "(osgi.os=windows)"));
		assertTrue(getFixture().isEnabled(properties));
		getFixture().setFilter((Filter) EcoreUtil.createFromString(CommonPackage.Literals.FILTER, "(osgi.os=linux)"));
		assertFalse(getFixture().isEnabled(properties));
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
		getFixture().setFilter((Filter) EcoreUtil.createFromString(CommonPackage.Literals.FILTER, CommonConstants.FILTER_ECLIPSE_P2_OPTIONAL));
		assertTrue(getFixture().isOptional());
		getFixture().setFilter(null);
		assertFalse(getFixture().isOptional());
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.ComponentRequest#merge(org.eclipse.buckminster.model.common.ComponentRequest)
	 * <em>Merge</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.ComponentRequest#merge(org.eclipse.buckminster.model.common.ComponentRequest)
	 * @generated NOT
	 */
	public void testMerge__ComponentRequest() {
		ComponentRequest cr = EcoreUtil.copy(getFixture());
		assertTrue(cr == cr.merge(getFixture()));

		// Cannot merge different names
		cr.setId("some.other.id");
		try {
			cr.merge(getFixture());
			fail();
		} catch (ComponentRequestConflictException e) {
		}
		cr.setId(getFixture().getId());

		// Narrower range takes precedence
		cr.setRange(new VersionRange("[1.2.0,1.2.1)"));
		assertTrue(cr == cr.merge(getFixture()));
		cr.setRange(new VersionRange("[1.2.0,1.2.0]"));
		assertTrue(cr == cr.merge(getFixture()));

		// Intersection
		cr.setRange(new VersionRange("[1.3.0,1.5.0)"));
		assertEquals(cr.merge(getFixture()).getRange().toString(), "[1.3.0,1.4.0)");

		// Cannot merge unless ranges intersect
		cr.setRange(new VersionRange("[1.1.0,1.2.0)"));
		try {
			cr.merge(getFixture());
			fail();
		} catch (ComponentRequestConflictException e) {
		}
		cr.setRange(getFixture().getRange());

		// Defined type takes precedence
		cr.setType(null);
		assertTrue(getFixture() == cr.merge(getFixture()));

		// Cannot merge different types
		cr.setType("eclipse.feature");
		try {
			cr.merge(getFixture());
			fail();
		} catch (ComponentRequestConflictException e) {
		}
	}

	/**
	 * Returns the fixture for this Component Request test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected ComponentRequest getFixture() {
		return (ComponentRequest) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		ComponentRequest cr = CommonFactory.eINSTANCE.createComponentRequest();
		setFixture(cr);
		cr.setId("org.eclipse.buckminster.model.common.tests");
		cr.setType("osgi.bundle");
		cr.setRange((VersionRange) EcoreUtil.createFromString(CommonPackage.Literals.VERSION_RANGE, "[1.2.0,1.4.0)"));
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

} // ComponentRequestTest

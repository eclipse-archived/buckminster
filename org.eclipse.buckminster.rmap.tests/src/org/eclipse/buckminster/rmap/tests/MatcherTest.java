/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.tests;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.ComponentName;
import org.eclipse.buckminster.rmap.Matcher;
import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Matcher</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.Matcher#getComponentTypesAttr() <em>
 * Component Types Attr</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.rmap.Matcher#matches(org.eclipse.buckminster.model.common.ComponentName, java.util.Map)
 * <em>Matches</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Matcher#getResourceMap() <em>Get
 * Resource Map</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class MatcherTest extends TestCase {

	/**
	 * The fixture for this Matcher test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
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
	 * {@link org.eclipse.buckminster.rmap.Matcher#getComponentTypesAttr()
	 * <em>Component Types Attr</em>}' feature getter. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Matcher#getComponentTypesAttr()
	 * @generated NOT
	 */
	public void testGetComponentTypesAttr() {
		List<String> ctypes = getFixture().getComponentTypes();
		ctypes.add("osgi.bundle");
		ctypes.add("buckminster");
		assertEquals("osgi.bundle,buckminster", getFixture().getComponentTypesAttr());
	}

	/**
	 * Tests the '{@link org.eclipse.buckminster.rmap.Matcher#getResourceMap()
	 * <em>Get Resource Map</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Matcher#getResourceMap()
	 * @generated NOT
	 */
	public void testGetResourceMap() {
		ResourceMap rmap = RmapFactory.eINSTANCE.createResourceMap();
		rmap.getMatchers().add(getFixture());
		assertTrue(getFixture().getResourceMap() == rmap);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.Matcher#matches(org.eclipse.buckminster.model.common.ComponentName, java.util.Map)
	 * <em>Matches</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Matcher#matches(org.eclipse.buckminster.model.common.ComponentName,
	 *      java.util.Map)
	 * @generated NOT
	 */
	public void testMatches__ComponentName_Map() {
		ComponentName cn = CommonFactory.eINSTANCE.createComponentName();
		cn.setId("this.pattern");
		fixture.setPattern((Pattern) EcoreUtil.createFromString(CommonPackage.Literals.PATTERN, "this\\.pattern"));
		assertTrue(fixture.matches(cn, Collections.<String, String> emptyMap()));
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.Matcher#setComponentTypesAttr(java.lang.String)
	 * <em>Component Types Attr</em>}' feature setter. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Matcher#setComponentTypesAttr(java.lang.String)
	 * @generated NOT
	 */
	public void testSetComponentTypesAttr() {
		getFixture().setComponentTypesAttr("eclipse.feature,osgi.bundle");
		List<String> ctypes = getFixture().getComponentTypes();
		assertEquals(2, ctypes.size());
		assertEquals("eclipse.feature", ctypes.get(0));
		assertEquals("osgi.bundle", ctypes.get(1));
	}

	/**
	 * Returns the fixture for this Matcher test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Matcher getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Matcher test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(Matcher fixture) {
		this.fixture = fixture;
	}

} // MatcherTest

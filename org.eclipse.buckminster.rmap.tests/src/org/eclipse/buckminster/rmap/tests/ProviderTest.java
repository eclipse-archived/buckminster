/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.tests;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.model.common.PropertyRef;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.RmapConstants;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.impl.ProviderImpl;
import org.eclipse.buckminster.rmap.util.RmapResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Provider</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.rmap.Provider#getComponentTypesAttr() <em>Component Types Attr</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.Provider#isSource() <em>Source</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.Provider#isMutable() <em>Mutable</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.rmap.Provider#getURI(java.util.Map) <em>Get URI</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ProviderTest extends TestCase {

	/**
	 * The fixture for this Provider test case.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected Provider fixture = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ProviderTest.class);
	}

	/**
	 * Constructs a new Provider test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ProviderTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Provider test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Provider fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Provider test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Provider getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(RmapFactory.eINSTANCE.createProvider());
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

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.Provider#getComponentTypesAttr()
	 * <em>Component Types Attr</em>}' feature getter. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Provider#getComponentTypesAttr()
	 * @generated NOT
	 */
	public void testGetComponentTypesAttr() {
		List<String> ctypes = getFixture().getComponentTypes();
		ctypes.add("osgi.bundle");
		ctypes.add("buckminster");
		assertEquals("osgi.bundle,buckminster", getFixture()
				.getComponentTypesAttr());
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.Provider#setComponentTypesAttr(java.lang.String)
	 * <em>Component Types Attr</em>}' feature setter. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Provider#setComponentTypesAttr(java.lang.String)
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
	 * Tests the '{@link org.eclipse.buckminster.rmap.Provider#isSource()
	 * <em>Source</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Provider#isSource()
	 * @generated NOT
	 */
	public void testIsSource() {
		assertTrue(getFixture().isSource());
		getFixture().getProperties().put(RmapConstants.IS_SOURCE, "false");
		assertFalse(getFixture().isSource());
	}

	/**
	 * Tests that the provider can be loaded using an URI and a fragment
	 */
	public void testLoadResource() {
		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
						new RmapResourceFactoryImpl());

		resourceSet.getPackageRegistry().put(CommonPackage.eNS_URI,
				CommonPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(RmapPackage.eNS_URI,
				RmapPackage.eINSTANCE);

		URI uri = URI.createFileURI(RmapTests.getTestData("urimatcher.rmap").getAbsolutePath()).appendFragment("//@searchPaths[name='default']/@providers.0");
		Resource resource = resourceSet.getResource(uri, true);
		Object provider = resource.getEObject(uri.fragment());
		assertNotNull(provider);
		assertEquals(ProviderImpl.class, provider.getClass());
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.Provider#setSource(boolean)
	 * <em>Source</em>}' feature setter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Provider#setSource(boolean)
	 * @generated NOT
	 */
	public void testSetSource() {
		Map<String, String> props = getFixture().getProperties();
		assertFalse(props.containsKey(RmapConstants.IS_SOURCE));
		getFixture().setSource(false);
		assertTrue(props.containsKey(RmapConstants.IS_SOURCE));
		getFixture().setSource(true);
		assertFalse(props.containsKey(RmapConstants.IS_SOURCE));
	}

	/**
	 * Tests the '{@link org.eclipse.buckminster.rmap.Provider#isMutable()
	 * <em>Mutable</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Provider#isMutable()
	 * @generated NOT
	 */
	public void testIsMutable() {
		assertTrue(getFixture().isMutable());
		getFixture().getProperties().put(RmapConstants.IS_MUTABLE, "false");
		assertFalse(getFixture().isMutable());
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.Provider#setMutable(boolean)
	 * <em>Mutable</em>}' feature setter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Provider#setMutable(boolean)
	 * @generated NOT
	 */
	public void testSetMutable() {
		Map<String, String> props = getFixture().getProperties();
		assertFalse(props.containsKey(RmapConstants.IS_MUTABLE));
		getFixture().setMutable(false);
		assertTrue(props.containsKey(RmapConstants.IS_MUTABLE));
		getFixture().setMutable(true);
		assertFalse(props.containsKey(RmapConstants.IS_MUTABLE));
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.Provider#getURI(java.util.Map)
	 * <em>Get URI</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Provider#getURI(java.util.Map)
	 * @generated NOT
	 */
	public void testGetURI__Map() {
		PropertyRef pref = CommonFactory.eINSTANCE.createPropertyRef();
		pref.setKey("buckminster.component");
		Format format = CommonFactory.eINSTANCE.createFormat();
		format.setFormat("http://test.org/components/{0}.jar");
		format.getValues().add(pref);
		getFixture().setURI(format);
		assertEquals(
				"http://test.org/components/testing.jar",
				getFixture().getURI(
						Collections.singletonMap("buckminster.component",
								"testing")));
	}

} // ProviderTest

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.tests;

import java.net.URL;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.Constant;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.rmap.Locator;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.SearchPath;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Resource Map</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.ResourceMap#getMatchers() <em>
 * Matchers</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.ResourceMap#getContextURL() <em>Get
 * Context URL</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ResourceMapTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ResourceMapTest.class);
	}

	/**
	 * The fixture for this Resource Map test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected ResourceMap fixture = null;

	/**
	 * Constructs a new Resource Map test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceMapTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.ResourceMap#getContextURL()
	 * <em>Get Context URL</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.ResourceMap#getContextURL()
	 * @generated NOT
	 */
	public void testGetContextURL() throws Exception {
		assertNull(fixture.getContextURL());
		ResourceMap rmap = RmapTests.loadTestResourceMap("urimatcher.rmap");
		URL contextURL = rmap.getContextURL();
		assertNotNull(contextURL);
		String path = contextURL.getPath();
		assertNotNull(path);
		assertTrue(path.endsWith("/urimatcher.rmap"));
		Provider p = rmap.getSearchPaths().get(0).getProviders().get(0);
		System.out.println(rmap.eResource().getURIFragment(p));
	}

	/**
	 * Tests the '{@link org.eclipse.buckminster.rmap.ResourceMap#getMatchers()
	 * <em>Matchers</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.ResourceMap#getMatchers()
	 * @generated NOT
	 */
	public void testGetMatchers() {
		assertFalse(fixture.getMatchers().isEmpty());
	}

	/**
	 * Returns the fixture for this Resource Map test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ResourceMap getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Resource Map test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(ResourceMap fixture) {
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
		RmapFactory factory = RmapFactory.eINSTANCE;
		setFixture(factory.createResourceMap());
		SearchPath searchPath = factory.createSearchPath();
		searchPath.setName("default");
		fixture.getSearchPaths().add(searchPath);
		Locator locator = factory.createLocator();
		locator.setSearchPath(searchPath);
		fixture.getMatchers().add(locator);
		Provider provider = factory.createProvider();
		searchPath.getProviders().add(provider);
		provider.setReaderType("p2");
		provider.getComponentTypes().add("eclipse.feature");
		provider.getComponentTypes().add("osgi.bundle");
		Format uri = CommonFactory.eINSTANCE.createFormat();
		provider.setURI(uri);
		uri.setFormat("{0}/tools/buckminster/updates-3.6");
		Constant constant = CommonFactory.eINSTANCE.createConstant();
		constant.setValue("http://download.eclipse.org");
		uri.getValues().add(constant);
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

} // ResourceMapTest

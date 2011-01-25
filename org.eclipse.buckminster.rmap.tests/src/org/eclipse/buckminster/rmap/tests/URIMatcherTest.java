/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.tests;

import java.util.Map;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.RxPattern;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.URIMatcher;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>URI Matcher</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.URIMatcher#getComponentType() <em>Get
 * Component Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class URIMatcherTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(URIMatcherTest.class);
	}

	/**
	 * The fixture for this URI Matcher test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected URIMatcher fixture = null;

	/**
	 * Constructs a new URI Matcher test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public URIMatcherTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.URIMatcher#getComponentType()
	 * <em>Get Component Type</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.URIMatcher#getComponentType()
	 * @generated NOT
	 */
	public void testGetComponentType() {
		assertNull(getFixture().getComponentType());

		RmapFactory factory = RmapFactory.eINSTANCE;
		Provider provider = factory.createProvider();
		provider.getComponentTypes().add("osgi.bundle");
		provider.setMatcher(getFixture());
		assertEquals("osgi.bundle", fixture.getComponentType());

		provider.getComponentTypes().add("eclipse.feature");
		assertNull(getFixture().getComponentType());
	}

	public void testGetMatchMap__CharSequence() {
		Map<String, String> result = getFixture().getMatchMap(
				"http://download.eclipse.org/eclipse/downloads/drops/R-3.6-201006080911/download.php?dropFile=eclipse-SDK-3.6-win32.zip");
		assertNotNull(result);
		assertEquals("eclipse-SDK", result.get("tagged.name"));
		assertEquals("3.6", result.get("tagged.version"));
		assertEquals("win32", result.get("tagged.os"));

		result = getFixture().getMatchMap(
				"http://download.eclipse.org/eclipse/downloads/drops/R-3.6-201006080911/download.php?dropFile=eclipse-SDK-3.6-win32-x86_64.zip");
		assertNotNull(result);
		assertEquals("eclipse-SDK", result.get("tagged.name"));
		assertEquals("3.6", result.get("tagged.version"));
		assertEquals("win32", result.get("tagged.os"));
		assertEquals("x86_64", result.get("tagged.arch"));
	}

	/**
	 * Returns the fixture for this URI Matcher test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected URIMatcher getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this URI Matcher test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(URIMatcher fixture) {
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
		CommonFactory commonFactory = CommonFactory.eINSTANCE;
		URIMatcher matcher = factory.createURIMatcher();
		FeatureMap matchers = matcher.getRxPartsGroup();

		RxPattern name = commonFactory.createRxPattern();
		name.setPrefix("http://download.eclipse.org/eclipse/downloads/drops/R-3.6-201006080911/download.php?dropFile=");
		name.setPattern("[a-zA-Z_.-]+");
		matchers.add(RmapPackage.Literals.DOCUMENT_ROOT__NAME, name);

		RxPattern version = commonFactory.createRxPattern();
		version.setPrefix("-");
		version.setPattern("[0-9]+((\\.[0-9]+)+(\\.[a-zA-Z0-9_-]+)?)?");
		matchers.add(RmapPackage.Literals.DOCUMENT_ROOT__VERSION, version);

		RxPattern os = commonFactory.createRxPattern();
		os.setPrefix("-");
		os.setPattern("win32|linux|solaris|hpux|aix|macosx");
		matchers.add(RmapPackage.Literals.DOCUMENT_ROOT__OS, os);

		RxPattern ws = commonFactory.createRxPattern();
		ws.setPrefix("-");
		ws.setOptional(true);
		ws.setPattern("win32|gtk|motif|cocoa|carbon");
		matchers.add(RmapPackage.Literals.DOCUMENT_ROOT__WS, ws);

		RxPattern arch = commonFactory.createRxPattern();
		arch.setOptional(true);
		arch.setPrefix("-");
		arch.setPattern("x86|x86_64|ppc|ppc64|s390|s390x|ia64_32");
		matchers.add(RmapPackage.Literals.DOCUMENT_ROOT__ARCH, arch);

		RxPattern end = commonFactory.createRxPattern();
		end.setPrefix(".");
		end.setPattern("zip|tar\\.gz");
		matchers.add(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__RX_PATTERN, end);

		setFixture(matcher);
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

} // URIMatcherTest

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.RxAssembly;
import org.eclipse.buckminster.model.common.RxGroup;
import org.eclipse.buckminster.model.common.RxPart;
import org.eclipse.buckminster.model.common.RxPattern;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Rx Assembly</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.RxAssembly#getPattern() <em>
 * Pattern</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.RxAssembly#getMatchMap(java.lang.CharSequence)
 * <em>Get Match Map</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class RxAssemblyTest extends RxGroupTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(RxAssemblyTest.class);
	}

	/**
	 * Constructs a new Rx Assembly test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RxAssemblyTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.RxAssembly#getMatchMap(java.lang.CharSequence)
	 * <em>Get Match Map</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.RxAssembly#getMatchMap(java.lang.CharSequence)
	 * @generated NOT
	 */
	public void testGetMatchMap__CharSequence() {
		Map<String, String> result = ((RxAssembly) fixture).getMatchMap("http://www.test.org/downloads/test.component_one_1.23.qualifier.tar.gz");
		assertNotNull(result);
		assertEquals("test.component_one", result.get("name"));
		assertEquals("1.23.qualifier", result.get("version"));
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.RxAssembly#getPattern()
	 * <em>Pattern</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.RxAssembly#getPattern()
	 * @generated NOT
	 */
	public void testGetPattern() {
		Pattern pattern = ((RxAssembly) fixture).getPattern();
		assertNotNull(pattern);
		assertEquals(
				"^http://www\\.test\\.org/downloads/([a-zA-Z0-9._-]+)_(([0-9]+)(?:\\.([0-9]+))?(?:\\.([0-9]+))?(?:\\.([a-zA-Z0-9_-]+))?)\\.tar\\.gz$",
				pattern.toString());
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.RxAssembly#getRxParts()
	 * <em>Rx Parts</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.RxAssembly#getRxParts()
	 * @generated NOT
	 */
	@Override
	public void testGetRxParts() {
		assertEquals(3, ((RxAssembly) fixture).getRxParts().size());
	}

	/**
	 * Returns the fixture for this Rx Assembly test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected RxAssembly getFixture() {
		return (RxAssembly) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		CommonFactory factory = CommonFactory.eINSTANCE;
		setFixture(factory.createRxAssembly());
		List<RxPart> parts = ((RxAssembly) fixture).getRxParts();

		RxPattern part = factory.createRxPattern();
		part.setName("name");
		part.setPattern("[a-zA-Z0-9._-]+");
		part.setPrefix("http://www.test.org/downloads/");
		part.setSuffix("_");
		parts.add(part);

		RxGroup vgroup = factory.createRxGroup();
		vgroup.setName("version");
		part = factory.createRxPattern();
		part.setName("major");
		part.setPattern("[0-9]+");
		vgroup.getRxParts().add(part);

		part = factory.createRxPattern();
		part.setName("minor");
		part.setPrefix(".");
		part.setPattern("[0-9]+");
		part.setOptional(true);
		vgroup.getRxParts().add(part);

		part = factory.createRxPattern();
		part.setName("micro");
		part.setPrefix(".");
		part.setPattern("[0-9]+");
		part.setOptional(true);
		vgroup.getRxParts().add(part);

		part = factory.createRxPattern();
		part.setName("qualifier");
		part.setPrefix(".");
		part.setPattern("[a-zA-Z0-9_-]+");
		part.setOptional(true);
		vgroup.getRxParts().add(part);
		parts.add(vgroup);

		part = factory.createRxPattern();
		part.setSuffix(".tar.gz");
		parts.add(part);
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

} // RxAssemblyTest

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.tests;

import java.util.regex.Pattern;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.util.VersionHelper;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.Transform;
import org.eclipse.buckminster.rmap.VersionConverter;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Version Converter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.rmap.VersionConverter#createVersion(java.lang.String) <em>Create Version</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.VersionConverter#createIdentifier(org.eclipse.equinox.p2.metadata.Version) <em>Create Identifier</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class VersionConverterTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(VersionConverterTest.class);
	}

	/**
	 * The fixture for this Version Converter test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VersionConverter fixture = null;

	/**
	 * Constructs a new Version Converter test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public VersionConverterTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.VersionConverter#createIdentifier(org.eclipse.equinox.p2.metadata.Version)
	 * <em>Create Identifier</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.VersionConverter#createIdentifier(org.eclipse.equinox.p2.metadata.Version)
	 * @generated NOT
	 */
	public void testCreateIdentifier__Version() {
		assertEquals("REL1_2_3foo", fixture.createIdentifier(Version.create("1.2.3.foo")));
		assertEquals("REL1_2_foo", fixture.createIdentifier(Version.create("1.2.0.foo")));
		assertEquals("REL1_2_3", fixture.createIdentifier(Version.create("1.2.3")));
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.VersionConverter#createVersion(java.lang.String)
	 * <em>Create Version</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.VersionConverter#createVersion(java.lang.String)
	 * @generated NOT
	 */
	public void testCreateVersion__String() {
		Version version = fixture.createVersion("REL1_2_3foo");
		assertNotNull(version);
		assertEquals("1.2.3.foo", version.toString());

		version = fixture.createVersion("REL1_2_foo");
		assertNotNull(version);
		assertEquals("1.2.0.foo", version.toString());

		version = fixture.createVersion("REL1_2_3");
		assertNotNull(version);
		assertEquals("1.2.3", version.toString());
	}

	/**
	 * Returns the fixture for this Version Converter test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected VersionConverter getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Version Converter test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(VersionConverter fixture) {
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
		setFixture(factory.createVersionConverter());
		fixture.setVersionFormat(VersionHelper.getOSGiFormat());

		Transform transform = factory.createTransform();
		transform.setFromPattern(Pattern.compile("(\\d+)\\.(\\d+)\\.0\\.([a-zA-Z]\\w*)"));
		transform.setFromReplacement("REL$1_$2_$3");
		transform.setToPattern(Pattern.compile("REL(\\d+)_(\\d+)_([a-zA-Z]\\w*)"));
		transform.setToReplacement("$1.$2.0.$3");
		fixture.getTransformers().add(transform);

		transform = factory.createTransform();
		transform.setFromPattern(Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)\\.([a-zA-Z]\\w*)"));
		transform.setFromReplacement("REL$1_$2_$3$4");
		transform.setToPattern(Pattern.compile("REL(\\d+)_(\\d+)_(\\d+)([a-zA-Z]\\w*)"));
		transform.setToReplacement("$1.$2.$3.$4");
		fixture.getTransformers().add(transform);

		transform = factory.createTransform();
		transform.setFromPattern(Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)"));
		transform.setFromReplacement("REL$1_$2_$3");
		transform.setToPattern(Pattern.compile("REL(\\d+)_(\\d+)_(\\d+)"));
		transform.setToReplacement("$1.$2.$3");
		fixture.getTransformers().add(transform);
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

} // VersionConverterTest

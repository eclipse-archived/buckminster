/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven.tests;

import java.util.regex.Pattern;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.Transform;
import org.eclipse.buckminster.rmap.maven.MapEntry;
import org.eclipse.buckminster.rmap.maven.Mappings;
import org.eclipse.buckminster.rmap.maven.MavenFactory;
import org.eclipse.buckminster.rmap.maven.MavenProvider;
import org.eclipse.core.runtime.CoreException;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Provider</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.rmap.maven.MavenProvider#getComponentName(java.lang.String, java.lang.String)
 * <em>Get Component Name</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.rmap.maven.MavenProvider#getMapEntry(java.lang.String)
 * <em>Get Map Entry</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MavenProviderTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(MavenProviderTest.class);
	}

	/**
	 * The fixture for this Provider test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected MavenProvider fixture = null;

	/**
	 * Constructs a new Provider test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MavenProviderTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.maven.MavenProvider#getComponentName(java.lang.String, java.lang.String)
	 * <em>Get Component Name</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.maven.MavenProvider#getComponentName(java.lang.String,
	 *      java.lang.String)
	 * @generated NOT
	 */
	public void testGetComponentName__String_String() {
		assertEquals("the.special.component", fixture.getComponentName("a.very.special.group", "with.a.special.artifact"));
		assertEquals("org.eclipse.buckminster.core", fixture.getComponentName("org.eclipse", "org.eclipse.buckminster.core"));
		assertEquals("some.group/some.artifact", fixture.getComponentName("some.group", "some.artifact"));
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.maven.MavenProvider#getMapEntry(java.lang.String)
	 * <em>Get Map Entry</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.maven.MavenProvider#getMapEntry(java.lang.String)
	 * @generated NOT
	 */
	public void testGetMapEntry__String() throws CoreException {
		MapEntry me = fixture.getMapEntry("the.special.component");
		assertEquals("the.special.component", me.getName());
		assertEquals("a.very.special.group", me.getGroupId());
		assertEquals("with.a.special.artifact", me.getArtifactId());

		me = fixture.getMapEntry("org.eclipse.buckminster.core");
		assertEquals("org.eclipse.buckminster.core", me.getName());
		assertEquals("org.eclipse", me.getGroupId());
		assertEquals("org.eclipse.buckminster.core", me.getArtifactId());

		me = fixture.getMapEntry("some.group/some.artifact");
		assertEquals("some.group/some.artifact", me.getName());
		assertEquals("some.group", me.getGroupId());
		assertEquals("some.artifact", me.getArtifactId());
	}

	/**
	 * Returns the fixture for this Provider test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MavenProvider getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Provider test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(MavenProvider fixture) {
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
		MavenFactory factory = MavenFactory.eINSTANCE;
		MavenProvider provider = factory.createMavenProvider();
		Mappings mappings = factory.createMappings();
		provider.setMappings(mappings);

		MapEntry me = factory.createMapEntry();
		me.setGroupId("a.very.special.group");
		me.setArtifactId("with.a.special.artifact");
		me.setName("the.special.component");
		mappings.getEntries().add(me);

		Transform rule = RmapFactory.eINSTANCE.createTransform();
		rule.setFromPattern(Pattern.compile("(org\\.eclipse)\\.(.*)"));
		rule.setFromReplacement("$1/$1.$2");
		rule.setToPattern(Pattern.compile("org\\.eclipse/(.*)"));
		rule.setToReplacement("$1");
		mappings.getRules().add(rule);

		setFixture(provider);
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

} // MavenProviderTest

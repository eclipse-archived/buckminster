/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.buckminster.rmap.maven.GroupAndArtifact;
import org.eclipse.buckminster.rmap.maven.MavenFactory;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Group And Artifact</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.rmap.maven.GroupAndArtifact#isMatchFor(java.lang.String, java.lang.String)
 * <em>Is Match For</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class GroupAndArtifactTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(GroupAndArtifactTest.class);
	}

	/**
	 * The fixture for this Group And Artifact test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected GroupAndArtifact fixture = null;

	/**
	 * Constructs a new Group And Artifact test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public GroupAndArtifactTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.maven.GroupAndArtifact#isMatchFor(java.lang.String, java.lang.String)
	 * <em>Is Match For</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.maven.GroupAndArtifact#isMatchFor(java.lang.String,
	 *      java.lang.String)
	 * @generated NOT
	 */
	public void testIsMatchFor__String_String() {
		fixture.setGroupId("group");
		fixture.setArtifactId("artifact");
		assertTrue(fixture.isMatchFor("group", "artifact"));
		assertFalse(fixture.isMatchFor("group2", "artifact"));
		assertFalse(fixture.isMatchFor("group", "artifact2"));
	}

	/**
	 * Returns the fixture for this Group And Artifact test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected GroupAndArtifact getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Group And Artifact test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(GroupAndArtifact fixture) {
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
		setFixture(MavenFactory.eINSTANCE.createGroupAndArtifact());
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

} // GroupAndArtifactTest

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven.tests;

import junit.textui.TestRunner;

import org.eclipse.buckminster.rmap.maven.MapEntry;
import org.eclipse.buckminster.rmap.maven.MavenFactory;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Map Entry</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class MapEntryTest extends GroupAndArtifactTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(MapEntryTest.class);
	}

	/**
	 * Constructs a new Map Entry test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MapEntryTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Map Entry test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected MapEntry getFixture() {
		return (MapEntry) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(MavenFactory.eINSTANCE.createMapEntry());
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

} // MapEntryTest

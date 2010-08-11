/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.buckminster.rmap.maven.Mappings;
import org.eclipse.buckminster.rmap.maven.MavenFactory;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Mappings</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class MappingsTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(MappingsTest.class);
	}

	/**
	 * The fixture for this Mappings test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected Mappings fixture = null;

	/**
	 * Constructs a new Mappings test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MappingsTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Mappings test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Mappings getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Mappings test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(Mappings fixture) {
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
		setFixture(MavenFactory.eINSTANCE.createMappings());
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

} // MappingsTest

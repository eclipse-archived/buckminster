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

import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.Transform;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Transform</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.rmap.Transform#transformFrom(java.lang.String)
 * <em>Transform From</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.rmap.Transform#transformTo(java.lang.String)
 * <em>Transform To</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TransformTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(TransformTest.class);
	}

	/**
	 * The fixture for this Transform test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected Transform fixture = null;

	/**
	 * Constructs a new Transform test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TransformTest(String name) {
		super(name);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.Transform#transformFrom(java.lang.String)
	 * <em>Transform From</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Transform#transformFrom(java.lang.String)
	 * @generated NOT
	 */
	public void testTransformFrom__String() {
		assertEquals("REL1_2_3apa", fixture.transformFrom("1.2.3.apa"));
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.rmap.Transform#transformTo(java.lang.String)
	 * <em>Transform To</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.Transform#transformTo(java.lang.String)
	 * @generated NOT
	 */
	public void testTransformTo__String() {
		assertEquals("1.2.3.apa", fixture.transformTo("REL1_2_3apa"));
	}

	/**
	 * Returns the fixture for this Transform test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Transform getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Transform test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(Transform fixture) {
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
		setFixture(RmapFactory.eINSTANCE.createTransform());
		fixture.setFromPattern(Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)\\.([a-zA-Z]\\w*)"));
		fixture.setFromReplacement("REL$1_$2_$3$4");
		fixture.setToPattern(Pattern.compile("REL(\\d+)_(\\d+)_(\\d+)([a-zA-Z]\\w*)"));
		fixture.setToReplacement("$1.$2.$3.$4");
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

} // TransformTest

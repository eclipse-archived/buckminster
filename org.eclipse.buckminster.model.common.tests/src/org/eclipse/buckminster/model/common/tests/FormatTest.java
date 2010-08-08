/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.textui.TestRunner;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.model.common.PropertyRef;
import org.eclipse.buckminster.model.common.Value;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Format</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class FormatTest extends ValueFilterTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(FormatTest.class);
	}

	/**
	 * Constructs a new Format test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FormatTest(String name) {
		super(name);
	}

	@Override
	public void testGetValue__Map() {
		Map<String, String> actors = new HashMap<String, String>();
		actors.put("jumper", "fox");
		actors.put("lazy", "dog");
		assertEquals("The quick brown fox jumps over the lazy dog", fixture.getValue(actors));
	}

	@Override
	public void testGetValues() {
		List<Value> values = getFixture().getValues();
		assertEquals(2, values.size());
	}

	@Override
	public void testGetValues__Map() {
		Map<String, String> actors = new HashMap<String, String>();
		actors.put("jumper", "fox");
		actors.put("lazy", "dog");
		List<String> result = getFixture().getValues(actors);
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("The quick brown fox jumps over the lazy dog", result.get(0));
	}

	/**
	 * Returns the fixture for this Format test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Format getFixture() {
		return (Format) fixture;
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
		Format format = factory.createFormat();
		format.setFormat("The quick brown {0} jumps over the lazy {1}");
		List<Value> values = format.getValues();
		PropertyRef jumper = factory.createPropertyRef();
		jumper.setKey("jumper");
		values.add(jumper);
		PropertyRef lazy = factory.createPropertyRef();
		lazy.setKey("lazy");
		values.add(lazy);
		setFixture(format);
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

} // FormatTest

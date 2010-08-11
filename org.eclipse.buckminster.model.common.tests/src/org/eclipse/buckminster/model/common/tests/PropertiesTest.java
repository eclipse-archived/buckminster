/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.tests;

import java.io.File;
import java.text.MessageFormat;
import java.util.Map;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.Constant;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.model.common.Match;
import org.eclipse.buckminster.model.common.Properties;
import org.eclipse.buckminster.model.common.PropertyRef;
import org.eclipse.buckminster.model.common.Replace;
import org.eclipse.buckminster.model.common.Split;
import org.eclipse.buckminster.model.common.impl.PropertiesImpl;
import org.eclipse.buckminster.model.common.util.BMProperties;
import org.eclipse.buckminster.model.common.util.CircularExpansionException;
import org.eclipse.buckminster.model.common.util.ExpandingProperties;
import org.eclipse.buckminster.model.common.util.IProperties;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Properties</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.Properties#getProperties()
 * <em>Get Properties</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class PropertiesTest extends TestCase {
	static void log(String message, Object... args) {
		System.out.format(message + "%n", args);
	}

	/**
	 * The fixture for this Properties test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected Properties fixture = null;

	/**
	 * Constructs a new Properties test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PropertiesTest(String name) {
		super(name);
	}

	public void testCircularExpansionTrap() {
		IProperties props = new ExpandingProperties();
		props.put("some.text", "text is ${some.text.again}!");
		props.put("some.other.text", "Hello ${some.text}!");
		props.put("some.text.again", "Ouch! ${some.other.text}");

		try {
			log(props.get("some.text.again"));
			assertFalse(true);
		} catch (CircularExpansionException e) {
			log("OK: \"%s\" resulted in a CircularExpansionException", e.getMessage());
		}
	}

	public void testExpandingProperties() {
		IProperties props = new ExpandingProperties(BMProperties.getSystemProperties());
		props.put("salut", "Hello ${user.name}!");
		props.put("salut.home", "${salut} Your $${user.home} is ${user.home}");

		String result = props.get("salut.home");
		String expected = "Hello " + System.getProperty("user.name") + "! Your ${user.home} is " + System.getProperty("user.home");

		log(result);
		assertEquals(expected, result);
	}

	public void testExpressions() {
		CommonFactory factory = CommonFactory.eINSTANCE;
		Constant cvsRoot = factory.createConstant();
		cvsRoot.setValue(":pserver:${user.name}@buckminster.tigris.org:/cvs");
		String fmtString = "First \"{0}\", second \"{1}\", third \"{2}\", and fourth \"{3}\"";

		Format fmt = factory.createFormat();
		fmt.setFormat(fmtString);
		Split split = factory.createSplit();
		split.setPattern(":|@");
		Replace rpl = factory.createReplace();
		Match match = factory.createMatch();
		match.setPattern("^:(.*)$");
		match.setReplacement("$1");
		rpl.getMatches().add(match);
		rpl.getValues().add(cvsRoot);
		split.getValues().add(rpl);
		fmt.getValues().add(split);

		String expected = new MessageFormat(fmtString).format(new String[] { "pserver", System.getProperty("user.name"), "buckminster.tigris.org",
				"/cvs" });

		IProperties props = BMProperties.getSystemProperties();
		String result = fmt.getValue(props);
		log(result);
		assertEquals(expected, result);
	}

	/**
	 * Tests the '
	 * {@link org.eclipse.buckminster.model.common.Properties#getProperties()
	 * <em>Get Properties</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.Properties#getProperties()
	 * @generated NOT
	 */
	public void testGetProperties() {
		CommonFactory factory = CommonFactory.eINSTANCE;
		Properties props = new PropertiesImpl() {};
		Constant c = factory.createConstant();
		c.setValue("first");
		props.getPropertyConstants().put("one", c);

		PropertyRef p = factory.createPropertyRef();
		p.setKey("referenced");
		props.getPropertyElements().put("two", p);

		c = factory.createConstant();
		c.setValue("third");
		props.getPropertyConstants().put("three", c);

		c = factory.createConstant();
		c.setValue("second");
		props.getPropertyConstants().put("referenced", c);

		Map<String, String> map = props.getProperties();
		assertNotNull(map);
		assertEquals(4, map.size());
		assertEquals("first", map.get("one"));
		assertEquals("second", map.get("two"));
		assertEquals("third", map.get("three"));
		assertEquals("second", map.get("referenced"));
	}

	public void testSystemProperties() {
		CommonFactory factory = CommonFactory.eINSTANCE;
		Format fmt = factory.createFormat();
		fmt.setFormat("You are {0}, the parent of your home is {1} and you run Java version {2}");

		PropertyRef pref = factory.createPropertyRef();
		pref.setKey("user.name");
		fmt.getValues().add(pref);

		Replace rpl1 = factory.createReplace();
		Match match = factory.createMatch();
		match.setPattern(Pattern.quote("\\"));
		match.setReplacement("/");
		rpl1.getMatches().add(match);

		pref = factory.createPropertyRef();
		pref.setKey("user.home");
		rpl1.getValues().add(pref);

		Replace rpl2 = factory.createReplace();
		match = factory.createMatch();
		match.setPattern("^(.*)/[^/]+$");
		match.setReplacement("$1");
		rpl2.getMatches().add(match);

		rpl2.getValues().add(rpl1);
		fmt.getValues().add(rpl2);

		pref = factory.createPropertyRef();
		pref.setKey("java.version");
		fmt.getValues().add(pref);

		IProperties props = BMProperties.getSystemProperties();
		String result = fmt.getValue(props);
		String expected = "You are " + props.get("user.name") + ", the parent of your home is "
				+ (new File(props.get("user.home"))).getParent().replace('\\', '/') + " and you run Java version " + props.get("java.version");

		log(result);
		assertEquals(expected, result);
	}

	/**
	 * Returns the fixture for this Properties test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Properties getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Properties test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(Properties fixture) {
		this.fixture = fixture;
	}

} // PropertiesTest

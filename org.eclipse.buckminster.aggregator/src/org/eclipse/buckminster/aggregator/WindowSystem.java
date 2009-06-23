/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Window System</b></em>', and
 * utility methods for working with them. <!-- end-user-doc -->
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getWindowSystem()
 * @model
 * @generated
 */
public enum WindowSystem implements Enumerator
{
	/**
	 * The '<em><b>Win32</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #WIN32_VALUE
	 * @generated
	 * @ordered
	 */
	WIN32(0, "Win32", "Win32"), /**
	 * The '<em><b>GTK</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #GTK_VALUE
	 * @generated
	 * @ordered
	 */
	GTK(1, "GTK", "GTK"), /**
	 * The '<em><b>Carbon</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #CARBON_VALUE
	 * @generated
	 * @ordered
	 */
	CARBON(2, "Carbon", "Carbon"), /**
	 * The '<em><b>Cocoa</b></em>' literal object.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #COCOA_VALUE
	 * @generated
	 * @ordered
	 */
	COCOA(3, "Cocoa", "cocoa");

	/**
	 * The '<em><b>Win32</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Win32</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WIN32
	 * @model name="Win32"
	 * @generated
	 * @ordered
	 */
	public static final int WIN32_VALUE = 0;

	/**
	 * The '<em><b>GTK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GTK</b></em>' literal object isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GTK
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GTK_VALUE = 1;

	/**
	 * The '<em><b>Carbon</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Carbon</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CARBON
	 * @model name="Carbon"
	 * @generated
	 * @ordered
	 */
	public static final int CARBON_VALUE = 2;

	/**
	 * The '<em><b>Cocoa</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Cocoa</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COCOA
	 * @model name="Cocoa" literal="cocoa"
	 * @generated
	 * @ordered
	 */
	public static final int COCOA_VALUE = 3;

	/**
	 * An array of all the '<em><b>Window System</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static final WindowSystem[] VALUES_ARRAY = new WindowSystem[] {
			WIN32,
			GTK,
			CARBON,
			COCOA,
		};

	/**
	 * A public read-only list of all the '<em><b>Window System</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static final List<WindowSystem> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Window System</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WindowSystem get(String literal)
	{
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WindowSystem result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Window System</b></em>' literal with the specified name.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static WindowSystem getByName(String name)
	{
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WindowSystem result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Window System</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WindowSystem get(int value)
	{
		switch (value) {
			case WIN32_VALUE: return WIN32;
			case GTK_VALUE: return GTK;
			case CARBON_VALUE: return CARBON;
			case COCOA_VALUE: return COCOA;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private WindowSystem(int value, String name, String literal)
	{
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue()
	{
	  return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName()
	{
	  return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral()
	{
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		return literal;
	}

} // WindowSystem

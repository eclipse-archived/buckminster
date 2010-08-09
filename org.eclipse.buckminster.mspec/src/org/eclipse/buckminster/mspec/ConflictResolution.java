/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Conflict Resolution</b></em>', and utility methods for working with
 * them. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.mspec.MspecPackage#getConflictResolution()
 * @model
 * @generated
 */
public enum ConflictResolution implements Enumerator {
	/**
	 * The '<em><b>FAIL</b></em>' literal object. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #FAIL_VALUE
	 * @generated
	 * @ordered
	 */
	FAIL(0, "FAIL", "FAIL"),

	/**
	 * The '<em><b>KEEP</b></em>' literal object. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #KEEP_VALUE
	 * @generated
	 * @ordered
	 */
	KEEP(1, "KEEP", "KEEP"),

	/**
	 * The '<em><b>REPLACE</b></em>' literal object. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #REPLACE_VALUE
	 * @generated
	 * @ordered
	 */
	REPLACE(2, "REPLACE", "REPLACE"),

	/**
	 * The '<em><b>UPDATE</b></em>' literal object. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #UPDATE_VALUE
	 * @generated
	 * @ordered
	 */
	UPDATE(3, "UPDATE", "UPDATE");

	/**
	 * The '<em><b>FAIL</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FAIL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #FAIL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FAIL_VALUE = 0;

	/**
	 * The '<em><b>KEEP</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>KEEP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #KEEP
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int KEEP_VALUE = 1;

	/**
	 * The '<em><b>REPLACE</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REPLACE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #REPLACE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REPLACE_VALUE = 2;

	/**
	 * The '<em><b>UPDATE</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UPDATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #UPDATE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UPDATE_VALUE = 3;

	/**
	 * An array of all the '<em><b>Conflict Resolution</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static final ConflictResolution[] VALUES_ARRAY = new ConflictResolution[] { FAIL, KEEP, REPLACE, UPDATE, };

	/**
	 * A public read-only list of all the '<em><b>Conflict Resolution</b></em>'
	 * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final List<ConflictResolution> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Conflict Resolution</b></em>' literal with the
	 * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static ConflictResolution get(int value) {
		switch (value) {
			case FAIL_VALUE:
				return FAIL;
			case KEEP_VALUE:
				return KEEP;
			case REPLACE_VALUE:
				return REPLACE;
			case UPDATE_VALUE:
				return UPDATE;
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Conflict Resolution</b></em>' literal with the
	 * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static ConflictResolution get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ConflictResolution result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Conflict Resolution</b></em>' literal with the
	 * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static ConflictResolution getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ConflictResolution result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	private ConflictResolution(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public String getLiteral() {
		return literal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public int getValue() {
		return value;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string
	 * representation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public String toString() {
		return literal;
	}

} // ConflictResolution

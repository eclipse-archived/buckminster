/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Version Selector Type</b></em>', and utility methods for working with
 * them. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getVersionSelectorType()
 * @model
 * @generated
 */
public enum VersionSelectorType implements Enumerator {
	/**
	 * The '<em><b>BRANCH</b></em>' literal object. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #BRANCH_VALUE
	 * @generated
	 * @ordered
	 */
	BRANCH(0, "BRANCH", "BRANCH"),

	/**
	 * The '<em><b>TAG</b></em>' literal object. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #TAG_VALUE
	 * @generated
	 * @ordered
	 */
	TAG(1, "TAG", "TAG");

	/**
	 * The '<em><b>BRANCH</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BRANCH</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #BRANCH
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BRANCH_VALUE = 0;

	/**
	 * The '<em><b>TAG</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TAG</b></em>' literal object isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #TAG
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TAG_VALUE = 1;

	/**
	 * An array of all the '<em><b>Version Selector Type</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static final VersionSelectorType[] VALUES_ARRAY = new VersionSelectorType[] { BRANCH, TAG, };

	/**
	 * A public read-only list of all the '<em><b>Version Selector Type</b></em>
	 * ' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final List<VersionSelectorType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Version Selector Type</b></em>' literal with the
	 * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static VersionSelectorType get(int value) {
		switch (value) {
			case BRANCH_VALUE:
				return BRANCH;
			case TAG_VALUE:
				return TAG;
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Version Selector Type</b></em>' literal with the
	 * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static VersionSelectorType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			VersionSelectorType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Version Selector Type</b></em>' literal with the
	 * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static VersionSelectorType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			VersionSelectorType result = VALUES_ARRAY[i];
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
	private VersionSelectorType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getLiteral() {
		return literal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
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

} // VersionSelectorType

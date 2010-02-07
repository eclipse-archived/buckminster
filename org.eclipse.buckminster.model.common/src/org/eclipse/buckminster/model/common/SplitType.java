/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Split Type</b></em>', and utility methods for working with them. <!--
 * end-user-doc --> <!-- begin-model-doc --> A split can be done using a
 * delimiter or a pattern containing capturing groups. In case of delimiter it
 * can be quoted or not quoted. <!-- end-model-doc -->
 * 
 * @see org.eclipse.buckminster.model.common.CommonPackage#getSplitType()
 * @model
 * @generated
 */
public enum SplitType implements Enumerator {
	/**
	 * The '<em><b>Quoted</b></em>' literal object. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #QUOTED_VALUE
	 * @generated
	 * @ordered
	 */
	QUOTED(0, "quoted", "quoted"),

	/**
	 * The '<em><b>Unquoted</b></em>' literal object. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #UNQUOTED_VALUE
	 * @generated
	 * @ordered
	 */
	UNQUOTED(1, "unquoted", "unquoted"),

	/**
	 * The '<em><b>Groups</b></em>' literal object. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #GROUPS_VALUE
	 * @generated
	 * @ordered
	 */
	GROUPS(2, "groups", "groups");

	/**
	 * The '<em><b>Quoted</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Quoted</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #QUOTED
	 * @model name="quoted"
	 * @generated
	 * @ordered
	 */
	public static final int QUOTED_VALUE = 0;

	/**
	 * The '<em><b>Unquoted</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unquoted</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #UNQUOTED
	 * @model name="unquoted"
	 * @generated
	 * @ordered
	 */
	public static final int UNQUOTED_VALUE = 1;

	/**
	 * The '<em><b>Groups</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Groups</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #GROUPS
	 * @model name="groups"
	 * @generated
	 * @ordered
	 */
	public static final int GROUPS_VALUE = 2;

	/**
	 * An array of all the '<em><b>Split Type</b></em>' enumerators. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static final SplitType[] VALUES_ARRAY = new SplitType[] { QUOTED, UNQUOTED, GROUPS, };

	/**
	 * A public read-only list of all the '<em><b>Split Type</b></em>'
	 * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final List<SplitType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Split Type</b></em>' literal with the specified
	 * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static SplitType get(int value) {
		switch (value) {
			case QUOTED_VALUE:
				return QUOTED;
			case UNQUOTED_VALUE:
				return UNQUOTED;
			case GROUPS_VALUE:
				return GROUPS;
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Split Type</b></em>' literal with the specified
	 * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static SplitType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SplitType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Split Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static SplitType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SplitType result = VALUES_ARRAY[i];
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
	private SplitType(int value, String name, String literal) {
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

} // SplitType

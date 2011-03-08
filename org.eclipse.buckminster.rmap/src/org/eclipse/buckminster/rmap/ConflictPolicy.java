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
 * <em><b>Conflict Policy</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getConflictPolicy()
 * @model
 * @generated
 */
public enum ConflictPolicy implements Enumerator {
	/**
	 * The '<em><b>Use Workspace</b></em>' literal object. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #USE_WORKSPACE_VALUE
	 * @generated
	 * @ordered
	 */
	USE_WORKSPACE(0, "useWorkspace", "USE_WORKSPACE"),

	/**
	 * The '<em><b>Use SCM</b></em>' literal object. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #USE_SCM_VALUE
	 * @generated
	 * @ordered
	 */
	USE_SCM(1, "useSCM", "USE_SCM"),

	/**
	 * The '<em><b>Fail</b></em>' literal object. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #FAIL_VALUE
	 * @generated
	 * @ordered
	 */
	FAIL(2, "fail", "FAIL");

	/**
	 * The '<em><b>Use Workspace</b></em>' literal value. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of '<em><b>Use Workspace</b></em>' literal object isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #USE_WORKSPACE
	 * @model name="useWorkspace" literal="USE_WORKSPACE"
	 * @generated
	 * @ordered
	 */
	public static final int USE_WORKSPACE_VALUE = 0;

	/**
	 * The '<em><b>Use SCM</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Use SCM</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #USE_SCM
	 * @model name="useSCM" literal="USE_SCM"
	 * @generated
	 * @ordered
	 */
	public static final int USE_SCM_VALUE = 1;

	/**
	 * The '<em><b>Fail</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Fail</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #FAIL
	 * @model name="fail" literal="FAIL"
	 * @generated
	 * @ordered
	 */
	public static final int FAIL_VALUE = 2;

	/**
	 * An array of all the '<em><b>Conflict Policy</b></em>' enumerators. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static final ConflictPolicy[] VALUES_ARRAY = new ConflictPolicy[] { USE_WORKSPACE, USE_SCM, FAIL, };

	/**
	 * A public read-only list of all the '<em><b>Conflict Policy</b></em>'
	 * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final List<ConflictPolicy> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Conflict Policy</b></em>' literal with the specified
	 * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static ConflictPolicy get(int value) {
		switch (value) {
			case USE_WORKSPACE_VALUE:
				return USE_WORKSPACE;
			case USE_SCM_VALUE:
				return USE_SCM;
			case FAIL_VALUE:
				return FAIL;
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Conflict Policy</b></em>' literal with the specified
	 * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static ConflictPolicy get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ConflictPolicy result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Conflict Policy</b></em>' literal with the specified
	 * name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static ConflictPolicy getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ConflictPolicy result = VALUES_ARRAY[i];
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
	private ConflictPolicy(int value, String name, String literal) {
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

} // ConflictPolicy

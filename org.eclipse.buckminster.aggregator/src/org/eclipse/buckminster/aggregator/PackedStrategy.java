/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Packed Strategy</b></em>', and
 * utility methods for working with them. <!-- end-user-doc -->
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getPackedStrategy()
 * @model
 * @generated
 */
public enum PackedStrategy implements Enumerator
{
	/**
	 * The '<em><b>Copy</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #COPY_VALUE
	 * @generated
	 * @ordered
	 */
	COPY(0, "Copy", "COPY"),

	/**
	 * The '<em><b>Verify</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #VERIFY_VALUE
	 * @generated
	 * @ordered
	 */
	VERIFY(1, "Verify", "VERIFY"),

	/**
	 * The '<em><b>Unpack As Sibling</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #UNPACK_AS_SIBLING_VALUE
	 * @generated
	 * @ordered
	 */
	UNPACK_AS_SIBLING(2, "UnpackAsSibling", "UNPACK_AS_SIBLING"),

	/**
	 * The '<em><b>Unpack</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #UNPACK_VALUE
	 * @generated
	 * @ordered
	 */
	UNPACK(3, "Unpack", "UNPACK"),

	/**
	 * The '<em><b>Skip</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #SKIP_VALUE
	 * @generated
	 * @ordered
	 */
	SKIP(4, "Skip", "SKIP");

	/**
	 * The '<em><b>Copy</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Copy</b></em>' literal object isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COPY
	 * @model name="Copy" literal="COPY"
	 * @generated
	 * @ordered
	 */
	public static final int COPY_VALUE = 0;

	/**
	 * The '<em><b>Verify</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Verify</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VERIFY
	 * @model name="Verify" literal="VERIFY"
	 * @generated
	 * @ordered
	 */
	public static final int VERIFY_VALUE = 1;

	/**
	 * The '<em><b>Unpack As Sibling</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unpack  As  Sibling</b></em>' literal object isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNPACK_AS_SIBLING
	 * @model name="UnpackAsSibling" literal="UNPACK_AS_SIBLING"
	 * @generated
	 * @ordered
	 */
	public static final int UNPACK_AS_SIBLING_VALUE = 2;

	/**
	 * The '<em><b>Unpack</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unpack</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNPACK
	 * @model name="Unpack" literal="UNPACK"
	 * @generated
	 * @ordered
	 */
	public static final int UNPACK_VALUE = 3;

	/**
	 * The '<em><b>Skip</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Skip</b></em>' literal object isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SKIP
	 * @model name="Skip" literal="SKIP"
	 * @generated
	 * @ordered
	 */
	public static final int SKIP_VALUE = 4;

	/**
	 * An array of all the '<em><b>Packed Strategy</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static final PackedStrategy[] VALUES_ARRAY = new PackedStrategy[]
		{
			COPY,
			VERIFY,
			UNPACK_AS_SIBLING,
			UNPACK,
			SKIP,
		};

	/**
	 * A public read-only list of all the '<em><b>Packed Strategy</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static final List<PackedStrategy> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Packed Strategy</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PackedStrategy get(int value)
	{
		switch (value)
		{
			case COPY_VALUE: return COPY;
			case VERIFY_VALUE: return VERIFY;
			case UNPACK_AS_SIBLING_VALUE: return UNPACK_AS_SIBLING;
			case UNPACK_VALUE: return UNPACK;
			case SKIP_VALUE: return SKIP;
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Packed Strategy</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PackedStrategy get(String literal)
	{
		for (int i = 0; i < VALUES_ARRAY.length; ++i)
		{
			PackedStrategy result = VALUES_ARRAY[i];
			if (result.toString().equals(literal))
			{
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Packed Strategy</b></em>' literal with the specified name.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static PackedStrategy getByName(String name)
	{
		for (int i = 0; i < VALUES_ARRAY.length; ++i)
		{
			PackedStrategy result = VALUES_ARRAY[i];
			if (result.getName().equals(name))
			{
				return result;
			}
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
	private PackedStrategy(int value, String name, String literal)
	{
		this.value = value;
		this.name = name;
		this.literal = literal;
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
	public int getValue()
	{
	  return value;
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

} // PackedStrategy

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Up To Date Policy</b></em>', and
 * utility methods for working with them. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getUpToDatePolicy()
 * @model
 * @generated
 */
public enum UpToDatePolicy implements Enumerator
{
	/**
	 * The '<em><b>DEFAULT</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #DEFAULT_VALUE
	 * @generated
	 * @ordered
	 */
	DEFAULT(0, "DEFAULT", "DEFAULT"),

	/**
	 * The '<em><b>ACTOR</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #ACTOR_VALUE
	 * @generated
	 * @ordered
	 */
	ACTOR(1, "ACTOR", "ACTOR"),

	/**
	 * The '<em><b>COUNT</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #COUNT_VALUE
	 * @generated
	 * @ordered
	 */
	COUNT(2, "COUNT", "COUNT"),

	/**
	 * The '<em><b>MAPPER</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #MAPPER_VALUE
	 * @generated
	 * @ordered
	 */
	MAPPER(3, "MAPPER", "MAPPER"),

	/**
	 * The '<em><b>NOT EMPTY</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #NOT_EMPTY_VALUE
	 * @generated
	 * @ordered
	 */
	NOT_EMPTY(4, "NOT_EMPTY", "NOT_EMPTY"),

	/**
	 * The '<em><b>NEVER</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #NEVER_VALUE
	 * @generated
	 * @ordered
	 */
	NEVER(5, "NEVER", "NEVER");

	/**
	 * The '<em><b>DEFAULT</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc
	 * --> Never trust a product defined as a folder since the expected number of files is unknown <!-- end-model-doc
	 * -->
	 * 
	 * @see #DEFAULT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DEFAULT_VALUE = 0;

	/**
	 * The '<em><b>ACTOR</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Let the associated actor decide <!-- end-model-doc -->
	 * 
	 * @see #ACTOR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ACTOR_VALUE = 1;

	/**
	 * The '<em><b>COUNT</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Trust timestamp only when the product contains the number of files denoted in attribute 'count' <!--
	 * end-model-doc -->
	 * 
	 * @see #COUNT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int COUNT_VALUE = 2;

	/**
	 * The '<em><b>MAPPER</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc
	 * --> Trust timestamp of a product that matches the action prerequisites in number and optional pattern. The
	 * 'count' attribute may be used to denote file additions <!-- end-model-doc -->
	 * 
	 * @see #MAPPER
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MAPPER_VALUE = 3;

	/**
	 * The '<em><b>NOT EMPTY</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc
	 * --> Trust timestamp of any product that contains at least one file <!-- end-model-doc -->
	 * 
	 * @see #NOT_EMPTY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NOT_EMPTY_VALUE = 4;

	/**
	 * The '<em><b>NEVER</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * The product is never up to date. The action must always be executed when its product is requested <!--
	 * end-model-doc -->
	 * 
	 * @see #NEVER
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NEVER_VALUE = 5;

	/**
	 * An array of all the '<em><b>Up To Date Policy</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	private static final UpToDatePolicy[] VALUES_ARRAY = new UpToDatePolicy[] { DEFAULT, ACTOR, COUNT, MAPPER,
			NOT_EMPTY, NEVER, };

	/**
	 * A public read-only list of all the '<em><b>Up To Date Policy</b></em>' enumerators. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static final List<UpToDatePolicy> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Up To Date Policy</b></em>' literal with the specified integer value. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static UpToDatePolicy get(int value)
	{
		switch(value)
		{
		case DEFAULT_VALUE:
			return DEFAULT;
		case ACTOR_VALUE:
			return ACTOR;
		case COUNT_VALUE:
			return COUNT;
		case MAPPER_VALUE:
			return MAPPER;
		case NOT_EMPTY_VALUE:
			return NOT_EMPTY;
		case NEVER_VALUE:
			return NEVER;
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Up To Date Policy</b></em>' literal with the specified literal value. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static UpToDatePolicy get(String literal)
	{
		for(int i = 0; i < VALUES_ARRAY.length; ++i)
		{
			UpToDatePolicy result = VALUES_ARRAY[i];
			if(result.toString().equals(literal))
			{
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Up To Date Policy</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static UpToDatePolicy getByName(String name)
	{
		for(int i = 0; i < VALUES_ARRAY.length; ++i)
		{
			UpToDatePolicy result = VALUES_ARRAY[i];
			if(result.getName().equals(name))
			{
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
	 * Only this class can construct instances. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private UpToDatePolicy(int value, String name, String literal)
	{
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getLiteral()
	{
		return literal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString()
	{
		return literal;
	}

} // UpToDatePolicy

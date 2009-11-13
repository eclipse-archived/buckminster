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
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Installable Unit Type</b></em>',
 * and utility methods for working with them. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getInstallableUnitType()
 * @model
 * @generated
 */
public enum InstallableUnitType implements Enumerator
{
	/**
	 * The '<em><b>BUNDLE</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #BUNDLE_VALUE
	 * @generated
	 * @ordered
	 */
	BUNDLE(0, "BUNDLE", "bundle"),

	/**
	 * The '<em><b>FEATURE</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #FEATURE_VALUE
	 * @generated
	 * @ordered
	 */
	FEATURE(1, "FEATURE", "feature"),

	/**
	 * The '<em><b>PRODUCT</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #PRODUCT_VALUE
	 * @generated
	 * @ordered
	 */
	PRODUCT(2, "PRODUCT", "product"),

	/**
	 * The '<em><b>CATEGORY</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #CATEGORY_VALUE
	 * @generated
	 * @ordered
	 */
	CATEGORY(3, "CATEGORY", "category"),

	/**
	 * The '<em><b>FRAGMENT</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #FRAGMENT_VALUE
	 * @generated
	 * @ordered
	 */
	FRAGMENT(4, "FRAGMENT", "fragment"),

	/**
	 * The '<em><b>OTHER</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #OTHER_VALUE
	 * @generated
	 * @ordered
	 */
	OTHER(5, "OTHER", "other");

	/**
	 * The '<em><b>BUNDLE</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BUNDLE</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #BUNDLE
	 * @model literal="bundle"
	 * @generated
	 * @ordered
	 */
	public static final int BUNDLE_VALUE = 0;

	/**
	 * The '<em><b>FEATURE</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FEATURE</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #FEATURE
	 * @model literal="feature"
	 * @generated
	 * @ordered
	 */
	public static final int FEATURE_VALUE = 1;

	/**
	 * The '<em><b>PRODUCT</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PRODUCT</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #PRODUCT
	 * @model literal="product"
	 * @generated
	 * @ordered
	 */
	public static final int PRODUCT_VALUE = 2;

	/**
	 * The '<em><b>CATEGORY</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CATEGORY</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #CATEGORY
	 * @model literal="category"
	 * @generated
	 * @ordered
	 */
	public static final int CATEGORY_VALUE = 3;

	/**
	 * The '<em><b>FRAGMENT</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FRAGMENT</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #FRAGMENT
	 * @model literal="fragment"
	 * @generated
	 * @ordered
	 */
	public static final int FRAGMENT_VALUE = 4;

	/**
	 * The '<em><b>OTHER</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OTHER</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #OTHER
	 * @model literal="other"
	 * @generated
	 * @ordered
	 */
	public static final int OTHER_VALUE = 5;

	/**
	 * An array of all the '<em><b>Installable Unit Type</b></em>' enumerators. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	private static final InstallableUnitType[] VALUES_ARRAY = new InstallableUnitType[] { BUNDLE, FEATURE, PRODUCT,
			CATEGORY, FRAGMENT, OTHER, };

	/**
	 * A public read-only list of all the '<em><b>Installable Unit Type</b></em>' enumerators. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final List<InstallableUnitType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Installable Unit Type</b></em>' literal with the specified integer value. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static InstallableUnitType get(int value)
	{
		switch(value)
		{
		case BUNDLE_VALUE:
			return BUNDLE;
		case FEATURE_VALUE:
			return FEATURE;
		case PRODUCT_VALUE:
			return PRODUCT;
		case CATEGORY_VALUE:
			return CATEGORY;
		case FRAGMENT_VALUE:
			return FRAGMENT;
		case OTHER_VALUE:
			return OTHER;
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Installable Unit Type</b></em>' literal with the specified literal value. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static InstallableUnitType get(String literal)
	{
		for(int i = 0; i < VALUES_ARRAY.length; ++i)
		{
			InstallableUnitType result = VALUES_ARRAY[i];
			if(result.toString().equals(literal))
			{
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Installable Unit Type</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static InstallableUnitType getByName(String name)
	{
		for(int i = 0; i < VALUES_ARRAY.length; ++i)
		{
			InstallableUnitType result = VALUES_ARRAY[i];
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
	private InstallableUnitType(int value, String name, String literal)
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

} // InstallableUnitType

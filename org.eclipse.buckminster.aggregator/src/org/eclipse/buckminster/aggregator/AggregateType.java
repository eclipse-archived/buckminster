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
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Aggregate Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregateType()
 * @model
 * @generated
 */
public enum AggregateType implements Enumerator
{
  /**
   * The '<em><b>Stable</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #STABLE_VALUE
   * @generated
   * @ordered
   */
  STABLE(3, "Stable", "S"),

  /**
   * The '<em><b>Integration</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #INTEGRATION_VALUE
   * @generated
   * @ordered
   */
  INTEGRATION(2, "Integration", "I"),

  /**
   * The '<em><b>Nightly</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NIGHTLY_VALUE
   * @generated
   * @ordered
   */
  NIGHTLY(1, "Nightly", "N"),

  /**
   * The '<em><b>Maintenance</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MAINTENANCE_VALUE
   * @generated
   * @ordered
   */
  MAINTENANCE(5, "Maintenance", "M"),

  /**
   * The '<em><b>Continuous</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #CONTINUOUS_VALUE
   * @generated
   * @ordered
   */
  CONTINUOUS(0, "Continuous", "C"),

  /**
   * The '<em><b>Release</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #RELEASE_VALUE
   * @generated
   * @ordered
   */
  RELEASE(4, "Release", "R");

  /**
   * The '<em><b>Stable</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Stable</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #STABLE
   * @model name="Stable" literal="S"
   * @generated
   * @ordered
   */
  public static final int STABLE_VALUE = 3;

  /**
   * The '<em><b>Integration</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Integration</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #INTEGRATION
   * @model name="Integration" literal="I"
   * @generated
   * @ordered
   */
  public static final int INTEGRATION_VALUE = 2;

  /**
   * The '<em><b>Nightly</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Nightly</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #NIGHTLY
   * @model name="Nightly" literal="N"
   * @generated
   * @ordered
   */
  public static final int NIGHTLY_VALUE = 1;

  /**
   * The '<em><b>Maintenance</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Maintenance</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MAINTENANCE
   * @model name="Maintenance" literal="M"
   * @generated
   * @ordered
   */
  public static final int MAINTENANCE_VALUE = 5;

  /**
   * The '<em><b>Continuous</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Continuous</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #CONTINUOUS
   * @model name="Continuous" literal="C"
   * @generated
   * @ordered
   */
  public static final int CONTINUOUS_VALUE = 0;

  /**
   * The '<em><b>Release</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Release</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #RELEASE
   * @model name="Release" literal="R"
   * @generated
   * @ordered
   */
  public static final int RELEASE_VALUE = 4;

  /**
   * An array of all the '<em><b>Aggregate Type</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final AggregateType[] VALUES_ARRAY =
    new AggregateType[]
    {
      STABLE,
      INTEGRATION,
      NIGHTLY,
      MAINTENANCE,
      CONTINUOUS,
      RELEASE,
    };

  /**
   * A public read-only list of all the '<em><b>Aggregate Type</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<AggregateType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Aggregate Type</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static AggregateType get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      AggregateType result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Aggregate Type</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static AggregateType getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      AggregateType result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Aggregate Type</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static AggregateType get(int value)
  {
    switch (value)
    {
      case STABLE_VALUE: return STABLE;
      case INTEGRATION_VALUE: return INTEGRATION;
      case NIGHTLY_VALUE: return NIGHTLY;
      case MAINTENANCE_VALUE: return MAINTENANCE;
      case CONTINUOUS_VALUE: return CONTINUOUS;
      case RELEASE_VALUE: return RELEASE;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private AggregateType(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //AggregateType

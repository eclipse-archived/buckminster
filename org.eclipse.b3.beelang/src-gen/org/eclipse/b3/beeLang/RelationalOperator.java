/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Relational Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getRelationalOperator()
 * @model
 * @generated
 */
public enum RelationalOperator implements Enumerator
{
  /**
   * The '<em><b>EQ MATCHES</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQ_MATCHES_VALUE
   * @generated
   * @ordered
   */
  EQ_MATCHES(0, "EQ_MATCHES", "~="),

  /**
   * The '<em><b>EQ</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQ_VALUE
   * @generated
   * @ordered
   */
  EQ(1, "EQ", "=="),

  /**
   * The '<em><b>EQ IDENTITY</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQ_IDENTITY_VALUE
   * @generated
   * @ordered
   */
  EQ_IDENTITY(2, "EQ_IDENTITY", "==="),

  /**
   * The '<em><b>EQ NOT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQ_NOT_VALUE
   * @generated
   * @ordered
   */
  EQ_NOT(3, "EQ_NOT", "!="),

  /**
   * The '<em><b>EQ NOT IDENTITY</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQ_NOT_IDENTITY_VALUE
   * @generated
   * @ordered
   */
  EQ_NOT_IDENTITY(4, "EQ_NOT_IDENTITY", "!=="),

  /**
   * The '<em><b>EQ GTEQ</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQ_GTEQ_VALUE
   * @generated
   * @ordered
   */
  EQ_GTEQ(5, "EQ_GTEQ", ">="),

  /**
   * The '<em><b>EQ LEEQ</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQ_LEEQ_VALUE
   * @generated
   * @ordered
   */
  EQ_LEEQ(6, "EQ_LEEQ", "<="),

  /**
   * The '<em><b>EQ GT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQ_GT_VALUE
   * @generated
   * @ordered
   */
  EQ_GT(7, "EQ_GT", ">"),

  /**
   * The '<em><b>EQ LT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQ_LT_VALUE
   * @generated
   * @ordered
   */
  EQ_LT(8, "EQ_LT", "<"),

  /**
   * The '<em><b>EQ INSTANCEOF</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQ_INSTANCEOF_VALUE
   * @generated
   * @ordered
   */
  EQ_INSTANCEOF(9, "EQ_INSTANCEOF", "instanceof");

  /**
   * The '<em><b>EQ MATCHES</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EQ MATCHES</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EQ_MATCHES
   * @model literal="~="
   * @generated
   * @ordered
   */
  public static final int EQ_MATCHES_VALUE = 0;

  /**
   * The '<em><b>EQ</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EQ</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EQ
   * @model literal="=="
   * @generated
   * @ordered
   */
  public static final int EQ_VALUE = 1;

  /**
   * The '<em><b>EQ IDENTITY</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EQ IDENTITY</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EQ_IDENTITY
   * @model literal="==="
   * @generated
   * @ordered
   */
  public static final int EQ_IDENTITY_VALUE = 2;

  /**
   * The '<em><b>EQ NOT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EQ NOT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EQ_NOT
   * @model literal="!="
   * @generated
   * @ordered
   */
  public static final int EQ_NOT_VALUE = 3;

  /**
   * The '<em><b>EQ NOT IDENTITY</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EQ NOT IDENTITY</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EQ_NOT_IDENTITY
   * @model literal="!=="
   * @generated
   * @ordered
   */
  public static final int EQ_NOT_IDENTITY_VALUE = 4;

  /**
   * The '<em><b>EQ GTEQ</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EQ GTEQ</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EQ_GTEQ
   * @model literal=">="
   * @generated
   * @ordered
   */
  public static final int EQ_GTEQ_VALUE = 5;

  /**
   * The '<em><b>EQ LEEQ</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EQ LEEQ</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EQ_LEEQ
   * @model literal="<="
   * @generated
   * @ordered
   */
  public static final int EQ_LEEQ_VALUE = 6;

  /**
   * The '<em><b>EQ GT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EQ GT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EQ_GT
   * @model literal=">"
   * @generated
   * @ordered
   */
  public static final int EQ_GT_VALUE = 7;

  /**
   * The '<em><b>EQ LT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EQ LT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EQ_LT
   * @model literal="<"
   * @generated
   * @ordered
   */
  public static final int EQ_LT_VALUE = 8;

  /**
   * The '<em><b>EQ INSTANCEOF</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EQ INSTANCEOF</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EQ_INSTANCEOF
   * @model literal="instanceof"
   * @generated
   * @ordered
   */
  public static final int EQ_INSTANCEOF_VALUE = 9;

  /**
   * An array of all the '<em><b>Relational Operator</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final RelationalOperator[] VALUES_ARRAY =
    new RelationalOperator[]
    {
      EQ_MATCHES,
      EQ,
      EQ_IDENTITY,
      EQ_NOT,
      EQ_NOT_IDENTITY,
      EQ_GTEQ,
      EQ_LEEQ,
      EQ_GT,
      EQ_LT,
      EQ_INSTANCEOF,
    };

  /**
   * A public read-only list of all the '<em><b>Relational Operator</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<RelationalOperator> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Relational Operator</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static RelationalOperator get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      RelationalOperator result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Relational Operator</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static RelationalOperator getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      RelationalOperator result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Relational Operator</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static RelationalOperator get(int value)
  {
    switch (value)
    {
      case EQ_MATCHES_VALUE: return EQ_MATCHES;
      case EQ_VALUE: return EQ;
      case EQ_IDENTITY_VALUE: return EQ_IDENTITY;
      case EQ_NOT_VALUE: return EQ_NOT;
      case EQ_NOT_IDENTITY_VALUE: return EQ_NOT_IDENTITY;
      case EQ_GTEQ_VALUE: return EQ_GTEQ;
      case EQ_LEEQ_VALUE: return EQ_LEEQ;
      case EQ_GT_VALUE: return EQ_GT;
      case EQ_LT_VALUE: return EQ_LT;
      case EQ_INSTANCEOF_VALUE: return EQ_INSTANCEOF;
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
  private RelationalOperator(int value, String name, String literal)
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
  
} //RelationalOperator

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
 * A representation of the literals of the enumeration '<em><b>Assignment Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getAssignmentOperator()
 * @model
 * @generated
 */
public enum AssignmentOperator implements Enumerator
{
  /**
   * The '<em><b>SET</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SET_VALUE
   * @generated
   * @ordered
   */
  SET(0, "SET", "="),

  /**
   * The '<em><b>SET PLUS</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SET_PLUS_VALUE
   * @generated
   * @ordered
   */
  SET_PLUS(1, "SET_PLUS", "+="),

  /**
   * The '<em><b>SET MINUS</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SET_MINUS_VALUE
   * @generated
   * @ordered
   */
  SET_MINUS(2, "SET_MINUS", "-="),

  /**
   * The '<em><b>SET MUL</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SET_MUL_VALUE
   * @generated
   * @ordered
   */
  SET_MUL(3, "SET_MUL", "*="),

  /**
   * The '<em><b>SET DIV</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SET_DIV_VALUE
   * @generated
   * @ordered
   */
  SET_DIV(4, "SET_DIV", "/="),

  /**
   * The '<em><b>SET MOD</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SET_MOD_VALUE
   * @generated
   * @ordered
   */
  SET_MOD(5, "SET_MOD", "%="),

  /**
   * The '<em><b>SET AND</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SET_AND_VALUE
   * @generated
   * @ordered
   */
  SET_AND(6, "SET_AND", "&="),

  /**
   * The '<em><b>SET XOR</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SET_XOR_VALUE
   * @generated
   * @ordered
   */
  SET_XOR(7, "SET_XOR", "^="),

  /**
   * The '<em><b>SET OR</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SET_OR_VALUE
   * @generated
   * @ordered
   */
  SET_OR(8, "SET_OR", "|="),

  /**
   * The '<em><b>SET LSHIFT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SET_LSHIFT_VALUE
   * @generated
   * @ordered
   */
  SET_LSHIFT(9, "SET_LSHIFT", "<<="),

  /**
   * The '<em><b>SET RSHIFT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SET_RSHIFT_VALUE
   * @generated
   * @ordered
   */
  SET_RSHIFT(10, "SET_RSHIFT", ">>="),

  /**
   * The '<em><b>SET RSHIFT 0</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SET_RSHIFT_0_VALUE
   * @generated
   * @ordered
   */
  SET_RSHIFT_0(11, "SET_RSHIFT_0", ">>>=");

  /**
   * The '<em><b>SET</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SET</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SET
   * @model literal="="
   * @generated
   * @ordered
   */
  public static final int SET_VALUE = 0;

  /**
   * The '<em><b>SET PLUS</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SET PLUS</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SET_PLUS
   * @model literal="+="
   * @generated
   * @ordered
   */
  public static final int SET_PLUS_VALUE = 1;

  /**
   * The '<em><b>SET MINUS</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SET MINUS</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SET_MINUS
   * @model literal="-="
   * @generated
   * @ordered
   */
  public static final int SET_MINUS_VALUE = 2;

  /**
   * The '<em><b>SET MUL</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SET MUL</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SET_MUL
   * @model literal="*="
   * @generated
   * @ordered
   */
  public static final int SET_MUL_VALUE = 3;

  /**
   * The '<em><b>SET DIV</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SET DIV</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SET_DIV
   * @model literal="/="
   * @generated
   * @ordered
   */
  public static final int SET_DIV_VALUE = 4;

  /**
   * The '<em><b>SET MOD</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SET MOD</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SET_MOD
   * @model literal="%="
   * @generated
   * @ordered
   */
  public static final int SET_MOD_VALUE = 5;

  /**
   * The '<em><b>SET AND</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SET AND</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SET_AND
   * @model literal="&="
   * @generated
   * @ordered
   */
  public static final int SET_AND_VALUE = 6;

  /**
   * The '<em><b>SET XOR</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SET XOR</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SET_XOR
   * @model literal="^="
   * @generated
   * @ordered
   */
  public static final int SET_XOR_VALUE = 7;

  /**
   * The '<em><b>SET OR</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SET OR</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SET_OR
   * @model literal="|="
   * @generated
   * @ordered
   */
  public static final int SET_OR_VALUE = 8;

  /**
   * The '<em><b>SET LSHIFT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SET LSHIFT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SET_LSHIFT
   * @model literal="<<="
   * @generated
   * @ordered
   */
  public static final int SET_LSHIFT_VALUE = 9;

  /**
   * The '<em><b>SET RSHIFT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SET RSHIFT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SET_RSHIFT
   * @model literal=">>="
   * @generated
   * @ordered
   */
  public static final int SET_RSHIFT_VALUE = 10;

  /**
   * The '<em><b>SET RSHIFT 0</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>SET RSHIFT 0</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SET_RSHIFT_0
   * @model literal=">>>="
   * @generated
   * @ordered
   */
  public static final int SET_RSHIFT_0_VALUE = 11;

  /**
   * An array of all the '<em><b>Assignment Operator</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final AssignmentOperator[] VALUES_ARRAY =
    new AssignmentOperator[]
    {
      SET,
      SET_PLUS,
      SET_MINUS,
      SET_MUL,
      SET_DIV,
      SET_MOD,
      SET_AND,
      SET_XOR,
      SET_OR,
      SET_LSHIFT,
      SET_RSHIFT,
      SET_RSHIFT_0,
    };

  /**
   * A public read-only list of all the '<em><b>Assignment Operator</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<AssignmentOperator> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Assignment Operator</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static AssignmentOperator get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      AssignmentOperator result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Assignment Operator</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static AssignmentOperator getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      AssignmentOperator result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Assignment Operator</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static AssignmentOperator get(int value)
  {
    switch (value)
    {
      case SET_VALUE: return SET;
      case SET_PLUS_VALUE: return SET_PLUS;
      case SET_MINUS_VALUE: return SET_MINUS;
      case SET_MUL_VALUE: return SET_MUL;
      case SET_DIV_VALUE: return SET_DIV;
      case SET_MOD_VALUE: return SET_MOD;
      case SET_AND_VALUE: return SET_AND;
      case SET_XOR_VALUE: return SET_XOR;
      case SET_OR_VALUE: return SET_OR;
      case SET_LSHIFT_VALUE: return SET_LSHIFT;
      case SET_RSHIFT_VALUE: return SET_RSHIFT;
      case SET_RSHIFT_0_VALUE: return SET_RSHIFT_0;
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
  private AssignmentOperator(int value, String name, String literal)
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
  
} //AssignmentOperator

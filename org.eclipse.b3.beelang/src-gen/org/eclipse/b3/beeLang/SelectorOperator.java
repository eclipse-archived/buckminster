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
 * A representation of the literals of the enumeration '<em><b>Selector Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getSelectorOperator()
 * @model
 * @generated
 */
public enum SelectorOperator implements Enumerator
{
  /**
   * The '<em><b>THIS</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #THIS_VALUE
   * @generated
   * @ordered
   */
  THIS(0, "THIS", "."),

  /**
   * The '<em><b>PARENT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #PARENT_VALUE
   * @generated
   * @ordered
   */
  PARENT(1, "PARENT", ".."),

  /**
   * The '<em><b>CHILDREN</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #CHILDREN_VALUE
   * @generated
   * @ordered
   */
  CHILDREN(2, "CHILDREN", "*"),

  /**
   * The '<em><b>ANSCESTORS</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ANSCESTORS_VALUE
   * @generated
   * @ordered
   */
  ANSCESTORS(3, "ANSCESTORS", "**");

  /**
   * The '<em><b>THIS</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>THIS</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #THIS
   * @model literal="."
   * @generated
   * @ordered
   */
  public static final int THIS_VALUE = 0;

  /**
   * The '<em><b>PARENT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>PARENT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #PARENT
   * @model literal=".."
   * @generated
   * @ordered
   */
  public static final int PARENT_VALUE = 1;

  /**
   * The '<em><b>CHILDREN</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>CHILDREN</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #CHILDREN
   * @model literal="*"
   * @generated
   * @ordered
   */
  public static final int CHILDREN_VALUE = 2;

  /**
   * The '<em><b>ANSCESTORS</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>ANSCESTORS</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #ANSCESTORS
   * @model literal="**"
   * @generated
   * @ordered
   */
  public static final int ANSCESTORS_VALUE = 3;

  /**
   * An array of all the '<em><b>Selector Operator</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final SelectorOperator[] VALUES_ARRAY =
    new SelectorOperator[]
    {
      THIS,
      PARENT,
      CHILDREN,
      ANSCESTORS,
    };

  /**
   * A public read-only list of all the '<em><b>Selector Operator</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<SelectorOperator> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Selector Operator</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SelectorOperator get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      SelectorOperator result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Selector Operator</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SelectorOperator getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      SelectorOperator result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Selector Operator</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SelectorOperator get(int value)
  {
    switch (value)
    {
      case THIS_VALUE: return THIS;
      case PARENT_VALUE: return PARENT;
      case CHILDREN_VALUE: return CHILDREN;
      case ANSCESTORS_VALUE: return ANSCESTORS;
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
  private SelectorOperator(int value, String name, String literal)
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
  
} //SelectorOperator

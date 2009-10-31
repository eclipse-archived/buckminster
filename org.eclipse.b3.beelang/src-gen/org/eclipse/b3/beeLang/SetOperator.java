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
 * A representation of the literals of the enumeration '<em><b>Set Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getSetOperator()
 * @model
 * @generated
 */
public enum SetOperator implements Enumerator
{
  /**
   * The '<em><b>Select</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SELECT_VALUE
   * @generated
   * @ordered
   */
  SELECT(0, "select", "select"),

  /**
   * The '<em><b>Collect</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #COLLECT_VALUE
   * @generated
   * @ordered
   */
  COLLECT(1, "collect", "collect"),

  /**
   * The '<em><b>Reject</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #REJECT_VALUE
   * @generated
   * @ordered
   */
  REJECT(2, "reject", "reject"),

  /**
   * The '<em><b>Exists</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EXISTS_VALUE
   * @generated
   * @ordered
   */
  EXISTS(3, "exists", "exists"),

  /**
   * The '<em><b>Notexists</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NOTEXISTS_VALUE
   * @generated
   * @ordered
   */
  NOTEXISTS(4, "notexists", "notexists"),

  /**
   * The '<em><b>Foreach</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FOREACH_VALUE
   * @generated
   * @ordered
   */
  FOREACH(5, "foreach", "foreach");

  /**
   * The '<em><b>Select</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Select</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SELECT
   * @model name="select"
   * @generated
   * @ordered
   */
  public static final int SELECT_VALUE = 0;

  /**
   * The '<em><b>Collect</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Collect</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #COLLECT
   * @model name="collect"
   * @generated
   * @ordered
   */
  public static final int COLLECT_VALUE = 1;

  /**
   * The '<em><b>Reject</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Reject</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #REJECT
   * @model name="reject"
   * @generated
   * @ordered
   */
  public static final int REJECT_VALUE = 2;

  /**
   * The '<em><b>Exists</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Exists</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EXISTS
   * @model name="exists"
   * @generated
   * @ordered
   */
  public static final int EXISTS_VALUE = 3;

  /**
   * The '<em><b>Notexists</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Notexists</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #NOTEXISTS
   * @model name="notexists"
   * @generated
   * @ordered
   */
  public static final int NOTEXISTS_VALUE = 4;

  /**
   * The '<em><b>Foreach</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Foreach</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FOREACH
   * @model name="foreach"
   * @generated
   * @ordered
   */
  public static final int FOREACH_VALUE = 5;

  /**
   * An array of all the '<em><b>Set Operator</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final SetOperator[] VALUES_ARRAY =
    new SetOperator[]
    {
      SELECT,
      COLLECT,
      REJECT,
      EXISTS,
      NOTEXISTS,
      FOREACH,
    };

  /**
   * A public read-only list of all the '<em><b>Set Operator</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<SetOperator> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Set Operator</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SetOperator get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      SetOperator result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Set Operator</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SetOperator getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      SetOperator result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Set Operator</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SetOperator get(int value)
  {
    switch (value)
    {
      case SELECT_VALUE: return SELECT;
      case COLLECT_VALUE: return COLLECT;
      case REJECT_VALUE: return REJECT;
      case EXISTS_VALUE: return EXISTS;
      case NOTEXISTS_VALUE: return NOTEXISTS;
      case FOREACH_VALUE: return FOREACH;
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
  private SetOperator(int value, String name, String literal)
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
  
} //SetOperator

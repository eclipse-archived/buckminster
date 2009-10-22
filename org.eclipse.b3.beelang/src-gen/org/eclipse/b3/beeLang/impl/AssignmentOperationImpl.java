/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.AssignmentOperation;
import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Expression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assignment Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.AssignmentOperationImpl#getLeft <em>Left</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.AssignmentOperationImpl#getOp <em>Op</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.AssignmentOperationImpl#getRight <em>Right</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssignmentOperationImpl extends ExpressionImpl implements AssignmentOperation
{
  /**
   * The cached value of the '{@link #getLeft() <em>Left</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLeft()
   * @generated
   * @ordered
   */
  protected EList<Expression> left;

  /**
   * The default value of the '{@link #getOp() <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected static final String OP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOp() <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected String op = OP_EDEFAULT;

  /**
   * The cached value of the '{@link #getRight() <em>Right</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRight()
   * @generated
   * @ordered
   */
  protected EList<Expression> right;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AssignmentOperationImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return BeeLangPackage.Literals.ASSIGNMENT_OPERATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Expression> getLeft()
  {
    if (left == null)
    {
      left = new EObjectContainmentEList<Expression>(Expression.class, this, BeeLangPackage.ASSIGNMENT_OPERATION__LEFT);
    }
    return left;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOp()
  {
    return op;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOp(String newOp)
  {
    String oldOp = op;
    op = newOp;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.ASSIGNMENT_OPERATION__OP, oldOp, op));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Expression> getRight()
  {
    if (right == null)
    {
      right = new EObjectContainmentEList<Expression>(Expression.class, this, BeeLangPackage.ASSIGNMENT_OPERATION__RIGHT);
    }
    return right;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case BeeLangPackage.ASSIGNMENT_OPERATION__LEFT:
        return ((InternalEList<?>)getLeft()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.ASSIGNMENT_OPERATION__RIGHT:
        return ((InternalEList<?>)getRight()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case BeeLangPackage.ASSIGNMENT_OPERATION__LEFT:
        return getLeft();
      case BeeLangPackage.ASSIGNMENT_OPERATION__OP:
        return getOp();
      case BeeLangPackage.ASSIGNMENT_OPERATION__RIGHT:
        return getRight();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case BeeLangPackage.ASSIGNMENT_OPERATION__LEFT:
        getLeft().clear();
        getLeft().addAll((Collection<? extends Expression>)newValue);
        return;
      case BeeLangPackage.ASSIGNMENT_OPERATION__OP:
        setOp((String)newValue);
        return;
      case BeeLangPackage.ASSIGNMENT_OPERATION__RIGHT:
        getRight().clear();
        getRight().addAll((Collection<? extends Expression>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case BeeLangPackage.ASSIGNMENT_OPERATION__LEFT:
        getLeft().clear();
        return;
      case BeeLangPackage.ASSIGNMENT_OPERATION__OP:
        setOp(OP_EDEFAULT);
        return;
      case BeeLangPackage.ASSIGNMENT_OPERATION__RIGHT:
        getRight().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case BeeLangPackage.ASSIGNMENT_OPERATION__LEFT:
        return left != null && !left.isEmpty();
      case BeeLangPackage.ASSIGNMENT_OPERATION__OP:
        return OP_EDEFAULT == null ? op != null : !OP_EDEFAULT.equals(op);
      case BeeLangPackage.ASSIGNMENT_OPERATION__RIGHT:
        return right != null && !right.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (op: ");
    result.append(op);
    result.append(')');
    return result.toString();
  }

} //AssignmentOperationImpl

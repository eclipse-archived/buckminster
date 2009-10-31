/**
 * <copyright>
 * </copyright>
 *
 */
package beelangtypes.impl;

import beelangtypes.*;

import org.eclipse.b3.HexInteger;
import org.eclipse.b3.RegularExpression;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BeelangtypesFactoryImpl extends EFactoryImpl implements BeelangtypesFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static BeelangtypesFactory init()
  {
    try
    {
      BeelangtypesFactory theBeelangtypesFactory = (BeelangtypesFactory)EPackage.Registry.INSTANCE.getEFactory("http://beelangtypes/1.0"); 
      if (theBeelangtypesFactory != null)
      {
        return theBeelangtypesFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new BeelangtypesFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BeelangtypesFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case BeelangtypesPackage.HEX_INTEGER:
        return createHexIntegerFromString(eDataType, initialValue);
      case BeelangtypesPackage.REGULAR_EXPRESSION:
        return createRegularExpressionFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case BeelangtypesPackage.HEX_INTEGER:
        return convertHexIntegerToString(eDataType, instanceValue);
      case BeelangtypesPackage.REGULAR_EXPRESSION:
        return convertRegularExpressionToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HexInteger createHexIntegerFromString(EDataType eDataType, String initialValue)
  {
    return (HexInteger)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertHexIntegerToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RegularExpression createRegularExpressionFromString(EDataType eDataType, String initialValue)
  {
    return (RegularExpression)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertRegularExpressionToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BeelangtypesPackage getBeelangtypesPackage()
  {
    return (BeelangtypesPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static BeelangtypesPackage getPackage()
  {
    return BeelangtypesPackage.eINSTANCE;
  }

} //BeelangtypesFactoryImpl

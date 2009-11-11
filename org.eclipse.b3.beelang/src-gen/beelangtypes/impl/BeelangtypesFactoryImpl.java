/**
 * <copyright>
 * </copyright>
 *
 */
package beelangtypes.impl;

import beelangtypes.*;

import java.net.URI;

import org.eclipse.b3.RadixInteger;
import org.eclipse.b3.RegularExpression;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;

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
      case BeelangtypesPackage.RADIX_INTEGER:
        return createRadixIntegerFromString(eDataType, initialValue);
      case BeelangtypesPackage.REGULAR_EXPRESSION:
        return createRegularExpressionFromString(eDataType, initialValue);
      case BeelangtypesPackage.URI:
        return createURIFromString(eDataType, initialValue);
      case BeelangtypesPackage.VERSION_RANGE:
        return createVersionRangeFromString(eDataType, initialValue);
      case BeelangtypesPackage.VERSION:
        return createVersionFromString(eDataType, initialValue);
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
      case BeelangtypesPackage.RADIX_INTEGER:
        return convertRadixIntegerToString(eDataType, instanceValue);
      case BeelangtypesPackage.REGULAR_EXPRESSION:
        return convertRegularExpressionToString(eDataType, instanceValue);
      case BeelangtypesPackage.URI:
        return convertURIToString(eDataType, instanceValue);
      case BeelangtypesPackage.VERSION_RANGE:
        return convertVersionRangeToString(eDataType, instanceValue);
      case BeelangtypesPackage.VERSION:
        return convertVersionToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RadixInteger createRadixIntegerFromString(EDataType eDataType, String initialValue)
  {
    return (RadixInteger)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertRadixIntegerToString(EDataType eDataType, Object instanceValue)
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
  public URI createURIFromString(EDataType eDataType, String initialValue)
  {
    return (URI)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertURIToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VersionRange createVersionRangeFromString(EDataType eDataType, String initialValue)
  {
    return (VersionRange)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertVersionRangeToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(eDataType, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Version createVersionFromString(EDataType eDataType, String initialValue)
  {
    return (Version)super.createFromString(eDataType, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertVersionToString(EDataType eDataType, Object instanceValue)
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

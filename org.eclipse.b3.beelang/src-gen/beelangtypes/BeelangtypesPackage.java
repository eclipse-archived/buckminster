/**
 * <copyright>
 * </copyright>
 *
 */
package beelangtypes;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see beelangtypes.BeelangtypesFactory
 * @model kind="package"
 * @generated
 */
public interface BeelangtypesPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "beelangtypes";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://beelangtypes/1.0";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "beelangtypes";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  BeelangtypesPackage eINSTANCE = beelangtypes.impl.BeelangtypesPackageImpl.init();

  /**
   * The meta object id for the '<em>Radix Integer</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.RadixInteger
   * @see beelangtypes.impl.BeelangtypesPackageImpl#getRadixInteger()
   * @generated
   */
  int RADIX_INTEGER = 0;

  /**
   * The meta object id for the '<em>Regular Expression</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.RegularExpression
   * @see beelangtypes.impl.BeelangtypesPackageImpl#getRegularExpression()
   * @generated
   */
  int REGULAR_EXPRESSION = 1;

  /**
   * The meta object id for the '<em>URI</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.net.URI
   * @see beelangtypes.impl.BeelangtypesPackageImpl#getURI()
   * @generated
   */
  int URI = 2;

  /**
   * The meta object id for the '<em>Version Range</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.equinox.internal.provisional.p2.core.VersionRange
   * @see beelangtypes.impl.BeelangtypesPackageImpl#getVersionRange()
   * @generated
   */
  int VERSION_RANGE = 3;

  /**
   * The meta object id for the '<em>Version</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.equinox.internal.provisional.p2.core.Version
   * @see beelangtypes.impl.BeelangtypesPackageImpl#getVersion()
   * @generated
   */
  int VERSION = 4;


  /**
   * Returns the meta object for data type '{@link org.eclipse.b3.RadixInteger <em>Radix Integer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Radix Integer</em>'.
   * @see org.eclipse.b3.RadixInteger
   * @model instanceClass="org.eclipse.b3.RadixInteger"
   * @generated
   */
  EDataType getRadixInteger();

  /**
   * Returns the meta object for data type '{@link org.eclipse.b3.RegularExpression <em>Regular Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Regular Expression</em>'.
   * @see org.eclipse.b3.RegularExpression
   * @model instanceClass="org.eclipse.b3.RegularExpression"
   * @generated
   */
  EDataType getRegularExpression();

  /**
   * Returns the meta object for data type '{@link java.net.URI <em>URI</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>URI</em>'.
   * @see java.net.URI
   * @model instanceClass="java.net.URI"
   * @generated
   */
  EDataType getURI();

  /**
   * Returns the meta object for data type '{@link org.eclipse.equinox.internal.provisional.p2.core.VersionRange <em>Version Range</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Version Range</em>'.
   * @see org.eclipse.equinox.internal.provisional.p2.core.VersionRange
   * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.core.VersionRange"
   * @generated
   */
  EDataType getVersionRange();

  /**
   * Returns the meta object for data type '{@link org.eclipse.equinox.internal.provisional.p2.core.Version <em>Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Version</em>'.
   * @see org.eclipse.equinox.internal.provisional.p2.core.Version
   * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.core.Version"
   * @generated
   */
  EDataType getVersion();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  BeelangtypesFactory getBeelangtypesFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '<em>Radix Integer</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.RadixInteger
     * @see beelangtypes.impl.BeelangtypesPackageImpl#getRadixInteger()
     * @generated
     */
    EDataType RADIX_INTEGER = eINSTANCE.getRadixInteger();

    /**
     * The meta object literal for the '<em>Regular Expression</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.RegularExpression
     * @see beelangtypes.impl.BeelangtypesPackageImpl#getRegularExpression()
     * @generated
     */
    EDataType REGULAR_EXPRESSION = eINSTANCE.getRegularExpression();

    /**
     * The meta object literal for the '<em>URI</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.net.URI
     * @see beelangtypes.impl.BeelangtypesPackageImpl#getURI()
     * @generated
     */
    EDataType URI = eINSTANCE.getURI();

    /**
     * The meta object literal for the '<em>Version Range</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.equinox.internal.provisional.p2.core.VersionRange
     * @see beelangtypes.impl.BeelangtypesPackageImpl#getVersionRange()
     * @generated
     */
    EDataType VERSION_RANGE = eINSTANCE.getVersionRange();

    /**
     * The meta object literal for the '<em>Version</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.equinox.internal.provisional.p2.core.Version
     * @see beelangtypes.impl.BeelangtypesPackageImpl#getVersion()
     * @generated
     */
    EDataType VERSION = eINSTANCE.getVersion();

  }

} //BeelangtypesPackage

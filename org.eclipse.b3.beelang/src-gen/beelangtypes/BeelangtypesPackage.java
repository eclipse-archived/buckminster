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
   * The meta object id for the '<em>Hex Integer</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.HexInteger
   * @see beelangtypes.impl.BeelangtypesPackageImpl#getHexInteger()
   * @generated
   */
  int HEX_INTEGER = 0;

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
   * Returns the meta object for data type '{@link org.eclipse.b3.HexInteger <em>Hex Integer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Hex Integer</em>'.
   * @see org.eclipse.b3.HexInteger
   * @model instanceClass="org.eclipse.b3.HexInteger"
   * @generated
   */
  EDataType getHexInteger();

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
     * The meta object literal for the '<em>Hex Integer</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.HexInteger
     * @see beelangtypes.impl.BeelangtypesPackageImpl#getHexInteger()
     * @generated
     */
    EDataType HEX_INTEGER = eINSTANCE.getHexInteger();

    /**
     * The meta object literal for the '<em>Regular Expression</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.RegularExpression
     * @see beelangtypes.impl.BeelangtypesPackageImpl#getRegularExpression()
     * @generated
     */
    EDataType REGULAR_EXPRESSION = eINSTANCE.getRegularExpression();

  }

} //BeelangtypesPackage

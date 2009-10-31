/**
 * <copyright>
 * </copyright>
 *
 */
package beelangtypes;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see beelangtypes.BeelangtypesPackage
 * @generated
 */
public interface BeelangtypesFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  BeelangtypesFactory eINSTANCE = beelangtypes.impl.BeelangtypesFactoryImpl.init();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  BeelangtypesPackage getBeelangtypesPackage();

} //BeelangtypesFactory

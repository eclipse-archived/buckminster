/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Mapped Unit</b></em>'.
 * 
 * @extends StatusProvider <!-- end-user-doc -->
 * 
 *          <p>
 *          The following features are supported:
 *          <ul>
 *          <li>{@link org.eclipse.buckminster.aggregator.MappedUnit#getValidConfigurations <em>Valid Configurations
 *          </em>}</li>
 *          </ul>
 *          </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMappedUnit()
 * @model abstract="true"
 * @generated
 */
public interface MappedUnit extends InstallableUnitReference, EnabledStatusProvider, StatusProvider
{
	/**
	 * Returns the value of the '<em><b>Valid Configurations</b></em>' reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.Configuration}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Valid Configurations</em>' reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Valid Configurations</em>' reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMappedUnit_ValidConfigurations()
	 * @model keys="operatingSystem windowSystem architecture"
	 * @generated
	 */
	EList<Configuration> getValidConfigurations();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	boolean isBranchEnabled();

} // MappedUnit

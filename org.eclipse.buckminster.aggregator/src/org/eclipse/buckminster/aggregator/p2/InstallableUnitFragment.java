/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2;

import org.eclipse.emf.common.util.EList;

import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Installable Unit Fragment</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.InstallableUnitFragment#getHostList <em>Host List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getInstallableUnitFragment()
 * @model superTypes="org.eclipse.buckminster.aggregator.p2.InstallableUnit org.eclipse.buckminster.aggregator.p2.IInstallableUnitFragment"
 * @generated
 */
public interface InstallableUnitFragment extends InstallableUnit, IInstallableUnitFragment
{
	/**
	 * Returns the value of the '<em><b>Host List</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.aggregator.p2.RequiredCapability}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Host List</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Host List</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getInstallableUnitFragment_HostList()
	 * @model containment="true"
	 * @generated
	 */
	EList<RequiredCapability> getHostList();

} // InstallableUnitFragment

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import org.eclipse.buckminster.model.common.ComponentRequest;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>IContext</b></em>'. <!-- end-user-doc -->
 * 
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getIContext()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IContext extends EObject {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	CSpec findBestMatch(ComponentRequest request);

} // IContext

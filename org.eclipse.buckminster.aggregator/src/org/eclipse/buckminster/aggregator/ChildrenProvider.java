/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Children Provider</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.ChildrenProvider#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getChildrenProvider()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ChildrenProvider<T> extends EObject
{
	/**
	 * Returns the value of the '<em><b>Children</b></em>' reference list. The list contents are of type {@link T}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Children</em>' reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getChildrenProvider_Children()
	 * @model kind="reference" resolveProxies="false" changeable="false" volatile="true"
	 * @generated
	 */
	EList<T> getChildren();

} // ChildrenProvider

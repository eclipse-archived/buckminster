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

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Valid Configurations Rule</b></em>'. <!--
 * end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.ValidConfigurationsRule#getValidConfigurations <em>Valid Configurations
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getValidConfigurationsRule()
 * @model
 * @generated
 */
public interface ValidConfigurationsRule extends MapRule
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
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getValidConfigurationsRule_ValidConfigurations()
	 * @model
	 * @generated
	 */
	EList<Configuration> getValidConfigurations();

} // ValidConfigurationsRule

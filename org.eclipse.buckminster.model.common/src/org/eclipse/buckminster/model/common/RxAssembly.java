/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import java.lang.CharSequence;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Rx Assembly</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.RxAssembly#getPattern <em>Pattern</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.model.common.CommonPackage#getRxAssembly()
 * @model
 * @generated
 */
public interface RxAssembly extends RxGroup {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model inputDataType="org.eclipse.buckminster.model.common.CharSequence"
	 * @generated
	 */
	Map<String, String> getMatchMap(CharSequence input);

	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pattern</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Pattern</em>' attribute.
	 * @see #isSetPattern()
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getRxAssembly_Pattern()
	 * @model unsettable="true"
	 *        dataType="org.eclipse.buckminster.model.common.Pattern"
	 *        transient="true" changeable="false"
	 * @generated
	 */
	Pattern getPattern();

} // RxAssembly

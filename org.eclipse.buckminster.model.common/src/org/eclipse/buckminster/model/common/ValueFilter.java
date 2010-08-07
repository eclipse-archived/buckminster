/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Value Filter</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.ValueFilter#getMultiValueGroup <em>Multi Value Group</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.ValueFilter#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.model.common.CommonPackage#getValueFilter()
 * @model abstract="true"
 * @generated
 */
public interface ValueFilter extends Value {
	/**
	 * Returns the value of the '<em><b>Multi Value Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Multi Value Group</em>' attribute list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multi Value Group</em>' attribute list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getValueFilter_MultiValueGroup()
	 * @model dataType="org.eclipse.emf.ecore.EFeatureMapEntry" required="true" many="true"
	 *        extendedMetaData="kind='group' name='basicValue:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getMultiValueGroup();

	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.eclipse.buckminster.model.common.Value}&lt;?>. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getValueFilter_Values()
	 * @model containment="true" required="true" transient="true"
	 *        changeable="false" volatile="true" derived="true"
	 *        extendedMetaData=
	 *        "kind='element' name='value' namespace='http://www.eclipse.org/buckminster/Common-1.0' group='http://www.eclipse.org/buckminster/Common-1.0#basicProperty:group'"
	 * @generated
	 */
	EList<Value> getValues();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="org.eclipse.buckminster.model.common.List<org.eclipse.emf.ecore.EString>" many="false"
	 * @generated
	 */
	List<String> getValues(Map<String, String> properties);

} // ValueFilter

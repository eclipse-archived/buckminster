/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.model.common.ComponentName;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Matcher</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.rmap.Matcher#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.Matcher#getComponentTypesAttr <em>Component Types Attr</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.Matcher#getComponentTypes <em>Component Types</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.Matcher#getResolutionFilter <em>Resolution Filter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.rmap.RmapPackage#getMatcher()
 * @model abstract="true"
 * @generated
 */
public interface Matcher extends EObject {
	/**
	 * Returns the value of the '<em><b>Component Types</b></em>' attribute
	 * list. The list contents are of type {@link java.lang.String}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Types</em>' attribute list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Component Types</em>' attribute list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getMatcher_ComponentTypes()
	 * @model default="" transient="true"
	 * @generated
	 */
	EList<String> getComponentTypes();

	/**
	 * Returns the value of the '<em><b>Resolution Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolution Filter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolution Filter</em>' attribute.
	 * @see #setResolutionFilter(Filter)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getMatcher_ResolutionFilter()
	 * @model dataType="org.eclipse.buckminster.model.common.Filter"
	 * @generated
	 */
	Filter getResolutionFilter();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Matcher#getResolutionFilter <em>Resolution Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolution Filter</em>' attribute.
	 * @see #getResolutionFilter()
	 * @generated
	 */
	void setResolutionFilter(Filter value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean matches(ComponentName componentName, Map<String, String> properties);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	ResourceMap getResourceMap();

	/**
	 * Returns the value of the '<em><b>Component Types Attr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Types Attr</em>' attribute isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Types Attr</em>' attribute.
	 * @see #setComponentTypesAttr(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getMatcher_ComponentTypesAttr()
	 * @model volatile="true" derived="true"
	 *        extendedMetaData="name='componentTypes' kind='attribute'"
	 * @generated
	 */
	String getComponentTypesAttr();

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
	 * @see #setPattern(Pattern)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getMatcher_Pattern()
	 * @model dataType="org.eclipse.buckminster.model.common.Pattern"
	 * @generated
	 */
	Pattern getPattern();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Matcher#getComponentTypesAttr <em>Component Types Attr</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Component Types Attr</em>' attribute.
	 * @see #getComponentTypesAttr()
	 * @generated
	 */
	void setComponentTypesAttr(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Matcher#getPattern <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pattern</em>' attribute.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(Pattern value);

} // Matcher

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.Format;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Provider</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.Provider#getComponentTypes <em>
 * Component Types</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Provider#getComponentTypesAttr <em>
 * Component Types Attr</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Provider#getReaderType <em>Reader
 * Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Provider#isSource <em>Source</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Provider#isMutable <em>Mutable</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Provider#getResolutionFilter <em>
 * Resolution Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Provider#getVersionConverter <em>
 * Version Converter</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Provider#getUri <em>Uri</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Provider#getMatchers <em>Matchers
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Provider#getDocumentation <em>
 * Documentation</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider()
 * @model
 * @generated
 */
public interface Provider extends Properties {
	/**
	 * Returns the value of the '<em><b>Component Types</b></em>' attribute
	 * list. The list contents are of type {@link java.lang.String}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Component Types</em>' attribute list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_ComponentTypes()
	 * @model default="" transient="true"
	 * @generated
	 */
	EList<String> getComponentTypes();

	/**
	 * Returns the value of the '<em><b>Component Types Attr</b></em>'
	 * attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Types Attr</em>' attribute isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Component Types Attr</em>' attribute.
	 * @see #setComponentTypesAttr(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_ComponentTypesAttr()
	 * @model volatile="true" derived="true"
	 *        extendedMetaData="name='componentTypes' kind='attribute'"
	 * @generated
	 */
	String getComponentTypesAttr();

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Documentation</em>' containment reference.
	 * @see #setDocumentation(Documentation)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_Documentation()
	 * @model containment="true" extendedMetaData=
	 *        "name='documentation' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	Documentation getDocumentation();

	/**
	 * Returns the value of the '<em><b>Matchers</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.eclipse.buckminster.rmap.URIMatcher}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Matchers</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Matchers</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_Matchers()
	 * @model containment="true" extendedMetaData=
	 *        "name='matcher' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<URIMatcher> getMatchers();

	/**
	 * Returns the value of the '<em><b>Reader Type</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reader Type</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Reader Type</em>' attribute.
	 * @see #setReaderType(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_ReaderType()
	 * @model required="true"
	 * @generated
	 */
	String getReaderType();

	/**
	 * Returns the value of the '<em><b>Resolution Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolution Filter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Resolution Filter</em>' attribute.
	 * @see #setResolutionFilter(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_ResolutionFilter()
	 * @model
	 * @generated
	 */
	String getResolutionFilter();

	/**
	 * Returns the value of the '<em><b>Uri</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uri</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Uri</em>' containment reference.
	 * @see #setUri(Format)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_Uri()
	 * @model containment="true" required="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	Format getUri();

	/**
	 * Returns the value of the '<em><b>Version Converter</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version Converter</em>' containment reference
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version Converter</em>' containment
	 *         reference.
	 * @see #setVersionConverter(VersionConverter)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_VersionConverter()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	VersionConverter getVersionConverter();

	/**
	 * Returns the value of the '<em><b>Mutable</b></em>' attribute. The default
	 * value is <code>"true"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mutable</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Mutable</em>' attribute.
	 * @see #setMutable(boolean)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_Mutable()
	 * @model default="true"
	 * @generated
	 */
	boolean isMutable();

	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute. The default
	 * value is <code>"true"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(boolean)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_Source()
	 * @model default="true"
	 * @generated
	 */
	boolean isSource();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Provider#getComponentTypesAttr
	 * <em>Component Types Attr</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Component Types Attr</em>'
	 *            attribute.
	 * @see #getComponentTypesAttr()
	 * @generated
	 */
	void setComponentTypesAttr(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Provider#getDocumentation
	 * <em>Documentation</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Documentation</em>' containment
	 *            reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(Documentation value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Provider#isMutable <em>Mutable</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Mutable</em>' attribute.
	 * @see #isMutable()
	 * @generated
	 */
	void setMutable(boolean value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Provider#getReaderType
	 * <em>Reader Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Reader Type</em>' attribute.
	 * @see #getReaderType()
	 * @generated
	 */
	void setReaderType(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Provider#getResolutionFilter
	 * <em>Resolution Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Resolution Filter</em>' attribute.
	 * @see #getResolutionFilter()
	 * @generated
	 */
	void setResolutionFilter(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Provider#isSource <em>Source</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Source</em>' attribute.
	 * @see #isSource()
	 * @generated
	 */
	void setSource(boolean value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Provider#getUri <em>Uri</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Uri</em>' containment reference.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(Format value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Provider#getVersionConverter
	 * <em>Version Converter</em>}' containment reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version Converter</em>' containment
	 *            reference.
	 * @see #getVersionConverter()
	 * @generated
	 */
	void setVersionConverter(VersionConverter value);

} // Provider

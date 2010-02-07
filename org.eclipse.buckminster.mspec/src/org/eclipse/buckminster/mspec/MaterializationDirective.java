/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec;

import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.Property;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Materialization Directive</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getPropertyGroup
 * <em>Property Group</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getProperties
 * <em>Properties</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getConflictResolution
 * <em>Conflict Resolution</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getInstallLocation
 * <em>Install Location</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getMaterializer
 * <em>Materializer</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getWorkspaceLocation
 * <em>Workspace Location</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationDirective()
 * @model
 * @generated
 */
public interface MaterializationDirective extends EObject {
	/**
	 * Returns the value of the '<em><b>Conflict Resolution</b></em>' attribute.
	 * The default value is <code>"UPDATE"</code>. The literals are from the
	 * enumeration {@link org.eclipse.buckminster.mspec.ConflictResolution}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conflict Resolution</em>' attribute isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Conflict Resolution</em>' attribute.
	 * @see org.eclipse.buckminster.mspec.ConflictResolution
	 * @see #setConflictResolution(ConflictResolution)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationDirective_ConflictResolution()
	 * @model default="UPDATE"
	 * @generated
	 */
	ConflictResolution getConflictResolution();

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
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationDirective_Documentation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	Documentation getDocumentation();

	/**
	 * Returns the value of the '<em><b>Install Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Install Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Install Location</em>' attribute.
	 * @see #setInstallLocation(String)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationDirective_InstallLocation()
	 * @model
	 * @generated
	 */
	String getInstallLocation();

	/**
	 * Returns the value of the '<em><b>Materializer</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
	 * materializer to use. Possible values includes &quot;filesystem&quot;,
	 * &quot;workspace&quot;, and &quot;p2&quot; but other might be added
	 * through the buckminster.materializers extension point. <!-- end-model-doc
	 * -->
	 * 
	 * @return the value of the '<em>Materializer</em>' attribute.
	 * @see #setMaterializer(String)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationDirective_Materializer()
	 * @model
	 * @generated
	 */
	String getMaterializer();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.model.common.Property}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Properties</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationDirective_Properties()
	 * @model containment="true" required="true" transient="true"
	 *        changeable="false" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='basicProperty' namespace='http://www.eclipse.org/buckminster/Common-1.0' group='http://www.eclipse.org/buckminster/Common-1.0#basicProperty:group'"
	 * @generated
	 */
	EList<Property> getProperties();

	/**
	 * Returns the value of the '<em><b>Property Group</b></em>' attribute list.
	 * The list contents are of type
	 * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Property Group</em>' attribute list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Property Group</em>' attribute list.
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationDirective_PropertyGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
	 *        required="true" many="true" extendedMetaData ="kind='group' name='basicProperty:group' namespace='http://www.eclipse.org/buckminster/Common-1.0'"
	 * @generated
	 */
	FeatureMap getPropertyGroup();

	/**
	 * Returns the value of the '<em><b>Workspace Location</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * Path to the designated workspace. Only in effect for materializers that
	 * has a conceptual understanding of a workspace. Defaults to the
	 * installLocation. In a node, this path can be relative to the global path.
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Workspace Location</em>' attribute.
	 * @see #setWorkspaceLocation(String)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationDirective_WorkspaceLocation()
	 * @model
	 * @generated
	 */
	String getWorkspaceLocation();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getConflictResolution
	 * <em>Conflict Resolution</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Conflict Resolution</em>' attribute.
	 * @see org.eclipse.buckminster.mspec.ConflictResolution
	 * @see #getConflictResolution()
	 * @generated
	 */
	void setConflictResolution(ConflictResolution value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getDocumentation
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
	 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getInstallLocation
	 * <em>Install Location</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Install Location</em>' attribute.
	 * @see #getInstallLocation()
	 * @generated
	 */
	void setInstallLocation(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getMaterializer
	 * <em>Materializer</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Materializer</em>' attribute.
	 * @see #getMaterializer()
	 * @generated
	 */
	void setMaterializer(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getWorkspaceLocation
	 * <em>Workspace Location</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Workspace Location</em>' attribute.
	 * @see #getWorkspaceLocation()
	 * @generated
	 */
	void setWorkspaceLocation(String value);

} // MaterializationDirective

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Materialization Spec</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.mspec.MaterializationSpec#getMspecNodes
 * <em>Mspec Nodes</em>}</li>
 * <li>{@link org.eclipse.buckminster.mspec.MaterializationSpec#getName <em>Name
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.mspec.MaterializationSpec#getShortDesc
 * <em>Short Desc</em>}</li>
 * <li>{@link org.eclipse.buckminster.mspec.MaterializationSpec#getUrl <em>Url
 * </em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.MaterializationSpec#getMaxParallelJobs
 * <em>Max Parallel Jobs</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationSpec()
 * @model
 * @generated
 */
public interface MaterializationSpec extends MaterializationDirective {
	/**
	 * Returns the value of the '<em><b>Max Parallel Jobs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Parallel Jobs</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Max Parallel Jobs</em>' attribute.
	 * @see #setMaxParallelJobs(int)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationSpec_MaxParallelJobs()
	 * @model
	 * @generated
	 */
	int getMaxParallelJobs();

	/**
	 * Returns the value of the '<em><b>Mspec Nodes</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.mspec.MaterializationNode}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mspec Nodes</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Mspec Nodes</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationSpec_MspecNodes()
	 * @model containment="true" extendedMetaData=
	 *        "name='mspecNode' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<MaterializationNode> getMspecNodes();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationSpec_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>Short Desc</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Short Desc</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Short Desc</em>' attribute.
	 * @see #setShortDesc(String)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationSpec_ShortDesc()
	 * @model
	 * @generated
	 */
	String getShortDesc();

	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> A URL
	 * appointing the BillOfMaterials or ComponentQuery to materialize <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationSpec_Url()
	 * @model
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.mspec.MaterializationSpec#getMaxParallelJobs
	 * <em>Max Parallel Jobs</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Max Parallel Jobs</em>' attribute.
	 * @see #getMaxParallelJobs()
	 * @generated
	 */
	void setMaxParallelJobs(int value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.mspec.MaterializationSpec#getName
	 * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.mspec.MaterializationSpec#getShortDesc
	 * <em>Short Desc</em>} ' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Short Desc</em>' attribute.
	 * @see #getShortDesc()
	 * @generated
	 */
	void setShortDesc(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.mspec.MaterializationSpec#getUrl
	 * <em>Url</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

} // MaterializationSpec

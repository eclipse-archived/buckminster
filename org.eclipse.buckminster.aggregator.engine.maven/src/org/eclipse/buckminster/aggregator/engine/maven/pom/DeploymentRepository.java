/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Deployment Repository</b></em>'. <!--
 * end-user-doc -->
 * 
 * <!-- begin-model-doc --> 4.0.0
 * 
 * Repository contains the information needed for deploying to the remote repoistory.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#isUniqueVersion <em>Unique
 * Version</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#getUrl <em>Url</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#getLayout <em>Layout</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getDeploymentRepository()
 * @model extendedMetaData="name='DeploymentRepository' kind='elementOnly'"
 * @generated
 */
public interface DeploymentRepository extends EObject
{
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> 4.0.0
	 * 
	 * A unique identifier for a repository. This is used to match the repository to configuration in the
	 * &lt;code&gt;settings.xml&lt;/code&gt; file, for example.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getDeploymentRepository_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Returns the value of the '<em><b>Layout</b></em>' attribute. The default value is <code>"default"</code>. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> 4.0.0
	 * 
	 * The type of layout this repository uses for locating and storing artifacts - can be
	 * &lt;code&gt;legacy&lt;/code&gt; or &lt;code&gt;default&lt;/code&gt;.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Layout</em>' attribute.
	 * @see #isSetLayout()
	 * @see #unsetLayout()
	 * @see #setLayout(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getDeploymentRepository_Layout()
	 * @model default="default" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='layout' namespace='##targetNamespace'"
	 * @generated
	 */
	String getLayout();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> 4.0.0
	 * 
	 * Human readable name of the repository.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getDeploymentRepository_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> 4.0.0
	 * 
	 * The url of the repository, in the form &lt;code&gt;protocol://hostname/path&lt;/code&gt;.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getDeploymentRepository_Url()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='url' namespace='##targetNamespace'"
	 * @generated
	 */
	String getUrl();

	/**
	 * Returns whether the value of the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#getLayout <em>Layout</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Layout</em>' attribute is set.
	 * @see #unsetLayout()
	 * @see #getLayout()
	 * @see #setLayout(String)
	 * @generated
	 */
	boolean isSetLayout();

	/**
	 * Returns whether the value of the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#isUniqueVersion
	 * <em>Unique Version</em>}' attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Unique Version</em>' attribute is set.
	 * @see #unsetUniqueVersion()
	 * @see #isUniqueVersion()
	 * @see #setUniqueVersion(boolean)
	 * @generated
	 */
	boolean isSetUniqueVersion();

	/**
	 * Returns the value of the '<em><b>Unique Version</b></em>' attribute. The default value is <code>"true"</code>.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> 4.0.0 Whether to assign snapshots a unique
	 * version comprised of the timestamp and build number, or to use the same version each time <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Unique Version</em>' attribute.
	 * @see #isSetUniqueVersion()
	 * @see #unsetUniqueVersion()
	 * @see #setUniqueVersion(boolean)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getDeploymentRepository_UniqueVersion()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='uniqueVersion' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isUniqueVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#getId
	 * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#getLayout
	 * <em>Layout</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Layout</em>' attribute.
	 * @see #isSetLayout()
	 * @see #unsetLayout()
	 * @see #getLayout()
	 * @generated
	 */
	void setLayout(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#getName
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
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#isUniqueVersion
	 * <em>Unique Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Unique Version</em>' attribute.
	 * @see #isSetUniqueVersion()
	 * @see #unsetUniqueVersion()
	 * @see #isUniqueVersion()
	 * @generated
	 */
	void setUniqueVersion(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#getUrl
	 * <em>Url</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Unsets the value of the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#getLayout <em>Layout</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetLayout()
	 * @see #getLayout()
	 * @see #setLayout(String)
	 * @generated
	 */
	void unsetLayout();

	/**
	 * Unsets the value of the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository#isUniqueVersion
	 * <em>Unique Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetUniqueVersion()
	 * @see #isUniqueVersion()
	 * @see #setUniqueVersion(boolean)
	 * @generated
	 */
	void unsetUniqueVersion();

} // DeploymentRepository

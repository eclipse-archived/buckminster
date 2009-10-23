/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Notifier</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> 4.0.0
 * 
 * Configures one method for notifying users/developers when a build breaks.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#getType <em>Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnError <em>Send On Error</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnFailure <em>Send On Failure</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnSuccess <em>Send On Success</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnWarning <em>Send On Warning</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#getAddress <em>Address</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#getConfiguration <em>Configuration</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getNotifier()
 * @model extendedMetaData="name='Notifier' kind='elementOnly'"
 * @generated
 */
public interface Notifier extends EObject
{
	/**
	 * Returns the value of the '<em><b>Address</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> 4.0.0
	 * 
	 * &lt;b&gt;Deprecated&lt;/b&gt;. Where to send the notification to - eg email address.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Address</em>' attribute.
	 * @see #setAddress(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getNotifier_Address()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='address' namespace='##targetNamespace'"
	 * @generated
	 */
	String getAddress();

	/**
	 * Returns the value of the '<em><b>Configuration</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> 0.0.0+ Extended configuration specific to this notifier goes here. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Configuration</em>' containment reference.
	 * @see #setConfiguration(ConfigurationType)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getNotifier_Configuration()
	 * @model containment="true" extendedMetaData="kind='element' name='configuration' namespace='##targetNamespace'"
	 * @generated
	 */
	ConfigurationType getConfiguration();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. The default value is <code>"mail"</code>. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> 4.0.0 The mechanism used to deliver
	 * notifications. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getNotifier_Type()
	 * @model default="mail" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='type' namespace='##targetNamespace'"
	 * @generated
	 */
	String getType();

	/**
	 * Returns the value of the '<em><b>Send On Error</b></em>' attribute. The default value is <code>"true"</code>.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> 4.0.0 Whether to send notifications on
	 * error. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Send On Error</em>' attribute.
	 * @see #isSetSendOnError()
	 * @see #unsetSendOnError()
	 * @see #setSendOnError(boolean)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getNotifier_SendOnError()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='sendOnError' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSendOnError();

	/**
	 * Returns the value of the '<em><b>Send On Failure</b></em>' attribute. The default value is <code>"true"</code>.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> 4.0.0 Whether to send notifications on
	 * failure. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Send On Failure</em>' attribute.
	 * @see #isSetSendOnFailure()
	 * @see #unsetSendOnFailure()
	 * @see #setSendOnFailure(boolean)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getNotifier_SendOnFailure()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='sendOnFailure' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSendOnFailure();

	/**
	 * Returns the value of the '<em><b>Send On Success</b></em>' attribute. The default value is <code>"true"</code>.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> 4.0.0 Whether to send notifications on
	 * success. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Send On Success</em>' attribute.
	 * @see #isSetSendOnSuccess()
	 * @see #unsetSendOnSuccess()
	 * @see #setSendOnSuccess(boolean)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getNotifier_SendOnSuccess()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='sendOnSuccess' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSendOnSuccess();

	/**
	 * Returns the value of the '<em><b>Send On Warning</b></em>' attribute. The default value is <code>"true"</code>.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> 4.0.0 Whether to send notifications on
	 * warning. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Send On Warning</em>' attribute.
	 * @see #isSetSendOnWarning()
	 * @see #unsetSendOnWarning()
	 * @see #setSendOnWarning(boolean)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getNotifier_SendOnWarning()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='sendOnWarning' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSendOnWarning();

	/**
	 * Returns whether the value of the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnError <em>Send On Error</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Send On Error</em>' attribute is set.
	 * @see #unsetSendOnError()
	 * @see #isSendOnError()
	 * @see #setSendOnError(boolean)
	 * @generated
	 */
	boolean isSetSendOnError();

	/**
	 * Returns whether the value of the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnFailure <em>Send On Failure</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Send On Failure</em>' attribute is set.
	 * @see #unsetSendOnFailure()
	 * @see #isSendOnFailure()
	 * @see #setSendOnFailure(boolean)
	 * @generated
	 */
	boolean isSetSendOnFailure();

	/**
	 * Returns whether the value of the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnSuccess <em>Send On Success</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Send On Success</em>' attribute is set.
	 * @see #unsetSendOnSuccess()
	 * @see #isSendOnSuccess()
	 * @see #setSendOnSuccess(boolean)
	 * @generated
	 */
	boolean isSetSendOnSuccess();

	/**
	 * Returns whether the value of the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnWarning <em>Send On Warning</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Send On Warning</em>' attribute is set.
	 * @see #unsetSendOnWarning()
	 * @see #isSendOnWarning()
	 * @see #setSendOnWarning(boolean)
	 * @generated
	 */
	boolean isSetSendOnWarning();

	/**
	 * Returns whether the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#getType
	 * <em>Type</em>}' attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(String)
	 * @generated
	 */
	boolean isSetType();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#getAddress
	 * <em>Address</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Address</em>' attribute.
	 * @see #getAddress()
	 * @generated
	 */
	void setAddress(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#getConfiguration
	 * <em>Configuration</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Configuration</em>' containment reference.
	 * @see #getConfiguration()
	 * @generated
	 */
	void setConfiguration(ConfigurationType value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnError
	 * <em>Send On Error</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Send On Error</em>' attribute.
	 * @see #isSetSendOnError()
	 * @see #unsetSendOnError()
	 * @see #isSendOnError()
	 * @generated
	 */
	void setSendOnError(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnFailure
	 * <em>Send On Failure</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Send On Failure</em>' attribute.
	 * @see #isSetSendOnFailure()
	 * @see #unsetSendOnFailure()
	 * @see #isSendOnFailure()
	 * @generated
	 */
	void setSendOnFailure(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnSuccess
	 * <em>Send On Success</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Send On Success</em>' attribute.
	 * @see #isSetSendOnSuccess()
	 * @see #unsetSendOnSuccess()
	 * @see #isSendOnSuccess()
	 * @generated
	 */
	void setSendOnSuccess(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnWarning
	 * <em>Send On Warning</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Send On Warning</em>' attribute.
	 * @see #isSetSendOnWarning()
	 * @see #unsetSendOnWarning()
	 * @see #isSendOnWarning()
	 * @generated
	 */
	void setSendOnWarning(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#getType <em>Type</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Unsets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnError
	 * <em>Send On Error</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetSendOnError()
	 * @see #isSendOnError()
	 * @see #setSendOnError(boolean)
	 * @generated
	 */
	void unsetSendOnError();

	/**
	 * Unsets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnFailure
	 * <em>Send On Failure</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetSendOnFailure()
	 * @see #isSendOnFailure()
	 * @see #setSendOnFailure(boolean)
	 * @generated
	 */
	void unsetSendOnFailure();

	/**
	 * Unsets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnSuccess
	 * <em>Send On Success</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetSendOnSuccess()
	 * @see #isSendOnSuccess()
	 * @see #setSendOnSuccess(boolean)
	 * @generated
	 */
	void unsetSendOnSuccess();

	/**
	 * Unsets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#isSendOnWarning
	 * <em>Send On Warning</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetSendOnWarning()
	 * @see #isSendOnWarning()
	 * @see #setSendOnWarning(boolean)
	 * @generated
	 */
	void unsetSendOnWarning();

	/**
	 * Unsets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier#getType
	 * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(String)
	 * @generated
	 */
	void unsetType();

} // Notifier

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom.impl;

import org.eclipse.buckminster.aggregator.engine.maven.pom.ConfigurationType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Notifier</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.NotifierImpl#getType <em>Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.NotifierImpl#isSendOnError <em>Send On Error
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.NotifierImpl#isSendOnFailure <em>Send On Failure
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.NotifierImpl#isSendOnSuccess <em>Send On Success
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.NotifierImpl#isSendOnWarning <em>Send On Warning
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.NotifierImpl#getAddress <em>Address</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.NotifierImpl#getConfiguration <em>Configuration
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class NotifierImpl extends EObjectImpl implements Notifier
{
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = "mail";

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * This is true if the Type attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean typeESet;

	/**
	 * The default value of the '{@link #isSendOnError() <em>Send On Error</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isSendOnError()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SEND_ON_ERROR_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSendOnError() <em>Send On Error</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isSendOnError()
	 * @generated
	 * @ordered
	 */
	protected boolean sendOnError = SEND_ON_ERROR_EDEFAULT;

	/**
	 * This is true if the Send On Error attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean sendOnErrorESet;

	/**
	 * The default value of the '{@link #isSendOnFailure() <em>Send On Failure</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isSendOnFailure()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SEND_ON_FAILURE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSendOnFailure() <em>Send On Failure</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isSendOnFailure()
	 * @generated
	 * @ordered
	 */
	protected boolean sendOnFailure = SEND_ON_FAILURE_EDEFAULT;

	/**
	 * This is true if the Send On Failure attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean sendOnFailureESet;

	/**
	 * The default value of the '{@link #isSendOnSuccess() <em>Send On Success</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isSendOnSuccess()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SEND_ON_SUCCESS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSendOnSuccess() <em>Send On Success</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isSendOnSuccess()
	 * @generated
	 * @ordered
	 */
	protected boolean sendOnSuccess = SEND_ON_SUCCESS_EDEFAULT;

	/**
	 * This is true if the Send On Success attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean sendOnSuccessESet;

	/**
	 * The default value of the '{@link #isSendOnWarning() <em>Send On Warning</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isSendOnWarning()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SEND_ON_WARNING_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSendOnWarning() <em>Send On Warning</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isSendOnWarning()
	 * @generated
	 * @ordered
	 */
	protected boolean sendOnWarning = SEND_ON_WARNING_EDEFAULT;

	/**
	 * This is true if the Send On Warning attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean sendOnWarningESet;

	/**
	 * The default value of the '{@link #getAddress() <em>Address</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAddress() <em>Address</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected String address = ADDRESS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConfiguration() <em>Configuration</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getConfiguration()
	 * @generated
	 * @ordered
	 */
	protected ConfigurationType configuration;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected NotifierImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetConfiguration(ConfigurationType newConfiguration, NotificationChain msgs)
	{
		ConfigurationType oldConfiguration = configuration;
		configuration = newConfiguration;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.NOTIFIER__CONFIGURATION, oldConfiguration, newConfiguration);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch(featureID)
		{
		case PomPackage.NOTIFIER__TYPE:
			return getType();
		case PomPackage.NOTIFIER__SEND_ON_ERROR:
			return isSendOnError();
		case PomPackage.NOTIFIER__SEND_ON_FAILURE:
			return isSendOnFailure();
		case PomPackage.NOTIFIER__SEND_ON_SUCCESS:
			return isSendOnSuccess();
		case PomPackage.NOTIFIER__SEND_ON_WARNING:
			return isSendOnWarning();
		case PomPackage.NOTIFIER__ADDRESS:
			return getAddress();
		case PomPackage.NOTIFIER__CONFIGURATION:
			return getConfiguration();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch(featureID)
		{
		case PomPackage.NOTIFIER__CONFIGURATION:
			return basicSetConfiguration(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch(featureID)
		{
		case PomPackage.NOTIFIER__TYPE:
			return isSetType();
		case PomPackage.NOTIFIER__SEND_ON_ERROR:
			return isSetSendOnError();
		case PomPackage.NOTIFIER__SEND_ON_FAILURE:
			return isSetSendOnFailure();
		case PomPackage.NOTIFIER__SEND_ON_SUCCESS:
			return isSetSendOnSuccess();
		case PomPackage.NOTIFIER__SEND_ON_WARNING:
			return isSetSendOnWarning();
		case PomPackage.NOTIFIER__ADDRESS:
			return ADDRESS_EDEFAULT == null
					? address != null
					: !ADDRESS_EDEFAULT.equals(address);
		case PomPackage.NOTIFIER__CONFIGURATION:
			return configuration != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch(featureID)
		{
		case PomPackage.NOTIFIER__TYPE:
			setType((String)newValue);
			return;
		case PomPackage.NOTIFIER__SEND_ON_ERROR:
			setSendOnError((Boolean)newValue);
			return;
		case PomPackage.NOTIFIER__SEND_ON_FAILURE:
			setSendOnFailure((Boolean)newValue);
			return;
		case PomPackage.NOTIFIER__SEND_ON_SUCCESS:
			setSendOnSuccess((Boolean)newValue);
			return;
		case PomPackage.NOTIFIER__SEND_ON_WARNING:
			setSendOnWarning((Boolean)newValue);
			return;
		case PomPackage.NOTIFIER__ADDRESS:
			setAddress((String)newValue);
			return;
		case PomPackage.NOTIFIER__CONFIGURATION:
			setConfiguration((ConfigurationType)newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch(featureID)
		{
		case PomPackage.NOTIFIER__TYPE:
			unsetType();
			return;
		case PomPackage.NOTIFIER__SEND_ON_ERROR:
			unsetSendOnError();
			return;
		case PomPackage.NOTIFIER__SEND_ON_FAILURE:
			unsetSendOnFailure();
			return;
		case PomPackage.NOTIFIER__SEND_ON_SUCCESS:
			unsetSendOnSuccess();
			return;
		case PomPackage.NOTIFIER__SEND_ON_WARNING:
			unsetSendOnWarning();
			return;
		case PomPackage.NOTIFIER__ADDRESS:
			setAddress(ADDRESS_EDEFAULT);
			return;
		case PomPackage.NOTIFIER__CONFIGURATION:
			setConfiguration((ConfigurationType)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ConfigurationType getConfiguration()
	{
		return configuration;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSendOnError()
	{
		return sendOnError;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSendOnFailure()
	{
		return sendOnFailure;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSendOnSuccess()
	{
		return sendOnSuccess;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSendOnWarning()
	{
		return sendOnWarning;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetSendOnError()
	{
		return sendOnErrorESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetSendOnFailure()
	{
		return sendOnFailureESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetSendOnSuccess()
	{
		return sendOnSuccessESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetSendOnWarning()
	{
		return sendOnWarningESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetType()
	{
		return typeESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setAddress(String newAddress)
	{
		String oldAddress = address;
		address = newAddress;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.NOTIFIER__ADDRESS, oldAddress, address));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setConfiguration(ConfigurationType newConfiguration)
	{
		if(newConfiguration != configuration)
		{
			NotificationChain msgs = null;
			if(configuration != null)
				msgs = ((InternalEObject)configuration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.NOTIFIER__CONFIGURATION, null, msgs);
			if(newConfiguration != null)
				msgs = ((InternalEObject)newConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.NOTIFIER__CONFIGURATION, null, msgs);
			msgs = basicSetConfiguration(newConfiguration, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.NOTIFIER__CONFIGURATION, newConfiguration,
					newConfiguration));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSendOnError(boolean newSendOnError)
	{
		boolean oldSendOnError = sendOnError;
		sendOnError = newSendOnError;
		boolean oldSendOnErrorESet = sendOnErrorESet;
		sendOnErrorESet = true;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.NOTIFIER__SEND_ON_ERROR, oldSendOnError,
					sendOnError, !oldSendOnErrorESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSendOnFailure(boolean newSendOnFailure)
	{
		boolean oldSendOnFailure = sendOnFailure;
		sendOnFailure = newSendOnFailure;
		boolean oldSendOnFailureESet = sendOnFailureESet;
		sendOnFailureESet = true;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.NOTIFIER__SEND_ON_FAILURE,
					oldSendOnFailure, sendOnFailure, !oldSendOnFailureESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSendOnSuccess(boolean newSendOnSuccess)
	{
		boolean oldSendOnSuccess = sendOnSuccess;
		sendOnSuccess = newSendOnSuccess;
		boolean oldSendOnSuccessESet = sendOnSuccessESet;
		sendOnSuccessESet = true;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.NOTIFIER__SEND_ON_SUCCESS,
					oldSendOnSuccess, sendOnSuccess, !oldSendOnSuccessESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSendOnWarning(boolean newSendOnWarning)
	{
		boolean oldSendOnWarning = sendOnWarning;
		sendOnWarning = newSendOnWarning;
		boolean oldSendOnWarningESet = sendOnWarningESet;
		sendOnWarningESet = true;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.NOTIFIER__SEND_ON_WARNING,
					oldSendOnWarning, sendOnWarning, !oldSendOnWarningESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setType(String newType)
	{
		String oldType = type;
		type = newType;
		boolean oldTypeESet = typeESet;
		typeESet = true;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.NOTIFIER__TYPE, oldType, type,
					!oldTypeESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString()
	{
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (type: ");
		if(typeESet)
			result.append(type);
		else
			result.append("<unset>");
		result.append(", sendOnError: ");
		if(sendOnErrorESet)
			result.append(sendOnError);
		else
			result.append("<unset>");
		result.append(", sendOnFailure: ");
		if(sendOnFailureESet)
			result.append(sendOnFailure);
		else
			result.append("<unset>");
		result.append(", sendOnSuccess: ");
		if(sendOnSuccessESet)
			result.append(sendOnSuccess);
		else
			result.append("<unset>");
		result.append(", sendOnWarning: ");
		if(sendOnWarningESet)
			result.append(sendOnWarning);
		else
			result.append("<unset>");
		result.append(", address: ");
		result.append(address);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetSendOnError()
	{
		boolean oldSendOnError = sendOnError;
		boolean oldSendOnErrorESet = sendOnErrorESet;
		sendOnError = SEND_ON_ERROR_EDEFAULT;
		sendOnErrorESet = false;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PomPackage.NOTIFIER__SEND_ON_ERROR, oldSendOnError,
					SEND_ON_ERROR_EDEFAULT, oldSendOnErrorESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetSendOnFailure()
	{
		boolean oldSendOnFailure = sendOnFailure;
		boolean oldSendOnFailureESet = sendOnFailureESet;
		sendOnFailure = SEND_ON_FAILURE_EDEFAULT;
		sendOnFailureESet = false;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PomPackage.NOTIFIER__SEND_ON_FAILURE,
					oldSendOnFailure, SEND_ON_FAILURE_EDEFAULT, oldSendOnFailureESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetSendOnSuccess()
	{
		boolean oldSendOnSuccess = sendOnSuccess;
		boolean oldSendOnSuccessESet = sendOnSuccessESet;
		sendOnSuccess = SEND_ON_SUCCESS_EDEFAULT;
		sendOnSuccessESet = false;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PomPackage.NOTIFIER__SEND_ON_SUCCESS,
					oldSendOnSuccess, SEND_ON_SUCCESS_EDEFAULT, oldSendOnSuccessESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetSendOnWarning()
	{
		boolean oldSendOnWarning = sendOnWarning;
		boolean oldSendOnWarningESet = sendOnWarningESet;
		sendOnWarning = SEND_ON_WARNING_EDEFAULT;
		sendOnWarningESet = false;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PomPackage.NOTIFIER__SEND_ON_WARNING,
					oldSendOnWarning, SEND_ON_WARNING_EDEFAULT, oldSendOnWarningESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetType()
	{
		String oldType = type;
		boolean oldTypeESet = typeESet;
		type = TYPE_EDEFAULT;
		typeESet = false;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PomPackage.NOTIFIER__TYPE, oldType, TYPE_EDEFAULT,
					oldTypeESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PomPackage.Literals.NOTIFIER;
	}

} // NotifierImpl

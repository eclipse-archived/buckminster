/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.Status;
import org.eclipse.buckminster.aggregator.StatusCode;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Status</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.StatusImpl#getCode <em>Code</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.StatusImpl#getMessage <em>Message</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class StatusImpl extends MinimalEObjectImpl.Container implements Status
{
	private static final Status s_okStatus = new StatusImpl(StatusCode.OK);

	private static final Status s_brokenStatus = new StatusImpl(StatusCode.BROKEN);

	private static final Status s_waitingStatus = new StatusImpl(StatusCode.WAITING);

	public static Status createStatus(StatusCode statusCode)
	{
		switch(statusCode)
		{
		case BROKEN:
			return s_brokenStatus;
		case WAITING:
			return s_waitingStatus;
		default:
			return s_okStatus;
		}
	}

	public static Status createStatus(StatusCode statusCode, String message)
	{
		return new StatusImpl(statusCode, message);
	}

	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The default value of the '{@link #getCode() <em>Code</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected static final StatusCode CODE_EDEFAULT = StatusCode.OK;

	/**
	 * The offset of the flags representing the value of the '{@link #getCode() <em>Code</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected static final int CODE_EFLAG_OFFSET = 0;

	/**
	 * The flags representing the default value of the '{@link #getCode() <em>Code</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected static final int CODE_EFLAG_DEFAULT = CODE_EDEFAULT.ordinal() << CODE_EFLAG_OFFSET;

	/**
	 * The array of enumeration values for '{@link StatusCode Status Code}' <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	private static final StatusCode[] CODE_EFLAG_VALUES = StatusCode.values();

	/**
	 * The flags representing the value of the '{@link #getCode() <em>Code</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected static final int CODE_EFLAG = 0x3 << CODE_EFLAG_OFFSET;

	/**
	 * The default value of the '{@link #getMessage() <em>Message</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getMessage() <em>Message</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getMessage()
	 * @generated
	 * @ordered
	 */
	protected String message = MESSAGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected StatusImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected StatusImpl(StatusCode statusCode)
	{
		this(statusCode, null);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected StatusImpl(StatusCode statusCode, String message)
	{
		super();
		eFlags = eFlags & ~CODE_EFLAG | statusCode.ordinal() << CODE_EFLAG_OFFSET;
		this.message = message;
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
		case AggregatorPackage.STATUS__CODE:
			return getCode();
		case AggregatorPackage.STATUS__MESSAGE:
			return getMessage();
		}
		return super.eGet(featureID, resolve, coreType);
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
		case AggregatorPackage.STATUS__CODE:
			return (eFlags & CODE_EFLAG) != CODE_EFLAG_DEFAULT;
		case AggregatorPackage.STATUS__MESSAGE:
			return MESSAGE_EDEFAULT == null
					? message != null
					: !MESSAGE_EDEFAULT.equals(message);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public StatusCode getCode()
	{
		return CODE_EFLAG_VALUES[(eFlags & CODE_EFLAG) >>> CODE_EFLAG_OFFSET];
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getMessage()
	{
		return message;
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
		result.append(" (code: ");
		result.append(CODE_EFLAG_VALUES[(eFlags & CODE_EFLAG) >>> CODE_EFLAG_OFFSET]);
		result.append(", message: ");
		result.append(message);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return AggregatorPackage.Literals.STATUS;
	}

} // StatusImpl

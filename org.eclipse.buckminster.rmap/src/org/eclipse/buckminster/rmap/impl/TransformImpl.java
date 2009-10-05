/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import java.util.regex.Pattern;

import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.Transform;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Transform</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.impl.TransformImpl#getFromPattern <em>From Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.TransformImpl#getFromReplacement <em>From Replacement</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.TransformImpl#getToPattern <em>To Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.TransformImpl#getToReplacement <em>To Replacement</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TransformImpl extends EObjectImpl implements Transform
{
	/**
	 * The default value of the '{@link #getFromPattern() <em>From Pattern</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getFromPattern()
	 * @generated
	 * @ordered
	 */
	protected static final Pattern FROM_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFromPattern() <em>From Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFromPattern()
	 * @generated
	 * @ordered
	 */
	protected Pattern fromPattern = FROM_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getFromReplacement() <em>From Replacement</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getFromReplacement()
	 * @generated
	 * @ordered
	 */
	protected static final String FROM_REPLACEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFromReplacement() <em>From Replacement</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getFromReplacement()
	 * @generated
	 * @ordered
	 */
	protected String fromReplacement = FROM_REPLACEMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getToPattern() <em>To Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getToPattern()
	 * @generated
	 * @ordered
	 */
	protected static final Pattern TO_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getToPattern() <em>To Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getToPattern()
	 * @generated
	 * @ordered
	 */
	protected Pattern toPattern = TO_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getToReplacement() <em>To Replacement</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getToReplacement()
	 * @generated
	 * @ordered
	 */
	protected static final String TO_REPLACEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getToReplacement() <em>To Replacement</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getToReplacement()
	 * @generated
	 * @ordered
	 */
	protected String toReplacement = TO_REPLACEMENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TransformImpl()
	{
		super();
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
		case RmapPackage.TRANSFORM__FROM_PATTERN:
			return getFromPattern();
		case RmapPackage.TRANSFORM__FROM_REPLACEMENT:
			return getFromReplacement();
		case RmapPackage.TRANSFORM__TO_PATTERN:
			return getToPattern();
		case RmapPackage.TRANSFORM__TO_REPLACEMENT:
			return getToReplacement();
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
		case RmapPackage.TRANSFORM__FROM_PATTERN:
			return FROM_PATTERN_EDEFAULT == null
					? fromPattern != null
					: !FROM_PATTERN_EDEFAULT.equals(fromPattern);
		case RmapPackage.TRANSFORM__FROM_REPLACEMENT:
			return FROM_REPLACEMENT_EDEFAULT == null
					? fromReplacement != null
					: !FROM_REPLACEMENT_EDEFAULT.equals(fromReplacement);
		case RmapPackage.TRANSFORM__TO_PATTERN:
			return TO_PATTERN_EDEFAULT == null
					? toPattern != null
					: !TO_PATTERN_EDEFAULT.equals(toPattern);
		case RmapPackage.TRANSFORM__TO_REPLACEMENT:
			return TO_REPLACEMENT_EDEFAULT == null
					? toReplacement != null
					: !TO_REPLACEMENT_EDEFAULT.equals(toReplacement);
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
		case RmapPackage.TRANSFORM__FROM_PATTERN:
			setFromPattern((Pattern)newValue);
			return;
		case RmapPackage.TRANSFORM__FROM_REPLACEMENT:
			setFromReplacement((String)newValue);
			return;
		case RmapPackage.TRANSFORM__TO_PATTERN:
			setToPattern((Pattern)newValue);
			return;
		case RmapPackage.TRANSFORM__TO_REPLACEMENT:
			setToReplacement((String)newValue);
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
		case RmapPackage.TRANSFORM__FROM_PATTERN:
			setFromPattern(FROM_PATTERN_EDEFAULT);
			return;
		case RmapPackage.TRANSFORM__FROM_REPLACEMENT:
			setFromReplacement(FROM_REPLACEMENT_EDEFAULT);
			return;
		case RmapPackage.TRANSFORM__TO_PATTERN:
			setToPattern(TO_PATTERN_EDEFAULT);
			return;
		case RmapPackage.TRANSFORM__TO_REPLACEMENT:
			setToReplacement(TO_REPLACEMENT_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Pattern getFromPattern()
	{
		return fromPattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getFromReplacement()
	{
		return fromReplacement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Pattern getToPattern()
	{
		return toPattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getToReplacement()
	{
		return toReplacement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFromPattern(Pattern newFromPattern)
	{
		Pattern oldFromPattern = fromPattern;
		fromPattern = newFromPattern;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.TRANSFORM__FROM_PATTERN, oldFromPattern,
					fromPattern));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFromReplacement(String newFromReplacement)
	{
		String oldFromReplacement = fromReplacement;
		fromReplacement = newFromReplacement;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.TRANSFORM__FROM_REPLACEMENT,
					oldFromReplacement, fromReplacement));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setToPattern(Pattern newToPattern)
	{
		Pattern oldToPattern = toPattern;
		toPattern = newToPattern;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.TRANSFORM__TO_PATTERN, oldToPattern,
					toPattern));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setToReplacement(String newToReplacement)
	{
		String oldToReplacement = toReplacement;
		toReplacement = newToReplacement;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.TRANSFORM__TO_REPLACEMENT,
					oldToReplacement, toReplacement));
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
		result.append(" (fromPattern: ");
		result.append(fromPattern);
		result.append(", fromReplacement: ");
		result.append(fromReplacement);
		result.append(", toPattern: ");
		result.append(toPattern);
		result.append(", toReplacement: ");
		result.append(toReplacement);
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
		return RmapPackage.Literals.TRANSFORM;
	}

} // TransformImpl

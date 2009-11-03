/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2.impl;

import org.eclipse.buckminster.aggregator.p2.P2Package;
import org.eclipse.buckminster.aggregator.p2.UpdateDescriptor;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionRange;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Update Descriptor</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.UpdateDescriptorImpl#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.UpdateDescriptorImpl#getRange <em>Range</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.UpdateDescriptorImpl#getDescription <em>Description</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.UpdateDescriptorImpl#getSeverity <em>Severity</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class UpdateDescriptorImpl extends MinimalEObjectImpl.Container implements UpdateDescriptor
{
	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getRange() <em>Range</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getRange()
	 * @generated
	 * @ordered
	 */
	protected static final VersionRange RANGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRange() <em>Range</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getRange()
	 * @generated
	 * @ordered
	 */
	protected VersionRange range = RANGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSeverity() <em>Severity</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getSeverity()
	 * @generated
	 * @ordered
	 */
	protected static final int SEVERITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSeverity() <em>Severity</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getSeverity()
	 * @generated
	 * @ordered
	 */
	protected int severity = SEVERITY_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected UpdateDescriptorImpl()
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
		case P2Package.UPDATE_DESCRIPTOR__ID:
			return getId();
		case P2Package.UPDATE_DESCRIPTOR__RANGE:
			return getRange();
		case P2Package.UPDATE_DESCRIPTOR__DESCRIPTION:
			return getDescription();
		case P2Package.UPDATE_DESCRIPTOR__SEVERITY:
			return getSeverity();
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
		case P2Package.UPDATE_DESCRIPTOR__ID:
			return ID_EDEFAULT == null
					? id != null
					: !ID_EDEFAULT.equals(id);
		case P2Package.UPDATE_DESCRIPTOR__RANGE:
			return RANGE_EDEFAULT == null
					? range != null
					: !RANGE_EDEFAULT.equals(range);
		case P2Package.UPDATE_DESCRIPTOR__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null
					? description != null
					: !DESCRIPTION_EDEFAULT.equals(description);
		case P2Package.UPDATE_DESCRIPTOR__SEVERITY:
			return severity != SEVERITY_EDEFAULT;
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
		case P2Package.UPDATE_DESCRIPTOR__ID:
			setId((String)newValue);
			return;
		case P2Package.UPDATE_DESCRIPTOR__RANGE:
			setRange((VersionRange)newValue);
			return;
		case P2Package.UPDATE_DESCRIPTOR__DESCRIPTION:
			setDescription((String)newValue);
			return;
		case P2Package.UPDATE_DESCRIPTOR__SEVERITY:
			setSeverity((Integer)newValue);
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
		case P2Package.UPDATE_DESCRIPTOR__ID:
			setId(ID_EDEFAULT);
			return;
		case P2Package.UPDATE_DESCRIPTOR__RANGE:
			setRange(RANGE_EDEFAULT);
			return;
		case P2Package.UPDATE_DESCRIPTOR__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case P2Package.UPDATE_DESCRIPTOR__SEVERITY:
			setSeverity(SEVERITY_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public VersionRange getRange()
	{
		return range;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getSeverity()
	{
		return severity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isUpdateOf(IInstallableUnit iu)
	{
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDescription(String newDescription)
	{
		String oldDescription = description;
		description = newDescription;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.UPDATE_DESCRIPTOR__DESCRIPTION,
					oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setId(String newId)
	{
		String oldId = id;
		id = newId;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.UPDATE_DESCRIPTOR__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setRange(VersionRange newRange)
	{
		VersionRange oldRange = range;
		range = newRange;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.UPDATE_DESCRIPTOR__RANGE, oldRange, range));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSeverity(int newSeverity)
	{
		int oldSeverity = severity;
		severity = newSeverity;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.UPDATE_DESCRIPTOR__SEVERITY, oldSeverity,
					severity));
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
		result.append(" (id: ");
		result.append(id);
		result.append(", range: ");
		result.append(range);
		result.append(", description: ");
		result.append(description);
		result.append(", severity: ");
		result.append(severity);
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
		return P2Package.Literals.UPDATE_DESCRIPTOR;
	}

} // UpdateDescriptorImpl

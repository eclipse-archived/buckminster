/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import java.util.Collection;

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.Contact;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.StatusProvider;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Contribution</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl#getRepositories <em>Repositories</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl#getContacts <em>Contacts</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl#isEnabled <em>Enabled</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ContributionImpl extends MinimalEObjectImpl.Container implements Contribution
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
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRepositories() <em>Repositories</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRepositories()
	 * @generated
	 * @ordered
	 */
	protected EList<MappedRepository> repositories;

	/**
	 * The cached value of the '{@link #getContacts() <em>Contacts</em>}' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getContacts()
	 * @generated
	 * @ordered
	 */
	protected EList<Contact> contacts;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The flag representing the value of the '{@link #isEnabled() <em>Enabled</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final int ENABLED_EFLAG = 1 << 0;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ContributionImpl()
	{
		super();
		eFlags |= ENABLED_EFLAG;
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
		case AggregatorPackage.CONTRIBUTION__LABEL:
			return getLabel();
		case AggregatorPackage.CONTRIBUTION__REPOSITORIES:
			return getRepositories();
		case AggregatorPackage.CONTRIBUTION__CONTACTS:
			return getContacts();
		case AggregatorPackage.CONTRIBUTION__ENABLED:
			return isEnabled();
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
		case AggregatorPackage.CONTRIBUTION__REPOSITORIES:
			return ((InternalEList<?>)getRepositories()).basicRemove(otherEnd, msgs);
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
		case AggregatorPackage.CONTRIBUTION__LABEL:
			return LABEL_EDEFAULT == null
					? label != null
					: !LABEL_EDEFAULT.equals(label);
		case AggregatorPackage.CONTRIBUTION__REPOSITORIES:
			return repositories != null && !repositories.isEmpty();
		case AggregatorPackage.CONTRIBUTION__CONTACTS:
			return contacts != null && !contacts.isEmpty();
		case AggregatorPackage.CONTRIBUTION__ENABLED:
			return ((eFlags & ENABLED_EFLAG) != 0) != ENABLED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch(featureID)
		{
		case AggregatorPackage.CONTRIBUTION__LABEL:
			setLabel((String)newValue);
			return;
		case AggregatorPackage.CONTRIBUTION__REPOSITORIES:
			getRepositories().clear();
			getRepositories().addAll((Collection<? extends MappedRepository>)newValue);
			return;
		case AggregatorPackage.CONTRIBUTION__CONTACTS:
			getContacts().clear();
			getContacts().addAll((Collection<? extends Contact>)newValue);
			return;
		case AggregatorPackage.CONTRIBUTION__ENABLED:
			setEnabled((Boolean)newValue);
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
		case AggregatorPackage.CONTRIBUTION__LABEL:
			setLabel(LABEL_EDEFAULT);
			return;
		case AggregatorPackage.CONTRIBUTION__REPOSITORIES:
			getRepositories().clear();
			return;
		case AggregatorPackage.CONTRIBUTION__CONTACTS:
			getContacts().clear();
			return;
		case AggregatorPackage.CONTRIBUTION__ENABLED:
			setEnabled(ENABLED_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Contact> getContacts()
	{
		if(contacts == null)
		{
			contacts = new EObjectResolvingEList<Contact>(Contact.class, this, AggregatorPackage.CONTRIBUTION__CONTACTS);
		}
		return contacts;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<MappedRepository> getRepositories()
	{
		if(repositories == null)
		{
			repositories = new EObjectContainmentEList<MappedRepository>(MappedRepository.class, this,
					AggregatorPackage.CONTRIBUTION__REPOSITORIES);
		}
		return repositories;
	}

	public int getStatus()
	{
		for(MappedRepository repo : getRepositories())
		{
			if(repo.getStatus() != StatusProvider.OK)
				return StatusProvider.BROKEN_CHILD;
		}
		return StatusProvider.OK;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isEnabled()
	{
		return (eFlags & ENABLED_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setEnabled(boolean newEnabled)
	{
		boolean oldEnabled = (eFlags & ENABLED_EFLAG) != 0;
		if(newEnabled)
			eFlags |= ENABLED_EFLAG;
		else
			eFlags &= ~ENABLED_EFLAG;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.CONTRIBUTION__ENABLED, oldEnabled,
					newEnabled));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLabel(String newLabel)
	{
		String oldLabel = label;
		label = newLabel;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.CONTRIBUTION__LABEL, oldLabel,
					label));
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
		result.append(" (label: ");
		result.append(label);
		result.append(", enabled: ");
		result.append((eFlags & ENABLED_EFLAG) != 0);
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
		return AggregatorPackage.Literals.CONTRIBUTION;
	}

} // ContributionImpl

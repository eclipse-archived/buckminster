/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import java.util.Collection;

import org.eclipse.buckminster.aggregator.AggregateType;
import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.Configuration;
import org.eclipse.buckminster.aggregator.Contact;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.CustomCategory;
import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Aggregator</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getType <em>Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getBuildRoot <em>Build Root</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getConfigurations <em>Configurations</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getContributions <em>Contributions</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getBuildmaster <em>Buildmaster</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#isSendmail <em>Sendmail</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getContacts <em>Contacts</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getCustomCategories <em>Custom Categories</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AggregatorImpl extends MinimalEObjectImpl.Container implements Aggregator
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
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final AggregateType TYPE_EDEFAULT = AggregateType.STABLE;

	/**
	 * The offset of the flags representing the value of the '{@link #getType() <em>Type</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected static final int TYPE_EFLAG_OFFSET = 0;

	/**
	 * The flags representing the default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected static final int TYPE_EFLAG_DEFAULT = TYPE_EDEFAULT.ordinal() << TYPE_EFLAG_OFFSET;

	/**
	 * The array of enumeration values for '{@link AggregateType Aggregate Type}' <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	private static final AggregateType[] TYPE_EFLAG_VALUES = AggregateType.values();

	/**
	 * The flags representing the value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final int TYPE_EFLAG = 0x7 << TYPE_EFLAG_OFFSET;

	/**
	 * The default value of the '{@link #getBuildRoot() <em>Build Root</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getBuildRoot()
	 * @generated
	 * @ordered
	 */
	protected static final String BUILD_ROOT_EDEFAULT = "${user.home}/build";

	/**
	 * The cached value of the '{@link #getBuildRoot() <em>Build Root</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getBuildRoot()
	 * @generated
	 * @ordered
	 */
	protected String buildRoot = BUILD_ROOT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConfigurations() <em>Configurations</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getConfigurations()
	 * @generated
	 * @ordered
	 */
	protected EList<Configuration> configurations;

	/**
	 * The cached value of the '{@link #getContributions() <em>Contributions</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getContributions()
	 * @generated
	 * @ordered
	 */
	protected EList<Contribution> contributions;

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
	 * The cached value of the '{@link #getBuildmaster() <em>Buildmaster</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBuildmaster()
	 * @generated
	 * @ordered
	 */
	protected Contact buildmaster;

	/**
	 * The default value of the '{@link #isSendmail() <em>Sendmail</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isSendmail()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SENDMAIL_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isSendmail() <em>Sendmail</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #isSendmail()
	 * @generated
	 * @ordered
	 */
	protected static final int SENDMAIL_EFLAG = 1 << 3;

	/**
	 * The cached value of the '{@link #getContacts() <em>Contacts</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getContacts()
	 * @generated
	 * @ordered
	 */
	protected EList<Contact> contacts;

	/**
	 * The cached value of the '{@link #getCustomCategories() <em>Custom Categories</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCustomCategories()
	 * @generated
	 * @ordered
	 */
	protected EList<CustomCategory> customCategories;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AggregatorImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Contact basicGetBuildmaster()
	{
		return buildmaster;
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
		case AggregatorPackage.AGGREGATOR__TYPE:
			return getType();
		case AggregatorPackage.AGGREGATOR__BUILD_ROOT:
			return getBuildRoot();
		case AggregatorPackage.AGGREGATOR__CONFIGURATIONS:
			return getConfigurations();
		case AggregatorPackage.AGGREGATOR__CONTRIBUTIONS:
			return getContributions();
		case AggregatorPackage.AGGREGATOR__LABEL:
			return getLabel();
		case AggregatorPackage.AGGREGATOR__BUILDMASTER:
			if(resolve)
				return getBuildmaster();
			return basicGetBuildmaster();
		case AggregatorPackage.AGGREGATOR__SENDMAIL:
			return isSendmail();
		case AggregatorPackage.AGGREGATOR__CONTACTS:
			return getContacts();
		case AggregatorPackage.AGGREGATOR__CUSTOM_CATEGORIES:
			return getCustomCategories();
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
		case AggregatorPackage.AGGREGATOR__CONFIGURATIONS:
			return ((InternalEList<?>)getConfigurations()).basicRemove(otherEnd, msgs);
		case AggregatorPackage.AGGREGATOR__CONTRIBUTIONS:
			return ((InternalEList<?>)getContributions()).basicRemove(otherEnd, msgs);
		case AggregatorPackage.AGGREGATOR__CONTACTS:
			return ((InternalEList<?>)getContacts()).basicRemove(otherEnd, msgs);
		case AggregatorPackage.AGGREGATOR__CUSTOM_CATEGORIES:
			return ((InternalEList<?>)getCustomCategories()).basicRemove(otherEnd, msgs);
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
		case AggregatorPackage.AGGREGATOR__TYPE:
			return (eFlags & TYPE_EFLAG) != TYPE_EFLAG_DEFAULT;
		case AggregatorPackage.AGGREGATOR__BUILD_ROOT:
			return BUILD_ROOT_EDEFAULT == null
					? buildRoot != null
					: !BUILD_ROOT_EDEFAULT.equals(buildRoot);
		case AggregatorPackage.AGGREGATOR__CONFIGURATIONS:
			return configurations != null && !configurations.isEmpty();
		case AggregatorPackage.AGGREGATOR__CONTRIBUTIONS:
			return contributions != null && !contributions.isEmpty();
		case AggregatorPackage.AGGREGATOR__LABEL:
			return LABEL_EDEFAULT == null
					? label != null
					: !LABEL_EDEFAULT.equals(label);
		case AggregatorPackage.AGGREGATOR__BUILDMASTER:
			return buildmaster != null;
		case AggregatorPackage.AGGREGATOR__SENDMAIL:
			return ((eFlags & SENDMAIL_EFLAG) != 0) != SENDMAIL_EDEFAULT;
		case AggregatorPackage.AGGREGATOR__CONTACTS:
			return contacts != null && !contacts.isEmpty();
		case AggregatorPackage.AGGREGATOR__CUSTOM_CATEGORIES:
			return customCategories != null && !customCategories.isEmpty();
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
		case AggregatorPackage.AGGREGATOR__TYPE:
			setType((AggregateType)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__BUILD_ROOT:
			setBuildRoot((String)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__CONFIGURATIONS:
			getConfigurations().clear();
			getConfigurations().addAll((Collection<? extends Configuration>)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__CONTRIBUTIONS:
			getContributions().clear();
			getContributions().addAll((Collection<? extends Contribution>)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__LABEL:
			setLabel((String)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__BUILDMASTER:
			setBuildmaster((Contact)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__SENDMAIL:
			setSendmail((Boolean)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__CONTACTS:
			getContacts().clear();
			getContacts().addAll((Collection<? extends Contact>)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__CUSTOM_CATEGORIES:
			getCustomCategories().clear();
			getCustomCategories().addAll((Collection<? extends CustomCategory>)newValue);
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
		case AggregatorPackage.AGGREGATOR__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case AggregatorPackage.AGGREGATOR__BUILD_ROOT:
			setBuildRoot(BUILD_ROOT_EDEFAULT);
			return;
		case AggregatorPackage.AGGREGATOR__CONFIGURATIONS:
			getConfigurations().clear();
			return;
		case AggregatorPackage.AGGREGATOR__CONTRIBUTIONS:
			getContributions().clear();
			return;
		case AggregatorPackage.AGGREGATOR__LABEL:
			setLabel(LABEL_EDEFAULT);
			return;
		case AggregatorPackage.AGGREGATOR__BUILDMASTER:
			setBuildmaster((Contact)null);
			return;
		case AggregatorPackage.AGGREGATOR__SENDMAIL:
			setSendmail(SENDMAIL_EDEFAULT);
			return;
		case AggregatorPackage.AGGREGATOR__CONTACTS:
			getContacts().clear();
			return;
		case AggregatorPackage.AGGREGATOR__CUSTOM_CATEGORIES:
			getCustomCategories().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Contact getBuildmaster()
	{
		if(buildmaster != null && buildmaster.eIsProxy())
		{
			InternalEObject oldBuildmaster = (InternalEObject)buildmaster;
			buildmaster = (Contact)eResolveProxy(oldBuildmaster);
			if(buildmaster != oldBuildmaster)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							AggregatorPackage.AGGREGATOR__BUILDMASTER, oldBuildmaster, buildmaster));
			}
		}
		return buildmaster;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getBuildRoot()
	{
		return buildRoot;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Configuration> getConfigurations()
	{
		if(configurations == null)
		{
			configurations = new EObjectContainmentEList<Configuration>(Configuration.class, this,
					AggregatorPackage.AGGREGATOR__CONFIGURATIONS);
		}
		return configurations;
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
			contacts = new EObjectContainmentEList<Contact>(Contact.class, this, AggregatorPackage.AGGREGATOR__CONTACTS);
		}
		return contacts;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Contribution> getContributions()
	{
		if(contributions == null)
		{
			contributions = new EObjectContainmentEList<Contribution>(Contribution.class, this,
					AggregatorPackage.AGGREGATOR__CONTRIBUTIONS);
		}
		return contributions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<CustomCategory> getCustomCategories()
	{
		if(customCategories == null)
		{
			customCategories = new EObjectContainmentEList<CustomCategory>(CustomCategory.class, this,
					AggregatorPackage.AGGREGATOR__CUSTOM_CATEGORIES);
		}
		return customCategories;
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

	public int getStatus()
	{
		for(Contribution contribution : getContributions())
		{
			if(contribution.getStatus() != StatusProvider.OK)
				return StatusProvider.BROKEN_CHILD;
		}
		return StatusProvider.OK;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AggregateType getType()
	{
		return TYPE_EFLAG_VALUES[(eFlags & TYPE_EFLAG) >>> TYPE_EFLAG_OFFSET];
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSendmail()
	{
		return (eFlags & SENDMAIL_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setBuildmaster(Contact newBuildmaster)
	{
		Contact oldBuildmaster = buildmaster;
		buildmaster = newBuildmaster;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.AGGREGATOR__BUILDMASTER,
					oldBuildmaster, buildmaster));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setBuildRoot(String newBuildRoot)
	{
		String oldBuildRoot = buildRoot;
		buildRoot = newBuildRoot;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.AGGREGATOR__BUILD_ROOT,
					oldBuildRoot, buildRoot));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.AGGREGATOR__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSendmail(boolean newSendmail)
	{
		boolean oldSendmail = (eFlags & SENDMAIL_EFLAG) != 0;
		if(newSendmail)
			eFlags |= SENDMAIL_EFLAG;
		else
			eFlags &= ~SENDMAIL_EFLAG;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.AGGREGATOR__SENDMAIL, oldSendmail,
					newSendmail));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setType(AggregateType newType)
	{
		AggregateType oldType = TYPE_EFLAG_VALUES[(eFlags & TYPE_EFLAG) >>> TYPE_EFLAG_OFFSET];
		if(newType == null)
			newType = TYPE_EDEFAULT;
		eFlags = eFlags & ~TYPE_EFLAG | newType.ordinal() << TYPE_EFLAG_OFFSET;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.AGGREGATOR__TYPE, oldType, newType));
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
		result.append(TYPE_EFLAG_VALUES[(eFlags & TYPE_EFLAG) >>> TYPE_EFLAG_OFFSET]);
		result.append(", buildRoot: ");
		result.append(buildRoot);
		result.append(", label: ");
		result.append(label);
		result.append(", sendmail: ");
		result.append((eFlags & SENDMAIL_EFLAG) != 0);
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
		return AggregatorPackage.Literals.AGGREGATOR;
	}

} // AggregatorImpl

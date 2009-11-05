/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import java.util.Collection;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.Contact;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.DescriptionProvider;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MavenMapping;
import org.eclipse.buckminster.aggregator.StatusProvider;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
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
 * <li>{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl#isEnabled <em>Enabled</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl#getDescription <em>Description</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl#getRepositories <em>Repositories</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl#getContacts <em>Contacts</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl#getMavenMappings <em>Maven Mappings</em>}</li>
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
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = "";

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
	 * The cached value of the '{@link #getMavenMappings() <em>Maven Mappings</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMavenMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<MavenMapping> mavenMappings;

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
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if(baseClass == DescriptionProvider.class)
		{
			switch(derivedFeatureID)
			{
			case AggregatorPackage.CONTRIBUTION__DESCRIPTION:
				return AggregatorPackage.DESCRIPTION_PROVIDER__DESCRIPTION;
			default:
				return -1;
			}
		}
		if(baseClass == StatusProvider.class)
		{
			switch(derivedFeatureID)
			{
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
	{
		if(baseClass == DescriptionProvider.class)
		{
			switch(baseFeatureID)
			{
			case AggregatorPackage.DESCRIPTION_PROVIDER__DESCRIPTION:
				return AggregatorPackage.CONTRIBUTION__DESCRIPTION;
			default:
				return -1;
			}
		}
		if(baseClass == StatusProvider.class)
		{
			switch(baseFeatureID)
			{
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		case AggregatorPackage.CONTRIBUTION__ENABLED:
			return isEnabled();
		case AggregatorPackage.CONTRIBUTION__DESCRIPTION:
			return getDescription();
		case AggregatorPackage.CONTRIBUTION__LABEL:
			return getLabel();
		case AggregatorPackage.CONTRIBUTION__REPOSITORIES:
			return getRepositories();
		case AggregatorPackage.CONTRIBUTION__CONTACTS:
			return getContacts();
		case AggregatorPackage.CONTRIBUTION__MAVEN_MAPPINGS:
			return getMavenMappings();
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
		case AggregatorPackage.CONTRIBUTION__MAVEN_MAPPINGS:
			return ((InternalEList<?>)getMavenMappings()).basicRemove(otherEnd, msgs);
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
		case AggregatorPackage.CONTRIBUTION__ENABLED:
			return ((eFlags & ENABLED_EFLAG) != 0) != ENABLED_EDEFAULT;
		case AggregatorPackage.CONTRIBUTION__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null
					? description != null
					: !DESCRIPTION_EDEFAULT.equals(description);
		case AggregatorPackage.CONTRIBUTION__LABEL:
			return LABEL_EDEFAULT == null
					? label != null
					: !LABEL_EDEFAULT.equals(label);
		case AggregatorPackage.CONTRIBUTION__REPOSITORIES:
			return repositories != null && !repositories.isEmpty();
		case AggregatorPackage.CONTRIBUTION__CONTACTS:
			return contacts != null && !contacts.isEmpty();
		case AggregatorPackage.CONTRIBUTION__MAVEN_MAPPINGS:
			return mavenMappings != null && !mavenMappings.isEmpty();
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
		case AggregatorPackage.CONTRIBUTION__ENABLED:
			setEnabled((Boolean)newValue);
			return;
		case AggregatorPackage.CONTRIBUTION__DESCRIPTION:
			setDescription((String)newValue);
			return;
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
		case AggregatorPackage.CONTRIBUTION__MAVEN_MAPPINGS:
			getMavenMappings().clear();
			getMavenMappings().addAll((Collection<? extends MavenMapping>)newValue);
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
		case AggregatorPackage.CONTRIBUTION__ENABLED:
			setEnabled(ENABLED_EDEFAULT);
			return;
		case AggregatorPackage.CONTRIBUTION__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case AggregatorPackage.CONTRIBUTION__LABEL:
			setLabel(LABEL_EDEFAULT);
			return;
		case AggregatorPackage.CONTRIBUTION__REPOSITORIES:
			getRepositories().clear();
			return;
		case AggregatorPackage.CONTRIBUTION__CONTACTS:
			getContacts().clear();
			return;
		case AggregatorPackage.CONTRIBUTION__MAVEN_MAPPINGS:
			getMavenMappings().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<MavenMapping> getAllMavenMappings()
	{
		EList<MavenMapping> myMappings = getMavenMappings();
		EList<MavenMapping> parentMappings = ((Aggregator)eContainer()).getMavenMappings();
		EList<MavenMapping> allMappings = new BasicEList<MavenMapping>(myMappings.size() + parentMappings.size());
		allMappings.addAll(myMappings);
		allMappings.addAll(parentMappings);

		return allMappings;
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
	public String getDescription()
	{
		return description;
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
	public EList<MavenMapping> getMavenMappings()
	{
		if(mavenMappings == null)
		{
			mavenMappings = new EObjectContainmentEList<MavenMapping>(MavenMapping.class, this,
					AggregatorPackage.CONTRIBUTION__MAVEN_MAPPINGS);
		}
		return mavenMappings;
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

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<MappedRepository> getRepositories(boolean enabledOnly)
	{
		EList<MappedRepository> repos = getRepositories();
		if(enabledOnly)
		{
			EList<MappedRepository> enabledRepos = null;
			int top = repos.size();
			for(int idx = 0; idx < top; ++idx)
			{
				MappedRepository repo = repos.get(idx);
				if(repo.isEnabled())
				{
					if(enabledRepos != null)
						enabledRepos.add(repo);
					continue;
				}

				if(enabledRepos == null)
				{
					enabledRepos = new BasicEList<MappedRepository>(repos.size() - 1);
					for(int sdx = 0; sdx < idx; ++sdx)
						enabledRepos.add(repos.get(sdx));
				}
			}
			if(enabledRepos != null)
				repos = enabledRepos;
		}
		return repos;
	}

	synchronized public int getStatus()
	{
		int status;

		for(MappedRepository repo : getRepositories())
		{
			if((status = repo.getStatus()) != StatusProvider.OK && status != StatusProvider.WAITING)
				return StatusProvider.BROKEN_CHILD;
		}
		for(MavenMapping mapping : getMavenMappings())
		{
			if((status = mapping.getStatus()) != StatusProvider.OK && status != StatusProvider.WAITING)
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
	public void setDescription(String newDescription)
	{
		String oldDescription = description;
		description = newDescription;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.CONTRIBUTION__DESCRIPTION,
					oldDescription, description));
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
		result.append(" (enabled: ");
		result.append((eFlags & ENABLED_EFLAG) != 0);
		result.append(", description: ");
		result.append(description);
		result.append(", label: ");
		result.append(label);
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

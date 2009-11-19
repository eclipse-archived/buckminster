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
import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.Configuration;
import org.eclipse.buckminster.aggregator.Contact;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.CustomCategory;
import org.eclipse.buckminster.aggregator.MavenMapping;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MetadataRepositoryReference;
import org.eclipse.buckminster.aggregator.PackedStrategy;
import org.eclipse.buckminster.aggregator.Status;
import org.eclipse.buckminster.aggregator.StatusCode;
import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Aggregator</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getStatus <em>Status</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getConfigurations <em>Configurations</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getContributions <em>Contributions</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getBuildmaster <em>Buildmaster</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getContacts <em>Contacts</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getCustomCategories <em>Custom Categories</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getBuildRoot <em>Build Root</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getPackedStrategy <em>Packed Strategy</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#isSendmail <em>Sendmail</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getType <em>Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#isMavenResult <em>Maven Result</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getValidationRepositories <em>Validation
 * Repositories</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl#getMavenMappings <em>Maven Mappings</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AggregatorImpl extends DescriptionProviderImpl implements Aggregator
{
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
	 * The cached value of the '{@link #getBuildmaster() <em>Buildmaster</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBuildmaster()
	 * @generated
	 * @ordered
	 */
	protected Contact buildmaster;

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
	 * The default value of the '{@link #getPackedStrategy() <em>Packed Strategy</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getPackedStrategy()
	 * @generated
	 * @ordered
	 */
	protected static final PackedStrategy PACKED_STRATEGY_EDEFAULT = PackedStrategy.COPY;

	/**
	 * The offset of the flags representing the value of the '{@link #getPackedStrategy() <em>Packed Strategy</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected static final int PACKED_STRATEGY_EFLAG_OFFSET = 0;

	/**
	 * The flags representing the default value of the '{@link #getPackedStrategy() <em>Packed Strategy</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected static final int PACKED_STRATEGY_EFLAG_DEFAULT = PACKED_STRATEGY_EDEFAULT.ordinal() << PACKED_STRATEGY_EFLAG_OFFSET;

	/**
	 * The array of enumeration values for '{@link PackedStrategy Packed Strategy}' <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	private static final PackedStrategy[] PACKED_STRATEGY_EFLAG_VALUES = PackedStrategy.values();

	/**
	 * The flags representing the value of the '{@link #getPackedStrategy() <em>Packed Strategy</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPackedStrategy()
	 * @generated
	 * @ordered
	 */
	protected static final int PACKED_STRATEGY_EFLAG = 0x7 << PACKED_STRATEGY_EFLAG_OFFSET;

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
	protected static final int TYPE_EFLAG_OFFSET = 4;

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
	 * The default value of the '{@link #isMavenResult() <em>Maven Result</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isMavenResult()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MAVEN_RESULT_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isMavenResult() <em>Maven Result</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMavenResult()
	 * @generated
	 * @ordered
	 */
	protected static final int MAVEN_RESULT_EFLAG = 1 << 7;

	/**
	 * The cached value of the '{@link #getValidationRepositories() <em>Validation Repositories</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getValidationRepositories()
	 * @generated
	 * @ordered
	 */
	protected EList<MetadataRepositoryReference> validationRepositories;

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
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if(baseClass == StatusProvider.class)
		{
			switch(derivedFeatureID)
			{
			case AggregatorPackage.AGGREGATOR__STATUS:
				return AggregatorPackage.STATUS_PROVIDER__STATUS;
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
		if(baseClass == StatusProvider.class)
		{
			switch(baseFeatureID)
			{
			case AggregatorPackage.STATUS_PROVIDER__STATUS:
				return AggregatorPackage.AGGREGATOR__STATUS;
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
		case AggregatorPackage.AGGREGATOR__STATUS:
			return getStatus();
		case AggregatorPackage.AGGREGATOR__CONFIGURATIONS:
			return getConfigurations();
		case AggregatorPackage.AGGREGATOR__CONTRIBUTIONS:
			return getContributions();
		case AggregatorPackage.AGGREGATOR__BUILDMASTER:
			if(resolve)
				return getBuildmaster();
			return basicGetBuildmaster();
		case AggregatorPackage.AGGREGATOR__CONTACTS:
			return getContacts();
		case AggregatorPackage.AGGREGATOR__CUSTOM_CATEGORIES:
			return getCustomCategories();
		case AggregatorPackage.AGGREGATOR__LABEL:
			return getLabel();
		case AggregatorPackage.AGGREGATOR__BUILD_ROOT:
			return getBuildRoot();
		case AggregatorPackage.AGGREGATOR__PACKED_STRATEGY:
			return getPackedStrategy();
		case AggregatorPackage.AGGREGATOR__SENDMAIL:
			return isSendmail();
		case AggregatorPackage.AGGREGATOR__TYPE:
			return getType();
		case AggregatorPackage.AGGREGATOR__MAVEN_RESULT:
			return isMavenResult();
		case AggregatorPackage.AGGREGATOR__VALIDATION_REPOSITORIES:
			return getValidationRepositories();
		case AggregatorPackage.AGGREGATOR__MAVEN_MAPPINGS:
			return getMavenMappings();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch(featureID)
		{
		case AggregatorPackage.AGGREGATOR__CONTACTS:
			return ((InternalEList<InternalEObject>)(InternalEList<?>)getContacts()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
		case AggregatorPackage.AGGREGATOR__VALIDATION_REPOSITORIES:
			return ((InternalEList<?>)getValidationRepositories()).basicRemove(otherEnd, msgs);
		case AggregatorPackage.AGGREGATOR__MAVEN_MAPPINGS:
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
		case AggregatorPackage.AGGREGATOR__STATUS:
			return getStatus() != null;
		case AggregatorPackage.AGGREGATOR__CONFIGURATIONS:
			return configurations != null && !configurations.isEmpty();
		case AggregatorPackage.AGGREGATOR__CONTRIBUTIONS:
			return contributions != null && !contributions.isEmpty();
		case AggregatorPackage.AGGREGATOR__BUILDMASTER:
			return buildmaster != null;
		case AggregatorPackage.AGGREGATOR__CONTACTS:
			return contacts != null && !contacts.isEmpty();
		case AggregatorPackage.AGGREGATOR__CUSTOM_CATEGORIES:
			return customCategories != null && !customCategories.isEmpty();
		case AggregatorPackage.AGGREGATOR__LABEL:
			return LABEL_EDEFAULT == null
					? label != null
					: !LABEL_EDEFAULT.equals(label);
		case AggregatorPackage.AGGREGATOR__BUILD_ROOT:
			return BUILD_ROOT_EDEFAULT == null
					? buildRoot != null
					: !BUILD_ROOT_EDEFAULT.equals(buildRoot);
		case AggregatorPackage.AGGREGATOR__PACKED_STRATEGY:
			return (eFlags & PACKED_STRATEGY_EFLAG) != PACKED_STRATEGY_EFLAG_DEFAULT;
		case AggregatorPackage.AGGREGATOR__SENDMAIL:
			return ((eFlags & SENDMAIL_EFLAG) != 0) != SENDMAIL_EDEFAULT;
		case AggregatorPackage.AGGREGATOR__TYPE:
			return (eFlags & TYPE_EFLAG) != TYPE_EFLAG_DEFAULT;
		case AggregatorPackage.AGGREGATOR__MAVEN_RESULT:
			return ((eFlags & MAVEN_RESULT_EFLAG) != 0) != MAVEN_RESULT_EDEFAULT;
		case AggregatorPackage.AGGREGATOR__VALIDATION_REPOSITORIES:
			return validationRepositories != null && !validationRepositories.isEmpty();
		case AggregatorPackage.AGGREGATOR__MAVEN_MAPPINGS:
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
		case AggregatorPackage.AGGREGATOR__CONFIGURATIONS:
			getConfigurations().clear();
			getConfigurations().addAll((Collection<? extends Configuration>)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__CONTRIBUTIONS:
			getContributions().clear();
			getContributions().addAll((Collection<? extends Contribution>)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__BUILDMASTER:
			setBuildmaster((Contact)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__CONTACTS:
			getContacts().clear();
			getContacts().addAll((Collection<? extends Contact>)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__CUSTOM_CATEGORIES:
			getCustomCategories().clear();
			getCustomCategories().addAll((Collection<? extends CustomCategory>)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__LABEL:
			setLabel((String)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__BUILD_ROOT:
			setBuildRoot((String)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__PACKED_STRATEGY:
			setPackedStrategy((PackedStrategy)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__SENDMAIL:
			setSendmail((Boolean)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__TYPE:
			setType((AggregateType)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__MAVEN_RESULT:
			setMavenResult((Boolean)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__VALIDATION_REPOSITORIES:
			getValidationRepositories().clear();
			getValidationRepositories().addAll((Collection<? extends MetadataRepositoryReference>)newValue);
			return;
		case AggregatorPackage.AGGREGATOR__MAVEN_MAPPINGS:
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
		case AggregatorPackage.AGGREGATOR__CONFIGURATIONS:
			getConfigurations().clear();
			return;
		case AggregatorPackage.AGGREGATOR__CONTRIBUTIONS:
			getContributions().clear();
			return;
		case AggregatorPackage.AGGREGATOR__BUILDMASTER:
			setBuildmaster((Contact)null);
			return;
		case AggregatorPackage.AGGREGATOR__CONTACTS:
			getContacts().clear();
			return;
		case AggregatorPackage.AGGREGATOR__CUSTOM_CATEGORIES:
			getCustomCategories().clear();
			return;
		case AggregatorPackage.AGGREGATOR__LABEL:
			setLabel(LABEL_EDEFAULT);
			return;
		case AggregatorPackage.AGGREGATOR__BUILD_ROOT:
			setBuildRoot(BUILD_ROOT_EDEFAULT);
			return;
		case AggregatorPackage.AGGREGATOR__PACKED_STRATEGY:
			setPackedStrategy(PACKED_STRATEGY_EDEFAULT);
			return;
		case AggregatorPackage.AGGREGATOR__SENDMAIL:
			setSendmail(SENDMAIL_EDEFAULT);
			return;
		case AggregatorPackage.AGGREGATOR__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case AggregatorPackage.AGGREGATOR__MAVEN_RESULT:
			setMavenResult(MAVEN_RESULT_EDEFAULT);
			return;
		case AggregatorPackage.AGGREGATOR__VALIDATION_REPOSITORIES:
			getValidationRepositories().clear();
			return;
		case AggregatorPackage.AGGREGATOR__MAVEN_MAPPINGS:
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
	public EList<MetadataRepositoryReference> getAllMetadataRepositoryReferences(boolean enabledOnly)
	{
		EList<MetadataRepositoryReference> allRepos = new BasicEList<MetadataRepositoryReference>();

		for(Contribution contribution : getContributions(enabledOnly))
			for(MappedRepository mappedRepository : contribution.getRepositories(enabledOnly))
				allRepos.add(mappedRepository);

		allRepos.addAll(getValidationRepositories(enabledOnly));

		return allRepos;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Contact getBuildmaster()
	{
		if(buildmaster != null && ((EObject)buildmaster).eIsProxy())
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
			contacts = new EObjectContainmentWithInverseEList<Contact>(Contact.class, this,
					AggregatorPackage.AGGREGATOR__CONTACTS, AggregatorPackage.CONTACT__AGGREGATOR);
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
	 * @generated NOT
	 */
	public EList<Contribution> getContributions(boolean enabledOnly)
	{
		EList<Contribution> contribs = getContributions();
		if(enabledOnly)
		{
			EList<Contribution> enabledContribs = null;
			int top = contribs.size();
			for(int idx = 0; idx < top; ++idx)
			{
				Contribution contrib = contribs.get(idx);
				if(contrib.isEnabled())
				{
					if(enabledContribs != null)
						enabledContribs.add(contrib);
					continue;
				}

				if(enabledContribs == null)
				{
					enabledContribs = new BasicEList<Contribution>(contribs.size() - 1);
					for(int sdx = 0; sdx < idx; ++sdx)
						enabledContribs.add(contribs.get(sdx));
				}
			}
			if(enabledContribs != null)
				contribs = enabledContribs;
		}
		return contribs;
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
					AggregatorPackage.AGGREGATOR__MAVEN_MAPPINGS);
		}
		return mavenMappings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PackedStrategy getPackedStrategy()
	{
		return PACKED_STRATEGY_EFLAG_VALUES[(eFlags & PACKED_STRATEGY_EFLAG) >>> PACKED_STRATEGY_EFLAG_OFFSET];
	}

	synchronized public Status getStatus()
	{
		StatusCode statusCode;
		for(Contribution contribution : getContributions())
		{
			if((statusCode = contribution.getStatus().getCode()) != StatusCode.OK && statusCode != StatusCode.WAITING)
				return AggregatorFactory.eINSTANCE.createStatus(StatusCode.BROKEN);
		}
		for(MetadataRepositoryReference repo : getValidationRepositories(true))
		{
			if((statusCode = repo.getStatus().getCode()) != StatusCode.OK && statusCode != StatusCode.WAITING)
				return AggregatorFactory.eINSTANCE.createStatus(StatusCode.BROKEN);
		}
		for(MavenMapping mapping : getMavenMappings())
		{
			if((statusCode = mapping.getStatus().getCode()) != StatusCode.OK && statusCode != StatusCode.WAITING)
				return AggregatorFactory.eINSTANCE.createStatus(StatusCode.BROKEN);
		}
		return AggregatorFactory.eINSTANCE.createStatus(StatusCode.OK);
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
	public EList<MetadataRepositoryReference> getValidationRepositories()
	{
		if(validationRepositories == null)
		{
			validationRepositories = new EObjectContainmentEList<MetadataRepositoryReference>(
					MetadataRepositoryReference.class, this, AggregatorPackage.AGGREGATOR__VALIDATION_REPOSITORIES);
		}
		return validationRepositories;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<MetadataRepositoryReference> getValidationRepositories(boolean enabledOnly)
	{
		EList<MetadataRepositoryReference> validationRepositories = getValidationRepositories();
		if(enabledOnly)
		{
			EList<MetadataRepositoryReference> enabledValidationRepositories = null;
			int top = validationRepositories.size();
			for(int idx = 0; idx < top; ++idx)
			{
				MetadataRepositoryReference validationRepository = validationRepositories.get(idx);
				if(validationRepository.isEnabled())
				{
					if(enabledValidationRepositories != null)
						enabledValidationRepositories.add(validationRepository);
					continue;
				}

				if(enabledValidationRepositories == null)
				{
					enabledValidationRepositories = new BasicEList<MetadataRepositoryReference>(
							validationRepositories.size() - 1);
					for(int sdx = 0; sdx < idx; ++sdx)
						enabledValidationRepositories.add(validationRepositories.get(sdx));
				}
			}
			if(enabledValidationRepositories != null)
				validationRepositories = enabledValidationRepositories;
		}
		return validationRepositories;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isMavenResult()
	{
		return (eFlags & MAVEN_RESULT_EFLAG) != 0;
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
	public void setMavenResult(boolean newMavenResult)
	{
		boolean oldMavenResult = (eFlags & MAVEN_RESULT_EFLAG) != 0;
		if(newMavenResult)
			eFlags |= MAVEN_RESULT_EFLAG;
		else
			eFlags &= ~MAVEN_RESULT_EFLAG;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.AGGREGATOR__MAVEN_RESULT,
					oldMavenResult, newMavenResult));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPackedStrategy(PackedStrategy newPackedStrategy)
	{
		PackedStrategy oldPackedStrategy = PACKED_STRATEGY_EFLAG_VALUES[(eFlags & PACKED_STRATEGY_EFLAG) >>> PACKED_STRATEGY_EFLAG_OFFSET];
		if(newPackedStrategy == null)
			newPackedStrategy = PACKED_STRATEGY_EDEFAULT;
		eFlags = eFlags & ~PACKED_STRATEGY_EFLAG | newPackedStrategy.ordinal() << PACKED_STRATEGY_EFLAG_OFFSET;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.AGGREGATOR__PACKED_STRATEGY,
					oldPackedStrategy, newPackedStrategy));
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
		result.append(" (label: ");
		result.append(label);
		result.append(", buildRoot: ");
		result.append(buildRoot);
		result.append(", packedStrategy: ");
		result.append(PACKED_STRATEGY_EFLAG_VALUES[(eFlags & PACKED_STRATEGY_EFLAG) >>> PACKED_STRATEGY_EFLAG_OFFSET]);
		result.append(", sendmail: ");
		result.append((eFlags & SENDMAIL_EFLAG) != 0);
		result.append(", type: ");
		result.append(TYPE_EFLAG_VALUES[(eFlags & TYPE_EFLAG) >>> TYPE_EFLAG_OFFSET]);
		result.append(", mavenResult: ");
		result.append((eFlags & MAVEN_RESULT_EFLAG) != 0);
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

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import java.util.Collection;

import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.CustomCategory;
import org.eclipse.buckminster.aggregator.Feature;
import org.eclipse.buckminster.aggregator.InfosProvider;
import org.eclipse.buckminster.aggregator.Status;
import org.eclipse.buckminster.aggregator.StatusCode;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Added Category</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.CustomCategoryImpl#getStatus <em>Status</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.CustomCategoryImpl#getErrors <em>Errors</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.CustomCategoryImpl#getWarnings <em>Warnings</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.CustomCategoryImpl#getInfos <em>Infos</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.CustomCategoryImpl#getIdentifier <em>Identifier</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.CustomCategoryImpl#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.CustomCategoryImpl#getDescription <em>Description</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.CustomCategoryImpl#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class CustomCategoryImpl extends MinimalEObjectImpl.Container implements CustomCategory
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
	 * The cached value of the '{@link #getErrors() <em>Errors</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getErrors()
	 * @generated
	 * @ordered
	 */
	protected EList<String> errors;

	/**
	 * The cached value of the '{@link #getWarnings() <em>Warnings</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getWarnings()
	 * @generated
	 * @ordered
	 */
	protected EList<String> warnings;

	/**
	 * The cached value of the '{@link #getInfos() <em>Infos</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getInfos()
	 * @generated
	 * @ordered
	 */
	protected EList<String> infos;

	/**
	 * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String identifier = IDENTIFIER_EDEFAULT;

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
	 * The cached value of the '{@link #getFeatures() <em>Features</em>}' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList<Feature> features;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CustomCategoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if(baseClass == InfosProvider.class)
		{
			switch(derivedFeatureID)
			{
			case AggregatorPackage.CUSTOM_CATEGORY__ERRORS:
				return AggregatorPackage.INFOS_PROVIDER__ERRORS;
			case AggregatorPackage.CUSTOM_CATEGORY__WARNINGS:
				return AggregatorPackage.INFOS_PROVIDER__WARNINGS;
			case AggregatorPackage.CUSTOM_CATEGORY__INFOS:
				return AggregatorPackage.INFOS_PROVIDER__INFOS;
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
		if(baseClass == InfosProvider.class)
		{
			switch(baseFeatureID)
			{
			case AggregatorPackage.INFOS_PROVIDER__ERRORS:
				return AggregatorPackage.CUSTOM_CATEGORY__ERRORS;
			case AggregatorPackage.INFOS_PROVIDER__WARNINGS:
				return AggregatorPackage.CUSTOM_CATEGORY__WARNINGS;
			case AggregatorPackage.INFOS_PROVIDER__INFOS:
				return AggregatorPackage.CUSTOM_CATEGORY__INFOS;
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
		case AggregatorPackage.CUSTOM_CATEGORY__STATUS:
			return getStatus();
		case AggregatorPackage.CUSTOM_CATEGORY__ERRORS:
			return getErrors();
		case AggregatorPackage.CUSTOM_CATEGORY__WARNINGS:
			return getWarnings();
		case AggregatorPackage.CUSTOM_CATEGORY__INFOS:
			return getInfos();
		case AggregatorPackage.CUSTOM_CATEGORY__IDENTIFIER:
			return getIdentifier();
		case AggregatorPackage.CUSTOM_CATEGORY__LABEL:
			return getLabel();
		case AggregatorPackage.CUSTOM_CATEGORY__DESCRIPTION:
			return getDescription();
		case AggregatorPackage.CUSTOM_CATEGORY__FEATURES:
			return getFeatures();
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
		case AggregatorPackage.CUSTOM_CATEGORY__FEATURES:
			return ((InternalEList<InternalEObject>)(InternalEList<?>)getFeatures()).basicAdd(otherEnd, msgs);
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
		case AggregatorPackage.CUSTOM_CATEGORY__FEATURES:
			return ((InternalEList<?>)getFeatures()).basicRemove(otherEnd, msgs);
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
		case AggregatorPackage.CUSTOM_CATEGORY__STATUS:
			return getStatus() != null;
		case AggregatorPackage.CUSTOM_CATEGORY__ERRORS:
			return errors != null && !errors.isEmpty();
		case AggregatorPackage.CUSTOM_CATEGORY__WARNINGS:
			return warnings != null && !warnings.isEmpty();
		case AggregatorPackage.CUSTOM_CATEGORY__INFOS:
			return infos != null && !infos.isEmpty();
		case AggregatorPackage.CUSTOM_CATEGORY__IDENTIFIER:
			return IDENTIFIER_EDEFAULT == null
					? identifier != null
					: !IDENTIFIER_EDEFAULT.equals(identifier);
		case AggregatorPackage.CUSTOM_CATEGORY__LABEL:
			return LABEL_EDEFAULT == null
					? label != null
					: !LABEL_EDEFAULT.equals(label);
		case AggregatorPackage.CUSTOM_CATEGORY__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null
					? description != null
					: !DESCRIPTION_EDEFAULT.equals(description);
		case AggregatorPackage.CUSTOM_CATEGORY__FEATURES:
			return features != null && !features.isEmpty();
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
		case AggregatorPackage.CUSTOM_CATEGORY__ERRORS:
			getErrors().clear();
			getErrors().addAll((Collection<? extends String>)newValue);
			return;
		case AggregatorPackage.CUSTOM_CATEGORY__WARNINGS:
			getWarnings().clear();
			getWarnings().addAll((Collection<? extends String>)newValue);
			return;
		case AggregatorPackage.CUSTOM_CATEGORY__INFOS:
			getInfos().clear();
			getInfos().addAll((Collection<? extends String>)newValue);
			return;
		case AggregatorPackage.CUSTOM_CATEGORY__IDENTIFIER:
			setIdentifier((String)newValue);
			return;
		case AggregatorPackage.CUSTOM_CATEGORY__LABEL:
			setLabel((String)newValue);
			return;
		case AggregatorPackage.CUSTOM_CATEGORY__DESCRIPTION:
			setDescription((String)newValue);
			return;
		case AggregatorPackage.CUSTOM_CATEGORY__FEATURES:
			getFeatures().clear();
			getFeatures().addAll((Collection<? extends Feature>)newValue);
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
		case AggregatorPackage.CUSTOM_CATEGORY__ERRORS:
			getErrors().clear();
			return;
		case AggregatorPackage.CUSTOM_CATEGORY__WARNINGS:
			getWarnings().clear();
			return;
		case AggregatorPackage.CUSTOM_CATEGORY__INFOS:
			getInfos().clear();
			return;
		case AggregatorPackage.CUSTOM_CATEGORY__IDENTIFIER:
			setIdentifier(IDENTIFIER_EDEFAULT);
			return;
		case AggregatorPackage.CUSTOM_CATEGORY__LABEL:
			setLabel(LABEL_EDEFAULT);
			return;
		case AggregatorPackage.CUSTOM_CATEGORY__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case AggregatorPackage.CUSTOM_CATEGORY__FEATURES:
			getFeatures().clear();
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
	public EList<String> getErrors()
	{
		if(errors == null)
		{
			errors = new EDataTypeUniqueEList<String>(String.class, this, AggregatorPackage.CUSTOM_CATEGORY__ERRORS);
		}
		return errors;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Feature> getFeatures()
	{
		if(features == null)
		{
			features = new EObjectWithInverseResolvingEList.ManyInverse<Feature>(Feature.class, this,
					AggregatorPackage.CUSTOM_CATEGORY__FEATURES, AggregatorPackage.FEATURE__CATEGORIES);
		}
		return features;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getIdentifier()
	{
		return identifier;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<String> getInfos()
	{
		if(infos == null)
		{
			infos = new EDataTypeUniqueEList<String>(String.class, this, AggregatorPackage.CUSTOM_CATEGORY__INFOS);
		}
		return infos;
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

	synchronized public Status getStatus()
	{
		StatusCode statusCode;
		for(Feature feature : getFeatures())
		{
			if(feature.isEnabled() && (statusCode = feature.getStatus().getCode()) != StatusCode.OK
					&& statusCode != StatusCode.WAITING)
				return AggregatorFactory.eINSTANCE.createStatus(StatusCode.BROKEN);
		}
		return AggregatorFactory.eINSTANCE.createStatus(StatusCode.OK);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<String> getWarnings()
	{
		if(warnings == null)
		{
			warnings = new EDataTypeUniqueEList<String>(String.class, this, AggregatorPackage.CUSTOM_CATEGORY__WARNINGS);
		}
		return warnings;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.CUSTOM_CATEGORY__DESCRIPTION,
					oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIdentifier(String newIdentifier)
	{
		String oldIdentifier = identifier;
		identifier = newIdentifier;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.CUSTOM_CATEGORY__IDENTIFIER,
					oldIdentifier, identifier));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.CUSTOM_CATEGORY__LABEL, oldLabel,
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
		result.append(" (errors: ");
		result.append(errors);
		result.append(", warnings: ");
		result.append(warnings);
		result.append(", infos: ");
		result.append(infos);
		result.append(", identifier: ");
		result.append(identifier);
		result.append(", label: ");
		result.append(label);
		result.append(", description: ");
		result.append(description);
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
		return AggregatorPackage.Literals.CUSTOM_CATEGORY;
	}

} // CustomCategoryImpl

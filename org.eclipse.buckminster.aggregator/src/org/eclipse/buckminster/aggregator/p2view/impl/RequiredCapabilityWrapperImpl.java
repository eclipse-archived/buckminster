/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2view.impl;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.LabelProvider;

import org.eclipse.buckminster.aggregator.p2.RequiredCapability;

import org.eclipse.buckminster.aggregator.p2view.P2viewPackage;
import org.eclipse.buckminster.aggregator.p2view.RequiredCapabilityWrapper;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionRange;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Required Capability Wrapper</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.RequiredCapabilityWrapperImpl#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.RequiredCapabilityWrapperImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.RequiredCapabilityWrapperImpl#getNamespace <em>Namespace
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.RequiredCapabilityWrapperImpl#getRange <em>Range</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.RequiredCapabilityWrapperImpl#isNegation <em>Negation</em>}
 * </li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.RequiredCapabilityWrapperImpl#getSelectorList <em>Selector
 * List</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.RequiredCapabilityWrapperImpl#isMultiple <em>Multiple</em>}
 * </li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.RequiredCapabilityWrapperImpl#isOptional <em>Optional</em>}
 * </li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.RequiredCapabilityWrapperImpl#isGreedy <em>Greedy</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.RequiredCapabilityWrapperImpl#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.RequiredCapabilityWrapperImpl#getGenuine <em>Genuine</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
@SuppressWarnings("unused")
public class RequiredCapabilityWrapperImpl extends MinimalEObjectImpl.Container implements RequiredCapabilityWrapper
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
	 * The default value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected static final String FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected String filter = FILTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNamespace() <em>Namespace</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
	protected static final String NAMESPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNamespace() <em>Namespace</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
	protected String namespace = NAMESPACE_EDEFAULT;

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
	 * The default value of the '{@link #isNegation() <em>Negation</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isNegation()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEGATION_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isNegation() <em>Negation</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #isNegation()
	 * @generated
	 * @ordered
	 */
	protected static final int NEGATION_EFLAG = 1 << 0;

	/**
	 * The cached value of the '{@link #getSelectorList() <em>Selector List</em>}' attribute list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getSelectorList()
	 * @generated
	 * @ordered
	 */
	protected EList<String> selectorList;

	/**
	 * The default value of the '{@link #isMultiple() <em>Multiple</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isMultiple()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MULTIPLE_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isMultiple() <em>Multiple</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #isMultiple()
	 * @generated
	 * @ordered
	 */
	protected static final int MULTIPLE_EFLAG = 1 << 1;

	/**
	 * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPTIONAL_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isOptional() <em>Optional</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected static final int OPTIONAL_EFLAG = 1 << 2;

	/**
	 * The default value of the '{@link #isGreedy() <em>Greedy</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isGreedy()
	 * @generated
	 * @ordered
	 */
	protected static final boolean GREEDY_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isGreedy() <em>Greedy</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isGreedy()
	 * @generated
	 * @ordered
	 */
	protected static final int GREEDY_EFLAG = 1 << 3;

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
	 * The cached value of the '{@link #getGenuine() <em>Genuine</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGenuine()
	 * @generated
	 * @ordered
	 */
	protected RequiredCapability genuine;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RequiredCapabilityWrapperImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected RequiredCapabilityWrapperImpl(RequiredCapability rc)
	{
		super();
		genuine = rc;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if(baseClass == LabelProvider.class)
		{
			switch(derivedFeatureID)
			{
			case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__LABEL:
				return AggregatorPackage.LABEL_PROVIDER__LABEL;
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
		if(baseClass == LabelProvider.class)
		{
			switch(baseFeatureID)
			{
			case AggregatorPackage.LABEL_PROVIDER__LABEL:
				return P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__LABEL;
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
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__FILTER:
			return getFilter();
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__NAME:
			return getName();
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__NAMESPACE:
			return getNamespace();
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__RANGE:
			return getRange();
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__NEGATION:
			return isNegation();
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__SELECTOR_LIST:
			return getSelectorList();
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__MULTIPLE:
			return isMultiple();
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__OPTIONAL:
			return isOptional();
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__GREEDY:
			return isGreedy();
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__LABEL:
			return getLabel();
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__GENUINE:
			return getGenuine();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch(featureID)
		{
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__LABEL:
			return LABEL_EDEFAULT == null
					? label != null
					: !LABEL_EDEFAULT.equals(label);
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__GENUINE:
			return genuine != null;
		default:
			return ((InternalEObject)genuine).eIsSet(featureID);
		}
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
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__FILTER:
			setFilter((String)newValue);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__NAME:
			setName((String)newValue);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__NAMESPACE:
			setNamespace((String)newValue);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__RANGE:
			setRange((VersionRange)newValue);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__NEGATION:
			setNegation((Boolean)newValue);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__SELECTOR_LIST:
			getSelectorList().clear();
			getSelectorList().addAll((Collection<? extends String>)newValue);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__MULTIPLE:
			setMultiple((Boolean)newValue);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__OPTIONAL:
			setOptional((Boolean)newValue);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__GREEDY:
			setGreedy((Boolean)newValue);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__LABEL:
			setLabel((String)newValue);
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
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__FILTER:
			setFilter(FILTER_EDEFAULT);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__NAME:
			setName(NAME_EDEFAULT);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__NAMESPACE:
			setNamespace(NAMESPACE_EDEFAULT);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__RANGE:
			setRange(RANGE_EDEFAULT);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__NEGATION:
			setNegation(NEGATION_EDEFAULT);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__SELECTOR_LIST:
			getSelectorList().clear();
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__MULTIPLE:
			setMultiple(MULTIPLE_EDEFAULT);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__OPTIONAL:
			setOptional(OPTIONAL_EDEFAULT);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__GREEDY:
			setGreedy(GREEDY_EDEFAULT);
			return;
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__LABEL:
			setLabel(LABEL_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getFilter()
	{
		return genuine.getFilter();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RequiredCapability getGenuine()
	{
		return genuine;
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
	 * @generated NOT
	 */
	public String getName()
	{
		return genuine.getName();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getNamespace()
	{
		return genuine.getNamespace();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public VersionRange getRange()
	{
		return genuine.getRange();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<String> getSelectorList()
	{
		EList<String> eList = new BasicEList<String>();
		eList.addAll(Arrays.asList(genuine.getSelectors()));

		return eList;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String[] getSelectors()
	{
		return genuine.getSelectors();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isGreedy()
	{
		return genuine.isGreedy();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isMultiple()
	{
		return genuine.isMultiple();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isNegation()
	{
		return genuine.isNegation();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isOptional()
	{
		return genuine.isOptional();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean satisfiedBy(IProvidedCapability capability)
	{
		return genuine.satisfiedBy(capability);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setFilter(String newFilter)
	{
		String oldFilter = genuine.getFilter();
		genuine.setFilter(newFilter);
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__FILTER,
					oldFilter, newFilter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setGreedy(boolean newGreedy)
	{
		throw new UnsupportedOperationException();
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
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.REQUIRED_CAPABILITY_WRAPPER__LABEL,
					oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setMultiple(boolean newMultiple)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setName(String newName)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setNamespace(String newNamespace)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setNegation(boolean newNegation)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setOptional(boolean newOptional)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setRange(VersionRange newRange)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setSelectors(String[] selectors)
	{
		genuine.setSelectors(selectors);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String toString()
	{
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (filter: ");
		result.append(getFilter());
		result.append(", name: ");
		result.append(getName());
		result.append(", namespace: ");
		result.append(getNamespace());
		result.append(", range: ");
		result.append(getRange());
		result.append(", negation: ");
		result.append(isNegation());
		result.append(", selectorList: ");
		result.append(getSelectorList());
		result.append(", multiple: ");
		result.append(isMultiple());
		result.append(", optional: ");
		result.append(isOptional());
		result.append(", greedy: ");
		result.append(isGreedy());
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
		return P2viewPackage.Literals.REQUIRED_CAPABILITY_WRAPPER;
	}

} // RequiredCapabilityWrapperImpl

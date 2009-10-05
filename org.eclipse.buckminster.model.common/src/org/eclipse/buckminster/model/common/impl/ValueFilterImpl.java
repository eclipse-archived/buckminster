/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.Collection;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Constant;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.model.common.PropertyRef;
import org.eclipse.buckminster.model.common.Replace;
import org.eclipse.buckminster.model.common.Split;
import org.eclipse.buckminster.model.common.ToLower;
import org.eclipse.buckminster.model.common.ToUpper;
import org.eclipse.buckminster.model.common.ValueFilter;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Value Filter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.impl.ValueFilterImpl#getConstants <em>Constants</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.impl.ValueFilterImpl#getFormats <em>Formats</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.impl.ValueFilterImpl#getPropertyRefs <em>Property Refs</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.impl.ValueFilterImpl#getReplacements <em>Replacements</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.impl.ValueFilterImpl#getSplits <em>Splits</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.impl.ValueFilterImpl#getToLowers <em>To Lowers</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.impl.ValueFilterImpl#getToUppers <em>To Uppers</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class ValueFilterImpl extends EObjectImpl implements ValueFilter
{
	/**
	 * The cached value of the '{@link #getConstants() <em>Constants</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getConstants()
	 * @generated
	 * @ordered
	 */
	protected EList<Constant> constants;

	/**
	 * The cached value of the '{@link #getFormats() <em>Formats</em>}' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getFormats()
	 * @generated
	 * @ordered
	 */
	protected EList<Format> formats;

	/**
	 * The cached value of the '{@link #getPropertyRefs() <em>Property Refs</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPropertyRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyRef> propertyRefs;

	/**
	 * The cached value of the '{@link #getReplacements() <em>Replacements</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReplacements()
	 * @generated
	 * @ordered
	 */
	protected EList<Replace> replacements;

	/**
	 * The cached value of the '{@link #getSplits() <em>Splits</em>}' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getSplits()
	 * @generated
	 * @ordered
	 */
	protected EList<Split> splits;

	/**
	 * The cached value of the '{@link #getToLowers() <em>To Lowers</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getToLowers()
	 * @generated
	 * @ordered
	 */
	protected EList<ToLower> toLowers;

	/**
	 * The cached value of the '{@link #getToUppers() <em>To Uppers</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getToUppers()
	 * @generated
	 * @ordered
	 */
	protected EList<ToUpper> toUppers;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ValueFilterImpl()
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
		case CommonPackage.VALUE_FILTER__CONSTANTS:
			return getConstants();
		case CommonPackage.VALUE_FILTER__FORMATS:
			return getFormats();
		case CommonPackage.VALUE_FILTER__PROPERTY_REFS:
			return getPropertyRefs();
		case CommonPackage.VALUE_FILTER__REPLACEMENTS:
			return getReplacements();
		case CommonPackage.VALUE_FILTER__SPLITS:
			return getSplits();
		case CommonPackage.VALUE_FILTER__TO_LOWERS:
			return getToLowers();
		case CommonPackage.VALUE_FILTER__TO_UPPERS:
			return getToUppers();
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
		case CommonPackage.VALUE_FILTER__CONSTANTS:
			return ((InternalEList<?>)getConstants()).basicRemove(otherEnd, msgs);
		case CommonPackage.VALUE_FILTER__FORMATS:
			return ((InternalEList<?>)getFormats()).basicRemove(otherEnd, msgs);
		case CommonPackage.VALUE_FILTER__PROPERTY_REFS:
			return ((InternalEList<?>)getPropertyRefs()).basicRemove(otherEnd, msgs);
		case CommonPackage.VALUE_FILTER__REPLACEMENTS:
			return ((InternalEList<?>)getReplacements()).basicRemove(otherEnd, msgs);
		case CommonPackage.VALUE_FILTER__SPLITS:
			return ((InternalEList<?>)getSplits()).basicRemove(otherEnd, msgs);
		case CommonPackage.VALUE_FILTER__TO_LOWERS:
			return ((InternalEList<?>)getToLowers()).basicRemove(otherEnd, msgs);
		case CommonPackage.VALUE_FILTER__TO_UPPERS:
			return ((InternalEList<?>)getToUppers()).basicRemove(otherEnd, msgs);
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
		case CommonPackage.VALUE_FILTER__CONSTANTS:
			return constants != null && !constants.isEmpty();
		case CommonPackage.VALUE_FILTER__FORMATS:
			return formats != null && !formats.isEmpty();
		case CommonPackage.VALUE_FILTER__PROPERTY_REFS:
			return propertyRefs != null && !propertyRefs.isEmpty();
		case CommonPackage.VALUE_FILTER__REPLACEMENTS:
			return replacements != null && !replacements.isEmpty();
		case CommonPackage.VALUE_FILTER__SPLITS:
			return splits != null && !splits.isEmpty();
		case CommonPackage.VALUE_FILTER__TO_LOWERS:
			return toLowers != null && !toLowers.isEmpty();
		case CommonPackage.VALUE_FILTER__TO_UPPERS:
			return toUppers != null && !toUppers.isEmpty();
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
		case CommonPackage.VALUE_FILTER__CONSTANTS:
			getConstants().clear();
			getConstants().addAll((Collection<? extends Constant>)newValue);
			return;
		case CommonPackage.VALUE_FILTER__FORMATS:
			getFormats().clear();
			getFormats().addAll((Collection<? extends Format>)newValue);
			return;
		case CommonPackage.VALUE_FILTER__PROPERTY_REFS:
			getPropertyRefs().clear();
			getPropertyRefs().addAll((Collection<? extends PropertyRef>)newValue);
			return;
		case CommonPackage.VALUE_FILTER__REPLACEMENTS:
			getReplacements().clear();
			getReplacements().addAll((Collection<? extends Replace>)newValue);
			return;
		case CommonPackage.VALUE_FILTER__SPLITS:
			getSplits().clear();
			getSplits().addAll((Collection<? extends Split>)newValue);
			return;
		case CommonPackage.VALUE_FILTER__TO_LOWERS:
			getToLowers().clear();
			getToLowers().addAll((Collection<? extends ToLower>)newValue);
			return;
		case CommonPackage.VALUE_FILTER__TO_UPPERS:
			getToUppers().clear();
			getToUppers().addAll((Collection<? extends ToUpper>)newValue);
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
		case CommonPackage.VALUE_FILTER__CONSTANTS:
			getConstants().clear();
			return;
		case CommonPackage.VALUE_FILTER__FORMATS:
			getFormats().clear();
			return;
		case CommonPackage.VALUE_FILTER__PROPERTY_REFS:
			getPropertyRefs().clear();
			return;
		case CommonPackage.VALUE_FILTER__REPLACEMENTS:
			getReplacements().clear();
			return;
		case CommonPackage.VALUE_FILTER__SPLITS:
			getSplits().clear();
			return;
		case CommonPackage.VALUE_FILTER__TO_LOWERS:
			getToLowers().clear();
			return;
		case CommonPackage.VALUE_FILTER__TO_UPPERS:
			getToUppers().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Constant> getConstants()
	{
		if(constants == null)
		{
			constants = new EObjectContainmentEList<Constant>(Constant.class, this,
					CommonPackage.VALUE_FILTER__CONSTANTS);
		}
		return constants;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Format> getFormats()
	{
		if(formats == null)
		{
			formats = new EObjectContainmentEList<Format>(Format.class, this, CommonPackage.VALUE_FILTER__FORMATS);
		}
		return formats;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<PropertyRef> getPropertyRefs()
	{
		if(propertyRefs == null)
		{
			propertyRefs = new EObjectContainmentEList<PropertyRef>(PropertyRef.class, this,
					CommonPackage.VALUE_FILTER__PROPERTY_REFS);
		}
		return propertyRefs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Replace> getReplacements()
	{
		if(replacements == null)
		{
			replacements = new EObjectContainmentEList<Replace>(Replace.class, this,
					CommonPackage.VALUE_FILTER__REPLACEMENTS);
		}
		return replacements;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Split> getSplits()
	{
		if(splits == null)
		{
			splits = new EObjectContainmentEList<Split>(Split.class, this, CommonPackage.VALUE_FILTER__SPLITS);
		}
		return splits;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<ToLower> getToLowers()
	{
		if(toLowers == null)
		{
			toLowers = new EObjectContainmentEList<ToLower>(ToLower.class, this, CommonPackage.VALUE_FILTER__TO_LOWERS);
		}
		return toLowers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<ToUpper> getToUppers()
	{
		if(toUppers == null)
		{
			toUppers = new EObjectContainmentEList<ToUpper>(ToUpper.class, this, CommonPackage.VALUE_FILTER__TO_UPPERS);
		}
		return toUppers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return CommonPackage.Literals.VALUE_FILTER;
	}

} // ValueFilterImpl

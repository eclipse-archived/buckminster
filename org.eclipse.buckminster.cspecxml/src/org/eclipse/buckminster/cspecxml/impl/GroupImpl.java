/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IDefinitions;
import org.eclipse.buckminster.cspecxml.IGroup;
import org.eclipse.buckminster.cspecxml.IPrerequisite;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Group</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.GroupImpl#getGroup <em>Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.GroupImpl#getDefinitions <em>Definitions</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.GroupImpl#getAttribute <em>Attribute</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.GroupImpl#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.GroupImpl#getRebase <em>Rebase</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class GroupImpl extends AttributeImpl implements IGroup
{
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected static final Filter FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected Filter filter = FILTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getRebase() <em>Rebase</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getRebase()
	 * @generated
	 * @ordered
	 */
	protected static final String REBASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRebase() <em>Rebase</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getRebase()
	 * @generated
	 * @ordered
	 */
	protected String rebase = REBASE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected GroupImpl()
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
		case ICSpecXMLPackage.GROUP__GROUP:
			if(coreType)
				return getGroup();
			return ((FeatureMap.Internal)getGroup()).getWrapper();
		case ICSpecXMLPackage.GROUP__DEFINITIONS:
			return getDefinitions();
		case ICSpecXMLPackage.GROUP__ATTRIBUTE:
			return getAttribute();
		case ICSpecXMLPackage.GROUP__FILTER:
			return getFilter();
		case ICSpecXMLPackage.GROUP__REBASE:
			return getRebase();
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
		case ICSpecXMLPackage.GROUP__GROUP:
			return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
		case ICSpecXMLPackage.GROUP__DEFINITIONS:
			return ((InternalEList<?>)getDefinitions()).basicRemove(otherEnd, msgs);
		case ICSpecXMLPackage.GROUP__ATTRIBUTE:
			return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
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
		case ICSpecXMLPackage.GROUP__GROUP:
			return group != null && !group.isEmpty();
		case ICSpecXMLPackage.GROUP__DEFINITIONS:
			return !getDefinitions().isEmpty();
		case ICSpecXMLPackage.GROUP__ATTRIBUTE:
			return !getAttribute().isEmpty();
		case ICSpecXMLPackage.GROUP__FILTER:
			return FILTER_EDEFAULT == null
					? filter != null
					: !FILTER_EDEFAULT.equals(filter);
		case ICSpecXMLPackage.GROUP__REBASE:
			return REBASE_EDEFAULT == null
					? rebase != null
					: !REBASE_EDEFAULT.equals(rebase);
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
		case ICSpecXMLPackage.GROUP__GROUP:
			((FeatureMap.Internal)getGroup()).set(newValue);
			return;
		case ICSpecXMLPackage.GROUP__DEFINITIONS:
			getDefinitions().clear();
			getDefinitions().addAll((Collection<? extends IDefinitions>)newValue);
			return;
		case ICSpecXMLPackage.GROUP__ATTRIBUTE:
			getAttribute().clear();
			getAttribute().addAll((Collection<? extends IPrerequisite>)newValue);
			return;
		case ICSpecXMLPackage.GROUP__FILTER:
			setFilter((Filter)newValue);
			return;
		case ICSpecXMLPackage.GROUP__REBASE:
			setRebase((String)newValue);
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
		case ICSpecXMLPackage.GROUP__GROUP:
			getGroup().clear();
			return;
		case ICSpecXMLPackage.GROUP__DEFINITIONS:
			getDefinitions().clear();
			return;
		case ICSpecXMLPackage.GROUP__ATTRIBUTE:
			getAttribute().clear();
			return;
		case ICSpecXMLPackage.GROUP__FILTER:
			setFilter(FILTER_EDEFAULT);
			return;
		case ICSpecXMLPackage.GROUP__REBASE:
			setRebase(REBASE_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IPrerequisite> getAttribute()
	{
		return getGroup().list(ICSpecXMLPackage.Literals.GROUP__ATTRIBUTE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IDefinitions> getDefinitions()
	{
		return getGroup().list(ICSpecXMLPackage.Literals.GROUP__DEFINITIONS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Filter getFilter()
	{
		return filter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FeatureMap getGroup()
	{
		if(group == null)
		{
			group = new BasicFeatureMap(this, ICSpecXMLPackage.GROUP__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getRebase()
	{
		return rebase;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFilter(Filter newFilter)
	{
		Filter oldFilter = filter;
		filter = newFilter;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.GROUP__FILTER, oldFilter, filter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setRebase(String newRebase)
	{
		String oldRebase = rebase;
		rebase = newRebase;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.GROUP__REBASE, oldRebase, rebase));
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
		result.append(" (group: ");
		result.append(group);
		result.append(", filter: ");
		result.append(filter);
		result.append(", rebase: ");
		result.append(rebase);
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
		return ICSpecXMLPackage.Literals.GROUP;
	}

} // GroupImpl

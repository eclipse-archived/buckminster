/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspecxml.IArtifact;
import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IDefinitions;
import org.eclipse.buckminster.cspecxml.IPath;
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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Artifact</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ArtifactImpl#getGroup <em>Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ArtifactImpl#getDefinitions <em>Definitions</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ArtifactImpl#getPath <em>Path</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ArtifactImpl#getBase <em>Base</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ArtifactImpl#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ArtifactImpl#getPath1 <em>Path1</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ArtifactImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ArtifactImpl extends AttributeImpl implements IArtifact
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
	 * The default value of the '{@link #getBase() <em>Base</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBase() <em>Base</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected String base = BASE_EDEFAULT;

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
	 * The default value of the '{@link #getPath1() <em>Path1</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPath1()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH1_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath1() <em>Path1</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getPath1()
	 * @generated
	 * @ordered
	 */
	protected String path1 = PATH1_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ArtifactImpl()
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
		case ICSpecXMLPackage.ARTIFACT__GROUP:
			if(coreType)
				return getGroup();
			return ((FeatureMap.Internal)getGroup()).getWrapper();
		case ICSpecXMLPackage.ARTIFACT__DEFINITIONS:
			return getDefinitions();
		case ICSpecXMLPackage.ARTIFACT__PATH:
			return getPath();
		case ICSpecXMLPackage.ARTIFACT__BASE:
			return getBase();
		case ICSpecXMLPackage.ARTIFACT__FILTER:
			return getFilter();
		case ICSpecXMLPackage.ARTIFACT__PATH1:
			return getPath1();
		case ICSpecXMLPackage.ARTIFACT__TYPE:
			return getType();
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
		case ICSpecXMLPackage.ARTIFACT__GROUP:
			return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
		case ICSpecXMLPackage.ARTIFACT__DEFINITIONS:
			return ((InternalEList<?>)getDefinitions()).basicRemove(otherEnd, msgs);
		case ICSpecXMLPackage.ARTIFACT__PATH:
			return ((InternalEList<?>)getPath()).basicRemove(otherEnd, msgs);
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
		case ICSpecXMLPackage.ARTIFACT__GROUP:
			return group != null && !group.isEmpty();
		case ICSpecXMLPackage.ARTIFACT__DEFINITIONS:
			return !getDefinitions().isEmpty();
		case ICSpecXMLPackage.ARTIFACT__PATH:
			return !getPath().isEmpty();
		case ICSpecXMLPackage.ARTIFACT__BASE:
			return BASE_EDEFAULT == null
					? base != null
					: !BASE_EDEFAULT.equals(base);
		case ICSpecXMLPackage.ARTIFACT__FILTER:
			return FILTER_EDEFAULT == null
					? filter != null
					: !FILTER_EDEFAULT.equals(filter);
		case ICSpecXMLPackage.ARTIFACT__PATH1:
			return PATH1_EDEFAULT == null
					? path1 != null
					: !PATH1_EDEFAULT.equals(path1);
		case ICSpecXMLPackage.ARTIFACT__TYPE:
			return TYPE_EDEFAULT == null
					? type != null
					: !TYPE_EDEFAULT.equals(type);
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
		case ICSpecXMLPackage.ARTIFACT__GROUP:
			((FeatureMap.Internal)getGroup()).set(newValue);
			return;
		case ICSpecXMLPackage.ARTIFACT__DEFINITIONS:
			getDefinitions().clear();
			getDefinitions().addAll((Collection<? extends IDefinitions>)newValue);
			return;
		case ICSpecXMLPackage.ARTIFACT__PATH:
			getPath().clear();
			getPath().addAll((Collection<? extends IPath>)newValue);
			return;
		case ICSpecXMLPackage.ARTIFACT__BASE:
			setBase((String)newValue);
			return;
		case ICSpecXMLPackage.ARTIFACT__FILTER:
			setFilter((Filter)newValue);
			return;
		case ICSpecXMLPackage.ARTIFACT__PATH1:
			setPath1((String)newValue);
			return;
		case ICSpecXMLPackage.ARTIFACT__TYPE:
			setType((String)newValue);
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
		case ICSpecXMLPackage.ARTIFACT__GROUP:
			getGroup().clear();
			return;
		case ICSpecXMLPackage.ARTIFACT__DEFINITIONS:
			getDefinitions().clear();
			return;
		case ICSpecXMLPackage.ARTIFACT__PATH:
			getPath().clear();
			return;
		case ICSpecXMLPackage.ARTIFACT__BASE:
			setBase(BASE_EDEFAULT);
			return;
		case ICSpecXMLPackage.ARTIFACT__FILTER:
			setFilter(FILTER_EDEFAULT);
			return;
		case ICSpecXMLPackage.ARTIFACT__PATH1:
			setPath1(PATH1_EDEFAULT);
			return;
		case ICSpecXMLPackage.ARTIFACT__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getBase()
	{
		return base;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IDefinitions> getDefinitions()
	{
		return getGroup().list(ICSpecXMLPackage.Literals.ARTIFACT__DEFINITIONS);
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
			group = new BasicFeatureMap(this, ICSpecXMLPackage.ARTIFACT__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IPath> getPath()
	{
		return getGroup().list(ICSpecXMLPackage.Literals.ARTIFACT__PATH);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getPath1()
	{
		return path1;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setBase(String newBase)
	{
		String oldBase = base;
		base = newBase;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.ARTIFACT__BASE, oldBase, base));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.ARTIFACT__FILTER, oldFilter, filter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPath1(String newPath1)
	{
		String oldPath1 = path1;
		path1 = newPath1;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.ARTIFACT__PATH1, oldPath1, path1));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setType(String newType)
	{
		String oldType = type;
		type = newType;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.ARTIFACT__TYPE, oldType, type));
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
		result.append(", base: ");
		result.append(base);
		result.append(", filter: ");
		result.append(filter);
		result.append(", path1: ");
		result.append(path1);
		result.append(", type: ");
		result.append(type);
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
		return ICSpecXMLPackage.Literals.ARTIFACT;
	}

} // ArtifactImpl

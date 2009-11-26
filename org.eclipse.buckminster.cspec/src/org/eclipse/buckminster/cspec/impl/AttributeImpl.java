/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import org.eclipse.buckminster.cspec.Attribute;
import org.eclipse.buckminster.cspec.CSpec;
import org.eclipse.buckminster.cspec.CspecPackage;

import org.eclipse.buckminster.cspec.IContext;
import org.eclipse.buckminster.model.common.Documentation;

import org.eclipse.buckminster.osgi.filter.Filter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Attribute</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.impl.AttributeImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.AttributeImpl#isPublic <em>Public</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.AttributeImpl#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.AttributeImpl#getCspec <em>Cspec</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.AttributeImpl#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class AttributeImpl extends EObjectImpl implements Attribute
{
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
	 * The default value of the '{@link #isPublic() <em>Public</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isPublic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PUBLIC_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isPublic() <em>Public</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isPublic()
	 * @generated
	 * @ordered
	 */
	protected boolean public_ = PUBLIC_EDEFAULT;

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
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected Documentation documentation;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AttributeImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetCspec(CSpec newCspec, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newCspec, CspecPackage.ATTRIBUTE__CSPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDocumentation(Documentation newDocumentation, NotificationChain msgs)
	{
		Documentation oldDocumentation = documentation;
		documentation = newDocumentation;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					CspecPackage.ATTRIBUTE__DOCUMENTATION, oldDocumentation, newDocumentation);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
	{
		switch(eContainerFeatureID())
		{
		case CspecPackage.ATTRIBUTE__CSPEC:
			return eInternalContainer().eInverseRemove(this, CspecPackage.CSPEC__ATTRIBUTES, CSpec.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
		case CspecPackage.ATTRIBUTE__NAME:
			return getName();
		case CspecPackage.ATTRIBUTE__PUBLIC:
			return isPublic();
		case CspecPackage.ATTRIBUTE__FILTER:
			return getFilter();
		case CspecPackage.ATTRIBUTE__CSPEC:
			return getCspec();
		case CspecPackage.ATTRIBUTE__DOCUMENTATION:
			return getDocumentation();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch(featureID)
		{
		case CspecPackage.ATTRIBUTE__CSPEC:
			if(eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetCspec((CSpec)otherEnd, msgs);
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
		case CspecPackage.ATTRIBUTE__CSPEC:
			return basicSetCspec(null, msgs);
		case CspecPackage.ATTRIBUTE__DOCUMENTATION:
			return basicSetDocumentation(null, msgs);
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
		case CspecPackage.ATTRIBUTE__NAME:
			return NAME_EDEFAULT == null
					? name != null
					: !NAME_EDEFAULT.equals(name);
		case CspecPackage.ATTRIBUTE__PUBLIC:
			return public_ != PUBLIC_EDEFAULT;
		case CspecPackage.ATTRIBUTE__FILTER:
			return FILTER_EDEFAULT == null
					? filter != null
					: !FILTER_EDEFAULT.equals(filter);
		case CspecPackage.ATTRIBUTE__CSPEC:
			return getCspec() != null;
		case CspecPackage.ATTRIBUTE__DOCUMENTATION:
			return documentation != null;
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
		case CspecPackage.ATTRIBUTE__NAME:
			setName((String)newValue);
			return;
		case CspecPackage.ATTRIBUTE__PUBLIC:
			setPublic((Boolean)newValue);
			return;
		case CspecPackage.ATTRIBUTE__FILTER:
			setFilter((Filter)newValue);
			return;
		case CspecPackage.ATTRIBUTE__CSPEC:
			setCspec((CSpec)newValue);
			return;
		case CspecPackage.ATTRIBUTE__DOCUMENTATION:
			setDocumentation((Documentation)newValue);
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
		case CspecPackage.ATTRIBUTE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case CspecPackage.ATTRIBUTE__PUBLIC:
			setPublic(PUBLIC_EDEFAULT);
			return;
		case CspecPackage.ATTRIBUTE__FILTER:
			setFilter(FILTER_EDEFAULT);
			return;
		case CspecPackage.ATTRIBUTE__CSPEC:
			setCspec((CSpec)null);
			return;
		case CspecPackage.ATTRIBUTE__DOCUMENTATION:
			setDocumentation((Documentation)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CSpec getCspec()
	{
		if(eContainerFeatureID() != CspecPackage.ATTRIBUTE__CSPEC)
			return null;
		return (CSpec)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Documentation getDocumentation()
	{
		return documentation;
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
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isDerived(IContext context)
	{
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isPublic()
	{
		return public_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setCspec(CSpec newCspec)
	{
		if(newCspec != eInternalContainer()
				|| (eContainerFeatureID() != CspecPackage.ATTRIBUTE__CSPEC && newCspec != null))
		{
			if(EcoreUtil.isAncestor(this, newCspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if(eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if(newCspec != null)
				msgs = ((InternalEObject)newCspec).eInverseAdd(this, CspecPackage.CSPEC__ATTRIBUTES, CSpec.class, msgs);
			msgs = basicSetCspec(newCspec, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ATTRIBUTE__CSPEC, newCspec, newCspec));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDocumentation(Documentation newDocumentation)
	{
		if(newDocumentation != documentation)
		{
			NotificationChain msgs = null;
			if(documentation != null)
				msgs = ((InternalEObject)documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- CspecPackage.ATTRIBUTE__DOCUMENTATION, null, msgs);
			if(newDocumentation != null)
				msgs = ((InternalEObject)newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- CspecPackage.ATTRIBUTE__DOCUMENTATION, null, msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ATTRIBUTE__DOCUMENTATION,
					newDocumentation, newDocumentation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ATTRIBUTE__FILTER, oldFilter, filter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ATTRIBUTE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPublic(boolean newPublic)
	{
		boolean oldPublic = public_;
		public_ = newPublic;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ATTRIBUTE__PUBLIC, oldPublic, public_));
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
		result.append(" (name: ");
		result.append(name);
		result.append(", public: ");
		result.append(public_);
		result.append(", filter: ");
		result.append(filter);
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
		return CspecPackage.Literals.ATTRIBUTE;
	}

} // AttributeImpl

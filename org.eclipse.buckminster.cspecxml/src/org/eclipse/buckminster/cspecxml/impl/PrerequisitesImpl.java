/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IPrerequisite;
import org.eclipse.buckminster.cspecxml.IPrerequisites;
import org.eclipse.buckminster.model.common.Documentation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Prerequisites</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.PrerequisitesImpl#getDocumentation <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.PrerequisitesImpl#getGroup <em>Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.PrerequisitesImpl#getAttribute <em>Attribute</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.PrerequisitesImpl#getAlias <em>Alias</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.PrerequisitesImpl#getRebase <em>Rebase</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PrerequisitesImpl extends EObjectImpl implements IPrerequisites
{
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
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getAlias() <em>Alias</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected static final String ALIAS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAlias() <em>Alias</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected String alias = ALIAS_EDEFAULT;

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
	protected PrerequisitesImpl()
	{
		super();
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
					ICSpecXMLPackage.PREREQUISITES__DOCUMENTATION, oldDocumentation, newDocumentation);
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
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch(featureID)
		{
		case ICSpecXMLPackage.PREREQUISITES__DOCUMENTATION:
			return getDocumentation();
		case ICSpecXMLPackage.PREREQUISITES__GROUP:
			if(coreType)
				return getGroup();
			return ((FeatureMap.Internal)getGroup()).getWrapper();
		case ICSpecXMLPackage.PREREQUISITES__ATTRIBUTE:
			return getAttribute();
		case ICSpecXMLPackage.PREREQUISITES__ALIAS:
			return getAlias();
		case ICSpecXMLPackage.PREREQUISITES__REBASE:
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
		case ICSpecXMLPackage.PREREQUISITES__DOCUMENTATION:
			return basicSetDocumentation(null, msgs);
		case ICSpecXMLPackage.PREREQUISITES__GROUP:
			return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
		case ICSpecXMLPackage.PREREQUISITES__ATTRIBUTE:
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
		case ICSpecXMLPackage.PREREQUISITES__DOCUMENTATION:
			return documentation != null;
		case ICSpecXMLPackage.PREREQUISITES__GROUP:
			return group != null && !group.isEmpty();
		case ICSpecXMLPackage.PREREQUISITES__ATTRIBUTE:
			return !getAttribute().isEmpty();
		case ICSpecXMLPackage.PREREQUISITES__ALIAS:
			return ALIAS_EDEFAULT == null
					? alias != null
					: !ALIAS_EDEFAULT.equals(alias);
		case ICSpecXMLPackage.PREREQUISITES__REBASE:
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
		case ICSpecXMLPackage.PREREQUISITES__DOCUMENTATION:
			setDocumentation((Documentation)newValue);
			return;
		case ICSpecXMLPackage.PREREQUISITES__GROUP:
			((FeatureMap.Internal)getGroup()).set(newValue);
			return;
		case ICSpecXMLPackage.PREREQUISITES__ATTRIBUTE:
			getAttribute().clear();
			getAttribute().addAll((Collection<? extends IPrerequisite>)newValue);
			return;
		case ICSpecXMLPackage.PREREQUISITES__ALIAS:
			setAlias((String)newValue);
			return;
		case ICSpecXMLPackage.PREREQUISITES__REBASE:
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
		case ICSpecXMLPackage.PREREQUISITES__DOCUMENTATION:
			setDocumentation((Documentation)null);
			return;
		case ICSpecXMLPackage.PREREQUISITES__GROUP:
			getGroup().clear();
			return;
		case ICSpecXMLPackage.PREREQUISITES__ATTRIBUTE:
			getAttribute().clear();
			return;
		case ICSpecXMLPackage.PREREQUISITES__ALIAS:
			setAlias(ALIAS_EDEFAULT);
			return;
		case ICSpecXMLPackage.PREREQUISITES__REBASE:
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
	public String getAlias()
	{
		return alias;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IPrerequisite> getAttribute()
	{
		return getGroup().list(ICSpecXMLPackage.Literals.PREREQUISITES__ATTRIBUTE);
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
	public FeatureMap getGroup()
	{
		if(group == null)
		{
			group = new BasicFeatureMap(this, ICSpecXMLPackage.PREREQUISITES__GROUP);
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
	public void setAlias(String newAlias)
	{
		String oldAlias = alias;
		alias = newAlias;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PREREQUISITES__ALIAS, oldAlias,
					alias));
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
						- ICSpecXMLPackage.PREREQUISITES__DOCUMENTATION, null, msgs);
			if(newDocumentation != null)
				msgs = ((InternalEObject)newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- ICSpecXMLPackage.PREREQUISITES__DOCUMENTATION, null, msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PREREQUISITES__DOCUMENTATION,
					newDocumentation, newDocumentation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PREREQUISITES__REBASE, oldRebase,
					rebase));
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
		result.append(", alias: ");
		result.append(alias);
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
		return ICSpecXMLPackage.Literals.PREREQUISITES;
	}

} // PrerequisitesImpl

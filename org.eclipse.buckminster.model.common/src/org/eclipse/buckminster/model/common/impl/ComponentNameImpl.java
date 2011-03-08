/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.model.common.CommonConstants;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.ComponentName;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Component Name</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.impl.ComponentNameImpl#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.ComponentNameImpl#getType
 * <em>Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ComponentNameImpl extends EObjectImpl implements ComponentName {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	protected ComponentNameImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public int compareTo(ComponentName o) {
		if (this == o)
			return 0;

		int cmp = Trivial.compareAllowNull(getType(), o.getType());
		if (cmp == 0) {
			cmp = Trivial.compareAllowNull(getId(), o.getId());
		}
		return cmp;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.COMPONENT_NAME__ID:
				return getId();
			case CommonPackage.COMPONENT_NAME__TYPE:
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CommonPackage.COMPONENT_NAME__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case CommonPackage.COMPONENT_NAME__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		}
		return super.eIsSet(featureID);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (!o.getClass().equals(getClass()))
			return false;
		ComponentName cn = (ComponentName) o;
		return Trivial.equalsAllowNull(getId(), cn.getId()) && Trivial.equalsAllowNull(getType(), cn.getType());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CommonPackage.COMPONENT_NAME__ID:
				setId((String) newValue);
				return;
			case CommonPackage.COMPONENT_NAME__TYPE:
				setType((String) newValue);
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
	public void eUnset(int featureID) {
		switch (featureID) {
			case CommonPackage.COMPONENT_NAME__ID:
				setId(ID_EDEFAULT);
				return;
			case CommonPackage.COMPONENT_NAME__TYPE:
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
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Map<String, String> getProperties() {
		HashMap<String, String> p = new HashMap<String, String>();
		if (getId() != null)
			p.put(CommonConstants.COMPONENT_NAME, getId());
		if (getType() != null)
			p.put(CommonConstants.COMPONENT_TYPE, getType());
		return p;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		int hc = getId() == null ? 31 : getId().hashCode();
		if (getType() != null) {
			hc *= 37;
			hc += getType().hashCode();
		}
		return hc;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean matches(ComponentName cn) {
		return Trivial.equalsAllowNull(getId(), cn.getId()) && (getType() == null || cn.getType() == null || getType().equals(cn.getType()));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.COMPONENT_NAME__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.COMPONENT_NAME__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public ComponentName toPureComponentName() {
		return this;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public final String toString() {
		StringBuilder bld = new StringBuilder();
		toString(bld);
		return bld.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void toString(StringBuilder bld) {
		if (getId() != null)
			bld.append(getId());
		bld.append(':');
		if (getType() != null) {
			bld.append(getType());
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.COMPONENT_NAME;
	}

} // ComponentNameImpl

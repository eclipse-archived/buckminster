/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.Map;

import org.eclipse.buckminster.model.common.CommonConstants;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.ComponentName;
import org.eclipse.buckminster.model.common.util.VersionHelper;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Component Identifier</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.ComponentIdentifierImpl#getVersion
 * <em>Version</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ComponentIdentifierImpl extends ComponentNameImpl implements ComponentIdentifier {
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final Version VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected Version version = VERSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComponentIdentifierImpl() {
		super();
	}

	@Override
	public int compareTo(ComponentName o) {
		if (this == o)
			return 0;

		int cmp = super.compareTo(o);
		if (cmp == 0)
			cmp = Trivial.compareAllowNull(getVersion(), (o instanceof ComponentIdentifier) ? ((ComponentIdentifier) o).getVersion() : null);
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
			case CommonPackage.COMPONENT_IDENTIFIER__VERSION:
				return getVersion();
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
			case CommonPackage.COMPONENT_IDENTIFIER__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
		}
		return super.eIsSet(featureID);
	}

	@Override
	public boolean equals(Object o) {
		return this == o || (super.equals(o) && Trivial.equalsAllowNull(getVersion(), ((ComponentIdentifier) o).getVersion()));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CommonPackage.COMPONENT_IDENTIFIER__VERSION:
				setVersion((Version) newValue);
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
			case CommonPackage.COMPONENT_IDENTIFIER__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> p = super.getProperties();
		if (getVersion() != null)
			p.put(CommonConstants.COMPONENT_VERSION, getVersion().toString());
		return p;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Version getVersion() {
		return version;
	}

	@Override
	public int hashCode() {
		int hc = super.hashCode();
		hc *= 37;
		if (version != null)
			hc += version.hashCode();
		return hc;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean matches(ComponentIdentifier ci) {
		return super.matches(ci) && (getVersion() == null || ci.getVersion() == null || getVersion().equals(ci.getVersion()));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setVersion(Version newVersion) {
		Version oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.COMPONENT_IDENTIFIER__VERSION, oldVersion, version));
	}

	@Override
	public ComponentName toPureComponentName() {
		ComponentName cn = new ComponentNameImpl();
		cn.setId(getId());
		cn.setType(getType());
		return cn;
	}

	@Override
	public void toString(StringBuilder result) {
		super.toString(result);
		result.append('$');
		if (getVersion() != null) {
			result.append(VersionHelper.getHumanReadable(getVersion()));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String toStringGen() {
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.COMPONENT_IDENTIFIER;
	}

} // ComponentIdentifierImpl

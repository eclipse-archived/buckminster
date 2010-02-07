/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IGenerator;
import org.eclipse.buckminster.model.common.util.VersionHelper;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Generator</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.GeneratorImpl#getAttribute
 * <em>Attribute</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.GeneratorImpl#getComponent
 * <em>Component</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.GeneratorImpl#getComponentType
 * <em>Component Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.GeneratorImpl#getGenerates
 * <em>Generates</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.GeneratorImpl#getGeneratesType
 * <em>Generates Type</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.GeneratorImpl#getGeneratesVersionString
 * <em>Generates Version String </em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.GeneratorImpl#getGeneratesVersionType
 * <em>Generates Version Type </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class GeneratorImpl extends EObjectImpl implements IGenerator {
	/**
	 * The default value of the '{@link #getAttribute() <em>Attribute</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTRIBUTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAttribute() <em>Attribute</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected String attribute = ATTRIBUTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getComponent() <em>Component</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPONENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComponent() <em>Component</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected String component = COMPONENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getComponentType()
	 * <em>Component Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getComponentType()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPONENT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComponentType()
	 * <em>Component Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getComponentType()
	 * @generated
	 * @ordered
	 */
	protected String componentType = COMPONENT_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getGenerates() <em>Generates</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGenerates()
	 * @generated
	 * @ordered
	 */
	protected static final String GENERATES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGenerates() <em>Generates</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGenerates()
	 * @generated
	 * @ordered
	 */
	protected String generates = GENERATES_EDEFAULT;

	/**
	 * The default value of the '{@link #getGeneratesType()
	 * <em>Generates Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGeneratesType()
	 * @generated
	 * @ordered
	 */
	protected static final String GENERATES_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeneratesType()
	 * <em>Generates Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGeneratesType()
	 * @generated
	 * @ordered
	 */
	protected String generatesType = GENERATES_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getGeneratesVersionString()
	 * <em>Generates Version String</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getGeneratesVersionString()
	 * @generated
	 * @ordered
	 */
	protected static final String GENERATES_VERSION_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeneratesVersionString()
	 * <em>Generates Version String</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getGeneratesVersionString()
	 * @generated
	 * @ordered
	 */
	protected String generatesVersionString = GENERATES_VERSION_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getGeneratesVersionType()
	 * <em>Generates Version Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGeneratesVersionType()
	 * @generated
	 * @ordered
	 */
	protected static final String GENERATES_VERSION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeneratesVersionType()
	 * <em>Generates Version Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGeneratesVersionType()
	 * @generated
	 * @ordered
	 */
	protected String generatesVersionType = GENERATES_VERSION_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected GeneratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ICSpecXMLPackage.GENERATOR__ATTRIBUTE:
				return getAttribute();
			case ICSpecXMLPackage.GENERATOR__COMPONENT:
				return getComponent();
			case ICSpecXMLPackage.GENERATOR__COMPONENT_TYPE:
				return getComponentType();
			case ICSpecXMLPackage.GENERATOR__GENERATES:
				return getGenerates();
			case ICSpecXMLPackage.GENERATOR__GENERATES_TYPE:
				return getGeneratesType();
			case ICSpecXMLPackage.GENERATOR__GENERATES_VERSION_STRING:
				return getGeneratesVersionString();
			case ICSpecXMLPackage.GENERATOR__GENERATES_VERSION_TYPE:
				return getGeneratesVersionType();
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
			case ICSpecXMLPackage.GENERATOR__ATTRIBUTE:
				return ATTRIBUTE_EDEFAULT == null ? attribute != null : !ATTRIBUTE_EDEFAULT.equals(attribute);
			case ICSpecXMLPackage.GENERATOR__COMPONENT:
				return COMPONENT_EDEFAULT == null ? component != null : !COMPONENT_EDEFAULT.equals(component);
			case ICSpecXMLPackage.GENERATOR__COMPONENT_TYPE:
				return COMPONENT_TYPE_EDEFAULT == null ? componentType != null : !COMPONENT_TYPE_EDEFAULT.equals(componentType);
			case ICSpecXMLPackage.GENERATOR__GENERATES:
				return GENERATES_EDEFAULT == null ? generates != null : !GENERATES_EDEFAULT.equals(generates);
			case ICSpecXMLPackage.GENERATOR__GENERATES_TYPE:
				return GENERATES_TYPE_EDEFAULT == null ? generatesType != null : !GENERATES_TYPE_EDEFAULT.equals(generatesType);
			case ICSpecXMLPackage.GENERATOR__GENERATES_VERSION_STRING:
				return GENERATES_VERSION_STRING_EDEFAULT == null ? generatesVersionString != null : !GENERATES_VERSION_STRING_EDEFAULT
						.equals(generatesVersionString);
			case ICSpecXMLPackage.GENERATOR__GENERATES_VERSION_TYPE:
				return GENERATES_VERSION_TYPE_EDEFAULT == null ? generatesVersionType != null : !GENERATES_VERSION_TYPE_EDEFAULT
						.equals(generatesVersionType);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ICSpecXMLPackage.GENERATOR__ATTRIBUTE:
				setAttribute((String) newValue);
				return;
			case ICSpecXMLPackage.GENERATOR__COMPONENT:
				setComponent((String) newValue);
				return;
			case ICSpecXMLPackage.GENERATOR__COMPONENT_TYPE:
				setComponentType((String) newValue);
				return;
			case ICSpecXMLPackage.GENERATOR__GENERATES:
				setGenerates((String) newValue);
				return;
			case ICSpecXMLPackage.GENERATOR__GENERATES_TYPE:
				setGeneratesType((String) newValue);
				return;
			case ICSpecXMLPackage.GENERATOR__GENERATES_VERSION_STRING:
				setGeneratesVersionString((String) newValue);
				return;
			case ICSpecXMLPackage.GENERATOR__GENERATES_VERSION_TYPE:
				setGeneratesVersionType((String) newValue);
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
			case ICSpecXMLPackage.GENERATOR__ATTRIBUTE:
				setAttribute(ATTRIBUTE_EDEFAULT);
				return;
			case ICSpecXMLPackage.GENERATOR__COMPONENT:
				setComponent(COMPONENT_EDEFAULT);
				return;
			case ICSpecXMLPackage.GENERATOR__COMPONENT_TYPE:
				setComponentType(COMPONENT_TYPE_EDEFAULT);
				return;
			case ICSpecXMLPackage.GENERATOR__GENERATES:
				setGenerates(GENERATES_EDEFAULT);
				return;
			case ICSpecXMLPackage.GENERATOR__GENERATES_TYPE:
				setGeneratesType(GENERATES_TYPE_EDEFAULT);
				return;
			case ICSpecXMLPackage.GENERATOR__GENERATES_VERSION_STRING:
				setGeneratesVersionString(GENERATES_VERSION_STRING_EDEFAULT);
				return;
			case ICSpecXMLPackage.GENERATOR__GENERATES_VERSION_TYPE:
				setGeneratesVersionType(GENERATES_VERSION_TYPE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getComponentType() {
		return componentType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getGenerates() {
		return generates;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getGeneratesType() {
		return generatesType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Version getGeneratesVersion() {
		return VersionHelper.createVersion(getGeneratesVersionType(), getGeneratesVersionString());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getGeneratesVersionString() {
		return generatesVersionString;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getGeneratesVersionType() {
		return generatesVersionType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setAttribute(String newAttribute) {
		String oldAttribute = attribute;
		attribute = newAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.GENERATOR__ATTRIBUTE, oldAttribute, attribute));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setComponent(String newComponent) {
		String oldComponent = component;
		component = newComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.GENERATOR__COMPONENT, oldComponent, component));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setComponentType(String newComponentType) {
		String oldComponentType = componentType;
		componentType = newComponentType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.GENERATOR__COMPONENT_TYPE, oldComponentType, componentType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setGenerates(String newGenerates) {
		String oldGenerates = generates;
		generates = newGenerates;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.GENERATOR__GENERATES, oldGenerates, generates));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setGeneratesType(String newGeneratesType) {
		String oldGeneratesType = generatesType;
		generatesType = newGeneratesType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.GENERATOR__GENERATES_TYPE, oldGeneratesType, generatesType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setGeneratesVersionString(String newGeneratesVersionString) {
		String oldGeneratesVersionString = generatesVersionString;
		generatesVersionString = newGeneratesVersionString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.GENERATOR__GENERATES_VERSION_STRING, oldGeneratesVersionString,
					generatesVersionString));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setGeneratesVersionType(String newGeneratesVersionType) {
		String oldGeneratesVersionType = generatesVersionType;
		generatesVersionType = newGeneratesVersionType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.GENERATOR__GENERATES_VERSION_TYPE, oldGeneratesVersionType,
					generatesVersionType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (attribute: ");
		result.append(attribute);
		result.append(", component: ");
		result.append(component);
		result.append(", componentType: ");
		result.append(componentType);
		result.append(", generates: ");
		result.append(generates);
		result.append(", generatesType: ");
		result.append(generatesType);
		result.append(", generatesVersionString: ");
		result.append(generatesVersionString);
		result.append(", generatesVersionType: ");
		result.append(generatesVersionType);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ICSpecXMLPackage.Literals.GENERATOR;
	}

} // GeneratorImpl

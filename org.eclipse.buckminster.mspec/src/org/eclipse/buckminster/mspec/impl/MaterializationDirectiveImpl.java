/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec.impl;

import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.Property;

import org.eclipse.buckminster.mspec.ConflictResolution;
import org.eclipse.buckminster.mspec.MaterializationDirective;
import org.eclipse.buckminster.mspec.MspecPackage;

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
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Materialization Directive</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationDirectiveImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationDirectiveImpl#getPropertyGroup
 * <em>Property Group</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationDirectiveImpl#getProperties
 * <em>Properties</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationDirectiveImpl#getConflictResolution
 * <em>Conflict Resolution</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationDirectiveImpl#getInstallLocation
 * <em>Install Location</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationDirectiveImpl#getMaterializer
 * <em>Materializer</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationDirectiveImpl#getWorkspaceLocation
 * <em>Workspace Location</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MaterializationDirectiveImpl extends EObjectImpl implements MaterializationDirective {
	/**
	 * The cached value of the '{@link #getDocumentation()
	 * <em>Documentation</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected Documentation documentation;

	/**
	 * The cached value of the '{@link #getPropertyGroup()
	 * <em>Property Group</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPropertyGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap propertyGroup;

	/**
	 * The default value of the '{@link #getConflictResolution()
	 * <em>Conflict Resolution</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getConflictResolution()
	 * @generated
	 * @ordered
	 */
	protected static final ConflictResolution CONFLICT_RESOLUTION_EDEFAULT = ConflictResolution.UPDATE;

	/**
	 * The cached value of the '{@link #getConflictResolution()
	 * <em>Conflict Resolution</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getConflictResolution()
	 * @generated
	 * @ordered
	 */
	protected ConflictResolution conflictResolution = CONFLICT_RESOLUTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getInstallLocation()
	 * <em>Install Location</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getInstallLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTALL_LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInstallLocation()
	 * <em>Install Location</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getInstallLocation()
	 * @generated
	 * @ordered
	 */
	protected String installLocation = INSTALL_LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaterializer()
	 * <em>Materializer</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getMaterializer()
	 * @generated
	 * @ordered
	 */
	protected static final String MATERIALIZER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaterializer() <em>Materializer</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMaterializer()
	 * @generated
	 * @ordered
	 */
	protected String materializer = MATERIALIZER_EDEFAULT;

	/**
	 * The default value of the '{@link #getWorkspaceLocation()
	 * <em>Workspace Location</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getWorkspaceLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String WORKSPACE_LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWorkspaceLocation()
	 * <em>Workspace Location</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getWorkspaceLocation()
	 * @generated
	 * @ordered
	 */
	protected String workspaceLocation = WORKSPACE_LOCATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MaterializationDirectiveImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDocumentation(Documentation newDocumentation, NotificationChain msgs) {
		Documentation oldDocumentation = documentation;
		documentation = newDocumentation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_DIRECTIVE__DOCUMENTATION,
					oldDocumentation, newDocumentation);
			if (msgs == null)
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MspecPackage.MATERIALIZATION_DIRECTIVE__DOCUMENTATION:
				return getDocumentation();
			case MspecPackage.MATERIALIZATION_DIRECTIVE__PROPERTY_GROUP:
				if (coreType)
					return getPropertyGroup();
				return ((FeatureMap.Internal) getPropertyGroup()).getWrapper();
			case MspecPackage.MATERIALIZATION_DIRECTIVE__PROPERTIES:
				return getProperties();
			case MspecPackage.MATERIALIZATION_DIRECTIVE__CONFLICT_RESOLUTION:
				return getConflictResolution();
			case MspecPackage.MATERIALIZATION_DIRECTIVE__INSTALL_LOCATION:
				return getInstallLocation();
			case MspecPackage.MATERIALIZATION_DIRECTIVE__MATERIALIZER:
				return getMaterializer();
			case MspecPackage.MATERIALIZATION_DIRECTIVE__WORKSPACE_LOCATION:
				return getWorkspaceLocation();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MspecPackage.MATERIALIZATION_DIRECTIVE__DOCUMENTATION:
				return basicSetDocumentation(null, msgs);
			case MspecPackage.MATERIALIZATION_DIRECTIVE__PROPERTY_GROUP:
				return ((InternalEList<?>) getPropertyGroup()).basicRemove(otherEnd, msgs);
			case MspecPackage.MATERIALIZATION_DIRECTIVE__PROPERTIES:
				return ((InternalEList<?>) getProperties()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MspecPackage.MATERIALIZATION_DIRECTIVE__DOCUMENTATION:
				return documentation != null;
			case MspecPackage.MATERIALIZATION_DIRECTIVE__PROPERTY_GROUP:
				return propertyGroup != null && !propertyGroup.isEmpty();
			case MspecPackage.MATERIALIZATION_DIRECTIVE__PROPERTIES:
				return !getProperties().isEmpty();
			case MspecPackage.MATERIALIZATION_DIRECTIVE__CONFLICT_RESOLUTION:
				return conflictResolution != CONFLICT_RESOLUTION_EDEFAULT;
			case MspecPackage.MATERIALIZATION_DIRECTIVE__INSTALL_LOCATION:
				return INSTALL_LOCATION_EDEFAULT == null ? installLocation != null : !INSTALL_LOCATION_EDEFAULT.equals(installLocation);
			case MspecPackage.MATERIALIZATION_DIRECTIVE__MATERIALIZER:
				return MATERIALIZER_EDEFAULT == null ? materializer != null : !MATERIALIZER_EDEFAULT.equals(materializer);
			case MspecPackage.MATERIALIZATION_DIRECTIVE__WORKSPACE_LOCATION:
				return WORKSPACE_LOCATION_EDEFAULT == null ? workspaceLocation != null : !WORKSPACE_LOCATION_EDEFAULT.equals(workspaceLocation);
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
			case MspecPackage.MATERIALIZATION_DIRECTIVE__DOCUMENTATION:
				setDocumentation((Documentation) newValue);
				return;
			case MspecPackage.MATERIALIZATION_DIRECTIVE__PROPERTY_GROUP:
				((FeatureMap.Internal) getPropertyGroup()).set(newValue);
				return;
			case MspecPackage.MATERIALIZATION_DIRECTIVE__CONFLICT_RESOLUTION:
				setConflictResolution((ConflictResolution) newValue);
				return;
			case MspecPackage.MATERIALIZATION_DIRECTIVE__INSTALL_LOCATION:
				setInstallLocation((String) newValue);
				return;
			case MspecPackage.MATERIALIZATION_DIRECTIVE__MATERIALIZER:
				setMaterializer((String) newValue);
				return;
			case MspecPackage.MATERIALIZATION_DIRECTIVE__WORKSPACE_LOCATION:
				setWorkspaceLocation((String) newValue);
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
			case MspecPackage.MATERIALIZATION_DIRECTIVE__DOCUMENTATION:
				setDocumentation((Documentation) null);
				return;
			case MspecPackage.MATERIALIZATION_DIRECTIVE__PROPERTY_GROUP:
				getPropertyGroup().clear();
				return;
			case MspecPackage.MATERIALIZATION_DIRECTIVE__CONFLICT_RESOLUTION:
				setConflictResolution(CONFLICT_RESOLUTION_EDEFAULT);
				return;
			case MspecPackage.MATERIALIZATION_DIRECTIVE__INSTALL_LOCATION:
				setInstallLocation(INSTALL_LOCATION_EDEFAULT);
				return;
			case MspecPackage.MATERIALIZATION_DIRECTIVE__MATERIALIZER:
				setMaterializer(MATERIALIZER_EDEFAULT);
				return;
			case MspecPackage.MATERIALIZATION_DIRECTIVE__WORKSPACE_LOCATION:
				setWorkspaceLocation(WORKSPACE_LOCATION_EDEFAULT);
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
	public ConflictResolution getConflictResolution() {
		return conflictResolution;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Documentation getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getInstallLocation() {
		return installLocation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getMaterializer() {
		return materializer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Property> getProperties() {
		return getPropertyGroup().list(MspecPackage.Literals.MATERIALIZATION_DIRECTIVE__PROPERTIES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FeatureMap getPropertyGroup() {
		if (propertyGroup == null) {
			propertyGroup = new BasicFeatureMap(this, MspecPackage.MATERIALIZATION_DIRECTIVE__PROPERTY_GROUP);
		}
		return propertyGroup;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getWorkspaceLocation() {
		return workspaceLocation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setConflictResolution(ConflictResolution newConflictResolution) {
		ConflictResolution oldConflictResolution = conflictResolution;
		conflictResolution = newConflictResolution == null ? CONFLICT_RESOLUTION_EDEFAULT : newConflictResolution;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_DIRECTIVE__CONFLICT_RESOLUTION, oldConflictResolution,
					conflictResolution));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDocumentation(Documentation newDocumentation) {
		if (newDocumentation != documentation) {
			NotificationChain msgs = null;
			if (documentation != null)
				msgs = ((InternalEObject) documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- MspecPackage.MATERIALIZATION_DIRECTIVE__DOCUMENTATION, null, msgs);
			if (newDocumentation != null)
				msgs = ((InternalEObject) newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- MspecPackage.MATERIALIZATION_DIRECTIVE__DOCUMENTATION, null, msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_DIRECTIVE__DOCUMENTATION, newDocumentation,
					newDocumentation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setInstallLocation(String newInstallLocation) {
		String oldInstallLocation = installLocation;
		installLocation = newInstallLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_DIRECTIVE__INSTALL_LOCATION, oldInstallLocation,
					installLocation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setMaterializer(String newMaterializer) {
		String oldMaterializer = materializer;
		materializer = newMaterializer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_DIRECTIVE__MATERIALIZER, oldMaterializer, materializer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setWorkspaceLocation(String newWorkspaceLocation) {
		String oldWorkspaceLocation = workspaceLocation;
		workspaceLocation = newWorkspaceLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_DIRECTIVE__WORKSPACE_LOCATION, oldWorkspaceLocation,
					workspaceLocation));
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
		result.append(" (propertyGroup: ");
		result.append(propertyGroup);
		result.append(", conflictResolution: ");
		result.append(conflictResolution);
		result.append(", installLocation: ");
		result.append(installLocation);
		result.append(", materializer: ");
		result.append(materializer);
		result.append(", workspaceLocation: ");
		result.append(workspaceLocation);
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
		return MspecPackage.Literals.MATERIALIZATION_DIRECTIVE;
	}

} // MaterializationDirectiveImpl

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec.impl;

import java.util.regex.Pattern;

import org.eclipse.buckminster.mspec.MaterializationNode;
import org.eclipse.buckminster.mspec.MspecPackage;
import org.eclipse.buckminster.mspec.Unpack;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Materialization Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationNodeImpl#getNamePattern
 * <em>Name Pattern</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationNodeImpl#getLeafArtifact
 * <em>Leaf Artifact</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationNodeImpl#getComponentType
 * <em>Component Type</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationNodeImpl#getResourcePath
 * <em>Resource Path</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationNodeImpl#isExclude
 * <em>Exclude</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationNodeImpl#getBindingNamePattern
 * <em>Binding Name Pattern</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationNodeImpl#getBindingNameReplacement
 * <em>Binding Name Replacement</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationNodeImpl#getUnpack
 * <em>Unpack</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.mspec.impl.MaterializationNodeImpl#getFilter
 * <em>Filter</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MaterializationNodeImpl extends MaterializationDirectiveImpl implements MaterializationNode {
	/**
	 * The default value of the '{@link #getNamePattern() <em>Name Pattern</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getNamePattern()
	 * @generated
	 * @ordered
	 */
	protected static final Pattern NAME_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNamePattern() <em>Name Pattern</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getNamePattern()
	 * @generated
	 * @ordered
	 */
	protected Pattern namePattern = NAME_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getLeafArtifact()
	 * <em>Leaf Artifact</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLeafArtifact()
	 * @generated
	 * @ordered
	 */
	protected static final String LEAF_ARTIFACT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLeafArtifact()
	 * <em>Leaf Artifact</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLeafArtifact()
	 * @generated
	 * @ordered
	 */
	protected String leafArtifact = LEAF_ARTIFACT_EDEFAULT;

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
	 * The default value of the '{@link #getResourcePath()
	 * <em>Resource Path</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getResourcePath()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourcePath()
	 * <em>Resource Path</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getResourcePath()
	 * @generated
	 * @ordered
	 */
	protected String resourcePath = RESOURCE_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #isExclude() <em>Exclude</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isExclude()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXCLUDE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExclude() <em>Exclude</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isExclude()
	 * @generated
	 * @ordered
	 */
	protected boolean exclude = EXCLUDE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBindingNamePattern()
	 * <em>Binding Name Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getBindingNamePattern()
	 * @generated
	 * @ordered
	 */
	protected static final Pattern BINDING_NAME_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBindingNamePattern()
	 * <em>Binding Name Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getBindingNamePattern()
	 * @generated
	 * @ordered
	 */
	protected Pattern bindingNamePattern = BINDING_NAME_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getBindingNameReplacement()
	 * <em>Binding Name Replacement</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getBindingNameReplacement()
	 * @generated
	 * @ordered
	 */
	protected static final String BINDING_NAME_REPLACEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBindingNameReplacement()
	 * <em>Binding Name Replacement</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getBindingNameReplacement()
	 * @generated
	 * @ordered
	 */
	protected String bindingNameReplacement = BINDING_NAME_REPLACEMENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getUnpack() <em>Unpack</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getUnpack()
	 * @generated
	 * @ordered
	 */
	protected Unpack unpack;

	/**
	 * The default value of the '{@link #getFilter() <em>Filter</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected static final Filter FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilter() <em>Filter</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected Filter filter = FILTER_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MaterializationNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetUnpack(Unpack newUnpack, NotificationChain msgs) {
		Unpack oldUnpack = unpack;
		unpack = newUnpack;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_NODE__UNPACK, oldUnpack,
					newUnpack);
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
			case MspecPackage.MATERIALIZATION_NODE__NAME_PATTERN:
				return getNamePattern();
			case MspecPackage.MATERIALIZATION_NODE__LEAF_ARTIFACT:
				return getLeafArtifact();
			case MspecPackage.MATERIALIZATION_NODE__COMPONENT_TYPE:
				return getComponentType();
			case MspecPackage.MATERIALIZATION_NODE__RESOURCE_PATH:
				return getResourcePath();
			case MspecPackage.MATERIALIZATION_NODE__EXCLUDE:
				return isExclude();
			case MspecPackage.MATERIALIZATION_NODE__BINDING_NAME_PATTERN:
				return getBindingNamePattern();
			case MspecPackage.MATERIALIZATION_NODE__BINDING_NAME_REPLACEMENT:
				return getBindingNameReplacement();
			case MspecPackage.MATERIALIZATION_NODE__UNPACK:
				return getUnpack();
			case MspecPackage.MATERIALIZATION_NODE__FILTER:
				return getFilter();
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
			case MspecPackage.MATERIALIZATION_NODE__UNPACK:
				return basicSetUnpack(null, msgs);
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
			case MspecPackage.MATERIALIZATION_NODE__NAME_PATTERN:
				return NAME_PATTERN_EDEFAULT == null ? namePattern != null : !NAME_PATTERN_EDEFAULT.equals(namePattern);
			case MspecPackage.MATERIALIZATION_NODE__LEAF_ARTIFACT:
				return LEAF_ARTIFACT_EDEFAULT == null ? leafArtifact != null : !LEAF_ARTIFACT_EDEFAULT.equals(leafArtifact);
			case MspecPackage.MATERIALIZATION_NODE__COMPONENT_TYPE:
				return COMPONENT_TYPE_EDEFAULT == null ? componentType != null : !COMPONENT_TYPE_EDEFAULT.equals(componentType);
			case MspecPackage.MATERIALIZATION_NODE__RESOURCE_PATH:
				return RESOURCE_PATH_EDEFAULT == null ? resourcePath != null : !RESOURCE_PATH_EDEFAULT.equals(resourcePath);
			case MspecPackage.MATERIALIZATION_NODE__EXCLUDE:
				return exclude != EXCLUDE_EDEFAULT;
			case MspecPackage.MATERIALIZATION_NODE__BINDING_NAME_PATTERN:
				return BINDING_NAME_PATTERN_EDEFAULT == null ? bindingNamePattern != null : !BINDING_NAME_PATTERN_EDEFAULT.equals(bindingNamePattern);
			case MspecPackage.MATERIALIZATION_NODE__BINDING_NAME_REPLACEMENT:
				return BINDING_NAME_REPLACEMENT_EDEFAULT == null ? bindingNameReplacement != null : !BINDING_NAME_REPLACEMENT_EDEFAULT
						.equals(bindingNameReplacement);
			case MspecPackage.MATERIALIZATION_NODE__UNPACK:
				return unpack != null;
			case MspecPackage.MATERIALIZATION_NODE__FILTER:
				return FILTER_EDEFAULT == null ? filter != null : !FILTER_EDEFAULT.equals(filter);
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
			case MspecPackage.MATERIALIZATION_NODE__NAME_PATTERN:
				setNamePattern((Pattern) newValue);
				return;
			case MspecPackage.MATERIALIZATION_NODE__LEAF_ARTIFACT:
				setLeafArtifact((String) newValue);
				return;
			case MspecPackage.MATERIALIZATION_NODE__COMPONENT_TYPE:
				setComponentType((String) newValue);
				return;
			case MspecPackage.MATERIALIZATION_NODE__RESOURCE_PATH:
				setResourcePath((String) newValue);
				return;
			case MspecPackage.MATERIALIZATION_NODE__EXCLUDE:
				setExclude((Boolean) newValue);
				return;
			case MspecPackage.MATERIALIZATION_NODE__BINDING_NAME_PATTERN:
				setBindingNamePattern((Pattern) newValue);
				return;
			case MspecPackage.MATERIALIZATION_NODE__BINDING_NAME_REPLACEMENT:
				setBindingNameReplacement((String) newValue);
				return;
			case MspecPackage.MATERIALIZATION_NODE__UNPACK:
				setUnpack((Unpack) newValue);
				return;
			case MspecPackage.MATERIALIZATION_NODE__FILTER:
				setFilter((Filter) newValue);
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
			case MspecPackage.MATERIALIZATION_NODE__NAME_PATTERN:
				setNamePattern(NAME_PATTERN_EDEFAULT);
				return;
			case MspecPackage.MATERIALIZATION_NODE__LEAF_ARTIFACT:
				setLeafArtifact(LEAF_ARTIFACT_EDEFAULT);
				return;
			case MspecPackage.MATERIALIZATION_NODE__COMPONENT_TYPE:
				setComponentType(COMPONENT_TYPE_EDEFAULT);
				return;
			case MspecPackage.MATERIALIZATION_NODE__RESOURCE_PATH:
				setResourcePath(RESOURCE_PATH_EDEFAULT);
				return;
			case MspecPackage.MATERIALIZATION_NODE__EXCLUDE:
				setExclude(EXCLUDE_EDEFAULT);
				return;
			case MspecPackage.MATERIALIZATION_NODE__BINDING_NAME_PATTERN:
				setBindingNamePattern(BINDING_NAME_PATTERN_EDEFAULT);
				return;
			case MspecPackage.MATERIALIZATION_NODE__BINDING_NAME_REPLACEMENT:
				setBindingNameReplacement(BINDING_NAME_REPLACEMENT_EDEFAULT);
				return;
			case MspecPackage.MATERIALIZATION_NODE__UNPACK:
				setUnpack((Unpack) null);
				return;
			case MspecPackage.MATERIALIZATION_NODE__FILTER:
				setFilter(FILTER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Pattern getBindingNamePattern() {
		return bindingNamePattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public String getBindingNameReplacement() {
		return bindingNameReplacement;
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

	public Filter getFilter() {
		return filter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public String getLeafArtifact() {
		return leafArtifact;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Pattern getNamePattern() {
		return namePattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public String getResourcePath() {
		return resourcePath;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Unpack getUnpack() {
		return unpack;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public boolean isExclude() {
		return exclude;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setBindingNamePattern(Pattern newBindingNamePattern) {
		Pattern oldBindingNamePattern = bindingNamePattern;
		bindingNamePattern = newBindingNamePattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_NODE__BINDING_NAME_PATTERN, oldBindingNamePattern,
					bindingNamePattern));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setBindingNameReplacement(String newBindingNameReplacement) {
		String oldBindingNameReplacement = bindingNameReplacement;
		bindingNameReplacement = newBindingNameReplacement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_NODE__BINDING_NAME_REPLACEMENT,
					oldBindingNameReplacement, bindingNameReplacement));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_NODE__COMPONENT_TYPE, oldComponentType, componentType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setExclude(boolean newExclude) {
		boolean oldExclude = exclude;
		exclude = newExclude;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_NODE__EXCLUDE, oldExclude, exclude));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setFilter(Filter newFilter) {
		Filter oldFilter = filter;
		filter = newFilter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_NODE__FILTER, oldFilter, filter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setLeafArtifact(String newLeafArtifact) {
		String oldLeafArtifact = leafArtifact;
		leafArtifact = newLeafArtifact;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_NODE__LEAF_ARTIFACT, oldLeafArtifact, leafArtifact));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setNamePattern(Pattern newNamePattern) {
		Pattern oldNamePattern = namePattern;
		namePattern = newNamePattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_NODE__NAME_PATTERN, oldNamePattern, namePattern));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setResourcePath(String newResourcePath) {
		String oldResourcePath = resourcePath;
		resourcePath = newResourcePath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_NODE__RESOURCE_PATH, oldResourcePath, resourcePath));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setUnpack(Unpack newUnpack) {
		if (newUnpack != unpack) {
			NotificationChain msgs = null;
			if (unpack != null)
				msgs = ((InternalEObject) unpack)
						.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MspecPackage.MATERIALIZATION_NODE__UNPACK, null, msgs);
			if (newUnpack != null)
				msgs = ((InternalEObject) newUnpack)
						.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MspecPackage.MATERIALIZATION_NODE__UNPACK, null, msgs);
			msgs = basicSetUnpack(newUnpack, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.MATERIALIZATION_NODE__UNPACK, newUnpack, newUnpack));
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
		result.append(" (namePattern: ");
		result.append(namePattern);
		result.append(", leafArtifact: ");
		result.append(leafArtifact);
		result.append(", componentType: ");
		result.append(componentType);
		result.append(", resourcePath: ");
		result.append(resourcePath);
		result.append(", exclude: ");
		result.append(exclude);
		result.append(", bindingNamePattern: ");
		result.append(bindingNamePattern);
		result.append(", bindingNameReplacement: ");
		result.append(bindingNameReplacement);
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
	protected EClass eStaticClass() {
		return MspecPackage.Literals.MATERIALIZATION_NODE;
	}

} // MaterializationNodeImpl

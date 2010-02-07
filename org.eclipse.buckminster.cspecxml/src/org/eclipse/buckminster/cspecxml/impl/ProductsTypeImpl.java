/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.Collection;

import java.util.regex.Pattern;
import org.eclipse.buckminster.cspecxml.IActionArtifact;
import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IPath;
import org.eclipse.buckminster.cspecxml.IProductsType;
import org.eclipse.buckminster.cspecxml.UpToDatePolicy;

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
 * <em><b>Products Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl#getGroup
 * <em>Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl#getPath
 * <em>Path</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl#getPublic
 * <em>Public</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl#getPrivate
 * <em>Private</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl#getAlias
 * <em>Alias</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl#getBase
 * <em>Base</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl#getFileCount
 * <em>File Count</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl#getPattern
 * <em>Pattern</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl#getReplacement
 * <em>Replacement</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl#getUpToDatePolicy
 * <em>Up To Date Policy</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ProductsTypeImpl extends EObjectImpl implements IProductsType {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getAlias() <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected static final String ALIAS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAlias() <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected String alias = ALIAS_EDEFAULT;

	/**
	 * The default value of the '{@link #getBase() <em>Base</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBase() <em>Base</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected String base = BASE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFileCount() <em>File Count</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFileCount()
	 * @generated
	 * @ordered
	 */
	protected static final int FILE_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getFileCount() <em>File Count</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFileCount()
	 * @generated
	 * @ordered
	 */
	protected int fileCount = FILE_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getPattern() <em>Pattern</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected static final Pattern PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPattern() <em>Pattern</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected Pattern pattern = PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getReplacement() <em>Replacement</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReplacement()
	 * @generated
	 * @ordered
	 */
	protected static final String REPLACEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReplacement() <em>Replacement</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReplacement()
	 * @generated
	 * @ordered
	 */
	protected String replacement = REPLACEMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpToDatePolicy()
	 * <em>Up To Date Policy</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getUpToDatePolicy()
	 * @generated
	 * @ordered
	 */
	protected static final UpToDatePolicy UP_TO_DATE_POLICY_EDEFAULT = UpToDatePolicy.DEFAULT;

	/**
	 * The cached value of the '{@link #getUpToDatePolicy()
	 * <em>Up To Date Policy</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getUpToDatePolicy()
	 * @generated
	 * @ordered
	 */
	protected UpToDatePolicy upToDatePolicy = UP_TO_DATE_POLICY_EDEFAULT;

	/**
	 * This is true if the Up To Date Policy attribute has been set. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean upToDatePolicyESet;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ProductsTypeImpl() {
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
			case ICSpecXMLPackage.PRODUCTS_TYPE__GROUP:
				if (coreType)
					return getGroup();
				return ((FeatureMap.Internal) getGroup()).getWrapper();
			case ICSpecXMLPackage.PRODUCTS_TYPE__PATH:
				return getPath();
			case ICSpecXMLPackage.PRODUCTS_TYPE__PUBLIC:
				return getPublic();
			case ICSpecXMLPackage.PRODUCTS_TYPE__PRIVATE:
				return getPrivate();
			case ICSpecXMLPackage.PRODUCTS_TYPE__ALIAS:
				return getAlias();
			case ICSpecXMLPackage.PRODUCTS_TYPE__BASE:
				return getBase();
			case ICSpecXMLPackage.PRODUCTS_TYPE__FILE_COUNT:
				return getFileCount();
			case ICSpecXMLPackage.PRODUCTS_TYPE__PATTERN:
				return getPattern();
			case ICSpecXMLPackage.PRODUCTS_TYPE__REPLACEMENT:
				return getReplacement();
			case ICSpecXMLPackage.PRODUCTS_TYPE__UP_TO_DATE_POLICY:
				return getUpToDatePolicy();
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
			case ICSpecXMLPackage.PRODUCTS_TYPE__GROUP:
				return ((InternalEList<?>) getGroup()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.PRODUCTS_TYPE__PATH:
				return ((InternalEList<?>) getPath()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.PRODUCTS_TYPE__PUBLIC:
				return ((InternalEList<?>) getPublic()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.PRODUCTS_TYPE__PRIVATE:
				return ((InternalEList<?>) getPrivate()).basicRemove(otherEnd, msgs);
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
			case ICSpecXMLPackage.PRODUCTS_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case ICSpecXMLPackage.PRODUCTS_TYPE__PATH:
				return !getPath().isEmpty();
			case ICSpecXMLPackage.PRODUCTS_TYPE__PUBLIC:
				return !getPublic().isEmpty();
			case ICSpecXMLPackage.PRODUCTS_TYPE__PRIVATE:
				return !getPrivate().isEmpty();
			case ICSpecXMLPackage.PRODUCTS_TYPE__ALIAS:
				return ALIAS_EDEFAULT == null ? alias != null : !ALIAS_EDEFAULT.equals(alias);
			case ICSpecXMLPackage.PRODUCTS_TYPE__BASE:
				return BASE_EDEFAULT == null ? base != null : !BASE_EDEFAULT.equals(base);
			case ICSpecXMLPackage.PRODUCTS_TYPE__FILE_COUNT:
				return fileCount != FILE_COUNT_EDEFAULT;
			case ICSpecXMLPackage.PRODUCTS_TYPE__PATTERN:
				return PATTERN_EDEFAULT == null ? pattern != null : !PATTERN_EDEFAULT.equals(pattern);
			case ICSpecXMLPackage.PRODUCTS_TYPE__REPLACEMENT:
				return REPLACEMENT_EDEFAULT == null ? replacement != null : !REPLACEMENT_EDEFAULT.equals(replacement);
			case ICSpecXMLPackage.PRODUCTS_TYPE__UP_TO_DATE_POLICY:
				return isSetUpToDatePolicy();
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ICSpecXMLPackage.PRODUCTS_TYPE__GROUP:
				((FeatureMap.Internal) getGroup()).set(newValue);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__PATH:
				getPath().clear();
				getPath().addAll((Collection<? extends IPath>) newValue);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__PUBLIC:
				getPublic().clear();
				getPublic().addAll((Collection<? extends IActionArtifact>) newValue);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__PRIVATE:
				getPrivate().clear();
				getPrivate().addAll((Collection<? extends IActionArtifact>) newValue);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__ALIAS:
				setAlias((String) newValue);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__BASE:
				setBase((String) newValue);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__FILE_COUNT:
				setFileCount((Integer) newValue);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__PATTERN:
				setPattern((Pattern) newValue);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__REPLACEMENT:
				setReplacement((String) newValue);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__UP_TO_DATE_POLICY:
				setUpToDatePolicy((UpToDatePolicy) newValue);
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
			case ICSpecXMLPackage.PRODUCTS_TYPE__GROUP:
				getGroup().clear();
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__PATH:
				getPath().clear();
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__PUBLIC:
				getPublic().clear();
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__PRIVATE:
				getPrivate().clear();
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__ALIAS:
				setAlias(ALIAS_EDEFAULT);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__BASE:
				setBase(BASE_EDEFAULT);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__FILE_COUNT:
				setFileCount(FILE_COUNT_EDEFAULT);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__PATTERN:
				setPattern(PATTERN_EDEFAULT);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__REPLACEMENT:
				setReplacement(REPLACEMENT_EDEFAULT);
				return;
			case ICSpecXMLPackage.PRODUCTS_TYPE__UP_TO_DATE_POLICY:
				unsetUpToDatePolicy();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getBase() {
		return base;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getFileCount() {
		return fileCount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, ICSpecXMLPackage.PRODUCTS_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IPath> getPath() {
		return getGroup().list(ICSpecXMLPackage.Literals.PRODUCTS_TYPE__PATH);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Pattern getPattern() {
		return pattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IActionArtifact> getPrivate() {
		return getGroup().list(ICSpecXMLPackage.Literals.PRODUCTS_TYPE__PRIVATE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IActionArtifact> getPublic() {
		return getGroup().list(ICSpecXMLPackage.Literals.PRODUCTS_TYPE__PUBLIC);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getReplacement() {
		return replacement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public UpToDatePolicy getUpToDatePolicy() {
		return upToDatePolicy;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetUpToDatePolicy() {
		return upToDatePolicyESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setAlias(String newAlias) {
		String oldAlias = alias;
		alias = newAlias;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PRODUCTS_TYPE__ALIAS, oldAlias, alias));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setBase(String newBase) {
		String oldBase = base;
		base = newBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PRODUCTS_TYPE__BASE, oldBase, base));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFileCount(int newFileCount) {
		int oldFileCount = fileCount;
		fileCount = newFileCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PRODUCTS_TYPE__FILE_COUNT, oldFileCount, fileCount));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPattern(Pattern newPattern) {
		Pattern oldPattern = pattern;
		pattern = newPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PRODUCTS_TYPE__PATTERN, oldPattern, pattern));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setReplacement(String newReplacement) {
		String oldReplacement = replacement;
		replacement = newReplacement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PRODUCTS_TYPE__REPLACEMENT, oldReplacement, replacement));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setUpToDatePolicy(UpToDatePolicy newUpToDatePolicy) {
		UpToDatePolicy oldUpToDatePolicy = upToDatePolicy;
		upToDatePolicy = newUpToDatePolicy == null ? UP_TO_DATE_POLICY_EDEFAULT : newUpToDatePolicy;
		boolean oldUpToDatePolicyESet = upToDatePolicyESet;
		upToDatePolicyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PRODUCTS_TYPE__UP_TO_DATE_POLICY, oldUpToDatePolicy,
					upToDatePolicy, !oldUpToDatePolicyESet));
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
		result.append(" (group: ");
		result.append(group);
		result.append(", alias: ");
		result.append(alias);
		result.append(", base: ");
		result.append(base);
		result.append(", fileCount: ");
		result.append(fileCount);
		result.append(", pattern: ");
		result.append(pattern);
		result.append(", replacement: ");
		result.append(replacement);
		result.append(", upToDatePolicy: ");
		if (upToDatePolicyESet)
			result.append(upToDatePolicy);
		else
			result.append("<unset>");
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetUpToDatePolicy() {
		UpToDatePolicy oldUpToDatePolicy = upToDatePolicy;
		boolean oldUpToDatePolicyESet = upToDatePolicyESet;
		upToDatePolicy = UP_TO_DATE_POLICY_EDEFAULT;
		upToDatePolicyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ICSpecXMLPackage.PRODUCTS_TYPE__UP_TO_DATE_POLICY, oldUpToDatePolicy,
					UP_TO_DATE_POLICY_EDEFAULT, oldUpToDatePolicyESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ICSpecXMLPackage.Literals.PRODUCTS_TYPE;
	}

} // ProductsTypeImpl

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import java.util.Collection;

import java.util.Map;
import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.Format;

import org.eclipse.buckminster.model.common.impl.PropertiesImpl;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.Repository;
import org.eclipse.buckminster.rmap.RmapConstants;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.SearchPath;
import org.eclipse.buckminster.rmap.URIMatcher;
import org.eclipse.buckminster.rmap.VersionConverter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Provider</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getComponentTypes
 * <em>Component Types</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getComponentTypesAttr
 * <em>Component Types Attr</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getReaderType <em>
 * Reader Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#isSource <em>Source
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#isMutable <em>
 * Mutable</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getResolutionFilter
 * <em>Resolution Filter</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getVersionConverter
 * <em>Version Converter</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getURI <em>URI
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getMatcher <em>
 * Matcher</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getRepository <em>
 * Repository</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ProviderImpl extends PropertiesImpl implements Provider {
	/**
	 * The cached value of the '{@link #getComponentTypes()
	 * <em>Component Types</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getComponentTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> componentTypes;

	/**
	 * The default value of the '{@link #getComponentTypesAttr()
	 * <em>Component Types Attr</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getComponentTypesAttr()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPONENT_TYPES_ATTR_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getReaderType() <em>Reader Type</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReaderType()
	 * @generated
	 * @ordered
	 */
	protected static final String READER_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReaderType() <em>Reader Type</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReaderType()
	 * @generated
	 * @ordered
	 */
	protected String readerType = READER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSource()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SOURCE_EDEFAULT = true;

	/**
	 * The default value of the '{@link #isMutable() <em>Mutable</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMutable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MUTABLE_EDEFAULT = true;

	/**
	 * The default value of the '{@link #getResolutionFilter()
	 * <em>Resolution Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getResolutionFilter()
	 * @generated
	 * @ordered
	 */
	protected static final Filter RESOLUTION_FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResolutionFilter()
	 * <em>Resolution Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getResolutionFilter()
	 * @generated
	 * @ordered
	 */
	protected Filter resolutionFilter = RESOLUTION_FILTER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVersionConverter()
	 * <em>Version Converter</em>}' containment reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getVersionConverter()
	 * @generated
	 * @ordered
	 */
	protected VersionConverter versionConverter;

	/**
	 * The cached value of the '{@link #getURI() <em>URI</em>}' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getURI()
	 * @generated
	 * @ordered
	 */
	protected Format uri;

	/**
	 * The cached value of the '{@link #getMatcher() <em>Matcher</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMatcher()
	 * @generated
	 * @ordered
	 */
	protected URIMatcher matcher;

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
	 * The cached value of the '{@link #getRepository() <em>Repository</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRepository()
	 * @generated
	 * @ordered
	 */
	protected Repository repository;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ProviderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Repository basicGetRepository() {
		return repository;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__DOCUMENTATION, oldDocumentation,
					newDocumentation);
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
	public NotificationChain basicSetMatcher(URIMatcher newMatcher, NotificationChain msgs) {
		URIMatcher oldMatcher = matcher;
		matcher = newMatcher;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__MATCHER, oldMatcher, newMatcher);
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
	public NotificationChain basicSetURI(Format newURI, NotificationChain msgs) {
		Format oldURI = uri;
		uri = newURI;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__URI, oldURI, newURI);
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
	public NotificationChain basicSetVersionConverter(VersionConverter newVersionConverter, NotificationChain msgs) {
		VersionConverter oldVersionConverter = versionConverter;
		versionConverter = newVersionConverter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__VERSION_CONVERTER,
					oldVersionConverter, newVersionConverter);
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
			case RmapPackage.PROVIDER__COMPONENT_TYPES:
				return getComponentTypes();
			case RmapPackage.PROVIDER__COMPONENT_TYPES_ATTR:
				return getComponentTypesAttr();
			case RmapPackage.PROVIDER__READER_TYPE:
				return getReaderType();
			case RmapPackage.PROVIDER__SOURCE:
				return isSource();
			case RmapPackage.PROVIDER__MUTABLE:
				return isMutable();
			case RmapPackage.PROVIDER__RESOLUTION_FILTER:
				return getResolutionFilter();
			case RmapPackage.PROVIDER__VERSION_CONVERTER:
				return getVersionConverter();
			case RmapPackage.PROVIDER__URI:
				return getURI();
			case RmapPackage.PROVIDER__MATCHER:
				return getMatcher();
			case RmapPackage.PROVIDER__DOCUMENTATION:
				return getDocumentation();
			case RmapPackage.PROVIDER__REPOSITORY:
				if (resolve)
					return getRepository();
				return basicGetRepository();
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
			case RmapPackage.PROVIDER__VERSION_CONVERTER:
				return basicSetVersionConverter(null, msgs);
			case RmapPackage.PROVIDER__URI:
				return basicSetURI(null, msgs);
			case RmapPackage.PROVIDER__MATCHER:
				return basicSetMatcher(null, msgs);
			case RmapPackage.PROVIDER__DOCUMENTATION:
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RmapPackage.PROVIDER__COMPONENT_TYPES:
				return componentTypes != null && !componentTypes.isEmpty();
			case RmapPackage.PROVIDER__COMPONENT_TYPES_ATTR:
				return COMPONENT_TYPES_ATTR_EDEFAULT == null ? getComponentTypesAttr() != null : !COMPONENT_TYPES_ATTR_EDEFAULT
						.equals(getComponentTypesAttr());
			case RmapPackage.PROVIDER__READER_TYPE:
				return READER_TYPE_EDEFAULT == null ? readerType != null : !READER_TYPE_EDEFAULT.equals(readerType);
			case RmapPackage.PROVIDER__SOURCE:
				return isSource() != SOURCE_EDEFAULT;
			case RmapPackage.PROVIDER__MUTABLE:
				return isMutable() != MUTABLE_EDEFAULT;
			case RmapPackage.PROVIDER__RESOLUTION_FILTER:
				return RESOLUTION_FILTER_EDEFAULT == null ? resolutionFilter != null : !RESOLUTION_FILTER_EDEFAULT.equals(resolutionFilter);
			case RmapPackage.PROVIDER__VERSION_CONVERTER:
				return versionConverter != null;
			case RmapPackage.PROVIDER__URI:
				return uri != null;
			case RmapPackage.PROVIDER__MATCHER:
				return matcher != null;
			case RmapPackage.PROVIDER__DOCUMENTATION:
				return documentation != null;
			case RmapPackage.PROVIDER__REPOSITORY:
				return repository != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RmapPackage.PROVIDER__COMPONENT_TYPES:
				getComponentTypes().clear();
				getComponentTypes().addAll((Collection<? extends String>) newValue);
				return;
			case RmapPackage.PROVIDER__COMPONENT_TYPES_ATTR:
				setComponentTypesAttr((String) newValue);
				return;
			case RmapPackage.PROVIDER__READER_TYPE:
				setReaderType((String) newValue);
				return;
			case RmapPackage.PROVIDER__SOURCE:
				setSource((Boolean) newValue);
				return;
			case RmapPackage.PROVIDER__MUTABLE:
				setMutable((Boolean) newValue);
				return;
			case RmapPackage.PROVIDER__RESOLUTION_FILTER:
				setResolutionFilter((Filter) newValue);
				return;
			case RmapPackage.PROVIDER__VERSION_CONVERTER:
				setVersionConverter((VersionConverter) newValue);
				return;
			case RmapPackage.PROVIDER__URI:
				setURI((Format) newValue);
				return;
			case RmapPackage.PROVIDER__MATCHER:
				setMatcher((URIMatcher) newValue);
				return;
			case RmapPackage.PROVIDER__DOCUMENTATION:
				setDocumentation((Documentation) newValue);
				return;
			case RmapPackage.PROVIDER__REPOSITORY:
				setRepository((Repository) newValue);
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
			case RmapPackage.PROVIDER__COMPONENT_TYPES:
				getComponentTypes().clear();
				return;
			case RmapPackage.PROVIDER__COMPONENT_TYPES_ATTR:
				setComponentTypesAttr(COMPONENT_TYPES_ATTR_EDEFAULT);
				return;
			case RmapPackage.PROVIDER__READER_TYPE:
				setReaderType(READER_TYPE_EDEFAULT);
				return;
			case RmapPackage.PROVIDER__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case RmapPackage.PROVIDER__MUTABLE:
				setMutable(MUTABLE_EDEFAULT);
				return;
			case RmapPackage.PROVIDER__RESOLUTION_FILTER:
				setResolutionFilter(RESOLUTION_FILTER_EDEFAULT);
				return;
			case RmapPackage.PROVIDER__VERSION_CONVERTER:
				setVersionConverter((VersionConverter) null);
				return;
			case RmapPackage.PROVIDER__URI:
				setURI((Format) null);
				return;
			case RmapPackage.PROVIDER__MATCHER:
				setMatcher((URIMatcher) null);
				return;
			case RmapPackage.PROVIDER__DOCUMENTATION:
				setDocumentation((Documentation) null);
				return;
			case RmapPackage.PROVIDER__REPOSITORY:
				setRepository((Repository) null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EList<String> getComponentTypes() {
		if (componentTypes == null) {
			componentTypes = new EDataTypeUniqueEList<String>(String.class, this, RmapPackage.PROVIDER__COMPONENT_TYPES);
		}
		return componentTypes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public String getComponentTypesAttr() {
		EList<String> ctypes = getComponentTypes();
		int top = ctypes.size();
		switch (top) {
			case 0:
				return null;
			case 1:
				return ctypes.get(0);
		}
		StringBuilder bld = new StringBuilder();
		bld.append(ctypes.get(0));
		for (int idx = 1; idx < top; ++idx) {
			bld.append(',');
			bld.append(ctypes.get(idx));
		}
		return bld.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Documentation getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public URIMatcher getMatcher() {
		return matcher;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public String getReaderType() {
		return readerType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Repository getRepository() {
		if (repository != null && repository.eIsProxy()) {
			InternalEObject oldRepository = (InternalEObject) repository;
			repository = (Repository) eResolveProxy(oldRepository);
			if (repository != oldRepository) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RmapPackage.PROVIDER__REPOSITORY, oldRepository, repository));
			}
		}
		return repository;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Filter getResolutionFilter() {
		return resolutionFilter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Format getURI() {
		return uri;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public String getURI(Map<String, String> properties) {
		return getURI().getValue(getProperties(properties));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public VersionConverter getVersionConverter() {
		return versionConverter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public boolean isMutable() {
		String tmp = getProperties().get(RmapConstants.IS_MUTABLE);
		return tmp == null ? MUTABLE_EDEFAULT : Boolean.valueOf(tmp);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public boolean isSource() {
		String tmp = getProperties().get(RmapConstants.IS_SOURCE);
		return tmp == null ? SOURCE_EDEFAULT : Boolean.valueOf(tmp);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public void setComponentTypesAttr(String newComponentTypesAttr) {
		if (newComponentTypesAttr == null || newComponentTypesAttr.length() == 0) {
			if (componentTypes != null)
				componentTypes.clear();
			return;
		}

		EList<String> ctypes = getComponentTypes();
		ctypes.clear();
		int commaIdx = newComponentTypesAttr.indexOf(',');
		int idx = 0;
		while (commaIdx >= idx) {
			ctypes.add(newComponentTypesAttr.substring(idx, commaIdx));
			idx = commaIdx + 1;
			commaIdx = newComponentTypesAttr.indexOf(',', idx);
		}
		if (idx < newComponentTypesAttr.length())
			ctypes.add(newComponentTypesAttr.substring(idx));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setDocumentation(Documentation newDocumentation) {
		if (newDocumentation != documentation) {
			NotificationChain msgs = null;
			if (documentation != null)
				msgs = ((InternalEObject) documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RmapPackage.PROVIDER__DOCUMENTATION, null,
						msgs);
			if (newDocumentation != null)
				msgs = ((InternalEObject) newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RmapPackage.PROVIDER__DOCUMENTATION, null,
						msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__DOCUMENTATION, newDocumentation, newDocumentation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setMatcher(URIMatcher newMatcher) {
		if (newMatcher != matcher) {
			NotificationChain msgs = null;
			if (matcher != null)
				msgs = ((InternalEObject) matcher).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RmapPackage.PROVIDER__MATCHER, null, msgs);
			if (newMatcher != null)
				msgs = ((InternalEObject) newMatcher).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RmapPackage.PROVIDER__MATCHER, null, msgs);
			msgs = basicSetMatcher(newMatcher, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__MATCHER, newMatcher, newMatcher));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public void setMutable(boolean mutable) {
		Map<String, String> props = getProperties();
		if (mutable == MUTABLE_EDEFAULT)
			props.remove(RmapConstants.IS_MUTABLE);
		else
			props.put(RmapConstants.IS_MUTABLE, String.valueOf(mutable));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setReaderType(String newReaderType) {
		String oldReaderType = readerType;
		readerType = newReaderType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__READER_TYPE, oldReaderType, readerType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setRepository(Repository newRepository) {
		Repository oldRepository = repository;
		repository = newRepository;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__REPOSITORY, oldRepository, repository));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setResolutionFilter(Filter newResolutionFilter) {
		Filter oldResolutionFilter = resolutionFilter;
		resolutionFilter = newResolutionFilter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__RESOLUTION_FILTER, oldResolutionFilter, resolutionFilter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setSource(boolean source) {
		Map<String, String> props = getProperties();
		if (source == SOURCE_EDEFAULT)
			props.remove(RmapConstants.IS_SOURCE);
		else
			props.put(RmapConstants.IS_SOURCE, String.valueOf(source));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setURI(Format newURI) {
		if (newURI != uri) {
			NotificationChain msgs = null;
			if (uri != null)
				msgs = ((InternalEObject) uri).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RmapPackage.PROVIDER__URI, null, msgs);
			if (newURI != null)
				msgs = ((InternalEObject) newURI).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RmapPackage.PROVIDER__URI, null, msgs);
			msgs = basicSetURI(newURI, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__URI, newURI, newURI));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setVersionConverter(VersionConverter newVersionConverter) {
		if (newVersionConverter != versionConverter) {
			NotificationChain msgs = null;
			if (versionConverter != null)
				msgs = ((InternalEObject) versionConverter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RmapPackage.PROVIDER__VERSION_CONVERTER,
						null, msgs);
			if (newVersionConverter != null)
				msgs = ((InternalEObject) newVersionConverter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RmapPackage.PROVIDER__VERSION_CONVERTER,
						null, msgs);
			msgs = basicSetVersionConverter(newVersionConverter, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__VERSION_CONVERTER, newVersionConverter, newVersionConverter));
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
		result.append(" (componentTypes: ");
		result.append(componentTypes);
		result.append(", readerType: ");
		result.append(readerType);
		result.append(", resolutionFilter: ");
		result.append(resolutionFilter);
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
		return RmapPackage.Literals.PROVIDER;
	}

	Map<String, String> getProperties(Map<String, String> properties) {
		SearchPath searchPath = (SearchPath) eContainer();
		if (searchPath != null) {
			ResourceMapImpl rmap = (ResourceMapImpl) searchPath.eContainer();
			if (rmap != null)
				properties = rmap.getProperties(properties);
		}
		return properties;
	}

} // ProviderImpl

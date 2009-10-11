/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import java.util.Collection;

import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.Format;

import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.URIMatcher;
import org.eclipse.buckminster.rmap.VersionConverter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Provider</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getComponentTypes <em>Component Types</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getComponentTypesAttr <em>Component Types Attr</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getReaderType <em>Reader Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#isSource <em>Source</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#isMutable <em>Mutable</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getResolutionFilter <em>Resolution Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getVersionConverter <em>Version Converter</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getUri <em>Uri</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getMatchers <em>Matchers</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ProviderImpl#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ProviderImpl extends EObjectImpl implements Provider
{
	/**
	 * The cached value of the '{@link #getComponentTypes() <em>Component Types</em>}' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getComponentTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> componentTypes;

	/**
	 * The default value of the '{@link #getComponentTypesAttr() <em>Component Types Attr</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getComponentTypesAttr()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPONENT_TYPES_ATTR_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getReaderType() <em>Reader Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getReaderType()
	 * @generated
	 * @ordered
	 */
	protected static final String READER_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReaderType() <em>Reader Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getReaderType()
	 * @generated
	 * @ordered
	 */
	protected String readerType = READER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isSource() <em>Source</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isSource()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SOURCE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSource() <em>Source</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isSource()
	 * @generated
	 * @ordered
	 */
	protected boolean source = SOURCE_EDEFAULT;

	/**
	 * The default value of the '{@link #isMutable() <em>Mutable</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isMutable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MUTABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isMutable() <em>Mutable</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isMutable()
	 * @generated
	 * @ordered
	 */
	protected boolean mutable = MUTABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getResolutionFilter() <em>Resolution Filter</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResolutionFilter()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOLUTION_FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResolutionFilter() <em>Resolution Filter</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResolutionFilter()
	 * @generated
	 * @ordered
	 */
	protected String resolutionFilter = RESOLUTION_FILTER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVersionConverter() <em>Version Converter</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersionConverter()
	 * @generated
	 * @ordered
	 */
	protected VersionConverter versionConverter;

	/**
	 * The cached value of the '{@link #getUri() <em>Uri</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected Format uri;

	/**
	 * The cached value of the '{@link #getMatchers() <em>Matchers</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMatchers()
	 * @generated
	 * @ordered
	 */
	protected EList<URIMatcher> matchers;

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
	protected ProviderImpl()
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
					RmapPackage.PROVIDER__DOCUMENTATION, oldDocumentation, newDocumentation);
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
	public NotificationChain basicSetUri(Format newUri, NotificationChain msgs)
	{
		Format oldUri = uri;
		uri = newUri;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__URI,
					oldUri, newUri);
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
	public NotificationChain basicSetVersionConverter(VersionConverter newVersionConverter, NotificationChain msgs)
	{
		VersionConverter oldVersionConverter = versionConverter;
		versionConverter = newVersionConverter;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					RmapPackage.PROVIDER__VERSION_CONVERTER, oldVersionConverter, newVersionConverter);
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
			return getUri();
		case RmapPackage.PROVIDER__MATCHERS:
			return getMatchers();
		case RmapPackage.PROVIDER__DOCUMENTATION:
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch(featureID)
		{
		case RmapPackage.PROVIDER__VERSION_CONVERTER:
			return basicSetVersionConverter(null, msgs);
		case RmapPackage.PROVIDER__URI:
			return basicSetUri(null, msgs);
		case RmapPackage.PROVIDER__MATCHERS:
			return ((InternalEList<?>)getMatchers()).basicRemove(otherEnd, msgs);
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
	public boolean eIsSet(int featureID)
	{
		switch(featureID)
		{
		case RmapPackage.PROVIDER__COMPONENT_TYPES:
			return componentTypes != null && !componentTypes.isEmpty();
		case RmapPackage.PROVIDER__COMPONENT_TYPES_ATTR:
			return COMPONENT_TYPES_ATTR_EDEFAULT == null
					? getComponentTypesAttr() != null
					: !COMPONENT_TYPES_ATTR_EDEFAULT.equals(getComponentTypesAttr());
		case RmapPackage.PROVIDER__READER_TYPE:
			return READER_TYPE_EDEFAULT == null
					? readerType != null
					: !READER_TYPE_EDEFAULT.equals(readerType);
		case RmapPackage.PROVIDER__SOURCE:
			return source != SOURCE_EDEFAULT;
		case RmapPackage.PROVIDER__MUTABLE:
			return mutable != MUTABLE_EDEFAULT;
		case RmapPackage.PROVIDER__RESOLUTION_FILTER:
			return RESOLUTION_FILTER_EDEFAULT == null
					? resolutionFilter != null
					: !RESOLUTION_FILTER_EDEFAULT.equals(resolutionFilter);
		case RmapPackage.PROVIDER__VERSION_CONVERTER:
			return versionConverter != null;
		case RmapPackage.PROVIDER__URI:
			return uri != null;
		case RmapPackage.PROVIDER__MATCHERS:
			return matchers != null && !matchers.isEmpty();
		case RmapPackage.PROVIDER__DOCUMENTATION:
			return documentation != null;
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
		case RmapPackage.PROVIDER__COMPONENT_TYPES:
			getComponentTypes().clear();
			getComponentTypes().addAll((Collection<? extends String>)newValue);
			return;
		case RmapPackage.PROVIDER__COMPONENT_TYPES_ATTR:
			setComponentTypesAttr((String)newValue);
			return;
		case RmapPackage.PROVIDER__READER_TYPE:
			setReaderType((String)newValue);
			return;
		case RmapPackage.PROVIDER__SOURCE:
			setSource((Boolean)newValue);
			return;
		case RmapPackage.PROVIDER__MUTABLE:
			setMutable((Boolean)newValue);
			return;
		case RmapPackage.PROVIDER__RESOLUTION_FILTER:
			setResolutionFilter((String)newValue);
			return;
		case RmapPackage.PROVIDER__VERSION_CONVERTER:
			setVersionConverter((VersionConverter)newValue);
			return;
		case RmapPackage.PROVIDER__URI:
			setUri((Format)newValue);
			return;
		case RmapPackage.PROVIDER__MATCHERS:
			getMatchers().clear();
			getMatchers().addAll((Collection<? extends URIMatcher>)newValue);
			return;
		case RmapPackage.PROVIDER__DOCUMENTATION:
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
			setVersionConverter((VersionConverter)null);
			return;
		case RmapPackage.PROVIDER__URI:
			setUri((Format)null);
			return;
		case RmapPackage.PROVIDER__MATCHERS:
			getMatchers().clear();
			return;
		case RmapPackage.PROVIDER__DOCUMENTATION:
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
	public EList<String> getComponentTypes()
	{
		if(componentTypes == null)
		{
			componentTypes = new EDataTypeUniqueEList<String>(String.class, this, RmapPackage.PROVIDER__COMPONENT_TYPES);
		}
		return componentTypes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getComponentTypesAttr()
	{
		EList<String> ctypes = getComponentTypes();
		int top = ctypes.size();
		switch(top)
		{
		case 0:
			return null;
		case 1:
			return ctypes.get(0);
		}
		StringBuilder bld = new StringBuilder();
		bld.append(ctypes.get(0));
		for(int idx = 1; idx < top; ++idx)
		{
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
	public Documentation getDocumentation()
	{
		return documentation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<URIMatcher> getMatchers()
	{
		if(matchers == null)
		{
			matchers = new EObjectContainmentEList<URIMatcher>(URIMatcher.class, this, RmapPackage.PROVIDER__MATCHERS);
		}
		return matchers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getReaderType()
	{
		return readerType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getResolutionFilter()
	{
		return resolutionFilter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Format getUri()
	{
		return uri;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public VersionConverter getVersionConverter()
	{
		return versionConverter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isMutable()
	{
		return mutable;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSource()
	{
		return source;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setComponentTypesAttr(String newComponentTypesAttr)
	{
		if(newComponentTypesAttr == null || newComponentTypesAttr.length() == 0)
		{
			if(componentTypes != null)
				componentTypes.clear();
			return;
		}

		EList<String> ctypes = getComponentTypes();
		ctypes.clear();
		int commaIdx = newComponentTypesAttr.indexOf(',');
		int idx = 0;
		while(commaIdx >= idx)
		{
			ctypes.add(newComponentTypesAttr.substring(idx, commaIdx));
			idx = commaIdx + 1;
			commaIdx = newComponentTypesAttr.indexOf(',', idx);
		}
		if(idx < newComponentTypesAttr.length())
			ctypes.add(newComponentTypesAttr.substring(idx));
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
						- RmapPackage.PROVIDER__DOCUMENTATION, null, msgs);
			if(newDocumentation != null)
				msgs = ((InternalEObject)newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- RmapPackage.PROVIDER__DOCUMENTATION, null, msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__DOCUMENTATION,
					newDocumentation, newDocumentation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setMutable(boolean newMutable)
	{
		boolean oldMutable = mutable;
		mutable = newMutable;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__MUTABLE, oldMutable, mutable));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setReaderType(String newReaderType)
	{
		String oldReaderType = readerType;
		readerType = newReaderType;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__READER_TYPE, oldReaderType,
					readerType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setResolutionFilter(String newResolutionFilter)
	{
		String oldResolutionFilter = resolutionFilter;
		resolutionFilter = newResolutionFilter;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__RESOLUTION_FILTER,
					oldResolutionFilter, resolutionFilter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSource(boolean newSource)
	{
		boolean oldSource = source;
		source = newSource;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setUri(Format newUri)
	{
		if(newUri != uri)
		{
			NotificationChain msgs = null;
			if(uri != null)
				msgs = ((InternalEObject)uri).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RmapPackage.PROVIDER__URI,
						null, msgs);
			if(newUri != null)
				msgs = ((InternalEObject)newUri).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RmapPackage.PROVIDER__URI,
						null, msgs);
			msgs = basicSetUri(newUri, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__URI, newUri, newUri));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVersionConverter(VersionConverter newVersionConverter)
	{
		if(newVersionConverter != versionConverter)
		{
			NotificationChain msgs = null;
			if(versionConverter != null)
				msgs = ((InternalEObject)versionConverter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- RmapPackage.PROVIDER__VERSION_CONVERTER, null, msgs);
			if(newVersionConverter != null)
				msgs = ((InternalEObject)newVersionConverter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- RmapPackage.PROVIDER__VERSION_CONVERTER, null, msgs);
			msgs = basicSetVersionConverter(newVersionConverter, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.PROVIDER__VERSION_CONVERTER,
					newVersionConverter, newVersionConverter));
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
		result.append(" (componentTypes: ");
		result.append(componentTypes);
		result.append(", readerType: ");
		result.append(readerType);
		result.append(", source: ");
		result.append(source);
		result.append(", mutable: ");
		result.append(mutable);
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
	protected EClass eStaticClass()
	{
		return RmapPackage.Literals.PROVIDER;
	}

} // ProviderImpl

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.metadata.impl;

import org.eclipse.buckminster.aggregator.engine.maven.metadata.DocumentRoot;
import org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData;
import org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Document Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS
 * Prefix Map</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.DocumentRootImpl#getXSISchemaLocation <em>
 * XSI Schema Location</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.DocumentRootImpl#getMetadata <em>Metadata
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot
{
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DocumentRootImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetMetadata(MetaData newMetadata, NotificationChain msgs)
	{
		return ((FeatureMap.Internal)getMixed()).basicAdd(MetadataPackage.Literals.DOCUMENT_ROOT__METADATA,
				newMetadata, msgs);
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
		case MetadataPackage.DOCUMENT_ROOT__MIXED:
			if(coreType)
				return getMixed();
			return ((FeatureMap.Internal)getMixed()).getWrapper();
		case MetadataPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			if(coreType)
				return getXMLNSPrefixMap();
			else
				return getXMLNSPrefixMap().map();
		case MetadataPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			if(coreType)
				return getXSISchemaLocation();
			else
				return getXSISchemaLocation().map();
		case MetadataPackage.DOCUMENT_ROOT__METADATA:
			return getMetadata();
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
		case MetadataPackage.DOCUMENT_ROOT__MIXED:
			return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
		case MetadataPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
		case MetadataPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
		case MetadataPackage.DOCUMENT_ROOT__METADATA:
			return basicSetMetadata(null, msgs);
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
		case MetadataPackage.DOCUMENT_ROOT__MIXED:
			return mixed != null && !mixed.isEmpty();
		case MetadataPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
		case MetadataPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
		case MetadataPackage.DOCUMENT_ROOT__METADATA:
			return getMetadata() != null;
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
		case MetadataPackage.DOCUMENT_ROOT__MIXED:
			((FeatureMap.Internal)getMixed()).set(newValue);
			return;
		case MetadataPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
			return;
		case MetadataPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
			return;
		case MetadataPackage.DOCUMENT_ROOT__METADATA:
			setMetadata((MetaData)newValue);
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
		case MetadataPackage.DOCUMENT_ROOT__MIXED:
			getMixed().clear();
			return;
		case MetadataPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			getXMLNSPrefixMap().clear();
			return;
		case MetadataPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			getXSISchemaLocation().clear();
			return;
		case MetadataPackage.DOCUMENT_ROOT__METADATA:
			setMetadata((MetaData)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MetaData getMetadata()
	{
		return (MetaData)getMixed().get(MetadataPackage.Literals.DOCUMENT_ROOT__METADATA, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FeatureMap getMixed()
	{
		if(mixed == null)
		{
			mixed = new BasicFeatureMap(this, MetadataPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap()
	{
		if(xMLNSPrefixMap == null)
		{
			xMLNSPrefixMap = new EcoreEMap<String, String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY,
					EStringToStringMapEntryImpl.class, this, MetadataPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation()
	{
		if(xSISchemaLocation == null)
		{
			xSISchemaLocation = new EcoreEMap<String, String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY,
					EStringToStringMapEntryImpl.class, this, MetadataPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setMetadata(MetaData newMetadata)
	{
		((FeatureMap.Internal)getMixed()).set(MetadataPackage.Literals.DOCUMENT_ROOT__METADATA, newMetadata);
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
		result.append(" (mixed: ");
		result.append(mixed);
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
		return MetadataPackage.Literals.DOCUMENT_ROOT;
	}

} // DocumentRootImpl

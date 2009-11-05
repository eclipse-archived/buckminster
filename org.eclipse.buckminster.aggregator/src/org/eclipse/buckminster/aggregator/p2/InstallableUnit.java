/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2;

import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionedId;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Installable Unit</b></em>'.
 * 
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getArtifactList <em>Artifact List</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getProvidedCapabilityList <em>Provided Capability
 * List</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getRequiredCapabilityList <em>Required Capability
 * List</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getMetaRequiredCapabilityList <em>Meta Required
 * Capability List</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getPropertyMap <em>Property Map</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getTouchpointDataList <em>Touchpoint Data List</em>}
 * </li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getInstallableUnit()
 * @model superTypes="org.eclipse.buckminster.aggregator.p2.IInstallableUnit"
 * @generated
 */
public interface InstallableUnit extends IInstallableUnit, StatusProvider
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model otherDataType="org.eclipse.emf.ecore.xml.type.AnySimpleType"
	 * @generated
	 */
	int compareTo(Object other);

	/**
	 * Returns the value of the '<em><b>Artifact List</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.aggregator.p2.ArtifactKey}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifact List</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Artifact List</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getInstallableUnit_ArtifactList()
	 * @model containment="true"
	 * @generated
	 */
	EList<ArtifactKey> getArtifactList();

	/**
	 * Returns the value of the '<em><b>Meta Required Capability List</b></em>' containment reference list. The list
	 * contents are of type {@link org.eclipse.buckminster.aggregator.p2.RequiredCapability}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Meta Required Capability List</em>' containment reference list isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Meta Required Capability List</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getInstallableUnit_MetaRequiredCapabilityList()
	 * @model containment="true"
	 * @generated
	 */
	EList<RequiredCapability> getMetaRequiredCapabilityList();

	/**
	 * Returns the value of the '<em><b>Property Map</b></em>' map. The key is of type {@link java.lang.String}, and the
	 * value is of type {@link java.lang.String}, <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Map</em>' map isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Property Map</em>' map.
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getInstallableUnit_PropertyMap()
	 * @model mapType=
	 *        "org.eclipse.buckminster.aggregator.p2.Property<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<String, String> getPropertyMap();

	/**
	 * Returns the value of the '<em><b>Provided Capability List</b></em>' containment reference list. The list contents
	 * are of type {@link org.eclipse.buckminster.aggregator.p2.ProvidedCapability}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provided Capability List</em>' containment reference list isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Provided Capability List</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getInstallableUnit_ProvidedCapabilityList()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProvidedCapability> getProvidedCapabilityList();

	/**
	 * Returns the value of the '<em><b>Required Capability List</b></em>' containment reference list. The list contents
	 * are of type {@link org.eclipse.buckminster.aggregator.p2.RequiredCapability}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Capability List</em>' containment reference list isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Required Capability List</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getInstallableUnit_RequiredCapabilityList()
	 * @model containment="true"
	 * @generated
	 */
	EList<RequiredCapability> getRequiredCapabilityList();

	/**
	 * Returns the value of the '<em><b>Touchpoint Data List</b></em>' containment reference list. The list contents are
	 * of type {@link org.eclipse.buckminster.aggregator.p2.TouchpointData}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Touchpoint Data List</em>' containment reference list isn't clear, there really should
	 * be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Touchpoint Data List</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getInstallableUnit_TouchpointDataList()
	 * @model containment="true"
	 * @generated
	 */
	EList<TouchpointData> getTouchpointDataList();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. The default value is <code>""</code>. The literals are
	 * from the enumeration {@link org.eclipse.buckminster.aggregator.p2.InstallableUnitType}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnitType
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getInstallableUnit_Type()
	 * @model default="" required="true" changeable="false" volatile="true"
	 * @generated
	 */
	InstallableUnitType getType();

	/**
	 * Obtains the name and version information either from the proxy URI fragment or from attributes. So, it works for
	 * both genuine instance or proxy.
	 */
	VersionedId getVersionedName();

	/**
	 * Obtains the name and version information from the proxy URI fragment
	 */
	VersionedId getVersionedNameFromProxy();

} // InstallableUnit

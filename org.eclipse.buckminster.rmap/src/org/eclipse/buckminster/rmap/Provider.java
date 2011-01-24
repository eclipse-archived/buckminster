/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import java.util.Map;

import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.model.common.Properties;
import org.eclipse.buckminster.rmap.util.IComponentReader;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Provider</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.rmap.Provider#getReaderType <em>Reader Type</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.Provider#isSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.Provider#isMutable <em>Mutable</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.Provider#getRepository <em>Repository</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.Provider#getVersionConverter <em>Version Converter</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.Provider#getURI <em>URI</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.Provider#getMatcher <em>Matcher</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.Provider#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider()
 * @model
 * @generated
 */
public interface Provider extends Properties, Matcher {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * <p>
	 * Some providers are backed by other resource map like files. The PDE map
	 * files and the Team PSF files are examples of this. This method returns
	 * the resource map representation of the backing files
	 * </p>
	 * <p>
	 * It is the resolvers responsibility to check if the provider is delegating
	 * and if it is, use the delegation map.
	 * </p>
	 * 
	 * @return A resource map to use for delegation
	 * @throws CoreException
	 *             if the delegation map cannot be produced
	 * @throws UnsupportedOperationException
	 *             if the provider is not a delegating kind
	 * @see #hasDelegationMap()
	 * @param reader
	 *            The reader used when exploring the sources used as input
	 * @param problemCollector
	 *            A MultiStatus that will receive any warnings and errors
	 * @param queryHints
	 *            A map that will receive query hints such as tags
	 * @param monitor
	 *            Monitor used for progress reporting <!-- end-model-doc -->
	 * @model exceptions="org.eclipse.buckminster.model.common.CoreException"
	 *        readerDataType="org.eclipse.buckminster.rmap.IComponentReader"
	 *        problemCollectorType
	 *        ="org.eclipse.buckminster.model.common.IStatus" monitorType=
	 *        "org.eclipse.buckminster.model.common.IProgressMonitor"
	 * @generated
	 */
	ResourceMap getDelegationMap(IComponentReader reader, IStatus problemCollector, Map<ComponentIdentifier, Map<String, String>> queryHints,
			IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' containment reference.
	 * @see #setDocumentation(Documentation)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_Documentation()
	 * @model containment="true"
	 *        extendedMetaData="name='documentation' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	Documentation getDocumentation();

	/**
	 * Returns the value of the '<em><b>Matcher</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Matcher</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Matcher</em>' containment reference.
	 * @see #setMatcher(URIMatcher)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_Matcher()
	 * @model containment="true"
	 *        extendedMetaData="name='matcher' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	URIMatcher getMatcher();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Map<String, String> getProperties(Map<String, String> properties);

	/**
	 * Returns the value of the '<em><b>Reader Type</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reader Type</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Reader Type</em>' attribute.
	 * @see #setReaderType(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_ReaderType()
	 * @model required="true"
	 * @generated
	 */
	String getReaderType();

	/**
	 * Returns the value of the '<em><b>Repository</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repository</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Repository</em>' reference.
	 * @see #setRepository(Repository)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_Repository()
	 * @model
	 * @generated
	 */
	Repository getRepository();

	/**
	 * Returns the value of the '<em><b>URI</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>URI</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>URI</em>' containment reference.
	 * @see #setURI(Format)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_URI()
	 * @model containment="true" required="true"
	 *        extendedMetaData="name='uri' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	Format getURI();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String getURI(Map<String, String> properties);

	/**
	 * Returns the value of the '<em><b>Version Converter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version Converter</em>' containment reference
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version Converter</em>' containment reference.
	 * @see #setVersionConverter(VersionConverter)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_VersionConverter()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	VersionConverter getVersionConverter();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * <p>
	 * Some providers are backed by other resource map like files. The PDE map
	 * files and the Team PSF files are examples of this. This method returns
	 * <code>true</code> if this is such a provider
	 * </p>
	 * <p>
	 * It is the resolvers responsibility to check if the provider is delegating
	 * and if it is, use the
	 * {@link #getDelegationMap(IComponentReader, IStatus, Map, IProgressMonitor)}
	 * method to obtain the new resource map
	 * </p>
	 * 
	 * @return <code>true</code> if this is a delegating provider
	 * 
	 *         <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean hasDelegationMap();

	/**
	 * Returns the value of the '<em><b>Mutable</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mutable</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mutable</em>' attribute.
	 * @see #setMutable(boolean)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_Mutable()
	 * @model default="true" volatile="true"
	 * @generated
	 */
	boolean isMutable();

	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(boolean)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProvider_Source()
	 * @model default="true" volatile="true"
	 * @generated
	 */
	boolean isSource();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Provider#getDocumentation <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' containment reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(Documentation value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Provider#getMatcher <em>Matcher</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Matcher</em>' containment reference.
	 * @see #getMatcher()
	 * @generated
	 */
	void setMatcher(URIMatcher value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Provider#isMutable <em>Mutable</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mutable</em>' attribute.
	 * @see #isMutable()
	 * @generated
	 */
	void setMutable(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Provider#getReaderType <em>Reader Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Reader Type</em>' attribute.
	 * @see #getReaderType()
	 * @generated
	 */
	void setReaderType(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Provider#getRepository <em>Repository</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Repository</em>' reference.
	 * @see #getRepository()
	 * @generated
	 */
	void setRepository(Repository value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Provider#isSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' attribute.
	 * @see #isSource()
	 * @generated
	 */
	void setSource(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Provider#getURI <em>URI</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>URI</em>' containment reference.
	 * @see #getURI()
	 * @generated
	 */
	void setURI(Format value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Provider#getVersionConverter <em>Version Converter</em>}' containment reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version Converter</em>' containment reference.
	 * @see #getVersionConverter()
	 * @generated
	 */
	void setVersionConverter(VersionConverter value);

} // Provider

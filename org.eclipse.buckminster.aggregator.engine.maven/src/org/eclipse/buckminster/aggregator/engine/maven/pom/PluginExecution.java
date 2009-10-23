/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Plugin Execution</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <!-- begin-model-doc --> 4.0.0 <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution#getPhase <em>Phase</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution#getGoals <em>Goals</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution#getInherited <em>Inherited</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution#getConfiguration <em>Configuration
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getPluginExecution()
 * @model extendedMetaData="name='PluginExecution' kind='elementOnly'"
 * @generated
 */
public interface PluginExecution extends EObject
{
	/**
	 * Returns the value of the '<em><b>Configuration</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> 0.0.0+ <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Configuration</em>' containment reference.
	 * @see #setConfiguration(ConfigurationType)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getPluginExecution_Configuration()
	 * @model containment="true" extendedMetaData="kind='element' name='configuration' namespace='##targetNamespace'"
	 * @generated
	 */
	ConfigurationType getConfiguration();

	/**
	 * Returns the value of the '<em><b>Goals</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * --> <!-- begin-model-doc --> 4.0.0 The goals to execute with the given configuration. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Goals</em>' containment reference.
	 * @see #setGoals(ExecutionGoalsType)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getPluginExecution_Goals()
	 * @model containment="true" extendedMetaData="kind='element' name='goals' namespace='##targetNamespace'"
	 * @generated
	 */
	ExecutionGoalsType getGoals();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. The default value is <code>"default"</code>. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> 4.0.0 The identifier of this execution for
	 * labelling the goals during the build, and for matching exections to merge during inheritance. <!-- end-model-doc
	 * -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #isSetId()
	 * @see #unsetId()
	 * @see #setId(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getPluginExecution_Id()
	 * @model default="default" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Returns the value of the '<em><b>Inherited</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc --> 4.0.0
	 * 
	 * Whether any configuration should be propagated to child POMs. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Inherited</em>' attribute.
	 * @see #setInherited(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getPluginExecution_Inherited()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='inherited' namespace='##targetNamespace'"
	 * @generated
	 */
	String getInherited();

	/**
	 * Returns the value of the '<em><b>Phase</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> 4.0.0 The build lifecycle phase to bind the goals in this execution to. If omitted, the goals
	 * will be bound to the default specified in their metadata. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Phase</em>' attribute.
	 * @see #setPhase(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getPluginExecution_Phase()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='phase' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPhase();

	/**
	 * Returns whether the value of the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution#getId <em>Id</em>}' attribute is set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Id</em>' attribute is set.
	 * @see #unsetId()
	 * @see #getId()
	 * @see #setId(String)
	 * @generated
	 */
	boolean isSetId();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution#getConfiguration
	 * <em>Configuration</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Configuration</em>' containment reference.
	 * @see #getConfiguration()
	 * @generated
	 */
	void setConfiguration(ConfigurationType value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution#getGoals
	 * <em>Goals</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Goals</em>' containment reference.
	 * @see #getGoals()
	 * @generated
	 */
	void setGoals(ExecutionGoalsType value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution#getId
	 * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #isSetId()
	 * @see #unsetId()
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution#getInherited
	 * <em>Inherited</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Inherited</em>' attribute.
	 * @see #getInherited()
	 * @generated
	 */
	void setInherited(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution#getPhase
	 * <em>Phase</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Phase</em>' attribute.
	 * @see #getPhase()
	 * @generated
	 */
	void setPhase(String value);

	/**
	 * Unsets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution#getId
	 * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetId()
	 * @see #getId()
	 * @see #setId(String)
	 * @generated
	 */
	void unsetId();

} // PluginExecution

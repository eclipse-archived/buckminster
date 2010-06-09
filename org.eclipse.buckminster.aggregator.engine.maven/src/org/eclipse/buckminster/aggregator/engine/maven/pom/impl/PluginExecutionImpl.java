/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom.impl;

import org.eclipse.buckminster.aggregator.engine.maven.pom.ConfigurationType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ExecutionGoalsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Plugin Execution</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.PluginExecutionImpl#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.PluginExecutionImpl#getPhase <em>Phase</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.PluginExecutionImpl#getGoals <em>Goals</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.PluginExecutionImpl#getInherited <em>Inherited
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.PluginExecutionImpl#getConfiguration <em>
 * Configuration</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PluginExecutionImpl extends EObjectImpl implements PluginExecution
{
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = "default";

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * This is true if the Id attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean idESet;

	/**
	 * The default value of the '{@link #getPhase() <em>Phase</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPhase()
	 * @generated
	 * @ordered
	 */
	protected static final String PHASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPhase() <em>Phase</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getPhase()
	 * @generated
	 * @ordered
	 */
	protected String phase = PHASE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGoals() <em>Goals</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGoals()
	 * @generated
	 * @ordered
	 */
	protected ExecutionGoalsType goals;

	/**
	 * The default value of the '{@link #getInherited() <em>Inherited</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getInherited()
	 * @generated
	 * @ordered
	 */
	protected static final String INHERITED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInherited() <em>Inherited</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getInherited()
	 * @generated
	 * @ordered
	 */
	protected String inherited = INHERITED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConfiguration() <em>Configuration</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getConfiguration()
	 * @generated
	 * @ordered
	 */
	protected ConfigurationType configuration;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PluginExecutionImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetConfiguration(ConfigurationType newConfiguration, NotificationChain msgs)
	{
		ConfigurationType oldConfiguration = configuration;
		configuration = newConfiguration;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.PLUGIN_EXECUTION__CONFIGURATION, oldConfiguration, newConfiguration);
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
	public NotificationChain basicSetGoals(ExecutionGoalsType newGoals, NotificationChain msgs)
	{
		ExecutionGoalsType oldGoals = goals;
		goals = newGoals;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.PLUGIN_EXECUTION__GOALS, oldGoals, newGoals);
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
		case PomPackage.PLUGIN_EXECUTION__ID:
			return getId();
		case PomPackage.PLUGIN_EXECUTION__PHASE:
			return getPhase();
		case PomPackage.PLUGIN_EXECUTION__GOALS:
			return getGoals();
		case PomPackage.PLUGIN_EXECUTION__INHERITED:
			return getInherited();
		case PomPackage.PLUGIN_EXECUTION__CONFIGURATION:
			return getConfiguration();
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
		case PomPackage.PLUGIN_EXECUTION__GOALS:
			return basicSetGoals(null, msgs);
		case PomPackage.PLUGIN_EXECUTION__CONFIGURATION:
			return basicSetConfiguration(null, msgs);
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
		case PomPackage.PLUGIN_EXECUTION__ID:
			return isSetId();
		case PomPackage.PLUGIN_EXECUTION__PHASE:
			return PHASE_EDEFAULT == null
					? phase != null
					: !PHASE_EDEFAULT.equals(phase);
		case PomPackage.PLUGIN_EXECUTION__GOALS:
			return goals != null;
		case PomPackage.PLUGIN_EXECUTION__INHERITED:
			return INHERITED_EDEFAULT == null
					? inherited != null
					: !INHERITED_EDEFAULT.equals(inherited);
		case PomPackage.PLUGIN_EXECUTION__CONFIGURATION:
			return configuration != null;
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
		case PomPackage.PLUGIN_EXECUTION__ID:
			setId((String)newValue);
			return;
		case PomPackage.PLUGIN_EXECUTION__PHASE:
			setPhase((String)newValue);
			return;
		case PomPackage.PLUGIN_EXECUTION__GOALS:
			setGoals((ExecutionGoalsType)newValue);
			return;
		case PomPackage.PLUGIN_EXECUTION__INHERITED:
			setInherited((String)newValue);
			return;
		case PomPackage.PLUGIN_EXECUTION__CONFIGURATION:
			setConfiguration((ConfigurationType)newValue);
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
		case PomPackage.PLUGIN_EXECUTION__ID:
			unsetId();
			return;
		case PomPackage.PLUGIN_EXECUTION__PHASE:
			setPhase(PHASE_EDEFAULT);
			return;
		case PomPackage.PLUGIN_EXECUTION__GOALS:
			setGoals((ExecutionGoalsType)null);
			return;
		case PomPackage.PLUGIN_EXECUTION__INHERITED:
			setInherited(INHERITED_EDEFAULT);
			return;
		case PomPackage.PLUGIN_EXECUTION__CONFIGURATION:
			setConfiguration((ConfigurationType)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ConfigurationType getConfiguration()
	{
		return configuration;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ExecutionGoalsType getGoals()
	{
		return goals;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getInherited()
	{
		return inherited;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getPhase()
	{
		return phase;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetId()
	{
		return idESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setConfiguration(ConfigurationType newConfiguration)
	{
		if(newConfiguration != configuration)
		{
			NotificationChain msgs = null;
			if(configuration != null)
				msgs = ((InternalEObject)configuration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.PLUGIN_EXECUTION__CONFIGURATION, null, msgs);
			if(newConfiguration != null)
				msgs = ((InternalEObject)newConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.PLUGIN_EXECUTION__CONFIGURATION, null, msgs);
			msgs = basicSetConfiguration(newConfiguration, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.PLUGIN_EXECUTION__CONFIGURATION,
					newConfiguration, newConfiguration));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setGoals(ExecutionGoalsType newGoals)
	{
		if(newGoals != goals)
		{
			NotificationChain msgs = null;
			if(goals != null)
				msgs = ((InternalEObject)goals).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.PLUGIN_EXECUTION__GOALS, null, msgs);
			if(newGoals != null)
				msgs = ((InternalEObject)newGoals).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.PLUGIN_EXECUTION__GOALS, null, msgs);
			msgs = basicSetGoals(newGoals, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.PLUGIN_EXECUTION__GOALS, newGoals,
					newGoals));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setId(String newId)
	{
		String oldId = id;
		id = newId;
		boolean oldIdESet = idESet;
		idESet = true;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.PLUGIN_EXECUTION__ID, oldId, id,
					!oldIdESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setInherited(String newInherited)
	{
		String oldInherited = inherited;
		inherited = newInherited;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.PLUGIN_EXECUTION__INHERITED, oldInherited,
					inherited));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPhase(String newPhase)
	{
		String oldPhase = phase;
		phase = newPhase;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.PLUGIN_EXECUTION__PHASE, oldPhase, phase));
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
		result.append(" (id: ");
		if(idESet)
			result.append(id);
		else
			result.append("<unset>");
		result.append(", phase: ");
		result.append(phase);
		result.append(", inherited: ");
		result.append(inherited);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetId()
	{
		String oldId = id;
		boolean oldIdESet = idESet;
		id = ID_EDEFAULT;
		idESet = false;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PomPackage.PLUGIN_EXECUTION__ID, oldId,
					ID_EDEFAULT, oldIdESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PomPackage.Literals.PLUGIN_EXECUTION;
	}

} // PluginExecutionImpl

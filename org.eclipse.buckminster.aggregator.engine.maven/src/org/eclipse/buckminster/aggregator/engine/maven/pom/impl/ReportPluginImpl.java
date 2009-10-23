/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom.impl;

import org.eclipse.buckminster.aggregator.engine.maven.pom.ConfigurationType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ReportPlugin;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ReportSetsType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Report Plugin</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.ReportPluginImpl#getGroupId <em>Group Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.ReportPluginImpl#getArtifactId <em>Artifact Id
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.ReportPluginImpl#getVersion <em>Version</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.ReportPluginImpl#getInherited <em>Inherited</em>}
 * </li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.ReportPluginImpl#getConfiguration <em>
 * Configuration</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.ReportPluginImpl#getReportSets <em>Report Sets
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ReportPluginImpl extends EObjectImpl implements ReportPlugin
{
	/**
	 * The default value of the '{@link #getGroupId() <em>Group Id</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_ID_EDEFAULT = "org.apache.maven.plugins";

	/**
	 * The cached value of the '{@link #getGroupId() <em>Group Id</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected String groupId = GROUP_ID_EDEFAULT;

	/**
	 * This is true if the Group Id attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean groupIdESet;

	/**
	 * The default value of the '{@link #getArtifactId() <em>Artifact Id</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getArtifactId()
	 * @generated
	 * @ordered
	 */
	protected static final String ARTIFACT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArtifactId() <em>Artifact Id</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getArtifactId()
	 * @generated
	 * @ordered
	 */
	protected String artifactId = ARTIFACT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

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
	 * The cached value of the '{@link #getReportSets() <em>Report Sets</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReportSets()
	 * @generated
	 * @ordered
	 */
	protected ReportSetsType reportSets;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ReportPluginImpl()
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
					PomPackage.REPORT_PLUGIN__CONFIGURATION, oldConfiguration, newConfiguration);
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
	public NotificationChain basicSetReportSets(ReportSetsType newReportSets, NotificationChain msgs)
	{
		ReportSetsType oldReportSets = reportSets;
		reportSets = newReportSets;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.REPORT_PLUGIN__REPORT_SETS, oldReportSets, newReportSets);
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
		case PomPackage.REPORT_PLUGIN__GROUP_ID:
			return getGroupId();
		case PomPackage.REPORT_PLUGIN__ARTIFACT_ID:
			return getArtifactId();
		case PomPackage.REPORT_PLUGIN__VERSION:
			return getVersion();
		case PomPackage.REPORT_PLUGIN__INHERITED:
			return getInherited();
		case PomPackage.REPORT_PLUGIN__CONFIGURATION:
			return getConfiguration();
		case PomPackage.REPORT_PLUGIN__REPORT_SETS:
			return getReportSets();
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
		case PomPackage.REPORT_PLUGIN__CONFIGURATION:
			return basicSetConfiguration(null, msgs);
		case PomPackage.REPORT_PLUGIN__REPORT_SETS:
			return basicSetReportSets(null, msgs);
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
		case PomPackage.REPORT_PLUGIN__GROUP_ID:
			return isSetGroupId();
		case PomPackage.REPORT_PLUGIN__ARTIFACT_ID:
			return ARTIFACT_ID_EDEFAULT == null
					? artifactId != null
					: !ARTIFACT_ID_EDEFAULT.equals(artifactId);
		case PomPackage.REPORT_PLUGIN__VERSION:
			return VERSION_EDEFAULT == null
					? version != null
					: !VERSION_EDEFAULT.equals(version);
		case PomPackage.REPORT_PLUGIN__INHERITED:
			return INHERITED_EDEFAULT == null
					? inherited != null
					: !INHERITED_EDEFAULT.equals(inherited);
		case PomPackage.REPORT_PLUGIN__CONFIGURATION:
			return configuration != null;
		case PomPackage.REPORT_PLUGIN__REPORT_SETS:
			return reportSets != null;
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
		case PomPackage.REPORT_PLUGIN__GROUP_ID:
			setGroupId((String)newValue);
			return;
		case PomPackage.REPORT_PLUGIN__ARTIFACT_ID:
			setArtifactId((String)newValue);
			return;
		case PomPackage.REPORT_PLUGIN__VERSION:
			setVersion((String)newValue);
			return;
		case PomPackage.REPORT_PLUGIN__INHERITED:
			setInherited((String)newValue);
			return;
		case PomPackage.REPORT_PLUGIN__CONFIGURATION:
			setConfiguration((ConfigurationType)newValue);
			return;
		case PomPackage.REPORT_PLUGIN__REPORT_SETS:
			setReportSets((ReportSetsType)newValue);
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
		case PomPackage.REPORT_PLUGIN__GROUP_ID:
			unsetGroupId();
			return;
		case PomPackage.REPORT_PLUGIN__ARTIFACT_ID:
			setArtifactId(ARTIFACT_ID_EDEFAULT);
			return;
		case PomPackage.REPORT_PLUGIN__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case PomPackage.REPORT_PLUGIN__INHERITED:
			setInherited(INHERITED_EDEFAULT);
			return;
		case PomPackage.REPORT_PLUGIN__CONFIGURATION:
			setConfiguration((ConfigurationType)null);
			return;
		case PomPackage.REPORT_PLUGIN__REPORT_SETS:
			setReportSets((ReportSetsType)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getArtifactId()
	{
		return artifactId;
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
	public String getGroupId()
	{
		return groupId;
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
	public ReportSetsType getReportSets()
	{
		return reportSets;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getVersion()
	{
		return version;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetGroupId()
	{
		return groupIdESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setArtifactId(String newArtifactId)
	{
		String oldArtifactId = artifactId;
		artifactId = newArtifactId;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.REPORT_PLUGIN__ARTIFACT_ID, oldArtifactId,
					artifactId));
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
						- PomPackage.REPORT_PLUGIN__CONFIGURATION, null, msgs);
			if(newConfiguration != null)
				msgs = ((InternalEObject)newConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.REPORT_PLUGIN__CONFIGURATION, null, msgs);
			msgs = basicSetConfiguration(newConfiguration, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.REPORT_PLUGIN__CONFIGURATION,
					newConfiguration, newConfiguration));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setGroupId(String newGroupId)
	{
		String oldGroupId = groupId;
		groupId = newGroupId;
		boolean oldGroupIdESet = groupIdESet;
		groupIdESet = true;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.REPORT_PLUGIN__GROUP_ID, oldGroupId,
					groupId, !oldGroupIdESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.REPORT_PLUGIN__INHERITED, oldInherited,
					inherited));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setReportSets(ReportSetsType newReportSets)
	{
		if(newReportSets != reportSets)
		{
			NotificationChain msgs = null;
			if(reportSets != null)
				msgs = ((InternalEObject)reportSets).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.REPORT_PLUGIN__REPORT_SETS, null, msgs);
			if(newReportSets != null)
				msgs = ((InternalEObject)newReportSets).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.REPORT_PLUGIN__REPORT_SETS, null, msgs);
			msgs = basicSetReportSets(newReportSets, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.REPORT_PLUGIN__REPORT_SETS, newReportSets,
					newReportSets));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVersion(String newVersion)
	{
		String oldVersion = version;
		version = newVersion;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.REPORT_PLUGIN__VERSION, oldVersion,
					version));
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
		result.append(" (groupId: ");
		if(groupIdESet)
			result.append(groupId);
		else
			result.append("<unset>");
		result.append(", artifactId: ");
		result.append(artifactId);
		result.append(", version: ");
		result.append(version);
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
	public void unsetGroupId()
	{
		String oldGroupId = groupId;
		boolean oldGroupIdESet = groupIdESet;
		groupId = GROUP_ID_EDEFAULT;
		groupIdESet = false;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, PomPackage.REPORT_PLUGIN__GROUP_ID, oldGroupId,
					GROUP_ID_EDEFAULT, oldGroupIdESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PomPackage.Literals.REPORT_PLUGIN;
	}

} // ReportPluginImpl

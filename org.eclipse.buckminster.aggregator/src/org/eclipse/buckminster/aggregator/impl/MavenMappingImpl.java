/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.MavenItem;
import org.eclipse.buckminster.aggregator.MavenMapping;
import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.buckminster.aggregator.util.GeneralUtils;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Maven Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MavenMappingImpl#getNamePattern <em>Name Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MavenMappingImpl#getGroupId <em>Group Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MavenMappingImpl#getArtifactId <em>Artifact Id</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MavenMappingImpl extends MinimalEObjectImpl.Container implements MavenMapping
{
	private Pattern compiledPattern;

	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The default value of the '{@link #getNamePattern() <em>Name Pattern</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getNamePattern()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNamePattern() <em>Name Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getNamePattern()
	 * @generated
	 * @ordered
	 */
	protected String namePattern = NAME_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroupId() <em>Group Id</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_ID_EDEFAULT = null;

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

	public MavenMappingImpl(String namePattern, String groupId, String artifactId)
	{
		setNamePattern(namePattern);
		setGroupId(groupId);
		setArtifactId(artifactId);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MavenMappingImpl()
	{
		super();
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
		case AggregatorPackage.MAVEN_MAPPING__NAME_PATTERN:
			return getNamePattern();
		case AggregatorPackage.MAVEN_MAPPING__GROUP_ID:
			return getGroupId();
		case AggregatorPackage.MAVEN_MAPPING__ARTIFACT_ID:
			return getArtifactId();
		}
		return super.eGet(featureID, resolve, coreType);
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
		case AggregatorPackage.MAVEN_MAPPING__NAME_PATTERN:
			return NAME_PATTERN_EDEFAULT == null
					? namePattern != null
					: !NAME_PATTERN_EDEFAULT.equals(namePattern);
		case AggregatorPackage.MAVEN_MAPPING__GROUP_ID:
			return GROUP_ID_EDEFAULT == null
					? groupId != null
					: !GROUP_ID_EDEFAULT.equals(groupId);
		case AggregatorPackage.MAVEN_MAPPING__ARTIFACT_ID:
			return ARTIFACT_ID_EDEFAULT == null
					? artifactId != null
					: !ARTIFACT_ID_EDEFAULT.equals(artifactId);
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
		case AggregatorPackage.MAVEN_MAPPING__NAME_PATTERN:
			setNamePattern((String)newValue);
			return;
		case AggregatorPackage.MAVEN_MAPPING__GROUP_ID:
			setGroupId((String)newValue);
			return;
		case AggregatorPackage.MAVEN_MAPPING__ARTIFACT_ID:
			setArtifactId((String)newValue);
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
		case AggregatorPackage.MAVEN_MAPPING__NAME_PATTERN:
			setNamePattern(NAME_PATTERN_EDEFAULT);
			return;
		case AggregatorPackage.MAVEN_MAPPING__GROUP_ID:
			setGroupId(GROUP_ID_EDEFAULT);
			return;
		case AggregatorPackage.MAVEN_MAPPING__ARTIFACT_ID:
			setArtifactId(ARTIFACT_ID_EDEFAULT);
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
	public String getGroupId()
	{
		return groupId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getNamePattern()
	{
		return namePattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int getStatus()
	{
		try
		{
			String pattern = GeneralUtils.trimmedOrNull(getNamePattern());
			if(pattern == null || GeneralUtils.trimmedOrNull(getGroupId()) == null
					|| GeneralUtils.trimmedOrNull(getArtifactId()) == null)
				return StatusProvider.BROKEN_CHILD;

			compiledPattern = Pattern.compile(pattern);
			checkReplacements(compiledPattern, getGroupId(), getArtifactId());
			return StatusProvider.OK;
		}
		catch(PatternSyntaxException e)
		{
			return StatusProvider.BROKEN_CHILD;
		}
		catch(IndexOutOfBoundsException e)
		{
			return StatusProvider.BROKEN_CHILD;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public MavenItem map(String installableUnitID)
	{
		if(compiledPattern != null)
		{
			Matcher m = compiledPattern.matcher(installableUnitID);
			if(m.matches())
			{
				MavenItem item = AggregatorFactory.eINSTANCE.createMavenItem();
				item.setGroupId(m.replaceFirst(getGroupId()));
				item.setArtifactId(m.replaceFirst(getArtifactId()));

				return item;
			}
		}

		throw new RuntimeException("Mapping pattern is null");
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
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.MAVEN_MAPPING__ARTIFACT_ID,
					oldArtifactId, artifactId));
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
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.MAVEN_MAPPING__GROUP_ID,
					oldGroupId, groupId));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setNamePattern(String newNamePattern)
	{
		String oldNamePattern = namePattern;
		namePattern = newNamePattern;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.MAVEN_MAPPING__NAME_PATTERN,
					oldNamePattern, namePattern));

		if(newNamePattern != null)
		{
			if(!newNamePattern.equals(oldNamePattern))
				try
				{
					compiledPattern = Pattern.compile(GeneralUtils.trimmedOrNull(newNamePattern));
				}
				catch(PatternSyntaxException e)
				{
					// ignore
				}
		}
		else
			compiledPattern = null;
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
		result.append(" (namePattern: ");
		result.append(namePattern);
		result.append(", groupId: ");
		result.append(groupId);
		result.append(", artifactId: ");
		result.append(artifactId);
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
		return AggregatorPackage.Literals.MAVEN_MAPPING;
	}

	private void checkReplacements(Pattern pattern, String... replacements)
	{
		String emptyString = "";
		String auxGroup = "(.*)";
		Matcher matcher = compiledPattern.matcher(emptyString);
		StringBuilder auxPatternBuilder = new StringBuilder();
		for(int i = matcher.groupCount(); i > 0; i--)
			auxPatternBuilder.append(auxGroup);

		Pattern auxPattern = Pattern.compile(auxPatternBuilder.toString());
		Matcher auxMatcher = auxPattern.matcher(emptyString);
		auxMatcher.replaceAll(getGroupId());
		auxMatcher.replaceAll(getArtifactId());
	}

} // MavenMappingImpl

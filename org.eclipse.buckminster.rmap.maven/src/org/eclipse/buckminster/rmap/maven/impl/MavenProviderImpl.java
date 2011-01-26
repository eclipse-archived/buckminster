/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven.impl;

import java.util.List;

import org.eclipse.buckminster.rmap.Transform;
import org.eclipse.buckminster.rmap.impl.ProviderImpl;
import org.eclipse.buckminster.rmap.maven.GroupAndArtifact;
import org.eclipse.buckminster.rmap.maven.MapEntry;
import org.eclipse.buckminster.rmap.maven.Mappings;
import org.eclipse.buckminster.rmap.maven.MavenPackage;
import org.eclipse.buckminster.rmap.maven.MavenProvider;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.osgi.util.NLS;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Maven Provider</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.rmap.maven.impl.MavenProviderImpl#getMappings
 * <em>Mappings</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MavenProviderImpl extends ProviderImpl implements MavenProvider {
	/**
	 * Apply default rules. I.e.
	 * <ul>
	 * <li>If the component name contains a dot, then separate the group and
	 * artifact using the last dot.</li>
	 * <li>If no dot is found, then use the same name for the group and artifact
	 * </li>
	 * </ul>
	 * 
	 * @param name
	 *            the name of the component
	 * @return an entry with a Maven groupId and artifactId
	 */
	public static MapEntry getDefaultMapEntry(String name) {
		int dotIdx = name.lastIndexOf('/');
		MapEntry entry = new MapEntryImpl();
		entry.setName(name);
		if (dotIdx > 0) {
			entry.setGroupId(name.substring(0, dotIdx));
			entry.setArtifactId(name.substring(dotIdx + 1));
		} else {
			entry.setGroupId(name);
			entry.setArtifactId(name);
		}
		return entry;
	}

	/**
	 * Apply default rules. I.e.
	 * <ul>
	 * <li>If the <code>groupId</code> and <code>artifactId</code> are equal,
	 * use the <code>artifactId</code></li>
	 * <li>If the <code>groupId</code> and <code>artifactId</code> are
	 * different, use <code>groupId.artifactId</code></li>
	 * </ul>
	 * 
	 * @param groupId
	 *            the Maven group id
	 * @param artifactId
	 *            the Maven artifact id
	 * @return The default component name.
	 */
	public static String getDefaultName(String groupId, String artifactId) {
		if (groupId.equals(artifactId)) {
			// Constructs like <groupId>:<artifactId> are known to exist
			//
			int colonIdx = artifactId.indexOf(':');
			if (colonIdx < 0)
				return artifactId;

			groupId = artifactId.substring(0, colonIdx);
			artifactId = artifactId.substring(colonIdx + 1);
		}
		return groupId + '/' + artifactId;
	}

	/**
	 * The cached value of the '{@link #getMappings() <em>Mappings</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMappings()
	 * @generated
	 * @ordered
	 */
	protected Mappings mappings;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MavenProviderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetMappings(Mappings newMappings, NotificationChain msgs) {
		Mappings oldMappings = mappings;
		mappings = newMappings;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MavenPackage.MAVEN_PROVIDER__MAPPINGS, oldMappings,
					newMappings);
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
			case MavenPackage.MAVEN_PROVIDER__MAPPINGS:
				return getMappings();
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
			case MavenPackage.MAVEN_PROVIDER__MAPPINGS:
				return basicSetMappings(null, msgs);
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
			case MavenPackage.MAVEN_PROVIDER__MAPPINGS:
				return mappings != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MavenPackage.MAVEN_PROVIDER__MAPPINGS:
				setMappings((Mappings) newValue);
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
			case MavenPackage.MAVEN_PROVIDER__MAPPINGS:
				setMappings((Mappings) null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getComponentName(String groupId, String artifactId) {
		if (getMappings() == null)
			return getDefaultName(groupId, artifactId);

		for (MapEntry me : getMappings().getEntries()) {
			if (me.isMatchFor(groupId, artifactId))
				return me.getName();

			List<GroupAndArtifact> aliases = me.getAliases();
			int idx = aliases.size();
			while (--idx >= 0) {
				GroupAndArtifact alias = aliases.get(idx);
				if (alias.isMatchFor(groupId, artifactId))
					return me.getName();
			}
		}

		List<Transform> rules = getMappings().getRules();
		if (rules.size() > 0) {
			String compiled = groupId + '/' + artifactId;
			for (Transform rule : rules) {
				String transformed = rule.transformTo(compiled);
				if (transformed != null)
					return transformed;
			}
		}
		return getDefaultName(groupId, artifactId);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public MapEntry getMapEntry(String name) throws CoreException {
		if (getMappings() == null)
			return getDefaultMapEntry(name);

		for (MapEntry me : getMappings().getEntries()) {
			if (name.equals(me.getName()))
				return me;
		}

		String transformed = null;
		for (Transform rule : getMappings().getRules()) {
			transformed = rule.transformFrom(name);
			if (transformed != null)
				break;
		}

		if (transformed == null)
			return getDefaultMapEntry(name);

		int slashPos = transformed.indexOf('/');
		if (slashPos < 0)
			throw BuckminsterException.fromMessage(NLS.bind("The result of applying a match rule on ''{0}'' had no separator slash: ''{1}''", name,
					transformed));

		MapEntry me = new MapEntryImpl();
		me.setName(name);
		me.setGroupId(transformed.substring(0, slashPos));
		me.setArtifactId(transformed.substring(slashPos + 1));
		return me;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Mappings getMappings() {
		return mappings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setMappings(Mappings newMappings) {
		if (newMappings != mappings) {
			NotificationChain msgs = null;
			if (mappings != null)
				msgs = ((InternalEObject) mappings).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MavenPackage.MAVEN_PROVIDER__MAPPINGS, null, msgs);
			if (newMappings != null)
				msgs = ((InternalEObject) newMappings).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MavenPackage.MAVEN_PROVIDER__MAPPINGS, null, msgs);
			msgs = basicSetMappings(newMappings, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MavenPackage.MAVEN_PROVIDER__MAPPINGS, newMappings, newMappings));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return MavenPackage.Literals.MAVEN_PROVIDER;
	}

} // MavenProviderImpl

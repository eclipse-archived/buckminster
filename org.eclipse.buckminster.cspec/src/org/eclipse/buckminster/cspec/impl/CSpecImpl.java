/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import java.net.URL;
import java.util.Collection;

import org.eclipse.buckminster.cspec.Attribute;
import org.eclipse.buckminster.cspec.CSpec;
import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.cspec.Generator;
import org.eclipse.buckminster.cspec.IContext;
import org.eclipse.buckminster.cspec.SelfArtifact;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.impl.ComponentIdentifierImpl;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>CSpec</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.impl.CSpecImpl#getDependencies <em>
 * Dependencies</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.CSpecImpl#getGenerators <em>
 * Generators</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.CSpecImpl#getAttributes <em>
 * Attributes</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.CSpecImpl#getDocumentation <em>
 * Documentation</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.CSpecImpl#getShortDesc <em>
 * Short Desc</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.CSpecImpl#getFilter <em>Filter
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.CSpecImpl#getProjectInfo <em>
 * Project Info</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.CSpecImpl#getSelf <em>Self
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class CSpecImpl extends ComponentIdentifierImpl implements CSpec {
	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}
	 * ' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<ComponentRequest> dependencies;

	/**
	 * The cached value of the '{@link #getGenerators() <em>Generators</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGenerators()
	 * @generated
	 * @ordered
	 */
	protected EList<Generator> generators;

	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<Attribute> attributes;

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
	 * The default value of the '{@link #getShortDesc() <em>Short Desc</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getShortDesc()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_DESC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShortDesc() <em>Short Desc</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getShortDesc()
	 * @generated
	 * @ordered
	 */
	protected String shortDesc = SHORT_DESC_EDEFAULT;

	/**
	 * The default value of the '{@link #getFilter() <em>Filter</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected static final Filter FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilter() <em>Filter</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected Filter filter = FILTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getProjectInfo() <em>Project Info</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProjectInfo()
	 * @generated
	 * @ordered
	 */
	protected static final URL PROJECT_INFO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProjectInfo() <em>Project Info</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProjectInfo()
	 * @generated
	 * @ordered
	 */
	protected URL projectInfo = PROJECT_INFO_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CSpecImpl() {
		super();
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CspecPackage.CSPEC__DOCUMENTATION, oldDocumentation,
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
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CspecPackage.CSPEC__DEPENDENCIES:
				return getDependencies();
			case CspecPackage.CSPEC__GENERATORS:
				return getGenerators();
			case CspecPackage.CSPEC__ATTRIBUTES:
				return getAttributes();
			case CspecPackage.CSPEC__DOCUMENTATION:
				return getDocumentation();
			case CspecPackage.CSPEC__SHORT_DESC:
				return getShortDesc();
			case CspecPackage.CSPEC__FILTER:
				return getFilter();
			case CspecPackage.CSPEC__PROJECT_INFO:
				return getProjectInfo();
			case CspecPackage.CSPEC__SELF:
				return getSelf();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CspecPackage.CSPEC__GENERATORS:
				return ((InternalEList<InternalEObject>) (InternalEList<?>) getGenerators()).basicAdd(otherEnd, msgs);
			case CspecPackage.CSPEC__ATTRIBUTES:
				return ((InternalEList<InternalEObject>) (InternalEList<?>) getAttributes()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CspecPackage.CSPEC__DEPENDENCIES:
				return ((InternalEList<?>) getDependencies()).basicRemove(otherEnd, msgs);
			case CspecPackage.CSPEC__GENERATORS:
				return ((InternalEList<?>) getGenerators()).basicRemove(otherEnd, msgs);
			case CspecPackage.CSPEC__ATTRIBUTES:
				return ((InternalEList<?>) getAttributes()).basicRemove(otherEnd, msgs);
			case CspecPackage.CSPEC__DOCUMENTATION:
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
			case CspecPackage.CSPEC__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case CspecPackage.CSPEC__GENERATORS:
				return generators != null && !generators.isEmpty();
			case CspecPackage.CSPEC__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case CspecPackage.CSPEC__DOCUMENTATION:
				return documentation != null;
			case CspecPackage.CSPEC__SHORT_DESC:
				return SHORT_DESC_EDEFAULT == null ? shortDesc != null : !SHORT_DESC_EDEFAULT.equals(shortDesc);
			case CspecPackage.CSPEC__FILTER:
				return FILTER_EDEFAULT == null ? filter != null : !FILTER_EDEFAULT.equals(filter);
			case CspecPackage.CSPEC__PROJECT_INFO:
				return PROJECT_INFO_EDEFAULT == null ? projectInfo != null : !PROJECT_INFO_EDEFAULT.equals(projectInfo);
			case CspecPackage.CSPEC__SELF:
				return isSetSelf();
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CspecPackage.CSPEC__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection<? extends ComponentRequest>) newValue);
				return;
			case CspecPackage.CSPEC__GENERATORS:
				getGenerators().clear();
				getGenerators().addAll((Collection<? extends Generator>) newValue);
				return;
			case CspecPackage.CSPEC__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends Attribute>) newValue);
				return;
			case CspecPackage.CSPEC__DOCUMENTATION:
				setDocumentation((Documentation) newValue);
				return;
			case CspecPackage.CSPEC__SHORT_DESC:
				setShortDesc((String) newValue);
				return;
			case CspecPackage.CSPEC__FILTER:
				setFilter((Filter) newValue);
				return;
			case CspecPackage.CSPEC__PROJECT_INFO:
				setProjectInfo((URL) newValue);
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
			case CspecPackage.CSPEC__DEPENDENCIES:
				getDependencies().clear();
				return;
			case CspecPackage.CSPEC__GENERATORS:
				getGenerators().clear();
				return;
			case CspecPackage.CSPEC__ATTRIBUTES:
				getAttributes().clear();
				return;
			case CspecPackage.CSPEC__DOCUMENTATION:
				setDocumentation((Documentation) null);
				return;
			case CspecPackage.CSPEC__SHORT_DESC:
				setShortDesc(SHORT_DESC_EDEFAULT);
				return;
			case CspecPackage.CSPEC__FILTER:
				setFilter(FILTER_EDEFAULT);
				return;
			case CspecPackage.CSPEC__PROJECT_INFO:
				setProjectInfo(PROJECT_INFO_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Attribute getAttribute(String name) {
		for (Attribute attr : getAttributes())
			if (name.equals(attr.getName()))
				return attr;
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Attribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentWithInverseEList<Attribute>(Attribute.class, this, CspecPackage.CSPEC__ATTRIBUTES,
					CspecPackage.ATTRIBUTE__CSPEC);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<ComponentRequest> getDependencies() {
		if (dependencies == null) {
			dependencies = new EObjectContainmentEList<ComponentRequest>(ComponentRequest.class, this, CspecPackage.CSPEC__DEPENDENCIES);
		}
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Attribute> getDerivedAttributes(IContext context, boolean includePrivate) {
		EList<Attribute> attributes = new BasicEList<Attribute>();
		for (Attribute ag : getAttributes())
			if ((includePrivate || ag.isPublic()) && ag.isDerived(context))
				attributes.add(ag);
		return attributes;
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
	public Filter getFilter() {
		return filter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Generator> getGenerators() {
		if (generators == null) {
			generators = new EObjectContainmentWithInverseEList<Generator>(Generator.class, this, CspecPackage.CSPEC__GENERATORS,
					CspecPackage.GENERATOR__CSPEC);
		}
		return generators;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public URL getProjectInfo() {
		return projectInfo;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public SelfArtifact getSelf() {
		return (SelfArtifact) getAttribute(SELF_ARTIFACT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getShortDesc() {
		return shortDesc;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isSetSelf() {
		return getAttribute(SELF_ARTIFACT) != null;
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
				msgs = ((InternalEObject) documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CspecPackage.CSPEC__DOCUMENTATION, null, msgs);
			if (newDocumentation != null)
				msgs = ((InternalEObject) newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CspecPackage.CSPEC__DOCUMENTATION, null, msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.CSPEC__DOCUMENTATION, newDocumentation, newDocumentation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFilter(Filter newFilter) {
		Filter oldFilter = filter;
		filter = newFilter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.CSPEC__FILTER, oldFilter, filter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setProjectInfo(URL newProjectInfo) {
		URL oldProjectInfo = projectInfo;
		projectInfo = newProjectInfo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.CSPEC__PROJECT_INFO, oldProjectInfo, projectInfo));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setShortDesc(String newShortDesc) {
		String oldShortDesc = shortDesc;
		shortDesc = newShortDesc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.CSPEC__SHORT_DESC, oldShortDesc, shortDesc));
	}

	@Override
	public void toString(StringBuilder result) {
		if (eIsProxy()) {
			result.append(super.toString());
			return;
		}

		result.append(" (shortDesc: ");
		result.append(shortDesc);
		result.append(", filter: ");
		result.append(filter);
		result.append(", projectInfo: ");
		result.append(projectInfo);
		result.append(')');
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String toStringGen() {
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CspecPackage.Literals.CSPEC;
	}

} // CSpecImpl

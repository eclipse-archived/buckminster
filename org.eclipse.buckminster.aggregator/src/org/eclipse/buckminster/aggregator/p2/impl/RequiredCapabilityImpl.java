/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2.impl;

import org.eclipse.buckminster.aggregator.p2.P2Package;
import org.eclipse.buckminster.aggregator.p2.RequiredCapability;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Required Capability</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl#getNamespace <em>Namespace</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl#getRange <em>Range</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl#getSelectors <em>Selectors</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl#isMultiple <em>Multiple</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl#isOptional <em>Optional</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl#isGreedy <em>Greedy</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class RequiredCapabilityImpl extends MinimalEObjectImpl.Container implements RequiredCapability
{
	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The default value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected static final String FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected String filter = FILTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNamespace() <em>Namespace</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
	protected static final String NAMESPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNamespace() <em>Namespace</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
	protected String namespace = NAMESPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRange() <em>Range</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getRange()
	 * @generated
	 * @ordered
	 */
	protected static final VersionRange RANGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRange() <em>Range</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getRange()
	 * @generated
	 * @ordered
	 */
	protected VersionRange range = RANGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSelectors() <em>Selectors</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getSelectors()
	 * @generated
	 * @ordered
	 */
	protected static final String[] SELECTORS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSelectors() <em>Selectors</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getSelectors()
	 * @generated
	 * @ordered
	 */
	protected String[] selectors = SELECTORS_EDEFAULT;

	/**
	 * The default value of the '{@link #isMultiple() <em>Multiple</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isMultiple()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MULTIPLE_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isMultiple() <em>Multiple</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #isMultiple()
	 * @generated
	 * @ordered
	 */
	protected static final int MULTIPLE_EFLAG = 1 << 0;

	/**
	 * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPTIONAL_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isOptional() <em>Optional</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected static final int OPTIONAL_EFLAG = 1 << 1;

	/**
	 * The default value of the '{@link #isGreedy() <em>Greedy</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isGreedy()
	 * @generated
	 * @ordered
	 */
	protected static final boolean GREEDY_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isGreedy() <em>Greedy</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isGreedy()
	 * @generated
	 * @ordered
	 */
	protected static final int GREEDY_EFLAG = 1 << 2;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RequiredCapabilityImpl()
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
		case P2Package.REQUIRED_CAPABILITY__FILTER:
			return getFilter();
		case P2Package.REQUIRED_CAPABILITY__NAME:
			return getName();
		case P2Package.REQUIRED_CAPABILITY__NAMESPACE:
			return getNamespace();
		case P2Package.REQUIRED_CAPABILITY__RANGE:
			return getRange();
		case P2Package.REQUIRED_CAPABILITY__SELECTORS:
			return getSelectors();
		case P2Package.REQUIRED_CAPABILITY__MULTIPLE:
			return isMultiple();
		case P2Package.REQUIRED_CAPABILITY__OPTIONAL:
			return isOptional();
		case P2Package.REQUIRED_CAPABILITY__GREEDY:
			return isGreedy();
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
		case P2Package.REQUIRED_CAPABILITY__FILTER:
			return FILTER_EDEFAULT == null
					? filter != null
					: !FILTER_EDEFAULT.equals(filter);
		case P2Package.REQUIRED_CAPABILITY__NAME:
			return NAME_EDEFAULT == null
					? name != null
					: !NAME_EDEFAULT.equals(name);
		case P2Package.REQUIRED_CAPABILITY__NAMESPACE:
			return NAMESPACE_EDEFAULT == null
					? namespace != null
					: !NAMESPACE_EDEFAULT.equals(namespace);
		case P2Package.REQUIRED_CAPABILITY__RANGE:
			return RANGE_EDEFAULT == null
					? range != null
					: !RANGE_EDEFAULT.equals(range);
		case P2Package.REQUIRED_CAPABILITY__SELECTORS:
			return SELECTORS_EDEFAULT == null
					? selectors != null
					: !SELECTORS_EDEFAULT.equals(selectors);
		case P2Package.REQUIRED_CAPABILITY__MULTIPLE:
			return ((eFlags & MULTIPLE_EFLAG) != 0) != MULTIPLE_EDEFAULT;
		case P2Package.REQUIRED_CAPABILITY__OPTIONAL:
			return ((eFlags & OPTIONAL_EFLAG) != 0) != OPTIONAL_EDEFAULT;
		case P2Package.REQUIRED_CAPABILITY__GREEDY:
			return ((eFlags & GREEDY_EFLAG) != 0) != GREEDY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof IRequiredCapability))
			return false;
		final IRequiredCapability other = (IRequiredCapability)obj;
		if(filter == null)
		{
			if(other.getFilter() != null)
				return false;
		}
		else if(!filter.equals(other.getFilter()))
			return false;
		if(isMultiple() != other.isMultiple())
			return false;
		if(!name.equals(other.getName()))
			return false;
		if(!namespace.equals(other.getNamespace()))
			return false;
		if(isOptional() != other.isOptional())
			return false;
		if(!range.equals(other.getRange()))
			return false;
		return true;
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
		case P2Package.REQUIRED_CAPABILITY__FILTER:
			setFilter((String)newValue);
			return;
		case P2Package.REQUIRED_CAPABILITY__NAME:
			setName((String)newValue);
			return;
		case P2Package.REQUIRED_CAPABILITY__NAMESPACE:
			setNamespace((String)newValue);
			return;
		case P2Package.REQUIRED_CAPABILITY__RANGE:
			setRange((VersionRange)newValue);
			return;
		case P2Package.REQUIRED_CAPABILITY__SELECTORS:
			setSelectors((String[])newValue);
			return;
		case P2Package.REQUIRED_CAPABILITY__MULTIPLE:
			setMultiple((Boolean)newValue);
			return;
		case P2Package.REQUIRED_CAPABILITY__OPTIONAL:
			setOptional((Boolean)newValue);
			return;
		case P2Package.REQUIRED_CAPABILITY__GREEDY:
			setGreedy((Boolean)newValue);
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
		case P2Package.REQUIRED_CAPABILITY__FILTER:
			setFilter(FILTER_EDEFAULT);
			return;
		case P2Package.REQUIRED_CAPABILITY__NAME:
			setName(NAME_EDEFAULT);
			return;
		case P2Package.REQUIRED_CAPABILITY__NAMESPACE:
			setNamespace(NAMESPACE_EDEFAULT);
			return;
		case P2Package.REQUIRED_CAPABILITY__RANGE:
			setRange(RANGE_EDEFAULT);
			return;
		case P2Package.REQUIRED_CAPABILITY__SELECTORS:
			setSelectors(SELECTORS_EDEFAULT);
			return;
		case P2Package.REQUIRED_CAPABILITY__MULTIPLE:
			setMultiple(MULTIPLE_EDEFAULT);
			return;
		case P2Package.REQUIRED_CAPABILITY__OPTIONAL:
			setOptional(OPTIONAL_EDEFAULT);
			return;
		case P2Package.REQUIRED_CAPABILITY__GREEDY:
			setGreedy(GREEDY_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getFilter()
	{
		return filter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getNamespace()
	{
		return namespace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public VersionRange getRange()
	{
		return range;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String[] getSelectors()
	{
		return selectors;
	}

	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filter == null)
				? 0
				: filter.hashCode());
		result = prime * result + (isMultiple()
				? 1231
				: 1237);
		result = prime * result + name.hashCode();
		result = prime * result + namespace.hashCode();
		result = prime * result + (isOptional()
				? 1231
				: 1237);
		result = prime * result + range.hashCode();
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isGreedy()
	{
		return (eFlags & GREEDY_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isMultiple()
	{
		return (eFlags & MULTIPLE_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isOptional()
	{
		return (eFlags & OPTIONAL_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFilter(String newFilter)
	{
		String oldFilter = filter;
		filter = newFilter;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.REQUIRED_CAPABILITY__FILTER, oldFilter,
					filter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setGreedy(boolean newGreedy)
	{
		boolean oldGreedy = (eFlags & GREEDY_EFLAG) != 0;
		if(newGreedy)
			eFlags |= GREEDY_EFLAG;
		else
			eFlags &= ~GREEDY_EFLAG;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.REQUIRED_CAPABILITY__GREEDY, oldGreedy,
					newGreedy));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setMultiple(boolean newMultiple)
	{
		boolean oldMultiple = (eFlags & MULTIPLE_EFLAG) != 0;
		if(newMultiple)
			eFlags |= MULTIPLE_EFLAG;
		else
			eFlags &= ~MULTIPLE_EFLAG;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.REQUIRED_CAPABILITY__MULTIPLE, oldMultiple,
					newMultiple));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.REQUIRED_CAPABILITY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setNamespace(String newNamespace)
	{
		String oldNamespace = namespace;
		namespace = newNamespace;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.REQUIRED_CAPABILITY__NAMESPACE,
					oldNamespace, namespace));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setOptional(boolean newOptional)
	{
		boolean oldOptional = (eFlags & OPTIONAL_EFLAG) != 0;
		if(newOptional)
			eFlags |= OPTIONAL_EFLAG;
		else
			eFlags &= ~OPTIONAL_EFLAG;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.REQUIRED_CAPABILITY__OPTIONAL, oldOptional,
					newOptional));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setRange(VersionRange newRange)
	{
		VersionRange oldRange = range;
		range = newRange;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.REQUIRED_CAPABILITY__RANGE, oldRange, range));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSelectors(String[] newSelectors)
	{
		String[] oldSelectors = selectors;
		selectors = newSelectors;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.REQUIRED_CAPABILITY__SELECTORS,
					oldSelectors, selectors));
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
		result.append(" (filter: ");
		result.append(filter);
		result.append(", name: ");
		result.append(name);
		result.append(", namespace: ");
		result.append(namespace);
		result.append(", range: ");
		result.append(range);
		result.append(", selectors: ");
		result.append(selectors);
		result.append(", multiple: ");
		result.append((eFlags & MULTIPLE_EFLAG) != 0);
		result.append(", optional: ");
		result.append((eFlags & OPTIONAL_EFLAG) != 0);
		result.append(", greedy: ");
		result.append((eFlags & GREEDY_EFLAG) != 0);
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
		return P2Package.Literals.REQUIRED_CAPABILITY;
	}

} // RequiredCapabilityImpl

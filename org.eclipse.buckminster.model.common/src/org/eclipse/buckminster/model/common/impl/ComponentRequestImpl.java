/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.Map;

import org.eclipse.buckminster.model.common.CommonConstants;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.ComponentName;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.model.common.util.ComponentRequestConflictException;
import org.eclipse.buckminster.model.common.util.VersionHelper;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.equinox.p2.metadata.VersionRange;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Component Request</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.ComponentRequestImpl#getRange
 * <em>Range</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.ComponentRequestImpl#getFilter
 * <em>Filter</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ComponentRequestImpl extends ComponentNameImpl implements ComponentRequest {
	/**
	 * The default value of the '{@link #getRange() <em>Range</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRange()
	 * @generated
	 * @ordered
	 */
	protected static final VersionRange RANGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRange() <em>Range</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRange()
	 * @generated
	 * @ordered
	 */
	protected VersionRange range = RANGE_EDEFAULT;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComponentRequestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public void appendViewName(StringBuilder result) {
		result.append(getId());
		String componentType = getType();
		if (componentType != null) {
			result.append(':');
			result.append(componentType);
		}
		if (filter != null)
			result.append(filter);
	}

	@Override
	public int compareTo(ComponentName o) {
		if (this == o)
			return 0;

		int cmp = super.compareTo(o);
		if (cmp != 0)
			return cmp;

		if (o instanceof ComponentRequest) {
			ComponentRequest cr = (ComponentRequest) o;
			if (getRange() == null) {
				if (cr.getRange() != null)
					return -1;
			} else {
				if (cr.getRange() == null)
					return 1;

				cmp = getRange().getMinimum().compareTo(cr.getRange().getMinimum());
				if (cmp != 0)
					return cmp;
				if (getRange().getIncludeMinimum()) {
					if (!cr.getRange().getIncludeMinimum())
						return -1;
				} else {
					if (cr.getRange().getIncludeMinimum())
						return 1;
				}

				cmp = getRange().getMaximum().compareTo(cr.getRange().getMaximum());
				if (cmp != 0)
					return cmp;
				if (getRange().getIncludeMaximum()) {
					if (!cr.getRange().getIncludeMaximum())
						return 1;
				} else {
					if (cr.getRange().getIncludeMaximum())
						return -1;
				}
			}
			if (getFilter() == null) {
				if (cr.getFilter() != null)
					return -1;
			} else {
				if (cr.getFilter() == null)
					return 1;
				cmp = getFilter().toString().compareTo(cr.getFilter().toString());
				if (cmp != 0)
					return cmp;
			}
		} else {
			if (getRange() != null || getFilter() != null)
				return 1;
		}
		return 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public boolean designates(ComponentIdentifier cid) {
		return Trivial.equalsAllowNull(getId(), cid.getId()) && (getType() == null || getType().equals(cid.getType()))
				&& (getRange() == null || getRange().isIncluded(cid.getVersion()));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.COMPONENT_REQUEST__RANGE:
				return getRange();
			case CommonPackage.COMPONENT_REQUEST__FILTER:
				return getFilter();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CommonPackage.COMPONENT_REQUEST__RANGE:
				return RANGE_EDEFAULT == null ? range != null : !RANGE_EDEFAULT.equals(range);
			case CommonPackage.COMPONENT_REQUEST__FILTER:
				return FILTER_EDEFAULT == null ? filter != null : !FILTER_EDEFAULT.equals(filter);
		}
		return super.eIsSet(featureID);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!super.equals(o))
			return false;

		ComponentRequest cr = (ComponentRequest) o;
		return Trivial.equalsAllowNull(getRange(), cr.getRange()) && Trivial.equalsAllowNull(getFilter(), cr.getFilter());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CommonPackage.COMPONENT_REQUEST__RANGE:
				setRange((VersionRange) newValue);
				return;
			case CommonPackage.COMPONENT_REQUEST__FILTER:
				setFilter((Filter) newValue);
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
			case CommonPackage.COMPONENT_REQUEST__RANGE:
				setRange(RANGE_EDEFAULT);
				return;
			case CommonPackage.COMPONENT_REQUEST__FILTER:
				setFilter(FILTER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Filter getFilter() {
		return filter;
	}

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> p = super.getProperties();
		if (getRange() != null)
			p.put(CommonConstants.COMPONENT_RANGE, getRange().toString());
		return p;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public VersionRange getRange() {
		return range;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getViewName() {
		StringBuilder bld = new StringBuilder();
		appendViewName(bld);
		return bld.toString();
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();
		hash = 31 * hash + (getRange() == null ? 0 : getRange().hashCode());
		return 31 * hash + (getFilter() == null ? 0 : getFilter().hashCode());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public boolean isEnabled(Map<String, ? extends Object> properties) {
		return filter == null || filter.matchCase(properties);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public boolean isOptional() {
		return filter != null && filter.toString().contains(CommonConstants.FILTER_ECLIPSE_P2_OPTIONAL);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public ComponentRequest merge(ComponentRequest request) {
		if (!Trivial.equalsAllowNull(getId(), request.getId()))
			throw new ComponentRequestConflictException(this, request);

		int cmp = 0;
		final VersionRange thisVD = getRange();
		final VersionRange thatVD = request.getRange();
		VersionRange mergedVD = null;
		if (thisVD == null) {
			if (thatVD != null) {
				cmp = 1; // limited by that
				mergedVD = thatVD;
			}
		} else {
			if (thatVD == null) {
				cmp = -1; // limited by this
				mergedVD = thisVD;
			} else {
				mergedVD = thisVD.intersect(thatVD);
				if (mergedVD == null)
					throw new ComponentRequestConflictException(this, request);

				if (mergedVD.equals(thisVD)) {
					if (!mergedVD.equals(thatVD))
						cmp = -1; // limited by this
				} else {
					if (mergedVD.equals(thatVD))
						cmp = 1; // limited by that
					else
						cmp = 2; // Limited by both
				}
			}
		}

		final String thisCType = getType();
		final String thatCType = request.getType();
		String mergedCType = null;
		if (thisCType == null) {
			if (thatCType != null) {
				if (cmp == 0)
					cmp = 1;
				mergedCType = thatCType;
			}
		} else {
			if (thatCType != null) {
				if (!thisCType.equals(thatCType))
					throw new ComponentRequestConflictException(this, request);
			} else {
				if (cmp == 0)
					cmp = -1;
			}
			mergedCType = thisCType;
		}

		final Filter thisFilter = getFilter();
		final Filter thatFilter = request.getFilter();
		boolean thisOptional = isOptional();
		boolean thatOptional = request.isOptional();
		Filter mergedFilter = null;
		if (!Trivial.equalsAllowNull(thisFilter, thatFilter)) {
			if (thisOptional != thatOptional)
				// Filters can only be merged if both are required or
				// both are optional
				//
				throw new ComponentRequestConflictException(this, request);

			if (thisFilter != null && thatFilter != null)
				mergedFilter = thisFilter.addFilterWithOr(thatFilter);
		} else
			mergedFilter = thisFilter;

		// Never allow an optional request to qualify one that is not
		// optional. The opposite is OK though.
		//
		if (thisOptional) {
			if (!thatOptional) {
				if (cmp == -1 || cmp == 2)
					// Qualified by this
					throw new ComponentRequestConflictException(this, request);

				return request;
			}
		} else if (thatOptional) {
			if (cmp > 0)
				// Qualified by that
				throw new ComponentRequestConflictException(this, request);
			return this;
		}

		if (Trivial.equalsAllowNull(mergedVD, thisVD) && Trivial.equalsAllowNull(mergedCType, thisCType)
				&& Trivial.equalsAllowNull(thisFilter, mergedFilter))
			return this;

		if (Trivial.equalsAllowNull(mergedVD, thatVD) && Trivial.equalsAllowNull(mergedCType, thatCType)
				&& Trivial.equalsAllowNull(thatFilter, mergedFilter))
			return request;

		ComponentRequestImpl result = new ComponentRequestImpl();
		result.setId(id);
		result.setType(mergedCType);
		result.setRange(mergedVD);
		result.setFilter(mergedFilter);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setFilter(Filter newFilter) {
		Filter oldFilter = filter;
		filter = newFilter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.COMPONENT_REQUEST__FILTER, oldFilter, filter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setRange(VersionRange newRange) {
		VersionRange oldRange = range;
		range = newRange;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.COMPONENT_REQUEST__RANGE, oldRange, range));
	}

	@Override
	public ComponentName toPureComponentName() {
		ComponentName cn = new ComponentNameImpl();
		cn.setId(getId());
		cn.setType(getType());
		return cn;
	}

	@Override
	public void toString(StringBuilder bld) {
		super.toString(bld);
		bld.append('/');
		if (getRange() != null) {
			bld.append(VersionHelper.getHumanReadable(getRange()));
		}
		if (getFilter() != null)
			bld.append(getFilter());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
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
		return CommonPackage.Literals.COMPONENT_REQUEST;
	}

} // ComponentRequestImpl

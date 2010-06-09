/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.Map;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.osgi.framework.InvalidSyntaxException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * A ComponentRequest is part of a requirement. All referenced components must
 * be available in a workspace for a requirment to be fulfilled. A component can
 * be further qualified using target references in cases when only a part of the
 * component is needed. The ComponentRequest uses a singleton pattern and is
 * optimized for use as key in a Map or Set.
 * 
 * @author thhal
 */
public class ComponentRequest extends ComponentName implements IComponentRequest {
	@SuppressWarnings("hiding")
	public static final String TAG = "component"; //$NON-NLS-1$

	static public final String ATTR_VERSION_DESIGNATOR = "versionDesignator"; //$NON-NLS-1$

	static public final String ATTR_VERSION_TYPE = "versionType"; //$NON-NLS-1$

	public static final String ATTR_FILTER = "filter"; //$NON-NLS-1$

	private final VersionRange versionRange;

	private final Filter filter;

	public static final Filter P2_OPTIONAL_FILTER;

	public static final String FILTER_ECLIPSE_P2_OPTIONAL = "(!(eclipse.p2.optional=false))"; //$NON-NLS-1$

	public static final String FILTER_SOURCE_BUNDLE = "(buckminster.download.source=true)"; //$NON-NLS-1$

	public static final String FILTER_OPTIONAL_SOURCE_BUNDLE = "(&" + ComponentRequest.FILTER_ECLIPSE_P2_OPTIONAL + FILTER_SOURCE_BUNDLE + ')'; //$NON-NLS-1$

	static {
		try {
			P2_OPTIONAL_FILTER = FilterFactory.newInstance(ComponentRequest.FILTER_ECLIPSE_P2_OPTIONAL);
		} catch (InvalidSyntaxException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public ComponentRequest(ComponentRequestBuilder bld) {
		super(bld.getName(), bld.getComponentTypeID());
		VersionRange vr = bld.getVersionRange();
		if (vr != null && vr.equals(VersionRange.emptyRange))
			vr = null;
		versionRange = vr;
		filter = bld.getFilter();
	}

	public ComponentRequest(String name, String componentType, String versionRangeStr, String versionTypeId) throws CoreException {
		this(name, componentType, versionRangeStr, versionTypeId, null);
	}

	public ComponentRequest(String name, String componentType, String versionRangeStr, String versionTypeId, Filter filter) throws CoreException {
		this(name, componentType, VersionHelper.createRange(versionTypeId, versionRangeStr), filter);
	}

	public ComponentRequest(String name, String componentType, VersionRange versionRange) {
		this(name, componentType, versionRange, null);
	}

	public ComponentRequest(String name, String componentType, VersionRange versionRange, Filter filter) {
		super(name, componentType);
		if (versionRange != null && versionRange.equals(VersionRange.emptyRange))
			versionRange = null;
		this.versionRange = versionRange;
		this.filter = filter;
	}

	public void appendViewName(StringBuilder bld) {
		bld.append(getName());
		String componentType = getComponentTypeID();
		if (componentType != null) {
			bld.append(':');
			bld.append(componentType);
		}
		if (filter != null)
			bld.append(filter);
	}

	@Override
	public boolean designates(IComponentIdentifier id) {
		return Trivial.equalsAllowNull(getName(), id.getName())
				&& (getComponentTypeID() == null || getComponentTypeID().equals(id.getComponentTypeID()))
				&& (versionRange == null || versionRange.isIncluded(id.getVersion()));
	}

	/**
	 * Returns true if this component reference is equal to obj with respect to
	 * name, versionSelector, and match rule.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		return super.equals(o) && Trivial.equalsAllowNull(versionRange, ((ComponentRequest) o).versionRange)
				&& Trivial.equalsAllowNull(filter, ((ComponentRequest) o).filter);
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	@Override
	public Filter getFilter() {
		return filter;
	}

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> p = super.getProperties();
		if (versionRange != null)
			p.put(KeyConstants.VERSION_DESIGNATOR, versionRange.toString());
		return p;
	}

	@Override
	public VersionRange getVersionRange() {
		return versionRange;
	}

	public String getViewName() {
		StringBuilder bld = new StringBuilder();
		appendViewName(bld);
		return bld.toString();
	}

	/**
	 * Returns the hashCode for this component request.
	 */
	@Override
	public int hashCode() {
		int hash = super.hashCode();
		hash = 31 * hash + (versionRange == null ? 0 : versionRange.hashCode());
		return 31 * hash + (filter == null ? 0 : filter.hashCode());
	}

	public boolean isEnabled(Map<String, ? extends Object> properties) {
		return filter == null || filter.matchCase(properties);
	}

	public boolean isOptional() {
		return filter != null && filter.toString().contains(FILTER_ECLIPSE_P2_OPTIONAL);
	}

	public boolean isSynthetic() {
		return filter != null && filter.toString().contains(FILTER_ECLIPSE_P2_OPTIONAL);
	}

	public ComponentRequest mergeDesignator(ComponentRequest that) throws CoreException {
		if (!Trivial.equalsAllowNull(getName(), that.getName()))
			throw new ComponentRequestConflictException(this, that);

		int cmp = 0;
		final VersionRange thisVD = getVersionRange();
		final VersionRange thatVD = that.getVersionRange();
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
					throw new ComponentRequestConflictException(this, that);

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

		final String thisCType = getComponentTypeID();
		final String thatCType = that.getComponentTypeID();
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
					throw new ComponentRequestConflictException(this, that);
			} else {
				if (cmp == 0)
					cmp = -1;
			}
			mergedCType = thisCType;
		}

		final Filter thisFilter = getFilter();
		final Filter thatFilter = that.getFilter();
		boolean thisOptional = isOptional();
		boolean thatOptional = that.isOptional();
		Filter mergedFilter = null;
		if (!Trivial.equalsAllowNull(thisFilter, thatFilter)) {
			if (thisOptional != thatOptional)
				// Filters can only be merged if both are required or
				// both are optional
				//
				throw new ComponentRequestConflictException(this, that);

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
					throw new ComponentRequestConflictException(this, that);

				return that;
			}
		} else if (thatOptional) {
			if (cmp > 0)
				// Qualified by that
				throw new ComponentRequestConflictException(this, that);
			return this;
		}

		if (Trivial.equalsAllowNull(mergedVD, thisVD) && Trivial.equalsAllowNull(mergedCType, thisCType)
				&& Trivial.equalsAllowNull(thisFilter, mergedFilter))
			return this;

		if (Trivial.equalsAllowNull(mergedVD, thatVD) && Trivial.equalsAllowNull(mergedCType, thatCType)
				&& Trivial.equalsAllowNull(thatFilter, mergedFilter))
			return that;

		return new ComponentRequest(getName(), mergedCType, mergedVD, mergedFilter);
	}

	@Override
	public ComponentName toPureComponentName() {
		return new ComponentName(this);
	}

	@Override
	public void toString(StringBuilder bld) {
		super.toString(bld);
		if (versionRange != null) {
			bld.append('/');
			bld.append(VersionHelper.getHumanReadable(versionRange));
		}
		if (filter != null)
			bld.append(filter);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) {
		super.addAttributes(attrs);
		if (versionRange != null)
			Utils.addAttribute(attrs, ATTR_VERSION_DESIGNATOR, versionRange.toString());
		if (filter != null)
			Utils.addAttribute(attrs, ATTR_FILTER, filter.toString());
	}
}

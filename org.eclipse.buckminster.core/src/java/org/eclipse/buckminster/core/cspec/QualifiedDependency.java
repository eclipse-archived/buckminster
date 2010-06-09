/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.query.IAdvisorNode;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.VersionRange;

public class QualifiedDependency {
	private final ComponentRequest request;

	private final Set<String> attributes;

	public QualifiedDependency(ComponentRequest request, Collection<String> attributes) {
		this.request = request;
		this.attributes = Utils.createUnmodifiableSet(attributes);
	}

	public QualifiedDependency applyAdvice(IAdvisorNode advice) {
		if (advice == null)
			return this;

		boolean change = false;
		ComponentRequest rq = request;
		VersionRange dsg = advice.getVersionOverride();
		if (dsg != null) {
			change = true;
			rq = new ComponentRequest(rq.getName(), rq.getComponentTypeID(), dsg);
		}

		Collection<String> attrs = advice.getAttributes();
		if (attrs.size() > 0) {
			change = true;
			if (advice.isPrune() && attributes.size() > 0 && !attributes.containsAll(attrs)) {
				HashSet<String> pruned = new HashSet<String>();
				for (String attrName : attrs)
					if (attributes.contains(attrName))
						pruned.add(attrName);

				if (pruned.size() == 0)
					//
					// We don't want anything from this cspec
					//
					return null;

				attrs = pruned;
			}
		} else
			attrs = attributes;

		return change ? new QualifiedDependency(rq, attrs) : this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof QualifiedDependency))
			return false;

		QualifiedDependency that = (QualifiedDependency) o;
		return request.equals(that.request) && attributes.equals(that.attributes);
	}

	public final Set<String> getAttributeNames() {
		return attributes;
	}

	public final ComponentRequest getRequest() {
		return request;
	}

	public boolean hasAllAttributes(Collection<String> attrs) {
		return attributes.containsAll(attrs);
	}

	public boolean hasAllAttributes(String[] attrs) {
		return hasAllAttributes(Arrays.asList(attrs));
	}

	@Override
	public int hashCode() {
		return request.hashCode() * 31 + attributes.hashCode();
	}

	/**
	 * Merges the version designator and the attributes of the new dependency
	 * with the current one. The method will return this instance if the merge
	 * is a no-op.
	 * 
	 * @param newQDep
	 *            the new qualified depenency
	 * @return This instance or a new instance if modifications where necessary.
	 * @throws CoreException
	 *             if the qualification is in conflict with the previously
	 *             defined dependency with respect to its version designator
	 */
	public QualifiedDependency mergeDependency(QualifiedDependency newQDep) throws CoreException {
		Set<String> attrs = newQDep.getAttributeNames();
		ComponentRequest newRequest = request.mergeDesignator(newQDep.getRequest());
		if (newRequest == request && hasAllAttributes(attrs))
			return this;

		Set<String> allAttrs;
		if (attributes.size() == 0)
			allAttrs = attrs;
		else if (attrs.size() == 0)
			allAttrs = attributes;
		else {
			allAttrs = new HashSet<String>(attributes);
			allAttrs.addAll(attrs);
		}
		return new QualifiedDependency(newRequest, allAttrs);
	}
}

/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter.impl;

import java.util.Map;

class PresentFilterImpl extends FilterImpl
{
	PresentFilterImpl(String attr)
	{
		super(FilterImpl.PRESENT, attr);
	}

	public int compareTo(FilterImpl o)
	{
		return internalCompareTo(o);
	}

	@Override
	boolean match0(Map<String, ? extends Object> properties)
	{
		return (properties == null)
				? false
				: properties.get(getAttr()) != null;
	}

	@Override
	void toString(StringBuilder sb)
	{
		sb.append('(');
		sb.append(getAttr());
		sb.append("=*)"); //$NON-NLS-1$
	}
}

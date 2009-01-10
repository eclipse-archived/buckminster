/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter;

import java.util.Map;

/**
 * This interface adds some introspection capabilities to the standard OSGi {@link org.osgi.framework.Filter Filter}
 * together with match methods that accepts the {@link Map} interface.
 * 
 * @author Thomas Hallgren
 */
public interface Filter extends org.osgi.framework.Filter
{
	/**
	 * Add the attributes that are interrogated by this filter. For each added attribute, also add the list of values
	 * that the attribute is compared to.
	 * 
	 * @param propertyChoices
	 */
	void addConsultedAttributes(Map<String, String[]> propertyChoices);

	/**
	 * Filter using the <code>properties</code> keys and values. The keys are case insensitively matched with the
	 * filter.
	 * 
	 * @param properties
	 *            The properties whose keys and values are used in the match.
	 * @return <code>true</code> if the properties match this filter; <code>false</code> otherwise.
	 * @throws IllegalArgumentException
	 *             If the properties contains case variants of the same key name.
	 */
	boolean match(Map<String, ? extends Object> properties);

	/**
	 * Filter using the <code>properties</code> keys and values.
	 * 
	 * @param properties
	 *            The properties whose keys and values are used in the match.
	 * @return <code>true</code> if the properties match this filter; <code>false</code> otherwise.
	 */
	boolean matchCase(Map<String, ? extends Object> properties);
}

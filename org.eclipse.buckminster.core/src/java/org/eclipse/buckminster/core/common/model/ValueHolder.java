/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.model;

import java.util.Map;

/**
 * Abstract class for holder of values. The holder will produce either exactly
 * one value (holders such as {@link Format}, {@link Replace}, or
 * {@link PropertyRef}) or it may produce multiple values (currently true only
 * for {@link Split}.<br/> If the {@link #getValue(Map<String,String>}} method is
 * called on a holder that produces multiple values and if the result is exactly
 * one value, that value is returned. If the result is zero values, the empty
 * string is returned. If the result is more then one value, the result of
 * concatenating those values is returned.<br/> Calling
 * {@link #getValues(Map<String,String>)} on a holder that produces a single value will
 * result in a one element array.
 * 
 * @author Thomas Hallgren
 */
public abstract class ValueHolder extends AbstractSaxableElement
{
	public static final String NO_VALUE = "";

	private boolean m_mutable;

	protected ValueHolder()
	{
		m_mutable = false;
	}

	/**
	 * Returns the resolved value of this holder using <code>properties</code>
	 * as the scope.
	 * 
	 * @param properties
	 *            The scope used when resolving the value.
	 * @return A string representing the resolved value.
	 * @throws CircularExpansionException
	 *             if the <code>recursionGuard</code> reaches its threshold.
	 */
	public final String getValue(Map<String, String> properties)
	{
		return this.checkedGetValue(properties, 0);
	}

	/**
	 * Returns resolved value array of this holder using <code>properties</code>
	 * as the scope.
	 * 
	 * @param properties
	 *            The scope used when resolving the values.
	 * @return A string array representing the resolved values.
	 * @throws CircularExpansionException
	 *             if the <code>recursionGuard</code> reaches its threshold.
	 */
	public final String[] getValues(Map<String, String> properties)
	{
		return this.checkedGetValues(properties, 0);
	}

	@Override
	public boolean equals(Object o)
	{
		return o == this || (o != null && o.getClass() == this.getClass() && m_mutable == ((ValueHolder)o).m_mutable);
	}

	@Override
	public int hashCode()
	{
		return m_mutable ? 17 : 61;
	}

	/**
	 * This method will return <code>false</code> for all holders that
	 * produces exactly one value and <code>true</code> if the holder
	 * may produce zero or many values.
	 * @return <code>true</code> if the producer may produce zero or many values.
	 */
	public boolean isMultiValueProducer()
	{
		return false;
	}

	/**
	 * Returns <code>true</code> if this value is considered mutable
	 * @return <code>true</code> if the value is mutable
	 */
	public boolean isMutable()
	{
		return m_mutable;
	}

	/**
	 * Set the mutable status for the contained value
	 */
	public void setMutable(boolean flag)
	{
		m_mutable = flag;
	}

	/**
	 * Returns the resolved value of this holder using <code>properties</code>
	 * as the scope.
	 * 
	 * @param properties
	 *            The scope used when resolving the value.
	 * @param recursionGuard
	 *            A guard that is increased for each recursive expansion that is
	 *            made.
	 * @return A string representing the resolved value.
	 * @throws CircularExpansionException
	 *             if the <code>recursionGuard</code> reaches its threshold.
	 */
	protected abstract String checkedGetValue(Map<String, String> properties, int recursionGuard);

	/**
	 * Returns resolved value array of this holder using <code>properties</code>
	 * as the scope.
	 * 
	 * @param properties
	 *            The scope used when resolving the values
	 * @param recursionGuard
	 *            A guard that is increased for each recursive expansion that is
	 *            made.
	 * @return A string array representing the resolved values
	 * @throws CircularExpansionException
	 *             if the <code>recursionGuard</code> reaches its threshold.
	 */
	protected String[] checkedGetValues(Map<String, String> properties, int recursionGuard)
	{
		return new String[]{this.checkedGetValue(properties, recursionGuard)};
	}
}


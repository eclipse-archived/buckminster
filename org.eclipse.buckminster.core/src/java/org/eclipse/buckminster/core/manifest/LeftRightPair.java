/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.manifest;

public abstract class LeftRightPair<T>
{
	private final T m_left;

	private final T m_right;

	public LeftRightPair(T left, T right)
	{
		m_left = left;
		m_right = right;
	}
	
	public T getLeft()
	{
		return m_left;
	}

	public T getRight()
	{
		return m_right;
	}

	@Override
	public String toString()
	{
		return new StringBuilder(m_left.toString()).append(" <=> ").append(m_right.toString()).toString();
	}
}

/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.sax;

/**
 * A key that uniqely identifies a handler in the handler cache.
 * @author Thomas Hallgren
 */
class HandlerKey implements Cloneable
{
	private String m_uri;

	private String m_localName;

	private String m_typeName;

	private int m_hash;

	void init(String uri, String localName, String typeName)
	{
		int hash = (localName == null) ? 1 : 31 + localName.hashCode();
		if(uri != null)
			hash = 31 * hash + uri.hashCode();
		if(typeName != null)
			hash = 31 * hash + typeName.hashCode();

		m_uri = uri;
		m_localName = localName;
		m_typeName = typeName;
		m_hash = hash;
	}

	@Override
	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			return null;
		}
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;

		if(!(obj instanceof HandlerKey))
			return false;

		HandlerKey other = (HandlerKey)obj;
		return nullEquals(m_uri, other.m_uri)
			&& nullEquals(m_typeName, other.m_typeName)
			&& nullEquals(m_localName, other.m_localName);
	}

	@Override
	public int hashCode()
	{
		return m_hash;
	}

	static boolean nullEquals(String a, String b)
	{
		return (a == null)
			? (b == null)
			: (b != null) && a.equals(b);
	}
}
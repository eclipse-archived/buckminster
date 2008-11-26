/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.sax;

import java.util.UUID;

/**
 * Base class for immutable UUID keyed classes. The contract specifies that such a class may only declare immutable
 * (i.e. final) properties since the id of the instance is calculated from the XML serialization.
 * 
 * @author Thomas Hallgren
 */
public abstract class UUIDKeyed extends AbstractSaxableElement implements ISaxable
{
	private transient UUID m_id;

	private transient byte[] m_image;

	@Override
	public final boolean equals(Object o)
	{
		return o == this || ((o instanceof UUIDKeyed && ((UUIDKeyed)o).getId().equals(this.getId())));
	}

	public synchronized final UUID getId()
	{
		if(m_id == null)
		{
			m_image = Utils.getImage(this);
			m_id = UUID.nameUUIDFromBytes(m_image);
		}
		return m_id;
	}

	public synchronized final byte[] getImage()
	{
		if(m_image == null)
		{
			m_image = Utils.getImage(this);
			if(m_id == null)
				m_id = UUID.nameUUIDFromBytes(m_image);
		}
		return m_image;
	}

	@Override
	public final int hashCode()
	{
		return this.getId().hashCode();
	}

	public final synchronized void setId(UUID id)
	{
		m_id = id;
	}
}

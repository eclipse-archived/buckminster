/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.distroprovider;


/**
 * @author Karel Brezina
 *
 */
public class DistroVariant extends PropertySet
{
	public static int INVALID = -1000;
	
	public static int BROKEN = -1;
	
	public static int INCOMPATIBLE = 0;
	
	public static int COMPATIBLE = 1;
	
	private Long m_distroId;
	
	private int m_status = INVALID;
	
	public DistroVariant()
	{
	}

	public Long getDistroId()
	{
		return m_distroId;
	}

	public void setDistroId(Long distroId)
	{
		m_distroId = distroId;
	}

	public int getStatus()
	{
		return m_status;
	}

	public void setStatus(int status)
	{
		if(status != BROKEN && status != INCOMPATIBLE && status != COMPATIBLE)
			throw new IllegalArgumentException("Illegal status ID " + status);

		m_status = status;
	}
}

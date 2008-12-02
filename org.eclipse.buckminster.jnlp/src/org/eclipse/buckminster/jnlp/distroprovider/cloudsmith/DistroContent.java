/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.distroprovider.cloudsmith;

/**
 * @author Karel Brezina
 * 
 */
public class DistroContent
{
	private byte[] m_bomContent;

	private byte[] m_mspecContent;

	public byte[] getBomContent()
	{
		return m_bomContent;
	}

	public byte[] getMspecContent()
	{
		return m_mspecContent;
	}

	public void setBomContent(byte[] bomContent)
	{
		m_bomContent = bomContent;
	}

	public void setMspecContent(byte[] mspecContent)
	{
		m_mspecContent = mspecContent;
	}
}

/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.opml.builder;

import java.net.URI;
import java.util.Date;

import org.eclipse.buckminster.opml.model.Head;

/**
 * @author Thomas Hallgren
 * 
 */
public class HeadBuilder
{
	private Date m_dateCreated;

	private Date m_dateModified;

	private URI m_docs;

	private int[] m_expansionState;

	private String m_ownerEmail;

	private URI m_ownerId;

	private String m_ownerName;

	private String m_title;

	private int m_vertScrollState;

	private int m_windowBottom;

	private int m_windowLeft;

	private int m_windowRight;

	private int m_windowTop;

	public void clear()
	{
		m_dateCreated = null;
		m_docs = null;
		m_expansionState = new int[0];
		m_dateModified = null;
		m_ownerEmail = null;
		m_ownerId = null;
		m_ownerName = null;
		m_title = null;
		m_vertScrollState = 0;
		m_windowBottom = 0;
		m_windowLeft = 0;
		m_windowRight = 0;
		m_windowTop = 0;
	}

	public Date getDateCreated()
	{
		return m_dateCreated;
	}

	public Date getDateModified()
	{
		return m_dateModified;
	}

	public URI getDocs()
	{
		return m_docs;
	}

	public int[] getExpansionState()
	{
		return m_expansionState;
	}

	public String getOwnerEmail()
	{
		return m_ownerEmail;
	}

	public URI getOwnerId()
	{
		return m_ownerId;
	}

	public String getOwnerName()
	{
		return m_ownerName;
	}

	public String getTitle()
	{
		return m_title;
	}

	public int getVertScrollState()
	{
		return m_vertScrollState;
	}

	public int getWindowBottom()
	{
		return m_windowBottom;
	}

	public int getWindowLeft()
	{
		return m_windowLeft;
	}

	public int getWindowRight()
	{
		return m_windowRight;
	}

	public int getWindowTop()
	{
		return m_windowTop;
	}

	public void initFrom(Head head)
	{
		m_dateCreated = head.getDateCreated();
		m_docs = head.getDocs();
		m_expansionState = head.getExpansionState();
		m_dateModified = head.getDateModified();
		m_ownerEmail = head.getOwnerEmail();
		m_ownerId = head.getOwnerId();
		m_ownerName = head.getOwnerName();
		m_title = head.getTitle();
		m_vertScrollState = head.getVertScrollState();
		m_windowBottom = head.getWindowBottom();
		m_windowLeft = head.getWindowLeft();
		m_windowRight = head.getWindowRight();
		m_windowTop = head.getWindowTop();
	}

	public void setDateCreated(Date created)
	{
		m_dateCreated = created;
	}

	public void setDateModified(Date modified)
	{
		m_dateModified = modified;
	}

	public void setDocs(URI docs)
	{
		m_docs = docs;
	}

	public void setExpansionState(int[] expansionState)
	{
		m_expansionState = expansionState;
	}

	public void setOwnerEmail(String ownerEmail)
	{
		m_ownerEmail = ownerEmail;
	}

	public void setOwnerId(URI ownerId)
	{
		m_ownerId = ownerId;
	}

	public void setOwnerName(String ownerName)
	{
		m_ownerName = ownerName;
	}

	public void setTitle(String title)
	{
		m_title = title;
	}

	public void setVertScrollState(int vertScrollState)
	{
		m_vertScrollState = vertScrollState;
	}

	public void setWindowBottom(int windowBottom)
	{
		m_windowBottom = windowBottom;
	}

	public void setWindowLeft(int windowLeft)
	{
		m_windowLeft = windowLeft;
	}

	public void setWindowRight(int windowRight)
	{
		m_windowRight = windowRight;
	}

	public void setWindowTop(int windowTop)
	{
		m_windowTop = windowTop;
	}
}

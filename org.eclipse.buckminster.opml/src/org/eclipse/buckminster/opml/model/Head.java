/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.opml.model;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.eclipse.buckminster.opml.IHead;
import org.eclipse.buckminster.opml.builder.HeadBuilder;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 *
 */
public class Head extends AbstractSaxableElement implements IHead
{
	public static final String ELEM_DATE_CREATED = "dateCreated";
	public static final String ELEM_DATE_MODIFIED = "dateModified";

	public static final String ELEM_DOCS = "docs";
	public static final String ELEM_EXPANSION_STATE = "expansionState"; 
	public static final String ELEM_OWNER_EMAIL = "ownerEmail";
	public static final String ELEM_OWNER_ID = "ownerId";
	public static final String ELEM_OWNER_NAME = "ownerName";
	public static final String ELEM_TITLE = "title";
	public static final String ELEM_VERT_SCROLL_STATE = "vertScrollState";
	public static final String ELEM_WINDOW_BOTTOM = "windowBottom";
	public static final String ELEM_WINDOW_LEFT = "windowLeft";
	public static final String ELEM_WINDOW_RIGHT = "windowRight";
	public static final String ELEM_WINDOW_TOP = "windowTop";
	public static final DateFormat RFC_822_4DY_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
	public static final DateFormat RFC_822_FORMAT = new SimpleDateFormat("EEE, d MMM yy HH:mm:ss Z", Locale.US);
	public static final String TAG = "head";

	private final Date m_dateCreated;
	private final Date m_dateModified;
	private final URI m_docs;
	private final int[] m_expansionState;
	private final String m_ownerEmail;
	private final URI m_ownerId;
	private final String m_ownerName;
	private final String m_title;
	private final int m_vertScrollState;
	private final int m_windowBottom;
	private final int m_windowLeft;
	private final int m_windowRight;
	private final int m_windowTop;

	public Head(HeadBuilder head)
	{
		m_dateCreated = head.getDateCreated();
		m_docs = head.getDocs();
		m_expansionState = head.getExpansionState();
		m_dateModified = head.getDateModified();
		m_ownerEmail = head.getOwnerEmail();
		m_ownerName = head.getOwnerName();
		m_ownerId = head.getOwnerId();
		m_title = head.getTitle();
		m_vertScrollState = head.getVertScrollState();
		m_windowBottom = head.getWindowBottom();
		m_windowLeft = head.getWindowLeft();
		m_windowRight = head.getWindowRight();
		m_windowTop = head.getWindowTop();		
	}

	public Date getDateCreated()
	{
		return m_dateCreated;
	}

	public Date getDateModified()
	{
		return m_dateModified;
	}

	public String getDefaultTag()
	{
		return TAG;
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

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		// Order is significant here since the XML-schema defines a sequence
		//
		emitString(handler, namespace, prefix, ELEM_TITLE, m_title);
		emitDate(handler, namespace, prefix, ELEM_DATE_CREATED, m_dateCreated);
		emitDate(handler, namespace, prefix, ELEM_DATE_MODIFIED, m_dateModified);
		emitString(handler, namespace, prefix, ELEM_OWNER_NAME, m_ownerName);
		emitString(handler, namespace, prefix, ELEM_OWNER_EMAIL, m_ownerEmail);
		emitString(handler, namespace, prefix, ELEM_OWNER_ID, m_ownerId);
		emitString(handler, namespace, prefix, ELEM_DOCS, m_docs);
		emitIntList(handler, namespace, prefix, ELEM_EXPANSION_STATE, m_expansionState);
		emitInt(handler, namespace, prefix, ELEM_VERT_SCROLL_STATE, m_vertScrollState);
		emitInt(handler, namespace, prefix, ELEM_WINDOW_TOP, m_windowTop);
		emitInt(handler, namespace, prefix, ELEM_WINDOW_LEFT, m_windowLeft);
		emitInt(handler, namespace, prefix, ELEM_WINDOW_BOTTOM, m_windowBottom);
		emitInt(handler, namespace, prefix, ELEM_WINDOW_RIGHT, m_windowRight);
	}

	private void emitDate(ContentHandler handler, String namespace, String prefix, String elemName, Date date) throws SAXException
	{
		if(date != null)
			emitString(handler, namespace, prefix, elemName, RFC_822_4DY_FORMAT.format(date));
	}

	private void emitInt(ContentHandler handler, String namespace, String prefix, String elemName, int value) throws SAXException
	{
		if(value != 0)
			emitString(handler, namespace, prefix, elemName, Integer.toString(value));
	}

	private void emitIntList(ContentHandler handler, String namespace, String prefix, String elemName, int[] value) throws SAXException
	{
		if(value == null)
			return;
		int top = value.length;
		if(top > 0)
		{
			StringBuilder bld = new StringBuilder(top * 3);
			bld.append(value[0]);
			for(int idx = 1; idx < top; ++idx)
			{
				bld.append(',');
				bld.append(value[idx]);
			}
			emitString(handler, namespace, prefix, elemName, bld.toString());
		}
	}

	private void emitString(ContentHandler handler, String namespace, String prefix, String elemName, Object value) throws SAXException
	{
		if(value == null)
			return;
		String qName = Utils.makeQualifiedName(prefix, elemName);
		handler.startElement(namespace, elemName, qName, EMPTY_ATTRIBUTES);
		char[] chars = value.toString().toCharArray();
		if(chars.length > 0)
			handler.characters(chars, 0, chars.length);
		handler.endElement(namespace, elemName, qName);
	}
}

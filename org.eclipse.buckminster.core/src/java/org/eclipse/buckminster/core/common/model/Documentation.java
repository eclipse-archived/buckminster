/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.common.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.common.parser.DocumentationParser;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class Documentation extends FlowWithAttributes implements ISaxable
{
	public static final String BM_TAG = "documentation";

	public static final String XHTML_TAG = "div";

	private static final String LEAD_IN =
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<div xmlns=\"http://www.w3.org/1999/xhtml\">";

	private static final String TAIL =
		"</div>";

	/**
	 * Parse a text snipped into a Documentation object. This method will first wrap
	 * the snipped in a XHTML &lt;div&gt; tag and then parse it using the {@link DocumentationParser}
	 * @param doc The text to be parsed.
	 * @return A documentation object
	 * @throws CoreException
	 */
	public static Documentation parse(String doc) throws CoreException
	{
		try
		{
			ByteArrayInputStream input = new ByteArrayInputStream((LEAD_IN + doc + TAIL).getBytes("UTF-8"));
			IParser<Documentation> parser = new DocumentationParser();
			return parser.parse("generated", input);
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public Documentation(ISaxableElement[] children, String[] keyNamePairs)
	{
		super(BM_TAG, children, keyNamePairs);
	}

	/**
	 * Merge a documentation with another documentation. The new documentation found in <code>doc</code> is appended
	 * to the end of this documentation.
	 * @param doc
	 * @return The merged documentation.
	 */
	public Documentation merge(Documentation doc)
	{
		if(doc == null)
			return this;

		ISaxableElement[] thisChildren = getChildren();
		ISaxableElement[] thatChildren = doc.getChildren();
		ISaxableElement[] allChildren = new ISaxableElement[thisChildren.length + thatChildren.length];
		System.arraycopy(thisChildren, 0, allChildren, 0, thisChildren.length);
		System.arraycopy(thatChildren, 0, allChildren, thisChildren.length, thatChildren.length);

		// Merge the element attributes if necessary, let attributes from doc have
		// precedence in case of conflic
		//
		String[] thisAttrs = getKeyNamePairs();
		String[] thatAttrs = doc.getKeyNamePairs();
		String[] allAttrs;
		if(thisAttrs.length == 0)
			allAttrs = thatAttrs;
		else if(thatAttrs.length == 0)
			allAttrs = thisAttrs;
		else
		{
			HashMap<String, String> attrMap = new HashMap<String, String>();
			for(int idx = 0; idx < thisAttrs.length; idx += 2)
				attrMap.put(thisAttrs[idx], thisAttrs[idx + 1]);
			for(int idx = 0; idx < thatAttrs.length; idx += 2)
				attrMap.put(thatAttrs[idx], thatAttrs[idx + 1]);
			ArrayList<String> attrList = new ArrayList<String>();
			for(Map.Entry<String, String> entry : attrMap.entrySet())
			{
				attrList.add(entry.getKey());
				attrList.add(entry.getValue());
			}
			allAttrs = attrList.toArray(new String[attrList.size()]);
		}
		return new Documentation(allChildren, allAttrs);
	}

	/**
	 * Emits the SAX events for a an XML document with the XHTML &lt;div&gt; tag as the root
	 * element.
	 * @param receiver The content handler to receive the SAX events
	 * @throws SAXException if something goes wrong during the event generation
	 */
	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		this.toSax(receiver, XMLConstants.XHTML_NS, null, XHTML_TAG);
		receiver.endDocument();
	}

	@Override
	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName)
	throws SAXException
	{
		receiver.startPrefixMapping("", XMLConstants.XHTML_NS);
		super.toSax(receiver, namespace, prefix, localName);
		receiver.endPrefixMapping("");
	}

	/**
	 * Returns the XHTML content of top element in the form of a String. The tag of the top element is
	 * not included.
	 */
	@Override
	public String toString()
	{
		try
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ContentHandler serializer = Utils.newSerializer(null, out, "UTF-8", -1, false);
			toSax(serializer);
			String xmlContent = new String(out.toByteArray(), "UTF-8");
			int idx = xmlContent.indexOf("<" + XHTML_TAG);
			if(idx < 0)
				return null;
			int top = xmlContent.length();
			for(idx += XHTML_TAG.length() + 1; idx < top; ++idx)
				if(xmlContent.charAt(idx) == '>')
					break;
			if(idx == top)
				return null;
			++idx;
			int lastIdx = xmlContent.lastIndexOf("</" + XHTML_TAG + ">");
			if(lastIdx < idx)
				return null;
			
			return xmlContent.substring(idx, lastIdx);
		}
		catch(SAXException e)
		{
			throw new RuntimeException(e);
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}
}

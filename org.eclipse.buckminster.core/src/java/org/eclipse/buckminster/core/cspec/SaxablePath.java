/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec;

import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class SaxablePath extends Path implements ISaxableElement
{
	public static final String ATTR_PATH = "path"; //$NON-NLS-1$

	public static final String TAG = "path"; //$NON-NLS-1$

	public static SaxablePath coerce(IPath path)
	{
		return path instanceof SaxablePath
				? (SaxablePath)path
				: new SaxablePath(path.toOSString());
	}

	public static SaxablePath fromPortableString(String pathString)
	{
		return new SaxablePath(Path.fromPortableString(pathString).toOSString());
	}

	private SaxablePath(String fullPath)
	{
		super(fullPath);
	}

	public SaxablePath(String device, String path)
	{
		super(device, path);
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public void toSax(ContentHandler handler, String namespace, String prefix, String localName) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ATTR_PATH, this.toPortableString());
		String qName = Utils.makeQualifiedName(prefix, localName);
		handler.startElement(namespace, localName, qName, attrs);
		handler.endElement(namespace, localName, qName);
	}
}

package org.eclipse.buckminster.opml;

import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.core.runtime.IAdaptable;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public interface IOPML extends ISaxable, IAdaptable
{

	IBody getBody();

	IHead getHead();

	String getVersion();

	void toSax(ContentHandler receiver) throws SAXException;

}

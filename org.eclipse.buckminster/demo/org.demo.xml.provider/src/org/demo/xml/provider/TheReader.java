package org.demo.xml.provider;

import java.io.IOException;
import java.io.Reader;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import se.tada.util.sax.StringElementHandler;
import se.tada.util.sax.TopHandler;
import se.tada.util.sax.Utils;

public class TheReader extends TopHandler
{
	private String m_world;
	private String m_text;
	private final String m_topElementName;

	public TheReader(String topElementName) throws SAXException
	{
		super(Utils.createXMLReader(false, false));
		m_topElementName = topElementName;

	}

	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException
	{
		if(m_topElementName.equals(qName))
		{
			m_world = this.getStringValue(attrs, "world");
			StringElementHandler seh = new StringElementHandler()
			{
				public void endElement(String suri, String slocalName, String sqName) throws SAXException
				{
					super.endElement(suri, slocalName, sqName);
					m_text = new String(this.getBuffer(), 0, this.getLengthAndReset());
				}
			};
			this.assignTopHandler(seh);
			this.pushHandler(seh, attrs);
		}
		else
			super.startElement(uri, localName, qName, attrs);
	}

	public String getWorld()
	{
		return m_world;
	}

	public String parseInput(Reader input) throws SAXException
	{
		m_text = null;
		try
		{
			InputSource source = new InputSource(input);
			this.getXMLReader().parse(source);
			return m_text;
		}
		catch(IOException e)
		{
			throw new SAXException(e);
		}
	}
}

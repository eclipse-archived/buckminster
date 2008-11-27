package org.eclipse.buckminster.core.rmap.parser;

import org.eclipse.buckminster.core.common.parser.FormatHandler;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class DigestHandler extends FormatHandler
{
	public static final String TAG = Provider.TAG_DIGEST;

	private String m_algorithm;

	public DigestHandler(AbstractHandler parent)
	{
		super(parent);
	}

	String getAlgorithm()
	{
		return m_algorithm;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		m_algorithm = getStringValue(attrs, Provider.ATTR_ALGORITHM);
	}
}

package org.eclipse.buckminster.core.common.parser;

import java.util.ArrayList;

import org.eclipse.buckminster.core.common.model.Flow;
import org.eclipse.buckminster.core.common.model.FlowWithAttributes;
import org.eclipse.buckminster.core.common.model.Text;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class FlowHandler extends ChildHandler implements ChildPoppedListener
{
	private static final ISaxableElement[] s_noChildren = new ISaxableElement[0];
	private final String m_localName;
	private String[] m_keyValuePairs;
	private ArrayList<ISaxableElement> m_elements;
	private StringBuilder m_text;

	protected FlowHandler(AbstractHandler parentHandler, String localName)
	{
		super(parentHandler);
		m_localName = localName;
	}

	@Override
	public void characters(char[] chars, int start, int length)
	throws SAXException
	{
		if(length == 0)
			return;

		if(m_text == null)
			m_text = new StringBuilder();
		m_text.append(chars, start, length);
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		addElement(((FlowHandler)child).createElement());
	}

	public Flow createElement()
	{
		addTextIfAny();
		ISaxableElement[] children = (m_elements == null)
			? s_noChildren
			: m_elements.toArray(new ISaxableElement[m_elements.size()]);

		return createFlowElement(m_localName, m_keyValuePairs, children);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		addTextIfAny();
		return new FlowHandler(this, localName);
	}

	@Override
	public void handleAttributes(Attributes attrs)
	{
		int numAttrs = attrs.getLength();
		if(m_elements != null)
			m_elements.clear();
		if(m_text != null)
			m_text.setLength(0);
		if(numAttrs > 0)
		{
			m_keyValuePairs = new String[numAttrs * 2];
			for(int idx = 0; idx < numAttrs; ++idx)
			{
				int kaIdx = idx * 2;
				m_keyValuePairs[kaIdx] = attrs.getLocalName(idx);
				m_keyValuePairs[kaIdx+1] = attrs.getValue(idx);
			}
		}
		else
			m_keyValuePairs = Trivial.EMPTY_STRING_ARRAY;
	}

	@Override
	public String getTAG()
	{
		return m_localName;
	}

	Flow createFlowElement(String localName, String[] keyValuePairs, ISaxableElement[] children)
	{
		return (keyValuePairs.length == 0)
			? new Flow(localName, children)
			: new FlowWithAttributes(localName, children, keyValuePairs);
	}

	private void addElement(ISaxableElement element)
	{
		if(m_elements == null)
			m_elements = new ArrayList<ISaxableElement>();
		m_elements.add(element);
	}

	private void addTextIfAny()
	{
		if(m_text == null)
			return;

		int textLen = m_text.length();
		if(textLen == 0)
			return;

		char[] buf = new char[textLen];
		m_text.getChars(0, textLen, buf, 0);
		addElement(new Text(buf));
		m_text.setLength(0);
	}
}

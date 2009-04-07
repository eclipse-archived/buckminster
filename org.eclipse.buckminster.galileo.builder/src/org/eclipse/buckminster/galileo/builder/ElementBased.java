package org.eclipse.buckminster.galileo.builder;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class ElementBased
{
	private static <T extends ElementBased> T createElementBased(Class<T> cls, Element element) throws CoreException
	{
		try
		{
			return cls.getConstructor(Element.class).newInstance(element);
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	private final Element m_element;

	protected ElementBased(Element element)
	{
		m_element = element;
	}

	protected <T extends ElementBased> List<T> createElementBasedList(Class<T> cls, String childName)
			throws CoreException
	{
		List<T> elemBasedList = new ArrayList<T>();
		for(Node node = m_element.getFirstChild(); node != null; node = node.getNextSibling())
			if(node instanceof Element && childName.equals(node.getNodeName()))
				elemBasedList.add(createElementBased(cls, (Element)node));
		return elemBasedList;
	}

	protected <T extends ElementBased> T createElementBasedSingleton(Class<T> cls, String childName)
			throws CoreException
	{
		for(Node node = m_element.getFirstChild(); node != null; node = node.getNextSibling())
			if(node instanceof Element && childName.equals(node.getNodeName()))
				return createElementBased(cls, (Element)node);
		return null;
	}

	protected String getAttribute(String attrName)
	{
		return Trivial.trim(m_element.getAttribute(attrName));
	}
}

/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.sax;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * This handler is the subclass of all handlers in the Tada SAX parser system
 * except the root handler. It defines a standard way to associate a handler
 * with an element using a static public string variable called <code>TAG</code>.
 * Handlers are cached and reused. The implementor of a
 * <code>ChildHandler</code> should implement the method
 * {@link #createHandler(String qName)} in order to create handlers for nested
 * elements and the method {@link #handleAttributes(Attributes)} to handle the
 * attributes for the element that the handler represents. A concrete subclass
 * of <code>ChildHandler</code> must also have a static String attribute
 * called <code>TAG</code>.
 * 
 * @author Thomas Hallgren
 */
public abstract class ChildHandler extends AbstractHandler
{
	private AbstractHandler m_parentHandler;

	protected ChildHandler(AbstractHandler parentHandler)
	{
		m_parentHandler = parentHandler;
	}

	/**
	 * Push a handler that corresponds to the <code>qName</code>, cached or
	 * created, on the handler stack so that it becomes the current
	 * <code>ContentHandler</code> of the <code>TopHandler</code>.
	 * 
	 * @param uri
	 *            The Namespace URI.
	 * @param localName
	 *            The local name.
	 * @param qName
	 *            The qualified name.
	 * @param attributes
	 *            The attributes that will be sent to the
	 *            {@link #handleAttributes(Attributes)} method of the new
	 *            handler.
	 * @exception org.xml.sax.SAXException
	 *                Any SAX exception, possibly wrapping another exception.
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException
	{
		if(localName.length() == 0)
			localName = qName;
		if(uri.length() == 0)
			uri = null;
		this.pushHandler(this.createHandler(uri, localName, attrs), attrs);
	}

	/**
	 * Pop this handler from the <code>TopHandler</code> handler stack.
	 * 
	 * @param uri
	 *            The Namespace URI, ignored.
	 * @param localName
	 *            The local name, ignored.
	 * @param qName
	 *            The qualified name, ignored.
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		this.popHandler();
		ContentHandler parent = this.getParentHandler();
		if(parent instanceof ChildPoppedListener)
			((ChildPoppedListener)parent).childPopped(this);
	}

	public final AbstractHandler getParentHandler()
	{
		return m_parentHandler;
	}

	@Override
	public String getPrefixMapping(String prefix)
	{
		String uri = super.getPrefixMapping(prefix);
		return uri == null ? this.getParentHandler().getPrefixMapping(prefix) : uri;
	}

	/**
	 * This method must be overridden by handlers that deals with nested
	 * elements.
	 * 
	 * @param uri
	 *            The namespace of the element for which a handler should be
	 *            created or <code>null</code> if namespace are not used.
	 * @param localName The unqualified name of the element.
	 * @param attrs The element attributes. May be <code>null</code>.
	 * @return Derived methods returns a new handler that corresponds to
	 *         <code>qName</code>.
	 * @throws UnrecognizedElementException
	 *             unless overridden.
	 */
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		throw new UnrecognizedElementException(this.getTAG(), localName, this.getDocumentLocator());
	}

	/**
	 * This method should be overridden by handlers that deals with element
	 * attributes. By default, this method does nothing.
	 * 
	 * @param attributes
	 *            The attributes as passed to the
	 *            {@link #startElement(String, String, String, Attributes)}
	 *            method.
	 * @throws SAXException
	 */
	public void handleAttributes(Attributes attributes) throws SAXException
	{
	}

	/**
	 * Returns the document locator.
	 * 
	 * @return the document locator.
	 */
	@Override
	protected final Locator getDocumentLocator()
	{
		return m_parentHandler.getDocumentLocator();
	}

	/**
	 * Returns the top element handler.
	 * 
	 * @return the top element handler.
	 */
	@Override
	public final TopHandler getTopHandler()
	{
		return m_parentHandler.getTopHandler();
	}

	/**
	 * Pop the current handler from the <code>TopHandler</code> handler stack.
	 */
	protected final void popHandler()
	{
		this.getTopHandler().popHandler();
	}

	/**
	 * Push a handler on the <code>TopHandler</code> handler stack so that it
	 * becomes the active <code>ContentHandler</code>.
	 * 
	 * @param handler
	 *            The handler to push.
	 * @param attrs
	 *            Attributes to send to the
	 *            {@link #handleAttributes(Attributes)} method of the new
	 *            handler.
	 * @throws SAXException
	 */
	protected final void pushHandler(ChildHandler handler, Attributes attrs) throws SAXException
	{
		this.getTopHandler().pushHandler(handler, attrs);
	}

	/**
	 * Return the value of the public static String TAG field declared for the
	 * class of the receiver of the call.
	 */
	@Override
	public String getTAG()
	{
		try
		{
			Field idField = this.getClass().getDeclaredField("TAG");
			int mods = idField.getModifiers();
			if(!idField.isAccessible())
				idField.setAccessible(true);
			return (Modifier.isStatic(mods)) ? (String)idField.get(null) : null;
		}
		catch(NoSuchFieldException e)
		{
			return this.getClass() + "(lacks TAG field)";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return e.getMessage();
		}
	}
}

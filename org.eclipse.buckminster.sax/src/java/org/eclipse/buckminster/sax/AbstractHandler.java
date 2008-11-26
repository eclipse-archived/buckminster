/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.sax;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This handler is the subclass of all handlers in the Tada SAX parser system. It provides utility methods to extract
 * attribute values.
 * 
 * @author Thomas Hallgren
 */
public abstract class AbstractHandler extends DefaultHandler
{
	/**
	 * Returns an boolean value. This method returns the <code>defaultValue</code> if the attribute does not exist or if
	 * it consists entirely of whitespace.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @param defaultValue
	 *            The value to be returned if the attribute is not set.
	 * @return The value of the attribute or <code>defaultValue</code>.
	 */
	public static boolean getOptionalBooleanValue(Attributes attrs, String qName, boolean defaultValue)
	{
		String value = getOptionalStringValue(attrs, qName);
		if(value == null)
			return defaultValue;
		if("1".equals(value))
			return true;
		if("0".equals(value))
			return false;
		return "true".equalsIgnoreCase(value);
	}

	/**
	 * Returns an long value. This method returns the <code>defaultValue</code> if the attribute does not exist or if it
	 * consists entirely of whitespace.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @param defaultValue
	 *            The value to be returned if the attribute is not set.
	 * @return The value of the attribute or <code>defaultValue</code>.
	 */
	public static final double getOptionalDoubleValue(Attributes attrs, String qName, double defaultValue)
	{
		String value = getOptionalStringValue(attrs, qName);
		return (value == null)
				? defaultValue
				: Double.parseDouble(value);
	}

	/**
	 * Returns an long value. This method returns the <code>defaultValue</code> if the attribute does not exist or if it
	 * consists entirely of whitespace.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @return The value of the attribute or <code>defaultValue</code>.
	 */
	public static final int getOptionalIntValue(Attributes attrs, String qName, int defaultValue)
	{
		String value = getOptionalStringValue(attrs, qName);
		return (value == null)
				? defaultValue
				: Integer.parseInt(value);
	}

	/**
	 * Returns an long value. This method returns the <code>defaultValue</code> if the attribute does not exist or if it
	 * consists entirely of whitespace.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @param defaultValue
	 *            The value to be returned if the attribute is not set.
	 * @return The value of the attribute or <code>defaultValue</code>.
	 */
	public static final long getOptionalLongValue(Attributes attrs, String qName, long defaultValue)
	{
		String value = getOptionalStringValue(attrs, qName);
		return (value == null)
				? defaultValue
				: Long.parseLong(value);
	}

	/**
	 * Returns an attribute that is trimmed from whitespace and with a length greater then zero. This method returns the
	 * <code>null</code> if the attribute does not exist or if it consists entirely of whitespace.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @return The value of the attribute or <code>null</code>.
	 */
	public static final String getOptionalStringValue(Attributes attrs, String qName)
	{
		String value = attrs.getValue(qName);
		if(value != null)
		{
			value = value.trim();
			if(value.length() == 0)
				value = null;
		}
		return value;
	}

	/**
	 * Returns an attribute that is trimmed from whitespace and with a length greater then zero. This method returns the
	 * <code>defaultValue</code> if the attribute does not exist or if it consists entirely of whitespace.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @param defaultValue
	 *            The value to be returned if the attribute is not set.
	 * @return The value of the attribute or <code>defaultValue</code>.
	 */
	public static final String getOptionalStringValue(Attributes attrs, String qName, String defaultValue)
	{
		String value = getOptionalStringValue(attrs, qName);
		if(value == null)
			value = defaultValue;
		return value;
	}

	private HashMap<String, String> m_prefixMappings;

	@Override
	public void endPrefixMapping(String prefix) throws SAXException
	{
		if(m_prefixMappings != null)
			m_prefixMappings.remove(prefix);
	}

	/**
	 * Returns an attribute that is compiled into a regular expression pattern. This method returns <code>null</code>
	 * when the attribute is <code>null</code> or an empty String.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @return The compiled pattern or <code>null</code>.
	 * @throws SAXParseException
	 *             when the attribute is not null or empty and cannot be compiled into a regular expression pattern.
	 */
	public Pattern getOptionalPatternValue(Attributes attrs, String qName) throws SAXParseException
	{
		String value = getOptionalStringValue(attrs, qName);
		if(value == null || value.length() == 0)
			return null;
		try
		{
			return Pattern.compile(value);
		}
		catch(PatternSyntaxException e)
		{
			throw new SAXParseException("The value of attribute " + qName + " is not a valid regular expression",
					getDocumentLocator(), e);
		}
	}

	/**
	 * Returns an attribute that is parsed into a URL. This method returns <code>null</code> when the attribute is
	 * <code>null</code> or an empty String.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @return The URL or <code>null</code>.
	 * @throws SAXParseException
	 *             when the attribute is not null or empty and cannot be parsed into a URL
	 */
	public URL getOptionalURLValue(Attributes attrs, String qName) throws SAXParseException
	{
		String value = getOptionalStringValue(attrs, qName);
		if(value == null || value.length() == 0)
			return null;
		try
		{
			return new URL(value);
		}
		catch(MalformedURLException e)
		{
			// Do a space check.
			//
			if(value.indexOf(' ') > 0)
			{
				try
				{
					return new URL(value.replaceAll("\\s", "%20"));
				}
				catch(MalformedURLException me1)
				{
				}
			}
			throw new SAXParseException("The value of attribute " + qName + " is not a valid URL",
					getDocumentLocator(), e);
		}
	}

	public String getPrefixMapping(String prefix)
	{
		return m_prefixMappings == null
				? null
				: m_prefixMappings.get(prefix);
	}

	/**
	 * Returns the name of the element for which this class is a handler. In essence, this method returns the value of
	 * the static variable <code>TAG</code> declared for the class of the instance that receives the call.
	 * 
	 * @return The element name.
	 */
	public abstract String getTAG();

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException
	{
		if(m_prefixMappings == null)
			m_prefixMappings = new HashMap<String, String>();
		m_prefixMappings.put(prefix, uri);
	}

	/**
	 * Returns the boolean value of an attribute.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @return The value of the attribute or <code>null</code>.
	 * @throws MissingRequiredAttributeException
	 *             if the attribute does not exist.
	 */
	protected boolean getBooleanValue(Attributes attrs, String qName) throws MissingRequiredAttributeException
	{
		String value = this.getStringValue(attrs, qName);
		if("1".equals(value))
			return true;
		if("0".equals(value))
			return false;
		return "true".equalsIgnoreCase(value);
	}

	protected abstract Locator getDocumentLocator();

	/**
	 * Returns the double value of an attribute.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @return The value of the attribute or <code>null</code>.
	 * @throws MissingRequiredAttributeException
	 *             if the attribute does not exist.
	 */
	protected double getDoubleValue(Attributes attrs, String qName) throws MissingRequiredAttributeException
	{
		return Double.parseDouble(this.getStringValue(attrs, qName));
	}

	/**
	 * Returns the int value of an attribute.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @return The value of the attribute or <code>null</code>.
	 * @throws MissingRequiredAttributeException
	 *             if the attribute does not exist.
	 */
	protected int getIntValue(Attributes attrs, String qName) throws MissingRequiredAttributeException
	{
		return Integer.parseInt(this.getStringValue(attrs, qName));
	}

	/**
	 * Returns the long value of an attribute.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @return The value of the attribute or <code>null</code>.
	 * @throws MissingRequiredAttributeException
	 *             if the attribute does not exist.
	 */
	protected long getLongValue(Attributes attrs, String qName) throws MissingRequiredAttributeException
	{
		return Long.parseLong(this.getStringValue(attrs, qName));
	}

	/**
	 * Returns an attribute that is compiled into a regular expression pattern.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @return The compiled pattern.
	 * @throws MissingRequiredAttributeException
	 *             when the attribute is not null or empty
	 * @throws SAXParseException
	 *             when the attribute value cannot be compiled into a regular expression pattern.
	 */
	protected Pattern getPatternValue(Attributes attrs, String qName) throws SAXParseException,
			MissingRequiredAttributeException
	{
		Pattern value = getOptionalPatternValue(attrs, qName);
		if(value == null)
			throw new MissingRequiredAttributeException(this.getTAG(), qName, this.getDocumentLocator());
		return value;
	}

	/**
	 * Returns an attribute that is trimmed from whitespace and with a length greater then zero.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @return The value of the attribute or <code>null</code>.
	 * @throws MissingRequiredAttributeException
	 *             if the attribute does not exist or if it consists entirely of whitespace.
	 */
	protected String getStringValue(Attributes attrs, String qName) throws MissingRequiredAttributeException
	{
		String value = getOptionalStringValue(attrs, qName);
		if(value == null)
			throw new MissingRequiredAttributeException(this.getTAG(), qName, this.getDocumentLocator());
		return value;
	}

	protected abstract TopHandler getTopHandler();

	/**
	 * Returns an attribute that is parsed into a URL.
	 * 
	 * @param attrs
	 *            The attribute container.
	 * @param qName
	 *            The qualified name of the attribute.
	 * @return The URL.
	 * @throws MissingRequiredAttributeException
	 *             when the attribute is not null or empty
	 * @throws SAXParseException
	 *             when the attribute value cannot be parsed into a URL.
	 */
	protected URL getURLValue(Attributes attrs, String qName) throws SAXParseException,
			MissingRequiredAttributeException
	{
		URL value = getOptionalURLValue(attrs, qName);
		if(value == null)
			throw new MissingRequiredAttributeException(this.getTAG(), qName, this.getDocumentLocator());
		return value;
	}
}

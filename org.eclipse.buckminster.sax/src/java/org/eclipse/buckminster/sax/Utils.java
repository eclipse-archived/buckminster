/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.sax;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Utils
{
	private static class ByteInputOutputBuffer extends ByteArrayOutputStream
	{
		public InputStream getInputStream()
		{
			return new ByteArrayInputStream(buf, 0, count);
		}
	}

	private static SAXTransformerFactory s_saxTransformerFactory;

	private static final Class<?>[] s_emptyArgTypes = new Class[] {};

	private static final Object[] s_emptyArgs = new Object[] {};

	private static final SAXParserFactory s_parserFactory = SAXParserFactory.newInstance();

	/**
	 * Adds a CDATA type attribute using the default namespace
	 * 
	 * @param attrs
	 *            The attribtue collection receiveing the attribute
	 * @param name
	 *            The name of the attribute
	 * @param value
	 *            The attribute value
	 */
	public static void addAttribute(AttributesImpl attrs, String name, String value)
	{
		attrs.addAttribute("", name, name, "CDATA", value); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static <T> List<T> createUnmodifiableList(Collection<T> coll)
	{
		List<T> aList;
		if(coll == null || coll.size() == 0)
			aList = Collections.emptyList();
		else
		{
			List<T> newList;
			if(coll.size() == 1)
			{
				T value = (coll instanceof List<?>)
						? ((List<T>)coll).get(0)
						: coll.iterator().next();
				newList = Collections.singletonList(value);
			}
			else
				newList = new ArrayList<T>(coll);
			aList = Collections.unmodifiableList(newList);
		}
		return aList;
	}

	public static <K, V> Map<K, V> createUnmodifiableMap(Map<K, V> aMap)
	{
		if(aMap == null || aMap.size() == 0)
			aMap = Collections.emptyMap();
		else
		{
			if(aMap.size() == 1)
			{
				Map.Entry<K, V> entry = aMap.entrySet().iterator().next();
				aMap = Collections.singletonMap(entry.getKey(), entry.getValue());
			}
			else
				aMap = new HashMap<K, V>(aMap);
			aMap = Collections.unmodifiableMap(aMap);
		}
		return aMap;
	}

	public static <T> Set<T> createUnmodifiableSet(Collection<T> coll)
	{
		Set<T> aSet;
		if(coll == null || coll.size() == 0)
			aSet = Collections.emptySet();
		else
		{
			Set<T> newSet;
			if(coll.size() == 1)
			{
				T value = (coll instanceof List<?>)
						? ((List<T>)coll).get(0)
						: coll.iterator().next();
				newSet = Collections.singleton(value);
			}
			else
				newSet = new HashSet<T>(coll);
			aSet = Collections.unmodifiableSet(newSet);
		}
		return aSet;
	}

	/**
	 * Create an XMLReader instance.
	 * 
	 * @param validating
	 *            true if a validating parser is desired
	 * @param withNamespace
	 *            true if the parser is namespace aware
	 * @return The created instance.
	 * @throws SAXException
	 *             If no XMLReader could be created.
	 */
	public static XMLReader createXMLReader(boolean validating, boolean withNamespace) throws SAXException
	{
		try
		{
			synchronized(s_parserFactory)
			{
				s_parserFactory.setValidating(validating);
				s_parserFactory.setNamespaceAware(withNamespace);
				return s_parserFactory.newSAXParser().getXMLReader();
			}
		}
		catch(ParserConfigurationException e)
		{
			throw new SAXException(e);
		}
	}

	public static <T extends ISaxableElement> void emitCollection(String namespace, String prefix, String localName,
			String elemName, Attributes attrs, Collection<T> collection, ContentHandler handler) throws SAXException
	{
		if(collection.isEmpty() && attrs.getLength() == 0)
			return;

		String qName = makeQualifiedName(prefix, localName);
		handler.startElement(namespace, localName, qName, attrs);
		for(T elem : collection)
			elem.toSax(handler, namespace, prefix, elemName == null
					? elem.getDefaultTag()
					: elemName);
		handler.endElement(namespace, localName, qName);
	}

	public static <T extends ISaxableElement> void emitCollection(String namespace, String prefix, String localName,
			String elemName, Attributes attrs, T[] array, ContentHandler handler) throws SAXException
	{
		if(array.length == 0)
			return;

		String qName = makeQualifiedName(prefix, localName);
		handler.startElement(namespace, localName, qName, attrs);
		for(T elem : array)
			elem.toSax(handler, namespace, prefix, elemName == null
					? elem.getDefaultTag()
					: elemName);
		handler.endElement(namespace, localName, qName);
	}

	public static <T extends ISaxableElement> void emitCollection(String namespace, String prefix, String localName,
			String elemName, Collection<T> collection, ContentHandler handler) throws SAXException
	{
		emitCollection(namespace, prefix, localName, elemName, ISaxableElement.EMPTY_ATTRIBUTES, collection, handler);
	}

	/**
	 * Using J2SE 5.0 or higher, we would not need this since the <code>Locator</code> then can be casted to a
	 * <code>Locator2</code>. With 1.4 we know that the method is there but we can't get to it without using reflection.
	 * 
	 * @param locator
	 *            The locator to extract the encoding from
	 * @return The encoding as stated in the entity or <code>null</code> if not found.
	 */
	public static String getEncoding(Locator locator)
	{
		String enc = null;
		if(locator != null)
		{
			try
			{
				enc = (String)locator.getClass().getMethod("getEncoding", s_emptyArgTypes).invoke(locator, s_emptyArgs); //$NON-NLS-1$
			}
			catch(Exception e)
			{
				// either this locator object doesn't have this
				// method, or we're on an old JDK
			}
		}
		if(enc == null)
			enc = "UTF-8"; //$NON-NLS-1$
		return enc;
	}

	/**
	 * Obtain the byte image that corresponds to the <code>UTF-8</code> encoded XML image of the <code>saxable</code>
	 * argument. The method will make no attempt to nice indenting and the newline character used will be unix style
	 * always.
	 * 
	 * @param saxable
	 *            The element to be emited. Must not be <code>null</code>.
	 * @return the byte image
	 */
	public static byte[] getImage(ISaxable saxable)
	{
		try
		{
			ByteArrayOutputStream builder = new ByteArrayOutputStream();
			serializeUgly(saxable, builder);
			return builder.toByteArray();
		}
		catch(SAXException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Obtain the input stream to the <code>UTF-8</code> encoded XML image of the <code>saxable</code> argument.
	 * 
	 * @param saxable
	 *            The element to be emited. Must not be <code>null</code>.
	 * @return the input stream
	 */
	public static InputStream getInputStream(ISaxable saxable) throws SAXException
	{
		ByteInputOutputBuffer builder = new ByteInputOutputBuffer();
		serialize(saxable, builder);
		return builder.getInputStream();
	}

	/**
	 * Creates a qualified name by concatenating the <code>prefix</code>, a colon, and the <code>localName</code>. If
	 * <code>prefix</code> is <code>null</code> or an empty string, the <code>localName</code> is returned.
	 * 
	 * @param prefix
	 *            The prefix for the qualified name
	 * @param localName
	 *            The localName
	 * @return The qualified name.
	 */
	public static String makeQualifiedName(String prefix, String localName)
	{
		return (prefix == null || prefix.length() == 0)
				? localName
				: prefix + ':' + localName;
	}

	/**
	 * Instantiates and returns a new ContentHandler that will print its output to a stream.
	 * 
	 * @param file
	 *            The name of the output file. Use for diagnostic only.
	 * @param stream
	 *            The stream that will receive the output.
	 * @param indent
	 *            The indentation to use when pretty-printing or -1 if no pretty-printing is desired.
	 * @return A ContentHandler that will act as a serializer.
	 * @throws SAXException
	 *             if a problem occured creating the serializer.
	 */
	public static ContentHandler newSerializer(File file, OutputStream stream, String encoding, int indent,
			boolean useSysLinesep) throws SAXException
	{
		TransformerHandler serializer = createTransformerHandler(indent);
		Transformer t = serializer.getTransformer();
		t.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
		if(encoding != null)
			t.setOutputProperty(OutputKeys.ENCODING, encoding);

		if(indent >= 0)
			t.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$

		StreamResult out = new StreamResult();
		if(file != null)
			out.setSystemId(file);

		try
		{
			if(encoding == null)
				encoding = t.getOutputProperty(OutputKeys.ENCODING);
			out.setWriter(new OutputStreamWriter(stream, encoding));
		}
		catch(UnsupportedEncodingException e)
		{
			throw new SAXException(e.getMessage());
		}

		String lineSep = System.getProperty("line.separator"); //$NON-NLS-1$
		if(useSysLinesep || "\n".equals(lineSep)) //$NON-NLS-1$
			serializer.setResult(out);
		else
		{
			// Someone forgot to expose the ToXMLStream.setLineSepUse(boolean) so there's
			// no way we can do that in a nice way. This has been known to work though
			//
			System.setProperty("line.separator", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
			try
			{
				serializer.setResult(out);
			}
			finally
			{
				System.setProperty("line.separator", lineSep); //$NON-NLS-1$
			}
		}
		return serializer;
	}

	public static void serialize(ISaxable saxable, OutputStream outputStream) throws SAXException
	{
		ContentHandler serializer = newSerializer(null, outputStream, "UTF-8", 4, true); //$NON-NLS-1$
		saxable.toSax(serializer);
	}

	public static void serializeUgly(ISaxable saxable, OutputStream outputStream) throws SAXException
	{
		ContentHandler serializer = newSerializer(null, outputStream, "UTF-8", -1, false); //$NON-NLS-1$
		saxable.toSax(serializer);
	}

	private static synchronized TransformerHandler createTransformerHandler(int indent) throws SAXException
	{
		if(s_saxTransformerFactory == null)
		{
			TransformerFactory tf = TransformerFactory.newInstance();
			if(!tf.getFeature(SAXTransformerFactory.FEATURE))
				throw new SAXException("The TransformerFactory is not a SAXTransformerFactory"); //$NON-NLS-1$

			s_saxTransformerFactory = (SAXTransformerFactory)tf;
		}
		try
		{
			s_saxTransformerFactory.setAttribute("indent-number", Integer.toString(indent)); //$NON-NLS-1$
		}
		catch(IllegalArgumentException e)
		{
			// This transformer doesn't support ident-number. We dont' consider that an error
		}

		try
		{
			return s_saxTransformerFactory.newTransformerHandler();
		}
		catch(TransformerConfigurationException e)
		{
			throw new SAXException(e.getMessage());
		}
	}
}

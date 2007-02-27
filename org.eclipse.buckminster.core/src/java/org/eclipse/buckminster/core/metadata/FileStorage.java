/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.model.ElementNotFoundException;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("serial")
public class FileStorage<T extends IUUIDKeyed> extends HashMap<UUID,TimestampedKey> implements ISaxableStorage<T>
{
	private static final String SEQUENCE_FILE = ".sqfile";

	private final Class<T> m_class;

	private final File m_folder;

	private final HashMap<UUID, T> m_parsed = new HashMap<UUID, T>();

	private final int m_sequenceNumber;

	private transient T[] m_allElements;

	private HashMap<String, Method> m_getters;

	private final IParser<T> m_parser;
	
	private boolean m_sequenceChanged;

	public FileStorage(File folder, IParser<T> parser, Class<T> clazz, int sequenceNumber) throws CoreException
	{
		m_folder = folder;
		m_parser = parser;
		m_class = clazz;
		m_sequenceNumber = sequenceNumber;

		FileUtils.createDirectory(folder, null);
		int foundSequenceNumber = readSequenceNumber();
		m_sequenceChanged = (foundSequenceNumber != sequenceNumber);
		if(m_sequenceChanged)
			writeSequenceNumber(m_sequenceNumber);

		File[] files = m_folder.listFiles();
		int idx = files.length;
		while(--idx >= 0)
		{
			File file = files[idx];
			String name = file.getName();
			if(name.charAt(0) == '.')
				continue;
			UUID id = UUID.fromString(name);
			put(id, new TimestampedKey(id, file.lastModified()));
		}
	}

	public boolean sequenceChanged()
	{
		return m_sequenceChanged;
	}

	@Override
	public void clear()
	{
		try
		{
			FileUtils.prepareDestination(m_folder, true, new NullProgressMonitor());
			writeSequenceNumber(m_sequenceNumber);
		}
		catch(CoreException e)
		{
			CorePlugin.getLogger().error(e.toString(), e);
		}
		m_parsed.clear();
		super.clear();
	}

	public synchronized boolean contains(T element) throws CoreException
	{
		return containsKey(element.getId());
	}

	public synchronized UUID[] getKeys()
	{
		Set<UUID> keys = keySet();
		return keys.toArray(new UUID[keys.size()]);
	}

	public synchronized long getCreationTime(UUID elementId) throws ElementNotFoundException
	{
		TimestampedKey tsKey = get(elementId);
		if(tsKey == null)
			throw new ElementNotFoundException(this, elementId);
		return tsKey.getCreationTime();
	}

	public synchronized T getElement(UUID elementId) throws CoreException
	{
		if(!containsKey(elementId))
			throw new ElementNotFoundException(this, elementId);

		T element = m_parsed.get(elementId);
		if(element != null)
			return element;

		InputStream input = null;
		try
		{
			File elementFile = getElementFile(elementId);
			input = new FileInputStream(elementFile);
			element = m_parser.parse(elementFile.toString(), input);
			element.setId(elementId);
			m_parsed.put(elementId, element);
			return element;
		}
		catch(FileNotFoundException e)
		{
			throw new ElementNotFoundException(this, elementId);
		}
		catch(SAXException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	@SuppressWarnings("unchecked")
	public synchronized T[] getElements() throws CoreException
	{
		if(m_allElements == null)
		{
			Set<UUID> keys = keySet();
			int idx = keys.size();
			m_allElements = (T[])Array.newInstance(m_class, idx);
			for(UUID key : keys)
				m_allElements[--idx] = getElement(key);
		}
		return m_allElements;
	}

	public String getName()
	{
		return m_folder.getName();
	}

	public synchronized TimestampedKey[] getTimestampedKeys()
	{
		Collection<TimestampedKey> values = values();
		return values.toArray(new TimestampedKey[values.size()]);
	}

	public synchronized List<UUID> getReferencingKeys(UUID foreignKey, String keyName) throws CoreException
	{
		List<UUID> result = null;
		Method getter = getGetter(keyName);
		try
		{
			for(UUID elementId : keySet())
			{
				T element = getElement(elementId);
				UUID fkey = (UUID)getter.invoke(element, Trivial.EMPTY_OBJECT_ARRAY);
				if(fkey != null && fkey.equals(foreignKey))
				{
					if(result == null)
						result = new ArrayList<UUID>();
					result.add(elementId);
				}
			}
			if(result == null)
				result = Collections.emptyList();
			return result;
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public synchronized void putElement(T element) throws CoreException
	{
		UUID id = element.getId();
		if(containsKey(id))
			return;

		m_parsed.put(id, element);
		persistImage(id, element.getImage());
	}

	public synchronized void putElement(UUID id, T element) throws CoreException
	{
		UUID realId = element.getId();
		putElement(element);
		if(id.equals(realId))
			return;

		// A discreprancy has occured between elements. Likely due to
		// different XML versions.
		//
		CorePlugin.getLogger().debug(String.format(
			"Element id discrepancy in storage %s, expected %s, was %s", getName(), realId, id));

		if(containsKey(id))
			return;

		m_parsed.put(id, element);
		persistImage(id, element.getImage());
	}

	public int readSequenceNumber() throws CoreException
	{
		DataInputStream in = null;
		try
		{
			in = new DataInputStream(new FileInputStream(new File(m_folder, SEQUENCE_FILE)));
			return in.readInt();
		}
		catch(FileNotFoundException e)
		{
			return -1;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(in);
		}
	}

	public synchronized void removeElement(UUID elementId) throws CoreException
	{
		m_parsed.remove(elementId);
		persistImage(elementId, null);
	}

	private File getElementFile(UUID elementId)
	{
		return new File(m_folder, elementId.toString());
	}

	private synchronized Method getGetter(String keyName) throws CoreException
	{
		String key = keyName.toLowerCase();
		if(m_getters == null)
			m_getters = new HashMap<String, Method>();
		else
		{
			Method getter = m_getters.get(key);
			if(getter != null)
				return getter;
		}

		Class<UUID> retType = UUID.class;
		for(Method method : m_class.getMethods())
		{
			// The method has to be a non static, public method that
			// returns an UUID and takes no arguments.
			//
			int mod = method.getModifiers();
			if(Modifier.isPublic(mod)
			&& !Modifier.isStatic(mod)
			&& method.getReturnType().equals(retType)
			&& method.getParameterTypes().length == 0)
			{
				String name = method.getName().toLowerCase();
				if(name.length() > 3 && name.startsWith("get"))
				{
					name = name.substring(3);
					if(name.equals(key))
					{
						m_getters.put(key, method);
						return method;						
					}
				}
			}
		}
		throw new BuckminsterException("No such foreign key: " + keyName);
	}

	private void persistImage(UUID elementId, byte[] image) throws CoreException
	{
		m_allElements = null;
		File elementFile = getElementFile(elementId);
		if(image == null)
		{
			if(!elementFile.delete() && elementFile.exists())
				throw new FileUtils.DeleteException(elementFile);
			remove(elementId);
			return;
		}

		OutputStream output = null;
		try
		{
			output = new FileOutputStream(elementFile);
			output.write(image);
		}
		catch(IOException e)
		{
			elementFile.delete();
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(output);
		}
		put(elementId, new TimestampedKey(elementId, elementFile.lastModified()));
	}

	private void writeSequenceNumber(int sequenceNumber) throws CoreException
	{
		DataOutputStream out = null;
		try
		{
			out = new DataOutputStream(new FileOutputStream(new File(m_folder, SEQUENCE_FILE)));
			out.writeInt(sequenceNumber);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(out);
		}
	}
}

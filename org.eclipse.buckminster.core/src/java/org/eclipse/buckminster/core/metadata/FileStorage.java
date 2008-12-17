/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.model.ElementNotFoundException;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class FileStorage<T extends UUIDKeyed> implements ISaxableStorage<T>
{
	public static class Lock
	{
		public static Lock lock(File file, boolean exclusive) throws CoreException
		{
			return new Lock(file, exclusive);
		}

		private final RandomAccessFile m_lockFile;

		private FileLock m_lock;

		private Lock(File file, boolean exclusive) throws CoreException
		{
			RandomAccessFile lockFile = null;
			try
			{
				lockFile = new RandomAccessFile(file, "rws"); //$NON-NLS-1$
				m_lock = lockFile.getChannel().lock(0, Long.MAX_VALUE, !exclusive);
				m_lockFile = lockFile;
			}
			catch(IOException e)
			{
				IOUtils.close(lockFile);
				throw BuckminsterException.wrap(e);
			}
		}

		public FileChannel getLockChannel()
		{
			return m_lock.channel();
		}

		public RandomAccessFile getLockFile()
		{
			return m_lockFile;
		}

		public void migrateToExclusive() throws CoreException
		{
			if(m_lock.isShared())
			{
				FileLock shared = m_lock;
				try
				{
					FileChannel channel = shared.channel();
					shared.release();
					m_lock = channel.lock(0, Long.MAX_VALUE, true);
				}
				catch(IOException e)
				{
					throw BuckminsterException.wrap(e);
				}
			}
		}

		public void release()
		{
			try
			{
				m_lock.release();
			}
			catch(IOException e)
			{
			}
			IOUtils.close(m_lockFile);
		}
	}

	private final HashMap<UUID, TimestampedKey> m_timestamps = new HashMap<UUID, TimestampedKey>();

	private static final String SEQUENCE_FILE = ".sqfile"; //$NON-NLS-1$

	private final Class<T> m_class;

	private final File m_folder;

	private final File m_sqFile;

	private final HashMap<UUID, T> m_parsed = new HashMap<UUID, T>();

	private final int m_sequenceNumber;

	private transient T[] m_allElements;

	private HashMap<String, Method> m_getters;

	private final IParser<T> m_parser;

	private long m_cacheTime;

	private long m_lastChecked;

	private boolean m_sequenceChanged;

	public FileStorage(File folder, IParser<T> parser, Class<T> clazz, int sequenceNumber) throws CoreException
	{
		m_folder = folder;
		m_sqFile = new File(m_folder, SEQUENCE_FILE);
		m_parser = parser;
		m_class = clazz;
		m_sequenceNumber = sequenceNumber;

		FileUtils.createDirectory(folder, null);
		Lock lock = Lock.lock(m_sqFile, true);
		try
		{
			ByteBuffer bf = ByteBuffer.allocateDirect(4);
			bf.order(ByteOrder.LITTLE_ENDIAN);
			FileChannel fc = lock.getLockChannel();
			int foundSequenceNumber = fc.read(bf) == 4
					? bf.getInt(0)
					: -1;

			m_sequenceChanged = (foundSequenceNumber >= 0 && foundSequenceNumber != sequenceNumber);
			if(foundSequenceNumber < 0 || m_sequenceChanged)
			{
				// Use exclusive lock and write the new sequence number
				//
				bf.clear();
				bf.putInt(m_sequenceNumber);
				bf.flip();
				fc.position(0);
				fc.write(bf);
			}

			File[] files = m_folder.listFiles();
			int idx = files.length;
			while(--idx >= 0)
			{
				File file = files[idx];
				String name = file.getName();
				if(name.charAt(0) == '.')
					continue;
				UUID id = UUID.fromString(name);
				m_timestamps.put(id, new TimestampedKey(id, file.lastModified()));
			}
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			lock.release();
		}
		m_cacheTime = m_sqFile.lastModified();
		m_lastChecked = System.currentTimeMillis();
	}

	private void checkCache()
	{
		// The lastModified() call is an IO call. We don't want that to happen
		// too frequently so we use the m_lastChecked to limit it to no more then
		// once a second.
		//
		long now = System.currentTimeMillis();
		if(now - m_lastChecked < 1000)
			return;

		if(m_cacheTime >= m_sqFile.lastModified())
		{
			m_lastChecked = now;
			return;
		}

		try
		{
			Lock lock = Lock.lock(m_sqFile, false);
			try
			{
				m_timestamps.clear();
				m_parsed.clear();
				m_allElements = null;
				File[] files = m_folder.listFiles();
				int idx = files.length;
				while(--idx >= 0)
				{
					File file = files[idx];
					String name = file.getName();
					if(name.charAt(0) == '.')
						continue;
					UUID id = UUID.fromString(name);
					m_timestamps.put(id, new TimestampedKey(id, file.lastModified()));
				}
				m_cacheTime = m_sqFile.lastModified();
				m_lastChecked = System.currentTimeMillis();
			}
			finally
			{
				lock.release();
			}
		}
		catch(CoreException e)
		{
			CorePlugin.getLogger().error(e, e.getMessage());
		}
	}

	public synchronized void clear()
	{
		try
		{
			Lock lock = Lock.lock(m_sqFile, true);
			try
			{
				for(File file : m_folder.listFiles())
					if(!file.equals(m_sqFile))
						file.delete();
			}
			finally
			{
				lock.release();
			}
		}
		catch(CoreException e)
		{
			CorePlugin.getLogger().error(e, e.toString());
		}
		m_parsed.clear();
		m_timestamps.clear();
	}

	public synchronized boolean contains(T element) throws CoreException
	{
		checkCache();
		return m_timestamps.containsKey(element.getId());
	}

	public synchronized long getCreationTime(UUID elementId) throws ElementNotFoundException
	{
		checkCache();
		TimestampedKey tsKey = m_timestamps.get(elementId);
		if(tsKey == null)
			throw new ElementNotFoundException(this, elementId);
		return tsKey.getCreationTime();
	}

	public synchronized T getElement(UUID elementId) throws CoreException
	{
		checkCache();
		if(!m_timestamps.containsKey(elementId))
			throw new ElementNotFoundException(this, elementId);

		T element = m_parsed.get(elementId);
		if(element != null)
			return element;

		Lock lock = Lock.lock(m_sqFile, false);
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
		finally
		{
			IOUtils.close(input);
			lock.release();
		}
	}

	private File getElementFile(UUID elementId)
	{
		return new File(m_folder, elementId.toString());
	}

	@SuppressWarnings("unchecked")
	public synchronized T[] getElements() throws CoreException
	{
		checkCache();
		if(m_allElements == null)
		{
			Set<UUID> keys = m_timestamps.keySet();
			Set<UUID> badKeys = null;
			int idx = keys.size();
			T[] elems = (T[])Array.newInstance(m_class, idx);
			for(UUID key : keys)
			{
				try
				{
					--idx;
					elems[idx] = getElement(key);
				}
				catch(CoreException e)
				{
					CorePlugin.getLogger().warning(BuckminsterException.unwind(e),
							NLS.bind(Messages.Unable_to_read_0, m_class.getName()));
					if(badKeys == null)
						badKeys = new HashSet<UUID>();
					badKeys.add(key);
				}
			}

			if(badKeys != null)
			{
				idx = elems.length;
				int goodIdx = idx - badKeys.size();
				T[] goodElems = (T[])Array.newInstance(m_class, goodIdx);
				while(--idx >= 0)
				{
					T elem = elems[idx];
					if(elem != null)
						goodElems[--goodIdx] = elem;
				}
				elems = goodElems;
				for(UUID badKey : badKeys)
					m_timestamps.remove(badKey);
			}
			m_allElements = elems;
		}
		return m_allElements;
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
			if(Modifier.isPublic(mod) && !Modifier.isStatic(mod) && method.getReturnType().equals(retType)
					&& method.getParameterTypes().length == 0)
			{
				String name = method.getName().toLowerCase();
				if(name.length() > 3 && name.startsWith("get")) //$NON-NLS-1$
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
		throw BuckminsterException.fromMessage(NLS.bind(Messages.No_such_foreign_key_0, keyName));
	}

	public synchronized UUID[] getKeys()
	{
		checkCache();
		Set<UUID> keys = m_timestamps.keySet();
		return keys.toArray(new UUID[keys.size()]);
	}

	public String getName()
	{
		return m_folder.getName();
	}

	public synchronized List<UUID> getReferencingKeys(UUID foreignKey, String keyName) throws CoreException
	{
		List<UUID> result = null;
		Method getter = getGetter(keyName);
		try
		{
			for(UUID elementId : m_timestamps.keySet())
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

	public synchronized TimestampedKey[] getTimestampedKeys()
	{
		checkCache();
		Collection<TimestampedKey> values = m_timestamps.values();
		return values.toArray(new TimestampedKey[values.size()]);
	}

	private void persistImage(UUID elementId, byte[] image) throws CoreException
	{
		Lock lock = Lock.lock(m_sqFile, true);
		try
		{
			m_allElements = null;
			File elementFile = getElementFile(elementId);
			if(image == null)
			{
				if(!elementFile.delete() && elementFile.exists())
					throw new FileUtils.DeleteException(elementFile);

				m_timestamps.remove(elementId);
			}
			else
			{
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
			}
			m_cacheTime = System.currentTimeMillis();
			m_lastChecked = m_cacheTime;
			m_sqFile.setLastModified(m_cacheTime);
		}
		finally
		{
			lock.release();
		}
	}

	public synchronized void putElement(T element) throws CoreException
	{
		UUID id = element.getId();
		long timestamp;
		if(!m_timestamps.containsKey(id))
		{
			m_parsed.put(id, element);
			persistImage(id, element.getImage());
			timestamp = System.currentTimeMillis();
		}
		else
		{
			timestamp = System.currentTimeMillis();
			getElementFile(id).setLastModified(timestamp);
		}
		m_timestamps.put(id, new TimestampedKey(id, timestamp));
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
		CorePlugin.getLogger()
				.debug("Element id discrepancy in storage %s, expected %s, was %s", getName(), realId, id); //$NON-NLS-1$

		if(m_timestamps.containsKey(id))
			return;

		m_parsed.put(id, element);
		persistImage(id, element.getImage());
		m_timestamps.put(id, new TimestampedKey(id, System.currentTimeMillis()));
	}

	public synchronized void removeElement(UUID elementId) throws CoreException
	{
		m_parsed.remove(elementId);
		persistImage(elementId, null);
	}

	public boolean sequenceChanged()
	{
		return m_sequenceChanged;
	}
}

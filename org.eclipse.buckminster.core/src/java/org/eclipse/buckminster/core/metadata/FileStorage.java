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
import java.lang.reflect.Method;
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
public class FileStorage<T extends UUIDKeyed> extends AbstractSaxableStorage<T> {
	public static class Lock {
		public static Lock lock(File file, boolean exclusive) throws CoreException {
			return new Lock(file, exclusive);
		}

		private final RandomAccessFile lockFile;

		private FileLock lock;

		private Lock(File file, boolean exclusive) throws CoreException {
			RandomAccessFile raFile = null;
			try {
				raFile = new RandomAccessFile(file, "rws"); //$NON-NLS-1$
				lock = raFile.getChannel().lock(0, Long.MAX_VALUE, !exclusive);
				lockFile = raFile;
			} catch (IOException e) {
				IOUtils.close(raFile);
				throw BuckminsterException.wrap(e);
			}
		}

		public FileChannel getLockChannel() {
			return lock.channel();
		}

		public RandomAccessFile getLockFile() {
			return lockFile;
		}

		public void migrateToExclusive() throws CoreException {
			if (lock.isShared()) {
				FileLock shared = lock;
				try {
					FileChannel channel = shared.channel();
					shared.release();
					lock = channel.lock(0, Long.MAX_VALUE, true);
				} catch (IOException e) {
					throw BuckminsterException.wrap(e);
				}
			}
		}

		public void release() {
			try {
				lock.release();
			} catch (IOException e) {
			}
			IOUtils.close(lockFile);
		}
	}

	private final HashMap<UUID, TimestampedKey> timestamps = new HashMap<UUID, TimestampedKey>();

	private static final String SEQUENCE_FILE = ".sqfile"; //$NON-NLS-1$

	private final File folder;

	private final File sqFile;

	private final HashMap<UUID, T> parsed = new HashMap<UUID, T>();

	private transient T[] allElements;

	private final IParser<T> parser;

	private long cacheTime;

	private long lastChecked;

	private boolean sequenceChanged;

	public FileStorage(File folder, IParser<T> parser, Class<T> clazz, int sequenceNumber) throws CoreException {
		super(clazz);
		this.folder = folder;
		this.sqFile = new File(folder, SEQUENCE_FILE);
		this.parser = parser;

		FileUtils.createDirectory(folder, null);
		Lock lock = Lock.lock(sqFile, true);
		try {
			ByteBuffer bf = ByteBuffer.allocateDirect(4);
			bf.order(ByteOrder.LITTLE_ENDIAN);
			FileChannel fc = lock.getLockChannel();
			int foundSequenceNumber = fc.read(bf) == 4 ? bf.getInt(0) : -1;

			sequenceChanged = (foundSequenceNumber >= 0 && foundSequenceNumber != sequenceNumber);
			if (foundSequenceNumber < 0 || sequenceChanged) {
				// Use exclusive lock and write the new sequence number
				//
				bf.clear();
				bf.putInt(sequenceNumber);
				bf.flip();
				fc.position(0);
				fc.write(bf);
			}

			File[] files = folder.listFiles();
			int idx = files.length;
			while (--idx >= 0) {
				File file = files[idx];
				String name = file.getName();
				if (name.charAt(0) == '.')
					continue;
				UUID id = UUID.fromString(name);
				timestamps.put(id, new TimestampedKey(id, file.lastModified()));
			}
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			lock.release();
		}
		cacheTime = sqFile.lastModified();
		lastChecked = System.currentTimeMillis();
	}

	@Override
	public synchronized void clear() {
		try {
			Lock lock = Lock.lock(sqFile, true);
			try {
				for (File file : folder.listFiles())
					if (!file.equals(sqFile))
						file.delete();
			} finally {
				lock.release();
			}
		} catch (CoreException e) {
			CorePlugin.getLogger().error(e, e.toString());
		}
		parsed.clear();
		timestamps.clear();
	}

	@Override
	public synchronized boolean contains(T element) throws CoreException {
		checkCache();
		return timestamps.containsKey(element.getId());
	}

	@Override
	public synchronized long getCreationTime(UUID elementId) throws ElementNotFoundException {
		checkCache();
		TimestampedKey tsKey = timestamps.get(elementId);
		if (tsKey == null)
			throw new ElementNotFoundException(this, elementId);
		return tsKey.getCreationTime();
	}

	@Override
	public synchronized T getElement(UUID elementId) throws CoreException {
		checkCache();
		if (!timestamps.containsKey(elementId))
			throw new ElementNotFoundException(this, elementId);

		T element = parsed.get(elementId);
		if (element != null)
			return element;

		Lock lock = Lock.lock(sqFile, false);
		InputStream input = null;
		try {
			File elementFile = getElementFile(elementId);
			input = new FileInputStream(elementFile);
			element = parser.parse(elementFile.toString(), input);
			element.setId(elementId);
			parsed.put(elementId, element);
			return element;
		} catch (FileNotFoundException e) {
			throw new ElementNotFoundException(this, elementId);
		} finally {
			IOUtils.close(input);
			lock.release();
		}
	}

	@Override
	public synchronized T[] getElements() throws CoreException {
		checkCache();
		if (allElements == null) {
			Set<UUID> keys = timestamps.keySet();
			Set<UUID> badKeys = null;
			int idx = keys.size();
			T[] elems = createArray(idx);
			for (UUID key : keys) {
				try {
					--idx;
					elems[idx] = getElement(key);
				} catch (CoreException e) {
					CorePlugin.getLogger().warning(BuckminsterException.unwind(e), NLS.bind(Messages.Unable_to_read_0, getElementClass().getName()));
					if (badKeys == null)
						badKeys = new HashSet<UUID>();
					badKeys.add(key);
				}
			}

			if (badKeys != null) {
				idx = elems.length;
				int goodIdx = idx - badKeys.size();
				T[] goodElems = createArray(goodIdx);
				while (--idx >= 0) {
					T elem = elems[idx];
					if (elem != null)
						goodElems[--goodIdx] = elem;
				}
				elems = goodElems;
				for (UUID badKey : badKeys)
					timestamps.remove(badKey);
			}
			allElements = elems;
		}
		return allElements;
	}

	@Override
	public synchronized UUID[] getKeys() {
		checkCache();
		Set<UUID> keys = timestamps.keySet();
		return keys.toArray(new UUID[keys.size()]);
	}

	@Override
	public String getName() {
		return folder.getName();
	}

	@Override
	public synchronized List<UUID> getReferencingKeys(UUID foreignKey, String keyName) throws CoreException {
		List<UUID> result = null;
		Method getter = getGetter(keyName);
		try {
			for (UUID elementId : timestamps.keySet()) {
				T element = getElement(elementId);
				UUID fkey = (UUID) getter.invoke(element, Trivial.EMPTY_OBJECT_ARRAY);
				if (fkey != null && fkey.equals(foreignKey)) {
					if (result == null)
						result = new ArrayList<UUID>();
					result.add(elementId);
				}
			}
			if (result == null)
				result = Collections.emptyList();
			return result;
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public synchronized TimestampedKey[] getTimestampedKeys() {
		checkCache();
		Collection<TimestampedKey> values = timestamps.values();
		return values.toArray(new TimestampedKey[values.size()]);
	}

	@Override
	public synchronized void putElement(T element) throws CoreException {
		UUID id = element.getId();
		long timestamp;
		if (!timestamps.containsKey(id)) {
			parsed.put(id, element);
			persistImage(id, element.getImage());
			timestamp = System.currentTimeMillis();
		} else {
			timestamp = System.currentTimeMillis();
			getElementFile(id).setLastModified(timestamp);
		}
		timestamps.put(id, new TimestampedKey(id, timestamp));
	}

	@Override
	public synchronized void putElement(UUID id, T element) throws CoreException {
		UUID realId = element.getId();
		putElement(element);
		if (id.equals(realId))
			return;

		// A discreprancy has occured between elements. Likely due to
		// different XML versions.
		//
		CorePlugin.getLogger().debug("Element id discrepancy in storage %s, expected %s, was %s", getName(), realId, id); //$NON-NLS-1$

		if (timestamps.containsKey(id))
			return;

		parsed.put(id, element);
		persistImage(id, element.getImage());
		timestamps.put(id, new TimestampedKey(id, System.currentTimeMillis()));
	}

	@Override
	public synchronized void removeElement(UUID elementId) throws CoreException {
		parsed.remove(elementId);
		persistImage(elementId, null);
	}

	@Override
	public boolean sequenceChanged() {
		return sequenceChanged;
	}

	private void checkCache() {
		// The lastModified() call is an IO call. We don't want that to happen
		// too frequently so we use the lastChecked to limit it to no more
		// then
		// once a second.
		//
		long now = System.currentTimeMillis();
		if (now - lastChecked < 1000)
			return;

		if (cacheTime >= sqFile.lastModified()) {
			lastChecked = now;
			return;
		}

		try {
			Lock lock = Lock.lock(sqFile, false);
			try {
				timestamps.clear();
				parsed.clear();
				allElements = null;
				File[] files = folder.listFiles();
				int idx = files.length;
				while (--idx >= 0) {
					File file = files[idx];
					String name = file.getName();
					if (name.charAt(0) == '.')
						continue;
					UUID id = UUID.fromString(name);
					timestamps.put(id, new TimestampedKey(id, file.lastModified()));
				}
				cacheTime = sqFile.lastModified();
				lastChecked = System.currentTimeMillis();
			} finally {
				lock.release();
			}
		} catch (CoreException e) {
			CorePlugin.getLogger().error(e, e.getMessage());
		}
	}

	private File getElementFile(UUID elementId) {
		return new File(folder, elementId.toString());
	}

	private void persistImage(UUID elementId, byte[] image) throws CoreException {
		Lock lock = Lock.lock(sqFile, true);
		try {
			allElements = null;
			File elementFile = getElementFile(elementId);
			if (image == null) {
				if (!elementFile.delete() && elementFile.exists())
					throw new FileUtils.DeleteException(elementFile);

				timestamps.remove(elementId);
			} else {
				OutputStream output = null;
				try {
					output = new FileOutputStream(elementFile);
					output.write(image);
				} catch (IOException e) {
					elementFile.delete();
					throw BuckminsterException.wrap(e);
				} finally {
					IOUtils.close(output);
				}
			}
			cacheTime = System.currentTimeMillis();
			lastChecked = cacheTime;
			sqFile.setLastModified(cacheTime);
		} finally {
			lock.release();
		}
	}
}

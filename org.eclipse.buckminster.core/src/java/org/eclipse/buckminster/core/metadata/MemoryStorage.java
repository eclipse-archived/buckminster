package org.eclipse.buckminster.core.metadata;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.metadata.model.ElementNotFoundException;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.core.runtime.CoreException;

public class MemoryStorage<T extends UUIDKeyed> extends AbstractSaxableStorage<T>
{
	static class TimestampedElement<ET extends UUIDKeyed> extends TimestampedKey
	{
		private final ET m_element;

		TimestampedElement(ET element)
		{
			super(element.getId(), System.currentTimeMillis());
			m_element = element;
		}

		ET getElement()
		{
			return m_element;
		}
	}

	private final Map<UUID, TimestampedElement<T>> m_elements = new HashMap<UUID, TimestampedElement<T>>();

	public MemoryStorage(Class<T> elementClass)
	{
		super(elementClass);
	}

	public synchronized void clear()
	{
		m_elements.clear();
	}

	public synchronized boolean contains(T element) throws CoreException
	{
		return m_elements.containsKey(element.getId());
	}

	public synchronized long getCreationTime(UUID elementId) throws CoreException
	{
		TimestampedElement<T> t = m_elements.get(elementId);
		if(t == null)
			throw new ElementNotFoundException(this, elementId);
		return t.getCreationTime();
	}

	public synchronized T getElement(UUID elementId) throws CoreException, ElementNotFoundException
	{
		TimestampedElement<T> t = m_elements.get(elementId);
		if(t == null)
			throw new ElementNotFoundException(this, elementId);
		return t.getElement();
	}

	public synchronized T[] getElements() throws CoreException
	{
		T[] elems = createArray(m_elements.size());
		int idx = 0;
		for(TimestampedElement<T> te : m_elements.values())
			elems[idx++] = te.getElement();
		return elems;
	}

	public synchronized UUID[] getKeys()
	{
		return m_elements.keySet().toArray(new UUID[m_elements.size()]);
	}

	public String getName()
	{
		return getClass().getName();
	}

	public synchronized List<UUID> getReferencingKeys(UUID foreignKey, String keyName) throws CoreException
	{
		List<UUID> result = null;
		Method getter = getGetter(keyName);
		try
		{
			for(TimestampedElement<T> et : m_elements.values())
			{
				T element = et.getElement();
				UUID fkey = (UUID)getter.invoke(element, Trivial.EMPTY_OBJECT_ARRAY);
				if(fkey != null && fkey.equals(foreignKey))
				{
					if(result == null)
						result = new ArrayList<UUID>();
					result.add(element.getId());
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
		return m_elements.values().toArray(new TimestampedKey[m_elements.size()]);
	}

	public void putElement(T element) throws CoreException
	{
		putElement(element.getId(), element);
	}

	public synchronized void putElement(UUID id, T element) throws CoreException
	{
		m_elements.put(id, new TimestampedElement<T>(element));
	}

	public synchronized void removeElement(UUID elementId) throws CoreException
	{
		m_elements.remove(elementId);
	}

	public boolean sequenceChanged()
	{
		return false;
	}
}

/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.SaxablePath;
import org.eclipse.buckminster.core.metadata.IUUIDKeyed;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IPath;

/**
 * Base class for immutable UUID keyed classes. The contract specifies
 * that such a class may only declare immutable (i.e. final) properties
 * since the id of the instance is calculated from the XML serialization.
 *
 * @author Thomas Hallgren
 */
public abstract class UUIDKeyed implements IUUIDKeyed, ISaxable
{
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
				T value = (coll instanceof List) ? ((List<T>)coll).get(0) : coll.iterator().next();
				newList = Collections.singletonList(value);
			}
			else
				newList = new ArrayList<T>(coll);
			aList = Collections.unmodifiableList(newList);
		}
		return aList;
	}

	public static <K,V> Map<K,V> createUnmodifiableMap(Map<K,V> aMap)
	{
		if(aMap == null || aMap.size() == 0)
			aMap = Collections.emptyMap();
		else
		{
			if(aMap.size() == 1)
			{
				Map.Entry<K,V> entry = aMap.entrySet().iterator().next();
				aMap = Collections.singletonMap(entry.getKey(), entry.getValue());
			}
			else
				aMap = new HashMap<K,V>(aMap);
			aMap = Collections.unmodifiableMap(aMap);
		}
		return aMap;
	}

	public static Set<IPath> createUnmodifiablePaths(Set<IPath> aSet)
	{
		if(aSet == null || aSet.size() == 0)
			aSet = Collections.emptySet();
		else
		{
			HashSet<IPath> saxablePaths = new HashSet<IPath>();
			for(IPath path : aSet)
				saxablePaths.add(SaxablePath.coerce(path));
			aSet = Collections.unmodifiableSet(saxablePaths);
		}
		return aSet;
	}

	public static Map<String,String> createUnmodifiableProperties(Map<String,String> aMap)
	{
		if(aMap == null || aMap.size() == 0)
			aMap = Collections.emptyMap();
		else
			aMap = Collections.unmodifiableMap(new ExpandingProperties(aMap));
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
				T value = (coll instanceof List) ? ((List<T>)coll).get(0) : coll.iterator().next();
				newSet = Collections.singleton(value);
			}
			else
				newSet = new HashSet<T>(coll);
			aSet = Collections.unmodifiableSet(newSet);
		}
		return aSet;
	}

	private transient UUID m_id;

	private transient byte[] m_image;

	@Override
	public final boolean equals(Object o)
	{
		return o == this || ((o instanceof UUIDKeyed && ((UUIDKeyed)o).getId().equals(this.getId())));
	}

	public synchronized final UUID getId()
	{
		if(m_id == null)
		{
			m_image = Utils.getImage(this);
			m_id = UUID.nameUUIDFromBytes(m_image);
		}
		return m_id;
	}

	public synchronized final byte[] getImage()
	{
		if(m_image == null)
		{
			m_image = Utils.getImage(this);
			if(m_id == null)
				m_id = UUID.nameUUIDFromBytes(m_image);
		}
		return m_image;
	}

	@Override
	public final int hashCode()
	{
		return this.getId().hashCode();
	}

	public final synchronized void setId(UUID id)
	{
		m_id = id;
	}
}

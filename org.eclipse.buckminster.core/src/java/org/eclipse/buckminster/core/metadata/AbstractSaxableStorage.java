/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.UUID;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
abstract class AbstractSaxableStorage<T extends UUIDKeyed> implements ISaxableStorage<T>
{
	private final Class<T> m_class;

	private HashMap<String, Method> m_getters;

	AbstractSaxableStorage(Class<T> clazz)
	{
		m_class = clazz;
	}

	@SuppressWarnings("unchecked")
	T[] createArray(int length)
	{
		return (T[])Array.newInstance(m_class, length);
	}

	Class<T> getElementClass()
	{
		return m_class;
	}

	synchronized Method getGetter(String keyName) throws CoreException
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
}

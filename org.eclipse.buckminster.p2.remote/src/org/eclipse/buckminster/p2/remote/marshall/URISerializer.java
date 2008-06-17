/*
 * jabsorb - a Java to JavaScript Advanced Object Request Broker
 * http://www.jabsorb.org
 *
 * Copyright 2007-2008 The jabsorb team
 *
 * based on original code from
 * JSON-RPC-Java - a JSON-RPC to Java Bridge with dynamic invocation
 *
 * Copyright Metaparadigm Pte. Ltd. 2004.
 * Michael Clark <michael@metaparadigm.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.eclipse.buckminster.p2.remote.marshall;

import java.net.URI;

import org.jabsorb.serializer.AbstractSerializer;
import org.jabsorb.serializer.MarshallException;
import org.jabsorb.serializer.ObjectMatch;
import org.jabsorb.serializer.SerializerState;
import org.jabsorb.serializer.UnmarshallException;

/**
 * Serialises URI's
 */
public class URISerializer extends AbstractSerializer
{
	private static Class[] s_serializableClasses = new Class[] { URI.class };

	private static Class[] s_JSONClasses = new Class[] { String.class };

	public Class[] getJSONClasses()
	{
		return s_JSONClasses;
	}

	public Class[] getSerializableClasses()
	{
		return s_serializableClasses;
	}

	public Object marshall(SerializerState state, Object p, Object o) throws MarshallException
	{
		if(o instanceof URI)
			return o.toString();
		throw new MarshallException("cannot marshall URI using class " + o.getClass());
	}

	public ObjectMatch tryUnmarshall(SerializerState state, Class clazz, Object o) throws UnmarshallException
	{
		try
		{
			new URI((String)o);
			state.setSerialized(o, ObjectMatch.OKAY);
			return ObjectMatch.OKAY;
		}
		catch(Exception e)
		{
			throw new UnmarshallException("not an URI");
		}
	}

	public Object unmarshall(SerializerState state, Class clazz, Object o) throws UnmarshallException
	{
		try
		{
			return new URI((String)o);
		}
		catch(Exception e)
		{
			throw new UnmarshallException("not an URI");
		}
	}

}

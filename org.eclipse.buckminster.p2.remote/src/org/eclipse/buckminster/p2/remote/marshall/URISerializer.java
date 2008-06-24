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
import java.net.URISyntaxException;

import org.jabsorb.serializer.AbstractSerializer;
import org.jabsorb.serializer.MarshallException;
import org.jabsorb.serializer.ObjectMatch;
import org.jabsorb.serializer.SerializerState;
import org.jabsorb.serializer.UnmarshallException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Serialises URI's
 */
public class URISerializer extends AbstractSerializer
{
	private static final long serialVersionUID = 4563455823730868040L;

	private static Class<?>[] s_serializableClasses = new Class[] { URI.class };

	private static Class<?>[] s_JSONClasses = new Class[] { JSONObject.class };

	public Class<?>[] getJSONClasses()
	{
		return s_JSONClasses;
	}

	public Class<?>[] getSerializableClasses()
	{
		return s_serializableClasses;
	}

	public Object marshall(SerializerState state, Object p, Object o) throws MarshallException
	{
		if(!(o instanceof URI))
			throw new MarshallException("cannot marshall URI using class " + o.getClass());

		JSONObject obj = new JSONObject();
		try
		{
			if(ser.getMarshallClassHints())
				obj.put("javaClass", o.getClass().getName());
			obj.put("uri", o.toString());
		}
		catch(JSONException e)
		{
			throw new MarshallException(e.getMessage(), e);
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	public ObjectMatch tryUnmarshall(SerializerState state, Class clazz, Object o) throws UnmarshallException
	{
		JSONObject jso = (JSONObject)o;
		try
		{
			jso.getString("uri");
		}
		catch(JSONException e)
		{
			throw new UnmarshallException("no uri", e);
		}
		state.setSerialized(o, ObjectMatch.OKAY);
		return ObjectMatch.OKAY;
	}

	@SuppressWarnings("unchecked")
	public Object unmarshall(SerializerState state, Class clazz, Object o) throws UnmarshallException
	{
		URI returnValue;
		try
		{
			returnValue = new URI(((JSONObject)o).getString("uri"));
		}
		catch(JSONException e)
		{
			throw new UnmarshallException("no uri", e);
		}
		catch(URISyntaxException e)
		{
			throw new UnmarshallException("Could not convert into URI", e);
		}
		state.setSerialized(o, returnValue);
		return returnValue;
	}

}

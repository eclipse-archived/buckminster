/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.p2.remote.IRepositoryDataStream;
import org.eclipse.buckminster.p2.remote.IRepositoryFacade;
import org.eclipse.buckminster.p2.remote.marshall.IUMarshaller;
import org.eclipse.buckminster.p2.remote.marshall.URISerializer;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.jabsorb.JSONRPCBridge;
import org.jabsorb.JSONRPCResult;
import org.jabsorb.JSONSerializer;
import org.jabsorb.client.ErrorResponse;
import org.jabsorb.client.Session;
import org.jabsorb.serializer.AbstractSerializer;
import org.jabsorb.serializer.MarshallException;
import org.jabsorb.serializer.ObjectMatch;
import org.jabsorb.serializer.SerializerState;
import org.jabsorb.serializer.UnmarshallException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Thomas Hallgren
 */
public class Client extends AbstractSerializer implements InvocationHandler
{
	private static final long serialVersionUID = 6731191127498719759L;

	private static Pattern s_stacktraceLine = Pattern.compile("^\\s+at\\s([A-Za-z0-9_.-]+)\\.([A-Za-z0-9_-]+)\\(([A-Za-z0-9_.-]+):([0-9]+)\\)$");

	public static Client create(Session session) throws Exception
	{
		JSONSerializer serializer = new JSONSerializer();
		serializer.registerDefaultSerializers();
		Client client = new Client(session, serializer);
		serializer.registerSerializer(client);
		serializer.registerSerializer(new IUMarshaller());
		serializer.registerSerializer(new URISerializer());
		return client;
	}

	private final WeakHashMap<Object, JSONObject> m_proxies = new WeakHashMap<Object, JSONObject>();

	private final Session m_session;

	private final JSONSerializer m_serializer;

	private Client(Session session, JSONSerializer serializer)
	{
		m_session = session;
		m_serializer = serializer;
	}

	@SuppressWarnings("unchecked")
	public Class[] getJSONClasses()
	{
		return new Class[] { JSONObject.class };
	}

	@SuppressWarnings("unchecked")
	public Class[] getSerializableClasses()
	{
		return new Class[] { IRepositoryDataStream.class, IRepositoryFacade.class };
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		String methodName = method.getName();
		if(methodName.equals("hashCode"))
		{
			return new Integer(System.identityHashCode(proxy));
		}
		else if(methodName.equals("equals"))
		{
			return (proxy == args[0] ? Boolean.TRUE : Boolean.FALSE);
		}
		else if(methodName.equals("toString"))
		{
			return proxy.getClass().getName() + '@' + Integer.toHexString(proxy.hashCode());
		}
		return invoke(m_proxies.get(proxy), method.getName(), args, method.getReturnType());
	}

	public Object marshall(SerializerState state, Object p, Object o) throws MarshallException
	{
		JSONObject json = m_proxies.get(o);
		if(json == null)
			throw new MarshallException("Not a callable reference");
		return json;
	}

	public Object openProxy(String serviceName, Class<?> cls) throws JSONException
	{
		Object result = Proxy.newProxyInstance(cls.getClassLoader(), new Class[] { cls }, this);
		JSONObject jso = new JSONObject();
		jso.put("JSONRPCType", "CallableReference");
		jso.put("javaClass", cls.getName());
		jso.put("objectID", serviceName);
		m_proxies.put(result, jso);
		return result;
	}

	@SuppressWarnings("unchecked")
	public ObjectMatch tryUnmarshall(SerializerState state, Class clazz, Object json)
	throws UnmarshallException
	{
		state.setSerialized(json, ObjectMatch.OKAY);
		return ObjectMatch.OKAY;
	}

	@SuppressWarnings("unchecked")
	public Object unmarshall(SerializerState state, Class clazz, Object json) throws UnmarshallException
	{
		JSONObject jso = (JSONObject)json;
		if("CallableReference".equals(jso.opt("JSONRPCType")))
		{
			try
			{
				Class<?> cls = Class.forName(jso.getString("javaClass"));
				Object result = Proxy.newProxyInstance(cls.getClassLoader(), new Class[] { cls }, this);
				m_proxies.put(result, jso);
				return result;
			}
			catch(Exception e)
			{
				throw new UnmarshallException(e.getMessage(), e);
			}
		}
		throw new UnmarshallException("Not a callable reference");
	}

	/**
	 * Generate and throw exception based on the data in the 'responseMessage'
	 */
	protected void processException(JSONObject responseMessage) throws Exception
	{
		JSONObject error = (JSONObject)responseMessage.get("error");
		if(error == null)
			throw new ErrorResponse(new Integer(JSONRPCResult.CODE_ERR_PARSE), "Unknown response:"
				+ responseMessage.toString(2), null);

		Integer code = new Integer(error.has("code") ? error.getInt("code") : 0);
		String trace = error.has("trace") ? error.getString("trace") : null;
		String msg = error.has("msg") ? error.getString("msg") : null;
		ErrorResponse er = new ErrorResponse(code, msg, trace);
		if(trace == null)
			throw er;

		int colonIdx = trace.indexOf(':');
		if(colonIdx <= 0)
			throw er;

		Class<? extends Exception> exClass = null;
		try
		{
			exClass = Class.forName(trace.substring(0, colonIdx)).asSubclass(Exception.class);
		}
		catch(Exception e)
		{
			throw er;
		}

		Constructor<? extends Exception> exCtorMsgEx = null;
		Constructor<? extends Exception> exCtorStatus = null;
		Constructor<? extends Exception> exCtorMsg = null;
		try
		{
			exCtorMsgEx = exClass.getConstructor(String.class, Throwable.class);
		}
		catch(Exception e)
		{
			try
			{
				exCtorStatus = exClass.getConstructor(IStatus.class);
			}
			catch(Exception e2)
			{
				try
				{
					exCtorMsg = exClass.getConstructor(String.class);
				}
				catch(Exception e3)
				{
				}
			}
		}

		Exception ex = null;
		if(exCtorMsgEx != null)
			ex = exCtorMsgEx.newInstance(msg, er);
		else if(exCtorStatus != null)
			ex = exCtorStatus.newInstance(new Status(IStatus.ERROR, Activator.ID, msg, er));
		else if(exCtorMsg != null)
			ex = exCtorMsg.newInstance(msg);
		else
			throw er;

		ArrayList<StackTraceElement> traces = new ArrayList<StackTraceElement>();
		StringTokenizer tokens = new StringTokenizer(trace, "\n");
		if(tokens.hasMoreTokens())
		{
			tokens.nextToken(); // Skip first line
			while(tokens.hasMoreTokens())
			{
				Matcher m = s_stacktraceLine.matcher(tokens.nextToken());
				if(!m.matches())
					break;

				traces.add(new StackTraceElement(m.group(1), m.group(2), m.group(3),
					Integer.parseInt(m.group(4))));
			}
		}
		ex.setStackTrace(traces.toArray(new StackTraceElement[traces.size()]));
		throw ex;
	}

	private Object invoke(JSONObject jso, String methodName, Object[] args, Class<?> returnType)
	throws Exception
	{
		JSONObject message = new JSONObject();
		Object objId = jso.get("objectID");
		String methodTag;
		if(objId instanceof Integer)
			methodTag = JSONRPCBridge.OBJECT_METHOD_PREFIX + '[' + objId + "].";
		else
			methodTag = jso.getString("objectID") + ".";

		methodTag += methodName;
		message.put("method", methodTag);

		JSONArray params = new JSONArray();
		if(args != null)
		{
			for(int argNo = 0; argNo < args.length; argNo++)
			{
				Object arg = args[argNo];
				SerializerState state = new SerializerState();
				params.put(m_serializer.marshall(state, /* parent */null, arg, new Integer(argNo)));
			}
		}
		message.put("params", params);
		message.put("id", 1);

		JSONObject responseMessage = m_session.sendAndReceive(message);

		if(!responseMessage.has("result"))
			processException(responseMessage);
		Object rawResult = responseMessage.get("result");
		if(rawResult == null)
		{
			processException(responseMessage);
		}
		if(returnType.equals(Void.TYPE))
			return null;
		SerializerState state = new SerializerState();
		return m_serializer.unmarshall(state, returnType, rawResult);
	}
}

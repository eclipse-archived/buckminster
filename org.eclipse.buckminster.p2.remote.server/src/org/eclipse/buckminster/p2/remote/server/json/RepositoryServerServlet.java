/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.server.json;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.buckminster.p2.remote.IRepositoryDataStream;
import org.eclipse.buckminster.p2.remote.IRepositoryFacade;
import org.eclipse.buckminster.p2.remote.IRepositoryServer;
import org.eclipse.buckminster.p2.remote.marshall.IUMarshaller;
import org.eclipse.buckminster.p2.remote.marshall.URISerializer;
import org.eclipse.buckminster.p2.remote.server.MetadataRepositoryFacade;
import org.eclipse.buckminster.p2.remote.server.RepositoryDataStream;
import org.eclipse.buckminster.p2.remote.server.RepositoryServer;
import org.eclipse.buckminster.runtime.Logger;
import org.jabsorb.JSONRPCBridge;
import org.jabsorb.JSONRPCResult;
import org.jabsorb.serializer.AbstractSerializer;
import org.jabsorb.serializer.MarshallException;
import org.jabsorb.serializer.ObjectMatch;
import org.jabsorb.serializer.SerializerState;
import org.jabsorb.serializer.UnmarshallException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Thomas Hallgren
 */
public class RepositoryServerServlet extends HttpServlet
{
	/**
	 * jabsorb version 1.3.0 is not able to handle callable references for return types of interface
	 * type. It can only manage passing the actual server implementation over to the client. This
	 * class is a remedy for that until a better solution is provided in jabsorb.
	 */
	@SuppressWarnings("unchecked")
	public static class CallableInterfaceSerializer extends AbstractSerializer
	{
		private static final long serialVersionUID = -5868861157010733475L;

		private final JSONRPCBridge m_bridge;

		private static final Class<?>[] s_jsonClasses = new Class<?>[] { JSONObject.class };

		private static final Class<?>[] s_concreteClasses = new Class<?>[] { MetadataRepositoryFacade.class,
			RepositoryDataStream.class };

		private static final Class<?>[] s_interfaceClasses = new Class<?>[] { IRepositoryFacade.class,
			IRepositoryDataStream.class };

		public CallableInterfaceSerializer(JSONRPCBridge bridge)
		{
			m_bridge = bridge;
		}

		public Class[] getJSONClasses()
		{
			return s_jsonClasses;
		}

		public Class[] getSerializableClasses()
		{
			return s_concreteClasses;
		}

		public Class[] getSerializableInterfaces()
		{
			return s_interfaceClasses;
		}

		public Object marshall(SerializerState state, Object p, Object o) throws MarshallException
		{
			Class oc = o.getClass();
			int idx = s_concreteClasses.length;
			while(--idx >= 0)
			{
				Class<?> c = s_concreteClasses[idx];
				if(c.equals(oc))
				{
					try
					{
						Integer identity = new Integer(System.identityHashCode(o));
						JSONObject jso = new JSONObject();
						jso.put("JSONRPCType", "CallableReference");
						jso.put("javaClass", s_interfaceClasses[idx].getName());
						jso.put("objectID", identity);
						m_bridge.registerObject(identity, o);
						return jso;
					}
					catch(JSONException e)
					{
						throw new MarshallException(e.getMessage());
					}
				}
			}
			throw new MarshallException("Unable to marshal an instance of " + oc);
		}

		public ObjectMatch tryUnmarshall(SerializerState state, Class clazz, Object json)
		throws UnmarshallException
		{
			state.setSerialized(json, ObjectMatch.OKAY);
			return ObjectMatch.OKAY;
		}

		public Object unmarshall(SerializerState state, Class clazz, Object json) throws UnmarshallException
		{
			JSONObject jso = (JSONObject)json;
			Object ref = null;
			String json_type;
			int object_id;
			try
			{
				json_type = jso.getString("JSONRPCType");
				object_id = jso.getInt("objectID");
			}
			catch(JSONException e)
			{
				throw new UnmarshallException(e.getMessage(), e);
			}
			if(json_type != null && json_type.equals("CallableReference"))
				ref = m_bridge.getReference(object_id);

			state.setSerialized(jso, ref);
			return ref;
		}
	}

	private static final long serialVersionUID = 2598977451925554498L;

	/**
	 * The size of the buffer used for reading requests
	 */
	private final static int BUFFER_SIZE = 0x8000;

	private static URI getRequestURI(HttpServletRequest request) throws ServletException
	{
		try
		{
			return new URI(request.getRequestURL().toString());
		}
		catch(URISyntaxException e)
		{
			throw new ServletException(e);
		}
	}

	/**
	 * Called when a JSON-RPC requests comes in. Looks in the session for a JSONRPCBridge and if not
	 * found there, uses the global bridge; then passes off the JSON-PRC call to be handled by the
	 * JSONRPCBridge found.
	 * @param request servlet request from browser.
	 * @param response servlet response to browser.
	 * @throws IOException if an IOException occurs during processing.
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException,
		IOException
	{
		response.setContentType("text/plain;charset=utf-8");
		String charset = request.getCharacterEncoding();
		if(charset == null)
			charset = "UTF-8";
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), charset));

		CharArrayWriter data = new CharArrayWriter();
		char buf[] = new char[BUFFER_SIZE];
		int ret;
		while((ret = in.read(buf, 0, BUFFER_SIZE)) != -1)
		{
			data.write(buf, 0, ret);
		}

		JSONRPCResult jsonResponse;
		try
		{
			String receiveString = data.toString();
			if(receiveString.length() == 0)
			{
				// This is a request to establish the session
				//
				establishSession(request, response);
				return;
			}
			JSONRPCBridge bridge = findBridge(request);
			JSONObject jsonRequest = new JSONObject(receiveString);
			jsonResponse = bridge.call(new Object[] { request, response }, jsonRequest);
		}
		catch(JSONException e)
		{
			jsonResponse = new JSONRPCResult(JSONRPCResult.CODE_ERR_PARSE, null, JSONRPCResult.MSG_ERR_PARSE);
		}

		String sendString = jsonResponse.toString();
		byte[] bout = sendString.getBytes("UTF-8");
		response.setIntHeader("Content-Length", bout.length);

		OutputStream out = response.getOutputStream();
		out.write(bout);
		out.flush();
		out.close();
	}

	private void establishSession(HttpServletRequest request, HttpServletResponse response)
	throws ServletException
	{
		Cookie cookie = new Cookie("buckminster.p2.remote", UUID.randomUUID().toString());
		response.addCookie(cookie);

		HttpSession session = request.getSession(true);
		final JSONRPCBridge bridge = new JSONRPCBridge();
		try
		{
			bridge.registerReference(MetadataRepositoryFacade.class);
			bridge.registerReference(IRepositoryFacade.class);
			bridge.registerReference(IRepositoryDataStream.class);
			bridge.registerSerializer(new CallableInterfaceSerializer(bridge));
			bridge.registerSerializer(new IUMarshaller());
			bridge.registerSerializer(new URISerializer());
			bridge.registerObject(IRepositoryServer.SERVICE_NAME, RepositoryServer.getServer(
				getRequestURI(request), JSONRPCBridge.getSerializer()), IRepositoryServer.class);

		}
		catch(RuntimeException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
		session.setAttribute("JSONRPCBridge", bridge);
		Logger.setConsoleLevelThreshold(Logger.DEBUG);
	}

	private JSONRPCBridge findBridge(HttpServletRequest request) throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		if(session == null)
			throw new ServletException("No session has been established");

		JSONRPCBridge bridge = (JSONRPCBridge)session.getAttribute("JSONRPCBridge");
		if(bridge == null)
			throw new ServletException("JSONRPCBridge was not found in current session");
		return bridge;
	}
}

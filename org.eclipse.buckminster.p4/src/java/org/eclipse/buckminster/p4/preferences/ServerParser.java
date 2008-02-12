/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.p4.preferences;

import java.io.InputStream;

import org.eclipse.buckminster.core.parser.AbstractParser;
import org.eclipse.buckminster.core.parser.ISAXParser;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


/**
 * @author Thomas Hallgren
 */
public class ServerParser extends AbstractParser<Server> implements ChildPoppedListener
{
	public interface IAskReplaceOK
	{
		boolean isReplaceOK(String serverName);
	}

	public static class ReplaceDeniedException extends SAXException
	{
		private static final long serialVersionUID = -7137084066639108641L;		
	}

	private Server m_server;

	private final IAskReplaceOK m_askReplaceOK;

	public ServerParser(IAskReplaceOK askReplaceOK) throws CoreException
	{
		super(null, new String[]
   		{
   			Server.BM_SERVER_NS
   		}, new String[]
   		{
   			Server.BM_SERVER_RESOURCE
   		}, true);
		m_askReplaceOK = askReplaceOK;
	}

	public Server parse(String systemId, InputStream stream) throws CoreException
	{
		this.parseInput(systemId, stream);
		return m_server;
	}

	class ServerSAXParser extends SAXParserWrapper implements ISAXParser<Server>
	{
		public Server getResult()
		{
			return m_server;
		}
	}

	public ISAXParser<Server> getSAXParser()
	{
		return new ServerSAXParser();
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		m_server = ((ServerHandler)child).getServer();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs)
	throws SAXException
	{
		if(ServerHandler.TAG.equals(localName))
		{
			ServerHandler ch = new ServerHandler(this);
			this.pushHandler(ch, attrs);
		}
		else
			super.startElement(uri, localName, qName, attrs);
	}

	boolean isReplaceOK(String serverName)
	{
		return m_askReplaceOK == null ? true : m_askReplaceOK.isReplaceOK(serverName);
	}
}

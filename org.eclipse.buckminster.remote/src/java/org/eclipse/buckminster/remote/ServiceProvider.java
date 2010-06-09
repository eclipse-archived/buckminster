/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.remote;

/**
 * @author Karel Brezina
 * 
 */
public class ServiceProvider implements IServiceProvider
{
	private String m_id;

	private String m_name;

	private String m_host;

	private String m_initializationPath;

	private String m_servicePath;

	private String m_port;

	private String m_protocol;

	private boolean m_loginSupport;

	/**
	 * Creates a remote service provider
	 * 
	 * @param id
	 * @param name
	 * @param protocol
	 * @param host
	 * @param port
	 * @param initializationPath
	 * @param servicePath
	 * @param loginSupport
	 */
	public ServiceProvider(String id, String name, String protocol, String host, String port,
			String initializationPath, String servicePath, boolean loginSupport)
	{
		super();
		m_id = id;
		m_name = name;
		m_host = host;
		m_initializationPath = initializationPath;
		m_servicePath = servicePath;
		m_port = port;
		m_protocol = protocol;
		m_loginSupport = loginSupport;
	}

	public String getHost()
	{
		return m_host;
	}

	public String getId()
	{
		return m_id;
	}

	public String getInitializationPath()
	{
		return m_initializationPath;
	}

	public String getName()
	{
		return m_name;
	}

	public String getPort()
	{
		return m_port;
	}

	public String getProtocol()
	{
		return m_protocol;
	}

	public String getServicePath()
	{
		return m_servicePath;
	}

	public boolean isLoginSupported()
	{
		return m_loginSupport;
	}
}

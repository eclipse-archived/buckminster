/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.cloudsmith.accountservice;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.eclipse.buckminster.core.helpers.CryptoUtils;
import org.eclipse.buckminster.jnlp.accountservice.IAuthenticator;
import org.eclipse.buckminster.jnlp.cloudsmith.accountservice.AccountServiceLoginResponse;
import org.eclipse.buckminster.jnlp.cloudsmith.accountservice.IAccountService;
import org.jabsorb.client.Client;
import org.jabsorb.client.ErrorResponse;
import org.jabsorb.client.HTTPSession;
import org.jabsorb.client.TransportRegistry;

/**
 * @author Karel Brezina
 * 
 */
public class Authenticator implements IAuthenticator
{
	abstract class MethodWrapper<T>
	{
		abstract public T process() throws Exception;
		
		public T run() throws Exception
		{
			if(m_currentStatus == AuthenticationStatus.BEFORE_INIT && m_remoteAccountService == null)
			{
				throw new Exception("Authenticator is not initialized - initialize method needs to be called");			
			}

			try
			{
				return process();
			}
			catch(ErrorResponse e)
			{
				try
				{
					refresh();

					return process();
				}
				catch(ErrorResponse e1)
				{
					throw new Exception("Cannot connect to the remote authentication service", e1);
				}
			}
		}
	}
	
	private enum AuthenticationStatus
	{
		BEFORE_INIT, AFTER_INIT, AFTER_LOGIN
	}
	
	private static final String JSON_RPC_BRIDGE_URL_SUFFIX = "json-rpc-bridge/accountService";

	private static final String JSON_RPC_URL_SUFFIX = "json-rpc/accountService";

	private static final String ENCRYPT_ALGORITHM = "SHA-256";
	
	private AuthenticationStatus m_currentStatus = AuthenticationStatus.BEFORE_INIT;
	
	private String m_lastLoginUserName;
	
	private String m_lastLoginPassword;

	private String m_lastLoginKey;
	
	private AccountServiceLoginResponse m_lastLoginResponse;

	private String m_serviceURL;
	
	private IAccountService m_remoteAccountService = null;
	
	private HttpClient m_httpClient;

	public void initialize(String serviceURL) throws Exception
	{
		if(serviceURL == null)
		{
			throw new Exception("Service URL is missing");
		}
		
		m_serviceURL = serviceURL;
		
		m_httpClient = new HttpClient();
		HttpState httpState = new HttpState();
		m_httpClient.setState(httpState);
		
		GetMethod method = null;
		
		try
		{
			method = new GetMethod(serviceURL + JSON_RPC_BRIDGE_URL_SUFFIX);
			int status = m_httpClient.executeMethod(method);
			if(status != HttpStatus.SC_OK)
				throw new IOException("Setup did not succeed - make sure the AccountServiceServlet servlet is running on " + serviceURL);
		}
		finally
		{
			if(method != null)
				method.releaseConnection();
		}

		TransportRegistry registry = new TransportRegistry();

		HTTPSession.register(registry);

		HTTPSession session = (HTTPSession)registry.createSession(serviceURL + JSON_RPC_URL_SUFFIX);

		// Pass the the session set up earlier
		session.setState(httpState);

		Client jsonClient = new Client(session);
		
		m_remoteAccountService = (IAccountService)jsonClient.openProxy("accountService", IAccountService.class);
		
		m_currentStatus = AuthenticationStatus.AFTER_INIT;
	}

	public IAuthenticator createDuplicate(boolean login) throws Exception
	{
		Authenticator authenticator = new Authenticator();
		authenticator.initialize(m_serviceURL);
		
		if(m_currentStatus == AuthenticationStatus.AFTER_LOGIN && login)
		{
			if(m_lastLoginKey != null)
				authenticator.login(m_lastLoginKey);
			else
				authenticator.login(m_lastLoginUserName, m_lastLoginPassword);
		}
		
		return authenticator;
	}
	
	public HttpClient getHttpClient()
	{
		return m_httpClient;
	}

	public boolean isLoggedIn() throws Exception
	{
		MethodWrapper<Boolean> method = new MethodWrapper<Boolean>()
		{

			@Override
			public Boolean process() throws Exception
			{
				return Boolean.valueOf(m_remoteAccountService.isLoggedIn());
			}
		};

		return method.run().booleanValue();
	}

	public int login(final String userName, final String password) throws Exception
	{
		MethodWrapper<AccountServiceLoginResponse> method = new MethodWrapper<AccountServiceLoginResponse>()
		{

			@Override
			public AccountServiceLoginResponse process() throws Exception
			{
				return m_remoteAccountService.login(userName, CryptoUtils.encrypt(password, ENCRYPT_ALGORITHM));
			}
		};

		m_lastLoginResponse = method.run();
		int result = m_lastLoginResponse.getResponseId();
		
		if(result == LOGIN_OK)
		{
			m_currentStatus = AuthenticationStatus.AFTER_LOGIN;
			m_lastLoginUserName = userName;
			m_lastLoginPassword = password;
			m_lastLoginKey = null;
		}
		else
		{
			m_lastLoginResponse = null;
		}

		return result;
	}

	public int relogin(final String userName, final String password) throws Exception
	{
		if(isLoggedIn())
		{
			if(userName != null && userName.equals(m_lastLoginUserName) && password != null && password.equals(m_lastLoginPassword))
				return IAuthenticator.LOGIN_OK;
			
			logout();
		}
		
		return login(userName, password);
	}
	
	public int login(final String loginKey) throws Exception
	{
		MethodWrapper<AccountServiceLoginResponse> method = new MethodWrapper<AccountServiceLoginResponse>()
		{

			@Override
			public AccountServiceLoginResponse process() throws Exception
			{
				return m_remoteAccountService.login(loginKey);
			}
		};

		m_lastLoginResponse = method.run();
		int result = m_lastLoginResponse.getResponseId();
		
		if(result == LOGIN_OK)
		{
			m_currentStatus = AuthenticationStatus.AFTER_LOGIN;
			m_lastLoginUserName = null;
			m_lastLoginPassword = null;
			m_lastLoginKey = loginKey;
		}
		else
		{
			m_lastLoginResponse = null;
		}

		return result;
	}

	public int relogin(final String loginKey) throws Exception
	{
		if(isLoggedIn())
		{
			if(loginKey != null && loginKey.equals(m_lastLoginKey))
				return IAuthenticator.LOGIN_OK;
			
			logout();
		}
		
		return login(loginKey);
	}
	
	public String getCurrenlyLoggedUserName()
	{
		return m_lastLoginResponse == null ? null : m_lastLoginResponse.getUserName();
	}
	
	public String getLoginKey() throws Exception
	{
		MethodWrapper<String> method = new MethodWrapper<String>()
		{

			@Override
			public String process() throws Exception
			{
				return m_remoteAccountService.getLoginKey();
			}
		};

		return method.run();
	}

	public void keepAlive() throws Exception
	{
		MethodWrapper<Boolean> method = new MethodWrapper<Boolean>()
		{

			@Override
			public Boolean process() throws Exception
			{
				m_remoteAccountService.keepAlive();
				return Boolean.valueOf(true);
			}
		};

		method.run();
	}


	private int logoutWithoutRefreshingConnection() throws Exception
	{
		MethodWrapper<Integer> method = new MethodWrapper<Integer>()
		{

			@Override
			public Integer process() throws Exception
			{
				return Integer.valueOf(m_remoteAccountService.logoutSession());
			}
		};
		
		int result;
		
		try
		{
			result = method.run().intValue();
		}
		finally
		{
			m_currentStatus = AuthenticationStatus.BEFORE_INIT;
			m_remoteAccountService = null;
		}
		
		return result;
	}
	
	public int logout() throws Exception
	{
		int result;
		
		try
		{
			result = logoutWithoutRefreshingConnection();
		}
		finally
		{
			initialize(m_serviceURL);
		}
		
		return result;
	}
	
	public void releaseConnection() throws Exception
	{
		logoutWithoutRefreshingConnection();
	}

	public int register(final String userName, final String password, final String email) throws Exception
	{
		MethodWrapper<Integer> method = new MethodWrapper<Integer>()
		{

			@Override
			public Integer process() throws Exception
			{
				return Integer.valueOf(m_remoteAccountService.register(userName, CryptoUtils.encrypt(password, ENCRYPT_ALGORITHM), email));
			}
		};

		return method.run().intValue();
	}

	public int checkSpaceReadAccess(final String spaceName) throws Exception
	{
		MethodWrapper<Integer> method = new MethodWrapper<Integer>()
		{

			@Override
			public Integer process() throws Exception
			{
				return Integer.valueOf(m_remoteAccountService.checkFolderReadAccess(spaceName));
			}
		};

		return method.run().intValue();
	}

	// refreshes connection after session timeout
	private void refresh() throws Exception
	{
		if(m_currentStatus == AuthenticationStatus.BEFORE_INIT)
			return;
		
		initialize(m_serviceURL);
		
		if(m_currentStatus == AuthenticationStatus.AFTER_INIT)
			return;

		if(m_lastLoginKey != null)
			login(m_lastLoginKey);
		else
			login(m_lastLoginUserName, m_lastLoginPassword);
	}
}

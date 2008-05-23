/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.cloudsmith.accountservice;

import java.io.Serializable;

/**
 * Response of IAccountService.login()
 * 
 * @author Karel Brezina
 */
public class AccountServiceLoginResponse implements Serializable
{
	private static final long serialVersionUID = -978349240463870537L;

	private int m_responseId;
	
	private String m_loginKey;
	
	private long m_userId;
	
	private String m_userName;
	
	private String m_userRealName;

	public AccountServiceLoginResponse()
	{
	}
	
	public AccountServiceLoginResponse(int responseid)
	{
		this(responseid, null, -1, null, null);
	}

	public AccountServiceLoginResponse(int responseid, String loginKey, long userId, String userName, String userRealName)
	{
		m_responseId = responseid;
		m_loginKey = loginKey;
		m_userId = userId;
		m_userName = userName;
		m_userRealName = userRealName;
	}

	public int getResponseId()
	{
		return m_responseId;
	}

	public void setResponseId(int responseid)
	{
		m_responseId = responseid;
	}

	public String getLoginKey()
	{
		return m_loginKey;
	}

	public void setLoginKey(String loginKey)
	{
		m_loginKey = loginKey;
	}

	public long getUserId()
	{
		return m_userId;
	}

	public void setUserId(long userId)
	{
		m_userId = userId;
	}	

	public String getUserName()
	{
		return m_userName;
	}

	public void setUserName(String userName)
	{
		m_userName = userName;
	}

	public String getUserRealName()
	{
		return m_userRealName;
	}

	public void setUserRealName(String userRealName)
	{
		m_userRealName = userRealName;
	}
}

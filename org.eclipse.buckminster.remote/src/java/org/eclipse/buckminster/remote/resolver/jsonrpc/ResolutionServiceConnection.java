/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.remote.resolver.jsonrpc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.remote.IAuthenticatedConnection;
import org.eclipse.buckminster.remote.IProgressInfo;
import org.eclipse.buckminster.remote.IServiceProvider;
import org.eclipse.buckminster.remote.Messages;
import org.eclipse.buckminster.remote.ProviderUtil;
import org.eclipse.buckminster.remote.resolver.IResolutionServiceConnection;
import org.eclipse.buckminster.remote.resolver.service.IRemoteResolutionService;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;
import org.jabsorb.client.Client;
import org.jabsorb.client.ErrorResponse;
import org.jabsorb.client.HTTPSession;
import org.jabsorb.client.TransportRegistry;

/**
 * Connection to a remote resolution server using JABSORB
 * 
 * @author Karel Brezina
 * 
 */
public class ResolutionServiceConnection implements IResolutionServiceConnection, IAuthenticatedConnection
{
	private static final String ENCRYPT_ALGORITHM = "SHA-256"; //$NON-NLS-1$

	private static final int DEFAULT_TIMEOUT = 60000;

	private static String encrypt(String input, String algorithmName)
	{
		String md5val = ""; //$NON-NLS-1$
		MessageDigest algorithm = null;

		try
		{
			algorithm = MessageDigest.getInstance(algorithmName);
		}
		catch(NoSuchAlgorithmException nsae)
		{
			throw new IllegalArgumentException(NLS.bind(Messages.unknown_encrypt_algorithm_0, algorithmName));
		}

		byte[] defaultBytes = input.getBytes();
		algorithm.reset();
		algorithm.update(defaultBytes);
		byte messageDigest[] = algorithm.digest();
		StringBuffer hexString = new StringBuffer();

		for(int i = 0; i < messageDigest.length; i++)
		{
			String hex = Integer.toHexString(0xFF & messageDigest[i]);
			if(hex.length() == 1)
			{
				hexString.append('0');
			}
			hexString.append(hex);
		}
		md5val = hexString.toString();
		return md5val;
	}

	private static IServiceProvider getProvider(String providerID) throws CoreException
	{
		if(providerID == null || providerID.length() == 0)
		{
			throw BuckminsterException.fromMessage(Messages.remote_service_provider_not_set);
		}

		return ProviderUtil.findProvider(providerID);
	}

	private IServiceProvider m_serviceProvider;

	private String m_initializationURL;

	private String m_serviceURL;

	private String m_login;

	private String m_password;

	private boolean m_connectionUsed;

	private IRemoteResolutionService m_remoteResolutionService = null;

	private HttpClient m_httpClient;

	private boolean m_isCancelSent;

	private boolean m_isDone;

	private int m_timeout;

	public ResolutionServiceConnection(IServiceProvider provider, String login, String password) throws CoreException
	{
		this(provider, login, password, DEFAULT_TIMEOUT);
	}

	public ResolutionServiceConnection(IServiceProvider provider, String login, String password, int timeout)
			throws CoreException
	{
		if(provider == null)
		{
			throw BuckminsterException.fromMessage(Messages.remote_service_provider_not_set);
		}

		m_serviceProvider = provider;
		m_initializationURL = ProviderUtil.getInitializationPoint(provider);
		m_serviceURL = ProviderUtil.getTargetPoint(provider);
		m_login = login;
		m_password = password;
		m_connectionUsed = false;
		m_timeout = timeout;

		initialize();
	}

	public ResolutionServiceConnection(String providerID, String login, String password) throws CoreException
	{
		this(getProvider(providerID), login, password);
	}

	public void cancel() throws CoreException
	{
		checkInitialization();

		try
		{
			m_remoteResolutionService.cancel();
		}
		catch(ErrorResponse e)
		{
			throw BuckminsterException.fromMessage(e, Messages.remote_res_error_cancel);
		}
		finally
		{
			m_connectionUsed = true;
		}

		m_isCancelSent = true;
	}

	public void fireQueryResolution(BillOfMaterials bom) throws CoreException
	{
		checkInitialization();

		ByteArrayOutputStream bout;
		try
		{
			bout = new ByteArrayOutputStream();
			GZIPOutputStream gout = new GZIPOutputStream(bout);
			Utils.serializeUgly(bom, gout);
			gout.close();
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}

		byte[] b64GzipBomImage = Base64.encodeBase64(bout.toByteArray());

		try
		{
			m_remoteResolutionService.fireBomResolution(b64GzipBomImage);
		}
		catch(ErrorResponse e)
		{
			throw BuckminsterException.fromMessage(e, Messages.remote_res_error_BOM);
		}
		finally
		{
			m_connectionUsed = true;
		}
	}

	public HttpClient getHttpClient()
	{
		return m_httpClient;
	}

	public IProgressInfo getProgressInfo() throws CoreException
	{
		checkInitialization();

		IProgressInfo progressInfo;
		try
		{
			progressInfo = m_remoteResolutionService.getProgressInfo();
		}
		catch(ErrorResponse e)
		{
			throw BuckminsterException.fromMessage(e, Messages.remote_res_error_progress_info);
		}
		finally
		{
			m_connectionUsed = true;
		}

		if(progressInfo.isDone())
			m_isDone = true;

		return progressInfo;
	}

	public BillOfMaterials getResolutionResult() throws CoreException
	{
		checkInitialization();

		byte[] b64GzipBomImage;

		try
		{
			b64GzipBomImage = m_remoteResolutionService.getResolutionResult();
		}
		catch(ErrorResponse e)
		{
			throw BuckminsterException.fromMessage(e, Messages.remote_res_error_resolution_result);
		}
		finally
		{
			m_connectionUsed = true;
		}

		if(b64GzipBomImage == null)
			return null;

		try
		{
			byte[] gzipBomImage = Base64.decodeBase64(b64GzipBomImage);

			InputStream bomInStream = new GZIPInputStream(new ByteArrayInputStream(gzipBomImage));

			IParser<BillOfMaterials> parser = CorePlugin.getDefault().getParserFactory().getBillOfMaterialsParser(true);

			return parser.parse("byte image", bomInStream); //$NON-NLS-1$
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public void initialize() throws CoreException
	{
		m_connectionUsed = false;
		m_httpClient = new HttpClient();
		HttpState httpState = new HttpState();
		m_httpClient.setState(httpState);
		DefaultHttpParams.getDefaultParams().setParameter(HttpMethodParams.SO_TIMEOUT, Integer.valueOf(m_timeout));

		GetMethod method = null;

		try
		{
			method = new GetMethod(m_initializationURL);
			int status = m_httpClient.executeMethod(method);
			if(status != HttpStatus.SC_OK)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.setup_fail_check_service_on_0, m_serviceURL));
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			if(method != null)
				method.releaseConnection();
		}

		TransportRegistry registry = new TransportRegistry();

		HTTPSession.register(registry);

		HTTPSession session = (HTTPSession)registry.createSession(m_serviceURL);

		// Pass the the session set up earlier
		session.setState(httpState);

		Client jsonClient = new Client(session);

		m_remoteResolutionService = (IRemoteResolutionService)jsonClient.openProxy("resolutionService", //$NON-NLS-1$
				IRemoteResolutionService.class);

		m_isCancelSent = false;
		m_isDone = false;

		// login if credentials are provided
		if(m_login != null && m_login.length() > 0 && m_password != null && m_password.length() > 0)
		{
			int result = login(m_login, m_password);
			if(result == IAuthenticatedConnection.LOGIN_FAILED)
				throw new RuntimeException(NLS.bind(Messages.login_to_service_by_provider_0_failed, m_serviceProvider
						.getName())); //$NON-NLS-1$
		}
	}

	public boolean isCancelSent()
	{
		return m_isCancelSent;
	}

	public boolean isDone()
	{
		return m_isDone;
	}

	public boolean isLoggedIn() throws CoreException
	{
		checkInitialization();

		boolean result;
		try
		{
			result = m_remoteResolutionService.isLoggedIn();
		}
		catch(ErrorResponse e)
		{
			throw BuckminsterException.fromMessage(e, Messages.remote_res_error_login_info);
		}

		return result;
	}

	/**
	 * Login to the remote service
	 * 
	 * @param userName
	 *            username
	 * @param password
	 *            password encrypted by SHA-256
	 * @return
	 * @throws CoreException
	 */
	public int login(final String userName, final String password) throws CoreException
	{
		checkInitialization();

		int result;

		try
		{
			result = m_remoteResolutionService.login(userName, encrypt(password, ENCRYPT_ALGORITHM));
		}
		catch(ErrorResponse e)
		{
			throw BuckminsterException.fromMessage(e, Messages.remote_res_error_login_failed);
		}

		if(result == IRemoteResolutionService.LOGIN_OK)
			return IAuthenticatedConnection.LOGIN_OK;

		return IAuthenticatedConnection.LOGIN_FAILED;
	}

	public void logout() throws CoreException
	{
		checkInitialization();

		try
		{
			m_remoteResolutionService.logout();
		}
		catch(ErrorResponse e)
		{
			throw BuckminsterException.fromMessage(e, Messages.remote_res_error_logout_failed);
		}
		finally
		{
			m_remoteResolutionService = null;
			m_connectionUsed = true;
		}
	}

	public void releaseConnection() throws CoreException
	{
		try
		{
			logout();
		}
		catch(CoreException e)
		{
			// session might have timed out
		}
	}

	public void reset() throws CoreException
	{
		if(m_connectionUsed)
		{
			if(m_remoteResolutionService != null)
				logout();

			initialize();
		}
	}

	private void checkInitialization() throws CoreException
	{
		if(m_remoteResolutionService == null)
			throw BuckminsterException.fromMessage(Messages.remote_res_service_not_initialized);
	}
}

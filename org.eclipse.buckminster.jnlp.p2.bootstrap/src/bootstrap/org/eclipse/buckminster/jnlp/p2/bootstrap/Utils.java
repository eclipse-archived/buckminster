/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.p2.bootstrap;

import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_DEFAULT_BROWSER_NOT_AVAILABLE_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_JAVA_RUNTIME_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_REMOTE_IO_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.REPORT_ERROR_PREFIX;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.REPORT_ERROR_VIEW;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import sun.misc.BASE64Encoder;

/**
 * @author Filip Hrbek
 * 
 *         Useful utilities.
 */
public class Utils
{
	public static String UTF_8 = "UTF-8"; //$NON-NLS-1$

	public static String NEW_LINE_SEPARATOR = "\n"; //$NON-NLS-1$

	public static void close(Closeable closeable)
	{
		if(closeable != null)
		{
			try
			{
				closeable.close();
			}
			catch(IOException e)
			{
			}
		}
	}

	public static String createExceptionMessage(Throwable throwable)
	{
		String[] systemProps = { "os.name", "os.arch", "user.country", "java.vendor", "java.version" };

		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append(Messages.getString("environment_colon"));
		messageBuilder.append(NEW_LINE_SEPARATOR);

		for(String prop : systemProps)
		{
			messageBuilder.append(prop);
			messageBuilder.append("=");
			messageBuilder.append(System.getProperty(prop));
			messageBuilder.append(NEW_LINE_SEPARATOR);
		}

		messageBuilder.append(NEW_LINE_SEPARATOR);
		messageBuilder.append(Messages.getString("stack_trace_colon"));
		messageBuilder.append(NEW_LINE_SEPARATOR);

		StringWriter stackTrace = new StringWriter();
		PrintWriter stackTraceWriter = null;
		try
		{
			stackTraceWriter = new PrintWriter(stackTrace);
			throwable.printStackTrace(stackTraceWriter);
		}
		finally
		{
			if(stackTraceWriter != null)
			{
				stackTraceWriter.flush();
				stackTraceWriter.close();
			}
		}

		messageBuilder.append(stackTrace);

		return messageBuilder.toString();
	}

	/**
	 * Creates a hash code for specified url, suitable for creating a folder in the cache structure.
	 * 
	 * @param jnlp
	 * @return
	 */
	public static String createHash(URL jnlp)
	{
		MessageDigest md;

		try
		{
			md = MessageDigest.getInstance("MD5"); //$NON-NLS-1$
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}

		return new BASE64Encoder().encode(md.digest(jnlp.toString().getBytes()));
	}

	public static Image createImage(byte[] imageData)
	{
		return imageData != null
				? Toolkit.getDefaultToolkit().createImage(imageData)
				: null;
	}

	public static Image createImage(String url) throws JNLPException
	{
		return createImage(loadData(url));
	}

	public static String createMailtoURL(String to, String subject, String body)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("mailto:");
		sb.append(mailtoEncode(to));
		sb.append("?subject=");
		sb.append(mailtoEncode(subject));
		sb.append("&body=");
		sb.append(mailtoEncode(body));

		return sb.toString();
	}

	public static void deleteRecursive(File file) throws JNLPException
	{
		if(!file.exists())
			return;

		try
		{
			File[] list = file.listFiles();
			int count = (list == null)
					? 0
					: list.length;
			if(count > 0)
			{
				while(--count >= 0)
					deleteRecursive(list[count]);
			}

			if(!file.delete() && file.exists())
				throw new JNLPException(
						Messages.getString("unable_to_delete") + file.getAbsolutePath(), Messages.getString("check_file_permissions"), //$NON-NLS-1$ //$NON-NLS-2$
						BootstrapConstants.ERROR_CODE_FILE_IO_EXCEPTION);
		}
		catch(SecurityException e)
		{
			throw new JNLPException(
					Messages.getString("unable_to_delete") + file.getAbsolutePath() + ": " + e.getMessage(), //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("check_file_permissions"), BootstrapConstants.ERROR_CODE_FILE_IO_EXCEPTION, e); //$NON-NLS-1$
		}
	}

	public static void emailException(String recipient, String subject, Throwable throwable) throws JNLPException
	{
		String mailtoURL = createMailtoURL(recipient, subject, createExceptionMessage(throwable));

		showInBrowser(mailtoURL);
	}

	/**
	 * Formats a timestamp to a format suitable for easy cache management.
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date)
	{
		return formatDate(Long.valueOf(date.getTime()));
	}

	/**
	 * Formats a timestamp to a format suitable for easy cache management.
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String formatDate(Long timestamp)
	{
		return String.format("%d", timestamp); //$NON-NLS-1$
	}

	/**
	 * This method prepares argument with proxy information which will be passed to the application. Notice that there
	 * arguments don't set system properties, they are supposed to be parsed in the application to set up the proxy
	 * rules internally.
	 * 
	 * The algorithm of getting proxy information is not ideal since the proxy selector might use non-trivial rules.
	 * However, we don't know which proxy selector implementation will handle our requests and there is no way of
	 * retrieving all the proxy rules.
	 * 
	 * Let's keep it simple - we try to use dummy addresses for the most common protocols. This will guarantee that we
	 * inherit most probable browser proxy settings.
	 * 
	 * If the rules are not guessed optimally, there should be an option in the launched application to override
	 * automatic proxy discovery with user's own rules, with the possibility to persist the settings in the application
	 * installation directory.
	 * 
	 * @return
	 * @throws JNLPException
	 * @throws URISyntaxException
	 */
	public static List<String> getProxySettings() throws JNLPException
	{
		List<String> args;
		try
		{
			args = new ArrayList<String>();
			ProxySelector proxySelector = ProxySelector.getDefault();

			for(URI uri : new URI[] { new URI("http://dummy.host.com"), new URI("https://dummy.host.com"), //$NON-NLS-1$ //$NON-NLS-2$
					new URI("ftp://dummy.host.com") }) //$NON-NLS-1$
			{
				List<Proxy> proxies = proxySelector.select(uri);
				String protocol = uri.getScheme();

				for(Proxy proxy : proxies)
				{
					if(Proxy.NO_PROXY.equals(proxy))
						break;

					SocketAddress address = proxy.address();
					if(address instanceof InetSocketAddress)
					{
						InetSocketAddress iaddr = (InetSocketAddress)address;
						args.add("-D" + protocol + ".proxyHost"); //$NON-NLS-1$ //$NON-NLS-2$
						args.add(iaddr.getHostName());
						args.add("-D" + protocol + ".proxyPort"); //$NON-NLS-1$ //$NON-NLS-2$
						args.add("" + iaddr.getPort()); //$NON-NLS-1$
						args.add("-D" + protocol + ".nonProxyHosts"); //$NON-NLS-1$ //$NON-NLS-2$
						args.add("localhost|127.0.0.1"); //$NON-NLS-1$
						break;
					}
				}
			}
		}
		catch(URISyntaxException e)
		{
			throw new JNLPException(
					Messages.getString("unable_to_detect_proxy_settings"), Messages.getString("report_the_problem"), //$NON-NLS-1$ //$NON-NLS-2$
					ERROR_CODE_JAVA_RUNTIME_EXCEPTION);
		}

		return args;
	}

	public static byte[] loadData(String url) throws JNLPException
	{
		byte[] data = null;
		if(url != null)
		{
			InputStream is = null;
			try
			{
				is = new URL(url).openStream();
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] buf = new byte[0x1000];
				int count;
				while((count = is.read(buf)) > 0)
					os.write(buf, 0, count);
				data = os.toByteArray();

			}
			catch(IOException e)
			{
				throw new JNLPException(
						Messages.getString("unable_to_read_a_splash_screen_or_window_icon_image"), //$NON-NLS-1$
						Messages.getString("check_your_internet_connection_and_try_again"), ERROR_CODE_REMOTE_IO_EXCEPTION, e); //$NON-NLS-1$
			}
			finally
			{
				Utils.close(is);
			}
		}

		return data;
	}

	public static String mailtoEncode(String input)
	{
		String output;

		try
		{
			output = URLEncoder.encode(input, UTF_8);
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}

		return output.replace("+", "%20");
	}

	/**
	 * Converts a single -extra string parameter into a list of parameters. Parameters are delimited by space. Example:
	 * -extra "-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=y"
	 * 
	 * @param args
	 * @return
	 */
	public static List<String> parseExtraArgs(String extraArgsString)
	{
		if(extraArgsString != null && !"null".equals(extraArgsString)) //$NON-NLS-1$
		{
			String[] extraArgs = extraArgsString.split(" "); //$NON-NLS-1$

			return Arrays.asList(extraArgs);
		}

		return Collections.emptyList();
	}

	public static void reportToServer(String basePathURL, String errorCode, Throwable throwable) throws IOException
	{
		if(basePathURL == null)
			return;

		String string = basePathURL + REPORT_ERROR_VIEW + "?errorCode=" + REPORT_ERROR_PREFIX + errorCode + //$NON-NLS-1$
				"&errorMessage=" + createExceptionMessage(throwable); //$NON-NLS-1$
		URL feedbackURL = new URL(string);
		// ping feedback view to report it to apache log
		InputStream is = feedbackURL.openStream();

		byte[] copyBuf = new byte[8192];
		while(is.read(copyBuf) > 0)
			;

		is.close();
	}

	/**
	 * Opens url in a default browser
	 * 
	 * @param url
	 * @throws JNLPException
	 */
	// from Java 1.6 use java.awt.Desktop.getDesktop().browse(URI)
	public static void showInBrowser(String url) throws JNLPException
	{
		String os = System.getProperty("os.name").toLowerCase(); //$NON-NLS-1$
		Runtime rt = Runtime.getRuntime();
		try
		{
			if(os.indexOf("win") >= 0) //$NON-NLS-1$
			{
				// alternative command: cmd.exe /C start <url> - however, it doesn't like '&' in url
				String[] cmd = new String[3];
				cmd[0] = "rundll32"; //$NON-NLS-1$
				cmd[1] = "url.dll,FileProtocolHandler"; //$NON-NLS-1$
				cmd[2] = url;

				rt.exec(cmd);
			}
			else if(os.indexOf("mac") >= 0) //$NON-NLS-1$
			{
				rt.exec("open " + url); //$NON-NLS-1$
			}
			else
			{
				// prioritized 'guess' of users' preference
				String[] browsers = { "epiphany", "firefox", "mozilla", "konqueror", "netscape", "opera", "chrome" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

				StringBuffer cmd = new StringBuffer();
				for(int i = 0; i < browsers.length; i++)
					cmd.append((i == 0
							? "" //$NON-NLS-1$
							: " || ") + browsers[i] + " \"" + url + "\" "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

				rt.exec(new String[] { "sh", "-c", cmd.toString() }); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		catch(IOException e)
		{
			throw new JNLPException(
					Messages.getString("cannot_open_default_web_browser"), Messages.getString("your_web_browser_is_not_properly_installed"), ERROR_CODE_DEFAULT_BROWSER_NOT_AVAILABLE_EXCEPTION, e); //$NON-NLS-1$ //$NON-NLS-2$

		}
	}

	/**
	 * Copies specified input stream into the output stream and closes the input stream whil the output stream remains
	 * open.
	 * 
	 * @param is
	 * @param os
	 * @throws IOException
	 */
	public static void streamCopy(InputStream is, OutputStream os) throws IOException
	{
		byte[] buffer = new byte[1024];
		int len;

		while((len = is.read(buffer)) != -1)
		{
			os.write(buffer, 0, len);
		}
		is.close();
	}
}

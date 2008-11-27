/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.internal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.p4.Messages;
import org.eclipse.buckminster.p4.P4Plugin;
import org.eclipse.buckminster.p4.internal.DepotObject.ViewEntry;
import org.eclipse.buckminster.p4.preferences.Client;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;
import org.osgi.service.prefs.BackingStoreException;


/**
 * @author thhal
 */
public class Connection extends PropertyScope
{
	/**
	 * Maximum number of arguments before the P4Client will switch into using
	 * <code>-x &lt;argument file&gt;</code> notation.
	 */
	public static final int ARG_FILE_TRESHOLD = 32;

	private static DateFormat s_dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //$NON-NLS-1$

	private static Pattern s_depotPattern = Pattern.compile("^Depot (\\w+) .*"); //$NON-NLS-1$

	private static Pattern s_tzPattern = Pattern.compile("(-?)(\\d{1,2}):?(\\d{2})$"); //$NON-NLS-1$

	private final String m_address;

	private final String m_charset;

	private final Client m_clientPrefs;

	private ConnectionInfo m_info;

	public Connection(DepotURI depotURI)
	{
		this(depotURI.getScope(), depotURI.getClient(), depotURI.getAddress());
	}

	public Connection(Map<String,String> scope, Client clientPrefs, String address)
	{
		super(scope);
		m_charset = null;
		m_clientPrefs = clientPrefs;
		m_address = address;
	}

	private static TimeZone parseTimeZone(String serverDate)
	{
		if(serverDate == null)
			return TimeZone.getDefault();

		String[] dateSplit = serverDate.split("\\s+"); //$NON-NLS-1$
		if(dateSplit.length != 4)
			return TimeZone.getDefault();

		String tzLabel = dateSplit[3];
		Matcher tzMatch = s_tzPattern.matcher(dateSplit[2]);
		if(!tzMatch.matches())
			return TimeZone.getDefault();

		boolean negative = "-".equals(tzMatch.group(1)); //$NON-NLS-1$
		int hours = Integer.parseInt(tzMatch.group(2));
		int minutes = Integer.parseInt(tzMatch.group(3));
		int seconds = hours * 3600 + minutes * 60;
		if(negative)
			seconds = -seconds;
		int myZone = seconds * 1000;
		String myDate = dateSplit[0] + ' ' + dateSplit[1];

		TimeZone okByOffset = null;
		String[] ids = TimeZone.getAvailableIDs();
		synchronized(s_dateFormat)
		{
			try
			{
				for(String id : ids)
				{
					TimeZone candidate = TimeZone.getTimeZone(id);
					
					// Parse a Date using this candidate as the TimeZone. Then
					// check if that Date is in Daylight saving time. If it is,
					// then we need to add the DSTSavings before comparing the
					// raw offset.
					//
					s_dateFormat.setTimeZone(candidate);
					Date testDate = s_dateFormat.parse(myDate);
					int rawOffset = candidate.getRawOffset();
					boolean isDST = candidate.inDaylightTime(testDate);
					if(isDST)
						rawOffset += candidate.getDSTSavings();

					if(rawOffset == myZone)
					{
						String displayName = candidate.getDisplayName(isDST, TimeZone.SHORT);
						if(displayName.equals(tzLabel))
							return candidate;
						okByOffset = candidate;
					}
				}
			}
			catch(ParseException e)
			{
				return TimeZone.getDefault();
			}
		}
		return okByOffset == null
			? TimeZone.getDefault()
			: okByOffset;
	}

	public List<Map<String, String>> exec(String cmd) throws CoreException
	{
		return this.exec(cmd, null, null);
	}

	public List<Map<String, String>> exec(String cmd, String args[]) throws CoreException
	{
		return this.exec(cmd, args, null);
	}

	public List<Map<String, String>> exec(String cmd, String args[], Map<String,String> cmdInput)
	throws CoreException
	{
		List<String> argv = new ArrayList<String>();

		// argv.add("C:\\tools\\eclipse\\plugins\\com.perforce.p4api_2004.2.3122\\win32exec.exe");
		argv.add(P4Plugin.getDefault().getP4Binary());
		argv.add("-G"); //$NON-NLS-1$
		argv.add("-p"); //$NON-NLS-1$
		argv.add(m_address);

		String tmp = this.expand(m_clientPrefs.getName());
		if(tmp != null)
		{
			argv.add("-c"); //$NON-NLS-1$
			argv.add(tmp);
		}

		tmp = this.expand(m_clientPrefs.getServer().getUser());
		if(tmp != null)
		{
			argv.add("-u"); //$NON-NLS-1$
			argv.add(tmp);
		}

		tmp = m_clientPrefs.getServer().getPassword();
		if(tmp != null)
		{
			argv.add("-P"); //$NON-NLS-1$
			argv.add(tmp);
		}

		if(m_charset != null)
		{
			argv.add("-C"); //$NON-NLS-1$
			argv.add(m_charset);
		}
		if(args == null)
		{
			argv.add(cmd);
			return this.exec(argv, cmdInput);
		}

		if(args.length > ARG_FILE_TRESHOLD)
		{
			File argsFile = null;
			PrintWriter writer = null;
			try
			{
				argsFile = File.createTempFile("p4", ".tmp"); //$NON-NLS-1$ //$NON-NLS-2$
				argsFile.deleteOnExit();

				writer = new PrintWriter(new FileWriter(argsFile));
				for(int i = 0; i < args.length; i++)
					writer.println(args[i]);
				writer.close();
				writer = null;

				argv.add("-x"); //$NON-NLS-1$
				argv.add(argsFile.getPath());
				argv.add(cmd);
				return this.exec(argv, cmdInput);
			}
			catch(IOException e)
			{
				throw BuckminsterException.wrap(e);
			}
			finally
			{
				if(argsFile != null)
					argsFile.delete();
				IOUtils.close(writer);
			}
		}

		argv.add(cmd);
		for(String arg : args)
			argv.add(arg);
		return this.exec(argv, cmdInput);
	}

	public ClientSpec getClientSpec() throws CoreException
	{
		List<Map<String,String>> info = this.exec("info"); //$NON-NLS-1$
		if(info.size() != 1)
			throw BuckminsterException.fromMessage(Messages.p4_info_failed);
		Map<String,String> clientInfo = info.get(0);

		ClientSpec clientSpec = new ClientSpec(this, this.exec("client", new String[] { "-o" }).get(0)); //$NON-NLS-1$ //$NON-NLS-2$

		// Perforce will create a new client for us if no client existed. That client
		// will have a default entry that reflects the complete depot. We do not
		// want that.
		//
		IPath clientRoot = clientSpec.getRoot();
		String localRoot = this.expand(m_clientPrefs.getLocalRoot());
		IPath expectedRoot = (localRoot == null) ? null : new Path(localRoot);
		if("*unknown*".equals(clientInfo.get("clientName")))			 //$NON-NLS-1$ //$NON-NLS-2$
		{
			// This is a freshly created ClientSpec
			//
			clientSpec.setView(new ViewEntry[0]);
			
			// Compare as files since the Path.equals is case sensitive on all platforms
			//
			if(expectedRoot != null && !expectedRoot.toFile().equals(clientRoot.toFile()))
				clientSpec.setRoot(expectedRoot);
			clientSpec.commitChanges();
		}
		else
		{
			if(clientRoot != null)
			{
				if(expectedRoot == null)
				{
					m_clientPrefs.setLocalRoot(clientRoot.toOSString());
					try
					{
						m_clientPrefs.save();
					}
					catch (BackingStoreException e)
					{
						throw BuckminsterException.wrap(e);
					}
				}
				// Compare as files since the Path.equals is case sensitive on all platforms
				//
				else if(!clientRoot.toFile().equals(expectedRoot.toFile()))
				{
					// We found a client in the perforce server that has a root
					// that doesn't match the root defined in our preferences.
					// This must be fixed and we cannot be the judge
					//
					throw new LocalRootMismatchException(
						this.expand(m_clientPrefs.getName()),
						m_address,
						expectedRoot, clientRoot);
				}
			}
		}
		return clientSpec;
	}

	public synchronized ConnectionInfo getConnectionInfo() throws CoreException
	{
		if(m_info != null)
			return m_info;

		List<Map<String, String>> info = this.exec("info", null, null); //$NON-NLS-1$
		int numInfo = info.size();
		if(numInfo == 0)
			return null;

		boolean secure = false;
		String user = null;
		String client = null;
		String root = null;
		String port = null;
		String date = null;

		Map<String, String> firstInfo = info.get(0);
		if("stat".equals(firstInfo.get("code"))) //$NON-NLS-1$ //$NON-NLS-2$
		{
			for(Map.Entry<String, String> entry : firstInfo.entrySet())
			{
				String key = entry.getKey();
				String value = entry.getValue();
				if(key.equals("userName")) //$NON-NLS-1$
					user = value;
				else if(key.equals("clientName")) //$NON-NLS-1$
					client = value;
				else if(key.equals("clientRoot")) //$NON-NLS-1$
					root = value;
				else if(key.equals("serverAddress")) //$NON-NLS-1$
					port = value;
				else if(key.equals("serverDate")) //$NON-NLS-1$
					date = value;
				else if(key.equals("security") && value.equals("enabled")) //$NON-NLS-1$ //$NON-NLS-2$
					secure = true;
			}
		}
		else
		{
			for(Map<String, String> map : info)
			{
				String data = map.get("data"); //$NON-NLS-1$
				if(data != null)
				{
					int idx = data.indexOf(':');
					if(idx > -1)
					{
						String key = data.substring(0, idx);
						String value = data.substring(idx + 1);
						if(key.equals("User name")) //$NON-NLS-1$
							user = value;
						else if(key.equals("Client name")) //$NON-NLS-1$
							client = value;
						else if(key.equals("Client root")) //$NON-NLS-1$
							root = value;
						else if(key.equals("Server address")) //$NON-NLS-1$
							port = value;
						else if(key.equals("Server date")) //$NON-NLS-1$
							date = value;
						else if(key.equals("security") && value.equals("enabled")) //$NON-NLS-1$ //$NON-NLS-2$
							secure = true;
					}
				}
			}
		}
		m_info = new ConnectionInfo(user, client, root, port, parseTimeZone(date), secure);
		return m_info;
	}

	public DepotFolder[] getDepots() throws CoreException
	{
		List<Map<String, String>> depotsData = this.exec("depots"); //$NON-NLS-1$
		int numDepots = depotsData.size();
		ArrayList<DepotFolder> depots = new ArrayList<DepotFolder>(numDepots);
		for(int idx = 0; idx < numDepots; ++idx)
		{
			Map<String,String> dataMap = depotsData.get(idx);
			String name = dataMap.get("name"); //$NON-NLS-1$
			if(name == null)
			{
				String data = dataMap.get("data"); //$NON-NLS-1$
				if(data != null)
				{
					Matcher matcher = s_depotPattern.matcher(data);
					if(matcher.matches())
						name = matcher.group(1);
				}
			}
			if(name != null)
			{
				depots.add(new DepotFolder(this,
					Collections.<String, String>singletonMap("dir", "//" + name), FileSpec.HEAD)); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return depots.toArray(new DepotFolder[depots.size()]);
	}

	public DepotFile getFile(FileSpec path) throws CoreException
	{
		List<DepotFile> files = this.getFiles(new FileSpec[] { path }, false);
		return (files.size() > 0) ? files.get(0) : null;
	}

	public List<DepotFile> getFiles(FileSpec paths[], boolean includeDeleted) throws CoreException
	{
		int idx = paths.length;
		if(idx == 0)
			return Collections.<DepotFile> emptyList();
		
		String[] pathStrings = new String[idx];
		while(--idx >= 0)
			pathStrings[idx] = paths[idx].toString();

		return this.getFiles(this.exec("fstat", pathStrings), includeDeleted); //$NON-NLS-1$
	}

	/**
	 * Returns the last change number for the given <code>path</code>. The <code>path</code> must denote a
	 * folder. The command will append &quot;/...&quot; to the path and, in case
	 * <code>qualifier</code> is not null or an empty string, also append &quot;@&lt;qualifier&gt;&quot;
	 * @param path The folder that limits the search for a change number.
	 * @param qualifier An optional qualifier (typically a label) that limits the search.
	 * @return The latest change number or -1 if no change could be found.
	 * @throws CoreException
	 */
	public long getLastChangeNumber(IPath path, String qualifier) throws CoreException
	{
		String stringPath = path.append("...").toString(); //$NON-NLS-1$
		if(qualifier != null && qualifier.length() > 0)
			stringPath += '@' + qualifier;

		List<Map<String, String>> data = this.exec("changes", new String[] { "-m", "1", "-s", "submitted", stringPath }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		if(data.size() == 1)
		{
			String change = data.get(0).get("change"); //$NON-NLS-1$
			if(change != null)
				return Long.parseLong(change);
		}
		return -1;
	}

	public DepotFolder[] getFolders(IPath path, FileSpec.Specifier revision) throws CoreException
	{
		List<Map<String, String>> data = this.exec("dirs", new String[] { path.toString() }); //$NON-NLS-1$
		int numDepots = data.size();
		DepotFolder depots[] = new DepotFolder[numDepots];
		for(int idx = 0; idx < numDepots; ++idx)
			depots[idx] = new DepotFolder(this, data.get(idx), revision);
		return depots;
	}

	public Label getLabel(String labelName) throws CoreException
	{
		List<Map<String, String>> data = this.exec("label", new String[] { "-o", labelName }); //$NON-NLS-1$ //$NON-NLS-2$
		if(data.size() == 1)
		{
			Label label = new Label(this, data.get(0));
			if(label.getAccess() != null || label.getUpdate() != null)
				return label;
		}
		return null;
	}

	public Label[] getLabels(IPath path) throws CoreException
	{
		List<Map<String, String>> data = this.exec("labels", new String[] { path.toString() }); //$NON-NLS-1$
		int numLabels = data.size();
		Label labels[] = new Label[numLabels];
		for(int idx = 0; idx < numLabels; ++idx)
		{
			Map<String, String> entry = data.get(idx);

			// P4 stupidity. The "labels" command returns a map with "label"
			// but the "label" command returns "Label". Sigh...
			//
			String label = entry.remove("label"); //$NON-NLS-1$
			if(label != null)
				entry.put("Label", label); //$NON-NLS-1$
			labels[idx] = new Label(this, entry);
		}
		return labels;
	}

	public Date parseDate(String perforceDate) throws CoreException
	{
		synchronized(s_dateFormat)
		{
			s_dateFormat.setTimeZone(this.getConnectionInfo().getTimeZone());
			try
			{
				return s_dateFormat.parse(perforceDate);
			}
			catch(ParseException e)
			{
				throw BuckminsterException.wrap(e);
			}
		}
	}

	public String formatDate(Date date) throws CoreException
	{
		synchronized(s_dateFormat)
		{
			s_dateFormat.setTimeZone(this.getConnectionInfo().getTimeZone());
			return s_dateFormat.format(date);
		}
	}

	public void setClientSpec(Map<String, String> values) throws CoreException
	{
		this.exec("client", new String[] { "-i" }, values); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public String[] where(IPath path) throws CoreException
	{
		Map<String, String> result = this.exec("where", new String[] { path.toString() }).get(0); //$NON-NLS-1$
		String data = result.get("data"); //$NON-NLS-1$
		String paths[] = new String[3];
		if(data != null)
		{
			paths = new String[3];

			// Locate second path that starts with '//'
			//
			int second = data.indexOf("//", 2); //$NON-NLS-1$
			if(second < 0)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.weird_responds_from_p4_where_0, data));

			int secondEnd = data.indexOf(' ', second);
			if(secondEnd < 0)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.weird_responds_from_p4_where_0, data));

			int third = secondEnd + 1;
			while(Character.isWhitespace(data.charAt(third)))
				++third;

			int firstEnd = second - 1;
			while(firstEnd >= 0 && Character.isWhitespace(data.charAt(firstEnd)))
				--firstEnd;
			++firstEnd;

			paths[0] = data.substring(0, firstEnd);
			paths[1] = data.substring(second, secondEnd);
			paths[2] = data.substring(third);
		}
		else
		{
			paths[0] = result.get("depotFile"); //$NON-NLS-1$
			paths[1] = result.get("clientFile"); //$NON-NLS-1$
			paths[2] = result.get("path"); //$NON-NLS-1$
		}
		return paths;
	}

	List<DepotFile> getFiles(List<Map<String, String>> fileInfos, boolean includeDeleted)
	{
		ArrayList<DepotFile> files = new ArrayList<DepotFile>(fileInfos.size());
		for(Map<String, String> fileInfo : fileInfos)
		{
			String depotPath = fileInfo.get("depotFile"); //$NON-NLS-1$
			if(depotPath != null)
			{
				if(includeDeleted || !"delete".equals(fileInfo.get("headAction"))) //$NON-NLS-1$ //$NON-NLS-2$
					files.add(new DepotFile(this, fileInfo));
			}
		}
		return files;
	}

	private List<Map<String, String>> exec(List<String> cmdLine, Map<String,String> cmdInput)
	throws CoreException
	{
		final Process process;
		OutputStream procOut = null;
		try
		{
			process = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
			procOut = process.getOutputStream();
			if(cmdInput != null)
			{
				PythonOutputStream output = new PythonOutputStream(procOut);
				output.writeObject(cmdInput);
				output.flush();
			}
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(procOut);
		}

		final String[] inThreadException = new String[1];
		final ArrayList<Map<String, String>> results = new ArrayList<Map<String, String>>();
		Thread inThread = new Thread()
		{
			@Override
			public void run()
			{
				InputStream procIn = null;
				try
				{
					procIn = process.getInputStream();
					PythonInputStream input = new PythonInputStream(procIn);
					for(;;)
					{
						Map<String,String> result = input.readStringMap();
						if(result == null)
							break;
						results.add(result);
					}
				}
				catch(IOException e)
				{
					inThreadException[0] = e.getMessage();
				}
				finally
				{
					IOUtils.close(procIn);
				}
			}
		};

		final StringBuilder errorBuilder = new StringBuilder();
		Thread errThread = new Thread()
		{
			@Override
			public void run()
			{
				InputStream procErr = null;
				try
				{
					procErr = process.getErrorStream();
					InputStreamReader rdr = new InputStreamReader(procErr, "US-ASCII"); //$NON-NLS-1$
					char[] buf = new char[1024];
					int errCnt = rdr.read(buf);
					if(errCnt > 0)
					{
						do
						{
							errorBuilder.append(buf, 0, errCnt);
							errCnt = rdr.read(buf);
						} while(errCnt > 0);
					}
				}
				catch(IOException e)
				{
					// We ignore this for now. Errors reading the error stream
					// is not of major interest anyway.
				}
				finally
				{
					IOUtils.close(procErr);
				}
			}
		};
		inThread.start();
		errThread.start();

		int exitCode;
		try
		{
			exitCode = process.waitFor();
			inThread.join();
			errThread.join();
			if(inThreadException[0] != null)
				throw BuckminsterException.fromMessage(inThreadException[0]);
		}
		catch(InterruptedException e)
		{
			throw BuckminsterException.wrap(e);
		}

		if(exitCode != 0)
		{
			for(Map<String, String> result : results)
			{
				if("error".equals(result.get("code"))) //$NON-NLS-1$ //$NON-NLS-2$
					throw BuckminsterException.fromMessage(result.get("data").toString()); //$NON-NLS-1$
			}

			String error = errorBuilder.toString();
			if(error.length() == 0)
				throw BuckminsterException.fromMessage(Messages.process_died_with_exit_code_0, Integer.valueOf(exitCode));

			throw BuckminsterException.fromMessage(error);
		}
		return results;
	}
}


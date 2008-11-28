/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.p4.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;

/**
 * @author thhal
 */
public abstract class DepotObject
{
	public class ViewEntry
	{
		private final IPath m_depotPath;
		private final IPath m_localPath;

		public ViewEntry(IPath depotPath, IPath localPath)
		{
			if(!(depotPath.isUNC() && localPath.isUNC()))
				throw new IllegalArgumentException(Messages.only_UNC_paths_are_accepted);
			m_depotPath = depotPath;
			m_localPath = localPath;
		}

		ViewEntry(String viewSpecLine)
		{
			this(parsePaths(viewSpecLine));
		}

		private ViewEntry(IPath[] paths)
		{
			this(paths[0], paths[1]);
		}

		/**
		 * @return Returns the depotPath.
		 */
		public final IPath getDepotPath()
		{
			return m_depotPath;
		}

		/**
		 * @return Returns the localPath.
		 */
		public final IPath getLocalPath()
		{
			return m_localPath;
		}

		/**
		 * 
		 * @return A string representation.
		 */
		@Override
		public final String toString()
		{
			return concatMultiPaths(new String[]
			                                   {
					expandEscapedChars(m_depotPath.toString()),
					expandEscapedChars(m_localPath.toString())
			                                   });
		}
	}

	private final Connection m_connection;
	private final Map<String,String> m_info;

	protected DepotObject(Connection client, Map<String,String> info)
	{
		m_connection = client;
		m_info = info;
	}

	public final Connection getConnection()
	{
		return m_connection;
	}

	/**
	 * Concatenates the array of <code>paths</code> into one space separated string. Paths that contains
	 * spaces will be quoted using the &quot; character.
	 * @param paths The paths to concatenate.
	 * @return The string of concatenated paths.
	 * @see #splitMultiPaths(String)
	 */
	public static String concatMultiPaths(String[] paths)
	{
		StringBuilder bld = new StringBuilder();
		for(String path : paths)
		{
			if(bld.length() > 0)
				bld.append(' ');
			if(path.indexOf(' ') >= 0)
			{
				bld.append('"');
				bld.append(path);
				bld.append('"');
			}
			else
				bld.append(path);
		}
		return bld.toString();
	}

	/**
	 * Expand the P4 &quot;forbidden&quot; characters into their %&lt;hexadecimal ascii code&gt; corresponance.
	 * @param path The path to expand.
	 * @return An expanded path.
	 * @see #parseEscapedChars(String)
	 */
	public static String expandEscapedChars(String path)
	{
		StringBuilder bld = new StringBuilder();
		int top = path.length();
		for(int idx = 0; idx < top; ++idx)
		{
			char c = path.charAt(idx);
			switch(c)
			{
			case '@':
				bld.append("%40"); //$NON-NLS-1$
				continue;
			case '#':
				bld.append("%23"); //$NON-NLS-1$
				continue;
			case '*':
				bld.append("%2a"); //$NON-NLS-1$
				continue;
			case '%':
				bld.append("%25"); //$NON-NLS-1$
				continue;
			}
			bld.append(c);
		}
		return bld.toString();
	}

	/**
	 * Split a String containing space separated paths into multiple
	 * strings. A path may contain spaces if it is quoted using &quot;
	 * @param entry The entry that contains multiple paths.
	 * @return An array of paths.
	 * @see #concatMultiPaths(String[])
	 */
	public static String[] splitMultiPaths(String entry)
	{
		List<String> paths = new ArrayList<String>();
		int top = entry.length();
		for(int idx = 0; idx < top; ++idx)
		{
			String body = null;
			char c = entry.charAt(idx);
			while(idx < top && Character.isWhitespace(c))
				c = entry.charAt(++idx);

			if(idx == top)
				break;

			if(c == '"')
			{
				int start = ++idx;
				for(; idx < top; ++idx)
					if(entry.charAt(idx) == '"')
						body = entry.substring(start, idx++);
				if(body == null)
					throw new IllegalArgumentException(NLS.bind(Messages.missing_ending_quote_view_file_entry_0, entry));
			}
			else
			{
				int start = idx++;
				while(idx < top && !Character.isWhitespace(entry.charAt(idx)))
					++idx;
				body = entry.substring(start, idx);
			}
			paths.add(body);
		}
		return paths.toArray(new String[paths.size()]);
	}

	/**
	 * Parse occurences of %&lt;hexadecimal ascii code&gt; into their corresponding ascii character.
	 * @param path The path to parse
	 * @return A path where the parsed characters has been replaced.
	 * @see #expandEscapedChars(String)
	 */
	public static String parseEscapedChars(String path)
	{
		StringBuilder bld = null;
		int top = path.length();
		for(int idx = 0; idx < top; ++idx)
		{
			char c = path.charAt(idx);
			if(c == '%' && idx + 1 < top)
			{
				int percentStart = idx;
				char c2 = path.charAt(++idx);
				if(c2 == '%')
				{
					// Loose one of the two %
					//
					if(bld == null)
					{
						bld = new StringBuilder();
						if(percentStart > 0)
							bld.append(path.substring(0, percentStart));
					}
					bld.append(c2);
					continue;
				}

				if(Character.isDigit(c2)
				&& idx + 1 < top
				&& Character.isDigit(path.charAt(++idx)))
				{
					// Treat as hex character
					//
					if(bld == null)
					{
						bld = new StringBuilder();
						if(percentStart > 0)
							bld.append(path.substring(0, percentStart));
					}
					int ascii = Integer.parseInt(path.substring(idx - 1, idx + 1), 16);
					bld.append((char)ascii);
				}
				else
				{
					if(bld != null)
					{
						bld.append(c);
						bld.append(c2);
					}
				}
				continue;
			}
			if(bld != null)
				bld.append(c);
		}
		if(bld != null)
			path = bld.toString();
		return path;
	}

	final synchronized String get(String key)
	{
		return m_info.get(key);
	}

	final synchronized String put(String key, String value)
	{
		return m_info.put(key, value);
	}

	final synchronized String remove(String key)
	{
		return m_info.remove(key);
	}

	final Map<String, String> getInfo()
	{
		return m_info;
	}

	Date getParsedDate(String dateName) throws CoreException
	{
		String date = this.get(dateName);
		if(date == null)
			return null;
		return this.getConnection().parseDate(date);
	}

	final ViewEntry[] getViewSpec()
	{
		List<ViewEntry> entries = new ArrayList<ViewEntry>();
		int entryNum = 0;
		for(;; ++entryNum)
		{
			String pair = this.get("View" + Integer.toString(entryNum)); //$NON-NLS-1$
			if(pair == null)
				break;
			entries.add(new ViewEntry(pair));
		}
		return entries.toArray(new ViewEntry[entries.size()]);
	}

	private static IPath[] parsePaths(String viewSpecLine)
	{
		String[] paths = splitMultiPaths(viewSpecLine);
		if(paths.length != 2)
			throw new IllegalArgumentException(Messages.pair_with_depot_path_and_client_path_expected);

		return new IPath[]
		{
			new Path(parseEscapedChars(paths[0])),
			new Path(parseEscapedChars(paths[1]))
		};
	}
}

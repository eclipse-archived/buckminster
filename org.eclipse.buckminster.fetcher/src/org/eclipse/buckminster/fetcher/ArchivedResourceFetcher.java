package org.eclipse.buckminster.fetcher;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.download.Installer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class ArchivedResourceFetcher extends AbstractResourceFetcher
{
	private static class PatternFileFilter implements FileFilter
	{
		private static final String SEP = System.getProperty("file.separator");

		private List<String> m_patterns = new ArrayList<String>();

		public PatternFileFilter(Iterable<String> patternsToInclude)
		{
			for(String p : patternsToInclude)
			{
				p = p.replace(SEP, "/");
				p = p.replace(".", "\\.");
				p = p.replace("*", ".*");
				p = p.replace("?", ".?");
				m_patterns.add(p);
			}
		}

		private boolean isSubtract(String patt)
		{
			return patt.startsWith("-");
		}

		private String rawPatt(String patt)
		{
			if(!isSubtract(patt))
				return patt;
			return patt.substring(1);
		}

		private boolean match(String p, File rel)
		{
			String s = rel.getName();
			if(p.indexOf('/') >= 0)
				s = rel.toString();
			s = s.replace(SEP, "/");
			return s.matches(p);
		}

		public boolean accept(File pathname)
		{
			if(m_patterns.isEmpty())
				return true;
			boolean ok = false;
			for(String p : m_patterns)
			{
				boolean subtract = isSubtract(p);
				p = rawPatt(p);
				boolean b = match(p, pathname);
				if(b && subtract)
					return false;
				if(b)
					ok = true;
			}
			return ok;
		}
	}

	private final String m_filename;

	private final FileFilter m_filter;

	private final boolean m_flatten;

	public ArchivedResourceFetcher(URL url, String dir, boolean shouldFlatten, List<String> patternsToInclude)
			throws CoreException
	{
		super(url, dir);
		m_filename = url.getFile();
		if(patternsToInclude.isEmpty())
			m_filter = null;
		else
			m_filter = new PatternFileFilter(patternsToInclude);
		m_flatten = shouldFlatten;
	}

	@Override
	protected void consume(InputStream stream, IProgressMonitor monitor) throws IOException, CoreException
	{
		final Installer installer = Installer.getInstaller(m_filename, true);
		installer.install(stream, new File(getDestinationDir()), m_filter, m_flatten, monitor);
	}
}

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

public class ArchivedResourceFetcher extends AbstractResourceFetcher {
	private static class PatternFileFilter implements FileFilter {
		private static final String SEP = System.getProperty("file.separator"); //$NON-NLS-1$

		private List<String> patterns = new ArrayList<String>();

		public PatternFileFilter(Iterable<String> patternsToInclude) {
			for (String p : patternsToInclude) {
				p = p.replace(SEP, "/"); //$NON-NLS-1$
				p = p.replace(".", "\\."); //$NON-NLS-1$ //$NON-NLS-2$
				p = p.replace("*", ".*"); //$NON-NLS-1$ //$NON-NLS-2$
				p = p.replace("?", ".?"); //$NON-NLS-1$ //$NON-NLS-2$
				patterns.add(p);
			}
		}

		@Override
		public boolean accept(File pathname) {
			if (patterns.isEmpty())
				return true;
			boolean ok = false;
			for (String p : patterns) {
				boolean subtract = isSubtract(p);
				p = rawPatt(p);
				boolean b = match(p, pathname);
				if (b && subtract)
					return false;
				if (b)
					ok = true;
			}
			return ok;
		}

		private boolean isSubtract(String patt) {
			return patt.startsWith("-"); //$NON-NLS-1$
		}

		private boolean match(String p, File rel) {
			String s = rel.getName();
			if (p.indexOf('/') >= 0)
				s = rel.toString();
			s = s.replace(SEP, "/"); //$NON-NLS-1$
			return s.matches(p);
		}

		private String rawPatt(String patt) {
			if (!isSubtract(patt))
				return patt;
			return patt.substring(1);
		}
	}

	private final String filename;

	private final FileFilter filter;

	private final boolean flatten;

	public ArchivedResourceFetcher(URL url, String dir, boolean shouldFlatten, List<String> patternsToInclude) throws CoreException {
		super(url, dir);
		filename = url.getFile();
		if (patternsToInclude.isEmpty())
			filter = null;
		else
			filter = new PatternFileFilter(patternsToInclude);
		flatten = shouldFlatten;
	}

	@Override
	protected void consume(InputStream stream, IProgressMonitor monitor) throws IOException, CoreException {
		final Installer installer = Installer.getInstaller(filename, true);
		installer.install(stream, new File(getDestinationDir()), filter, flatten, monitor);
	}
}

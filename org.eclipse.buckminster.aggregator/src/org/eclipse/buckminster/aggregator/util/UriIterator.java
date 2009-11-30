package org.eclipse.buckminster.aggregator.util;

import java.net.URI;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class UriIterator implements Iterator<URI>
{
	private final URI m_root;

	private final URI[] m_uris;

	private final Pattern m_excludePattern;

	private int m_index;

	public UriIterator(URI root, Pattern excludePattern, IProgressMonitor monitor) throws CoreException
	{
		m_uris = UriUtils.list(UriUtils.appendTrailingSlash(root), monitor);
		m_index = -1;
		m_excludePattern = excludePattern;
		m_root = root;
		positionNext();
	}

	public UriIterator(URI root, Pattern excludePattern, URI[] uris)
	{
		m_uris = uris;
		m_index = -1;
		m_excludePattern = excludePattern;
		m_root = root;
		positionNext();
	}

	public URI getRoot()
	{
		return m_root;
	}

	public URI[] getURIs()
	{
		return m_uris;
	}

	public boolean hasNext()
	{
		return m_index < m_uris.length;
	}

	public URI next()
	{
		if(!hasNext())
			throw new NoSuchElementException();

		URI nextURL = m_uris[m_index];
		positionNext();
		return nextURL;
	}

	public void remove()
	{
		throw new UnsupportedOperationException();
	}

	public int size()
	{
		return m_uris.length;
	}

	private void positionNext()
	{
		if(m_excludePattern == null)
			++m_index;
		else
		{
			int top = m_uris.length;
			for(;;)
			{
				if(++m_index >= top || !m_excludePattern.matcher(m_uris[m_index].toString()).matches())
					break;
			}
		}
	}
}

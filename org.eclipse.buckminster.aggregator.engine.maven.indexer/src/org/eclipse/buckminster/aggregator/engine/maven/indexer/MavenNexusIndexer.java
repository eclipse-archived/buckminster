/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.engine.maven.indexer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermEnum;
import org.apache.maven.embedder.Configuration;
import org.apache.maven.embedder.ConfigurationValidationResult;
import org.apache.maven.embedder.DefaultConfiguration;
import org.apache.maven.embedder.MavenEmbedder;
import org.apache.maven.embedder.MavenEmbedderException;
import org.codehaus.plexus.PlexusContainer;
import org.eclipse.buckminster.aggregator.engine.maven.MavenActivator;
import org.eclipse.buckminster.aggregator.engine.maven.VersionUtil;
import org.eclipse.buckminster.aggregator.engine.maven.loader.VersionEntry;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.sonatype.nexus.index.ArtifactInfo;
import org.sonatype.nexus.index.NexusIndexer;
import org.sonatype.nexus.index.context.IndexCreator;
import org.sonatype.nexus.index.context.IndexingContext;
import org.sonatype.nexus.index.packer.IndexPacker;
import org.sonatype.nexus.index.packer.IndexPackingRequest;
import org.sonatype.nexus.index.updater.IndexUpdateRequest;
import org.sonatype.nexus.index.updater.IndexUpdater;

/**
 * @author Filip Hrbek (filip.hrbek@cloudsmith.com)
 * 
 */
public class MavenNexusIndexer implements IMaven2Indexer
{
	private class VersionEntryIterator implements Iterator<VersionEntry>
	{
		private IndexReader m_indexReader;

		private TermEnum m_termEnum;

		private VersionEntry m_nextEntry;

		public VersionEntryIterator(IndexReader indexReader) throws IOException
		{
			m_indexReader = indexReader;
			m_termEnum = m_indexReader.terms(new Term(ArtifactInfo.UINFO, ""));
			m_nextEntry = getNextEntry(false);
		}

		public void close() throws IOException
		{
			synchronized(MavenNexusIndexer.this)
			{
				m_openIterators.remove(this);
				if(m_termEnum != null)
					m_termEnum.close();
				if(m_indexReader != null)
					m_indexReader.close();
			}
		}

		public boolean hasNext()
		{
			boolean hasNext = m_nextEntry != null;
			if(!hasNext)
				try
				{
					close();
				}
				catch(IOException e)
				{
					throw new RuntimeException("Unable to close index reader");
				}

			return hasNext;
		}

		public VersionEntry next()
		{
			if(m_nextEntry != null)
			{
				VersionEntry entry = m_nextEntry;
				try
				{
					m_nextEntry = getNextEntry(true);
				}
				catch(IOException e)
				{
					throw new RuntimeException(e);
				}
				return entry;
			}

			throw new NoSuchElementException();
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}

		private VersionEntry getNextEntry(boolean moveToNext) throws IOException
		{
			if(moveToNext && !m_termEnum.next())
				return null;

			while(m_termEnum.term() != null && ArtifactInfo.UINFO.equals(m_termEnum.term().field()))
			{
				String record = m_termEnum.term().toString();
				String[] tokens = record.split("[:|]");

				// we look for something like "u:groupId|artifactId|version|NA"
				if(tokens.length == 5)
				{
					try
					{
						return new VersionEntry(tokens[1], tokens[2], VersionUtil.createVersion(tokens[3]));
					}
					catch(CoreException e)
					{
						IOException ioe = new IOException(e.getMessage());
						ioe.initCause(e);
						throw ioe;
					}
				}

				if(!m_termEnum.next())
					break;
			}

			return null;
		}
	}

	private IndexingContext m_context;

	private Set<VersionEntryIterator> m_openIterators;

	public MavenNexusIndexer()
	{
		m_openIterators = new HashSet<VersionEntryIterator>();
	}

	synchronized public void closeRemoteIndex() throws CoreException
	{
		try
		{
			for(VersionEntryIterator itor : m_openIterators)
				itor.close();

			if(m_context != null)
			{
				m_context.close(false);
				m_context = null;
			}
		}
		catch(IOException e)
		{
			throw BuckminsterException.fromMessage(e, e.getMessage());
		}
	}

	synchronized public Iterator<VersionEntry> getArtifacts() throws CoreException
	{
		try
		{
			IndexReader indexReader = IndexReader.open(m_context.getIndexDirectory());

			VersionEntryIterator itor = new VersionEntryIterator(indexReader);
			m_openIterators.add(itor);
			return itor;
		}
		catch(IOException e)
		{
			throw BuckminsterException.fromMessage(e, e.getMessage());
		}
	}

	synchronized public int getNumberOfEntries() throws CoreException
	{
		// not very efficient but quite fast, may be improved later
		Iterator<VersionEntry> artifacts = getArtifacts();
		int counter = 0;
		while(artifacts.hasNext())
		{
			artifacts.next();
			counter++;
		}

		return counter;
	}

	public void openRemoteIndex(URI location, boolean clearLocalCache) throws IndexNotFoundException, CoreException
	{
		closeRemoteIndex();
		m_openIterators.clear();

		File cacheDirectory = MavenActivator.getPlugin().getCacheDirectory(location);
		File indexDirectory = new File(cacheDirectory, "index");
		try
		{
			MavenEmbedder embedder = getMavenEmbedder();
			PlexusContainer plexus = embedder.getPlexusContainer();
			NexusIndexer indexer = (NexusIndexer)plexus.lookup(NexusIndexer.class);
			IndexUpdater updater = (IndexUpdater)plexus.lookup(IndexUpdater.class);
			IndexCreator creator = (IndexCreator)plexus.lookup(IndexCreator.class, "min");

			String repoId = "mavenRepo";
			if(!clearLocalCache)
				m_context = indexer.addIndexingContext(repoId, // index id (usually the same as repository
						// id)
						repoId, // repository id
						(File)null, // null for remote repo
						indexDirectory, // Lucene directory where index is stored
						location.toString(), // repository url, used by index updater
						null, // null if derived from repositoryUrl
						Collections.singletonList(creator));
			else
				m_context = indexer.addIndexingContextForced(repoId, // index id (usually the same as repository
						// id)
						repoId, // repository id
						(File)null, // null for remote repo
						indexDirectory, // Lucene directory where index is stored
						location.toString(), // repository url, used by index updater
						null, // null if derived from repositoryUrl
						Collections.singletonList(creator));

			IndexUpdateRequest request = new IndexUpdateRequest(m_context);
			updater.fetchAndUpdateIndex(request);
		}
		catch(Exception e)
		{
			throw new IndexNotFoundException(e);
		}
	}

	public void updateLocalIndex(URI location, boolean createNew) throws CoreException
	{
		try
		{
			MavenEmbedder embedder = getMavenEmbedder();
			PlexusContainer plexus = embedder.getPlexusContainer();
			NexusIndexer indexer = (NexusIndexer)plexus.lookup(NexusIndexer.class);
			IndexPacker packer = (IndexPacker)plexus.lookup(IndexPacker.class);
			String repoId = "mavenRepo";
			File repository = new File(location);
			File index = new File(repository, ".index");
			File internalIndex = new File(new File(repository.getParentFile().getParentFile(), "interim"),
					"maven-index");
			List<IndexCreator> creators = new ArrayList<IndexCreator>(2);
			creators.add((IndexCreator)plexus.lookup(IndexCreator.class, "min"));
			creators.add((IndexCreator)plexus.lookup(IndexCreator.class, "jarContent"));
			IndexingContext context = indexer.addIndexingContext(repoId, repoId, repository, internalIndex, null, null,
					creators);
			try
			{
				indexer.scan(context, !createNew);
				IndexPackingRequest request = new IndexPackingRequest(context, index);
				List<IndexPackingRequest.IndexFormat> formats = new ArrayList<IndexPackingRequest.IndexFormat>(2);
				formats.add(IndexPackingRequest.IndexFormat.FORMAT_V1);
				formats.add(IndexPackingRequest.IndexFormat.FORMAT_LEGACY);
				request.setFormats(formats);
				request.setCreateChecksumFiles(true);
				request.setCreateIncrementalChunks(!createNew);
				packer.packIndex(request);
			}
			finally
			{
				if(context != null)
					context.close(false);
			}
		}
		catch(Exception e)
		{
			throw BuckminsterException.fromMessage(e, "Unable to create an index for %s", location.toString());
		}
	}

	private MavenEmbedder getMavenEmbedder() throws CoreException
	{
		Configuration configuration = new DefaultConfiguration();
		ConfigurationValidationResult validationResult = MavenEmbedder.validateConfiguration(configuration);

		if(validationResult.isValid())
		{
			try
			{
				return new MavenEmbedder(configuration);
			}
			catch(MavenEmbedderException e)
			{
				throw BuckminsterException.fromMessage(e, "Error obtaining a maven embedder");
			}
		}
		else
		{
			if(validationResult.getGlobalSettingsException() != null)
				throw BuckminsterException.fromMessage(validationResult.getGlobalSettingsException().getMessage());
			else if(validationResult.getUserSettingsException() != null)
				throw BuckminsterException.fromMessage(validationResult.getUserSettingsException().getMessage());
			else
				throw BuckminsterException.fromMessage("Maven configuration is not valid");
		}
	}
}

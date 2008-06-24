/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.eclipse.buckminster.p2.remote.Activator;
import org.eclipse.buckminster.p2.remote.FacadeAlreadyExistsException;
import org.eclipse.buckminster.p2.remote.IRepositoryFacade;
import org.eclipse.buckminster.p2.remote.IRepositoryServer;
import org.eclipse.buckminster.p2.remote.NoSuchFacadeException;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.p2.console.ProvisioningHelper;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.jabsorb.JSONSerializer;

/**
 * @author Thomas Hallgren
 */
public class RepositoryServer implements IRepositoryServer
{
	private static final String PROP_METADATAREPOSITORY_PREFIX = "metadataRepository.";

	private static final String PROP_ARTIFACTREPOSITORY_PREFIX = "artifactRepository.";

	public static IRepositoryServer getServer(URI uri, JSONSerializer serializer) throws IOException
	{
		synchronized(s_servers)
		{
			RepositoryServer server = s_servers.get(uri);
			if(server == null)
			{
				server = new RepositoryServer(uri, serializer);
				s_servers.put(uri, server);
			}
			return server;
		}
	}

	static URL url(URI uri)
	{
		try
		{
			return uri.toURL();
		}
		catch(MalformedURLException e)
		{
			throw new RuntimeException(e);
		}
	}

	private final URI m_uri;

	private final Map<String, URI> m_knownArtifactRepos = new HashMap<String, URI>();

	private final Map<String, URI> m_knownMetadataRepos = new HashMap<String, URI>();

	private final JSONSerializer m_serializer;

	private static final Map<URI, RepositoryServer> s_servers = new HashMap<URI, RepositoryServer>();

	private RepositoryServer(URI uri, JSONSerializer serializer) throws IOException
	{
		m_uri = uri;
		m_serializer = serializer;
		load();
	}

	public synchronized IRepositoryFacade createArtifactRepositoryFacade(String facadeName)
	throws ProvisionException
	{
		if(m_knownArtifactRepos.containsKey(facadeName))
			throw new FacadeAlreadyExistsException(m_uri, facadeName);

		File facadeArea = getFacadeArea(facadeName, false);
		URI repoURI = new File(facadeArea, "artifacts").toURI();
		IArtifactRepository repo = Activator.getArtifactRepositoryManager().createRepository(url(repoURI),
			facadeName, IArtifactRepositoryManager.TYPE_SIMPLE_REPOSITORY, null);

		m_knownArtifactRepos.put(facadeName, repoURI);
		try
		{
			save();
			return new ArtifactRepositoryFacade(facadeName, new LoggingArtifactRepository(this, repo,
				facadeArea));
		}
		catch(CoreException e)
		{
			throw new ProvisionException(e.getStatus());
		}
	}

	public synchronized IRepositoryFacade createMetadataRepositoryFacade(String facadeName)
	throws ProvisionException
	{
		if(m_knownMetadataRepos.containsKey(facadeName))
			throw new FacadeAlreadyExistsException(m_uri, facadeName);

		File facadeArea = getFacadeArea(facadeName, true);
		URI repoURI = new File(facadeArea, "content").toURI();
		IMetadataRepository repo = Activator.getMetadataRepositoryManager().createRepository(url(repoURI),
			facadeName, IMetadataRepositoryManager.TYPE_SIMPLE_REPOSITORY, null);

		m_knownMetadataRepos.put(facadeName, repoURI);
		save();
		return new MetadataRepositoryFacade(facadeName, new LoggingMetadataRepository(this, repo, facadeArea));
	}

	public synchronized boolean deleteArtifactRepositoryFacade(String facadeName) throws ProvisionException
	{
		return deleteRepositoryFacade(facadeName, m_knownArtifactRepos, false);
	}

	public synchronized boolean deleteMetadataRepositoryFacade(String facadeName) throws ProvisionException
	{
		return deleteRepositoryFacade(facadeName, m_knownMetadataRepos, true);
	}

	public synchronized IRepositoryFacade getArtifactRepositoryFacade(String facadeName)
	throws ProvisionException
	{
		URI repoURI = m_knownArtifactRepos.get(facadeName);
		if(repoURI == null)
			throw new NoSuchFacadeException(m_uri, facadeName);

		try
		{
			return new ArtifactRepositoryFacade(facadeName, new LoggingArtifactRepository(this,
				ProvisioningHelper.getArtifactRepository(url(repoURI)), getFacadeArea(facadeName, false)));
		}
		catch(CoreException e)
		{
			throw new ProvisionException(e.getStatus());
		}
	}

	public synchronized List<String> getArtifactRepositoryFacadeNames()
	{
		return getRepositoryFacadeNames(m_knownArtifactRepos);
	}

	public synchronized IRepositoryFacade getMetadataRepositoryFacade(String facadeName)
	throws ProvisionException
	{
		URI repoURI = m_knownMetadataRepos.get(facadeName);
		if(repoURI == null)
			throw new NoSuchFacadeException(m_uri, facadeName);

		return new MetadataRepositoryFacade(facadeName, new LoggingMetadataRepository(this,
			ProvisioningHelper.getMetadataRepository(url(repoURI)), getFacadeArea(facadeName, true)));
	}

	public synchronized List<String> getMetadataRepositoryFacadeNames()
	{
		return getRepositoryFacadeNames(m_knownMetadataRepos);
	}

	public JSONSerializer getSerializer()
	{
		return m_serializer;
	}

	public void hardReset() throws ProvisionException
	{
		synchronized(s_servers)
		{
			deleteRecursive(Activator.getAgentLocation());
			s_servers.clear();
			m_knownArtifactRepos.clear();
			m_knownMetadataRepos.clear();
			s_servers.put(m_uri, this);
		}
	}

	private void deleteRecursive(File file)
	{
		File[] subfiles = file.listFiles();
		if(subfiles != null)
			for(File subfile : subfiles)
				deleteRecursive(subfile);
		file.delete();
	}

	private boolean deleteRepositoryFacade(String facadeName, Map<String, URI> knownRepos, boolean isMetadata)
	throws ProvisionException
	{
		URI repoURI = knownRepos.get(facadeName);
		if(repoURI == null)
			return false;

		if(isMetadata)
			Activator.getMetadataRepositoryManager().removeRepository(url(repoURI));
		else
			Activator.getArtifactRepositoryManager().removeRepository(url(repoURI));

		deleteRecursive(getFacadeArea(facadeName, isMetadata));
		try
		{
			knownRepos.remove(facadeName);
			save();
		}
		catch(CoreException e)
		{
			throw new ProvisionException(e.getStatus());
		}
		return true;
	}

	private File getFacadeArea(String facadeName, boolean isMetadata)
	{
		return new File(new File(Activator.getAgentLocation(), isMetadata ? "metadata" : "artifact"),
			facadeName);
	}

	private List<String> getRepositoryFacadeNames(Map<String, URI> knownRepos)
	{
		ArrayList<String> names = new ArrayList<String>(knownRepos.keySet());
		Collections.sort(names);
		return names;
	}

	private File getStateFile()
	{
		File repoArea = Activator.getAgentLocation();
		byte[] repoIDBytes;
		try
		{
			repoIDBytes = m_uri.toASCIIString().getBytes("US-ASCII");
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
		UUID repoID = UUID.nameUUIDFromBytes(repoIDBytes);
		return new File(repoArea, repoID.toString());
	}

	private void load() throws IOException
	{
		Properties props = new Properties();
		InputStream input = null;
		try
		{
			input = new BufferedInputStream(new FileInputStream(getStateFile()));
			props.load(input);
		}
		catch(FileNotFoundException e)
		{
		}
		finally
		{
			IOUtils.close(input);
		}

		Enumeration<?> names = props.propertyNames();
		while(names.hasMoreElements())
		{
			String propName = (String)names.nextElement();
			boolean isMeta = propName.startsWith(PROP_METADATAREPOSITORY_PREFIX);
			if(!(isMeta || propName.startsWith(PROP_ARTIFACTREPOSITORY_PREFIX)))
				continue;

			String repoURI = props.getProperty(propName);
			if(repoURI == null)
				continue;

			URI uri;
			try
			{
				uri = new URI(repoURI);
			}
			catch(URISyntaxException e)
			{
				e.printStackTrace();
				continue;
			}

			if(isMeta)
				m_knownMetadataRepos.put(propName.substring(PROP_METADATAREPOSITORY_PREFIX.length()), uri);
			else
				m_knownArtifactRepos.put(propName.substring(PROP_ARTIFACTREPOSITORY_PREFIX.length()), uri);
		}
	}

	private void save() throws ProvisionException
	{
		Properties props = new Properties();
		for(Map.Entry<String, URI> entry : m_knownMetadataRepos.entrySet())
			props.setProperty(PROP_METADATAREPOSITORY_PREFIX + entry.getKey(), entry.getValue().toString());

		for(Map.Entry<String, URI> entry : m_knownArtifactRepos.entrySet())
			props.setProperty(PROP_ARTIFACTREPOSITORY_PREFIX + entry.getKey(), entry.getValue().toString());

		OutputStream output = null;
		try
		{
			output = new BufferedOutputStream(new FileOutputStream(getStateFile()));
			props.store(output, null);
		}
		catch(IOException e)
		{
			throw new ProvisionException(BuckminsterException.createStatus(e));
		}
		finally
		{
			IOUtils.close(output);
		}
	}
}

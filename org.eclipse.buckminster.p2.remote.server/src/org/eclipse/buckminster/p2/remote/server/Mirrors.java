/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.query.Query;
import org.jabsorb.serializer.UnmarshallException;

/**
 * @author Thomas Hallgren
 */
public class Mirrors
{
	private final RepositoryServer m_server;

	private final File m_mirrorsFile;

	private final Map<String, Query> m_mirrorsMap;

	public Mirrors(File mirrorsFile, RepositoryServer server) throws ProvisionException
	{
		m_mirrorsFile = mirrorsFile;
		m_server = server;
		Map<String, Query> mirrorsMap = null;
		Reader input = null;
		try
		{
			input = new InputStreamReader(new FileInputStream(m_mirrorsFile), "UTF-8");
			StringWriter wrt = new StringWriter();
			char[] buffer = new char[0x4000];
			int cnt;
			while((cnt = input.read(buffer)) > 0)
				wrt.write(buffer, 0, cnt);
			mirrorsMap = parseMirrors(wrt.toString());
		}
		catch(FileNotFoundException e)
		{
		}
		catch(Exception e)
		{
			throw new ProvisionException(BuckminsterException.wrap(e).getStatus());
		}
		finally
		{
			IOUtils.close(input);
		}

		if(mirrorsMap == null)
		{
			m_mirrorsMap = new HashMap<String, Query>();
			save();
		}
		else
			m_mirrorsMap = mirrorsMap;
	}

	public boolean addMirror(URI uri, Query query) throws ProvisionException
	{
		if(m_mirrorsMap.containsKey(uri))
		{
			Query oldEntry = m_mirrorsMap.get(uri);
			if(oldEntry == query || (oldEntry != null && oldEntry.equals(query)))
				return false;
		}

		m_mirrorsMap.put(uri.toString(), query);
		save();
		return true;
	}

	public Map<String, Query> getMirrors()
	{
		return Collections.unmodifiableMap(m_mirrorsMap);
	}

	public void save() throws ProvisionException
	{
		Writer output = null;
		try
		{
			output = new OutputStreamWriter(new FileOutputStream(m_mirrorsFile), "UTF-8");
			output.write(m_server.getSerializer().toJSON(m_mirrorsMap));
		}
		catch(Exception e)
		{
			throw new ProvisionException(BuckminsterException.wrap(e).getStatus());
		}
		finally
		{
			IOUtils.close(output);
		}
	}

	@SuppressWarnings("unchecked")
	private Map<String, Query> parseMirrors(String json) throws UnmarshallException
	{
		return (Map)m_server.getSerializer().fromJSON(json);
	}
}

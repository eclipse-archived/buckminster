/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.server;

import java.io.File;
import java.net.URL;
import java.util.Map;

import org.eclipse.buckminster.p2.remote.change.SetDescription;
import org.eclipse.buckminster.p2.remote.change.SetName;
import org.eclipse.buckminster.p2.remote.change.SetProperty;
import org.eclipse.buckminster.p2.remote.change.SetProvider;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.equinox.internal.provisional.p2.core.repository.IRepository;

public class LoggingRepository extends PlatformObject implements IRepository
{
	protected final IRepository wrappedRepository;

	private final ChangeLog m_changeLog;

	public LoggingRepository(IRepository repository, File changeLogFile) throws CoreException
	{
		wrappedRepository = repository;
		m_changeLog = new ChangeLog(changeLogFile, 0L);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter)
	{
		if(IRepository.class.isAssignableFrom(adapter))
			return this;
		return super.getAdapter(adapter);
	}

	public ChangeLog getChangeLog()
	{
		return m_changeLog;
	}

	public String getDescription()
	{
		return wrappedRepository.getDescription();
	}

	public URL getLocation()
	{
		return wrappedRepository.getLocation();
	}

	public String getName()
	{
		return wrappedRepository.getName();
	}

	@SuppressWarnings("unchecked")
	public Map getProperties()
	{
		return wrappedRepository.getProperties();
	}

	public String getProvider()
	{
		return wrappedRepository.getProvider();
	}

	public String getType()
	{
		return wrappedRepository.getType();
	}

	public String getVersion()
	{
		return wrappedRepository.getVersion();
	}

	public boolean isModifiable()
	{
		return wrappedRepository.isModifiable();
	}

	public void setDescription(String description)
	{
		SetDescription change = new SetDescription();
		change.setDescription(description);
		m_changeLog.addChange(change);
		wrappedRepository.setDescription(description);
	}

	public void setName(String name)
	{
		SetName change = new SetName();
		change.setName(name);
		m_changeLog.addChange(change);
		wrappedRepository.setName(name);
	}

	public String setProperty(String key, String value)
	{
		SetProperty change = new SetProperty();
		change.setKey(key);
		change.setValue(value);
		m_changeLog.addChange(change);
		return wrappedRepository.setProperty(key, value);
	}

	public void setProvider(String provider)
	{
		SetProvider change = new SetProvider();
		change.setProvider(provider);
		m_changeLog.addChange(change);
		wrappedRepository.setProvider(provider);
	}
}

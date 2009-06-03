package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.equinox.internal.p2.publisher.eclipse.ExecutablesDescriptor;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.p2.publisher.IPublisherAction;

@SuppressWarnings("restriction")
public class ApplicationLauncherAction extends org.eclipse.equinox.p2.publisher.eclipse.ApplicationLauncherAction
{
	private final String m_id;

	private final Version m_version;

	private final String m_flavor;

	public ApplicationLauncherAction(String id, Version version, String flavor, String executableName, File location,
			String[] configSpecs)
	{
		super(id, version, flavor, executableName, location, configSpecs);
		m_id = id;
		m_version = version;
		m_flavor = flavor;
	}

	@Override
	protected Collection<IPublisherAction> createExecutablesActions(String[] configSpecs)
	{
		Collection<IPublisherAction> actions = new ArrayList<IPublisherAction>(configSpecs.length);
		for(int i = 0; i < configSpecs.length; i++)
		{
			ExecutablesDescriptor executables = computeExecutables(configSpecs[i]);
			IPublisherAction action = new EquinoxExecutableAction(executables, configSpecs[i], m_id, m_version,
					m_flavor);
			actions.add(action);
		}
		return actions;
	}
}

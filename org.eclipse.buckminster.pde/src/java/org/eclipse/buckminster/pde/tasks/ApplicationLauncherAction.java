package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.equinox.internal.p2.publisher.eclipse.ExecutablesDescriptor;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.publisher.AbstractPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherAction;

@SuppressWarnings("restriction")
public class ApplicationLauncherAction extends org.eclipse.equinox.p2.publisher.eclipse.ApplicationLauncherAction {
	private final String id;

	private final Version version;

	private final String flavor;

	private final File location;

	public ApplicationLauncherAction(String id, Version version, String flavor, String executableName, File location, String[] configSpecs) {
		super(id, version, flavor, executableName, location, configSpecs);
		this.id = id;
		this.version = version;
		this.flavor = flavor;
		this.location = location;
	}

	@Override
	protected ExecutablesDescriptor computeExecutables(String configSpec) {
		// See if we know about an executables feature then use it as the source
		ExecutablesDescriptor result = ExecutablesDescriptor.createExecutablesFromFeature(location, configSpec);
		if (result != null)
			return result;
		// otherwise, assume that we are running against an Eclipse install and
		// do the default thing
		String os = AbstractPublisherAction.parseConfigSpec(configSpec)[1];
		return ExecutablesDescriptor.createDescriptor(os, "eclipse", location); //$NON-NLS-1$
	}

	@Override
	protected Collection<IPublisherAction> createExecutablesActions(String[] configSpecs) {
		Collection<IPublisherAction> actions = new ArrayList<IPublisherAction>(configSpecs.length);
		for (int i = 0; i < configSpecs.length; i++) {
			ExecutablesDescriptor executables = computeExecutables(configSpecs[i]);
			IPublisherAction action = new EquinoxExecutableAction(executables, configSpecs[i], id, version, flavor);
			actions.add(action);
		}
		return actions;
	}

}

package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.equinox.internal.p2.publisher.eclipse.ExecutablesDescriptor;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.publisher.AbstractPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.osgi.service.environment.Constants;

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
		String os = AbstractPublisherAction.parseConfigSpec(configSpec)[1];
		ExecutablesDescriptor result = ExecutablesDescriptor.createExecutablesFromFeature(location, configSpec);
		if (result == null) {
			// otherwise, assume that we are running against an Eclipse install
			// and do the default thing
			result = ExecutablesDescriptor.createDescriptor(os, "eclipse", location); //$NON-NLS-1$
		}
		if (Constants.OS_MACOSX.equals(os)) {
			String name = result.getExecutableName();
			if (name != null) {
				// First character of the name should be in upper case.
				StringBuilder appNameBld = new StringBuilder();
				appNameBld.append(Character.toUpperCase(name.charAt(0)));
				appNameBld.append(name, 1, name.length());
				result.setExecutableName(appNameBld.toString(), false);
			}
		}
		return result;
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

package org.eclipse.buckminster.jdt.commands;

import java.io.File;
import java.util.List;

import org.eclipse.buckminster.cmdline.Headless;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.commands.WorkspaceCommand;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.internal.launching.StandardVMType;
import org.eclipse.jdt.launching.AbstractVMInstallType;
import org.eclipse.jdt.launching.IVMInstallType;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.VMStandin;
import org.eclipse.osgi.util.NLS;

@SuppressWarnings("restriction")
public class InstallJRE extends WorkspaceCommand {
	static private final OptionDescriptor OPT_LOCATION = new OptionDescriptor('L', "location", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor OPT_NAME = new OptionDescriptor('N', "name", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor OPT_DEFAULT_ARGS = new OptionDescriptor('D', "defaultArgs", OptionValueType.REQUIRED); //$NON-NLS-1$

	private File location;

	private String name;

	private String defaultArgs;

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		appendHere.add(OPT_LOCATION);
		appendHere.add(OPT_NAME);
		appendHere.add(OPT_DEFAULT_ARGS);
		super.getOptionDescriptors(appendHere);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		if (option.is(OPT_LOCATION))
			location = new File(option.getValue());
		else if (option.is(OPT_NAME))
			name = option.getValue();
		else if (option.is(OPT_DEFAULT_ARGS))
			name = option.getValue();
		else
			super.handleOption(option);
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception {
		if (unparsed.length > 0)
			throw new UsageException(Messages.Too_many_arguments);
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		if (location == null)
			throw new UsageException("Missing required option --location <JRE location>");

		if (!location.isDirectory())
			throw new UsageException(NLS.bind("JRE location {0} does not appoint a directory", location.getAbsolutePath()));

		if (name == null)
			name = location.getName();

		IVMInstallType vmType = JavaRuntime.getVMInstallType(StandardVMType.ID_STANDARD_VM_TYPE);
		IStatus status = vmType.validateInstallLocation(location);
		if (!status.isOK())
			throw new CoreException(status);

		long unique = System.currentTimeMillis();
		while (vmType.findVMInstall(String.valueOf(unique)) != null)
			unique++;

		VMStandin vm = new VMStandin(vmType, Long.toString(unique));
		vm.setInstallLocation(location);
		vm.setLibraryLocations(vmType.getDefaultLibraryLocations(location));
		vm.setName(name);
		if (vmType instanceof AbstractVMInstallType) {
			AbstractVMInstallType atype = (AbstractVMInstallType) vmType;
			vm.setJavadocLocation(atype.getDefaultJavadocLocation(location));
			String vmArgs = atype.getDefaultVMArguments(location);
			if (defaultArgs != null) {
				if (vmArgs == null)
					vmArgs = defaultArgs;
				else
					vmArgs = vmArgs + ' ' + defaultArgs;
			}
			vm.setVMArgs(vmArgs);
		}
		vm.convertToRealVM();
		return Headless.EXIT_OK;
	}
}

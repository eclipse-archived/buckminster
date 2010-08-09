/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public class Perform extends WorkspaceCommand {
	static private final OptionDescriptor FORCED_DESCRIPTOR = new OptionDescriptor('F', "force", OptionValueType.NONE); //$NON-NLS-1$

	static private final OptionDescriptor QUIET_DESCRIPTOR = new OptionDescriptor('Q', "quiet", OptionValueType.NONE); //$NON-NLS-1$

	static private final OptionDescriptor MAXWARNINGS_DESCRIPTOR = new OptionDescriptor('W', "maxWarnings", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private final List<Attribute> attributes = new ArrayList<Attribute>();

	private boolean forced = false;

	private int maxWarnings = -1;

	private boolean quiet;

	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}

	public boolean isQuiet() {
		return quiet;
	}

	public void setQuiet(boolean quiet) {
		this.quiet = quiet;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		appendHere.add(MAXWARNINGS_DESCRIPTOR);
		appendHere.add(FORCED_DESCRIPTOR);
		appendHere.add(QUIET_DESCRIPTOR);
		super.getOptionDescriptors(appendHere);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		if (option.is(MAXWARNINGS_DESCRIPTOR))
			maxWarnings = Integer.parseInt(option.getValue());
		else if (option.is(FORCED_DESCRIPTOR))
			forced = true;
		else if (option.is(QUIET_DESCRIPTOR))
			quiet = true;
		else
			super.handleOption(option);
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception {
		for (String s : unparsed) {
			String component = null;
			String attribute = null;
			int splitIdx = s.lastIndexOf('#');
			if (splitIdx > 0) {
				attribute = s.substring(splitIdx + 1).trim();
				if (attribute.length() == 0)
					attribute = null;
				component = s.substring(0, splitIdx).trim();
				if (component.length() == 0)
					component = null;
			}
			if (component == null || attribute == null)
				throw new UsageException(Messages.Attribute_names_must_be_in_the_form_component_name_attribute_name);

			CSpec cspec = WorkspaceInfo.getResolution(CommonFactory.eINSTANCE.createComponentIdentifier(component)).getCSpec();
			addAttribute(cspec.getRequiredAttribute(attribute));
		}
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		if (attributes.isEmpty())
			throw new UsageException(Messages.No_attributes_specified);

		IPerformManager pm = CorePlugin.getPerformManager();
		IStatus status = pm.perform(attributes, null, forced, quiet, monitor).getStatus();

		if (status.isOK())
			return 0;

		// Get all problem markers. Sort them by timestamp
		//
		IMarker[] markers = ResourcesPlugin.getWorkspace().getRoot().findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		if (markers.length == 0)
			return 0;

		if (isQuiet()) {
			for (IMarker problem : markers)
				if (problem.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) == IMarker.SEVERITY_ERROR)
					return 1;
			return 0;
		}

		Map<Long, IMarker> problems = new TreeMap<Long, IMarker>();
		for (IMarker problem : markers)
			problems.put(Long.valueOf(problem.getCreationTime()), problem);

		int warnings = 0;
		int errors = 0;
		int exitValue = 0;
		for (IMarker problem : problems.values()) {
			switch (problem.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO)) {
				case IMarker.SEVERITY_ERROR:
					exitValue = 1;
					errors++;
					System.err.println(formatMarkerMessage("Error", problem)); //$NON-NLS-1$
					break;
				case IMarker.SEVERITY_WARNING:
					warnings++;
					System.err.println(formatMarkerMessage("Warning", problem)); //$NON-NLS-1$
					break;
				case IMarker.SEVERITY_INFO:
					System.out.println(formatMarkerMessage("Info", problem)); //$NON-NLS-1$
			}
		}

		if (warnings + errors > 0) {
			StringBuilder bld = new StringBuilder();
			bld.append(Messages.Found);
			if (errors > 0) {
				bld.append(errors);
				bld.append(Messages.Errors);
				if (warnings > 0)
					bld.append(Messages.And);
			}
			if (warnings > 0) {
				bld.append(warnings);
				bld.append(Messages.Warnings);
			}
			System.err.println(bld.toString());
		}

		if (maxWarnings != -1 && warnings > maxWarnings) {
			System.err.println(Messages.Too_many_warnings_Exiting_with_error_status);
			exitValue = 1;
		}

		if (exitValue > 0)
			System.err.println(Messages.Build_failed);
		return exitValue;
	}

	private String formatMarkerMessage(String type, IMarker problem) {
		StringBuilder bld = new StringBuilder();
		bld.append(type);
		bld.append(": file "); //$NON-NLS-1$
		bld.append(problem.getResource().getLocation().toOSString());
		int line = problem.getAttribute(IMarker.LINE_NUMBER, -1);
		if (line > 0) {
			bld.append(", line "); //$NON-NLS-1$
			bld.append(line);
		}
		bld.append(": "); //$NON-NLS-1$
		bld.append(problem.getAttribute(IMarker.MESSAGE, "")); //$NON-NLS-1$
		return bld.toString();
	}
}

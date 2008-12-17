/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.commands;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

public class Perform extends WorkspaceCommand
{
	static private final OptionDescriptor DEFINE_DESCRIPTOR = new OptionDescriptor('D', "define", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private static final Pattern DEFINE_PATTERN = Pattern.compile("^([^=]+)(?:=(.+))?$"); //$NON-NLS-1$

	static private final OptionDescriptor FORCED_DESCRIPTOR = new OptionDescriptor('F', "force", OptionValueType.NONE); //$NON-NLS-1$

	static private final OptionDescriptor QUIET_DESCRIPTOR = new OptionDescriptor('Q', "quiet", OptionValueType.NONE); //$NON-NLS-1$

	static private final OptionDescriptor MAXWARNINGS_DESCRIPTOR = new OptionDescriptor('W', "maxWarnings", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	static private final OptionDescriptor PROPERTIES_DESCRIPTOR = new OptionDescriptor('P', "properties", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private final List<Attribute> m_attributes = new ArrayList<Attribute>();

	private boolean m_forced = false;

	private int m_maxWarnings = -1;

	private Map<String, String> m_props;

	private boolean m_quiet;

	public void addAttribute(Attribute attribute)
	{
		m_attributes.add(attribute);
	}

	public void addProperties(Map<String, String> properties)
	{
		if(m_props == null)
			m_props = new HashMap<String, String>(properties);
		else
			m_props.putAll(properties);
	}

	public void addProperty(String key, String value)
	{
		if(m_props == null)
			m_props = new HashMap<String, String>();
		m_props.put(key, value);
	}

	public boolean isQuiet()
	{
		return m_quiet;
	}

	public void setQuiet(boolean quiet)
	{
		m_quiet = quiet;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception
	{
		appendHere.add(DEFINE_DESCRIPTOR);
		appendHere.add(PROPERTIES_DESCRIPTOR);
		appendHere.add(MAXWARNINGS_DESCRIPTOR);
		appendHere.add(FORCED_DESCRIPTOR);
		appendHere.add(QUIET_DESCRIPTOR);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(DEFINE_DESCRIPTOR))
		{
			String v = option.getValue();
			Matcher m = DEFINE_PATTERN.matcher(v);
			if(!m.matches())
				throw new IllegalArgumentException(NLS.bind(Messages.Not_a_key_value_string_0, v));
			String key = m.group(1);
			String value = m.group(2) == null
					? "" //$NON-NLS-1$
					: m.group(2);
			addProperty(key, value);
		}
		if(option.is(PROPERTIES_DESCRIPTOR))
		{
			String v = option.getValue();
			InputStream input = null;
			try
			{
				URL propsURL = URLUtils.normalizeToURL(v);
				input = DownloadManager.read(propsURL, null);
				addProperties(new BMProperties(input));
			}
			catch(MalformedURLException e)
			{
				throw new IllegalArgumentException(NLS.bind(Messages.Invalid_URL_or_Path_0, v));
			}
			finally
			{
				IOUtils.close(input);
			}
		}
		else if(option.is(MAXWARNINGS_DESCRIPTOR))
			m_maxWarnings = Integer.parseInt(option.getValue());
		else if(option.is(FORCED_DESCRIPTOR))
			m_forced = true;
		else if(option.is(QUIET_DESCRIPTOR))
			m_quiet = true;
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		for(String s : unparsed)
		{
			String component = null;
			String attribute = null;
			int splitIdx = s.lastIndexOf('#');
			if(splitIdx > 0)
			{
				attribute = s.substring(splitIdx + 1).trim();
				if(attribute.length() == 0)
					attribute = null;
				component = s.substring(0, splitIdx).trim();
				if(component.length() == 0)
					component = null;
			}
			if(component == null || attribute == null)
				throw new UsageException(Messages.Attribute_names_must_be_in_the_form_component_name_attribute_name);

			CSpec cspec = WorkspaceInfo.getResolution(ComponentIdentifier.parse(component)).getCSpec();
			addAttribute(cspec.getRequiredAttribute(attribute));
		}
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception
	{
		if(m_attributes.isEmpty())
			throw new UsageException(Messages.No_attributes_specified);

		IPerformManager pm = CorePlugin.getPerformManager();
		IStatus status = pm.perform(m_attributes, m_props, m_forced, m_quiet, monitor).getStatus();

		if(status.isOK())
			return 0;

		// Get all problem markers. Sort them by timestamp
		//
		IMarker[] markers = ResourcesPlugin.getWorkspace().getRoot().findMarkers(IMarker.PROBLEM, true,
				IResource.DEPTH_INFINITE);
		if(markers.length == 0)
			return 0;

		if(isQuiet())
		{
			for(IMarker problem : markers)
				if(problem.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) == IMarker.SEVERITY_ERROR)
					return 1;
			return 0;
		}

		Map<Long, IMarker> problems = new TreeMap<Long, IMarker>();
		for(IMarker problem : markers)
			problems.put(Long.valueOf(problem.getCreationTime()), problem);

		int warnings = 0;
		int errors = 0;
		int exitValue = 0;
		for(IMarker problem : problems.values())
		{
			switch(problem.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO))
			{
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

		if(warnings + errors > 0)
		{
			StringBuilder bld = new StringBuilder();
			bld.append(Messages.Found);
			if(errors > 0)
			{
				bld.append(errors);
				bld.append(Messages.Errors);
				if(warnings > 0)
					bld.append(Messages.And);
			}
			if(warnings > 0)
			{
				bld.append(warnings);
				bld.append(Messages.Warnings);
			}
			System.err.println(bld.toString());
		}

		if(m_maxWarnings != -1 && warnings > m_maxWarnings)
		{
			System.err.println(Messages.Too_many_warnings_Exiting_with_error_status);
			exitValue = 1;
		}

		if(exitValue > 0)
			System.err.println(Messages.Build_failed);
		return exitValue;
	}

	private String formatMarkerMessage(String type, IMarker problem)
	{
		StringBuilder bld = new StringBuilder();
		bld.append(type);
		bld.append(": file "); //$NON-NLS-1$
		bld.append(problem.getResource().getLocation().toOSString());
		int line = problem.getAttribute(IMarker.LINE_NUMBER, -1);
		if(line > 0)
		{
			bld.append(", line "); //$NON-NLS-1$
			bld.append(line);
		}
		bld.append(": "); //$NON-NLS-1$
		bld.append(problem.getAttribute(IMarker.MESSAGE, "")); //$NON-NLS-1$
		return bld.toString();
	}
}

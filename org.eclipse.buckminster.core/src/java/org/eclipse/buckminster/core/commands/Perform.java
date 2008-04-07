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

public class Perform extends WorkspaceCommand
{
	static private final OptionDescriptor DEFINE_DESCRIPTOR = new OptionDescriptor('D', "define", OptionValueType.REQUIRED);

	static private final OptionDescriptor PROPERTIES_DESCRIPTOR = new OptionDescriptor('P', "properties", OptionValueType.REQUIRED);

	static private final OptionDescriptor MAXWARNINGS_DESCRIPTOR = new OptionDescriptor('W', "maxWarnings", OptionValueType.REQUIRED);

	static private final OptionDescriptor FORCED_DESCRIPTOR = new OptionDescriptor('F', "force", OptionValueType.NONE);

	private static final Pattern DEFINE_PATTERN = Pattern.compile("^([^=]+)(?:=(.+))?$");
	
	private Map<String, String> m_props;
	
	private int m_maxWarnings = -1;

	private boolean m_forced = false;

	private final List<Attribute> m_attributes = new ArrayList<Attribute>();
	
	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception
	{
		if(m_attributes.isEmpty())
			throw new UsageException("No attributes specified");

		IPerformManager pm = CorePlugin.getPerformManager();
		IStatus status = pm.perform(m_attributes, m_props, m_forced, monitor).getStatus();

		if(status.isOK())
			return 0;

		System.err.print(status.getMessage());

		// Get all problem markers. Sort them by timestamp
		//
		Map<Long,IMarker> problems = null;
		for(IMarker problem : ResourcesPlugin.getWorkspace().getRoot().findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE))
		{
			if(problems == null)
				problems = new TreeMap<Long, IMarker>();
			problems.put(Long.valueOf(problem.getCreationTime()), problem);
		}
		if(problems == null)
			return 0;

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
				System.err.println(formatMarkerMessage("Error", problem));
				break;
			case IMarker.SEVERITY_WARNING:
				warnings++;
				System.err.println(formatMarkerMessage("Warning", problem));
				break;
			case IMarker.SEVERITY_INFO:
				System.out.println(formatMarkerMessage("Info", problem));
			}
		}

		if(warnings + errors > 0)
		{
			StringBuilder bld = new StringBuilder();
			bld.append("Found ");
			if(errors > 0)
			{
				bld.append(errors);
				bld.append(" errors");
				if(warnings > 0)
					bld.append(" and ");
			}
			if(warnings > 0)
			{
				bld.append(warnings);
				bld.append(" warnings");
			}
			System.err.println(bld.toString());
		}

		if(m_maxWarnings != -1 && warnings > m_maxWarnings)
		{
			System.err.println("Too many warnings. Exiting with error status");
			exitValue = 1;
		}

		if(exitValue > 0)
			System.err.println("Build failed!");
		return exitValue;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception
	{
		appendHere.add(DEFINE_DESCRIPTOR);
		appendHere.add(PROPERTIES_DESCRIPTOR);
		appendHere.add(MAXWARNINGS_DESCRIPTOR);
		appendHere.add(FORCED_DESCRIPTOR);
	}

    @Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(DEFINE_DESCRIPTOR))
		{
			String v = option.getValue();
			Matcher m = DEFINE_PATTERN.matcher(v);
			if(!m.matches())
				throw new IllegalArgumentException("Not a key[=value] string : " + v);
			String key = m.group(1);
			String value = m.group(2) == null ? "" : m.group(2);
			if(m_props == null)
				 m_props = new HashMap<String, String>();
			m_props.put(key, value);
		}
		if(option.is(PROPERTIES_DESCRIPTOR))
		{
			String v = option.getValue();
			InputStream input = null;
			try
			{
				URL propsURL = URLUtils.normalizeToURL(v);
				input = DownloadManager.read(propsURL);
				Map<String,String> props = new BMProperties(input);
				if(m_props == null)
					m_props = props;
				else
					m_props.putAll(props);
			}
			catch(MalformedURLException e)
			{
				throw new IllegalArgumentException("Invalid URL or Path: " + v);
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
    			throw new UsageException("Attribute names must be in the form <component name>#<attribute name>");

    		CSpec cspec = WorkspaceInfo.getResolution(ComponentIdentifier.parse(component)).getCSpec();
    		m_attributes.add(cspec.getRequiredAttribute(attribute));
    	}
	}

	private String formatMarkerMessage(String type, IMarker problem)
	{
		StringBuilder bld = new StringBuilder();
		bld.append(type);
		bld.append(": file ");
		bld.append(problem.getResource().getLocation().toOSString());
		int line = problem.getAttribute(IMarker.LINE_NUMBER, -1);
		if(line > 0)
		{
			bld.append(", line ");
			bld.append(line);
		}
		bld.append(": ");
		bld.append(problem.getAttribute(IMarker.MESSAGE, ""));
		return bld.toString();
	}
}

/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.internal.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.manifest.Difference;
import org.eclipse.buckminster.core.manifest.Manifest;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.IProgressMonitor;

public class DiffManifest extends AbstractCommand
{
	static private final OptionDescriptor ALGORITHM_DESCRIPTOR = new OptionDescriptor('a', "algorithm",
			OptionValueType.REQUIRED);

	static private final OptionDescriptor ASSUMEDLINESEPARATOR_DESCRIPTOR = new OptionDescriptor('l',
			"assumedlineseparator", OptionValueType.REQUIRED);

	static private final OptionDescriptor REPORT_DESCRIPTOR = new OptionDescriptor('r', "report", OptionValueType.NONE);

	static private final OptionDescriptor DIFFERENCESONLY_DESCRIPTOR = new OptionDescriptor('d', "differencesonly",
			OptionValueType.NONE);

	static private final OptionDescriptor SETSTATUS_DESCRIPTOR = new OptionDescriptor('s', "setstatus",
			OptionValueType.NONE);

	private String m_algorithm = null;

	private String m_assumedLineSep = null;

	private boolean m_report = false;

	private boolean m_differencesOnly = false;

	private boolean m_setStatus = false;

	private File m_left = null;

	private File m_right = null;

	@SuppressWarnings("unchecked")
	@Override
	protected void getOptionDescriptors(List appendHere) throws Exception
	{
		appendHere.add(ALGORITHM_DESCRIPTOR);
		appendHere.add(ASSUMEDLINESEPARATOR_DESCRIPTOR);
		appendHere.add(REPORT_DESCRIPTOR);
		appendHere.add(DIFFERENCESONLY_DESCRIPTOR);
		appendHere.add(SETSTATUS_DESCRIPTOR);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(ALGORITHM_DESCRIPTOR))
			m_algorithm = option.getValue();
		else if(option.is(ASSUMEDLINESEPARATOR_DESCRIPTOR))
			m_assumedLineSep = this.convertToRealLineSep(option.getValue());
		else if(option.is(REPORT_DESCRIPTOR))
			m_report = true;
		else if(option.is(DIFFERENCESONLY_DESCRIPTOR))
			m_differencesOnly = true;
		else if(option.is(SETSTATUS_DESCRIPTOR))
			m_setStatus = true;
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		if(unparsed.length > 2)
			throw new UsageException("Too many arguments.");
		if(unparsed.length > 0)
			m_left = new File(unparsed[0]);
		if(unparsed.length > 1)
			m_right = new File(unparsed[1]);
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		if(m_left == null || m_right == null)
			throw new SimpleErrorExitException("No left/right root or manifest given");

		if(!m_left.exists() || !m_right.exists())
			throw new SimpleErrorExitException("Both left and right must exist as either file or directory");

		Difference d = null;
		Difference.RESULT r = null;
		try
		{
			monitor.beginTask(null, 4);

			Manifest lmf = m_left.isFile()
					? Manifest.fromBufferedReader(new BufferedReader(new FileReader(m_left)), null)
					: Manifest.create(m_left, m_algorithm, m_assumedLineSep, null, MonitorUtils.subMonitor(monitor, 1));
			MonitorUtils.testCancelStatus(monitor);

			Manifest rmf = m_right.isFile()
					? Manifest.fromBufferedReader(new BufferedReader(new FileReader(m_right)), null)
					: Manifest.create(m_right, m_algorithm, m_assumedLineSep, null, MonitorUtils.subMonitor(monitor, 1));
			MonitorUtils.testCancelStatus(monitor);

			d = lmf.getDifference(rmf);
			CorePlugin.getLogger().info(d.toString());
			MonitorUtils.worked(monitor, 1);

			r = d.getResult();
			MonitorUtils.worked(monitor, 1);
		}
		finally
		{
			monitor.done();
		}

		int status = 0;
		switch(r)
		{
		case EQUAL:
			System.out.println("Manifests are equal.");
			break;
		case MATCHING:
			System.out.println("Manifests are not equal, but matches and can be merged.");
			status = 1;
			break;
		case MERGABLE_NONMATCHING:
			System.out.println("Manifests are not equal and do not match, but can be merged.");
			status = 2;
			break;
		case NONMATCHING:
			System.out.println("Manifests do not match.");
			status = 3;
			break;
		default:
			throw new InternalError("Unexpected diff result: " + r);
		}

		if(m_report)
			d.report(new PrintWriter(System.out, true), m_differencesOnly);

		return m_setStatus
				? status
				: 0;
	}

	private String convertToRealLineSep(String symbolicLineSep)
	{
		if(symbolicLineSep.equalsIgnoreCase("crlf"))
			return "\r\n";
		else if(symbolicLineSep.equalsIgnoreCase("cr"))
			return "\r";
		else if(symbolicLineSep.equalsIgnoreCase("lf"))
			return "\n";
		else
			return new String(TextUtils.makeByteArrayFromHexString(symbolicLineSep));
	}
}

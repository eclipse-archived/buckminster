/*******************************************************************************
 * Copyright (c) 2009, eXXcellent solutions gmbh
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * Contributors:
 *     Achim Demelt - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.emma;

import java.util.List;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.emma.internal.Messages;
import org.eclipse.buckminster.junit.JUnitCommand;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.mountainminds.eclemma.core.CoverageTools;
import com.mountainminds.eclemma.core.ICoverageSession;
import com.mountainminds.eclemma.core.ISessionExporter;

public class Emma extends JUnitCommand
{
	private static final OptionDescriptor MERGE_DESCRIPTOR = new OptionDescriptor('m', "merge", OptionValueType.NONE); //$NON-NLS-1$

	private static final OptionDescriptor EMMA_DESCRIPTOR = new OptionDescriptor(null, "emma", OptionValueType.REQUIRED); //$NON-NLS-1$

	private static final OptionDescriptor TXT_DESCRIPTOR = new OptionDescriptor(null, "txt", OptionValueType.REQUIRED); //$NON-NLS-1$

	private static final OptionDescriptor HTML_DESCRIPTOR = new OptionDescriptor(null, "html", OptionValueType.REQUIRED); //$NON-NLS-1$

	private static final OptionDescriptor XML_DESCRIPTOR = new OptionDescriptor(null, "xml", OptionValueType.REQUIRED); //$NON-NLS-1$

	private boolean m_merge;

	private String m_emma;

	private String m_xml;

	private String m_txt;

	private String m_html;

	@Override
	protected String getLaunchMode()
	{
		return CoverageTools.LAUNCH_MODE;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception
	{
		super.getOptionDescriptors(appendHere);

		appendHere.add(MERGE_DESCRIPTOR);
		appendHere.add(EMMA_DESCRIPTOR);
		appendHere.add(HTML_DESCRIPTOR);
		appendHere.add(TXT_DESCRIPTOR);
		appendHere.add(XML_DESCRIPTOR);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		super.handleOption(option);

		if(option.is(MERGE_DESCRIPTOR))
			m_merge = true;
		else if(option.is(EMMA_DESCRIPTOR))
			m_emma = option.getValue();
		else if(option.is(HTML_DESCRIPTOR))
			m_html = option.getValue();
		else if(option.is(TXT_DESCRIPTOR))
			m_txt = option.getValue();
		else if(option.is(XML_DESCRIPTOR))
			m_xml = option.getValue();
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception
	{
		ICoverageSession[] oldSessions = CoverageTools.getSessionManager().getSessions();

		int result = super.internalRun(monitor);

		// check for coverage sessions
		ICoverageSession[] sessions = CoverageTools.getSessionManager().getSessions();
		if(sessions == null || sessions.length == 0 || (oldSessions != null && oldSessions.length == sessions.length))
			throw BuckminsterException.fromMessage(Messages.Emma_No_coverage_sessions);

		// use newest session as default. merge older sessions if requested
		ICoverageSession session = sessions[sessions.length - 1];
		if(m_merge)
			for(int i = sessions.length - 2; i >= 0; i--)
				session = session.merge(sessions[i], session.getDescription()
						+ " + " + sessions[i].getDescription()); //$NON-NLS-1$

		// export in requested formats
		if(m_emma != null)
			export(session, ISessionExporter.EMMA_FORMAT, m_emma, monitor);
		if(m_html != null)
			export(session, ISessionExporter.HTML_FORMAT, m_html, monitor);
		if(m_txt != null)
			export(session, ISessionExporter.TXT_FORMAT, m_txt, monitor);
		if(m_xml != null)
			export(session, ISessionExporter.XML_FORMAT, m_xml, monitor);

		return result;
	}

	private void export(ICoverageSession coverageSession, int format, String destinationFile, IProgressMonitor monitor)
			throws CoreException
	{
		ISessionExporter exporter = CoverageTools.getExporter(coverageSession);
		exporter.setDestination(destinationFile);
		exporter.setFormat(format);
		exporter.export(monitor);
	}
}

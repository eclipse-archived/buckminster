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

import java.text.MessageFormat;
import java.util.Date;
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
import com.mountainminds.eclemma.core.ISessionExporter.ExportFormat;
import com.mountainminds.eclemma.core.ISessionManager;

public class Emma extends JUnitCommand {
	private static final OptionDescriptor MERGE_DESCRIPTOR = new OptionDescriptor('m', "merge", OptionValueType.NONE); //$NON-NLS-1$

	private static final OptionDescriptor EXEC_DESCRIPTOR = new OptionDescriptor(null, "exec", OptionValueType.REQUIRED); //$NON-NLS-1$

	private static final OptionDescriptor CSV_DESCRIPTOR = new OptionDescriptor(null, "csv", OptionValueType.REQUIRED); //$NON-NLS-1$

	private static final OptionDescriptor HTML_DESCRIPTOR = new OptionDescriptor(null, "html", OptionValueType.REQUIRED); //$NON-NLS-1$

	private static final OptionDescriptor HTMLZIP_DESCRIPTOR = new OptionDescriptor(null, "htmlzip", OptionValueType.REQUIRED); //$NON-NLS-1$

	private static final OptionDescriptor XML_DESCRIPTOR = new OptionDescriptor(null, "xml", OptionValueType.REQUIRED); //$NON-NLS-1$

	private boolean merge;

	private String exec;

	private String htmlzip;

	private String xml;

	private String csv;

	private String html;

	@Override
	protected String getLaunchMode() {
		return CoverageTools.LAUNCH_MODE;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		super.getOptionDescriptors(appendHere);

		appendHere.add(MERGE_DESCRIPTOR);
		appendHere.add(EXEC_DESCRIPTOR);
		appendHere.add(HTML_DESCRIPTOR);
		appendHere.add(CSV_DESCRIPTOR);
		appendHere.add(XML_DESCRIPTOR);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		super.handleOption(option);

		if (option.is(MERGE_DESCRIPTOR))
			merge = true;
		else if (option.is(EXEC_DESCRIPTOR))
			exec = option.getValue();
		else if (option.is(HTML_DESCRIPTOR))
			html = option.getValue();
		else if (option.is(HTMLZIP_DESCRIPTOR))
			htmlzip = option.getValue();
		else if (option.is(CSV_DESCRIPTOR))
			csv = option.getValue();
		else if (option.is(XML_DESCRIPTOR))
			xml = option.getValue();
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		final ISessionManager sm = CoverageTools.getSessionManager();
		List<ICoverageSession> oldSessions = sm.getSessions();

		int result = super.internalRun(monitor);

		// check for coverage sessions
		List<ICoverageSession> sessions = sm.getSessions();
		if (sessions == null || sessions.isEmpty() || (oldSessions != null && oldSessions.size() == sessions.size()))
			throw BuckminsterException.fromMessage(Messages.Emma_No_coverage_sessions);

		// use newest session as default. merge older sessions if requested
		ICoverageSession session = sessions.get(sessions.size() - 1);
		if (merge)
			sm.mergeSessions(sessions, MessageFormat.format(Messages.Emma_Merge_sessions_description, new Date()), monitor);

		// export in requested formats
		if (exec != null)
			export(session, ExportFormat.EXEC, exec, monitor);
		if (html != null)
			export(session, ExportFormat.HTML, html, monitor);
		if (csv != null)
			export(session, ExportFormat.CSV, csv, monitor);
		if (xml != null)
			export(session, ExportFormat.XML, xml, monitor);
		if (htmlzip != null)
			export(session, ExportFormat.HTMLZIP, htmlzip, monitor);

		return result;
	}

	private void export(ICoverageSession coverageSession, ExportFormat format, String destinationFile, IProgressMonitor monitor) throws CoreException {
		ISessionExporter exporter = CoverageTools.getExporter(coverageSession);
		exporter.setDestination(destinationFile);
		exporter.setFormat(format);
		exporter.export(monitor);
	}
}

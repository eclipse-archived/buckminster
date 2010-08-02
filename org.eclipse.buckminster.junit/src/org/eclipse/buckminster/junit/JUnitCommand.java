/*******************************************************************************
 * Copyright (c) 2009, eXXcellent solutions gmbh and others
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * Contributors:
 *     Achim Demelt - initial API and implementation
 *     Matthias Kappeller - Bug 321064 - No JUnit TestReport created for huge report files
 *******************************************************************************/
package org.eclipse.buckminster.junit;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.commands.Launch;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.junit.internal.Messages;
import org.eclipse.buckminster.junit.internal.ResultSerializer;
import org.eclipse.buckminster.junit.internal.TestListener;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.junit.JUnitCore;
import org.xml.sax.InputSource;

public class JUnitCommand extends Launch {
	private static final OptionDescriptor QUIET_DESCRIPTOR = new OptionDescriptor('q', "quiet", OptionValueType.NONE); //$NON-NLS-1$

	private static final OptionDescriptor OUTPUT_DESCRIPTOR = new OptionDescriptor('o', "output", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private static final OptionDescriptor TERSE_XML_DESCRIPTOR = new OptionDescriptor(null, "terseXML", //$NON-NLS-1$
			OptionValueType.NONE);

	private static final OptionDescriptor FLAT_XML_DESCRIPTOR = new OptionDescriptor(null, "flatXML", //$NON-NLS-1$
			OptionValueType.NONE);

	private static final OptionDescriptor MAX_AWAIT_JUNIT_REPORT_DESCRIPTOR = new OptionDescriptor('t', "maxTimeAwaitJunitReport", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private boolean quiet;

	private String outputPath;

	private boolean terseXML;

	private boolean flatXML;

	private int maxAwaitJunitReport = 10;

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		super.getOptionDescriptors(appendHere);
		appendHere.add(QUIET_DESCRIPTOR);
		appendHere.add(OUTPUT_DESCRIPTOR);
		appendHere.add(TERSE_XML_DESCRIPTOR);
		appendHere.add(FLAT_XML_DESCRIPTOR);
		appendHere.add(MAX_AWAIT_JUNIT_REPORT_DESCRIPTOR);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		super.handleOption(option);

		if (option.is(QUIET_DESCRIPTOR))
			quiet = true;
		else if (option.is(OUTPUT_DESCRIPTOR))
			outputPath = option.getValue();
		else if (option.is(TERSE_XML_DESCRIPTOR))
			terseXML = true;
		else if (option.is(FLAT_XML_DESCRIPTOR))
			flatXML = true;
		else if (option.is(MAX_AWAIT_JUNIT_REPORT_DESCRIPTOR)) {
			maxAwaitJunitReport = Integer.valueOf(option.getValue()).intValue();
		}
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		TestListener listener = new TestListener(quiet);
		JUnitCore.addTestRunListener(listener);

		try {
			int result = super.internalRun(monitor);

			if (outputPath != null)
				exportTestRunSession(listener);

			return result;
		} finally {
			JUnitCore.removeTestRunListener(listener);
		}
	}

	/*
	 * This method is basically copied from JUnitModel#exportTestRunSession in
	 * order to avoid using internal API.
	 */
	private void exportTestRunSession(TestListener listener) throws Exception {

		// bug #321064 - No JUnit TestReport created for huge report files
		try {
			listener.waitForFinish(maxAwaitJunitReport, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			CorePlugin.getLogger().warning(e, Messages.JUnitCommand_Export_interrupted);
			Thread.interrupted();
		}

		// bug #292376 - JUnit reporting fails if output path does not exist
		File parentFile = new File(outputPath).getParentFile();
		if (parentFile != null)
			FileUtils.createDirectory(parentFile, new NullProgressMonitor());

		OutputStream out = new BufferedOutputStream(new FileOutputStream(outputPath));
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		InputSource inputSource = new InputSource();
		SAXSource source = new SAXSource(new ResultSerializer(listener, getRawStdOut(), getRawStdErr(), terseXML, flatXML), inputSource);
		StreamResult result = new StreamResult(out);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); //$NON-NLS-1$
		transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
		/*
		 * Bug in Xalan: Only indents if proprietary property
		 * org.apache.xalan.templates.OutputProperties.S_KEY_INDENT_AMOUNT is
		 * set.
		 * 
		 * Bug in Xalan as shipped with J2SE 5.0: Does not read the
		 * indent-amount property at all >:-(.
		 */
		try {
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (IllegalArgumentException e) {
			// no indentation today...
		}
		transformer.transform(source, result);
	}
}

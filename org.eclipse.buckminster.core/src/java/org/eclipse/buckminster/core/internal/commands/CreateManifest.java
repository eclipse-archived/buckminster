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
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.manifest.Manifest;
import org.eclipse.core.runtime.IProgressMonitor;

public class CreateManifest extends WorkspaceCommand
{
	static private final OptionDescriptor ALGORITHM_DESCRIPTOR = new OptionDescriptor('a', "algorithm",
			OptionValueType.REQUIRED);
	
	static private final OptionDescriptor ASSUMEDLINESEPARATOR_DESCRIPTOR = new OptionDescriptor('l',
			"assumedlineseparator", OptionValueType.REQUIRED);
	
	static private final OptionDescriptor DESCRIPTION_DESCRIPTOR = new OptionDescriptor('d', "description",
			OptionValueType.REQUIRED);
	
	static private final OptionDescriptor INPUT_DESCRIPTOR = new OptionDescriptor('i', "input",
			OptionValueType.REQUIRED);
	
	static private final OptionDescriptor OUTPUT_DESCRIPTOR = new OptionDescriptor('o', "output",
			OptionValueType.REQUIRED);
	
	private String m_algorithm = null;
	
	private String m_assumedLineSep = null;

	private String m_description = null;

	private File m_input = null;

	private File m_output = null;

	private File m_root = null;

	@SuppressWarnings("unchecked")
	@Override
	protected void getOptionDescriptors(List appendHere) throws Exception
	{
		appendHere.add(ALGORITHM_DESCRIPTOR);
		appendHere.add(ASSUMEDLINESEPARATOR_DESCRIPTOR);
		appendHere.add(DESCRIPTION_DESCRIPTOR);
		appendHere.add(INPUT_DESCRIPTOR);
		appendHere.add(OUTPUT_DESCRIPTOR);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if (option.is(ALGORITHM_DESCRIPTOR))
			m_algorithm = option.getValue();
		else if (option.is(ASSUMEDLINESEPARATOR_DESCRIPTOR))
			m_assumedLineSep = this.convertToRealLineSep(option.getValue());
		else if (option.is(DESCRIPTION_DESCRIPTOR))
			m_description = option.getValue();
		else if (option.is(INPUT_DESCRIPTOR))
			m_input = new File(option.getValue());
		else if (option.is(OUTPUT_DESCRIPTOR))
			m_output = new File(option.getValue());
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		if (unparsed.length > 1)
			throw new UsageException("Too many arguments.");
		if (unparsed.length > 0)
			m_root = new File(unparsed[0]);
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception
	{
		if (m_root == null)
			throw new SimpleErrorExitException("No root given");
		
		if (!m_root.isDirectory())
			throw new SimpleErrorExitException("Root must be a directory");
		
		if (m_input != null && !m_input.isFile())
			throw new SimpleErrorExitException("Not a file: " + m_input);
		
		if (m_output == null)
			m_output = new File(m_root.getPath() + ".manifest");

		Manifest mf = Manifest.create(m_root, m_algorithm, m_assumedLineSep, m_description, monitor);

		if (m_input != null)
		{
			System.out.print("Merging with input manifest from '");
			System.out.print(m_input);
			System.out.print("'...");
			Manifest imf = Manifest.fromBufferedReader(new BufferedReader(new FileReader(m_input)), m_description);
			mf = Manifest.merge(imf, mf);
			System.out.println("complete!");
		}
		
		System.out.print("Writing '");
		System.out.print(m_output);
		System.out.print("'...");
		PrintWriter pw = new PrintWriter(new FileWriter(m_output));
		mf.toPrintWriter(pw);
		pw.close();
		System.out.println("complete!");
		
		return 0;
	}

	private String convertToRealLineSep(String symbolicLineSep)
	{
		if (symbolicLineSep.equalsIgnoreCase("crlf"))
			return "\r\n";
		else if (symbolicLineSep.equalsIgnoreCase("cr"))
			return "\r";
		else if (symbolicLineSep.equalsIgnoreCase("lf"))
			return "\n";
		else
			return new String(TextUtils.makeByteArrayFromHexString(symbolicLineSep));
	}
}

/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.demo.hwcommand;

import java.util.List;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class HWCommand extends AbstractCommand
{
	static private final OptionDescriptor GOODBYE_DESCRIPTOR = new OptionDescriptor(null, "goodbye",
		OptionValueType.REQUIRED);

	private String m_goodbye = "Goodbye!";

	@Override
	public int run(IProgressMonitor monitor) throws Exception
	{
        System.out.println("Hello world");
        System.out.println(m_goodbye);
		return 0;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception
	{
		appendHere.add(GOODBYE_DESCRIPTOR);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(GOODBYE_DESCRIPTOR))
			m_goodbye = option.getValue();
	}
}

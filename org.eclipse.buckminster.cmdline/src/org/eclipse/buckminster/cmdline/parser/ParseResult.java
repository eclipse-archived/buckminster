/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cmdline.parser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;

public class ParseResult
{
	static private final String DASH = "-";

	static private final String DOUBLEDASH = "--";

	static public ParseResult parse(String[] args, List optionDescriptors)
		throws NoOptionNameException, AmbiguousOptionException, InvalidOptionException, OptionRequiresValueException
	{
		List options = new ArrayList();
		int max = args.length;
		int i;

		for (i = 0; i < max; i++)
		{
			String arg = args[i].trim();

			if (arg.equals(DOUBLEDASH))
			{
				// if it's a lone doubledash:
				//
				i++;	// consume the arg
				break;	// end parsing
			}

			if (!arg.startsWith(DASH))
				// if it doesn't start with a dash at all:
				//
				break;	// end parsing (but the arg is not 'consumed')

			if (arg.length() == 1)
				// if we reach this spot we only have a single dash which is
				// not normally useful
				// 
				throw new NoOptionNameException();

			// PDE automatically tacks '-pdelaunch' on to the cmd line
			// when starting from the pde. Just throw it away!
			//
			if (arg.equals("-pdelaunch"))
				continue;

			// Eclipse 3.3 adds a -launcher <path to executable>
			// Skip those
			//
			if(arg.equals("-launcher"))
			{
				++i;
				continue;
			}

			// Eclipse 3.3 adds a -name Eclipse <number>
			// Skip those
			//
			if(arg.equals("-name"))
			{
				i += 2;
				continue;
			}

			boolean isLongName = arg.startsWith(DOUBLEDASH);

			// strip the dash(es)
			//
			String argName = arg.substring(1 + (isLongName ? 1 : 0));

			// keep everyone honest, ensure we only get one character if one dash. If
			// there are more characters, we treat the rest as a possible optionValue
			// (i.e. bunching).
			//
			String optionValue = null;
			if (!isLongName && argName.length() > 1)
			{
				optionValue = argName.substring(1);
				argName = argName.substring(0,1);
			}

			// match only *one* of the descriptors, complain if ambiguities are found
			//
			OptionDescriptor descriptorToUse = null;
			int top = optionDescriptors.size();
			for(int idx = 0; idx < top; ++idx)
			{
				// ensure we give precedence to exact matches (long names only) 
				// thus, if --foo and --foobar are valid options, --foo must
				// not be seen as an abbrev for --foobar
				//
				OptionDescriptor descriptor = (OptionDescriptor)optionDescriptors.get(idx);
				if (descriptor.isAcceptableName(argName, isLongName, true))
				{
					descriptorToUse = descriptor;
					break;
				}

				// in the event of long names used, try the abbreviation
				//
				if (isLongName && descriptor.isAcceptableName(argName, isLongName, false))
				{
					if (descriptorToUse != null)
						throw new AmbiguousOptionException(arg);
					descriptorToUse = descriptor;
				}
			}

			if (descriptorToUse == null)
				throw new InvalidOptionException(arg);

			if(optionValue != null)
			{
				// 'bunched' option already provided a value
				//
				if (descriptorToUse.getType() == OptionValueType.NONE)
					throw new InvalidOptionException(arg);
			}
			else if (descriptorToUse.getType() == OptionValueType.NONE)
			{
				// nothing to do
			}
			else if (descriptorToUse.getType() == OptionValueType.OPTIONAL)
			{
				// only use an existing next value if it doesn't start with -
				//
				if (i + 1 < max)
				{
					String s = args[i + 1];
					if (!s.startsWith(DASH))
					{
						i++;
						optionValue = s;
					}
				}
			}
			else if (descriptorToUse.getType() == OptionValueType.REQUIRED)
			{
				if (i + 1 == max)
					throw new OptionRequiresValueException(arg);
				optionValue = args[++i];
			}
			else
				throw new InternalError("Unknown OptionValueType");

			options.add(new Option(descriptorToUse, argName, optionValue, isLongName));
		}

		String[] unparsed = new String[max - i];
		System.arraycopy(args, i, unparsed, 0, unparsed.length);

		return new ParseResult((Option[])options.toArray(new Option[options.size()]), unparsed);
	}

	private final Option[] m_options;

	private final String[] m_unparsed;

	private ParseResult(Option[] options, String[] unparsed)
	{
		m_options = options;
		m_unparsed = unparsed;
	}

	public Option[] getOptions()
	{
		return m_options;
	}

	public String[] getUnparsed()
	{
		return m_unparsed;
	}
}


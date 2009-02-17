/*****************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.suffixdemangler;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 * 
 */
public class SuffixDemangler extends AbstractCommand
{
	public enum ShortSuffixType
	{
		SEQUENCE, TIMESTAMP
	}

	private static final DateFormat DefaultFormat;

	private static final String DefaultPattern = "yyMMdd-HHmm"; //$NON-NLS-1$

	private static final OptionDescriptor FORMAT_OPT = new OptionDescriptor('f', "format", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private static final OptionDescriptor IDENTIFIER_OPT = new OptionDescriptor('i', "identifier", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private static final OptionDescriptor QUALIFIER_OPT = new OptionDescriptor('q', "qualifier", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private static final OptionDescriptor STORAGE_FOLDER_OPT = new OptionDescriptor('s', "storage", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private static final OptionDescriptor TYPE_OPT = new OptionDescriptor('t', "type", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private static final OptionDescriptor VERSION_OPT = new OptionDescriptor('v', "version", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private static final OptionDescriptor WIDTH_OPT = new OptionDescriptor('w', "width", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private static final DateFormat ISO_8601Format;

	private static final String ISO_8601Pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"; //$NON-NLS-1$

	// The line must be in the following format:
	//
	// <version> <ugly string> <timestamp> <sequence number>
	//
	// whitespace acts as a delimiter and is hence not allowed in any of the elements.
	//
	private static final Pattern s_linePattern = Pattern
			.compile("^\\s*([^\\s]+)\\s+([^\\s]+)\\s+([^\\s]+)\\s+([0-9]+)\\s*$"); //$NON-NLS-1$

	private static final Calendar UTC_Calendar;

	static
	{
		UTC_Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")); //$NON-NLS-1$
		ISO_8601Format = new SimpleDateFormat(ISO_8601Pattern);
		ISO_8601Format.setCalendar(UTC_Calendar);
		DefaultFormat = new SimpleDateFormat(DefaultPattern);
		DefaultFormat.setCalendar(UTC_Calendar);
	}

	private static final String validateArgument(String argName, String arg)
	{
		int idx = 0;
		if(arg != null)
		{
			arg = arg.trim();
			idx = arg.length();
		}

		if(idx == 0)
			throw new IllegalArgumentException(NLS.bind(Messages._0_cannotBeEmpty, argName));

		while(--idx >= 0)
			if(Character.isWhitespace(arg.charAt(idx)))
				throw new IllegalArgumentException(NLS.bind(Messages._0_cannotContainWhitespace, argName));
		return arg;
	}

	private DateFormat m_format;

	private String m_identifier;

	private String m_qualifier;

	private File m_storageFolder;

	private ShortSuffixType m_type = ShortSuffixType.TIMESTAMP;

	private String m_version;

	private int m_width = 4;

	public String getTimestamp() throws IOException
	{
		if(m_storageFolder == null || m_identifier == null || m_version == null || m_qualifier == null)
			throw new IllegalStateException(Messages.missingArguments);

		String fileName = m_identifier + ".suffixmap"; //$NON-NLS-1$
		File storageFile = new File(m_storageFolder, fileName);
		RandomAccessFile raf = null;
		FileLock rafLock = null;
		try
		{
			raf = new RandomAccessFile(storageFile, "rw"); //$NON-NLS-1$
			FileChannel rafChannel = raf.getChannel();
			rafLock = rafChannel.lock();
			int topSequence = 0;
			int lineNumber = 0;
			String line;
			while((line = raf.readLine()) != null)
			{
				// Skip empty lines and lines that start with '#'
				//
				++lineNumber;
				if(line.length() == 0 || line.charAt(0) == '#')
					continue;

				Matcher m = s_linePattern.matcher(line);
				if(!m.matches())
					continue;

				String sequenceStr = m.group(4);
				int sequence = Integer.parseInt(sequenceStr);
				if(sequence > topSequence)
					topSequence = sequence;

				if(m.group(1).equals(m_version) && m.group(2).equals(m_qualifier))
				{
					Date timestamp;
					try
					{
						timestamp = ISO_8601Format.parse(m.group(3));
					}
					catch(ParseException e)
					{
						throw new IOException(NLS.bind(Messages.file_0_hasCorruptTimestampAtLine_1, storageFile,
								Integer.valueOf(lineNumber)));
					}
					return createReturnValue(timestamp, sequence);
				}
			}

			// Nothing found. Write a new entry
			//
			topSequence++;
			Date timestamp = new Date();
			raf.writeBytes(m_version + ' ' + m_qualifier + ' ' + ISO_8601Format.format(timestamp) + ' ' + topSequence
					+ System.getProperty("line.separator")); //$NON-NLS-1$

			return createReturnValue(timestamp, topSequence);
		}
		finally
		{
			if(rafLock != null)
				rafLock.release();
			raf.close();
		}
	}

	public void setID(String id)
	{
		m_identifier = validateArgument("ID", id); //$NON-NLS-1$
	}

	public void setQualifier(String qualifier)
	{
		m_qualifier = validateArgument("qualifier", qualifier); //$NON-NLS-1$
	}

	public void setStorageFolder(String folder)
	{
		File storageFolder = new File(folder);
		if(!storageFolder.exists())
			throw new IllegalArgumentException(NLS.bind(Messages.storageFolder_0_doesNotExist, storageFolder));
		m_storageFolder = storageFolder;
	}

	public void setTimestampFormat(String formatString)
	{
		m_format = (formatString == null)
				? null
				: new SimpleDateFormat(formatString);
	}

	public void setType(ShortSuffixType type)
	{
		m_type = type;
	}

	public void setType(String type)
	{
		setType(ShortSuffixType.valueOf(validateArgument("type", type.toUpperCase()))); //$NON-NLS-1$;
	}

	public void setVersion(String version)
	{
		m_version = validateArgument("version", version); //$NON-NLS-1$
	}

	public void setWidth(int width)
	{
		if(width < 0)
			throw new IllegalArgumentException(Messages.widthCannotBeNegative);
		if(width > 10)
			throw new IllegalArgumentException(Messages.widthCannotBeGreaterThen10);
		m_width = width;
	}

	public void setWidth(String widthString)
	{
		if(widthString == null)
			m_width = 4;
		else
			setWidth(Integer.parseInt(widthString));
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception
	{
		appendHere.add(FORMAT_OPT);
		appendHere.add(IDENTIFIER_OPT);
		appendHere.add(QUALIFIER_OPT);
		appendHere.add(STORAGE_FOLDER_OPT);
		appendHere.add(TYPE_OPT);
		appendHere.add(VERSION_OPT);
		appendHere.add(WIDTH_OPT);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(FORMAT_OPT))
			setTimestampFormat(option.getValue());
		else if(option.is(IDENTIFIER_OPT))
			setID(option.getValue());
		else if(option.is(QUALIFIER_OPT))
			setQualifier(option.getValue());
		else if(option.is(STORAGE_FOLDER_OPT))
			setStorageFolder(option.getValue());
		else if(option.is(TYPE_OPT))
			setType(option.getValue());
		else if(option.is(VERSION_OPT))
			setVersion(option.getValue());
		else if(option.is(WIDTH_OPT))
			setWidth(option.getValue());
	};

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		if(unparsed.length > 0)
			throw new SimpleErrorExitException(Messages.tooManyArguments);
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		System.out.println(getTimestamp());
		return 0;
	}

	private String createReturnValue(Date timestamp, int topSequence)
	{
		if(m_type == ShortSuffixType.SEQUENCE)
		{
			String sqString = Integer.toString(topSequence);
			if(m_width > 0)
			{
				int diff = m_width - sqString.length();
				if(diff > 0)
				{
					StringBuilder sb = new StringBuilder(m_width);
					while(--diff >= 0)
						sb.append('0');
					sb.append(sqString);
					sqString = sb.toString();
				}
			}
			return sqString;
		}

		if(m_format == null)
			m_format = DefaultFormat;
		return m_format.format(timestamp);
	}
}

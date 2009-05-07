package org.eclipse.buckminster.jarprocessor;

import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.jar.JarInputStream;
import java.util.jar.Pack200;
import java.util.jar.Pack200.Packer;
import java.util.jar.Pack200.Unpacker;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

abstract class RecursivePack200 implements IConstants
{
	/**
	 * This class will change the Pack200 magic. It's only supposed to be used on files that contains no class files
	 * (and hence default to magics higher than Java 1.5 if a higher VM is used).
	 */
	static class MagicChangerOutputStream extends FilterOutputStream
	{
		private int cnt = 0;

		public MagicChangerOutputStream(OutputStream wrapped)
		{
			super(wrapped);
		}

		@Override
		public void write(byte b[], int off, int len) throws IOException
		{
			if(cnt == 4 && len >= 4)
			{
				b[0] = 7;
				b[1] = (byte)(150 & 0xff);
			}
			out.write(b, off, len);
			cnt += len;
		}
	}

	/**
	 * An input stream that ignores the call to close().
	 */
	static class NonClosingInputStream extends FilterInputStream
	{
		public NonClosingInputStream(InputStream wrapped) throws IOException
		{
			super(wrapped);
		}

		@Override
		public void close() throws IOException
		{
		}
	}

	/**
	 * An input stream that ignores the call to close(). We don't want that.
	 */
	static class NonClosingJarInputStream extends JarInputStream
	{
		public NonClosingJarInputStream(InputStream wrapped) throws IOException
		{
			super(wrapped);
		}

		@Override
		public void close() throws IOException
		{
			inf.end();
		}
	}

	private final List<String> m_defaultArgs;

	private static final Pattern s_stripDebugPattern = Pattern.compile("^-(?:G|-strip-debug)$"); //$NON-NLS-1$

	private static final Pattern s_noKeepFileOrderPattern = Pattern.compile("^-(?:O|-no-keep-file-order)$"); //$NON-NLS-1$

	private static final Pattern s_keepFileOrderPattern = Pattern.compile("^--keep-file-order$"); //$NON-NLS-1$

	private static final Pattern s_effortPattern = Pattern.compile("^-(?:E|-effort=)([0-9])$"); //$NON-NLS-1$

	private static final Pattern s_segmentLimitPattern = Pattern.compile("^-(?:S|-segment-limit=)([0-9]+)$"); //$NON-NLS-1$

	private static final Pattern s_deflateHintPattern = Pattern.compile("^-(?:H|-deflate-hint=)(true|false|keep)$"); //$NON-NLS-1$

	private static final Pattern s_modificationTimePatter = Pattern.compile("^-(?:m|-modification-time=)(latest|keep)$"); //$NON-NLS-1$

	private static final Pattern s_passFilePatter = Pattern.compile("^-(?:P|-pass-file=)(.+)$"); //$NON-NLS-1$

	static ZipEntry createEntry(ZipEntry original)
	{
		ZipEntry copy = createEntry(original, original.getName());
		copy.setExtra(original.getExtra());
		return copy;
	}

	static ZipEntry createEntry(ZipEntry original, String name)
	{
		ZipEntry copy = new ZipEntry(name);
		copy.setComment(original.getComment());
		copy.setTime(original.getTime());
		return copy;
	}

	RecursivePack200(List<String> defaultArgs)
	{
		if(defaultArgs == null || defaultArgs.isEmpty())
			m_defaultArgs = Collections.emptyList();
		else
			m_defaultArgs = new ArrayList<String>(defaultArgs);
	}

	Packer getPacker(JarInfo jarInfo)
	{
		int passFileSuffix = 1;
		List<String> args = new ArrayList<String>();
		jarInfo.appendArgs(args);
		args.addAll(m_defaultArgs);

		// Default arguments are last in the list. This HashSet
		// guarantees that only the first occurrence is used for
		// each option
		//
		HashSet<String> propsAdded = new HashSet<String>();

		Packer packer = Pack200.newPacker();
		Map<String, String> properties = packer.properties();
		for(String arg : args)
		{
			Matcher m = s_effortPattern.matcher(arg);
			if(m.matches())
			{
				if(propsAdded.add(Packer.EFFORT))
					properties.put(Packer.EFFORT, m.group(1));
				continue;
			}

			m = s_segmentLimitPattern.matcher(arg);
			if(m.matches())
			{
				if(propsAdded.add(Packer.SEGMENT_LIMIT))
					properties.put(Packer.SEGMENT_LIMIT, m.group(1));
				continue;
			}

			m = s_noKeepFileOrderPattern.matcher(arg);
			if(m.matches())
			{
				if(propsAdded.add(Packer.KEEP_FILE_ORDER))
					properties.put(Packer.KEEP_FILE_ORDER, Packer.FALSE);
				continue;
			}

			m = s_keepFileOrderPattern.matcher(arg);
			if(m.matches())
			{
				if(propsAdded.add(Packer.KEEP_FILE_ORDER))
					properties.put(Packer.KEEP_FILE_ORDER, Packer.TRUE);
				continue;
			}

			m = s_stripDebugPattern.matcher(arg);
			if(m.matches())
			{
				if(propsAdded.add("strip.debug")) //$NON-NLS-1$
				{
					properties.put(Packer.CODE_ATTRIBUTE_PFX + "LineNumberTable", Packer.STRIP); //$NON-NLS-1$
					properties.put(Packer.CODE_ATTRIBUTE_PFX + "LocalVariableTable", Packer.STRIP); //$NON-NLS-1$
					properties.put(Packer.CLASS_ATTRIBUTE_PFX + "SourceFile", Packer.STRIP); //$NON-NLS-1$
				}
				continue;
			}

			m = s_deflateHintPattern.matcher(arg);
			if(m.matches())
			{
				if(propsAdded.add(Packer.DEFLATE_HINT))
					properties.put(Packer.DEFLATE_HINT, m.group(1));
				continue;
			}

			m = s_modificationTimePatter.matcher(arg);
			if(m.matches())
			{
				if(propsAdded.add(Packer.MODIFICATION_TIME))
					properties.put(Packer.MODIFICATION_TIME, m.group(1));
				continue;
			}

			m = s_passFilePatter.matcher(arg);
			if(m.matches())
			{
				IPath path = Path.fromOSString(m.group(1));
				properties.put(Packer.PASS_FILE_PFX + '.' + passFileSuffix++, path.toPortableString());
				continue;
			}
		}
		return packer;
	}

	Unpacker getUnpacker()
	{
		return Pack200.newUnpacker();
	}
}

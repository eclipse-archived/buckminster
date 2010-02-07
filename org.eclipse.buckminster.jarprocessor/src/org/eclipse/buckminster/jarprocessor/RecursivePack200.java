package org.eclipse.buckminster.jarprocessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.jar.Pack200;
import java.util.jar.Pack200.Packer;
import java.util.jar.Pack200.Unpacker;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;

import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

abstract class RecursivePack200 implements IConstants {
	/**
	 * An input stream that ignores the call to close().
	 */
	static class NonClosingInputStream extends FilterInputStream {
		public NonClosingInputStream(InputStream wrapped) throws IOException {
			super(wrapped);
		}

		@Override
		public void close() throws IOException {
		}
	}

	private final List<String> defaultArgs;

	static final Pattern EFFORT_PATTERN = Pattern.compile("^-(?:E|-effort=)([0-9])$"); //$NON-NLS-1$

	private static final Pattern stripDebugPattern = Pattern.compile("^-(?:G|-strip-debug)$"); //$NON-NLS-1$

	private static final Pattern noKeepFileOrderPattern = Pattern.compile("^-(?:O|-no-keep-file-order)$"); //$NON-NLS-1$

	private static final Pattern keepFileOrderPattern = Pattern.compile("^--keep-file-order$"); //$NON-NLS-1$

	private static final Pattern segmentLimitPattern = Pattern.compile("^-(?:S|-segment-limit=)([0-9]+)$"); //$NON-NLS-1$

	private static final Pattern deflateHintPattern = Pattern.compile("^-(?:H|-deflate-hint=)(true|false|keep)$"); //$NON-NLS-1$

	private static final Pattern modificationTimePatter = Pattern.compile("^-(?:m|-modification-time=)(latest|keep)$"); //$NON-NLS-1$

	private static final Pattern passFilePatter = Pattern.compile("^-(?:P|-pass-file=)(.+)$"); //$NON-NLS-1$

	static ZipEntry createEntry(ZipEntry original) {
		ZipEntry copy = createEntry(original, original.getName());
		copy.setExtra(original.getExtra());
		return copy;
	}

	static ZipEntry createEntry(ZipEntry original, String name) {
		ZipEntry copy = new ZipEntry(name);
		copy.setComment(original.getComment());
		copy.setTime(original.getTime());
		return copy;
	}

	RecursivePack200(List<String> defaultArgs) {
		if (defaultArgs == null || defaultArgs.isEmpty())
			this.defaultArgs = Collections.emptyList();
		else
			this.defaultArgs = new ArrayList<String>(defaultArgs);
	}

	Packer getPacker(JarInfo jarInfo) {
		int passFileSuffix = 1;
		List<String> args = new ArrayList<String>();
		jarInfo.appendArgs(args);
		args.addAll(defaultArgs);

		// Default arguments are last in the list. This HashSet
		// guarantees that only the first occurrence is used for
		// each option
		//
		HashSet<String> propsAdded = new HashSet<String>();

		Packer packer = Pack200.newPacker();
		Map<String, String> properties = packer.properties();

		if (!jarInfo.hasClasses()) {
			// This is a parent of a nested pack. No need to pack again.
			propsAdded.add(Packer.EFFORT);
			properties.put(Packer.EFFORT, "0"); //$NON-NLS-1$
		}

		for (String arg : args) {
			Matcher m = EFFORT_PATTERN.matcher(arg);
			if (m.matches()) {
				if (propsAdded.add(Packer.EFFORT))
					properties.put(Packer.EFFORT, m.group(1));
				continue;
			}

			m = segmentLimitPattern.matcher(arg);
			if (m.matches()) {
				if (propsAdded.add(Packer.SEGMENT_LIMIT))
					properties.put(Packer.SEGMENT_LIMIT, m.group(1));
				continue;
			}

			m = noKeepFileOrderPattern.matcher(arg);
			if (m.matches()) {
				if (propsAdded.add(Packer.KEEP_FILE_ORDER))
					properties.put(Packer.KEEP_FILE_ORDER, Packer.FALSE);
				continue;
			}

			m = keepFileOrderPattern.matcher(arg);
			if (m.matches()) {
				if (propsAdded.add(Packer.KEEP_FILE_ORDER))
					properties.put(Packer.KEEP_FILE_ORDER, Packer.TRUE);
				continue;
			}

			m = stripDebugPattern.matcher(arg);
			if (m.matches()) {
				if (propsAdded.add("strip.debug")) //$NON-NLS-1$
				{
					properties.put(Packer.CODE_ATTRIBUTE_PFX + "LineNumberTable", Packer.STRIP); //$NON-NLS-1$
					properties.put(Packer.CODE_ATTRIBUTE_PFX + "LocalVariableTable", Packer.STRIP); //$NON-NLS-1$
					properties.put(Packer.CLASS_ATTRIBUTE_PFX + "SourceFile", Packer.STRIP); //$NON-NLS-1$
				}
				continue;
			}

			m = deflateHintPattern.matcher(arg);
			if (m.matches()) {
				if (propsAdded.add(Packer.DEFLATE_HINT))
					properties.put(Packer.DEFLATE_HINT, m.group(1));
				continue;
			}

			m = modificationTimePatter.matcher(arg);
			if (m.matches()) {
				if (propsAdded.add(Packer.MODIFICATION_TIME))
					properties.put(Packer.MODIFICATION_TIME, m.group(1));
				continue;
			}

			m = passFilePatter.matcher(arg);
			if (m.matches()) {
				IPath path = Path.fromOSString(m.group(1));
				properties.put(Packer.PASS_FILE_PFX + '.' + passFileSuffix++, path.toPortableString());
				continue;
			}
		}
		return packer;
	}

	Unpacker getUnpacker() {
		return Pack200.newUnpacker();
	}

	void pack(JarInfo jarInfo, InputStream in, OutputStream out) throws IOException {
		File temp = null;
		try {
			// We need a temp file here since the Packer will alter the
			// META-INF/MANIFEST.MF if we pass it an JarInputStream. Not
			// good if the jar is signed...
			temp = File.createTempFile("conditionFile", ".jar"); //$NON-NLS-1$ //$NON-NLS-2$
			OutputStream tempOut = null;
			try {
				tempOut = new FileOutputStream(temp);
				IOUtils.copy(in, tempOut, new NullProgressMonitor());
			} finally {
				IOUtils.close(tempOut);
			}
			Packer packer = getPacker(jarInfo);
			packer.pack(new JarFile(temp), out);
			out.flush();
		} finally {
			if (temp != null)
				temp.delete();
		}
	}
}

package org.eclipse.buckminster.jarprocessor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.jar.JarOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;

public class RecursiveUnpacker extends RecursivePack200 {
	static boolean isJarMagic(byte[] magic) {
		return (magic[0] == (byte) 'P' && magic[1] == (byte) 'K' && magic[2] >= 1 && magic[2] < 8 && magic[3] == magic[2] + 1);
	}

	public RecursiveUnpacker(List<String> defaultArgs) {
		super(defaultArgs);
	}

	public void unpack(File packedFile, File destFolder, boolean retainPacked) throws CoreException {
		boolean sharedFolder;
		if (destFolder == null) {
			sharedFolder = true;
			destFolder = packedFile.getParentFile();
		} else
			sharedFolder = destFolder.equals(packedFile.getParentFile());

		InputStream input = null;
		OutputStream output = null;
		String fileName = packedFile.getAbsolutePath();
		String name = packedFile.getName();
		try {
			input = new GZIPInputStream(new FileInputStream(packedFile));
			output = new FileOutputStream(new File(destFolder, name.substring(0, name.length() - PACK_GZ_SUFFIX.length())));
			nestedUnpack(input, output);
			if (sharedFolder) {
				if (!retainPacked)
					packedFile.delete();
			} else {
				if (retainPacked)
					FileUtils.copyFile(packedFile, destFolder, name, null);
			}
		} catch (IOException e) {
			throw BuckminsterException.fromMessage(e, "Unable to condition %s", fileName); //$NON-NLS-1$
		} finally {
			IOUtils.close(input);
			IOUtils.close(output);
		}
	}

	private void nestedUnpack(InputStream input, OutputStream unpacked) throws IOException {
		// The Unpack200 will corrupt the manifest for packed files if -E0 is
		// used. This is because it copies the jar entries one by one and
		// rewrites the manifest.
		//
		// Here we check if the packed file starts with a jar magic. If it does,
		// then this is a plain copy and we treat is as such.
		//
		final BufferedInputStream bufferedInput = new BufferedInputStream(input);
		bufferedInput.mark(4);
		byte[] magic = new byte[4];
		if (bufferedInput.read(magic, 0, 4) != 4)
			throw new IOException("Unable to read packed file magic"); //$NON-NLS-1$
		bufferedInput.reset();

		ZipInputStream jarIn;
		if (isJarMagic(magic))
			jarIn = new ZipInputStream(bufferedInput);
		else {
			final ProducerThread jarPumper = new ProducerThread("Unpack200 jarPumper") //$NON-NLS-1$
			{
				@Override
				protected void internalRun(OutputStream writer) throws Exception {
					JarOutputStream jarOut = new JarOutputStream(writer);
					getUnpacker().unpack(new NonClosingInputStream(bufferedInput), jarOut);
					jarOut.finish();
				}
			};
			jarPumper.start();
			jarIn = new ZipInputStream(jarPumper.getReaderStream());
		}

		ZipOutputStream jarOut = new ZipOutputStream(unpacked);
		ZipEntry entry;
		while ((entry = jarIn.getNextEntry()) != null) {
			String name = entry.getName();
			if (entry.isDirectory()) {
				jarOut.putNextEntry(createEntry(entry));
				continue;
			}

			String jarName = null;
			InputStream packedInput = null;
			if (name.endsWith(PACK_GZ_SUFFIX)) {
				jarName = name.substring(0, name.length() - PACK_GZ_SUFFIX.length());
				packedInput = new GZIPInputStream(jarIn);
			} else if (name.endsWith(PACK_SUFFIX)) {
				jarName = name.substring(0, name.length() - PACK_SUFFIX.length());
				packedInput = jarIn;
			}
			if (packedInput != null) {
				Buckminster.getLogger().debug("Unpacker: Recursive unpack of %s", name); //$NON-NLS-1$
				jarOut.putNextEntry(createEntry(entry, jarName));
				nestedUnpack(packedInput, jarOut);
				continue;
			}

			jarOut.putNextEntry(createEntry(entry));
			IOUtils.copy(jarIn, jarOut, null);
		}
		jarOut.finish();
	}
}

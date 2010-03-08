package org.eclipse.buckminster.jarprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200.Unpacker;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;

public class RecursiveUnpacker extends RecursivePack200 {
	static class NestedUnpackingJarOutputStream extends JarOutputStream {
		private OutputStream currentStream;
		private IOException pipeException;
		private Thread unpackPumper;

		NestedUnpackingJarOutputStream(OutputStream innerStream) throws IOException {
			super(new JarOutputStream(innerStream));
			currentStream = out;
		}

		@Override
		public synchronized void close() throws IOException {
			currentStream.close();
		}

		@Override
		public synchronized void closeEntry() throws IOException {
			popStreams();
			getInnerStream().closeEntry();
		}

		@Override
		public synchronized void finish() throws IOException {
			popStreams();
			getInnerStream().finish();
		}

		@Override
		public synchronized void flush() throws IOException {
			currentStream.flush();
		}

		@Override
		public synchronized void putNextEntry(ZipEntry ze) throws IOException {
			Logger log = Buckminster.getLogger();
			String name = ze.getName();
			popStreams();
			if (name.endsWith(PACK_GZ_SUFFIX)) {
				pushPack200Unpacker(true);
				log.debug("Unpacker: Recursive unpack of %s", name); //$NON-NLS-1$
				ze = createEntry(ze, name.substring(0, name.length() - PACK_GZ_SUFFIX.length()));
			} else if (name.endsWith(PACK_SUFFIX)) {
				pushPack200Unpacker(false);
				log.debug("Unpacker: Recursive unpack of %s", name); //$NON-NLS-1$
				ze = createEntry(ze, name.substring(0, name.length() - PACK_SUFFIX.length()));
			}
			getInnerStream().putNextEntry(ze);
		}

		@Override
		public synchronized void setComment(String comment) {
			getInnerStream().setComment(comment);
		}

		@Override
		public synchronized void setLevel(int level) {
			getInnerStream().setLevel(level);
		}

		@Override
		public synchronized void setMethod(int method) {
			getInnerStream().setMethod(method);
		}

		@Override
		public synchronized void write(byte b[]) throws IOException {
			currentStream.write(b);
		}

		@Override
		public synchronized void write(byte[] b, int off, int len) throws IOException {
			currentStream.write(b, off, len);
		}

		@Override
		public synchronized void write(int b) throws IOException {
			currentStream.write(b);
		}

		private JarOutputStream getInnerStream() {
			return (JarOutputStream) out;
		}

		private void popStreams() throws IOException {
			if (currentStream != out) {
				currentStream.close();
				currentStream = out;
				if (unpackPumper != null)
					try {
						wait();
					} catch (InterruptedException e) {
					}
				if (pipeException != null)
					throw pipeException;
			}
		}

		private void pushPack200Unpacker(final boolean gzipped) throws IOException {
			final PipedInputStream pipeInput = new PipedInputStream();
			currentStream = new PipedOutputStream(pipeInput);
			unpackPumper = new Thread("Pack200 unpackPumper") //$NON-NLS-1$
			{
				@Override
				public void run() {
					Unpacker unpacker = getUnpacker();
					try {
						JarOutputStream jarOut = new NestedUnpackingJarOutputStream(getInnerStream());
						InputStream input = pipeInput;
						if (gzipped)
							input = new GZIPInputStream(pipeInput);
						unpacker.unpack(input, jarOut);
						jarOut.finish();
					} catch (IOException e) {
						pipeException = e;
					}
					synchronized (NestedUnpackingJarOutputStream.this) {
						unpackPumper = null;
						NestedUnpackingJarOutputStream.this.notify();
					}
				}
			};
			pipeException = null;
			unpackPumper.start();
		}
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
			NestedUnpackingJarOutputStream nuJarOutput = new NestedUnpackingJarOutputStream(output);
			Unpacker unpacker = getUnpacker();
			unpacker.unpack(input, nuJarOutput);
			input = null; // Closed by unpack
			nuJarOutput.close();
			output = null;
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
}

package org.eclipse.buckminster.jarprocessor.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.buckminster.jarprocessor.RecursiveConditioner;
import org.eclipse.buckminster.jarprocessor.RecursivePacker;
import org.eclipse.buckminster.jarprocessor.RecursiveUnpacker;
import org.eclipse.buckminster.runtime.IOUtils;
import org.junit.Test;

public class TestRecursivePack extends AbstractTest {
	static final class StreamGobblerRedirector extends Thread {
		private final InputStream is;
		private final PrintStream os;

		StreamGobblerRedirector(InputStream is, PrintStream os) {
			this.is = is;
			this.os = os;
		}

		@Override
		public void run() {
			try {
				final InputStreamReader isr = new InputStreamReader(is);
				final BufferedReader bufferedReader = new BufferedReader(isr);
				String readLine;
				while ((readLine = bufferedReader.readLine()) != null)
					os.println(readLine);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private static Map<String, Long> verify(File file) throws Exception {
		HashMap<String, Long> crcMap = new HashMap<String, Long>();
		verify(crcMap, file);
		return crcMap;
	}

	private static void verify(Map<String, Long> crcMap, File file)
			throws Exception {

		JarFile verifier = new JarFile(file, true);
		for (Enumeration<JarEntry> entries = verifier.entries(); entries
				.hasMoreElements();) {
			JarEntry entry = entries.nextElement();
			entry.getCertificates();
			crcMap.put(entry.getName(), entry.getSize());
			if (entry.getName().endsWith(".jar")) {
				InputStream input = verifier.getInputStream(entry);
				File temp = File.createTempFile("nested", ".jar");
				try {
					OutputStream out = new FileOutputStream(temp);
					IOUtils.copy(input, out, null);
					out.close();
					verify(crcMap, temp);
				} finally {
					temp.delete();
					IOUtils.close(input);
				}
			}
		}
		verifier.close();
		File javaHome = new File(System.getProperty("java.home"));
		if("jre".equals(javaHome.getName()))
			javaHome = javaHome.getParentFile();
		File javaBin = new File(javaHome, "bin");
		File jarsignerExe = new File(javaBin, "jarsigner");
		boolean jarsignerOK = jarsignerExe.canExecute();
		if(!jarsignerOK) {
			jarsignerExe = new File(javaBin, "jarsigner.exe");
			jarsignerOK = jarsignerExe.canExecute();
		}
		assertTrue("Unable to find jarsigner executable", jarsignerOK);

		Process jarsigner = Runtime.getRuntime().exec(new String[] { jarsignerExe.getAbsolutePath(), "-verify", file.getAbsolutePath() });
		final StreamGobblerRedirector errorGobbler = new StreamGobblerRedirector(jarsigner.getErrorStream(), System.err);
		final StreamGobblerRedirector outputGobbler = new StreamGobblerRedirector(jarsigner.getInputStream(), System.out);
		errorGobbler.start();
		outputGobbler.start();
		final int returnCode = jarsigner.waitFor();
		System.err.flush();
		System.out.flush();
		assertEquals("jarsigner failed exit code != 0 when verifying " + file.getAbsolutePath(), 0, returnCode);
	}

	private static void assertEqualNames(Collection<String> ns1,
			Collection<String> ns2) {
		for (String n1 : ns1)
			assertTrue("Expected \'" + n1 + "\' is missing", ns2.contains(n1));
		for (String n2 : ns2)
			assertTrue("Found \'" + n2 + "\' unexpectedly", ns1.contains(n2));
	}

	@Test
	public void testPackFile() throws Exception {

		File originalJar = getTestJar("jdtstuff.jar");
		Map<String, Long> originalCrcMap = verify(originalJar);
		File outputFolder = getTestFolder("testPackFile");
		File tmpFolder = new File(outputFolder, "temp");
		tmpFolder.mkdirs();
		File conditionedJarFile = new File(outputFolder, originalJar.getName());
		RecursiveConditioner conditioner = new RecursiveConditioner(tmpFolder, null);
		conditioner.condition(originalJar, conditionedJarFile);

		Map<String, Long> conditionedCrcMap = verify(conditionedJarFile);
		assertEqualNames(originalCrcMap.keySet(), conditionedCrcMap.keySet());

		RecursivePacker packer = new RecursivePacker(tmpFolder, null, true);
		packer.pack(conditionedJarFile, outputFolder, false);

		RecursiveUnpacker unpacker = new RecursiveUnpacker(tmpFolder, null);
		unpacker.unpack(new File(outputFolder, "jdtstuff.jar.pack.gz"),
				outputFolder, true);
		File unpacked = new File(outputFolder, "jdtstuff.jar");
		Map<String, Long> unpackedCrcMap = verify(unpacked);
		assertEqualNames(conditionedCrcMap.keySet(), unpackedCrcMap.keySet());
		for (Entry<String, Long> entry : conditionedCrcMap.entrySet()) {
			Long ucrc = unpackedCrcMap.get(entry.getKey());
			assertEquals("2: CRC for " + entry.getKey() + " differ", entry
					.getValue(), ucrc);
		}
	}

	@Test
	public void testPdeCoreFile() throws Exception {

		File originalJar = getTestJar("pdestuff.jar");
		Map<String, Long> originalCrcMap = verify(originalJar);
		File outputFolder = getTestFolder("testPackFile");
		File tmpFolder = new File(outputFolder, "temp");
		tmpFolder.mkdirs();
		File conditionedJarFile = new File(outputFolder, originalJar.getName());
		RecursiveConditioner conditioner = new RecursiveConditioner(tmpFolder, null);
		conditioner.condition(originalJar, conditionedJarFile);

		Map<String, Long> conditionedCrcMap = verify(conditionedJarFile);
		assertEqualNames(originalCrcMap.keySet(), conditionedCrcMap.keySet());

		RecursivePacker packer = new RecursivePacker(tmpFolder, null, true);
		packer.pack(conditionedJarFile, outputFolder, false);

		RecursiveUnpacker unpacker = new RecursiveUnpacker(tmpFolder, null);
		unpacker.unpack(new File(outputFolder, "pdestuff.jar.pack.gz"),
				outputFolder, true);
		File unpacked = new File(outputFolder, "pdestuff.jar");
		Map<String, Long> unpackedCrcMap = verify(unpacked);
		assertEqualNames(conditionedCrcMap.keySet(), unpackedCrcMap.keySet());
		for (Entry<String, Long> entry : conditionedCrcMap.entrySet()) {
			Long ucrc = unpackedCrcMap.get(entry.getKey());
			assertEquals("2: CRC for " + entry.getKey() + " differ", entry
					.getValue(), ucrc);
		}
	}

}

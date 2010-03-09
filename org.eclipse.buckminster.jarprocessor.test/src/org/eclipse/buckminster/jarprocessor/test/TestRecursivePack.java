package org.eclipse.buckminster.jarprocessor.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

	private static Map<String, Long> verify(File file) throws IOException {
		HashMap<String, Long> crcMap = new HashMap<String, Long>();
		verify(crcMap, file);
		return crcMap;
	}

	private static void verify(Map<String, Long> crcMap, File file)
			throws IOException {

		JarFile verifier = new JarFile(file, true);
		for (Enumeration<JarEntry> entries = verifier.entries(); entries
				.hasMoreElements();) {
			JarEntry entry = entries.nextElement();
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
		File conditionedJarFile = new File(outputFolder, originalJar.getName());
		RecursiveConditioner conditioner = new RecursiveConditioner(null);
		conditioner.condition(originalJar, conditionedJarFile);

		Map<String, Long> conditionedCrcMap = verify(conditionedJarFile);
		assertEqualNames(originalCrcMap.keySet(), conditionedCrcMap.keySet());

		RecursivePacker packer = new RecursivePacker(null, true);
		packer.pack(conditionedJarFile, outputFolder, false);

		RecursiveUnpacker unpacker = new RecursiveUnpacker(null);
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
}

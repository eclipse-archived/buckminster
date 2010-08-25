package org.eclipse.buckminster.git.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import junit.framework.TestCase;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Bundle;

abstract class GitTestCase extends TestCase {
	public static File getTestData(String fileName) {
		Bundle self = Activator.context.getBundle();
		URL base = self.getEntry("testData");
		if (base == null)
			fail("Unable to find \"testData\" folder");
		File result = toFile(base);
		if (fileName != null)
			result = new File(result, fileName);
		return result;
	}

	public static File getTestOutputFolder(String name) throws CoreException, IOException {
		return Activator.context.getDataFile(name);
	}

	private static File toFile(URL url) {
		File file = null;
		try {
			file = new File(
					new Path(FileLocator.toFileURL(url).getPath()).toOSString());
		} catch (IOException e) {
			fail("Exception while converting URL \"" + url
					+ "\" to a file: " + e.getMessage());
		}
		return file;
	}
}

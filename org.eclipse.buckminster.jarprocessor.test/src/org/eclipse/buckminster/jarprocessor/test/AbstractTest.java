package org.eclipse.buckminster.jarprocessor.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import junit.framework.TestCase;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;
import org.osgi.framework.Bundle;

public abstract class AbstractTest extends TestCase {
	public static File getTestJar(String fileName) throws Exception {
		Bundle self = Platform.getBundle("org.eclipse.buckminster.jarprocessor.test");
		URL base = self.getEntry("testJars");
		if (base == null)
			throw new RuntimeException("Unable to find \"testJars\" folder");
		return new File(toFile(base), fileName);
	}

	public static File getTestFolder(String name) throws CoreException, IOException {
		Buckminster bucky = Buckminster.getDefault();
		Location instanceLocation = bucky.getService(Location.class, Location.INSTANCE_FILTER);
		URL url = instanceLocation != null ? instanceLocation.getURL() : null;
		File testFolder;
		if (instanceLocation == null || !instanceLocation.isSet() || url == null) {
			String tempDir = System.getProperty("java.io.tmpdir");
			testFolder = new File(tempDir, name);
		} else {
			testFolder = new File(toFile(url), name);
		}
		if (testFolder.exists())
			FileUtils.deleteRecursive(testFolder, null);
		testFolder.mkdirs();
		return testFolder;
	}

	private static File toFile(URL url) throws IOException {
		return new File(new Path(FileLocator.toFileURL(url).getPath()).toOSString());
	}
}

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
import org.eclipse.osgi.service.datalocation.Location;
import org.osgi.framework.Bundle;
import org.osgi.service.packageadmin.PackageAdmin;

public abstract class AbstractTest extends TestCase {
	public static File getTestJar(String fileName) throws Exception {
		Buckminster bucky = Buckminster.getDefault();
		PackageAdmin packageAdmin = bucky.getService(PackageAdmin.class);
		try {
		Bundle self = getBundle(packageAdmin, "org.eclipse.buckminster.jarprocessor.test");
		URL base = self.getEntry("testJars");
		if (base == null)
			throw new RuntimeException("Unable to find \"testJars\" folder");
			return new File(toFile(base), fileName);
		} finally {
			bucky.ungetService(packageAdmin);
		}
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

	private static synchronized Bundle getBundle(PackageAdmin packageAdmin, String symbolicName)
	{
		Bundle[] bundles = packageAdmin.getBundles(symbolicName, null);
		if(bundles == null)
			return null;
		// Return the first bundle that is not installed or uninstalled
		for(int i = 0; i < bundles.length; i++)
		{
			if((bundles[i].getState() & (Bundle.INSTALLED | Bundle.UNINSTALLED)) == 0)
			{
				return bundles[i];
			}
		}
		return null;
	}
}

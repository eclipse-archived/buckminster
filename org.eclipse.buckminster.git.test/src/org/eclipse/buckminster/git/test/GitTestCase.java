package org.eclipse.buckminster.git.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import junit.framework.TestCase;

import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

abstract class GitTestCase extends TestCase
{
	/*
	 * Look up and return a file handle to the given entry in the bundle.
	 */
	protected File getTestData(String message, String entry) {
		if (entry == null)
			fail(message + " entry is null.");
		Bundle gitBundle = Platform.getBundle("org.eclipse.buckminster.git.test");
		if(gitBundle == null)
			fail(message + "Unable to obtain bundle org.eclipse.buckminster.git.test");
		IPath entryPath = Path.fromPortableString(entry);
		if(entryPath.segmentCount() < 2)
			fail(message + "Path '" + entry + "' has less then 2 segments");
		String parent = entryPath.removeLastSegments(1).toPortableString();
		String entryName = entryPath.lastSegment();
		Enumeration<?> entries = gitBundle.findEntries(parent, entryName, false);
		if(!entries.hasMoreElements())
			fail(message + " entry '" + entry + "'not found in bundle org.eclipse.buckminster.git.test");
		URL base = (URL)entries.nextElement();
		try {
			String osPath = new Path(FileLocator.toFileURL(base).getPath()).toOSString();
			File result = new File(osPath);
			if (!result.getCanonicalPath().equals(result.getPath()))
				fail(message + " result path: " + result.getPath() + " does not match canonical path: " + result.getCanonicalFile().getPath());
			return result;
		} catch (IOException e) {
			fail(message + ": " + e.getMessage());
		}
		// avoid compile error... should never reach this code
		return null;
	}

	public ComponentQuery getComponentQuery() throws Exception {
		File cquery = getTestData("Component Query", "testData/test.cquery");
		return ComponentQuery.fromURL(cquery.toURI().toURL(), null, true);
	}
}

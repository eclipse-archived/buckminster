package org.eclipse.buckminster.git.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

import junit.framework.TestCase;

public class GitTestCase extends TestCase
{
	/*
	 * Look up and return a file handle to the given entry in the bundle.
	 */
	protected File getTestData(String message, String entry) {
		if (entry == null)
			fail(message + " entry is null.");
		URL base = Activator.getContext().getBundle().getEntry(entry);
		if (base == null)
			fail(message + " entry not found in bundle: " + entry);
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
		return ComponentQuery.fromURL(cquery.toURL(), null, true);
	}
}

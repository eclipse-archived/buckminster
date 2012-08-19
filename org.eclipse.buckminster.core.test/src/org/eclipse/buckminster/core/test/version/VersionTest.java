package org.eclipse.buckminster.core.test.version;

import junit.framework.TestCase;

import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;

public class VersionTest extends TestCase {
	public void testEmptyVersionRange() {
		Version empty = Version.create("0.0.0");
		VersionRange range = VersionHelper.exactRange(empty);
		assertNull("Range from empty version is not an empty range", range);
	}
}

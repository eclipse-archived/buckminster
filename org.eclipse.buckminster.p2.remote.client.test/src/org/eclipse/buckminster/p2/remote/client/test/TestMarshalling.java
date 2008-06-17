package org.eclipse.buckminster.p2.remote.client.test;

import java.net.URL;
import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.buckminster.p2.remote.marshall.IUMarshaller;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.internal.p2.console.ProvisioningHelper;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.jabsorb.JSONRPCBridge;
import org.jabsorb.JSONSerializer;
import org.osgi.framework.Bundle;

public class TestMarshalling extends TestCase
{
	public static Test suite() throws Exception
	{
		Bundle[] bundles = Platform.getBundles("org.eclipse.equinox.p2.exemplarysetup", null);
		bundles[0].start();

		bundles = Platform.getBundles("org.eclipse.equinox.p2.metadata", null);
		bundles[0].start();

		TestSuite suite = new TestSuite("Test for org.eclipse.buckminster.core");
		suite.addTest(new TestMarshalling("testMarshalling"));
		return suite;
	}

	public TestMarshalling(String testName)
	{
		super(testName);
	}

	public void testMarshalling() throws Exception
	{
		InstallableUnitQuery query = new InstallableUnitQuery(null, VersionRange.emptyRange);
		Collector repoRoots = ProvisioningHelper.getInstallableUnits(new URL(
			"http://download.eclipse.org/tools/buckminster/updates-3.4/"), query, new NullProgressMonitor());

		JSONSerializer serializer = JSONRPCBridge.getSerializer();
		serializer.registerSerializer(new IUMarshaller());
		Iterator unitIterator = repoRoots.iterator();
		while(unitIterator.hasNext())
		{
			IInstallableUnit iu = (IInstallableUnit)unitIterator.next();
			String jsonString = serializer.toJSON(iu);
			iu = (IInstallableUnit)serializer.fromJSON(jsonString);
			System.out.println(iu.getId());
		}
	}
}

package org.eclipse.buckminster.galileo.builder.test;

import java.io.File;
import java.net.URI;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.internal.provisional.p2.director.IPlanner;
import org.eclipse.equinox.internal.provisional.p2.director.ProfileChangeRequest;
import org.eclipse.equinox.internal.provisional.p2.director.ProvisioningPlan;
import org.eclipse.equinox.internal.provisional.p2.engine.DefaultPhaseSet;
import org.eclipse.equinox.internal.provisional.p2.engine.IEngine;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfile;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.engine.ProvisioningContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.service.packageadmin.PackageAdmin;

@SuppressWarnings("restriction")
public class MiscTests extends TestCase
{
	static private final String EXEMPLARY_SETUP_BUNDLE = "org.eclipse.equinox.p2.exemplarysetup"; //$NON-NLS-1$

	static private final String CORE_BUNDLE = "org.eclipse.equinox.p2.core"; //$NON-NLS-1$

	static ProvisioningContext createContext(URI site)
	{
		URI[] repoLocations = new URI[] { site };
		ProvisioningContext context = new ProvisioningContext(repoLocations);
		context.setArtifactRepositories(repoLocations);
		return context;
	}

	private static synchronized Bundle getBundle(PackageAdmin packageAdmin, String symbolicName)
	{
		Bundle[] bundles = packageAdmin.getBundles(symbolicName, null);
		if(bundles != null)
		{
			for(int i = 0; i < bundles.length; i++)
			{
				Bundle bundle = bundles[i];
				if((bundle.getState() & (Bundle.INSTALLED | Bundle.UNINSTALLED)) == 0)
					return bundle;
			}
		}
		return null;
	}

	private static boolean startEarly(PackageAdmin packageAdmin, String bundleName) throws BundleException
	{
		Bundle bundle = getBundle(packageAdmin, bundleName);
		if(bundle == null || bundle.getState() == Bundle.ACTIVE)
			return false;
		bundle.start(Bundle.START_TRANSIENT);
		return true;
	}

	private static boolean stopBundle(PackageAdmin packageAdmin, String bundleName) throws BundleException
	{
		Bundle bundle = getBundle(packageAdmin, bundleName);
		if(bundle == null || bundle.getState() != Bundle.ACTIVE)
			return false;
		bundle.stop(Bundle.START_TRANSIENT);
		return true;
	}

	public void testProperties() throws Exception
	{
		File tpLocation = new File("/home/thhal/tmp/eclipse");
		System.setProperty("eclipse.p2.data.area", new File(tpLocation, "p2").toString());

		Buckminster bucky = Buckminster.getDefault();
		PackageAdmin packageAdmin = bucky.getService(PackageAdmin.class);
		try
		{
			stopBundle(packageAdmin, EXEMPLARY_SETUP_BUNDLE);
			stopBundle(packageAdmin, CORE_BUNDLE);

			if(!startEarly(packageAdmin, CORE_BUNDLE))
			{
				System.err.format("Missing bundle %s%n", CORE_BUNDLE);
				return;
			}

			if(!startEarly(packageAdmin, EXEMPLARY_SETUP_BUNDLE))
			{
				System.err.format("Missing bundle %s%n", EXEMPLARY_SETUP_BUNDLE);
				return;
			}

			IProfileRegistry profileRegistry = bucky.getService(IProfileRegistry.class);
			IProfile profile = profileRegistry.getProfile("SDKProfile");
			for(Map.Entry<String, String> entry : ((Map<String, String>)profile.getProperties()).entrySet())
				System.out.format("%20s = %s%n", entry.getKey(), entry.getValue());

			ProvisioningContext context = createContext(URI.create("file:/home/thhal/tmp/galileo.test/repo"));
			ProfileChangeRequest request = new ProfileChangeRequest(profile);
			request.setProfileProperty(IProfile.PROP_ENVIRONMENTS, "osgi.ws=win32,osgi.os=win32,osgi.arch=x86");
			IPlanner planner = bucky.getService(IPlanner.class);
			ProvisioningPlan plan = planner.getProvisioningPlan(request, context, null);
			IEngine engine = bucky.getService(IEngine.class);
			IStatus status = engine.perform(profile, new DefaultPhaseSet(), plan.getOperands(), context, null);
			if(status.getSeverity() == IStatus.ERROR)
				throw BuckminsterException.wrap(status);

			profile = profileRegistry.getProfile("SDKProfile");
			for(Map.Entry<String, String> entry : ((Map<String, String>)profile.getProperties()).entrySet())
				System.out.format("%20s = %s%n", entry.getKey(), entry.getValue());

			bucky.ungetService(profileRegistry);
			bucky.ungetService(planner);
			bucky.ungetService(engine);
		}
		finally
		{
			bucky.ungetService(packageAdmin);
		}
	}
}

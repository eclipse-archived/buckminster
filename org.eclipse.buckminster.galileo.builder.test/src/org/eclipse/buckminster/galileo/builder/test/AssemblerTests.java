package org.eclipse.buckminster.galileo.builder.test;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.buckminster.galileo.builder.BuildModel;
import org.eclipse.buckminster.galileo.builder.CompositeRepoGenerator;
import org.eclipse.buckminster.galileo.builder.ExtraRepoGenerator;
import org.eclipse.buckminster.galileo.builder.MirrorGenerator;
import org.eclipse.buckminster.galileo.builder.PlatformRepoGenerator;
import org.eclipse.buckminster.galileo.builder.RepositoryVerifier;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.service.packageadmin.PackageAdmin;
import org.w3c.dom.Document;

public class AssemblerTests extends TestCase
{
	static private final String EXEMPLARY_SETUP = "org.eclipse.equinox.p2.exemplarysetup"; //$NON-NLS-1$

	public static Test suite() throws Exception
	{
		File tpLocation = new File("/home/thhal/tmp/eclipse");
		Buckminster bucky = Buckminster.getDefault();
		PackageAdmin packageAdmin = bucky.getService(PackageAdmin.class);
		try
		{
			if(!startEarly(packageAdmin, EXEMPLARY_SETUP))
			{
				Buckminster.getLogger().error(NLS.bind("Missing bundle {0}", EXEMPLARY_SETUP));
				return null;
			}
		}
		finally
		{
			bucky.ungetService(packageAdmin);
		}

		TestSuite suite = new TestSuite("Tests for org.eclipse.buckminster.galileo.assembler"); //$NON-NLS-1$
		DocumentBuilder bld = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = bld.parse(new File(
				"/home/thhal/workspaces/galileo-build/org.eclipse.galileo.build/out/build/galileo/galileo.build"));
		BuildModel bm = new BuildModel(document.getDocumentElement());
		File baseLocation = new File("/home/thhal/tmp/galileo.test/tmp");
		File targetRepoLocation = new File("/home/thhal/tmp/galileo.test/repo");
		suite.addTest(new AssemblerTests("testCompositeGenerator", bm, baseLocation, tpLocation, targetRepoLocation));
		suite.addTest(new AssemblerTests("testPlatformRepoGenerator", bm, baseLocation, tpLocation, targetRepoLocation));
		suite.addTest(new AssemblerTests("testExtraRepoGenerator", bm, baseLocation, tpLocation, targetRepoLocation));
		suite.addTest(new AssemblerTests("testRepositoryVerifier", bm, baseLocation, tpLocation, targetRepoLocation));
		suite.addTest(new AssemblerTests("testMirroring", bm, baseLocation, tpLocation, targetRepoLocation));
		return suite;
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

	private static boolean startEarly(PackageAdmin packageAdmin, String bundleName) throws BundleException
	{
		Bundle bundle = getBundle(packageAdmin, bundleName);
		if(bundle == null)
			return false;
		bundle.start(Bundle.START_TRANSIENT);
		return true;
	}

	private final BuildModel m_buildModel;

	private final File m_repoLocation;

	private final File m_targetPlatformLocation;

	private final File m_targetRepoLocation;

	public AssemblerTests(String testName, BuildModel buildModel, File repoLocation, File targetPlatformLocation,
			File targetRepoLocation)
	{
		super(testName);
		m_buildModel = buildModel;
		m_repoLocation = repoLocation;
		m_targetPlatformLocation = targetPlatformLocation;
		m_targetRepoLocation = targetRepoLocation;
	}

	public void testCompositeGenerator() throws Exception
	{
		CompositeRepoGenerator repoGenerator = new CompositeRepoGenerator(m_repoLocation,
				"Galileo Composite Repository");
		repoGenerator.run(m_buildModel, new NullProgressMonitor());

	}

	public void testExtraRepoGenerator() throws Exception
	{
		ExtraRepoGenerator extraGenerator = new ExtraRepoGenerator(m_repoLocation, "Generated contributions");
		extraGenerator.run(m_buildModel);
	}

	public void testMirroring() throws Exception
	{
		MirrorGenerator mirrorGenerator = new MirrorGenerator(m_repoLocation.toURI(), m_targetRepoLocation,
				"Galileo Repository");
		mirrorGenerator.run(new NullProgressMonitor());
	}

	public void testPlatformRepoGenerator() throws Exception
	{
		PlatformRepoGenerator tpGenerator = new PlatformRepoGenerator(m_repoLocation, m_targetPlatformLocation);
		tpGenerator.run(new NullProgressMonitor());
	}

	public void testRepositoryVerifier() throws Exception
	{
		RepositoryVerifier ipt = new RepositoryVerifier(m_repoLocation.toURI(), "org.eclipse.galileo", null);
		ipt.run(m_buildModel.getConfigurations(), new NullProgressMonitor());
	}
}

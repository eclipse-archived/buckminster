/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.test.junit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.buckminster.test.junit.TestCommand.TestLocationResolver.TestSuiteDescriptor;
import org.eclipse.buckminster.test.junit.TestRunner.TestSuiteResult;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Bundle;
import org.osgi.service.packageadmin.PackageAdmin;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * <p>
 * JUnit testing support for Buckminster.
 * </p>
 * <p>
 * The class implements a "test" command. The command adds JUnit plug-in testing support to Buckminster.
 * </p>
 * <p>
 * For usage instruction issue:
 * <ul>
 * <code>buckminster test -?</code>
 * </ul>
 * </p>
 * 
 * @author Michal Rùžièka
 */
public class TestCommand extends AbstractCommand
{
	/**
	 * <p>
	 * A test location resolver helper class.
	 * </p>
	 */
	protected static class TestLocationResolver
	{
		public static final String RUNTIME_LOCATION = "runtime";

		public static final String DEAFULT_TEST_LIST_RESOURCE = "/plugin-tests.lst";

		@SuppressWarnings("serial")
		public static class ResolutionException extends Exception
		{
			public ResolutionException(Throwable cause)
			{
				super(cause);
			}
		}

		/**
		 * <p>
		 * The resolved test description.
		 * </p>
		 */
		public interface TestSuiteDescriptor
		{
			Bundle getSuiteBundle() throws ResolutionException;

			Class<?> getSuiteClass() throws ResolutionException;

			String getSuiteName();
		}

		protected static class UnresolvedTestSuiteDescriptor implements TestSuiteDescriptor
		{
			private String m_suiteName;
			private Throwable m_cause;

			public UnresolvedTestSuiteDescriptor(String suiteName, Throwable resolutionException)
			{
				m_suiteName = suiteName;
				m_cause = resolutionException;
				m_cause.fillInStackTrace();
			}

			public Bundle getSuiteBundle() throws ResolutionException
			{
				throw new ResolutionException(m_cause);
			}

			public Class<?> getSuiteClass() throws ResolutionException
			{
				throw new ResolutionException(m_cause);
			}

			public String getSuiteName()
			{
				return m_suiteName;
			}
		}

		private static String getBundleResourceName(Bundle bundle, String resource)
		{
			return bundle.getSymbolicName() + ':' + resource;
		}

		protected static class ResolvedTestSuiteDescriptor implements TestSuiteDescriptor
		{
			private Bundle m_suiteBundle;
			private Class<?> m_suiteClass;

			public ResolvedTestSuiteDescriptor(Bundle suiteBundle, Class<?> suiteClass)
			{
				m_suiteBundle = suiteBundle;
				m_suiteClass = suiteClass;
			}

			public Bundle getSuiteBundle()
			{
				return m_suiteBundle;
			}

			public Class<?> getSuiteClass()
			{
				return m_suiteClass;
			}

			public String getSuiteName()
			{
				return getBundleResourceName(m_suiteBundle, m_suiteClass.getName());
			}
		}

		private static TestPlugin s_testPlugin = TestPlugin.getDefault();

		private PackageAdmin m_packageAdmin = s_testPlugin.getPackageAdmin();

		private Set<URL> m_seenURLs = new HashSet<URL>();

		private List<TestSuiteDescriptor> m_resolvedSuites;

		/**
		 * <p>
		 * The sole constructor and the only publicly accessible (non static) member of this class. The resolution
		 * process is started immediately upon instantiation of this class. Once the resolution is done the instance is
		 * not useful any more. The resolved tests are stored in the supplied <code>resolvedSuites<code> list.
		 * </p>
		 *
		 * @param locations the test locations to resolve
		 * @param monitor resolution progress monitor 
		 * @param resolvedSuites the list where to store the resolved tests
		 */
		public TestLocationResolver(String[] locations, IProgressMonitor monitor, List<TestSuiteDescriptor> resolvedSuites)
		{
			monitor.beginTask("Resolving test suites", locations.length+1);

			m_resolvedSuites = resolvedSuites;

			monitor.worked(1);

			for(String location : locations)
			{
				if(monitor.isCanceled())
				{
					return;
				}

				resolveLocation(location, null);

				monitor.worked(1);
			}

			monitor.done();
		}

		/**
		 * <p>
		 * Checks if the supplied <code>name</code> is a valid bundle name. It is identical in function to
		 * {@link org.eclipse.pde.internal.core.util.IdUtil#isValidCompositeID(String name)}
		 * </p>
		 * 
		 * @param name
		 *            the name to check
		 * @return <code>true</code> if the supplied <code>name</code> is a valid bundle name, <code>false</code>
		 *         otherwise
		 * @see org.eclipse.pde.internal.core.util.IdUtil#isValidCompositeID(String name)
		 */
		public static boolean isBundleName(String name)
		{
			if(name.length() == 0)
			{
				return false;
			}

			int cp = name.codePointAt(0);

			if(!(cp >= 'A' && cp <= 'Z' || cp >= 'a' && cp <= 'z' || cp >= '0' && cp <= '9' || cp == '_'))
			{
				return false;
			}

			int i = Character.charCount(cp);

			if(i >= name.length())
			{
				return true;
			}

			for(;;)
			{
				cp = name.codePointAt(i);

				if(cp >= 'A' && cp <= 'Z' || cp >= 'a' && cp <= 'z' || cp >= '0' && cp <= '9' || cp == '_')
				{
					i += Character.charCount(cp);

					if(i < name.length())
					{
						continue;
					}

					return true;
				}

				if(cp == '.')
				{
					i += Character.charCount(cp);

					if(i < name.length())
					{
						continue;
					}
				}

				return false;
			}
		}

		/**
		 * <p>
		 * Checks if the supplied <code>name</code> is a valid fully qualified Java class name i.e. a sequence of one
		 * or more valid Java identifiers separated by dots ("<code>.</code>").
		 * </p>
		 * 
		 * @param name
		 *            the name to check
		 * @return <code>true</code> if the supplied <code>name</code> is a valid fully qualified Java class name,
		 *         <code>false</code> otherwise
		 * @see (a method introduced Java 6) javax.lang.model.SourceVersion.isIdentifier(CharSequence name)
		 */
		public static boolean isQualifiedIdentifier(String name)
		{
			int cp;

			for(int i = 0;; i += Character.charCount(cp))
			{
				if(i >= name.length())
				{
					return false;
				}

				cp = name.codePointAt(i);

				if(!Character.isJavaIdentifierStart(cp))
				{
					return false;
				}

				for(;;)
				{
					i += Character.charCount(cp);

					if(i >= name.length())
					{
						return true;
					}

					cp = name.codePointAt(i);

					if(cp == '.')
					{
						break;
					}

					if(!Character.isJavaIdentifierPart(cp))
					{
						return false;
					}
				}
			}
		}

		@SuppressWarnings("unchecked")
		private static Enumeration<URL> getBundleResources(Bundle bundle, String resource) throws IOException
		{
			return bundle.getResources(resource);
		}

		/**
		 * <p>
		 * Resolves (possibly recursively) the supplied <code>location</code> assuming the <code>parent</code>
		 * bundle to contain locations which don't have their containing bundle explicitly specified.
		 * </p>
		 * 
		 * @param location
		 *            the location to resolve
		 * @param parent
		 *            a bundle to be assumed to contain the resources which don't have their containing bundle
		 *            explicitly specified
		 */
		private void resolveLocation(String location, Bundle parent)
		{
			if(!location.startsWith(RUNTIME_LOCATION + ':'))
			{
				// try to handle the location specification as an URL first
				try
				{
					resolveLocationList(new URL(location), null);
					return;
				}
				catch(MalformedURLException e)
				{
					// fall through
				}
				catch(IOException e)
				{
					m_resolvedSuites.add(new UnresolvedTestSuiteDescriptor(location, e));
					return;
				}
			}
			else
			{
				location = location.substring(RUNTIME_LOCATION.length() + 1);
			}

			// handle the location specification as a runtime reference
			Bundle bundle;
			String resource;

			{
				int pos = location.indexOf(':');
				String bundleName;

				if(pos > 0 && isBundleName(bundleName = location.substring(0, pos)))
				{
					Bundle[] bundles = m_packageAdmin.getBundles(bundleName, null);

					if(bundles == null)
					{
						m_resolvedSuites.add(new UnresolvedTestSuiteDescriptor(location, new RuntimeException("No such bundle: " + bundleName)));
						return;
					}

					bundle = bundles[0];
					resource = location.substring(pos + 1);
				}
				else if(parent == null)
				{
					m_resolvedSuites.add(new UnresolvedTestSuiteDescriptor(location, new RuntimeException("Illegal or no bundle specified")));
					return;
				}
				else
				{
					bundle = parent;
					resource = (pos == 0) ? location.substring(1) : location;
				}
			}

			if(isQualifiedIdentifier(resource))
			{
				// try to handle the resource as a class name (if it is a legal class name)
				try
				{
					m_resolvedSuites.add(new ResolvedTestSuiteDescriptor(bundle, bundle.loadClass(resource)));
				}
				catch(Throwable t)
				{
					m_resolvedSuites.add(new UnresolvedTestSuiteDescriptor(getBundleResourceName(bundle, resource), t));
				}
			}
			else
			{
				Enumeration<URL> resourceEnumerator;
				
				if(resource.length() == 0)
				{
					resource = DEAFULT_TEST_LIST_RESOURCE;
				}

				try
				{
					resourceEnumerator = getBundleResources(bundle, resource);
				}
				catch(Throwable t)
				{
					m_resolvedSuites.add(new UnresolvedTestSuiteDescriptor(getBundleResourceName(bundle, resource), t));
					return;
				}

				if(resourceEnumerator == null)
				{
					m_resolvedSuites.add(new UnresolvedTestSuiteDescriptor(getBundleResourceName(bundle, resource),
							new RuntimeException("No such resource: " + resource)));
					return;
				}

				while(resourceEnumerator.hasMoreElements())
				{
					URL resourceURL = resourceEnumerator.nextElement();

					try
					{
						resolveLocationList(resourceURL, bundle);
					}
					catch(IOException e)
					{
						m_resolvedSuites.add(new UnresolvedTestSuiteDescriptor(getBundleResourceName(bundle, resource), new RuntimeException("Error accessing " + resourceURL.toString(), e)));
					}
				}
			}
		}

		/**
		 * <p>
		 * Resolves test locations contained in the target of the supplied <code>locationList</code> URL. The URL
		 * target is assumed to contain newline separated list of test locations. Assumes the <code>parent</code>
		 * bundle to contain locations which don't have their containing bundle explicitly specified.
		 * </p>
		 * 
		 * @param locationList
		 *            the URL pointing to the resource to process
		 * @param parent
		 *            a bundle to be assumed to contain the resources which don't have their containing bundle
		 *            explicitly specified
		 */
		private void resolveLocationList(URL locationList, Bundle parent) throws IOException
		{
			// prevent recursion
			if(m_seenURLs.contains(locationList))
			{
				return;
			}

			m_seenURLs.add(locationList);

			BufferedReader reader = new BufferedReader(new InputStreamReader(locationList.openStream()));
			String location;

			while((location = reader.readLine()) != null)
			{
				resolveLocation(location, parent);
			}

			reader.close();
		}
	}


	private static final OptionDescriptor RESULT_PREFIX = new OptionDescriptor('p', "prefix", OptionValueType.REQUIRED);

	private static final OptionDescriptor RESULT_DIRECTORY = new OptionDescriptor('d', "directory", OptionValueType.REQUIRED);

	private static final OptionDescriptor QUIET_FLAG = new OptionDescriptor('q', "quiet", OptionValueType.NONE);

	private boolean m_quietFlag;

	private String[] m_locations;

	private File m_resultDirectory;

	private String m_resultPrefix;

	private File getReportFile()
	{
		String fileName = (m_resultPrefix != null
				? m_resultPrefix
				: "result") + '-' + System.currentTimeMillis() + ".xml";

		return m_resultDirectory != null
				? new File(m_resultDirectory, fileName)
				: new File(fileName);
	}

	/**
	 * <p>
	 * The implementation of the <code>test</code> command for Buckminster. For more info see its usage instructions
	 * by issuing:
	 * <ul>
	 * <code>buckminster test -?</code>
	 * </ul>
	 * </p>
	 */
	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		int len = m_locations.length;

		if(len == 0)
		{
			throw new UsageException("No testsuites specified.");
		}

		monitor.beginTask("Buckminster JUnit testing", 10000);

		List<TestSuiteDescriptor> resolvedLocations = new LinkedList<TestSuiteDescriptor>();
		new TestLocationResolver(m_locations, MonitorUtils.subMonitor(monitor, 1000), resolvedLocations);

		File reportFile = getReportFile();
		ContentHandler report;

		IProgressMonitor submonitor = MonitorUtils.subMonitor(monitor, 9000);

		submonitor.beginTask("Executing test suites", resolvedLocations.size());

		try
		{
			report = Utils.newSerializer(reportFile, new FileOutputStream(reportFile), "UTF-8", -1, false);
		}
		catch(FileNotFoundException e)
		{
			throw new Exception("Failed to create report file: " + reportFile.getPath(), e);
		}
		catch(SAXException e)
		{
			throw new Exception("Failed to create XML serializer of the test results", e);
		}

		TestSuiteResult.startReport(report);

		{
			TestRunner runner = new TestRunner();
			TestSuiteResult result;

			for(TestSuiteDescriptor testSuite : resolvedLocations)
			{
				try
				{
					submonitor.subTask(testSuite.getSuiteName());

					if(!m_quietFlag)
					{
						System.out.println("Running test suite: " + testSuite.getSuiteName());
					}

					result = runner.run(testSuite, submonitor);

					try
					{
						result.toSax(report, null, null, result.getDefaultTag());
					}
					catch(SAXException e)
					{
						throw new Exception("Failed to serialize test result", e);
					}
				}
				finally
				{
					submonitor.subTask(null);
					submonitor.worked(1);

					if(submonitor.isCanceled())
					{
						break;
					}
				}
			}
		}

		TestSuiteResult.endReport(report);

		submonitor.done();
		monitor.done();

		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void getOptionDescriptors(List appendHere) throws Exception
	{
		super.getOptionDescriptors(appendHere);
		appendHere.add(RESULT_PREFIX);
		appendHere.add(RESULT_DIRECTORY);
		appendHere.add(QUIET_FLAG);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(RESULT_PREFIX))
		{
			m_resultPrefix = option.getValue();
		}
		else if(option.is(RESULT_DIRECTORY))
		{
			m_resultDirectory = new File(option.getValue());
		}
		else if(option.is(QUIET_FLAG))
		{
			m_quietFlag = true;
		}
		else
		{
			super.handleOption(option);
		}
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		m_locations = unparsed;
	}
}

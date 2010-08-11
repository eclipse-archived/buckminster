/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.util.RmapResourceFactoryImpl;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.osgi.framework.Bundle;

/**
 * <!-- begin-user-doc --> A test suite for the '<em><b>rmap</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class RmapTests extends TestSuite {

	public static File getTestData(String fileName) {
		Bundle self = Activator.context.getBundle();
		URL base = self.getEntry("testData");
		if (base == null)
			Assert.fail("Unable to find \"testData\" folder");
		return new File(toFile(base), fileName);
	}

	public static ResourceMap loadTestResourceMap(String fileName) {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new RmapResourceFactoryImpl());

		resourceSet.getPackageRegistry().put(RmapPackage.eNS_URI, RmapPackage.eINSTANCE);

		Resource resource = resourceSet.getResource(URI.createFileURI(getTestData(fileName).getAbsolutePath()), true);
		return (ResourceMap) resource.getContents().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new RmapTests("rmap Tests");
		suite.addTestSuite(DocumentRootTest.class);
		suite.addTestSuite(LocatorTest.class);
		suite.addTestSuite(ProviderTest.class);
		suite.addTestSuite(RedirectTest.class);
		suite.addTestSuite(ResourceMapTest.class);
		suite.addTestSuite(TransformTest.class);
		suite.addTestSuite(URIMatcherTest.class);
		suite.addTestSuite(VersionConverterTest.class);
		return suite;
	}

	private static File toFile(URL url) {
		File file = null;
		try {
			file = new File(new Path(FileLocator.toFileURL(url).getPath()).toOSString());
		} catch (IOException e) {
			Assert.fail("Exception while converting URL \"" + url + "\" to a file: " + e.getMessage());
		}
		return file;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public RmapTests(String name) {
		super(name);
	}

} // RmapTests

package org.eclipse.buckminster.pde.test;

import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.eclipse.buckminster.pde.internal.FeatureModelReader;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;

@SuppressWarnings("restriction")
public class SourceFeatureTest extends CommonPdeProjectTest {

	public void testFragmentSourceEntry() throws Exception {
		// Assert that we build OK
		cleanBuild();
		runBuildFeatureAction("source.feature.jar");

		// Assert that the source feature is created and published
		getResult().refreshLocal(IResource.DEPTH_INFINITE, monitor());
		IFolder jarFolder = getBuildFeatureOutputFolder().getFolder("source.jar");
		assertTrue("No source.jar folder was created by source.feature.jar action", jarFolder.exists());
		IResource[] members = jarFolder.members();
		assertEquals("Incorrect number of members in source.jar folder", 1, members.length);

		// Assert source feature manifest contains desired entries
		Set<String> sourceBundles = new HashSet<String>();
		IFile jar = (IFile) members[0];
		JarFile jarFile = new JarFile(jar.getLocation().toFile());
		try {
			ZipEntry manifest = jarFile.getEntry("feature.xml");
			IFeature feature = FeatureModelReader.readFeatureModel(jarFile.getInputStream(manifest)).getFeature();
			for (IFeaturePlugin iFeaturePlugin : feature.getPlugins()) {
				sourceBundles.add(iFeaturePlugin.getId());
			}
		} finally {
			jarFile.close();
		}
		assertTrue("Source entry for included plugin is missing", sourceBundles.contains("common.project.test.plugin.source"));
		assertTrue("Source entry for included fragment is missing", sourceBundles.contains("common.project.test.fragment.source"));
		assertEquals("Only two plugins should be contained", 2, sourceBundles.size());
	}

}

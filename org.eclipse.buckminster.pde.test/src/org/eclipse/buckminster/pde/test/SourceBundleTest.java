package org.eclipse.buckminster.pde.test;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.osgi.framework.Constants;

public class SourceBundleTest extends PDETestCase {
	private static final String NAME = "sourceGenBundle";
	private static final String VERSION = "1.0.0";

	public void testSourceBundleGenFolder() throws Exception {
		File projectFolder = getTestData(NAME);
		IProgressMonitor monitor = new NullProgressMonitor();

		IWorkspace workspace = ResourcesPlugin.getWorkspace();

		// Bind project
		IProject project = createProject(projectFolder, workspace);

		// The buckminster.properties will redirect the build result to this
		// location
		IWorkspaceRoot wsRoot = workspace.getRoot();
		IProject result = wsRoot.getProject("result");
		if (result.exists())
			result.delete(true, true, monitor);
		result.create(monitor);
		result.open(monitor);

		// Assert that we build OK
		workspace.build(IncrementalProjectBuilder.FULL_BUILD, monitor);

		Map<String, String> props = Collections.singletonMap("buckminster.output.root", "${workspace.root}/result");
		IPerformManager performManager = CorePlugin.getPerformManager();
		performManager.perform(Collections.singletonList(WorkspaceInfo.getCSpec(project).getAttribute("source.bundle.jar")), props, false, true,
				monitor);

		// Assert that the bundle is created and published
		result.refreshLocal(IResource.DEPTH_INFINITE, monitor);
		IFolder jarFolder = result.getFolder(NAME + '_' + VERSION + "-osgi.bundle/source.jar");
		assertTrue("No source.jar folder was created by source.bundle.jar action", jarFolder.exists());
		IResource[] members = jarFolder.members();
		assertEquals("Incorrect number of members in source.jar folder", 1, members.length);
		IFile jar = (IFile) members[0];
		JarFile jarFile = new JarFile(jar.getLocation().toFile());
		try {
			Manifest manifest = jarFile.getManifest();
			Attributes attributes = manifest.getMainAttributes();
			assertEquals("Bundle-Name not translated in source bundle manifest", "SourceGen Test Bundle Source",
					attributes.getValue(Constants.BUNDLE_NAME));
			assertEquals("Bundle-Vendor not translated in source bundle manifest", "TheProvider", attributes.getValue(Constants.BUNDLE_VENDOR));
		} finally {
			jarFile.close();
		}
	}
}

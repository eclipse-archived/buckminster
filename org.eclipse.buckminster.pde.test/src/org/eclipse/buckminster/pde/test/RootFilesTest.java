package org.eclipse.buckminster.pde.test;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

public class RootFilesTest extends PDETestCase {
	public void testRootsNotFlattened() throws Exception {
		File projectFolder = getTestData("rootfiles");
		File[] dirList = projectFolder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return !pathname.getName().startsWith(".");
			}
		});
		assertTrue(projectFolder.getAbsolutePath() + " is not a directory", dirList != null);
		assertEquals("Incorrect number of folders in directory " + projectFolder.getAbsolutePath(), 4, dirList.length);

		IProgressMonitor monitor = new NullProgressMonitor();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot wsRoot = workspace.getRoot();

		ArrayList<IProject> boundProjects = new ArrayList<IProject>();
		// Bind projects
		for (File projDir : dirList) {
			IPath locationPath = Path.fromOSString(projDir.getAbsolutePath());
			IProjectDescription description = workspace.loadProjectDescription(locationPath.append(".project")); //$NON-NLS-1$
			IProject project = wsRoot.getProject(description.getName());
			project.create(description, monitor);
			project.open(monitor);
			boundProjects.add(project);
		}

		// The buckminster.properties will redirect the build result to this
		// location
		IProject result = wsRoot.getProject("result");
		result.create(monitor);
		result.open(monitor);

		// Assert that we build OK
		workspace.build(IncrementalProjectBuilder.FULL_BUILD, monitor);

		boolean errors = false;
		List<String> messages = new ArrayList<String>();
		for (IProject project : boundProjects) {
			IMarker[] markers = project.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			for (IMarker marker : markers) {
				if (marker.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) == IMarker.SEVERITY_ERROR)
					errors = true;
				messages.add(marker.getAttribute(IMarker.LOCATION, "<unknown location>") + ": "
						+ marker.getAttribute(IMarker.MESSAGE, "<unknown problem>"));
			}
		}
		if (errors) {
			for (String message : messages)
				System.out.println(message);
			fail();
		}

		// Load the properties
		IProject siteFeature = wsRoot.getProject("test.update");
		InputStream inStream = siteFeature.getFile("build.properties").getContents();
		Map<String, String> props = new BMProperties(inStream);
		inStream.close();
		IPerformManager performManager = CorePlugin.getPerformManager();
		performManager.perform(Collections.singletonList(WorkspaceInfo.getCSpec(siteFeature).getAttribute("site.p2")), props, false, true, monitor);

		// Assert that both bundles were created and published
		result.refreshLocal(IResource.DEPTH_INFINITE, monitor);
		IFolder binary = result.getFolder("test.update_1.0.0-eclipse.feature/site.p2/binary");
		assertTrue("No binary folder was created in p2 repository", binary.exists());
		IResource[] binaryFiles = binary.members();
		assertEquals("Unexpected number of files in binary folder", 2, binaryFiles.length);

		List<String> allRoots = new ArrayList<String>();
		IFile binFile = (IFile) binaryFiles[0];
		ZipInputStream zi = new ZipInputStream(binFile.getContents());
		ZipEntry ze;
		while ((ze = zi.getNextEntry()) != null)
			if (!ze.isDirectory())
				allRoots.add(ze.getName());
		zi.close();
		binFile = (IFile) binaryFiles[1];
		zi = new ZipInputStream(binFile.getContents());
		while ((ze = zi.getNextEntry()) != null)
			if (!ze.isDirectory())
				allRoots.add(ze.getName());
		zi.close();
		System.out.println(allRoots);
		assertEquals("Unexpected number of root files", 3, allRoots.size());
		assertTrue("Root file mycfg1/test.txt is missing", allRoots.contains("mycfg1/test.txt"));
		assertTrue("Root file mycfg1/test/test2.txt is missing", allRoots.contains("mycfg1/test/test2.txt"));
		assertTrue("Root file jvm/java is missing", allRoots.contains("jvm/java"));
	}
}

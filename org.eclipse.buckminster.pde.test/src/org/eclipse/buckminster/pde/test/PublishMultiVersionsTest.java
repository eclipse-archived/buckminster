package org.eclipse.buckminster.pde.test;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.commands.Build;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.pde.prefs.TargetPlatformPathHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

public class PublishMultiVersionsTest extends PDETestCase {
	public void testMultiVersions() throws Exception {
		File projectFolder = getTestData("multiver");
		File[] dirList = projectFolder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return !pathname.getName().startsWith(".");
			}
		});
		assertTrue(projectFolder.getAbsolutePath() + " is not a directory", dirList != null);
		assertEquals("Incorrect number of folders in directory " + projectFolder.getAbsolutePath(), 6, dirList.length);

		IProgressMonitor monitor = new NullProgressMonitor();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot wsRoot = workspace.getRoot();

		ArrayList<IProject> boundProjects = new ArrayList<IProject>();
		// Bind projects
		for (File projDir : dirList) {
			IPath locationPath = Path.fromOSString(projDir.getAbsolutePath());
			IProjectDescription description;
			try {
				description = workspace.loadProjectDescription(locationPath.append(".project")); //$NON-NLS-1$
			} catch (CoreException e) {
				// The target platform is not actually a project so we need to
				// create
				// a description for it.
				description = workspace.newProjectDescription(locationPath.lastSegment());
				description.setLocation(locationPath);
			}
			IProject project = wsRoot.getProject(description.getName());
			project.create(description, monitor);
			project.open(monitor);
			boundProjects.add(project);
		}

		// The buckminster.properties will redirect the build result to this
		// location
		IProject result = wsRoot.getProject("output");
		if (result.exists())
			result.delete(true, true, monitor);

		result.create(monitor);
		result.open(monitor);

		// Set the target platform
		TargetPlatformPathHandler tpp = new TargetPlatformPathHandler();
		tpp.set(wsRoot.getProject("com.extol.test.target").getLocation().toOSString());

		// Assert that we build OK
		workspace.build(IncrementalProjectBuilder.FULL_BUILD, monitor);

		for (IProject project : boundProjects) {
			int errors = 0;
			IMarker[] markers = project.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			for (IMarker marker : markers) {
				System.err.println(Build.formatMarkerMessage("Marker", marker));
				if (marker.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) == IMarker.SEVERITY_ERROR)
					++errors;
			}
			assertEquals("We've got error markers on project " + project.getName(), 0, errors);
		}

		// Load the properties
		IProject repoFeature = wsRoot.getProject("com.extol.test.repo.feature");
		InputStream inStream = repoFeature.getFile("buckminster.properties").getContents();
		Map<String, String> props = new BMProperties(inStream);
		inStream.close();
		IPerformManager performManager = CorePlugin.getPerformManager();
		performManager.perform(Collections.singletonList(WorkspaceInfo.getCSpec(repoFeature).getAttribute("site.p2")), props, false, true, monitor);

		// Assert that both bundles were created and published
		result.refreshLocal(IResource.DEPTH_INFINITE, monitor);
		IFile one_1_0_0 = result.getFile("com.extol.test.repo.feature_1.0.0-eclipse.feature/site.p2/plugins/com.extol.test.one_1.0.0.jar");
		assertTrue("com.extol.test.one_1.0.0 is not published", one_1_0_0.exists());
		IFile one_2_0_0 = result.getFile("com.extol.test.repo.feature_1.0.0-eclipse.feature/site.p2/plugins/com.extol.test.one_2.0.0.jar");
		assertTrue("com.extol.test.one_2.0.0 is not published", one_2_0_0.exists());
	}
}

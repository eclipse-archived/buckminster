package org.eclipse.buckminster.pde.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.IPerformManager;
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

public class MultiSourceFolderTest extends PDETestCase {
	private static final String NAME = "multiple-source-folder";
	private static final String VERSION = "1.0.0";

	public void testMultiSourceFolder() throws Exception {
		File projectFolder = getTestData(NAME);
		IProgressMonitor monitor = new NullProgressMonitor();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot wsRoot = workspace.getRoot();

		// Bind project
		IPath locationPath = Path.fromOSString(projectFolder.getAbsolutePath());
		IProjectDescription description = workspace.loadProjectDescription(locationPath.append(".project")); //$NON-NLS-1$
		IProject project = wsRoot.getProject(description.getName());
		project.create(description, monitor);
		project.open(monitor);

		// The buckminster.properties will redirect the build result to this
		// location
		IProject result = wsRoot.getProject("result");
		result.create(monitor);
		result.open(monitor);

		// Assert that we build OK
		workspace.build(IncrementalProjectBuilder.FULL_BUILD, monitor);

		boolean errors = false;
		List<String> messages = new ArrayList<String>();
		IMarker[] markers = project.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		for (IMarker marker : markers) {
			if (marker.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) == IMarker.SEVERITY_ERROR)
				errors = true;
			messages.add(marker.getAttribute(IMarker.LOCATION, "<unknown location>") + ": "
					+ marker.getAttribute(IMarker.MESSAGE, "<unknown problem>"));
		}
		if (errors) {
			for (String message : messages)
				System.out.println(message);
			fail();
		}

		Map<String, String> props = Collections.singletonMap("buckminster.output.root", "${workspace.root}/result");
		IPerformManager performManager = CorePlugin.getPerformManager();
		performManager.perform(Collections.singletonList(WorkspaceInfo.getCSpec(project).getAttribute("bundle.jar")), props, false, true, monitor);

		// Assert that both bundles were created and published
		result.refreshLocal(IResource.DEPTH_INFINITE, monitor);
		IFolder jar = result.getFolder(NAME + '_' + VERSION + "-osgi.bundle/jar");
		assertTrue("No jar folder was created by bundle.jar action", jar.exists());
		IResource[] members = jar.members();
		assertEquals("The jar folder created by bundle.jar action does not contain 1 file", members.length, 1);
		assertTrue("The jar folder created by bundle.jar action does not contain a file", members[0] instanceof IFile);

		Set<String> allFiles = new HashSet<String>();
		ZipInputStream zi = new ZipInputStream(((IFile) members[0]).getContents());
		ZipEntry ze;
		while ((ze = zi.getNextEntry()) != null)
			if (!ze.isDirectory())
				allFiles.add(ze.getName());
		zi.close();
		System.out.println(allFiles);
		assertEquals("Unexpected number of root files", 4, allFiles.size());
		assertTrue("webapp.txt is missing", allFiles.contains("webapp.txt"));
		assertTrue("META-INF/MANIFEST.MF is missing", allFiles.contains("META-INF/MANIFEST.MF"));
		assertTrue("WEB-INF/classes/java.txt is missing", allFiles.contains("WEB-INF/classes/java.txt"));
		assertTrue("WEB-INF/classes/resource.txt is missing", allFiles.contains("WEB-INF/classes/resource.txt"));
	}
}

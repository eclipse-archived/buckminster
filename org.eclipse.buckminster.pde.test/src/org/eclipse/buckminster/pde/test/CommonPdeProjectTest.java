package org.eclipse.buckminster.pde.test;

import java.io.File;
import java.util.Collections;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.junit.Before;

public abstract class CommonPdeProjectTest extends PDETestCase {

	protected static final String COMMON_PROJECT_TEST_BUILD_FEATURE_PRJ_NAME = "common.project.test.build.feature";
	private static final String TEST_PROJECTS_LOC = "commonProjectTest";
	protected static final String VERSION = "1.0.0";
	private IProject result;
	private IProgressMonitor monitor;
	private IWorkspace workspace;

	public CommonPdeProjectTest() {
		super();
	}

	protected void cleanBuild() throws CoreException {
		getWorkspace().build(IncrementalProjectBuilder.CLEAN_BUILD, monitor());
		boolean wasInterrupted = false;
		do {
			try {
				Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
				wasInterrupted = false;
			} catch (OperationCanceledException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				wasInterrupted = true;
			}
		} while (wasInterrupted);
	}

	protected void cleanUpWorkspace() throws CoreException {
		IProject resultProj = resultProject();
		if (resultProj.exists())
			resultProj.delete(true, true, monitor());
		for (IProject project : workspace.getRoot().getProjects()) {
			project.delete(IResource.NEVER_DELETE_PROJECT_CONTENT, monitor());
		}
	}

	private void createResultProject() throws CoreException {
		result = resultProject();
		if (result.exists()) {
			throw new IllegalStateException("Result project already exists");
		}
		result.create(monitor());
		result.open(monitor());
	}

	protected IProject getResult() {
		return result;
	}

	protected IWorkspace getWorkspace() {
		if (workspace == null) {
			workspace = ResourcesPlugin.getWorkspace();
		}
		return workspace;
	}

	protected IProgressMonitor monitor() {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		return monitor;
	}

	private IProject resultProject() {
		return getWorkspace().getRoot().getProject("result");
	}

	protected void runBuildFeatureAction(String action) throws CoreException {
		Map<String, String> props = Collections.singletonMap("buckminster.output.root", "${workspace.root}/result");
		IPerformManager performManager = CorePlugin.getPerformManager();
		IResource featureProject = getWorkspace().getRoot().getProject(COMMON_PROJECT_TEST_BUILD_FEATURE_PRJ_NAME);
		assertNotNull("Feature project '" + COMMON_PROJECT_TEST_BUILD_FEATURE_PRJ_NAME + "' does not exists in workspace "
				+ getWorkspace().getRoot().getFullPath(), featureProject);
		CSpec cSpec = WorkspaceInfo.getCSpec(featureProject);
		performManager.perform(Collections.singletonList(cSpec.getAttribute(action)), props, false, true, monitor());
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		cleanUpWorkspace();
		createResultProject();

		File projectsFolder = getTestData(TEST_PROJECTS_LOC);
		for (File projectFolder : projectsFolder.listFiles()) {
			if (projectFolder.isDirectory()) {
				createProject(projectFolder, getWorkspace());
			}
		}

	}

	protected IFolder getBuildFeatureOutputFolder() {
		return getResult().getFolder(COMMON_PROJECT_TEST_BUILD_FEATURE_PRJ_NAME + '_' + VERSION + "-eclipse.feature");
	}
}
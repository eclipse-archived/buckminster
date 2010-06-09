/**************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ***************************************************************************/
package org.eclipse.buckminster.ant.tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tools.ant.util.FileUtils;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.LocalResolver;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * Makes Buckminster aware of a project and performs a workspace bind. This task
 * is ment to be used when projects are created on the fly as the result of a
 * prebind action.
 * 
 * @author Thomas Hallgren
 */
public class WorkspaceBindTask {
	private final File projectDir;

	public WorkspaceBindTask(File projectDir) {
		this.projectDir = projectDir;
	}

	public void execute() throws CoreException {
		InputStream input = null;
		IProjectDescription projDesc = null;
		try {
			input = new BufferedInputStream(new FileInputStream(new File(projectDir, IProjectDescription.DESCRIPTION_FILE_NAME)));
			projDesc = ResourcesPlugin.getWorkspace().loadProjectDescription(input);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			FileUtils.close(input);
		}

		// Set up whats needed to simulate a cquery that resolved to a local
		// reader
		// with eclipse.installed component type.
		//
		ComponentQueryBuilder qbld = new ComponentQueryBuilder();
		qbld.setRootRequest(new ComponentRequest(projDesc.getName(), null, null));
		qbld.setPlatformAgnostic(true);
		ComponentQuery query = qbld.createComponentQuery();
		ResolutionContext context = new ResolutionContext(query);
		NodeQuery topQuery = context.getRootNodeQuery();

		IPath projectPath = Path.fromOSString(projectDir.toString()).addTrailingSeparator();
		Resolution resolution = LocalResolver.fromPath(topQuery, projectPath, null);

		Materialization mat = new Materialization(projectPath, resolution.getComponentIdentifier());
		StorageManager sm = StorageManager.getDefault();
		resolution.store(sm);
		mat.store(sm);

		BillOfMaterials bom = BillOfMaterials.create(new ResolvedNode(topQuery, resolution), query);
		MaterializationSpecBuilder mspecBuilder = new MaterializationSpecBuilder();
		mspecBuilder.setName(bom.getViewName());
		mspecBuilder.setMaterializerID(IMaterializer.WORKSPACE);
		MaterializationSpec mspec = mspecBuilder.createMaterializationSpec();
		IMaterializer wsMat = CorePlugin.getDefault().getMaterializer(IMaterializer.WORKSPACE);
		MaterializationContext matCtx = new MaterializationContext(bom, mspec, context);
		wsMat.performInstallAction(mat.getResolution(), matCtx, new NullProgressMonitor());
		IStatus status = matCtx.getStatus();
		if (status.getSeverity() == IStatus.ERROR)
			throw new CoreException(status);
	}
}

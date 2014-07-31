/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.materializer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.osgi.service.datalocation.Location;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractMaterializer extends AbstractExtension implements IMaterializer {
	private static IConfigurationElement[] getElements() {
		IExtensionRegistry exReg = Platform.getExtensionRegistry();
		return exReg.getConfigurationElementsFor(MATERIALIZERS_POINT);
	}

	public static String[] getMaterializerIDs(boolean includeEmptyEntry) {
		IConfigurationElement[] elems = getElements();
		int idx = elems.length;
		ArrayList<String> names = new ArrayList<String>(idx + 1);
		if (includeEmptyEntry)
			names.add(""); //$NON-NLS-1$
		while (--idx >= 0)
			names.add(elems[idx].getAttribute("id")); //$NON-NLS-1$
		Collections.sort(names);
		return names.toArray(new String[names.size()]);
	}

	public static void performInstallActions(BillOfMaterials bom, MaterializationContext context, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, bom.uniqueNodeCount() * 100);
		try {
			Resolution resolution = bom.getResolution();
			MaterializationSpec mspec = context.getMaterializationSpec();
			IMaterializer materializer = mspec.getMaterializer(resolution);
			Set<Resolution> perused = new LinkedHashSet<Resolution>();
			materializer.installRecursive(bom, context, new HashSet<String>(), perused, monitor);
			IStatus status = context.getStatus();
			if (status.getSeverity() == IStatus.ERROR)
				return;

			// Collect the reader types in the order they were perused
			//
			Set<String> readerTypes = new LinkedHashSet<String>();
			for (Resolution res : perused) {
				if (!mspec.isExcluded(res))
					readerTypes.add(mspec.getMaterializer(res).getMaterializationReaderType(res).getId());
			}

			CorePlugin plugin = CorePlugin.getDefault();
			for (String readerTypeId : readerTypes) {
				IReaderType readerType = plugin.getReaderType(readerTypeId);
				readerType.postMaterialization(context, new SubProgressMonitor(monitor, 1));
			}
			for (Resolution res : perused)
				mspec.getMaterializer(resolution).performPostInstallAction(res, context, new SubProgressMonitor(monitor, 1));
		} finally {
			monitor.done();
		}
	}

	@Override
	public boolean canWorkInParallel() {
		// Most materializers should be able to do this.
		//
		return true;
	}

	private void delegateAndInstallRecursive(BOMNode node, MaterializationContext context, Set<String> generated, Set<Resolution> perused,
			IProgressMonitor monitor) throws CoreException {
		Resolution res = node.getResolution();
		if (res == null)
			return;

		IMaterializer materializer;
		if (node instanceof GeneratorNode)
			materializer = this;
		else
			materializer = getMaterializer(context, res);
		((AbstractMaterializer) materializer).installRecursive(node, context, generated, perused, monitor);
	}

	private boolean generateResolution(GeneratorNode generatorNode, MaterializationContext context, IProgressMonitor monitor) throws CoreException {
		CSpec cspec = generatorNode.getDeclaringCSpec();
		try {
			IPerformManager performManager = CorePlugin.getPerformManager();
			Attribute generatorAttribute = cspec.getReferencedAttribute(generatorNode.getComponent(), null, null, generatorNode.getAttribute(),
					new ModelCache());
			if (generatorAttribute != null) {
				performManager.perform(Collections.singletonList(generatorAttribute), context, false, false, monitor);
				return true;
			}
		} catch (CoreException e) {
			if (!context.isContinueOnError())
				throw e;
			context.addRequestStatus(generatorNode.getRequest(), e.getStatus());
		}
		return false;
	}

	@Override
	public IPath getDefaultInstallRoot(MaterializationContext context, Resolution resolution) throws CoreException {
		IPath rootDir = Path.fromOSString(getMaterializerRootDir());
		if (rootDir.isAbsolute())
			return rootDir;

		if (Platform.OS_WIN32.equals(Platform.getOS())) {
			File userDir = null;
			String appDataEnv = System.getenv("APPDATA"); //$NON-NLS-1$
			if (appDataEnv != null) {
				userDir = new File(appDataEnv + "\\buckminster"); //$NON-NLS-1$
				return Path.fromOSString(new File(userDir, rootDir.toOSString()).toString());
			}
		}

		Location userLocation = Platform.getUserLocation();
		if (userLocation != null) {
			File userDir = FileUtils.getFile(userLocation.getURL());
			if (userDir != null) {
				if (Platform.OS_WIN32.equals(Platform.getOS()))
					userDir = new File(userDir, "Application Data\\buckminster"); //$NON-NLS-1$
				else
					userDir = new File(userDir, "buckminster"); //$NON-NLS-1$
				return Path.fromOSString(new File(userDir, rootDir.toOSString()).toString());
			}
		}
		throw BuckminsterException.fromMessage(Messages.Unable_to_determine_users_home_directory);
	}

	@Override
	public IReaderType getMaterializationReaderType(Resolution resolution) throws CoreException {
		return resolution.getProvider().getReaderType();
	}

	private IMaterializer getMaterializer(MaterializationContext context, Resolution res) throws CoreException {
		String materializerId = context.getMaterializationSpec().getMaterializerID(res);
		return materializerId.equals(getId()) ? this : CorePlugin.getDefault().getMaterializer(materializerId);
	}

	public abstract String getMaterializerRootDir() throws CoreException;

	@Override
	public void installRecursive(BOMNode node, MaterializationContext context, Set<String> generated, Set<Resolution> perused,
			IProgressMonitor monitor) throws CoreException {
		if (node instanceof GeneratorNode) {
			GeneratorNode generatorNode = (GeneratorNode) node;
			String generates = generatorNode.getGeneratesId().toString();
			if (!generated.contains(generates)) {
				if (generateResolution(generatorNode, context, MonitorUtils.subMonitor(monitor, 100)))
					generated.add(generates);
			}
		} else {
			Resolution resolution = node.getResolution();
			if (resolution == null || perused.contains(resolution))
				return;

			perused.add(resolution);
			for (BOMNode child : node.getChildren())
				delegateAndInstallRecursive(child, context, generated, perused, monitor);

			if (!context.getMaterializationSpec().isExcluded(resolution)) {
				// The local reader might create resolutions that are not
				// materialized and
				// hence not stored so we must make sure it's stored here.
				//
				resolution.store(StorageManager.getDefault());
				performInstallAction(resolution, context, MonitorUtils.subMonitor(monitor, 100));
			}
		}
	}

	@Override
	public void performInstallAction(Resolution resolution, MaterializationContext context, IProgressMonitor monitor) throws CoreException {
		// The AbstractMaterializer will not perform any install actions
		//
		MonitorUtils.complete(monitor);
	}

	@Override
	public void performPostInstallAction(Resolution resolution, MaterializationContext context, IProgressMonitor monitor) throws CoreException {
	}
}

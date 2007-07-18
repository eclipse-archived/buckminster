/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
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
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
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
import org.eclipse.osgi.service.datalocation.Location;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractMaterializer extends AbstractExtension implements IMaterializer
{
	public static void performInstallActions(BillOfMaterials bom, MaterializationContext context,
			IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, bom.uniqueNodeCount() * 100);
		try
		{
			IMaterializer materializer = context.getMaterializationSpec().getMaterializer(bom.getRequest());
			materializer.installRecursive(bom, context, new HashSet<String>(), new HashSet<Resolution>(), monitor);
			IStatus status = context.getStatus();
			if(status.getSeverity() == IStatus.ERROR)
				throw new CoreException(status);
		}
		finally
		{
			monitor.done();
		}
	}

	public boolean canWorkInParallel()
	{
		// Most materializers should be able to do this.
		//
		return true;
	}

	public IPath getDefaultInstallRoot(MaterializationContext context, Resolution resolution, boolean forFile) throws CoreException
	{
		if(Platform.OS_WIN32.equals(Platform.getOS()))
		{
			File userDir = null;
			String appDataEnv = System.getenv("APPDATA");
			if(appDataEnv != null)
			{
				userDir = new File(appDataEnv + "\\buckminster");
				return Path.fromOSString(new File(userDir, getMaterializerRootDir()).toString());
			}
		}

		Location userLocation = Platform.getUserLocation();
		if(userLocation != null)
		{
			File userDir = FileUtils.getFile(userLocation.getURL());
			if(userDir != null)
			{
				if(Platform.OS_WIN32.equals(Platform.getOS()))
					userDir = new File(userDir, "Application Data\\buckminster");
				else
					userDir = new File(userDir, "buckminster");
				return Path.fromOSString(new File(userDir, getMaterializerRootDir()).toString());
			}
		}
		throw BuckminsterException.fromMessage("Unable to determine users home directory");
	}

	public abstract String getMaterializerRootDir();

	public void installRecursive(DepNode node, MaterializationContext context,
			Set<String> generated, Set<Resolution> perused, IProgressMonitor monitor) throws CoreException
	{
		if(node instanceof GeneratorNode)
		{
			GeneratorNode generatorNode = (GeneratorNode)node;
			String generates = generatorNode.getGenerates();
			if(!generated.contains(generates))
			{
				generateResolution(generatorNode, context, MonitorUtils.subMonitor(monitor, 100));
				generated.add(generates);
			}
		}
		else
		{
			Resolution resolution = node.getResolution();
			if(resolution == null || perused.contains(resolution))
				return;

			perused.add(resolution);
			for(DepNode child : node.getChildren())
				delegateAndInstallRecursive(child, context, generated, perused, monitor);

			if(!context.getMaterializationSpec().isExcluded(resolution.getComponentIdentifier()))
				performInstallAction(resolution, context, MonitorUtils.subMonitor(monitor, 100));
		}
	}

	public void performInstallAction(Resolution resolution, MaterializationContext context, IProgressMonitor monitor) throws CoreException
	{
		// The AbstractMaterializer will not perform any install actions
		//
		MonitorUtils.complete(monitor);
	}

	private void delegateAndInstallRecursive(DepNode node, MaterializationContext context,
			Set<String> generated, Set<Resolution> perused, IProgressMonitor monitor) throws CoreException
	{
		String materializerId = context.getMaterializationSpec().getMaterializerID(node.getRequest());
		IMaterializer materializer = materializerId.equals(getId()) ? this : CorePlugin.getDefault().getMaterializer(materializerId);
		((AbstractMaterializer)materializer).installRecursive(node, context, generated, perused, monitor);
	}

	private void generateResolution(GeneratorNode generatorNode, MaterializationContext context, IProgressMonitor monitor)
	{
		try
		{
			IPerformManager performManager = CorePlugin.getPerformManager();
			CSpec cspec = generatorNode.getDeclaringCSpec();
			Attribute generatorAttribute = cspec.getReferencedAttribute(
					generatorNode.getComponent(), generatorNode.getAttribute(), new ModelCache());
			performManager.perform(Collections.singletonList(generatorAttribute), context, false, monitor);
		}
		catch(CoreException e)
		{
			context.addException(e.getStatus());
		}
	}

	public static String[] getMaterializerIDs(boolean includeEmptyEntry)
	{
		IConfigurationElement[] elems = getElements();
		int idx = elems.length;
		ArrayList<String> names = new ArrayList<String>(idx+1);
		if(includeEmptyEntry)
			names.add("");
		while(--idx >= 0)
			names.add(elems[idx].getAttribute("id"));
		Collections.sort(names);
		return names.toArray(new String[names.size()]);
	}

	private static IConfigurationElement[] getElements()
	{
		IExtensionRegistry exReg = Platform.getExtensionRegistry();
		return exReg.getConfigurationElementsFor(MATERIALIZERS_POINT);
	}
}

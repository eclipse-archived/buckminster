/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.materializer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractMaterializer extends AbstractExtension implements IMaterializer
{
	public void performInstallActions(BillOfMaterials bom, Set<Resolution> excludes, RMContext context,
			IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, bom.uniqueNodeCount() * 100);
		try
		{
			HashSet<Resolution> notThese = new HashSet<Resolution>();
			if(excludes != null)
				notThese.addAll(excludes);
			HashSet<String> generated = new HashSet<String>();
			installRecursive(bom, notThese, context, generated, monitor);
		}
		finally
		{
			monitor.done();
		}
	}

	public void performInstallAction(Resolution resolution, RMContext context, IProgressMonitor monitor) throws CoreException
	{
		// The AbstractMaterializer will not perform any install actions
		//
		MonitorUtils.complete(monitor);
	}

	private void generateResolution(GeneratorNode generatorNode, RMContext context, IProgressMonitor monitor) throws CoreException
	{
		IPerformManager performManager = CorePlugin.getPerformManager();
		CSpec cspec = generatorNode.getDeclaringCSpec();
		Attribute generatorAttribute = cspec.getReferencedAttribute(
				generatorNode.getComponent(), generatorNode.getAttribute(), new ModelCache());
		performManager.perform(Collections.singletonList(generatorAttribute), context, false, monitor);
	}

	private void installRecursive(DepNode node, Set<Resolution> excludes, RMContext context,
			Set<String> generated, IProgressMonitor monitor) throws CoreException
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
			if(resolution == null || excludes.contains(resolution))
				return;

			excludes.add(resolution);
			for(DepNode child : node.getChildren())
				installRecursive(child, excludes, context, generated, monitor);
			performInstallAction(resolution, context, MonitorUtils.subMonitor(monitor, 100));
		}
	}
}

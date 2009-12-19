/*******************************************************************************
 * Copyright (c) 2009 Johannes Utzig.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Johannes Utzig - initial API and implementation
 *******************************************************************************/

package org.eclipse.buckminster.ui.dependency.visualizer.actions;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.ui.AbstractCSpecAction;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.dependency.visualizer.Activator;
import org.eclipse.buckminster.ui.dependency.visualizer.DependencyVisualizer;
import org.eclipse.buckminster.ui.dependency.visualizer.Messages;
import org.eclipse.buckminster.ui.dependency.visualizer.input.BOMEditorInput;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

/**
 * @author Johannes Utzig
 * 
 */
public class OpenDepencencyGraphAction extends AbstractCSpecAction
{

	private static final Set<String> EMPTY_SET = new HashSet<String>(0);

	@Override
	public void run(IAction action)
	{

		super.run(action);
	}

	private BOMNode buildBOM(Resolution resolution, Map<UUID, BOMNode> bomCache, SubMonitor monitor)
			throws CoreException
	{
		Collection<ComponentRequest> children = resolution.getCSpec().getDependencies();
		monitor.beginTask(Messages.OpenDepencencyGraphAction_ProcessingGraphMainTaskLabel, 100 * children.size());
		String subtask = Messages.OpenDepencencyGraphAction_ProcessingItemTaskLabel;
		monitor.subTask(MessageFormat.format(subtask, resolution.getName()));
		List<BOMNode> nodes = new ArrayList<BOMNode>(children.size());
		for(ComponentRequest componentRequest : children)
		{
			if(monitor.isCanceled())
				return new UnresolvedNode(new QualifiedDependency(componentRequest, EMPTY_SET));
			BOMNode child = null;
			try
			{
				Resolution childResolution = WorkspaceInfo.resolveLocal(componentRequest, true);
				UUID resolutionID = childResolution.getId();
				if(bomCache.containsKey(resolutionID))
				{
					child = bomCache.get(resolutionID);
				}
				else
				{
					child = buildBOM(childResolution, bomCache, monitor.newChild(100));
					bomCache.put(resolutionID, child);
				}

			}
			catch(MissingComponentException e)
			{
				child = new UnresolvedNode(new QualifiedDependency(componentRequest, EMPTY_SET));
				monitor.worked(100);
			}

			nodes.add(child);
		}
		monitor.done();
		BOMNode thisBom = new ResolvedNode(resolution, nodes);
		bomCache.put(resolution.getId(), thisBom);
		return thisBom;

	}

	@Override
	protected void run(CSpec cspec, Shell shell)
	{

		final ComponentRequest request = new ComponentRequest(cspec.getComponentIdentifier().getName(),
				cspec.getComponentIdentifier().getComponentTypeID(), null);

		Job computeResolution = new Job(Messages.OpenDepencencyGraphAction_ResolvingDependencyGraphJobTitle)
		{

			@Override
			protected IStatus run(IProgressMonitor monitor)
			{
				try
				{

					Resolution resolution = WorkspaceInfo.resolveLocal(request, true);
					SubMonitor subMonitor = SubMonitor.convert(monitor);
					BOMNode node = buildBOM(resolution, new HashMap<UUID, BOMNode>(), subMonitor);
					ComponentQueryBuilder builder = new ComponentQueryBuilder();
					builder.setRootRequest(request);
					BillOfMaterials bom = new BillOfMaterials(node, builder.createComponentQuery(), new Date());
					final BOMEditorInput input = new BOMEditorInput(bom);
					m_activePart.getSite().getShell().getDisplay().asyncExec(new Runnable()
					{

						public void run()
						{
							try
							{
								IDE.openEditor(m_activePart.getSite().getPage(), input, DependencyVisualizer.ID);
							}
							catch(PartInitException e)
							{
								UiUtils.openError(m_activePart.getSite().getShell(),
										Messages.OpenDepencencyGraphAction_OpenEditorErrorMessage, e);
							}
						}
					});

					return Status.OK_STATUS;
				}
				catch(CoreException e)
				{
					return new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getLocalizedMessage());
				}
			}
		};
		computeResolution.setUser(true);
		computeResolution.schedule();

	}

}

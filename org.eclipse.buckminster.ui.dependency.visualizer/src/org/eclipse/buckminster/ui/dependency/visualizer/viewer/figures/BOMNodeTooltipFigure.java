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
package org.eclipse.buckminster.ui.dependency.visualizer.viewer.figures;

import java.text.DateFormat;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.ui.dependency.visualizer.Messages;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.DependencyLabelProvider;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.SchemeBorder;
import org.eclipse.draw2d.SchemeBorder.SCHEMES;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionRange;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * creates a Tooltip figure for a {@link BOMNode}
 * 
 * @author Johannes Utzig
 * 
 */
@SuppressWarnings("restriction")
public class BOMNodeTooltipFigure extends RectangleFigure
{

	private BOMNode node;

	public BOMNodeTooltipFigure(BOMNode node)
	{
		this.node = node;
		setBackgroundColor(ColorConstants.tooltipBackground);
		setForegroundColor(ColorConstants.tooltipForeground);
		setLayoutManager(new BorderLayout());
		setBorder(new MarginBorder(5, 5, 5, 5));
		add(createHeadFigure(), BorderLayout.TOP);
		add(createBodyFigure(), BorderLayout.CENTER);
	}

	private IFigure createBodyFigure()
	{
		Figure figure = new Figure();
		figure.setBorder(new SchemeBorder(SCHEMES.RIDGED));
		figure.setLayoutManager(new GridLayout(2, false));
		Resolution resolution = node.getResolution();

		if(resolution != null)
		{
			Label label = new Label(Messages.Type);
			figure.add(label);
			label = new Label(resolution.getComponentTypeId());
			figure.add(label);
			if(resolution.getVersion() != null)
			{
				label = new Label(Messages.Version);
				figure.add(label);
				label = new Label(resolution.getVersionMatch().toString());
				figure.add(label);
			}

			VersionRange designator = node.getRequest().getVersionRange();
			if(designator != null)
			{
				label = new Label(Messages.VersionRange);
				figure.add(label);

				label = new Label(designator.toString());
				figure.add(label);

			}

			label = new Label(Messages.ReaderType);
			figure.add(label);
			label = new Label(node.getResolution().getReaderTypeId());
			figure.add(label);
			if(resolution.getRepository() != null)
			{
				label = new Label(Messages.Repository);
				figure.add(label);
				label = new Label(node.getResolution().getRepository());
				figure.add(label);
			}

			if(resolution.getMatchedBranchOrTag() != null)
			{
				VersionSelector selector = resolution.getMatchedBranchOrTag();
				if(selector.getType() == VersionSelector.BRANCH)
				{
					label = new Label(Messages.Branch);
				}
				else
				{
					label = new Label(Messages.Tag);
				}

				figure.add(label);
				label = new Label(resolution.getMatchedBranchOrTag().getName());
				figure.add(label);
			}

			if(resolution.getSelectedRevision() != 0l && resolution.getSelectedRevision() != -1l)
			{
				label = new Label(Messages.SelectedRevision);
				figure.add(label);
				label = new Label(Long.toString(resolution.getSelectedRevision()));
				figure.add(label);
			}
			if(resolution.getSelectedTimestamp() != null)
			{
				label = new Label(Messages.SelectedDate);
				figure.add(label);
				label = new Label(DateFormat.getInstance().format(resolution.getSelectedTimestamp()));
				figure.add(label);
			}

		}
		else
		{

			ComponentRequest request = node.getRequest();
			Label label = new Label(Messages.Type);
			figure.add(label);
			label = new Label(request.getComponentTypeID());
			figure.add(label);

			VersionRange designator = node.getRequest().getVersionRange();
			if(designator != null)
			{
				label = new Label(Messages.VersionRange);
				figure.add(label);

				label = new Label(designator.toString());
				figure.add(label);

			}

			label = new Label(Messages.UnresolvedNode);
			figure.add(label);
		}
		return figure;
	}

	private IFigure createHeadFigure()
	{
		LabelProvider provider = new DependencyLabelProvider();
		Image image = provider.getImage(node);
		String text = provider.getText(node);
		Label label = new Label(text, image);
		return label;
	}

}

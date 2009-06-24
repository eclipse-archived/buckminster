package org.eclipse.buckminster.aggregator.presentation;

import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public abstract class BuildRepoAction extends Action
{
	private boolean m_verifyOnly;

	public BuildRepoAction(boolean verifyOnly)
	{
		m_verifyOnly = verifyOnly;

		if(m_verifyOnly)
		{
			setText("Verify Repository");
		}
		else
		{
			setText("Build Repository");
			Object imageURL = AggregatorEditorPlugin.INSTANCE.getImage("full/obj16/start_task.gif");

			if(imageURL != null && imageURL instanceof URL)
				setImageDescriptor(ImageDescriptor.createFromURL((URL)imageURL));
		}
	}

	@Override
	public void run()
	{
		if(saveModel())
		{
		}
	}

	protected abstract boolean saveModel();
}

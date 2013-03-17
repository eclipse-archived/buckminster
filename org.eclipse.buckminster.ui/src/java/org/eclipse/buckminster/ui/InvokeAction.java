/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui;

import java.io.File;
import java.util.List;

import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.ui.dialogs.InvokeActionDialog;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Thomas Hallgren
 */
public class InvokeAction extends AbstractCSpecAction {

	@Override
	protected void run(CSpec cspec, Shell shell) {
		List<Attribute> viableAttributes;
		try {
			viableAttributes = cspec.getAttributesProducedByActions(true);
		} catch (CoreException e) {
			UiUtils.openError(shell, Messages.error_during_action_perform, e);
			return;
		}

		InvokeActionDialog dialog = new InvokeActionDialog(shell, Messages.actions_of + cspec.getName(), viableAttributes);

		openDialog(dialog);

	}

	@Override
	protected void run(Shell shell) {
		InvokeActionDialog dialog = new InvokeActionDialog(shell, this);
		openDialog(dialog);
	}

	private void openDialog(InvokeActionDialog dialog) {
		if (dialog.open() != Window.OK)
			return;
		File propertiesFile = dialog.getPropertiesFile();
		boolean forceRebuild = dialog.isForceRebuild();
		List<Attribute> attributes = dialog.getSelectedAttributes();
		if (attributes == null)
			return;
		InvokeActionJob job = new InvokeActionJob(attributes.get(0).getName(), attributes, propertiesFile, forceRebuild);
		job.schedule();

	}

}

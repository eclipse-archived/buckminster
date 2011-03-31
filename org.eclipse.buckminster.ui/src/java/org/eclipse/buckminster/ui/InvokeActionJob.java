package org.eclipse.buckminster.ui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;

public class InvokeActionJob extends WorkspaceJob {

	private final List<Attribute> attributes;

	private final File propertiesFile;

	private boolean forced;

	public InvokeActionJob(String name, List<Attribute> attributes, File propertiesFile, boolean forced) {
		super(name);
		setPriority(LONG);
		setUser(true);
		setSystem(false);
		this.attributes = attributes;
		this.propertiesFile = propertiesFile;
		this.forced = forced;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		IPerformManager pm = CorePlugin.getPerformManager();
		Map<String, Object> props = null;
		if (propertiesFile != null) {
			BufferedInputStream input = null;
			try {
				input = new BufferedInputStream(new FileInputStream(propertiesFile));
				props = new HashMap<String, Object>(new BMProperties(input));

				// Replace string value "*" with the special object that
				// match all entries
				//
				for (Map.Entry<String, Object> entry : props.entrySet())
					if ("*".equals(entry.getValue())) //$NON-NLS-1$
						entry.setValue(FilterUtils.MATCH_ALL_OBJ);
			} catch (IOException e) {
				return BuckminsterException.wrap(e).getStatus();
			} finally {
				IOUtils.close(input);
			}
		}
		try {
			return pm.perform(attributes, props, forced, false, monitor).getStatus();
		} catch (Throwable e) {
			final IStatus status = BuckminsterException.wrap(e).getStatus();
			CorePlugin.logWarningsAndErrors(status);
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					ErrorDialog.openError(null, Messages.action_error, null, status);
				}
			});
			return Status.OK_STATUS;
		}
	}
}
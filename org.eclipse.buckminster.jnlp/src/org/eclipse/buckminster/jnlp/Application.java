package org.eclipse.buckminster.jnlp;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication
{
	/**
	 * A WizardDialog that is not application modal.
	 * @author Thomas Hallgren
	 */
	static class ApplicationDialog extends WizardDialog
	{
		public ApplicationDialog(IWizard newWizard)
		{
			super(null, newWizard);
			setShellStyle(getShellStyle() & ~SWT.APPLICATION_MODAL);
		}
	}

	/**
	 * The wizard dialog width
	 */
	private static final int WIZARD_WIDTH = 500;

	/**
	 * The wizard dialog height
	 */
	private static final int WIZARD_HEIGHT = 500;

	public Object start(IApplicationContext context) throws Exception
	{
		// We need to create a display first thing since many mechanisms
		// depend on its presence.
		//
		Display display = Display.getDefault();

		try
		{
			// Create the wizard dialog and resize it.
			//
			ApplicationDialog dialog = new ApplicationDialog(new InstallWizard());
			dialog.create();
			Shell shell = dialog.getShell();
			shell.setSize(Math.max(WIZARD_WIDTH, shell.getSize().x), WIZARD_HEIGHT);
			dialog.open();
			return IApplication.EXIT_OK;
		}
		finally
		{
			display.dispose();
		}
	}

	public void stop()
	{
	}
}

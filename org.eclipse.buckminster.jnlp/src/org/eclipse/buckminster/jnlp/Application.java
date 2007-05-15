package org.eclipse.buckminster.jnlp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.runtime.IOUtils;
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
	public static final Integer OK_EXIT_CODE = new Integer(0);
	public static final Integer ERROR_EXIT_CODE = new Integer(1);

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
/*			
			ImageRegistry reg = JFaceResources.getImageRegistry();
			//ImageDescriptor helpImage = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor("IMGS_LCL_LINKTO_HELP");
			ImageDescriptor helpImage = ImageDescriptor.createFromImage(new Image(Display.getDefault(), "c:/tmp/help_contents.gif"));
			reg.put(Dialog.DLG_IMG_HELP, helpImage);
*/		
		}

		@Override
		public boolean isHelpAvailable()
	    {
	    	return true;	    	
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
		String arg = null;
		Object runArgs = context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
		if(runArgs instanceof String[])
		{
			String[] args = (String[])runArgs;
			for(int idx = 0; idx < args.length; ++idx)
			{
				if("-configURL".equals(args[idx]))
				{
					if(++idx < args.length)
					{
						arg = args[idx];
						if(arg != null)
						{
							arg = arg.trim();
							if(arg.length() == 0)
								arg = null;
						}
					}
					break;
				}
			}
		}

		if(arg == null)
		{
			CorePlugin.getLogger().error("Missing required argument -configURL <URL to config properties>");
			return ERROR_EXIT_CODE;
		}

		Map<String,String> properties;
		InputStream propStream = null;
		try
		{
			URL propertiesURL = new URL(arg);
			propStream = new BufferedInputStream(propertiesURL.openStream());
			properties = new BMProperties(propStream);
		}
		catch(IOException e)
		{
			CorePlugin.getLogger().error(e.getMessage(), e);
			return ERROR_EXIT_CODE;
		}
		finally
		{
			IOUtils.close(propStream);
		}

		// We need to create a display first thing since many mechanisms
		// depend on its presence.
		//
		Display display = Display.getDefault();

		try
		{
			// Create the wizard dialog and resize it.
			//
			ApplicationDialog dialog = new ApplicationDialog(new InstallWizard(properties));
			dialog.create();
			Shell shell = dialog.getShell();
			shell.setSize(WIZARD_WIDTH, WIZARD_HEIGHT);
			//shell.setSize(Math.max(WIZARD_WIDTH, shell.getSize().x), WIZARD_HEIGHT);
			dialog.open();
			return IApplication.EXIT_OK;
		}
		catch(Exception e)
		{
			CorePlugin.getLogger().error(e.getMessage(), e);
			return ERROR_EXIT_CODE;
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

/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.jnlp.ide;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.jnlp.HelpLinkErrorDialog;
import org.eclipse.buckminster.jnlp.InstallWizard;
import org.eclipse.buckminster.jnlp.JNLPException;
import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.ui.general.wizard.AdvancedWizardDialog;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.window.Window.IExceptionHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * This class controls all aspects of the materializer's execution when running 
 * in the Eclipse IDE.
 */
public class IDEApplication
{
	public static final Integer OK_EXIT_CODE = new Integer(0);

	public static final Integer ERROR_EXIT_CODE = new Integer(1);

	/**
	 * The wizard dialog width
	 */
	private static final int WIZARD_MIN_WIDTH = 550;
	private static final int WIZARD_MAX_WIDTH = 850;

	/**
	 * The wizard dialog height
	 */
	private static final int WIZARD_MIN_HEIGHT = 550;
	private static final int WIZARD_MAX_HEIGHT = 750;
		
	private String m_errorURL = ERROR_HELP_URL;

	public Object start(String configUrl) throws Exception
	{
		String errorCode = null;

		try{
			if(configUrl == null || configUrl.length() < 1)
			{
				errorCode = ERROR_CODE_MISSING_ARGUMENT_EXCEPTION;
				throw BuckminsterException.fromMessage("Missing required argument configUrl <URL to config properties>");
			}

			Map<String, String> properties = new HashMap<String, String>();
			InputStream propStream = null;
			try
			{
				URL propertiesURL = new URL(configUrl);
				propStream = new BufferedInputStream(propertiesURL.openStream());
				Map<String, String> allProperties = new BMProperties(propStream);

				// Get rid of empty properties
				for(Map.Entry<String, String> entry : allProperties.entrySet())
				{
					String value = entry.getValue();
					if(!(value == null || value.trim().length() == 0))
						properties.put(entry.getKey(), value);
				}
			}
			catch(IOException e)
			{
				errorCode = ERROR_CODE_REMOTE_IO_EXCEPTION;
				throw BuckminsterException.fromMessage(e, "Can not read materialization information");
			}
			finally
			{
				IOUtils.close(propStream);
			}

			try
			{
				// Create the wizard dialog and resize it.
				//
				final InstallWizard installWizard = new InstallWizard(properties);
				m_errorURL = installWizard.getErrorURL();
				
				AdvancedWizardDialog dialog = new AdvancedWizardDialog(installWizard, ~SWT.APPLICATION_MODAL);
				dialog.create();

				// General exception handler
				Window.setExceptionHandler(new IExceptionHandler()
				{

					public void handleException(Throwable t)
					{
						if(t instanceof ThreadDeath)
						{
							// Don't catch ThreadDeath as this is a normal occurrence when
							// the thread dies
							throw (ThreadDeath)t;
						}

						IStatus status = BuckminsterException.wrap(t.getCause() != null
								? t.getCause()
								: t).getStatus();
						CorePlugin.logWarningsAndErrors(status);

						if(t instanceof JNLPException)
						{
							JNLPException je = (JNLPException)t;

							HelpLinkErrorDialog.openError(null, installWizard.getWindowImage(), MaterializationConstants.ERROR_WINDOW_TITLE, je
									.getMessage(), MaterializationConstants.ERROR_HELP_TITLE,
									m_errorURL, je.getErrorCode(), status);
						}
						else
						{
							HelpLinkErrorDialog.openError(null, installWizard.getWindowImage(), MaterializationConstants.ERROR_WINDOW_TITLE,
									"Materializator error", MaterializationConstants.ERROR_HELP_TITLE,
									m_errorURL, ERROR_CODE_RUNTIME_EXCEPTION, status);
						}

						// Try to keep running.
					}
				});

				final Shell shell = dialog.getShell();
				shell.setSize(
						Math.min(Math.max(WIZARD_MIN_WIDTH, shell.getSize().x), WIZARD_MAX_WIDTH),
						Math.min(Math.max(WIZARD_MIN_HEIGHT, shell.getSize().y), WIZARD_MAX_HEIGHT));

				// when the shell is not started "ON TOP", it starts blinking
				shell.addShellListener(new ShellAdapter()
				{
					private int m_cnt = 0;

					@Override
					public void shellActivated(ShellEvent e)
					{
						if(m_cnt == 0)
						{
							Display.getDefault().asyncExec(new Runnable()
							{
								public void run()
								{
									shell.forceActive();
								}
							});

							m_cnt++;
						}
					}
				});

				try
				{					
					dialog.open();
					return OK_EXIT_CODE;
				}
				catch(Throwable e)
				{
					errorCode = ERROR_CODE_RUNTIME_EXCEPTION;
					final String finalErrorCode = errorCode;
					final IStatus status = BuckminsterException.wrap(e).getStatus();
					CorePlugin.logWarningsAndErrors(status);
					Display.getDefault().syncExec(new Runnable()
					{
						public void run()
						{
							HelpLinkErrorDialog.openError(null, null, MaterializationConstants.ERROR_WINDOW_TITLE,
									"Materialization wizard failed", MaterializationConstants.ERROR_HELP_TITLE,
									m_errorURL, finalErrorCode, status);
						}
					});
					return ERROR_EXIT_CODE;
				}
			}
			finally
			{
				// NOT want you want inside IDE
//				display.dispose();
			}
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			if(errorCode == null)
			{
				errorCode = ERROR_CODE_RUNTIME_EXCEPTION;
			}
			final String finalErrorCode = errorCode;
			final IStatus status = BuckminsterException.wrap(e).getStatus();
			CorePlugin.logWarningsAndErrors(status);
			Display.getDefault().syncExec(new Runnable()
			{
				public void run()
				{
					HelpLinkErrorDialog.openError(null, null, MaterializationConstants.ERROR_WINDOW_TITLE,
							"Materialization cannot be started", MaterializationConstants.ERROR_HELP_TITLE,
							m_errorURL, finalErrorCode, status);
				}
			});
			return ERROR_EXIT_CODE;
		}
	}

	public void stop()
	{
	}
}

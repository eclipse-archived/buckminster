/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_MISSING_ARGUMENT_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_REMOTE_IO_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_RUNTIME_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_HELP_URL;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.jnlp.ui.general.wizard.AdvancedWizardDialog;
import org.eclipse.buckminster.jnlp.wizard.install.InstallWizard;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.window.Window.IExceptionHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
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
	 * The wizard dialog width
	 */
	private static final int WIZARD_MIN_WIDTH = 550;

	private static final int WIZARD_MAX_WIDTH = 750;

	/**
	 * The wizard dialog height
	 */
	private static final int WIZARD_MIN_HEIGHT = 550;

	private static final int WIZARD_MAX_HEIGHT = 750;

	private static final long DEFAULT_POPUP_DELAY = 500;

	/**
	 * String for synchronization with the bootstrap
	 */
	private String m_syncString = null;

	private String m_errorURL = ERROR_HELP_URL;

	public Object start(IApplicationContext context) throws Exception
	{
		Object runArgs = context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
		String errorCode = null;

		try
		{
			String configUrl = null;
			Long popupAfter = null;

			if(runArgs instanceof String[])
			{
				String[] args = (String[])runArgs;
				for(int idx = 0; idx < args.length; ++idx)
				{
					String arg = args[idx];

					if("-configURL".equals(arg)) //$NON-NLS-1$
					{
						if(++idx < args.length)
						{
							configUrl = args[idx];
							if(configUrl != null)
							{
								configUrl = configUrl.trim();
								if(configUrl.length() == 0)
									configUrl = null;
							}
						}
					}
					else if("-syncString".equals(arg)) //$NON-NLS-1$
					{
						if(++idx < args.length)
						{
							m_syncString = args[idx];
							if(m_syncString != null)
							{
								m_syncString = m_syncString.trim();
								if(m_syncString.length() == 0)
									m_syncString = null;
							}
						}
					}
					else if("-popupAfter".equals(arg)) //$NON-NLS-1$
					{
						if(++idx < args.length)
						{
							try
							{
								popupAfter = Long.valueOf(args[idx]);
							}
							catch(NumberFormatException e)
							{
								// popupAfter remains null
							}
						}
					}
					else if(arg.startsWith("-") //$NON-NLS-1$
							&& (arg.endsWith(".proxyHost") || arg.endsWith(".proxyPort") || arg //$NON-NLS-1$ //$NON-NLS-2$
									.endsWith(".nonProxyHosts"))) //$NON-NLS-1$
					{
						if(++idx < args.length)
							System.setProperty(arg.substring(1), args[idx]);
					}
				}
			}

			// We need to create a display first thing since many mechanisms
			// depend on its presence.
			//
			Display.setAppName("Materializer"); //$NON-NLS-1$
			Display display = Display.getDefault();

			HelpLinkErrorDialog.setSyncString(m_syncString);

			if(!Platform.getInstanceLocation().lock())
			{
				errorCode = MaterializationConstants.ERROR_CODE_ALREADY_RUNNING_EXCEPTION;
				throw BuckminsterException.fromMessage(Messages.materializer_is_already_running);
			}

			BuckminsterPreferences.setLogLevelConsole(Logger.SILENT);
			BuckminsterPreferences.setLogLevelEclipseLogger(Logger.DEBUG);

			if(configUrl == null)
			{
				errorCode = ERROR_CODE_MISSING_ARGUMENT_EXCEPTION;
				throw BuckminsterException
						.fromMessage(Messages.missing_required_argument_configURL_URL_to_config_properties);
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
				throw BuckminsterException.fromMessage(e, Messages.can_not_read_materialization_information);
			}
			finally
			{
				IOUtils.close(propStream);
			}

			m_errorURL = properties.get(MaterializationConstants.PROP_ERROR_URL);
			if(m_errorURL == null)
				m_errorURL = MaterializationConstants.ERROR_HELP_URL;

			String errorMessage = properties.get(MaterializationConstants.PROP_ERROR_MESSAGE);

			if(errorMessage != null)
			{
				errorCode = MaterializationConstants.ERROR_CODE_404_EXCEPTION;
				throw BuckminsterException
						.fromMessage(new String(Base64.decodeBase64(errorMessage.getBytes()), "UTF-8")); //$NON-NLS-1$
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

							HelpLinkErrorDialog.openError(null, installWizard.getWindowImage(),
									MaterializationConstants.ERROR_WINDOW_TITLE, je.getMessage(),
									MaterializationConstants.ERROR_HELP_TITLE, m_errorURL, je.getErrorCode(), status);
						}
						else
						{
							HelpLinkErrorDialog.openError(null, installWizard.getWindowImage(),
									MaterializationConstants.ERROR_WINDOW_TITLE, Messages.materializator_error,
									MaterializationConstants.ERROR_HELP_TITLE, m_errorURL,
									ERROR_CODE_RUNTIME_EXCEPTION, status);
						}

						// Try to keep running.
					}
				});

				final Shell shell = dialog.getShell();
				shell.setSize(Math.min(Math.max(WIZARD_MIN_WIDTH, shell.getSize().x), WIZARD_MAX_WIDTH), Math.min(Math
						.max(WIZARD_MIN_HEIGHT, shell.getSize().y), WIZARD_MAX_HEIGHT));

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
					if(popupAfter != null)
					{
						long popupDelay = popupAfter.longValue() - (new Date()).getTime();
						if(popupDelay > 0)
							Thread.sleep(popupDelay);
					}

					synchronizeWithBootstrap();

					long popupDelay = DEFAULT_POPUP_DELAY;
					String popupDelayString = properties.get(MaterializationConstants.PROP_POPUP_DELAY);

					if(popupDelayString != null)
					{
						try
						{
							popupDelay = new Long(popupDelayString).longValue();
						}
						catch(Throwable e)
						{
							popupDelay = DEFAULT_POPUP_DELAY;
						}
					}

					// need to wait a while until applet finishes
					Thread.sleep(popupDelay);

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
									Messages.materialization_wizard_failed, MaterializationConstants.ERROR_HELP_TITLE,
									m_errorURL, finalErrorCode, status);
						}
					});
					return ERROR_EXIT_CODE;
				}
			}
			finally
			{
				display.dispose();
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
							Messages.materialization_cannot_be_started, MaterializationConstants.ERROR_HELP_TITLE,
							m_errorURL, finalErrorCode, status);
				}
			});
			return ERROR_EXIT_CODE;
		}
	}

	public void stop()
	{
	}

	private void synchronizeWithBootstrap()
	{
		if(m_syncString != null)
			System.out.println(m_syncString);
	}
}

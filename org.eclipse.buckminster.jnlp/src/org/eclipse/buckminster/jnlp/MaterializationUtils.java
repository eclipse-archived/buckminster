/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;
import org.eclipse.buckminster.jnlp.accountservice.IAuthenticator;
import org.eclipse.buckminster.jnlp.ui.general.wizard.AdvancedWizardDialog;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Karel Brezina
 *
 */
public class MaterializationUtils
{
	/**
	 * The publishing wizard dialog width
	 */
	private static final int PUBLISH_WIZARD_WIDTH = 450;

	/**
	 * The publishing wizard dialog height
	 */
	private static final int PUBLISH_WIZARD_HEIGHT = 500;

	private static final Map<String,String> s_humanReadableComponentTypes;
	
	// needs to be synchronized with the server side, new component types need to be added
	static
	{
		s_humanReadableComponentTypes = new HashMap<String,String>();
		s_humanReadableComponentTypes.put("unknown", "None");
		s_humanReadableComponentTypes.put("cssite", "Cloudsmith");
		s_humanReadableComponentTypes.put("osgi.bundle", "OSGi Bundle");
		s_humanReadableComponentTypes.put("eclipse.feature", "Eclipse Feature");
		s_humanReadableComponentTypes.put("maven", "Maven");
		s_humanReadableComponentTypes.put("site.feature", "Eclipse Update Manager");
		s_humanReadableComponentTypes.put("buckminster", "Buckminster");
		s_humanReadableComponentTypes.put("jar", "Java Archive");
		s_humanReadableComponentTypes.put("bom", "Bill of Materials");
	}

	/**
	 * Checks HTTP response code and throws JNLPException if there is a problem
	 * 
	 * @param connection
	 * @throws JNLPException
	 * @throws IOException
	 */
	public static void checkConnection(int status, String originalURL) throws JNLPException, IOException
	{
		if(status != HttpStatus.SC_OK)
		{
			String errorCode;

			switch(status)
			{
			case HttpStatus.SC_FORBIDDEN:

				errorCode = ERROR_CODE_403_EXCEPTION;
				break;

			case HttpStatus.SC_NOT_FOUND:

				errorCode = ERROR_CODE_404_EXCEPTION;
				break;

			case HttpStatus.SC_INTERNAL_SERVER_ERROR:

				errorCode = ERROR_CODE_500_EXCEPTION;
				break;

			default:
				errorCode = ERROR_CODE_REMOTE_IO_EXCEPTION;
				break;
			}

			throw new JNLPException("Cannot read materialization specification", errorCode, new BuckminsterException(
					originalURL + " - " + HttpStatus.getStatusText(status)));

		}
	}
	
	/**
	 * Checks response of IAuthenticator.register method
	 * 
	 * @param result result of IAuthenticator.register method
	 * @throws JNLPException
	 */
	
	public static void checkRegistrationResponse(int result) throws JNLPException
	{
		switch(result)
		{
		case IAuthenticator.REGISTER_FAIL:
			throw new JNLPException("Registration was not successful", null);
		case IAuthenticator.REGISTER_LOGIN_EXISTS:
			throw new JNLPException("Login name already exists - choose a different one", null);
		case IAuthenticator.REGISTER_LOGIN_TOO_SHORT:
			throw new JNLPException("Login is too short - length must be between 3 and 25", null);
		case IAuthenticator.REGISTER_LOGIN_CONTAINS_AT:
			throw new JNLPException("Login name contains '@'", null);
		case IAuthenticator.REGISTER_LOGIN_INVALID:
			throw new JNLPException("Login name is invalid", null);
		case IAuthenticator.REGISTER_PASSWORD_TOO_SHORT:
			throw new JNLPException("Password is too short - length must be between 4 and 25", null);
		case IAuthenticator.REGISTER_EMAIL_FORMAT_ERROR:
			throw new JNLPException("Email does not have standard format", null);
		case IAuthenticator.REGISTER_EMAIL_ALREADY_VALIDATED:
			throw new JNLPException("Email is already verified for another user", null);	
		}
	}
	
	/**
	 * Gets human readable component type
	 * 
	 * @param componentType componentType ID
	 * @return human readable component type
	 */
	public static String getHumanReadableComponentType(String componentType)
	{
		String hrType = s_humanReadableComponentTypes.get(componentType);
		if(hrType == null)
			hrType = componentType;
		return hrType;
	}
	
	/**
	 * Opens publishing wizard dialog
	 * 
	 * @param installWizard
	 * @param parentShell
	 */
	public static void startPublishingWizard(InstallWizard installWizard, Shell parentShell)
	{
		PublishWizard publishWizard = new PublishWizard(installWizard);
		
		AdvancedWizardDialog dialog = new AdvancedWizardDialog(parentShell, publishWizard);
		dialog.create();
		
		final Shell shell = dialog.getShell();
		shell.setSize(Math.max(PUBLISH_WIZARD_WIDTH, shell.getSize().x), Math.max(PUBLISH_WIZARD_HEIGHT, shell.getSize().y));
		
		dialog.open();
	}
}

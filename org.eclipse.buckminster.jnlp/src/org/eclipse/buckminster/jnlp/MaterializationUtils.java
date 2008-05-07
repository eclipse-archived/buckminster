/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpStatus;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.jnlp.accountservice.IAuthenticator;
import org.eclipse.buckminster.jnlp.ui.general.wizard.AdvancedWizardDialog;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Karel Brezina
 *
 */
public class MaterializationUtils
{
	static class PropertyEntryByLength implements Comparable<PropertyEntryByLength>
	{
		private String m_key;
		
		private String m_value;

		public PropertyEntryByLength(String key, String value)
		{
			m_key = key;
			m_value = value;
		}
				
		public String getKey()
		{
			return m_key;
		}

		public String getValue()
		{
			return m_value;
		}

		public int compareTo(PropertyEntryByLength o)
		{
			int result = o.getKey().length() - m_key.length();
			
			if(result != 0)
				return result;
			
			return m_key.compareTo(o.getKey());
		}	
	}
	
	private static Map<MaterializationSpecBuilder, Map<String, String>> m_expandProperties =
		new HashMap<MaterializationSpecBuilder, Map<String, String>>();
	
	private static Map<MaterializationSpecBuilder, Set<PropertyEntryByLength>> m_generalizeProperties =
		new HashMap<MaterializationSpecBuilder, Set<PropertyEntryByLength>>();
	
	/**
	 * The publishing wizard dialog width
	 */
	private static final int PUBLISH_WIZARD_MIN_WIDTH = 450;
	private static final int PUBLISH_WIZARD_MAX_WIDTH = 850;

	/**
	 * The publishing wizard dialog height
	 */
	private static final int PUBLISH_WIZARD_MIN_HEIGHT = 450;
	private static final int PUBLISH_WIZARD_MAX_HEIGHT = 750;

	private static final Map<String,String> s_humanReadableComponentTypes;
	
	// needs to be synchronized with the server side, new component types need to be added
	static
	{
		s_humanReadableComponentTypes = new HashMap<String,String>();
		s_humanReadableComponentTypes.put(null, "Any");
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

			throw new JNLPException("Cannot read materialization specification", errorCode, BuckminsterException.fromMessage(
					"%s - %s", originalURL, HttpStatus.getStatusText(status)));
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
		shell.setSize(
				Math.min(Math.max(PUBLISH_WIZARD_MIN_WIDTH, shell.getSize().x), PUBLISH_WIZARD_MAX_WIDTH),
				Math.min(Math.max(PUBLISH_WIZARD_MIN_HEIGHT, shell.getSize().y), PUBLISH_WIZARD_MAX_HEIGHT));
		
		dialog.open();
	}

	/**
	 * Expands <code>IPath</code> that contains properties
	 * 
	 * @param mspec
	 * @param installLocation
	 * @return
	 */
	public static IPath expandPath(MaterializationSpecBuilder mspec, IPath installLocation)
	{
		Map<String, String> properties = m_expandProperties.get(mspec);
		
		if(properties == null)
		{
			properties = new RMContext(mspec.getProperties());
			m_expandProperties.put(mspec, properties);
		}
		
		return Path.fromOSString(ExpandingProperties.expand(properties, installLocation.toOSString(), 0));
	}

	/**
	 * Generalize <code>IPath</code>
	 * 
	 * @param mspec
	 * @param installLocation
	 * @return
	 */
	public static IPath generalizePath(MaterializationSpecBuilder mspec, IPath installLocation)
	{
		if(installLocation == null)
			return null;
		
		Set<PropertyEntryByLength> properties = m_generalizeProperties.get(mspec);
		
		if(properties == null)
		{
			properties = new TreeSet<PropertyEntryByLength>();
			RMContext context = new RMContext(mspec.getProperties());
			
			for(String key : context.keySet())
			{
				String value = context.get(key);
				
				if(value == null || value.length() == 0)
					continue;
				
				// unifying file separators
				String unifiedValue = new Path(value).removeTrailingSeparator().toString();

				// changing meaning - key is value, value is key
				properties.add(new PropertyEntryByLength(unifiedValue, key));				
			}
			
			m_generalizeProperties.put(mspec, properties);
		}

		String pathToGeneralize = installLocation.addTrailingSeparator().toString();
		int len = pathToGeneralize.length();
		
		for(PropertyEntryByLength entry: properties)
		{
			String key = entry.getKey();
			if(key.length() > len)
				continue;
			
			Pattern pattern = Pattern.compile("(^|/)(" + Pattern.quote(key) + ")/");
			Matcher matcher = pattern.matcher(pathToGeneralize);
			pathToGeneralize = matcher.replaceAll("$1\\${" + entry.getValue() + "}/");
		}
		
		return new Path(pathToGeneralize);
	}
	
	/**
	 * Gets default install location
	 * 
	 * @param artifactName
	 * @return
	 * @throws JNLPException
	 */
	public static String getDefaultDestination(String artifactName) throws JNLPException
	{
		String destination = null;
		
		String userHome = System.getProperty("user.home");

		if(userHome != null)
		{
			destination = userHome + File.separator + DEFAULT_MATERIALIZATION_FOLDER;
		
			if(artifactName != null)
				destination += File.separator + artifactName;
		}
		return destination;
	}
	
	public static Image getImage(String imageName)
	{
		Class<?> myClass = MaterializationUtils.class;
		String imageResource = "/icons/" + imageName;
		URL imageUrl = myClass.getResource(imageResource);
		return ImageDescriptor.createFromURL(imageUrl).createImage();
	}
}

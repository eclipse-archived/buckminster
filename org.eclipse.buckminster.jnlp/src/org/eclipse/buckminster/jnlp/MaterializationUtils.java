/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.DEFAULT_MATERIALIZATION_FOLDER;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_403_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_404_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_500_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_ARTIFACT_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_FILE_IO_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_REMOTE_IO_EXCEPTION;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.metadata.IResolution;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.jnlp.distroprovider.IRemoteDistroProvider;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.xml.sax.SAXException;

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

		public int compareTo(PropertyEntryByLength o)
		{
			int result = o.getKey().length() - m_key.length();

			if(result != 0)
				return result;

			return m_key.compareTo(o.getKey());
		}

		public String getKey()
		{
			return m_key;
		}

		public String getValue()
		{
			return m_value;
		}
	}

	private static Map<MaterializationSpecBuilder, Map<String, ? extends Object>> m_expandProperties = new HashMap<MaterializationSpecBuilder, Map<String, ? extends Object>>();

	private static Map<MaterializationSpecBuilder, Set<PropertyEntryByLength>> m_generalizeProperties = new HashMap<MaterializationSpecBuilder, Set<PropertyEntryByLength>>();

	private static final Map<String, String> s_humanReadableComponentTypes;

	// needs to be synchronized with the server side, new component types need to be added
	static
	{
		s_humanReadableComponentTypes = new HashMap<String, String>();
		s_humanReadableComponentTypes.put(null, "Any"); //$NON-NLS-1$
		s_humanReadableComponentTypes.put("unknown", "None"); //$NON-NLS-1$ //$NON-NLS-2$
		s_humanReadableComponentTypes.put("cssite", "Cloudsmith"); //$NON-NLS-1$ //$NON-NLS-2$
		s_humanReadableComponentTypes.put("osgi.bundle", "OSGi Bundle"); //$NON-NLS-1$ //$NON-NLS-2$
		s_humanReadableComponentTypes.put("eclipse.feature", "Eclipse Feature"); //$NON-NLS-1$ //$NON-NLS-2$
		s_humanReadableComponentTypes.put("maven", "Maven"); //$NON-NLS-1$ //$NON-NLS-2$
		s_humanReadableComponentTypes.put("site.feature", "Eclipse Update Manager"); //$NON-NLS-1$ //$NON-NLS-2$
		s_humanReadableComponentTypes.put("buckminster", "Buckminster"); //$NON-NLS-1$ //$NON-NLS-2$
		s_humanReadableComponentTypes.put("jar", "Java Archive"); //$NON-NLS-1$ //$NON-NLS-2$
		s_humanReadableComponentTypes.put("bom", "Bill of Materials"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Checks HTTP response code and throws JNLPException if there is a problem
	 * 
	 * @param connection
	 * @throws JNLPException
	 * @throws IOException
	 */
	public static void checkConnection(int status, String originalURL) throws JNLPException
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

			throw new JNLPException(Messages.cannot_read_materialization_specification, errorCode, BuckminsterException
					.fromMessage("%s - %s", originalURL, HttpStatus.getStatusText(status))); //$NON-NLS-1$
		}
	}

	/**
	 * Checks response of IAuthenticator.register method
	 * 
	 * @param result
	 *            result of IAuthenticator.register method
	 * @throws JNLPException
	 */

	public static void checkRegistrationResponse(int result) throws JNLPException
	{
		switch(result)
		{
		case IRemoteDistroProvider.REGISTER_FAIL:
			throw new JNLPException(Messages.registration_was_not_successful, null);
		case IRemoteDistroProvider.REGISTER_LOGIN_EXISTS:
			throw new JNLPException(Messages.login_name_already_exists_choose_a_different_one, null);
		case IRemoteDistroProvider.REGISTER_LOGIN_TOO_SHORT:
			throw new JNLPException(Messages.login_is_too_short_length_must_be_between_3_and_25, null);
		case IRemoteDistroProvider.REGISTER_LOGIN_CONTAINS_AT:
			throw new JNLPException(Messages.login_name_contains_at, null);
		case IRemoteDistroProvider.REGISTER_LOGIN_INVALID:
			throw new JNLPException(Messages.login_name_is_invalid, null);
		case IRemoteDistroProvider.REGISTER_PASSWORD_TOO_SHORT:
			throw new JNLPException(Messages.password_is_too_short_length_must_be_between_4_and_25, null);
		case IRemoteDistroProvider.REGISTER_EMAIL_FORMAT_ERROR:
			throw new JNLPException(Messages.email_does_not_have_standard_format, null);
		case IRemoteDistroProvider.REGISTER_EMAIL_ALREADY_VALIDATED:
			throw new JNLPException(Messages.email_is_already_verified_for_another_user, null);
		}
	}

	/**
	 * Excludes CSSite components
	 * 
	 * @param mspec
	 * @param depNode
	 * @throws CoreException
	 */
	public static void excludeCSsiteComponents(MaterializationSpecBuilder mspec, BOMNode depNode) throws CoreException
	{
		if(hasCSsiteReader(depNode))
			excludeComponent(mspec, depNode);

		for(BOMNode childDepNode : depNode.getChildren())
			excludeCSsiteComponents(mspec, childDepNode);
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
		Map<String, ? extends Object> properties = m_expandProperties.get(mspec);

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
				String value = (String)context.get(key);

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

		for(PropertyEntryByLength entry : properties)
		{
			String key = entry.getKey();
			if(key.length() > len)
				continue;

			Pattern pattern = Pattern.compile("(^|/)(" + Pattern.quote(key) + ")/"); //$NON-NLS-1$ //$NON-NLS-2$
			Matcher matcher = pattern.matcher(pathToGeneralize);
			pathToGeneralize = matcher.replaceAll("$1\\${" + entry.getValue() + "}/"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		return new Path(pathToGeneralize);
	}

	public static File getBackupFolder(File eclipseFolder)
	{
		String backupString = eclipseFolder.getPath() + ".backup"; //$NON-NLS-1$
		File backupFile = new File(backupString);

		int i = 0;
		while(backupFile.exists())
		{
			backupFile = new File(backupString + String.format(".%d", Integer.valueOf(i++))); //$NON-NLS-1$
		}

		return backupFile;
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

		String userHome = System.getProperty("user.home"); //$NON-NLS-1$

		if(userHome != null)
		{
			destination = userHome + File.separator + DEFAULT_MATERIALIZATION_FOLDER;

			if(artifactName != null)
				destination += File.separator + artifactName;
		}
		return destination;
	}

	/**
	 * Gets human readable component type
	 * 
	 * @param componentType
	 *            componentType ID
	 * @return human readable component type
	 */
	public static String getHumanReadableComponentType(String componentType)
	{
		String hrType = s_humanReadableComponentTypes.get(componentType);
		if(hrType == null)
			hrType = componentType;
		return hrType;
	}

	public static Image getImage(String imageName)
	{
		Class<?> myClass = MaterializationUtils.class;
		String imageResource = "/icons/" + imageName; //$NON-NLS-1$
		URL imageUrl = myClass.getResource(imageResource);
		return ImageDescriptor.createFromURL(imageUrl).createImage();
	}

	/**
	 * From a given path computes a path that exists in the file system.
	 * 
	 * @param enteredPath
	 *            entered path
	 * @return path that exists in the file system
	 */
	public static String getKnownPath(String enteredPath)
	{
		IPath path = new Path(enteredPath);

		File file = null;
		String pathString = null;
		do
		{
			// second and other runs - remove last segment
			if(file != null)
				path = path.removeLastSegments(1);

			pathString = path.removeTrailingSeparator().toOSString();
			file = new File(pathString);
		} while(!file.exists() && pathString.length() > 0);

		if(!file.isDirectory())
			return null;

		return pathString;
	}

	public static void saveBOM(BillOfMaterials bom, File file)
	{
		OutputStream os = null;

		try
		{
			os = new FileOutputStream(file);
			Utils.serialize(bom, os);
		}
		catch(FileNotFoundException e1)
		{
			throw new JNLPException(Messages.file_cannot_be_opened_or_created, ERROR_CODE_FILE_IO_EXCEPTION, e1);
		}
		catch(SAXException e1)
		{
			throw new JNLPException(Messages.unable_to_read_BOM_specification, ERROR_CODE_ARTIFACT_EXCEPTION, e1);
		}
		finally
		{
			IOUtils.close(os);
		}
	}

	private static void excludeComponent(MaterializationSpecBuilder mspec, BOMNode depNode) throws CoreException
	{
		IResolution resolution = depNode.getResolution();

		if(resolution != null)
		{
			ICSpecData cspec = resolution.getCSpec();

			if(cspec != null)
			{
				String componentName = cspec.getName();
				String componentType = cspec.getComponentTypeID();

				for(MaterializationNodeBuilder builder : mspec.getNodeBuilders())
					if((componentType == null || componentType.equals(builder.getComponentTypeID()))
							&& builder.getNamePattern().matcher(componentName).matches())
						builder.setExclude(true);

				MaterializationNodeBuilder nodeBuilder = mspec.addNodeBuilder();
				nodeBuilder.setNamePattern(Pattern.compile("^\\Q" + componentName + "\\E$")); //$NON-NLS-1$ //$NON-NLS-2$
				nodeBuilder.setComponentTypeID(componentType);
				nodeBuilder.setExclude(true);
			}
		}
	}

	private static boolean hasCSsiteReader(BOMNode depNode) throws CoreException
	{
		IResolution resolution = depNode.getResolution();

		if(resolution != null)
			if(MaterializationConstants.READER_TYPE_CSSITE.equals(resolution.getProvider().getReaderTypeId()))
				return true;

		return false;
	}
}

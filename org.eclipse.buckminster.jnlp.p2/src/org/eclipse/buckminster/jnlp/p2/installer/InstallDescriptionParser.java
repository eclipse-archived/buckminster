/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Code 9 - ongoing development
 *     Cloudsmith - ongoing development
 *******************************************************************************/
package org.eclipse.buckminster.jnlp.p2.installer;

import static org.eclipse.buckminster.jnlp.p2.installer.P2PropertyKeys.*;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import org.eclipse.buckminster.jnlp.p2.JNLPPlugin;
import org.eclipse.core.runtime.*;
import org.eclipse.equinox.internal.p2.core.helpers.LogHelper;

/**
 * This class is responsible for loading install descriptions from a stream.
 */
@SuppressWarnings("restriction")
public class InstallDescriptionParser
{
	/**
	 * Loads and returns an install description that is stored in a properties file.
	 * 
	 * @param site
	 *            The URL of the install properties file.
	 */
	public static InstallDescription createDescription(String site, SubMonitor monitor) throws IOException
	{
		// if no description URL was given from the outside, look for an "install.properties" file
		// in relative to where the installer is running. This allows the installer to be self-contained
		InputStream in = null;
		if(site == null)
		{
			File file = new File("installer.properties").getAbsoluteFile(); //$NON-NLS-1$
			if(file.exists())
				in = new FileInputStream(file);
		}
		else
			in = new URL(site).openStream();

		Properties properties = new Properties();
		try
		{
			if(in != null)
				properties.load(in);
		}
		finally
		{
			safeClose(in);
		}
		
		return createDescription(properties);
	}

	public static InstallDescription createDescription(Properties properties)
	{
		InstallDescription result = new InstallDescription();
		result = initialize(result, properties);
		initializeProfileProperties(result, properties);

		// now override the properties from anything interesting in system properties
		result = initialize(result, System.getProperties());
		return result;
	}
	
	private static InstallDescription initialize(InstallDescription description, Properties properties)
	{
		String property = properties.getProperty(PROP_ARTIFACT_REPOSITORY);
		if(property != null)
			description.setArtifactRepositories(getURIs(property));

		property = properties.getProperty(PROP_METADATA_REPOSITORY);
		if(property != null)
			description.setMetadataRepositories(getURIs(property));

		property = properties.getProperty(PROP_IS_AUTO_START);
		if(property != null)
			description.setAutoStart(Boolean.TRUE.toString().equalsIgnoreCase(property));

		property = properties.getProperty(PROP_LAUNCHER_NAME);
		if(property != null)
			description.setLauncherName(property);

		property = properties.getProperty(PROP_INSTALL_LOCATION);
		if(property != null)
			description.setInstallLocation(new Path(property));

		property = properties.getProperty(PROP_AGENT_LOCATION);
		if(property != null)
			description.setAgentLocation(new Path(property));

		property = properties.getProperty(PROP_BUNDLE_LOCATION);
		if(property != null)
			description.setBundleLocation(new Path(property));

		property = properties.getProperty(PROP_PROFILE_NAME);
		if(property != null)
			description.setProductName(property);

		// Process the retro root id and rootVersion properties
		String id = properties.getProperty(PROP_ROOT_ID);
		if(id != null)
		{
			String version = properties.getProperty(PROP_ROOT_VERSION);
			description.setRoots(new VersionedName[] { new VersionedName(id, version) });
		}

		String rootSpec = properties.getProperty(PROP_ROOTS);
		if(rootSpec != null)
		{
			String[] rootList = getArrayFromString(rootSpec, ",");
			VersionedName[] roots = new VersionedName[rootList.length];
			for(int i = 0; i < rootList.length; i++)
				roots[i] = VersionedName.parse(rootList[i]);
			description.setRoots(roots);
		}
		return description;
	}

	/**
	 * Add all of the given properties to profile properties of the given description after removing the keys known to
	 * be for the installer. This allows install descriptions to also set random profile properties.
	 * 
	 * @param description
	 * @param properties
	 */
	private static void initializeProfileProperties(InstallDescription description, Properties properties)
	{
		// any remaining properties are profile properties
		Map<Object, Object> profileProperties = new HashMap<Object, Object>(properties);
		profileProperties.remove(PROP_PROFILE_NAME);
		profileProperties.remove(PROP_ARTIFACT_REPOSITORY);
		profileProperties.remove(PROP_METADATA_REPOSITORY);
		profileProperties.remove(PROP_IS_AUTO_START);
		profileProperties.remove(PROP_LAUNCHER_NAME);
		profileProperties.remove(PROP_AGENT_LOCATION);
		profileProperties.remove(PROP_BUNDLE_LOCATION);
		profileProperties.remove(PROP_ROOT_ID);
		profileProperties.remove(PROP_ROOT_VERSION);
		profileProperties.remove(PROP_ROOTS);
		description.setProfileProperties(profileProperties);
	}

	private static URI[] getURIs(String spec)
	{
		if(spec == null)
			return null;
		String[] urlSpecs = getArrayFromString(spec, ","); //$NON-NLS-1$
		ArrayList<URI> result = new ArrayList<URI>(urlSpecs.length);
		for(int i = 0; i < urlSpecs.length; i++)
		{
			try
			{
				result.add(new URI(urlSpecs[i]));
			}
			catch(URISyntaxException e)
			{
				LogHelper.log(new Status(IStatus.ERROR, JNLPPlugin.JNLP_P2,
						"Invalid URL in install description: " + urlSpecs[i], e)); //$NON-NLS-1$
			}
		}
		return result.toArray(new URI[result.size()]);
	}

	private static void safeClose(InputStream in)
	{
		try
		{
			if(in != null)
				in.close();
		}
		catch(IOException e)
		{
			// ignore secondary failure during close
		}
	}

	/**
	 * Convert a list of tokens into an array. The list separator has to be specified.
	 */
	public static String[] getArrayFromString(String list, String separator)
	{
		if(list == null || list.trim().equals("")) //$NON-NLS-1$
			return new String[0];
		List<String> result = new ArrayList<String>();
		for(StringTokenizer tokens = new StringTokenizer(list, separator); tokens.hasMoreTokens();)
		{
			String token = tokens.nextToken().trim();
			if(!token.equals("")) //$NON-NLS-1$
				result.add(token);
		}
		return result.toArray(new String[result.size()]);
	}

}

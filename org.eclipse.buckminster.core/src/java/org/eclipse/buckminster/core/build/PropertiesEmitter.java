/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.build;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Format;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * An Abstract Builder that emits the properties to some location.
 * 
 * @author Thomas Hallgren
 */
public abstract class PropertiesEmitter extends AbstractBuckminsterBuilder implements IResourceChangeListener
{
	/**
	 * The path, relative to the project root, of the properties file.
	 */
	public static final String ARG_FILE = "file"; //$NON-NLS-1$

	/**
	 * File separator. Defaults to setting of system property &quot;file.separator&quot;
	 */
	public static final String ARG_FILE_SEPARATOR = "file.separator"; //$NON-NLS-1$

	/**
	 * A boolean argument denoting wether or not the already present properties should be retained (truncate == false)
	 * or discarded (truncate == true).
	 */
	public static final String ARG_TRUNCATE = "truncate"; //$NON-NLS-1$

	public static final String DEFAULT_PROPERTY_FILE = "buckminster.properties"; //$NON-NLS-1$

	private Map<String, String> m_arguments;

	private HashMap<String, Format> m_formatters;

	private Map<String, String> m_properties;

	private IFile m_propertyFile;

	/**
	 * Add a format that can be used when creating the keys in the emitted properties. Before adding the
	 * <code>defaultFormat</code>, a check is made if an alternative format has been provided in the <code>args</code>
	 * map that was supplied to the <b>build</b> method.
	 * 
	 * @param argKey
	 *            The key to use when obtaining the formatter.
	 * @param defaultFormat
	 *            The default formatter.
	 */
	protected void addFormat(String argKey, Format defaultFormat)
	{
		String arg = this.getArgument(argKey);
		m_formatters.put(argKey, (arg == null)
				? defaultFormat
				: new MessageFormat(arg));
	}

	/**
	 * Subclasses should implement this method to supply formatters for the key of each of the properties that it intend
	 * to emit.
	 * 
	 * @see #addFormat
	 */
	protected abstract void addFormatters();

	/**
	 * Add a property to the set of properties that will be emitted. The formatKey must correspond to a previously added
	 * formatter.
	 * 
	 * @param formatKey
	 * @param keyArgs
	 * @param value
	 * @see #addFormat(String, Format)
	 * @see #addFormatters()
	 */
	protected void addProperty(String formatKey, String[] keyArgs, String value)
	{
		m_properties.put(m_formatters.get(formatKey).format(keyArgs), value);
	}

	/**
	 * Subclasses should implement this method to add the properties that it wants to emit.
	 * 
	 * @throws CoreException
	 */
	protected abstract void appendProperties() throws CoreException;

	@Override
	protected IProject[] doAutoBuild(Map<String, String> args, IProgressMonitor monitor) throws CoreException
	{
		return this.doFullBuild(args, monitor);
	}

	@Override
	protected IProject[] doCleanBuild(Map<String, String> args, IProgressMonitor monitor) throws CoreException
	{
		if(m_propertyFile != null)
		{
			monitor.beginTask(Messages.Deleting + m_propertyFile, 100);
			m_propertyFile.refreshLocal(IResource.DEPTH_ZERO, MonitorUtils.subMonitor(monitor, 50));
			if(m_propertyFile.exists())
				m_propertyFile.delete(false, false, MonitorUtils.subMonitor(monitor, 50));
			monitor.done();
		}
		return null;
	}

	@Override
	protected IProject[] doFullBuild(Map<String, String> args, IProgressMonitor monitor) throws CoreException
	{
		m_arguments = args;
		m_formatters = new HashMap<String, Format>();

		String propertyFileName = this.getArgument(ARG_FILE);
		if(propertyFileName == null)
			propertyFileName = DEFAULT_PROPERTY_FILE;

		m_propertyFile = this.getProject().getFile(propertyFileName);

		boolean truncate = Boolean.parseBoolean(this.getArgument(ARG_TRUNCATE));
		int ticks = 5;
		if(!truncate)
			ticks += 2;

		monitor.beginTask(null, ticks);
		monitor.subTask(Messages.Emitting_properties);

		m_propertyFile.refreshLocal(IResource.DEPTH_ZERO, MonitorUtils.subMonitor(monitor, 1));
		Map<String, String> oldProps = null;

		if(m_propertyFile.exists())
		{
			InputStream recentProperties = null;
			try
			{
				recentProperties = new BufferedInputStream(m_propertyFile.getContents());
				oldProps = new BMProperties(recentProperties);
				MonitorUtils.worked(monitor, 2);
			}
			catch(IOException e)
			{
				throw BuckminsterException.wrap(e);
			}
			finally
			{
				IOUtils.close(recentProperties);
			}
		}

		m_properties = new HashMap<String, String>();
		if(!truncate && oldProps != null)
			m_properties.putAll(oldProps);

		this.addFormatters();
		try
		{
			this.appendProperties();
			MonitorUtils.worked(monitor, 2);
			AccessibleByteArrayOutputStream output = new AccessibleByteArrayOutputStream();
			BMProperties.store(m_properties, output, Messages.Generated_by_Buckminster_Do_not_edit);

			IProgressMonitor storeMonitor = MonitorUtils.subMonitor(monitor, 2);
			if(oldProps != null)
			{
				if(!m_properties.equals(oldProps))
				{
					m_propertyFile.setContents(output.getInputStream(), false, false, storeMonitor);

					// Might stem from a project created on existing folders.
					//
					m_propertyFile.setDerived(true);
				}
			}
			else
			{
				FileUtils.createFolder(m_propertyFile.getParent());
				m_propertyFile.create(output.getInputStream(), false, storeMonitor);
				m_propertyFile.setDerived(true);
			}
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			monitor.done();
			m_arguments = null;
			m_formatters = null;
			m_properties = null;

			// We retain m_propertyFile for the sake of cleaning.
		}
		return null;
	}

	@Override
	protected IProject[] doIncrementalBuild(Map<String, String> args, IProgressMonitor monitor) throws CoreException
	{
		return this.doFullBuild(args, monitor);
	}

	public void doStartupOnIntialize() throws CoreException
	{
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
	}

	/**
	 * Format the path according to value of <code>ARG_FILE_SEPARATOR</code>.
	 * 
	 * @param path
	 *            path to format.
	 * @return The formatted path.
	 */
	protected final String formatPath(IPath path)
	{
		String filesep = this.getArgument(ARG_FILE_SEPARATOR);
		if(filesep == null || filesep.length() == 0)
			return path.toOSString();

		String portable = path.toPortableString();
		if(filesep.equals("/")) //$NON-NLS-1$
			return portable;

		return portable.replace('/', filesep.charAt(0));
	}

	/**
	 * Returns a value supplied in the <code>args</code> argument of the <code>build</code> method or <code>null</code>
	 * if the given <code>key</code> was not found.
	 * 
	 * @param key
	 *            The key used when obtaining the value.
	 * @return The value or <code>null</code>.
	 */
	protected final String getArgument(String key)
	{
		return m_arguments == null
				? null
				: (String)m_arguments.get(key);
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event)
	{
		if(event.getType() != IResourceChangeEvent.POST_CHANGE || m_propertyFile == null)
			return;

		IResourceDelta propFileDelta = event.getDelta().findMember(m_propertyFile.getFullPath());
		if(propFileDelta != null && (propFileDelta.getKind() & IResourceDelta.REMOVED) != 0)
			//
			// Someone removed our property file. Let's make sure it's rebuilt
			//
			this.forgetLastBuiltState();
	}
}

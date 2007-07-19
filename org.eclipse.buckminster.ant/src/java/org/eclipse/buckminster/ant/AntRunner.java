/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Michael Giroux (michael.giroux@objectweb.org) - bug 149739
 *******************************************************************************/
package org.eclipse.buckminster.ant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.ant.core.AntCorePlugin;
import org.eclipse.ant.core.Property;
import org.eclipse.ant.internal.core.IAntCoreConstants;
import org.eclipse.ant.internal.core.InternalCoreAntMessages;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

/**
 * Entry point for running Ant builds inside Eclipse (within the same JRE).
 * Clients may instantiate this class; it is not intended to be subclassed.
 * <p/>
 * <div class="TableSubHeadingColor">
 * <b>Usage note:</b><br/>
 * As opposed to {@link org.eclipse.ant.core.AntRunner} this class will not
 * set up a new classloader for each call and consequently, it does not support
 * the customClassPath.
 * <p>Refer to the "Platform Ant Support" chapter of the Programmer's Guide
 * section in the Platform Plug-in Developer Guide for complete details.</p>
 * </div>
 */
@SuppressWarnings("restriction")
public class AntRunner
{
    /** Message priority of &quot;error&quot;. */
    public static final int MSG_ERR = 0;
    /** Message priority of &quot;warning&quot;. */
    public static final int MSG_WARN = 1;
    /** Message priority of &quot;information&quot;. */
    public static final int MSG_INFO = 2;
    /** Message priority of &quot;verbose&quot;. */
    public static final int MSG_VERBOSE = 3;
    /** Message priority of &quot;debug&quot;. */
    public static final int MSG_DEBUG = 4;

    private static boolean s_buildRunning = false;
	private static final Class<?> s_internalAntRunnerClass;
	private static final Method s_addBuildLogger;
	private static final Method s_getBuildErrorMessage;
	private static final Method s_setBuildFileLocation;
	private static final Method s_setAntHome;
	private static final Method s_addUserProperties;
	private static final Method s_addPropertyFiles;
	private static final Method s_setArguments;
	private static final Method s_setProgressMonitor;
	private static final Method s_setMessageOutputLevel;
	private static final Method s_setExecutionTargets;
	private static final Method s_run;

	static
	{
		try
		{
			Class<?>[] string = new Class<?>[] { String.class };
			Class<?>[] strings = new Class<?>[] { String[].class };
			s_internalAntRunnerClass = getInternalAntRunnerClass();
			s_addBuildLogger = s_internalAntRunnerClass.getMethod("addBuildLogger", string);
			s_getBuildErrorMessage = s_internalAntRunnerClass.getMethod("getBuildExceptionErrorMessage", new Class[] { Throwable.class });
			s_run = s_internalAntRunnerClass.getMethod("run", Trivial.EMPTY_CLASS_ARRAY);
			s_setBuildFileLocation = s_internalAntRunnerClass.getMethod("setBuildFileLocation", string);
			s_setAntHome = s_internalAntRunnerClass.getMethod("setAntHome", string);
			s_addUserProperties = s_internalAntRunnerClass.getMethod("addUserProperties", new Class[] { Map.class });
			s_addPropertyFiles = s_internalAntRunnerClass.getMethod("addPropertyFiles", strings);
			s_setArguments = s_internalAntRunnerClass.getMethod("setArguments", strings);
			s_setProgressMonitor = s_internalAntRunnerClass.getMethod("setProgressMonitor", new Class[] { IProgressMonitor.class });
			s_setMessageOutputLevel = s_internalAntRunnerClass.getMethod("setMessageOutputLevel", new Class[] { int.class });
			s_setExecutionTargets = s_internalAntRunnerClass.getMethod("setExecutionTargets", strings);
		}
		catch(ClassNotFoundException e)
		{
			throw new ExceptionInInitializerError(problemLoadingClass(e));
		}
		catch(NoSuchMethodException e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}

	@SuppressWarnings("unchecked")
	private static List<Property> getGlobalAntProperties()
	{
		return AntCorePlugin.getPlugin().getPreferences().getProperties();
	}

	private String m_buildFileLocation = IAntCoreConstants.DEFAULT_BUILD_FILENAME;

	private String[] m_targets;

	private Map<String,String> m_userProperties;

	private String[] m_arguments;

	private String[] m_propertyFiles;

	private String m_antHome;

	private String m_buildLoggerClassName;

	/**
	 * Sets the build file location on the file system.
	 * 
	 * @param buildFileLocation
	 *            the file system location of the build file
	 */
	public void setBuildFileLocation(IPath buildFileLocation)
	{
		if(buildFileLocation == null)
			m_buildFileLocation = IAntCoreConstants.DEFAULT_BUILD_FILENAME;
		else
			m_buildFileLocation = buildFileLocation.toOSString();
	}

	/**
	 * Sets the build logger. The parameter <code>className</code>
	 * is the class name of an <code>org.apache.tools.ant.BuildLogger</code>
	 * implementation. The class will be instantiated at runtime and the
	 * logger will be called on build events
	 * (<code>org.apache.tools.ant.BuildEvent</code>).  
	 * Only one build logger is permitted for any build.
     * 
     * <p>Refer to {@link AntRunner Usage Note} for implementation details.
     * 
	 * @param className a build logger class name
	 */
	public void setBuildLogger(String className) {
		m_buildLoggerClassName = className;
	}

	/**
	 * Sets the arguments to be passed to the build (e.g. -verbose).
	 * 
	 * @param arguments
	 *            the arguments to be passed to the build
	 */
	public void setArguments(String[] arguments)
	{
		m_arguments = arguments;
	}

	/**
	 * Sets the targets and execution order.
	 * 
	 * @param executionTargets
	 *            which targets should be run and in which order
	 */
	public void setExecutionTargets(String[] executionTargets)
	{
		m_targets = executionTargets;
	}

	/**
	 * Adds user-defined properties. Keys and values must be String objects.
	 * 
	 * @param properties
	 *            a Map of user-defined properties
	 */
	public void addUserProperties(Map<String,String> properties)
	{
		if(m_userProperties == null)
			m_userProperties = new HashMap<String,String>(properties);
		else
			m_userProperties.putAll(properties);
	}

	/**
	 * Runs the build file. If a progress monitor is specified it will be available during the script execution as a
	 * reference in the Ant Project (<code>org.apache.tools.ant.Project.getReferences()</code>). A long- running
	 * task could, for example, get the monitor during its execution and check for cancellation. The key value to
	 * retrieve the progress monitor instance is <code>AntCorePlugin.ECLIPSE_PROGRESS_MONITOR</code>.
	 * 
	 * Only one build can occur at any given time.
	 * 
	 * Sets the current threads context class loader to the AntClassLoader for the duration of the build.
	 * 
	 * @param monitor
	 *            a progress monitor, or <code>null</code> if progress reporting and cancellation are not desired
	 * @throws CoreException
	 *             Thrown if a build is already occurring or if an exception occurs during the build
	 */
	public void run(IProgressMonitor monitor) throws CoreException
	{
		synchronized(s_internalAntRunnerClass)
		{
			Object runner = null;
			ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
			try
			{
				runner = s_internalAntRunnerClass.newInstance();
				s_setBuildFileLocation.invoke(runner, new Object[] { m_buildFileLocation });
	
				if(m_antHome != null)
					s_setAntHome.invoke(runner, new Object[] { m_antHome });
	
				if(m_buildLoggerClassName == null)
					//
					//indicate that the default logger is not to be used
					//
					m_buildLoggerClassName= "";
	
				s_addBuildLogger.invoke(runner, new Object[] { m_buildLoggerClassName });
	
				if(m_userProperties != null)
				{
					Map<String,String> allProps = m_userProperties;
	
					// The eclipse ant runner will not include the global properties
					// if we add user properties so we need to include them here
					//
					List<Property> properties = getGlobalAntProperties();
					if(properties != null)
					{
						allProps = new HashMap<String,String>(m_userProperties);
						for(Property property : properties)
						{
							// We must do early expansion since the expansion is based
							// on Eclipse variables and not on other properties.
							//
							String value= property.getValue(true);
							if (value != null)
								allProps.put(property.getName(), value);
						}
					}
					s_addUserProperties.invoke(runner, new Object[] { allProps });
				}
	
				if(m_propertyFiles != null && m_propertyFiles.length > 0)
					s_addPropertyFiles.invoke(runner, new Object[] { m_propertyFiles });
	
				if(m_arguments != null && m_arguments.length > 0)
					s_setArguments.invoke(runner, new Object[] { m_arguments });
	
				if(monitor != null)
					s_setProgressMonitor.invoke(runner, new Object[] { monitor });
	
				int messageOutputLevel;
				switch(BuckminsterPreferences.getLogLevelAntLogger())
				{
				case Logger.DEBUG:
					messageOutputLevel = MSG_DEBUG;
					break;
				case Logger.WARNING:
					messageOutputLevel = MSG_WARN;
					break;
				case Logger.ERROR:
					messageOutputLevel = MSG_ERR;
					break;
				default:
					messageOutputLevel = MSG_INFO;
				}
				if(messageOutputLevel != MSG_INFO)
					s_setMessageOutputLevel.invoke(runner, new Object[] { new Integer(messageOutputLevel) });
	
				if(m_targets != null)
					s_setExecutionTargets.invoke(runner, new Object[] { m_targets });
	
				s_run.invoke(runner, Trivial.EMPTY_OBJECT_ARRAY);
			}
			catch(NoClassDefFoundError e)
			{
				throw problemLoadingClass(e);
			}
			catch(InvocationTargetException e)
			{
				throw handleInvocationTargetException(runner, s_internalAntRunnerClass, e);
			}
			catch(Exception e)
			{
				String message = (e.getMessage() == null)
						? InternalCoreAntMessages.AntRunner_Build_Failed__3
						: e.getMessage();
				IStatus status = new Status(IStatus.ERROR, AntCorePlugin.PI_ANTCORE, AntCorePlugin.ERROR_RUNNING_BUILD,
						message, e);
				throw new CoreException(status);
			}
			finally
			{
				s_buildRunning = false;
				Thread.currentThread().setContextClassLoader(originalClassLoader);
			}
		}
	}

	private static Class<?> getInternalAntRunnerClass() throws ClassNotFoundException
	{
		ClassLoader loader = getClassLoader();
		Thread.currentThread().setContextClassLoader(loader);
		return loader.loadClass("org.eclipse.ant.internal.core.ant.InternalAntRunner"); //$NON-NLS-1$
	}

	/*
	 * Handles exceptions that are loaded by the Ant Class Loader by asking the Internal Ant Runner class for the
	 * correct error message.
	 * 
	 * Handles OperationCanceledExceptions, nested NoClassDefFoundError and nested ClassNotFoundException
	 */
	static CoreException handleInvocationTargetException(Object runner, Class<?> classInternalAntRunner,
			InvocationTargetException e)
	{
		Throwable realException = e.getTargetException();
		if(realException instanceof OperationCanceledException)
			return new CoreException(Status.CANCEL_STATUS);

		String message = null;
		if(runner != null)
		{
			try
			{
				message = (String)s_getBuildErrorMessage.invoke(runner, new Object[] { realException });
			}
			catch(Exception ex)
			{
				// do nothing as already in error state
			}
		}

		// J9 throws NoClassDefFoundError nested in a InvocationTargetException
		//
		if(message == null
				&& ((realException instanceof NoClassDefFoundError) || (realException instanceof ClassNotFoundException)))
			return problemLoadingClass(realException);

		boolean internalError = false;
		if(message == null)
		{
			// error did not result from a BuildException
			//
			internalError = true;
			message = (realException.getMessage() == null)
					? InternalCoreAntMessages.AntRunner_Build_Failed__3
					: realException.getMessage();
		}
		IStatus status = new Status(IStatus.ERROR, AntCorePlugin.PI_ANTCORE, AntCorePlugin.ERROR_RUNNING_BUILD,
				message, realException);
		if(internalError)
			AntCorePlugin.getPlugin().getLog().log(status);

		return new CoreException(status);
	}

	static CoreException problemLoadingClass(Throwable e)
	{
		String missingClassName = e.getMessage();
		String message;
		if(missingClassName != null)
		{
			missingClassName = missingClassName.replace('/', '.');
			message = InternalCoreAntMessages.AntRunner_Could_not_find_one_or_more_classes__Please_check_the_Ant_classpath__2;
			message = NLS.bind(message, new String[] { missingClassName });
		}
		else
		{
			message = InternalCoreAntMessages.AntRunner_Could_not_find_one_or_more_classes__Please_check_the_Ant_classpath__1;
		}
		IStatus status = new Status(IStatus.ERROR, AntCorePlugin.PI_ANTCORE, AntCorePlugin.ERROR_RUNNING_BUILD,
				message, e);
		AntCorePlugin.getPlugin().getLog().log(status);
		return new CoreException(status);
	}

	private static ClassLoader getClassLoader()
	{
		return AntCorePlugin.getPlugin().getNewClassLoader();
	}

	/**
	 * Sets the user specified property files.
	 * 
	 * @param propertyFiles
	 *            array of property file paths
	 * @since 2.1
	 */
	public void setPropertyFiles(String[] propertyFiles)
	{
		m_propertyFiles = propertyFiles;
	}

	/**
	 * Sets the Ant home to use for this build
	 * 
	 * @param antHome
	 *            String specifying the Ant home to use
	 * @since 2.1
	 */
	public void setAntHome(String antHome)
	{
		m_antHome = antHome;
	}

	/**
	 * Returns whether an Ant build is already in progress
	 * 
	 * Only one Ant build can occur at any given time.
	 * 
	 * @since 2.1
	 * @return boolean
	 */
	public static boolean isBuildRunning()
	{
		return s_buildRunning;
	}
}

/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.jdt.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IFileReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.jdt.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.internal.core.JavaModelManager;
import org.eclipse.jdt.internal.core.JavaProject;


/**
 * A IStreamConsumer responsible for reading and parsing a
 * <code>.classpath</code> file.
 *
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class ClasspathReader extends JavaProject implements IStreamConsumer<IClasspathEntry[]>
{
	@SuppressWarnings("hiding")
	public static final String CLASSPATH_FILENAME = ".classpath"; //$NON-NLS-1$

	public ClasspathReader()
	{
		super(ResourcesPlugin.getWorkspace().getRoot().getProject(" "), JavaModelManager.getJavaModelManager().getJavaModel()); //$NON-NLS-1$
	}

	public static IClasspathEntry[] getClasspath(IComponentReader reader, IProgressMonitor monitor)
	throws CoreException
	{
		ClasspathReader rdr = new ClasspathReader();
		try
		{
			return (reader instanceof ICatalogReader)
				? ((ICatalogReader)reader).readFile(CLASSPATH_FILENAME, rdr, monitor)
				: ((IFileReader)reader).readFile(rdr, monitor);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public IClasspathEntry[] consumeStream(IComponentReader reader, String streamName, InputStream stream, IProgressMonitor monitor) throws CoreException, IOException
	{
		monitor.beginTask(null, 150);
		monitor.subTask(Messages.parsing_classpath);
		try
		{
			ByteArrayOutputStream builder = new ByteArrayOutputStream();
			FileUtils.copyFile(stream, builder, MonitorUtils.subMonitor(monitor, 100));
			return myDecodeClasspath(new String(builder.toByteArray()));
		}
		finally
		{
			monitor.done();
		}
	}

	private static Method s_decodeClasspathMethod;
	private static boolean s_isEclipse3_3 = false;

	private IClasspathEntry[] myDecodeClasspath(String xmlClasspath) throws CoreException
	{
		if(s_decodeClasspathMethod == null)
		{
			Class<? extends JavaProject> c = JavaProject.class;
			synchronized(c)
			{
				try
				{
					// The 3.3.x signature is like this:
					// IClasspathEntry[] decodeClasspath(String xmlClasspath, Map unknownElements) throws IOException, AssertionFailedException {
					//
					s_decodeClasspathMethod = c.getDeclaredMethod("decodeClasspath", String.class, Map.class); //$NON-NLS-1$
					s_isEclipse3_3 = true;
				}
				catch(NoSuchMethodException e)
				{
					try
					{
						// The 3.2.1 signature is like this:
						// IClasspathEntry[] decodeClasspath(String xmlClasspath, boolean createMarker, boolean logProblems)
						//
						s_decodeClasspathMethod = c.getDeclaredMethod("decodeClasspath", String.class, boolean.class, boolean.class); //$NON-NLS-1$
						s_isEclipse3_3 = false;
					}
					catch(NoSuchMethodException e2)
					{
						throw BuckminsterException.wrap(e2);
					}
				}
			}
		}
		try
		{
			Object[] args = s_isEclipse3_3 ? new Object[] { xmlClasspath, null } : new Object[] { xmlClasspath, Boolean.FALSE, Boolean.FALSE };
			return (IClasspathEntry[])s_decodeClasspathMethod.invoke(this, args);
		}
		catch(Throwable t)
		{
			throw BuckminsterException.wrap(t);
		}
	}
}
/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ant.internal.build;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.eclipse.ant.core.AntCorePlugin;
import org.eclipse.buckminster.ant.AntPlugin;
import org.eclipse.buckminster.core.build.AbstractBuckminsterBuilder;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * <p>
 * This builder will execute Ant scripts fast and efficiently. Contrary to the
 * normal Eclipse builder, it not instantiate a new ClassLoader each time it is
 * called. The <code>ClassLoader</code> is in fact only instantiated once for
 * all bulder instances and the classpath can therefore not vary between those
 * instances.
 * </p>
 * <p>
 * The drawback with this design is that it is less flexible. The class path
 * cannot be modified on a per-builder basis (only for Ant as a whole)</code>
 * can vary.
 * </p>
 * <p>
 * A strong motivation for the selected design was that an approach where a new
 * chain of ClassLoaders where set up each time around was extremely memory
 * consuming. Caches used by the <code>sun.misc.URL.Classpath</code> (probably
 * timed caches) caused <code>OutOfMemoryException</code> when many builds
 * where executed within a short timeframe.
 * </p>
 * 
 * @author Thomas Hallgren
 */
public class AntBuilder extends AbstractAntBuilder
{
	private static final IPath s_antHome;

	private static final Method s_buildMethod;

	private static final Constructor<? extends Object> s_ctor;

	private static final ClassLoader s_antLoader;

	static
	{
		AntCorePlugin plugin = AntCorePlugin.getPlugin();
		s_antLoader = plugin.getNewClassLoader();
		Thread curr = Thread.currentThread();
		ClassLoader currCtxLoader = curr.getContextClassLoader();
		try
		{
			curr.setContextClassLoader(s_antLoader);
			Class<?> internalAntRunnerClass = s_antLoader.loadClass("org.eclipse.buckminster.ant.support.InternalAntBuilder");
			s_buildMethod = internalAntRunnerClass.getMethod("build", IPath.class, IPath.class, String[].class, Map.class, Map.class, PrintStream.class, PrintStream.class);
			s_ctor = internalAntRunnerClass.getConstructor(IPath.class);
			s_antHome = new Path(plugin.getPreferences().getDefaultAntHome());
		}
		catch (Throwable e)
		{
			throw new ExceptionInInitializerError(e);
		}
		finally
		{
			curr.setContextClassLoader(currCtxLoader);
		}
	}

	// This is the actual ant project.
	//
	private Object m_internalAntBuilder;

	/**
	 * Creates the opaque internal ant builder. The builder is created
	 * using the special ant Classloader.
	 * @return An instance of the <code>org.eclipse.buckminster.ant.support.InternalAntBuilder</code> class.
	 * @throws CoreException If the builder could not be created for some reason.
	 */
	public static Object createInternalAntBuilder() throws CoreException
	{
		Thread curr = Thread.currentThread();
		ClassLoader currCtxLoader = curr.getContextClassLoader();
		try
		{
			curr.setContextClassLoader(s_antLoader);
			return s_ctor.newInstance(s_antHome);
		}
		catch (Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			curr.setContextClassLoader(currCtxLoader);
		}
	}

	/**
	 * Invokes the internal ant builder.
	 * @param builder A builder created by {@link #createInternalAntBuilder()}.
	 * @param absoluteScriptFile The absolute path to the build script.
	 * @param baseDir The base directory to use for the execution (might be <code>null</code>).
	 * @param targets An array of targets to execute. Might be <code>null</code> or empty for default target execution.
	 * @param userProps Properties (key value pairs) to pass to the build.
	 * @param out A stream that will receive normal output from the build.
	 * @param err A stream that will receive error output from the build.
	 * @throws CoreException
	 */
	public static void invokeInternalAntBuilder(Object builder, IPath absoluteScriptFile, IPath baseDir, String[] targets, Map<String, String> userProps, Map<String,List<IPath>> fileSetGroups, PrintStream out, PrintStream err)
	throws CoreException
	{
		Thread curr = Thread.currentThread();
		ClassLoader currCtxLoader = curr.getContextClassLoader();
		try
		{
			curr.setContextClassLoader(s_antLoader);
			s_buildMethod.invoke(builder, absoluteScriptFile, baseDir, targets, userProps, fileSetGroups, out, err);
		}
		catch (Throwable e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			curr.setContextClassLoader(currCtxLoader);
		}
	}

	@Override
	protected IProject[] doBuild(int kind, Map<String,String> args, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			if (m_internalAntBuilder == null)
				m_internalAntBuilder = createInternalAntBuilder();
				
			String target = this.getTarget(args, kind);
			String[] targets = target == null ? null : new String[] { target };
	
			// only set the 'kind' property if a propname is given
			//
			String kindPropName = AbstractBuckminsterBuilder.getValue(args, ARG_BUILD_KIND_PROPERTY_KEY);
			Map<String, String> props = this.getFixedProperties(args);
			if(kindPropName != null)
				props.put(kindPropName, AbstractBuckminsterBuilder.kindToString(kind));
			
			invokeInternalAntBuilder(
				m_internalAntBuilder,
				this.getScriptFile(args).getLocation(),
				this.getBaseDir(args),
				targets,
				props,
				null,
				this.getOutStream(),
				this.getErrStream());
		}
		catch(CoreException e)
		{
			AntPlugin.getLogger().error(e.getMessage(), e);
			throw e;
		}

		return null;
	}

	@Override
	protected void scriptFileChanged()
	{
		m_internalAntBuilder = null;
	}
}

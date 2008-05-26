/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.test;

import java.io.File;
import java.net.URL;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.MissingReaderTypeException;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.internal.EclipsePlatformReaderType;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

@SuppressWarnings("restriction")
public class PDEBuilderTest extends PDETestCase
{
	public void testPluginCompiler() throws Exception
	{
		PDEPlugin pdePlugin = PDEPlugin.getDefault();
		if(pdePlugin == null)
			throw new Exception("This test must be run as a \"JUnit Plug-in Test\"");

		IProgressMonitor nulMon = new NullProgressMonitor();
		String[] componentTypes = new String[] { IComponentType.OSGI_BUNDLE, IComponentType.ECLIPSE_FEATURE };
		IResolver resolver = this.createResolver(pdePlugin.toString(), IComponentType.OSGI_BUNDLE);
		CorePlugin corePlugin = CorePlugin.getDefault();
		URL location = FileLocator.toFileURL(pdePlugin.getBundle().getEntry("/"));
		Provider provider = new Provider(IReaderType.URL_CATALOG, componentTypes, location.toString());

		IComponentType bundleType = CorePlugin.getDefault().getComponentType(IComponentType.OSGI_BUNDLE);
		IReaderType readerType = provider.getReaderType();
		IComponentReader[] reader = new IComponentReader[] {
			readerType.getReader(provider, bundleType, resolver.getContext().getRootNodeQuery(), null, nulMon) };

		IResolutionBuilder builder = corePlugin.getResolutionBuilder("plugin2cspec");
		Utils.serialize(builder.build(reader, false, nulMon), System.out);
		if(reader[0] != null)
			reader[0].close();

		location = FileLocator.toFileURL(Platform.getBundle("org.junit").getEntry("/"));
		resolver = this.createResolver("org.junit", null);
		provider = new Provider(IReaderType.URL_CATALOG, componentTypes, location.toString());

		reader[0] = readerType.getReader(provider, bundleType, resolver.getContext().getRootNodeQuery(), null, nulMon);
		Utils.serialize(builder.build(reader, false, nulMon), System.out);
		if(reader[0] != null)
			reader[0].close();

		IFeatureModel featureModel = EclipsePlatformReaderType.getBestFeature("org.eclipse.pde", null, null);
		String installLocation = featureModel.getInstallLocation();
		if(installLocation == null)
		{
			// Feature is in the workspace.
			//
			IResource res = featureModel.getUnderlyingResource();
			installLocation = res.getLocation().toOSString();
		}
		File tmp = new File(installLocation);

		location = tmp.toURL();
		resolver = this.createResolver("org.eclipse.pde", null);
		builder = corePlugin.getResolutionBuilder("feature2cspec");
		provider = new Provider(IReaderType.URL_CATALOG, componentTypes, location.toString());
		reader[0] = readerType.getReader(provider, bundleType, resolver.getContext().getRootNodeQuery(), null, nulMon);
		Utils.serialize(builder.build(reader, false, nulMon), System.out);
		if(reader[0] != null)
			reader[0].close();

		try
		{
			resolver = this.createResolver("org.tigris.subversion.subclipse.core", null);
			provider = new Provider("svn", componentTypes, "http://subclipse.tigris.org/svn/subclipse/trunk/subclipse?moduleBeforeTag&amp;moduleAfterTag#core");
			readerType = provider.getReaderType();
			reader[0] = readerType.getReader(provider, bundleType, resolver.getContext().getRootNodeQuery(), null, nulMon);
			builder = corePlugin.getResolutionBuilder("plugin2cspec");
			Utils.serialize(builder.build(reader, false, nulMon), System.out);
			if(reader[0] != null)
				reader[0].close();
		}
		catch(MissingReaderTypeException e)
		{
			System.out.println("The buckminster.svn plugin is apparently not installed");
		}
	}
}

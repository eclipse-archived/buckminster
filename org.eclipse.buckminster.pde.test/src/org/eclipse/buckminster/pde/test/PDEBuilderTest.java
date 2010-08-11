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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.util.IComponentReader;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;

public class PDEBuilderTest extends PDETestCase {
	public void testPluginCompiler() throws Exception {
		PDEPlugin pdePlugin = PDEPlugin.getDefault();
		if (pdePlugin == null)
			throw new Exception("This test must be run as a \"JUnit Plug-in Test\""); //$NON-NLS-1$

		IProgressMonitor nulMon = new NullProgressMonitor();
		List<String> componentTypes = new ArrayList<String>();
		componentTypes.add(IComponentType.OSGI_BUNDLE);
		componentTypes.add(IComponentType.ECLIPSE_FEATURE);
		IResolver resolver = this.createResolver(pdePlugin.toString(), IComponentType.OSGI_BUNDLE);
		CorePlugin corePlugin = CorePlugin.getDefault();
		URL location = FileLocator.toFileURL(pdePlugin.getBundle().getEntry("/")); //$NON-NLS-1$
		Provider provider = RmapFactory.eINSTANCE.createProvider();
		provider.getComponentTypes().addAll(componentTypes);
		provider.setReaderType(IReaderType.URL_CATALOG);

		Format uri = CommonFactory.eINSTANCE.createFormat();
		uri.setFormat(location.toString());
		provider.setURI(uri);

		IComponentType bundleType = CorePlugin.getDefault().getComponentType(IComponentType.OSGI_BUNDLE);
		IReaderType readerType = CorePlugin.getDefault().getReaderType(provider.getReaderType());
		IComponentReader[] reader = new IComponentReader[] { readerType.getReader(provider, bundleType, resolver.getContext().getRootNodeQuery(),
				null, nulMon) };

		IResolutionBuilder builder = corePlugin.getResolutionBuilder("plugin2cspec"); //$NON-NLS-1$
		BOMNode node = builder.build(reader, false, nulMon);
		IOUtils.close(reader[0]);

		Resolution resolution = node.getResolution();
		assertNotNull("Resolution is null", resolution);
		Utils.serialize(resolution.getCSpec(), System.out);

		location = FileLocator.toFileURL(Platform.getBundle("org.junit").getEntry("/")); //$NON-NLS-1$ //$NON-NLS-2$
		resolver = this.createResolver("org.junit", null); //$NON-NLS-1$

		provider = RmapFactory.eINSTANCE.createProvider();
		provider.getComponentTypes().addAll(componentTypes);
		provider.setReaderType(IReaderType.URL_CATALOG);
		uri = CommonFactory.eINSTANCE.createFormat();
		uri.setFormat(location.toString());
		provider.setURI(uri);

		reader[0] = readerType.getReader(provider, bundleType, resolver.getContext().getRootNodeQuery(), null, nulMon);
		node = builder.build(reader, false, nulMon);
		IOUtils.close(reader[0]);

		resolution = node.getResolution();
		assertNotNull("Resolution is null", resolution);
		Utils.serialize(resolution.getCSpec(), System.out);
		if (reader[0] != null)
			reader[0].close();
	}
}

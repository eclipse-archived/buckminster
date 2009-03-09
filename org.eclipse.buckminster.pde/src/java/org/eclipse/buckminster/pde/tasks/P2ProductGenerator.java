/*****************************************************************************
 * Copyright (c) 2008-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.actor.IllegalPrerequisiteException;
import org.eclipse.buckminster.core.actor.MissingPrerequisiteException;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.internal.p2.publisher.eclipse.ProductFile;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.core.repository.IRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.spi.p2.artifact.repository.SimpleArtifactRepositoryFactory;
import org.eclipse.equinox.internal.provisional.spi.p2.metadata.repository.SimpleMetadataRepositoryFactory;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.Publisher;
import org.eclipse.equinox.p2.publisher.PublisherInfo;
import org.eclipse.equinox.p2.publisher.eclipse.ProductAction;
import org.eclipse.osgi.util.NLS;

/**
 * Ant task that creates a product base. The base in this case is a product stripped from all features and plugins.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class P2ProductGenerator extends AbstractActor
{
	public static final String ALIAS_SITE = "site"; //$NON-NLS-1$

	public static final String ALIAS_PRODUCT_DESCRIPTOR = "product.descriptor"; //$NON-NLS-1$

	private final static String MATCH_ALL = "*"; //$NON-NLS-1$

	private static final String WS_CARBON = "carbon"; //$NON-NLS-1$

	private static final String WS_COCOA = "cocoa"; //$NON-NLS-1$

	private static final String WS_GTK = "gtk"; //$NON-NLS-1$

	private static final String WS_MOTIF = "motif"; //$NON-NLS-1$

	private static final String WS_WIN32 = "win32"; //$NON-NLS-1$

	private static final String WS_WPF = "wpf"; //$NON-NLS-1$

	private static final String OS_AIX = "aix"; //$NON-NLS-1$

	private static final String OS_HPUX = "hpux"; //$NON-NLS-1$

	private static final String OS_LINUX = "linux"; //$NON-NLS-1$

	private static final String OS_MACOSX = "maxosx"; //$NON-NLS-1$

	private static final String OS_SOLARIS = "solaris"; //$NON-NLS-1$

	private static final String OS_WIN32 = "win32"; //$NON-NLS-1$

	private static final String ARCH_IA64_32 = "ia64_32"; //$NON-NLS-1$

	private static final String ARCH_PPC = "ppc"; //$NON-NLS-1$

	private static final String ARCH_SPARC = "sparc"; //$NON-NLS-1$

	private static final String ARCH_X86 = "x86"; //$NON-NLS-1$

	private static final String ARCH_X86_64 = "x86_64"; //$NON-NLS-1$

	private final static String[][] s_defaultKnownConfigs = { //
	{ WS_CARBON, OS_MACOSX, ARCH_PPC }, //
			{ WS_CARBON, OS_MACOSX, ARCH_X86 }, //
			{ WS_COCOA, OS_MACOSX, ARCH_PPC }, //
			{ WS_COCOA, OS_MACOSX, ARCH_X86 }, //
			{ WS_COCOA, OS_MACOSX, ARCH_X86_64 }, //
			{ WS_GTK, OS_LINUX, ARCH_PPC }, //
			{ WS_GTK, OS_LINUX, ARCH_X86 }, //
			{ WS_GTK, OS_LINUX, ARCH_X86_64 }, //
			{ WS_GTK, OS_SOLARIS, ARCH_SPARC }, //
			{ WS_MOTIF, OS_AIX, ARCH_PPC }, //
			{ WS_MOTIF, OS_HPUX, ARCH_IA64_32 }, //
			{ WS_MOTIF, OS_LINUX, ARCH_X86 }, //
			{ WS_WIN32, OS_WIN32, ARCH_X86 }, //
			{ WS_WIN32, OS_WIN32, ARCH_X86_64 }, //
			{ WS_WPF, OS_WIN32, ARCH_X86 }, //
	};

	public static IProductDescriptor getProductDescriptor(File productFile) throws CoreException
	{
		try
		{
			return new ProductFile(productFile.getAbsolutePath());
		}
		catch(RuntimeException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	private static boolean isMatch(String[] config, String targetWS, String targetOS, String targetArch)
	{
		return (MATCH_ALL.equals(targetWS) || config[0].equals(targetWS))
				&& (MATCH_ALL.equals(targetOS) || config[1].equals(targetOS))
				&& (MATCH_ALL.equals(targetArch) || config[2].equals(targetArch));
	}

	public void run(File productDescriptorFile, File siteFolder, File exeFeature,
			Map<String, ? extends Object> properties, IProgressMonitor monitor) throws CoreException
	{
		PublisherInfo info = new PublisherInfo();

		URI siteURI = siteFolder.toURI();
		SimpleArtifactRepositoryFactory arFactory = new SimpleArtifactRepositoryFactory();
		IArtifactRepository ar = arFactory.load(siteURI, IRepositoryManager.REPOSITORY_HINT_MODIFIABLE, monitor);
		info.setArtifactRepository(ar);

		SimpleMetadataRepositoryFactory mdrFactory = new SimpleMetadataRepositoryFactory();
		IMetadataRepository mdr = mdrFactory.load(siteURI, IRepositoryManager.REPOSITORY_HINT_MODIFIABLE, monitor);
		info.setMetadataRepository(mdr);

		String flavor = (String)properties.get("org.eclipse.p2.flavor"); //$NON-NLS-1$
		if(flavor == null)
			flavor = "tooling"; //$NON-NLS-1$

		info.setConfigurations(getConfigurations(properties));
		IPublisherAction[] actions = createActions(productDescriptorFile, exeFeature, flavor);
		Publisher publisher = createPublisher(info);
		IStatus result = publisher.publish(actions, new NullProgressMonitor());
		if(result.getSeverity() == IStatus.ERROR)
			throw new CoreException(result);
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		Action action = ctx.getAction();
		IPath outputPath = AbstractActor.getSingleAttributePath(ctx, action, false);
		IPath site = null;
		IPath productDescriptor = null;
		IPath exeFeature = null;
		CSpec cspec = action.getCSpec();
		for(Prerequisite preq : action.getPrerequisites())
		{
			if(ALIAS_SITE.equals(preq.getAlias()))
			{
				// This prerequisite should appoint the site as a folder
				//
				Attribute rt = preq.getReferencedAttribute(cspec, ctx);
				if(rt != null)
					site = AbstractActor.getSingleAttributePath(ctx, rt, true);
				continue;
			}

			if(ALIAS_PRODUCT_DESCRIPTOR.equals(preq.getAlias()))
			{
				// This prerequisite should appoint the site defining feature as a folder
				//
				Attribute rt = preq.getReferencedAttribute(cspec, ctx);
				if(rt != null)
					productDescriptor = AbstractActor.getSingleAttributePath(ctx, rt, false);
				continue;
			}

			if("org.eclipse.equinox.executable".equals(preq.getComponentName())) //$NON-NLS-1$
			{
				Attribute rt = preq.getReferencedAttribute(cspec, ctx);
				if(rt != null)
					exeFeature = AbstractActor.getSingleAttributePath(ctx, rt, false);
				continue;
			}

			throw new IllegalPrerequisiteException(action, preq.getName());
		}

		if(site == null)
			throw new MissingPrerequisiteException(action, ALIAS_SITE);

		if(productDescriptor == null)
			throw new MissingPrerequisiteException(action, ALIAS_PRODUCT_DESCRIPTOR);

		if(!outputPath.hasTrailingSeparator())
			throw new IllegalArgumentException(NLS.bind(
					org.eclipse.buckminster.core.Messages.output_of_action_0_must_be_folder, action.getQualifiedName()));

		File outputDir = outputPath.toFile().getAbsoluteFile();
		outputDir.mkdirs();

		Map<String, ? extends Object> props = ctx.getProperties();
		File siteDir = null;
		File siteFile = site.toFile().getAbsoluteFile();
		if(siteFile.isDirectory())
		{
			// If input is a folder, then output should be a mirror of that. Just
			// copy the structure.
			//
			org.eclipse.buckminster.core.helpers.FileUtils.deepCopy(siteFile, outputDir, ConflictResolution.REPLACE,
					monitor);
			siteDir = outputDir;
		}
		else if(siteFile.getName().endsWith(".zip")) //$NON-NLS-1$
		{
			// We need a temporary folder where we expand the site since we want the output
			// to contain a zip when the input is a zip.
			siteDir = new File(props.get(KeyConstants.ACTION_TEMP).toString());
			try
			{
				FileUtils.unzipFile(siteFile, siteDir);
			}
			catch(IOException e)
			{
				throw BuckminsterException.wrap(e);
			}
		}

		File exeFeatureFile = (exeFeature == null)
				? null
				: exeFeature.toFile().getAbsoluteFile();

		File productDescriptorFile = productDescriptor.toFile().getAbsoluteFile();
		run(productDescriptorFile, siteDir, exeFeatureFile, ctx.getProperties(), monitor);
		return Status.OK_STATUS;
	}

	private IPublisherAction[] createActions(File productFile, File deltaPack, String flavor) throws CoreException
	{
		return new IPublisherAction[] { createProductAction(productFile, deltaPack, flavor) };
	}

	private IPublisherAction createProductAction(File productFile, File deltaPack, String flavor) throws CoreException
	{
		IProductDescriptor productDescriptor = getProductDescriptor(productFile);
		return new ProductAction(null, productDescriptor, flavor, deltaPack);
	}

	private Publisher createPublisher(PublisherInfo info)
	{
		return new Publisher(info);
	}

	private String[] getConfigurations(Map<String, ? extends Object> props)
	{
		String targetWS = props.get(TargetPlatform.TARGET_WS).toString();
		if(targetWS == null)
			targetWS = org.eclipse.pde.core.plugin.TargetPlatform.getWS();

		String targetOS = props.get(TargetPlatform.TARGET_OS).toString();
		if(targetOS == null)
			targetOS = org.eclipse.pde.core.plugin.TargetPlatform.getOS();

		String targetArch = props.get(TargetPlatform.TARGET_ARCH).toString();
		if(targetArch == null)
			targetArch = org.eclipse.pde.core.plugin.TargetPlatform.getOSArch();

		if(!(MATCH_ALL.equals(targetOS) || MATCH_ALL.equals(targetWS) || MATCH_ALL.equals(targetArch)))
			return new String[] { targetWS + '.' + targetOS + '.' + targetArch };

		ArrayList<String> possibleMatches = new ArrayList<String>();
		for(String[] config : s_defaultKnownConfigs)
		{
			if(isMatch(config, targetWS, targetOS, targetArch))
				possibleMatches.add(config[0] + '.' + config[1] + '.' + config[2]);
		}
		return possibleMatches.toArray(new String[possibleMatches.size()]);
	}
}

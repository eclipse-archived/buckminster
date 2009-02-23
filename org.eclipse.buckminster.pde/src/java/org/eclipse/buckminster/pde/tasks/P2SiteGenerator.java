/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.actor.IllegalPrerequisiteException;
import org.eclipse.buckminster.core.actor.MissingPrerequisiteException;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.p2.publisher.eclipse.FeatureParser;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.core.repository.IRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.spi.p2.artifact.repository.SimpleArtifactRepositoryFactory;
import org.eclipse.equinox.internal.provisional.spi.p2.metadata.repository.SimpleMetadataRepositoryFactory;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.Publisher;
import org.eclipse.equinox.p2.publisher.PublisherInfo;
import org.eclipse.equinox.p2.publisher.eclipse.BundlesAction;
import org.eclipse.equinox.p2.publisher.eclipse.Feature;
import org.eclipse.equinox.p2.publisher.eclipse.FeaturesAction;
import org.eclipse.osgi.util.NLS;

@SuppressWarnings("restriction")
public class P2SiteGenerator extends AbstractActor
{
	public static final String ALIAS_SITE = "site"; //$NON-NLS-1$

	public static final String ALIAS_SITE_FEATURE = "site.feature"; //$NON-NLS-1$

	public void run(File siteFeature, File siteFolder) throws CoreException
	{
		if(siteFolder == null)
			// Nothing to do
			return;

		FeatureParser parser = new FeatureParser();
		Feature topFeature = parser.parse(siteFeature);
		if(topFeature == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_parse_feature_from_0, siteFeature));

		topFeature.setLocation(siteFeature.toString());
		PublisherInfo info = new PublisherInfo();

		URI siteURI = siteFolder.toURI();
		SimpleArtifactRepositoryFactory arFactory = new SimpleArtifactRepositoryFactory();
		IArtifactRepository ar = arFactory
				.create(siteURI, topFeature.getLabel() + " - Artifact Repository", null, null); //$NON-NLS-1$
		String trueStr = Boolean.toString(true);
		ar.setProperty(IRepository.PROP_COMPRESSED, trueStr);
		ar.setProperty(Publisher.PUBLISH_PACK_FILES_AS_SIBLINGS, trueStr);

		info.setArtifactRepository(ar);

		SimpleMetadataRepositoryFactory mdrFactory = new SimpleMetadataRepositoryFactory();
		IMetadataRepository mdr = mdrFactory.create(siteURI, topFeature.getLabel(), null, null);
		mdr.setProperty(IRepository.PROP_COMPRESSED, trueStr);
		info.setMetadataRepository(mdr);

		IPublisherAction[] actions = createActions(topFeature, siteFolder);
		Publisher publisher = createPublisher(info);
		IStatus result = publisher.publish(actions, new NullProgressMonitor());
		if(result.getSeverity() == IStatus.ERROR)
			throw new CoreException(result);
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		Action action = ctx.getAction();
		IPath outputPath = AbstractActor.getSingleProductPath(ctx, action, false);
		IPath site = null;
		IPath siteFeature = null;
		CSpec cspec = action.getCSpec();
		for(Prerequisite preq : action.getPrerequisites())
		{
			if(ALIAS_SITE.equals(preq.getAlias()))
			{
				// This prerequisite should appoint the site as a folder
				//
				Attribute rt = preq.getReferencedAttribute(cspec, ctx);
				if(rt != null)
					site = AbstractActor.getSingleProductPath(ctx, rt, true);
				continue;
			}

			if(ALIAS_SITE_FEATURE.equals(preq.getAlias()))
			{
				// This prerequisite should appoint the site defining feature as a folder
				//
				Attribute rt = preq.getReferencedAttribute(cspec, ctx);
				if(rt != null)
					siteFeature = AbstractActor.getSingleProductPath(ctx, rt, false);
				continue;
			}
			throw new IllegalPrerequisiteException(action, preq.getName());
		}

		if(site == null)
			throw new MissingPrerequisiteException(action, ALIAS_SITE);

		if(siteFeature == null)
			throw new MissingPrerequisiteException(action, ALIAS_SITE_FEATURE);

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

		File siteFeatureFile = siteFeature.toFile().getAbsoluteFile();
		if(siteFeature.hasTrailingSeparator())
		{
			// This can be a feature folder in which case it contains a feature.xml
			//
			boolean multipleJars = false;
			boolean isFeatureFolder = false;
			String jarFile = null;
			for(String file : siteFeatureFile.list())
			{
				if(file.endsWith(".jar")) //$NON-NLS-1$
				{
					// Possible feature file
					multipleJars = jarFile != null;
					jarFile = file;
					continue;
				}
				if(file.equals("feature.xml")) //$NON-NLS-1$
				{
					isFeatureFolder = true;
					break;
				}
			}
			if(!isFeatureFolder)
			{
				if(jarFile == null || multipleJars)
					throw BuckminsterException.fromMessage(NLS.bind(
							Messages.prerequisite_0_for_action_1_must_be_a_feature, ALIAS_SITE_FEATURE, action
									.getQualifiedName()));
				siteFeatureFile = new File(siteFeatureFile, jarFile);
			}
		}

		run(siteFeatureFile, siteDir);
		if(siteDir != outputDir)
		{
			// Zip the content of the siteDir. The name of the zip should
			// be the same as the name of the input zip.
			//
			File outputZip = new File(outputDir, siteFile.getName());
			try
			{
				FileUtils.zip(siteDir.listFiles(), null, outputZip, FileUtils.createRootPathComputer(siteDir));
			}
			catch(IOException e)
			{
				throw BuckminsterException.wrap(e);
			}
		}
		return Status.OK_STATUS;
	}

	private IPublisherAction[] createActions(Feature topFeature, File siteFolder) throws CoreException
	{
		return new IPublisherAction[] { createFeaturesAction(siteFolder), createBundlesAction(siteFolder),
				createCategoriesAction(topFeature) };
	}

	private IPublisherAction createBundlesAction(File siteFolder)
	{
		return new BundlesAction(new File[] { new File(siteFolder, "plugins") }); //$NON-NLS-1$
	}

	private IPublisherAction createCategoriesAction(Feature topFeature) throws CoreException
	{
		return new FeatureToP2SiteAction(topFeature);
	}

	private IPublisherAction createFeaturesAction(File siteFolder)
	{
		return new FeaturesAction(new File[] { new File(siteFolder, "features") }); //$NON-NLS-1$
	}

	private Publisher createPublisher(PublisherInfo info)
	{
		return new Publisher(info);
	}
}

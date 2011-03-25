/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.internal.actor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.actor.MissingPrerequisiteException;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.internal.model.EditableFeatureModel;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureInfo;

@SuppressWarnings("restriction")
public class MergeLicenseFeature extends AbstractActor {
	public static final String ID = "mergeLicenseFeature"; //$NON-NLS-1$

	private static final Pattern propertyFilePattern = Pattern.compile("feature.*\\.properties"); //$NON-NLS-1$

	private static FileFilter featurePropertyFiles = new FileFilter() {
		@Override
		public boolean accept(File file) {
			return propertyFilePattern.matcher(file.getName()).matches();
		}
	};

	private static void copyBinIncludes(Attribute binIncludes, File attributeBase, IPath outputPath, IActionContext ctx, IProgressMonitor monitor)
			throws CoreException {
		// Copy everything specified in binIncludes except the feature.xml
		// and feature*.properties since they have been dealt with already
		PathGroup base = new PathGroup(Path.fromOSString(attributeBase.getAbsolutePath()), new IPath[0]);
		for (PathGroup pg : binIncludes.getPathGroups(ctx, null)) {
			for (IPath path : pg.getPaths()) {
				if (path.segmentCount() == 1) {
					String name = path.segment(0);
					if (IPDEConstants.FEATURE_MANIFEST.equals(name) || propertyFilePattern.matcher(name).matches())
						continue;
				}
				base.copyPathTo(path, outputPath, monitor);
			}
		}
	}

	private static Properties loadFile(File file) throws IOException {
		InputStream fis = new BufferedInputStream(new FileInputStream(file));
		try {
			Properties props = new Properties();
			props.load(fis);
			return props;
		} finally {
			IOUtils.close(fis);
		}
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException {
		Action action = ctx.getAction();
		IPath outputPath = AbstractActor.getSingleAttributePath(ctx, action, false);
		IPath licenseFeaturePath = null;
		IPath manifestPath = null;
		IPath licenseManifestPath = null;
		CSpec cspec = action.getCSpec();
		Attribute binIncludes = null;
		Attribute licenseFeatureContents = null;
		for (Prerequisite preq : action.getPrerequisites()) {
			if (IPDEConstants.ALIAS_LICENSE_FEATURE.equals(preq.getAlias())) {
				// This prerequisite should appoint the site as a folder
				//
				Attribute rt = preq.getReferencedAttribute(cspec, ctx);
				if (rt == null)
					continue;

				licenseFeaturePath = AbstractActor.getSingleAttributePath(ctx, rt, true);
				continue;
			}
			if (IPDEConstants.ALIAS_MANIFEST.equals(preq.getAlias())) {
				Attribute rt = preq.getReferencedAttribute(cspec, ctx);
				if (rt == null)
					continue;

				manifestPath = AbstractActor.getSingleAttributePath(ctx, rt, true);
				continue;
			}
			if (IPDEConstants.ALIAS_LICENSE_MANIFEST.equals(preq.getAlias())) {
				Attribute rt = preq.getReferencedAttribute(cspec, ctx);
				if (rt == null)
					continue;

				licenseManifestPath = AbstractActor.getSingleAttributePath(ctx, rt, true);
				continue;
			}
			if (IBuildEntry.BIN_INCLUDES.equals(preq.getAlias())) {
				binIncludes = preq.getReferencedAttribute(cspec, ctx);
				continue;
			}
			if (IPDEConstants.ALIAS_LICENSE_FEATURE_CONTENTS.equals(preq.getAlias())) {
				licenseFeatureContents = preq.getReferencedAttribute(cspec, ctx);
				continue;
			}
		}

		if (licenseFeaturePath == null)
			throw new MissingPrerequisiteException(action, IPDEConstants.ALIAS_LICENSE_FEATURE);

		if (manifestPath == null)
			throw new MissingPrerequisiteException(action, IPDEConstants.ALIAS_MANIFEST);

		if (!outputPath.hasTrailingSeparator())
			throw new IllegalArgumentException(NLS.bind(org.eclipse.buckminster.core.Messages.output_of_action_0_must_be_folder,
					action.getQualifiedName()));

		try {
			File outputDir = outputPath.toFile().getAbsoluteFile();
			outputDir.mkdirs();

			Map<String, ? extends Object> props = ctx.getProperties();
			File licenseFeatureDir;
			File licenseFeatureFile = licenseFeaturePath.toFile().getAbsoluteFile();
			if (licenseFeatureFile.isDirectory()) {
				licenseFeatureDir = licenseFeatureFile;
			} else {
				licenseFeatureDir = new File(new File(props.get(KeyConstants.ACTION_TEMP).toString()), "licenseFeature"); //$NON-NLS-1$
				licenseFeatureDir.mkdirs();
				FileUtils.unzipFile(licenseFeatureFile, licenseFeatureDir);
			}

			File payloadFeatureDir;
			File payloadFeatureFile = ctx.getComponentLocation().toFile();
			if (payloadFeatureFile.isDirectory()) {
				payloadFeatureDir = payloadFeatureFile;
			} else {
				payloadFeatureDir = new File(new File(props.get(KeyConstants.ACTION_TEMP).toString()), "payloadFeature"); //$NON-NLS-1$
				payloadFeatureDir.mkdirs();
				FileUtils.unzipFile(payloadFeatureFile, payloadFeatureDir);
			}

			if (binIncludes != null)
				copyBinIncludes(binIncludes, payloadFeatureDir, outputPath, ctx, monitor);

			if (licenseFeatureContents != null)
				copyBinIncludes(licenseFeatureContents, licenseFeatureDir, outputPath, ctx, monitor);

			EditableFeatureModel payloadFeatureModel = new EditableFeatureModel(manifestPath.append(IPDEConstants.FEATURE_MANIFEST).toFile());
			payloadFeatureModel.load();
			EditableFeatureModel licenseFeatureModel = new EditableFeatureModel(licenseManifestPath.append(IPDEConstants.FEATURE_MANIFEST).toFile());
			licenseFeatureModel.load();

			IFeature payloadFeature = payloadFeatureModel.getFeature();
			IFeature licenseFeature = licenseFeatureModel.getFeature();
			IFeatureInfo info = licenseFeature.getFeatureInfo(IFeature.INFO_LICENSE);
			if (info != null)
				payloadFeature.setFeatureInfo(info, IFeature.INFO_LICENSE);
			info = licenseFeature.getFeatureInfo(IFeature.INFO_COPYRIGHT);
			if (info != null)
				payloadFeature.setFeatureInfo(info, IFeature.INFO_COPYRIGHT);
			payloadFeature.setLicenseFeatureID(null);
			payloadFeature.setLicenseFeatureVersion(null);

			payloadFeatureModel.save(new File(outputDir, IPDEConstants.FEATURE_MANIFEST));
			mergeProperties(licenseFeatureDir, payloadFeatureDir, outputDir);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}

		return Status.OK_STATUS;
	}

	private void mergeProperties(File licenseFeatureDir, File payloadFeatureDir, File targetDir) throws CoreException, IOException {
		// Copy all properties from payload to target
		File[] payloadPropertyFiles = payloadFeatureDir.listFiles(featurePropertyFiles);
		for (File payloadPropertyFile : payloadPropertyFiles)
			org.eclipse.buckminster.core.helpers.FileUtils.copyFile(payloadPropertyFile, targetDir, payloadPropertyFile.getName(), null);

		// Merge license properties
		File[] licensePropertyFiles = licenseFeatureDir.listFiles(featurePropertyFiles);
		for (File licensePropertyFile : licensePropertyFiles) {
			File featurePropertyFile = new File(targetDir, licensePropertyFile.getName());
			if (featurePropertyFile.exists()) {
				Properties licenseProperties = loadFile(licensePropertyFile);
				Properties featureProperties = loadFile(featurePropertyFile);
				Enumeration<?> licenseKeys = licenseProperties.keys();

				// Check for conflicting properties
				while (licenseKeys.hasMoreElements()) {
					String licenseKey = (String) licenseKeys.nextElement();
					if (featureProperties.containsKey(licenseKey)) {
						throw BuckminsterException.fromMessage(NLS.bind(Messages.error_conflictingProperties, new String[] { licenseKey,
								licensePropertyFile.getAbsolutePath(), featurePropertyFile.getAbsolutePath() }));
					}
				}
			}

			// Now append (or create) necessary feature_*.properties files
			FileWriter featurePropertyWriter = null;
			FileReader licensePropertyReader = new FileReader(licensePropertyFile);
			try {
				featurePropertyWriter = new FileWriter(featurePropertyFile, true);
				char[] buffer = new char[0x800];
				int bytesRead = licensePropertyReader.read(buffer);
				while (bytesRead > -1) {
					featurePropertyWriter.write(buffer, 0, bytesRead);
					bytesRead = licensePropertyReader.read(buffer);
				}
			} finally {
				IOUtils.close(featurePropertyWriter);
				IOUtils.close(licensePropertyReader);
			}
		}
	}
}

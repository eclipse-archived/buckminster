package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.build.IBuildEntry;
import org.osgi.framework.Constants;

public class SourceBundleConsolidator extends BundleConsolidator
{
	public SourceBundleConsolidator(File inputFile, File outputFile, File propertiesFile, String qualifier)
			throws CoreException
	{
		super(inputFile, outputFile, propertiesFile, qualifier);
	}

	/*
	 * Bundle-SymbolicName: org.eclipse.core.filebuffers.source Bundle-ManifestVersion: 2 Bundle-Name: %pluginName
	 * Bundle-Localization: plugin Bundle-Version: 3.5.0.N20090216-2000 Eclipse-SourceBundle:
	 * org.eclipse.core.filebuffers;version="3.5.0.N200
	 */
	@Override
	protected boolean treatManifest(Manifest manifest, String symbolicName, IVersion version)
	{
		if(symbolicName == null || version == null)
		{
			return false;
		}

		Attributes attributes = manifest.getMainAttributes();
		String manifestVersion = attributes.getValue(Attributes.Name.MANIFEST_VERSION);
		String bundleManifestVersion = attributes.getValue(Constants.BUNDLE_MANIFESTVERSION);
		String bundleName = attributes.getValue(Constants.BUNDLE_NAME);
		String bundleLocalization = attributes.getValue(Constants.BUNDLE_LOCALIZATION);

		if(bundleName != null && bundleName.length() > 0)
		{
			// If the bundleName starts with '%', then the translation is
			// done in plugin.properties
			//
			if(bundleName.charAt(0) != '%')
				bundleName = bundleName + " Source"; //$NON-NLS-1$
		}

		manifest.clear(); // Since we don't want all dependencies, exports, etc.

		String src = (String)getProperties().get(IBuildEntry.SRC_INCLUDES);
		if(src == null)
			src = "."; //$NON-NLS-1$

		attributes.putValue(Attributes.Name.MANIFEST_VERSION.toString(), manifestVersion);
		attributes.putValue(Constants.BUNDLE_SYMBOLICNAME, symbolicName + ".source"); //$NON-NLS-1$
		attributes.putValue(IPDEConstants.MANIFEST_HEADER_SOURCE_BUNDLE, symbolicName + ";roots=\"" + src + "\""); //$NON-NLS-1$//$NON-NLS-2$
		attributes.putValue(Constants.BUNDLE_VERSION, version.toString());
		attributes.putValue(Constants.BUNDLE_MANIFESTVERSION, bundleManifestVersion);
		if(bundleName != null)
			attributes.putValue(Constants.BUNDLE_NAME, bundleName);
		if(bundleLocalization != null)
			attributes.putValue(Constants.BUNDLE_LOCALIZATION, bundleLocalization);
		return true;
	}
}

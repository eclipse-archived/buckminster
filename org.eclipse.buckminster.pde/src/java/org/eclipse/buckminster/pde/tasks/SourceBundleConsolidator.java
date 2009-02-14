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

	@Override
	protected boolean treatManifest(Manifest manifest, String symbolicName, IVersion version)
	{
		if(symbolicName == null || version == null)
		{
			return false;
		}

		Attributes attributes = manifest.getMainAttributes();
		String src = (String)getProperties().get(IBuildEntry.SRC_INCLUDES);
		if(src == null)
		{
			src = "."; //$NON-NLS-1$
		}
		// set our symbolic name
		attributes.putValue(Constants.BUNDLE_SYMBOLICNAME, symbolicName + ".source"); //$NON-NLS-1$
		// declare to be a source plugin
		attributes.putValue(IPDEConstants.MANIFEST_HEADER_SOURCE_BUNDLE, symbolicName + ";roots=\"" + src + "\""); //$NON-NLS-1$//$NON-NLS-2$

		return true;
	}
}

package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.model.common.util.BMProperties;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.Version;
import org.osgi.framework.Constants;

public class SourceBundleConsolidator extends BundleConsolidator {
	private static final String LABEL_FORMAT = "{0} Source"; //$NON-NLS-1$

	public SourceBundleConsolidator(File inputFile, File outputFile, File propertiesFile, String qualifier) throws CoreException {
		super(inputFile, outputFile, propertiesFile, qualifier);
	}

	/*
	 * Bundle-SymbolicName: org.eclipse.core.filebuffers.source
	 * Bundle-ManifestVersion: 2 Bundle-Name: %pluginName Bundle-Localization:
	 * plugin Bundle-Version: 3.5.0.N20090216-2000 Eclipse-SourceBundle:
	 * org.eclipse.core.filebuffers;version="3.5.0.N200
	 */
	@Override
	protected boolean treatManifest(Manifest manifest, String symbolicName, Version version) throws IOException {
		if (symbolicName == null || version == null) {
			return false;
		}

		IActionContext ctx = AbstractActor.getActiveContext();
		Attributes attributes = manifest.getMainAttributes();
		String manifestVersion = Trivial.trim(attributes.getValue(Attributes.Name.MANIFEST_VERSION));
		String bundleManifestVersion = Trivial.trim(attributes.getValue(Constants.BUNDLE_MANIFESTVERSION));
		String bundleName = Trivial.trim(attributes.getValue(Constants.BUNDLE_NAME));
		String vendor = Trivial.trim(attributes.getValue(Constants.BUNDLE_VENDOR));
		String bundleLocalization = attributes.getValue(Constants.BUNDLE_LOCALIZATION);

		Map<String, String> translations = Collections.emptyMap();
		if ("plugin".equals(bundleLocalization)) { //$NON-NLS-1$
			InputStream inStream = null;
			try {
				// We need to get hold of the feature.properties from the
				// original source
				inStream = new BufferedInputStream(new FileInputStream(new File(ctx.getComponentLocation().toFile(), "plugin.properties"))); //$NON-NLS-1$
				translations = new BMProperties(inStream);
			} catch (CoreException e) {
			} catch (FileNotFoundException e) {
			} finally {
				IOUtils.close(inStream);
			}
		}

		if (bundleName != null) {
			if (bundleName.charAt(0) == '%') {
				bundleName = translations.get(bundleName.substring(1));
				if (bundleName == null)
					bundleName = symbolicName;
			}
			String format = ctx.getProperties().get(IPDEConstants.PROP_PDE_SOURCE_BUNDLE_LABEL_FORMAT);
			if (format == null)
				format = LABEL_FORMAT;
			bundleName = MessageFormat.format(format, bundleName);
		}

		if (vendor != null && vendor.charAt(0) == '%')
			vendor = translations.get(vendor.substring(1));
		manifest.clear(); // Since we don't want all dependencies, exports, etc.

		String src = (String) getProperties().get("src.additionalRoots"); //$NON-NLS-1$
		if (src == null)
			src = "."; //$NON-NLS-1$

		String versionStr = version.toString();
		attributes.putValue(Constants.BUNDLE_SYMBOLICNAME, symbolicName + ".source;singleton:=true"); //$NON-NLS-1$
		attributes.putValue(IPDEConstants.MANIFEST_HEADER_SOURCE_BUNDLE, symbolicName + ";version=\"" + versionStr + "\";roots=\"" + src + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		attributes.putValue(Constants.BUNDLE_VERSION, versionStr);

		if (manifestVersion != null)
			attributes.putValue(Attributes.Name.MANIFEST_VERSION.toString(), manifestVersion);
		if (bundleManifestVersion != null)
			attributes.putValue(Constants.BUNDLE_MANIFESTVERSION, bundleManifestVersion);
		if (bundleName != null)
			attributes.putValue(Constants.BUNDLE_NAME, bundleName);
		if (vendor != null)
			attributes.putValue(Constants.BUNDLE_VENDOR, vendor);
		return true;
	}
}

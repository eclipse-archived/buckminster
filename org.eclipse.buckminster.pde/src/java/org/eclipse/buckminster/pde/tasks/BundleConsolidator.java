/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.osgi.util.ManifestElement;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;

/**
 * @author Thomas Hallgren
 * 
 */
public class BundleConsolidator extends VersionConsolidator {
	private final byte[] bytes;

	public BundleConsolidator(File inputFile, File outputFile, File propertiesFile, String qualifier) throws CoreException {
		super(outputFile, propertiesFile, qualifier);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		InputStream input = null;
		try {
			input = new FileInputStream(inputFile);
			IOUtils.copy(input, output, null);
		} catch (IOException e) {
			throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_manifest_from_0, inputFile));
		} finally {
			IOUtils.close(input);
		}
		bytes = output.toByteArray();
	}

	public void run() throws IOException {
		Manifest manifest = new Manifest(new ByteArrayInputStream(bytes));
		Attributes a = manifest.getMainAttributes();
		String symbolicName = a.getValue(Constants.BUNDLE_SYMBOLICNAME);
		String id = null;
		Version newVersion = null;
		boolean changed = false;
		if (symbolicName != null) {
			try {
				ManifestElement[] elements = ManifestElement.parseHeader(Constants.BUNDLE_SYMBOLICNAME, symbolicName);
				id = elements[0].getValue();
			} catch (BundleException be) {
				throw new IOException(be.getMessage());
			}

			String versionStr = a.getValue(Constants.BUNDLE_VERSION);
			if (versionStr != null) {
				try {
					Version version = Version.parseVersion(versionStr);
					ComponentIdentifier ci = new ComponentIdentifier(id, IComponentType.OSGI_BUNDLE, version);
					newVersion = replaceQualifier(ci, Collections.<ComponentIdentifier> emptyList());

					if (!(newVersion == null || version.equals(newVersion))) {
						a.put(new Attributes.Name(Constants.BUNDLE_VERSION), newVersion.toString());
						changed = true;
					}
				} catch (IllegalArgumentException e) {
				}
			}
		}

		// mind the order of the operands
		changed = treatManifest(manifest, id, newVersion) || changed;

		OutputStream out = null;
		try {
			out = new FileOutputStream(getOutputFile());
			if (changed) {
				out = new BufferedOutputStream(out);
				manifest.write(out);
			} else
				out.write(bytes);
		} finally {
			IOUtils.close(out);
		}
	}

	/**
	 * Subclasses may override and treat the manifest in whichever way they
	 * like, as long as it stays valid.
	 * 
	 * @param manifest
	 *            The manifest to treat. Never null.
	 * @param symbolicName
	 *            The symbolicName of the bundle, aka bundle ID. May be null.
	 * @param version
	 *            The (new) version of the bundle. May be null.
	 * @return Whether the method has changed the manifest or not.
	 */
	protected boolean treatManifest(Manifest manifest, String symbolicName, Version version) {
		// empty default implementation
		return false;
	}
}

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
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.cspecgen.CSpecGenerator;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.metadata.VersionedId;
import org.eclipse.osgi.util.ManifestElement;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;

/**
 * @author Thomas Hallgren
 * 
 */
public class BundleConsolidator extends VersionConsolidator {
	public static IVersionedId getVersionedId(Manifest manifest) throws BundleException {
		Attributes a = manifest.getMainAttributes();
		String symbolicName = a.getValue(Constants.BUNDLE_SYMBOLICNAME);
		if (symbolicName == null)
			return null;

		ManifestElement[] elements = ManifestElement.parseHeader(Constants.BUNDLE_SYMBOLICNAME, symbolicName);
		String id = elements[0].getValue();
		Version version = null;
		String versionStr = a.getValue(Constants.BUNDLE_VERSION);
		if (versionStr != null) {
			try {
				version = Version.parseVersion(versionStr);
			} catch (IllegalArgumentException e) {
			}
		}
		return new VersionedId(id, version);

	}

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
		changed = fixRequiredBundleVersions(a) || changed;

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

	protected boolean fixRequiredBundleVersions(Attributes a) throws IOException {
		String requiredBundles = a.getValue(Constants.REQUIRE_BUNDLE);
		if (requiredBundles == null)
			return false;

		IActionContext ctx = AbstractActor.getActiveContext();
		Map<String, ? extends Object> props = ctx.getProperties();
		if (props == null)
			return false;

		if (!VersionConsolidator.getBooleanProperty(props, IPDEConstants.PROP_PDE_BUNDLE_RANGE_GENERATION,
				IPDEConstants.PDE_BUNDLE_RANGE_GENERATION_DEFAULT))
			return false;

		boolean retainLowerBound = VersionConsolidator.getBooleanProperty(props, IPDEConstants.PROP_PDE_MATCH_RULE_RETAIN_LOWER, false);
		int pdeMatchRule = IMatchRules.EQUIVALENT;

		String dfltMatchRule = (String) props.get(IPDEConstants.PROP_PDE_MATCH_RULE_DEFAULT);
		if (dfltMatchRule != null)
			pdeMatchRule = CSpecGenerator.getMatchRule(dfltMatchRule);

		if (pdeMatchRule == IMatchRules.NONE)
			return false;

		boolean changed = false;
		StringBuilder bld = new StringBuilder();

		try {
			boolean firstElement = true;
			for (ManifestElement element : ManifestElement.parseHeader(Constants.REQUIRE_BUNDLE, requiredBundles)) {
				if (firstElement)
					firstElement = false;
				else
					bld.append(',');
				bld.append(element);
				if (element.getAttribute(Constants.BUNDLE_VERSION_ATTRIBUTE) != null)
					continue;

				Version v = null;
				try {
					CSpec cspec = ctx.getGlobalContext().findCSpec(ctx.getCSpec(),
							new ComponentRequest(element.getValue(), IComponentType.OSGI_BUNDLE, null));
					v = cspec.getVersion();
				} catch (CoreException e) {
					continue;
				}
				if (v == null || v.equals(Version.emptyVersion))
					continue;

				VersionRange range = CSpecGenerator.createRuleBasedRange(pdeMatchRule, retainLowerBound, v);
				changed = true;
				bld.append(';');
				bld.append(Constants.BUNDLE_VERSION_ATTRIBUTE);
				bld.append('=');
				bld.append('"');
				bld.append(range);
				bld.append('"');
			}
		} catch (BundleException be) {
			throw new IOException(be.getMessage(), be);
		}
		if (changed)
			a.putValue(Constants.REQUIRE_BUNDLE, bld.toString());
		return changed;
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

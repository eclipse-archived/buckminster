/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.version;

import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.rmap.model.BidirectionalTransformer;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.IVersionFormat;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractConverter extends AbstractExtension implements IVersionConverter {
	private static final BidirectionalTransformer[] noTransformers = new BidirectionalTransformer[0];

	private BidirectionalTransformer[] transformers = noTransformers;

	private IVersionFormat versionFormat = getDefaultVersionFormat();

	public IVersionFormat getVersionFormat() {
		return versionFormat;
	}

	/**
	 * Assigns the transformer used when converting between plain versions and a
	 * version component.
	 * 
	 * @param transformer
	 */
	public final void setTransformers(BidirectionalTransformer[] transformers) {
		transformers = transformers == null ? noTransformers : transformers;
	}

	/**
	 * Assigns the version type that is used when creating versions
	 * 
	 * @param versionFormat
	 */
	public final void setVersionFormat(IVersionFormat versionFormat) {
		versionFormat = versionFormat == null ? getDefaultVersionFormat() : versionFormat;
	}

	/**
	 * Converts the version into a selector component, i.e. a branch or a branch
	 * qualifier such as a tag, timestamp, or change number.
	 * 
	 * @param source
	 *            The version to convert.
	 * @return The converted result or <code>null</code> if <code>source</code>
	 *         did not match the <code>from</code> pattern of any of the
	 *         contained transformers.
	 * @throws CoreException
	 */
	protected String createSelectorComponent(Version source) throws CoreException {
		String result = source.toString();
		if (transformers.length > 0) {
			boolean matchFound = false;
			for (BidirectionalTransformer transformer : transformers) {
				String transformed = transformer.transformFrom(result);
				if (transformed != null) {
					matchFound = true;
					result = transformed;
				}
			}
			if (!matchFound)
				return null;
		}
		return result;
	}

	/**
	 * Converts a selector component, i.e. a branch or tag into a version
	 * 
	 * @param versionType
	 *            The version type for the version to create.
	 * @param source
	 *            The selector component to convert.
	 * @return The converted result or <code>null</code> if <code>source</code>
	 *         did not match the <code>to</code> pattern of any of the contained
	 *         transformers.
	 * @throws CoreException
	 */
	protected Version createVersionFromSelectorComponent(String source) throws CoreException {
		if (transformers.length > 0) {
			boolean matchFound = false;
			for (BidirectionalTransformer transformer : transformers) {
				String transformed = transformer.transformTo(source);
				if (transformed != null) {
					matchFound = true;
					source = transformed;
				}
			}
			if (!matchFound)
				return null;
		}
		try {
			return versionFormat.parse(source);
		} catch (IllegalArgumentException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	protected abstract IVersionFormat getDefaultVersionFormat();
}

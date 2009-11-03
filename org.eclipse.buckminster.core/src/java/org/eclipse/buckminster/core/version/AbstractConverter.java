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
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionFormat;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public abstract class AbstractConverter extends AbstractExtension implements IVersionConverter
{
	private static final BidirectionalTransformer[] s_noTransformers = new BidirectionalTransformer[0];

	private BidirectionalTransformer[] m_transformers = s_noTransformers;

	private VersionFormat m_versionFormat = getDefaultVersionFormat();

	public VersionFormat getVersionFormat()
	{
		return m_versionFormat;
	}

	/**
	 * Assigns the transformer used when converting between plain versions and a version component.
	 * 
	 * @param transformer
	 */
	public final void setTransformers(BidirectionalTransformer[] transformers)
	{
		m_transformers = transformers == null
				? s_noTransformers
				: transformers;
	}

	/**
	 * Assigns the version type that is used when creating versions
	 * 
	 * @param versionFormat
	 */
	public final void setVersionFormat(VersionFormat versionFormat)
	{
		m_versionFormat = versionFormat == null
				? getDefaultVersionFormat()
				: versionFormat;
	}

	/**
	 * Converts the version into a selector component, i.e. a branch or a branch qualifier such as a tag, timestamp, or
	 * change number.
	 * 
	 * @param source
	 *            The version to convert.
	 * @return The converted result or <code>null</code> if <code>source</code> did not match the <code>from</code>
	 *         pattern of any of the contained transformers.
	 * @throws CoreException
	 */
	protected String createSelectorComponent(Version source) throws CoreException
	{
		String result = source.toString();
		if(m_transformers.length > 0)
		{
			boolean matchFound = false;
			for(BidirectionalTransformer transformer : m_transformers)
			{
				String transformed = transformer.transformFrom(result);
				if(transformed != null)
				{
					matchFound = true;
					result = transformed;
				}
			}
			if(!matchFound)
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
	 * @return The converted result or <code>null</code> if <code>source</code> did not match the <code>to</code>
	 *         pattern of any of the contained transformers.
	 * @throws CoreException
	 */
	protected Version createVersionFromSelectorComponent(String source) throws CoreException
	{
		if(m_transformers.length > 0)
		{
			boolean matchFound = false;
			for(BidirectionalTransformer transformer : m_transformers)
			{
				String transformed = transformer.transformTo(source);
				if(transformed != null)
				{
					matchFound = true;
					source = transformed;
				}
			}
			if(!matchFound)
				return null;
		}
		try
		{
			return m_versionFormat.parse(source);
		}
		catch(IllegalArgumentException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	protected abstract VersionFormat getDefaultVersionFormat();
}

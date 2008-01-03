/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.common.model;



/**
 * @author Thomas Hallgren
 */
public class TaggedRxPattern extends RxPattern
{
	public static final String TAGGED_PREFIX = "tagged.";
	private final String m_tag;

	public TaggedRxPattern(String tag, String name, boolean optional, String pattern, String prefix, String suffix)
	{
		super(name, optional, pattern, prefix, suffix);
		m_tag = tag;
	}

	@Override
	public String getDefaultTag()
	{
		return m_tag;
	}

	@Override
	public boolean isTagged()
	{
		return true;
	}
}

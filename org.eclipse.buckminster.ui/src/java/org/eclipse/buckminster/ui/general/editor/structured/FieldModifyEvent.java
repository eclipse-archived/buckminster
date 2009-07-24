/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.structured;

/**
 * Event sent when a field was modified
 * 
 * @author Karel Brezina
 */
public class FieldModifyEvent
{
	private final Object m_originalEvent;

	public FieldModifyEvent(Object originalEvent)
	{
		this.m_originalEvent = originalEvent;
	}

	public Object getOriginalEvent()
	{
		return m_originalEvent;
	}
}

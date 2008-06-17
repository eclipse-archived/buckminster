// TODO: Remove license that should not be used
/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/
/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.equinox.p2.authoring.internal;

/**
 * A ModelPart that supports setting an event bus where the model sends {@link ModelChangeEvent } events when
 * some part of the model has changed.
 * The intended use is to create a model with a ModelRoot where children are ModelPart instances. When change occurs,
 * the ModelPart instances call {@link ModelPart#notifyChanged()} and the event bubbles up to the root (this class).
 * 
 * @author Henrik Lindberg
 *
 */
public class ModelRoot extends ModelPart
{
	private IEditorEventBus m_eventBus;
	private boolean m_changed = false;
	private ModelChangeEvent m_changeEvent = new ModelChangeEvent(this);
	
	/**
	 * Returns if this model has changed since the change flag was last cleared.
	 * @return true if some part of the model has changed since flag was last cleared.
	 */
	public boolean isChanged()
	{
		return m_changed;
	}
	/**
	 * Sets the model change flag and notifies registered listeners.
	 * @param changed
	 */
	public void setChanged(boolean changed)
	{
		m_changed = changed;
		if(m_eventBus != null)
			m_eventBus.publishEvent(m_changeEvent);
	}
	/**
	 * Returns the event bus in use for this model object. 
	 * @return
	 */
	public IEditorEventBus getEventBus()
	{
		return m_eventBus;
	}
	/**
	 * Registers the event bus to use for this model object. Events from the model are sent to this bus. (And if no
	 * bus is registers, no events are sent).
	 * As a side effect, the changed flag is set to false, but no notification is sent.
	 * @param eventBus
	 */
	public void setEventBus(IEditorEventBus eventBus)
	{
		m_eventBus = eventBus;
		m_changed = false;
	}
	/**
	 * Typically called from model parts of the overall model (i.e. getParent().notifyChanged()) to notify that a sub part
	 * of the model has changed. This implementation sets the change flag of the model using {@link #setChanged(boolean) }
	 * with true as an argument.
	 */
	@Override
	public void notifyChanged()
	{
		setChanged(true);		
	}
}
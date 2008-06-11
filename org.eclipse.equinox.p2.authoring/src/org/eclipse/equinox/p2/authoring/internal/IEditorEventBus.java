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
import java.util.EventObject;

import org.eclipse.osgi.framework.eventmgr.EventDispatcher;

/**
 * A bus for events in an editor. Use this eventbus as a central event mechanism in an editor instead of creating
 * a tangled web of listeners. 
 * 
 * @author Henrik Lindberg
 *
 */

public interface IEditorEventBus extends EventDispatcher {

	public abstract void addListener(IEditorListener listener);

	public abstract void removeListener(IEditorListener listener);

	public abstract void publishEvent(EventObject event);

	public abstract void close();

}
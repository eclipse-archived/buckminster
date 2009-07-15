/*******************************************************************************
 * Copyright (c) 2009 Johannes Utzig.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Johannes Utzig - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.ui.dependency.visualizer.controls.listener;

import org.eclipse.buckminster.ui.dependency.visualizer.controls.AbstractViewerSettingControl;

/**
 * IViewerSettingChangeListeners can be registered on {@link AbstractViewerSettingControl}s.
 * <p>
 * Clients that are interested in setting change events for the dependency visualization need to implement this
 * interface.
 * 
 * @author Johannes Utzig
 * 
 */
public interface IViewerSettingChangeListener
{
	void viewerSettingChanged(ViewerSettingChangeEvent event);

}

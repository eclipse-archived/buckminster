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
package org.eclipse.buckminster.ui.dependency.visualizer.controls;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.buckminster.ui.dependency.visualizer.DependencyVisualizer;
import org.eclipse.buckminster.ui.dependency.visualizer.controls.listener.IViewerSettingChangeListener;
import org.eclipse.buckminster.ui.dependency.visualizer.controls.listener.ViewerSettingChangeEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * common base class for the sections of the 'settings' section in the
 * {@link DependencyVisualizer} editor
 * 
 * @author Johannes Utzig
 * 
 */
public abstract class AbstractViewerSettingControl {

	private FormToolkit toolkit;

	private Set<IViewerSettingChangeListener> listeners = new HashSet<IViewerSettingChangeListener>();

	public AbstractViewerSettingControl(FormToolkit toolkit) {
		super();
		this.toolkit = toolkit;
	}

	/**
	 * adds a {@link IViewerSettingChangeListener} to this control.
	 * <p>
	 * does nothing if an identical listener has already been registered.
	 * 
	 * @param listener
	 */
	public void addViewerSettingChangeListener(IViewerSettingChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * Clients should override this method to have their controls created.
	 * <p>
	 * The given composite will usually be a {@link Section} with
	 * {@link FillLayout} applied.
	 * 
	 * @param parent
	 * @return the only children of the parent
	 */
	public abstract Control createControl(Composite parent);

	/**
	 * 
	 * @return the toolkit that should be used to create the widgets within this
	 *         control
	 */
	public FormToolkit getWidgetToolkit() {
		return toolkit;
	}

	/**
	 * removes a {@link IViewerSettingChangeListener} from this control.
	 * <p>
	 * does nothing if an identical listener has already been registered.
	 * 
	 * @param listener
	 */
	public void removeViewerSettingChangeListener(IViewerSettingChangeListener listener) {
		listeners.remove(listener);
	}

	/**
	 * informs the registered {@link IViewerSettingChangeListener} about setting
	 * changes
	 * 
	 * @param event
	 */
	protected void fireViewerSettingsChangedEvent(ViewerSettingChangeEvent event) {
		for (IViewerSettingChangeListener listener : listeners) {
			listener.viewerSettingChanged(event);

		}
	}

}

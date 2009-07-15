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

public class ViewerSettingChangeEvent
{

	private Object source;

	private ViewerSettingType type;

	private Object data;

	private Object old;

	/**
	 * 
	 * @param source
	 *            - the component that triggered the event or <code>null</code>
	 * @param type
	 *            - the type of the event. Must <b>not</b> be <code>null</code>
	 * @param data
	 *            - the new data or <code>null</code>
	 * @param old
	 *            - the old data or <code>null</code>
	 * @see ViewerSettingType
	 * @see IViewerSettingChangeListener
	 */
	public ViewerSettingChangeEvent(Object source, ViewerSettingType type, Object data, Object old)
	{
		super();
		this.source = source;
		this.type = type;
		this.data = data;
		this.old = old;
	}

	/**
	 * 
	 * @return the new value of the setting or <code>null</code>
	 * @see ViewerSettingChangeEvent#getType()
	 */
	public Object getData()
	{
		return data;
	}

	/**
	 * 
	 * @return the value if the setting before the change or <code>null</code>
	 * @see ViewerSettingChangeEvent#getType()
	 */
	public Object getOld()
	{
		return old;
	}

	/**
	 * 
	 * @return the component that triggered the event or <code>null</code>
	 */
	public Object getSource()
	{
		return source;
	}

	/**
	 * 
	 * @return the {@link ViewerSettingType} of the event
	 */
	public ViewerSettingType getType()
	{
		return type;
	}

}

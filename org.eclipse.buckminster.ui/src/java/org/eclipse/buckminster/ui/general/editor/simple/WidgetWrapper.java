/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.simple;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

/**
 * @author Karel Brezina
 * 
 */
public class WidgetWrapper implements IWidgetin {
	private final Widget widget;

	public WidgetWrapper(Widget widget) {
		this.widget = widget;
	}

	@Override
	public void addListener(int eventType, Listener listener) {
		widget.addListener(eventType, listener);
	}

	@Override
	public Object getData() {
		return widget.getData();
	}

	public Widget getWidget() {
		return widget;
	}

	@Override
	public void notifyListeners(int eventType, Event event) {
		widget.notifyListeners(eventType, event);
	}

	@Override
	public void removeListener(int eventType, Listener listener) {
		widget.removeListener(eventType, listener);
	}

	@Override
	public void setData(Object data) {
		widget.setData(data);
	}
}

/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ui.internal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class LabeledCombo extends Composite {
	private final Label label;

	private final Combo combo;

	public LabeledCombo(Composite parent, int style) {
		super(parent, style);
		GridLayout gd = new GridLayout(2, false);
		gd.marginWidth = gd.marginHeight = 0;
		this.setLayout(gd);
		label = new Label(this, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		combo = new Combo(this, style);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	}

	public void add(String string) {
		combo.add(string);
	}

	public void add(String string, int index) {
		combo.add(string, index);
	}

	public void addSelectionListener(SelectionListener listener) {
		combo.addSelectionListener(listener);
	}

	public String getItem(int index) {
		return combo.getItem(index);
	}

	public Point getSelection() {
		return combo.getSelection();
	}

	public int getSelectionIndex() {
		return combo.getSelectionIndex();
	}

	public int indexOf(String string) {
		return combo.indexOf(string);
	}

	public void removeSelectionListener(SelectionListener listener) {
		combo.removeSelectionListener(listener);
	}

	public void select(int index) {
		combo.select(index);
	}

	@Override
	public void setEnabled(boolean flag) {
		label.setEnabled(flag);
		combo.setEnabled(flag);
		super.setEnabled(flag);
	}

	public void setItems(String[] items) {
		combo.setItems(items);
	}

	public void setLabel(String labelText) {
		label.setText(labelText);
	}
}

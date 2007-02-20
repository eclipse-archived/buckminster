/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui;


import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class UiUtils
{
	public static void openError(Shell shell, String title, Throwable t)
	{
		CoreException c = BuckminsterException.wrap(t);
		ErrorDialog.openError(shell, title, null, c.getStatus());
	}

	public static Button createCheckButton(Composite parent, String key, SelectionListener listener)
	{
		Button button = new Button(parent, SWT.CHECK);
		if(key != null)
			button.setText(JFaceResources.getString(key));
		button.setFont(parent.getFont());
		if(listener != null)
			button.addSelectionListener(listener);
		return button;
	}

	public static LabeledCombo createEnumCombo(Composite parent, String labelText, Enum<?>[] values, SelectionListener listener)
	{
		LabeledCombo combo = new LabeledCombo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
		combo.setLabel(labelText);
		for(Enum<?> value : values)
			combo.add(value.toString());
		combo.select(0);
		if(listener != null)
			combo.addSelectionListener(listener);
		return combo;
	}

	public static Text createLabeledText(Composite parent, String label, int style, ModifyListener listener)
	{
		final Label lbl = new Label(parent, SWT.NONE);
		lbl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		lbl.setText(label);

		Text text = new Text(parent, SWT.BORDER | style)
		{
			@Override
			public void setEnabled(boolean flag)
			{
				lbl.setEnabled(flag);
				super.setEnabled(flag);
			}

			@Override
			protected void checkSubclass()
			{
			}
		};
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		if(listener != null)
			text.addModifyListener(listener);
		return text;
	}

	public static ListViewer createListViewer(Composite c, String[] lines, Font font, int maxHeightHintInLines, int maxWidthInChars)
	{
		ListViewer lv = new ListViewer(c, SWT.V_SCROLL | SWT.BORDER | SWT.H_SCROLL);
		org.eclipse.swt.widgets.List l = lv.getList();
		l.setFont(font);
		int maxWidthSeen = 0;
		for(String s : lines)
		{
			if(s.length() > maxWidthSeen)
				maxWidthSeen = s.length();
			l.add(s);
		}		
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = l.getItemHeight()
				* (lines.length < maxHeightHintInLines ? lines.length : maxHeightHintInLines);
		GC gc = new GC(l);
//		gc.setFont(l.getFont());
		int avgCharWidth = gc.getFontMetrics().getAverageCharWidth();
		gc.dispose();
		gd.widthHint = avgCharWidth * (maxWidthSeen < maxWidthInChars ? maxWidthSeen : maxWidthInChars);
		l.setLayoutData(gd);
		return lv;
	}
	
	public static Button createPushButton(Composite parent, String key, SelectionListener listener)
	{
		Button button = new Button(parent, SWT.PUSH);
		if(key != null)
			button.setText(JFaceResources.getString(key));
		button.setFont(parent.getFont());
		if(listener != null)
			button.addSelectionListener(listener);
		return button;
	}

	public static void setChildrenVisible(Composite composite, boolean visible)
	{
		for(Control c : composite.getChildren())
			c.setVisible(visible);
	}

	public static void setEnabledRecursively(Control c, boolean enabled)
	{
		c.setEnabled(enabled);
		if(c instanceof Composite)
			for(Control child : ((Composite)c).getChildren())
				setEnabledRecursively(child, enabled);
	}

	public static void setExpandedRecursively(Object treeOrTreeItem, boolean expand)
	{
		TreeItem[] tia;
		if(treeOrTreeItem instanceof Tree)
			tia = ((Tree)treeOrTreeItem).getItems();
		else
			tia = new TreeItem[] { (TreeItem)treeOrTreeItem };

		for(TreeItem ti : tia)
		{
			ti.setExpanded(expand);
			for(TreeItem child : ti.getItems())
				setExpandedRecursively(child, expand);
		}
	}

	public static String trimmedValue(Text text)
	{
		String value = null;
		if(text != null)
		{
			value = text.getText().trim();
			if(value.length() == 0)
				value = null;
		}
		return value;
	}
}

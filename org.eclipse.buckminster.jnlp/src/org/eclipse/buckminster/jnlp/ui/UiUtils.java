/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.jnlp.ui;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class UiUtils
{
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

	public static Label createGridLabel(Composite parent, String string, int horizontalSpan, int widthHint, int style)
	{
		Label lbl = new Label(parent, style);
		GridData data = new GridData(SWT.FILL, SWT.CENTER, false, false);

		if(widthHint > 0)
		{
			data.widthHint = widthHint;
		}

		if(horizontalSpan > 1)
		{
			data.horizontalSpan = horizontalSpan;
		}

		lbl.setLayoutData(data);
		lbl.setText(string);

		return lbl;
	}

	public static Text createGridText(Composite parent, int horizontalSpan, int widthHint, ModifyListener listener,
			int style)
	{
		return createNoBorderGridText(parent, horizontalSpan, widthHint, listener, SWT.BORDER | style);
	}

	public static Text createNoBorderGridText(Composite parent, int horizontalSpan, int widthHint,
			ModifyListener listener, int style)
	{
		Text text = new Text(parent, style);
		GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);

		if(horizontalSpan > 0)
		{
			data.horizontalSpan = horizontalSpan;
		}

		if(widthHint > 0)
		{
			data.widthHint = widthHint;
		}

		text.setLayoutData(data);
		if(listener != null)
			text.addModifyListener(listener);

		return text;
	}
	
	public static Combo createGridCombo(Composite parent, int horizontalSpan, int widthHint,
			SelectionListener selectionListener, ModifyListener modifyListener, int style)
	{
		Combo combo = new Combo(parent, style);

		GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);

		if(horizontalSpan > 0)
		{
			data.horizontalSpan = horizontalSpan;
		}

		if(widthHint > 0)
		{
			data.widthHint = widthHint;
		}

		combo.setLayoutData(data);

		if(selectionListener != null)
		{
			combo.addSelectionListener(selectionListener);
		}

		if(modifyListener != null)
		{
			combo.addModifyListener(modifyListener);
		}

		return combo;
	}

	public static Combo createGridEnumCombo(Composite parent, int horizontalSpan, int widthHint, Enum<?>[] values,
			SelectionListener selectionListener, ModifyListener modifyListener, int style)
	{
		Combo combo = UiUtils.createGridCombo(parent, horizontalSpan, widthHint, selectionListener, modifyListener,
				style);
		for(Enum<?> value : values)
			combo.add(value.toString());
		combo.select(0);

		return combo;
	}

	public static Combo createGridArrayCombo(Composite parent, int horizontalSpan, int widthHint, String[] values,
			SelectionListener selectionListener, ModifyListener modifyListener, int style)
	{
		Combo combo = UiUtils.createGridCombo(parent, horizontalSpan, widthHint, selectionListener, modifyListener,
				style);
		combo.setItems(values);
		combo.select(0);

		return combo;
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
	
	public static String trimmedValue(String string)
	{
		String value = null;
		
		if(string != null)
		{
			value = string.trim();
			if(value.length() == 0)
				value = null;
		}
		return value;
	}
	
	public static String getNotNullString(String string)
	{
		return string == null ? "" : string; //$NON-NLS-1$
	}
}

/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor;

import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Karel Brezina
 *
 */
public class EditorUtils
{
	private EditorUtils()
	{
	}
	
	public static Composite getNamedTabComposite(Composite parent, String header)
	{
		Composite tabComposite = new Composite(parent, SWT.NONE);
		tabComposite.setLayout(new GridLayout(1, true));
		tabComposite.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		tabComposite.setBackgroundMode(SWT.INHERIT_FORCE);

		Label headerLabel = new Label(tabComposite, SWT.BOLD);
		headerLabel.setText(header);
		headerLabel.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));
		FontData fontData = new FontData();
		fontData.setHeight(14);
		headerLabel.setFont(new Font(tabComposite.getDisplay(), fontData));
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, false, false);
		gridData.heightHint = 30;
		headerLabel.setLayoutData(gridData);

		return tabComposite;
	}
	
	public static Label createHeaderLabel(Composite parent, String headerText, int horizontalSpan)
	{
		Label label = UiUtils.createGridLabel(parent, headerText, horizontalSpan, 0, SWT.NONE);
		label.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));

		return label;
	}
}

/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.structured;

import org.eclipse.buckminster.ui.general.editor.TableRowDialog;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

/**
 * @author Karel Brezina
 *
 */
public class TwoPagesTableEditor<T> extends StructuredTableEditor<T>
{
	class EditRowDialog extends TableRowDialog
	{
		public EditRowDialog(Shell parentShell, Image windowImage, String windowTitle, Image wizardImage, String helpURL, boolean newRow)
		{
			super(parentShell, windowImage, windowTitle, wizardImage, helpURL, newRow);
		}

		@Override
		protected Control createDialogArea(Composite parent)
		{
			Composite composite = (Composite)super.createDialogArea(parent);

			Composite rowComposite = new Composite(composite, SWT.NONE);
			
			GridLayout layout = new GridLayout(2, false);
			rowComposite.setLayout(layout);
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
			rowComposite.setLayoutData(gridData);

			createStackOptions(rowComposite);

			createStack(rowComposite);
			
			fillStackOptions();

			refreshRow();
			
			return rowComposite;
		}
			
		@Override
		protected void enableDisableOkButton()
		{
			getButton(IDialogConstants.OK_ID).setEnabled(true);
		}
		
		@Override
		protected void buttonPressed(int buttonId)
		{
			if(buttonId == IDialogConstants.OK_ID)
			{
				try
				{
					saveRow();
				}
				catch(ValidatorException e)
				{
					setErrorMessage(e.getMessage());
					return;
				}
			}
			
			setReturnCode(buttonId);
			close();		
		}
	}
	
	private final Image m_windowImage;
	private final String m_windowTitle;
	private final Image m_wizardImage;
	private final String m_helpURL;
	
	public TwoPagesTableEditor(Composite parent, IOnePageTable<T> table, boolean swapButtonsFlag, Image windowImage, String windowTitle, Image wizardImage, String helpURL, int style)
	{
		super(parent, table, swapButtonsFlag, style);
		m_windowImage = windowImage;
		m_windowTitle = windowTitle;
		m_wizardImage = wizardImage;
		m_helpURL = helpURL;
	}
	
	@Override
	protected void initComposite()
	{
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		setLayout(layout);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		setLayoutData(gridData);

		createTableGroup(this);
	}

	@Override
	protected Composite createTableGroupComposite(Composite parent)
	{
		Composite componentTableGroup = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout(2, false);
		gl.marginHeight = gl.marginWidth = 0;
		componentTableGroup.setLayout(gl);
		componentTableGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		return componentTableGroup;
	}
	
	@Override
	protected Composite createTableButtonsComposite(Composite parent)
	{
		Composite buttonBox = new Composite(parent, SWT.NONE);
		buttonBox.setLayout(new FillLayout(SWT.VERTICAL));
		buttonBox.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));

		return buttonBox;
	}


	@Override
	protected void newRow()
	{
		getTableViewer().getTable().deselectAll();
		editRow();
	}

	@Override
	protected void editRow()
	{
		new EditRowDialog(
				this.getShell(), m_windowImage, m_windowTitle, m_wizardImage, m_helpURL, true)
			.open();
	}

	@Override
	protected void rowSelectionEvent()
	{
		enableDisableButtonGroup();
	}

	@Override
	public void refresh()
	{
		refreshTable();
		enableDisableButtonGroup();
	}

	@Override
	protected void enableDisableButtonGroup()
	{
		Table table = getTableViewer().getTable();
		int top = table.getItemCount();
		int idx = getSelectionIndex();
		
		getEditButton().setEnabled(idx >= 0);
		getRemoveButton().setEnabled(idx >= 0);
		
		if(isSwapButtonAllowed())
		{
			getMoveUpButton().setEnabled(idx > 0);
			getMoveDownButton().setEnabled(idx >= 0 && idx < top - 1);
		}
	}

}

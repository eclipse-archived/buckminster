/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 *
 */
public class SpacePage extends InstallWizardPage
{
	private Text m_specName;
	
	private String[] m_spaces;

	private Combo m_spaceCombo;

	protected SpacePage(String[] spaces)
	{
		super("SelectSpaceStep", "Space", "Select space for saving download settings.", null);
		
		m_spaces = spaces;
	}

	public String getSelectedSpace()
	{
		return m_spaces[m_spaceCombo.getSelectionIndex()];
	}

	public void setSelectedSpace(String space)
	{
		int idx = Arrays.asList(m_spaces).indexOf(space);

		if(idx == -1)
		{
			throw new RuntimeException("Unknown space '" + space + "'");
		}

		m_spaceCombo.select(idx);
	}

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(2, false));

		new Label(pageComposite, SWT.NONE).setText("Materialization Name:");
		
		m_specName = new Text(pageComposite, SWT.BORDER);
		m_specName.setText(getMaterializationSpecBuilder().getName() == null ? "" : getMaterializationSpecBuilder().getName());
		m_specName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_specName.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				boolean pageComplete = false;
				
				if(m_specName.getText().length() == 0)
				{
					setErrorMessage("Materialization Name cannot be empty");
				} else
				{
					setErrorMessage(null);
					pageComplete = true;
				}
				
				setPageComplete(pageComplete);
				getMaterializationSpecBuilder().setName(m_specName.getText());
			}
		});

		new Label(pageComposite, SWT.NONE).setText("Space for saving download settings:");

		if(m_spaces.length == 0)
		{
			throw new RuntimeException("No publishing spaces are set.");
		}

		m_spaceCombo = new Combo(pageComposite, SWT.READ_ONLY);
		m_spaceCombo.setItems(m_spaces);
		m_spaceCombo.select(0);

		setControl(pageComposite);
	}
	

	@Override
	public void setVisible(boolean visible)
	{
		super.setVisible(visible);

		if(!visible	&&
				(getMaterializationSpecBuilder().getName() == null ||
						getMaterializationSpecBuilder().getName().length() == 0))
		{
			getMaterializationSpecBuilder().setName(getInstallWizard().getArtifactName());
			m_specName.setText(getInstallWizard().getArtifactName());
		}
	}

}

/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.*;

import java.util.List;

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
public class PublishSpacePage extends PublishWizardPage
{
	private Combo m_spaceCombo;
	
	private Text m_artifactName;
	
	protected PublishSpacePage()
	{
		super(PublishWizard.PUBLISH_STEP, "Publish", "Publish distro to a selected space.", null);
	}

	public void createControl(Composite parent)
	{
		setPageComplete(false); // set to true when the page is shown

		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(2, false));

		new Label(pageComposite, SWT.NONE).setText("Target Space:");

		m_spaceCombo = new Combo(pageComposite, SWT.READ_ONLY);

		new Label(pageComposite, SWT.NONE).setText("Distro Name:");
		
		m_artifactName = new Text(pageComposite, SWT.BORDER);
		m_artifactName.setText(getPublishWizard().getMSpecBuilder().getName() == null ? "" : getPublishWizard().getMSpecBuilder().getName());
		m_artifactName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_artifactName.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				if(m_artifactName.getText().length() == 0)
				{
					setErrorMessage("Distro Name cannot be empty");
				} else
				{
					setErrorMessage(null);
					
					getPublishWizard().getMSpecBuilder().setName(m_artifactName.getText());
				}
				
				setPageComplete(isComplete());
			}
		});

		setControl(pageComposite);
	}

	private boolean isComplete()
	{
		return m_artifactName.getText().length() != 0;
	}
	
	@Override
	protected void beforeDisplaySetup()
	{
		setPageComplete(isComplete());
		
		List<String> availableSpaces;
		
		try
		{
			availableSpaces = getPublishWizard().getPublisher().getSpaceNames();
		}
		catch(Exception e)
		{
			throw new JNLPException(
					"Cannot read available spaces", ERROR_CODE_PUBLISHING_EXCEPTION, e);
		}
		
		if(availableSpaces.size() == 0)
			throw new JNLPException(
					"Cannot read available spaces", ERROR_CODE_PUBLISHING_EXCEPTION);
		
		m_spaceCombo.setItems(availableSpaces.toArray(new String[0]));
		m_spaceCombo.select(0);
	}

	String getSelectedSpace()
	{
		return m_spaceCombo.getText();
	}

	String getArtifactName()
	{
		return m_artifactName.getText();
	}

}

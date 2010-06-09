/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.presentation;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.equinox.internal.provisional.p2.metadata.Version;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionRange;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.text.FindReplaceDocumentAdapterContentProposalProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

/**
 * @author Karel Brezina
 * 
 */
@SuppressWarnings("restriction")
public class FindIUDialog extends TrayDialog
{
	private static final int FIND_ID = IDialogConstants.CLIENT_ID + 1;

	private static String getString(String key)
	{
		return AggregatorEditorPlugin.INSTANCE.getString(key);
	}

	private AggregatorEditor m_aggregatorEditor;

	private Text m_idPatternText;

	private Button m_regularExprButton;

	private ContentAssistCommandAdapter m_PatterFieldContentAssist;

	private Text m_versionFromText;

	private Text m_versionToText;

	private Button m_forwardButton;

	private Label m_statusLabel;

	private Button m_findButton;

	protected FindIUDialog(IWorkbenchWindow window)
	{
		super(window.getShell());
		setShellStyle(SWT.SHELL_TRIM | SWT.RESIZE);

		m_aggregatorEditor = (AggregatorEditor)window.getActivePage().getActivePart();
	}

	protected void buttonPressed(int buttonId)
	{
		switch(buttonId)
		{
		case IDialogConstants.CANCEL_ID:
			close();
			break;
		default:
			if(performAction(buttonId))
				close();
		}
	}

	protected void configureShell(Shell shell)
	{
		super.configureShell(shell);
		shell.setText(getString("_UI_FindIUDialog_windowTitle"));
	}

	protected Control createButtonBar(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 0; // create
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);

		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label filler = new Label(composite, SWT.NONE);
		filler.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
		layout.numColumns++;

		m_findButton = createButton(composite, FIND_ID, getString("_UI_FindIUDialog_findButton"), true);
		m_findButton.setEnabled(isFindEnabled());
		createButton(composite, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);

		return composite;
	}

	protected Control createDialogArea(Composite parent)
	{
		Composite result = (Composite)super.createDialogArea(parent);

		Control fContents = createPageArea(result);
		fContents.setLayoutData(new GridData(GridData.FILL_BOTH));

		return result;
	}

	protected Control createPageArea(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setFont(parent.getFont());
		composite.setLayout(new GridLayout(4, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		new Label(composite, SWT.NONE).setText(getString("_UI_FindIUDialog_installableUnitIDField"));
		m_idPatternText = new Text(composite, SWT.BORDER);
		GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
		layoutData.horizontalSpan = 3;
		m_idPatternText.setLayoutData(layoutData);
		m_idPatternText.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				m_findButton.setEnabled(m_idPatternText.getCharCount() > 0);
			}
		});

		new Label(composite, SWT.NONE);
		m_regularExprButton = new Button(composite, SWT.CHECK);
		m_regularExprButton.setText(getString("_UI_FindIUDialog_regularExpressionField"));
		layoutData = new GridData();
		layoutData.horizontalSpan = 3;
		m_regularExprButton.setLayoutData(layoutData);
		m_regularExprButton.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				m_PatterFieldContentAssist.setEnabled(isFindEnabled());
			}
		});

		TextContentAdapter contentAdapter = new TextContentAdapter();
		FindReplaceDocumentAdapterContentProposalProvider findProposer = new FindReplaceDocumentAdapterContentProposalProvider(
				true);
		m_PatterFieldContentAssist = new ContentAssistCommandAdapter(m_idPatternText, contentAdapter, findProposer,
				ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, new char[] { '\\', '[', '(' }, true);

		m_PatterFieldContentAssist.setEnabled(m_regularExprButton.getSelection());

		labelSeparator(composite);

		new Label(composite, SWT.NONE).setText(getString("_UI_FindIUDialog_versionFromField"));
		m_versionFromText = new Text(composite, SWT.BORDER);
		m_versionFromText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(composite, SWT.NONE).setText(getString("_UI_FindIUDialog_versionToField"));
		m_versionToText = new Text(composite, SWT.BORDER);
		m_versionToText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		labelSeparator(composite);

		Group directionGroup = new Group(composite, SWT.NONE);
		layoutData = new GridData(GridData.FILL_HORIZONTAL);
		layoutData.horizontalSpan = 4;
		directionGroup.setLayoutData(layoutData);
		directionGroup.setText(getString("_UI_FindIUDialog_directionGroupName"));
		directionGroup.setLayout(new GridLayout(2, true));

		m_forwardButton = new Button(directionGroup, SWT.RADIO);
		m_forwardButton.setText(getString("_UI_FindIUDialog_forwardButton"));

		Button backwardButton = new Button(directionGroup, SWT.RADIO);
		backwardButton.setText(getString("_UI_FindIUDialog_backwardButton"));

		m_forwardButton.setSelection(true);

		m_statusLabel = new Label(composite, SWT.LEFT);
		layoutData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		layoutData.horizontalSpan = 4;
		m_statusLabel.setLayoutData(layoutData);

		return composite;
	}

	protected boolean performAction(int actionID)
	{
		switch(actionID)
		{
		case CANCEL:
			return true;
		case FIND_ID:
			return performFind();
		default:
			return false;
		}
	}

	private boolean isFindEnabled()
	{
		return m_regularExprButton.getSelection();
	}

	private void labelSeparator(Composite composite)
	{
		Label separatorLabel = new Label(composite, SWT.NONE);
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 4;
		layoutData.heightHint = 8;
		separatorLabel.setLayoutData(layoutData);
	}

	private boolean performFind()
	{
		Pattern idPattern = null;
		try
		{
			if(m_regularExprButton.getSelection())
				idPattern = Pattern.compile(UIUtils.trimmedValue(m_idPatternText));
			else
				idPattern = Pattern.compile(Pattern.quote(UIUtils.trimmedValue(m_idPatternText)));
		}
		catch(PatternSyntaxException e)
		{
			statusMessage(true, e.getLocalizedMessage());
			return false;
		}

		Version minVersion = null;
		String versionFromString = UIUtils.trimmedValue(m_versionFromText);

		if(versionFromString != null)
			minVersion = Version.parseVersion(versionFromString);
		else
			minVersion = Version.MIN_VERSION;

		Version maxVersion = null;
		String versionToString = UIUtils.trimmedValue(m_versionToText);

		if(versionToString != null)
			maxVersion = Version.parseVersion(versionToString);
		else
			maxVersion = Version.MAX_VERSION;

		VersionRange versionRange = new VersionRange(minVersion, true, maxVersion, true);

		m_aggregatorEditor.registerFindIUArguments(idPattern, versionRange);

		if(m_aggregatorEditor.findNextIU(m_forwardButton.getSelection()))
			statusMessage(false, "");
		else
			statusMessage(false, "String Not Found");

		return false;
	}

	private void statusMessage(boolean error, String message)
	{
		m_statusLabel.setText(message);

		if(error)
			m_statusLabel.setForeground(JFaceColors.getErrorText(m_statusLabel.getDisplay()));
		else
			m_statusLabel.setForeground(null);

		if(UIUtils.trimmedValue(message) != null)
			getShell().getDisplay().beep();
	}
}

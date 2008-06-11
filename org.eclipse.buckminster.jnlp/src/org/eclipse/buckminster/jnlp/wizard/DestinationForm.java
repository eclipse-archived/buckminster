/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.core.mspec.builder.MaterializationDirectiveBuilder;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.jnlp.ui.UiUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
public class DestinationForm
{
	private final static String[] DESTINATION_TYPES = { IMaterializer.FILE_SYSTEM, IMaterializer.WORKSPACE,
			IMaterializer.TARGET_PLATFORM };

	private final static String[] DESTINATION_TYPES_TO_SHOW = { "Filesystem", "Workspace", "Eclipse Installation" };

	private static final String TOOL_TIP_TYPE = "Destination type for materialization: Filesystem, Workspace, Eclipse Installation";

	private static final String TOOL_TIP_DIRECTORY = "Destination directory for materialization";

	private static final String TOOL_TIP_BROWSE_DIRECTORY = "Browse destination directory for materialization";

	private static final String TOOL_TIP_CONFLICTS = "How to resolve filesystem conflicts:\nChoises: Fail, Replace, Keep";

	private MaterializationDirectiveBuilder m_builder;

	private String m_installLocation;

	private boolean m_showDestinationType;

	private boolean m_allowEmptyDestinationType;

	private boolean m_showConflictResolution;

	private boolean m_allowEmptyConflictResolution;

	private boolean m_showBrowseButton;

	private Combo m_destTypeCombo;

	private Text m_locationText;

	private Button m_browseButton;

	private Combo m_conflictCombo;

	private List<String> m_destinationTypes;

	private List<String> m_destinationTypesToShow;

	private int m_defaultDestinationType;

	private List<ConflictResolution> m_conflictResolutions;

	private List<String> m_conflictResolutionsToShow;

	private int m_defaultConflictResolution;

	public DestinationForm(MaterializationDirectiveBuilder builder, String defaultInstallLocation,
			boolean showDestinationType, boolean allowEmptyDestinationType, boolean showConflictResolution,
			boolean allowEmptyConflictResolution, boolean showBrowseButton)
	{
		m_builder = builder == null
				? new MaterializationNodeBuilder()
				: builder;
		m_installLocation = defaultInstallLocation;
		m_showDestinationType = showDestinationType;
		m_allowEmptyDestinationType = allowEmptyDestinationType;
		m_showConflictResolution = showConflictResolution;
		m_allowEmptyConflictResolution = allowEmptyConflictResolution;
		m_showBrowseButton = showBrowseButton;
	}

	public void createControl(Composite parent)
	{
		if(m_showDestinationType)
		{
			Label label = new Label(parent, SWT.NONE);
			label.setText("Destination Type:");
			label.setToolTipText(TOOL_TIP_TYPE);

			m_destinationTypes = new ArrayList<String>();
			m_destinationTypes.addAll(Arrays.asList(DESTINATION_TYPES));
			m_destinationTypesToShow = new ArrayList<String>();
			m_destinationTypesToShow.addAll(Arrays.asList(DESTINATION_TYPES_TO_SHOW));
			m_defaultDestinationType = 0;

			if(m_allowEmptyDestinationType)
			{
				m_destinationTypes.add(0, null);
				m_destinationTypesToShow.add(0, "");
			}

			m_destTypeCombo = UiUtils.createGridArrayCombo(parent, 0, 0, m_destinationTypesToShow
					.toArray(new String[0]), null, null, SWT.READ_ONLY);

			new Label(parent, SWT.NONE);

			m_destTypeCombo.setToolTipText(TOOL_TIP_TYPE);

			for(int i = 0; i < m_destinationTypes.size(); i++)
				m_destTypeCombo.setData(String.valueOf(i), m_destinationTypes.get(i));

			m_destTypeCombo.addModifyListener(new ModifyListener()
			{

				public void modifyText(ModifyEvent e)
				{
					m_builder.setMaterializerID((String)m_destTypeCombo.getData(String.valueOf(m_destTypeCombo
							.getSelectionIndex())));
				}
			});
		}

		Label label = new Label(parent, SWT.NONE);
		label.setText("Destination Address:");
		label.setToolTipText(TOOL_TIP_DIRECTORY);

		m_locationText = new Text(parent, SWT.BORDER);
		m_locationText.setText(m_builder.getInstallLocation() == null
				? ""
				: m_builder.getInstallLocation().removeTrailingSeparator().toOSString());
		m_locationText.setToolTipText(TOOL_TIP_DIRECTORY);
		m_locationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_locationText.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				String pathStr = m_locationText.getText();
				IPath path = (pathStr == null || pathStr.length() == 0)
						? null
						: Path.fromOSString(pathStr).addTrailingSeparator();

				m_builder.setInstallLocation(path);
			}
		});

		if(m_showBrowseButton)
		{
			m_browseButton = new Button(parent, SWT.PUSH);
			m_browseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
			m_browseButton.setText("Browse");
			m_browseButton.setToolTipText(TOOL_TIP_BROWSE_DIRECTORY);
			m_browseButton.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					DirectoryDialog dlg = new DirectoryDialog(m_browseButton.getShell());
					dlg.setFilterPath(getKnownPath());
					String dir = dlg.open();

					if(dir != null)
					{
						m_locationText.setText(dir);
					}
				}

				private String getKnownPath()
				{
					IPath path = m_builder.getInstallLocation();

					if(path == null)
						return null;

					File file = null;
					String pathString = null;
					do
					{
						// second and other runs - remove last segment
						if(file != null)
							path = path.removeLastSegments(1);

						pathString = path.removeTrailingSeparator().toOSString();
						file = new File(pathString);
					} while(!file.exists());

					if(!file.isDirectory())
						return null;

					return pathString;
				}
			});
		}
		else
		{
			new Label(parent, SWT.NONE);
		}

		if(m_showConflictResolution)
		{
			label = new Label(parent, SWT.NONE);
			label.setText("Conflict Resolution:");
			label.setToolTipText(TOOL_TIP_CONFLICTS);

			m_conflictResolutions = new ArrayList<ConflictResolution>();
			m_conflictResolutionsToShow = new ArrayList<String>();
			m_defaultConflictResolution = ConflictResolution.REPLACE.ordinal();

			for(ConflictResolution cr : ConflictResolution.values())
			{
				// we don't support ConflictResolution.UPDATE
				if(cr == ConflictResolution.UPDATE)
					continue;

				m_conflictResolutions.add(cr);
				m_conflictResolutionsToShow.add(cr.toString());
			}

			if(m_allowEmptyConflictResolution)
			{
				m_conflictResolutions.add(0, null);
				m_conflictResolutionsToShow.add(0, "");
				m_defaultConflictResolution = 0;
			}

			m_conflictCombo = UiUtils.createGridArrayCombo(parent, 0, 0, m_conflictResolutionsToShow
					.toArray(new String[0]), null, null, SWT.READ_ONLY);

			m_conflictCombo.select(m_builder.getConflictResolution() == null
					? m_defaultConflictResolution
					: m_conflictResolutions.indexOf(m_builder.getConflictResolution()));

			m_conflictCombo.setToolTipText(TOOL_TIP_CONFLICTS);

			for(int i = 0; i < m_conflictResolutions.size(); i++)
			{
				m_conflictCombo.setData(String.valueOf(i), m_conflictResolutions.get(i));
			}

			m_builder.setConflictResolution((ConflictResolution)m_conflictCombo.getData(String.valueOf(m_conflictCombo
					.getSelectionIndex())));
			m_conflictCombo.addModifyListener(new ModifyListener()
			{

				public void modifyText(ModifyEvent e)
				{
					m_builder.setConflictResolution((ConflictResolution)m_conflictCombo.getData(String
							.valueOf(m_conflictCombo.getSelectionIndex())));
				}
			});

			new Label(parent, SWT.NONE);

			if(m_destTypeCombo != null)
			{
				m_destTypeCombo.addModifyListener(new ModifyListener()
				{

					public void modifyText(ModifyEvent e)
					{
						setEnabledConflictResolution();
					}
				});
			}
		}
	}

	private void setEnabledConflictResolution()
	{
		if(m_conflictCombo != null && m_destTypeCombo != null)
			if(IMaterializer.TARGET_PLATFORM.equals(m_destTypeCombo.getData(String.valueOf(m_destTypeCombo
					.getSelectionIndex()))))
			{
				m_conflictCombo.setEnabled(false);
				m_conflictCombo.select(m_conflictResolutions.indexOf(ConflictResolution.KEEP));
			}
			else
			{
				m_conflictCombo.setEnabled(true);
			}
	}

	public void setBuilder(MaterializationDirectiveBuilder builder)
	{
		m_builder = builder;
	}

	public void update()
	{
		if(m_destTypeCombo != null)
		{
			int defaultIdx = m_defaultDestinationType;
			if(m_builder.getMaterializerID() != null)
				defaultIdx = m_destinationTypes.indexOf(m_builder.getMaterializerID());

			m_destTypeCombo.select(defaultIdx);
			m_builder.setMaterializerID(m_destinationTypes.get(defaultIdx));
		}

		if(m_builder.getInstallLocation() == null)
		{
			if(m_installLocation != null)
			{
				m_builder.setInstallLocation(new Path(m_installLocation).addTrailingSeparator());
				m_locationText.setText(m_installLocation);
			}
		}
		else
		{
			m_locationText.setText(m_builder.getInstallLocation().removeTrailingSeparator().toOSString());
		}

		if(m_conflictCombo != null)
		{
			// we don't support ConflictResolution.UPDATE
			
			m_conflictCombo.select(m_builder.getConflictResolution() == null
					? m_defaultConflictResolution
					: m_conflictResolutions.indexOf(m_builder.getConflictResolution() == ConflictResolution.UPDATE
							? ConflictResolution.REPLACE
							: m_builder.getConflictResolution()));
		}

		setEnabledConflictResolution();
	}

	public void setEnabled(boolean enabled)
	{
		if(m_destTypeCombo != null)
			m_destTypeCombo.setEnabled(enabled);

		m_locationText.setEnabled(enabled);

		if(m_browseButton != null)
			m_browseButton.setEnabled(enabled);

		if(m_conflictCombo != null)
			m_conflictCombo.setEnabled(enabled);

		if(enabled)
			setEnabledConflictResolution();
	}
}

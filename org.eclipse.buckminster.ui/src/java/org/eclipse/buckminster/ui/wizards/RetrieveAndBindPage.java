/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.wizards;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.materializer.AbstractMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.core.mspec.model.MaterializationNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.ui.DynamicTableLayout;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @author Kenneth Olwing
 * @author Thomas Hallgren
 */
public class RetrieveAndBindPage extends AbstractQueryPage
{
	class ComponentLabelProvider extends LabelProvider implements ITableLabelProvider
	{
		public Image getColumnImage(Object element, int columnIndex)
		{
			return null;
		}

		public String getColumnText(Object element, int columnIndex)
		{
			MaterializationContext context = getQueryWizard().getMaterializationContext();
			Resolution resolution = (Resolution)element;
			ComponentRequest request = resolution.getRequest();
			String lbl;
			switch(columnIndex)
			{
			case 0:
				lbl = request.getViewName();
				break;
			case 1:
				IVersionSelector vs = resolution.getVersionMatch().getFixedVersionSelector();
				lbl = vs == null
						? ""
						: vs.toString();
				break;
			case 2:
				try
				{
					if(resolution.isMaterializable())
					{
						if(resolution.isMaterialized(context.getInstallLocation(resolution)))
							lbl = "Yes";
						else
							lbl = "No";
					}
					else
						lbl = "N/A";
				}
				catch(CoreException e)
				{
					lbl = "ERROR";
					CorePlugin.getLogger().error(e.getMessage(), e);
				}
				break;
			default:
				try
				{
					if(resolution.isMaterializable())
					{
						if(WorkspaceInfo.getResource(resolution.getCSpec().getComponentIdentifier()) != null)
							lbl = "Yes";
						else
							lbl = "No";
					}
					else
						lbl = "N/A";
				}
				catch(CoreException e)
				{
					lbl = "ERROR";
					CorePlugin.getLogger().error(e.getMessage(), e);
				}
			}
			return lbl;
		}
	}

	static class ResolutionComparator implements Comparator<Resolution>
	{
		public int compare(Resolution o1, Resolution o2)
		{
			int result = o1.getRequest().getViewName().compareTo(o2.getRequest().getViewName());
			if(result == 0)
			{
				IVersion vsA = o1.getVersion();
				IVersion vsB = o2.getVersion();
				if(vsA != null)
					result = (vsB == null)
							? 1
							: vsA.compareTo(vsB);
				else if(vsB != null)
					result = -1;
			}
			return result;
		}
	}

	static class ResolutionViewerSorter extends ViewerSorter
	{
		private final ResolutionComparator m_nodeComparator = new ResolutionComparator();

		@Override
		public int compare(Viewer viewer, Object a, Object b)
		{
			return m_nodeComparator.compare((Resolution)a, (Resolution)b);
		}
	}

	private TableViewer m_componentTable;

	private Combo m_globalConflictResolutionCombo;

	private Combo m_conflictResolutionCombo;

	private Button m_skipButton;

	private Group m_settingsGroup;

	private Composite m_nodeComposite;

	private Combo m_globalMaterializer;

	private Text m_globalInstallLocation;

	private Combo m_materializer;

	private Text m_installLocation;

	private Text m_resourcePath;

	private Button m_useDefaultsButton;

	public RetrieveAndBindPage()
	{
		super("");
		setDescription(UiPlugin.getResourceString("RetrieveAndBindPage.description")); //$NON-NLS-1$
	}

	@Override
	public Composite createControls(Composite parent)
	{
		Composite topComposite = new Composite(parent, SWT.NONE);
		topComposite.setLayout(new GridLayout());
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		createComponentTableGroup(topComposite);
		createSettingsGroup(topComposite);

		return topComposite;
	}

	@Override
	protected void pageIsShowing()
	{
		super.pageIsShowing();
		QueryWizard wizard = getQueryWizard();
		try
		{
			TableViewer tv = getComponentTable();
			BillOfMaterials bom = wizard.getBOM();
			tv.setInput(bom.findAll(null));
			tv.getTable().select(0);
			setSelectedComponentValues(getSelectedComponent());
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	Resolution getSelectedComponent()
	{
		IStructuredSelection selection = (IStructuredSelection)m_componentTable.getSelection();
		return (selection != null && selection.size() == 1)
				? (Resolution)selection.getFirstElement()
				: null;
	}

	private String browseForLocation(Resolution node)
	{
		// FIXME - this should use a dialog supplied by the repo provider
		DirectoryDialog dd = new DirectoryDialog(getShell());
		String dir = dd.open();

		return dir;
	}

	private void createComponentTableGroup(Composite parent)
	{
		Group globalSettings = new Group(parent, SWT.NONE);
		globalSettings.setText("Global settings");
		globalSettings.setLayout(new GridLayout(3, false));
		globalSettings.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		m_globalInstallLocation = UiUtils.createLabeledText(globalSettings, "Location:", 0, new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				Text fld = (Text)me.getSource();
				String txt = UiUtils.trimmedValue(fld);
				setGlobalInstallLocation(txt == null
						? null
						: new Path(txt));
			}
		});
		m_globalInstallLocation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		UiUtils.createPushButton(globalSettings, "Browse...", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				String newLoc = browseForLocation(getSelectedComponent());
				if(newLoc != null)
					m_globalInstallLocation.setText(newLoc);
			}
		});

		UiUtils.createGridLabel(globalSettings, "Materializer:", 1, 0, SWT.NONE);
		m_globalMaterializer = UiUtils.createGridCombo(globalSettings, 2, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);
		m_globalMaterializer.setItems(AbstractMaterializer.getMaterializerIDs(true));
		m_globalMaterializer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				Combo combo = (Combo)se.getSource();
				int idx = combo.getSelectionIndex();
				String materializerId = idx >= 0
						? combo.getItem(idx)
						: "";
				if(materializerId.length() == 0)
					materializerId = null;
				setGlobalMaterializer(materializerId);
			}
		});

		UiUtils.createGridLabel(globalSettings, "On non empty install location:", 1, 0, SWT.NONE);
		m_globalConflictResolutionCombo = UiUtils.createGridCombo(globalSettings, 2, 0, null, null, SWT.DROP_DOWN
				| SWT.READ_ONLY | SWT.SIMPLE);
		for(ConflictResolution value : ConflictResolution.values())
			m_globalConflictResolutionCombo.add(value.toString());
		m_globalConflictResolutionCombo.select(0);
		m_globalConflictResolutionCombo.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				globalConflictResolutionEvent(((Combo)se.getSource()).getSelectionIndex());
			}
		});

		Group componentTableGroup = new Group(parent, SWT.NONE);
		componentTableGroup.setLayout(new GridLayout());
		componentTableGroup.setText("Selected components:");
		componentTableGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Table table = new Table(componentTableGroup, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);

		String[] columnNames = new String[] { "Name", "Version", "Present", "Bound" };
		int[] columnWeights = new int[] { 20, 10, 5, 5 };

		table.setHeaderVisible(true);
		DynamicTableLayout layout = new DynamicTableLayout(150);
		for(int idx = 0; idx < columnNames.length; idx++)
		{
			TableColumn tableColumn = new TableColumn(table, SWT.LEFT, idx);
			tableColumn.setText(columnNames[idx]);
			layout.addColumnData(new ColumnWeightData(columnWeights[idx], true));
		}
		table.setLayout(layout);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		m_componentTable = new TableViewer(table);
		m_componentTable.setLabelProvider(new ComponentLabelProvider());
		m_componentTable.setContentProvider(new ArrayContentProvider());
		m_componentTable.setSorter(new ResolutionViewerSorter());
		m_componentTable.addSelectionChangedListener(new ISelectionChangedListener()
		{
			public void selectionChanged(SelectionChangedEvent event)
			{
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				if(selection.size() == 1)
					try
					{
						setSelectedComponentValues((Resolution)selection.getFirstElement());
					}
					catch(CoreException e)
					{
						displayException(e);
					}
			}
		});
	}

	private void createSettingsGroup(Composite parent)
	{
		m_settingsGroup = new Group(parent, SWT.NONE);
		m_settingsGroup.setLayout(new GridLayout(2, false));
		m_settingsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		m_skipButton = UiUtils.createCheckButton(m_settingsGroup, "Skip this component", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				Button b = (Button)se.getSource();
				skipEvent(b.getSelection());
			}
		});
		m_skipButton.setSelection(false);

		m_useDefaultsButton = UiUtils.createCheckButton(m_settingsGroup, "Use defaults", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				Button b = (Button)se.getSource();
				useDefaultEvent(b.getSelection());
			}
		});
		m_useDefaultsButton.setSelection(true);

		m_nodeComposite = new Composite(m_settingsGroup, SWT.NONE);
		m_nodeComposite.setLayout(new GridLayout(3, false));
		m_nodeComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		UiUtils.createGridLabel(m_nodeComposite, "Materializer:", 1, 0, SWT.NONE);
		m_materializer = UiUtils.createGridCombo(m_nodeComposite, 2, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);
		m_materializer.setItems(AbstractMaterializer.getMaterializerIDs(true));
		m_materializer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				Combo combo = (Combo)se.getSource();
				int idx = combo.getSelectionIndex();
				String materializerId = idx >= 0
						? combo.getItem(idx)
						: "";
				if(materializerId.length() == 0)
					materializerId = null;
				setMaterializer(getSelectedComponent(), materializerId);
			}
		});

		m_installLocation = UiUtils.createLabeledText(m_nodeComposite, "Location:", 0, new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				Text fld = (Text)me.getSource();
				String txt = UiUtils.trimmedValue(fld);
				setLocation(getSelectedComponent(), txt == null
						? null
						: new Path(txt));
			}
		});
		m_installLocation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		UiUtils.createPushButton(m_nodeComposite, "Browse...", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				String newLoc = browseForLocation(getSelectedComponent());
				if(newLoc != null)
					m_installLocation.setText(newLoc);
			}
		});

		UiUtils.createGridLabel(m_nodeComposite, "On non empty install location:", 1, 0, SWT.NONE);
		m_conflictResolutionCombo = new Combo(m_nodeComposite, SWT.NONE);
		for(ConflictResolution value : ConflictResolution.values())
			m_conflictResolutionCombo.add(value.toString());
		m_conflictResolutionCombo.select(0);
		m_conflictResolutionCombo.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				conflictResolutionEvent(((Combo)se.getSource()).getSelectionIndex());
			}
		});
		m_conflictResolutionCombo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		m_resourcePath = UiUtils.createLabeledText(m_nodeComposite, "ProjectBinding name:", 0, new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				setProjectName(getSelectedComponent(), UiUtils.trimmedValue((Text)me.getSource()));
			}
		});
		m_resourcePath.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
	}

	private TableViewer getComponentTable()
	{
		return m_componentTable;
	}

	@SuppressWarnings("unchecked")
	private List<Resolution> getTableInput()
	{
		return (List<Resolution>)getComponentTable().getInput();
	}

	private void setLocation(Resolution selected, IPath path)
	{
		try
		{
			MaterializationContext context = getQueryWizard().getMaterializationContext();
			if(!Trivial.equalsAllowNull(context.getInstallLocation(selected), path))
			{
				getMaterializationNode(selected).setInstallLocation(path);
				updatePageCompletion();
			}
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	private void setGlobalInstallLocation(IPath path)
	{
		try
		{
			if(path != null)
			{
				if(!path.isAbsolute())
				{
					setErrorMessage("\"" + path + "\" is not an absolute path");
					return;
				}
			}

			QueryWizard wizard = getQueryWizard();
			MaterializationContext context = wizard.getMaterializationContext();
			if(!Trivial.equalsAllowNull(context.getMaterializationSpec().getInstallLocation(), path))
			{
				wizard.getMaterializationSpec().setInstallLocation(path);
				wizard.invalidateMaterializationContext();
				getComponentTable().setInput(wizard.getBOM().findAll(null));
				setSelectedComponentValues(getSelectedComponent());
			}
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	private void setGlobalMaterializer(String materializer)
	{
		try
		{
			QueryWizard wizard = getQueryWizard();
			MaterializationContext context = wizard.getMaterializationContext();
			if(!Trivial.equalsAllowNull(context.getMaterializationSpec().getMaterializerID(), materializer))
			{
				wizard.getMaterializationSpec().setMaterializer(materializer);
				wizard.invalidateMaterializationContext();
				setSelectedComponentValues(getSelectedComponent());
			}
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	private void globalConflictResolutionEvent(int ordinal)
	{
		QueryWizard wizard = getQueryWizard();
		MaterializationContext context = wizard.getMaterializationContext();
		MaterializationSpec mspec = context.getMaterializationSpec();
		ConflictResolution cr = ConflictResolution.values()[ordinal];
		if(!cr.equals(mspec.getConflictResolution()))
		{
			wizard.getMaterializationSpec().setConflictResolution(cr);
			wizard.invalidateMaterializationContext();
		}
	}

	private void setMaterializer(Resolution selected, String materializer)
	{
		try
		{
			MaterializationContext context = getQueryWizard().getMaterializationContext();
			MaterializationNode node = context.getMaterializationSpec().getMatchingNode(selected.getRequest());
			String nodeMat = (node == null)
					? null
					: node.getMaterializerID();
			if(!Trivial.equalsAllowNull(nodeMat, materializer))
			{
				getMaterializationNode(selected).setMaterializer(materializer);
				updatePageCompletion();
			}
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	private void setProjectName(Resolution resolution, String name)
	{
		try
		{
			MaterializationContext context = getQueryWizard().getMaterializationContext();
			MaterializationNode node = context.getMaterializationSpec().getMatchingNode(resolution.getRequest());
			IPath path = (name == null)
					? null
					: Path.fromPortableString(name);

			if(node == null)
			{
				if(path != null)
				{
					getMaterializationNode(resolution).setResourcePath(path);
					updatePageCompletion();
				}
			}
			else
			{
				if(!Trivial.equalsAllowNull(path, node.getResourcePath()))
				{
					getMaterializationNode(resolution).setResourcePath(path);
					updatePageCompletion();
				}
			}
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	private void setSelectedComponentValues(Resolution resolution) throws CoreException
	{
		if(resolution == null)
		{
			UiUtils.setChildrenVisible(m_settingsGroup, false);
			updatePageCompletion();
			return;
		}

		ComponentRequest request = resolution.getRequest();
		MaterializationContext context = getQueryWizard().getMaterializationContext();
		MaterializationSpec mspec = context.getMaterializationSpec();
		MaterializationNode node = mspec.getMatchingNode(resolution.getRequest());

		IPath destination = mspec.getInstallLocation();
		m_globalInstallLocation.setText(destination == null
				? ""
				: destination.toOSString());

		int matIdx = 0;
		String materializer = mspec.getMaterializerID();
		if(materializer != null)
		{
			matIdx = m_globalMaterializer.indexOf(materializer);
			if(matIdx < 0)
				matIdx = 0;
		}
		m_globalMaterializer.select(matIdx);

		ConflictResolution cr = mspec.getConflictResolution();
		if(cr == null)
			cr = ConflictResolution.getDefault();
		m_globalConflictResolutionCombo.select(cr.ordinal());

		boolean useDefaults = node == null;
		boolean skip = mspec.isExcluded(request);
		destination = context.getInstallLocation(resolution);

		m_settingsGroup.setText(resolution.getRequest().getViewName());
		if(node != null)
		{
			IPath resourcePath = node.getResourcePath();
			m_resourcePath.setText(resourcePath == null
					? ""
					: resourcePath.toPortableString());
		}
		else
			m_resourcePath.setText("");

		materializer = (node == null)
				? null
				: node.getMaterializerID();
		matIdx = 0;
		if(materializer != null)
		{
			matIdx = m_materializer.indexOf(materializer);
			if(matIdx < 0)
				matIdx = 0;
		}
		m_materializer.select(matIdx);
		m_installLocation.setText(destination.toOSString());
		m_skipButton.setSelection(skip);
		m_useDefaultsButton.setSelection(useDefaults);
		m_conflictResolutionCombo.select(mspec.getConflictResolution(request).ordinal());
		UiUtils.setEnabledRecursively(m_nodeComposite, !useDefaults);

		// Show/Hide all except the button.
		//
		UiUtils.setChildrenVisible(m_settingsGroup, !skip && resolution.isMaterializable());
		if(skip)
			m_skipButton.setVisible(true);

		updatePageCompletion();
	}

	private MaterializationNodeBuilder getMaterializationNode(Resolution resolution) throws CoreException
	{
		QueryWizard wizard = getQueryWizard();
		ComponentName cname = resolution.getComponentIdentifier();
		MaterializationSpecBuilder mspec = wizard.getMaterializationSpec();
		MaterializationNodeBuilder node = mspec.getMatchingNode(cname);
		if(node == null)
		{
			node = new MaterializationNodeBuilder();
			node.setNamePattern(Pattern.compile("^\\Q" + cname.getName() + "\\E$"));
			node.setCategory(cname.getCategory());
			mspec.getNodes().add(node);
		}
		wizard.invalidateMaterializationContext();
		return node;
	}

	private void skipEvent(boolean skip)
	{
		Resolution resolution = getSelectedComponent();
		if(resolution == null)
			return;

		try
		{
			MaterializationContext context = getQueryWizard().getMaterializationContext();
			MaterializationNode node = context.getMaterializationSpec().getMatchingNode(resolution.getRequest());
			if(skip)
			{
				if(node == null || !node.isExclude())
					getMaterializationNode(resolution).setExclude(true);
			}
			else
			{
				if(node != null && node.isExclude())
				{
					useDefaultEvent(true);
					return;
				}
			}

			setSelectedComponentValues(resolution);
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	private void updatePageCompletion() throws CoreException
	{
		setErrorMessage(null);
		setPageComplete(false);

		MaterializationContext context = getQueryWizard().getMaterializationContext();
		MaterializationSpec mspec = context.getMaterializationSpec();
		List<Resolution> resolutions = getTableInput();
		Collections.sort(resolutions, new ResolutionComparator());

		for(Resolution resolution : resolutions)
		{
			if(!resolution.isMaterializable() || mspec.isExcluded(resolution.getRequest()))
				continue;

			String id = resolution.getRequest().getViewName();
			IPath destination = context.getInstallLocation(resolution);
			File f = destination.toFile();
			if(!f.isAbsolute())
			{
				setErrorMessage("The location " + f + " for " + id + " is not an absolute path");
				return;
			}

			if(f.exists()
					&& context.getMaterializationSpec().getConflictResolution(resolution.getRequest()) == ConflictResolution.FAIL
					&& !resolution.isMaterialized(destination))
			{
				if(f.isFile())
				{
					setErrorMessage("The location " + f + " for " + id + " already exists as a file");
					return;
				}
				else if(f.list().length != 0)
				{
					setErrorMessage("The location " + f + " for " + id + " exists but is not empty");
					return;
				}
			}
		}
		setPageComplete(true);
	}

	private void useDefaultEvent(boolean flag)
	{
		Resolution resolution = getSelectedComponent();
		if(resolution != null)
		{
			try
			{
				QueryWizard wizard = getQueryWizard();
				ComponentRequest rq = resolution.getRequest();
				MaterializationSpecBuilder bld = wizard.getMaterializationSpec();
				MaterializationNodeBuilder node = bld.getMatchingNode(rq);
				if(flag)
				{
					if(node != null)
					{
						bld.getNodes().remove(node);
						wizard.invalidateMaterializationContext();
					}
				}
				else
				{
					if(node == null)
					{
						node = new MaterializationNodeBuilder();
						node.setNamePattern(Pattern.compile("^\\Q" + rq.getName() + "\\E$"));
						node.setCategory(rq.getCategory());
						bld.getNodes().add(node);
						wizard.invalidateMaterializationContext();
					}
				}
				setSelectedComponentValues(resolution);
			}
			catch(CoreException e)
			{
				displayException(e);
			}
		}
	}

	private void conflictResolutionEvent(int ordinal)
	{
		Resolution resolution = getSelectedComponent();
		if(resolution == null && ordinal < 0)
			return;

		try
		{
			MaterializationContext context = getQueryWizard().getMaterializationContext();
			MaterializationSpec mspec = context.getMaterializationSpec();
			ConflictResolution cr = ConflictResolution.values()[ordinal];
			if(!cr.equals(mspec.getConflictResolution(resolution.getRequest())))
				getMaterializationNode(resolution).setConflictResolution(cr);
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}
}

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

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.ui.DynamicTableLayout;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.SaveRunnable;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.dialogs.SaveAsDialog;

/**
 * @author Kenneth Olwing
 * @author Thomas Hallgren
 */
public class ResolverNodePage extends AbstractQueryPage
{
	class RequestLabelProvider extends LabelProvider implements ITableLabelProvider
	{
		public Image getColumnImage(Object element, int columnIndex)
		{
			return null;
		}

		public String getColumnText(Object element, int columnIndex)
		{
			ComponentRequest rq = (ComponentRequest)element;
			String lbl;
			switch(columnIndex)
			{
			case 0:
				lbl = rq.getName();
				break;
			case 1:
				IVersionDesignator vd = rq.getVersionDesignator();
				lbl = vd == null ? "" : vd.toString();
				break;
			default:
				lbl = rq.getCategory();
			}
			return lbl;
		}
	}

	TableViewer m_dependenciesTable;

	private Group m_detailGroup;

	private Image m_grayDotImage;

	private Image m_grayDotWithRedExclamationImage;

	private Image m_greenDotImage;

	private Font m_itemItalicFont;

	private final HashMap<Resolution, Integer> m_masterDups = new HashMap<Resolution, Integer>();

	private Tree m_masterTree;

	private Composite m_masterTreeComposite;

	private final HashMap<Resolution, DepNode> m_parentWhenFirstSeen = new HashMap<Resolution, DepNode>();

	private Image m_redDotImage;

	private Button m_reresolveButton;

	private Button m_showTargetPlatformButton;

	private Button m_unresolveButton;

	private Image m_yellowDotImage;

	public ResolverNodePage()
	{
		super("");

		m_redDotImage = UiPlugin.getImageDescriptor("images/red_dot_16x16.bmp").createImage();
		m_greenDotImage = UiPlugin.getImageDescriptor("images/green_dot_16x16.bmp").createImage();
		m_yellowDotImage = UiPlugin.getImageDescriptor("images/yellow_dot_16x16.bmp").createImage();
		m_grayDotImage = UiPlugin.getImageDescriptor("images/gray_dot_16x16.bmp").createImage();
		m_grayDotWithRedExclamationImage = UiPlugin.getImageDescriptor(
			"images/gray_dot_with_red_exclamation_16x16.bmp").createImage();

		setDescription(UiPlugin.getResourceString("DepNodePage.description")); //$NON-NLS-1$
	}

	@Override
	public Composite createControls(Composite parent)
	{
		Composite topComposite = new Composite(parent, SWT.NONE);
		topComposite.setLayout(new GridLayout());
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		SashForm form = new SashForm(topComposite, SWT.VERTICAL);
		form.setLayout(new GridLayout());
		form.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		createMasterGroup(form);
		createDetailGroup(form);
		form.setWeights(new int[] { 60, 40 });

		createButtonGroup(topComposite);
		return topComposite;
	}

	@Override
	public void dispose()
	{
		m_redDotImage.dispose();
		m_redDotImage = null;
		m_greenDotImage.dispose();
		m_greenDotImage = null;
		m_grayDotImage.dispose();
		m_grayDotImage = null;
		m_grayDotWithRedExclamationImage.dispose();
		m_grayDotWithRedExclamationImage = null;
		m_yellowDotImage.dispose();
		m_yellowDotImage = null;
		if(m_itemItalicFont != null)
			m_itemItalicFont.dispose();
		m_itemItalicFont = null;

		super.dispose();
	}

	@Override
	protected void pageIsShowing()
	{
		super.pageIsShowing();
		resetMaster();
		DepNode currentNode = getSelectedMasterNode();

		TreeItem found = null;
		for(TreeItem child : m_masterTree.getItems())
		{
			TreeItem ti = findItemWithData(child, currentNode);
			if(ti != null)
			{
				found = ti;
				break;
			}
		}
		if(found == null)
			found = m_masterTree.getItems()[0];

		m_masterTree.setSelection(new TreeItem[] { found });
		masterTreeSelectionEvent(found, false);

		updatePageCompletion();
		getControl().setVisible(true);
	}

	void masterTreeSelectionEvent(TreeItem ti, boolean checkEvent)
	{
		try
		{
			DepNode node = (DepNode)ti.getData();
			Resolution resolution = node.getResolution();
			boolean isResolved = (resolution != null);
			if(isResolved)
			{
				CSpec cspec = resolution.getCSpec();
				m_dependenciesTable.setInput(cspec.getDependencies().values());
				m_detailGroup.setText("Dependencies in " + node.getViewName() + ":");
			}
			else
			{
				m_dependenciesTable.setInput(null);
				m_detailGroup.setText("");
			}
			BillOfMaterials bom = getQueryWizard().getBOM();
			m_reresolveButton.setEnabled(!bom.isFullyResolved());
			m_unresolveButton.setEnabled(isResolved);
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	void resetMaster()
	{
		QueryWizard wizard = getQueryWizard();
		BillOfMaterials bom = wizard.getBOM();
		try
		{
			m_masterDups.clear();
			countDuplicates(null, bom, wizard.getMaterializationContext().getMaterializationSpec());
			m_masterTree.removeAll();
			m_masterTree.clearAll(false);
			addMasterItem(new TreeItem(m_masterTree, SWT.NONE), null, bom);
			m_reresolveButton.setEnabled(!bom.isFullyResolved());
			updatePageCompletion();
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	void reresolve()
	{
		try
		{
			QueryWizard wizard = getQueryWizard();
			BillOfMaterials bom = wizard.getBOM();
			ResolutionContext context = new ResolutionContext(bom.getQuery());
			context.setContinueOnError(true);

			final BillOfMaterials[] bin = new BillOfMaterials[] { bom };
			final IResolver resolver = new MainResolver(context);
			wizard.getContainer().run(true, true, new IRunnableWithProgress()
			{
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
				{
					try
					{
						BillOfMaterials oldBOM = bin[0];
						BillOfMaterials newBOM = oldBOM.fullyResolve(resolver, monitor);
						if(oldBOM.equals(newBOM))
							bin[0] = null;
						else
							bin[0] = newBOM;
					}
					catch(Throwable t)
					{
						throw new InvocationTargetException(t);
					}
				}
			});
			CorePlugin.logWarningsAndErrors(context.getStatus());
			bom = bin[0];
			if(bom != null)
			{
				wizard.setBOM(bom);
				resetMaster();
			}
		}
		catch(Throwable t)
		{
			displayException(BuckminsterException.wrap(t));
		}
	}

	void saveBOMInFileSystem()
	{
		FileDialog dlg = new FileDialog(getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.bom" });
		String location = dlg.open();
		if(location == null)
			return;
		saveToPath(new Path(location));
	}

	void saveBOMInWorkspace()
	{
		SaveAsDialog dialog = new SaveAsDialog(getShell());
		if(dialog.open() == Window.CANCEL)
			return;

		IPath filePath = dialog.getResult();
		if(filePath == null)
			return;

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IFile file = workspace.getRoot().getFile(filePath);
		saveToPath(file.getLocation());
	}

	void unresolveNode()
	{
		final DepNode node = getSelectedMasterNode();
		if(node == null)
			return;

		if(node instanceof UnresolvedNode)
			return;
		
		QueryWizard queryWizard = getQueryWizard();
		try
		{
			queryWizard.setBOM(queryWizard.getBOM().replaceNode(new UnresolvedNode(node.getQualifiedDependency())));
		}
		catch(CoreException e)
		{
			displayException(e);
		}
		resetMaster();
	}

	private void addMasterItem(TreeItem ti, DepNode parent, DepNode node) throws CoreException
	{
		ti.removeAll();
		ti.setText(node.getViewName());
		ti.setData(node);

		if(node instanceof UnresolvedNode)
		{
			if(node.isFullyResolved(getContext().getComponentQuery()))
				ti.setImage(m_yellowDotImage);
			else
				ti.setImage(m_redDotImage);
			ti.setFont(null);
			return;
		}

		Resolution resolution = node.getResolution();
		if(resolution != null)
		{
			Integer nc = m_masterDups.get(resolution);
			if(nc != null && nc.intValue() > 1 && !wasParentWhenFirstSeen(parent, node))
			{
				ti.setImage(m_grayDotImage);
				ti.setFont(m_itemItalicFont);
				return;
			}
		}

		ti.setImage(m_greenDotImage);
		ti.setFont(null);
		boolean showPlatformTargets = m_showTargetPlatformButton.getSelection();
		for(DepNode child : getSortedChildren(node))
		{
			Resolution ci = child.getResolution();
			if(!showPlatformTargets && ci != null)
			{
				if(IReaderType.ECLIPSE_PLATFORM.equals(ci.getProvider().getReaderTypeId()))
					continue;
			}
			addMasterItem(new TreeItem(ti, SWT.NONE), node, child);
		}
		ti.setExpanded(true);
	}

	private void countDuplicates(DepNode parent, DepNode node, MaterializationSpec mspec)
	throws CoreException
	{
		Resolution ci = node.getResolution();
		if(ci == null)
			return;

		if(!mspec.isExcluded(ci.getComponentIdentifier()))
		{
			Integer nc = m_masterDups.get(ci);
			int nci;
			if(nc == null)
			{
				nci = 0;
				if(parent != null)
					m_parentWhenFirstSeen.put(ci, parent);
			}
			else
				nci = nc.intValue();

			m_masterDups.put(ci, new Integer(1 + nci));
			for(DepNode child : getSortedChildren(node))
				countDuplicates(node, child, mspec);
		}
	}

	private void createButtonGroup(Composite parent)
	{
		Composite buttons = new Composite(parent, SWT.NONE);
		buttons.setLayout(new GridLayout(3, false));
		buttons.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));

		Button saveButton = UiUtils.createPushButton(buttons, "Save BOM", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				saveBOMInWorkspace();
			}
		});
		saveButton.setLayoutData(new GridData(SWT.TRAIL, SWT.TOP, true, false));

		Button extSaveButton = UiUtils.createPushButton(buttons, "External Save BOM", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				saveBOMInFileSystem();
			}
		});
		extSaveButton.setLayoutData(new GridData(SWT.TRAIL, SWT.TOP, false, false));
	}

	private void createDetailGroup(Composite parent)
	{
		m_detailGroup = new Group(parent, SWT.NONE);
		m_detailGroup.setLayout(new GridLayout());
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.heightHint = 100;
		m_detailGroup.setLayoutData(gd);

		Table table = new Table(m_detailGroup, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);

		String[] columnNames = new String[] { "Name", "Version designator", "Category" };
		int[] columnWeights = new int[] { 20, 10, 10 };

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

		m_dependenciesTable = new TableViewer(table);
		m_dependenciesTable.setLabelProvider(new RequestLabelProvider());
		m_dependenciesTable.setContentProvider(new ArrayContentProvider());
	}

	private void createMasterGroup(Composite parent)
	{
		Group masterGroup = new Group(parent, SWT.NONE);
		masterGroup.setLayout(new GridLayout(3, false));
		masterGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		masterGroup.setText("Component specification selection:");

		m_masterTreeComposite = new Composite(masterGroup, SWT.NONE);
		m_masterTreeComposite.setLayout(new GridLayout());

		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.heightHint = 100;
		gd.horizontalSpan = 3;
		m_masterTreeComposite.setLayoutData(gd);

		m_masterTree = new Tree(m_masterTreeComposite, SWT.NONE | SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL
			| SWT.V_SCROLL);
		m_masterTree.setLayout(new GridLayout());
		m_masterTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		m_masterTree.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				masterTreeSelectionEvent((TreeItem)se.item, false);
			}
		});

		Font f = m_masterTreeComposite.getFont();
		FontData[] fda = f.getFontData();
		for(FontData fd : fda)
			fd.setStyle(SWT.ITALIC);
		m_itemItalicFont = new Font(Display.getCurrent(), fda);

		Composite modifiersComposite = new Composite(masterGroup, SWT.NONE);
		modifiersComposite.setLayout(new GridLayout(3, false));
		modifiersComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

		m_showTargetPlatformButton = UiUtils.createCheckButton(modifiersComposite,
			"Show target platform components", new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					resetMaster();
				}
			});

		m_reresolveButton = UiUtils.createPushButton(modifiersComposite, "Re-resolve", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				reresolve();
			}
		});

		m_unresolveButton = UiUtils.createPushButton(modifiersComposite, "Unresolve node", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				unresolveNode();
			}
		});
	}

	private TreeItem findItemWithData(TreeItem ti, DepNode node)
	{
		if(ti.getData() == node)
			return ti;

		for(TreeItem child : ti.getItems())
		{
			TreeItem answer = findItemWithData(child, node);
			if(answer != null)
				return answer;
		}
		return null;
	}

	private DepNode getSelectedMasterNode()
	{
		TreeItem[] tia = m_masterTree.getSelection();
		return (tia.length > 0) ? (DepNode)tia[0].getData() : null;
	}

	private Collection<DepNode> getSortedChildren(DepNode parent) throws CoreException
	{
		Collection<DepNode> children = parent.getChildren();
		if(children.size() > 1)
		{
			Map<String, DepNode> sortedMap = new TreeMap<String, DepNode>();
			for(DepNode child : children)
				sortedMap.put(child.getViewName(), child);
			children = sortedMap.values();
		}
		return children;
	}

	private void saveToPath(IPath path)
	{
		QueryWizard wizard = getQueryWizard();
		IWizardContainer container = wizard.getContainer();
		try
		{
			SaveRunnable sr = new SaveRunnable(wizard.getBOM().exportGraph(), path);
			container.run(true, true, sr);
			wizard.getMaterializationSpec().setURL(path.toFile().toURI().toURL());
		}
		catch(InterruptedException e)
		{
		}
		catch(Exception e)
		{
			CoreException t = BuckminsterException.wrap(e);
			String msg = "Unable to save file " + path;
			CorePlugin.getLogger().error(msg, t);
			ErrorDialog.openError(getShell(), null, msg, t.getStatus());
		}
	}

	private void updatePageCompletion()
	{
		boolean complete;
		String errorMsg = null;
		QueryWizard qw = getQueryWizard();
		BillOfMaterials bom = qw.getBOM();
		try
		{
			complete = (bom != null);
			if(complete)
			{
				if(!bom.isFullyResolved())
					complete = qw.getContext().isContinueOnError();
			}
		}
		catch(CoreException e)
		{
			complete = false;
			displayException(e);
		}
		if(!complete)
			errorMsg = "A selected specification is unresolved";
		setPageComplete(complete);
		setErrorMessage(errorMsg);
	}

	private boolean wasParentWhenFirstSeen(DepNode parent, DepNode node) throws CoreException
	{
		if(parent == null)
			//
			// Top node is never a duplicate
			//
			return true;

		Resolution resolution = node.getResolution();
		return (resolution == null) ? false : m_parentWhenFirstSeen.get(resolution) == parent;
	}
}

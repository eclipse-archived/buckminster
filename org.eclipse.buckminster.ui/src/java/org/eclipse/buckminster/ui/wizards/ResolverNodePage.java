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
import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.IResolution;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.SaveRunnable;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.internal.DynamicTableLayout;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.osgi.util.NLS;
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
public class ResolverNodePage extends AbstractQueryPage {
	class RequestLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			ComponentRequest rq = (ComponentRequest) element;
			String lbl;
			switch (columnIndex) {
				case 0:
					lbl = rq.getName();
					break;
				case 1:
					VersionRange vd = rq.getVersionRange();
					lbl = vd == null ? "" : VersionHelper.getHumanReadable(vd); //$NON-NLS-1$
					break;
				default:
					lbl = rq.getComponentTypeID();
			}
			return lbl;
		}
	}

	TableViewer dependenciesTable;

	private Group detailGroup;

	private Image grayDotImage;

	private Image grayDotWithRedExclamationImage;

	private Image greenDotImage;

	private Font itemItalicFont;

	private final HashMap<Resolution, Integer> masterDups = new HashMap<Resolution, Integer>();

	private Tree masterTree;

	private Composite masterTreeComposite;

	private final HashMap<Resolution, BOMNode> parentWhenFirstSeen = new HashMap<Resolution, BOMNode>();

	private Image redDotImage;

	private Button reresolveButton;

	private Button showTargetPlatformButton;

	private Button unresolveButton;

	private Image yellowDotImage;

	public ResolverNodePage() {
		super(""); //$NON-NLS-1$

		redDotImage = UiPlugin.getImageDescriptor("images/red_dot_16x16.bmp").createImage(); //$NON-NLS-1$
		greenDotImage = UiPlugin.getImageDescriptor("images/green_dot_16x16.bmp").createImage(); //$NON-NLS-1$
		yellowDotImage = UiPlugin.getImageDescriptor("images/yellow_dot_16x16.bmp").createImage(); //$NON-NLS-1$
		grayDotImage = UiPlugin.getImageDescriptor("images/gray_dot_16x16.bmp").createImage(); //$NON-NLS-1$
		grayDotWithRedExclamationImage = UiPlugin.getImageDescriptor("images/gray_dot_with_red_exclamation_16x16.bmp").createImage(); //$NON-NLS-1$

		setDescription(Messages.resolution_tree);
	}

	@Override
	public Composite createControls(Composite parent) {
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
	public void dispose() {
		redDotImage.dispose();
		redDotImage = null;
		greenDotImage.dispose();
		greenDotImage = null;
		grayDotImage.dispose();
		grayDotImage = null;
		grayDotWithRedExclamationImage.dispose();
		grayDotWithRedExclamationImage = null;
		yellowDotImage.dispose();
		yellowDotImage = null;
		if (itemItalicFont != null)
			itemItalicFont.dispose();
		itemItalicFont = null;

		super.dispose();
	}

	@Override
	protected void pageIsShowing() {
		super.pageIsShowing();
		resetMaster();
		BOMNode currentNode = getSelectedMasterNode();

		TreeItem found = null;
		for (TreeItem child : masterTree.getItems()) {
			TreeItem ti = findItemWithData(child, currentNode);
			if (ti != null) {
				found = ti;
				break;
			}
		}
		if (found == null)
			found = masterTree.getItems()[0];

		masterTree.setSelection(new TreeItem[] { found });
		masterTreeSelectionEvent(found, false);

		updatePageCompletion();
		getControl().setVisible(true);
	}

	void masterTreeSelectionEvent(TreeItem ti, boolean checkEvent) {
		try {
			BOMNode node = (BOMNode) ti.getData();
			IResolution resolution = node.getResolution();
			boolean isResolved = (resolution != null);
			if (isResolved) {
				ICSpecData cspec = resolution.getCSpec();
				dependenciesTable.setInput(cspec.getDependencies());
				detailGroup.setText(NLS.bind(Messages.dependencies_in_0, node.getViewName()));
			} else {
				dependenciesTable.setInput(null);
				detailGroup.setText(""); //$NON-NLS-1$
			}
			BillOfMaterials bom = getQueryWizard().getBOM();
			reresolveButton.setEnabled(!bom.isFullyResolved(getContext()));
			unresolveButton.setEnabled(isResolved);
		} catch (CoreException e) {
			displayException(e);
		}
	}

	void reresolve() {
		try {
			QueryWizard wizard = getQueryWizard();
			BillOfMaterials bom = wizard.getBOM();
			ResolutionContext context = new ResolutionContext(bom.getQuery());
			context.setContinueOnError(true);

			final BillOfMaterials[] bin = new BillOfMaterials[] { bom };
			final IResolver resolver = new MainResolver(context);
			wizard.getContainer().run(true, true, new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					try {
						BillOfMaterials oldBOM = bin[0];
						BillOfMaterials newBOM = oldBOM.fullyResolve(resolver, monitor);
						if (oldBOM.equals(newBOM))
							bin[0] = null;
						else
							bin[0] = newBOM;
					} catch (Throwable t) {
						throw new InvocationTargetException(t);
					}
				}
			});
			CorePlugin.logWarningsAndErrors(context.getStatus());
			bom = bin[0];
			if (bom != null) {
				wizard.setBOM(bom);
				resetMaster();
			}
		} catch (Throwable t) {
			displayException(BuckminsterException.wrap(t));
		}
	}

	void resetMaster() {
		QueryWizard wizard = getQueryWizard();
		BillOfMaterials bom = wizard.getBOM();
		try {
			masterDups.clear();
			countDuplicates(null, bom, wizard.getMaterializationContext().getMaterializationSpec());
			masterTree.removeAll();
			masterTree.clearAll(false);
			addMasterItem(new TreeItem(masterTree, SWT.NONE), null, bom);
			reresolveButton.setEnabled(!bom.isFullyResolved(wizard.getContext()));
			updatePageCompletion();
		} catch (CoreException e) {
			displayException(e);
		}
	}

	void saveBOMInFileSystem() {
		FileDialog dlg = new FileDialog(getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.bom" }); //$NON-NLS-1$
		String location = dlg.open();
		if (location == null)
			return;
		saveToPath(new Path(location));
	}

	void saveBOMInWorkspace() {
		SaveAsDialog dialog = new SaveAsDialog(getShell());
		if (dialog.open() == Window.CANCEL)
			return;

		IPath filePath = dialog.getResult();
		if (filePath == null)
			return;

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IFile file = workspace.getRoot().getFile(filePath);
		saveToPath(file.getLocation());
	}

	void unresolveNode() {
		final BOMNode node = getSelectedMasterNode();
		if (node == null || node.getResolution() == null)
			return;

		QueryWizard queryWizard = getQueryWizard();
		try {
			queryWizard.setBOM(queryWizard.getBOM().replaceNode(new UnresolvedNode(node.getQualifiedDependency())));
		} catch (CoreException e) {
			displayException(e);
		}
		resetMaster();
	}

	private void addMasterItem(TreeItem ti, BOMNode parent, BOMNode node) throws CoreException {
		ti.removeAll();
		ti.setText(node.getViewName());
		ti.setData(node);

		IResolution resolution = node.getResolution();
		if (resolution == null) {
			if (node.isFullyResolved(getContext().getComponentQuery(), getContext()))
				ti.setImage(yellowDotImage);
			else
				ti.setImage(redDotImage);
			ti.setFont(null);
			return;
		}

		Integer nc = masterDups.get(resolution);
		if (nc != null && nc.intValue() > 1 && !wasParentWhenFirstSeen(parent, node)) {
			ti.setImage(grayDotImage);
			ti.setFont(itemItalicFont);
			return;
		}

		ti.setImage(greenDotImage);
		ti.setFont(null);
		boolean showPlatformTargets = showTargetPlatformButton.getSelection();
		for (BOMNode child : getSortedChildren(node)) {
			IResolution ci = child.getResolution();
			if (!showPlatformTargets && ci != null) {
				if (IReaderType.ECLIPSE_PLATFORM.equals(ci.getProvider().getReaderTypeId()))
					continue;
			}
			addMasterItem(new TreeItem(ti, SWT.NONE), node, child);
		}
		ti.setExpanded(true);
	}

	private void countDuplicates(BOMNode parent, BOMNode node, MaterializationSpec mspec) throws CoreException {
		Resolution ci = node.getResolution();
		if (ci == null)
			return;

		if (!mspec.isExcluded(ci)) {
			Integer nc = masterDups.get(ci);
			int nci;
			if (nc == null) {
				nci = 0;
				if (parent != null)
					parentWhenFirstSeen.put(ci, parent);
			} else
				nci = nc.intValue();

			masterDups.put(ci, new Integer(1 + nci));
			if (nci == 0) {
				for (BOMNode child : getSortedChildren(node))
					countDuplicates(node, child, mspec);
			}
		}
	}

	private void createButtonGroup(Composite parent) {
		Composite buttons = new Composite(parent, SWT.NONE);
		buttons.setLayout(new GridLayout(3, false));
		buttons.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));

		Button saveButton = UiUtils.createPushButton(buttons, Messages.save_bom, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveBOMInWorkspace();
			}
		});
		saveButton.setLayoutData(new GridData(SWT.TRAIL, SWT.TOP, true, false));

		Button extSaveButton = UiUtils.createPushButton(buttons, Messages.external_save_bom, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveBOMInFileSystem();
			}
		});
		extSaveButton.setLayoutData(new GridData(SWT.TRAIL, SWT.TOP, false, false));
	}

	private void createDetailGroup(Composite parent) {
		detailGroup = new Group(parent, SWT.NONE);
		detailGroup.setLayout(new GridLayout());
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.heightHint = 100;
		detailGroup.setLayoutData(gd);

		Table table = new Table(detailGroup, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);

		String[] columnNames = new String[] { Messages.name, Messages.version_designator, Messages.category };
		int[] columnWeights = new int[] { 20, 10, 10 };

		table.setHeaderVisible(true);
		DynamicTableLayout layout = new DynamicTableLayout(150);
		for (int idx = 0; idx < columnNames.length; idx++) {
			TableColumn tableColumn = new TableColumn(table, SWT.LEFT, idx);
			tableColumn.setText(columnNames[idx]);
			layout.addColumnData(new ColumnWeightData(columnWeights[idx], true));
		}
		table.setLayout(layout);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		dependenciesTable = new TableViewer(table);
		dependenciesTable.setLabelProvider(new RequestLabelProvider());
		dependenciesTable.setContentProvider(new ArrayContentProvider());
	}

	private void createMasterGroup(Composite parent) {
		Group masterGroup = new Group(parent, SWT.NONE);
		masterGroup.setLayout(new GridLayout(3, false));
		masterGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		masterGroup.setText(Messages.component_specification_selection);

		masterTreeComposite = new Composite(masterGroup, SWT.NONE);
		masterTreeComposite.setLayout(new GridLayout());

		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.heightHint = 100;
		gd.horizontalSpan = 3;
		masterTreeComposite.setLayoutData(gd);

		masterTree = new Tree(masterTreeComposite, SWT.NONE | SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		masterTree.setLayout(new GridLayout());
		masterTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		masterTree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				masterTreeSelectionEvent((TreeItem) se.item, false);
			}
		});

		Font f = masterTreeComposite.getFont();
		FontData[] fda = f.getFontData();
		for (FontData fd : fda)
			fd.setStyle(SWT.ITALIC);
		itemItalicFont = new Font(Display.getCurrent(), fda);

		Composite modifiersComposite = new Composite(masterGroup, SWT.NONE);
		modifiersComposite.setLayout(new GridLayout(3, false));
		modifiersComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

		showTargetPlatformButton = UiUtils.createCheckButton(modifiersComposite, Messages.show_target_platform_components, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				resetMaster();
			}
		});

		reresolveButton = UiUtils.createPushButton(modifiersComposite, Messages.re_resolve, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				reresolve();
			}
		});

		unresolveButton = UiUtils.createPushButton(modifiersComposite, Messages.unresolved_node, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				unresolveNode();
			}
		});
	}

	private TreeItem findItemWithData(TreeItem ti, BOMNode node) {
		if (ti.getData() == node)
			return ti;

		for (TreeItem child : ti.getItems()) {
			TreeItem answer = findItemWithData(child, node);
			if (answer != null)
				return answer;
		}
		return null;
	}

	private BOMNode getSelectedMasterNode() {
		TreeItem[] tia = masterTree.getSelection();
		return (tia.length > 0) ? (BOMNode) tia[0].getData() : null;
	}

	private Collection<BOMNode> getSortedChildren(BOMNode parent) throws CoreException {
		Collection<BOMNode> children = parent.getChildren();
		if (children.size() > 1) {
			Map<String, BOMNode> sortedMap = new TreeMap<String, BOMNode>();
			for (BOMNode child : children)
				sortedMap.put(child.getViewName(), child);
			children = sortedMap.values();
		}
		return children;
	}

	private void saveToPath(IPath path) {
		QueryWizard wizard = getQueryWizard();
		IWizardContainer container = wizard.getContainer();
		try {
			SaveRunnable sr = new SaveRunnable(wizard.getBOM(), path);
			container.run(true, true, sr);
			wizard.getMaterializationSpec().setURL(path.toFile().toURI().toString());
		} catch (InterruptedException e) {
		} catch (Exception e) {
			CoreException t = BuckminsterException.wrap(e);
			String msg = NLS.bind(Messages.unable_to_save_file_0, path);
			CorePlugin.getLogger().error(t, msg);
			ErrorDialog.openError(getShell(), null, msg, t.getStatus());
		}
	}

	private void updatePageCompletion() {
		boolean complete;
		String errorMsg = null;
		QueryWizard qw = getQueryWizard();
		BillOfMaterials bom = qw.getBOM();
		try {
			complete = (bom != null);
			if (complete) {
				if (!bom.isFullyResolved(qw.getContext()))
					complete = qw.getContext().isContinueOnError();
			}
		} catch (CoreException e) {
			complete = false;
			displayException(e);
		}
		if (!complete)
			errorMsg = Messages.a_selected_specification_is_unresolved;
		setPageComplete(complete);
		setErrorMessage(errorMsg);
	}

	private boolean wasParentWhenFirstSeen(BOMNode parent, BOMNode node) throws CoreException {
		if (parent == null)
			//
			// Top node is never a duplicate
			//
			return true;

		IResolution resolution = node.getResolution();
		return (resolution == null) ? false : parentWhenFirstSeen.get(resolution) == parent;
	}
}

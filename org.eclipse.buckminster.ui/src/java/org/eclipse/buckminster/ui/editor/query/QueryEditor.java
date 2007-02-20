/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.editor.query;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.model.ComponentCategory;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.materializer.MaterializerJob;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.query.builder.AdvisorNodeBuilder;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.query.model.MutableLevel;
import org.eclipse.buckminster.core.query.model.NotEmptyAction;
import org.eclipse.buckminster.core.query.model.SourceLevel;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.ui.ChangeAdapter;
import org.eclipse.buckminster.ui.DynamicTableLayout;
import org.eclipse.buckminster.ui.LabeledCombo;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.actions.BlankQueryAction;
import org.eclipse.buckminster.ui.editor.ComponentRequestGroup;
import org.eclipse.buckminster.ui.editor.SaveRunnable;
import org.eclipse.buckminster.ui.editor.VersionDesignator;
import org.eclipse.buckminster.ui.wizards.QueryWizard;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.part.EditorPart;
import org.xml.sax.SAXException;

/**
 * Editor for <code>ComponentQuery</code>.
 * 
 * @author Thomas Hallgren
 */
public class QueryEditor extends EditorPart
{
	public class EditorComponentRequestGroup extends ComponentRequestGroup
	{
		public EditorComponentRequestGroup(Composite parent, int style)
		{
			super(parent, style);
		}

		@Override
		protected ChangeAdapter getChangeAdapter()
		{
			return new TriggerChangedRequestListener();
		}
	}

	class AdvisorNodeLabelProvider extends LabelProvider implements ITableLabelProvider
	{
		public Image getColumnImage(Object element, int columnIndex)
		{
			return null;
		}

		public String getColumnText(Object element, int columnIndex)
		{
			AdvisorNodeBuilder node = (AdvisorNodeBuilder)element;
			String lbl;
			switch(columnIndex)
			{
			case 0:
				lbl = node.getNamePattern().toString();
				break;
			case 1:
				lbl = node.getCategory();
				break;
			default:
				lbl = null;
			}
			return lbl;
		}
	}

	class TriggerChangedRequestListener extends ChangeAdapter
	{
		@Override
		protected void onChange(TypedEvent e)
		{
			setDirty(true);
		}
	}

	private ComponentQueryBuilder m_componentQuery;

	private ComponentRequestGroup m_componentRequestGroup;

	private Button m_editOrCancelButton;

	private Button m_enableOverride;

	private boolean m_nodeEditMode;

	private boolean m_hasChanges;

	private Button m_resolveButton;

	private Button m_materializeButton;

	private Button m_externalSaveAsButton;

	private Button m_moveDownButton;

	private Button m_moveUpButton;

	private boolean m_mute;

	private LabeledCombo m_mutableLevel;

	private Text m_namePattern;

	private LabeledCombo m_category;

	private Text m_overlayFolder;

	private Button m_overlayBrowseButton;

	private Text m_wantedAttributes;

	private Button m_prune;

	private Text m_replaceFrom;

	private Text m_replaceTo;

	private boolean m_needsRefresh;

	private Button m_newOrSaveButton;

	private TableViewer m_nodeTable;

	private Button m_removeButton;

	private Text m_requestURL;

	private Text m_propertyURL;

	private LabeledCombo m_sourceLevel;

	private Button m_skipComponent;

	private Button m_allowCircular;

	private Label m_known;

	private Button m_useInstalled;

	private Button m_useMaterialization;

	private Button m_useProject;

	private VersionDesignator m_versionOverride;

	private LabeledCombo m_whenNotEmpty;

	private boolean m_continueOnError;

	@Override
	public void createPartControl(Composite parent)
	{
	    parent.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
	    parent.setBackgroundMode(SWT.INHERIT_FORCE);
		ScrolledComposite sc1 = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		Composite self = new Composite(sc1, SWT.NONE);
		sc1.setContent(self);
		self.setLayout(new GridLayout(1, true));

		createGlobalGroup(self);

		Group advisorNodes = new Group(self, SWT.NONE);
		advisorNodes.setText("Advisor Nodes");
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 3;
		advisorNodes.setLayout(layout);
		advisorNodes.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		createNodeTableGroup(advisorNodes);
		createButtonBox(advisorNodes);
		createNodeFields(advisorNodes);

		createActionButtons(self);
	    self.setSize(self.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	@Override
	public void doSave(IProgressMonitor monitor)
	{
		if(!commitChangesToQuery())
			return;

		IEditorInput input = getEditorInput();
		if(input == null)
			return;

		IPath path = (input instanceof ILocationProvider) ? ((ILocationProvider)input).getPath(input)
				: ((IPathEditorInput)input).getPath();

		saveToPath(path);
	}

	@Override
	public void doSaveAs()
	{
		if(!commitChangesToQuery())
			return;

		IEditorInput input = getEditorInput();
		if(input == null)
			return;

		SaveAsDialog dialog = new SaveAsDialog(getSite().getShell());
		IFile original = (input instanceof IFileEditorInput) ? ((IFileEditorInput)input).getFile() : null;
		if(original != null)
			dialog.setOriginalFile(original);

		if(dialog.open() == Window.CANCEL)
			return;

		IPath filePath = dialog.getResult();
		if(filePath == null)
			return;

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IFile file = workspace.getRoot().getFile(filePath);
		saveToPath(file.getLocation());
	}

	public void doExternalSaveAs()
	{
		if(!commitChangesToQuery())
			return;
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.cquery" });
		final String location = dlg.open();
		if(location == null)
			return;
		saveToPath(new Path(location));
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException
	{
		if(!(input instanceof ILocationProvider || input instanceof IPathEditorInput))
			throw new PartInitException("Invalid Input");
		setSite(site);

		InputStream stream = null;
		try
		{
			IPath path = (input instanceof ILocationProvider) ? ((ILocationProvider)input).getPath(input)
					: ((IPathEditorInput)input).getPath();

			File file = path.toFile();
			m_componentQuery = new ComponentQueryBuilder();
			if(file.length() == 0)
			{
				String defaultName = file.getName();
				if(defaultName.startsWith(BlankQueryAction.TEMP_FILE_PREFIX))
					defaultName = "";
				else
				{
					int lastDot = defaultName.lastIndexOf('.');
					if(lastDot > 0)
						defaultName = defaultName.substring(0, lastDot);
				}
				m_componentQuery.setRootRequest(new ComponentRequest(defaultName, null, null));
			}
			else
			{
				String systemId = file.toString();
				stream = new FileInputStream(file);
				IParser<ComponentQuery> parser = CorePlugin.getDefault().getParserFactory().getComponentQueryParser(true);
				m_componentQuery.initFrom(parser.parse(systemId, stream));
			}
			m_needsRefresh = true;
			if(m_componentRequestGroup != null)
				refreshQuery();
			setInputWithNotify(input);
			setPartName(input.getName());
		}
		catch(SAXException e)
		{
			throw new PartInitException(BuckminsterException.wrap(e).getMessage());
		}
		catch(FileNotFoundException e)
		{
			throw new PartInitException(e.getMessage());
		}
		finally
		{
			IOUtils.close(stream);
		}
	}

	@Override
	public boolean isDirty()
	{
		return m_hasChanges;
	}

	@Override
	public boolean isSaveAsAllowed()
	{
		return true;
	}

	@Override
	public void setFocus()
	{
		if(m_needsRefresh)
			refreshQuery();
	}

	private void cancelNode()
	{
		m_nodeEditMode = false;
		enableDisableButtonGroup();
		refreshNodeFields();
	}

	private boolean commitChangesToQuery()
	{
		if(m_nodeEditMode)
		{
			if(!MessageDialog.openConfirm(getSite().getShell(), null,
					"Do you want to discard the current node edit?"))
				return false;
			cancelNode();
		}

		try
		{
			String tmp = UiUtils.trimmedValue(m_requestURL);
			m_componentQuery.setResourceMapURL(URLUtils.normalizeToURL(tmp));
			
			tmp = UiUtils.trimmedValue(m_propertyURL);
			m_componentQuery.setPropertiesURL(URLUtils.normalizeToURL(tmp));
		}
		catch(MalformedURLException e)
		{
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}

		ComponentRequest[] requestRet = new ComponentRequest[1];
		String error = m_componentRequestGroup.commitChanges(requestRet);
		if(error == null)
			m_componentQuery.setRootRequest(requestRet[0]);
		else
		{
			MessageDialog.openError(getSite().getShell(), null, error);
			return false;
		}
		return true;
	}

	private void createActionButtons(Composite parent)
	{
		Composite buttonBox = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(5, false);
		layout.marginHeight = layout.marginWidth = 0;
		buttonBox.setLayout(layout);
		buttonBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		UiUtils.createCheckButton(buttonBox, "Continue on error", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				m_continueOnError = ((Button)e.getSource()).getSelection();
			}
		});

		m_resolveButton = UiUtils.createPushButton(buttonBox, "Resolve to Wizard", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				loadComponent(false);
			}
		});
		m_resolveButton.setLayoutData(new GridData(SWT.END, SWT.FILL, false, false));

		m_materializeButton = UiUtils.createPushButton(buttonBox, "Resolve and Materialize", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				loadComponent(true);
			}
		});
		m_materializeButton.setLayoutData(new GridData(SWT.END, SWT.FILL, false, false));

		m_externalSaveAsButton = UiUtils.createPushButton(buttonBox, "External Save As...", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				doExternalSaveAs();
			}
		});
		m_externalSaveAsButton.setLayoutData(new GridData(SWT.END, SWT.FILL, false, false));
	}

	private void createButtonBox(Composite parent)
	{
		Composite buttonBox = new Composite(parent, SWT.NULL);
		buttonBox.setLayoutData(new GridData(SWT.END, SWT.BEGINNING, false, false));
		RowLayout buttonBoxLayout = new RowLayout(SWT.VERTICAL);
		buttonBoxLayout.marginWidth = 0;
		buttonBoxLayout.fill = true;
		buttonBox.setLayout(buttonBoxLayout);

		m_newOrSaveButton = UiUtils.createPushButton(buttonBox, "New", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				newOrSaveNode();
			}
		});

		m_editOrCancelButton = UiUtils.createPushButton(buttonBox, "Edit", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				editOrCancelNode();
			}
		});

		m_removeButton = UiUtils.createPushButton(buttonBox, "Remove", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				removeNode();
			}
		});

		m_moveUpButton = UiUtils.createPushButton(buttonBox, "Move up", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				swapAndReselect(0, -1);
			}
		});

		m_moveDownButton = UiUtils.createPushButton(buttonBox, "Move down", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				swapAndReselect(1, 0);
			}
		});
	}

	private void createGlobalGroup(Composite parent)
	{
		Composite global = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		layout.marginHeight = layout.marginWidth = 0;
		global.setLayout(layout);
		global.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		m_componentRequestGroup = new EditorComponentRequestGroup(global, SWT.NONE);
		m_componentRequestGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

		m_propertyURL = UiUtils.createLabeledText(global, "Properties:", SWT.NONE, new TriggerChangedRequestListener());
		Button browseButton = new Button(global, SWT.PUSH);
		browseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		browseButton.setText("Browse...");
		browseButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				FileDialog dlg = new FileDialog(getSite().getShell());
				dlg.setFilterExtensions(new String[] { "*.properties" });
				String name = dlg.open();
				if(name == null)
					return;
				try
				{
					m_propertyURL.setText(TextUtils.notNullString(new URL(name)));
				}
				catch(MalformedURLException e)
				{
					try
					{
						m_propertyURL.setText(TextUtils.notNullString(new File(name).toURI().toURL()));
					}
					catch(MalformedURLException e1)
					{
					}
				}
			}
		});

		m_requestURL = UiUtils.createLabeledText(global, "Resource map:", SWT.NONE, new TriggerChangedRequestListener());
		browseButton = new Button(global, SWT.PUSH);
		browseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		browseButton.setText("Browse...");
		browseButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				FileDialog dlg = new FileDialog(getSite().getShell());
				dlg.setFilterExtensions(new String[] { "*.rmap" });
				String name = dlg.open();
				if(name == null)
					return;
				try
				{
					m_requestURL.setText(TextUtils.notNullString(new URL(name)));
				}
				catch(MalformedURLException e)
				{
					try
					{
						m_requestURL.setText(TextUtils.notNullString(new File(name).toURI().toURL()));
					}
					catch(MalformedURLException e1)
					{
					}
				}
			}
		});
	}

	private void createNodeFields(Composite parent)
	{
		Composite nameGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		layout.marginHeight = layout.marginWidth = 0;
		nameGroup.setLayout(layout);
		nameGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		m_namePattern = UiUtils.createLabeledText(nameGroup, "Name pattern:", SWT.NONE, null);
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd.widthHint = 200;
		m_namePattern.setLayoutData(gd);

		m_category = new LabeledCombo(nameGroup, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd.widthHint = 50;
		m_category.setLayoutData(gd);
		m_category.setLabel("Matched category:");
		m_category.setItems(ComponentCategory.getCategoryNames(true));

		m_skipComponent = UiUtils.createCheckButton(nameGroup, "Skip Component", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				enableDisableSkipSensitive();
			}
		});
		m_allowCircular = UiUtils.createCheckButton(nameGroup, "Allow Circular Dependency", null);
		GridData td = new GridData(SWT.FILL, SWT.FILL, true, false);
		td.horizontalSpan = 2;
		m_allowCircular.setLayoutData(td);

		Group targetGroup = new Group(nameGroup, SWT.NONE);
		targetGroup.setText("Attribute qualification");
		layout = new GridLayout(5, false);
		layout.marginHeight = 0;
		layout.marginWidth = 3;
		targetGroup.setLayout(layout);
		td = new GridData(SWT.FILL, SWT.FILL, true, false);
		td.horizontalSpan = 3;
		targetGroup.setLayoutData(td);
		m_wantedAttributes = UiUtils.createLabeledText(targetGroup, "Attributes:", SWT.NONE, null);
		m_prune = UiUtils.createCheckButton(targetGroup, "Prune according to Attributes", null);

		Group replaceGroup = new Group(nameGroup, SWT.NONE);
		replaceGroup.setText("Project Name Mapping");
		layout = new GridLayout(4, false);
		layout.marginHeight = 0;
		layout.marginWidth = 3;
		replaceGroup.setLayout(layout);
		td = new GridData(SWT.FILL, SWT.FILL, true, false);
		td.horizontalSpan = 3;
		replaceGroup.setLayoutData(td);

		m_replaceFrom = UiUtils.createLabeledText(replaceGroup, "Source pattern:", SWT.NONE, null);
		m_replaceTo = UiUtils.createLabeledText(replaceGroup, "Replacement:", SWT.NONE, null);

		Group levelGroup = new Group(nameGroup, SWT.NONE);
		levelGroup.setText("Special Requirements");
		layout = new GridLayout(3, false);
		layout.marginHeight = 0;
		layout.marginWidth = 3;
		levelGroup.setLayout(layout);
		td = new GridData(SWT.FILL, SWT.FILL, true, false);
		td.horizontalSpan = 5;
		levelGroup.setLayoutData(td);

		m_mutableLevel = UiUtils.createEnumCombo(levelGroup, "Mutable level", MutableLevel.values(), null);
		m_sourceLevel = UiUtils.createEnumCombo(levelGroup, "Source level", SourceLevel.values(), null);

		Group useGroup = new Group(parent, SWT.NONE);
		useGroup.setText("Use Known/Unknown");
		layout = new GridLayout(5, false);
		layout.marginHeight = 0;
		layout.marginWidth = 3;
		useGroup.setLayout(layout);
		useGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		m_known = new Label(useGroup, SWT.NONE);
		m_known.setText("Known:");
		m_useInstalled = UiUtils.createCheckButton(useGroup, "Plugins and Features", null);
		m_useMaterialization = UiUtils.createCheckButton(useGroup, "Materializations", null);
		m_useProject = UiUtils.createCheckButton(useGroup, "Projects", null);
		m_whenNotEmpty = UiUtils.createEnumCombo(useGroup, "Unknown:", NotEmptyAction.values(), null);
		td = new GridData(SWT.FILL, SWT.FILL, true, true);
		td.horizontalIndent = 10;
		m_whenNotEmpty.setLayoutData(td);

		Group overrideGroup = new Group(parent, SWT.NONE);
		overrideGroup.setText("Override");
		layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 3;
		overrideGroup.setLayout(layout);
		overrideGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		m_enableOverride = UiUtils.createCheckButton(overrideGroup, "Override version", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				boolean selected = ((Button)e.getSource()).getSelection();
				m_versionOverride.setEnabled(selected);
			}
		});

		m_versionOverride = new VersionDesignator(overrideGroup, SWT.NONE);
		m_versionOverride.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		m_nodeEditMode = false;

		Group addOnGroup = new Group(parent, SWT.NONE);
		addOnGroup.setText("Overlay folder (for prototyping)");
		layout = new GridLayout(3, false);
		layout.marginHeight = 0;
		layout.marginWidth = 3;
		addOnGroup.setLayout(layout);
		addOnGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		m_overlayFolder = UiUtils.createLabeledText(addOnGroup, "Folder:", SWT.NONE, null);
		m_overlayBrowseButton = new Button(addOnGroup, SWT.PUSH);
		m_overlayBrowseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		m_overlayBrowseButton.setText("Browse...");
		m_overlayBrowseButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				DirectoryDialog dlg = new DirectoryDialog(getSite().getShell());
				m_overlayFolder.setText(TextUtils.notNullString(dlg.open()));
			}
		});

	}

	private void createNodeTableGroup(Composite parent)
	{
		Composite componentTableGroup = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginHeight = gl.marginWidth = 0;
		componentTableGroup.setLayout(gl);
		componentTableGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Table table = new Table(componentTableGroup, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);

		table.setHeaderVisible(false);

		String[] columnNames = new String[] { "Component Name Pattern", "Matching Category" };
		int[] columnWeights = new int[] { 15, 5 };

		table.setHeaderVisible(true);
		DynamicTableLayout layout = new DynamicTableLayout(50);
		for(int idx = 0; idx < columnNames.length; idx++)
		{
			TableColumn tableColumn = new TableColumn(table, SWT.LEFT, idx);
			tableColumn.setText(columnNames[idx]);
			layout.addColumnData(new ColumnWeightData(columnWeights[idx], true));
		}
		table.setLayout(layout);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		m_nodeTable = new TableViewer(table);
		m_nodeTable.setLabelProvider(new AdvisorNodeLabelProvider());
		m_nodeTable.setContentProvider(new ArrayContentProvider());
		m_nodeTable.addSelectionChangedListener(new ISelectionChangedListener()
		{
			public void selectionChanged(SelectionChangedEvent event)
			{
				nodeSelectionEvent();
			}
		});
	}

	private void editNode()
	{
		m_nodeEditMode = true;
		enableDisableButtonGroup();
		setDirty(true);
	}

	private void editOrCancelNode()
	{
		if(m_nodeEditMode)
			cancelNode();
		else
			editNode();
	}

	private void enableDisableButtonGroup()
	{
		if(m_nodeEditMode)
		{
			// A node is being edited
			//
			m_newOrSaveButton.setText("Save");
			m_editOrCancelButton.setText("Cancel");
			m_editOrCancelButton.setEnabled(true);
			m_removeButton.setEnabled(false);
			m_moveUpButton.setEnabled(false);
			m_moveDownButton.setEnabled(false);
		}
		else
		{
			Table table = m_nodeTable.getTable();
			int top = table.getItemCount();
			int idx = table.getSelectionIndex();
			m_newOrSaveButton.setText("New");
			m_editOrCancelButton.setText("Edit");
			m_editOrCancelButton.setEnabled(idx >= 0);
			m_removeButton.setEnabled(idx >= 0);
			m_moveUpButton.setEnabled(idx > 0);
			m_moveDownButton.setEnabled(idx >= 0 && idx < top - 1);
		}
		m_nodeTable.getTable().setEnabled(!m_nodeEditMode);

		m_namePattern.setEnabled(m_nodeEditMode);
		m_category.setEnabled(m_nodeEditMode);
		m_skipComponent.setEnabled(m_nodeEditMode);
		enableDisableSkipSensitive();
	}

	private void enableDisableSkipSensitive()
	{
		boolean enableRest = m_nodeEditMode && !m_skipComponent.getSelection();

		m_allowCircular.setEnabled(enableRest);
		m_overlayFolder.setEnabled(enableRest);
		m_overlayBrowseButton.setEnabled(enableRest);
		m_wantedAttributes.setEnabled(enableRest);
		m_prune.setEnabled(enableRest);

		m_replaceFrom.setEnabled(enableRest);
		m_replaceTo.setEnabled(enableRest);

		m_mutableLevel.setEnabled(enableRest);
		m_sourceLevel.setEnabled(enableRest);
		m_whenNotEmpty.setEnabled(enableRest);

		m_known.setEnabled(enableRest);
		m_useInstalled.setEnabled(enableRest);
		m_useMaterialization.setEnabled(enableRest);
		m_useProject.setEnabled(enableRest);

		m_enableOverride.setEnabled(enableRest);
		m_versionOverride.setEnabled(enableRest && m_enableOverride.getSelection());
	}

	private AdvisorNodeBuilder getSelectedNode()
	{
		int idx = m_nodeTable.getTable().getSelectionIndex();
		return idx >= 0 ? (AdvisorNodeBuilder)m_nodeTable.getElementAt(idx) : null;
	}

	class ResolveJob extends Job
	{
		private final IResolver m_resolver;

		private final boolean m_materialize;

		ResolveJob(ComponentQuery query, boolean materialize) throws CoreException
		{
			super("Resolving query");
			m_resolver = new MainResolver(new RMContext(query));
			m_resolver.getContext().setContinueOnError(m_continueOnError);
			m_materialize = materialize;
			setUser(true);
			setPriority(LONG);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor)
		{
			final IWorkbenchPartSite site = getSite();
			RMContext ctx = m_resolver.getContext();
			ComponentQuery query = ctx.getComponentQuery();

			try
			{
				Display display = site.getShell().getDisplay();
				ComponentRequest rootRequest = query.getRootRequest();
				if(!m_materialize)
				{
					final BillOfMaterials bom = m_resolver.resolve(rootRequest, monitor);
					IStatus status = ctx.getStatus();
					if(status.getSeverity() == IStatus.ERROR && !ctx.isContinueOnError())
						return status;
					CorePlugin.logWarningsAndErrors(status);

					display.asyncExec(new Runnable()
					{
						public void run()
						{
							QueryWizard.openWizard(site, m_resolver.getContext(), bom);
						}
					});
					return Status.OK_STATUS;
				}

				monitor.beginTask(null, 2000);
				monitor.subTask("Resolving and materializing " + rootRequest.getName());
				try
				{
					BillOfMaterials bom = m_resolver.resolve(rootRequest, MonitorUtils.subMonitor(monitor, 1000));
					IStatus status = m_resolver.getContext().getStatus();

					if(status.getSeverity() == IStatus.ERROR && !ctx.isContinueOnError())
						return status;
					CorePlugin.logWarningsAndErrors(status);

					if(bom.isFullyResolved() || ctx.isContinueOnError())
					{
						setName("Materializing");
						MaterializerJob.run(bom, ctx, null, MonitorUtils.subMonitor(monitor, 1000));
						status = ctx.getStatus();
						if(status.getSeverity() == IStatus.ERROR && !ctx.isContinueOnError())
							return status;
						CorePlugin.logWarningsAndErrors(status);
					}
					return status;
				}
				finally
				{
					monitor.done();
				}
			}
			catch(Exception e)
			{
				return BuckminsterException.wrap(e).getStatus();
			}
		}
	}

	private void loadComponent(boolean materialize)
	{
		if(!commitChangesToQuery())
			return;

		try
		{
			ResolveJob resolveJob = new ResolveJob(m_componentQuery.createComponentQuery(), materialize);
			resolveJob.schedule();
		}
		catch(CoreException e)
		{
			ErrorDialog.openError(getSite().getShell(), null, null, e.getStatus());
		}
	}

	private void newNode()
	{
		m_nodeTable.getTable().deselectAll();
		refreshNodeFields();
		editNode();
	}

	private void newOrSaveNode()
	{
		if(m_nodeEditMode)
			saveNode();
		else
			newNode();
	}

	private void nodeSelectionEvent()
	{
		enableDisableButtonGroup();
		refreshNodeFields();
	}

	private void refreshList()
	{
		m_nodeTable.setInput(m_componentQuery.getAdvisoryNodeList());
	}

	private void refreshNodeFields()
	{
		AdvisorNodeBuilder node = getSelectedNode();
		if(node == null)
			//
			// Use an empty node as template to get the defaults right.
			//
			node = new AdvisorNodeBuilder();

		m_allowCircular.setSelection(node.allowCircularDependency());
		m_namePattern.setText(TextUtils.notNullString(node.getNamePattern()));
		m_category.select(m_category.indexOf(TextUtils.notNullString(node.getCategory())));
		m_overlayFolder.setText(TextUtils.notNullString(node.getOverlayFolder()));
		m_wantedAttributes.setText(TextUtils.notNullString(TextUtils.toCommaSeparatedList(node.getAttributes())));
		m_prune.setSelection(node.isPrune());
		m_replaceFrom.setText(TextUtils.notNullString(node.getReplaceFrom()));
		m_replaceTo.setText(TextUtils.notNullString(node.getReplaceTo()));
		m_mutableLevel.select(m_mutableLevel.indexOf(node.getMutableLevel().toString()));
		m_sourceLevel.select(m_sourceLevel.indexOf(node.getSourceLevel().toString()));
		m_whenNotEmpty.select(m_whenNotEmpty.indexOf(node.getWhenNotEmpty().toString()));
		m_skipComponent.setSelection(node.skipComponent());
		m_useInstalled.setSelection(node.useInstalled());
		m_useMaterialization.setSelection(node.useMaterialization());
		m_useProject.setSelection(node.useProject());

		IVersionDesignator vs = node.getVersionOverride();
		boolean enableOverride = (vs != null);
		m_enableOverride.setSelection(enableOverride);
		m_versionOverride.setEnabled(enableOverride);
		m_versionOverride.refreshValues(vs);
	}

	private void refreshQuery()
	{
		setDirty(false);
		m_mute = true;
		try
		{
			m_componentRequestGroup.refreshValues(m_componentQuery.getRootRequest());
			m_propertyURL.setText(TextUtils.notNullString(m_componentQuery.getPropertiesURL()));
			m_requestURL.setText(TextUtils.notNullString(m_componentQuery.getResourceMapURL()));
			refreshList();
			m_needsRefresh = false;
			nodeSelectionEvent();
		}
		finally
		{
			m_mute = false;
		}
	}

	private void removeNode()
	{
		AdvisorNodeBuilder node = getSelectedNode();
		if(node != null)
		{
			m_componentQuery.removeAdvisorNode(node);
			setDirty(true);
			refreshList();
		}
	}

	private boolean saveNode()
	{
		AdvisorNodeBuilder node = getSelectedNode();
		boolean isNewNode = false;
		if(node == null)
		{
			node = new AdvisorNodeBuilder();
			isNewNode = true;
		}

		boolean refreshListNeeded = false;
		String patternStr = UiUtils.trimmedValue(m_namePattern);
		String category = m_category.getItem(m_category.getSelectionIndex());
		if(category.length() == 0)
			category = null;

		if(patternStr == null)
		{
			MessageDialog.openError(getSite().getShell(), null, "The name pattern cannot be empty");
			return false;
		}
		Pattern pattern;
		try
		{
			pattern = Pattern.compile(patternStr);
		}
		catch(PatternSyntaxException e)
		{
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}

		String currentCategory = node.getCategory();
		Pattern currentPattern = node.getNamePattern();
		if(currentPattern == null || !currentPattern.toString().equals(patternStr)
				|| !Trivial.equalsAllowNull(currentCategory, category))
		{
			// Pattern changed. Verify that it's not a duplicate
			//
			AdvisorNodeBuilder patternEqual = m_componentQuery.getNodeByPattern(patternStr, category);
			if(patternEqual != null)
			{
				if(!MessageDialog.openQuestion(getSite().getShell(), null,
						"Overwrite existing node with same pattern"))
					return false;
				m_componentQuery.removeAdvisorNode(patternEqual);
			}
			refreshListNeeded = true;
		}
		node.setNamePattern(pattern);
		node.setCategory(category);
		node.setAllowCircularDependency(m_allowCircular.getSelection());

		boolean override = m_enableOverride.getSelection();
		IVersionDesignator versionOverride = null;
		if(override)
			versionOverride = m_versionOverride.getVersionDesignator();

		pattern = null;
		String tmp = UiUtils.trimmedValue(m_replaceFrom);
		try
		{
			if(tmp != null)
				pattern = Pattern.compile(patternStr);
			tmp = UiUtils.trimmedValue(m_overlayFolder);
			node.setOverlayFolder(tmp == null ? null : URLUtils.normalizeToURL(tmp));
		}
		catch(Exception e)
		{
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}

		node.setSkipComponent(m_skipComponent.getSelection());

		tmp = UiUtils.trimmedValue(m_wantedAttributes);
		if(tmp != null)
			for(String attribute : tmp.split(","))
				node.addAttribute(attribute);
		node.setPrune(m_prune.getSelection());

		node.setReplaceFrom(pattern);
		node.setReplaceTo(UiUtils.trimmedValue(m_replaceTo));

		int idx = m_mutableLevel.getSelectionIndex();
		node.setMutableLevel(idx >= 0 ? MutableLevel.values()[idx] : null);

		idx = m_sourceLevel.getSelectionIndex();
		node.setSourceLevel(idx >= 0 ? SourceLevel.values()[idx] : null);

		idx = m_whenNotEmpty.getSelectionIndex();
		node.setWhenNotEmpty(idx >= 0 ? NotEmptyAction.values()[idx] : null);

		node.setUseInstalled(m_useInstalled.getSelection());
		node.setUseMaterialization(m_useMaterialization.getSelection());
		node.setUseProject(m_useProject.getSelection());

		node.setVersionOverride(versionOverride);
		if(isNewNode)
		{
			// This was an add operation
			//
			m_componentQuery.addAdvisorNode(node);
			refreshListNeeded = true;
		}
		if(refreshListNeeded)
			refreshList();

		setDirty(true);
		m_nodeEditMode = false;
		enableDisableButtonGroup();
		return true;
	}

	private void saveToPath(IPath path)
	{
		try
		{
			SaveRunnable sr = new SaveRunnable(m_componentQuery.createComponentQuery(), path);
			getSite().getWorkbenchWindow().run(true, true, sr);
			setInputWithNotify(sr.getSavedInput());
			setDirty(false);
			setPartName(path.lastSegment());
			firePropertyChange(IWorkbenchPart.PROP_TITLE);
		}
		catch(InvocationTargetException e)
		{
			CoreException t = BuckminsterException.wrap(e);
			String msg = "Unable to save file " + path;
			CorePlugin.getLogger().error(msg, t);
			ErrorDialog.openError(getSite().getShell(), null, msg, t.getStatus());
		}
		catch(InterruptedException e)
		{
		}
	}

	private void setDirty(boolean flag)
	{
		if(m_mute || m_hasChanges == flag)
			return;
		m_hasChanges = flag;
		m_externalSaveAsButton.setEnabled(flag);
		firePropertyChange(PROP_DIRTY);
	}

	private void swapAndReselect(int idxOffset, int selectionOffset)
	{
		Table table = m_nodeTable.getTable();
		int idx = table.getSelectionIndex() + idxOffset;
		if(idx <= 0)
			return;

		List<AdvisorNodeBuilder> nl = m_componentQuery.getAdvisoryNodeList();
		if(idx >= nl.size())
			return;

		nl.set(idx - 1, nl.set(idx, nl.get(idx - 1)));
		refreshList();
		table.select(idx + selectionOffset);
		enableDisableButtonGroup();
		setDirty(true);
	}
}

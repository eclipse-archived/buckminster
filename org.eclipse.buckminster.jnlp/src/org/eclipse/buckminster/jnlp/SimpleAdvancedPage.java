/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.helpers.SmartArrayList;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * @author Karel Brezina
 * 
 */
public class SimpleAdvancedPage extends InstallWizardPage
{
	class MaterializationNodeHandler
	{
		private MaterializationNodeBuilder m_node;

		private CSpec m_cspec;

		private List<TreeItem> m_cloneItems = new ArrayList<TreeItem>();
		
		private TreeItem m_lastClone;
		
		private boolean m_canChangeExclude = true;

		public MaterializationNodeHandler(List<MaterializationNodeBuilder> nodes, MaterializationNodeBuilder node,
				CSpec cspec, boolean canChangeExclude)
		{
			m_node = node;
			nodes.add(node);
			m_cspec = cspec;
			m_canChangeExclude = canChangeExclude;
		}

		public void setExclude(boolean exclude)
		{
			if(m_canChangeExclude)
			{
				m_node.setExclude(exclude);
			}

			for(TreeItem ti : m_cloneItems)
			{
				ti.setChecked(!exclude);
			}
		}

		public boolean setExcludeAccordingToClonesCheckConflicts()
		{
			boolean totalAnd = true;
			boolean totalOr = true;
			boolean firstRun = true;
			
			for(TreeItem ti : m_cloneItems)
			{
				boolean checked = ti.getChecked();
				
				if(firstRun)
				{
					totalAnd = checked;
					totalOr = checked;
					firstRun = false;
				}
				else
				{					
					totalAnd = totalAnd && checked;
					totalOr = totalOr || checked;
				}
			}
			
			// all clones have the same check status
			if(totalAnd == totalOr)
			{
				setExclude(!totalAnd);
				return false;
			}

			setExclude(false);
			return true;
		}

		public TreeItem createTreeItemClone(final Tree parentTree, final int style)
		{
			m_lastClone = null;
			
			Display.getDefault().syncExec(new Runnable()
			{
				public void run()
				{
					m_lastClone = new TreeItem(parentTree, style);
					setupTreeItem(m_lastClone);
					m_cloneItems.add(m_lastClone);					
				}
			});
			
			return m_lastClone;
		}

		public TreeItem createTreeItemClone(final TreeItem parentTreeItem, final int style)
		{
			m_lastClone = null;
			
			Display.getDefault().syncExec(new Runnable()
			{
				public void run()
				{
					m_lastClone = new TreeItem(parentTreeItem, style);
					setupTreeItem(m_lastClone);
					m_cloneItems.add(m_lastClone);					
				}
			});
			
			return m_lastClone;
		}

		public List<TreeItem> getTreeItemClones()
		{
			return m_cloneItems;
		}

		private void setupTreeItem(TreeItem treeItem)
		{
			treeItem.setText(getComponentShortDescription());
			treeItem.setData(this);
			treeItem.setChecked(!m_node.isExclude() || !m_canChangeExclude);
		}

		public String getComponentShortDescription()
		{
			return m_cspec.getShortDesc() == null
					? (m_cspec.getComponentIdentifier().getName() + (m_cspec.getComponentIdentifier().getComponentTypeID() == null
							? ""
							: "/" + MaterializationUtils.getHumanReadableComponentType(m_cspec.getComponentIdentifier().getComponentTypeID())))
					: m_cspec.getShortDesc();
		}

		public String getComponentDescription()
		{
			SmartArrayList<String> smartList = new SmartArrayList<String>();
			
			if(m_cspec.getShortDesc() != null)
			{
				smartList.add("Description: " + m_cspec.getShortDesc());
			}
			if(m_cspec.getComponentIdentifier().getName() != null)
			{
				smartList.add("Name: " + m_cspec.getComponentIdentifier().getName());
			}
			if(m_cspec.getComponentIdentifier().getComponentTypeID() != null)
			{
				smartList.add("Meta-Data Extractor: " + MaterializationUtils.getHumanReadableComponentType(m_cspec.getComponentIdentifier().getComponentTypeID()));
			}
			if(m_cspec.getVersion() != null)
			{
				smartList.add("Version: " + m_cspec.getVersion());
			}
			if(m_node.getInstallLocation() != null)
			{
				smartList.add("Destination Address: " + m_node.getInstallLocation().removeTrailingSeparator().toOSString());
			}
			if(m_node.getConflictResolution() != null)
			{
				smartList.add("Conflict Resolution: " + m_node.getConflictResolution());
			}
			
			return smartList.toString("\n");
		}
	}

	private DestinationForm m_destinationForm;
	
	private Tree m_tree;
	
	private Button m_publishButton;
	
	private boolean m_treeInitialized = false;

	private Map<ComponentName, MaterializationNodeHandler> m_componentMap = new HashMap<ComponentName, MaterializationNodeHandler>();

	protected SimpleAdvancedPage()
	{
		super(MaterializationConstants.STEP_ADVANCED_SETTINGS, "Advanced", "Select components for materialization.", null);
	}

	public void createControl(Composite parent)
	{
		MaterializationSpecBuilder builder = getMaterializationSpecBuilder();

		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(3, false));
		pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_destinationForm = new DestinationForm(builder, true, true);
		m_destinationForm.createControl(pageComposite);

		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);

		Group treeGroup = new Group(pageComposite, SWT.NONE);
		treeGroup.setText("Components for Materialization");
		treeGroup.setLayout(new GridLayout());
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 3;
		treeGroup.setLayoutData(data);
		
		m_tree = new Tree(treeGroup, SWT.CHECK | SWT.BORDER);
		data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 2;
		m_tree.setLayoutData(data);

		m_tree.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				TreeItem item = (TreeItem)e.item;
				MaterializationNodeHandler handler = (MaterializationNodeHandler)item.getData();
				boolean checked = item.getChecked();
				handler.setExclude(!checked);				
				
				for(TreeItem itemClone : handler.getTreeItemClones())
					setCheckedSubtree(itemClone, checked);

				for(TreeItem itemClone : handler.getTreeItemClones())
				{
					checkAndRepairSubtreeCloneConflicts(itemClone);
						//	warning - clones repaired
				}
			}

			private void setCheckedSubtree(TreeItem item, boolean checked)
			{
				item.setChecked(checked);
				
				for(TreeItem child : item.getItems())
					setCheckedSubtree(child, checked);				
			}

			private boolean checkAndRepairSubtreeCloneConflicts(TreeItem item)
			{
				boolean conflict = false;
				
				MaterializationNodeHandler handler = (MaterializationNodeHandler)item.getData();

				conflict = conflict || handler.setExcludeAccordingToClonesCheckConflicts();
				
				for(TreeItem child : item.getItems())
				{
					boolean newConflict = checkAndRepairSubtreeCloneConflicts(child);
					conflict = conflict || newConflict;
				}
				
				return conflict;
			}
		});

		m_tree.addMouseTrackListener(new MouseTrackAdapter()
		{

			@Override
			public void mouseExit(MouseEvent e)
			{
				m_tree.setToolTipText(null);
			}

			@Override
			public void mouseHover(MouseEvent e)
			{
				TreeItem item = m_tree.getItem(new Point(e.x, e.y));
				String toolTipText = null;

				if(item != null)
				{
					toolTipText = ((MaterializationNodeHandler)item.getData()).getComponentDescription();
				}

				m_tree.setToolTipText(toolTipText);
			}
		});
		
		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);

		Composite publishComposite = new Composite(pageComposite, SWT.NONE);
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		publishComposite.setLayout(gridLayout);
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gridData.horizontalSpan = 3;
		publishComposite.setLayoutData(gridData);
		
		m_publishButton = new Button(publishComposite, SWT.PUSH);
		m_publishButton.setText("Publish as a Distro");
		gridData = new GridData(SWT.END, SWT.CENTER, true, false);
		m_publishButton.setLayoutData(gridData);
		m_publishButton.addSelectionListener(new SelectionAdapter(){

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				MaterializationUtils.startPublishingWizard(getInstallWizard(), getShell());
			}});
		
		setControl(pageComposite);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		m_destinationForm.setup();
	}
	
	/*
	 * Initializes tree and creates new MSpec nodes. This has to be called even if the Advanced Page is not needed,
	 * because it excludes cssite components from materialization.
	 */
	void initializeMSpecTree()
	{
		if(!m_treeInitialized)
		{
			initializeTree();

			for(TreeItem item : m_tree.getItems())
			{
				item.setExpanded(true);
			}
			
			m_treeInitialized = true;
		}
	}
	
	private void initializeTree()
	{
		BillOfMaterials bom = getInstallWizard().getBOM();
		
		getInstallWizard().getNewMaterializationNodeBuilders().clear();
		m_componentMap.clear();

		try
		{
			MaterializationNodeHandler handler = getHandler(bom);

			if(handler != null)
			{
				final TreeItem treeItem = handler.createTreeItemClone(m_tree, SWT.NONE);
				addChildrenItems(treeItem, bom);
			}

		}
		catch(CoreException e)
		{
			throw new JNLPException("Error while reading artifact specification -\n\tbill of materials can not be read",
					ERROR_CODE_BOM_IO_EXCEPTION, e);
		}
	}

	private void addChildrenItems(TreeItem parentTI, DepNode parentDN) throws CoreException
	{
		for(DepNode depNode : getSortedChildren(parentDN))
		{
			MaterializationNodeHandler handler = getHandler(depNode);

			if(handler != null)
			{
				TreeItem treeItem = handler.createTreeItemClone(parentTI, SWT.NONE);
				addChildrenItems(treeItem, depNode);
			}
		}
	}

	private MaterializationNodeHandler getHandler(DepNode depNode) throws CoreException
	{
		Resolution resolution = depNode.getResolution();

		if(resolution == null)
		{
			return null;
		}

		MaterializationNodeHandler handler = m_componentMap.get(resolution.getComponentIdentifier());

		if(handler == null)
		{
			CSpec cspec = resolution.getCSpec();

			if(cspec == null)
			{
				return null;
			}

			String componentName = cspec.getName();
			String componentType = cspec.getComponentTypeID();

			MaterializationNodeBuilder nodeBuilder = new MaterializationNodeBuilder();

			for(MaterializationNodeBuilder origNodeBuilder : getInstallWizard()
					.getOriginalMaterializationNodeBuilders())
			{
				if(origNodeBuilder.getNamePattern().matcher(componentName).matches()
						&& (origNodeBuilder.getComponentTypeID() == null || origNodeBuilder.getComponentTypeID().equals(
								componentType)))
				{
					nodeBuilder.initFrom(origNodeBuilder.createMaterializationNode());
					break;
				}
			}

			nodeBuilder.setNamePattern(Pattern.compile("^\\Q" + componentName + "\\E$"));
			nodeBuilder.setComponentTypeID(componentType);

			boolean canChangeExclude = true;
			
			if(hasCssiteReader(depNode))
			{
				nodeBuilder.setExclude(true);
				canChangeExclude = false;
			}
			
			handler = new MaterializationNodeHandler(getInstallWizard().getNewMaterializationNodeBuilders(),
					nodeBuilder, cspec, canChangeExclude);
			m_componentMap.put(resolution.getComponentIdentifier(), handler);
		}

		return handler;
	}
	
	// Temporary solution - cannot handle CSsite reader component types
	private boolean hasCssiteReader(DepNode depNode) throws CoreException
	{
		Resolution resolution = depNode.getResolution();
		
		if(resolution != null)
		{
			if("cssite".equals(resolution.getProvider().getReaderTypeId()))
			{
				return true;
			}
		}
		
		return false;
	}

	private Collection<DepNode> getSortedChildren(DepNode parent) throws CoreException
	{
		Collection<DepNode> children = parent.getChildren();
		if(children.size() > 1)
		{
			Map<String, DepNode> sortedMap = new TreeMap<String, DepNode>();
			for(DepNode child : children)
			{
				Resolution resolution = child.getResolution();
				
				if (resolution != null)
					sortedMap.put(resolution.getComponentIdentifier().toString(), child);
			}
			children = sortedMap.values();
		}
		return children;
	}

	@Override
	public IWizardPage getNextPage()
	{
		return null;
	}
}

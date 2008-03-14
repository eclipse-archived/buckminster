/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_BOM_IO_EXCEPTION;

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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * @author Karel Brezina
 *
 */
public class MSpecDetailsPanel
{
	class MaterializationNodeHandler
	{
		private MaterializationNodeBuilder m_node;

		private CSpec m_cspec;

		private List<TreeItem> m_cloneItems = new ArrayList<TreeItem>();
		
		private TreeItem m_lastClone;
		
		public MaterializationNodeHandler(List<MaterializationNodeBuilder> nodes, MaterializationNodeBuilder node, CSpec cspec)
		{
			m_node = node;
			nodes.add(node);
			m_cspec = cspec;
		}

		public MaterializationNodeBuilder getNodeBuilder()
		{
			return m_node;
		}

		public boolean isExclude()
		{
			return m_node.isExclude();
		}
		
		public void setExclude(boolean exclude)
		{
			m_node.setExclude(exclude);

			for(TreeItem ti : m_cloneItems)
			{
				ti.setChecked(!exclude);
			}
		}

		public boolean setExcludeAccordingToClonesCheckConflicts()
		{
			boolean totalAnd = true;
			boolean totalOr = false;
			
			for(TreeItem ti : m_cloneItems)
			{
				boolean checked = ti.getChecked();
				
				totalAnd = totalAnd && checked;
				totalOr = totalOr || checked;
				
				if(totalAnd != totalOr)
					break;
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
			treeItem.setChecked(!m_node.isExclude());
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

	private final static String TOOL_TIP_UNPACK = "Unpack component after materialization";
	
	private MaterializationSpecBuilder m_mspec;
	
	private BillOfMaterials m_bom;
	
	private List<MaterializationNodeBuilder> m_originalNodeBuilders;

	private String m_defaultInstallLocation;
	
	private DestinationForm m_destinationForm;
	
	private boolean m_showBrowseButtons;
	
	private Tree m_tree;
	
	private MaterializationNodeBuilder m_selectedNodeBuilder;
	
	private DestinationForm m_detailDestForm;
	
	private Button m_unpackCheckBox;
	
	private Map<ComponentName, MaterializationNodeHandler> m_componentMap = new HashMap<ComponentName, MaterializationNodeHandler>();

	public MSpecDetailsPanel(MaterializationSpecBuilder mspec, String defaultInstallLocation, boolean showBrowseButtons)
	{
		m_mspec = mspec;
		m_defaultInstallLocation = defaultInstallLocation;
		m_showBrowseButtons = showBrowseButtons;
	}
	
	public void update()
	{
		m_destinationForm.update();
	}
	
	public Control createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(3, false));
		pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		m_destinationForm = new DestinationForm(m_mspec, m_defaultInstallLocation, true, false, true, false, m_showBrowseButtons);
		m_destinationForm.createControl(pageComposite);

		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);

		Group treeGroup = new Group(pageComposite, SWT.NONE);
		treeGroup.setText("Components for Materialization");
		treeGroup.setLayout(new GridLayout(2, true));
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 3;
		treeGroup.setLayoutData(data);
		
		m_tree = new Tree(treeGroup, SWT.CHECK | SWT.BORDER);
		data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 1;
		m_tree.setLayoutData(data);

	    m_tree.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				TreeItem item = (TreeItem)e.item;
				MaterializationNodeHandler handler = (MaterializationNodeHandler)item.getData();

				if(e.detail == SWT.NONE)
				{
					m_selectedNodeBuilder = handler.getNodeBuilder();
					m_detailDestForm.setBuilder(m_selectedNodeBuilder);
					m_detailDestForm.update();
					m_unpackCheckBox.setSelection(m_selectedNodeBuilder.isUnpack());
					setEnableDetails(!handler.isExclude());
				}
				else if(e.detail == SWT.CHECK)
				{
					boolean checked = item.getChecked();
					handler.setExclude(!checked);				
					
					for(TreeItem itemClone : handler.getTreeItemClones())
						setCheckedSubtree(itemClone, checked);
	
					// the second run is just for one clone - they are identical
					TreeItem itemClone = handler.getTreeItemClones().get(0);

					if(itemClone != null && checkAndRepairSubtreeCloneConflicts(itemClone))
						if(!checked)
							// TODO display warning - some components are used in a different subtree - you can uncheck them manually
							;

					if(m_tree.getSelectionCount() == 1 && m_tree.getSelection()[0] == item)
						setEnableDetails(!handler.isExclude());
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
		
		Composite detailsComposite = new Composite(treeGroup, SWT.NONE);
		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		detailsComposite.setLayout(gridLayout);
		detailsComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label headerLabel = new Label(detailsComposite, SWT.BOLD);
		headerLabel.setText("Override:");
		headerLabel.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));
		GridData gridData = new GridData();
		gridData.horizontalSpan = 3;
		headerLabel.setLayoutData(gridData);
		
		m_detailDestForm = new DestinationForm(null, "", true, true, true, true, m_showBrowseButtons);
		m_detailDestForm.createControl(detailsComposite);
		
		Label label = new Label(detailsComposite, SWT.NONE);
		label.setText("Unpack Component:");
		label.setToolTipText(TOOL_TIP_UNPACK);
		
		m_unpackCheckBox = new Button(detailsComposite, SWT.CHECK);
		m_unpackCheckBox.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if(m_selectedNodeBuilder != null)
					m_selectedNodeBuilder.setUnpack(m_unpackCheckBox.getSelection());
				
			}});
		m_unpackCheckBox.setToolTipText(TOOL_TIP_UNPACK);

		new Label(detailsComposite, SWT.NONE);
		
		setEnableDetails(false);
		
		return pageComposite;
	}

	private void setEnableDetails(boolean enabled)
	{
		m_detailDestForm.setEnabled(enabled);
		m_unpackCheckBox.setEnabled(enabled);
	}
	
	/*
	 * Initializes tree and creates new MSpec nodes. This has to be called even if the Advanced Page is not needed,
	 * because it excludes cssite components from materialization.
	 */
	public void initializeMSpecTree(BillOfMaterials bom)
	{
		m_bom = bom;
		m_originalNodeBuilders = new ArrayList<MaterializationNodeBuilder>();
		m_originalNodeBuilders.addAll(m_mspec.getNodes());
		
		initializeTree();

		for(TreeItem item : m_tree.getItems())
		{
			item.setExpanded(true);
		}
	}
	
	private void initializeTree()
	{
		m_mspec.getNodes().clear();
		m_componentMap.clear();
		
		m_tree.removeAll();

		try
		{
			MaterializationNodeHandler handler = getHandler(m_bom);

			if(handler != null)
			{
				final TreeItem treeItem = handler.createTreeItemClone(m_tree, SWT.NONE);
				addChildrenItems(treeItem, m_bom);
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

			for(MaterializationNodeBuilder origNodeBuilder : m_originalNodeBuilders)
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

			handler = new MaterializationNodeHandler(m_mspec.getNodes(), nodeBuilder, cspec);
			m_componentMap.put(resolution.getComponentIdentifier(), handler);
		}

		return handler;
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


}

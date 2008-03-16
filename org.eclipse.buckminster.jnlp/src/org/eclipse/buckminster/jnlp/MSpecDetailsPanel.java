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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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
	private Map<MaterializationNodeHandler, Map<MaterializationNodeHandler, TreeNode>> m_treeNodeCache =
					new HashMap<MaterializationNodeHandler, Map<MaterializationNodeHandler, TreeNode>>();
	
	class TreeNode
	{
		private MaterializationNodeHandler m_handler;

		private TreeNode m_parent;
		
		private List<TreeNode> m_children = new ArrayList<TreeNode>();
		
		private boolean m_checked;
		
		public TreeNode(MaterializationNodeHandler handler, TreeNode parentTreeNode, boolean checked)
		{
			m_handler = handler;
			m_parent = parentTreeNode;
			m_checked = checked;
			
			if(m_parent != null)
				m_parent.addChild(this);
		}
		
		public TreeNode getParent()
		{
			return m_parent;
		}

		public List<TreeNode> getChildren()
		{
			return m_children;
		}

		public void addChild(TreeNode node)
		{
			if(!m_children.contains(node))
				m_children.add(node);
		}
		
		public boolean isChecked()
		{
			return m_checked;
		}

		public void setChecked(boolean checked)
		{
			m_checked = checked;
		}
		
		public MaterializationNodeHandler getHandler()
		{
			return m_handler;
		}
		
	}
	
	class TreeContentProvider implements ITreeContentProvider
	{

		public Object[] getChildren(Object parentElement)
		{
			TreeNode treeNode = (TreeNode)parentElement;
			return treeNode.getChildren().toArray();
		}

		public Object getParent(Object element)
		{
			TreeNode treeNode = (TreeNode)element;
			return treeNode.getParent();
		}

		public boolean hasChildren(Object element)
		{
			TreeNode treeNode = (TreeNode)element;
			return treeNode.getChildren().size() > 0;
		}

		public Object[] getElements(Object inputElement)
		{
			return getChildren(inputElement);
		}

		public void dispose()
		{
			// nothing
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
		{
			// nothing
		}
	}
	
	class LabelProvider implements ILabelProvider
	{
		List<ILabelProviderListener> m_listeners = new ArrayList<ILabelProviderListener>();

		public Image getImage(Object element)
		{
			return null;
		}

		public String getText(Object element)
		{
			TreeNode treeNode = (TreeNode)element;
			return treeNode.getHandler().getComponentShortDescription();
		}

		public void addListener(ILabelProviderListener listener)
		{
			m_listeners.add(listener);
		}

		public void dispose()
		{
			// nothing
		}

		public boolean isLabelProperty(Object element, String property)
		{
			return false;
		}

		public void removeListener(ILabelProviderListener listener)
		{
			m_listeners.remove(listener);
		}
	}
	
	class MaterializationNodeHandler
	{
		private MaterializationNodeBuilder m_node;

		private CSpec m_cspec;

		private List<TreeNode> m_cloneItems = new ArrayList<TreeNode>();
		
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

			for(TreeNode tn : m_cloneItems)
			{
				tn.setChecked(!exclude);
			}
		}

		public boolean setExcludeAccordingToClonesCheckConflicts()
		{
			boolean totalAnd = true;
			boolean totalOr = false;
			
			for(TreeNode tn : m_cloneItems)
			{
				boolean checked = tn.isChecked();
				
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

		public TreeNode createTreeNodeClone(final TreeNode parentTreeNode)
		{
			TreeNode treeNode = null;
			
			Map<MaterializationNodeHandler, TreeNode> map = m_treeNodeCache.get(this);
			
			if(map != null && parentTreeNode.getHandler() != null)
			{
				treeNode = map.get(parentTreeNode.getHandler());
			}
				
			if(treeNode == null)
			{
				treeNode = new TreeNode(this, parentTreeNode, !m_node.isExclude());
				if(parentTreeNode.getHandler() != null)
				{
					if(map == null)
					{
						map = new HashMap<MaterializationNodeHandler, TreeNode>();
						m_treeNodeCache.put(this, map);
					}
					map.put(parentTreeNode.getHandler(), treeNode);
				}
				
				m_cloneItems.add(treeNode);					
			}
			else
			{
				parentTreeNode.addChild(treeNode);
			}
			
			return treeNode;
		}

		public List<TreeNode> getTreeNodeClones()
		{
			return m_cloneItems;
		}

		public String getComponentShortDescription()
		{
			return (m_cspec.getShortDesc() == null ? m_cspec.getComponentIdentifier().getName() : m_cspec.getShortDesc()) +
					(m_cspec.getComponentIdentifier().getComponentTypeID() == null
							? ""
							: "/" + MaterializationUtils.getHumanReadableComponentType(m_cspec.getComponentIdentifier().getComponentTypeID()));
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
	
	private CheckboxTreeViewer m_treeViewer;
	
	private TreeNode m_treeRoot;
	
	private Set<TreeItem> m_expandedTreeItems = new HashSet<TreeItem>();
	
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
		
		if(m_treeViewer.getInput() == null)
		{
			m_treeViewer.setInput(m_treeRoot);
			m_treeViewer.setExpandedElements(m_treeRoot.getChildren().toArray());
			// add the root TreeItem
			m_expandedTreeItems.add(m_treeViewer.getTree().getTopItem());
			
			setupVisibleCheckboxes();
		}
	}
	
	private void setupVisibleCheckboxes()
	{
		Set<TreeItem> elements = new HashSet<TreeItem>();
		for(TreeItem item : m_expandedTreeItems)
		{
			elements.add(item);
			elements.addAll(Arrays.asList(item.getItems()));
		}

		for(TreeItem item : elements)
		{
			item.setChecked(((TreeNode)item.getData()).isChecked());
		}
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
		
		m_treeViewer = new CheckboxTreeViewer(treeGroup, SWT.BORDER);
		final Tree tree = m_treeViewer.getTree();
		data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 1;
		tree.setLayoutData(data);
		
		m_treeViewer.setContentProvider(new TreeContentProvider());
		m_treeViewer.setLabelProvider(new LabelProvider());
		m_treeViewer.setInput(null);

	    tree.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				TreeNode node = (TreeNode)e.item.getData();
				MaterializationNodeHandler handler = node.getHandler();

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
					boolean checked = !node.isChecked();
					handler.setExclude(!checked);				
					
					Set<TreeNode> visitedNodes = new HashSet<TreeNode>();
					for(TreeNode nodeClone : handler.getTreeNodeClones())
						setCheckedSubtree(nodeClone, checked, visitedNodes);
	
					// the second run is just for one clone - they are identical
					TreeNode nodeClone = handler.getTreeNodeClones().get(0);

					visitedNodes = new HashSet<TreeNode>();
					if(nodeClone != null && checkAndRepairSubtreeCloneConflicts(nodeClone, visitedNodes))
						if(!checked)
							// TODO display warning - some components are used in a different subtree - you can uncheck them manually
							;

					setupVisibleCheckboxes();

					// enable / disable details for current selection
					if(m_treeViewer.getTree().getSelectionCount() == 1)
						setEnableDetails(!((TreeNode)m_treeViewer.getTree().getSelection()[0].getData()).getHandler().isExclude());
				}
			}

		    private void setCheckedSubtree(TreeNode node, boolean checked, Set<TreeNode> visitedNodes)
			{
				if(visitedNodes.contains(node))
					return;
				
				node.setChecked(checked);
				visitedNodes.add(node);
				
				for(TreeNode child : node.getChildren())
					setCheckedSubtree(child, checked, visitedNodes);				
			}

			private boolean checkAndRepairSubtreeCloneConflicts(TreeNode node, Set<TreeNode> visitedNodes)
			{
				if(visitedNodes.contains(node))
					return false; // don't care about the original conflict status - if it was originally TRUE, TRUE gets to the top anyway
				
				visitedNodes.add(node);
				
				boolean conflict = false;
				
				MaterializationNodeHandler handler = node.getHandler();

				conflict = conflict || handler.setExcludeAccordingToClonesCheckConflicts();
				
				for(TreeNode child : node.getChildren())
				{
					boolean newConflict = checkAndRepairSubtreeCloneConflicts(child, visitedNodes);
					conflict = conflict || newConflict;
				}
				
				return conflict;
			}
		});

		tree.addMouseTrackListener(new MouseTrackAdapter()
		{

			@Override
			public void mouseExit(MouseEvent e)
			{
				tree.setToolTipText(null);
			}

			@Override
			public void mouseHover(MouseEvent e)
			{
				TreeItem item = tree.getItem(new Point(e.x, e.y));
				String toolTipText = null;

				if(item != null)
				{
					toolTipText = ((TreeNode)item.getData()).getHandler().getComponentDescription();
				}

				tree.setToolTipText(toolTipText);
			}
		});
		
	    tree.addTreeListener(new TreeListener()
		{
			public void treeExpanded(TreeEvent e)
			{
				m_expandedTreeItems.add((TreeItem)e.item);
				setupVisibleCheckboxes();
			}

			public void treeCollapsed(TreeEvent e)
			{
				m_expandedTreeItems.remove(e.item);
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
	}
	
	private void initializeTree()
	{
		m_mspec.getNodes().clear();
		m_componentMap.clear();
		m_treeRoot = new TreeNode(null, null, false);
		
		try
		{
			MaterializationNodeHandler handler = getHandler(m_bom);

			if(handler != null)
			{
				TreeNode treeNode = handler.createTreeNodeClone(m_treeRoot);
				addChildrenItems(treeNode, m_bom);
			}

		}
		catch(CoreException e)
		{
			throw new JNLPException("Error while reading artifact specification -\n\tbill of materials can not be read",
					ERROR_CODE_BOM_IO_EXCEPTION, e);
		}
	}

	private void addChildrenItems(TreeNode parentTN, DepNode parentDN) throws CoreException
	{
		for(DepNode depNode : getSortedChildren(parentDN))
		{
			MaterializationNodeHandler handler = getHandler(depNode);

			if(handler != null)
			{
				TreeNode treeNode = handler.createTreeNodeClone(parentTN);
				addChildrenItems(treeNode, depNode);
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

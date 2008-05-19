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
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
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
public class MSpecDetailsPanel implements IUnresolvedNodeHandler
{	
	private Map<MaterializationNodeHandler, Map<MaterializationNodeHandler, TreeNode>> m_treeNodeCache =
					new HashMap<MaterializationNodeHandler, Map<MaterializationNodeHandler, TreeNode>>();

	private enum ResolveStatus
	{
		RESOLVED, UNRESOLVED, UNRESOLVED_CHILD
	}
	
	class TreeNode
	{
		private MaterializationNodeHandler m_handler;

		private TreeNode m_parent;
		
		private Set<TreeNode> m_uncles = new HashSet<TreeNode>();	// secondary parents

		private List<TreeNode> m_children = new ArrayList<TreeNode>();
		
		private boolean m_checked;

		private boolean m_oldChecked;

		public TreeNode(MaterializationNodeHandler handler, TreeNode parentTreeNode, boolean checked)
		{
			m_handler = handler;
			m_parent = parentTreeNode;
			m_checked = checked;
			m_oldChecked = false;
			
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
		
		public void addUncle(TreeNode parentTreeNode)
		{
			m_uncles.add(parentTreeNode);		
		}
		
		public boolean isChecked()
		{
			return m_checked;
		}

		public void setChecked(boolean checked)
		{
			m_checked = checked;
		}
		
		public boolean isOldChecked()
		{
			return m_oldChecked;
		}

		public void setOldChecked(boolean checked)
		{
			m_oldChecked = checked;
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
		
		private static final String ICON_RESOLVED = "node.resolved.gif";
		
		private static final String ICON_UNRESOLVED = "node.unresolved.gif";
		
		private static final String ICON_UNRESOLVED_CHILD = "node.resolved_warning.gif";
		
		private final Image m_iconResolved = MaterializationUtils.getImage(ICON_RESOLVED);

		private final Image m_iconUnresolved = MaterializationUtils.getImage(ICON_UNRESOLVED);

		private final Image m_iconUnresolvedChild = MaterializationUtils.getImage(ICON_UNRESOLVED_CHILD);
		
		public Image getImage(Object element)
		{
			TreeNode treeNode = (TreeNode)element;
			switch(treeNode.getHandler().getResolveStatus())
			{
			case RESOLVED:
				return m_iconResolved;
			case UNRESOLVED:
				return m_iconUnresolved;
			case UNRESOLVED_CHILD:
				return m_iconUnresolvedChild;
			default:
				return null;
			}
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

		private ComponentRequest m_request;
		
		private CSpec m_cspec;

		private List<TreeNode> m_cloneItems = new ArrayList<TreeNode>();
		
		private ResolveStatus m_resolveStatus;
		
		public MaterializationNodeHandler(List<MaterializationNodeBuilder> nodes, MaterializationNodeBuilder node, ComponentRequest request, CSpec cspec, boolean resolved)
		{
			m_node = node;
			nodes.add(node);
			m_request = request;
			m_cspec = cspec;
			if(resolved)
				m_resolveStatus = ResolveStatus.RESOLVED;
			else
			{
				m_resolveStatus = ResolveStatus.UNRESOLVED;
			}
			
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

		public boolean setExcludeAccordingToClonesCheckConflicts(boolean checked)
		{
			boolean totalAnd = true;
			boolean totalOr = false;
			
			for(TreeNode tn : m_cloneItems)
			{
				boolean nodeChecked = tn.isChecked();
				
				totalAnd = totalAnd && nodeChecked;
				totalOr = totalOr || nodeChecked;
				
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

			if(!checked)
				rollbackChecked(); // subtree needs to be rollbacked
			
			return true;
		}

		private void rollbackChecked()
		{
			Set<TreeNode> visitedNodes = new HashSet<TreeNode>();
			
			for(TreeNode nodeClone : m_cloneItems)
				for(TreeNode childClone : nodeClone.getChildren())
					rollbackSubtreeChecked(childClone, visitedNodes);
		}

	    private void rollbackSubtreeChecked(TreeNode node, Set<TreeNode> visitedNodes)
		{
			if(visitedNodes.contains(node))
				return;
			
			node.setChecked(node.isOldChecked());
			visitedNodes.add(node);
			
			for(TreeNode child : node.getChildren())
				rollbackSubtreeChecked(child, visitedNodes);				
		}
		
		public TreeNode createTreeNodeClone(TreeNode parentTreeNode)
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
				treeNode.addUncle(parentTreeNode);
			}
			
			if(m_resolveStatus == ResolveStatus.UNRESOLVED || m_resolveStatus == ResolveStatus.UNRESOLVED_CHILD)
			{
				Set<TreeNode> visitedNodes = new HashSet<TreeNode>();
				setParentToUnresolvedChild(parentTreeNode, visitedNodes);
			}
			
			return treeNode;
		}

		private void setParentToUnresolvedChild(TreeNode treeNode, Set<TreeNode> visitedNodes)
		{
			if(visitedNodes.contains(treeNode))
				return;
			
			visitedNodes.add(treeNode);
			
			MaterializationNodeHandler handler = treeNode.getHandler();
			
			if(handler == null)
				return;
			
			if(handler.getResolveStatus() == ResolveStatus.UNRESOLVED || handler.getResolveStatus() == ResolveStatus.UNRESOLVED_CHILD)
				return;
			
			handler.setUnresolvedChild();
			
			for(TreeNode clone : handler.getTreeNodeClones())
			{
				setParentToUnresolvedChild(clone.getParent(), visitedNodes);
			}
		}

		public List<TreeNode> getTreeNodeClones()
		{
			return m_cloneItems;
		}

		public ResolveStatus getResolveStatus()
		{
			return m_resolveStatus;
		}
		
		public void setUnresolvedChild()
		{
			if(m_resolveStatus != ResolveStatus.UNRESOLVED)				
				m_resolveStatus = ResolveStatus.UNRESOLVED_CHILD;
		}
		
		public String getComponentShortDescription()
		{
			if(m_cspec == null)
				return m_request.getName() + "/" + MaterializationUtils.getHumanReadableComponentType(m_request.getComponentTypeID());
				
			return (m_cspec.getShortDesc() == null ? m_cspec.getComponentIdentifier().getName() : m_cspec.getShortDesc()) +
			 		"/" + MaterializationUtils.getHumanReadableComponentType(m_cspec.getComponentIdentifier().getComponentTypeID());
		}

		public String getComponentDescription()
		{
			SmartArrayList<String> smartList = new SmartArrayList<String>();
			
			if(m_cspec != null)
			{
				if(m_resolveStatus == ResolveStatus.UNRESOLVED_CHILD)
					smartList.add("*** DEPENDS ON AN UNRESOLVED ARTIFACT ***");
					
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
					smartList.add("Meta-Data Extractor: "
							+ MaterializationUtils.getHumanReadableComponentType(m_cspec.getComponentIdentifier()
									.getComponentTypeID()));
				}
				if(m_cspec.getVersion() != null)
				{
					smartList.add("Version: " + m_cspec.getVersion());
				}
				if(m_node.getInstallLocation() != null)
				{
					smartList.add("Destination Address: "
							+ m_node.getInstallLocation().removeTrailingSeparator().toOSString());
				}
				if(m_node.getConflictResolution() != null)
				{
					smartList.add("Conflict Resolution: " + m_node.getConflictResolution());
				}
			}
			else
			{
				smartList.add("*** UNRESOLVED ARTIFACT ***");
				smartList.add("Requested Name: " + m_request.getName());
				smartList.add("Requested Meta-data extractor: " + MaterializationUtils.getHumanReadableComponentType(m_request.getComponentTypeID()));
				smartList.add("Requested Version: " + (m_request.getVersionDesignator() == null ? "Any" : m_request.getVersionDesignator()));
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

	private Set<MaterializationNodeHandler> m_unresolved = new HashSet<MaterializationNodeHandler>();
	
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

			// add the root TreeItems
			m_expandedTreeItems.addAll(Arrays.asList(m_treeViewer.getTree().getItems()));
			
			setupVisibleCheckboxes();
		}
	}
	
	public boolean isUnresolvedNodeIncluded()
	{
		for(MaterializationNodeHandler handler : m_unresolved)
		{
			if(!handler.getNodeBuilder().isExclude())
				return true;
		}
		
		return false;
	}
	
	public void excludeUnresolvedNodes()
	{
		for(MaterializationNodeHandler handler : m_unresolved)
			handler.setExclude(true);
		
		setupVisibleCheckboxes();
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
					if(nodeClone != null && checkAndRepairSubtreeCloneConflicts(nodeClone, visitedNodes, checked))
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
				
				node.setOldChecked(node.isChecked());
				node.setChecked(checked);
				visitedNodes.add(node);
				
				for(TreeNode child : node.getChildren())
					setCheckedSubtree(child, checked, visitedNodes);				
			}

			private boolean checkAndRepairSubtreeCloneConflicts(TreeNode node, Set<TreeNode> visitedNodes, boolean checked)
			{
				if(visitedNodes.contains(node))
					return false; // don't care about the original conflict status - if it was originally TRUE, TRUE gets to the top anyway
				
				visitedNodes.add(node);
				
				boolean conflict = false;
				
				MaterializationNodeHandler handler = node.getHandler();

				conflict = conflict || handler.setExcludeAccordingToClonesCheckConflicts(checked);
				
				for(TreeNode child : node.getChildren())
				{
					boolean newConflict = checkAndRepairSubtreeCloneConflicts(child, visitedNodes, checked);
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
		m_originalNodeBuilders.addAll(m_mspec.getNodeBuilders());
		initializeTree();		
	}
	
	private void initializeTree()
	{
		m_mspec.getNodes().clear();
		m_componentMap.clear();
		m_unresolved.clear();
		m_treeRoot = new TreeNode(null, null, false);
		
		try
		{
			MaterializationNodeHandler handler = getHandler(m_bom);

			if(handler != null)
			{
				TreeNode treeNode = handler.createTreeNodeClone(m_treeRoot);
				addChildrenItems(treeNode, m_bom);
				
				// set unresolved child nodes
				Set<TreeNode> visitedNodes = new HashSet<TreeNode>();
				hasUnresolvedChild(m_treeRoot, visitedNodes);
			}
		}
		catch(CoreException e)
		{
			throw new JNLPException("Error while reading artifact specification -\n\tbill of materials can not be read",
					ERROR_CODE_BOM_IO_EXCEPTION, e);
		}
	}

	private boolean hasUnresolvedChild(TreeNode node, Set<TreeNode> visitedNodes)
	{
		if(visitedNodes.contains(node))
			return false;
		
		visitedNodes.add(node);
		
		boolean unresolvedChild = false;
		
		for(TreeNode child : node.getChildren())
		{
			if(hasUnresolvedChild(child, visitedNodes))
			{
				unresolvedChild = true;
				child.getHandler().setUnresolvedChild();
			}
		}
		
		return unresolvedChild;
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

		ComponentName componentNameId;		
		if(resolution == null)
			componentNameId = depNode.getRequest();
		else
			componentNameId = resolution.getComponentIdentifier();
		
		MaterializationNodeHandler handler = m_componentMap.get(componentNameId);

		if(handler == null)
		{
			CSpec cspec = null;
			if(resolution != null)
				cspec = resolution.getCSpec();
			
			String componentName;
			String componentType;
			
			if(cspec != null)
			{
				componentName = cspec.getName();
				componentType = cspec.getComponentTypeID();
			}
			else
			{
				componentName = depNode.getRequest().getName();
				componentType = depNode.getRequest().getComponentTypeID();
			}

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
			if(nodeBuilder.getInstallLocation() != null)
				nodeBuilder.setInstallLocation(MaterializationUtils.expandPath(m_mspec, nodeBuilder.getInstallLocation()));
			if(componentType != null)
				nodeBuilder.setComponentTypeID(componentType);

			handler = new MaterializationNodeHandler(m_mspec.getNodeBuilders(), nodeBuilder, depNode.getRequest(), cspec, depNode.getResolution() != null);
			m_componentMap.put(componentNameId, handler);
			if(depNode.getResolution() == null)
				m_unresolved.add(handler);
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
				
				String componentId;
				if (resolution != null)
					componentId = resolution.getComponentIdentifier().toString();
				else
					componentId = child.getRequest().toString();					
					
				sortedMap.put(componentId, child);
			}
			children = sortedMap.values();
		}
		return children;
	}
}

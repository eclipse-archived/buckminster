/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.buckminster.distro.ui.editor.distro;

import java.net.URI;

import org.eclipse.buckminster.generic.model.tree.ITreeParentDataNode;
import org.eclipse.buckminster.generic.ui.model.tree.UISafeTreeRootDataNode;
import org.eclipse.buckminster.generic.ui.providers.TreeDataNodeContentProvider;
import org.eclipse.buckminster.opml.IOutline;
import org.eclipse.buckminster.opml.builder.BodyBuilder;
import org.eclipse.buckminster.opml.builder.OutlineBuilder;
import org.eclipse.buckminster.ui.adapters.OutlineDataNode;
import org.eclipse.buckminster.ui.providers.BuckminsterLabelProvider;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.equinox.p2.authoring.forms.IMasterDetailsController;
import org.eclipse.equinox.p2.authoring.forms.TreeMasterDetailsBlock;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.eclipse.ui.forms.editor.FormPage;

/**
 * A MasterDetails block for OPML Body that display's the OPML Outlines in a tree and allows for 
 * editing of elements in the tree.
 * 
 * @author Henrik Lindberg
 * 
 */
public class OPMLBodyBlock extends TreeMasterDetailsBlock implements IDetailsPageProvider, IMasterDetailsController
{

	private IDetailsPage m_feedPage;

	private IDetailsPage m_folderPage;

	private BuckminsterLabelProvider m_labelProvider;

	private IDetailsPage m_linkPage;

	private TreeDataNodeContentProvider m_contentProvider;

	public OPMLBodyBlock(FormPage page, Object layoutData)
	{
		super(page, layoutData);
	}
	@Override
	public String getName()
	{
		return "Links and Feeds";
	}
	@Override
	public String getDescription()
	{
		return "Edit the links and feeds included in the distro";
	}
	public void add()
	{
		// TODO Unused - uses specific methods in a menu to add things...
	}

	public void addLink()
	{
		// TODO: add a link
	}

	public void addFolder()
	{
		// TODO: add a folder
	}

	public void addFeed()
	{
		// TODO: add a feed
	}

	/**
	 * Configures the add button to have a menu with different add types
	 */
	@Override
	protected void configureAddButton(final Button b)
	{
		// Create a listener for menu items so that the correct
		// type of add operation is performed.
		//
		SelectionListener listener = new SelectionListener()
		{

			public void widgetDefaultSelected(SelectionEvent e)
			{
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e)
			{
				Object data = e.item.getData();
				if("link".equals(data))
					addLink();
				if("feed".equals(data))
					addFeed();
				if("folder".equals(data))
					addFolder();
			}

		};
		Menu addMenu = new Menu(b.getShell(), SWT.POP_UP);
		MenuItem mi = new MenuItem(addMenu, SWT.PUSH);
		mi.setText("Add Feed");
		mi.setData("feed");
		mi.addSelectionListener(listener);

		mi = new MenuItem(addMenu, SWT.PUSH);
		mi.setText("Add Link");
		mi.setData("link");
		mi.addSelectionListener(listener);

		mi = new MenuItem(addMenu, SWT.PUSH);
		mi.setText("Add Folder");
		mi.setData("folder");
		mi.addSelectionListener(listener);

		// attach menu to button (pops up on right mouse click)
		b.setMenu(addMenu);
		// attach listener to button so menu pops up on button click (left click)
		b.addSelectionListener(new SelectionListener()
		{

			public void widgetDefaultSelected(SelectionEvent e)
			{
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e)
			{
				b.getMenu().setVisible(true);
			}

		});
	}

	public void down()
	{
		// TODO move selected element down

	}

	@Override
	public IDetailsPageProvider getDetailsPageProvider()
	{
		return this;
	}

	public IDetailsPage getFeedPage()
	{
		if(m_feedPage == null)
			m_feedPage = new OutlineFeedPage();
		return m_feedPage;
	}

	public IDetailsPage getFolderPage()
	{
		if(m_folderPage == null)
			m_folderPage = new OutlineFolderPage();
		return m_folderPage;
	}

	public IDetailsPage getLinkPage()
	{
		if(m_linkPage == null)
			m_linkPage = new OutlineLinkPage();
		return m_linkPage;
	}

	@Override
	public IStructuredContentProvider getMasterContentProvider()
	{
		if(m_contentProvider == null)
			m_contentProvider = new TreeDataNodeContentProvider()
			{
				/**
				 * Initializes the content provider with a tree root that delivers events in a UI safe way.
				 */
				@Override
				protected void initialize()
				{
					UISafeTreeRootDataNode hiddenRoot = new UISafeTreeRootDataNode("resolutions");
					setHiddenRoot(hiddenRoot);
				}

				@Override
				public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
				{
					// if nothing changed
					if(oldInput == newInput && !(newInput instanceof IViewSite))
						return;

					super.inputChanged(viewer, oldInput, newInput);
					ITreeParentDataNode root = getHiddenRoot();
					if(root == null)
					{
						initialize();
						root = getHiddenRoot();
					}
					// TODO: Change dummy implementation to real
					BodyBuilder bbld = new BodyBuilder();
					BodyBuilder rootBuilder = bbld;

					OutlineBuilder obld = bbld.addOutline();
					// Top level feed
					obld.setText("Feed the monkey");
					obld.setHtmlUrl(URI.create("http://www.somewhere.com/somepage.html"));
					obld.setXmlUrl(URI.create("http://feeds.somewhere.com/afeed"));
					obld.setTypeString("rss");

					// Top level link
					obld = bbld.addOutline();
					obld.setText("Chain the monkey");
					obld.setUrl(URI.create("http://www.somewhere.com/somepage.html"));
					obld.setTypeString("link");

					// Top level folder
					obld = bbld.addOutline();
					obld.setText("Daddy's bottom drawer");

					// add to nested folder
					bbld = obld;

					// nested feed
					obld = bbld.addOutline();
					obld.setText("Dirty little pamphlet");
					obld.setHtmlUrl(URI.create("http://www.somewhere.com/drawer.html"));
					obld.setXmlUrl(URI.create("http://feeds.somewhere.com/dirtyfeed"));
					obld.setTypeString("rss");

					// nested link
					obld = bbld.addOutline();
					obld.setText("Dirty love");
					obld.setUrl(URI.create("http://www.somewhere.com/detergents.html"));
					obld.setTypeString("link");

					root.removeAllChildren();
					for(IOutline o : rootBuilder.getOutlines())
						root.addChild(new OutlineDataNode(o));
				}
			};
		return m_contentProvider;
	}

	/**
	 * Returns 'this' as a handler of add, remove, up, down,...
	 */
	@Override
	public IMasterDetailsController getMasterDetailsController()
	{
		return this;
	}

	@Override
	public ILabelProvider getMasterLabelProvider()
	{
		if(m_labelProvider == null)
			m_labelProvider = new BuckminsterLabelProvider();
		return m_labelProvider;
	}

	/**
	 * Returns a page for a page key returned by {@link #getPageKey(Object)}.
	 */
	public IDetailsPage getPage(Object key)
	{
		if((key instanceof OutlineFlavorDetector.Flavor))
			switch((OutlineFlavorDetector.Flavor)key)
			{
			case FEED:
				return getFeedPage();
			case FOLDER:
				return getFolderPage();
			case LINK:
				return getLinkPage();
			}
		return null;
	}
	/**
	 * Selects a page key for the selected object. See {@link #getPage(Object)}.
	 */
	public Object getPageKey(Object object)
	{
		if(!(object instanceof IAdaptable))
			return null;
		IOutline outline = (IOutline)((IAdaptable)object).getAdapter(IOutline.class);
		if(outline == null)
			return null;
		return OutlineFlavorDetector.getType(outline);
	}

	public void remove()
	{
		// TODO remove selected element

	}

	public void up()
	{
		// TODO move selected element up
	}

}

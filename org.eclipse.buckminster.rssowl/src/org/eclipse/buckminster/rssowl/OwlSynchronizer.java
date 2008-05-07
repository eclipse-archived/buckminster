

package org.eclipse.buckminster.rssowl;

import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.generic.utils.ProgressUtils;
import org.eclipse.buckminster.opml.model.Body;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.opml.model.Outline;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import org.rssowl.core.Owl;
import org.rssowl.core.persist.IBookMark;
import org.rssowl.core.persist.IFeed;
import org.rssowl.core.persist.IFolder;
import org.rssowl.core.persist.IMark;
import org.rssowl.core.persist.ISearchMark;
import org.rssowl.core.persist.dao.DynamicDAO;
import org.rssowl.core.persist.dao.IFeedDAO;
import org.rssowl.core.persist.dao.IFolderDAO;
import org.rssowl.core.persist.reference.FeedLinkReference;
import org.rssowl.core.persist.reference.FeedReference;
import org.rssowl.core.util.URIUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Synchronizes the OPML for all resolutions with Rss Owl bookmarks and feeds.
 * 
 * @author Henrik Lindberg
 */
public class OwlSynchronizer
{
	private static URI s_componentBlogroll;

	/**
	 * Synchronizes the bookmarks for all resolutions.
	 * 
	 * @throws CoreException
	 */
	public static void syncAllResolutions(IProgressMonitor monitor) throws CoreException
	{
		final IProgressMonitor waitingForResolutionsMonitor = ProgressUtils.submon(monitor, 1);
		waitingForResolutionsMonitor.beginTask("Waiting for resolutions", IProgressMonitor.UNKNOWN);
		
		FolderState root = getComponentsFolder();
		List<Resolution> resolutions = null;
		waitingForResolutionsMonitor.worked(1);
		resolutions = WorkspaceInfo.getAllResolutions();
		waitingForResolutionsMonitor.done();
		
		if(resolutions == null)
			return;
		final IProgressMonitor bookmarkSyncMonitor = ProgressUtils.submon(monitor, 1);
		bookmarkSyncMonitor.beginTask("Synchronizing Bookmarks", resolutions.size()+2);

		for(Resolution r : resolutions)
		{
			bookmarkSyncMonitor.worked(1);
			
			// A resolution without OPML, or where the Body is null or has no outlines
			// is not worth processing. We may still end up with an empty folder though if all
			// the links in the outline are just url links...
			//
			OPML opml = r.getOPML();
			if(opml == null || opml.getBody() == null || opml.getBody().getOutlines().size() < 1)
				continue;
			
			FolderState cf = root.createFolder(r.getName());
			sync(r, cf);			
		}
		
		//--SAVE everything to keep (this is easy since the OWL DAO cascades all the changes.
		//
		root.save();
		bookmarkSyncMonitor.worked(1);
		
		//--REMOVE everything else
		root.removeUnused();		
		bookmarkSyncMonitor.worked(1);
		bookmarkSyncMonitor.done();
	}
	/**
	 * Updates one selected Resolution with Owl bookmarks.
	 * The folder for the resolution will not be removed if the resolution has changed from
	 * having OPML to being empty as this requires synchronizing the parent folder.
	 * 
	 * @param resolution
	 * @throws CoreException
	 */
	public static void syncResolution(Resolution resolution) throws CoreException
	{
		FolderState root = getComponentsFolder();
		FolderState cf = root.createFolder(resolution.getName());
		sync(resolution, cf);			

		root.save();
		// Do NOT call root.removeUnused as that would remove everything else but the
		// just synchronized folder! (As we did not mark any other folder as "keep").
		cf.removeUnused();
	}
	/**
	 * Synchronize the resolution and the folder. The resolution must have opml with
	 * at least one outline in the body.
	 * @param r
	 * @param componentFolder
	 */
	private static void sync(Resolution r, FolderState componentFolder)
	{
		OPML opml = r.getOPML();
		Body body = opml.getBody();
		if(body == null)
			return; // empty OPML
		sync(body.getOutlines(), componentFolder);
	}
	private static void sync(List<Outline> outlines, FolderState folder)
	{
		if(outlines == null)
			return;
		for(Outline outline : outlines)
		{
			// An outline is just a link if it has an url property and such outlines are ignored
			// as rssowl only handles feeds.
			if(outline.getUrl() != null)
				continue;
			
			// An outline that has an "XmlUrl" attribute is a feed reference,
			// and an outline that is not a feed is a folder
			if(outline.getXmlUrl() == null)
				sync(outline.getOutlines(), folder.createFolder(outline.getText()));				
			else
				folder.createMark(outline);
		}
	}
	/**
	 * If the buckminster root folder in OWL is not already created, it is automatically
	 * created.
	 * @return the Buckminster Component's root folder.
	 */
	private static FolderState getComponentsFolder()
	{
		// Create 
		URI blogroll = getComponentBlogroll();
		IFolderDAO folderDAO = DynamicDAO.getDAO(IFolderDAO.class);
		
		// Get root folders and search for the buckminster component folder.
		// If not found, create the folder.
		//
		Collection<IFolder> rootFolders = folderDAO.loadRoots();
		IFolder componentsFolder = null;
		for(IFolder f : rootFolders)
		{
			if("Components".equals(f.getName()) && blogroll.equals(f.getBlogrollLink()))
			{
				// found it
				componentsFolder = f;
				break; 
			}
		}
		if(componentsFolder == null)
		{
			// Create the components root folder.
			// Set a fake blogroll to make it special
		    componentsFolder = Owl.getModelFactory().createFolder(null, null, "Components");
		    componentsFolder.setBlogrollLink(blogroll);
		    folderDAO.save(componentsFolder);
		    // Return the newly created folder state
		    return new FolderState(componentsFolder, true);
		}
		// return the folder state for the existing root folder
		// (this will read the entire tree of folders)
		return new FolderState(componentsFolder, false);
	}

	/**
	 * Returns a "fake" URI used to represent the internal blogroll of all components in the
	 * workspace.
	 * 
	 * @return
	 */
	private static synchronized URI getComponentBlogroll()
	{
		if(s_componentBlogroll == null)
			s_componentBlogroll = URIUtils.createURI("http://org.eclipse.buckminster.rssowl/Components");
		return s_componentBlogroll; 
			
	}
	
	private static class MarkState
	{
		private IBookMark m_mark;
		private boolean m_keep;
		
		public MarkState(IBookMark mark, boolean keep)
		{
			m_mark = mark;
			m_keep = keep;
		}
		public void setKeep(boolean keep)
		{
			m_keep = keep;
		}
		public boolean isKeep()
		{
			return m_keep;
		}
		public IBookMark getMark()
		{
			return m_mark;
		}
	}
	private static class FolderState
	{
		private IFolder m_folder;
		private List<IFolder> m_children;
		private Map<String, FolderState> m_folderMap;
		private Map<String, MarkState> m_markMap;
		private boolean m_keep;
		
		public FolderState(IFolder folder, boolean infant)
		{
			m_folder = folder;
			m_children = infant ? new ArrayList<IFolder>(0) :  folder.getFolders();
			m_folderMap = new HashMap<String, FolderState>(m_children.size());
			for(IFolder f : m_children)
				m_folderMap.put(f.getName(), new FolderState(f, false));
			m_keep = false;
			
			// get the marks - these can be both search marks, and bookmarks
			// search marks complicates things - a user may have entered a search mark
			// that has the same name as a bookmark from the component, hence step one
			// is to drop all found search marks found inside the synchronized structure.
			//
			List<IMark> marks = folder.getMarks();
			m_markMap  = new HashMap<String, MarkState>(marks.size());
			for(IMark mark : marks)
			{
				if(mark instanceof ISearchMark)
					DynamicDAO.delete(mark);
				if(mark instanceof IBookMark)
					m_markMap.put(mark.getName(), new MarkState((IBookMark)mark, false));
			}
		}
		public void save()
		{
			DynamicDAO.save(m_folder);
		}
		public void removeUnused()
		{
			// All folders not marked as keep should be deleted
			// All folders that are kept should be visited so they can remove their unused
			// folders
			for(FolderState fs : m_folderMap.values())
			{
				if(fs.isKeep())
					fs.removeUnused();
				else
					DynamicDAO.delete(fs.m_folder);
			}
			
			// All bookmarks not marked as keep should be deleted
			for(MarkState ms : m_markMap.values())
			{
				if(!ms.isKeep())
					DynamicDAO.delete(ms.getMark());
			}
		}
		/** 
		 * Returns a folder state for the stated name after possibly creating the folder
		 * first. If folder existed it is marked as a folder to keep.
		 * @param name
		 * @return
		 */
		public FolderState createFolder(String name)
		{
			FolderState fs = m_folderMap.get(name);
			if(fs == null)
			{
			    fs = new FolderState(Owl.getModelFactory().createFolder(null, m_folder, name), true);

			    m_folderMap.put(name, fs);
			}
			fs.setKeep(true);
			return fs;
		}
		protected void setKeep(boolean keep)
		{
			m_keep = keep;
		}
		public boolean isKeep()
		{
			return m_keep;
		}
		public void createMark(Outline outline)
		{			
			if(outline.getXmlUrl() != null)
			{
				// Check if a Feed with the XML URL already exists
				IFeedDAO feedDao = Owl.getPersistenceService().getDAOService().getFeedDAO();
				FeedReference feedRef = feedDao.loadReference(outline.getXmlUrl());

				// Create if non existing
				if(feedRef == null)
				{
					IFeed feed = Owl.getModelFactory().createFeed(null, outline.getXmlUrl());
					feed.setHomepage(outline.getHtmlUrl());
					feed.setDescription(outline.getDescription());
					feed = DynamicDAO.save(feed);
				}

				// Is the Bookmark already defined?
				String label = outline.getTitle();
				label = label == null ? outline.getText() : label;
				label = label == null ? outline.getXmlUrl().toString() : label;
				
				MarkState mark = m_markMap.get(label);
				if(mark != null)
				{
					// mark existed already - the feed may be wrong though
					FeedLinkReference flr = mark.getMark().getFeedLinkReference();
					if(!flr.getLink().equals(outline.getXmlUrl()))
							mark.getMark().setFeedLinkReference(new FeedLinkReference(outline.getXmlUrl()));
					mark.setKeep(true);
				}
				else
				{
				// Create the BookMark
				FeedLinkReference feedLinkRef = new FeedLinkReference(outline.getXmlUrl());
				IBookMark created = Owl.getModelFactory().createBookMark(null, m_folder, 
						feedLinkRef, label);
				// record that this is a new bookmark we want to keep
				m_markMap.put(created.getName(), new MarkState(created, true));
				}
			}
		
		}
	
	}
}

package org.eclipse.buckminster.rssowl;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.jobs.Job;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin implements IResourceChangeListener
{

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.buckminster.rssowl";

	// The shared instance
	private static Activator s_instance;

	private boolean m_dirty;

	/**
	 * The constructor
	 */
	public Activator()
	{
	}

	/**
	 * Synchronizes bookmarks in RSS OWL with the current state of the workspace.
	 * 
	 */
	@Override
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		s_instance = this;
		
		// make sure RSS OWL bookmarks and feeds are synchronized
		Job syncJob = new OwlSyncJob();
		syncJob.schedule();
		
		// Add listening to the workspace to be able to resync on certain types of changes.
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		s_instance = null;
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault()
	{
		return s_instance;
	}
	private void setBookmarksDirty(boolean dirty)
	{
		m_dirty = dirty;
	}
	private boolean isBookmarksDirty()
	{
		return m_dirty;
	}
	public void resourceChanged(IResourceChangeEvent event)
	{
		setBookmarksDirty(false);
		try {
			event.getDelta().accept(new IResourceDeltaVisitor(){

				public boolean visit(IResourceDelta delta) throws CoreException
				{
					IResource r = delta.getResource();
					if(r.getName().endsWith(".opml"))
						setBookmarksDirty(true);
					return true;
				}
				
			});
		}
		catch(CoreException e)
		{
			e.printStackTrace();
		}
		if(isBookmarksDirty())
		{
			Job syncJob = new OwlSyncJob();
			syncJob.schedule(1000L);			
		}
	}
}

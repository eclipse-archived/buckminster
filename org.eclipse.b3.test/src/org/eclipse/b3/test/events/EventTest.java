/*******************************************************************************
 * Copyright (c) 2009, Cloudsmith Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cloudsmith Inc - initial API and implementation
 *******************************************************************************/

package org.eclipse.b3.test.events;

import java.util.EventObject;

import org.eclipse.b3.provisional.core.Build;
import org.eclipse.b3.provisional.core.IBuildConstants;
import org.eclipse.b3.provisional.core.IBuildContext;
import org.eclipse.b3.provisional.core.ServicesHelper;
import org.eclipse.b3.provisional.core.eventbus.IBuildEventBus;
import org.eclipse.b3.provisional.core.eventbus.ISynchronousBuildListener;
import org.eclipse.b3.test.Activator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.util.tracker.ServiceTracker;

import junit.framework.TestCase;

public class EventTest extends TestCase {

	protected int eventCount = 0;
	public void testGetDefaultBuildContext()
	{
		IBuildContext ctx = Build.getDefaultBuildContext();
		assertNotNull("No default build context obtained from b3 Build", ctx); //$NON-NLS-1$
		// track the event bus
	}
	public void testTrackDefaultEventBus() throws InterruptedException, InvalidSyntaxException
	{
		ServiceTracker tracker = new ServiceTracker(Activator.getDefault().getBundle().getBundleContext(), 
				ServicesHelper.getTrackerFilter(IBuildConstants.DEFAULT_BUILD_CONTEXT, IBuildEventBus.class), 
				null);
		tracker.open();
		IBuildEventBus bus = (IBuildEventBus) tracker.waitForService(1000);
		eventCount = 0;
		bus.addListener(new TestListener());
		final IBuildContext ctx = Build.getDefaultBuildContext();

		Job theJob = new Job("dosomestuff") { //$NON-NLS-1$
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				ctx.dummyRun();
				return Status.OK_STATUS;
			}
		};
		theJob.schedule();
		theJob.join();
		assertEquals("Should have received 10 events", 10, eventCount); //$NON-NLS-1$
	}
	public class TestListener implements ISynchronousBuildListener {

		public void notify(EventObject o) {
			eventCount++;
		}
		
	}
}

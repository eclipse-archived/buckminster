/*******************************************************************************
 * Copyright (c) 2009 Cloudsmith Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cloudsmith Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.test.util.progress.test;

import java.io.IOException;
import java.net.UnknownHostException;

import junit.framework.TestCase;

import org.eclipse.buckminster.util.progress.ExternalProgressMonitorConstants;
import org.eclipse.buckminster.util.progress.ExternalProgressProxyMonitor;
import org.eclipse.buckminster.util.progress.SocketProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;

public class LoopbackTest extends TestCase
{

	private static class CancelMasterRunner implements Runnable
	{
		private IProgressMonitor monitor;

		private long sleep;

		public CancelMasterRunner(IProgressMonitor monitorToCancel, long msSleep)
		{
			monitor = monitorToCancel;
			sleep = msSleep;
		}

		public void run()
		{
			try
			{
				Thread.sleep(sleep);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			monitor.setCanceled(true);
		}

	}

	private static class MasterRunner implements Runnable
	{

		private SocketProgressMonitor monitor;

		public MasterRunner(SocketProgressMonitor m)
		{
			monitor = m;
		}

		public void run()
		{
			monitor.execute();
		}

	}

	private static class SlaveRunner implements Runnable
	{
		private int port;

		public ExternalProgressProxyMonitor monitor;

		public SlaveRunner(int serverPort)
		{
			port = serverPort;
		}

		public void run()
		{
			try
			{
				monitor = new ExternalProgressProxyMonitor(port);
				doWork(monitor);

			}
			catch(UnknownHostException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				monitor.done();
			}
		}
	}

	private static void doMoreWork(IProgressMonitor monitor)
	{
		SubMonitor m = SubMonitor.convert(monitor, "Sub Task", 10);

		m.subTask("doing more work");
		for(int i = 0; i < 10; i++)
		{
			m.worked(1);
			if(m.isCanceled())
			{
				// System.err.print("Canceled!");
				return;
			}
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException e)
			{
				// ignore
			}
		}
	}

	private static void doWork(IProgressMonitor monitor)
	{
		SubMonitor m = SubMonitor.convert(monitor, "Test Task", 100);
		m.subTask("doing work");
		// work 50 in increments of 10
		for(int i = 0; i < 5; i++)
			m.worked(10);

		// work one more
		m.worked(1);
		doMoreWork(m.newChild(49));
	}

	public void testSocketCommunication()
	{
		// Create two threads; master and slave

		// Master
		// Use a CountingProgressMonitor so assertions can be made
		CountingProgressMonitor testMonitor = new CountingProgressMonitor();
		SocketProgressMonitor masterMonitor = new SocketProgressMonitor(testMonitor);
		masterMonitor.setCmd(new String[] { ExternalProgressMonitorConstants.LOOPBACK_COMMAND });
		int port = 0;
		try
		{
			port = masterMonitor.createSocket();
		}
		catch(IOException e)
		{
			fail("createSocket failed with message: " + e.getMessage());
		}

		Runnable master = new MasterRunner(masterMonitor);

		SlaveRunner slave = new SlaveRunner(port);
		Thread masterThread = new Thread(master);
		masterThread.start();
		Thread slaveThread = new Thread(slave);
		slaveThread.start();
		Runnable canceler = new CancelMasterRunner(masterMonitor, 3000);
		Thread cancelThread = new Thread(canceler);
		cancelThread.start();

		try
		{
			slaveThread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		try
		{
			masterThread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		try
		{
			cancelThread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		assertTrue("Slave should have been canceled", slave.monitor.isCanceled());
		assertTrue("Slave should have 1000 units total", testMonitor.totWork == 1000);
		assertTrue("Slave should have had 2 subtasks recorded", testMonitor.subTaskCounter == 2);
		
		// don't know exactly when cancel occurs, but after 510 units
		assertTrue("Slave should have had more than 510 units of work recorded", testMonitor.workedAmount > 510);
		assertTrue("Slave should have called done once", testMonitor.doneCounter == 1);
	}

// Remove for simple way to debug
//	/**
//	 * Simple test that just prints stuff to stdout - the objective is to not crash :)
//	 */
//	public void testProxyMonitor()
//	{
//		IProgressMonitor monitor = new ExternalProgressProxyMonitor(System.out, System.in);
//		try
//		{
//			doWork(monitor);
//		}
//		finally
//		{
//			monitor.done();
//		}
//
//	}
}

/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.cache;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Filip Hrbek
 * 
 * This security manager grants executing potentially dangerous actions to all threads belonging to
 * registered thread groups or their descendants.
 * 
 * If a security check is required from a thread which is not considered as trusted, then the security
 * check is delegated to the original security manager which was registered at the time of loading this
 * security manager class.
 * 
 * If this security manager is used, no other threads should call System.setSecurityManager() directly,
 * otherwise the behavior of the process is unpredictable.
 */
public final class SimpleJNLPCacheSecurityManager extends SecurityManager
{
	private static SimpleJNLPCacheSecurityManager s_manager = new SimpleJNLPCacheSecurityManager();

	private SecurityManager m_origManager;

	private List<ThreadGroup> m_trustedThreadGroups;
	
	private boolean m_grantGroupAccess;

	private SimpleJNLPCacheSecurityManager()
	{
		m_trustedThreadGroups = new ArrayList<ThreadGroup>();
		m_origManager = System.getSecurityManager();
		m_grantGroupAccess = false;
	}

	public static SimpleJNLPCacheSecurityManager getInstance()
	{
		return s_manager;
	}

	public void addTrustedThreadGroup(ThreadGroup group)
	{
		m_trustedThreadGroups.add(group);
		if(!s_manager.equals(System.getSecurityManager()))
			System.setSecurityManager(s_manager);
	}

	public void removeTrustedThreadGroup(ThreadGroup group)
	{
		m_trustedThreadGroups.remove(group);

		if(m_trustedThreadGroups.size() == 0 && s_manager.equals(System.getSecurityManager())
				&& m_origManager != null && !m_origManager.equals(System.getSecurityManager()))
			System.setSecurityManager(m_origManager);
	}

	@Override
	public void checkAccept(String host, int port)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkAccept(host, port);
	}

	@Override
	public void checkAccess(Thread t)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkAccess(t);
	}

	@Override
	public synchronized void checkAccess(ThreadGroup g)
	{
		if(!m_grantGroupAccess && !isTrusted() && m_origManager != null)
			m_origManager.checkAccess(g);
	}

	@Override
	public void checkAwtEventQueueAccess()
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkAwtEventQueueAccess();
	}

	@Override
	public void checkConnect(String host, int port, Object context)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkConnect(host, port, context);
	}

	@Override
	public void checkConnect(String host, int port)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkConnect(host, port);
	}

	@Override
	public void checkCreateClassLoader()
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkCreateClassLoader();
	}

	@Override
	public void checkDelete(String file)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkDelete(file);
	}

	@Override
	public void checkExec(String cmd)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkExec(cmd);
	}

	@Override
	public void checkExit(int status)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkExit(status);
	}

	@Override
	public void checkLink(String lib)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkLink(lib);
	}

	@Override
	public void checkListen(int port)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkListen(port);
	}

	@Override
	public void checkMemberAccess(Class<?> clazz, int which)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkMemberAccess(clazz, which);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void checkMulticast(InetAddress maddr, byte ttl)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkMulticast(maddr, ttl);
	}

	@Override
	public void checkMulticast(InetAddress maddr)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkMulticast(maddr);
	}

	@Override
	public void checkPackageAccess(String pkg)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkPackageAccess(pkg);
	}

	@Override
	public void checkPackageDefinition(String pkg)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkPackageDefinition(pkg);
	}

	@Override
	public void checkPermission(Permission perm, Object context)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkPermission(perm, context);
	}

	@Override
	public void checkPermission(Permission perm)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkPermission(perm);
	}

	@Override
	public void checkPrintJobAccess()
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkPrintJobAccess();
	}

	@Override
	public void checkPropertiesAccess()
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkPropertiesAccess();
	}

	@Override
	public void checkPropertyAccess(String key)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkPropertyAccess(key);
	}

	@Override
	public void checkRead(FileDescriptor fd)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkRead(fd);
	}

	@Override
	public void checkRead(String file, Object context)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkRead(file, context);
	}

	@Override
	public void checkRead(String file)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkRead(file);
	}

	@Override
	public void checkSecurityAccess(String target)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkSecurityAccess(target);
	}

	@Override
	public void checkSetFactory()
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkSetFactory();
	}

	@Override
	public void checkSystemClipboardAccess()
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkSystemClipboardAccess();
	}

	@Override
	public boolean checkTopLevelWindow(Object window)
	{
		if(!isTrusted() && m_origManager != null)
			return m_origManager.checkTopLevelWindow(window);

		return true;
	}

	@Override
	public void checkWrite(FileDescriptor fd)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkWrite(fd);
	}

	@Override
	public void checkWrite(String file)
	{
		if(!isTrusted() && m_origManager != null)
			m_origManager.checkWrite(file);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean getInCheck()
	{
		if(m_origManager != null)
			return m_origManager.getInCheck();

		return super.getInCheck();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Object getSecurityContext()
	{
		if(m_origManager != null)
			return m_origManager.getSecurityContext();

		return super.getSecurityContext();
	}

	@Override
	public ThreadGroup getThreadGroup()
	{
		if(m_origManager != null)
			return m_origManager.getThreadGroup();

		return super.getThreadGroup();
	}

	private boolean isTrusted()
	{
		return isTrusted(Thread.currentThread().getThreadGroup());
	}

	private synchronized boolean isTrusted(ThreadGroup group)
	{
		try
		{
			m_grantGroupAccess = true;

			if(m_trustedThreadGroups.contains(group))
				return true;

			ThreadGroup parent = group.getParent();

			if(parent != null && !parent.equals(group))
				return isTrusted(parent);

			return false;
		}
		catch(SecurityException e)
		{
			return false;
		}
		catch(RuntimeException e)
		{
			return false;
		}
		finally
		{
			m_grantGroupAccess = false;
		}
	}
}

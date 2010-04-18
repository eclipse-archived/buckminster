/*******************************************************************************
 * Copyright (c) 2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.team;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.reader.AbstractReaderType;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.ITeamReaderType;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.team.core.RepositoryProvider;

/**
 * A base class of team actors' specific perform contexts capturing the common
 * functionality - mapping of {@link RepositoryProvider} IDs to
 * {@link ITeamReaderType}s and collecting of errors. The team actors are
 * expected to extend this class with data/functionality specific to them.
 * 
 * @author michal.ruzicka@cloudsmith.com
 */
public class TeamPerformContext {

	// an instance of IReaderType used for caching of negative results of reader
	// type lookups
	private static final ITeamReaderType NO_READER_TYPE = (ITeamReaderType) Proxy.newProxyInstance(TeamPerformContext.class.getClassLoader(),
			new Class<?>[] { ITeamReaderType.class }, new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					throw new UnsupportedOperationException();
				}
			});

	private final IActionContext actionContext;

	private HashMap<String, ITeamReaderType> readerTypeCache = new HashMap<String, ITeamReaderType>();

	private ArrayList<IStatus> statuses = new ArrayList<IStatus>();

	public TeamPerformContext(IActionContext actionContext) {
		this.actionContext = actionContext;
	}

	/**
	 * Return the collected statuses wrapped in a multi status and clear the
	 * list of the collected statuses.
	 * 
	 * @param message
	 *            a message to use for the wrapping multi status
	 * @return the multi status wrapping the collected statuses
	 */
	public IStatus collectedStatus(String message) {
		IStatus status = AbstractTeamActor.createMultiStatus(message, statuses.toArray(new IStatus[statuses.size()]));
		statuses.clear();
		return status;
	}

	/**
	 * Add the given status to a list of collected statuses but only if the
	 * given status is not {@link IStatus#isOK()}.
	 * 
	 * @param status
	 *            the status to add to the list of collected statuses
	 */
	public void collectStatus(IStatus status) {
		if (!status.isOK())
			statuses.add(status);
	}

	/**
	 * Return the {@link IActionContext} this <code>TeamPerformContext</code> is
	 * linked to.
	 * 
	 * @return the action context this team perform context is linked to
	 */
	public IActionContext getActionContext() {
		return actionContext;
	}

	/**
	 * Return an implementation of {@link ITeamReaderType} capable of handling
	 * resources associated with {@link RepositoryProvider}s with the given ID.<br/>
	 * Note that this method only uses a cache of provider ID to reader type
	 * mappings populated by the
	 * {@link #getReaderTypeForRepositoryProvider(String)} method. It is
	 * considered to be an error if there is no appropriate mapping found in the
	 * cache for the given repository provider ID - an
	 * <code>IllegalStateException</code> exception is thrown in such case.
	 * 
	 * @param providerID
	 *            the repository provider ID to get the reader type for
	 * @return the reader type corresponding to the given
	 *         <code>providerID</code> or <code>null</code> if such a reader
	 *         type does not exist
	 * @throws IllegalStateException
	 *             if there is no appropriate repository provider ID to reader
	 *             type mapping found in the cache
	 */
	public ITeamReaderType getCachedReaderTypeForRepositoryProvider(String providerID) throws IllegalStateException {
		ITeamReaderType readerType = readerTypeCache.get(providerID);
		if (readerType == null)
			throw new IllegalStateException();
		if (readerType == NO_READER_TYPE)
			return null;
		return readerType;
	}

	/**
	 * Return an implementation of {@link ITeamReaderType} capable of handling
	 * resources associated with a {@link RepositoryProvider}s with the given
	 * ID.<br/>
	 * Note that this method performs caching of the provider ID to reader type
	 * mappings. The cached mappings are used by both this method and
	 * {@link #getCachedReaderTypeForRepositoryProvider(String)} which uses them
	 * exclusively.
	 * 
	 * @param providerID
	 *            the repository provider ID to get the reader type for
	 * @return the reader type corresponding to the given
	 *         <code>providerID</code> or <code>null</code> if such a reader
	 *         type does not exist
	 * @throws CoreException
	 */
	public ITeamReaderType getReaderTypeForRepositoryProvider(String providerID) throws CoreException {
		ITeamReaderType readerType = readerTypeCache.get(providerID);
		if (readerType == null) {
			IReaderType fundamentalReaderType = AbstractReaderType.getTypeForRepositoryProvider(providerID);
			readerType = (fundamentalReaderType != null && fundamentalReaderType instanceof ITeamReaderType)
					? (ITeamReaderType) fundamentalReaderType : NO_READER_TYPE;
			readerTypeCache.put(providerID, readerType);
		}
		if (readerType == NO_READER_TYPE)
			return null;
		return readerType;
	}

	/**
	 * Return <code>true</code> if there is at least one status in the list of
	 * collected statuses.
	 * 
	 * @return <code>true</code> if there is at least one status in the list of
	 *         collected statuses, <code>false</code> otherwise
	 */
	public boolean hasErrors() {
		return !statuses.isEmpty();
	}

}
/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.accountservice;

import java.util.List;

/**
 * Enables publishing of an artifact to a space
 * 
 * @author Karel Brezina
 */
public interface IPublisher extends IAuthenticator
{
	public static final int STATUS_OK = 1;
	public static final int STATUS_FAIL = -1;
	public static final int ERROR_NOT_LOGGED_IN = -2;
	public static final int ERROR_TIMEOUT = -3;
	public static final int ERROR_SPACE_ACCESS_DENIED = -4;
	public static final int ERROR_ARTIFACT_ALREADY_EXISTS = -5;

	/**
	 * Creates a new publisher, that is connected to the same server as the original publisher
	 * 
	 * @param login true - the new publisher will be logged in using the same credentials as the original one,
	 * false - the new publisher will be only initialized
	 * @return
	 * @throws Exception
	 */
	public IPublisher createDuplicatePublisher(boolean login) throws Exception;
	
	public List<String> getSpaceNames() throws Exception;
	
	public int publish(String originalSpaceName, String spaceName, String artifactName, String xmlData, boolean replaceExisting) throws Exception;
}

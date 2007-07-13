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
 * @author Karel Brezina
 *
 */
public interface IPublisher extends IAuthenticator
{
	public List<String> getSpaceNames() throws Exception;
	
	public int publish(String spaceName, String artifactName, String xmlData, boolean replaceExisting) throws Exception;
}

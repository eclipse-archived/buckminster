/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.remote;

import org.eclipse.buckminster.runtime.Buckminster;

/**
 * Remote service constants
 * 
 * @author Filip Hrbek
 */
public final class RemoteConstants
{
	public static final String REMOVE_NAMESPACE = Buckminster.NAMESPACE + ".remote"; //$NON-NLS-1$

	public static final String PROVIDERS_POINT = REMOVE_NAMESPACE + ".providers"; //$NON-NLS-1$

	public static final String PROVIDER_ID_ATTR = "ID"; //$NON-NLS-1$

	public static final String PROVIDER_NAME_ATTR = "name"; //$NON-NLS-1$

	public static final String PROVIDER_PROTOCOL_ATTR = "protocol"; //$NON-NLS-1$

	public static final String PROVIDER_HOSTNAME_ATTR = "hostname"; //$NON-NLS-1$

	public static final String PROVIDER_PORT_ATTR = "port"; //$NON-NLS-1$

	public static final String PROVIDER_INITIALIZATION_PATH_ATTR = "initializationPath"; //$NON-NLS-1$

	public static final String PROVIDER_SERVICE_PATH_ATTR = "servicePath"; //$NON-NLS-1$

	public static final String PROVIDER_LOGIN_SUPPORT = "loginSupport"; //$NON-NLS-1$

	public static final String BOOLEAN_TRUE = "true"; //$NON-NLS-1$

	public static final String BOOLEAN_FALSE = "false"; //$NON-NLS-1$
}

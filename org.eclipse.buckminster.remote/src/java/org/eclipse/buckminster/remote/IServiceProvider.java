/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.remote;

/**
 * Provider of a remote service
 * 
 * @author Karel Brezina
 */
public interface IServiceProvider
{
	/**
	 * Gets remote service host
	 * 
	 * @return the host
	 */
	String getHost();

	/**
	 * Gets remote service ID
	 * 
	 * @return the id
	 */
	String getId();

	/**
	 * Gets remote service initialization URL path
	 * 
	 * @return the service path
	 */
	String getInitializationPath();

	/**
	 * Gets remote service name
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * Gets remote service port
	 * 
	 * @return the port
	 */
	String getPort();

	/**
	 * Gets remote service protocol
	 * 
	 * @return the protocol
	 */
	String getProtocol();

	/**
	 * Gets remote service URL path
	 * 
	 * @return the service path
	 */
	String getServicePath();

	/**
	 * Gets remote service login support
	 * 
	 * @return true=login is supported in the remote service
	 */
	boolean isLoginSupported();
}

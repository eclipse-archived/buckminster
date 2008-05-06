/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.componentinfo;

import java.util.Map;

import org.eclipse.buckminster.opml.model.OPML;

/**
 * Provides further component information
 * 
 * @author Karel Brezina
 */
public interface IComponentInfoProvider
{
	/**
	 * Provides URL string to component info HTML page
	 * 
	 * @param properties	materialization properties
	 * @param opml			OPML
	 * @param destination 	destination folder
	 * @return				URL string to component info HTML page
	 */
	String prepareHTML(Map<String,String> properties, OPML opml, String destination) throws Exception;
}

/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.install;

/**
 * Listens for changes in MSPEC builder. If the builder is changed, sends event with the new builder
 * 
 * @author Karel Brezina
 *
 */
public interface  MSpecChangeListener
{
	void handleMSpecChangeEvent (MSpecChangeEvent event);
}

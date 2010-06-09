/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A IBranchFinder will find the component branches that matches a certain
 * query.
 * 
 * @author Thomas Hallgren
 */
public interface IBranchFinder {
	String[] getBranches(NodeQuery query, IProgressMonitor monitor) throws CoreException;
}

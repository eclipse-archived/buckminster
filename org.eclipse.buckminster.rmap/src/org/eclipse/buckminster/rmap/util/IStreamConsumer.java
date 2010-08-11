/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.rmap.util;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Interface used by the {@link IComponentReader} to pass an opened stream to
 * some intended consumer.
 * 
 * @author thhal
 */
public interface IStreamConsumer<T> {
	T consumeStream(IComponentReader reader, String streamName, InputStream stream, IProgressMonitor monitor) throws CoreException, IOException;
}

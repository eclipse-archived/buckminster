/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 * 
 * Contributors:
 *  Lorenzo Bettini - https://bugs.eclipse.org/bugs/show_bug.cgi?id=428301
 ******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 */
public abstract class CatalogReaderType extends AbstractReaderType {
	@Override
	public Map<String, String> getFetchFactoryProviderProps(Map<String, Object> properties, Provider delegee) {
		Map<String, String> props = new HashMap<String, String>();
		props.put(KeyConstants.IS_SOURCE, Boolean.TRUE.toString());
		props.put(KeyConstants.IS_MUTABLE, Boolean.TRUE.toString());
		return props;
	}

	@Override
	public IPath getLeafArtifact(Resolution resolution, MaterializationContext context) throws CoreException {
		IPath path = Path.fromPortableString(resolution.getComponentIdentifier().getName());
		int segCount = path.segmentCount();
		if (segCount > 1)
			path = path.removeFirstSegments(segCount - 1);
		return path.addTrailingSeparator();
	}

	@Override
	public boolean isFileReader() {
		return false;
	}
}

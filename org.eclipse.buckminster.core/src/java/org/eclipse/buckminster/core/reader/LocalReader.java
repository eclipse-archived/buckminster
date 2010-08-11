package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * This reader is used when Buckminster materialization is bypassed. It expects
 * that the components that it searches for is already present at the location
 * where they are supposed to be when materialized. The reader will not find
 * anything unless the query has a REUSE status on the unknown.
 * 
 * @author Thomas Hallgren
 */
public class LocalReader extends URLCatalogReader {
	public LocalReader(URLCatalogReaderType readerType, ProviderMatch rInfo) throws CoreException {
		super(readerType, rInfo);
	}

	@Override
	public boolean canMaterialize() {
		return false;
	}

	@Override
	public void materialize(IPath destination, IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException(Messages.local_reader_cannot_materialize);
	}
}

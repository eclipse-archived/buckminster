package org.eclipse.buckminster.core.reader;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepositoryManager;

public class P2Reader extends AbstractCatalogReader {
	public static IArtifactRepository getArtifactRepository(URI repoLocation, IProgressMonitor monitor) throws CoreException {
		IArtifactRepositoryManager manager = (IArtifactRepositoryManager) CorePlugin.getDefault().getResolverAgent().getService(
				IArtifactRepositoryManager.SERVICE_NAME);
		if (manager == null)
			throw new IllegalStateException("No artifact repository manager found"); //$NON-NLS-1$

		SubMonitor subMon = SubMonitor.convert(monitor, 200);
		try {
			return manager.loadRepository(repoLocation, subMon.newChild(100));
		} catch (ProvisionException e) {
			return manager.refreshRepository(repoLocation, subMon.newChild(100));
		} finally {
			if (monitor != null)
				monitor.done();
		}
	}

	public P2Reader(IReaderType readerType, ProviderMatch providerMatch) {
		super(readerType, providerMatch);
	}

	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException {
		IArtifactRepository ar = getArtifactRepository(getURI(), monitor);
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(destination.toFile()));
			ar.getArtifact(null, out, monitor);
		} catch (FileNotFoundException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(out);
		}
	}

	@Override
	protected boolean innerExists(String fileName, IProgressMonitor monitor) throws CoreException {
		return false;
	}

	@Override
	protected <T> T innerReadFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor) throws CoreException, IOException {
		return null;
	}

	URI getURI() {
		ProviderMatch pm = getProviderMatch();
		return URI.create(pm.getProvider().getURI(pm.getNodeQuery().getProperties()));
	}
}

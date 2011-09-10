package org.eclipse.buckminster.git.internal;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.eclipse.buckminster.core.reader.AbstractCatalogReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.treewalk.TreeWalk;

public class GitReader extends AbstractCatalogReader {
	private final RepositoryAccess repoAccess;

	protected GitReader(IReaderType readerType, ProviderMatch providerMatch) throws CoreException {
		super(readerType, providerMatch);
		Provider provider = providerMatch.getProvider();
		@SuppressWarnings("unchecked")
		Map<String, String> props = (Map<String, String>) provider.getProperties(providerMatch.getNodeQuery().getProperties());
		repoAccess = new RepositoryAccess(provider.getURI(props), props);
	}

	@Override
	public File getLocation() throws CoreException {
		return repoAccess.getLocation(getProviderMatch().getVersionMatch());
	}

	@Override
	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException {
		File location = getLocation();
		try {
			location = location.getCanonicalFile();
		} catch (IOException e) {
		}
		File destFile = destination.toFile();
		try {
			destFile = destFile.getCanonicalFile();
		} catch (IOException e) {
		}
		if (!location.equals(destFile))
			throw new UnsupportedOperationException(Messages.git_reader_can_not_materialize);
	}

	@Override
	public boolean isFileSystemReader() {
		return true;
	}

	@Override
	protected boolean innerExists(String fileName, IProgressMonitor monitor) throws CoreException {
		synchronized (repoAccess.getRepositoryPath()) {
			TreeWalk walk = repoAccess.getTreeWalk(getProviderMatch().getVersionMatch(), fileName, monitor);
			try {
				return walk.next();
			} catch (IOException e) {
				throw BuckminsterException.wrap(e);
			} finally {
				walk.release();
			}
		}
	}

	@Override
	protected <T> T innerReadFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor) throws CoreException, IOException {
		synchronized (repoAccess.getRepositoryPath()) {
			VersionMatch vm = getProviderMatch().getVersionMatch();
			TreeWalk walk = repoAccess.getTreeWalk(vm, fileName, monitor);
			try {
				if (!walk.next())
					throw new FileNotFoundException(fileName);
				Repository repo = repoAccess.getRepository(vm, monitor);
				ObjectLoader ol = repo.open(walk.getObjectId(0));
				byte[] bytes = ol.getBytes();
				return consumer.consumeStream(this, fileName, new ByteArrayInputStream(bytes), monitor);
			} finally {
				walk.release();
			}
		}
	}
}

package org.eclipse.buckminster.git.internal;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.buckminster.core.reader.AbstractCatalogReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.util.IStreamConsumer;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.TreeEntry;

public class GitReader extends AbstractCatalogReader {
	private final RepositoryAccess repoAccess;

	protected GitReader(IReaderType readerType, ProviderMatch providerMatch) throws CoreException {
		super(readerType, providerMatch);
		Provider provider = providerMatch.getProvider();
		repoAccess = new RepositoryAccess(//
				provider.getURI(providerMatch.getNodeQuery().getProperties()),//
				provider.getProperties());
	}

	@Override
	public void materialize(IPath destination, IProgressMonitor monitor) throws CoreException {
		repoAccess.checkout(getProviderMatch().getVersionMatch(), destination.toFile(), monitor);
	}

	@Override
	protected boolean innerExists(String fileName, IProgressMonitor monitor) throws CoreException {
		try {
			return repoAccess.getComponentTree(getProviderMatch().getVersionMatch(), monitor).existsBlob(fileName);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	protected <T> T innerReadFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor) throws CoreException, IOException {
		TreeEntry blobEntry = repoAccess.getComponentTree(getProviderMatch().getVersionMatch(), monitor).findBlobMember(fileName);
		if (blobEntry == null)
			throw new FileNotFoundException(fileName);

		Repository repo = blobEntry.getRepository();
		ObjectLoader ol = repo.openBlob(blobEntry.getId());
		byte[] bytes = ol.getBytes();
		return consumer.consumeStream(this, fileName, new ByteArrayInputStream(bytes), monitor);
	}
}

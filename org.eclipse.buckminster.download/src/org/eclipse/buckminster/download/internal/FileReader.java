/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.download.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.URL;
import java.util.Date;

import org.eclipse.buckminster.download.Messages;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.FileInfoBuilder;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.ecf.filetransfer.FileTransferJob;
import org.eclipse.ecf.filetransfer.IFileTransferListener;
import org.eclipse.ecf.filetransfer.IIncomingFileTransfer;
import org.eclipse.ecf.filetransfer.IRetrieveFileTransferContainerAdapter;
import org.eclipse.ecf.filetransfer.IncomingFileTransferException;
import org.eclipse.ecf.filetransfer.UserCancelledException;
import org.eclipse.ecf.filetransfer.events.IFileTransferEvent;
import org.eclipse.ecf.filetransfer.events.IIncomingFileTransferEvent;
import org.eclipse.ecf.filetransfer.events.IIncomingFileTransferReceiveDataEvent;
import org.eclipse.ecf.filetransfer.events.IIncomingFileTransferReceiveDoneEvent;
import org.eclipse.ecf.filetransfer.events.IIncomingFileTransferReceiveStartEvent;
import org.eclipse.ecf.filetransfer.identity.FileIDFactory;
import org.eclipse.ecf.filetransfer.identity.IFileID;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class FileReader extends FileTransferJob implements IFileTransferListener {
	private boolean closeStreamWhenFinished = false;

	private Exception exception;

	private IFileInfo fileInfo;

	private long lastProgressCount;

	private long lastStatsCount;

	private IProgressMonitor monitor;

	private boolean onlyGetInfo = false;

	private OutputStream outputStream;

	private ProgressStatistics statistics;

	private final int connectionRetryCount;

	private final long connectionRetryDelay;

	private final IConnectContext connectContext;

	/**
	 * Create a new FileReader that will retry failed connection attempts and
	 * sleep some amount of time between each attempt.
	 * 
	 * @param connectionRetryCount
	 *            The number of times to retry the connection. Set to zero to
	 *            fail on first attempt.
	 * @param connectionRetryDelay
	 *            The number of milliseconds to sleep between each attempt.
	 */
	public FileReader(IConnectContext connectContext) {
		super(Messages.URL_reader);

		// Hide this job.
		setSystem(true);
		setUser(false);
		this.connectionRetryCount = BuckminsterPreferences.getConnectionRetryCount();
		this.connectionRetryDelay = BuckminsterPreferences.getConnectionRetryDelay() * 1000L;
		this.connectContext = connectContext;
	}

	public IFileInfo getLastFileInfo() {
		return fileInfo;
	}

	@Override
	public synchronized void handleTransferEvent(IFileTransferEvent event) {
		if (event instanceof IIncomingFileTransferReceiveStartEvent) {
			IIncomingFileTransfer source = ((IIncomingFileTransferEvent) event).getSource();
			try {
				FileInfoBuilder fi = new FileInfoBuilder();
				Date lastModified = source.getRemoteLastModified();
				if (lastModified != null)
					fi.setLastModified(lastModified.getTime());
				fi.setName(source.getRemoteFileName());
				fi.setSize(source.getFileLength());
				fileInfo = fi;

				if (onlyGetInfo) {
					source.cancel();
				} else
					((IIncomingFileTransferReceiveStartEvent) event).receive(outputStream, this);
			} catch (IOException e) {
				exception = e;
				return;
			}

			if (monitor != null) {
				long fileLength = source.getFileLength();
				statistics = new ProgressStatistics(source.getRemoteFileName(), fileLength);
				monitor.beginTask(null, 1000);
				monitor.subTask(statistics.report());
				lastStatsCount = 0;
				lastProgressCount = 0;
			}
		} else if (event instanceof IIncomingFileTransferReceiveDataEvent) {
			IIncomingFileTransfer source = ((IIncomingFileTransferEvent) event).getSource();
			if (monitor != null) {
				if (monitor.isCanceled()) {
					source.cancel();
					return;
				}

				long br = source.getBytesReceived();
				long count = br - lastStatsCount;
				lastStatsCount = br;
				statistics.increase(count);
				if (statistics.shouldReport()) {
					count = br - lastProgressCount;
					lastProgressCount = br;
					monitor.subTask(statistics.report());
					monitor.worked((int) (1000 * count / statistics.getTotal()));
				}
			}
		} else if (event instanceof IIncomingFileTransferReceiveDoneEvent) {
			if (closeStreamWhenFinished)
				IOUtils.close(outputStream);

			if (exception == null) {
				exception = ((IIncomingFileTransferReceiveDoneEvent) event).getException();
				if (exception instanceof UserCancelledException)
					exception = null;
			}
		}
	}

	public InputStream read(URL url) throws CoreException, FileNotFoundException {
		final PipedInputStream input = new PipedInputStream();
		PipedOutputStream output;
		try {
			output = new PipedOutputStream(input);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}
		Buckminster.getLogger().debug("Downloading %s", url); //$NON-NLS-1$

		final IProgressMonitor cancellationMonitor = new NullProgressMonitor();
		sendRetrieveRequest(url, output, true, false, cancellationMonitor);

		return new InputStream() {
			@Override
			public int available() throws IOException {
				checkException();
				return input.available();
			}

			@Override
			public void close() throws IOException {
				cancellationMonitor.setCanceled(true);
				IOUtils.close(input);
				checkException();
			}

			@Override
			public void mark(int readlimit) {
				input.mark(readlimit);
			}

			@Override
			public boolean markSupported() {
				return input.markSupported();
			}

			@Override
			public int read() throws IOException {
				checkException();
				return input.read();
			}

			@Override
			public int read(byte b[]) throws IOException {
				checkException();
				return input.read(b);
			}

			@Override
			public int read(byte b[], int off, int len) throws IOException {
				checkException();
				return input.read(b, off, len);
			}

			@Override
			public void reset() throws IOException {
				checkException();
				input.reset();
			}

			@Override
			public long skip(long n) throws IOException {
				checkException();
				return input.skip(n);
			}

			private void checkException() throws IOException {
				if (exception == null)
					return;

				IOException e;
				Throwable t = BuckminsterException.unwind(exception);
				if (t instanceof IOException)
					e = (IOException) t;
				else {
					e = new IOException(t.getMessage());
					e.initCause(t);
				}
				throw e;
			}
		};
	}

	public IFileInfo readInfo(URL url) throws CoreException, FileNotFoundException {
		sendRetrieveRequest(url, null, false, true, null);
		return getLastFileInfo();
	}

	public void readInto(URL url, OutputStream out, IProgressMonitor mon) throws CoreException, FileNotFoundException {
		try {
			sendRetrieveRequest(url, out, false, false, mon);
			join();
		} catch (InterruptedException e) {
			mon.setCanceled(true);
			throw new OperationCanceledException();
		} finally {
			if (mon != null) {
				if (statistics == null)
					//
					// Monitor was never started. See to that it's balanced
					//
					mon.beginTask(null, 1);
				else
					statistics = null;
				mon.done();
			}
		}
	}

	protected void sendRetrieveRequest(URL url, OutputStream out, boolean closeWhenFinished, boolean onlyInfo, IProgressMonitor mon)
			throws CoreException, FileNotFoundException {
		IRetrieveFileTransferContainerAdapter adapter = Activator.getDefault().createRetrieveFileTransfer();
		adapter.setConnectContextForAuthentication(connectContext);

		exception = null;
		closeStreamWhenFinished = closeWhenFinished;
		onlyGetInfo = onlyInfo;
		fileInfo = null;
		statistics = null;
		lastProgressCount = 0L;
		lastStatsCount = 0L;
		monitor = mon;
		outputStream = out;

		for (int retryCount = 0;;) {
			if (monitor != null && monitor.isCanceled())
				throw new OperationCanceledException();

			try {
				IFileID fileID = FileIDFactory.getDefault().createFileID(adapter.getRetrieveNamespace(), url);
				adapter.sendRetrieveRequest(fileID, this, null);
			} catch (IncomingFileTransferException e) {
				exception = e;
			}

			if (exception != null) {
				Throwable t = BuckminsterException.unwind(exception);
				while (t instanceof CoreException) {
					Throwable t2 = ((CoreException) t).getStatus().getException();
					if (t2 == null)
						throw (CoreException) t;
					t = t2;
				}

				if (t instanceof FileNotFoundException)
					//
					// Connection succeeded but the target doesn't exist
					//
					throw (FileNotFoundException) t;

				if (t instanceof IOException && retryCount < connectionRetryCount) {
					// TODO: Retry only certain exceptions or filter out
					// some exceptions not worth retrying
					//
					++retryCount;
					exception = null;
					try {
						Buckminster.getLogger().warning(
								NLS.bind(Messages.connection_to_0_failed_on_1_retry_attempt_2, new String[] { url.toString(), t.getMessage(),
										String.valueOf(retryCount) }));
						Thread.sleep(connectionRetryDelay);
						continue;
					} catch (InterruptedException e) {
					}
				}
				throw BuckminsterException.wrap(exception);
			}
			break;
		}
	}
}

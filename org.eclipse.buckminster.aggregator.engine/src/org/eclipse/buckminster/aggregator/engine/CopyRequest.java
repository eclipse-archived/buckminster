package org.eclipse.buckminster.aggregator.engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.buckminster.aggregator.AggregatorPlugin;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.artifact.repository.ArtifactRequest;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactDescriptor;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.processing.ProcessingStepHandler;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;

public class CopyRequest extends ArtifactRequest
{
	private final File m_destination;

	public CopyRequest(IArtifactRepository src, IArtifactKey key, File destination)
	{
		super(key);
		setSourceRepository(src);
		m_destination = destination;
	}

	@Override
	public void perform(IProgressMonitor monitor)
	{
		monitor.subTask("Downloading " + getArtifactKey().getId());

		// if the request does not have a descriptor then try to fill one in by getting
		// the list of all and randomly picking one that appears to be optimized.
		IArtifactDescriptor optimized = null;
		IArtifactDescriptor canonical = null;
		IArtifactDescriptor descriptor = null;

		IArtifactDescriptor[] descriptors = source.getArtifactDescriptors(getArtifactKey());
		if(descriptors.length > 0)
		{
			for(int i = 0; i < descriptors.length; i++)
			{
				if(descriptors[i].getProperty(IArtifactDescriptor.FORMAT) == null)
					canonical = descriptors[i];
				else if(ProcessingStepHandler.canProcess(descriptors[i]))
					optimized = descriptors[i];
			}

			boolean chooseCanonical = source.getLocation().equals("file"); //$NON-NLS-1$
			// If the source repo is local then look for a canonical descriptor so we don't waste processing
			// time.
			descriptor = chooseCanonical
					? canonical
					: optimized;

			// if the descriptor is still null then we could not find our first choice of format so switch the
			// logic.
			if(descriptor == null)
				descriptor = !chooseCanonical
						? canonical
						: optimized;
		}

		// if the descriptor is not set now then the repo does not have the requested artifact
		if(descriptor == null)
		{
			setResult(new Status(IStatus.ERROR, AggregatorPlugin.getPluginID(), "Artifact not found: "
					+ getArtifactKey()));
			return;
		}

		IStatus status = transfer(descriptor, monitor);
		if(monitor.isCanceled())
		{
			setResult(Status.CANCEL_STATUS);
			return;
		}

		if(status.isOK() || status.getSeverity() == IStatus.CANCEL)
		{
			setResult(status);
			return;
		}

		if(descriptor == canonical || canonical == null)
		{
			setResult(status);
			return;
		}

		// try with canonical
		setResult(transfer(canonical, monitor));
	}

	private IStatus transfer(IArtifactDescriptor descriptor, IProgressMonitor monitor)
	{
		IStatus status;
		do
		{
			status = transferSingle(descriptor, monitor);
		} while(status.getSeverity() == IStatus.ERROR && status.getCode() == IArtifactRepository.CODE_RETRY);
		return status;
	}

	private IStatus transferSingle(IArtifactDescriptor descriptor, IProgressMonitor monitor)
	{
		OutputStream destination = null;
		IStatus status = null;
		try
		{
			destination = new FileOutputStream(m_destination);
			status = source.getArtifact(descriptor, destination, monitor);
		}
		catch(IOException e)
		{
			status = BuckminsterException.createStatus(e);
		}
		finally
		{
			if(destination != null)
				try
				{
					destination.close();
				}
				catch(IOException e)
				{
					// ignore
				}
		}
		return status;
	}
}

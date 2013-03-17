/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.processing.ProcessingStepHandler;
import org.eclipse.equinox.p2.metadata.IArtifactKey;
import org.eclipse.equinox.p2.metadata.expression.ExpressionUtil;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.IRunnableWithProgress;
import org.eclipse.equinox.p2.repository.artifact.IArtifactDescriptor;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;

/**
 * An action that will remove .jar files for which .jar.pack.gz files exists in
 * the artifact repository maintained by the publisher.
 */
@SuppressWarnings("restriction")
public class RemoveUnpackedSiblingsAction implements IPublisherAction {

	private static IQuery<IArtifactKey> ANY_ARTIFACT = QueryUtil.createMatchQuery(IArtifactKey.class, ExpressionUtil.TRUE_EXPRESSION);

	private static boolean isPacked(IArtifactDescriptor desc) {
		return desc != null && "packed".equals(desc.getProperty(IArtifactDescriptor.FORMAT)) //$NON-NLS-1$
				&& ProcessingStepHandler.canProcess(desc);
	}

	@Override
	public IStatus perform(IPublisherInfo info, IPublisherResult results, IProgressMonitor monitor) {
		final IArtifactRepository artifacts = info.getArtifactRepository();
		if (artifacts == null) {
			MonitorUtils.complete(monitor);
			return Status.OK_STATUS;
		}

		SubMonitor mon = SubMonitor.convert(monitor, IProgressMonitor.UNKNOWN);
		final List<IArtifactDescriptor> descsToRemove = new ArrayList<IArtifactDescriptor>();
		for (Iterator<IArtifactKey> iter = artifacts.query(ANY_ARTIFACT, mon.newChild(1)).iterator(); iter.hasNext();) {
			IArtifactDescriptor optimized = null;
			IArtifactDescriptor canonical = null;
			for (IArtifactDescriptor desc : artifacts.getArtifactDescriptors(iter.next())) {
				if (isPacked(desc))
					optimized = desc;
				else
					canonical = desc;
			}
			// If we have a packed artifact, then remove the unpacked one.
			if (optimized != null && canonical != null)
				descsToRemove.add(canonical);
		}
		if (descsToRemove.size() > 0) {
			artifacts.executeBatch(new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor p) throws InvocationTargetException, OperationCanceledException {
					SubMonitor sp = SubMonitor.convert(p, descsToRemove.size());
					for (IArtifactDescriptor descToRemove : descsToRemove)
						artifacts.removeDescriptor(descToRemove, sp.newChild(1));
				}
			}, mon.newChild(1));
		}
		MonitorUtils.done(monitor);
		return Status.OK_STATUS;
	}
}

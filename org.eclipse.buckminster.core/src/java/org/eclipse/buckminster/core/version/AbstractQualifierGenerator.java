/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.core.version;

import java.net.URI;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.osgi.util.NLS;

public abstract class AbstractQualifierGenerator extends AbstractExtension implements IQualifierGenerator {
	protected IInstallableUnit obtainFromReferenceRepo(IComponentIdentifier identifier, IProgressMonitor monitor) throws CoreException {
		MonitorUtils.begin(monitor, 2000);
		try {
			IActionContext ctx = AbstractActor.getActiveContext();
			if (ctx == null)
				return null;

			Map<String, ? extends Object> props = ctx.getProperties();
			IMetadataRepository repo = getReferenceRepository(props, MonitorUtils.subMonitor(monitor, 1990));
			if (repo == null)
				return null;

			String id = identifier.getName();
			if (IComponentType.ECLIPSE_FEATURE.equals(identifier.getComponentTypeID()))
				id += ".feature.group"; //$NON-NLS-1$

			VersionRange range = VersionHelper.unqualifiedRange(identifier.getVersion());
			if (range == null)
				return null;

			Iterator<IInstallableUnit> iter = repo.query(QueryUtil.createLatestQuery(QueryUtil.createIUQuery(id, range)),
					MonitorUtils.subMonitor(monitor, 10)).iterator();
			return iter.hasNext() ? iter.next() : null;
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	synchronized IMetadataRepository getReferenceRepository(Map<String, ? extends Object> props, IProgressMonitor monitor) throws CoreException {

		Object refURIVal = props.get(KeyConstants.REFERENCE_REPOSITORY);
		if (!(refURIVal instanceof String)) {
			MonitorUtils.complete(monitor);
			return null;
		}

		String expanded = ExpandingProperties.expand(props, (String) refURIVal, 0);
		URI refURI = URLUtils.normalizeToURI(expanded, true);
		IProvisioningAgent p2Agent = CorePlugin.getDefault().getResolverAgent();
		IMetadataRepositoryManager mdrManager = (IMetadataRepositoryManager) p2Agent.getService(IMetadataRepositoryManager.SERVICE_NAME);
		try {
			return mdrManager.loadRepository(refURI, monitor);
		} catch (ProvisionException e) {
			CorePlugin.getLogger().error(NLS.bind(Messages.Unable_to_load_reference_repo_0, refURI.toString()));
			throw e;
		}
	}
}

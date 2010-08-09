package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.P2Constants;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;

public class P2VersionFinder extends AbstractVersionFinder {
	private final IMetadataRepository mdr;

	public P2VersionFinder(Provider provider, IComponentType componentType, NodeQuery query, IMetadataRepository mdr) {
		super(provider, componentType, query);
		this.mdr = mdr;
	}

	@Override
	public VersionMatch getBestVersion(IProgressMonitor monitor) throws CoreException {
		ComponentRequest request = getQuery().getComponentRequest();
		String ctype = request.getType();
		boolean isFeature = (ctype != null && ctype.equals(IComponentType.ECLIPSE_FEATURE));

		String name = request.getId();
		if (isFeature) {
			if (!name.endsWith(P2Constants.FEATURE_GROUP))
				name += P2Constants.FEATURE_GROUP;
		}

		VersionRange range = request.getRange();
		IQuery<IInstallableUnit> query = (range == null || range.equals(VersionRange.emptyRange)) //
				? QueryUtil.createQuery("select(x | x.id == $0).latest()", name) //$NON-NLS-1$
				: QueryUtil.createQuery("select(x | x.id == $0 && x.version ~= $1).latest()", name, range); //$NON-NLS-1$
		IQueryResult<IInstallableUnit> result = mdr.query(query, monitor);
		if (result.isEmpty())
			return null;

		IInstallableUnit best = result.iterator().next();
		return new VersionMatch(best.getVersion(), null, -1, null, best.getId());
	}
}

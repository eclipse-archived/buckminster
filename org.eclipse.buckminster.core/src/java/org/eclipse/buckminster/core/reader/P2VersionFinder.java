package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.P2Constants;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.metadata.query.ExpressionContextQuery;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
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
		IComponentType ctype = request.getComponentType();
		boolean isFeature = (ctype != null && ctype.getId().equals(IComponentType.ECLIPSE_FEATURE));

		String name = request.getName();
		if (isFeature) {
			if (!name.endsWith(P2Constants.FEATURE_GROUP))
				name += P2Constants.FEATURE_GROUP;
		}

		VersionRange range = request.getVersionRange();
		IQuery<IInstallableUnit> query = (range == null || range.equals(VersionRange.emptyRange)) ? new ExpressionContextQuery<IInstallableUnit>(
				IInstallableUnit.class, "select(x | x.id == $0).latest()", name) //$NON-NLS-1$
				: new ExpressionContextQuery<IInstallableUnit>(IInstallableUnit.class,
						"select(x | x.id == $0 && x.version ~= $1).latest()", name, range); //$NON-NLS-1$
		IQueryResult<IInstallableUnit> result = mdr.query(query, monitor);
		if (result.isEmpty())
			return null;

		IInstallableUnit best = result.iterator().next();
		return new VersionMatch(best.getVersion(), null, -1, null, best.getId());
	}
}

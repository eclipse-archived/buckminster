package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.equinox.internal.p2.metadata.VersionedId;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.internal.provisional.frameworkadmin.BundleInfo;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.IQueryable;

@SuppressWarnings("restriction")
public class ProductVersionPatcher implements IProductDescriptor {
	private final IProductDescriptor product;

	private IQueryable<IInstallableUnit> mdr;

	public ProductVersionPatcher(IProductDescriptor product) {
		this.product = product;
	}

	public String getApplication() {
		return product.getApplication();
	}

	public List<BundleInfo> getBundleInfos() {
		List<BundleInfo> bis = product.getBundleInfos();
		if (bis.size() == 0)
			return bis;

		List<BundleInfo> pbis = new ArrayList<BundleInfo>(bis.size());
		for (BundleInfo bi : bis) {
			BundleInfo pbi = new BundleInfo(bi.getLocation(), bi.getStartLevel(), bi.isMarkedAsStarted());
			pbi.setBaseLocation(bi.getBaseLocation());
			pbi.setBundleId(bi.getBundleId());
			pbi.setManifest(bi.getManifest());
			pbi.setResolved(bi.isResolved());

			String id = bi.getSymbolicName();
			pbi.setSymbolicName(id);
			Version v = adjustVersion(id, bi.getVersion(), false);
			if (v != null)
				pbi.setVersion(v.toString());

			pbis.add(pbi);
		}
		return pbis;
	}

	public List<IVersionedId> getBundles(boolean includeFragments) {
		return adjustVersionedIdList(product.getBundles(includeFragments), false);
	}

	public String getConfigIniPath(String os) {
		return product.getConfigIniPath(os);
	}

	public Map<String, String> getConfigurationProperties() {
		return product.getConfigurationProperties();
	}

	public List<IVersionedId> getFeatures() {
		return adjustVersionedIdList(product.getFeatures(), true);
	}

	public List<IVersionedId> getFragments() {
		return adjustVersionedIdList(product.getFragments(), false);
	}

	public String[] getIcons(String os) {
		return product.getIcons(os);
	}

	public String getId() {
		return product.getId();
	}

	public String getLauncherName() {
		return product.getLauncherName();
	}

	public String getLicenseText() {
		return product.getLicenseText();
	}

	public String getLicenseURL() {
		return product.getLicenseURL();
	}

	public File getLocation() {
		return product.getLocation();
	}

	public String getProductId() {
		return product.getProductId();
	}

	public String getProductName() {
		return product.getProductName();
	}

	public String getProgramArguments(String os) {
		return product.getProgramArguments(os);
	}

	public String getSplashLocation() {
		return product.getSplashLocation();
	}

	public String getVersion() {
		String vstr = product.getVersion();
		Version version = vstr == null ? null : Version.parseVersion(vstr);
		if (Version.emptyVersion.equals(version))
			version = null;

		if (version != null) {
			String qualifier = VersionHelper.getQualifier(version);
			if (qualifier == null || !qualifier.endsWith("qualifier")) //$NON-NLS-1$
				return vstr;
		}

		boolean features = product.useFeatures();
		List<IVersionedId> deps = features ? product.getFeatures() : product.getBundles(false);

		if (deps.size() == 1) {
			IVersionedId dep = deps.get(0);
			version = adjustVersion(dep.getId(), dep.getVersion(), features);
			if (version != null)
				vstr = version.toString();
		}
		return vstr;
	}

	public String getVMArguments(String os) {
		return product.getVMArguments(os);
	}

	public boolean useFeatures() {
		return product.useFeatures();
	}

	void setQueryable(IQueryable<IInstallableUnit> queryable) {
		mdr = queryable;
	}

	private Version adjustVersion(String id, String version, boolean feature) {
		return adjustVersion(id, version == null ? null : Version.parseVersion(version), feature);
	}

	private Version adjustVersion(String id, Version version, boolean isFeature) {
		VersionRange range = null;
		if (version != null && Version.emptyVersion.equals(version))
			version = null;

		if (version != null) {
			String qualifier = VersionHelper.getQualifier(version);
			if (qualifier == null || !qualifier.endsWith("qualifier")) //$NON-NLS-1$
				return version;

			org.osgi.framework.Version ov = Version.toOSGiVersion(version);
			if (qualifier.length() > 9) {
				String lowQual = qualifier.substring(0, qualifier.length() - 1);
				String highQual = lowQual + "zzzzzzzzzzzzzzzz"; //$NON-NLS-1$
				Version low = Version.createOSGi(ov.getMajor(), ov.getMinor(), ov.getMicro(), lowQual);
				Version high = Version.createOSGi(ov.getMajor(), ov.getMinor(), ov.getMicro(), highQual);
				range = new VersionRange(low, true, high, true);
			} else {
				Version low = Version.createOSGi(ov.getMajor(), ov.getMinor(), ov.getMicro());
				Version high = Version.createOSGi(ov.getMajor(), ov.getMinor(), ov.getMicro() + 1);
				range = new VersionRange(low, true, high, false);
			}
		}

		String iuID = id;
		if (isFeature && !iuID.endsWith(IPDEConstants.FEATURE_GROUP))
			iuID += IPDEConstants.FEATURE_GROUP;

		InstallableUnitQuery query = new InstallableUnitQuery(iuID, range);
		IQueryResult<IInstallableUnit> result = mdr.query(query, null);
		if (result.isEmpty())
			return version;

		Version candidate = null;
		Iterator<IInstallableUnit> itor = result.iterator();
		while (itor.hasNext()) {
			Version v = itor.next().getVersion();
			if (candidate == null || v.compareTo(candidate) > 0)
				candidate = v;
		}
		return candidate;
	}

	private List<IVersionedId> adjustVersionedIdList(List<IVersionedId> vns, boolean features) {
		int top = vns.size();
		if (top == 0)
			return vns;

		ArrayList<IVersionedId> pvns = new ArrayList<IVersionedId>(top);
		for (IVersionedId vn : vns) {
			String id = vn.getId();
			pvns.add(new VersionedId(id, adjustVersion(id, vn.getVersion(), features)));
		}
		return pvns;
	}
}

package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.eclipse.buckminster.pde.internal.TypedCollections;
import org.eclipse.equinox.internal.p2.publisher.VersionedName;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.internal.provisional.frameworkadmin.BundleInfo;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.IQueryable;

@SuppressWarnings("restriction")
public class ProductVersionPatcher implements IProductDescriptor
{
	private final IProductDescriptor m_product;

	private IQueryable m_mdr;

	public ProductVersionPatcher(IProductDescriptor product)
	{
		m_product = product;
	}

	public List<?> getBundleInfos()
	{
		List<BundleInfo> bis = TypedCollections.getBundleInfos(m_product);
		if(bis.size() == 0)
			return bis;

		List<BundleInfo> pbis = new ArrayList<BundleInfo>(bis.size());
		for(BundleInfo bi : bis)
		{
			BundleInfo pbi = new BundleInfo(bi.getLocation(), bi.getStartLevel(), bi.isMarkedAsStarted());
			pbi.setBaseLocation(bi.getBaseLocation());
			pbi.setBundleId(bi.getBundleId());
			pbi.setManifest(bi.getManifest());
			pbi.setResolved(bi.isResolved());

			String id = bi.getSymbolicName();
			pbi.setSymbolicName(id);
			Version v = adjustVersion(id, bi.getVersion(), false);
			if(v != null)
				pbi.setVersion(v.toString());

			pbis.add(pbi);
		}
		return pbis;
	}

	public List<?> getBundles(boolean includeFragments)
	{
		return adjustVersionedNameList(TypedCollections.getProductBundles(m_product, includeFragments), false);
	}

	public String getConfigIniPath(String os)
	{
		return m_product.getConfigIniPath(os);
	}

	public Properties getConfigurationProperties()
	{
		return m_product.getConfigurationProperties();
	}

	public List<?> getFeatures()
	{
		return adjustVersionedNameList(TypedCollections.getProductFeatures(m_product), true);
	}

	public List<?> getFragments()
	{
		return adjustVersionedNameList(TypedCollections.getProductFragments(m_product), false);
	}

	public String[] getIcons(String os)
	{
		return m_product.getIcons(os);
	}

	public String getId()
	{
		return m_product.getId();
	}

	public String getLauncherName()
	{
		return m_product.getLauncherName();
	}

	public File getLocation()
	{
		return m_product.getLocation();
	}

	public String getProductName()
	{
		return m_product.getProductName();
	}

	public String getProgramArguments(String os)
	{
		return m_product.getProgramArguments(os);
	}

	public String getSplashLocation()
	{
		return m_product.getSplashLocation();
	}

	public String getVersion()
	{
		String vstr = m_product.getVersion();
		Version version = vstr == null
				? null
				: Version.parseVersion(vstr);
		if(Version.emptyVersion.equals(version))
			version = null;

		if(version != null)
		{
			String qualifier = version.getQualifier();
			if(qualifier == null || !qualifier.endsWith("qualifier")) //$NON-NLS-1$
				return vstr;
		}

		boolean features = m_product.useFeatures();
		List<VersionedName> deps = features
				? TypedCollections.getProductFeatures(m_product)
				: TypedCollections.getProductBundles(m_product, false);

		if(deps.size() == 1)
		{
			VersionedName dep = deps.get(0);
			version = adjustVersion(dep.getId(), dep.getVersion(), features);
			if(version != null)
				vstr = version.toString();
		}
		return vstr;
	}

	public String getVMArguments(String os)
	{
		return m_product.getVMArguments(os);
	}

	public boolean useFeatures()
	{
		return m_product.useFeatures();
	}

	void setQueryable(IQueryable queryable)
	{
		m_mdr = queryable;
	}

	private Version adjustVersion(String id, String version, boolean feature)
	{
		return adjustVersion(id, version == null
				? null
				: Version.parseVersion(version), feature);
	}

	private Version adjustVersion(String id, Version version, boolean isFeature)
	{
		VersionRange range = null;
		if(version != null && Version.emptyVersion.equals(version))
			version = null;

		if(version != null)
		{
			String qualifier = version.getQualifier();
			if(qualifier == null || !qualifier.endsWith("qualifier")) //$NON-NLS-1$
				return version;

			if(qualifier.length() > 9)
			{
				String lowQual = qualifier.substring(0, qualifier.length() - 1);
				String highQual = lowQual + "zzzzzzzzzzzzzzzz"; //$NON-NLS-1$
				Version low = new Version(version.getMajor(), version.getMinor(), version.getMicro(), lowQual);
				Version high = new Version(version.getMajor(), version.getMinor(), version.getMicro(), highQual);
				range = new VersionRange(low, true, high, true);
			}
			else
			{
				Version low = new Version(version.getMajor(), version.getMinor(), version.getMicro());
				Version high = new Version(version.getMajor(), version.getMinor(), version.getMicro() + 1);
				range = new VersionRange(low, true, high, false);
			}
		}

		String iuID = id;
		if(isFeature && !iuID.endsWith(".feature.group"))
			iuID += ".feature.group";

		InstallableUnitQuery query = new InstallableUnitQuery(iuID, range);
		Collector result = m_mdr.query(query, new Collector(), null);
		if(result.isEmpty())
			return version;

		Version candidate = null;
		Iterator<IInstallableUnit> itor = TypedCollections.iterator(result);
		while(itor.hasNext())
		{
			Version v = itor.next().getVersion();
			if(candidate == null || v.compareTo(candidate) > 0)
				candidate = v;
		}
		return candidate;
	}

	private List<?> adjustVersionedNameList(List<VersionedName> vns, boolean features)
	{
		int top = vns.size();
		if(top == 0)
			return vns;

		ArrayList<VersionedName> pvns = new ArrayList<VersionedName>(top);
		for(VersionedName vn : vns)
		{
			String id = vn.getId();
			pvns.add(new VersionedName(id, adjustVersion(id, vn.getVersion(), features)));
		}
		return pvns;
	}
}

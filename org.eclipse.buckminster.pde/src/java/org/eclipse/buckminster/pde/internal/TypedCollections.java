package org.eclipse.buckminster.pde.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.equinox.internal.p2.publisher.eclipse.FeatureManifestParser;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.internal.provisional.frameworkadmin.BundleInfo;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionedName;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.p2.publisher.IPublisherAdvice;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.pde.internal.build.Utils;

@SuppressWarnings("restriction")
public abstract class TypedCollections
{
	@SuppressWarnings("unchecked")
	public static <T extends IPublisherAdvice> Collection<T> getAdvice(IPublisherInfo info, String configSpec,
			boolean includeDefault, String id, Version version, Class<T> type)
	{
		return info.getAdvice(configSpec, includeDefault, id, version, type);
	}

	@SuppressWarnings("unchecked")
	public static List<BundleInfo> getBundleInfos(IProductDescriptor product)
	{
		return product.getBundleInfos();
	}

	@SuppressWarnings("unchecked")
	public static List<String> getMessageKeys(FeatureManifestParser parser)
	{
		return parser.getMessageKeys();
	}

	@SuppressWarnings("unchecked")
	public static List<VersionedName> getProductBundles(IProductDescriptor productDescriptor, boolean includeFragments)
	{
		return productDescriptor.getBundles(includeFragments);
	}

	@SuppressWarnings("unchecked")
	public static List<VersionedName> getProductFeatures(IProductDescriptor productDescriptor)
	{
		return productDescriptor.getFeatures();
	}

	@SuppressWarnings("unchecked")
	public static List<VersionedName> getProductFragments(IProductDescriptor productDescriptor)
	{
		return productDescriptor.getFragments();
	}

	@SuppressWarnings("unchecked")
	public static Iterator<IInstallableUnit> iterator(Collector result)
	{
		return result.iterator();
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Map<String, String>> processRootProperties(Properties buildProperties, boolean mergeCommon)
	{
		return Utils.processRootProperties(buildProperties, mergeCommon);
	}
}

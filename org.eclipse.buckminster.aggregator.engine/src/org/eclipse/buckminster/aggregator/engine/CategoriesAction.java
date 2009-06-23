/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.aggregator.engine;

import java.util.ArrayList;

import org.eclipse.buckminster.aggregator.CustomCategory;
import org.eclipse.buckminster.aggregator.Feature;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.equinox.p2.publisher.AbstractPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;
import org.eclipse.equinox.spi.p2.publisher.PublisherHelper;

public class CategoriesAction extends AbstractPublisherAction
{
	private final Builder builder;

	public CategoriesAction(Builder builder)
	{
		this.builder = builder;
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor)
	{
		for(CustomCategory category : builder.getAggregator().getCustomCategories())
			results.addIU(createCategoryIU(category, null), IPublisherResult.NON_ROOT);
		return Status.OK_STATUS;
	}

	private IInstallableUnit createCategoryIU(CustomCategory category, IInstallableUnit parentCategory)
	{
		InstallableUnitDescription cat = new MetadataFactory.InstallableUnitDescription();
		cat.setSingleton(true);
		String categoryId = category.getIdentifier();
		cat.setId(categoryId);
		cat.setVersion(Version.emptyVersion);
		cat.setProperty(IInstallableUnit.PROP_NAME, category.getLabel());
		cat.setProperty(IInstallableUnit.PROP_DESCRIPTION, category.getDescription());

		ArrayList<IRequiredCapability> rcs = new ArrayList<IRequiredCapability>();
		for(Feature feature : category.getFeatures())
		{
			if(!feature.isEnabled())
				continue;

			InstallableUnit iu = feature.getInstallableUnit();
			VersionRange range = new VersionRange(iu.getVersion(), true, iu.getVersion(), true);
			rcs.add(MetadataFactory.createRequiredCapability(IInstallableUnit.NAMESPACE_IU_ID, iu.getId(), range,
					iu.getFilter(), false, false));
		}
		cat.setRequiredCapabilities(rcs.toArray(new IRequiredCapability[rcs.size()]));
		cat.setCapabilities(new IProvidedCapability[] { PublisherHelper.createSelfCapability(categoryId,
				Version.emptyVersion) });
		cat.setArtifacts(new IArtifactKey[0]);
		cat.setProperty(IInstallableUnit.PROP_TYPE_CATEGORY, "true"); //$NON-NLS-1$
		return MetadataFactory.createInstallableUnit(cat);
	}
}

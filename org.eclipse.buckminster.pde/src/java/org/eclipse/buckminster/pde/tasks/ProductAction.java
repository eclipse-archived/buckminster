/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.internal.provisional.frameworkadmin.BundleInfo;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;
import org.eclipse.equinox.p2.publisher.PublisherResult;
import org.eclipse.equinox.p2.publisher.eclipse.EquinoxLauncherCUAction;

/**
 * Action that generates version adjusted products
 */
@SuppressWarnings("restriction")
public class ProductAction extends org.eclipse.equinox.p2.publisher.eclipse.ProductAction
{
	public ProductAction(String src, IProductDescriptor productDesc, String flvor, File exeFeatureLocation)
	{
		super(src, new ProductVersionPatcher(productDesc), flvor, exeFeatureLocation);
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor)
	{
		((ProductVersionPatcher)product).setQueryable(results);

		IPublisherResult innerResult = new PublisherResult();

		// The inner result must see the launchers since the EquinoxLauncherCUAction created by the
		// ApplicationLauncherAction must find them in order to generate the correct CU's
		//
		for(Object iu : results.getIUs(null, IPublisherResult.ROOT))
		{
			IInstallableUnit tmp = (IInstallableUnit)iu;
			if(tmp.getId().startsWith(EquinoxLauncherCUAction.ORG_ECLIPSE_EQUINOX_LAUNCHER))
			{
				innerResult.addIU(tmp, IPublisherResult.ROOT);
				continue;
			}

			for(Object bi : product.getBundleInfos())
			{
				if(tmp.getId().equals(((BundleInfo)bi).getSymbolicName()))
				{
					innerResult.addIU(tmp, IPublisherResult.ROOT);
					break;
				}
			}
		}

		IStatus status = super.perform(publisherInfo, innerResult, monitor);
		if(status.getSeverity() != IStatus.ERROR)
			results.merge(innerResult, IPublisherResult.MERGE_MATCHING);
		return status;
	}
}

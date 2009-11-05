package org.eclipse.buckminster.aggregator.provider;

import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.buckminster.aggregator.util.OverlaidImage;

public class AggregatorItemProviderAdapter extends ItemProviderAdapter
{
	public AggregatorItemProviderAdapter(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
	}

	@Override
	protected Object overlayImage(Object object, Object image)
	{
		image = super.overlayImage(object, image);

		if(object instanceof StatusProvider)
		{
			StatusProvider status = ((StatusProvider)object);
			if(status.getStatus() == StatusProvider.BROKEN_CHILD || status.getStatus() == StatusProvider.WAITING)
			{
				Object[] images = new Object[2];
				int[] positions = new int[2];

				images[0] = image;
				positions[0] = OverlaidImage.BASIC;

				if(status.getStatus() == StatusProvider.WAITING)
				{
					images[1] = getResourceLocator().getImage("full/ovr16/Loading");
				}
				else
				{
					images[1] = getResourceLocator().getImage("full/ovr16/Error");
				}
				positions[1] = OverlaidImage.OVERLAY_BOTTOM_RIGHT;

				image = new OverlaidImage(images, positions);
			}
		}

		return image;
	}

}

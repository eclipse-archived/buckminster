package org.eclipse.buckminster.aggregator.provider;

import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.buckminster.aggregator.util.OverlaidImage;
import org.eclipse.buckminster.runtime.Trivial;

public class AggregatorItemProviderAdapter extends ItemProviderAdapter
{
	static class AggregatorItemPropertyDescriptor extends ItemPropertyDescriptor
	{
		public AggregatorItemPropertyDescriptor(AdapterFactory adapterFactory, ResourceLocator resourceLocator,
				String displayName, String description, EStructuralFeature feature, boolean isSettable,
				boolean multiLine, boolean sortChoices, Object staticImage, String category, String[] filterFlags)

		{
			super(adapterFactory, resourceLocator, displayName, description, feature, isSettable, multiLine,
					sortChoices, staticImage, category, filterFlags);
		}

		public void setPropertyValue(Object object, Object value)
		{
			// Replaces empty string with null
			if(value instanceof String)
				value = Trivial.trim((String)value);

			super.setPropertyValue(object, value);
		}
	}

	public AggregatorItemProviderAdapter(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
	}

	@Override
	protected ItemPropertyDescriptor createItemPropertyDescriptor(AdapterFactory adapterFactory,
			ResourceLocator resourceLocator, String displayName, String description, EStructuralFeature feature,
			boolean isSettable, boolean multiLine, boolean sortChoices, Object staticImage, String category,
			String[] filterFlags)
	{
		return new AggregatorItemPropertyDescriptor(adapterFactory, resourceLocator, displayName, description, feature,
				isSettable, multiLine, sortChoices, staticImage, category, filterFlags);
	}

	@Override
	protected Object overlayImage(Object object, Object image)
	{
		image = super.overlayImage(object, image);

		StatusProvider sp = (StatusProvider)getRootAdapterFactory().adapt(object, StatusProvider.class);

		if(sp != null)
		{
			if(sp.getStatus() == StatusProvider.BROKEN_CHILD || sp.getStatus() == StatusProvider.WAITING)
			{
				Object[] images = new Object[2];
				int[] positions = new int[2];

				images[0] = image;
				positions[0] = OverlaidImage.BASIC;

				if(sp.getStatus() == StatusProvider.WAITING)
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

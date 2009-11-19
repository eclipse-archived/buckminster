package org.eclipse.buckminster.aggregator.provider;

import org.eclipse.buckminster.aggregator.Status;
import org.eclipse.buckminster.aggregator.StatusCode;
import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.buckminster.aggregator.util.OverlaidImage;
import org.eclipse.buckminster.runtime.Trivial;

public class AggregatorItemProviderAdapter extends ItemProviderAdapter implements TooltipTextProvider
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

	// default implementation
	public String getTooltipText(Object object)
	{
		if(!(object instanceof StatusProvider))
			return null;

		Status status = ((StatusProvider)object).getStatus();

		if(status.getMessage() == null)
			return null;

		StringBuilder sb = new StringBuilder();

		sb.append(getString("_UI_Structured_Tooltip_Label"));
		sb.append(" ");
		sb.append(getText(object));
		sb.append("\n");
		sb.append(getString("_UI_Structured_Tooltip_ErrorMessage"));
		sb.append(" ");
		sb.append(status.getMessage());

		return sb.toString();
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
			StatusCode sc = sp.getStatus().getCode();

			if(sc == StatusCode.BROKEN || sc == StatusCode.WAITING)
			{
				Object[] images = new Object[2];
				int[] positions = new int[2];

				images[0] = image;
				positions[0] = OverlaidImage.BASIC;

				if(sc == StatusCode.WAITING)
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

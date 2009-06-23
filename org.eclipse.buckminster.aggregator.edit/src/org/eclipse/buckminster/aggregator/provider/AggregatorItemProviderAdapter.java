package org.eclipse.buckminster.aggregator.provider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

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

		if(object instanceof StatusProvider && ((StatusProvider)object).getStatus() == StatusProvider.BROKEN_CHILD)
		{
			try
			{
				List<Object> images = new ArrayList<Object>(2);
				images.add(image);
				images.add(new URL(URI.createPlatformPluginURI("/org.eclipse.update.ui/icons/ovr16/warning_co.gif",
						false).toString()));

				// TODO implement proper ComposedImage class
				image = new ComposedImage(images)
				{
					public List<Point> getDrawPoints(Size size)
					{
						List<Point> results = new ArrayList<Point>();
						Point point = new Point();
						point.x = 0;
						point.y = 0;
						results.add(point);
						point = new Point();
						point.x = 8;
						point.y = 8;
						results.add(point);
						return results;
					}
				};
			}
			catch(MalformedURLException e)
			{
				// the original image will be used
			}
		}

		return image;
	}

}

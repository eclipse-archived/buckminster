package org.eclipse.buckminster.aggregator.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

public class AggregatorItemProviderAdapter extends ItemProviderAdapter
{
	class OverlaidImage extends ComposedImage
	{
		public static final int BASIC = 0;

		public static final int OVERLAY_TOP_LEFT = 1;

		public static final int OVERLAY_TOP_RIGHT = 2;

		public static final int OVERLAY_BOTTOM_LEFT = 3;

		public static final int OVERLAY_BOTTOM_RIGHT = 4;

		public static final int OVERLAY_CENTER = 5;

		private int[] m_positions;

		public OverlaidImage(Object[] images, int[] positions)
		{
			super(Arrays.asList(images));
			m_positions = positions;
		}

		public List<Point> getDrawPoints(Size size)
		{
			List<Point> results = new ArrayList<Point>();

			int i = 0;
			for(Size imageSize : imageSizes)
			{
				Point point = new Point();

				if(i < m_positions.length)
					switch(m_positions[i])
					{
					case OVERLAY_TOP_RIGHT:
						point.x = size.width - imageSize.width;
						point.y = 0;
						break;
					case OVERLAY_BOTTOM_LEFT:
						point.x = 0;
						point.y = size.height - imageSize.height;
						break;
					case OVERLAY_BOTTOM_RIGHT:
						point.x = size.width - imageSize.width;
						point.y = size.height - imageSize.height;
						break;
					case OVERLAY_CENTER:
						point.x = (size.width - imageSize.width) / 2;
						point.y = (size.height - imageSize.height) / 2;
						break;
					default:
						point.x = 0;
						point.y = 0;
					}

				results.add(point);

				i++;
			}

			return results;
		}
	}

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
			Object[] images = new Object[2];
			int[] positions = new int[2];

			images[0] = image;
			positions[0] = OverlaidImage.BASIC;

			images[1] = getResourceLocator().getImage("full/ovr16/Error");
			positions[1] = OverlaidImage.OVERLAY_BOTTOM_RIGHT;

			image = new OverlaidImage(images, positions);
		}

		return image;
	}

}

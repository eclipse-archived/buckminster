/**
 * 
 */
package org.eclipse.buckminster.aggregator.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.edit.provider.ComposedImage;

/**
 * @author Antonin Slezacek, slezicz@gmail.com
 * 
 */
public class OverlaidImage extends ComposedImage
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

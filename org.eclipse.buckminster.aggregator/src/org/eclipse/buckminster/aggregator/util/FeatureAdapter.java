package org.eclipse.buckminster.aggregator.util;

import org.eclipse.buckminster.aggregator.CustomCategory;
import org.eclipse.buckminster.aggregator.Feature;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;


/**
 * @author Karel Brezina
 *
 */
public class FeatureAdapter  extends AdapterImpl
{
	public boolean isAdapterForType(Object type)
	{
		return (type instanceof Feature);
	}

	public void notifyChanged(Notification notification)
	{
		if(!(notification.getNotifier() instanceof Feature))
			return;
		
		Feature feature = (Feature)notification.getNotifier();

		if(notification.getEventType() == Notification.REMOVE && notification.getOldValue() instanceof CustomCategory)
		{
			CustomCategory customCategory = (CustomCategory)notification.getOldValue();

			if(customCategory.getFeatures().contains(feature))
				customCategory.getFeatures().remove(feature);
		}
		else if(notification.getEventType() == Notification.ADD && notification.getNewValue() instanceof CustomCategory)
		{
			CustomCategory customCategory = (CustomCategory)notification.getNewValue();

			if(!customCategory.getFeatures().contains(feature))
				customCategory.getFeatures().add(feature);
		}
	}
}

package org.eclipse.buckminster.aggregator.util;

import org.eclipse.buckminster.aggregator.CustomCategory;
import org.eclipse.buckminster.aggregator.Feature;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

/**
 * @author Karel Brezina
 * 
 */
public class CustomCategoryAdapter extends AdapterImpl
{
	public boolean isAdapterForType(Object type)
	{
		return (type instanceof CustomCategory);
	}

	public void notifyChanged(Notification notification)
	{
		if(!(notification.getNotifier() instanceof CustomCategory))
			return;
		
		CustomCategory customCategory = (CustomCategory)notification.getNotifier();

		if(notification.getEventType() == Notification.REMOVE && notification.getOldValue() instanceof Feature)
		{
			Feature feature = (Feature)notification.getOldValue();

			if(feature.getCategory().contains(customCategory))
				feature.getCategory().remove(customCategory);
		}
		else if(notification.getEventType() == Notification.ADD && notification.getNewValue() instanceof Feature)
		{
			Feature feature = (Feature)notification.getNewValue();

			if(!feature.getCategory().contains(customCategory))
				feature.getCategory().add(customCategory);
		}
	}
}

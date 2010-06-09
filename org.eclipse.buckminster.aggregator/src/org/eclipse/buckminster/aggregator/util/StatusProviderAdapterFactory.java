/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import org.eclipse.buckminster.aggregator.Status;
import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * @author Karel Brezina
 * 
 */
public class StatusProviderAdapterFactory extends AdapterFactoryImpl
{
	static class StatusProviderAdapter extends AdapterImpl implements StatusProvider
	{
		public Status getStatus()
		{
			synchronized(getTarget())
			{
				if(getTarget() instanceof StatusProvider)
					return ((StatusProvider)getTarget()).getStatus();
				if(getTarget() instanceof InstallableUnit)
					return InstallableUnitUtils.getStatus((InstallableUnit)getTarget());
			}

			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isAdapterForType(Object type)
		{
			return type == StatusProvider.class;
		}
	}

	public boolean isFactoryForType(Object type)
	{
		if(type instanceof EPackageImpl)
			return true;

		return type == StatusProvider.class;
	}

	protected Adapter createAdapter(Notifier target)
	{
		return (target instanceof StatusProvider || target instanceof InstallableUnit)
				? new StatusProviderAdapter()
				: null;
	}
}

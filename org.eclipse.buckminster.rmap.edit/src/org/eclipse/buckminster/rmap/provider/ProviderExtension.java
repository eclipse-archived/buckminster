package org.eclipse.buckminster.rmap.provider;

import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.emf.common.notify.AdapterFactory;

public interface ProviderExtension
{
	AdapterFactory createItemProviderAdapterFactory();

	Provider createProvider();
}

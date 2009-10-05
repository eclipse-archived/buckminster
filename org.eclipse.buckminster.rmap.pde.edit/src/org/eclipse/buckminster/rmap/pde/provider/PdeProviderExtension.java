package org.eclipse.buckminster.rmap.pde.provider;

import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.pde.PdeFactory;
import org.eclipse.buckminster.rmap.provider.ProviderExtension;
import org.eclipse.emf.common.notify.AdapterFactory;

public class PdeProviderExtension implements ProviderExtension
{

	public AdapterFactory createItemProviderAdapterFactory()
	{
		return new PdeItemProviderAdapterFactory();
	}

	public Provider createProvider()
	{
		return PdeFactory.eINSTANCE.createPDEMapProvider();
	}

}

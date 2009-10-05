package org.eclipse.buckminster.rmap.maven.provider;

import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.maven.MavenFactory;
import org.eclipse.buckminster.rmap.provider.ProviderExtension;
import org.eclipse.emf.common.notify.AdapterFactory;

public class MavenProviderExtension implements ProviderExtension
{

	public AdapterFactory createItemProviderAdapterFactory()
	{
		return new MavenItemProviderAdapterFactory();
	}

	public Provider createProvider()
	{
		return MavenFactory.eINSTANCE.createMavenProvider();
	}

}

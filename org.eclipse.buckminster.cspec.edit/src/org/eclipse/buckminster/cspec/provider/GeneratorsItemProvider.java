package org.eclipse.buckminster.cspec.provider;

import java.util.Collection;

import org.eclipse.buckminster.cspec.CSpec;
import org.eclipse.buckminster.cspec.CspecFactory;
import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.model.common.provider.util.TransientItemProvider;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;

public class GeneratorsItemProvider extends TransientItemProvider
{
	public GeneratorsItemProvider(AdapterFactory adapterFactory, CSpec cspec)
	{
		super(adapterFactory, cspec);
	}

	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
	{
		if(childrenFeatures == null)
		{
			super.getChildrenFeatures(object);
			childrenFeatures.add(CspecPackage.Literals.CSPEC__GENERATORS);
		}
		return childrenFeatures;
	}

	@Override
	public String getText(Object object)
	{
		return "Generators";
	}

	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
	{
		super.collectNewChildDescriptors(newChildDescriptors, object);
		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.CSPEC__GENERATORS,
				CspecFactory.eINSTANCE.createGenerator()));
	}

	@Override
	protected ResourceLocator getResourceLocator()
	{
		return CspecEditPlugin.INSTANCE;
	}
}

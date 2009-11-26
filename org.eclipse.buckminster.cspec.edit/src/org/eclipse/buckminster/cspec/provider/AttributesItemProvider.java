package org.eclipse.buckminster.cspec.provider;

import java.util.Collection;

import org.eclipse.buckminster.cspec.CSpec;
import org.eclipse.buckminster.cspec.CspecFactory;
import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.model.common.provider.util.TransientItemProvider;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;

public class AttributesItemProvider extends TransientItemProvider
{

	public AttributesItemProvider(AdapterFactory adapterFactory, CSpec cspec)
	{
		super(adapterFactory, cspec);
	}

	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
	{
		if(childrenFeatures == null)
		{
			super.getChildrenFeatures(object);
			childrenFeatures.add(CspecPackage.Literals.CSPEC__ATTRIBUTES);
		}
		return childrenFeatures;
	}

	@Override
	public String getText(Object object)
	{
		return "Attributes";
	}

	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
	{
		super.collectNewChildDescriptors(newChildDescriptors, object);
		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.CSPEC__ATTRIBUTES,
				CspecFactory.eINSTANCE.createAction()));
		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.CSPEC__ATTRIBUTES,
				CspecFactory.eINSTANCE.createArtifact()));
		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.CSPEC__ATTRIBUTES,
				CspecFactory.eINSTANCE.createGroup()));
	}

	@Override
	protected ResourceLocator getResourceLocator()
	{
		return CspecEditPlugin.INSTANCE;
	}
}

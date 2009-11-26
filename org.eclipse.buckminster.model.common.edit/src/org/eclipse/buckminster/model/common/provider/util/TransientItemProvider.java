package org.eclipse.buckminster.model.common.provider.util;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

/**
 * This class is intended as the super class of non-model intermediary views added to the model. This is especially
 * useful in scenarios where entity A has several 0..* dependencies and instead of just having lots of objects directly
 * A, you want a group view for each dependency.
 * 
 * See chapter 19.2.3 in the EMF book for more info.
 */
public abstract class TransientItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
	protected TransientItemProvider(AdapterFactory adapterFactory, EObject object)
	{
		super(adapterFactory);
		object.eAdapters().add(this);
	}

	@Override
	public Command createCommand(final Object object, final EditingDomain domain,
			Class<? extends Command> commandClass, CommandParameter commandParameter)
	{
		commandParameter.setOwner(target);
		return super.createCommand(target, domain, commandClass, commandParameter);
	}

	@Override
	public Collection<?> getChildren(Object object)
	{
		return super.getChildren(target);
	}

	@Override
	public Object getImage(Object object)
	{
		return super.getImage(target);
	}

	@Override
	public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling)
	{
		return super.getNewChildDescriptors(target, editingDomain, sibling);
	}

	@Override
	public Object getParent(Object object)
	{
		return target;
	}

	@Override
	protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,
			Collection<?> collection, int index)
	{
		return createWrappedCommand(super.createAddCommand(domain, owner, feature, collection, index), owner);
	}

	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,
			Collection<?> collection)
	{
		return createWrappedCommand(super.createRemoveCommand(domain, owner, feature, collection), owner);
	}

	@Override
	protected abstract ResourceLocator getResourceLocator();

	private Command createWrappedCommand(Command command, final EObject owner)
	{
		return new CommandWrapper(command)
		{
			@Override
			public Collection<?> getAffectedObjects()
			{
				Collection<?> affected = super.getAffectedObjects();
				if(affected.contains(owner))
					affected = Collections.singleton(TransientItemProvider.this);
				return affected;
			}
		};
	}
}

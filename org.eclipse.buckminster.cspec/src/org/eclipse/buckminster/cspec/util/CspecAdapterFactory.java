/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.util;

import org.eclipse.buckminster.cspec.*;

import org.eclipse.buckminster.model.common.ComponentIdentifier;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.equinox.p2.metadata.IVersionedId;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage
 * @generated
 */
public class CspecAdapterFactory extends AdapterFactoryImpl
{
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static CspecPackage modelPackage;

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CspecSwitch<Adapter> modelSwitch = new CspecSwitch<Adapter>()
	{
		@Override
		public Adapter caseAction(Action object)
		{
			return createActionAdapter();
		}

		@Override
		public Adapter caseActionAttribute(ActionAttribute object)
		{
			return createActionAttributeAdapter();
		}

		@Override
		public Adapter caseAlterAction(AlterAction object)
		{
			return createAlterActionAdapter();
		}

		@Override
		public Adapter caseAlterArtifact(AlterArtifact object)
		{
			return createAlterArtifactAdapter();
		}

		@Override
		public Adapter caseAlterAttribute(AlterAttribute object)
		{
			return createAlterAttributeAdapter();
		}

		@Override
		public Adapter caseAlterGroup(AlterGroup object)
		{
			return createAlterGroupAdapter();
		}

		@Override
		public Adapter caseArtifact(Artifact object)
		{
			return createArtifactAdapter();
		}

		@Override
		public Adapter caseAttribute(Attribute object)
		{
			return createAttributeAdapter();
		}

		@Override
		public Adapter caseComponentIdentifier(ComponentIdentifier object)
		{
			return createComponentIdentifierAdapter();
		}

		@Override
		public Adapter caseCSpec(CSpec object)
		{
			return createCSpecAdapter();
		}

		@Override
		public Adapter caseCSpecExtension(CSpecExtension object)
		{
			return createCSpecExtensionAdapter();
		}

		@Override
		public Adapter caseGenerator(Generator object)
		{
			return createGeneratorAdapter();
		}

		@Override
		public Adapter caseGroup(Group object)
		{
			return createGroupAdapter();
		}

		@Override
		public Adapter caseIContext(IContext object)
		{
			return createIContextAdapter();
		}

		@Override
		public Adapter caseIVersionedId(IVersionedId object)
		{
			return createIVersionedIdAdapter();
		}

		@Override
		public Adapter casePathGroup(PathGroup object)
		{
			return createPathGroupAdapter();
		}

		@Override
		public Adapter casePrerequisite(Prerequisite object)
		{
			return createPrerequisiteAdapter();
		}

		@Override
		public Adapter caseRemove(Remove object)
		{
			return createRemoveAdapter();
		}

		@Override
		public Adapter caseRename(Rename object)
		{
			return createRenameAdapter();
		}

		@Override
		public Adapter caseSelfArtifact(SelfArtifact object)
		{
			return createSelfArtifactAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object)
		{
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CspecAdapterFactory()
	{
		if(modelPackage == null)
		{
			modelPackage = CspecPackage.eINSTANCE;
		}
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.Action <em>Action</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.Action
	 * @generated
	 */
	public Adapter createActionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.ActionAttribute
	 * <em>Action Attribute</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.ActionAttribute
	 * @generated
	 */
	public Adapter createActionAttributeAdapter()
	{
		return null;
	}

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target)
	{
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.AlterAction
	 * <em>Alter Action</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.AlterAction
	 * @generated
	 */
	public Adapter createAlterActionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.AlterArtifact
	 * <em>Alter Artifact</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.AlterArtifact
	 * @generated
	 */
	public Adapter createAlterArtifactAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.AlterAttribute
	 * <em>Alter Attribute</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.AlterAttribute
	 * @generated
	 */
	public Adapter createAlterAttributeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.AlterGroup
	 * <em>Alter Group</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.AlterGroup
	 * @generated
	 */
	public Adapter createAlterGroupAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.Artifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.Artifact
	 * @generated
	 */
	public Adapter createArtifactAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.Attribute <em>Attribute</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.Attribute
	 * @generated
	 */
	public Adapter createAttributeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.model.common.ComponentIdentifier
	 * <em>Component Identifier</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.ComponentIdentifier
	 * @generated
	 */
	public Adapter createComponentIdentifierAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.CSpec <em>CSpec</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.CSpec
	 * @generated
	 */
	public Adapter createCSpecAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.CSpecExtension
	 * <em>CSpec Extension</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.CSpecExtension
	 * @generated
	 */
	public Adapter createCSpecExtensionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.Generator <em>Generator</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.Generator
	 * @generated
	 */
	public Adapter createGeneratorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.Group <em>Group</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.Group
	 * @generated
	 */
	public Adapter createGroupAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.IContext <em>IContext</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.IContext
	 * @generated
	 */
	public Adapter createIContextAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.p2.metadata.IVersionedId <em>IVersioned Id</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.p2.metadata.IVersionedId
	 * @generated
	 */
	public Adapter createIVersionedIdAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.PathGroup <em>Path Group</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.PathGroup
	 * @generated
	 */
	public Adapter createPathGroupAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.Prerequisite
	 * <em>Prerequisite</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.Prerequisite
	 * @generated
	 */
	public Adapter createPrerequisiteAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.Remove <em>Remove</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.Remove
	 * @generated
	 */
	public Adapter createRemoveAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.Rename <em>Rename</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.Rename
	 * @generated
	 */
	public Adapter createRenameAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.cspec.SelfArtifact
	 * <em>Self Artifact</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspec.SelfArtifact
	 * @generated
	 */
	public Adapter createSelfArtifactAdapter()
	{
		return null;
	}

	/**
	 * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
	 * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
	 * the model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object)
	{
		if(object == modelPackage)
		{
			return true;
		}
		if(object instanceof EObject)
		{
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

} // CspecAdapterFactory

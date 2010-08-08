/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.util;

import java.util.Map;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.model.common.Constant;
import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.model.common.Match;
import org.eclipse.buckminster.model.common.Properties;
import org.eclipse.buckminster.model.common.PropertyRef;
import org.eclipse.buckminster.model.common.Replace;
import org.eclipse.buckminster.model.common.RxAssembly;
import org.eclipse.buckminster.model.common.RxGroup;
import org.eclipse.buckminster.model.common.RxPart;
import org.eclipse.buckminster.model.common.RxPattern;
import org.eclipse.buckminster.model.common.Split;
import org.eclipse.buckminster.model.common.ToLower;
import org.eclipse.buckminster.model.common.ToUpper;
import org.eclipse.buckminster.model.common.Value;
import org.eclipse.buckminster.model.common.ValueFilter;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.equinox.p2.metadata.IVersionedId;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.eclipse.buckminster.model.common.CommonPackage
 * @generated
 */
public class CommonAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static CommonPackage modelPackage;

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CommonSwitch<Adapter> modelSwitch = new CommonSwitch<Adapter>() {
		@Override
		public Adapter caseAbstractDocumentRoot(EObject object) {
			return createAbstractDocumentRootAdapter();
		}

		@Override
		public <T> Adapter caseComparable(Comparable<T> object) {
			return createComparableAdapter();
		}

		@Override
		public Adapter caseComponentIdentifier(ComponentIdentifier object) {
			return createComponentIdentifierAdapter();
		}

		@Override
		public Adapter caseComponentRequest(ComponentRequest object) {
			return createComponentRequestAdapter();
		}

		@Override
		public Adapter caseConstant(Constant object) {
			return createConstantAdapter();
		}

		@Override
		public Adapter caseDocumentation(Documentation object) {
			return createDocumentationAdapter();
		}

		@Override
		public Adapter caseFormat(Format object) {
			return createFormatAdapter();
		}

		@Override
		public Adapter caseIProgressMonitor(IProgressMonitor object) {
			return createIProgressMonitorAdapter();
		}

		@Override
		public Adapter caseIStatus(IStatus object) {
			return createIStatusAdapter();
		}

		@Override
		public Adapter caseIVersionedId(IVersionedId object) {
			return createIVersionedIdAdapter();
		}

		@Override
		public Adapter caseMatch(Match object) {
			return createMatchAdapter();
		}

		@Override
		public Adapter caseProperties(Properties object) {
			return createPropertiesAdapter();
		}

		@Override
		public Adapter casePropertyConstant(Map.Entry<String, Value> object) {
			return createPropertyConstantAdapter();
		}

		@Override
		public Adapter casePropertyElement(Map.Entry<String, Value> object) {
			return createPropertyElementAdapter();
		}

		@Override
		public Adapter casePropertyRef(PropertyRef object) {
			return createPropertyRefAdapter();
		}

		@Override
		public Adapter caseReplace(Replace object) {
			return createReplaceAdapter();
		}

		@Override
		public Adapter caseRxAssembly(RxAssembly object) {
			return createRxAssemblyAdapter();
		}

		@Override
		public Adapter caseRxGroup(RxGroup object) {
			return createRxGroupAdapter();
		}

		@Override
		public Adapter caseRxPart(RxPart object) {
			return createRxPartAdapter();
		}

		@Override
		public Adapter caseRxPattern(RxPattern object) {
			return createRxPatternAdapter();
		}

		@Override
		public Adapter caseSplit(Split object) {
			return createSplitAdapter();
		}

		@Override
		public Adapter caseToLower(ToLower object) {
			return createToLowerAdapter();
		}

		@Override
		public Adapter caseToUpper(ToUpper object) {
			return createToUpperAdapter();
		}

		@Override
		public Adapter caseValue(Value object) {
			return createValueAdapter();
		}

		@Override
		public Adapter caseValueFilter(ValueFilter object) {
			return createValueFilterAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public CommonAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = CommonPackage.eINSTANCE;
		}
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.ecore.EObject <em>Abstract Document Root</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.EObject
	 * @generated
	 */
	public Adapter createAbstractDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */

	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link java.lang.Comparable <em>Comparable</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see java.lang.Comparable
	 * @generated
	 */
	public Adapter createComparableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.ComponentIdentifier
	 * <em>Component Identifier</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.ComponentIdentifier
	 * @generated
	 */
	public Adapter createComponentIdentifierAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.ComponentRequest
	 * <em>Component Request</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.ComponentRequest
	 * @generated
	 */
	public Adapter createComponentRequestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.Constant <em>Constant</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.Constant
	 * @generated
	 */
	public Adapter createConstantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.Documentation
	 * <em>Documentation</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.Documentation
	 * @generated
	 */
	public Adapter createDocumentationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.Format <em>Format</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.Format
	 * @generated
	 */
	public Adapter createFormatAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.core.runtime.IProgressMonitor
	 * <em>IProgress Monitor</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @generated
	 */
	public Adapter createIProgressMonitorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.core.runtime.IStatus <em>IStatus</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.core.runtime.IStatus
	 * @generated
	 */
	public Adapter createIStatusAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.p2.metadata.IVersionedId
	 * <em>IVersioned Id</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.p2.metadata.IVersionedId
	 * @generated
	 */
	public Adapter createIVersionedIdAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.Match <em>Match</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.Match
	 * @generated
	 */
	public Adapter createMatchAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.Properties
	 * <em>Properties</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.Properties
	 * @generated
	 */
	public Adapter createPropertiesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry
	 * <em>Property Constant</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createPropertyConstantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry
	 * <em>Property Element</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createPropertyElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.PropertyRef
	 * <em>Property Ref</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.PropertyRef
	 * @generated
	 */
	public Adapter createPropertyRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.Replace <em>Replace</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.Replace
	 * @generated
	 */
	public Adapter createReplaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.RxAssembly
	 * <em>Rx Assembly</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.RxAssembly
	 * @generated
	 */
	public Adapter createRxAssemblyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.RxGroup <em>Rx Group</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.RxGroup
	 * @generated
	 */
	public Adapter createRxGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.RxPart <em>Rx Part</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.RxPart
	 * @generated
	 */
	public Adapter createRxPartAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.RxPattern
	 * <em>Rx Pattern</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.RxPattern
	 * @generated
	 */
	public Adapter createRxPatternAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.Split <em>Split</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.Split
	 * @generated
	 */
	public Adapter createSplitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.ToLower <em>To Lower</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.ToLower
	 * @generated
	 */
	public Adapter createToLowerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.ToUpper <em>To Upper</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.ToUpper
	 * @generated
	 */
	public Adapter createToUpperAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.Value <em>Value</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.Value
	 * @generated
	 */
	public Adapter createValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.model.common.ValueFilter
	 * <em>Value Filter</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.model.common.ValueFilter
	 * @generated
	 */
	public Adapter createValueFilterAdapter() {
		return null;
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc --> This implementation returns <code>true</code> if
	 * the object is either the model's package or is an instance object of the
	 * model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */

	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

} // CommonAdapterFactory

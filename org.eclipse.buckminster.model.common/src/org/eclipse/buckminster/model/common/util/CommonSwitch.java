/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.util;

import java.util.List;

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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.equinox.p2.metadata.IVersionedId;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * @see org.eclipse.buckminster.model.common.CommonPackage
 * @generated
 */
public class CommonSwitch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static CommonPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public CommonSwitch() {
		if (modelPackage == null) {
			modelPackage = CommonPackage.eINSTANCE;
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Document Root</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseAbstractDocumentRoot(EObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comparable</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comparable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseComparable(Comparable<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Identifier</em>'.
	 * <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Identifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseComponentIdentifier(ComponentIdentifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Request</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Request</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseComponentRequest(ComponentRequest object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constant</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseConstant(Constant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documentation</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documentation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDocumentation(Documentation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Format</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Format</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFormat(Format object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IProgress Monitor</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IProgress Monitor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIProgressMonitor(IProgressMonitor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IStatus</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IStatus</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIStatus(IStatus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IVersioned Id</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IVersioned Id</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIVersionedId(IVersionedId object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Match</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Match</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMatch(Match object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Properties</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Properties</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseProperties(Properties object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Constant</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Constant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePropertyConstant(Map.Entry<String, Value> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Element</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePropertyElement(Map.Entry<String, Value> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Ref</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePropertyRef(PropertyRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Replace</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Replace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseReplace(Replace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rx Assembly</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rx Assembly</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRxAssembly(RxAssembly object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rx Group</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rx Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRxGroup(RxGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rx Part</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rx Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRxPart(RxPart object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rx Pattern</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rx Pattern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRxPattern(RxPattern object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Split</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Split</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSplit(Split object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>To Lower</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>To Lower</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseToLower(ToLower object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>To Upper</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>To Upper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseToUpper(ToUpper object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseValue(Value object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Filter</em>'.
	 * <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseValueFilter(ValueFilter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch, but this is
	 * the last case anyway. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T1 defaultCase(EObject object) {
		return null;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T1 doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T1 doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case CommonPackage.ABSTRACT_DOCUMENT_ROOT: {
				EObject abstractDocumentRoot = (EObject)theEObject;
				T1 result = caseAbstractDocumentRoot(abstractDocumentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.COMPONENT_IDENTIFIER: {
				ComponentIdentifier componentIdentifier = (ComponentIdentifier)theEObject;
				T1 result = caseComponentIdentifier(componentIdentifier);
				if (result == null) result = caseIVersionedId(componentIdentifier);
				if (result == null) result = caseComparable(componentIdentifier);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.COMPONENT_REQUEST: {
				ComponentRequest componentRequest = (ComponentRequest)theEObject;
				T1 result = caseComponentRequest(componentRequest);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.CONSTANT: {
				Constant constant = (Constant)theEObject;
				T1 result = caseConstant(constant);
				if (result == null) result = caseValue(constant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.DOCUMENTATION: {
				Documentation documentation = (Documentation)theEObject;
				T1 result = caseDocumentation(documentation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.FORMAT: {
				Format format = (Format)theEObject;
				T1 result = caseFormat(format);
				if (result == null) result = caseValueFilter(format);
				if (result == null) result = caseValue(format);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.MATCH: {
				Match match = (Match)theEObject;
				T1 result = caseMatch(match);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.PROPERTIES: {
				Properties properties = (Properties)theEObject;
				T1 result = caseProperties(properties);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.PROPERTY_CONSTANT: {
				@SuppressWarnings("unchecked") Map.Entry<String, Value> propertyConstant = (Map.Entry<String, Value>)theEObject;
				T1 result = casePropertyConstant(propertyConstant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.PROPERTY_ELEMENT: {
				@SuppressWarnings("unchecked") Map.Entry<String, Value> propertyElement = (Map.Entry<String, Value>)theEObject;
				T1 result = casePropertyElement(propertyElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.PROPERTY_REF: {
				PropertyRef propertyRef = (PropertyRef)theEObject;
				T1 result = casePropertyRef(propertyRef);
				if (result == null) result = caseValue(propertyRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.REPLACE: {
				Replace replace = (Replace)theEObject;
				T1 result = caseReplace(replace);
				if (result == null) result = caseValueFilter(replace);
				if (result == null) result = caseValue(replace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.RX_ASSEMBLY: {
				RxAssembly rxAssembly = (RxAssembly)theEObject;
				T1 result = caseRxAssembly(rxAssembly);
				if (result == null) result = caseRxGroup(rxAssembly);
				if (result == null) result = caseRxPart(rxAssembly);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.RX_GROUP: {
				RxGroup rxGroup = (RxGroup)theEObject;
				T1 result = caseRxGroup(rxGroup);
				if (result == null) result = caseRxPart(rxGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.RX_PART: {
				RxPart rxPart = (RxPart)theEObject;
				T1 result = caseRxPart(rxPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.RX_PATTERN: {
				RxPattern rxPattern = (RxPattern)theEObject;
				T1 result = caseRxPattern(rxPattern);
				if (result == null) result = caseRxPart(rxPattern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.SPLIT: {
				Split split = (Split)theEObject;
				T1 result = caseSplit(split);
				if (result == null) result = caseValueFilter(split);
				if (result == null) result = caseValue(split);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.TO_LOWER: {
				ToLower toLower = (ToLower)theEObject;
				T1 result = caseToLower(toLower);
				if (result == null) result = caseValueFilter(toLower);
				if (result == null) result = caseValue(toLower);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.TO_UPPER: {
				ToUpper toUpper = (ToUpper)theEObject;
				T1 result = caseToUpper(toUpper);
				if (result == null) result = caseValueFilter(toUpper);
				if (result == null) result = caseValue(toUpper);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.VALUE: {
				Value value = (Value)theEObject;
				T1 result = caseValue(value);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CommonPackage.VALUE_FILTER: {
				ValueFilter valueFilter = (ValueFilter)theEObject;
				T1 result = caseValueFilter(valueFilter);
				if (result == null) result = caseValue(valueFilter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

} // CommonSwitch

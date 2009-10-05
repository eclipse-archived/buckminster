/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.util;

import java.util.List;

import org.eclipse.buckminster.model.common.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.model.common.CommonPackage
 * @generated
 */
public class CommonSwitch<T>
{
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static CommonPackage modelPackage;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CommonSwitch()
	{
		if(modelPackage == null)
		{
			modelPackage = CommonPackage.eINSTANCE;
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constant</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstant(Constant object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of ' <em>Documentation</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of ' <em>Documentation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentation(Documentation object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of ' <em>Document Root</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of ' <em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentRoot(EObject object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Format</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Format</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormat(Format object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Match</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Match</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMatch(Match object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProperty(Property object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of ' <em>Property Constant</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of ' <em>Property Constant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyConstant(PropertyConstant object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of ' <em>Property Element</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of ' <em>Property Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyElement(PropertyElement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Ref</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyRef(PropertyRef object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Replace</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Replace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReplace(Replace object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rx Group</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rx Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRxGroup(RxGroup object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rx Part</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rx Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRxPart(RxPart object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rx Pattern</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rx Pattern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRxPattern(RxPattern object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Split</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Split</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSplit(Split object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>To Lower</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>To Lower</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseToLower(ToLower object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>To Upper</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>To Upper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseToUpper(ToUpper object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Filter</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueFilter(ValueFilter object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object)
	{
		return null;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
	 * result. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject)
	{
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
	 * result. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject)
	{
		if(theEClass.eContainer() == modelPackage)
		{
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else
		{
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return eSuperTypes.isEmpty()
					? defaultCase(theEObject)
					: doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
	 * result. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject)
	{
		switch(classifierID)
		{
		case CommonPackage.CONSTANT:
		{
			Constant constant = (Constant)theEObject;
			T result = caseConstant(constant);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.DOCUMENTATION:
		{
			Documentation documentation = (Documentation)theEObject;
			T result = caseDocumentation(documentation);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.FORMAT:
		{
			Format format = (Format)theEObject;
			T result = caseFormat(format);
			if(result == null)
				result = caseValueFilter(format);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.MATCH:
		{
			Match match = (Match)theEObject;
			T result = caseMatch(match);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.PROPERTY:
		{
			Property property = (Property)theEObject;
			T result = caseProperty(property);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.PROPERTY_CONSTANT:
		{
			PropertyConstant propertyConstant = (PropertyConstant)theEObject;
			T result = casePropertyConstant(propertyConstant);
			if(result == null)
				result = caseProperty(propertyConstant);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.PROPERTY_ELEMENT:
		{
			PropertyElement propertyElement = (PropertyElement)theEObject;
			T result = casePropertyElement(propertyElement);
			if(result == null)
				result = caseProperty(propertyElement);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.PROPERTY_REF:
		{
			PropertyRef propertyRef = (PropertyRef)theEObject;
			T result = casePropertyRef(propertyRef);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.REPLACE:
		{
			Replace replace = (Replace)theEObject;
			T result = caseReplace(replace);
			if(result == null)
				result = caseValueFilter(replace);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.RX_GROUP:
		{
			RxGroup rxGroup = (RxGroup)theEObject;
			T result = caseRxGroup(rxGroup);
			if(result == null)
				result = caseRxPart(rxGroup);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.RX_PART:
		{
			RxPart rxPart = (RxPart)theEObject;
			T result = caseRxPart(rxPart);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.RX_PATTERN:
		{
			RxPattern rxPattern = (RxPattern)theEObject;
			T result = caseRxPattern(rxPattern);
			if(result == null)
				result = caseRxPart(rxPattern);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.SPLIT:
		{
			Split split = (Split)theEObject;
			T result = caseSplit(split);
			if(result == null)
				result = caseValueFilter(split);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.TO_LOWER:
		{
			ToLower toLower = (ToLower)theEObject;
			T result = caseToLower(toLower);
			if(result == null)
				result = caseValueFilter(toLower);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.TO_UPPER:
		{
			ToUpper toUpper = (ToUpper)theEObject;
			T result = caseToUpper(toUpper);
			if(result == null)
				result = caseValueFilter(toUpper);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.VALUE_FILTER:
		{
			ValueFilter valueFilter = (ValueFilter)theEObject;
			T result = caseValueFilter(valueFilter);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CommonPackage.DOCUMENT_ROOT:
		{
			EObject documentRoot = theEObject;
			T result = caseDocumentRoot(documentRoot);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

} // CommonSwitch

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.UUID;

import java.util.regex.Pattern;

import org.eclipse.buckminster.model.common.*;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.osgi.framework.InvalidSyntaxException;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class CommonFactoryImpl extends EFactoryImpl implements CommonFactory
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CommonPackage getPackage()
	{
		return CommonPackage.eINSTANCE;
	}

	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static CommonFactory init()
	{
		try
		{
			CommonFactory theCommonFactory = (CommonFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/buckminster/Common-1.0");
			if(theCommonFactory != null)
			{
				return theCommonFactory;
			}
		}
		catch(Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CommonFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CommonFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String convertFilterToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null
				? null
				: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String convertPatternToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null
				? null
				: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertPropertyKeyToString(EDataType eDataType, Object instanceValue)
	{
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertSplitTypeToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null
				? null
				: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue)
	{
		switch(eDataType.getClassifierID())
		{
		case CommonPackage.SPLIT_TYPE:
			return convertSplitTypeToString(eDataType, instanceValue);
		case CommonPackage.PROPERTY_KEY:
			return convertPropertyKeyToString(eDataType, instanceValue);
		case CommonPackage.UUID:
			return convertUuidToString(eDataType, instanceValue);
		case CommonPackage.PATTERN:
			return convertPatternToString(eDataType, instanceValue);
		case CommonPackage.FILTER:
			return convertFilterToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String convertUuidToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null
				? null
				: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch(eClass.getClassifierID())
		{
		case CommonPackage.CONSTANT:
			return createConstant();
		case CommonPackage.DOCUMENTATION:
			return createDocumentation();
		case CommonPackage.FORMAT:
			return createFormat();
		case CommonPackage.MATCH:
			return createMatch();
		case CommonPackage.PROPERTY_CONSTANT:
			return createPropertyConstant();
		case CommonPackage.PROPERTY_ELEMENT:
			return createPropertyElement();
		case CommonPackage.PROPERTY_REF:
			return createPropertyRef();
		case CommonPackage.REPLACE:
			return createReplace();
		case CommonPackage.RX_GROUP:
			return createRxGroup();
		case CommonPackage.RX_PATTERN:
			return createRxPattern();
		case CommonPackage.SPLIT:
			return createSplit();
		case CommonPackage.TO_LOWER:
			return createToLower();
		case CommonPackage.TO_UPPER:
			return createToUpper();
		case CommonPackage.DOCUMENT_ROOT:
			return createDocumentRoot();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Constant createConstant()
	{
		ConstantImpl constant = new ConstantImpl();
		return constant;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Documentation createDocumentation()
	{
		DocumentationImpl documentation = new DocumentationImpl();
		return documentation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EObject createDocumentRoot()
	{
		EObject documentRoot = super.create(CommonPackage.Literals.DOCUMENT_ROOT);
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Filter createFilterFromString(EDataType eDataType, String initialValue)
	{
		try
		{
			return initialValue == null
					? null
					: FilterFactory.newInstance(initialValue);
		}
		catch(InvalidSyntaxException e)
		{
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Format createFormat()
	{
		FormatImpl format = new FormatImpl();
		return format;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue)
	{
		switch(eDataType.getClassifierID())
		{
		case CommonPackage.SPLIT_TYPE:
			return createSplitTypeFromString(eDataType, initialValue);
		case CommonPackage.PROPERTY_KEY:
			return createPropertyKeyFromString(eDataType, initialValue);
		case CommonPackage.UUID:
			return createUuidFromString(eDataType, initialValue);
		case CommonPackage.PATTERN:
			return createPatternFromString(eDataType, initialValue);
		case CommonPackage.FILTER:
			return createFilterFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Match createMatch()
	{
		MatchImpl match = new MatchImpl();
		return match;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Pattern createPatternFromString(EDataType eDataType, String initialValue)
	{
		return Pattern.compile(initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PropertyConstant createPropertyConstant()
	{
		PropertyConstantImpl propertyConstant = new PropertyConstantImpl();
		return propertyConstant;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PropertyElement createPropertyElement()
	{
		PropertyElementImpl propertyElement = new PropertyElementImpl();
		return propertyElement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String createPropertyKeyFromString(EDataType eDataType, String initialValue)
	{
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PropertyRef createPropertyRef()
	{
		PropertyRefImpl propertyRef = new PropertyRefImpl();
		return propertyRef;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Replace createReplace()
	{
		ReplaceImpl replace = new ReplaceImpl();
		return replace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RxGroup createRxGroup()
	{
		RxGroupImpl rxGroup = new RxGroupImpl();
		return rxGroup;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RxPattern createRxPattern()
	{
		RxPatternImpl rxPattern = new RxPatternImpl();
		return rxPattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Split createSplit()
	{
		SplitImpl split = new SplitImpl();
		return split;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public SplitType createSplitTypeFromString(EDataType eDataType, String initialValue)
	{
		SplitType result = SplitType.get(initialValue);
		if(result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ToLower createToLower()
	{
		ToLowerImpl toLower = new ToLowerImpl();
		return toLower;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ToUpper createToUpper()
	{
		ToUpperImpl toUpper = new ToUpperImpl();
		return toUpper;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public UUID createUuidFromString(EDataType eDataType, String initialValue)
	{
		return initialValue == null
				? null
				: UUID.fromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CommonPackage getCommonPackage()
	{
		return (CommonPackage)getEPackage();
	}

} // CommonFactoryImpl

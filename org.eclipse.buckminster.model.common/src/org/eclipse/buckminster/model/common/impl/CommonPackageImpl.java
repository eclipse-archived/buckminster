/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.regex.Pattern;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Constant;
import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.model.common.Match;
import org.eclipse.buckminster.model.common.Property;
import org.eclipse.buckminster.model.common.PropertyConstant;
import org.eclipse.buckminster.model.common.PropertyElement;
import org.eclipse.buckminster.model.common.PropertyRef;
import org.eclipse.buckminster.model.common.Replace;
import org.eclipse.buckminster.model.common.RxGroup;
import org.eclipse.buckminster.model.common.RxPart;
import org.eclipse.buckminster.model.common.RxPattern;
import org.eclipse.buckminster.model.common.Split;
import org.eclipse.buckminster.model.common.SplitType;
import org.eclipse.buckminster.model.common.ToLower;
import org.eclipse.buckminster.model.common.ToUpper;
import org.eclipse.buckminster.model.common.ValueFilter;

import org.eclipse.buckminster.model.common.util.CommonValidator;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class CommonPackageImpl extends EPackageImpl implements CommonPackage
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass constantEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass documentationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass formatEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass matchEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass propertyConstantEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass propertyElementEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass propertyRefEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass replaceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass rxGroupEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass rxPartEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass rxPatternEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass splitEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass toLowerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass toUpperEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass valueFilterEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum splitTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType propertyKeyEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType uuidEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType patternEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType filterEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link CommonPackage#eINSTANCE} when that field is accessed. Clients should not
	 * invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CommonPackage init()
	{
		if(isInited)
			return (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		// Obtain or create and register package
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CommonPackageImpl
				? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new CommonPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theCommonPackage.createPackageContents();

		// Initialize created meta-data
		theCommonPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put(theCommonPackage, new EValidator.Descriptor()
		{
			public EValidator getEValidator()
			{
				return CommonValidator.INSTANCE;
			}
		});

		// Mark meta-data to indicate it can't be changed
		theCommonPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CommonPackage.eNS_URI, theCommonPackage);
		return theCommonPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.buckminster.model.common.CommonPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CommonPackageImpl()
	{
		super(eNS_URI, CommonFactory.eINSTANCE);
	}

	/**
	 * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
	 * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents()
	{
		if(isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		constantEClass = createEClass(CONSTANT);
		createEAttribute(constantEClass, CONSTANT__VALUE);

		documentationEClass = createEClass(DOCUMENTATION);
		createEAttribute(documentationEClass, DOCUMENTATION__MIXED);
		createEAttribute(documentationEClass, DOCUMENTATION__ANY);
		createEAttribute(documentationEClass, DOCUMENTATION__ANY_ATTRIBUTE);

		formatEClass = createEClass(FORMAT);
		createEAttribute(formatEClass, FORMAT__FORMAT);

		matchEClass = createEClass(MATCH);
		createEAttribute(matchEClass, MATCH__PATTERN);
		createEAttribute(matchEClass, MATCH__QUOTE_PATTERN);
		createEAttribute(matchEClass, MATCH__REPLACEMENT);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__KEY);
		createEAttribute(propertyEClass, PROPERTY__MUTABLE);

		propertyConstantEClass = createEClass(PROPERTY_CONSTANT);
		createEAttribute(propertyConstantEClass, PROPERTY_CONSTANT__VALUE);

		propertyElementEClass = createEClass(PROPERTY_ELEMENT);
		createEReference(propertyElementEClass, PROPERTY_ELEMENT__CONSTANT);
		createEReference(propertyElementEClass, PROPERTY_ELEMENT__FORMAT);
		createEReference(propertyElementEClass, PROPERTY_ELEMENT__PROPERTY_REF);
		createEReference(propertyElementEClass, PROPERTY_ELEMENT__REPLACE);
		createEReference(propertyElementEClass, PROPERTY_ELEMENT__TO_LOWER);
		createEReference(propertyElementEClass, PROPERTY_ELEMENT__TO_UPPER);

		propertyRefEClass = createEClass(PROPERTY_REF);
		createEAttribute(propertyRefEClass, PROPERTY_REF__KEY);

		replaceEClass = createEClass(REPLACE);
		createEReference(replaceEClass, REPLACE__MATCHES);
		createEAttribute(replaceEClass, REPLACE__PATTERN);
		createEAttribute(replaceEClass, REPLACE__QUOTE_PATTERN);
		createEAttribute(replaceEClass, REPLACE__REPLACEMENT);

		rxGroupEClass = createEClass(RX_GROUP);
		createEAttribute(rxGroupEClass, RX_GROUP__RX_PARTS_GROUP);
		createEReference(rxGroupEClass, RX_GROUP__RX_PART);

		rxPartEClass = createEClass(RX_PART);
		createEAttribute(rxPartEClass, RX_PART__NAME);
		createEAttribute(rxPartEClass, RX_PART__OPTIONAL);

		rxPatternEClass = createEClass(RX_PATTERN);
		createEAttribute(rxPatternEClass, RX_PATTERN__PATTERN);
		createEAttribute(rxPatternEClass, RX_PATTERN__PREFIX);
		createEAttribute(rxPatternEClass, RX_PATTERN__SUFFIX);

		splitEClass = createEClass(SPLIT);
		createEAttribute(splitEClass, SPLIT__LIMIT);
		createEAttribute(splitEClass, SPLIT__PATTERN);
		createEAttribute(splitEClass, SPLIT__STYLE);

		toLowerEClass = createEClass(TO_LOWER);

		toUpperEClass = createEClass(TO_UPPER);

		valueFilterEClass = createEClass(VALUE_FILTER);
		createEReference(valueFilterEClass, VALUE_FILTER__CONSTANTS);
		createEReference(valueFilterEClass, VALUE_FILTER__FORMATS);
		createEReference(valueFilterEClass, VALUE_FILTER__PROPERTY_REFS);
		createEReference(valueFilterEClass, VALUE_FILTER__REPLACEMENTS);
		createEReference(valueFilterEClass, VALUE_FILTER__SPLITS);
		createEReference(valueFilterEClass, VALUE_FILTER__TO_LOWERS);
		createEReference(valueFilterEClass, VALUE_FILTER__TO_UPPERS);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__RX_PART);
		createEReference(documentRootEClass, DOCUMENT_ROOT__BASIC_PROPERTY);

		// Create enums
		splitTypeEEnum = createEEnum(SPLIT_TYPE);

		// Create data types
		propertyKeyEDataType = createEDataType(PROPERTY_KEY);
		uuidEDataType = createEDataType(UUID);
		patternEDataType = createEDataType(PATTERN);
		filterEDataType = createEDataType(FILTER);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CommonFactory getCommonFactory()
	{
		return (CommonFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getConstant()
	{
		return constantEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getConstant_Value()
	{
		return (EAttribute)constantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDocumentation()
	{
		return documentationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDocumentation_Any()
	{
		return (EAttribute)documentationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDocumentation_AnyAttribute()
	{
		return (EAttribute)documentationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDocumentation_Mixed()
	{
		return (EAttribute)documentationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDocumentRoot()
	{
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_BasicProperty()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_RxPart()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getFilter()
	{
		return filterEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getFormat()
	{
		return formatEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getFormat_Format()
	{
		return (EAttribute)formatEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMatch()
	{
		return matchEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMatch_Pattern()
	{
		return (EAttribute)matchEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMatch_QuotePattern()
	{
		return (EAttribute)matchEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMatch_Replacement()
	{
		return (EAttribute)matchEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getPattern()
	{
		return patternEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getProperty()
	{
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProperty_Key()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProperty_Mutable()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPropertyConstant()
	{
		return propertyConstantEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPropertyConstant_Value()
	{
		return (EAttribute)propertyConstantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPropertyElement()
	{
		return propertyElementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPropertyElement_Constant()
	{
		return (EReference)propertyElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPropertyElement_Format()
	{
		return (EReference)propertyElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPropertyElement_PropertyRef()
	{
		return (EReference)propertyElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPropertyElement_Replace()
	{
		return (EReference)propertyElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPropertyElement_ToLower()
	{
		return (EReference)propertyElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPropertyElement_ToUpper()
	{
		return (EReference)propertyElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getPropertyKey()
	{
		return propertyKeyEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPropertyRef()
	{
		return propertyRefEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPropertyRef_Key()
	{
		return (EAttribute)propertyRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getReplace()
	{
		return replaceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getReplace_Matches()
	{
		return (EReference)replaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getReplace_Pattern()
	{
		return (EAttribute)replaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getReplace_QuotePattern()
	{
		return (EAttribute)replaceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getReplace_Replacement()
	{
		return (EAttribute)replaceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRxGroup()
	{
		return rxGroupEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getRxGroup_RxPart()
	{
		return (EReference)rxGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRxGroup_RxPartsGroup()
	{
		return (EAttribute)rxGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRxPart()
	{
		return rxPartEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRxPart_Name()
	{
		return (EAttribute)rxPartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRxPart_Optional()
	{
		return (EAttribute)rxPartEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRxPattern()
	{
		return rxPatternEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRxPattern_Pattern()
	{
		return (EAttribute)rxPatternEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRxPattern_Prefix()
	{
		return (EAttribute)rxPatternEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRxPattern_Suffix()
	{
		return (EAttribute)rxPatternEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getSplit()
	{
		return splitEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getSplit_Limit()
	{
		return (EAttribute)splitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getSplit_Pattern()
	{
		return (EAttribute)splitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getSplit_Style()
	{
		return (EAttribute)splitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EEnum getSplitType()
	{
		return splitTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getToLower()
	{
		return toLowerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getToUpper()
	{
		return toUpperEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getUuid()
	{
		return uuidEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getValueFilter()
	{
		return valueFilterEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getValueFilter_Constants()
	{
		return (EReference)valueFilterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getValueFilter_Formats()
	{
		return (EReference)valueFilterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getValueFilter_PropertyRefs()
	{
		return (EReference)valueFilterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getValueFilter_Replacements()
	{
		return (EReference)valueFilterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getValueFilter_Splits()
	{
		return (EReference)valueFilterEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getValueFilter_ToLowers()
	{
		return (EReference)valueFilterEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getValueFilter_ToUppers()
	{
		return (EReference)valueFilterEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
	 * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents()
	{
		if(isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		formatEClass.getESuperTypes().add(this.getValueFilter());
		propertyConstantEClass.getESuperTypes().add(this.getProperty());
		propertyElementEClass.getESuperTypes().add(this.getProperty());
		replaceEClass.getESuperTypes().add(this.getValueFilter());
		rxGroupEClass.getESuperTypes().add(this.getRxPart());
		rxPatternEClass.getESuperTypes().add(this.getRxPart());
		splitEClass.getESuperTypes().add(this.getValueFilter());
		toLowerEClass.getESuperTypes().add(this.getValueFilter());
		toUpperEClass.getESuperTypes().add(this.getValueFilter());

		// Initialize classes and features; add operations and parameters
		initEClass(constantEClass, Constant.class, "Constant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstant_Value(), ecorePackage.getEString(), "value", null, 1, 1, Constant.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentationEClass, Documentation.class, "Documentation", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentation_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1,
				Documentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentation_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1,
				Documentation.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentation_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0,
				-1, Documentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				!IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formatEClass, Format.class, "Format", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFormat_Format(), ecorePackage.getEString(), "format", null, 1, 1, Format.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(matchEClass, Match.class, "Match", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMatch_Pattern(), ecorePackage.getEString(), "pattern", null, 1, 1, Match.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatch_QuotePattern(), ecorePackage.getEBoolean(), "quotePattern", "false", 0, 1, Match.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatch_Replacement(), ecorePackage.getEString(), "replacement", null, 1, 1, Match.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyEClass, Property.class, "Property", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProperty_Key(), this.getPropertyKey(), "key", null, 1, 1, Property.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProperty_Mutable(), ecorePackage.getEBoolean(), "mutable", null, 0, 1, Property.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyConstantEClass, PropertyConstant.class, "PropertyConstant", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPropertyConstant_Value(), ecorePackage.getEString(), "value", null, 1, 1,
				PropertyConstant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(propertyElementEClass, PropertyElement.class, "PropertyElement", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertyElement_Constant(), this.getConstant(), null, "constant", null, 0, 1,
				PropertyElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyElement_Format(), this.getFormat(), null, "format", null, 0, 1,
				PropertyElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyElement_PropertyRef(), this.getPropertyRef(), null, "propertyRef", null, 0, 1,
				PropertyElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyElement_Replace(), this.getReplace(), null, "replace", null, 0, 1,
				PropertyElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyElement_ToLower(), this.getToLower(), null, "toLower", null, 0, 1,
				PropertyElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyElement_ToUpper(), this.getToUpper(), null, "toUpper", null, 0, 1,
				PropertyElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyRefEClass, PropertyRef.class, "PropertyRef", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPropertyRef_Key(), this.getPropertyKey(), "key", null, 1, 1, PropertyRef.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(replaceEClass, Replace.class, "Replace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReplace_Matches(), this.getMatch(), null, "matches", null, 0, -1, Replace.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReplace_Pattern(), ecorePackage.getEString(), "pattern", null, 0, 1, Replace.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReplace_QuotePattern(), ecorePackage.getEBoolean(), "quotePattern", "false", 0, 1,
				Replace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getReplace_Replacement(), ecorePackage.getEString(), "replacement", null, 0, 1, Replace.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rxGroupEClass, RxGroup.class, "RxGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRxGroup_RxPartsGroup(), ecorePackage.getEFeatureMapEntry(), "rxPartsGroup", null, 0, -1,
				RxGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getRxGroup_RxPart(), this.getRxPart(), null, "rxPart", null, 0, -1, RxGroup.class, IS_TRANSIENT,
				IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);

		initEClass(rxPartEClass, RxPart.class, "RxPart", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRxPart_Name(), ecorePackage.getEString(), "name", null, 0, 1, RxPart.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRxPart_Optional(), ecorePackage.getEBoolean(), "optional", "false", 0, 1, RxPart.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rxPatternEClass, RxPattern.class, "RxPattern", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRxPattern_Pattern(), ecorePackage.getEString(), "pattern", null, 1, 1, RxPattern.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRxPattern_Prefix(), ecorePackage.getEString(), "prefix", null, 0, 1, RxPattern.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRxPattern_Suffix(), ecorePackage.getEString(), "suffix", null, 0, 1, RxPattern.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(splitEClass, Split.class, "Split", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSplit_Limit(), ecorePackage.getEInt(), "limit", null, 0, 1, Split.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSplit_Pattern(), ecorePackage.getEString(), "pattern", null, 1, 1, Split.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSplit_Style(), this.getSplitType(), "style", "quoted", 0, 1, Split.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(toLowerEClass, ToLower.class, "ToLower", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(toUpperEClass, ToUpper.class, "ToUpper", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(valueFilterEClass, ValueFilter.class, "ValueFilter", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getValueFilter_Constants(), this.getConstant(), null, "constants", null, 0, -1,
				ValueFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValueFilter_Formats(), this.getFormat(), null, "formats", null, 0, -1, ValueFilter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValueFilter_PropertyRefs(), this.getPropertyRef(), null, "propertyRefs", null, 0, -1,
				ValueFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValueFilter_Replacements(), this.getReplace(), null, "replacements", null, 0, -1,
				ValueFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValueFilter_Splits(), this.getSplit(), null, "splits", null, 0, -1, ValueFilter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValueFilter_ToLowers(), this.getToLower(), null, "toLowers", null, 0, -1, ValueFilter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValueFilter_ToUppers(), this.getToUpper(), null, "toUppers", null, 0, -1, ValueFilter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, null, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDocumentRoot_RxPart(), this.getRxPart(), null, "rxPart", null, 0, -2, null, IS_TRANSIENT,
				IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEReference(getDocumentRoot_BasicProperty(), this.getProperty(), null, "basicProperty", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(splitTypeEEnum, SplitType.class, "SplitType");
		addEEnumLiteral(splitTypeEEnum, SplitType.QUOTED);
		addEEnumLiteral(splitTypeEEnum, SplitType.UNQUOTED);
		addEEnumLiteral(splitTypeEEnum, SplitType.GROUPS);

		// Initialize data types
		initEDataType(propertyKeyEDataType, String.class, "PropertyKey", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(uuidEDataType, java.util.UUID.class, "Uuid", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(patternEDataType, Pattern.class, "Pattern", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(filterEDataType, Filter.class, "Filter", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations()
	{
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
		addAnnotation(getConstant_Value(), source, new String[] { "kind", "attribute", "name", "value" });
		addAnnotation(documentationEClass, source, new String[] { "name", "Documentation", "kind", "mixed" });
		addAnnotation(getDocumentation_Mixed(), source, new String[] { "kind", "elementWildcard", "name", ":mixed" });
		addAnnotation(getDocumentation_Any(), source, new String[] { "kind", "elementWildcard", "wildcards", "##any",
				"name", ":1", "processing", "lax" });
		addAnnotation(getDocumentation_AnyAttribute(), source, new String[] { "kind", "attributeWildcard", "wildcards",
				"##any", "name", ":2", "processing", "lax" });
		addAnnotation(getFormat_Format(), source, new String[] { "kind", "attribute" });
		addAnnotation(getMatch_Pattern(), source, new String[] { "kind", "attribute" });
		addAnnotation(getMatch_QuotePattern(), source, new String[] { "kind", "attribute" });
		addAnnotation(getMatch_Replacement(), source, new String[] { "kind", "attribute" });
		addAnnotation(getProperty_Key(), source, new String[] { "kind", "attribute" });
		addAnnotation(getProperty_Mutable(), source, new String[] { "kind", "attribute" });
		addAnnotation(getPropertyConstant_Value(), source, new String[] { "kind", "attribute" });
		addAnnotation(getPropertyElement_Constant(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element" });
		addAnnotation(getPropertyElement_Format(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element" });
		addAnnotation(getPropertyElement_PropertyRef(), source, new String[] { "namespace", "##targetNamespace",
				"kind", "element" });
		addAnnotation(getPropertyElement_Replace(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element" });
		addAnnotation(getPropertyElement_ToLower(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element" });
		addAnnotation(getPropertyElement_ToUpper(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element" });
		addAnnotation(propertyKeyEDataType, source, new String[] { "name", "propertyKey", "baseType",
				"http://www.eclipse.org/emf/2003/XMLType#string", "pattern", "[A-Za-z0-9_.${}/]+", "enumeration", "" });
		addAnnotation(getPropertyRef_Key(), source, new String[] { "kind", "attribute" });
		addAnnotation(getReplace_Matches(), source,
				new String[] { "namespace", "##targetNamespace", "kind", "element" });
		addAnnotation(getReplace_Pattern(), source, new String[] { "kind", "attribute" });
		addAnnotation(getReplace_QuotePattern(), source, new String[] { "kind", "attribute" });
		addAnnotation(getReplace_Replacement(), source, new String[] { "kind", "attribute" });
		addAnnotation(getRxGroup_RxPartsGroup(), source, new String[] { "kind", "group", "name", "rxPart:group",
				"namespace", "##targetNamespace" });
		addAnnotation(getRxGroup_RxPart(), source, new String[] { "kind", "element", "name", "rxPart", "namespace",
				"##targetNamespace", "group", "rxPart:group" });
		addAnnotation(getRxPart_Name(), source, new String[] { "kind", "attribute", "name", "name" });
		addAnnotation(getRxPart_Optional(), source, new String[] { "kind", "attribute", "name", "optional" });
		addAnnotation(getRxPattern_Pattern(), source, new String[] { "kind", "attribute" });
		addAnnotation(getRxPattern_Prefix(), source, new String[] { "kind", "attribute" });
		addAnnotation(getRxPattern_Suffix(), source, new String[] { "kind", "attribute" });
		addAnnotation(getSplit_Limit(), source, new String[] { "kind", "attribute" });
		addAnnotation(getSplit_Pattern(), source, new String[] { "kind", "attribute" });
		addAnnotation(getSplit_Style(), source, new String[] { "kind", "attribute" });
		addAnnotation(uuidEDataType, source, new String[] { "name", "uuid", "baseType",
				"http://www.eclipse.org/emf/2003/XMLType#string", "pattern",
				"[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}" });
		addAnnotation(getValueFilter_Constants(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element", "name", "constant" });
		addAnnotation(getValueFilter_Formats(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element", "name", "format" });
		addAnnotation(getValueFilter_PropertyRefs(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element", "name", "propertyRef" });
		addAnnotation(getValueFilter_Replacements(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element", "name", "replace" });
		addAnnotation(getValueFilter_Splits(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element", "name", "split" });
		addAnnotation(getValueFilter_ToLowers(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element", "name", "toLower" });
		addAnnotation(getValueFilter_ToUppers(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element", "name", "toUpper" });
		addAnnotation(patternEDataType, source, new String[] { "name", "pattern", "baseType",
				"http://www.eclipse.org/emf/2003/XMLType#string" });
		addAnnotation(documentRootEClass, source, new String[] { "name", "", "kind", "mixed" });
		addAnnotation(getDocumentRoot_RxPart(), source, new String[] { "kind", "element", "name", "rxPart",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_BasicProperty(), source, new String[] { "kind", "element", "name",
				"basicProperty", "namespace", "##targetNamespace" });
	}

} // CommonPackageImpl

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.lang.CharSequence;
import java.lang.Comparable;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.model.common.CommonFactory;
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
import org.eclipse.buckminster.model.common.SplitType;
import org.eclipse.buckminster.model.common.ToLower;
import org.eclipse.buckminster.model.common.ToUpper;
import org.eclipse.buckminster.model.common.Value;
import org.eclipse.buckminster.model.common.ValueFilter;
import org.eclipse.buckminster.model.common.util.CommonValidator;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.equinox.p2.metadata.IVersionFormat;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class CommonPackageImpl extends EPackageImpl implements CommonPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constantEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractDocumentRootEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formatEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iProgressMonitorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iStatusEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass matchEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertiesEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyConstantEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyElementEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyRefEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass replaceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rxAssemblyEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rxGroupEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rxPartEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rxPatternEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass splitEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass toLowerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass toUpperEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueFilterEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iVersionedIdEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentRequestEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentIdentifierEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass comparableEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum splitTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType coreExceptionEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType propertyKeyEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType stringBuilderEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType throwableEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType uuidEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType patternEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType filterEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iStatusArrayEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType versionEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType charSequenceEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iVersionFormatEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType listEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType versionRangeEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType urlEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link CommonPackage#eINSTANCE} when
	 * that field is accessed. Clients should not invoke it directly. Instead,
	 * they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CommonPackage init() {
		if (isInited) return (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		// Obtain or create and register package
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CommonPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theCommonPackage.createPackageContents();

		// Initialize created meta-data
		theCommonPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theCommonPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
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
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory
	 * method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.buckminster.model.common.CommonPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CommonPackageImpl() {
		super(eNS_URI, CommonFactory.eINSTANCE);
	}

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		abstractDocumentRootEClass = createEClass(ABSTRACT_DOCUMENT_ROOT);
		createEAttribute(abstractDocumentRootEClass, ABSTRACT_DOCUMENT_ROOT__MIXED);
		createEReference(abstractDocumentRootEClass, ABSTRACT_DOCUMENT_ROOT__BASIC_VALUE);
		createEReference(abstractDocumentRootEClass, ABSTRACT_DOCUMENT_ROOT__CONSTANT);
		createEReference(abstractDocumentRootEClass, ABSTRACT_DOCUMENT_ROOT__FORMAT);
		createEReference(abstractDocumentRootEClass, ABSTRACT_DOCUMENT_ROOT__PROPERTY_REF);
		createEReference(abstractDocumentRootEClass, ABSTRACT_DOCUMENT_ROOT__REPLACE);
		createEReference(abstractDocumentRootEClass, ABSTRACT_DOCUMENT_ROOT__SPLIT);
		createEReference(abstractDocumentRootEClass, ABSTRACT_DOCUMENT_ROOT__TO_LOWER);
		createEReference(abstractDocumentRootEClass, ABSTRACT_DOCUMENT_ROOT__TO_UPPER);
		createEReference(abstractDocumentRootEClass, ABSTRACT_DOCUMENT_ROOT__RX_PART);
		createEReference(abstractDocumentRootEClass, ABSTRACT_DOCUMENT_ROOT__RX_PATTERN);
		createEReference(abstractDocumentRootEClass, ABSTRACT_DOCUMENT_ROOT__RX_GROUP);

		comparableEClass = createEClass(COMPARABLE);

		componentIdentifierEClass = createEClass(COMPONENT_IDENTIFIER);
		createEAttribute(componentIdentifierEClass, COMPONENT_IDENTIFIER__TYPE);

		componentRequestEClass = createEClass(COMPONENT_REQUEST);
		createEAttribute(componentRequestEClass, COMPONENT_REQUEST__ID);
		createEAttribute(componentRequestEClass, COMPONENT_REQUEST__RANGE);
		createEAttribute(componentRequestEClass, COMPONENT_REQUEST__FILTER);
		createEAttribute(componentRequestEClass, COMPONENT_REQUEST__TYPE);

		constantEClass = createEClass(CONSTANT);
		createEAttribute(constantEClass, CONSTANT__VALUE);

		documentationEClass = createEClass(DOCUMENTATION);
		createEAttribute(documentationEClass, DOCUMENTATION__MIXED);
		createEAttribute(documentationEClass, DOCUMENTATION__ANY);
		createEAttribute(documentationEClass, DOCUMENTATION__ANY_ATTRIBUTE);

		formatEClass = createEClass(FORMAT);
		createEAttribute(formatEClass, FORMAT__FORMAT);

		iProgressMonitorEClass = createEClass(IPROGRESS_MONITOR);

		iStatusEClass = createEClass(ISTATUS);
		createEAttribute(iStatusEClass, ISTATUS__CHILDREN);
		createEAttribute(iStatusEClass, ISTATUS__CODE);
		createEAttribute(iStatusEClass, ISTATUS__EXCEPTION);
		createEAttribute(iStatusEClass, ISTATUS__MESSAGE);
		createEAttribute(iStatusEClass, ISTATUS__PLUGIN);
		createEAttribute(iStatusEClass, ISTATUS__SEVERITY);

		iVersionedIdEClass = createEClass(IVERSIONED_ID);
		createEAttribute(iVersionedIdEClass, IVERSIONED_ID__ID);
		createEAttribute(iVersionedIdEClass, IVERSIONED_ID__VERSION);

		matchEClass = createEClass(MATCH);
		createEAttribute(matchEClass, MATCH__PATTERN);
		createEAttribute(matchEClass, MATCH__QUOTE_PATTERN);
		createEAttribute(matchEClass, MATCH__REPLACEMENT);
		createEAttribute(matchEClass, MATCH__COMPILED_PATTERN);

		propertiesEClass = createEClass(PROPERTIES);
		createEReference(propertiesEClass, PROPERTIES__PROPERTY_CONSTANTS);
		createEReference(propertiesEClass, PROPERTIES__PROPERTY_ELEMENTS);

		propertyConstantEClass = createEClass(PROPERTY_CONSTANT);
		createEAttribute(propertyConstantEClass, PROPERTY_CONSTANT__KEY);
		createEReference(propertyConstantEClass, PROPERTY_CONSTANT__VALUE);
		createEAttribute(propertyConstantEClass, PROPERTY_CONSTANT__MUTABLE);
		createEAttribute(propertyConstantEClass, PROPERTY_CONSTANT__STRING_VALUE);

		propertyElementEClass = createEClass(PROPERTY_ELEMENT);
		createEAttribute(propertyElementEClass, PROPERTY_ELEMENT__KEY);
		createEAttribute(propertyElementEClass, PROPERTY_ELEMENT__VALUE_GROUP);
		createEReference(propertyElementEClass, PROPERTY_ELEMENT__VALUE);

		propertyRefEClass = createEClass(PROPERTY_REF);
		createEAttribute(propertyRefEClass, PROPERTY_REF__KEY);

		replaceEClass = createEClass(REPLACE);
		createEReference(replaceEClass, REPLACE__MATCHES);
		createEAttribute(replaceEClass, REPLACE__PATTERN);
		createEAttribute(replaceEClass, REPLACE__QUOTE_PATTERN);
		createEAttribute(replaceEClass, REPLACE__REPLACEMENT);
		createEAttribute(replaceEClass, REPLACE__COMPILED_PATTERN);

		rxAssemblyEClass = createEClass(RX_ASSEMBLY);
		createEAttribute(rxAssemblyEClass, RX_ASSEMBLY__PATTERN);

		rxGroupEClass = createEClass(RX_GROUP);
		createEAttribute(rxGroupEClass, RX_GROUP__RX_PARTS_GROUP);
		createEReference(rxGroupEClass, RX_GROUP__RX_PARTS);

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
		createEAttribute(splitEClass, SPLIT__COMPILED_PATTERN);

		toLowerEClass = createEClass(TO_LOWER);

		toUpperEClass = createEClass(TO_UPPER);

		valueEClass = createEClass(VALUE);
		createEAttribute(valueEClass, VALUE__MUTABLE);

		valueFilterEClass = createEClass(VALUE_FILTER);
		createEAttribute(valueFilterEClass, VALUE_FILTER__MULTI_VALUE_GROUP);
		createEReference(valueFilterEClass, VALUE_FILTER__VALUES);

		// Create enums
		splitTypeEEnum = createEEnum(SPLIT_TYPE);

		// Create data types
		charSequenceEDataType = createEDataType(CHAR_SEQUENCE);
		coreExceptionEDataType = createEDataType(CORE_EXCEPTION);
		filterEDataType = createEDataType(FILTER);
		iStatusArrayEDataType = createEDataType(ISTATUS_ARRAY);
		iVersionFormatEDataType = createEDataType(IVERSION_FORMAT);
		listEDataType = createEDataType(LIST);
		patternEDataType = createEDataType(PATTERN);
		propertyKeyEDataType = createEDataType(PROPERTY_KEY);
		stringBuilderEDataType = createEDataType(STRING_BUILDER);
		throwableEDataType = createEDataType(THROWABLE);
		urlEDataType = createEDataType(URL);
		uuidEDataType = createEDataType(UUID);
		versionRangeEDataType = createEDataType(VERSION_RANGE);
		versionEDataType = createEDataType(VERSION);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getAbstractDocumentRoot() {
		return abstractDocumentRootEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EReference getAbstractDocumentRoot_BasicValue() {
		return (EReference)abstractDocumentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EReference getAbstractDocumentRoot_Constant() {
		return (EReference)abstractDocumentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EReference getAbstractDocumentRoot_Format() {
		return (EReference)abstractDocumentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getAbstractDocumentRoot_Mixed() {
		return (EAttribute)abstractDocumentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EReference getAbstractDocumentRoot_PropertyRef() {
		return (EReference)abstractDocumentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EReference getAbstractDocumentRoot_Replace() {
		return (EReference)abstractDocumentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EReference getAbstractDocumentRoot_RxPart() {
		return (EReference)abstractDocumentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractDocumentRoot_RxPattern() {
		return (EReference)abstractDocumentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractDocumentRoot_RxGroup() {
		return (EReference)abstractDocumentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EReference getAbstractDocumentRoot_Split() {
		return (EReference)abstractDocumentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EReference getAbstractDocumentRoot_ToLower() {
		return (EReference)abstractDocumentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EReference getAbstractDocumentRoot_ToUpper() {
		return (EReference)abstractDocumentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EDataType getCharSequence() {
		return charSequenceEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public CommonFactory getCommonFactory() {
		return (CommonFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getComparable() {
		return comparableEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getComponentIdentifier() {
		return componentIdentifierEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getComponentIdentifier_Type() {
		return (EAttribute)componentIdentifierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getComponentRequest() {
		return componentRequestEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getComponentRequest_Filter() {
		return (EAttribute)componentRequestEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getComponentRequest_Id() {
		return (EAttribute)componentRequestEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getComponentRequest_Range() {
		return (EAttribute)componentRequestEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getComponentRequest_Type() {
		return (EAttribute)componentRequestEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getConstant() {
		return constantEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getConstant_Value() {
		return (EAttribute)constantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EDataType getCoreException() {
		return coreExceptionEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getDocumentation() {
		return documentationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getDocumentation_Any() {
		return (EAttribute)documentationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getDocumentation_AnyAttribute() {
		return (EAttribute)documentationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getDocumentation_Mixed() {
		return (EAttribute)documentationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EDataType getFilter() {
		return filterEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getFormat() {
		return formatEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getFormat_Format() {
		return (EAttribute)formatEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getIProgressMonitor() {
		return iProgressMonitorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getIStatus() {
		return iStatusEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getIStatus_Children() {
		return (EAttribute)iStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getIStatus_Code() {
		return (EAttribute)iStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getIStatus_Exception() {
		return (EAttribute)iStatusEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getIStatus_Message() {
		return (EAttribute)iStatusEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getIStatus_Plugin() {
		return (EAttribute)iStatusEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getIStatus_Severity() {
		return (EAttribute)iStatusEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EDataType getIStatusArray() {
		return iStatusArrayEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getIVersionedId() {
		return iVersionedIdEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getIVersionedId_Id() {
		return (EAttribute)iVersionedIdEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getIVersionedId_Version() {
		return (EAttribute)iVersionedIdEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EDataType getIVersionFormat() {
		return iVersionFormatEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getList() {
		return listEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getMatch() {
		return matchEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getMatch_CompiledPattern() {
		return (EAttribute)matchEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getMatch_Pattern() {
		return (EAttribute)matchEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getMatch_QuotePattern() {
		return (EAttribute)matchEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getMatch_Replacement() {
		return (EAttribute)matchEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EDataType getPattern() {
		return patternEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProperties() {
		return propertiesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperties_PropertyConstants() {
		return (EReference)propertiesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperties_PropertyElements() {
		return (EReference)propertiesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getPropertyConstant() {
		return propertyConstantEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyConstant_Key() {
		return (EAttribute)propertyConstantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getPropertyConstant_Mutable() {
		return (EAttribute)propertyConstantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyConstant_StringValue() {
		return (EAttribute)propertyConstantEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyConstant_Value() {
		return (EReference)propertyConstantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getPropertyElement() {
		return propertyElementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyElement_Key() {
		return (EAttribute)propertyElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyElement_Value() {
		return (EReference)propertyElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyElement_ValueGroup() {
		return (EAttribute)propertyElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EDataType getPropertyKey() {
		return propertyKeyEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getStringBuilder() {
		return stringBuilderEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getPropertyRef() {
		return propertyRefEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getPropertyRef_Key() {
		return (EAttribute)propertyRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getReplace() {
		return replaceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getReplace_CompiledPattern() {
		return (EAttribute)replaceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EReference getReplace_Matches() {
		return (EReference)replaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getReplace_Pattern() {
		return (EAttribute)replaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getReplace_QuotePattern() {
		return (EAttribute)replaceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getReplace_Replacement() {
		return (EAttribute)replaceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getRxAssembly() {
		return rxAssemblyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getRxAssembly_Pattern() {
		return (EAttribute)rxAssemblyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getRxGroup() {
		return rxGroupEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getRxGroup_RxPartsGroup() {
		return (EAttribute)rxGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRxGroup_RxParts() {
		return (EReference)rxGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getRxPart() {
		return rxPartEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getRxPart_Name() {
		return (EAttribute)rxPartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getRxPart_Optional() {
		return (EAttribute)rxPartEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getRxPattern() {
		return rxPatternEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getRxPattern_Pattern() {
		return (EAttribute)rxPatternEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getRxPattern_Prefix() {
		return (EAttribute)rxPatternEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getRxPattern_Suffix() {
		return (EAttribute)rxPatternEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getSplit() {
		return splitEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getSplit_CompiledPattern() {
		return (EAttribute)splitEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getSplit_Limit() {
		return (EAttribute)splitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getSplit_Pattern() {
		return (EAttribute)splitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getSplit_Style() {
		return (EAttribute)splitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EEnum getSplitType() {
		return splitTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EDataType getThrowable() {
		return throwableEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getToLower() {
		return toLowerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getToUpper() {
		return toUpperEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EDataType getURL() {
		return urlEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EDataType getUuid() {
		return uuidEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getValue() {
		return valueEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EAttribute getValue_Mutable() {
		return (EAttribute)valueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EClass getValueFilter() {
		return valueFilterEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValueFilter_MultiValueGroup() {
		return (EAttribute)valueFilterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EReference getValueFilter_Values() {
		return (EReference)valueFilterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EDataType getVersion() {
		return versionEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EDataType getVersionRange() {
		return versionRangeEDataType;
	}

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters
		ETypeParameter comparableEClass_T = addETypeParameter(comparableEClass, "T");
		addETypeParameter(listEDataType, "T");

		// Set bounds for type parameters

		// Add supertypes to classes
		EGenericType g1 = createEGenericType(this.getIVersionedId());
		componentIdentifierEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getComparable());
		EGenericType g2 = createEGenericType(this.getComponentIdentifier());
		g1.getETypeArguments().add(g2);
		componentIdentifierEClass.getEGenericSuperTypes().add(g1);
		constantEClass.getESuperTypes().add(this.getValue());
		formatEClass.getESuperTypes().add(this.getValueFilter());
		propertyRefEClass.getESuperTypes().add(this.getValue());
		replaceEClass.getESuperTypes().add(this.getValueFilter());
		rxAssemblyEClass.getESuperTypes().add(this.getRxGroup());
		rxGroupEClass.getESuperTypes().add(this.getRxPart());
		rxPatternEClass.getESuperTypes().add(this.getRxPart());
		splitEClass.getESuperTypes().add(this.getValueFilter());
		toLowerEClass.getESuperTypes().add(this.getValueFilter());
		toUpperEClass.getESuperTypes().add(this.getValueFilter());
		valueFilterEClass.getESuperTypes().add(this.getValue());

		// Initialize classes and features; add operations and parameters
		initEClass(abstractDocumentRootEClass, null, "AbstractDocumentRoot", IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractDocumentRoot_BasicValue(), this.getValue(), null, "basicValue", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractDocumentRoot_Constant(), this.getConstant(), null, "constant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractDocumentRoot_Format(), this.getFormat(), null, "format", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractDocumentRoot_PropertyRef(), this.getPropertyRef(), null, "propertyRef", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractDocumentRoot_Replace(), this.getReplace(), null, "replace", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractDocumentRoot_Split(), this.getSplit(), null, "split", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractDocumentRoot_ToLower(), this.getToLower(), null, "toLower", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractDocumentRoot_ToUpper(), this.getToUpper(), null, "toUpper", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractDocumentRoot_RxPart(), this.getRxPart(), null, "rxPart", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractDocumentRoot_RxPattern(), this.getRxPattern(), null, "rxPattern", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractDocumentRoot_RxGroup(), this.getRxGroup(), null, "rxGroup", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(comparableEClass, Comparable.class, "Comparable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		EOperation op = addEOperation(comparableEClass, ecorePackage.getEInt(), "compareTo", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(comparableEClass_T);
		addEParameter(op, g1, "o", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(componentIdentifierEClass, ComponentIdentifier.class, "ComponentIdentifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponentIdentifier_Type(), ecorePackage.getEString(), "type", null, 0, 1, ComponentIdentifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(componentIdentifierEClass, null, "setId", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(componentIdentifierEClass, null, "setVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(componentRequestEClass, ComponentRequest.class, "ComponentRequest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponentRequest_Id(), ecorePackage.getEString(), "id", null, 0, 1, ComponentRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentRequest_Range(), this.getVersionRange(), "range", null, 0, 1, ComponentRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentRequest_Filter(), this.getFilter(), "filter", null, 0, 1, ComponentRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentRequest_Type(), ecorePackage.getEString(), "type", null, 0, 1, ComponentRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(componentRequestEClass, ecorePackage.getEBoolean(), "designates", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getComponentIdentifier(), "cid", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(componentRequestEClass, ecorePackage.getEBoolean(), "isOptional", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(componentRequestEClass, ecorePackage.getEBoolean(), "isEnabled", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		EGenericType g3 = createEGenericType(ecorePackage.getEJavaObject());
		g2.setEUpperBound(g3);
		addEParameter(op, g1, "properties", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(constantEClass, Constant.class, "Constant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstant_Value(), ecorePackage.getEString(), "value", null, 0, 1, Constant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentationEClass, Documentation.class, "Documentation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentation_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, Documentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentation_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, Documentation.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentation_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, Documentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formatEClass, Format.class, "Format", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFormat_Format(), ecorePackage.getEString(), "format", null, 1, 1, Format.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iProgressMonitorEClass, IProgressMonitor.class, "IProgressMonitor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(iProgressMonitorEClass, null, "beginTask", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "totalWork", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iProgressMonitorEClass, null, "done", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iProgressMonitorEClass, null, "internalWorked", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDouble(), "work", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iProgressMonitorEClass, ecorePackage.getEBoolean(), "isCancelled", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iProgressMonitorEClass, null, "setCancelled", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iProgressMonitorEClass, null, "setTaskName", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iProgressMonitorEClass, null, "subTask", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iProgressMonitorEClass, null, "worked", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "work", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iStatusEClass, IStatus.class, "IStatus", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIStatus_Children(), this.getIStatusArray(), "children", null, 0, 1, IStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIStatus_Code(), ecorePackage.getEInt(), "code", null, 0, 1, IStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIStatus_Exception(), this.getThrowable(), "exception", null, 0, 1, IStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIStatus_Message(), ecorePackage.getEString(), "message", null, 0, 1, IStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIStatus_Plugin(), ecorePackage.getEString(), "plugin", null, 0, 1, IStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIStatus_Severity(), ecorePackage.getEInt(), "severity", null, 0, 1, IStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(iStatusEClass, ecorePackage.getEBoolean(), "isOK", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iStatusEClass, ecorePackage.getEBoolean(), "matches", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "severityMask", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iVersionedIdEClass, IVersionedId.class, "IVersionedId", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIVersionedId_Id(), ecorePackage.getEString(), "id", null, 0, 1, IVersionedId.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIVersionedId_Version(), this.getVersion(), "version", null, 0, 1, IVersionedId.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(matchEClass, Match.class, "Match", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMatch_Pattern(), ecorePackage.getEString(), "pattern", null, 1, 1, Match.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatch_QuotePattern(), ecorePackage.getEBoolean(), "quotePattern", "false", 0, 1, Match.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatch_Replacement(), ecorePackage.getEString(), "replacement", null, 1, 1, Match.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatch_CompiledPattern(), this.getPattern(), "compiledPattern", null, 0, 1, Match.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		op = addEOperation(matchEClass, ecorePackage.getEString(), "match", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "resolved", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(propertiesEClass, Properties.class, "Properties", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProperties_PropertyConstants(), this.getPropertyConstant(), null, "propertyConstants", null, 0, -1, Properties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProperties_PropertyElements(), this.getPropertyElement(), null, "propertyElements", null, 0, -1, Properties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(propertiesEClass, null, "getProperties", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		initEClass(propertyConstantEClass, Map.Entry.class, "PropertyConstant", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPropertyConstant_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyConstant_Value(), this.getValue(), null, "value", null, 1, 1, Map.Entry.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getPropertyConstant_Mutable(), ecorePackage.getEBoolean(), "mutable", "true", 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPropertyConstant_StringValue(), ecorePackage.getEString(), "stringValue", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyElementEClass, Map.Entry.class, "PropertyElement", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPropertyElement_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPropertyElement_ValueGroup(), ecorePackage.getEFeatureMapEntry(), "valueGroup", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyElement_Value(), this.getValue(), null, "value", null, 1, 1, Map.Entry.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(propertyRefEClass, PropertyRef.class, "PropertyRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPropertyRef_Key(), this.getPropertyKey(), "key", null, 1, 1, PropertyRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(replaceEClass, Replace.class, "Replace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReplace_Matches(), this.getMatch(), null, "matches", null, 0, -1, Replace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReplace_Pattern(), ecorePackage.getEString(), "pattern", null, 0, 1, Replace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReplace_QuotePattern(), ecorePackage.getEBoolean(), "quotePattern", "false", 0, 1, Replace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReplace_Replacement(), ecorePackage.getEString(), "replacement", null, 0, 1, Replace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReplace_CompiledPattern(), this.getPattern(), "compiledPattern", null, 0, 1, Replace.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(rxAssemblyEClass, RxAssembly.class, "RxAssembly", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRxAssembly_Pattern(), this.getPattern(), "pattern", null, 0, 1, RxAssembly.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		op = addEOperation(rxAssemblyEClass, null, "getMatchMap", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCharSequence(), "input", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		initEClass(rxGroupEClass, RxGroup.class, "RxGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRxGroup_RxPartsGroup(), ecorePackage.getEFeatureMapEntry(), "rxPartsGroup", null, 0, -1, RxGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRxGroup_RxParts(), this.getRxPart(), null, "rxParts", null, 0, -1, RxGroup.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(rxPartEClass, RxPart.class, "RxPart", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRxPart_Name(), ecorePackage.getEString(), "name", null, 0, 1, RxPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRxPart_Optional(), ecorePackage.getEBoolean(), "optional", "false", 0, 1, RxPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(rxPartEClass, null, "addPattern", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getStringBuilder(), "collector", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getList());
		g2 = createEGenericType(this.getRxPart());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "namedParts", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(rxPatternEClass, RxPattern.class, "RxPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRxPattern_Pattern(), ecorePackage.getEString(), "pattern", null, 1, 1, RxPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRxPattern_Prefix(), ecorePackage.getEString(), "prefix", null, 0, 1, RxPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRxPattern_Suffix(), ecorePackage.getEString(), "suffix", null, 0, 1, RxPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(splitEClass, Split.class, "Split", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSplit_Limit(), ecorePackage.getEInt(), "limit", "0", 0, 1, Split.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSplit_Pattern(), ecorePackage.getEString(), "pattern", null, 1, 1, Split.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSplit_Style(), this.getSplitType(), "style", "quoted", 0, 1, Split.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSplit_CompiledPattern(), this.getPattern(), "compiledPattern", null, 0, 1, Split.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(toLowerEClass, ToLower.class, "ToLower", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(toUpperEClass, ToUpper.class, "ToUpper", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(valueEClass, Value.class, "Value", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValue_Mutable(), ecorePackage.getEBoolean(), "mutable", "true", 0, 1, Value.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(valueEClass, ecorePackage.getEString(), "getValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "properties", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(valueEClass, ecorePackage.getEBoolean(), "isMultiValued", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(valueFilterEClass, ValueFilter.class, "ValueFilter", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValueFilter_MultiValueGroup(), ecorePackage.getEFeatureMapEntry(), "multiValueGroup", null, 1, -1, ValueFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getValueFilter_Values(), this.getValue(), null, "values", null, 1, -1, ValueFilter.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		op = addEOperation(valueFilterEClass, null, "getValues", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "properties", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getList());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		// Initialize enums and add enum literals
		initEEnum(splitTypeEEnum, SplitType.class, "SplitType");
		addEEnumLiteral(splitTypeEEnum, SplitType.QUOTED);
		addEEnumLiteral(splitTypeEEnum, SplitType.UNQUOTED);
		addEEnumLiteral(splitTypeEEnum, SplitType.GROUPS);

		// Initialize data types
		initEDataType(charSequenceEDataType, CharSequence.class, "CharSequence", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(coreExceptionEDataType, CoreException.class, "CoreException", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(filterEDataType, Filter.class, "Filter", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iStatusArrayEDataType, IStatus[].class, "IStatusArray", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iVersionFormatEDataType, IVersionFormat.class, "IVersionFormat", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(listEDataType, List.class, "List", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(patternEDataType, Pattern.class, "Pattern", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(propertyKeyEDataType, String.class, "PropertyKey", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(stringBuilderEDataType, StringBuilder.class, "StringBuilder", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(throwableEDataType, Throwable.class, "Throwable", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(urlEDataType, java.net.URL.class, "URL", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(uuidEDataType, java.util.UUID.class, "Uuid", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(versionRangeEDataType, VersionRange.class, "VersionRange", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(versionEDataType, Version.class, "Version", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for
	 * <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (abstractDocumentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getAbstractDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getAbstractDocumentRoot_BasicValue(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "basicValue",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getAbstractDocumentRoot_Constant(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "constant",
			 "namespace", "##targetNamespace",
			 "affiliation", "basicValue"
		   });		
		addAnnotation
		  (getAbstractDocumentRoot_Format(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "format",
			 "namespace", "##targetNamespace",
			 "affiliation", "basicValue"
		   });		
		addAnnotation
		  (getAbstractDocumentRoot_PropertyRef(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "propertyRef",
			 "namespace", "##targetNamespace",
			 "affiliation", "basicValue"
		   });		
		addAnnotation
		  (getAbstractDocumentRoot_Replace(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "replace",
			 "namespace", "##targetNamespace",
			 "affiliation", "basicValue"
		   });		
		addAnnotation
		  (getAbstractDocumentRoot_Split(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "split",
			 "namespace", "##targetNamespace",
			 "affiliation", "basicValue"
		   });		
		addAnnotation
		  (getAbstractDocumentRoot_ToLower(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "toLower",
			 "namespace", "##targetNamespace",
			 "affiliation", "basicValue"
		   });		
		addAnnotation
		  (getAbstractDocumentRoot_ToUpper(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "toUpper",
			 "namespace", "##targetNamespace",
			 "affiliation", "basicValue"
		   });		
		addAnnotation
		  (getAbstractDocumentRoot_RxPart(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "rxPart",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getAbstractDocumentRoot_RxPattern(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "rxPattern",
			 "namespace", "##targetNamespace",
			 "affiliation", "rxPart"
		   });		
		addAnnotation
		  (getAbstractDocumentRoot_RxGroup(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "rxGroup",
			 "namespace", "##targetNamespace",
			 "affiliation", "rxPart"
		   });		
		addAnnotation
		  (documentationEClass, 
		   source, 
		   new String[] {
			 "name", "Documentation",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentation_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentation_Any(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "##any",
			 "name", ":1",
			 "processing", "lax"
		   });		
		addAnnotation
		  (getDocumentation_AnyAttribute(), 
		   source, 
		   new String[] {
			 "kind", "attributeWildcard",
			 "wildcards", "##any",
			 "name", ":2",
			 "processing", "lax"
		   });			
		addAnnotation
		  (getFormat_Format(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getMatch_Pattern(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getMatch_QuotePattern(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getMatch_Replacement(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getProperties_PropertyConstants(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "property",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getProperties_PropertyElements(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "propertyElement",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getPropertyConstant_StringValue(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "value"
		   });			
		addAnnotation
		  (getPropertyElement_ValueGroup(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "basicValue:group",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPropertyElement_Value(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "basicValue",
			 "namespace", "##targetNamespace",
			 "group", "basicValue:group"
		   });		
		addAnnotation
		  (getPropertyRef_Key(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getReplace_Matches(), 
		   source, 
		   new String[] {
			 "namespace", "##targetNamespace",
			 "kind", "element",
			 "name", "match"
		   });		
		addAnnotation
		  (getReplace_Pattern(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getReplace_QuotePattern(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getReplace_Replacement(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getRxGroup_RxPartsGroup(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "rxPart:group",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getRxGroup_RxParts(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "rxPart",
			 "namespace", "##targetNamespace",
			 "group", "rxPart:group"
		   });				
		addAnnotation
		  (getRxPart_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });			
		addAnnotation
		  (getRxPart_Optional(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "optional"
		   });				
		addAnnotation
		  (getRxPattern_Pattern(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getRxPattern_Prefix(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });			
		addAnnotation
		  (getRxPattern_Suffix(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getSplit_Limit(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getSplit_Pattern(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getSplit_Style(), 
		   source, 
		   new String[] {
			 "kind", "attribute"
		   });		
		addAnnotation
		  (getValueFilter_MultiValueGroup(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "basicValue:group",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getValueFilter_Values(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "basicValue",
			 "namespace", "##targetNamespace",
			 "group", "basicValue:group"
		   });		
		addAnnotation
		  (patternEDataType, 
		   source, 
		   new String[] {
			 "name", "pattern",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#string"
		   });		
		addAnnotation
		  (propertyKeyEDataType, 
		   source, 
		   new String[] {
			 "name", "propertyKey",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#string",
			 "pattern", "[A-Za-z0-9_.${}/]+",
			 "enumeration", ""
		   });			
		addAnnotation
		  (uuidEDataType, 
		   source, 
		   new String[] {
			 "name", "uuid",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#string",
			 "pattern", "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"
		   });
	}

} // CommonPackageImpl

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc --> <!-- begin-model-doc --> The common schema contains generic type definitions that are expected
 * to be reused in more then one schema.
 * 
 * <!-- end-model-doc -->
 * 
 * @see org.eclipse.buckminster.model.common.CommonFactory
 * @model kind="package"
 * @generated
 */
public interface CommonPackage extends EPackage
{
	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.ConstantImpl
		 * <em>Constant</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.ConstantImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getConstant()
		 * @generated
		 */
		EClass CONSTANT = eINSTANCE.getConstant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CONSTANT__VALUE = eINSTANCE.getConstant_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.DocumentationImpl
		 * <em>Documentation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.DocumentationImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getDocumentation()
		 * @generated
		 */
		EClass DOCUMENTATION = eINSTANCE.getDocumentation();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.FormatImpl <em>Format</em>}
		 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.FormatImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getFormat()
		 * @generated
		 */
		EClass FORMAT = eINSTANCE.getFormat();

		/**
		 * The meta object literal for the '<em><b>Format</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute FORMAT__FORMAT = eINSTANCE.getFormat_Format();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.MatchImpl <em>Match</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.MatchImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getMatch()
		 * @generated
		 */
		EClass MATCH = eINSTANCE.getMatch();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATCH__PATTERN = eINSTANCE.getMatch_Pattern();

		/**
		 * The meta object literal for the '<em><b>Quote Pattern</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATCH__QUOTE_PATTERN = eINSTANCE.getMatch_QuotePattern();

		/**
		 * The meta object literal for the '<em><b>Replacement</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATCH__REPLACEMENT = eINSTANCE.getMatch_Replacement();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.PropertyImpl
		 * <em>Property</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.PropertyImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROPERTY__KEY = eINSTANCE.getProperty_Key();

		/**
		 * The meta object literal for the '<em><b>Mutable</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROPERTY__MUTABLE = eINSTANCE.getProperty_Mutable();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.PropertyConstantImpl
		 * <em>Property Constant</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.PropertyConstantImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getPropertyConstant()
		 * @generated
		 */
		EClass PROPERTY_CONSTANT = eINSTANCE.getPropertyConstant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROPERTY_CONSTANT__VALUE = eINSTANCE.getPropertyConstant_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.PropertyElementImpl
		 * <em>Property Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.PropertyElementImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getPropertyElement()
		 * @generated
		 */
		EClass PROPERTY_ELEMENT = eINSTANCE.getPropertyElement();

		/**
		 * The meta object literal for the '<em><b>Constant</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROPERTY_ELEMENT__CONSTANT = eINSTANCE.getPropertyElement_Constant();

		/**
		 * The meta object literal for the '<em><b>Format</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROPERTY_ELEMENT__FORMAT = eINSTANCE.getPropertyElement_Format();

		/**
		 * The meta object literal for the '<em><b>Property Ref</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROPERTY_ELEMENT__PROPERTY_REF = eINSTANCE.getPropertyElement_PropertyRef();

		/**
		 * The meta object literal for the '<em><b>Replace</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROPERTY_ELEMENT__REPLACE = eINSTANCE.getPropertyElement_Replace();

		/**
		 * The meta object literal for the '<em><b>To Lower</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROPERTY_ELEMENT__TO_LOWER = eINSTANCE.getPropertyElement_ToLower();

		/**
		 * The meta object literal for the '<em><b>To Upper</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROPERTY_ELEMENT__TO_UPPER = eINSTANCE.getPropertyElement_ToUpper();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.PropertyRefImpl
		 * <em>Property Ref</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.PropertyRefImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getPropertyRef()
		 * @generated
		 */
		EClass PROPERTY_REF = eINSTANCE.getPropertyRef();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROPERTY_REF__KEY = eINSTANCE.getPropertyRef_Key();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.ReplaceImpl
		 * <em>Replace</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.ReplaceImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getReplace()
		 * @generated
		 */
		EClass REPLACE = eINSTANCE.getReplace();

		/**
		 * The meta object literal for the '<em><b>Matches</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference REPLACE__MATCHES = eINSTANCE.getReplace_Matches();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPLACE__PATTERN = eINSTANCE.getReplace_Pattern();

		/**
		 * The meta object literal for the '<em><b>Quote Pattern</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPLACE__QUOTE_PATTERN = eINSTANCE.getReplace_QuotePattern();

		/**
		 * The meta object literal for the '<em><b>Replacement</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPLACE__REPLACEMENT = eINSTANCE.getReplace_Replacement();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.RxGroupImpl
		 * <em>Rx Group</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.RxGroupImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getRxGroup()
		 * @generated
		 */
		EClass RX_GROUP = eINSTANCE.getRxGroup();

		/**
		 * The meta object literal for the '<em><b>Rx Parts</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference RX_GROUP__RX_PARTS = eINSTANCE.getRxGroup_RxParts();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.RxPartImpl
		 * <em>Rx Part</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.RxPartImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getRxPart()
		 * @generated
		 */
		EClass RX_PART = eINSTANCE.getRxPart();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RX_PART__NAME = eINSTANCE.getRxPart_Name();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RX_PART__OPTIONAL = eINSTANCE.getRxPart_Optional();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.RxPatternImpl
		 * <em>Rx Pattern</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.RxPatternImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getRxPattern()
		 * @generated
		 */
		EClass RX_PATTERN = eINSTANCE.getRxPattern();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RX_PATTERN__PATTERN = eINSTANCE.getRxPattern_Pattern();

		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RX_PATTERN__PREFIX = eINSTANCE.getRxPattern_Prefix();

		/**
		 * The meta object literal for the '<em><b>Suffix</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RX_PATTERN__SUFFIX = eINSTANCE.getRxPattern_Suffix();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.SplitImpl <em>Split</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.SplitImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getSplit()
		 * @generated
		 */
		EClass SPLIT = eINSTANCE.getSplit();

		/**
		 * The meta object literal for the '<em><b>Limit</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SPLIT__LIMIT = eINSTANCE.getSplit_Limit();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SPLIT__PATTERN = eINSTANCE.getSplit_Pattern();

		/**
		 * The meta object literal for the '<em><b>Style</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SPLIT__STYLE = eINSTANCE.getSplit_Style();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.ToLowerImpl
		 * <em>To Lower</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.ToLowerImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getToLower()
		 * @generated
		 */
		EClass TO_LOWER = eINSTANCE.getToLower();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.ToUpperImpl
		 * <em>To Upper</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.ToUpperImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getToUpper()
		 * @generated
		 */
		EClass TO_UPPER = eINSTANCE.getToUpper();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.ValueFilterImpl
		 * <em>Value Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.ValueFilterImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getValueFilter()
		 * @generated
		 */
		EClass VALUE_FILTER = eINSTANCE.getValueFilter();

		/**
		 * The meta object literal for the '<em><b>Constants</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VALUE_FILTER__CONSTANTS = eINSTANCE.getValueFilter_Constants();

		/**
		 * The meta object literal for the '<em><b>Formats</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VALUE_FILTER__FORMATS = eINSTANCE.getValueFilter_Formats();

		/**
		 * The meta object literal for the '<em><b>Property Refs</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VALUE_FILTER__PROPERTY_REFS = eINSTANCE.getValueFilter_PropertyRefs();

		/**
		 * The meta object literal for the '<em><b>Replacements</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VALUE_FILTER__REPLACEMENTS = eINSTANCE.getValueFilter_Replacements();

		/**
		 * The meta object literal for the '<em><b>Splits</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VALUE_FILTER__SPLITS = eINSTANCE.getValueFilter_Splits();

		/**
		 * The meta object literal for the '<em><b>To Lowers</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VALUE_FILTER__TO_LOWERS = eINSTANCE.getValueFilter_ToLowers();

		/**
		 * The meta object literal for the '<em><b>To Uppers</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VALUE_FILTER__TO_UPPERS = eINSTANCE.getValueFilter_ToUppers();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.impl.DocumentRootImpl
		 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.impl.DocumentRootImpl
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.model.common.SplitType <em>Split Type</em>}'
		 * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.model.common.SplitType
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getSplitType()
		 * @generated
		 */
		EEnum SPLIT_TYPE = eINSTANCE.getSplitType();

		/**
		 * The meta object literal for the '<em>Property Key</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see java.lang.String
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getPropertyKey()
		 * @generated
		 */
		EDataType PROPERTY_KEY = eINSTANCE.getPropertyKey();

		/**
		 * The meta object literal for the '<em>URL</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.net.URL
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getURL()
		 * @generated
		 */
		EDataType URL = eINSTANCE.getURL();

		/**
		 * The meta object literal for the '<em>Uuid</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.util.UUID
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getUuid()
		 * @generated
		 */
		EDataType UUID = eINSTANCE.getUuid();

		/**
		 * The meta object literal for the '<em>Pattern</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.util.regex.Pattern
		 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getPattern()
		 * @generated
		 */
		EDataType PATTERN = eINSTANCE.getPattern();

	}

	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "common";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/buckminster/Common-1.0";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "bc";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	CommonPackage eINSTANCE = org.eclipse.buckminster.model.common.impl.CommonPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.ConstantImpl <em>Constant</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.ConstantImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getConstant()
	 * @generated
	 */
	int CONSTANT = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONSTANT__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Constant</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONSTANT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.DocumentationImpl
	 * <em>Documentation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.DocumentationImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getDocumentation()
	 * @generated
	 */
	int DOCUMENTATION = 1;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION__MIXED = XMLTypePackage.ANY_TYPE__MIXED;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION__ANY = XMLTypePackage.ANY_TYPE__ANY;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION__ANY_ATTRIBUTE = XMLTypePackage.ANY_TYPE__ANY_ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>Documentation</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENTATION_FEATURE_COUNT = XMLTypePackage.ANY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.ValueFilterImpl
	 * <em>Value Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.ValueFilterImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getValueFilter()
	 * @generated
	 */
	int VALUE_FILTER = 15;

	/**
	 * The feature id for the '<em><b>Constants</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALUE_FILTER__CONSTANTS = 0;

	/**
	 * The feature id for the '<em><b>Formats</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALUE_FILTER__FORMATS = 1;

	/**
	 * The feature id for the '<em><b>Property Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALUE_FILTER__PROPERTY_REFS = 2;

	/**
	 * The feature id for the '<em><b>Replacements</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALUE_FILTER__REPLACEMENTS = 3;

	/**
	 * The feature id for the '<em><b>Splits</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALUE_FILTER__SPLITS = 4;

	/**
	 * The feature id for the '<em><b>To Lowers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALUE_FILTER__TO_LOWERS = 5;

	/**
	 * The feature id for the '<em><b>To Uppers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALUE_FILTER__TO_UPPERS = 6;

	/**
	 * The number of structural features of the '<em>Value Filter</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALUE_FILTER_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.FormatImpl <em>Format</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.FormatImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getFormat()
	 * @generated
	 */
	int FORMAT = 2;

	/**
	 * The feature id for the '<em><b>Constants</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FORMAT__CONSTANTS = VALUE_FILTER__CONSTANTS;

	/**
	 * The feature id for the '<em><b>Formats</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FORMAT__FORMATS = VALUE_FILTER__FORMATS;

	/**
	 * The feature id for the '<em><b>Property Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FORMAT__PROPERTY_REFS = VALUE_FILTER__PROPERTY_REFS;

	/**
	 * The feature id for the '<em><b>Replacements</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FORMAT__REPLACEMENTS = VALUE_FILTER__REPLACEMENTS;

	/**
	 * The feature id for the '<em><b>Splits</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FORMAT__SPLITS = VALUE_FILTER__SPLITS;

	/**
	 * The feature id for the '<em><b>To Lowers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FORMAT__TO_LOWERS = VALUE_FILTER__TO_LOWERS;

	/**
	 * The feature id for the '<em><b>To Uppers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FORMAT__TO_UPPERS = VALUE_FILTER__TO_UPPERS;

	/**
	 * The feature id for the '<em><b>Format</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FORMAT__FORMAT = VALUE_FILTER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Format</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FORMAT_FEATURE_COUNT = VALUE_FILTER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.MatchImpl <em>Match</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.MatchImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getMatch()
	 * @generated
	 */
	int MATCH = 3;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCH__PATTERN = 0;

	/**
	 * The feature id for the '<em><b>Quote Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCH__QUOTE_PATTERN = 1;

	/**
	 * The feature id for the '<em><b>Replacement</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCH__REPLACEMENT = 2;

	/**
	 * The number of structural features of the '<em>Match</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCH_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.PropertyImpl <em>Property</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.PropertyImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 4;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY__MUTABLE = 1;

	/**
	 * The number of structural features of the '<em>Property</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.PropertyConstantImpl
	 * <em>Property Constant</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.PropertyConstantImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getPropertyConstant()
	 * @generated
	 */
	int PROPERTY_CONSTANT = 5;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CONSTANT__KEY = PROPERTY__KEY;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CONSTANT__MUTABLE = PROPERTY__MUTABLE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CONSTANT__VALUE = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Property Constant</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CONSTANT_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.PropertyElementImpl
	 * <em>Property Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.PropertyElementImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getPropertyElement()
	 * @generated
	 */
	int PROPERTY_ELEMENT = 6;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ELEMENT__KEY = PROPERTY__KEY;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ELEMENT__MUTABLE = PROPERTY__MUTABLE;

	/**
	 * The feature id for the '<em><b>Constant</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ELEMENT__CONSTANT = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Format</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ELEMENT__FORMAT = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Property Ref</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ELEMENT__PROPERTY_REF = PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Replace</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ELEMENT__REPLACE = PROPERTY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>To Lower</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ELEMENT__TO_LOWER = PROPERTY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>To Upper</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ELEMENT__TO_UPPER = PROPERTY_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Property Element</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ELEMENT_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.PropertyRefImpl
	 * <em>Property Ref</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.PropertyRefImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getPropertyRef()
	 * @generated
	 */
	int PROPERTY_REF = 7;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF__KEY = 0;

	/**
	 * The number of structural features of the '<em>Property Ref</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.ReplaceImpl <em>Replace</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.ReplaceImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getReplace()
	 * @generated
	 */
	int REPLACE = 8;

	/**
	 * The feature id for the '<em><b>Constants</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPLACE__CONSTANTS = VALUE_FILTER__CONSTANTS;

	/**
	 * The feature id for the '<em><b>Formats</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPLACE__FORMATS = VALUE_FILTER__FORMATS;

	/**
	 * The feature id for the '<em><b>Property Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPLACE__PROPERTY_REFS = VALUE_FILTER__PROPERTY_REFS;

	/**
	 * The feature id for the '<em><b>Replacements</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPLACE__REPLACEMENTS = VALUE_FILTER__REPLACEMENTS;

	/**
	 * The feature id for the '<em><b>Splits</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPLACE__SPLITS = VALUE_FILTER__SPLITS;

	/**
	 * The feature id for the '<em><b>To Lowers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPLACE__TO_LOWERS = VALUE_FILTER__TO_LOWERS;

	/**
	 * The feature id for the '<em><b>To Uppers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPLACE__TO_UPPERS = VALUE_FILTER__TO_UPPERS;

	/**
	 * The feature id for the '<em><b>Matches</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPLACE__MATCHES = VALUE_FILTER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPLACE__PATTERN = VALUE_FILTER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Quote Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPLACE__QUOTE_PATTERN = VALUE_FILTER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Replacement</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPLACE__REPLACEMENT = VALUE_FILTER_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Replace</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPLACE_FEATURE_COUNT = VALUE_FILTER_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.RxPartImpl <em>Rx Part</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.RxPartImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getRxPart()
	 * @generated
	 */
	int RX_PART = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RX_PART__NAME = 0;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RX_PART__OPTIONAL = 1;

	/**
	 * The number of structural features of the '<em>Rx Part</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RX_PART_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.RxGroupImpl <em>Rx Group</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.RxGroupImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getRxGroup()
	 * @generated
	 */
	int RX_GROUP = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RX_GROUP__NAME = RX_PART__NAME;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RX_GROUP__OPTIONAL = RX_PART__OPTIONAL;

	/**
	 * The feature id for the '<em><b>Rx Parts</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RX_GROUP__RX_PARTS = RX_PART_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Rx Group</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RX_GROUP_FEATURE_COUNT = RX_PART_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.RxPatternImpl <em>Rx Pattern</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.RxPatternImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getRxPattern()
	 * @generated
	 */
	int RX_PATTERN = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RX_PATTERN__NAME = RX_PART__NAME;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RX_PATTERN__OPTIONAL = RX_PART__OPTIONAL;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RX_PATTERN__PATTERN = RX_PART_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RX_PATTERN__PREFIX = RX_PART_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RX_PATTERN__SUFFIX = RX_PART_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Rx Pattern</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RX_PATTERN_FEATURE_COUNT = RX_PART_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.SplitImpl <em>Split</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.SplitImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getSplit()
	 * @generated
	 */
	int SPLIT = 12;

	/**
	 * The feature id for the '<em><b>Constants</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPLIT__CONSTANTS = VALUE_FILTER__CONSTANTS;

	/**
	 * The feature id for the '<em><b>Formats</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPLIT__FORMATS = VALUE_FILTER__FORMATS;

	/**
	 * The feature id for the '<em><b>Property Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPLIT__PROPERTY_REFS = VALUE_FILTER__PROPERTY_REFS;

	/**
	 * The feature id for the '<em><b>Replacements</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPLIT__REPLACEMENTS = VALUE_FILTER__REPLACEMENTS;

	/**
	 * The feature id for the '<em><b>Splits</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPLIT__SPLITS = VALUE_FILTER__SPLITS;

	/**
	 * The feature id for the '<em><b>To Lowers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPLIT__TO_LOWERS = VALUE_FILTER__TO_LOWERS;

	/**
	 * The feature id for the '<em><b>To Uppers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPLIT__TO_UPPERS = VALUE_FILTER__TO_UPPERS;

	/**
	 * The feature id for the '<em><b>Limit</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPLIT__LIMIT = VALUE_FILTER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPLIT__PATTERN = VALUE_FILTER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPLIT__STYLE = VALUE_FILTER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Split</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPLIT_FEATURE_COUNT = VALUE_FILTER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.ToLowerImpl <em>To Lower</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.ToLowerImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getToLower()
	 * @generated
	 */
	int TO_LOWER = 13;

	/**
	 * The feature id for the '<em><b>Constants</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_LOWER__CONSTANTS = VALUE_FILTER__CONSTANTS;

	/**
	 * The feature id for the '<em><b>Formats</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_LOWER__FORMATS = VALUE_FILTER__FORMATS;

	/**
	 * The feature id for the '<em><b>Property Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_LOWER__PROPERTY_REFS = VALUE_FILTER__PROPERTY_REFS;

	/**
	 * The feature id for the '<em><b>Replacements</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_LOWER__REPLACEMENTS = VALUE_FILTER__REPLACEMENTS;

	/**
	 * The feature id for the '<em><b>Splits</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_LOWER__SPLITS = VALUE_FILTER__SPLITS;

	/**
	 * The feature id for the '<em><b>To Lowers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_LOWER__TO_LOWERS = VALUE_FILTER__TO_LOWERS;

	/**
	 * The feature id for the '<em><b>To Uppers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_LOWER__TO_UPPERS = VALUE_FILTER__TO_UPPERS;

	/**
	 * The number of structural features of the '<em>To Lower</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_LOWER_FEATURE_COUNT = VALUE_FILTER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.ToUpperImpl <em>To Upper</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.ToUpperImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getToUpper()
	 * @generated
	 */
	int TO_UPPER = 14;

	/**
	 * The feature id for the '<em><b>Constants</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_UPPER__CONSTANTS = VALUE_FILTER__CONSTANTS;

	/**
	 * The feature id for the '<em><b>Formats</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_UPPER__FORMATS = VALUE_FILTER__FORMATS;

	/**
	 * The feature id for the '<em><b>Property Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_UPPER__PROPERTY_REFS = VALUE_FILTER__PROPERTY_REFS;

	/**
	 * The feature id for the '<em><b>Replacements</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_UPPER__REPLACEMENTS = VALUE_FILTER__REPLACEMENTS;

	/**
	 * The feature id for the '<em><b>Splits</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_UPPER__SPLITS = VALUE_FILTER__SPLITS;

	/**
	 * The feature id for the '<em><b>To Lowers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_UPPER__TO_LOWERS = VALUE_FILTER__TO_LOWERS;

	/**
	 * The feature id for the '<em><b>To Uppers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_UPPER__TO_UPPERS = VALUE_FILTER__TO_UPPERS;

	/**
	 * The number of structural features of the '<em>To Upper</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TO_UPPER_FEATURE_COUNT = VALUE_FILTER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.impl.DocumentRootImpl
	 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.impl.DocumentRootImpl
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 16;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.model.common.SplitType <em>Split Type</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.model.common.SplitType
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getSplitType()
	 * @generated
	 */
	int SPLIT_TYPE = 17;

	/**
	 * The meta object id for the '<em>Property Key</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.lang.String
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getPropertyKey()
	 * @generated
	 */
	int PROPERTY_KEY = 18;

	/**
	 * The meta object id for the '<em>URL</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.net.URL
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getURL()
	 * @generated
	 */
	int URL = 19;

	/**
	 * The meta object id for the '<em>Uuid</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.util.UUID
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getUuid()
	 * @generated
	 */
	int UUID = 20;

	/**
	 * The meta object id for the '<em>Pattern</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.util.regex.Pattern
	 * @see org.eclipse.buckminster.model.common.impl.CommonPackageImpl#getPattern()
	 * @generated
	 */
	int PATTERN = 21;

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CommonFactory getCommonFactory();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.model.common.Constant <em>Constant</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Constant</em>'.
	 * @see org.eclipse.buckminster.model.common.Constant
	 * @generated
	 */
	EClass getConstant();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.Constant#getValue
	 * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.buckminster.model.common.Constant#getValue()
	 * @see #getConstant()
	 * @generated
	 */
	EAttribute getConstant_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.model.common.Documentation
	 * <em>Documentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Documentation</em>'.
	 * @see org.eclipse.buckminster.model.common.Documentation
	 * @generated
	 */
	EClass getDocumentation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.ecore.EObject <em>Document Root</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.eclipse.emf.ecore.EObject
	 * @model extendedMetaData="name='' kind='mixed'"
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for class ' {@link org.eclipse.buckminster.model.common.Format <em>Format</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Format</em>'.
	 * @see org.eclipse.buckminster.model.common.Format
	 * @generated
	 */
	EClass getFormat();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.Format#getFormat
	 * <em>Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Format</em>'.
	 * @see org.eclipse.buckminster.model.common.Format#getFormat()
	 * @see #getFormat()
	 * @generated
	 */
	EAttribute getFormat_Format();

	/**
	 * Returns the meta object for class ' {@link org.eclipse.buckminster.model.common.Match <em>Match</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Match</em>'.
	 * @see org.eclipse.buckminster.model.common.Match
	 * @generated
	 */
	EClass getMatch();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.Match#getPattern
	 * <em>Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see org.eclipse.buckminster.model.common.Match#getPattern()
	 * @see #getMatch()
	 * @generated
	 */
	EAttribute getMatch_Pattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.Match#isQuotePattern
	 * <em>Quote Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Quote Pattern</em>'.
	 * @see org.eclipse.buckminster.model.common.Match#isQuotePattern()
	 * @see #getMatch()
	 * @generated
	 */
	EAttribute getMatch_QuotePattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.Match#getReplacement
	 * <em>Replacement</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Replacement</em>'.
	 * @see org.eclipse.buckminster.model.common.Match#getReplacement()
	 * @see #getMatch()
	 * @generated
	 */
	EAttribute getMatch_Replacement();

	/**
	 * Returns the meta object for data type '{@link java.util.regex.Pattern <em>Pattern</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Pattern</em>'.
	 * @see java.util.regex.Pattern
	 * @model instanceClass="java.util.regex.Pattern"
	 *        extendedMetaData="name='pattern' baseType='http://www.eclipse.org/emf/2003/XMLType#string'"
	 * @generated
	 */
	EDataType getPattern();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.model.common.Property <em>Property</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.eclipse.buckminster.model.common.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.Property#getKey
	 * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.eclipse.buckminster.model.common.Property#getKey()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Key();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.Property#isMutable
	 * <em>Mutable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Mutable</em>'.
	 * @see org.eclipse.buckminster.model.common.Property#isMutable()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Mutable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.model.common.PropertyConstant
	 * <em>Property Constant</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Property Constant</em>'.
	 * @see org.eclipse.buckminster.model.common.PropertyConstant
	 * @generated
	 */
	EClass getPropertyConstant();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.PropertyConstant#getValue
	 * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.buckminster.model.common.PropertyConstant#getValue()
	 * @see #getPropertyConstant()
	 * @generated
	 */
	EAttribute getPropertyConstant_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.model.common.PropertyElement
	 * <em>Property Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Property Element</em>'.
	 * @see org.eclipse.buckminster.model.common.PropertyElement
	 * @generated
	 */
	EClass getPropertyElement();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.model.common.PropertyElement#getConstant <em>Constant</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Constant</em>'.
	 * @see org.eclipse.buckminster.model.common.PropertyElement#getConstant()
	 * @see #getPropertyElement()
	 * @generated
	 */
	EReference getPropertyElement_Constant();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.model.common.PropertyElement#getFormat <em>Format</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Format</em>'.
	 * @see org.eclipse.buckminster.model.common.PropertyElement#getFormat()
	 * @see #getPropertyElement()
	 * @generated
	 */
	EReference getPropertyElement_Format();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.model.common.PropertyElement#getPropertyRef <em>Property Ref</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference ' <em>Property Ref</em>'.
	 * @see org.eclipse.buckminster.model.common.PropertyElement#getPropertyRef()
	 * @see #getPropertyElement()
	 * @generated
	 */
	EReference getPropertyElement_PropertyRef();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.model.common.PropertyElement#getReplace <em>Replace</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Replace</em>'.
	 * @see org.eclipse.buckminster.model.common.PropertyElement#getReplace()
	 * @see #getPropertyElement()
	 * @generated
	 */
	EReference getPropertyElement_Replace();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.model.common.PropertyElement#getToLower <em>To Lower</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>To Lower</em>'.
	 * @see org.eclipse.buckminster.model.common.PropertyElement#getToLower()
	 * @see #getPropertyElement()
	 * @generated
	 */
	EReference getPropertyElement_ToLower();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.model.common.PropertyElement#getToUpper <em>To Upper</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>To Upper</em>'.
	 * @see org.eclipse.buckminster.model.common.PropertyElement#getToUpper()
	 * @see #getPropertyElement()
	 * @generated
	 */
	EReference getPropertyElement_ToUpper();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Property Key</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Property Key</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String" extendedMetaData="name='propertyKey' baseType='http://www.eclipse.org/emf/2003/XMLType#string' pattern='[A-Za-z0-9_.${}/]+' enumeration=''"
	 * @generated
	 */
	EDataType getPropertyKey();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.model.common.PropertyRef <em>Property Ref</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Property Ref</em>'.
	 * @see org.eclipse.buckminster.model.common.PropertyRef
	 * @generated
	 */
	EClass getPropertyRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.PropertyRef#getKey
	 * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.eclipse.buckminster.model.common.PropertyRef#getKey()
	 * @see #getPropertyRef()
	 * @generated
	 */
	EAttribute getPropertyRef_Key();

	/**
	 * Returns the meta object for class ' {@link org.eclipse.buckminster.model.common.Replace <em>Replace</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Replace</em>'.
	 * @see org.eclipse.buckminster.model.common.Replace
	 * @generated
	 */
	EClass getReplace();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.model.common.Replace#getMatches <em>Matches</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Matches</em>'.
	 * @see org.eclipse.buckminster.model.common.Replace#getMatches()
	 * @see #getReplace()
	 * @generated
	 */
	EReference getReplace_Matches();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.Replace#getPattern
	 * <em>Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see org.eclipse.buckminster.model.common.Replace#getPattern()
	 * @see #getReplace()
	 * @generated
	 */
	EAttribute getReplace_Pattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.Replace#isQuotePattern
	 * <em>Quote Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Quote Pattern</em>'.
	 * @see org.eclipse.buckminster.model.common.Replace#isQuotePattern()
	 * @see #getReplace()
	 * @generated
	 */
	EAttribute getReplace_QuotePattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.Replace#getReplacement
	 * <em>Replacement</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Replacement</em>'.
	 * @see org.eclipse.buckminster.model.common.Replace#getReplacement()
	 * @see #getReplace()
	 * @generated
	 */
	EAttribute getReplace_Replacement();

	/**
	 * Returns the meta object for class ' {@link org.eclipse.buckminster.model.common.RxGroup <em>Rx Group</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Rx Group</em>'.
	 * @see org.eclipse.buckminster.model.common.RxGroup
	 * @generated
	 */
	EClass getRxGroup();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.model.common.RxGroup#getRxParts <em>Rx Parts</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Rx Parts</em>'.
	 * @see org.eclipse.buckminster.model.common.RxGroup#getRxParts()
	 * @see #getRxGroup()
	 * @generated
	 */
	EReference getRxGroup_RxParts();

	/**
	 * Returns the meta object for class ' {@link org.eclipse.buckminster.model.common.RxPart <em>Rx Part</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Rx Part</em>'.
	 * @see org.eclipse.buckminster.model.common.RxPart
	 * @generated
	 */
	EClass getRxPart();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.RxPart#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.model.common.RxPart#getName()
	 * @see #getRxPart()
	 * @generated
	 */
	EAttribute getRxPart_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.RxPart#isOptional
	 * <em>Optional</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see org.eclipse.buckminster.model.common.RxPart#isOptional()
	 * @see #getRxPart()
	 * @generated
	 */
	EAttribute getRxPart_Optional();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.model.common.RxPattern <em>Rx Pattern</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Rx Pattern</em>'.
	 * @see org.eclipse.buckminster.model.common.RxPattern
	 * @generated
	 */
	EClass getRxPattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.RxPattern#getPattern
	 * <em>Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see org.eclipse.buckminster.model.common.RxPattern#getPattern()
	 * @see #getRxPattern()
	 * @generated
	 */
	EAttribute getRxPattern_Pattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.RxPattern#getPrefix
	 * <em>Prefix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Prefix</em>'.
	 * @see org.eclipse.buckminster.model.common.RxPattern#getPrefix()
	 * @see #getRxPattern()
	 * @generated
	 */
	EAttribute getRxPattern_Prefix();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.RxPattern#getSuffix
	 * <em>Suffix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Suffix</em>'.
	 * @see org.eclipse.buckminster.model.common.RxPattern#getSuffix()
	 * @see #getRxPattern()
	 * @generated
	 */
	EAttribute getRxPattern_Suffix();

	/**
	 * Returns the meta object for class ' {@link org.eclipse.buckminster.model.common.Split <em>Split</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Split</em>'.
	 * @see org.eclipse.buckminster.model.common.Split
	 * @generated
	 */
	EClass getSplit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.Split#getLimit
	 * <em>Limit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Limit</em>'.
	 * @see org.eclipse.buckminster.model.common.Split#getLimit()
	 * @see #getSplit()
	 * @generated
	 */
	EAttribute getSplit_Limit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.Split#getPattern
	 * <em>Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see org.eclipse.buckminster.model.common.Split#getPattern()
	 * @see #getSplit()
	 * @generated
	 */
	EAttribute getSplit_Pattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.model.common.Split#getStyle
	 * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Style</em>'.
	 * @see org.eclipse.buckminster.model.common.Split#getStyle()
	 * @see #getSplit()
	 * @generated
	 */
	EAttribute getSplit_Style();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.buckminster.model.common.SplitType <em>Split Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Split Type</em>'.
	 * @see org.eclipse.buckminster.model.common.SplitType
	 * @generated
	 */
	EEnum getSplitType();

	/**
	 * Returns the meta object for class ' {@link org.eclipse.buckminster.model.common.ToLower <em>To Lower</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>To Lower</em>'.
	 * @see org.eclipse.buckminster.model.common.ToLower
	 * @generated
	 */
	EClass getToLower();

	/**
	 * Returns the meta object for class ' {@link org.eclipse.buckminster.model.common.ToUpper <em>To Upper</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>To Upper</em>'.
	 * @see org.eclipse.buckminster.model.common.ToUpper
	 * @generated
	 */
	EClass getToUpper();

	/**
	 * Returns the meta object for data type '{@link java.net.URL <em>URL</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>URL</em>'.
	 * @see java.net.URL
	 * @model instanceClass="java.net.URL"
	 *        extendedMetaData="name='URL' baseType='http://www.eclipse.org/emf/2003/XMLType#anyURI'"
	 * @generated
	 */
	EDataType getURL();

	/**
	 * Returns the meta object for data type '{@link java.util.UUID <em>Uuid</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Uuid</em>'.
	 * @see java.util.UUID
	 * @model instanceClass="java.util.UUID" extendedMetaData="name='uuid' baseType='http://www.eclipse.org/emf/2003/XMLType#string' pattern='[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}'"
	 * @generated
	 */
	EDataType getUuid();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.model.common.ValueFilter <em>Value Filter</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Value Filter</em>'.
	 * @see org.eclipse.buckminster.model.common.ValueFilter
	 * @generated
	 */
	EClass getValueFilter();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.model.common.ValueFilter#getConstants <em>Constants</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Constants</em>'.
	 * @see org.eclipse.buckminster.model.common.ValueFilter#getConstants()
	 * @see #getValueFilter()
	 * @generated
	 */
	EReference getValueFilter_Constants();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.model.common.ValueFilter#getFormats <em>Formats</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Formats</em>'.
	 * @see org.eclipse.buckminster.model.common.ValueFilter#getFormats()
	 * @see #getValueFilter()
	 * @generated
	 */
	EReference getValueFilter_Formats();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.model.common.ValueFilter#getPropertyRefs <em>Property Refs</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Property Refs</em>'.
	 * @see org.eclipse.buckminster.model.common.ValueFilter#getPropertyRefs()
	 * @see #getValueFilter()
	 * @generated
	 */
	EReference getValueFilter_PropertyRefs();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.model.common.ValueFilter#getReplacements <em>Replacements</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Replacements</em>'.
	 * @see org.eclipse.buckminster.model.common.ValueFilter#getReplacements()
	 * @see #getValueFilter()
	 * @generated
	 */
	EReference getValueFilter_Replacements();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.model.common.ValueFilter#getSplits <em>Splits</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Splits</em>'.
	 * @see org.eclipse.buckminster.model.common.ValueFilter#getSplits()
	 * @see #getValueFilter()
	 * @generated
	 */
	EReference getValueFilter_Splits();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.model.common.ValueFilter#getToLowers <em>To Lowers</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>To Lowers</em>'.
	 * @see org.eclipse.buckminster.model.common.ValueFilter#getToLowers()
	 * @see #getValueFilter()
	 * @generated
	 */
	EReference getValueFilter_ToLowers();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.model.common.ValueFilter#getToUppers <em>To Uppers</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>To Uppers</em>'.
	 * @see org.eclipse.buckminster.model.common.ValueFilter#getToUppers()
	 * @see #getValueFilter()
	 * @generated
	 */
	EReference getValueFilter_ToUppers();

} // CommonPackage

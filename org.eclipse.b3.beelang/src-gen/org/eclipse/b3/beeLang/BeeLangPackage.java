/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.b3.beeLang.BeeLangFactory
 * @model kind="package"
 * @generated
 */
public interface BeeLangPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "beeLang";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/b3/BeeLang";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "beeLang";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  BeeLangPackage eINSTANCE = org.eclipse.b3.beeLang.impl.BeeLangPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BeeModelImpl <em>Bee Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BeeModelImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBeeModel()
   * @generated
   */
  int BEE_MODEL = 0;

  /**
   * The feature id for the '<em><b>Imports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BEE_MODEL__IMPORTS = 0;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BEE_MODEL__BODY = 1;

  /**
   * The number of structural features of the '<em>Bee Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BEE_MODEL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.VersionImpl <em>Version</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.VersionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVersion()
   * @generated
   */
  int VERSION = 1;

  /**
   * The feature id for the '<em><b>Original</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERSION__ORIGINAL = 0;

  /**
   * The number of structural features of the '<em>Version</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERSION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.VersionRangeImpl <em>Version Range</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.VersionRangeImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVersionRange()
   * @generated
   */
  int VERSION_RANGE = 2;

  /**
   * The feature id for the '<em><b>Min Limit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERSION_RANGE__MIN_LIMIT = 0;

  /**
   * The feature id for the '<em><b>Min</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERSION_RANGE__MIN = 1;

  /**
   * The feature id for the '<em><b>Max</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERSION_RANGE__MAX = 2;

  /**
   * The feature id for the '<em><b>Max Limit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERSION_RANGE__MAX_LIMIT = 3;

  /**
   * The number of structural features of the '<em>Version Range</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERSION_RANGE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ImportImpl <em>Import</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ImportImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getImport()
   * @generated
   */
  int IMPORT = 3;

  /**
   * The feature id for the '<em><b>Import Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT__IMPORT_CLASS = 0;

  /**
   * The number of structural features of the '<em>Import</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl <em>Build Unit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BuildUnitImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBuildUnit()
   * @generated
   */
  int BUILD_UNIT = 4;

  /**
   * The feature id for the '<em><b>Execution Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__EXECUTION_MODE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__NAME = 1;

  /**
   * The feature id for the '<em><b>Version</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__VERSION = 2;

  /**
   * The feature id for the '<em><b>Implements</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__IMPLEMENTS = 3;

  /**
   * The feature id for the '<em><b>Compound Property Operation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__COMPOUND_PROPERTY_OPERATION = 4;

  /**
   * The feature id for the '<em><b>Provided Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__PROVIDED_CAPABILITIES = 5;

  /**
   * The feature id for the '<em><b>Required Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__REQUIRED_CAPABILITIES = 6;

  /**
   * The feature id for the '<em><b>Meta Required Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__META_REQUIRED_CAPABILITIES = 7;

  /**
   * The feature id for the '<em><b>Advice</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__ADVICE = 8;

  /**
   * The feature id for the '<em><b>Synchronizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__SYNCHRONIZATIONS = 9;

  /**
   * The feature id for the '<em><b>Parts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__PARTS = 10;

  /**
   * The feature id for the '<em><b>Repository Configurations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__REPOSITORY_CONFIGURATIONS = 11;

  /**
   * The number of structural features of the '<em>Build Unit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT_FEATURE_COUNT = 12;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ProvidedCapabilityImpl <em>Provided Capability</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ProvidedCapabilityImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getProvidedCapability()
   * @generated
   */
  int PROVIDED_CAPABILITY = 5;

  /**
   * The feature id for the '<em><b>Filter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROVIDED_CAPABILITY__FILTER = 0;

  /**
   * The feature id for the '<em><b>Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROVIDED_CAPABILITY__INTERFACE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROVIDED_CAPABILITY__NAME = 2;

  /**
   * The feature id for the '<em><b>Version</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROVIDED_CAPABILITY__VERSION = 3;

  /**
   * The number of structural features of the '<em>Provided Capability</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROVIDED_CAPABILITY_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.RequiredCapabilityImpl <em>Required Capability</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.RequiredCapabilityImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRequiredCapability()
   * @generated
   */
  int REQUIRED_CAPABILITY = 6;

  /**
   * The feature id for the '<em><b>Filter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRED_CAPABILITY__FILTER = 0;

  /**
   * The feature id for the '<em><b>Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRED_CAPABILITY__INTERFACE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRED_CAPABILITY__NAME = 2;

  /**
   * The feature id for the '<em><b>Range</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRED_CAPABILITY__RANGE = 3;

  /**
   * The number of structural features of the '<em>Required Capability</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRED_CAPABILITY_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.NamedPropertiesImpl <em>Named Properties</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.NamedPropertiesImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getNamedProperties()
   * @generated
   */
  int NAMED_PROPERTIES = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_PROPERTIES__NAME = 0;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_PROPERTIES__BODY = 1;

  /**
   * The number of structural features of the '<em>Named Properties</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_PROPERTIES_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PropertyOperationImpl <em>Property Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PropertyOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPropertyOperation()
   * @generated
   */
  int PROPERTY_OPERATION = 9;

  /**
   * The number of structural features of the '<em>Property Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_OPERATION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.CompoundPropertyOperationImpl <em>Compound Property Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.CompoundPropertyOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCompoundPropertyOperation()
   * @generated
   */
  int COMPOUND_PROPERTY_OPERATION = 8;

  /**
   * The feature id for the '<em><b>Operations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOUND_PROPERTY_OPERATION__OPERATIONS = PROPERTY_OPERATION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Compound Property Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOUND_PROPERTY_OPERATION_FEATURE_COUNT = PROPERTY_OPERATION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FilteredPropertyOperationImpl <em>Filtered Property Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FilteredPropertyOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFilteredPropertyOperation()
   * @generated
   */
  int FILTERED_PROPERTY_OPERATION = 10;

  /**
   * The feature id for the '<em><b>Filter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTERED_PROPERTY_OPERATION__FILTER = PROPERTY_OPERATION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTERED_PROPERTY_OPERATION__BODY = PROPERTY_OPERATION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Filtered Property Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTERED_PROPERTY_OPERATION_FEATURE_COUNT = PROPERTY_OPERATION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.SetPropertyOperationImpl <em>Set Property Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.SetPropertyOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSetPropertyOperation()
   * @generated
   */
  int SET_PROPERTY_OPERATION = 11;

  /**
   * The feature id for the '<em><b>Immutable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_PROPERTY_OPERATION__IMMUTABLE = PROPERTY_OPERATION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_PROPERTY_OPERATION__KEY = PROPERTY_OPERATION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_PROPERTY_OPERATION__OP = PROPERTY_OPERATION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_PROPERTY_OPERATION__VALUE = PROPERTY_OPERATION_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Set Property Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_PROPERTY_OPERATION_FEATURE_COUNT = PROPERTY_OPERATION_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.UnsetPropertyOperationImpl <em>Unset Property Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.UnsetPropertyOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUnsetPropertyOperation()
   * @generated
   */
  int UNSET_PROPERTY_OPERATION = 12;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSET_PROPERTY_OPERATION__KEY = PROPERTY_OPERATION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Unset Property Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNSET_PROPERTY_OPERATION_FEATURE_COUNT = PROPERTY_OPERATION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.SynchronizationImpl <em>Synchronization</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.SynchronizationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSynchronization()
   * @generated
   */
  int SYNCHRONIZATION = 13;

  /**
   * The feature id for the '<em><b>Partrefs</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNCHRONIZATION__PARTREFS = 0;

  /**
   * The number of structural features of the '<em>Synchronization</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNCHRONIZATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PathGroupImpl <em>Path Group</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PathGroupImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPathGroup()
   * @generated
   */
  int PATH_GROUP = 14;

  /**
   * The feature id for the '<em><b>Paths</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATH_GROUP__PATHS = 0;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATH_GROUP__ANNOTATIONS = 1;

  /**
   * The number of structural features of the '<em>Path Group</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATH_GROUP_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PathVectorElementImpl <em>Path Vector Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PathVectorElementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPathVectorElement()
   * @generated
   */
  int PATH_VECTOR_ELEMENT = 15;

  /**
   * The number of structural features of the '<em>Path Vector Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATH_VECTOR_ELEMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FilteredPathVectorImpl <em>Filtered Path Vector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FilteredPathVectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFilteredPathVector()
   * @generated
   */
  int FILTERED_PATH_VECTOR = 16;

  /**
   * The feature id for the '<em><b>Filter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTERED_PATH_VECTOR__FILTER = PATH_VECTOR_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTERED_PATH_VECTOR__BODY = PATH_VECTOR_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Filtered Path Vector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTERED_PATH_VECTOR_FEATURE_COUNT = PATH_VECTOR_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PathVectorImpl <em>Path Vector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PathVectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPathVector()
   * @generated
   */
  int PATH_VECTOR = 17;

  /**
   * The feature id for the '<em><b>Base Path</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATH_VECTOR__BASE_PATH = PATH_VECTOR_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Paths</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATH_VECTOR__PATHS = PATH_VECTOR_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Path Vector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATH_VECTOR_FEATURE_COUNT = PATH_VECTOR_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.CompoundPathVectorImpl <em>Compound Path Vector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.CompoundPathVectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCompoundPathVector()
   * @generated
   */
  int COMPOUND_PATH_VECTOR = 18;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOUND_PATH_VECTOR__BODY = 0;

  /**
   * The number of structural features of the '<em>Compound Path Vector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOUND_PATH_VECTOR_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PrerequisiteImpl <em>Prerequisite</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PrerequisiteImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPrerequisite()
   * @generated
   */
  int PREREQUISITE = 19;

  /**
   * The feature id for the '<em><b>Surpressed</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREREQUISITE__SURPRESSED = 0;

  /**
   * The feature id for the '<em><b>Filter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREREQUISITE__FILTER = 1;

  /**
   * The feature id for the '<em><b>Alias</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREREQUISITE__ALIAS = 2;

  /**
   * The feature id for the '<em><b>Part Reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREREQUISITE__PART_REFERENCE = 3;

  /**
   * The feature id for the '<em><b>Closure</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREREQUISITE__CLOSURE = 4;

  /**
   * The number of structural features of the '<em>Prerequisite</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREREQUISITE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ClosureImpl <em>Closure</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ClosureImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getClosure()
   * @generated
   */
  int CLOSURE = 20;

  /**
   * The feature id for the '<em><b>Properties</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSURE__PROPERTIES = 0;

  /**
   * The feature id for the '<em><b>Advice</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSURE__ADVICE = 1;

  /**
   * The number of structural features of the '<em>Closure</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSURE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PrerequisiteEntryImpl <em>Prerequisite Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PrerequisiteEntryImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPrerequisiteEntry()
   * @generated
   */
  int PREREQUISITE_ENTRY = 21;

  /**
   * The number of structural features of the '<em>Prerequisite Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREREQUISITE_ENTRY_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PartInSelfImpl <em>Part In Self</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PartInSelfImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPartInSelf()
   * @generated
   */
  int PART_IN_SELF = 22;

  /**
   * The feature id for the '<em><b>Part Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART_IN_SELF__PART_NAME = PREREQUISITE_ENTRY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Part In Self</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART_IN_SELF_FEATURE_COUNT = PREREQUISITE_ENTRY_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.CapabilityReferencedPartImpl <em>Capability Referenced Part</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.CapabilityReferencedPartImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCapabilityReferencedPart()
   * @generated
   */
  int CAPABILITY_REFERENCED_PART = 23;

  /**
   * The feature id for the '<em><b>Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAPABILITY_REFERENCED_PART__INTERFACE = PREREQUISITE_ENTRY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAPABILITY_REFERENCED_PART__NAME = PREREQUISITE_ENTRY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Range</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAPABILITY_REFERENCED_PART__RANGE = PREREQUISITE_ENTRY_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Part Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAPABILITY_REFERENCED_PART__PART_NAME = PREREQUISITE_ENTRY_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Capability Referenced Part</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAPABILITY_REFERENCED_PART_FEATURE_COUNT = PREREQUISITE_ENTRY_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.CompoundReferencesImpl <em>Compound References</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.CompoundReferencesImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCompoundReferences()
   * @generated
   */
  int COMPOUND_REFERENCES = 24;

  /**
   * The feature id for the '<em><b>Prerequisites</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOUND_REFERENCES__PREREQUISITES = PREREQUISITE_ENTRY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Compound References</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOUND_REFERENCES_FEATURE_COUNT = PREREQUISITE_ENTRY_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PartImpl <em>Part</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PartImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPart()
   * @generated
   */
  int PART = 25;

  /**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART__VISIBILITY = 0;

  /**
   * The feature id for the '<em><b>Execution Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART__EXECUTION_MODE = 1;

  /**
   * The feature id for the '<em><b>Provided Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART__PROVIDED_CAPABILITIES = 2;

  /**
   * The feature id for the '<em><b>Pre Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART__PRE_CONDITION = 3;

  /**
   * The feature id for the '<em><b>Post Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART__POST_CONDITION = 4;

  /**
   * The feature id for the '<em><b>Properties</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART__PROPERTIES = 5;

  /**
   * The feature id for the '<em><b>Advice</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART__ADVICE = 6;

  /**
   * The feature id for the '<em><b>Group</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART__GROUP = 7;

  /**
   * The feature id for the '<em><b>Layout</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART__LAYOUT = 8;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART__STATEMENTS = 9;

  /**
   * The number of structural features of the '<em>Part</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PART_FEATURE_COUNT = 10;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ParameterImpl <em>Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ParameterImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getParameter()
   * @generated
   */
  int PARAMETER = 26;

  /**
   * The feature id for the '<em><b>Val</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__VAL = 0;

  /**
   * The number of structural features of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.LayoutImpl <em>Layout</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.LayoutImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLayout()
   * @generated
   */
  int LAYOUT = 27;

  /**
   * The feature id for the '<em><b>Post Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LAYOUT__POST_CONDITION = 0;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LAYOUT__BODY = 1;

  /**
   * The number of structural features of the '<em>Layout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LAYOUT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.GroupImpl <em>Group</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.GroupImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getGroup()
   * @generated
   */
  int GROUP = 28;

  /**
   * The feature id for the '<em><b>Pre Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP__PRE_CONDITION = 0;

  /**
   * The feature id for the '<em><b>Post Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP__POST_CONDITION = 1;

  /**
   * The feature id for the '<em><b>Prerequisites</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP__PREREQUISITES = 2;

  /**
   * The number of structural features of the '<em>Group</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.RepositoryConfigurationImpl <em>Repository Configuration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.RepositoryConfigurationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRepositoryConfiguration()
   * @generated
   */
  int REPOSITORY_CONFIGURATION = 29;

  /**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY_CONFIGURATION__LOCATION = 0;

  /**
   * The feature id for the '<em><b>Resolver Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY_CONFIGURATION__RESOLVER_CLASS = 1;

  /**
   * The feature id for the '<em><b>Advice</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY_CONFIGURATION__ADVICE = 2;

  /**
   * The number of structural features of the '<em>Repository Configuration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY_CONFIGURATION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.NamedAdviceImpl <em>Named Advice</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.NamedAdviceImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getNamedAdvice()
   * @generated
   */
  int NAMED_ADVICE = 30;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ADVICE__NAME = 0;

  /**
   * The feature id for the '<em><b>Advice</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ADVICE__ADVICE = 1;

  /**
   * The number of structural features of the '<em>Named Advice</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ADVICE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.AdviceImpl <em>Advice</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.AdviceImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAdvice()
   * @generated
   */
  int ADVICE = 31;

  /**
   * The number of structural features of the '<em>Advice</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.CompoundAdviceImpl <em>Compound Advice</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.CompoundAdviceImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCompoundAdvice()
   * @generated
   */
  int COMPOUND_ADVICE = 32;

  /**
   * The feature id for the '<em><b>Advice</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOUND_ADVICE__ADVICE = ADVICE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Compound Advice</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOUND_ADVICE_FEATURE_COUNT = ADVICE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.AdviceStatementImpl <em>Advice Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.AdviceStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAdviceStatement()
   * @generated
   */
  int ADVICE_STATEMENT = 33;

  /**
   * The feature id for the '<em><b>Path</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_STATEMENT__PATH = 0;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_STATEMENT__OP = 1;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_STATEMENT__VALUE = 2;

  /**
   * The feature id for the '<em><b>Advice</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_STATEMENT__ADVICE = 3;

  /**
   * The number of structural features of the '<em>Advice Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_STATEMENT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.AdvicePathImpl <em>Advice Path</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.AdvicePathImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAdvicePath()
   * @generated
   */
  int ADVICE_PATH = 34;

  /**
   * The feature id for the '<em><b>Absolute</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_PATH__ABSOLUTE = 0;

  /**
   * The feature id for the '<em><b>Selectors</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_PATH__SELECTORS = 1;

  /**
   * The number of structural features of the '<em>Advice Path</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_PATH_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.SelectorImpl <em>Selector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.SelectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSelector()
   * @generated
   */
  int SELECTOR = 35;

  /**
   * The feature id for the '<em><b>Selector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTOR__SELECTOR = 0;

  /**
   * The feature id for the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTOR__PREDICATE = 1;

  /**
   * The number of structural features of the '<em>Selector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTOR_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.NameSelectorImpl <em>Name Selector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.NameSelectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getNameSelector()
   * @generated
   */
  int NAME_SELECTOR = 36;

  /**
   * The feature id for the '<em><b>Selector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_SELECTOR__SELECTOR = SELECTOR__SELECTOR;

  /**
   * The feature id for the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_SELECTOR__PREDICATE = SELECTOR__PREDICATE;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_SELECTOR__NAME = SELECTOR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Name Selector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_SELECTOR_FEATURE_COUNT = SELECTOR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ThisSelectorImpl <em>This Selector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ThisSelectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getThisSelector()
   * @generated
   */
  int THIS_SELECTOR = 37;

  /**
   * The feature id for the '<em><b>Selector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THIS_SELECTOR__SELECTOR = SELECTOR__SELECTOR;

  /**
   * The feature id for the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THIS_SELECTOR__PREDICATE = SELECTOR__PREDICATE;

  /**
   * The number of structural features of the '<em>This Selector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THIS_SELECTOR_FEATURE_COUNT = SELECTOR_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ParentSelectorImpl <em>Parent Selector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ParentSelectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getParentSelector()
   * @generated
   */
  int PARENT_SELECTOR = 38;

  /**
   * The feature id for the '<em><b>Selector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARENT_SELECTOR__SELECTOR = SELECTOR__SELECTOR;

  /**
   * The feature id for the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARENT_SELECTOR__PREDICATE = SELECTOR__PREDICATE;

  /**
   * The number of structural features of the '<em>Parent Selector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARENT_SELECTOR_FEATURE_COUNT = SELECTOR_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ChildrenSelectorImpl <em>Children Selector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ChildrenSelectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getChildrenSelector()
   * @generated
   */
  int CHILDREN_SELECTOR = 39;

  /**
   * The feature id for the '<em><b>Selector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHILDREN_SELECTOR__SELECTOR = SELECTOR__SELECTOR;

  /**
   * The feature id for the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHILDREN_SELECTOR__PREDICATE = SELECTOR__PREDICATE;

  /**
   * The number of structural features of the '<em>Children Selector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHILDREN_SELECTOR_FEATURE_COUNT = SELECTOR_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.AncestorSelectorImpl <em>Ancestor Selector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.AncestorSelectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAncestorSelector()
   * @generated
   */
  int ANCESTOR_SELECTOR = 40;

  /**
   * The feature id for the '<em><b>Selector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANCESTOR_SELECTOR__SELECTOR = SELECTOR__SELECTOR;

  /**
   * The feature id for the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANCESTOR_SELECTOR__PREDICATE = SELECTOR__PREDICATE;

  /**
   * The number of structural features of the '<em>Ancestor Selector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANCESTOR_SELECTOR_FEATURE_COUNT = SELECTOR_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.RegexpSelectorImpl <em>Regexp Selector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.RegexpSelectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRegexpSelector()
   * @generated
   */
  int REGEXP_SELECTOR = 41;

  /**
   * The feature id for the '<em><b>Selector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REGEXP_SELECTOR__SELECTOR = SELECTOR__SELECTOR;

  /**
   * The feature id for the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REGEXP_SELECTOR__PREDICATE = SELECTOR__PREDICATE;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REGEXP_SELECTOR__PATTERN = SELECTOR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Regexp Selector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REGEXP_SELECTOR_FEATURE_COUNT = SELECTOR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FilterImpl <em>Filter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FilterImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFilter()
   * @generated
   */
  int FILTER = 42;

  /**
   * The feature id for the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTER__PREDICATE = 0;

  /**
   * The number of structural features of the '<em>Filter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTER_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PreConditionImpl <em>Pre Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PreConditionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPreCondition()
   * @generated
   */
  int PRE_CONDITION = 43;

  /**
   * The feature id for the '<em><b>Asserts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRE_CONDITION__ASSERTS = 0;

  /**
   * The number of structural features of the '<em>Pre Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRE_CONDITION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PostConditionImpl <em>Post Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PostConditionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPostCondition()
   * @generated
   */
  int POST_CONDITION = 44;

  /**
   * The feature id for the '<em><b>Asserts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POST_CONDITION__ASSERTS = 0;

  /**
   * The number of structural features of the '<em>Post Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POST_CONDITION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.AssertionExpressionImpl <em>Assertion Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.AssertionExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAssertionExpression()
   * @generated
   */
  int ASSERTION_EXPRESSION = 45;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERTION_EXPRESSION__EXPR = 0;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERTION_EXPRESSION__MESSAGE = 1;

  /**
   * The number of structural features of the '<em>Assertion Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERTION_EXPRESSION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.StatementImpl <em>Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.StatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getStatement()
   * @generated
   */
  int STATEMENT = 46;

  /**
   * The number of structural features of the '<em>Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BreakStatementImpl <em>Break Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BreakStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBreakStatement()
   * @generated
   */
  int BREAK_STATEMENT = 47;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BREAK_STATEMENT__LABEL = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Break Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BREAK_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ContinueStatementImpl <em>Continue Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ContinueStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getContinueStatement()
   * @generated
   */
  int CONTINUE_STATEMENT = 48;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTINUE_STATEMENT__LABEL = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Continue Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTINUE_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.CompoundStatementImpl <em>Compound Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.CompoundStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCompoundStatement()
   * @generated
   */
  int COMPOUND_STATEMENT = 49;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOUND_STATEMENT__STATEMENTS = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Compound Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOUND_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.StatementsImpl <em>Statements</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.StatementsImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getStatements()
   * @generated
   */
  int STATEMENTS = 50;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENTS__STATEMENTS = 0;

  /**
   * The number of structural features of the '<em>Statements</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENTS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.WhileStatementImpl <em>While Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.WhileStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWhileStatement()
   * @generated
   */
  int WHILE_STATEMENT = 51;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHILE_STATEMENT__CONDITION = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHILE_STATEMENT__BODY = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>While Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHILE_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.SwitchStatementImpl <em>Switch Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.SwitchStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSwitchStatement()
   * @generated
   */
  int SWITCH_STATEMENT = 52;

  /**
   * The feature id for the '<em><b>Switch Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_STATEMENT__SWITCH_EXPR = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Case</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_STATEMENT__CASE = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_STATEMENT__STATEMENTS = STATEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Switch Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.CaseImpl <em>Case</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.CaseImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCase()
   * @generated
   */
  int CASE = 53;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CASE__CONDITION = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CASE__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Case</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CASE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ForStatementImpl <em>For Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ForStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getForStatement()
   * @generated
   */
  int FOR_STATEMENT = 54;

  /**
   * The feature id for the '<em><b>Init</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_STATEMENT__INIT = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Regular</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_STATEMENT__REGULAR = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Cond</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_STATEMENT__COND = STATEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Iterate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_STATEMENT__ITERATE = STATEMENT_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_STATEMENT__BODY = STATEMENT_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>For Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.DoWhileStatementImpl <em>Do While Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.DoWhileStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getDoWhileStatement()
   * @generated
   */
  int DO_WHILE_STATEMENT = 55;

  /**
   * The feature id for the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DO_WHILE_STATEMENT__STATEMENT = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DO_WHILE_STATEMENT__CONDITION = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Do While Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DO_WHILE_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ReturnStatementImpl <em>Return Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ReturnStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getReturnStatement()
   * @generated
   */
  int RETURN_STATEMENT = 56;

  /**
   * The feature id for the '<em><b>Return</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RETURN_STATEMENT__RETURN = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Return Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RETURN_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FunctionStatementImpl <em>Function Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FunctionStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFunctionStatement()
   * @generated
   */
  int FUNCTION_STATEMENT = 57;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_STATEMENT__NAME = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Params</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_STATEMENT__PARAMS = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_STATEMENT__STATEMENTS = STATEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Function Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.IfStatementImpl <em>If Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.IfStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getIfStatement()
   * @generated
   */
  int IF_STATEMENT = 58;

  /**
   * The feature id for the '<em><b>Cond</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_STATEMENT__COND = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Then</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_STATEMENT__THEN = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Else</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_STATEMENT__ELSE = STATEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>If Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.LabelStatementImpl <em>Label Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.LabelStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLabelStatement()
   * @generated
   */
  int LABEL_STATEMENT = 59;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LABEL_STATEMENT__NAME = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LABEL_STATEMENT__STATEMENT = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Label Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LABEL_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ThrowStatementImpl <em>Throw Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ThrowStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getThrowStatement()
   * @generated
   */
  int THROW_STATEMENT = 60;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THROW_STATEMENT__EXPR = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Throw Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THROW_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.TryCatchStatementImpl <em>Try Catch Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.TryCatchStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getTryCatchStatement()
   * @generated
   */
  int TRY_CATCH_STATEMENT = 61;

  /**
   * The feature id for the '<em><b>Try Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRY_CATCH_STATEMENT__TRY_BLOCK = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Catch Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRY_CATCH_STATEMENT__CATCH_BLOCK = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Finally Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRY_CATCH_STATEMENT__FINALLY_BLOCK = STATEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Finally</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRY_CATCH_STATEMENT__FINALLY = STATEMENT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Try Catch Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRY_CATCH_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.CatchBlockImpl <em>Catch Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.CatchBlockImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCatchBlock()
   * @generated
   */
  int CATCH_BLOCK = 62;

  /**
   * The feature id for the '<em><b>Variable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATCH_BLOCK__VARIABLE = 0;

  /**
   * The feature id for the '<em><b>Catch Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATCH_BLOCK__CATCH_BLOCK = 1;

  /**
   * The number of structural features of the '<em>Catch Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATCH_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FinallyBlockImpl <em>Finally Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FinallyBlockImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFinallyBlock()
   * @generated
   */
  int FINALLY_BLOCK = 63;

  /**
   * The feature id for the '<em><b>Finally Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FINALLY_BLOCK__FINALLY_BLOCK = 0;

  /**
   * The number of structural features of the '<em>Finally Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FINALLY_BLOCK_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.WithStatementImpl <em>With Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.WithStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWithStatement()
   * @generated
   */
  int WITH_STATEMENT = 64;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_STATEMENT__EXPR = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_STATEMENT__STATEMENT = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>With Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.VarExpressionListImpl <em>Var Expression List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.VarExpressionListImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVarExpressionList()
   * @generated
   */
  int VAR_EXPRESSION_LIST = 65;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_EXPRESSION_LIST__EXPR = 0;

  /**
   * The number of structural features of the '<em>Var Expression List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_EXPRESSION_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ExpressionListImpl <em>Expression List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ExpressionListImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpressionList()
   * @generated
   */
  int EXPRESSION_LIST = 66;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_LIST__EXPR = 0;

  /**
   * The number of structural features of the '<em>Expression List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpression()
   * @generated
   */
  int EXPRESSION = 67;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__SVAL = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.UnaryExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUnaryExpression()
   * @generated
   */
  int UNARY_EXPRESSION = 68;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION__SVAL = EXPRESSION__SVAL;

  /**
   * The number of structural features of the '<em>Unary Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FeatureImpl <em>Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FeatureImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFeature()
   * @generated
   */
  int FEATURE = 69;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__NAME = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__VALUE = 1;

  /**
   * The number of structural features of the '<em>Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.VarExpressionImpl <em>Var Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.VarExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVarExpression()
   * @generated
   */
  int VAR_EXPRESSION = 70;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_EXPRESSION__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_EXPRESSION__NAME = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_EXPRESSION__VALUE = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Var Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.AssignmentOperationImpl <em>Assignment Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.AssignmentOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAssignmentOperation()
   * @generated
   */
  int ASSIGNMENT_OPERATION = 71;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT_OPERATION__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT_OPERATION__LEFT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT_OPERATION__OP = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT_OPERATION__RIGHT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Assignment Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT_OPERATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.IfExpressionImpl <em>If Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.IfExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getIfExpression()
   * @generated
   */
  int IF_EXPRESSION = 72;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION__CONDITION = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Then Part</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION__THEN_PART = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Else Part</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION__ELSE_PART = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>If Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BooleanOperationImpl <em>Boolean Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BooleanOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBooleanOperation()
   * @generated
   */
  int BOOLEAN_OPERATION = 73;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__LEFT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__OPERATOR = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__RIGHT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Boolean Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.OperationCallImpl <em>Operation Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.OperationCallImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getOperationCall()
   * @generated
   */
  int OPERATION_CALL = 74;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__PARAMS = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__NAME = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Post Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__POST_TARGET = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__TARGET = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Operation Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.RelationalOperationImpl <em>Relational Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.RelationalOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRelationalOperation()
   * @generated
   */
  int RELATIONAL_OPERATION = 75;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATIONAL_OPERATION__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATIONAL_OPERATION__LEFT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATIONAL_OPERATION__OPERATOR = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATIONAL_OPERATION__RIGHT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Relational Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATIONAL_OPERATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.UnaryOperationImpl <em>Unary Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.UnaryOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUnaryOperation()
   * @generated
   */
  int UNARY_OPERATION = 76;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_OPERATION__SVAL = UNARY_EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_OPERATION__NAME = UNARY_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_OPERATION__PARAMS = UNARY_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Unary Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_OPERATION_FEATURE_COUNT = UNARY_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.AtCallImpl <em>At Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.AtCallImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAtCall()
   * @generated
   */
  int AT_CALL = 77;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AT_CALL__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AT_CALL__TARGET = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Index</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AT_CALL__INDEX = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AT_CALL__PARAMS = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>At Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AT_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FeatureCallImpl <em>Feature Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FeatureCallImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFeatureCall()
   * @generated
   */
  int FEATURE_CALL = 78;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CALL__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CALL__TARGET = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CALL__TYPE = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Feature Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PropertyValueImpl <em>Property Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PropertyValueImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPropertyValue()
   * @generated
   */
  int PROPERTY_VALUE = 79;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_VALUE__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_VALUE__NAME = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Property Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_VALUE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.VariableValueImpl <em>Variable Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.VariableValueImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVariableValue()
   * @generated
   */
  int VARIABLE_VALUE = 80;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_VALUE__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_VALUE__NAME = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Variable Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_VALUE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.KeywordVariableImpl <em>Keyword Variable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.KeywordVariableImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getKeywordVariable()
   * @generated
   */
  int KEYWORD_VARIABLE = 81;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KEYWORD_VARIABLE__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KEYWORD_VARIABLE__NAME = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Keyword Variable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KEYWORD_VARIABLE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.CreatorCallImpl <em>Creator Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.CreatorCallImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCreatorCall()
   * @generated
   */
  int CREATOR_CALL = 82;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CREATOR_CALL__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CREATOR_CALL__NAME = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Target</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CREATOR_CALL__TARGET = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CREATOR_CALL__PARAMS = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Creator Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CREATOR_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.LiteralArrayImpl <em>Literal Array</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.LiteralArrayImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLiteralArray()
   * @generated
   */
  int LITERAL_ARRAY = 83;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_ARRAY__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Element</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_ARRAY__ELEMENT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Literal Array</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_ARRAY_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.LiteralObjectImpl <em>Literal Object</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.LiteralObjectImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLiteralObject()
   * @generated
   */
  int LITERAL_OBJECT = 84;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_OBJECT__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_OBJECT__FEATURES = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Literal Object</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_OBJECT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.LiteralFunctionImpl <em>Literal Function</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.LiteralFunctionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLiteralFunction()
   * @generated
   */
  int LITERAL_FUNCTION = 85;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_FUNCTION__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_FUNCTION__PARAMETERS = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_FUNCTION__STATEMENTS = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Literal Function</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_FUNCTION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BooleanLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBooleanLiteral()
   * @generated
   */
  int BOOLEAN_LITERAL = 86;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_LITERAL__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_LITERAL__VAL = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Boolean Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.IntegerLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getIntegerLiteral()
   * @generated
   */
  int INTEGER_LITERAL = 87;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_LITERAL__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Ival</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_LITERAL__IVAL = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Integer Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.NullLiteralImpl <em>Null Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.NullLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getNullLiteral()
   * @generated
   */
  int NULL_LITERAL = 88;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NULL_LITERAL__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NULL_LITERAL__VAL = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Null Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NULL_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.UndefinedLiteralImpl <em>Undefined Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.UndefinedLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUndefinedLiteral()
   * @generated
   */
  int UNDEFINED_LITERAL = 89;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNDEFINED_LITERAL__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNDEFINED_LITERAL__VAL = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Undefined Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNDEFINED_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.StringLiteralImpl <em>String Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.StringLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getStringLiteral()
   * @generated
   */
  int STRING_LITERAL = 90;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_LITERAL__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_LITERAL__VAL = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>String Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.RealLiteralImpl <em>Real Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.RealLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRealLiteral()
   * @generated
   */
  int REAL_LITERAL = 91;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_LITERAL__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_LITERAL__VAL = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Real Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.RegexpLiteralImpl <em>Regexp Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.RegexpLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRegexpLiteral()
   * @generated
   */
  int REGEXP_LITERAL = 92;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REGEXP_LITERAL__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REGEXP_LITERAL__VAL = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Regexp Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REGEXP_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.QueryLiteralImpl <em>Query Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.QueryLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getQueryLiteral()
   * @generated
   */
  int QUERY_LITERAL = 93;

  /**
   * The feature id for the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_LITERAL__SVAL = EXPRESSION__SVAL;

  /**
   * The feature id for the '<em><b>Val</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_LITERAL__VAL = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Query Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.Visibility <em>Visibility</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.Visibility
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVisibility()
   * @generated
   */
  int VISIBILITY = 94;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.ExecutionMode <em>Execution Mode</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.ExecutionMode
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExecutionMode()
   * @generated
   */
  int EXECUTION_MODE = 95;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.AssignmentOperator <em>Assignment Operator</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.AssignmentOperator
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAssignmentOperator()
   * @generated
   */
  int ASSIGNMENT_OPERATOR = 96;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.RelationalOperator <em>Relational Operator</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.RelationalOperator
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRelationalOperator()
   * @generated
   */
  int RELATIONAL_OPERATOR = 97;


  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.BeeModel <em>Bee Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Bee Model</em>'.
   * @see org.eclipse.b3.beeLang.BeeModel
   * @generated
   */
  EClass getBeeModel();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BeeModel#getImports <em>Imports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Imports</em>'.
   * @see org.eclipse.b3.beeLang.BeeModel#getImports()
   * @see #getBeeModel()
   * @generated
   */
  EReference getBeeModel_Imports();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.BeeModel#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.eclipse.b3.beeLang.BeeModel#getBody()
   * @see #getBeeModel()
   * @generated
   */
  EReference getBeeModel_Body();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Version <em>Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Version</em>'.
   * @see org.eclipse.b3.beeLang.Version
   * @generated
   */
  EClass getVersion();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Version#getOriginal <em>Original</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Original</em>'.
   * @see org.eclipse.b3.beeLang.Version#getOriginal()
   * @see #getVersion()
   * @generated
   */
  EAttribute getVersion_Original();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.VersionRange <em>Version Range</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Version Range</em>'.
   * @see org.eclipse.b3.beeLang.VersionRange
   * @generated
   */
  EClass getVersionRange();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.VersionRange#getMinLimit <em>Min Limit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Min Limit</em>'.
   * @see org.eclipse.b3.beeLang.VersionRange#getMinLimit()
   * @see #getVersionRange()
   * @generated
   */
  EAttribute getVersionRange_MinLimit();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.VersionRange#getMin <em>Min</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Min</em>'.
   * @see org.eclipse.b3.beeLang.VersionRange#getMin()
   * @see #getVersionRange()
   * @generated
   */
  EReference getVersionRange_Min();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.VersionRange#getMax <em>Max</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Max</em>'.
   * @see org.eclipse.b3.beeLang.VersionRange#getMax()
   * @see #getVersionRange()
   * @generated
   */
  EReference getVersionRange_Max();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.VersionRange#getMaxLimit <em>Max Limit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Max Limit</em>'.
   * @see org.eclipse.b3.beeLang.VersionRange#getMaxLimit()
   * @see #getVersionRange()
   * @generated
   */
  EAttribute getVersionRange_MaxLimit();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Import <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Import</em>'.
   * @see org.eclipse.b3.beeLang.Import
   * @generated
   */
  EClass getImport();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Import#getImportClass <em>Import Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Import Class</em>'.
   * @see org.eclipse.b3.beeLang.Import#getImportClass()
   * @see #getImport()
   * @generated
   */
  EAttribute getImport_ImportClass();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.BuildUnit <em>Build Unit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Build Unit</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit
   * @generated
   */
  EClass getBuildUnit();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.BuildUnit#getExecutionMode <em>Execution Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Execution Mode</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getExecutionMode()
   * @see #getBuildUnit()
   * @generated
   */
  EAttribute getBuildUnit_ExecutionMode();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.BuildUnit#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getName()
   * @see #getBuildUnit()
   * @generated
   */
  EAttribute getBuildUnit_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.BuildUnit#getVersion <em>Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Version</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getVersion()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_Version();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.b3.beeLang.BuildUnit#getImplements <em>Implements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Implements</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getImplements()
   * @see #getBuildUnit()
   * @generated
   */
  EAttribute getBuildUnit_Implements();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getCompoundPropertyOperation <em>Compound Property Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Compound Property Operation</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getCompoundPropertyOperation()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_CompoundPropertyOperation();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getProvidedCapabilities <em>Provided Capabilities</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Provided Capabilities</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getProvidedCapabilities()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_ProvidedCapabilities();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getRequiredCapabilities <em>Required Capabilities</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Required Capabilities</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getRequiredCapabilities()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_RequiredCapabilities();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getMetaRequiredCapabilities <em>Meta Required Capabilities</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Meta Required Capabilities</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getMetaRequiredCapabilities()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_MetaRequiredCapabilities();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getAdvice <em>Advice</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Advice</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getAdvice()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_Advice();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getSynchronizations <em>Synchronizations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Synchronizations</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getSynchronizations()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_Synchronizations();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getParts <em>Parts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parts</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getParts()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_Parts();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getRepositoryConfigurations <em>Repository Configurations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Repository Configurations</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getRepositoryConfigurations()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_RepositoryConfigurations();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ProvidedCapability <em>Provided Capability</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Provided Capability</em>'.
   * @see org.eclipse.b3.beeLang.ProvidedCapability
   * @generated
   */
  EClass getProvidedCapability();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ProvidedCapability#getFilter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Filter</em>'.
   * @see org.eclipse.b3.beeLang.ProvidedCapability#getFilter()
   * @see #getProvidedCapability()
   * @generated
   */
  EReference getProvidedCapability_Filter();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.ProvidedCapability#getInterface <em>Interface</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Interface</em>'.
   * @see org.eclipse.b3.beeLang.ProvidedCapability#getInterface()
   * @see #getProvidedCapability()
   * @generated
   */
  EAttribute getProvidedCapability_Interface();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.ProvidedCapability#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.ProvidedCapability#getName()
   * @see #getProvidedCapability()
   * @generated
   */
  EAttribute getProvidedCapability_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ProvidedCapability#getVersion <em>Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Version</em>'.
   * @see org.eclipse.b3.beeLang.ProvidedCapability#getVersion()
   * @see #getProvidedCapability()
   * @generated
   */
  EReference getProvidedCapability_Version();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.RequiredCapability <em>Required Capability</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Required Capability</em>'.
   * @see org.eclipse.b3.beeLang.RequiredCapability
   * @generated
   */
  EClass getRequiredCapability();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.RequiredCapability#getFilter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Filter</em>'.
   * @see org.eclipse.b3.beeLang.RequiredCapability#getFilter()
   * @see #getRequiredCapability()
   * @generated
   */
  EReference getRequiredCapability_Filter();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.RequiredCapability#getInterface <em>Interface</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Interface</em>'.
   * @see org.eclipse.b3.beeLang.RequiredCapability#getInterface()
   * @see #getRequiredCapability()
   * @generated
   */
  EAttribute getRequiredCapability_Interface();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.RequiredCapability#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.RequiredCapability#getName()
   * @see #getRequiredCapability()
   * @generated
   */
  EAttribute getRequiredCapability_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.RequiredCapability#getRange <em>Range</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Range</em>'.
   * @see org.eclipse.b3.beeLang.RequiredCapability#getRange()
   * @see #getRequiredCapability()
   * @generated
   */
  EReference getRequiredCapability_Range();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.NamedProperties <em>Named Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Properties</em>'.
   * @see org.eclipse.b3.beeLang.NamedProperties
   * @generated
   */
  EClass getNamedProperties();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.NamedProperties#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.NamedProperties#getName()
   * @see #getNamedProperties()
   * @generated
   */
  EAttribute getNamedProperties_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.NamedProperties#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.eclipse.b3.beeLang.NamedProperties#getBody()
   * @see #getNamedProperties()
   * @generated
   */
  EReference getNamedProperties_Body();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.CompoundPropertyOperation <em>Compound Property Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Compound Property Operation</em>'.
   * @see org.eclipse.b3.beeLang.CompoundPropertyOperation
   * @generated
   */
  EClass getCompoundPropertyOperation();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.CompoundPropertyOperation#getOperations <em>Operations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Operations</em>'.
   * @see org.eclipse.b3.beeLang.CompoundPropertyOperation#getOperations()
   * @see #getCompoundPropertyOperation()
   * @generated
   */
  EReference getCompoundPropertyOperation_Operations();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PropertyOperation <em>Property Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Operation</em>'.
   * @see org.eclipse.b3.beeLang.PropertyOperation
   * @generated
   */
  EClass getPropertyOperation();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.FilteredPropertyOperation <em>Filtered Property Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Filtered Property Operation</em>'.
   * @see org.eclipse.b3.beeLang.FilteredPropertyOperation
   * @generated
   */
  EClass getFilteredPropertyOperation();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.FilteredPropertyOperation#getFilter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Filter</em>'.
   * @see org.eclipse.b3.beeLang.FilteredPropertyOperation#getFilter()
   * @see #getFilteredPropertyOperation()
   * @generated
   */
  EReference getFilteredPropertyOperation_Filter();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.FilteredPropertyOperation#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.eclipse.b3.beeLang.FilteredPropertyOperation#getBody()
   * @see #getFilteredPropertyOperation()
   * @generated
   */
  EReference getFilteredPropertyOperation_Body();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.SetPropertyOperation <em>Set Property Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Property Operation</em>'.
   * @see org.eclipse.b3.beeLang.SetPropertyOperation
   * @generated
   */
  EClass getSetPropertyOperation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.SetPropertyOperation#isImmutable <em>Immutable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Immutable</em>'.
   * @see org.eclipse.b3.beeLang.SetPropertyOperation#isImmutable()
   * @see #getSetPropertyOperation()
   * @generated
   */
  EAttribute getSetPropertyOperation_Immutable();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.SetPropertyOperation#getKey <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see org.eclipse.b3.beeLang.SetPropertyOperation#getKey()
   * @see #getSetPropertyOperation()
   * @generated
   */
  EAttribute getSetPropertyOperation_Key();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.SetPropertyOperation#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see org.eclipse.b3.beeLang.SetPropertyOperation#getOp()
   * @see #getSetPropertyOperation()
   * @generated
   */
  EAttribute getSetPropertyOperation_Op();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.SetPropertyOperation#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.eclipse.b3.beeLang.SetPropertyOperation#getValue()
   * @see #getSetPropertyOperation()
   * @generated
   */
  EReference getSetPropertyOperation_Value();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.UnsetPropertyOperation <em>Unset Property Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unset Property Operation</em>'.
   * @see org.eclipse.b3.beeLang.UnsetPropertyOperation
   * @generated
   */
  EClass getUnsetPropertyOperation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.UnsetPropertyOperation#getKey <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see org.eclipse.b3.beeLang.UnsetPropertyOperation#getKey()
   * @see #getUnsetPropertyOperation()
   * @generated
   */
  EAttribute getUnsetPropertyOperation_Key();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Synchronization <em>Synchronization</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Synchronization</em>'.
   * @see org.eclipse.b3.beeLang.Synchronization
   * @generated
   */
  EClass getSynchronization();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.b3.beeLang.Synchronization#getPartrefs <em>Partrefs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Partrefs</em>'.
   * @see org.eclipse.b3.beeLang.Synchronization#getPartrefs()
   * @see #getSynchronization()
   * @generated
   */
  EAttribute getSynchronization_Partrefs();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PathGroup <em>Path Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Path Group</em>'.
   * @see org.eclipse.b3.beeLang.PathGroup
   * @generated
   */
  EClass getPathGroup();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.PathGroup#getPaths <em>Paths</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Paths</em>'.
   * @see org.eclipse.b3.beeLang.PathGroup#getPaths()
   * @see #getPathGroup()
   * @generated
   */
  EReference getPathGroup_Paths();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.PathGroup#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Annotations</em>'.
   * @see org.eclipse.b3.beeLang.PathGroup#getAnnotations()
   * @see #getPathGroup()
   * @generated
   */
  EReference getPathGroup_Annotations();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PathVectorElement <em>Path Vector Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Path Vector Element</em>'.
   * @see org.eclipse.b3.beeLang.PathVectorElement
   * @generated
   */
  EClass getPathVectorElement();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.FilteredPathVector <em>Filtered Path Vector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Filtered Path Vector</em>'.
   * @see org.eclipse.b3.beeLang.FilteredPathVector
   * @generated
   */
  EClass getFilteredPathVector();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.FilteredPathVector#getFilter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Filter</em>'.
   * @see org.eclipse.b3.beeLang.FilteredPathVector#getFilter()
   * @see #getFilteredPathVector()
   * @generated
   */
  EReference getFilteredPathVector_Filter();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.FilteredPathVector#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.eclipse.b3.beeLang.FilteredPathVector#getBody()
   * @see #getFilteredPathVector()
   * @generated
   */
  EReference getFilteredPathVector_Body();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PathVector <em>Path Vector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Path Vector</em>'.
   * @see org.eclipse.b3.beeLang.PathVector
   * @generated
   */
  EClass getPathVector();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.PathVector#getBasePath <em>Base Path</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Base Path</em>'.
   * @see org.eclipse.b3.beeLang.PathVector#getBasePath()
   * @see #getPathVector()
   * @generated
   */
  EAttribute getPathVector_BasePath();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.b3.beeLang.PathVector#getPaths <em>Paths</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Paths</em>'.
   * @see org.eclipse.b3.beeLang.PathVector#getPaths()
   * @see #getPathVector()
   * @generated
   */
  EAttribute getPathVector_Paths();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.CompoundPathVector <em>Compound Path Vector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Compound Path Vector</em>'.
   * @see org.eclipse.b3.beeLang.CompoundPathVector
   * @generated
   */
  EClass getCompoundPathVector();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.CompoundPathVector#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Body</em>'.
   * @see org.eclipse.b3.beeLang.CompoundPathVector#getBody()
   * @see #getCompoundPathVector()
   * @generated
   */
  EReference getCompoundPathVector_Body();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Prerequisite <em>Prerequisite</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Prerequisite</em>'.
   * @see org.eclipse.b3.beeLang.Prerequisite
   * @generated
   */
  EClass getPrerequisite();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Prerequisite#isSurpressed <em>Surpressed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Surpressed</em>'.
   * @see org.eclipse.b3.beeLang.Prerequisite#isSurpressed()
   * @see #getPrerequisite()
   * @generated
   */
  EAttribute getPrerequisite_Surpressed();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Prerequisite#getFilter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Filter</em>'.
   * @see org.eclipse.b3.beeLang.Prerequisite#getFilter()
   * @see #getPrerequisite()
   * @generated
   */
  EReference getPrerequisite_Filter();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Prerequisite#getAlias <em>Alias</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Alias</em>'.
   * @see org.eclipse.b3.beeLang.Prerequisite#getAlias()
   * @see #getPrerequisite()
   * @generated
   */
  EAttribute getPrerequisite_Alias();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Prerequisite#getPartReference <em>Part Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Part Reference</em>'.
   * @see org.eclipse.b3.beeLang.Prerequisite#getPartReference()
   * @see #getPrerequisite()
   * @generated
   */
  EReference getPrerequisite_PartReference();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Prerequisite#getClosure <em>Closure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Closure</em>'.
   * @see org.eclipse.b3.beeLang.Prerequisite#getClosure()
   * @see #getPrerequisite()
   * @generated
   */
  EReference getPrerequisite_Closure();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Closure <em>Closure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Closure</em>'.
   * @see org.eclipse.b3.beeLang.Closure
   * @generated
   */
  EClass getClosure();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Closure#getProperties <em>Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Properties</em>'.
   * @see org.eclipse.b3.beeLang.Closure#getProperties()
   * @see #getClosure()
   * @generated
   */
  EReference getClosure_Properties();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Closure#getAdvice <em>Advice</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Advice</em>'.
   * @see org.eclipse.b3.beeLang.Closure#getAdvice()
   * @see #getClosure()
   * @generated
   */
  EReference getClosure_Advice();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PrerequisiteEntry <em>Prerequisite Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Prerequisite Entry</em>'.
   * @see org.eclipse.b3.beeLang.PrerequisiteEntry
   * @generated
   */
  EClass getPrerequisiteEntry();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PartInSelf <em>Part In Self</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Part In Self</em>'.
   * @see org.eclipse.b3.beeLang.PartInSelf
   * @generated
   */
  EClass getPartInSelf();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.PartInSelf#getPartName <em>Part Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Part Name</em>'.
   * @see org.eclipse.b3.beeLang.PartInSelf#getPartName()
   * @see #getPartInSelf()
   * @generated
   */
  EAttribute getPartInSelf_PartName();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart <em>Capability Referenced Part</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Capability Referenced Part</em>'.
   * @see org.eclipse.b3.beeLang.CapabilityReferencedPart
   * @generated
   */
  EClass getCapabilityReferencedPart();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getInterface <em>Interface</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Interface</em>'.
   * @see org.eclipse.b3.beeLang.CapabilityReferencedPart#getInterface()
   * @see #getCapabilityReferencedPart()
   * @generated
   */
  EAttribute getCapabilityReferencedPart_Interface();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.CapabilityReferencedPart#getName()
   * @see #getCapabilityReferencedPart()
   * @generated
   */
  EAttribute getCapabilityReferencedPart_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getRange <em>Range</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Range</em>'.
   * @see org.eclipse.b3.beeLang.CapabilityReferencedPart#getRange()
   * @see #getCapabilityReferencedPart()
   * @generated
   */
  EReference getCapabilityReferencedPart_Range();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getPartName <em>Part Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Part Name</em>'.
   * @see org.eclipse.b3.beeLang.CapabilityReferencedPart#getPartName()
   * @see #getCapabilityReferencedPart()
   * @generated
   */
  EAttribute getCapabilityReferencedPart_PartName();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.CompoundReferences <em>Compound References</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Compound References</em>'.
   * @see org.eclipse.b3.beeLang.CompoundReferences
   * @generated
   */
  EClass getCompoundReferences();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.CompoundReferences#getPrerequisites <em>Prerequisites</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Prerequisites</em>'.
   * @see org.eclipse.b3.beeLang.CompoundReferences#getPrerequisites()
   * @see #getCompoundReferences()
   * @generated
   */
  EReference getCompoundReferences_Prerequisites();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Part <em>Part</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Part</em>'.
   * @see org.eclipse.b3.beeLang.Part
   * @generated
   */
  EClass getPart();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Part#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.eclipse.b3.beeLang.Part#getVisibility()
   * @see #getPart()
   * @generated
   */
  EAttribute getPart_Visibility();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Part#getExecutionMode <em>Execution Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Execution Mode</em>'.
   * @see org.eclipse.b3.beeLang.Part#getExecutionMode()
   * @see #getPart()
   * @generated
   */
  EAttribute getPart_ExecutionMode();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.Part#getProvidedCapabilities <em>Provided Capabilities</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Provided Capabilities</em>'.
   * @see org.eclipse.b3.beeLang.Part#getProvidedCapabilities()
   * @see #getPart()
   * @generated
   */
  EReference getPart_ProvidedCapabilities();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Part#getPreCondition <em>Pre Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pre Condition</em>'.
   * @see org.eclipse.b3.beeLang.Part#getPreCondition()
   * @see #getPart()
   * @generated
   */
  EReference getPart_PreCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Part#getPostCondition <em>Post Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Post Condition</em>'.
   * @see org.eclipse.b3.beeLang.Part#getPostCondition()
   * @see #getPart()
   * @generated
   */
  EReference getPart_PostCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Part#getProperties <em>Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Properties</em>'.
   * @see org.eclipse.b3.beeLang.Part#getProperties()
   * @see #getPart()
   * @generated
   */
  EReference getPart_Properties();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Part#getAdvice <em>Advice</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Advice</em>'.
   * @see org.eclipse.b3.beeLang.Part#getAdvice()
   * @see #getPart()
   * @generated
   */
  EReference getPart_Advice();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Part#getGroup <em>Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Group</em>'.
   * @see org.eclipse.b3.beeLang.Part#getGroup()
   * @see #getPart()
   * @generated
   */
  EReference getPart_Group();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Part#getLayout <em>Layout</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Layout</em>'.
   * @see org.eclipse.b3.beeLang.Part#getLayout()
   * @see #getPart()
   * @generated
   */
  EReference getPart_Layout();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Part#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statements</em>'.
   * @see org.eclipse.b3.beeLang.Part#getStatements()
   * @see #getPart()
   * @generated
   */
  EReference getPart_Statements();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Parameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter</em>'.
   * @see org.eclipse.b3.beeLang.Parameter
   * @generated
   */
  EClass getParameter();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Parameter#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Val</em>'.
   * @see org.eclipse.b3.beeLang.Parameter#getVal()
   * @see #getParameter()
   * @generated
   */
  EReference getParameter_Val();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Layout <em>Layout</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Layout</em>'.
   * @see org.eclipse.b3.beeLang.Layout
   * @generated
   */
  EClass getLayout();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Layout#getPostCondition <em>Post Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Post Condition</em>'.
   * @see org.eclipse.b3.beeLang.Layout#getPostCondition()
   * @see #getLayout()
   * @generated
   */
  EReference getLayout_PostCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Layout#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.eclipse.b3.beeLang.Layout#getBody()
   * @see #getLayout()
   * @generated
   */
  EReference getLayout_Body();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Group <em>Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Group</em>'.
   * @see org.eclipse.b3.beeLang.Group
   * @generated
   */
  EClass getGroup();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Group#getPreCondition <em>Pre Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pre Condition</em>'.
   * @see org.eclipse.b3.beeLang.Group#getPreCondition()
   * @see #getGroup()
   * @generated
   */
  EReference getGroup_PreCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Group#getPostCondition <em>Post Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Post Condition</em>'.
   * @see org.eclipse.b3.beeLang.Group#getPostCondition()
   * @see #getGroup()
   * @generated
   */
  EReference getGroup_PostCondition();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.Group#getPrerequisites <em>Prerequisites</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Prerequisites</em>'.
   * @see org.eclipse.b3.beeLang.Group#getPrerequisites()
   * @see #getGroup()
   * @generated
   */
  EReference getGroup_Prerequisites();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.RepositoryConfiguration <em>Repository Configuration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Repository Configuration</em>'.
   * @see org.eclipse.b3.beeLang.RepositoryConfiguration
   * @generated
   */
  EClass getRepositoryConfiguration();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.RepositoryConfiguration#getLocation <em>Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Location</em>'.
   * @see org.eclipse.b3.beeLang.RepositoryConfiguration#getLocation()
   * @see #getRepositoryConfiguration()
   * @generated
   */
  EAttribute getRepositoryConfiguration_Location();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.RepositoryConfiguration#getResolverClass <em>Resolver Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Resolver Class</em>'.
   * @see org.eclipse.b3.beeLang.RepositoryConfiguration#getResolverClass()
   * @see #getRepositoryConfiguration()
   * @generated
   */
  EAttribute getRepositoryConfiguration_ResolverClass();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.RepositoryConfiguration#getAdvice <em>Advice</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Advice</em>'.
   * @see org.eclipse.b3.beeLang.RepositoryConfiguration#getAdvice()
   * @see #getRepositoryConfiguration()
   * @generated
   */
  EReference getRepositoryConfiguration_Advice();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.NamedAdvice <em>Named Advice</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Advice</em>'.
   * @see org.eclipse.b3.beeLang.NamedAdvice
   * @generated
   */
  EClass getNamedAdvice();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.NamedAdvice#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.NamedAdvice#getName()
   * @see #getNamedAdvice()
   * @generated
   */
  EAttribute getNamedAdvice_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.NamedAdvice#getAdvice <em>Advice</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Advice</em>'.
   * @see org.eclipse.b3.beeLang.NamedAdvice#getAdvice()
   * @see #getNamedAdvice()
   * @generated
   */
  EReference getNamedAdvice_Advice();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Advice <em>Advice</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Advice</em>'.
   * @see org.eclipse.b3.beeLang.Advice
   * @generated
   */
  EClass getAdvice();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.CompoundAdvice <em>Compound Advice</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Compound Advice</em>'.
   * @see org.eclipse.b3.beeLang.CompoundAdvice
   * @generated
   */
  EClass getCompoundAdvice();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.CompoundAdvice#getAdvice <em>Advice</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Advice</em>'.
   * @see org.eclipse.b3.beeLang.CompoundAdvice#getAdvice()
   * @see #getCompoundAdvice()
   * @generated
   */
  EReference getCompoundAdvice_Advice();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.AdviceStatement <em>Advice Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Advice Statement</em>'.
   * @see org.eclipse.b3.beeLang.AdviceStatement
   * @generated
   */
  EClass getAdviceStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.AdviceStatement#getPath <em>Path</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Path</em>'.
   * @see org.eclipse.b3.beeLang.AdviceStatement#getPath()
   * @see #getAdviceStatement()
   * @generated
   */
  EReference getAdviceStatement_Path();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.AdviceStatement#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see org.eclipse.b3.beeLang.AdviceStatement#getOp()
   * @see #getAdviceStatement()
   * @generated
   */
  EAttribute getAdviceStatement_Op();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.AdviceStatement#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.eclipse.b3.beeLang.AdviceStatement#getValue()
   * @see #getAdviceStatement()
   * @generated
   */
  EReference getAdviceStatement_Value();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.AdviceStatement#getAdvice <em>Advice</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Advice</em>'.
   * @see org.eclipse.b3.beeLang.AdviceStatement#getAdvice()
   * @see #getAdviceStatement()
   * @generated
   */
  EReference getAdviceStatement_Advice();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.AdvicePath <em>Advice Path</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Advice Path</em>'.
   * @see org.eclipse.b3.beeLang.AdvicePath
   * @generated
   */
  EClass getAdvicePath();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.AdvicePath#isAbsolute <em>Absolute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Absolute</em>'.
   * @see org.eclipse.b3.beeLang.AdvicePath#isAbsolute()
   * @see #getAdvicePath()
   * @generated
   */
  EAttribute getAdvicePath_Absolute();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.AdvicePath#getSelectors <em>Selectors</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Selectors</em>'.
   * @see org.eclipse.b3.beeLang.AdvicePath#getSelectors()
   * @see #getAdvicePath()
   * @generated
   */
  EReference getAdvicePath_Selectors();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Selector <em>Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Selector</em>'.
   * @see org.eclipse.b3.beeLang.Selector
   * @generated
   */
  EClass getSelector();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Selector#getSelector <em>Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Selector</em>'.
   * @see org.eclipse.b3.beeLang.Selector#getSelector()
   * @see #getSelector()
   * @generated
   */
  EReference getSelector_Selector();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Selector#getPredicate <em>Predicate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Predicate</em>'.
   * @see org.eclipse.b3.beeLang.Selector#getPredicate()
   * @see #getSelector()
   * @generated
   */
  EReference getSelector_Predicate();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.NameSelector <em>Name Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Name Selector</em>'.
   * @see org.eclipse.b3.beeLang.NameSelector
   * @generated
   */
  EClass getNameSelector();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.NameSelector#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.NameSelector#getName()
   * @see #getNameSelector()
   * @generated
   */
  EAttribute getNameSelector_Name();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ThisSelector <em>This Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>This Selector</em>'.
   * @see org.eclipse.b3.beeLang.ThisSelector
   * @generated
   */
  EClass getThisSelector();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ParentSelector <em>Parent Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parent Selector</em>'.
   * @see org.eclipse.b3.beeLang.ParentSelector
   * @generated
   */
  EClass getParentSelector();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ChildrenSelector <em>Children Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Children Selector</em>'.
   * @see org.eclipse.b3.beeLang.ChildrenSelector
   * @generated
   */
  EClass getChildrenSelector();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.AncestorSelector <em>Ancestor Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ancestor Selector</em>'.
   * @see org.eclipse.b3.beeLang.AncestorSelector
   * @generated
   */
  EClass getAncestorSelector();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.RegexpSelector <em>Regexp Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Regexp Selector</em>'.
   * @see org.eclipse.b3.beeLang.RegexpSelector
   * @generated
   */
  EClass getRegexpSelector();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.RegexpSelector#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pattern</em>'.
   * @see org.eclipse.b3.beeLang.RegexpSelector#getPattern()
   * @see #getRegexpSelector()
   * @generated
   */
  EAttribute getRegexpSelector_Pattern();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Filter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Filter</em>'.
   * @see org.eclipse.b3.beeLang.Filter
   * @generated
   */
  EClass getFilter();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Filter#getPredicate <em>Predicate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Predicate</em>'.
   * @see org.eclipse.b3.beeLang.Filter#getPredicate()
   * @see #getFilter()
   * @generated
   */
  EReference getFilter_Predicate();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PreCondition <em>Pre Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pre Condition</em>'.
   * @see org.eclipse.b3.beeLang.PreCondition
   * @generated
   */
  EClass getPreCondition();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.PreCondition#getAsserts <em>Asserts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Asserts</em>'.
   * @see org.eclipse.b3.beeLang.PreCondition#getAsserts()
   * @see #getPreCondition()
   * @generated
   */
  EReference getPreCondition_Asserts();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PostCondition <em>Post Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Post Condition</em>'.
   * @see org.eclipse.b3.beeLang.PostCondition
   * @generated
   */
  EClass getPostCondition();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.PostCondition#getAsserts <em>Asserts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Asserts</em>'.
   * @see org.eclipse.b3.beeLang.PostCondition#getAsserts()
   * @see #getPostCondition()
   * @generated
   */
  EReference getPostCondition_Asserts();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.AssertionExpression <em>Assertion Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Assertion Expression</em>'.
   * @see org.eclipse.b3.beeLang.AssertionExpression
   * @generated
   */
  EClass getAssertionExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.AssertionExpression#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.AssertionExpression#getExpr()
   * @see #getAssertionExpression()
   * @generated
   */
  EReference getAssertionExpression_Expr();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.AssertionExpression#getMessage <em>Message</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Message</em>'.
   * @see org.eclipse.b3.beeLang.AssertionExpression#getMessage()
   * @see #getAssertionExpression()
   * @generated
   */
  EAttribute getAssertionExpression_Message();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Statement <em>Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Statement</em>'.
   * @see org.eclipse.b3.beeLang.Statement
   * @generated
   */
  EClass getStatement();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.BreakStatement <em>Break Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Break Statement</em>'.
   * @see org.eclipse.b3.beeLang.BreakStatement
   * @generated
   */
  EClass getBreakStatement();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.BreakStatement#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see org.eclipse.b3.beeLang.BreakStatement#getLabel()
   * @see #getBreakStatement()
   * @generated
   */
  EAttribute getBreakStatement_Label();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ContinueStatement <em>Continue Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Continue Statement</em>'.
   * @see org.eclipse.b3.beeLang.ContinueStatement
   * @generated
   */
  EClass getContinueStatement();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.ContinueStatement#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see org.eclipse.b3.beeLang.ContinueStatement#getLabel()
   * @see #getContinueStatement()
   * @generated
   */
  EAttribute getContinueStatement_Label();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.CompoundStatement <em>Compound Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Compound Statement</em>'.
   * @see org.eclipse.b3.beeLang.CompoundStatement
   * @generated
   */
  EClass getCompoundStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.CompoundStatement#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statements</em>'.
   * @see org.eclipse.b3.beeLang.CompoundStatement#getStatements()
   * @see #getCompoundStatement()
   * @generated
   */
  EReference getCompoundStatement_Statements();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Statements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Statements</em>'.
   * @see org.eclipse.b3.beeLang.Statements
   * @generated
   */
  EClass getStatements();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.Statements#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.eclipse.b3.beeLang.Statements#getStatements()
   * @see #getStatements()
   * @generated
   */
  EReference getStatements_Statements();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.WhileStatement <em>While Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>While Statement</em>'.
   * @see org.eclipse.b3.beeLang.WhileStatement
   * @generated
   */
  EClass getWhileStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.WhileStatement#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see org.eclipse.b3.beeLang.WhileStatement#getCondition()
   * @see #getWhileStatement()
   * @generated
   */
  EReference getWhileStatement_Condition();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.WhileStatement#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.eclipse.b3.beeLang.WhileStatement#getBody()
   * @see #getWhileStatement()
   * @generated
   */
  EReference getWhileStatement_Body();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.SwitchStatement <em>Switch Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Switch Statement</em>'.
   * @see org.eclipse.b3.beeLang.SwitchStatement
   * @generated
   */
  EClass getSwitchStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.SwitchStatement#getSwitchExpr <em>Switch Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Switch Expr</em>'.
   * @see org.eclipse.b3.beeLang.SwitchStatement#getSwitchExpr()
   * @see #getSwitchStatement()
   * @generated
   */
  EReference getSwitchStatement_SwitchExpr();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.SwitchStatement#getCase <em>Case</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Case</em>'.
   * @see org.eclipse.b3.beeLang.SwitchStatement#getCase()
   * @see #getSwitchStatement()
   * @generated
   */
  EReference getSwitchStatement_Case();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.SwitchStatement#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statements</em>'.
   * @see org.eclipse.b3.beeLang.SwitchStatement#getStatements()
   * @see #getSwitchStatement()
   * @generated
   */
  EReference getSwitchStatement_Statements();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Case <em>Case</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Case</em>'.
   * @see org.eclipse.b3.beeLang.Case
   * @generated
   */
  EClass getCase();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Case#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see org.eclipse.b3.beeLang.Case#getCondition()
   * @see #getCase()
   * @generated
   */
  EReference getCase_Condition();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Case#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statements</em>'.
   * @see org.eclipse.b3.beeLang.Case#getStatements()
   * @see #getCase()
   * @generated
   */
  EReference getCase_Statements();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ForStatement <em>For Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>For Statement</em>'.
   * @see org.eclipse.b3.beeLang.ForStatement
   * @generated
   */
  EClass getForStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ForStatement#getInit <em>Init</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Init</em>'.
   * @see org.eclipse.b3.beeLang.ForStatement#getInit()
   * @see #getForStatement()
   * @generated
   */
  EReference getForStatement_Init();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.ForStatement#isRegular <em>Regular</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Regular</em>'.
   * @see org.eclipse.b3.beeLang.ForStatement#isRegular()
   * @see #getForStatement()
   * @generated
   */
  EAttribute getForStatement_Regular();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ForStatement#getCond <em>Cond</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cond</em>'.
   * @see org.eclipse.b3.beeLang.ForStatement#getCond()
   * @see #getForStatement()
   * @generated
   */
  EReference getForStatement_Cond();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ForStatement#getIterate <em>Iterate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Iterate</em>'.
   * @see org.eclipse.b3.beeLang.ForStatement#getIterate()
   * @see #getForStatement()
   * @generated
   */
  EReference getForStatement_Iterate();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ForStatement#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.eclipse.b3.beeLang.ForStatement#getBody()
   * @see #getForStatement()
   * @generated
   */
  EReference getForStatement_Body();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.DoWhileStatement <em>Do While Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Do While Statement</em>'.
   * @see org.eclipse.b3.beeLang.DoWhileStatement
   * @generated
   */
  EClass getDoWhileStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.DoWhileStatement#getStatement <em>Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statement</em>'.
   * @see org.eclipse.b3.beeLang.DoWhileStatement#getStatement()
   * @see #getDoWhileStatement()
   * @generated
   */
  EReference getDoWhileStatement_Statement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.DoWhileStatement#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see org.eclipse.b3.beeLang.DoWhileStatement#getCondition()
   * @see #getDoWhileStatement()
   * @generated
   */
  EReference getDoWhileStatement_Condition();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ReturnStatement <em>Return Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Return Statement</em>'.
   * @see org.eclipse.b3.beeLang.ReturnStatement
   * @generated
   */
  EClass getReturnStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ReturnStatement#getReturn <em>Return</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Return</em>'.
   * @see org.eclipse.b3.beeLang.ReturnStatement#getReturn()
   * @see #getReturnStatement()
   * @generated
   */
  EReference getReturnStatement_Return();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.FunctionStatement <em>Function Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Statement</em>'.
   * @see org.eclipse.b3.beeLang.FunctionStatement
   * @generated
   */
  EClass getFunctionStatement();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.FunctionStatement#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.FunctionStatement#getName()
   * @see #getFunctionStatement()
   * @generated
   */
  EAttribute getFunctionStatement_Name();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.b3.beeLang.FunctionStatement#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Params</em>'.
   * @see org.eclipse.b3.beeLang.FunctionStatement#getParams()
   * @see #getFunctionStatement()
   * @generated
   */
  EAttribute getFunctionStatement_Params();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.FunctionStatement#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statements</em>'.
   * @see org.eclipse.b3.beeLang.FunctionStatement#getStatements()
   * @see #getFunctionStatement()
   * @generated
   */
  EReference getFunctionStatement_Statements();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.IfStatement <em>If Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>If Statement</em>'.
   * @see org.eclipse.b3.beeLang.IfStatement
   * @generated
   */
  EClass getIfStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.IfStatement#getCond <em>Cond</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cond</em>'.
   * @see org.eclipse.b3.beeLang.IfStatement#getCond()
   * @see #getIfStatement()
   * @generated
   */
  EReference getIfStatement_Cond();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.IfStatement#getThen <em>Then</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Then</em>'.
   * @see org.eclipse.b3.beeLang.IfStatement#getThen()
   * @see #getIfStatement()
   * @generated
   */
  EReference getIfStatement_Then();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.IfStatement#getElse <em>Else</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Else</em>'.
   * @see org.eclipse.b3.beeLang.IfStatement#getElse()
   * @see #getIfStatement()
   * @generated
   */
  EReference getIfStatement_Else();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.LabelStatement <em>Label Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Label Statement</em>'.
   * @see org.eclipse.b3.beeLang.LabelStatement
   * @generated
   */
  EClass getLabelStatement();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.LabelStatement#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.LabelStatement#getName()
   * @see #getLabelStatement()
   * @generated
   */
  EAttribute getLabelStatement_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.LabelStatement#getStatement <em>Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statement</em>'.
   * @see org.eclipse.b3.beeLang.LabelStatement#getStatement()
   * @see #getLabelStatement()
   * @generated
   */
  EReference getLabelStatement_Statement();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ThrowStatement <em>Throw Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Throw Statement</em>'.
   * @see org.eclipse.b3.beeLang.ThrowStatement
   * @generated
   */
  EClass getThrowStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ThrowStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.ThrowStatement#getExpr()
   * @see #getThrowStatement()
   * @generated
   */
  EReference getThrowStatement_Expr();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.TryCatchStatement <em>Try Catch Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Try Catch Statement</em>'.
   * @see org.eclipse.b3.beeLang.TryCatchStatement
   * @generated
   */
  EClass getTryCatchStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.TryCatchStatement#getTryBlock <em>Try Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Try Block</em>'.
   * @see org.eclipse.b3.beeLang.TryCatchStatement#getTryBlock()
   * @see #getTryCatchStatement()
   * @generated
   */
  EReference getTryCatchStatement_TryBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.TryCatchStatement#getCatchBlock <em>Catch Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Catch Block</em>'.
   * @see org.eclipse.b3.beeLang.TryCatchStatement#getCatchBlock()
   * @see #getTryCatchStatement()
   * @generated
   */
  EReference getTryCatchStatement_CatchBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.TryCatchStatement#getFinallyBlock <em>Finally Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Finally Block</em>'.
   * @see org.eclipse.b3.beeLang.TryCatchStatement#getFinallyBlock()
   * @see #getTryCatchStatement()
   * @generated
   */
  EReference getTryCatchStatement_FinallyBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.TryCatchStatement#getFinally <em>Finally</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Finally</em>'.
   * @see org.eclipse.b3.beeLang.TryCatchStatement#getFinally()
   * @see #getTryCatchStatement()
   * @generated
   */
  EReference getTryCatchStatement_Finally();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.CatchBlock <em>Catch Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Catch Block</em>'.
   * @see org.eclipse.b3.beeLang.CatchBlock
   * @generated
   */
  EClass getCatchBlock();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.CatchBlock#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Variable</em>'.
   * @see org.eclipse.b3.beeLang.CatchBlock#getVariable()
   * @see #getCatchBlock()
   * @generated
   */
  EAttribute getCatchBlock_Variable();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.CatchBlock#getCatchBlock <em>Catch Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Catch Block</em>'.
   * @see org.eclipse.b3.beeLang.CatchBlock#getCatchBlock()
   * @see #getCatchBlock()
   * @generated
   */
  EReference getCatchBlock_CatchBlock();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.FinallyBlock <em>Finally Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Finally Block</em>'.
   * @see org.eclipse.b3.beeLang.FinallyBlock
   * @generated
   */
  EClass getFinallyBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.FinallyBlock#getFinallyBlock <em>Finally Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Finally Block</em>'.
   * @see org.eclipse.b3.beeLang.FinallyBlock#getFinallyBlock()
   * @see #getFinallyBlock()
   * @generated
   */
  EReference getFinallyBlock_FinallyBlock();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.WithStatement <em>With Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>With Statement</em>'.
   * @see org.eclipse.b3.beeLang.WithStatement
   * @generated
   */
  EClass getWithStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.WithStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.WithStatement#getExpr()
   * @see #getWithStatement()
   * @generated
   */
  EReference getWithStatement_Expr();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.WithStatement#getStatement <em>Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statement</em>'.
   * @see org.eclipse.b3.beeLang.WithStatement#getStatement()
   * @see #getWithStatement()
   * @generated
   */
  EReference getWithStatement_Statement();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.VarExpressionList <em>Var Expression List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Var Expression List</em>'.
   * @see org.eclipse.b3.beeLang.VarExpressionList
   * @generated
   */
  EClass getVarExpressionList();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.VarExpressionList#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.VarExpressionList#getExpr()
   * @see #getVarExpressionList()
   * @generated
   */
  EReference getVarExpressionList_Expr();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ExpressionList <em>Expression List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression List</em>'.
   * @see org.eclipse.b3.beeLang.ExpressionList
   * @generated
   */
  EClass getExpressionList();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ExpressionList#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.ExpressionList#getExpr()
   * @see #getExpressionList()
   * @generated
   */
  EReference getExpressionList_Expr();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression</em>'.
   * @see org.eclipse.b3.beeLang.Expression
   * @generated
   */
  EClass getExpression();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Expression#getSval <em>Sval</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sval</em>'.
   * @see org.eclipse.b3.beeLang.Expression#getSval()
   * @see #getExpression()
   * @generated
   */
  EAttribute getExpression_Sval();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.UnaryExpression <em>Unary Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unary Expression</em>'.
   * @see org.eclipse.b3.beeLang.UnaryExpression
   * @generated
   */
  EClass getUnaryExpression();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Feature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature</em>'.
   * @see org.eclipse.b3.beeLang.Feature
   * @generated
   */
  EClass getFeature();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Feature#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.Feature#getName()
   * @see #getFeature()
   * @generated
   */
  EAttribute getFeature_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Feature#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.eclipse.b3.beeLang.Feature#getValue()
   * @see #getFeature()
   * @generated
   */
  EReference getFeature_Value();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.VarExpression <em>Var Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Var Expression</em>'.
   * @see org.eclipse.b3.beeLang.VarExpression
   * @generated
   */
  EClass getVarExpression();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.VarExpression#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.VarExpression#getName()
   * @see #getVarExpression()
   * @generated
   */
  EAttribute getVarExpression_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.VarExpression#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.eclipse.b3.beeLang.VarExpression#getValue()
   * @see #getVarExpression()
   * @generated
   */
  EReference getVarExpression_Value();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.AssignmentOperation <em>Assignment Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Assignment Operation</em>'.
   * @see org.eclipse.b3.beeLang.AssignmentOperation
   * @generated
   */
  EClass getAssignmentOperation();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.AssignmentOperation#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.eclipse.b3.beeLang.AssignmentOperation#getLeft()
   * @see #getAssignmentOperation()
   * @generated
   */
  EReference getAssignmentOperation_Left();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.AssignmentOperation#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see org.eclipse.b3.beeLang.AssignmentOperation#getOp()
   * @see #getAssignmentOperation()
   * @generated
   */
  EAttribute getAssignmentOperation_Op();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.AssignmentOperation#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.eclipse.b3.beeLang.AssignmentOperation#getRight()
   * @see #getAssignmentOperation()
   * @generated
   */
  EReference getAssignmentOperation_Right();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.IfExpression <em>If Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>If Expression</em>'.
   * @see org.eclipse.b3.beeLang.IfExpression
   * @generated
   */
  EClass getIfExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.IfExpression#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see org.eclipse.b3.beeLang.IfExpression#getCondition()
   * @see #getIfExpression()
   * @generated
   */
  EReference getIfExpression_Condition();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.IfExpression#getThenPart <em>Then Part</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Then Part</em>'.
   * @see org.eclipse.b3.beeLang.IfExpression#getThenPart()
   * @see #getIfExpression()
   * @generated
   */
  EReference getIfExpression_ThenPart();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.IfExpression#getElsePart <em>Else Part</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Else Part</em>'.
   * @see org.eclipse.b3.beeLang.IfExpression#getElsePart()
   * @see #getIfExpression()
   * @generated
   */
  EReference getIfExpression_ElsePart();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.BooleanOperation <em>Boolean Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean Operation</em>'.
   * @see org.eclipse.b3.beeLang.BooleanOperation
   * @generated
   */
  EClass getBooleanOperation();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.BooleanOperation#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.eclipse.b3.beeLang.BooleanOperation#getLeft()
   * @see #getBooleanOperation()
   * @generated
   */
  EReference getBooleanOperation_Left();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.BooleanOperation#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operator</em>'.
   * @see org.eclipse.b3.beeLang.BooleanOperation#getOperator()
   * @see #getBooleanOperation()
   * @generated
   */
  EAttribute getBooleanOperation_Operator();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.BooleanOperation#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.eclipse.b3.beeLang.BooleanOperation#getRight()
   * @see #getBooleanOperation()
   * @generated
   */
  EReference getBooleanOperation_Right();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.OperationCall <em>Operation Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Call</em>'.
   * @see org.eclipse.b3.beeLang.OperationCall
   * @generated
   */
  EClass getOperationCall();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.OperationCall#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.eclipse.b3.beeLang.OperationCall#getParams()
   * @see #getOperationCall()
   * @generated
   */
  EReference getOperationCall_Params();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.OperationCall#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.OperationCall#getName()
   * @see #getOperationCall()
   * @generated
   */
  EAttribute getOperationCall_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.OperationCall#getPostTarget <em>Post Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Post Target</em>'.
   * @see org.eclipse.b3.beeLang.OperationCall#getPostTarget()
   * @see #getOperationCall()
   * @generated
   */
  EReference getOperationCall_PostTarget();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.OperationCall#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target</em>'.
   * @see org.eclipse.b3.beeLang.OperationCall#getTarget()
   * @see #getOperationCall()
   * @generated
   */
  EReference getOperationCall_Target();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.RelationalOperation <em>Relational Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Relational Operation</em>'.
   * @see org.eclipse.b3.beeLang.RelationalOperation
   * @generated
   */
  EClass getRelationalOperation();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.RelationalOperation#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.eclipse.b3.beeLang.RelationalOperation#getLeft()
   * @see #getRelationalOperation()
   * @generated
   */
  EReference getRelationalOperation_Left();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.RelationalOperation#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operator</em>'.
   * @see org.eclipse.b3.beeLang.RelationalOperation#getOperator()
   * @see #getRelationalOperation()
   * @generated
   */
  EAttribute getRelationalOperation_Operator();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.RelationalOperation#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.eclipse.b3.beeLang.RelationalOperation#getRight()
   * @see #getRelationalOperation()
   * @generated
   */
  EReference getRelationalOperation_Right();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.UnaryOperation <em>Unary Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unary Operation</em>'.
   * @see org.eclipse.b3.beeLang.UnaryOperation
   * @generated
   */
  EClass getUnaryOperation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.UnaryOperation#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.UnaryOperation#getName()
   * @see #getUnaryOperation()
   * @generated
   */
  EAttribute getUnaryOperation_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.UnaryOperation#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.eclipse.b3.beeLang.UnaryOperation#getParams()
   * @see #getUnaryOperation()
   * @generated
   */
  EReference getUnaryOperation_Params();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.AtCall <em>At Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>At Call</em>'.
   * @see org.eclipse.b3.beeLang.AtCall
   * @generated
   */
  EClass getAtCall();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.AtCall#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target</em>'.
   * @see org.eclipse.b3.beeLang.AtCall#getTarget()
   * @see #getAtCall()
   * @generated
   */
  EReference getAtCall_Target();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.AtCall#getIndex <em>Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Index</em>'.
   * @see org.eclipse.b3.beeLang.AtCall#getIndex()
   * @see #getAtCall()
   * @generated
   */
  EReference getAtCall_Index();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.AtCall#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.eclipse.b3.beeLang.AtCall#getParams()
   * @see #getAtCall()
   * @generated
   */
  EReference getAtCall_Params();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.FeatureCall <em>Feature Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Call</em>'.
   * @see org.eclipse.b3.beeLang.FeatureCall
   * @generated
   */
  EClass getFeatureCall();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.FeatureCall#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target</em>'.
   * @see org.eclipse.b3.beeLang.FeatureCall#getTarget()
   * @see #getFeatureCall()
   * @generated
   */
  EReference getFeatureCall_Target();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.FeatureCall#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.eclipse.b3.beeLang.FeatureCall#getType()
   * @see #getFeatureCall()
   * @generated
   */
  EAttribute getFeatureCall_Type();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PropertyValue <em>Property Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Value</em>'.
   * @see org.eclipse.b3.beeLang.PropertyValue
   * @generated
   */
  EClass getPropertyValue();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.PropertyValue#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.PropertyValue#getName()
   * @see #getPropertyValue()
   * @generated
   */
  EAttribute getPropertyValue_Name();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.VariableValue <em>Variable Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variable Value</em>'.
   * @see org.eclipse.b3.beeLang.VariableValue
   * @generated
   */
  EClass getVariableValue();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.VariableValue#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.VariableValue#getName()
   * @see #getVariableValue()
   * @generated
   */
  EAttribute getVariableValue_Name();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.KeywordVariable <em>Keyword Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Keyword Variable</em>'.
   * @see org.eclipse.b3.beeLang.KeywordVariable
   * @generated
   */
  EClass getKeywordVariable();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.KeywordVariable#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.KeywordVariable#getName()
   * @see #getKeywordVariable()
   * @generated
   */
  EAttribute getKeywordVariable_Name();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.CreatorCall <em>Creator Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Creator Call</em>'.
   * @see org.eclipse.b3.beeLang.CreatorCall
   * @generated
   */
  EClass getCreatorCall();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.CreatorCall#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.CreatorCall#getName()
   * @see #getCreatorCall()
   * @generated
   */
  EAttribute getCreatorCall_Name();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.CreatorCall#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target</em>'.
   * @see org.eclipse.b3.beeLang.CreatorCall#getTarget()
   * @see #getCreatorCall()
   * @generated
   */
  EAttribute getCreatorCall_Target();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.CreatorCall#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.eclipse.b3.beeLang.CreatorCall#getParams()
   * @see #getCreatorCall()
   * @generated
   */
  EReference getCreatorCall_Params();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.LiteralArray <em>Literal Array</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Literal Array</em>'.
   * @see org.eclipse.b3.beeLang.LiteralArray
   * @generated
   */
  EClass getLiteralArray();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.LiteralArray#getElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Element</em>'.
   * @see org.eclipse.b3.beeLang.LiteralArray#getElement()
   * @see #getLiteralArray()
   * @generated
   */
  EReference getLiteralArray_Element();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.LiteralObject <em>Literal Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Literal Object</em>'.
   * @see org.eclipse.b3.beeLang.LiteralObject
   * @generated
   */
  EClass getLiteralObject();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.LiteralObject#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see org.eclipse.b3.beeLang.LiteralObject#getFeatures()
   * @see #getLiteralObject()
   * @generated
   */
  EReference getLiteralObject_Features();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.LiteralFunction <em>Literal Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Literal Function</em>'.
   * @see org.eclipse.b3.beeLang.LiteralFunction
   * @generated
   */
  EClass getLiteralFunction();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.b3.beeLang.LiteralFunction#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Parameters</em>'.
   * @see org.eclipse.b3.beeLang.LiteralFunction#getParameters()
   * @see #getLiteralFunction()
   * @generated
   */
  EAttribute getLiteralFunction_Parameters();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.LiteralFunction#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statements</em>'.
   * @see org.eclipse.b3.beeLang.LiteralFunction#getStatements()
   * @see #getLiteralFunction()
   * @generated
   */
  EReference getLiteralFunction_Statements();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.BooleanLiteral <em>Boolean Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean Literal</em>'.
   * @see org.eclipse.b3.beeLang.BooleanLiteral
   * @generated
   */
  EClass getBooleanLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.BooleanLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.eclipse.b3.beeLang.BooleanLiteral#getVal()
   * @see #getBooleanLiteral()
   * @generated
   */
  EAttribute getBooleanLiteral_Val();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.IntegerLiteral <em>Integer Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Integer Literal</em>'.
   * @see org.eclipse.b3.beeLang.IntegerLiteral
   * @generated
   */
  EClass getIntegerLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.IntegerLiteral#getIval <em>Ival</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ival</em>'.
   * @see org.eclipse.b3.beeLang.IntegerLiteral#getIval()
   * @see #getIntegerLiteral()
   * @generated
   */
  EAttribute getIntegerLiteral_Ival();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.NullLiteral <em>Null Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Null Literal</em>'.
   * @see org.eclipse.b3.beeLang.NullLiteral
   * @generated
   */
  EClass getNullLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.NullLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.eclipse.b3.beeLang.NullLiteral#getVal()
   * @see #getNullLiteral()
   * @generated
   */
  EAttribute getNullLiteral_Val();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.UndefinedLiteral <em>Undefined Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Undefined Literal</em>'.
   * @see org.eclipse.b3.beeLang.UndefinedLiteral
   * @generated
   */
  EClass getUndefinedLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.UndefinedLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.eclipse.b3.beeLang.UndefinedLiteral#getVal()
   * @see #getUndefinedLiteral()
   * @generated
   */
  EAttribute getUndefinedLiteral_Val();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.StringLiteral <em>String Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Literal</em>'.
   * @see org.eclipse.b3.beeLang.StringLiteral
   * @generated
   */
  EClass getStringLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.StringLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.eclipse.b3.beeLang.StringLiteral#getVal()
   * @see #getStringLiteral()
   * @generated
   */
  EAttribute getStringLiteral_Val();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.RealLiteral <em>Real Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Real Literal</em>'.
   * @see org.eclipse.b3.beeLang.RealLiteral
   * @generated
   */
  EClass getRealLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.RealLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.eclipse.b3.beeLang.RealLiteral#getVal()
   * @see #getRealLiteral()
   * @generated
   */
  EAttribute getRealLiteral_Val();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.RegexpLiteral <em>Regexp Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Regexp Literal</em>'.
   * @see org.eclipse.b3.beeLang.RegexpLiteral
   * @generated
   */
  EClass getRegexpLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.RegexpLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.eclipse.b3.beeLang.RegexpLiteral#getVal()
   * @see #getRegexpLiteral()
   * @generated
   */
  EAttribute getRegexpLiteral_Val();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.QueryLiteral <em>Query Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Query Literal</em>'.
   * @see org.eclipse.b3.beeLang.QueryLiteral
   * @generated
   */
  EClass getQueryLiteral();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.QueryLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Val</em>'.
   * @see org.eclipse.b3.beeLang.QueryLiteral#getVal()
   * @see #getQueryLiteral()
   * @generated
   */
  EReference getQueryLiteral_Val();

  /**
   * Returns the meta object for enum '{@link org.eclipse.b3.beeLang.Visibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Visibility</em>'.
   * @see org.eclipse.b3.beeLang.Visibility
   * @generated
   */
  EEnum getVisibility();

  /**
   * Returns the meta object for enum '{@link org.eclipse.b3.beeLang.ExecutionMode <em>Execution Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Execution Mode</em>'.
   * @see org.eclipse.b3.beeLang.ExecutionMode
   * @generated
   */
  EEnum getExecutionMode();

  /**
   * Returns the meta object for enum '{@link org.eclipse.b3.beeLang.AssignmentOperator <em>Assignment Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Assignment Operator</em>'.
   * @see org.eclipse.b3.beeLang.AssignmentOperator
   * @generated
   */
  EEnum getAssignmentOperator();

  /**
   * Returns the meta object for enum '{@link org.eclipse.b3.beeLang.RelationalOperator <em>Relational Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Relational Operator</em>'.
   * @see org.eclipse.b3.beeLang.RelationalOperator
   * @generated
   */
  EEnum getRelationalOperator();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  BeeLangFactory getBeeLangFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.BeeModelImpl <em>Bee Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.BeeModelImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBeeModel()
     * @generated
     */
    EClass BEE_MODEL = eINSTANCE.getBeeModel();

    /**
     * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BEE_MODEL__IMPORTS = eINSTANCE.getBeeModel_Imports();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BEE_MODEL__BODY = eINSTANCE.getBeeModel_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.VersionImpl <em>Version</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.VersionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVersion()
     * @generated
     */
    EClass VERSION = eINSTANCE.getVersion();

    /**
     * The meta object literal for the '<em><b>Original</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VERSION__ORIGINAL = eINSTANCE.getVersion_Original();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.VersionRangeImpl <em>Version Range</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.VersionRangeImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVersionRange()
     * @generated
     */
    EClass VERSION_RANGE = eINSTANCE.getVersionRange();

    /**
     * The meta object literal for the '<em><b>Min Limit</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VERSION_RANGE__MIN_LIMIT = eINSTANCE.getVersionRange_MinLimit();

    /**
     * The meta object literal for the '<em><b>Min</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VERSION_RANGE__MIN = eINSTANCE.getVersionRange_Min();

    /**
     * The meta object literal for the '<em><b>Max</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VERSION_RANGE__MAX = eINSTANCE.getVersionRange_Max();

    /**
     * The meta object literal for the '<em><b>Max Limit</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VERSION_RANGE__MAX_LIMIT = eINSTANCE.getVersionRange_MaxLimit();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ImportImpl <em>Import</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ImportImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getImport()
     * @generated
     */
    EClass IMPORT = eINSTANCE.getImport();

    /**
     * The meta object literal for the '<em><b>Import Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPORT__IMPORT_CLASS = eINSTANCE.getImport_ImportClass();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl <em>Build Unit</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.BuildUnitImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBuildUnit()
     * @generated
     */
    EClass BUILD_UNIT = eINSTANCE.getBuildUnit();

    /**
     * The meta object literal for the '<em><b>Execution Mode</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILD_UNIT__EXECUTION_MODE = eINSTANCE.getBuildUnit_ExecutionMode();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILD_UNIT__NAME = eINSTANCE.getBuildUnit_Name();

    /**
     * The meta object literal for the '<em><b>Version</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__VERSION = eINSTANCE.getBuildUnit_Version();

    /**
     * The meta object literal for the '<em><b>Implements</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILD_UNIT__IMPLEMENTS = eINSTANCE.getBuildUnit_Implements();

    /**
     * The meta object literal for the '<em><b>Compound Property Operation</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__COMPOUND_PROPERTY_OPERATION = eINSTANCE.getBuildUnit_CompoundPropertyOperation();

    /**
     * The meta object literal for the '<em><b>Provided Capabilities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__PROVIDED_CAPABILITIES = eINSTANCE.getBuildUnit_ProvidedCapabilities();

    /**
     * The meta object literal for the '<em><b>Required Capabilities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__REQUIRED_CAPABILITIES = eINSTANCE.getBuildUnit_RequiredCapabilities();

    /**
     * The meta object literal for the '<em><b>Meta Required Capabilities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__META_REQUIRED_CAPABILITIES = eINSTANCE.getBuildUnit_MetaRequiredCapabilities();

    /**
     * The meta object literal for the '<em><b>Advice</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__ADVICE = eINSTANCE.getBuildUnit_Advice();

    /**
     * The meta object literal for the '<em><b>Synchronizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__SYNCHRONIZATIONS = eINSTANCE.getBuildUnit_Synchronizations();

    /**
     * The meta object literal for the '<em><b>Parts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__PARTS = eINSTANCE.getBuildUnit_Parts();

    /**
     * The meta object literal for the '<em><b>Repository Configurations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__REPOSITORY_CONFIGURATIONS = eINSTANCE.getBuildUnit_RepositoryConfigurations();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ProvidedCapabilityImpl <em>Provided Capability</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ProvidedCapabilityImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getProvidedCapability()
     * @generated
     */
    EClass PROVIDED_CAPABILITY = eINSTANCE.getProvidedCapability();

    /**
     * The meta object literal for the '<em><b>Filter</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROVIDED_CAPABILITY__FILTER = eINSTANCE.getProvidedCapability_Filter();

    /**
     * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROVIDED_CAPABILITY__INTERFACE = eINSTANCE.getProvidedCapability_Interface();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROVIDED_CAPABILITY__NAME = eINSTANCE.getProvidedCapability_Name();

    /**
     * The meta object literal for the '<em><b>Version</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROVIDED_CAPABILITY__VERSION = eINSTANCE.getProvidedCapability_Version();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.RequiredCapabilityImpl <em>Required Capability</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.RequiredCapabilityImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRequiredCapability()
     * @generated
     */
    EClass REQUIRED_CAPABILITY = eINSTANCE.getRequiredCapability();

    /**
     * The meta object literal for the '<em><b>Filter</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REQUIRED_CAPABILITY__FILTER = eINSTANCE.getRequiredCapability_Filter();

    /**
     * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REQUIRED_CAPABILITY__INTERFACE = eINSTANCE.getRequiredCapability_Interface();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REQUIRED_CAPABILITY__NAME = eINSTANCE.getRequiredCapability_Name();

    /**
     * The meta object literal for the '<em><b>Range</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REQUIRED_CAPABILITY__RANGE = eINSTANCE.getRequiredCapability_Range();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.NamedPropertiesImpl <em>Named Properties</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.NamedPropertiesImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getNamedProperties()
     * @generated
     */
    EClass NAMED_PROPERTIES = eINSTANCE.getNamedProperties();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMED_PROPERTIES__NAME = eINSTANCE.getNamedProperties_Name();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_PROPERTIES__BODY = eINSTANCE.getNamedProperties_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.CompoundPropertyOperationImpl <em>Compound Property Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.CompoundPropertyOperationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCompoundPropertyOperation()
     * @generated
     */
    EClass COMPOUND_PROPERTY_OPERATION = eINSTANCE.getCompoundPropertyOperation();

    /**
     * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOUND_PROPERTY_OPERATION__OPERATIONS = eINSTANCE.getCompoundPropertyOperation_Operations();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PropertyOperationImpl <em>Property Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PropertyOperationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPropertyOperation()
     * @generated
     */
    EClass PROPERTY_OPERATION = eINSTANCE.getPropertyOperation();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.FilteredPropertyOperationImpl <em>Filtered Property Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.FilteredPropertyOperationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFilteredPropertyOperation()
     * @generated
     */
    EClass FILTERED_PROPERTY_OPERATION = eINSTANCE.getFilteredPropertyOperation();

    /**
     * The meta object literal for the '<em><b>Filter</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILTERED_PROPERTY_OPERATION__FILTER = eINSTANCE.getFilteredPropertyOperation_Filter();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILTERED_PROPERTY_OPERATION__BODY = eINSTANCE.getFilteredPropertyOperation_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.SetPropertyOperationImpl <em>Set Property Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.SetPropertyOperationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSetPropertyOperation()
     * @generated
     */
    EClass SET_PROPERTY_OPERATION = eINSTANCE.getSetPropertyOperation();

    /**
     * The meta object literal for the '<em><b>Immutable</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SET_PROPERTY_OPERATION__IMMUTABLE = eINSTANCE.getSetPropertyOperation_Immutable();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SET_PROPERTY_OPERATION__KEY = eINSTANCE.getSetPropertyOperation_Key();

    /**
     * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SET_PROPERTY_OPERATION__OP = eINSTANCE.getSetPropertyOperation_Op();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SET_PROPERTY_OPERATION__VALUE = eINSTANCE.getSetPropertyOperation_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.UnsetPropertyOperationImpl <em>Unset Property Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.UnsetPropertyOperationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUnsetPropertyOperation()
     * @generated
     */
    EClass UNSET_PROPERTY_OPERATION = eINSTANCE.getUnsetPropertyOperation();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNSET_PROPERTY_OPERATION__KEY = eINSTANCE.getUnsetPropertyOperation_Key();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.SynchronizationImpl <em>Synchronization</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.SynchronizationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSynchronization()
     * @generated
     */
    EClass SYNCHRONIZATION = eINSTANCE.getSynchronization();

    /**
     * The meta object literal for the '<em><b>Partrefs</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SYNCHRONIZATION__PARTREFS = eINSTANCE.getSynchronization_Partrefs();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PathGroupImpl <em>Path Group</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PathGroupImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPathGroup()
     * @generated
     */
    EClass PATH_GROUP = eINSTANCE.getPathGroup();

    /**
     * The meta object literal for the '<em><b>Paths</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PATH_GROUP__PATHS = eINSTANCE.getPathGroup_Paths();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PATH_GROUP__ANNOTATIONS = eINSTANCE.getPathGroup_Annotations();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PathVectorElementImpl <em>Path Vector Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PathVectorElementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPathVectorElement()
     * @generated
     */
    EClass PATH_VECTOR_ELEMENT = eINSTANCE.getPathVectorElement();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.FilteredPathVectorImpl <em>Filtered Path Vector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.FilteredPathVectorImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFilteredPathVector()
     * @generated
     */
    EClass FILTERED_PATH_VECTOR = eINSTANCE.getFilteredPathVector();

    /**
     * The meta object literal for the '<em><b>Filter</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILTERED_PATH_VECTOR__FILTER = eINSTANCE.getFilteredPathVector_Filter();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILTERED_PATH_VECTOR__BODY = eINSTANCE.getFilteredPathVector_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PathVectorImpl <em>Path Vector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PathVectorImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPathVector()
     * @generated
     */
    EClass PATH_VECTOR = eINSTANCE.getPathVector();

    /**
     * The meta object literal for the '<em><b>Base Path</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PATH_VECTOR__BASE_PATH = eINSTANCE.getPathVector_BasePath();

    /**
     * The meta object literal for the '<em><b>Paths</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PATH_VECTOR__PATHS = eINSTANCE.getPathVector_Paths();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.CompoundPathVectorImpl <em>Compound Path Vector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.CompoundPathVectorImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCompoundPathVector()
     * @generated
     */
    EClass COMPOUND_PATH_VECTOR = eINSTANCE.getCompoundPathVector();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOUND_PATH_VECTOR__BODY = eINSTANCE.getCompoundPathVector_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PrerequisiteImpl <em>Prerequisite</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PrerequisiteImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPrerequisite()
     * @generated
     */
    EClass PREREQUISITE = eINSTANCE.getPrerequisite();

    /**
     * The meta object literal for the '<em><b>Surpressed</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PREREQUISITE__SURPRESSED = eINSTANCE.getPrerequisite_Surpressed();

    /**
     * The meta object literal for the '<em><b>Filter</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PREREQUISITE__FILTER = eINSTANCE.getPrerequisite_Filter();

    /**
     * The meta object literal for the '<em><b>Alias</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PREREQUISITE__ALIAS = eINSTANCE.getPrerequisite_Alias();

    /**
     * The meta object literal for the '<em><b>Part Reference</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PREREQUISITE__PART_REFERENCE = eINSTANCE.getPrerequisite_PartReference();

    /**
     * The meta object literal for the '<em><b>Closure</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PREREQUISITE__CLOSURE = eINSTANCE.getPrerequisite_Closure();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ClosureImpl <em>Closure</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ClosureImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getClosure()
     * @generated
     */
    EClass CLOSURE = eINSTANCE.getClosure();

    /**
     * The meta object literal for the '<em><b>Properties</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLOSURE__PROPERTIES = eINSTANCE.getClosure_Properties();

    /**
     * The meta object literal for the '<em><b>Advice</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLOSURE__ADVICE = eINSTANCE.getClosure_Advice();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PrerequisiteEntryImpl <em>Prerequisite Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PrerequisiteEntryImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPrerequisiteEntry()
     * @generated
     */
    EClass PREREQUISITE_ENTRY = eINSTANCE.getPrerequisiteEntry();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PartInSelfImpl <em>Part In Self</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PartInSelfImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPartInSelf()
     * @generated
     */
    EClass PART_IN_SELF = eINSTANCE.getPartInSelf();

    /**
     * The meta object literal for the '<em><b>Part Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PART_IN_SELF__PART_NAME = eINSTANCE.getPartInSelf_PartName();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.CapabilityReferencedPartImpl <em>Capability Referenced Part</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.CapabilityReferencedPartImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCapabilityReferencedPart()
     * @generated
     */
    EClass CAPABILITY_REFERENCED_PART = eINSTANCE.getCapabilityReferencedPart();

    /**
     * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CAPABILITY_REFERENCED_PART__INTERFACE = eINSTANCE.getCapabilityReferencedPart_Interface();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CAPABILITY_REFERENCED_PART__NAME = eINSTANCE.getCapabilityReferencedPart_Name();

    /**
     * The meta object literal for the '<em><b>Range</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CAPABILITY_REFERENCED_PART__RANGE = eINSTANCE.getCapabilityReferencedPart_Range();

    /**
     * The meta object literal for the '<em><b>Part Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CAPABILITY_REFERENCED_PART__PART_NAME = eINSTANCE.getCapabilityReferencedPart_PartName();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.CompoundReferencesImpl <em>Compound References</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.CompoundReferencesImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCompoundReferences()
     * @generated
     */
    EClass COMPOUND_REFERENCES = eINSTANCE.getCompoundReferences();

    /**
     * The meta object literal for the '<em><b>Prerequisites</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOUND_REFERENCES__PREREQUISITES = eINSTANCE.getCompoundReferences_Prerequisites();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PartImpl <em>Part</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PartImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPart()
     * @generated
     */
    EClass PART = eINSTANCE.getPart();

    /**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PART__VISIBILITY = eINSTANCE.getPart_Visibility();

    /**
     * The meta object literal for the '<em><b>Execution Mode</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PART__EXECUTION_MODE = eINSTANCE.getPart_ExecutionMode();

    /**
     * The meta object literal for the '<em><b>Provided Capabilities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PART__PROVIDED_CAPABILITIES = eINSTANCE.getPart_ProvidedCapabilities();

    /**
     * The meta object literal for the '<em><b>Pre Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PART__PRE_CONDITION = eINSTANCE.getPart_PreCondition();

    /**
     * The meta object literal for the '<em><b>Post Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PART__POST_CONDITION = eINSTANCE.getPart_PostCondition();

    /**
     * The meta object literal for the '<em><b>Properties</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PART__PROPERTIES = eINSTANCE.getPart_Properties();

    /**
     * The meta object literal for the '<em><b>Advice</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PART__ADVICE = eINSTANCE.getPart_Advice();

    /**
     * The meta object literal for the '<em><b>Group</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PART__GROUP = eINSTANCE.getPart_Group();

    /**
     * The meta object literal for the '<em><b>Layout</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PART__LAYOUT = eINSTANCE.getPart_Layout();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PART__STATEMENTS = eINSTANCE.getPart_Statements();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ParameterImpl <em>Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ParameterImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getParameter()
     * @generated
     */
    EClass PARAMETER = eINSTANCE.getParameter();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER__VAL = eINSTANCE.getParameter_Val();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.LayoutImpl <em>Layout</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.LayoutImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLayout()
     * @generated
     */
    EClass LAYOUT = eINSTANCE.getLayout();

    /**
     * The meta object literal for the '<em><b>Post Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LAYOUT__POST_CONDITION = eINSTANCE.getLayout_PostCondition();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LAYOUT__BODY = eINSTANCE.getLayout_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.GroupImpl <em>Group</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.GroupImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getGroup()
     * @generated
     */
    EClass GROUP = eINSTANCE.getGroup();

    /**
     * The meta object literal for the '<em><b>Pre Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP__PRE_CONDITION = eINSTANCE.getGroup_PreCondition();

    /**
     * The meta object literal for the '<em><b>Post Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP__POST_CONDITION = eINSTANCE.getGroup_PostCondition();

    /**
     * The meta object literal for the '<em><b>Prerequisites</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP__PREREQUISITES = eINSTANCE.getGroup_Prerequisites();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.RepositoryConfigurationImpl <em>Repository Configuration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.RepositoryConfigurationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRepositoryConfiguration()
     * @generated
     */
    EClass REPOSITORY_CONFIGURATION = eINSTANCE.getRepositoryConfiguration();

    /**
     * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REPOSITORY_CONFIGURATION__LOCATION = eINSTANCE.getRepositoryConfiguration_Location();

    /**
     * The meta object literal for the '<em><b>Resolver Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REPOSITORY_CONFIGURATION__RESOLVER_CLASS = eINSTANCE.getRepositoryConfiguration_ResolverClass();

    /**
     * The meta object literal for the '<em><b>Advice</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REPOSITORY_CONFIGURATION__ADVICE = eINSTANCE.getRepositoryConfiguration_Advice();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.NamedAdviceImpl <em>Named Advice</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.NamedAdviceImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getNamedAdvice()
     * @generated
     */
    EClass NAMED_ADVICE = eINSTANCE.getNamedAdvice();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMED_ADVICE__NAME = eINSTANCE.getNamedAdvice_Name();

    /**
     * The meta object literal for the '<em><b>Advice</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_ADVICE__ADVICE = eINSTANCE.getNamedAdvice_Advice();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.AdviceImpl <em>Advice</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.AdviceImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAdvice()
     * @generated
     */
    EClass ADVICE = eINSTANCE.getAdvice();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.CompoundAdviceImpl <em>Compound Advice</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.CompoundAdviceImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCompoundAdvice()
     * @generated
     */
    EClass COMPOUND_ADVICE = eINSTANCE.getCompoundAdvice();

    /**
     * The meta object literal for the '<em><b>Advice</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOUND_ADVICE__ADVICE = eINSTANCE.getCompoundAdvice_Advice();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.AdviceStatementImpl <em>Advice Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.AdviceStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAdviceStatement()
     * @generated
     */
    EClass ADVICE_STATEMENT = eINSTANCE.getAdviceStatement();

    /**
     * The meta object literal for the '<em><b>Path</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ADVICE_STATEMENT__PATH = eINSTANCE.getAdviceStatement_Path();

    /**
     * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ADVICE_STATEMENT__OP = eINSTANCE.getAdviceStatement_Op();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ADVICE_STATEMENT__VALUE = eINSTANCE.getAdviceStatement_Value();

    /**
     * The meta object literal for the '<em><b>Advice</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ADVICE_STATEMENT__ADVICE = eINSTANCE.getAdviceStatement_Advice();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.AdvicePathImpl <em>Advice Path</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.AdvicePathImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAdvicePath()
     * @generated
     */
    EClass ADVICE_PATH = eINSTANCE.getAdvicePath();

    /**
     * The meta object literal for the '<em><b>Absolute</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ADVICE_PATH__ABSOLUTE = eINSTANCE.getAdvicePath_Absolute();

    /**
     * The meta object literal for the '<em><b>Selectors</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ADVICE_PATH__SELECTORS = eINSTANCE.getAdvicePath_Selectors();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.SelectorImpl <em>Selector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.SelectorImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSelector()
     * @generated
     */
    EClass SELECTOR = eINSTANCE.getSelector();

    /**
     * The meta object literal for the '<em><b>Selector</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SELECTOR__SELECTOR = eINSTANCE.getSelector_Selector();

    /**
     * The meta object literal for the '<em><b>Predicate</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SELECTOR__PREDICATE = eINSTANCE.getSelector_Predicate();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.NameSelectorImpl <em>Name Selector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.NameSelectorImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getNameSelector()
     * @generated
     */
    EClass NAME_SELECTOR = eINSTANCE.getNameSelector();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAME_SELECTOR__NAME = eINSTANCE.getNameSelector_Name();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ThisSelectorImpl <em>This Selector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ThisSelectorImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getThisSelector()
     * @generated
     */
    EClass THIS_SELECTOR = eINSTANCE.getThisSelector();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ParentSelectorImpl <em>Parent Selector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ParentSelectorImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getParentSelector()
     * @generated
     */
    EClass PARENT_SELECTOR = eINSTANCE.getParentSelector();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ChildrenSelectorImpl <em>Children Selector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ChildrenSelectorImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getChildrenSelector()
     * @generated
     */
    EClass CHILDREN_SELECTOR = eINSTANCE.getChildrenSelector();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.AncestorSelectorImpl <em>Ancestor Selector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.AncestorSelectorImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAncestorSelector()
     * @generated
     */
    EClass ANCESTOR_SELECTOR = eINSTANCE.getAncestorSelector();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.RegexpSelectorImpl <em>Regexp Selector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.RegexpSelectorImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRegexpSelector()
     * @generated
     */
    EClass REGEXP_SELECTOR = eINSTANCE.getRegexpSelector();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REGEXP_SELECTOR__PATTERN = eINSTANCE.getRegexpSelector_Pattern();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.FilterImpl <em>Filter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.FilterImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFilter()
     * @generated
     */
    EClass FILTER = eINSTANCE.getFilter();

    /**
     * The meta object literal for the '<em><b>Predicate</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILTER__PREDICATE = eINSTANCE.getFilter_Predicate();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PreConditionImpl <em>Pre Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PreConditionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPreCondition()
     * @generated
     */
    EClass PRE_CONDITION = eINSTANCE.getPreCondition();

    /**
     * The meta object literal for the '<em><b>Asserts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRE_CONDITION__ASSERTS = eINSTANCE.getPreCondition_Asserts();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PostConditionImpl <em>Post Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PostConditionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPostCondition()
     * @generated
     */
    EClass POST_CONDITION = eINSTANCE.getPostCondition();

    /**
     * The meta object literal for the '<em><b>Asserts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POST_CONDITION__ASSERTS = eINSTANCE.getPostCondition_Asserts();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.AssertionExpressionImpl <em>Assertion Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.AssertionExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAssertionExpression()
     * @generated
     */
    EClass ASSERTION_EXPRESSION = eINSTANCE.getAssertionExpression();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSERTION_EXPRESSION__EXPR = eINSTANCE.getAssertionExpression_Expr();

    /**
     * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ASSERTION_EXPRESSION__MESSAGE = eINSTANCE.getAssertionExpression_Message();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.StatementImpl <em>Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.StatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getStatement()
     * @generated
     */
    EClass STATEMENT = eINSTANCE.getStatement();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.BreakStatementImpl <em>Break Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.BreakStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBreakStatement()
     * @generated
     */
    EClass BREAK_STATEMENT = eINSTANCE.getBreakStatement();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BREAK_STATEMENT__LABEL = eINSTANCE.getBreakStatement_Label();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ContinueStatementImpl <em>Continue Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ContinueStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getContinueStatement()
     * @generated
     */
    EClass CONTINUE_STATEMENT = eINSTANCE.getContinueStatement();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONTINUE_STATEMENT__LABEL = eINSTANCE.getContinueStatement_Label();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.CompoundStatementImpl <em>Compound Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.CompoundStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCompoundStatement()
     * @generated
     */
    EClass COMPOUND_STATEMENT = eINSTANCE.getCompoundStatement();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOUND_STATEMENT__STATEMENTS = eINSTANCE.getCompoundStatement_Statements();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.StatementsImpl <em>Statements</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.StatementsImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getStatements()
     * @generated
     */
    EClass STATEMENTS = eINSTANCE.getStatements();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENTS__STATEMENTS = eINSTANCE.getStatements_Statements();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.WhileStatementImpl <em>While Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.WhileStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWhileStatement()
     * @generated
     */
    EClass WHILE_STATEMENT = eINSTANCE.getWhileStatement();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHILE_STATEMENT__CONDITION = eINSTANCE.getWhileStatement_Condition();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHILE_STATEMENT__BODY = eINSTANCE.getWhileStatement_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.SwitchStatementImpl <em>Switch Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.SwitchStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSwitchStatement()
     * @generated
     */
    EClass SWITCH_STATEMENT = eINSTANCE.getSwitchStatement();

    /**
     * The meta object literal for the '<em><b>Switch Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SWITCH_STATEMENT__SWITCH_EXPR = eINSTANCE.getSwitchStatement_SwitchExpr();

    /**
     * The meta object literal for the '<em><b>Case</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SWITCH_STATEMENT__CASE = eINSTANCE.getSwitchStatement_Case();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SWITCH_STATEMENT__STATEMENTS = eINSTANCE.getSwitchStatement_Statements();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.CaseImpl <em>Case</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.CaseImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCase()
     * @generated
     */
    EClass CASE = eINSTANCE.getCase();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CASE__CONDITION = eINSTANCE.getCase_Condition();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CASE__STATEMENTS = eINSTANCE.getCase_Statements();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ForStatementImpl <em>For Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ForStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getForStatement()
     * @generated
     */
    EClass FOR_STATEMENT = eINSTANCE.getForStatement();

    /**
     * The meta object literal for the '<em><b>Init</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_STATEMENT__INIT = eINSTANCE.getForStatement_Init();

    /**
     * The meta object literal for the '<em><b>Regular</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FOR_STATEMENT__REGULAR = eINSTANCE.getForStatement_Regular();

    /**
     * The meta object literal for the '<em><b>Cond</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_STATEMENT__COND = eINSTANCE.getForStatement_Cond();

    /**
     * The meta object literal for the '<em><b>Iterate</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_STATEMENT__ITERATE = eINSTANCE.getForStatement_Iterate();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_STATEMENT__BODY = eINSTANCE.getForStatement_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.DoWhileStatementImpl <em>Do While Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.DoWhileStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getDoWhileStatement()
     * @generated
     */
    EClass DO_WHILE_STATEMENT = eINSTANCE.getDoWhileStatement();

    /**
     * The meta object literal for the '<em><b>Statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DO_WHILE_STATEMENT__STATEMENT = eINSTANCE.getDoWhileStatement_Statement();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DO_WHILE_STATEMENT__CONDITION = eINSTANCE.getDoWhileStatement_Condition();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ReturnStatementImpl <em>Return Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ReturnStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getReturnStatement()
     * @generated
     */
    EClass RETURN_STATEMENT = eINSTANCE.getReturnStatement();

    /**
     * The meta object literal for the '<em><b>Return</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RETURN_STATEMENT__RETURN = eINSTANCE.getReturnStatement_Return();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.FunctionStatementImpl <em>Function Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.FunctionStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFunctionStatement()
     * @generated
     */
    EClass FUNCTION_STATEMENT = eINSTANCE.getFunctionStatement();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_STATEMENT__NAME = eINSTANCE.getFunctionStatement_Name();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_STATEMENT__PARAMS = eINSTANCE.getFunctionStatement_Params();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_STATEMENT__STATEMENTS = eINSTANCE.getFunctionStatement_Statements();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.IfStatementImpl <em>If Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.IfStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getIfStatement()
     * @generated
     */
    EClass IF_STATEMENT = eINSTANCE.getIfStatement();

    /**
     * The meta object literal for the '<em><b>Cond</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_STATEMENT__COND = eINSTANCE.getIfStatement_Cond();

    /**
     * The meta object literal for the '<em><b>Then</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_STATEMENT__THEN = eINSTANCE.getIfStatement_Then();

    /**
     * The meta object literal for the '<em><b>Else</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_STATEMENT__ELSE = eINSTANCE.getIfStatement_Else();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.LabelStatementImpl <em>Label Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.LabelStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLabelStatement()
     * @generated
     */
    EClass LABEL_STATEMENT = eINSTANCE.getLabelStatement();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LABEL_STATEMENT__NAME = eINSTANCE.getLabelStatement_Name();

    /**
     * The meta object literal for the '<em><b>Statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LABEL_STATEMENT__STATEMENT = eINSTANCE.getLabelStatement_Statement();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ThrowStatementImpl <em>Throw Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ThrowStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getThrowStatement()
     * @generated
     */
    EClass THROW_STATEMENT = eINSTANCE.getThrowStatement();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference THROW_STATEMENT__EXPR = eINSTANCE.getThrowStatement_Expr();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.TryCatchStatementImpl <em>Try Catch Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.TryCatchStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getTryCatchStatement()
     * @generated
     */
    EClass TRY_CATCH_STATEMENT = eINSTANCE.getTryCatchStatement();

    /**
     * The meta object literal for the '<em><b>Try Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TRY_CATCH_STATEMENT__TRY_BLOCK = eINSTANCE.getTryCatchStatement_TryBlock();

    /**
     * The meta object literal for the '<em><b>Catch Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TRY_CATCH_STATEMENT__CATCH_BLOCK = eINSTANCE.getTryCatchStatement_CatchBlock();

    /**
     * The meta object literal for the '<em><b>Finally Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TRY_CATCH_STATEMENT__FINALLY_BLOCK = eINSTANCE.getTryCatchStatement_FinallyBlock();

    /**
     * The meta object literal for the '<em><b>Finally</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TRY_CATCH_STATEMENT__FINALLY = eINSTANCE.getTryCatchStatement_Finally();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.CatchBlockImpl <em>Catch Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.CatchBlockImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCatchBlock()
     * @generated
     */
    EClass CATCH_BLOCK = eINSTANCE.getCatchBlock();

    /**
     * The meta object literal for the '<em><b>Variable</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATCH_BLOCK__VARIABLE = eINSTANCE.getCatchBlock_Variable();

    /**
     * The meta object literal for the '<em><b>Catch Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CATCH_BLOCK__CATCH_BLOCK = eINSTANCE.getCatchBlock_CatchBlock();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.FinallyBlockImpl <em>Finally Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.FinallyBlockImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFinallyBlock()
     * @generated
     */
    EClass FINALLY_BLOCK = eINSTANCE.getFinallyBlock();

    /**
     * The meta object literal for the '<em><b>Finally Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FINALLY_BLOCK__FINALLY_BLOCK = eINSTANCE.getFinallyBlock_FinallyBlock();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.WithStatementImpl <em>With Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.WithStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWithStatement()
     * @generated
     */
    EClass WITH_STATEMENT = eINSTANCE.getWithStatement();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WITH_STATEMENT__EXPR = eINSTANCE.getWithStatement_Expr();

    /**
     * The meta object literal for the '<em><b>Statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WITH_STATEMENT__STATEMENT = eINSTANCE.getWithStatement_Statement();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.VarExpressionListImpl <em>Var Expression List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.VarExpressionListImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVarExpressionList()
     * @generated
     */
    EClass VAR_EXPRESSION_LIST = eINSTANCE.getVarExpressionList();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VAR_EXPRESSION_LIST__EXPR = eINSTANCE.getVarExpressionList_Expr();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ExpressionListImpl <em>Expression List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ExpressionListImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpressionList()
     * @generated
     */
    EClass EXPRESSION_LIST = eINSTANCE.getExpressionList();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION_LIST__EXPR = eINSTANCE.getExpressionList_Expr();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getExpression();

    /**
     * The meta object literal for the '<em><b>Sval</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION__SVAL = eINSTANCE.getExpression_Sval();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.UnaryExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUnaryExpression()
     * @generated
     */
    EClass UNARY_EXPRESSION = eINSTANCE.getUnaryExpression();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.FeatureImpl <em>Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.FeatureImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFeature()
     * @generated
     */
    EClass FEATURE = eINSTANCE.getFeature();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE__NAME = eINSTANCE.getFeature_Name();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE__VALUE = eINSTANCE.getFeature_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.VarExpressionImpl <em>Var Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.VarExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVarExpression()
     * @generated
     */
    EClass VAR_EXPRESSION = eINSTANCE.getVarExpression();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VAR_EXPRESSION__NAME = eINSTANCE.getVarExpression_Name();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VAR_EXPRESSION__VALUE = eINSTANCE.getVarExpression_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.AssignmentOperationImpl <em>Assignment Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.AssignmentOperationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAssignmentOperation()
     * @generated
     */
    EClass ASSIGNMENT_OPERATION = eINSTANCE.getAssignmentOperation();

    /**
     * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSIGNMENT_OPERATION__LEFT = eINSTANCE.getAssignmentOperation_Left();

    /**
     * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ASSIGNMENT_OPERATION__OP = eINSTANCE.getAssignmentOperation_Op();

    /**
     * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSIGNMENT_OPERATION__RIGHT = eINSTANCE.getAssignmentOperation_Right();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.IfExpressionImpl <em>If Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.IfExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getIfExpression()
     * @generated
     */
    EClass IF_EXPRESSION = eINSTANCE.getIfExpression();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EXPRESSION__CONDITION = eINSTANCE.getIfExpression_Condition();

    /**
     * The meta object literal for the '<em><b>Then Part</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EXPRESSION__THEN_PART = eINSTANCE.getIfExpression_ThenPart();

    /**
     * The meta object literal for the '<em><b>Else Part</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EXPRESSION__ELSE_PART = eINSTANCE.getIfExpression_ElsePart();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.BooleanOperationImpl <em>Boolean Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.BooleanOperationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBooleanOperation()
     * @generated
     */
    EClass BOOLEAN_OPERATION = eINSTANCE.getBooleanOperation();

    /**
     * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BOOLEAN_OPERATION__LEFT = eINSTANCE.getBooleanOperation_Left();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BOOLEAN_OPERATION__OPERATOR = eINSTANCE.getBooleanOperation_Operator();

    /**
     * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BOOLEAN_OPERATION__RIGHT = eINSTANCE.getBooleanOperation_Right();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.OperationCallImpl <em>Operation Call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.OperationCallImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getOperationCall()
     * @generated
     */
    EClass OPERATION_CALL = eINSTANCE.getOperationCall();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERATION_CALL__PARAMS = eINSTANCE.getOperationCall_Params();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OPERATION_CALL__NAME = eINSTANCE.getOperationCall_Name();

    /**
     * The meta object literal for the '<em><b>Post Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERATION_CALL__POST_TARGET = eINSTANCE.getOperationCall_PostTarget();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERATION_CALL__TARGET = eINSTANCE.getOperationCall_Target();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.RelationalOperationImpl <em>Relational Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.RelationalOperationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRelationalOperation()
     * @generated
     */
    EClass RELATIONAL_OPERATION = eINSTANCE.getRelationalOperation();

    /**
     * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RELATIONAL_OPERATION__LEFT = eINSTANCE.getRelationalOperation_Left();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RELATIONAL_OPERATION__OPERATOR = eINSTANCE.getRelationalOperation_Operator();

    /**
     * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RELATIONAL_OPERATION__RIGHT = eINSTANCE.getRelationalOperation_Right();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.UnaryOperationImpl <em>Unary Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.UnaryOperationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUnaryOperation()
     * @generated
     */
    EClass UNARY_OPERATION = eINSTANCE.getUnaryOperation();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNARY_OPERATION__NAME = eINSTANCE.getUnaryOperation_Name();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference UNARY_OPERATION__PARAMS = eINSTANCE.getUnaryOperation_Params();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.AtCallImpl <em>At Call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.AtCallImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAtCall()
     * @generated
     */
    EClass AT_CALL = eINSTANCE.getAtCall();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AT_CALL__TARGET = eINSTANCE.getAtCall_Target();

    /**
     * The meta object literal for the '<em><b>Index</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AT_CALL__INDEX = eINSTANCE.getAtCall_Index();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AT_CALL__PARAMS = eINSTANCE.getAtCall_Params();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.FeatureCallImpl <em>Feature Call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.FeatureCallImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFeatureCall()
     * @generated
     */
    EClass FEATURE_CALL = eINSTANCE.getFeatureCall();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_CALL__TARGET = eINSTANCE.getFeatureCall_Target();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_CALL__TYPE = eINSTANCE.getFeatureCall_Type();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PropertyValueImpl <em>Property Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PropertyValueImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPropertyValue()
     * @generated
     */
    EClass PROPERTY_VALUE = eINSTANCE.getPropertyValue();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROPERTY_VALUE__NAME = eINSTANCE.getPropertyValue_Name();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.VariableValueImpl <em>Variable Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.VariableValueImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVariableValue()
     * @generated
     */
    EClass VARIABLE_VALUE = eINSTANCE.getVariableValue();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VARIABLE_VALUE__NAME = eINSTANCE.getVariableValue_Name();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.KeywordVariableImpl <em>Keyword Variable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.KeywordVariableImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getKeywordVariable()
     * @generated
     */
    EClass KEYWORD_VARIABLE = eINSTANCE.getKeywordVariable();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute KEYWORD_VARIABLE__NAME = eINSTANCE.getKeywordVariable_Name();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.CreatorCallImpl <em>Creator Call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.CreatorCallImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCreatorCall()
     * @generated
     */
    EClass CREATOR_CALL = eINSTANCE.getCreatorCall();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CREATOR_CALL__NAME = eINSTANCE.getCreatorCall_Name();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CREATOR_CALL__TARGET = eINSTANCE.getCreatorCall_Target();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CREATOR_CALL__PARAMS = eINSTANCE.getCreatorCall_Params();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.LiteralArrayImpl <em>Literal Array</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.LiteralArrayImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLiteralArray()
     * @generated
     */
    EClass LITERAL_ARRAY = eINSTANCE.getLiteralArray();

    /**
     * The meta object literal for the '<em><b>Element</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LITERAL_ARRAY__ELEMENT = eINSTANCE.getLiteralArray_Element();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.LiteralObjectImpl <em>Literal Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.LiteralObjectImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLiteralObject()
     * @generated
     */
    EClass LITERAL_OBJECT = eINSTANCE.getLiteralObject();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LITERAL_OBJECT__FEATURES = eINSTANCE.getLiteralObject_Features();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.LiteralFunctionImpl <em>Literal Function</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.LiteralFunctionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLiteralFunction()
     * @generated
     */
    EClass LITERAL_FUNCTION = eINSTANCE.getLiteralFunction();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LITERAL_FUNCTION__PARAMETERS = eINSTANCE.getLiteralFunction_Parameters();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LITERAL_FUNCTION__STATEMENTS = eINSTANCE.getLiteralFunction_Statements();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.BooleanLiteralImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBooleanLiteral()
     * @generated
     */
    EClass BOOLEAN_LITERAL = eINSTANCE.getBooleanLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BOOLEAN_LITERAL__VAL = eINSTANCE.getBooleanLiteral_Val();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.IntegerLiteralImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getIntegerLiteral()
     * @generated
     */
    EClass INTEGER_LITERAL = eINSTANCE.getIntegerLiteral();

    /**
     * The meta object literal for the '<em><b>Ival</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INTEGER_LITERAL__IVAL = eINSTANCE.getIntegerLiteral_Ival();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.NullLiteralImpl <em>Null Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.NullLiteralImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getNullLiteral()
     * @generated
     */
    EClass NULL_LITERAL = eINSTANCE.getNullLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NULL_LITERAL__VAL = eINSTANCE.getNullLiteral_Val();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.UndefinedLiteralImpl <em>Undefined Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.UndefinedLiteralImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUndefinedLiteral()
     * @generated
     */
    EClass UNDEFINED_LITERAL = eINSTANCE.getUndefinedLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNDEFINED_LITERAL__VAL = eINSTANCE.getUndefinedLiteral_Val();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.StringLiteralImpl <em>String Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.StringLiteralImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getStringLiteral()
     * @generated
     */
    EClass STRING_LITERAL = eINSTANCE.getStringLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute STRING_LITERAL__VAL = eINSTANCE.getStringLiteral_Val();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.RealLiteralImpl <em>Real Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.RealLiteralImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRealLiteral()
     * @generated
     */
    EClass REAL_LITERAL = eINSTANCE.getRealLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REAL_LITERAL__VAL = eINSTANCE.getRealLiteral_Val();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.RegexpLiteralImpl <em>Regexp Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.RegexpLiteralImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRegexpLiteral()
     * @generated
     */
    EClass REGEXP_LITERAL = eINSTANCE.getRegexpLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REGEXP_LITERAL__VAL = eINSTANCE.getRegexpLiteral_Val();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.QueryLiteralImpl <em>Query Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.QueryLiteralImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getQueryLiteral()
     * @generated
     */
    EClass QUERY_LITERAL = eINSTANCE.getQueryLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference QUERY_LITERAL__VAL = eINSTANCE.getQueryLiteral_Val();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.Visibility <em>Visibility</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.Visibility
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVisibility()
     * @generated
     */
    EEnum VISIBILITY = eINSTANCE.getVisibility();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.ExecutionMode <em>Execution Mode</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.ExecutionMode
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExecutionMode()
     * @generated
     */
    EEnum EXECUTION_MODE = eINSTANCE.getExecutionMode();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.AssignmentOperator <em>Assignment Operator</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.AssignmentOperator
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAssignmentOperator()
     * @generated
     */
    EEnum ASSIGNMENT_OPERATOR = eINSTANCE.getAssignmentOperator();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.RelationalOperator <em>Relational Operator</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.RelationalOperator
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRelationalOperator()
     * @generated
     */
    EEnum RELATIONAL_OPERATOR = eINSTANCE.getRelationalOperator();

  }

} //BeeLangPackage

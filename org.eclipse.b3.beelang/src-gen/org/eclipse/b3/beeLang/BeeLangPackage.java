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
   * The feature id for the '<em><b>Functions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BEE_MODEL__FUNCTIONS = 1;

  /**
   * The feature id for the '<em><b>Concern</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BEE_MODEL__CONCERN = 2;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BEE_MODEL__BODY = 3;

  /**
   * The number of structural features of the '<em>Bee Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BEE_MODEL_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ImportImpl <em>Import</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ImportImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getImport()
   * @generated
   */
  int IMPORT = 1;

  /**
   * The feature id for the '<em><b>Reexport</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT__REEXPORT = 0;

  /**
   * The feature id for the '<em><b>Importer</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT__IMPORTER = 1;

  /**
   * The feature id for the '<em><b>Name Space</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT__NAME_SPACE = 2;

  /**
   * The number of structural features of the '<em>Import</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.JavaImporterImpl <em>Java Importer</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.JavaImporterImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getJavaImporter()
   * @generated
   */
  int JAVA_IMPORTER = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_IMPORTER__NAME = 0;

  /**
   * The number of structural features of the '<em>Java Importer</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVA_IMPORTER_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.NativeImporterImpl <em>Native Importer</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.NativeImporterImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getNativeImporter()
   * @generated
   */
  int NATIVE_IMPORTER = 3;

  /**
   * The feature id for the '<em><b>Uri String</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_IMPORTER__URI_STRING = 0;

  /**
   * The number of structural features of the '<em>Native Importer</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NATIVE_IMPORTER_FEATURE_COUNT = 1;

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
   * The feature id for the '<em><b>Documentation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__DOCUMENTATION = 0;

  /**
   * The feature id for the '<em><b>Execution Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__EXECUTION_MODE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__NAME = 2;

  /**
   * The feature id for the '<em><b>Version</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__VERSION = 3;

  /**
   * The feature id for the '<em><b>Implements</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__IMPLEMENTS = 4;

  /**
   * The feature id for the '<em><b>Default Properties</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__DEFAULT_PROPERTIES = 5;

  /**
   * The feature id for the '<em><b>Provided Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__PROVIDED_CAPABILITIES = 6;

  /**
   * The feature id for the '<em><b>Required Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__REQUIRED_CAPABILITIES = 7;

  /**
   * The feature id for the '<em><b>Meta Required Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__META_REQUIRED_CAPABILITIES = 8;

  /**
   * The feature id for the '<em><b>Concerns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__CONCERNS = 9;

  /**
   * The feature id for the '<em><b>Synchronizations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__SYNCHRONIZATIONS = 10;

  /**
   * The feature id for the '<em><b>Builders</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__BUILDERS = 11;

  /**
   * The feature id for the '<em><b>Methods</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__METHODS = 12;

  /**
   * The feature id for the '<em><b>Repository Configurations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__REPOSITORY_CONFIGURATIONS = 13;

  /**
   * The feature id for the '<em><b>Property Sets</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__PROPERTY_SETS = 14;

  /**
   * The feature id for the '<em><b>Containers</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__CONTAINERS = 15;

  /**
   * The number of structural features of the '<em>Build Unit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT_FEATURE_COUNT = 16;

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
   * The feature id for the '<em><b>Capability</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROVIDED_CAPABILITY__CAPABILITY = 0;

  /**
   * The feature id for the '<em><b>Version</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROVIDED_CAPABILITY__VERSION = 1;

  /**
   * The number of structural features of the '<em>Provided Capability</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROVIDED_CAPABILITY_FEATURE_COUNT = 2;

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
   * The feature id for the '<em><b>Capability</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRED_CAPABILITY__CAPABILITY = 0;

  /**
   * The feature id for the '<em><b>Range</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRED_CAPABILITY__RANGE = 1;

  /**
   * The number of structural features of the '<em>Required Capability</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REQUIRED_CAPABILITY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FilteredCapabilityImpl <em>Filtered Capability</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FilteredCapabilityImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFilteredCapability()
   * @generated
   */
  int FILTERED_CAPABILITY = 7;

  /**
   * The feature id for the '<em><b>Filter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTERED_CAPABILITY__FILTER = 0;

  /**
   * The feature id for the '<em><b>Capability</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTERED_CAPABILITY__CAPABILITY = 1;

  /**
   * The number of structural features of the '<em>Filtered Capability</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTERED_CAPABILITY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.CapabilityImpl <em>Capability</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.CapabilityImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCapability()
   * @generated
   */
  int CAPABILITY = 8;

  /**
   * The feature id for the '<em><b>Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAPABILITY__INTERFACE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAPABILITY__NAME = 1;

  /**
   * The number of structural features of the '<em>Capability</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAPABILITY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.NamedPropertySetImpl <em>Named Property Set</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.NamedPropertySetImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getNamedPropertySet()
   * @generated
   */
  int NAMED_PROPERTY_SET = 9;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_PROPERTY_SET__NAME = 0;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_PROPERTY_SET__BODY = 1;

  /**
   * The number of structural features of the '<em>Named Property Set</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_PROPERTY_SET_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PropertyOperationImpl <em>Property Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PropertyOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPropertyOperation()
   * @generated
   */
  int PROPERTY_OPERATION = 11;

  /**
   * The number of structural features of the '<em>Property Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_OPERATION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PropertySetImpl <em>Property Set</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PropertySetImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPropertySet()
   * @generated
   */
  int PROPERTY_SET = 10;

  /**
   * The feature id for the '<em><b>Extends</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_SET__EXTENDS = PROPERTY_OPERATION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Operations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_SET__OPERATIONS = PROPERTY_OPERATION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Property Set</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_SET_FEATURE_COUNT = PROPERTY_OPERATION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FilteredPropertyOperationImpl <em>Filtered Property Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FilteredPropertyOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFilteredPropertyOperation()
   * @generated
   */
  int FILTERED_PROPERTY_OPERATION = 12;

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
  int SET_PROPERTY_OPERATION = 13;

  /**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_PROPERTY_OPERATION__FINAL = PROPERTY_OPERATION_FEATURE_COUNT + 0;

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
  int UNSET_PROPERTY_OPERATION = 14;

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
  int SYNCHRONIZATION = 15;

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
  int PATH_GROUP = 16;

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
  int PATH_VECTOR_ELEMENT = 17;

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
  int FILTERED_PATH_VECTOR = 18;

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
  int PATH_VECTOR = 19;

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
  int COMPOUND_PATH_VECTOR = 20;

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
  int PREREQUISITE = 21;

  /**
   * The feature id for the '<em><b>Filter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREREQUISITE__FILTER = 0;

  /**
   * The feature id for the '<em><b>With Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREREQUISITE__WITH_CLAUSE = 1;

  /**
   * The feature id for the '<em><b>Part Reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREREQUISITE__PART_REFERENCE = 2;

  /**
   * The feature id for the '<em><b>Alias</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREREQUISITE__ALIAS = 3;

  /**
   * The number of structural features of the '<em>Prerequisite</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREREQUISITE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.WithClauseImpl <em>With Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.WithClauseImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWithClause()
   * @generated
   */
  int WITH_CLAUSE = 22;

  /**
   * The feature id for the '<em><b>References</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_CLAUSE__REFERENCES = 0;

  /**
   * The feature id for the '<em><b>Properties</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_CLAUSE__PROPERTIES = 1;

  /**
   * The feature id for the '<em><b>Concern</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_CLAUSE__CONCERN = 2;

  /**
   * The number of structural features of the '<em>With Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_CLAUSE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PrerequisiteEntryImpl <em>Prerequisite Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PrerequisiteEntryImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPrerequisiteEntry()
   * @generated
   */
  int PREREQUISITE_ENTRY = 23;

  /**
   * The number of structural features of the '<em>Prerequisite Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREREQUISITE_ENTRY_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.DirectPartReferemceImpl <em>Direct Part Referemce</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.DirectPartReferemceImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getDirectPartReferemce()
   * @generated
   */
  int DIRECT_PART_REFEREMCE = 24;

  /**
   * The feature id for the '<em><b>Unit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECT_PART_REFEREMCE__UNIT = PREREQUISITE_ENTRY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Builder</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECT_PART_REFEREMCE__BUILDER = PREREQUISITE_ENTRY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECT_PART_REFEREMCE__PARAMETERS = PREREQUISITE_ENTRY_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Direct Part Referemce</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECT_PART_REFEREMCE_FEATURE_COUNT = PREREQUISITE_ENTRY_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.CapabilityReferencedPartImpl <em>Capability Referenced Part</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.CapabilityReferencedPartImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCapabilityReferencedPart()
   * @generated
   */
  int CAPABILITY_REFERENCED_PART = 25;

  /**
   * The feature id for the '<em><b>Capability</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAPABILITY_REFERENCED_PART__CAPABILITY = PREREQUISITE_ENTRY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Range</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAPABILITY_REFERENCED_PART__RANGE = PREREQUISITE_ENTRY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Part Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAPABILITY_REFERENCED_PART__PART_NAME = PREREQUISITE_ENTRY_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CAPABILITY_REFERENCED_PART__PARAMETERS = PREREQUISITE_ENTRY_FEATURE_COUNT + 3;

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
  int COMPOUND_REFERENCES = 26;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BuilderImpl <em>Builder</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BuilderImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBuilder()
   * @generated
   */
  int BUILDER = 27;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__DOCUMENTATION = 0;

  /**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__VISIBILITY = 1;

  /**
   * The feature id for the '<em><b>Execution Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__EXECUTION_MODE = 2;

  /**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__FINAL = 3;

  /**
   * The feature id for the '<em><b>Cached</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__CACHED = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__NAME = 5;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__PARAMS = 6;

  /**
   * The feature id for the '<em><b>Provided Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__PROVIDED_CAPABILITIES = 7;

  /**
   * The feature id for the '<em><b>Pre Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__PRE_CONDITION = 8;

  /**
   * The feature id for the '<em><b>Post Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__POST_CONDITION = 9;

  /**
   * The feature id for the '<em><b>Properties</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__PROPERTIES = 10;

  /**
   * The feature id for the '<em><b>Input</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__INPUT = 11;

  /**
   * The feature id for the '<em><b>Output</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__OUTPUT = 12;

  /**
   * The feature id for the '<em><b>Expression List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER__EXPRESSION_LIST = 13;

  /**
   * The number of structural features of the '<em>Builder</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER_FEATURE_COUNT = 14;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ParameterImpl <em>Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ParameterImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getParameter()
   * @generated
   */
  int PARAMETER = 28;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__NAME = 0;

  /**
   * The feature id for the '<em><b>Val</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__VAL = 1;

  /**
   * The number of structural features of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ParameterListImpl <em>Parameter List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ParameterListImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getParameterList()
   * @generated
   */
  int PARAMETER_LIST = 29;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_LIST__PARAMS = 0;

  /**
   * The number of structural features of the '<em>Parameter List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ParameterDeclarationListImpl <em>Parameter Declaration List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ParameterDeclarationListImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getParameterDeclarationList()
   * @generated
   */
  int PARAMETER_DECLARATION_LIST = 30;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_DECLARATION_LIST__PARAMS = 0;

  /**
   * The number of structural features of the '<em>Parameter Declaration List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_DECLARATION_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ParameterDeclarationImpl <em>Parameter Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ParameterDeclarationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getParameterDeclaration()
   * @generated
   */
  int PARAMETER_DECLARATION = 31;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_DECLARATION__TYPE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_DECLARATION__NAME = 1;

  /**
   * The number of structural features of the '<em>Parameter Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_DECLARATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BuilderOutputImpl <em>Builder Output</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BuilderOutputImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBuilderOutput()
   * @generated
   */
  int BUILDER_OUTPUT = 32;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER_OUTPUT__BODY = 0;

  /**
   * The number of structural features of the '<em>Builder Output</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER_OUTPUT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BuilderInputImpl <em>Builder Input</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BuilderInputImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBuilderInput()
   * @generated
   */
  int BUILDER_INPUT = 33;

  /**
   * The feature id for the '<em><b>Pre Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER_INPUT__PRE_CONDITION = 0;

  /**
   * The feature id for the '<em><b>Post Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER_INPUT__POST_CONDITION = 1;

  /**
   * The feature id for the '<em><b>Prerequisites</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER_INPUT__PREREQUISITES = 2;

  /**
   * The number of structural features of the '<em>Builder Input</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILDER_INPUT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.RepositoryConfigurationImpl <em>Repository Configuration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.RepositoryConfigurationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRepositoryConfiguration()
   * @generated
   */
  int REPOSITORY_CONFIGURATION = 34;

  /**
   * The number of structural features of the '<em>Repository Configuration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY_CONFIGURATION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.RepositoryDeclarationImpl <em>Repository Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.RepositoryDeclarationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRepositoryDeclaration()
   * @generated
   */
  int REPOSITORY_DECLARATION = 35;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY_DECLARATION__DOCUMENTATION = REPOSITORY_CONFIGURATION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY_DECLARATION__LOCATION = REPOSITORY_CONFIGURATION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY_DECLARATION__TYPE = REPOSITORY_CONFIGURATION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Context</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY_DECLARATION__CONTEXT = REPOSITORY_CONFIGURATION_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Repository Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY_DECLARATION_FEATURE_COUNT = REPOSITORY_CONFIGURATION_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ResolutionStrategyImpl <em>Resolution Strategy</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ResolutionStrategyImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getResolutionStrategy()
   * @generated
   */
  int RESOLUTION_STRATEGY = 36;

  /**
   * The feature id for the '<em><b>Strategy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOLUTION_STRATEGY__STRATEGY = REPOSITORY_CONFIGURATION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Repository Config</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOLUTION_STRATEGY__REPOSITORY_CONFIG = REPOSITORY_CONFIGURATION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Resolution Strategy</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESOLUTION_STRATEGY_FEATURE_COUNT = REPOSITORY_CONFIGURATION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ContainerConfigurationImpl <em>Container Configuration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ContainerConfigurationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getContainerConfiguration()
   * @generated
   */
  int CONTAINER_CONFIGURATION = 37;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_CONFIGURATION__DOCUMENTATION = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_CONFIGURATION__NAME = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_CONFIGURATION__TYPE = 2;

  /**
   * The feature id for the '<em><b>Context Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_CONFIGURATION__CONTEXT_BLOCK = 3;

  /**
   * The number of structural features of the '<em>Container Configuration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTAINER_CONFIGURATION_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ConcernImpl <em>Concern</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ConcernImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getConcern()
   * @generated
   */
  int CONCERN = 38;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCERN__DOCUMENTATION = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCERN__NAME = 1;

  /**
   * The feature id for the '<em><b>Concern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCERN__CONCERN = 2;

  /**
   * The number of structural features of the '<em>Concern</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCERN_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ConcernBlockImpl <em>Concern Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ConcernBlockImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getConcernBlock()
   * @generated
   */
  int CONCERN_BLOCK = 39;

  /**
   * The feature id for the '<em><b>Super Concerns</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCERN_BLOCK__SUPER_CONCERNS = 0;

  /**
   * The feature id for the '<em><b>Contexts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCERN_BLOCK__CONTEXTS = 1;

  /**
   * The feature id for the '<em><b>Functions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCERN_BLOCK__FUNCTIONS = 2;

  /**
   * The number of structural features of the '<em>Concern Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCERN_BLOCK_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.QueryImpl <em>Query</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.QueryImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getQuery()
   * @generated
   */
  int QUERY = 40;

  /**
   * The feature id for the '<em><b>Selector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY__SELECTOR = 0;

  /**
   * The number of structural features of the '<em>Query</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.QueryPathImpl <em>Query Path</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.QueryPathImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getQueryPath()
   * @generated
   */
  int QUERY_PATH = 41;

  /**
   * The feature id for the '<em><b>Absolute</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_PATH__ABSOLUTE = 0;

  /**
   * The feature id for the '<em><b>Selectors</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_PATH__SELECTORS = 1;

  /**
   * The number of structural features of the '<em>Query Path</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_PATH_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.SelectorImpl <em>Selector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.SelectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSelector()
   * @generated
   */
  int SELECTOR = 42;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTOR__NAME = 0;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTOR__OP = 1;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTOR__PATTERN = 2;

  /**
   * The feature id for the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTOR__PREDICATE = 3;

  /**
   * The number of structural features of the '<em>Selector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTOR_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FilterImpl <em>Filter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FilterImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFilter()
   * @generated
   */
  int FILTER = 43;

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
  int PRE_CONDITION = 44;

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
  int POST_CONDITION = 45;

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
  int ASSERTION_EXPRESSION = 46;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ExpressionListImpl <em>Expression List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ExpressionListImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpressionList()
   * @generated
   */
  int EXPRESSION_LIST = 47;

  /**
   * The feature id for the '<em><b>Expressions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_LIST__EXPRESSIONS = 0;

  /**
   * The number of structural features of the '<em>Expression List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.StatmentImpl <em>Statment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.StatmentImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getStatment()
   * @generated
   */
  int STATMENT = 48;

  /**
   * The number of structural features of the '<em>Statment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.StatementImpl <em>Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.StatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getStatement()
   * @generated
   */
  int STATEMENT = 49;

  /**
   * The number of structural features of the '<em>Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl <em>Function Or Method</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFunctionOrMethod()
   * @generated
   */
  int FUNCTION_OR_METHOD = 50;

  /**
   * The feature id for the '<em><b>Documentation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_OR_METHOD__DOCUMENTATION = 0;

  /**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_OR_METHOD__VISIBILITY = 1;

  /**
   * The feature id for the '<em><b>Execution Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_OR_METHOD__EXECUTION_MODE = 2;

  /**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_OR_METHOD__FINAL = 3;

  /**
   * The feature id for the '<em><b>Cached</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_OR_METHOD__CACHED = 4;

  /**
   * The feature id for the '<em><b>Form</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_OR_METHOD__FORM = 5;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_OR_METHOD__TYPE = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_OR_METHOD__NAME = 7;

  /**
   * The feature id for the '<em><b>Type Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_OR_METHOD__TYPE_PARAMS = 8;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_OR_METHOD__PARAMS = 9;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_OR_METHOD__BODY = 10;

  /**
   * The number of structural features of the '<em>Function Or Method</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_OR_METHOD_FEATURE_COUNT = 11;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.TypeParamDeclarationImpl <em>Type Param Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.TypeParamDeclarationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getTypeParamDeclaration()
   * @generated
   */
  int TYPE_PARAM_DECLARATION = 51;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_PARAM_DECLARATION__TYPE = 0;

  /**
   * The feature id for the '<em><b>Super Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_PARAM_DECLARATION__SUPER_TYPE = 1;

  /**
   * The number of structural features of the '<em>Type Param Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_PARAM_DECLARATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.IfExpressionTailImpl <em>If Expression Tail</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.IfExpressionTailImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getIfExpressionTail()
   * @generated
   */
  int IF_EXPRESSION_TAIL = 66;

  /**
   * The number of structural features of the '<em>If Expression Tail</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION_TAIL_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpression()
   * @generated
   */
  int EXPRESSION = 52;

  /**
   * The number of structural features of the '<em>Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = IF_EXPRESSION_TAIL_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.RuleTypeParamImpl <em>Rule Type Param</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.RuleTypeParamImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRuleTypeParam()
   * @generated
   */
  int RULE_TYPE_PARAM = 56;

  /**
   * The number of structural features of the '<em>Rule Type Param</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RULE_TYPE_PARAM_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.RuleTypeRefParamImpl <em>Rule Type Ref Param</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.RuleTypeRefParamImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRuleTypeRefParam()
   * @generated
   */
  int RULE_TYPE_REF_PARAM = 57;

  /**
   * The number of structural features of the '<em>Rule Type Ref Param</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RULE_TYPE_REF_PARAM_FEATURE_COUNT = RULE_TYPE_PARAM_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.TypeRefImpl <em>Type Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.TypeRefImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getTypeRef()
   * @generated
   */
  int TYPE_REF = 53;

  /**
   * The number of structural features of the '<em>Type Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_REF_FEATURE_COUNT = RULE_TYPE_REF_PARAM_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.SimpleTypeRefImpl <em>Simple Type Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.SimpleTypeRefImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSimpleTypeRef()
   * @generated
   */
  int SIMPLE_TYPE_REF = 54;

  /**
   * The feature id for the '<em><b>Type Name</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_TYPE_REF__TYPE_NAME = TYPE_REF_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Rule Type Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_TYPE_REF__RULE_TYPE_PARAMETER = TYPE_REF_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Simple Type Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_TYPE_REF_FEATURE_COUNT = TYPE_REF_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ClosureTypeRefImpl <em>Closure Type Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ClosureTypeRefImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getClosureTypeRef()
   * @generated
   */
  int CLOSURE_TYPE_REF = 55;

  /**
   * The feature id for the '<em><b>Parameter Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSURE_TYPE_REF__PARAMETER_TYPES = TYPE_REF_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Return Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSURE_TYPE_REF__RETURN_TYPE = TYPE_REF_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Closure Type Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSURE_TYPE_REF_FEATURE_COUNT = TYPE_REF_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.WildcardRefParamImpl <em>Wildcard Ref Param</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.WildcardRefParamImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWildcardRefParam()
   * @generated
   */
  int WILDCARD_REF_PARAM = 58;

  /**
   * The feature id for the '<em><b>Extends</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WILDCARD_REF_PARAM__EXTENDS = RULE_TYPE_PARAM_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Super Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WILDCARD_REF_PARAM__SUPER_REF = RULE_TYPE_PARAM_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Wildcard Ref Param</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WILDCARD_REF_PARAM_FEATURE_COUNT = RULE_TYPE_PARAM_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.UnaryExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUnaryExpression()
   * @generated
   */
  int UNARY_EXPRESSION = 59;

  /**
   * The number of structural features of the '<em>Unary Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ExpresionImpl <em>Expresion</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ExpresionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpresion()
   * @generated
   */
  int EXPRESION = 60;

  /**
   * The number of structural features of the '<em>Expresion</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.TryCatchExpressionImpl <em>Try Catch Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.TryCatchExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getTryCatchExpression()
   * @generated
   */
  int TRY_CATCH_EXPRESSION = 61;

  /**
   * The feature id for the '<em><b>Try Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRY_CATCH_EXPRESSION__TRY_BLOCK = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Catch</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRY_CATCH_EXPRESSION__CATCH = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Finally</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRY_CATCH_EXPRESSION__FINALLY = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Try Catch Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TRY_CATCH_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

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
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATCH_BLOCK__TYPE = 0;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATCH_BLOCK__VAR = 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATCH_BLOCK__EXPR = 2;

  /**
   * The number of structural features of the '<em>Catch Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATCH_BLOCK_FEATURE_COUNT = 3;

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
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FINALLY_BLOCK__EXPR = 0;

  /**
   * The number of structural features of the '<em>Finally Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FINALLY_BLOCK_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.SwitchExpressionImpl <em>Switch Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.SwitchExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSwitchExpression()
   * @generated
   */
  int SWITCH_EXPRESSION = 64;

  /**
   * The feature id for the '<em><b>Switch Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_EXPRESSION__SWITCH_EXPR = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Case</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_EXPRESSION__CASE = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Switch Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.CaseImpl <em>Case</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.CaseImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCase()
   * @generated
   */
  int CASE = 65;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CASE__CONDITION = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CASE__EXPR = 1;

  /**
   * The number of structural features of the '<em>Case</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CASE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FeatureCallImpl <em>Feature Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FeatureCallImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFeatureCall()
   * @generated
   */
  int FEATURE_CALL = 67;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.OperationCallImpl <em>Operation Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.OperationCallImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getOperationCall()
   * @generated
   */
  int OPERATION_CALL = 68;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__TARGET = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__TYPE = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__PARAMS = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__NAME = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Post Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__POST_TARGET = EXPRESSION_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__PARAMETERS = EXPRESSION_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>Operation Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 6;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ContextImpl <em>Context</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ContextImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getContext()
   * @generated
   */
  int CONTEXT = 69;

  /**
   * The feature id for the '<em><b>Selector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTEXT__SELECTOR = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTEXT__BLOCK = 1;

  /**
   * The number of structural features of the '<em>Context</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTEXT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ContextSelectorImpl <em>Context Selector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ContextSelectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getContextSelector()
   * @generated
   */
  int CONTEXT_SELECTOR = 70;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTEXT_SELECTOR__TYPE = 0;

  /**
   * The number of structural features of the '<em>Context Selector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTEXT_SELECTOR_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ExpressionSelectorImpl <em>Expression Selector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ExpressionSelectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpressionSelector()
   * @generated
   */
  int EXPRESSION_SELECTOR = 71;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_SELECTOR__TYPE = CONTEXT_SELECTOR__TYPE;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_SELECTOR__EXPR = CONTEXT_SELECTOR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Expression Selector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_SELECTOR_FEATURE_COUNT = CONTEXT_SELECTOR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.UnitSelectorImpl <em>Unit Selector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.UnitSelectorImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUnitSelector()
   * @generated
   */
  int UNIT_SELECTOR = 72;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIT_SELECTOR__TYPE = CONTEXT_SELECTOR__TYPE;

  /**
   * The feature id for the '<em><b>Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIT_SELECTOR__INTERFACE = CONTEXT_SELECTOR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIT_SELECTOR__NAME = CONTEXT_SELECTOR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Name Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIT_SELECTOR__NAME_PATTERN = CONTEXT_SELECTOR_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Version Range</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIT_SELECTOR__VERSION_RANGE = CONTEXT_SELECTOR_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Unit Selector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIT_SELECTOR_FEATURE_COUNT = CONTEXT_SELECTOR_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ContextBlockImpl <em>Context Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ContextBlockImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getContextBlock()
   * @generated
   */
  int CONTEXT_BLOCK = 73;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTEXT_BLOCK__STATEMENTS = 0;

  /**
   * The number of structural features of the '<em>Context Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTEXT_BLOCK_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ClosureExpressionImpl <em>Closure Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ClosureExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getClosureExpression()
   * @generated
   */
  int CLOSURE_EXPRESSION = 74;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSURE_EXPRESSION__PARAMETERS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSURE_EXPRESSION__EXPR = 1;

  /**
   * The number of structural features of the '<em>Closure Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSURE_EXPRESSION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FeatureImpl <em>Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FeatureImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFeature()
   * @generated
   */
  int FEATURE = 75;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.VersionImpl <em>Version</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.VersionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVersion()
   * @generated
   */
  int VERSION = 76;

  /**
   * The feature id for the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERSION__VERSION = 0;

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
  int VERSION_RANGE = 77;

  /**
   * The feature id for the '<em><b>Range</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERSION_RANGE__RANGE = 0;

  /**
   * The number of structural features of the '<em>Version Range</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERSION_RANGE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.VarargParameterDeclarationImpl <em>Vararg Parameter Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.VarargParameterDeclarationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVarargParameterDeclaration()
   * @generated
   */
  int VARARG_PARAMETER_DECLARATION = 78;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARARG_PARAMETER_DECLARATION__TYPE = PARAMETER_DECLARATION__TYPE;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARARG_PARAMETER_DECLARATION__NAME = PARAMETER_DECLARATION__NAME;

  /**
   * The number of structural features of the '<em>Vararg Parameter Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARARG_PARAMETER_DECLARATION_FEATURE_COUNT = PARAMETER_DECLARATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FunctionImpl <em>Function</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FunctionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFunction()
   * @generated
   */
  int FUNCTION = 79;

  /**
   * The feature id for the '<em><b>Func</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION__FUNC = STATMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Function</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_FEATURE_COUNT = STATMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.MethodImpl <em>Method</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.MethodImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getMethod()
   * @generated
   */
  int METHOD = 80;

  /**
   * The feature id for the '<em><b>Method</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD__METHOD = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Method</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ReturnExpressionImpl <em>Return Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ReturnExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getReturnExpression()
   * @generated
   */
  int RETURN_EXPRESSION = 81;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RETURN_EXPRESSION__EXPR = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Return Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RETURN_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.AssignmentOperationImpl <em>Assignment Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.AssignmentOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAssignmentOperation()
   * @generated
   */
  int ASSIGNMENT_OPERATION = 82;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.VarDeclarationImpl <em>Var Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.VarDeclarationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVarDeclaration()
   * @generated
   */
  int VAR_DECLARATION = 83;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_DECLARATION__TYPE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_DECLARATION__NAME = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Var Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VAR_DECLARATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BooleanOperationImpl <em>Boolean Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BooleanOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBooleanOperation()
   * @generated
   */
  int BOOLEAN_OPERATION = 84;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.RelationalOperationImpl <em>Relational Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.RelationalOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRelationalOperation()
   * @generated
   */
  int RELATIONAL_OPERATION = 85;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.SetOperationCallImpl <em>Set Operation Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.SetOperationCallImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSetOperationCall()
   * @generated
   */
  int SET_OPERATION_CALL = 86;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_OPERATION_CALL__PARAMS = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_OPERATION_CALL__OP = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Set Operation Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_OPERATION_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.UnaryOperationImpl <em>Unary Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.UnaryOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUnaryOperation()
   * @generated
   */
  int UNARY_OPERATION = 87;

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
  int AT_CALL = 88;

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
   * The feature id for the '<em><b>Parameters</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AT_CALL__PARAMETERS = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>At Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AT_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.WithClauseExpressionImpl <em>With Clause Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.WithClauseExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWithClauseExpression()
   * @generated
   */
  int WITH_CLAUSE_EXPRESSION = 89;

  /**
   * The feature id for the '<em><b>Withclause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_CLAUSE_EXPRESSION__WITHCLAUSE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_CLAUSE_EXPRESSION__EXPR = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>With Clause Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_CLAUSE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.WithContextExpressionImpl <em>With Context Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.WithContextExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWithContextExpression()
   * @generated
   */
  int WITH_CONTEXT_EXPRESSION = 90;

  /**
   * The feature id for the '<em><b>Context</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_CONTEXT_EXPRESSION__CONTEXT = EXPRESION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_CONTEXT_EXPRESSION__EXPR = EXPRESION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>With Context Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WITH_CONTEXT_EXPRESSION_FEATURE_COUNT = EXPRESION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.WildcardExpressionImpl <em>Wildcard Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.WildcardExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWildcardExpression()
   * @generated
   */
  int WILDCARD_EXPRESSION = 91;

  /**
   * The number of structural features of the '<em>Wildcard Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WILDCARD_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ThrowExpressionImpl <em>Throw Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ThrowExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getThrowExpression()
   * @generated
   */
  int THROW_EXPRESSION = 92;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THROW_EXPRESSION__EXPR = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Throw Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THROW_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BlockExpressionImpl <em>Block Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BlockExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBlockExpression()
   * @generated
   */
  int BLOCK_EXPRESSION = 93;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_EXPRESSION__EXPR = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Block Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.IfExpressionImpl <em>If Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.IfExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getIfExpression()
   * @generated
   */
  int IF_EXPRESSION = 94;

  /**
   * The feature id for the '<em><b>Cond</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION__COND = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION__VALUE = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Tail</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION__TAIL = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>If Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ElseIfExpressionImpl <em>Else If Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ElseIfExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getElseIfExpression()
   * @generated
   */
  int ELSE_IF_EXPRESSION = 95;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELSE_IF_EXPRESSION__CONDITION = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELSE_IF_EXPRESSION__VALUE = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Else If Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELSE_IF_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ElseExpressionImpl <em>Else Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ElseExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getElseExpression()
   * @generated
   */
  int ELSE_EXPRESSION = 96;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELSE_EXPRESSION__VALUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Else Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELSE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PropertyValueImpl <em>Property Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PropertyValueImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPropertyValue()
   * @generated
   */
  int PROPERTY_VALUE = 97;

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
  int VARIABLE_VALUE = 98;

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
  int KEYWORD_VARIABLE = 99;

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
  int CREATOR_CALL = 100;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CREATOR_CALL__NAME = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CREATOR_CALL__TARGET = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CREATOR_CALL__PARAMETERS = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Alias</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CREATOR_CALL__ALIAS = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Context Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CREATOR_CALL__CONTEXT_BLOCK = EXPRESSION_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Creator Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CREATOR_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ExpressionStatementImpl <em>Expression Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ExpressionStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpressionStatement()
   * @generated
   */
  int EXPRESSION_STATEMENT = 101;

  /**
   * The feature id for the '<em><b>Val</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_STATEMENT__VAL = STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Expression Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PropertiesStatementImpl <em>Properties Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PropertiesStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPropertiesStatement()
   * @generated
   */
  int PROPERTIES_STATEMENT = 102;

  /**
   * The feature id for the '<em><b>Properties</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTIES_STATEMENT__PROPERTIES = STATMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Properties Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTIES_STATEMENT_FEATURE_COUNT = STATMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.LiteralListImpl <em>Literal List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.LiteralListImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLiteralList()
   * @generated
   */
  int LITERAL_LIST = 103;

  /**
   * The feature id for the '<em><b>Element</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_LIST__ELEMENT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_LIST__TYPE = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Literal List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_LIST_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.LiteralMapImpl <em>Literal Map</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.LiteralMapImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLiteralMap()
   * @generated
   */
  int LITERAL_MAP = 104;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_MAP__FEATURES = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Key Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_MAP__KEY_TYPE = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Val Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_MAP__VAL_TYPE = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Literal Map</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_MAP_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.LiteralFunctionImpl <em>Literal Function</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.LiteralFunctionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLiteralFunction()
   * @generated
   */
  int LITERAL_FUNCTION = 105;

  /**
   * The feature id for the '<em><b>Closure</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_FUNCTION__CLOSURE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Literal Function</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_FUNCTION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BooleanLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBooleanLiteral()
   * @generated
   */
  int BOOLEAN_LITERAL = 106;

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
  int INTEGER_LITERAL = 107;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_LITERAL__VAL = EXPRESSION_FEATURE_COUNT + 0;

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
  int NULL_LITERAL = 108;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ThisLiteralImpl <em>This Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ThisLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getThisLiteral()
   * @generated
   */
  int THIS_LITERAL = 109;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THIS_LITERAL__VAL = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>This Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THIS_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.SuperLiteralImpl <em>Super Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.SuperLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSuperLiteral()
   * @generated
   */
  int SUPER_LITERAL = 110;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUPER_LITERAL__VAL = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Super Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUPER_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.UnitLiteralImpl <em>Unit Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.UnitLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUnitLiteral()
   * @generated
   */
  int UNIT_LITERAL = 111;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIT_LITERAL__VAL = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Unit Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIT_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.StringLiteralImpl <em>String Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.StringLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getStringLiteral()
   * @generated
   */
  int STRING_LITERAL = 112;

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
  int REAL_LITERAL = 113;

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
  int REGEXP_LITERAL = 114;

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
  int QUERY_LITERAL = 115;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.SelectorOperator <em>Selector Operator</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.SelectorOperator
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSelectorOperator()
   * @generated
   */
  int SELECTOR_OPERATOR = 116;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.AssignmentOperator <em>Assignment Operator</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.AssignmentOperator
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAssignmentOperator()
   * @generated
   */
  int ASSIGNMENT_OPERATOR = 117;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.RelationalOperator <em>Relational Operator</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.RelationalOperator
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRelationalOperator()
   * @generated
   */
  int RELATIONAL_OPERATOR = 118;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.SetOperator <em>Set Operator</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.SetOperator
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSetOperator()
   * @generated
   */
  int SET_OPERATOR = 119;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.Visibility <em>Visibility</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.Visibility
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVisibility()
   * @generated
   */
  int VISIBILITY = 120;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.ExecutionMode <em>Execution Mode</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.ExecutionMode
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExecutionMode()
   * @generated
   */
  int EXECUTION_MODE = 121;


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
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BeeModel#getFunctions <em>Functions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Functions</em>'.
   * @see org.eclipse.b3.beeLang.BeeModel#getFunctions()
   * @see #getBeeModel()
   * @generated
   */
  EReference getBeeModel_Functions();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BeeModel#getConcern <em>Concern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Concern</em>'.
   * @see org.eclipse.b3.beeLang.BeeModel#getConcern()
   * @see #getBeeModel()
   * @generated
   */
  EReference getBeeModel_Concern();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Import <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Import</em>'.
   * @see org.eclipse.b3.beeLang.Import
   * @generated
   */
  EClass getImport();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Import#isReexport <em>Reexport</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Reexport</em>'.
   * @see org.eclipse.b3.beeLang.Import#isReexport()
   * @see #getImport()
   * @generated
   */
  EAttribute getImport_Reexport();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Import#getImporter <em>Importer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Importer</em>'.
   * @see org.eclipse.b3.beeLang.Import#getImporter()
   * @see #getImport()
   * @generated
   */
  EReference getImport_Importer();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Import#getNameSpace <em>Name Space</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name Space</em>'.
   * @see org.eclipse.b3.beeLang.Import#getNameSpace()
   * @see #getImport()
   * @generated
   */
  EAttribute getImport_NameSpace();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.JavaImporter <em>Java Importer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Java Importer</em>'.
   * @see org.eclipse.b3.beeLang.JavaImporter
   * @generated
   */
  EClass getJavaImporter();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.JavaImporter#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.JavaImporter#getName()
   * @see #getJavaImporter()
   * @generated
   */
  EAttribute getJavaImporter_Name();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.NativeImporter <em>Native Importer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Native Importer</em>'.
   * @see org.eclipse.b3.beeLang.NativeImporter
   * @generated
   */
  EClass getNativeImporter();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.NativeImporter#getUriString <em>Uri String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Uri String</em>'.
   * @see org.eclipse.b3.beeLang.NativeImporter#getUriString()
   * @see #getNativeImporter()
   * @generated
   */
  EAttribute getNativeImporter_UriString();

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
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.BuildUnit#getDocumentation <em>Documentation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Documentation</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getDocumentation()
   * @see #getBuildUnit()
   * @generated
   */
  EAttribute getBuildUnit_Documentation();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.BuildUnit#getDefaultProperties <em>Default Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Default Properties</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getDefaultProperties()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_DefaultProperties();

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
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getConcerns <em>Concerns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Concerns</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getConcerns()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_Concerns();

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
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getBuilders <em>Builders</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Builders</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getBuilders()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_Builders();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getMethods <em>Methods</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Methods</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getMethods()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_Methods();

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
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getPropertySets <em>Property Sets</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Property Sets</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getPropertySets()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_PropertySets();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getContainers <em>Containers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Containers</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getContainers()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_Containers();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ProvidedCapability#getCapability <em>Capability</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Capability</em>'.
   * @see org.eclipse.b3.beeLang.ProvidedCapability#getCapability()
   * @see #getProvidedCapability()
   * @generated
   */
  EReference getProvidedCapability_Capability();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.RequiredCapability#getCapability <em>Capability</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Capability</em>'.
   * @see org.eclipse.b3.beeLang.RequiredCapability#getCapability()
   * @see #getRequiredCapability()
   * @generated
   */
  EReference getRequiredCapability_Capability();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.FilteredCapability <em>Filtered Capability</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Filtered Capability</em>'.
   * @see org.eclipse.b3.beeLang.FilteredCapability
   * @generated
   */
  EClass getFilteredCapability();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.FilteredCapability#getFilter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Filter</em>'.
   * @see org.eclipse.b3.beeLang.FilteredCapability#getFilter()
   * @see #getFilteredCapability()
   * @generated
   */
  EReference getFilteredCapability_Filter();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.FilteredCapability#getCapability <em>Capability</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Capability</em>'.
   * @see org.eclipse.b3.beeLang.FilteredCapability#getCapability()
   * @see #getFilteredCapability()
   * @generated
   */
  EReference getFilteredCapability_Capability();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Capability <em>Capability</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Capability</em>'.
   * @see org.eclipse.b3.beeLang.Capability
   * @generated
   */
  EClass getCapability();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Capability#getInterface <em>Interface</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Interface</em>'.
   * @see org.eclipse.b3.beeLang.Capability#getInterface()
   * @see #getCapability()
   * @generated
   */
  EAttribute getCapability_Interface();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Capability#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.Capability#getName()
   * @see #getCapability()
   * @generated
   */
  EAttribute getCapability_Name();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.NamedPropertySet <em>Named Property Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Property Set</em>'.
   * @see org.eclipse.b3.beeLang.NamedPropertySet
   * @generated
   */
  EClass getNamedPropertySet();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.NamedPropertySet#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.NamedPropertySet#getName()
   * @see #getNamedPropertySet()
   * @generated
   */
  EAttribute getNamedPropertySet_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.NamedPropertySet#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.eclipse.b3.beeLang.NamedPropertySet#getBody()
   * @see #getNamedPropertySet()
   * @generated
   */
  EReference getNamedPropertySet_Body();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PropertySet <em>Property Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Set</em>'.
   * @see org.eclipse.b3.beeLang.PropertySet
   * @generated
   */
  EClass getPropertySet();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.b3.beeLang.PropertySet#getExtends <em>Extends</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Extends</em>'.
   * @see org.eclipse.b3.beeLang.PropertySet#getExtends()
   * @see #getPropertySet()
   * @generated
   */
  EReference getPropertySet_Extends();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.PropertySet#getOperations <em>Operations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Operations</em>'.
   * @see org.eclipse.b3.beeLang.PropertySet#getOperations()
   * @see #getPropertySet()
   * @generated
   */
  EReference getPropertySet_Operations();

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
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.SetPropertyOperation#isFinal <em>Final</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Final</em>'.
   * @see org.eclipse.b3.beeLang.SetPropertyOperation#isFinal()
   * @see #getSetPropertyOperation()
   * @generated
   */
  EAttribute getSetPropertyOperation_Final();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Prerequisite#getWithClause <em>With Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>With Clause</em>'.
   * @see org.eclipse.b3.beeLang.Prerequisite#getWithClause()
   * @see #getPrerequisite()
   * @generated
   */
  EReference getPrerequisite_WithClause();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.WithClause <em>With Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>With Clause</em>'.
   * @see org.eclipse.b3.beeLang.WithClause
   * @generated
   */
  EClass getWithClause();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.WithClause#getReferences <em>References</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>References</em>'.
   * @see org.eclipse.b3.beeLang.WithClause#getReferences()
   * @see #getWithClause()
   * @generated
   */
  EReference getWithClause_References();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.WithClause#getProperties <em>Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Properties</em>'.
   * @see org.eclipse.b3.beeLang.WithClause#getProperties()
   * @see #getWithClause()
   * @generated
   */
  EReference getWithClause_Properties();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.WithClause#getConcern <em>Concern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Concern</em>'.
   * @see org.eclipse.b3.beeLang.WithClause#getConcern()
   * @see #getWithClause()
   * @generated
   */
  EReference getWithClause_Concern();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.DirectPartReferemce <em>Direct Part Referemce</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Direct Part Referemce</em>'.
   * @see org.eclipse.b3.beeLang.DirectPartReferemce
   * @generated
   */
  EClass getDirectPartReferemce();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.DirectPartReferemce#getUnit <em>Unit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Unit</em>'.
   * @see org.eclipse.b3.beeLang.DirectPartReferemce#getUnit()
   * @see #getDirectPartReferemce()
   * @generated
   */
  EAttribute getDirectPartReferemce_Unit();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.DirectPartReferemce#getBuilder <em>Builder</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Builder</em>'.
   * @see org.eclipse.b3.beeLang.DirectPartReferemce#getBuilder()
   * @see #getDirectPartReferemce()
   * @generated
   */
  EAttribute getDirectPartReferemce_Builder();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.DirectPartReferemce#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameters</em>'.
   * @see org.eclipse.b3.beeLang.DirectPartReferemce#getParameters()
   * @see #getDirectPartReferemce()
   * @generated
   */
  EReference getDirectPartReferemce_Parameters();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getCapability <em>Capability</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Capability</em>'.
   * @see org.eclipse.b3.beeLang.CapabilityReferencedPart#getCapability()
   * @see #getCapabilityReferencedPart()
   * @generated
   */
  EReference getCapabilityReferencedPart_Capability();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameters</em>'.
   * @see org.eclipse.b3.beeLang.CapabilityReferencedPart#getParameters()
   * @see #getCapabilityReferencedPart()
   * @generated
   */
  EReference getCapabilityReferencedPart_Parameters();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Builder <em>Builder</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Builder</em>'.
   * @see org.eclipse.b3.beeLang.Builder
   * @generated
   */
  EClass getBuilder();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Builder#getDocumentation <em>Documentation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Documentation</em>'.
   * @see org.eclipse.b3.beeLang.Builder#getDocumentation()
   * @see #getBuilder()
   * @generated
   */
  EAttribute getBuilder_Documentation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Builder#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.eclipse.b3.beeLang.Builder#getVisibility()
   * @see #getBuilder()
   * @generated
   */
  EAttribute getBuilder_Visibility();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Builder#getExecutionMode <em>Execution Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Execution Mode</em>'.
   * @see org.eclipse.b3.beeLang.Builder#getExecutionMode()
   * @see #getBuilder()
   * @generated
   */
  EAttribute getBuilder_ExecutionMode();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Builder#isFinal <em>Final</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Final</em>'.
   * @see org.eclipse.b3.beeLang.Builder#isFinal()
   * @see #getBuilder()
   * @generated
   */
  EAttribute getBuilder_Final();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Builder#isCached <em>Cached</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cached</em>'.
   * @see org.eclipse.b3.beeLang.Builder#isCached()
   * @see #getBuilder()
   * @generated
   */
  EAttribute getBuilder_Cached();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Builder#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.Builder#getName()
   * @see #getBuilder()
   * @generated
   */
  EAttribute getBuilder_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Builder#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Params</em>'.
   * @see org.eclipse.b3.beeLang.Builder#getParams()
   * @see #getBuilder()
   * @generated
   */
  EReference getBuilder_Params();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.Builder#getProvidedCapabilities <em>Provided Capabilities</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Provided Capabilities</em>'.
   * @see org.eclipse.b3.beeLang.Builder#getProvidedCapabilities()
   * @see #getBuilder()
   * @generated
   */
  EReference getBuilder_ProvidedCapabilities();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Builder#getPreCondition <em>Pre Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pre Condition</em>'.
   * @see org.eclipse.b3.beeLang.Builder#getPreCondition()
   * @see #getBuilder()
   * @generated
   */
  EReference getBuilder_PreCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Builder#getPostCondition <em>Post Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Post Condition</em>'.
   * @see org.eclipse.b3.beeLang.Builder#getPostCondition()
   * @see #getBuilder()
   * @generated
   */
  EReference getBuilder_PostCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Builder#getProperties <em>Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Properties</em>'.
   * @see org.eclipse.b3.beeLang.Builder#getProperties()
   * @see #getBuilder()
   * @generated
   */
  EReference getBuilder_Properties();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Builder#getInput <em>Input</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Input</em>'.
   * @see org.eclipse.b3.beeLang.Builder#getInput()
   * @see #getBuilder()
   * @generated
   */
  EReference getBuilder_Input();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Builder#getOutput <em>Output</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Output</em>'.
   * @see org.eclipse.b3.beeLang.Builder#getOutput()
   * @see #getBuilder()
   * @generated
   */
  EReference getBuilder_Output();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Builder#getExpressionList <em>Expression List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression List</em>'.
   * @see org.eclipse.b3.beeLang.Builder#getExpressionList()
   * @see #getBuilder()
   * @generated
   */
  EReference getBuilder_ExpressionList();

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
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Parameter#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.Parameter#getName()
   * @see #getParameter()
   * @generated
   */
  EAttribute getParameter_Name();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ParameterList <em>Parameter List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter List</em>'.
   * @see org.eclipse.b3.beeLang.ParameterList
   * @generated
   */
  EClass getParameterList();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ParameterList#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.eclipse.b3.beeLang.ParameterList#getParams()
   * @see #getParameterList()
   * @generated
   */
  EReference getParameterList_Params();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ParameterDeclarationList <em>Parameter Declaration List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter Declaration List</em>'.
   * @see org.eclipse.b3.beeLang.ParameterDeclarationList
   * @generated
   */
  EClass getParameterDeclarationList();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ParameterDeclarationList#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.eclipse.b3.beeLang.ParameterDeclarationList#getParams()
   * @see #getParameterDeclarationList()
   * @generated
   */
  EReference getParameterDeclarationList_Params();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ParameterDeclaration <em>Parameter Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter Declaration</em>'.
   * @see org.eclipse.b3.beeLang.ParameterDeclaration
   * @generated
   */
  EClass getParameterDeclaration();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ParameterDeclaration#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.eclipse.b3.beeLang.ParameterDeclaration#getType()
   * @see #getParameterDeclaration()
   * @generated
   */
  EReference getParameterDeclaration_Type();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.ParameterDeclaration#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.ParameterDeclaration#getName()
   * @see #getParameterDeclaration()
   * @generated
   */
  EAttribute getParameterDeclaration_Name();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.BuilderOutput <em>Builder Output</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Builder Output</em>'.
   * @see org.eclipse.b3.beeLang.BuilderOutput
   * @generated
   */
  EClass getBuilderOutput();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.BuilderOutput#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.eclipse.b3.beeLang.BuilderOutput#getBody()
   * @see #getBuilderOutput()
   * @generated
   */
  EReference getBuilderOutput_Body();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.BuilderInput <em>Builder Input</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Builder Input</em>'.
   * @see org.eclipse.b3.beeLang.BuilderInput
   * @generated
   */
  EClass getBuilderInput();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.BuilderInput#getPreCondition <em>Pre Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pre Condition</em>'.
   * @see org.eclipse.b3.beeLang.BuilderInput#getPreCondition()
   * @see #getBuilderInput()
   * @generated
   */
  EReference getBuilderInput_PreCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.BuilderInput#getPostCondition <em>Post Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Post Condition</em>'.
   * @see org.eclipse.b3.beeLang.BuilderInput#getPostCondition()
   * @see #getBuilderInput()
   * @generated
   */
  EReference getBuilderInput_PostCondition();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuilderInput#getPrerequisites <em>Prerequisites</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Prerequisites</em>'.
   * @see org.eclipse.b3.beeLang.BuilderInput#getPrerequisites()
   * @see #getBuilderInput()
   * @generated
   */
  EReference getBuilderInput_Prerequisites();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.RepositoryDeclaration <em>Repository Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Repository Declaration</em>'.
   * @see org.eclipse.b3.beeLang.RepositoryDeclaration
   * @generated
   */
  EClass getRepositoryDeclaration();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getDocumentation <em>Documentation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Documentation</em>'.
   * @see org.eclipse.b3.beeLang.RepositoryDeclaration#getDocumentation()
   * @see #getRepositoryDeclaration()
   * @generated
   */
  EAttribute getRepositoryDeclaration_Documentation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getLocation <em>Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Location</em>'.
   * @see org.eclipse.b3.beeLang.RepositoryDeclaration#getLocation()
   * @see #getRepositoryDeclaration()
   * @generated
   */
  EAttribute getRepositoryDeclaration_Location();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.eclipse.b3.beeLang.RepositoryDeclaration#getType()
   * @see #getRepositoryDeclaration()
   * @generated
   */
  EReference getRepositoryDeclaration_Type();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getContext <em>Context</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Context</em>'.
   * @see org.eclipse.b3.beeLang.RepositoryDeclaration#getContext()
   * @see #getRepositoryDeclaration()
   * @generated
   */
  EReference getRepositoryDeclaration_Context();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ResolutionStrategy <em>Resolution Strategy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Resolution Strategy</em>'.
   * @see org.eclipse.b3.beeLang.ResolutionStrategy
   * @generated
   */
  EClass getResolutionStrategy();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.ResolutionStrategy#getStrategy <em>Strategy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Strategy</em>'.
   * @see org.eclipse.b3.beeLang.ResolutionStrategy#getStrategy()
   * @see #getResolutionStrategy()
   * @generated
   */
  EAttribute getResolutionStrategy_Strategy();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ResolutionStrategy#getRepositoryConfig <em>Repository Config</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Repository Config</em>'.
   * @see org.eclipse.b3.beeLang.ResolutionStrategy#getRepositoryConfig()
   * @see #getResolutionStrategy()
   * @generated
   */
  EReference getResolutionStrategy_RepositoryConfig();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ContainerConfiguration <em>Container Configuration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Container Configuration</em>'.
   * @see org.eclipse.b3.beeLang.ContainerConfiguration
   * @generated
   */
  EClass getContainerConfiguration();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.ContainerConfiguration#getDocumentation <em>Documentation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Documentation</em>'.
   * @see org.eclipse.b3.beeLang.ContainerConfiguration#getDocumentation()
   * @see #getContainerConfiguration()
   * @generated
   */
  EAttribute getContainerConfiguration_Documentation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.ContainerConfiguration#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.ContainerConfiguration#getName()
   * @see #getContainerConfiguration()
   * @generated
   */
  EAttribute getContainerConfiguration_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ContainerConfiguration#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.eclipse.b3.beeLang.ContainerConfiguration#getType()
   * @see #getContainerConfiguration()
   * @generated
   */
  EReference getContainerConfiguration_Type();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ContainerConfiguration#getContextBlock <em>Context Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Context Block</em>'.
   * @see org.eclipse.b3.beeLang.ContainerConfiguration#getContextBlock()
   * @see #getContainerConfiguration()
   * @generated
   */
  EReference getContainerConfiguration_ContextBlock();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Concern <em>Concern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Concern</em>'.
   * @see org.eclipse.b3.beeLang.Concern
   * @generated
   */
  EClass getConcern();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Concern#getDocumentation <em>Documentation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Documentation</em>'.
   * @see org.eclipse.b3.beeLang.Concern#getDocumentation()
   * @see #getConcern()
   * @generated
   */
  EAttribute getConcern_Documentation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Concern#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.Concern#getName()
   * @see #getConcern()
   * @generated
   */
  EAttribute getConcern_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Concern#getConcern <em>Concern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Concern</em>'.
   * @see org.eclipse.b3.beeLang.Concern#getConcern()
   * @see #getConcern()
   * @generated
   */
  EReference getConcern_Concern();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ConcernBlock <em>Concern Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Concern Block</em>'.
   * @see org.eclipse.b3.beeLang.ConcernBlock
   * @generated
   */
  EClass getConcernBlock();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.b3.beeLang.ConcernBlock#getSuperConcerns <em>Super Concerns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Super Concerns</em>'.
   * @see org.eclipse.b3.beeLang.ConcernBlock#getSuperConcerns()
   * @see #getConcernBlock()
   * @generated
   */
  EAttribute getConcernBlock_SuperConcerns();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ConcernBlock#getContexts <em>Contexts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Contexts</em>'.
   * @see org.eclipse.b3.beeLang.ConcernBlock#getContexts()
   * @see #getConcernBlock()
   * @generated
   */
  EReference getConcernBlock_Contexts();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ConcernBlock#getFunctions <em>Functions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Functions</em>'.
   * @see org.eclipse.b3.beeLang.ConcernBlock#getFunctions()
   * @see #getConcernBlock()
   * @generated
   */
  EReference getConcernBlock_Functions();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Query <em>Query</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Query</em>'.
   * @see org.eclipse.b3.beeLang.Query
   * @generated
   */
  EClass getQuery();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Query#getSelector <em>Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Selector</em>'.
   * @see org.eclipse.b3.beeLang.Query#getSelector()
   * @see #getQuery()
   * @generated
   */
  EReference getQuery_Selector();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.QueryPath <em>Query Path</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Query Path</em>'.
   * @see org.eclipse.b3.beeLang.QueryPath
   * @generated
   */
  EClass getQueryPath();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.QueryPath#isAbsolute <em>Absolute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Absolute</em>'.
   * @see org.eclipse.b3.beeLang.QueryPath#isAbsolute()
   * @see #getQueryPath()
   * @generated
   */
  EAttribute getQueryPath_Absolute();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.QueryPath#getSelectors <em>Selectors</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Selectors</em>'.
   * @see org.eclipse.b3.beeLang.QueryPath#getSelectors()
   * @see #getQueryPath()
   * @generated
   */
  EReference getQueryPath_Selectors();

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
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Selector#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.Selector#getName()
   * @see #getSelector()
   * @generated
   */
  EAttribute getSelector_Name();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Selector#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see org.eclipse.b3.beeLang.Selector#getOp()
   * @see #getSelector()
   * @generated
   */
  EAttribute getSelector_Op();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Selector#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pattern</em>'.
   * @see org.eclipse.b3.beeLang.Selector#getPattern()
   * @see #getSelector()
   * @generated
   */
  EAttribute getSelector_Pattern();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ExpressionList <em>Expression List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression List</em>'.
   * @see org.eclipse.b3.beeLang.ExpressionList
   * @generated
   */
  EClass getExpressionList();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ExpressionList#getExpressions <em>Expressions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expressions</em>'.
   * @see org.eclipse.b3.beeLang.ExpressionList#getExpressions()
   * @see #getExpressionList()
   * @generated
   */
  EReference getExpressionList_Expressions();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Statment <em>Statment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Statment</em>'.
   * @see org.eclipse.b3.beeLang.Statment
   * @generated
   */
  EClass getStatment();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.FunctionOrMethod <em>Function Or Method</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Or Method</em>'.
   * @see org.eclipse.b3.beeLang.FunctionOrMethod
   * @generated
   */
  EClass getFunctionOrMethod();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getDocumentation <em>Documentation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Documentation</em>'.
   * @see org.eclipse.b3.beeLang.FunctionOrMethod#getDocumentation()
   * @see #getFunctionOrMethod()
   * @generated
   */
  EAttribute getFunctionOrMethod_Documentation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.eclipse.b3.beeLang.FunctionOrMethod#getVisibility()
   * @see #getFunctionOrMethod()
   * @generated
   */
  EAttribute getFunctionOrMethod_Visibility();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getExecutionMode <em>Execution Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Execution Mode</em>'.
   * @see org.eclipse.b3.beeLang.FunctionOrMethod#getExecutionMode()
   * @see #getFunctionOrMethod()
   * @generated
   */
  EAttribute getFunctionOrMethod_ExecutionMode();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.FunctionOrMethod#isFinal <em>Final</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Final</em>'.
   * @see org.eclipse.b3.beeLang.FunctionOrMethod#isFinal()
   * @see #getFunctionOrMethod()
   * @generated
   */
  EAttribute getFunctionOrMethod_Final();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.FunctionOrMethod#isCached <em>Cached</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cached</em>'.
   * @see org.eclipse.b3.beeLang.FunctionOrMethod#isCached()
   * @see #getFunctionOrMethod()
   * @generated
   */
  EAttribute getFunctionOrMethod_Cached();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getForm <em>Form</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Form</em>'.
   * @see org.eclipse.b3.beeLang.FunctionOrMethod#getForm()
   * @see #getFunctionOrMethod()
   * @generated
   */
  EAttribute getFunctionOrMethod_Form();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.eclipse.b3.beeLang.FunctionOrMethod#getType()
   * @see #getFunctionOrMethod()
   * @generated
   */
  EReference getFunctionOrMethod_Type();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.FunctionOrMethod#getName()
   * @see #getFunctionOrMethod()
   * @generated
   */
  EAttribute getFunctionOrMethod_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getTypeParams <em>Type Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Type Params</em>'.
   * @see org.eclipse.b3.beeLang.FunctionOrMethod#getTypeParams()
   * @see #getFunctionOrMethod()
   * @generated
   */
  EReference getFunctionOrMethod_TypeParams();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.eclipse.b3.beeLang.FunctionOrMethod#getParams()
   * @see #getFunctionOrMethod()
   * @generated
   */
  EReference getFunctionOrMethod_Params();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.eclipse.b3.beeLang.FunctionOrMethod#getBody()
   * @see #getFunctionOrMethod()
   * @generated
   */
  EReference getFunctionOrMethod_Body();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.TypeParamDeclaration <em>Type Param Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Param Declaration</em>'.
   * @see org.eclipse.b3.beeLang.TypeParamDeclaration
   * @generated
   */
  EClass getTypeParamDeclaration();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.TypeParamDeclaration#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Type</em>'.
   * @see org.eclipse.b3.beeLang.TypeParamDeclaration#getType()
   * @see #getTypeParamDeclaration()
   * @generated
   */
  EReference getTypeParamDeclaration_Type();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.TypeParamDeclaration#getSuperType <em>Super Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Super Type</em>'.
   * @see org.eclipse.b3.beeLang.TypeParamDeclaration#getSuperType()
   * @see #getTypeParamDeclaration()
   * @generated
   */
  EReference getTypeParamDeclaration_SuperType();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.TypeRef <em>Type Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Ref</em>'.
   * @see org.eclipse.b3.beeLang.TypeRef
   * @generated
   */
  EClass getTypeRef();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.SimpleTypeRef <em>Simple Type Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Simple Type Ref</em>'.
   * @see org.eclipse.b3.beeLang.SimpleTypeRef
   * @generated
   */
  EClass getSimpleTypeRef();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.b3.beeLang.SimpleTypeRef#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Type Name</em>'.
   * @see org.eclipse.b3.beeLang.SimpleTypeRef#getTypeName()
   * @see #getSimpleTypeRef()
   * @generated
   */
  EAttribute getSimpleTypeRef_TypeName();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.SimpleTypeRef#getRuleTypeParameter <em>Rule Type Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Rule Type Parameter</em>'.
   * @see org.eclipse.b3.beeLang.SimpleTypeRef#getRuleTypeParameter()
   * @see #getSimpleTypeRef()
   * @generated
   */
  EReference getSimpleTypeRef_RuleTypeParameter();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ClosureTypeRef <em>Closure Type Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Closure Type Ref</em>'.
   * @see org.eclipse.b3.beeLang.ClosureTypeRef
   * @generated
   */
  EClass getClosureTypeRef();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ClosureTypeRef#getParameterTypes <em>Parameter Types</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameter Types</em>'.
   * @see org.eclipse.b3.beeLang.ClosureTypeRef#getParameterTypes()
   * @see #getClosureTypeRef()
   * @generated
   */
  EReference getClosureTypeRef_ParameterTypes();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ClosureTypeRef#getReturnType <em>Return Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Return Type</em>'.
   * @see org.eclipse.b3.beeLang.ClosureTypeRef#getReturnType()
   * @see #getClosureTypeRef()
   * @generated
   */
  EReference getClosureTypeRef_ReturnType();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.RuleTypeParam <em>Rule Type Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Rule Type Param</em>'.
   * @see org.eclipse.b3.beeLang.RuleTypeParam
   * @generated
   */
  EClass getRuleTypeParam();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.RuleTypeRefParam <em>Rule Type Ref Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Rule Type Ref Param</em>'.
   * @see org.eclipse.b3.beeLang.RuleTypeRefParam
   * @generated
   */
  EClass getRuleTypeRefParam();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.WildcardRefParam <em>Wildcard Ref Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Wildcard Ref Param</em>'.
   * @see org.eclipse.b3.beeLang.WildcardRefParam
   * @generated
   */
  EClass getWildcardRefParam();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.WildcardRefParam#getExtends <em>Extends</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Extends</em>'.
   * @see org.eclipse.b3.beeLang.WildcardRefParam#getExtends()
   * @see #getWildcardRefParam()
   * @generated
   */
  EReference getWildcardRefParam_Extends();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.WildcardRefParam#getSuperRef <em>Super Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Super Ref</em>'.
   * @see org.eclipse.b3.beeLang.WildcardRefParam#getSuperRef()
   * @see #getWildcardRefParam()
   * @generated
   */
  EReference getWildcardRefParam_SuperRef();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Expresion <em>Expresion</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expresion</em>'.
   * @see org.eclipse.b3.beeLang.Expresion
   * @generated
   */
  EClass getExpresion();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.TryCatchExpression <em>Try Catch Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Try Catch Expression</em>'.
   * @see org.eclipse.b3.beeLang.TryCatchExpression
   * @generated
   */
  EClass getTryCatchExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.TryCatchExpression#getTryBlock <em>Try Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Try Block</em>'.
   * @see org.eclipse.b3.beeLang.TryCatchExpression#getTryBlock()
   * @see #getTryCatchExpression()
   * @generated
   */
  EReference getTryCatchExpression_TryBlock();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.TryCatchExpression#getCatch <em>Catch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Catch</em>'.
   * @see org.eclipse.b3.beeLang.TryCatchExpression#getCatch()
   * @see #getTryCatchExpression()
   * @generated
   */
  EReference getTryCatchExpression_Catch();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.TryCatchExpression#getFinally <em>Finally</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Finally</em>'.
   * @see org.eclipse.b3.beeLang.TryCatchExpression#getFinally()
   * @see #getTryCatchExpression()
   * @generated
   */
  EReference getTryCatchExpression_Finally();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.CatchBlock#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.eclipse.b3.beeLang.CatchBlock#getType()
   * @see #getCatchBlock()
   * @generated
   */
  EReference getCatchBlock_Type();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.CatchBlock#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Var</em>'.
   * @see org.eclipse.b3.beeLang.CatchBlock#getVar()
   * @see #getCatchBlock()
   * @generated
   */
  EAttribute getCatchBlock_Var();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.CatchBlock#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.CatchBlock#getExpr()
   * @see #getCatchBlock()
   * @generated
   */
  EReference getCatchBlock_Expr();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.FinallyBlock#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.FinallyBlock#getExpr()
   * @see #getFinallyBlock()
   * @generated
   */
  EReference getFinallyBlock_Expr();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.SwitchExpression <em>Switch Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Switch Expression</em>'.
   * @see org.eclipse.b3.beeLang.SwitchExpression
   * @generated
   */
  EClass getSwitchExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.SwitchExpression#getSwitchExpr <em>Switch Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Switch Expr</em>'.
   * @see org.eclipse.b3.beeLang.SwitchExpression#getSwitchExpr()
   * @see #getSwitchExpression()
   * @generated
   */
  EReference getSwitchExpression_SwitchExpr();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.SwitchExpression#getCase <em>Case</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Case</em>'.
   * @see org.eclipse.b3.beeLang.SwitchExpression#getCase()
   * @see #getSwitchExpression()
   * @generated
   */
  EReference getSwitchExpression_Case();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Case#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.Case#getExpr()
   * @see #getCase()
   * @generated
   */
  EReference getCase_Expr();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.IfExpressionTail <em>If Expression Tail</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>If Expression Tail</em>'.
   * @see org.eclipse.b3.beeLang.IfExpressionTail
   * @generated
   */
  EClass getIfExpressionTail();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.OperationCall#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameters</em>'.
   * @see org.eclipse.b3.beeLang.OperationCall#getParameters()
   * @see #getOperationCall()
   * @generated
   */
  EReference getOperationCall_Parameters();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Context <em>Context</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Context</em>'.
   * @see org.eclipse.b3.beeLang.Context
   * @generated
   */
  EClass getContext();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Context#getSelector <em>Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Selector</em>'.
   * @see org.eclipse.b3.beeLang.Context#getSelector()
   * @see #getContext()
   * @generated
   */
  EReference getContext_Selector();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Context#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.eclipse.b3.beeLang.Context#getBlock()
   * @see #getContext()
   * @generated
   */
  EReference getContext_Block();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ContextSelector <em>Context Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Context Selector</em>'.
   * @see org.eclipse.b3.beeLang.ContextSelector
   * @generated
   */
  EClass getContextSelector();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ContextSelector#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.eclipse.b3.beeLang.ContextSelector#getType()
   * @see #getContextSelector()
   * @generated
   */
  EReference getContextSelector_Type();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ExpressionSelector <em>Expression Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression Selector</em>'.
   * @see org.eclipse.b3.beeLang.ExpressionSelector
   * @generated
   */
  EClass getExpressionSelector();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ExpressionSelector#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.ExpressionSelector#getExpr()
   * @see #getExpressionSelector()
   * @generated
   */
  EReference getExpressionSelector_Expr();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.UnitSelector <em>Unit Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unit Selector</em>'.
   * @see org.eclipse.b3.beeLang.UnitSelector
   * @generated
   */
  EClass getUnitSelector();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.UnitSelector#getInterface <em>Interface</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Interface</em>'.
   * @see org.eclipse.b3.beeLang.UnitSelector#getInterface()
   * @see #getUnitSelector()
   * @generated
   */
  EAttribute getUnitSelector_Interface();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.UnitSelector#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.UnitSelector#getName()
   * @see #getUnitSelector()
   * @generated
   */
  EAttribute getUnitSelector_Name();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.UnitSelector#getNamePattern <em>Name Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name Pattern</em>'.
   * @see org.eclipse.b3.beeLang.UnitSelector#getNamePattern()
   * @see #getUnitSelector()
   * @generated
   */
  EAttribute getUnitSelector_NamePattern();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.UnitSelector#getVersionRange <em>Version Range</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Version Range</em>'.
   * @see org.eclipse.b3.beeLang.UnitSelector#getVersionRange()
   * @see #getUnitSelector()
   * @generated
   */
  EReference getUnitSelector_VersionRange();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ContextBlock <em>Context Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Context Block</em>'.
   * @see org.eclipse.b3.beeLang.ContextBlock
   * @generated
   */
  EClass getContextBlock();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ContextBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.eclipse.b3.beeLang.ContextBlock#getStatements()
   * @see #getContextBlock()
   * @generated
   */
  EReference getContextBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ClosureExpression <em>Closure Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Closure Expression</em>'.
   * @see org.eclipse.b3.beeLang.ClosureExpression
   * @generated
   */
  EClass getClosureExpression();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ClosureExpression#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.eclipse.b3.beeLang.ClosureExpression#getParameters()
   * @see #getClosureExpression()
   * @generated
   */
  EReference getClosureExpression_Parameters();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ClosureExpression#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.ClosureExpression#getExpr()
   * @see #getClosureExpression()
   * @generated
   */
  EReference getClosureExpression_Expr();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Version <em>Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Version</em>'.
   * @see org.eclipse.b3.beeLang.Version
   * @generated
   */
  EClass getVersion();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Version#getVersion <em>Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Version</em>'.
   * @see org.eclipse.b3.beeLang.Version#getVersion()
   * @see #getVersion()
   * @generated
   */
  EAttribute getVersion_Version();

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
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.VersionRange#getRange <em>Range</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Range</em>'.
   * @see org.eclipse.b3.beeLang.VersionRange#getRange()
   * @see #getVersionRange()
   * @generated
   */
  EAttribute getVersionRange_Range();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.VarargParameterDeclaration <em>Vararg Parameter Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Vararg Parameter Declaration</em>'.
   * @see org.eclipse.b3.beeLang.VarargParameterDeclaration
   * @generated
   */
  EClass getVarargParameterDeclaration();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Function <em>Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function</em>'.
   * @see org.eclipse.b3.beeLang.Function
   * @generated
   */
  EClass getFunction();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Function#getFunc <em>Func</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Func</em>'.
   * @see org.eclipse.b3.beeLang.Function#getFunc()
   * @see #getFunction()
   * @generated
   */
  EReference getFunction_Func();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Method <em>Method</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Method</em>'.
   * @see org.eclipse.b3.beeLang.Method
   * @generated
   */
  EClass getMethod();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Method#getMethod <em>Method</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Method</em>'.
   * @see org.eclipse.b3.beeLang.Method#getMethod()
   * @see #getMethod()
   * @generated
   */
  EReference getMethod_Method();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ReturnExpression <em>Return Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Return Expression</em>'.
   * @see org.eclipse.b3.beeLang.ReturnExpression
   * @generated
   */
  EClass getReturnExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ReturnExpression#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.ReturnExpression#getExpr()
   * @see #getReturnExpression()
   * @generated
   */
  EReference getReturnExpression_Expr();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.VarDeclaration <em>Var Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Var Declaration</em>'.
   * @see org.eclipse.b3.beeLang.VarDeclaration
   * @generated
   */
  EClass getVarDeclaration();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.VarDeclaration#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.eclipse.b3.beeLang.VarDeclaration#getType()
   * @see #getVarDeclaration()
   * @generated
   */
  EReference getVarDeclaration_Type();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.VarDeclaration#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.VarDeclaration#getName()
   * @see #getVarDeclaration()
   * @generated
   */
  EAttribute getVarDeclaration_Name();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.SetOperationCall <em>Set Operation Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Operation Call</em>'.
   * @see org.eclipse.b3.beeLang.SetOperationCall
   * @generated
   */
  EClass getSetOperationCall();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.SetOperationCall#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.eclipse.b3.beeLang.SetOperationCall#getParams()
   * @see #getSetOperationCall()
   * @generated
   */
  EReference getSetOperationCall_Params();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.SetOperationCall#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see org.eclipse.b3.beeLang.SetOperationCall#getOp()
   * @see #getSetOperationCall()
   * @generated
   */
  EAttribute getSetOperationCall_Op();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.AtCall#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameters</em>'.
   * @see org.eclipse.b3.beeLang.AtCall#getParameters()
   * @see #getAtCall()
   * @generated
   */
  EReference getAtCall_Parameters();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.WithClauseExpression <em>With Clause Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>With Clause Expression</em>'.
   * @see org.eclipse.b3.beeLang.WithClauseExpression
   * @generated
   */
  EClass getWithClauseExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.WithClauseExpression#getWithclause <em>Withclause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Withclause</em>'.
   * @see org.eclipse.b3.beeLang.WithClauseExpression#getWithclause()
   * @see #getWithClauseExpression()
   * @generated
   */
  EReference getWithClauseExpression_Withclause();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.WithClauseExpression#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.WithClauseExpression#getExpr()
   * @see #getWithClauseExpression()
   * @generated
   */
  EReference getWithClauseExpression_Expr();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.WithContextExpression <em>With Context Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>With Context Expression</em>'.
   * @see org.eclipse.b3.beeLang.WithContextExpression
   * @generated
   */
  EClass getWithContextExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.WithContextExpression#getContext <em>Context</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Context</em>'.
   * @see org.eclipse.b3.beeLang.WithContextExpression#getContext()
   * @see #getWithContextExpression()
   * @generated
   */
  EReference getWithContextExpression_Context();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.WithContextExpression#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.WithContextExpression#getExpr()
   * @see #getWithContextExpression()
   * @generated
   */
  EReference getWithContextExpression_Expr();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.WildcardExpression <em>Wildcard Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Wildcard Expression</em>'.
   * @see org.eclipse.b3.beeLang.WildcardExpression
   * @generated
   */
  EClass getWildcardExpression();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ThrowExpression <em>Throw Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Throw Expression</em>'.
   * @see org.eclipse.b3.beeLang.ThrowExpression
   * @generated
   */
  EClass getThrowExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ThrowExpression#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.ThrowExpression#getExpr()
   * @see #getThrowExpression()
   * @generated
   */
  EReference getThrowExpression_Expr();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.BlockExpression <em>Block Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Block Expression</em>'.
   * @see org.eclipse.b3.beeLang.BlockExpression
   * @generated
   */
  EClass getBlockExpression();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BlockExpression#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr</em>'.
   * @see org.eclipse.b3.beeLang.BlockExpression#getExpr()
   * @see #getBlockExpression()
   * @generated
   */
  EReference getBlockExpression_Expr();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.IfExpression#getCond <em>Cond</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cond</em>'.
   * @see org.eclipse.b3.beeLang.IfExpression#getCond()
   * @see #getIfExpression()
   * @generated
   */
  EReference getIfExpression_Cond();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.IfExpression#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.eclipse.b3.beeLang.IfExpression#getValue()
   * @see #getIfExpression()
   * @generated
   */
  EReference getIfExpression_Value();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.IfExpression#getTail <em>Tail</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Tail</em>'.
   * @see org.eclipse.b3.beeLang.IfExpression#getTail()
   * @see #getIfExpression()
   * @generated
   */
  EReference getIfExpression_Tail();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ElseIfExpression <em>Else If Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Else If Expression</em>'.
   * @see org.eclipse.b3.beeLang.ElseIfExpression
   * @generated
   */
  EClass getElseIfExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ElseIfExpression#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see org.eclipse.b3.beeLang.ElseIfExpression#getCondition()
   * @see #getElseIfExpression()
   * @generated
   */
  EReference getElseIfExpression_Condition();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ElseIfExpression#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.eclipse.b3.beeLang.ElseIfExpression#getValue()
   * @see #getElseIfExpression()
   * @generated
   */
  EReference getElseIfExpression_Value();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ElseExpression <em>Else Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Else Expression</em>'.
   * @see org.eclipse.b3.beeLang.ElseExpression
   * @generated
   */
  EClass getElseExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ElseExpression#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.eclipse.b3.beeLang.ElseExpression#getValue()
   * @see #getElseExpression()
   * @generated
   */
  EReference getElseExpression_Value();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.CreatorCall#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target</em>'.
   * @see org.eclipse.b3.beeLang.CreatorCall#getTarget()
   * @see #getCreatorCall()
   * @generated
   */
  EReference getCreatorCall_Target();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.CreatorCall#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameters</em>'.
   * @see org.eclipse.b3.beeLang.CreatorCall#getParameters()
   * @see #getCreatorCall()
   * @generated
   */
  EReference getCreatorCall_Parameters();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.CreatorCall#getAlias <em>Alias</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Alias</em>'.
   * @see org.eclipse.b3.beeLang.CreatorCall#getAlias()
   * @see #getCreatorCall()
   * @generated
   */
  EAttribute getCreatorCall_Alias();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.CreatorCall#getContextBlock <em>Context Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Context Block</em>'.
   * @see org.eclipse.b3.beeLang.CreatorCall#getContextBlock()
   * @see #getCreatorCall()
   * @generated
   */
  EReference getCreatorCall_ContextBlock();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ExpressionStatement <em>Expression Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression Statement</em>'.
   * @see org.eclipse.b3.beeLang.ExpressionStatement
   * @generated
   */
  EClass getExpressionStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ExpressionStatement#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Val</em>'.
   * @see org.eclipse.b3.beeLang.ExpressionStatement#getVal()
   * @see #getExpressionStatement()
   * @generated
   */
  EReference getExpressionStatement_Val();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PropertiesStatement <em>Properties Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Properties Statement</em>'.
   * @see org.eclipse.b3.beeLang.PropertiesStatement
   * @generated
   */
  EClass getPropertiesStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.PropertiesStatement#getProperties <em>Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Properties</em>'.
   * @see org.eclipse.b3.beeLang.PropertiesStatement#getProperties()
   * @see #getPropertiesStatement()
   * @generated
   */
  EReference getPropertiesStatement_Properties();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.LiteralList <em>Literal List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Literal List</em>'.
   * @see org.eclipse.b3.beeLang.LiteralList
   * @generated
   */
  EClass getLiteralList();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.LiteralList#getElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Element</em>'.
   * @see org.eclipse.b3.beeLang.LiteralList#getElement()
   * @see #getLiteralList()
   * @generated
   */
  EReference getLiteralList_Element();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.LiteralList#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.eclipse.b3.beeLang.LiteralList#getType()
   * @see #getLiteralList()
   * @generated
   */
  EReference getLiteralList_Type();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.LiteralMap <em>Literal Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Literal Map</em>'.
   * @see org.eclipse.b3.beeLang.LiteralMap
   * @generated
   */
  EClass getLiteralMap();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.LiteralMap#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see org.eclipse.b3.beeLang.LiteralMap#getFeatures()
   * @see #getLiteralMap()
   * @generated
   */
  EReference getLiteralMap_Features();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.LiteralMap#getKeyType <em>Key Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Key Type</em>'.
   * @see org.eclipse.b3.beeLang.LiteralMap#getKeyType()
   * @see #getLiteralMap()
   * @generated
   */
  EReference getLiteralMap_KeyType();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.LiteralMap#getValType <em>Val Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Val Type</em>'.
   * @see org.eclipse.b3.beeLang.LiteralMap#getValType()
   * @see #getLiteralMap()
   * @generated
   */
  EReference getLiteralMap_ValType();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.LiteralFunction#getClosure <em>Closure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Closure</em>'.
   * @see org.eclipse.b3.beeLang.LiteralFunction#getClosure()
   * @see #getLiteralFunction()
   * @generated
   */
  EReference getLiteralFunction_Closure();

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
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.IntegerLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.eclipse.b3.beeLang.IntegerLiteral#getVal()
   * @see #getIntegerLiteral()
   * @generated
   */
  EAttribute getIntegerLiteral_Val();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ThisLiteral <em>This Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>This Literal</em>'.
   * @see org.eclipse.b3.beeLang.ThisLiteral
   * @generated
   */
  EClass getThisLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.ThisLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.eclipse.b3.beeLang.ThisLiteral#getVal()
   * @see #getThisLiteral()
   * @generated
   */
  EAttribute getThisLiteral_Val();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.SuperLiteral <em>Super Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Super Literal</em>'.
   * @see org.eclipse.b3.beeLang.SuperLiteral
   * @generated
   */
  EClass getSuperLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.SuperLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.eclipse.b3.beeLang.SuperLiteral#getVal()
   * @see #getSuperLiteral()
   * @generated
   */
  EAttribute getSuperLiteral_Val();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.UnitLiteral <em>Unit Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unit Literal</em>'.
   * @see org.eclipse.b3.beeLang.UnitLiteral
   * @generated
   */
  EClass getUnitLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.UnitLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see org.eclipse.b3.beeLang.UnitLiteral#getVal()
   * @see #getUnitLiteral()
   * @generated
   */
  EAttribute getUnitLiteral_Val();

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
   * Returns the meta object for enum '{@link org.eclipse.b3.beeLang.SelectorOperator <em>Selector Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Selector Operator</em>'.
   * @see org.eclipse.b3.beeLang.SelectorOperator
   * @generated
   */
  EEnum getSelectorOperator();

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
   * Returns the meta object for enum '{@link org.eclipse.b3.beeLang.SetOperator <em>Set Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Set Operator</em>'.
   * @see org.eclipse.b3.beeLang.SetOperator
   * @generated
   */
  EEnum getSetOperator();

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
     * The meta object literal for the '<em><b>Functions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BEE_MODEL__FUNCTIONS = eINSTANCE.getBeeModel_Functions();

    /**
     * The meta object literal for the '<em><b>Concern</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BEE_MODEL__CONCERN = eINSTANCE.getBeeModel_Concern();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BEE_MODEL__BODY = eINSTANCE.getBeeModel_Body();

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
     * The meta object literal for the '<em><b>Reexport</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPORT__REEXPORT = eINSTANCE.getImport_Reexport();

    /**
     * The meta object literal for the '<em><b>Importer</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IMPORT__IMPORTER = eINSTANCE.getImport_Importer();

    /**
     * The meta object literal for the '<em><b>Name Space</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPORT__NAME_SPACE = eINSTANCE.getImport_NameSpace();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.JavaImporterImpl <em>Java Importer</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.JavaImporterImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getJavaImporter()
     * @generated
     */
    EClass JAVA_IMPORTER = eINSTANCE.getJavaImporter();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute JAVA_IMPORTER__NAME = eINSTANCE.getJavaImporter_Name();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.NativeImporterImpl <em>Native Importer</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.NativeImporterImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getNativeImporter()
     * @generated
     */
    EClass NATIVE_IMPORTER = eINSTANCE.getNativeImporter();

    /**
     * The meta object literal for the '<em><b>Uri String</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NATIVE_IMPORTER__URI_STRING = eINSTANCE.getNativeImporter_UriString();

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
     * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILD_UNIT__DOCUMENTATION = eINSTANCE.getBuildUnit_Documentation();

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
     * The meta object literal for the '<em><b>Default Properties</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__DEFAULT_PROPERTIES = eINSTANCE.getBuildUnit_DefaultProperties();

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
     * The meta object literal for the '<em><b>Concerns</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__CONCERNS = eINSTANCE.getBuildUnit_Concerns();

    /**
     * The meta object literal for the '<em><b>Synchronizations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__SYNCHRONIZATIONS = eINSTANCE.getBuildUnit_Synchronizations();

    /**
     * The meta object literal for the '<em><b>Builders</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__BUILDERS = eINSTANCE.getBuildUnit_Builders();

    /**
     * The meta object literal for the '<em><b>Methods</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__METHODS = eINSTANCE.getBuildUnit_Methods();

    /**
     * The meta object literal for the '<em><b>Repository Configurations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__REPOSITORY_CONFIGURATIONS = eINSTANCE.getBuildUnit_RepositoryConfigurations();

    /**
     * The meta object literal for the '<em><b>Property Sets</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__PROPERTY_SETS = eINSTANCE.getBuildUnit_PropertySets();

    /**
     * The meta object literal for the '<em><b>Containers</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__CONTAINERS = eINSTANCE.getBuildUnit_Containers();

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
     * The meta object literal for the '<em><b>Capability</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROVIDED_CAPABILITY__CAPABILITY = eINSTANCE.getProvidedCapability_Capability();

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
     * The meta object literal for the '<em><b>Capability</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REQUIRED_CAPABILITY__CAPABILITY = eINSTANCE.getRequiredCapability_Capability();

    /**
     * The meta object literal for the '<em><b>Range</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REQUIRED_CAPABILITY__RANGE = eINSTANCE.getRequiredCapability_Range();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.FilteredCapabilityImpl <em>Filtered Capability</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.FilteredCapabilityImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFilteredCapability()
     * @generated
     */
    EClass FILTERED_CAPABILITY = eINSTANCE.getFilteredCapability();

    /**
     * The meta object literal for the '<em><b>Filter</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILTERED_CAPABILITY__FILTER = eINSTANCE.getFilteredCapability_Filter();

    /**
     * The meta object literal for the '<em><b>Capability</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILTERED_CAPABILITY__CAPABILITY = eINSTANCE.getFilteredCapability_Capability();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.CapabilityImpl <em>Capability</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.CapabilityImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCapability()
     * @generated
     */
    EClass CAPABILITY = eINSTANCE.getCapability();

    /**
     * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CAPABILITY__INTERFACE = eINSTANCE.getCapability_Interface();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CAPABILITY__NAME = eINSTANCE.getCapability_Name();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.NamedPropertySetImpl <em>Named Property Set</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.NamedPropertySetImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getNamedPropertySet()
     * @generated
     */
    EClass NAMED_PROPERTY_SET = eINSTANCE.getNamedPropertySet();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMED_PROPERTY_SET__NAME = eINSTANCE.getNamedPropertySet_Name();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_PROPERTY_SET__BODY = eINSTANCE.getNamedPropertySet_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PropertySetImpl <em>Property Set</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PropertySetImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPropertySet()
     * @generated
     */
    EClass PROPERTY_SET = eINSTANCE.getPropertySet();

    /**
     * The meta object literal for the '<em><b>Extends</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY_SET__EXTENDS = eINSTANCE.getPropertySet_Extends();

    /**
     * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY_SET__OPERATIONS = eINSTANCE.getPropertySet_Operations();

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
     * The meta object literal for the '<em><b>Final</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SET_PROPERTY_OPERATION__FINAL = eINSTANCE.getSetPropertyOperation_Final();

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
     * The meta object literal for the '<em><b>Filter</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PREREQUISITE__FILTER = eINSTANCE.getPrerequisite_Filter();

    /**
     * The meta object literal for the '<em><b>With Clause</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PREREQUISITE__WITH_CLAUSE = eINSTANCE.getPrerequisite_WithClause();

    /**
     * The meta object literal for the '<em><b>Part Reference</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PREREQUISITE__PART_REFERENCE = eINSTANCE.getPrerequisite_PartReference();

    /**
     * The meta object literal for the '<em><b>Alias</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PREREQUISITE__ALIAS = eINSTANCE.getPrerequisite_Alias();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.WithClauseImpl <em>With Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.WithClauseImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWithClause()
     * @generated
     */
    EClass WITH_CLAUSE = eINSTANCE.getWithClause();

    /**
     * The meta object literal for the '<em><b>References</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WITH_CLAUSE__REFERENCES = eINSTANCE.getWithClause_References();

    /**
     * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WITH_CLAUSE__PROPERTIES = eINSTANCE.getWithClause_Properties();

    /**
     * The meta object literal for the '<em><b>Concern</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WITH_CLAUSE__CONCERN = eINSTANCE.getWithClause_Concern();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.DirectPartReferemceImpl <em>Direct Part Referemce</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.DirectPartReferemceImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getDirectPartReferemce()
     * @generated
     */
    EClass DIRECT_PART_REFEREMCE = eINSTANCE.getDirectPartReferemce();

    /**
     * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DIRECT_PART_REFEREMCE__UNIT = eINSTANCE.getDirectPartReferemce_Unit();

    /**
     * The meta object literal for the '<em><b>Builder</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DIRECT_PART_REFEREMCE__BUILDER = eINSTANCE.getDirectPartReferemce_Builder();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DIRECT_PART_REFEREMCE__PARAMETERS = eINSTANCE.getDirectPartReferemce_Parameters();

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
     * The meta object literal for the '<em><b>Capability</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CAPABILITY_REFERENCED_PART__CAPABILITY = eINSTANCE.getCapabilityReferencedPart_Capability();

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
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CAPABILITY_REFERENCED_PART__PARAMETERS = eINSTANCE.getCapabilityReferencedPart_Parameters();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.BuilderImpl <em>Builder</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.BuilderImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBuilder()
     * @generated
     */
    EClass BUILDER = eINSTANCE.getBuilder();

    /**
     * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILDER__DOCUMENTATION = eINSTANCE.getBuilder_Documentation();

    /**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILDER__VISIBILITY = eINSTANCE.getBuilder_Visibility();

    /**
     * The meta object literal for the '<em><b>Execution Mode</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILDER__EXECUTION_MODE = eINSTANCE.getBuilder_ExecutionMode();

    /**
     * The meta object literal for the '<em><b>Final</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILDER__FINAL = eINSTANCE.getBuilder_Final();

    /**
     * The meta object literal for the '<em><b>Cached</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILDER__CACHED = eINSTANCE.getBuilder_Cached();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILDER__NAME = eINSTANCE.getBuilder_Name();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILDER__PARAMS = eINSTANCE.getBuilder_Params();

    /**
     * The meta object literal for the '<em><b>Provided Capabilities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILDER__PROVIDED_CAPABILITIES = eINSTANCE.getBuilder_ProvidedCapabilities();

    /**
     * The meta object literal for the '<em><b>Pre Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILDER__PRE_CONDITION = eINSTANCE.getBuilder_PreCondition();

    /**
     * The meta object literal for the '<em><b>Post Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILDER__POST_CONDITION = eINSTANCE.getBuilder_PostCondition();

    /**
     * The meta object literal for the '<em><b>Properties</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILDER__PROPERTIES = eINSTANCE.getBuilder_Properties();

    /**
     * The meta object literal for the '<em><b>Input</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILDER__INPUT = eINSTANCE.getBuilder_Input();

    /**
     * The meta object literal for the '<em><b>Output</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILDER__OUTPUT = eINSTANCE.getBuilder_Output();

    /**
     * The meta object literal for the '<em><b>Expression List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILDER__EXPRESSION_LIST = eINSTANCE.getBuilder_ExpressionList();

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
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETER__NAME = eINSTANCE.getParameter_Name();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER__VAL = eINSTANCE.getParameter_Val();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ParameterListImpl <em>Parameter List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ParameterListImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getParameterList()
     * @generated
     */
    EClass PARAMETER_LIST = eINSTANCE.getParameterList();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER_LIST__PARAMS = eINSTANCE.getParameterList_Params();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ParameterDeclarationListImpl <em>Parameter Declaration List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ParameterDeclarationListImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getParameterDeclarationList()
     * @generated
     */
    EClass PARAMETER_DECLARATION_LIST = eINSTANCE.getParameterDeclarationList();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER_DECLARATION_LIST__PARAMS = eINSTANCE.getParameterDeclarationList_Params();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ParameterDeclarationImpl <em>Parameter Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ParameterDeclarationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getParameterDeclaration()
     * @generated
     */
    EClass PARAMETER_DECLARATION = eINSTANCE.getParameterDeclaration();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER_DECLARATION__TYPE = eINSTANCE.getParameterDeclaration_Type();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETER_DECLARATION__NAME = eINSTANCE.getParameterDeclaration_Name();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.BuilderOutputImpl <em>Builder Output</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.BuilderOutputImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBuilderOutput()
     * @generated
     */
    EClass BUILDER_OUTPUT = eINSTANCE.getBuilderOutput();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILDER_OUTPUT__BODY = eINSTANCE.getBuilderOutput_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.BuilderInputImpl <em>Builder Input</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.BuilderInputImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBuilderInput()
     * @generated
     */
    EClass BUILDER_INPUT = eINSTANCE.getBuilderInput();

    /**
     * The meta object literal for the '<em><b>Pre Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILDER_INPUT__PRE_CONDITION = eINSTANCE.getBuilderInput_PreCondition();

    /**
     * The meta object literal for the '<em><b>Post Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILDER_INPUT__POST_CONDITION = eINSTANCE.getBuilderInput_PostCondition();

    /**
     * The meta object literal for the '<em><b>Prerequisites</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILDER_INPUT__PREREQUISITES = eINSTANCE.getBuilderInput_Prerequisites();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.RepositoryDeclarationImpl <em>Repository Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.RepositoryDeclarationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRepositoryDeclaration()
     * @generated
     */
    EClass REPOSITORY_DECLARATION = eINSTANCE.getRepositoryDeclaration();

    /**
     * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REPOSITORY_DECLARATION__DOCUMENTATION = eINSTANCE.getRepositoryDeclaration_Documentation();

    /**
     * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REPOSITORY_DECLARATION__LOCATION = eINSTANCE.getRepositoryDeclaration_Location();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REPOSITORY_DECLARATION__TYPE = eINSTANCE.getRepositoryDeclaration_Type();

    /**
     * The meta object literal for the '<em><b>Context</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REPOSITORY_DECLARATION__CONTEXT = eINSTANCE.getRepositoryDeclaration_Context();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ResolutionStrategyImpl <em>Resolution Strategy</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ResolutionStrategyImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getResolutionStrategy()
     * @generated
     */
    EClass RESOLUTION_STRATEGY = eINSTANCE.getResolutionStrategy();

    /**
     * The meta object literal for the '<em><b>Strategy</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RESOLUTION_STRATEGY__STRATEGY = eINSTANCE.getResolutionStrategy_Strategy();

    /**
     * The meta object literal for the '<em><b>Repository Config</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RESOLUTION_STRATEGY__REPOSITORY_CONFIG = eINSTANCE.getResolutionStrategy_RepositoryConfig();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ContainerConfigurationImpl <em>Container Configuration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ContainerConfigurationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getContainerConfiguration()
     * @generated
     */
    EClass CONTAINER_CONFIGURATION = eINSTANCE.getContainerConfiguration();

    /**
     * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONTAINER_CONFIGURATION__DOCUMENTATION = eINSTANCE.getContainerConfiguration_Documentation();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONTAINER_CONFIGURATION__NAME = eINSTANCE.getContainerConfiguration_Name();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTAINER_CONFIGURATION__TYPE = eINSTANCE.getContainerConfiguration_Type();

    /**
     * The meta object literal for the '<em><b>Context Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTAINER_CONFIGURATION__CONTEXT_BLOCK = eINSTANCE.getContainerConfiguration_ContextBlock();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ConcernImpl <em>Concern</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ConcernImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getConcern()
     * @generated
     */
    EClass CONCERN = eINSTANCE.getConcern();

    /**
     * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONCERN__DOCUMENTATION = eINSTANCE.getConcern_Documentation();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONCERN__NAME = eINSTANCE.getConcern_Name();

    /**
     * The meta object literal for the '<em><b>Concern</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONCERN__CONCERN = eINSTANCE.getConcern_Concern();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ConcernBlockImpl <em>Concern Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ConcernBlockImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getConcernBlock()
     * @generated
     */
    EClass CONCERN_BLOCK = eINSTANCE.getConcernBlock();

    /**
     * The meta object literal for the '<em><b>Super Concerns</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONCERN_BLOCK__SUPER_CONCERNS = eINSTANCE.getConcernBlock_SuperConcerns();

    /**
     * The meta object literal for the '<em><b>Contexts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONCERN_BLOCK__CONTEXTS = eINSTANCE.getConcernBlock_Contexts();

    /**
     * The meta object literal for the '<em><b>Functions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONCERN_BLOCK__FUNCTIONS = eINSTANCE.getConcernBlock_Functions();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.QueryImpl <em>Query</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.QueryImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getQuery()
     * @generated
     */
    EClass QUERY = eINSTANCE.getQuery();

    /**
     * The meta object literal for the '<em><b>Selector</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference QUERY__SELECTOR = eINSTANCE.getQuery_Selector();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.QueryPathImpl <em>Query Path</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.QueryPathImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getQueryPath()
     * @generated
     */
    EClass QUERY_PATH = eINSTANCE.getQueryPath();

    /**
     * The meta object literal for the '<em><b>Absolute</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute QUERY_PATH__ABSOLUTE = eINSTANCE.getQueryPath_Absolute();

    /**
     * The meta object literal for the '<em><b>Selectors</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference QUERY_PATH__SELECTORS = eINSTANCE.getQueryPath_Selectors();

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
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SELECTOR__NAME = eINSTANCE.getSelector_Name();

    /**
     * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SELECTOR__OP = eINSTANCE.getSelector_Op();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SELECTOR__PATTERN = eINSTANCE.getSelector_Pattern();

    /**
     * The meta object literal for the '<em><b>Predicate</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SELECTOR__PREDICATE = eINSTANCE.getSelector_Predicate();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ExpressionListImpl <em>Expression List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ExpressionListImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpressionList()
     * @generated
     */
    EClass EXPRESSION_LIST = eINSTANCE.getExpressionList();

    /**
     * The meta object literal for the '<em><b>Expressions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION_LIST__EXPRESSIONS = eINSTANCE.getExpressionList_Expressions();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.StatmentImpl <em>Statment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.StatmentImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getStatment()
     * @generated
     */
    EClass STATMENT = eINSTANCE.getStatment();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl <em>Function Or Method</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFunctionOrMethod()
     * @generated
     */
    EClass FUNCTION_OR_METHOD = eINSTANCE.getFunctionOrMethod();

    /**
     * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_OR_METHOD__DOCUMENTATION = eINSTANCE.getFunctionOrMethod_Documentation();

    /**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_OR_METHOD__VISIBILITY = eINSTANCE.getFunctionOrMethod_Visibility();

    /**
     * The meta object literal for the '<em><b>Execution Mode</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_OR_METHOD__EXECUTION_MODE = eINSTANCE.getFunctionOrMethod_ExecutionMode();

    /**
     * The meta object literal for the '<em><b>Final</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_OR_METHOD__FINAL = eINSTANCE.getFunctionOrMethod_Final();

    /**
     * The meta object literal for the '<em><b>Cached</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_OR_METHOD__CACHED = eINSTANCE.getFunctionOrMethod_Cached();

    /**
     * The meta object literal for the '<em><b>Form</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_OR_METHOD__FORM = eINSTANCE.getFunctionOrMethod_Form();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_OR_METHOD__TYPE = eINSTANCE.getFunctionOrMethod_Type();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_OR_METHOD__NAME = eINSTANCE.getFunctionOrMethod_Name();

    /**
     * The meta object literal for the '<em><b>Type Params</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_OR_METHOD__TYPE_PARAMS = eINSTANCE.getFunctionOrMethod_TypeParams();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_OR_METHOD__PARAMS = eINSTANCE.getFunctionOrMethod_Params();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_OR_METHOD__BODY = eINSTANCE.getFunctionOrMethod_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.TypeParamDeclarationImpl <em>Type Param Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.TypeParamDeclarationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getTypeParamDeclaration()
     * @generated
     */
    EClass TYPE_PARAM_DECLARATION = eINSTANCE.getTypeParamDeclaration();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TYPE_PARAM_DECLARATION__TYPE = eINSTANCE.getTypeParamDeclaration_Type();

    /**
     * The meta object literal for the '<em><b>Super Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TYPE_PARAM_DECLARATION__SUPER_TYPE = eINSTANCE.getTypeParamDeclaration_SuperType();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.TypeRefImpl <em>Type Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.TypeRefImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getTypeRef()
     * @generated
     */
    EClass TYPE_REF = eINSTANCE.getTypeRef();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.SimpleTypeRefImpl <em>Simple Type Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.SimpleTypeRefImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSimpleTypeRef()
     * @generated
     */
    EClass SIMPLE_TYPE_REF = eINSTANCE.getSimpleTypeRef();

    /**
     * The meta object literal for the '<em><b>Type Name</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIMPLE_TYPE_REF__TYPE_NAME = eINSTANCE.getSimpleTypeRef_TypeName();

    /**
     * The meta object literal for the '<em><b>Rule Type Parameter</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SIMPLE_TYPE_REF__RULE_TYPE_PARAMETER = eINSTANCE.getSimpleTypeRef_RuleTypeParameter();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ClosureTypeRefImpl <em>Closure Type Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ClosureTypeRefImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getClosureTypeRef()
     * @generated
     */
    EClass CLOSURE_TYPE_REF = eINSTANCE.getClosureTypeRef();

    /**
     * The meta object literal for the '<em><b>Parameter Types</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLOSURE_TYPE_REF__PARAMETER_TYPES = eINSTANCE.getClosureTypeRef_ParameterTypes();

    /**
     * The meta object literal for the '<em><b>Return Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLOSURE_TYPE_REF__RETURN_TYPE = eINSTANCE.getClosureTypeRef_ReturnType();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.RuleTypeParamImpl <em>Rule Type Param</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.RuleTypeParamImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRuleTypeParam()
     * @generated
     */
    EClass RULE_TYPE_PARAM = eINSTANCE.getRuleTypeParam();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.RuleTypeRefParamImpl <em>Rule Type Ref Param</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.RuleTypeRefParamImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRuleTypeRefParam()
     * @generated
     */
    EClass RULE_TYPE_REF_PARAM = eINSTANCE.getRuleTypeRefParam();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.WildcardRefParamImpl <em>Wildcard Ref Param</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.WildcardRefParamImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWildcardRefParam()
     * @generated
     */
    EClass WILDCARD_REF_PARAM = eINSTANCE.getWildcardRefParam();

    /**
     * The meta object literal for the '<em><b>Extends</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WILDCARD_REF_PARAM__EXTENDS = eINSTANCE.getWildcardRefParam_Extends();

    /**
     * The meta object literal for the '<em><b>Super Ref</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WILDCARD_REF_PARAM__SUPER_REF = eINSTANCE.getWildcardRefParam_SuperRef();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ExpresionImpl <em>Expresion</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ExpresionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpresion()
     * @generated
     */
    EClass EXPRESION = eINSTANCE.getExpresion();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.TryCatchExpressionImpl <em>Try Catch Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.TryCatchExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getTryCatchExpression()
     * @generated
     */
    EClass TRY_CATCH_EXPRESSION = eINSTANCE.getTryCatchExpression();

    /**
     * The meta object literal for the '<em><b>Try Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TRY_CATCH_EXPRESSION__TRY_BLOCK = eINSTANCE.getTryCatchExpression_TryBlock();

    /**
     * The meta object literal for the '<em><b>Catch</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TRY_CATCH_EXPRESSION__CATCH = eINSTANCE.getTryCatchExpression_Catch();

    /**
     * The meta object literal for the '<em><b>Finally</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TRY_CATCH_EXPRESSION__FINALLY = eINSTANCE.getTryCatchExpression_Finally();

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
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CATCH_BLOCK__TYPE = eINSTANCE.getCatchBlock_Type();

    /**
     * The meta object literal for the '<em><b>Var</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATCH_BLOCK__VAR = eINSTANCE.getCatchBlock_Var();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CATCH_BLOCK__EXPR = eINSTANCE.getCatchBlock_Expr();

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
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FINALLY_BLOCK__EXPR = eINSTANCE.getFinallyBlock_Expr();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.SwitchExpressionImpl <em>Switch Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.SwitchExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSwitchExpression()
     * @generated
     */
    EClass SWITCH_EXPRESSION = eINSTANCE.getSwitchExpression();

    /**
     * The meta object literal for the '<em><b>Switch Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SWITCH_EXPRESSION__SWITCH_EXPR = eINSTANCE.getSwitchExpression_SwitchExpr();

    /**
     * The meta object literal for the '<em><b>Case</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SWITCH_EXPRESSION__CASE = eINSTANCE.getSwitchExpression_Case();

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
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CASE__EXPR = eINSTANCE.getCase_Expr();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.IfExpressionTailImpl <em>If Expression Tail</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.IfExpressionTailImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getIfExpressionTail()
     * @generated
     */
    EClass IF_EXPRESSION_TAIL = eINSTANCE.getIfExpressionTail();

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
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERATION_CALL__PARAMETERS = eINSTANCE.getOperationCall_Parameters();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ContextImpl <em>Context</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ContextImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getContext()
     * @generated
     */
    EClass CONTEXT = eINSTANCE.getContext();

    /**
     * The meta object literal for the '<em><b>Selector</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTEXT__SELECTOR = eINSTANCE.getContext_Selector();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTEXT__BLOCK = eINSTANCE.getContext_Block();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ContextSelectorImpl <em>Context Selector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ContextSelectorImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getContextSelector()
     * @generated
     */
    EClass CONTEXT_SELECTOR = eINSTANCE.getContextSelector();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTEXT_SELECTOR__TYPE = eINSTANCE.getContextSelector_Type();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ExpressionSelectorImpl <em>Expression Selector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ExpressionSelectorImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpressionSelector()
     * @generated
     */
    EClass EXPRESSION_SELECTOR = eINSTANCE.getExpressionSelector();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION_SELECTOR__EXPR = eINSTANCE.getExpressionSelector_Expr();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.UnitSelectorImpl <em>Unit Selector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.UnitSelectorImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUnitSelector()
     * @generated
     */
    EClass UNIT_SELECTOR = eINSTANCE.getUnitSelector();

    /**
     * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNIT_SELECTOR__INTERFACE = eINSTANCE.getUnitSelector_Interface();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNIT_SELECTOR__NAME = eINSTANCE.getUnitSelector_Name();

    /**
     * The meta object literal for the '<em><b>Name Pattern</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNIT_SELECTOR__NAME_PATTERN = eINSTANCE.getUnitSelector_NamePattern();

    /**
     * The meta object literal for the '<em><b>Version Range</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference UNIT_SELECTOR__VERSION_RANGE = eINSTANCE.getUnitSelector_VersionRange();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ContextBlockImpl <em>Context Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ContextBlockImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getContextBlock()
     * @generated
     */
    EClass CONTEXT_BLOCK = eINSTANCE.getContextBlock();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTEXT_BLOCK__STATEMENTS = eINSTANCE.getContextBlock_Statements();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ClosureExpressionImpl <em>Closure Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ClosureExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getClosureExpression()
     * @generated
     */
    EClass CLOSURE_EXPRESSION = eINSTANCE.getClosureExpression();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLOSURE_EXPRESSION__PARAMETERS = eINSTANCE.getClosureExpression_Parameters();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLOSURE_EXPRESSION__EXPR = eINSTANCE.getClosureExpression_Expr();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.VersionImpl <em>Version</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.VersionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVersion()
     * @generated
     */
    EClass VERSION = eINSTANCE.getVersion();

    /**
     * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VERSION__VERSION = eINSTANCE.getVersion_Version();

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
     * The meta object literal for the '<em><b>Range</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VERSION_RANGE__RANGE = eINSTANCE.getVersionRange_Range();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.VarargParameterDeclarationImpl <em>Vararg Parameter Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.VarargParameterDeclarationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVarargParameterDeclaration()
     * @generated
     */
    EClass VARARG_PARAMETER_DECLARATION = eINSTANCE.getVarargParameterDeclaration();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.FunctionImpl <em>Function</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.FunctionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFunction()
     * @generated
     */
    EClass FUNCTION = eINSTANCE.getFunction();

    /**
     * The meta object literal for the '<em><b>Func</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION__FUNC = eINSTANCE.getFunction_Func();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.MethodImpl <em>Method</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.MethodImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getMethod()
     * @generated
     */
    EClass METHOD = eINSTANCE.getMethod();

    /**
     * The meta object literal for the '<em><b>Method</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference METHOD__METHOD = eINSTANCE.getMethod_Method();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ReturnExpressionImpl <em>Return Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ReturnExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getReturnExpression()
     * @generated
     */
    EClass RETURN_EXPRESSION = eINSTANCE.getReturnExpression();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RETURN_EXPRESSION__EXPR = eINSTANCE.getReturnExpression_Expr();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.VarDeclarationImpl <em>Var Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.VarDeclarationImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVarDeclaration()
     * @generated
     */
    EClass VAR_DECLARATION = eINSTANCE.getVarDeclaration();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VAR_DECLARATION__TYPE = eINSTANCE.getVarDeclaration_Type();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VAR_DECLARATION__NAME = eINSTANCE.getVarDeclaration_Name();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.SetOperationCallImpl <em>Set Operation Call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.SetOperationCallImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSetOperationCall()
     * @generated
     */
    EClass SET_OPERATION_CALL = eINSTANCE.getSetOperationCall();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SET_OPERATION_CALL__PARAMS = eINSTANCE.getSetOperationCall_Params();

    /**
     * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SET_OPERATION_CALL__OP = eINSTANCE.getSetOperationCall_Op();

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
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AT_CALL__PARAMETERS = eINSTANCE.getAtCall_Parameters();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.WithClauseExpressionImpl <em>With Clause Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.WithClauseExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWithClauseExpression()
     * @generated
     */
    EClass WITH_CLAUSE_EXPRESSION = eINSTANCE.getWithClauseExpression();

    /**
     * The meta object literal for the '<em><b>Withclause</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WITH_CLAUSE_EXPRESSION__WITHCLAUSE = eINSTANCE.getWithClauseExpression_Withclause();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WITH_CLAUSE_EXPRESSION__EXPR = eINSTANCE.getWithClauseExpression_Expr();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.WithContextExpressionImpl <em>With Context Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.WithContextExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWithContextExpression()
     * @generated
     */
    EClass WITH_CONTEXT_EXPRESSION = eINSTANCE.getWithContextExpression();

    /**
     * The meta object literal for the '<em><b>Context</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WITH_CONTEXT_EXPRESSION__CONTEXT = eINSTANCE.getWithContextExpression_Context();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WITH_CONTEXT_EXPRESSION__EXPR = eINSTANCE.getWithContextExpression_Expr();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.WildcardExpressionImpl <em>Wildcard Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.WildcardExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getWildcardExpression()
     * @generated
     */
    EClass WILDCARD_EXPRESSION = eINSTANCE.getWildcardExpression();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ThrowExpressionImpl <em>Throw Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ThrowExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getThrowExpression()
     * @generated
     */
    EClass THROW_EXPRESSION = eINSTANCE.getThrowExpression();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference THROW_EXPRESSION__EXPR = eINSTANCE.getThrowExpression_Expr();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.BlockExpressionImpl <em>Block Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.BlockExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBlockExpression()
     * @generated
     */
    EClass BLOCK_EXPRESSION = eINSTANCE.getBlockExpression();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK_EXPRESSION__EXPR = eINSTANCE.getBlockExpression_Expr();

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
     * The meta object literal for the '<em><b>Cond</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EXPRESSION__COND = eINSTANCE.getIfExpression_Cond();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EXPRESSION__VALUE = eINSTANCE.getIfExpression_Value();

    /**
     * The meta object literal for the '<em><b>Tail</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EXPRESSION__TAIL = eINSTANCE.getIfExpression_Tail();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ElseIfExpressionImpl <em>Else If Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ElseIfExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getElseIfExpression()
     * @generated
     */
    EClass ELSE_IF_EXPRESSION = eINSTANCE.getElseIfExpression();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELSE_IF_EXPRESSION__CONDITION = eINSTANCE.getElseIfExpression_Condition();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELSE_IF_EXPRESSION__VALUE = eINSTANCE.getElseIfExpression_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ElseExpressionImpl <em>Else Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ElseExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getElseExpression()
     * @generated
     */
    EClass ELSE_EXPRESSION = eINSTANCE.getElseExpression();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELSE_EXPRESSION__VALUE = eINSTANCE.getElseExpression_Value();

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
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CREATOR_CALL__TARGET = eINSTANCE.getCreatorCall_Target();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CREATOR_CALL__PARAMETERS = eINSTANCE.getCreatorCall_Parameters();

    /**
     * The meta object literal for the '<em><b>Alias</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CREATOR_CALL__ALIAS = eINSTANCE.getCreatorCall_Alias();

    /**
     * The meta object literal for the '<em><b>Context Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CREATOR_CALL__CONTEXT_BLOCK = eINSTANCE.getCreatorCall_ContextBlock();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ExpressionStatementImpl <em>Expression Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ExpressionStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpressionStatement()
     * @generated
     */
    EClass EXPRESSION_STATEMENT = eINSTANCE.getExpressionStatement();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION_STATEMENT__VAL = eINSTANCE.getExpressionStatement_Val();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PropertiesStatementImpl <em>Properties Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PropertiesStatementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPropertiesStatement()
     * @generated
     */
    EClass PROPERTIES_STATEMENT = eINSTANCE.getPropertiesStatement();

    /**
     * The meta object literal for the '<em><b>Properties</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTIES_STATEMENT__PROPERTIES = eINSTANCE.getPropertiesStatement_Properties();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.LiteralListImpl <em>Literal List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.LiteralListImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLiteralList()
     * @generated
     */
    EClass LITERAL_LIST = eINSTANCE.getLiteralList();

    /**
     * The meta object literal for the '<em><b>Element</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LITERAL_LIST__ELEMENT = eINSTANCE.getLiteralList_Element();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LITERAL_LIST__TYPE = eINSTANCE.getLiteralList_Type();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.LiteralMapImpl <em>Literal Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.LiteralMapImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLiteralMap()
     * @generated
     */
    EClass LITERAL_MAP = eINSTANCE.getLiteralMap();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LITERAL_MAP__FEATURES = eINSTANCE.getLiteralMap_Features();

    /**
     * The meta object literal for the '<em><b>Key Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LITERAL_MAP__KEY_TYPE = eINSTANCE.getLiteralMap_KeyType();

    /**
     * The meta object literal for the '<em><b>Val Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LITERAL_MAP__VAL_TYPE = eINSTANCE.getLiteralMap_ValType();

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
     * The meta object literal for the '<em><b>Closure</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LITERAL_FUNCTION__CLOSURE = eINSTANCE.getLiteralFunction_Closure();

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
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INTEGER_LITERAL__VAL = eINSTANCE.getIntegerLiteral_Val();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ThisLiteralImpl <em>This Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ThisLiteralImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getThisLiteral()
     * @generated
     */
    EClass THIS_LITERAL = eINSTANCE.getThisLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute THIS_LITERAL__VAL = eINSTANCE.getThisLiteral_Val();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.SuperLiteralImpl <em>Super Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.SuperLiteralImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSuperLiteral()
     * @generated
     */
    EClass SUPER_LITERAL = eINSTANCE.getSuperLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SUPER_LITERAL__VAL = eINSTANCE.getSuperLiteral_Val();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.UnitLiteralImpl <em>Unit Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.UnitLiteralImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getUnitLiteral()
     * @generated
     */
    EClass UNIT_LITERAL = eINSTANCE.getUnitLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNIT_LITERAL__VAL = eINSTANCE.getUnitLiteral_Val();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.SelectorOperator <em>Selector Operator</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.SelectorOperator
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSelectorOperator()
     * @generated
     */
    EEnum SELECTOR_OPERATOR = eINSTANCE.getSelectorOperator();

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

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.SetOperator <em>Set Operator</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.SetOperator
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSetOperator()
     * @generated
     */
    EEnum SET_OPERATOR = eINSTANCE.getSetOperator();

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

  }

} //BeeLangPackage

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ImportImpl <em>Import</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ImportImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getImport()
   * @generated
   */
  int IMPORT = 1;

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
  int BUILD_UNIT = 2;

  /**
   * The feature id for the '<em><b>Synchronized</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__SYNCHRONIZED = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__NAME = 1;

  /**
   * The feature id for the '<em><b>Version</b></em>' attribute.
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
   * The feature id for the '<em><b>Provided Capability</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__PROVIDED_CAPABILITY = 4;

  /**
   * The feature id for the '<em><b>Required Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__REQUIRED_CAPABILITIES = 5;

  /**
   * The feature id for the '<em><b>Meta Required Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__META_REQUIRED_CAPABILITIES = 6;

  /**
   * The feature id for the '<em><b>Unset Properties</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__UNSET_PROPERTIES = 7;

  /**
   * The feature id for the '<em><b>Set Properties</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__SET_PROPERTIES = 8;

  /**
   * The feature id for the '<em><b>Advice</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__ADVICE = 9;

  /**
   * The feature id for the '<em><b>Synchronize</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__SYNCHRONIZE = 10;

  /**
   * The feature id for the '<em><b>Parts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__PARTS = 11;

  /**
   * The feature id for the '<em><b>Repository Config</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT__REPOSITORY_CONFIG = 12;

  /**
   * The number of structural features of the '<em>Build Unit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_UNIT_FEATURE_COUNT = 13;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ProvidedCapabilityImpl <em>Provided Capability</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ProvidedCapabilityImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getProvidedCapability()
   * @generated
   */
  int PROVIDED_CAPABILITY = 3;

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
   * The feature id for the '<em><b>Version</b></em>' attribute.
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
  int REQUIRED_CAPABILITY = 4;

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
   * The feature id for the '<em><b>Range</b></em>' attribute.
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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.StringPropertyImpl <em>String Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.StringPropertyImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getStringProperty()
   * @generated
   */
  int STRING_PROPERTY = 5;

  /**
   * The feature id for the '<em><b>Immutable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_PROPERTY__IMMUTABLE = 0;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_PROPERTY__KEY = 1;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_PROPERTY__VALUE = 2;

  /**
   * The number of structural features of the '<em>String Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_PROPERTY_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PropertyExpressionImpl <em>Property Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PropertyExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPropertyExpression()
   * @generated
   */
  int PROPERTY_EXPRESSION = 6;

  /**
   * The number of structural features of the '<em>Property Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_EXPRESSION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.SynchronizationImpl <em>Synchronization</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.SynchronizationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSynchronization()
   * @generated
   */
  int SYNCHRONIZATION = 7;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BuildPartImpl <em>Build Part</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BuildPartImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBuildPart()
   * @generated
   */
  int BUILD_PART = 8;

  /**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_PART__VISIBILITY = 0;

  /**
   * The feature id for the '<em><b>Provided Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_PART__PROVIDED_CAPABILITIES = 1;

  /**
   * The number of structural features of the '<em>Build Part</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUILD_PART_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ArtifactsPartImpl <em>Artifacts Part</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ArtifactsPartImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getArtifactsPart()
   * @generated
   */
  int ARTIFACTS_PART = 9;

  /**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS_PART__VISIBILITY = BUILD_PART__VISIBILITY;

  /**
   * The feature id for the '<em><b>Provided Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS_PART__PROVIDED_CAPABILITIES = BUILD_PART__PROVIDED_CAPABILITIES;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS_PART__NAME = BUILD_PART_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Asserts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS_PART__ASSERTS = BUILD_PART_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Paths</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS_PART__PATHS = BUILD_PART_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Artifacts Part</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS_PART_FEATURE_COUNT = BUILD_PART_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PathGroupImpl <em>Path Group</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PathGroupImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPathGroup()
   * @generated
   */
  int PATH_GROUP = 10;

  /**
   * The feature id for the '<em><b>Filter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATH_GROUP__FILTER = 0;

  /**
   * The feature id for the '<em><b>Paths</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATH_GROUP__PATHS = 1;

  /**
   * The feature id for the '<em><b>Base Path</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATH_GROUP__BASE_PATH = 2;

  /**
   * The feature id for the '<em><b>Unset Properties</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATH_GROUP__UNSET_PROPERTIES = 3;

  /**
   * The feature id for the '<em><b>Set Properties</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATH_GROUP__SET_PROPERTIES = 4;

  /**
   * The number of structural features of the '<em>Path Group</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATH_GROUP_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.GroupPartImpl <em>Group Part</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.GroupPartImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getGroupPart()
   * @generated
   */
  int GROUP_PART = 11;

  /**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_PART__VISIBILITY = BUILD_PART__VISIBILITY;

  /**
   * The feature id for the '<em><b>Provided Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_PART__PROVIDED_CAPABILITIES = BUILD_PART__PROVIDED_CAPABILITIES;

  /**
   * The feature id for the '<em><b>Synchronized</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_PART__SYNCHRONIZED = BUILD_PART_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_PART__NAME = BUILD_PART_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Asserts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_PART__ASSERTS = BUILD_PART_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Prerequisites</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_PART__PREREQUISITES = BUILD_PART_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Group Part</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_PART_FEATURE_COUNT = BUILD_PART_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PrerequisiteImpl <em>Prerequisite</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PrerequisiteImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPrerequisite()
   * @generated
   */
  int PREREQUISITE = 12;

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
  int CLOSURE = 13;

  /**
   * The feature id for the '<em><b>Unset Properties</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSURE__UNSET_PROPERTIES = 0;

  /**
   * The feature id for the '<em><b>Set Properties</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSURE__SET_PROPERTIES = 1;

  /**
   * The feature id for the '<em><b>Advice</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSURE__ADVICE = 2;

  /**
   * The number of structural features of the '<em>Closure</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSURE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PrerequisiteEntryImpl <em>Prerequisite Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PrerequisiteEntryImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPrerequisiteEntry()
   * @generated
   */
  int PREREQUISITE_ENTRY = 14;

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
  int PART_IN_SELF = 15;

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
  int CAPABILITY_REFERENCED_PART = 16;

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
   * The feature id for the '<em><b>Range</b></em>' attribute.
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
  int COMPOUND_REFERENCES = 17;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ActionPartImpl <em>Action Part</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ActionPartImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getActionPart()
   * @generated
   */
  int ACTION_PART = 18;

  /**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION_PART__VISIBILITY = BUILD_PART__VISIBILITY;

  /**
   * The feature id for the '<em><b>Provided Capabilities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION_PART__PROVIDED_CAPABILITIES = BUILD_PART__PROVIDED_CAPABILITIES;

  /**
   * The feature id for the '<em><b>Synchronized</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION_PART__SYNCHRONIZED = BUILD_PART_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Actor Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION_PART__ACTOR_PARAMETERS = BUILD_PART_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Asserts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION_PART__ASSERTS = BUILD_PART_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Result Groups</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION_PART__RESULT_GROUPS = BUILD_PART_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Action Part</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACTION_PART_FEATURE_COUNT = BUILD_PART_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ParameterImpl <em>Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ParameterImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getParameter()
   * @generated
   */
  int PARAMETER = 19;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__NAME = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__VALUE = 1;

  /**
   * The number of structural features of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ResultImpl <em>Result</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ResultImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getResult()
   * @generated
   */
  int RESULT = 20;

  /**
   * The feature id for the '<em><b>Result</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESULT__RESULT = 0;

  /**
   * The feature id for the '<em><b>Group</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESULT__GROUP = 1;

  /**
   * The feature id for the '<em><b>Filter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESULT__FILTER = 2;

  /**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESULT__VISIBILITY = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESULT__NAME = 4;

  /**
   * The feature id for the '<em><b>Asserts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESULT__ASSERTS = 5;

  /**
   * The feature id for the '<em><b>Paths</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESULT__PATHS = 6;

  /**
   * The feature id for the '<em><b>Closure</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESULT__CLOSURE = 7;

  /**
   * The number of structural features of the '<em>Result</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESULT_FEATURE_COUNT = 8;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ResultGroupImpl <em>Result Group</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ResultGroupImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getResultGroup()
   * @generated
   */
  int RESULT_GROUP = 21;

  /**
   * The feature id for the '<em><b>Asserts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESULT_GROUP__ASSERTS = 0;

  /**
   * The feature id for the '<em><b>Prerequisites</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESULT_GROUP__PREREQUISITES = 1;

  /**
   * The feature id for the '<em><b>Closure</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESULT_GROUP__CLOSURE = 2;

  /**
   * The number of structural features of the '<em>Result Group</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RESULT_GROUP_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.RepositoryConfigurationImpl <em>Repository Configuration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.RepositoryConfigurationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getRepositoryConfiguration()
   * @generated
   */
  int REPOSITORY_CONFIGURATION = 22;

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
  int NAMED_ADVICE = 23;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.CompoundAdviceImpl <em>Compound Advice</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.CompoundAdviceImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getCompoundAdvice()
   * @generated
   */
  int COMPOUND_ADVICE = 24;

  /**
   * The feature id for the '<em><b>Advice</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOUND_ADVICE__ADVICE = 0;

  /**
   * The number of structural features of the '<em>Compound Advice</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOUND_ADVICE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.AdviceStatementImpl <em>Advice Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.AdviceStatementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAdviceStatement()
   * @generated
   */
  int ADVICE_STATEMENT = 25;

  /**
   * The feature id for the '<em><b>Path</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_STATEMENT__PATH = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_STATEMENT__VALUE = 1;

  /**
   * The feature id for the '<em><b>Advice</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_STATEMENT__ADVICE = 2;

  /**
   * The number of structural features of the '<em>Advice Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_STATEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.AdvicePathImpl <em>Advice Path</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.AdvicePathImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAdvicePath()
   * @generated
   */
  int ADVICE_PATH = 26;

  /**
   * The feature id for the '<em><b>Path Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_PATH__PATH_ELEMENTS = 0;

  /**
   * The feature id for the '<em><b>Path Element</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_PATH__PATH_ELEMENT = 1;

  /**
   * The number of structural features of the '<em>Advice Path</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_PATH_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.AdvicePathElementImpl <em>Advice Path Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.AdvicePathElementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAdvicePathElement()
   * @generated
   */
  int ADVICE_PATH_ELEMENT = 27;

  /**
   * The feature id for the '<em><b>Node</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_PATH_ELEMENT__NODE = 0;

  /**
   * The feature id for the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_PATH_ELEMENT__PREDICATE = 1;

  /**
   * The number of structural features of the '<em>Advice Path Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_PATH_ELEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.AdvicePathChildrenImpl <em>Advice Path Children</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.AdvicePathChildrenImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAdvicePathChildren()
   * @generated
   */
  int ADVICE_PATH_CHILDREN = 28;

  /**
   * The feature id for the '<em><b>Node</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_PATH_CHILDREN__NODE = ADVICE_PATH_ELEMENT__NODE;

  /**
   * The feature id for the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_PATH_CHILDREN__PREDICATE = ADVICE_PATH_ELEMENT__PREDICATE;

  /**
   * The number of structural features of the '<em>Advice Path Children</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADVICE_PATH_CHILDREN_FEATURE_COUNT = ADVICE_PATH_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.FilterImpl <em>Filter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.FilterImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getFilter()
   * @generated
   */
  int FILTER = 29;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PreConditionAssertImpl <em>Pre Condition Assert</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PreConditionAssertImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPreConditionAssert()
   * @generated
   */
  int PRE_CONDITION_ASSERT = 30;

  /**
   * The feature id for the '<em><b>Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRE_CONDITION_ASSERT__SCOPE = 0;

  /**
   * The feature id for the '<em><b>Asserts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRE_CONDITION_ASSERT__ASSERTS = 1;

  /**
   * The number of structural features of the '<em>Pre Condition Assert</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRE_CONDITION_ASSERT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.PostConditionAssertImpl <em>Post Condition Assert</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.PostConditionAssertImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPostConditionAssert()
   * @generated
   */
  int POST_CONDITION_ASSERT = 31;

  /**
   * The feature id for the '<em><b>Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POST_CONDITION_ASSERT__SCOPE = 0;

  /**
   * The feature id for the '<em><b>Asserts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POST_CONDITION_ASSERT__ASSERTS = 1;

  /**
   * The number of structural features of the '<em>Post Condition Assert</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POST_CONDITION_ASSERT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.AssertionExpressionImpl <em>Assertion Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.AssertionExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAssertionExpression()
   * @generated
   */
  int ASSERTION_EXPRESSION = 32;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.SyntaxElementImpl <em>Syntax Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.SyntaxElementImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSyntaxElement()
   * @generated
   */
  int SYNTAX_ELEMENT = 33;

  /**
   * The number of structural features of the '<em>Syntax Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNTAX_ELEMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getExpression()
   * @generated
   */
  int EXPRESSION = 34;

  /**
   * The number of structural features of the '<em>Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = PROPERTY_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.ValueExpressionImpl <em>Value Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.ValueExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getValueExpression()
   * @generated
   */
  int VALUE_EXPRESSION = 35;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_EXPRESSION__VALUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Value Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VALUE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.OperationCallImpl <em>Operation Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.OperationCallImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getOperationCall()
   * @generated
   */
  int OPERATION_CALL = 36;

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
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__TARGET = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Operation Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.LiteralImpl <em>Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.LiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLiteral()
   * @generated
   */
  int LITERAL = 37;

  /**
   * The number of structural features of the '<em>Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BooleanLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBooleanLiteral()
   * @generated
   */
  int BOOLEAN_LITERAL = 38;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_LITERAL__VAL = LITERAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Boolean Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.IntegerLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getIntegerLiteral()
   * @generated
   */
  int INTEGER_LITERAL = 39;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_LITERAL__VAL = LITERAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Integer Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.NullLiteralImpl <em>Null Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.NullLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getNullLiteral()
   * @generated
   */
  int NULL_LITERAL = 40;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NULL_LITERAL__VAL = LITERAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Null Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NULL_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.StringLiteralImpl <em>String Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.StringLiteralImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getStringLiteral()
   * @generated
   */
  int STRING_LITERAL = 41;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_LITERAL__VAL = LITERAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>String Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.GlobalVarExpressionImpl <em>Global Var Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.GlobalVarExpressionImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getGlobalVarExpression()
   * @generated
   */
  int GLOBAL_VAR_EXPRESSION = 42;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_VAR_EXPRESSION__NAME = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Global Var Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_VAR_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.impl.BooleanOperationImpl <em>Boolean Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.impl.BooleanOperationImpl
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBooleanOperation()
   * @generated
   */
  int BOOLEAN_OPERATION = 43;

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
   * The meta object id for the '{@link org.eclipse.b3.beeLang.Visibility <em>Visibility</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.Visibility
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVisibility()
   * @generated
   */
  int VISIBILITY = 44;

  /**
   * The meta object id for the '{@link org.eclipse.b3.beeLang.AssertionScope <em>Assertion Scope</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.b3.beeLang.AssertionScope
   * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAssertionScope()
   * @generated
   */
  int ASSERTION_SCOPE = 45;


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
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.BuildUnit#isSynchronized <em>Synchronized</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Synchronized</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#isSynchronized()
   * @see #getBuildUnit()
   * @generated
   */
  EAttribute getBuildUnit_Synchronized();

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
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.BuildUnit#getVersion <em>Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Version</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getVersion()
   * @see #getBuildUnit()
   * @generated
   */
  EAttribute getBuildUnit_Version();

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
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getProvidedCapability <em>Provided Capability</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Provided Capability</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getProvidedCapability()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_ProvidedCapability();

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
   * Returns the meta object for the attribute list '{@link org.eclipse.b3.beeLang.BuildUnit#getUnsetProperties <em>Unset Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Unset Properties</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getUnsetProperties()
   * @see #getBuildUnit()
   * @generated
   */
  EAttribute getBuildUnit_UnsetProperties();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getSetProperties <em>Set Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Set Properties</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getSetProperties()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_SetProperties();

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
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getSynchronize <em>Synchronize</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Synchronize</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getSynchronize()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_Synchronize();

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
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildUnit#getRepositoryConfig <em>Repository Config</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Repository Config</em>'.
   * @see org.eclipse.b3.beeLang.BuildUnit#getRepositoryConfig()
   * @see #getBuildUnit()
   * @generated
   */
  EReference getBuildUnit_RepositoryConfig();

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
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.ProvidedCapability#getVersion <em>Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Version</em>'.
   * @see org.eclipse.b3.beeLang.ProvidedCapability#getVersion()
   * @see #getProvidedCapability()
   * @generated
   */
  EAttribute getProvidedCapability_Version();

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
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.RequiredCapability#getRange <em>Range</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Range</em>'.
   * @see org.eclipse.b3.beeLang.RequiredCapability#getRange()
   * @see #getRequiredCapability()
   * @generated
   */
  EAttribute getRequiredCapability_Range();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.StringProperty <em>String Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Property</em>'.
   * @see org.eclipse.b3.beeLang.StringProperty
   * @generated
   */
  EClass getStringProperty();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.StringProperty#isImmutable <em>Immutable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Immutable</em>'.
   * @see org.eclipse.b3.beeLang.StringProperty#isImmutable()
   * @see #getStringProperty()
   * @generated
   */
  EAttribute getStringProperty_Immutable();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.StringProperty#getKey <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see org.eclipse.b3.beeLang.StringProperty#getKey()
   * @see #getStringProperty()
   * @generated
   */
  EAttribute getStringProperty_Key();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.StringProperty#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.eclipse.b3.beeLang.StringProperty#getValue()
   * @see #getStringProperty()
   * @generated
   */
  EReference getStringProperty_Value();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PropertyExpression <em>Property Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Expression</em>'.
   * @see org.eclipse.b3.beeLang.PropertyExpression
   * @generated
   */
  EClass getPropertyExpression();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.BuildPart <em>Build Part</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Build Part</em>'.
   * @see org.eclipse.b3.beeLang.BuildPart
   * @generated
   */
  EClass getBuildPart();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.BuildPart#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.eclipse.b3.beeLang.BuildPart#getVisibility()
   * @see #getBuildPart()
   * @generated
   */
  EAttribute getBuildPart_Visibility();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.BuildPart#getProvidedCapabilities <em>Provided Capabilities</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Provided Capabilities</em>'.
   * @see org.eclipse.b3.beeLang.BuildPart#getProvidedCapabilities()
   * @see #getBuildPart()
   * @generated
   */
  EReference getBuildPart_ProvidedCapabilities();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ArtifactsPart <em>Artifacts Part</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Artifacts Part</em>'.
   * @see org.eclipse.b3.beeLang.ArtifactsPart
   * @generated
   */
  EClass getArtifactsPart();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.ArtifactsPart#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.ArtifactsPart#getName()
   * @see #getArtifactsPart()
   * @generated
   */
  EAttribute getArtifactsPart_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ArtifactsPart#getAsserts <em>Asserts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Asserts</em>'.
   * @see org.eclipse.b3.beeLang.ArtifactsPart#getAsserts()
   * @see #getArtifactsPart()
   * @generated
   */
  EReference getArtifactsPart_Asserts();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ArtifactsPart#getPaths <em>Paths</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Paths</em>'.
   * @see org.eclipse.b3.beeLang.ArtifactsPart#getPaths()
   * @see #getArtifactsPart()
   * @generated
   */
  EReference getArtifactsPart_Paths();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.PathGroup#getFilter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Filter</em>'.
   * @see org.eclipse.b3.beeLang.PathGroup#getFilter()
   * @see #getPathGroup()
   * @generated
   */
  EReference getPathGroup_Filter();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.b3.beeLang.PathGroup#getPaths <em>Paths</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Paths</em>'.
   * @see org.eclipse.b3.beeLang.PathGroup#getPaths()
   * @see #getPathGroup()
   * @generated
   */
  EAttribute getPathGroup_Paths();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.PathGroup#getBasePath <em>Base Path</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Base Path</em>'.
   * @see org.eclipse.b3.beeLang.PathGroup#getBasePath()
   * @see #getPathGroup()
   * @generated
   */
  EAttribute getPathGroup_BasePath();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.b3.beeLang.PathGroup#getUnsetProperties <em>Unset Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Unset Properties</em>'.
   * @see org.eclipse.b3.beeLang.PathGroup#getUnsetProperties()
   * @see #getPathGroup()
   * @generated
   */
  EAttribute getPathGroup_UnsetProperties();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.PathGroup#getSetProperties <em>Set Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Set Properties</em>'.
   * @see org.eclipse.b3.beeLang.PathGroup#getSetProperties()
   * @see #getPathGroup()
   * @generated
   */
  EReference getPathGroup_SetProperties();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.GroupPart <em>Group Part</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Group Part</em>'.
   * @see org.eclipse.b3.beeLang.GroupPart
   * @generated
   */
  EClass getGroupPart();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.GroupPart#isSynchronized <em>Synchronized</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Synchronized</em>'.
   * @see org.eclipse.b3.beeLang.GroupPart#isSynchronized()
   * @see #getGroupPart()
   * @generated
   */
  EAttribute getGroupPart_Synchronized();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.GroupPart#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.GroupPart#getName()
   * @see #getGroupPart()
   * @generated
   */
  EAttribute getGroupPart_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.GroupPart#getAsserts <em>Asserts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Asserts</em>'.
   * @see org.eclipse.b3.beeLang.GroupPart#getAsserts()
   * @see #getGroupPart()
   * @generated
   */
  EReference getGroupPart_Asserts();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.GroupPart#getPrerequisites <em>Prerequisites</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Prerequisites</em>'.
   * @see org.eclipse.b3.beeLang.GroupPart#getPrerequisites()
   * @see #getGroupPart()
   * @generated
   */
  EReference getGroupPart_Prerequisites();

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
   * Returns the meta object for the attribute list '{@link org.eclipse.b3.beeLang.Closure#getUnsetProperties <em>Unset Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Unset Properties</em>'.
   * @see org.eclipse.b3.beeLang.Closure#getUnsetProperties()
   * @see #getClosure()
   * @generated
   */
  EAttribute getClosure_UnsetProperties();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.Closure#getSetProperties <em>Set Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Set Properties</em>'.
   * @see org.eclipse.b3.beeLang.Closure#getSetProperties()
   * @see #getClosure()
   * @generated
   */
  EReference getClosure_SetProperties();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.Closure#getAdvice <em>Advice</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Advice</em>'.
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
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getRange <em>Range</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Range</em>'.
   * @see org.eclipse.b3.beeLang.CapabilityReferencedPart#getRange()
   * @see #getCapabilityReferencedPart()
   * @generated
   */
  EAttribute getCapabilityReferencedPart_Range();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ActionPart <em>Action Part</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Action Part</em>'.
   * @see org.eclipse.b3.beeLang.ActionPart
   * @generated
   */
  EClass getActionPart();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.ActionPart#isSynchronized <em>Synchronized</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Synchronized</em>'.
   * @see org.eclipse.b3.beeLang.ActionPart#isSynchronized()
   * @see #getActionPart()
   * @generated
   */
  EAttribute getActionPart_Synchronized();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ActionPart#getActorParameters <em>Actor Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Actor Parameters</em>'.
   * @see org.eclipse.b3.beeLang.ActionPart#getActorParameters()
   * @see #getActionPart()
   * @generated
   */
  EReference getActionPart_ActorParameters();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ActionPart#getAsserts <em>Asserts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Asserts</em>'.
   * @see org.eclipse.b3.beeLang.ActionPart#getAsserts()
   * @see #getActionPart()
   * @generated
   */
  EReference getActionPart_Asserts();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ActionPart#getResultGroups <em>Result Groups</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Result Groups</em>'.
   * @see org.eclipse.b3.beeLang.ActionPart#getResultGroups()
   * @see #getActionPart()
   * @generated
   */
  EReference getActionPart_ResultGroups();

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
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Parameter#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.eclipse.b3.beeLang.Parameter#getValue()
   * @see #getParameter()
   * @generated
   */
  EReference getParameter_Value();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Result <em>Result</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Result</em>'.
   * @see org.eclipse.b3.beeLang.Result
   * @generated
   */
  EClass getResult();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.Result#getResult <em>Result</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Result</em>'.
   * @see org.eclipse.b3.beeLang.Result#getResult()
   * @see #getResult()
   * @generated
   */
  EReference getResult_Result();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Result#getGroup <em>Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Group</em>'.
   * @see org.eclipse.b3.beeLang.Result#getGroup()
   * @see #getResult()
   * @generated
   */
  EReference getResult_Group();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Result#getFilter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Filter</em>'.
   * @see org.eclipse.b3.beeLang.Result#getFilter()
   * @see #getResult()
   * @generated
   */
  EReference getResult_Filter();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Result#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.eclipse.b3.beeLang.Result#getVisibility()
   * @see #getResult()
   * @generated
   */
  EAttribute getResult_Visibility();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.Result#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.Result#getName()
   * @see #getResult()
   * @generated
   */
  EAttribute getResult_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.Result#getAsserts <em>Asserts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Asserts</em>'.
   * @see org.eclipse.b3.beeLang.Result#getAsserts()
   * @see #getResult()
   * @generated
   */
  EReference getResult_Asserts();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.Result#getPaths <em>Paths</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Paths</em>'.
   * @see org.eclipse.b3.beeLang.Result#getPaths()
   * @see #getResult()
   * @generated
   */
  EReference getResult_Paths();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.Result#getClosure <em>Closure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Closure</em>'.
   * @see org.eclipse.b3.beeLang.Result#getClosure()
   * @see #getResult()
   * @generated
   */
  EReference getResult_Closure();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ResultGroup <em>Result Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Result Group</em>'.
   * @see org.eclipse.b3.beeLang.ResultGroup
   * @generated
   */
  EClass getResultGroup();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ResultGroup#getAsserts <em>Asserts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Asserts</em>'.
   * @see org.eclipse.b3.beeLang.ResultGroup#getAsserts()
   * @see #getResultGroup()
   * @generated
   */
  EReference getResultGroup_Asserts();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.ResultGroup#getPrerequisites <em>Prerequisites</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Prerequisites</em>'.
   * @see org.eclipse.b3.beeLang.ResultGroup#getPrerequisites()
   * @see #getResultGroup()
   * @generated
   */
  EReference getResultGroup_Prerequisites();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ResultGroup#getClosure <em>Closure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Closure</em>'.
   * @see org.eclipse.b3.beeLang.ResultGroup#getClosure()
   * @see #getResultGroup()
   * @generated
   */
  EReference getResultGroup_Closure();

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
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.AdvicePath#getPathElements <em>Path Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Path Elements</em>'.
   * @see org.eclipse.b3.beeLang.AdvicePath#getPathElements()
   * @see #getAdvicePath()
   * @generated
   */
  EReference getAdvicePath_PathElements();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.AdvicePath#getPathElement <em>Path Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Path Element</em>'.
   * @see org.eclipse.b3.beeLang.AdvicePath#getPathElement()
   * @see #getAdvicePath()
   * @generated
   */
  EReference getAdvicePath_PathElement();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.AdvicePathElement <em>Advice Path Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Advice Path Element</em>'.
   * @see org.eclipse.b3.beeLang.AdvicePathElement
   * @generated
   */
  EClass getAdvicePathElement();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.AdvicePathElement#getNode <em>Node</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Node</em>'.
   * @see org.eclipse.b3.beeLang.AdvicePathElement#getNode()
   * @see #getAdvicePathElement()
   * @generated
   */
  EAttribute getAdvicePathElement_Node();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.AdvicePathElement#getPredicate <em>Predicate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Predicate</em>'.
   * @see org.eclipse.b3.beeLang.AdvicePathElement#getPredicate()
   * @see #getAdvicePathElement()
   * @generated
   */
  EReference getAdvicePathElement_Predicate();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.AdvicePathChildren <em>Advice Path Children</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Advice Path Children</em>'.
   * @see org.eclipse.b3.beeLang.AdvicePathChildren
   * @generated
   */
  EClass getAdvicePathChildren();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PreConditionAssert <em>Pre Condition Assert</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pre Condition Assert</em>'.
   * @see org.eclipse.b3.beeLang.PreConditionAssert
   * @generated
   */
  EClass getPreConditionAssert();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.PreConditionAssert#getScope <em>Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Scope</em>'.
   * @see org.eclipse.b3.beeLang.PreConditionAssert#getScope()
   * @see #getPreConditionAssert()
   * @generated
   */
  EAttribute getPreConditionAssert_Scope();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.PreConditionAssert#getAsserts <em>Asserts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Asserts</em>'.
   * @see org.eclipse.b3.beeLang.PreConditionAssert#getAsserts()
   * @see #getPreConditionAssert()
   * @generated
   */
  EReference getPreConditionAssert_Asserts();

  /**
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.PostConditionAssert <em>Post Condition Assert</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Post Condition Assert</em>'.
   * @see org.eclipse.b3.beeLang.PostConditionAssert
   * @generated
   */
  EClass getPostConditionAssert();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.PostConditionAssert#getScope <em>Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Scope</em>'.
   * @see org.eclipse.b3.beeLang.PostConditionAssert#getScope()
   * @see #getPostConditionAssert()
   * @generated
   */
  EAttribute getPostConditionAssert_Scope();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.b3.beeLang.PostConditionAssert#getAsserts <em>Asserts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Asserts</em>'.
   * @see org.eclipse.b3.beeLang.PostConditionAssert#getAsserts()
   * @see #getPostConditionAssert()
   * @generated
   */
  EReference getPostConditionAssert_Asserts();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.SyntaxElement <em>Syntax Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Syntax Element</em>'.
   * @see org.eclipse.b3.beeLang.SyntaxElement
   * @generated
   */
  EClass getSyntaxElement();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.ValueExpression <em>Value Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Value Expression</em>'.
   * @see org.eclipse.b3.beeLang.ValueExpression
   * @generated
   */
  EClass getValueExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.b3.beeLang.ValueExpression#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.eclipse.b3.beeLang.ValueExpression#getValue()
   * @see #getValueExpression()
   * @generated
   */
  EReference getValueExpression_Value();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.Literal <em>Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Literal</em>'.
   * @see org.eclipse.b3.beeLang.Literal
   * @generated
   */
  EClass getLiteral();

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
   * Returns the meta object for class '{@link org.eclipse.b3.beeLang.GlobalVarExpression <em>Global Var Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Global Var Expression</em>'.
   * @see org.eclipse.b3.beeLang.GlobalVarExpression
   * @generated
   */
  EClass getGlobalVarExpression();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.b3.beeLang.GlobalVarExpression#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.b3.beeLang.GlobalVarExpression#getName()
   * @see #getGlobalVarExpression()
   * @generated
   */
  EAttribute getGlobalVarExpression_Name();

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
   * Returns the meta object for enum '{@link org.eclipse.b3.beeLang.Visibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Visibility</em>'.
   * @see org.eclipse.b3.beeLang.Visibility
   * @generated
   */
  EEnum getVisibility();

  /**
   * Returns the meta object for enum '{@link org.eclipse.b3.beeLang.AssertionScope <em>Assertion Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Assertion Scope</em>'.
   * @see org.eclipse.b3.beeLang.AssertionScope
   * @generated
   */
  EEnum getAssertionScope();

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
     * The meta object literal for the '<em><b>Synchronized</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILD_UNIT__SYNCHRONIZED = eINSTANCE.getBuildUnit_Synchronized();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILD_UNIT__NAME = eINSTANCE.getBuildUnit_Name();

    /**
     * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILD_UNIT__VERSION = eINSTANCE.getBuildUnit_Version();

    /**
     * The meta object literal for the '<em><b>Implements</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILD_UNIT__IMPLEMENTS = eINSTANCE.getBuildUnit_Implements();

    /**
     * The meta object literal for the '<em><b>Provided Capability</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__PROVIDED_CAPABILITY = eINSTANCE.getBuildUnit_ProvidedCapability();

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
     * The meta object literal for the '<em><b>Unset Properties</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILD_UNIT__UNSET_PROPERTIES = eINSTANCE.getBuildUnit_UnsetProperties();

    /**
     * The meta object literal for the '<em><b>Set Properties</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__SET_PROPERTIES = eINSTANCE.getBuildUnit_SetProperties();

    /**
     * The meta object literal for the '<em><b>Advice</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__ADVICE = eINSTANCE.getBuildUnit_Advice();

    /**
     * The meta object literal for the '<em><b>Synchronize</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__SYNCHRONIZE = eINSTANCE.getBuildUnit_Synchronize();

    /**
     * The meta object literal for the '<em><b>Parts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__PARTS = eINSTANCE.getBuildUnit_Parts();

    /**
     * The meta object literal for the '<em><b>Repository Config</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_UNIT__REPOSITORY_CONFIG = eINSTANCE.getBuildUnit_RepositoryConfig();

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
     * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROVIDED_CAPABILITY__VERSION = eINSTANCE.getProvidedCapability_Version();

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
     * The meta object literal for the '<em><b>Range</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REQUIRED_CAPABILITY__RANGE = eINSTANCE.getRequiredCapability_Range();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.StringPropertyImpl <em>String Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.StringPropertyImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getStringProperty()
     * @generated
     */
    EClass STRING_PROPERTY = eINSTANCE.getStringProperty();

    /**
     * The meta object literal for the '<em><b>Immutable</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute STRING_PROPERTY__IMMUTABLE = eINSTANCE.getStringProperty_Immutable();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute STRING_PROPERTY__KEY = eINSTANCE.getStringProperty_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STRING_PROPERTY__VALUE = eINSTANCE.getStringProperty_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PropertyExpressionImpl <em>Property Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PropertyExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPropertyExpression()
     * @generated
     */
    EClass PROPERTY_EXPRESSION = eINSTANCE.getPropertyExpression();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.BuildPartImpl <em>Build Part</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.BuildPartImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getBuildPart()
     * @generated
     */
    EClass BUILD_PART = eINSTANCE.getBuildPart();

    /**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BUILD_PART__VISIBILITY = eINSTANCE.getBuildPart_Visibility();

    /**
     * The meta object literal for the '<em><b>Provided Capabilities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BUILD_PART__PROVIDED_CAPABILITIES = eINSTANCE.getBuildPart_ProvidedCapabilities();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ArtifactsPartImpl <em>Artifacts Part</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ArtifactsPartImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getArtifactsPart()
     * @generated
     */
    EClass ARTIFACTS_PART = eINSTANCE.getArtifactsPart();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ARTIFACTS_PART__NAME = eINSTANCE.getArtifactsPart_Name();

    /**
     * The meta object literal for the '<em><b>Asserts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARTIFACTS_PART__ASSERTS = eINSTANCE.getArtifactsPart_Asserts();

    /**
     * The meta object literal for the '<em><b>Paths</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARTIFACTS_PART__PATHS = eINSTANCE.getArtifactsPart_Paths();

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
     * The meta object literal for the '<em><b>Filter</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PATH_GROUP__FILTER = eINSTANCE.getPathGroup_Filter();

    /**
     * The meta object literal for the '<em><b>Paths</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PATH_GROUP__PATHS = eINSTANCE.getPathGroup_Paths();

    /**
     * The meta object literal for the '<em><b>Base Path</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PATH_GROUP__BASE_PATH = eINSTANCE.getPathGroup_BasePath();

    /**
     * The meta object literal for the '<em><b>Unset Properties</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PATH_GROUP__UNSET_PROPERTIES = eINSTANCE.getPathGroup_UnsetProperties();

    /**
     * The meta object literal for the '<em><b>Set Properties</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PATH_GROUP__SET_PROPERTIES = eINSTANCE.getPathGroup_SetProperties();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.GroupPartImpl <em>Group Part</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.GroupPartImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getGroupPart()
     * @generated
     */
    EClass GROUP_PART = eINSTANCE.getGroupPart();

    /**
     * The meta object literal for the '<em><b>Synchronized</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GROUP_PART__SYNCHRONIZED = eINSTANCE.getGroupPart_Synchronized();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GROUP_PART__NAME = eINSTANCE.getGroupPart_Name();

    /**
     * The meta object literal for the '<em><b>Asserts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP_PART__ASSERTS = eINSTANCE.getGroupPart_Asserts();

    /**
     * The meta object literal for the '<em><b>Prerequisites</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP_PART__PREREQUISITES = eINSTANCE.getGroupPart_Prerequisites();

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
     * The meta object literal for the '<em><b>Unset Properties</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CLOSURE__UNSET_PROPERTIES = eINSTANCE.getClosure_UnsetProperties();

    /**
     * The meta object literal for the '<em><b>Set Properties</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLOSURE__SET_PROPERTIES = eINSTANCE.getClosure_SetProperties();

    /**
     * The meta object literal for the '<em><b>Advice</b></em>' containment reference list feature.
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
     * The meta object literal for the '<em><b>Range</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CAPABILITY_REFERENCED_PART__RANGE = eINSTANCE.getCapabilityReferencedPart_Range();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ActionPartImpl <em>Action Part</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ActionPartImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getActionPart()
     * @generated
     */
    EClass ACTION_PART = eINSTANCE.getActionPart();

    /**
     * The meta object literal for the '<em><b>Synchronized</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ACTION_PART__SYNCHRONIZED = eINSTANCE.getActionPart_Synchronized();

    /**
     * The meta object literal for the '<em><b>Actor Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ACTION_PART__ACTOR_PARAMETERS = eINSTANCE.getActionPart_ActorParameters();

    /**
     * The meta object literal for the '<em><b>Asserts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ACTION_PART__ASSERTS = eINSTANCE.getActionPart_Asserts();

    /**
     * The meta object literal for the '<em><b>Result Groups</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ACTION_PART__RESULT_GROUPS = eINSTANCE.getActionPart_ResultGroups();

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
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER__VALUE = eINSTANCE.getParameter_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ResultImpl <em>Result</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ResultImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getResult()
     * @generated
     */
    EClass RESULT = eINSTANCE.getResult();

    /**
     * The meta object literal for the '<em><b>Result</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RESULT__RESULT = eINSTANCE.getResult_Result();

    /**
     * The meta object literal for the '<em><b>Group</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RESULT__GROUP = eINSTANCE.getResult_Group();

    /**
     * The meta object literal for the '<em><b>Filter</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RESULT__FILTER = eINSTANCE.getResult_Filter();

    /**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RESULT__VISIBILITY = eINSTANCE.getResult_Visibility();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RESULT__NAME = eINSTANCE.getResult_Name();

    /**
     * The meta object literal for the '<em><b>Asserts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RESULT__ASSERTS = eINSTANCE.getResult_Asserts();

    /**
     * The meta object literal for the '<em><b>Paths</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RESULT__PATHS = eINSTANCE.getResult_Paths();

    /**
     * The meta object literal for the '<em><b>Closure</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RESULT__CLOSURE = eINSTANCE.getResult_Closure();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ResultGroupImpl <em>Result Group</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ResultGroupImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getResultGroup()
     * @generated
     */
    EClass RESULT_GROUP = eINSTANCE.getResultGroup();

    /**
     * The meta object literal for the '<em><b>Asserts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RESULT_GROUP__ASSERTS = eINSTANCE.getResultGroup_Asserts();

    /**
     * The meta object literal for the '<em><b>Prerequisites</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RESULT_GROUP__PREREQUISITES = eINSTANCE.getResultGroup_Prerequisites();

    /**
     * The meta object literal for the '<em><b>Closure</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RESULT_GROUP__CLOSURE = eINSTANCE.getResultGroup_Closure();

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
     * The meta object literal for the '<em><b>Path Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ADVICE_PATH__PATH_ELEMENTS = eINSTANCE.getAdvicePath_PathElements();

    /**
     * The meta object literal for the '<em><b>Path Element</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ADVICE_PATH__PATH_ELEMENT = eINSTANCE.getAdvicePath_PathElement();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.AdvicePathElementImpl <em>Advice Path Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.AdvicePathElementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAdvicePathElement()
     * @generated
     */
    EClass ADVICE_PATH_ELEMENT = eINSTANCE.getAdvicePathElement();

    /**
     * The meta object literal for the '<em><b>Node</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ADVICE_PATH_ELEMENT__NODE = eINSTANCE.getAdvicePathElement_Node();

    /**
     * The meta object literal for the '<em><b>Predicate</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ADVICE_PATH_ELEMENT__PREDICATE = eINSTANCE.getAdvicePathElement_Predicate();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.AdvicePathChildrenImpl <em>Advice Path Children</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.AdvicePathChildrenImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAdvicePathChildren()
     * @generated
     */
    EClass ADVICE_PATH_CHILDREN = eINSTANCE.getAdvicePathChildren();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PreConditionAssertImpl <em>Pre Condition Assert</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PreConditionAssertImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPreConditionAssert()
     * @generated
     */
    EClass PRE_CONDITION_ASSERT = eINSTANCE.getPreConditionAssert();

    /**
     * The meta object literal for the '<em><b>Scope</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRE_CONDITION_ASSERT__SCOPE = eINSTANCE.getPreConditionAssert_Scope();

    /**
     * The meta object literal for the '<em><b>Asserts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRE_CONDITION_ASSERT__ASSERTS = eINSTANCE.getPreConditionAssert_Asserts();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.PostConditionAssertImpl <em>Post Condition Assert</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.PostConditionAssertImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getPostConditionAssert()
     * @generated
     */
    EClass POST_CONDITION_ASSERT = eINSTANCE.getPostConditionAssert();

    /**
     * The meta object literal for the '<em><b>Scope</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POST_CONDITION_ASSERT__SCOPE = eINSTANCE.getPostConditionAssert_Scope();

    /**
     * The meta object literal for the '<em><b>Asserts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POST_CONDITION_ASSERT__ASSERTS = eINSTANCE.getPostConditionAssert_Asserts();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.SyntaxElementImpl <em>Syntax Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.SyntaxElementImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getSyntaxElement()
     * @generated
     */
    EClass SYNTAX_ELEMENT = eINSTANCE.getSyntaxElement();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.ValueExpressionImpl <em>Value Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.ValueExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getValueExpression()
     * @generated
     */
    EClass VALUE_EXPRESSION = eINSTANCE.getValueExpression();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VALUE_EXPRESSION__VALUE = eINSTANCE.getValueExpression_Value();

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
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERATION_CALL__TARGET = eINSTANCE.getOperationCall_Target();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.LiteralImpl <em>Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.LiteralImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getLiteral()
     * @generated
     */
    EClass LITERAL = eINSTANCE.getLiteral();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.impl.GlobalVarExpressionImpl <em>Global Var Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.impl.GlobalVarExpressionImpl
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getGlobalVarExpression()
     * @generated
     */
    EClass GLOBAL_VAR_EXPRESSION = eINSTANCE.getGlobalVarExpression();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GLOBAL_VAR_EXPRESSION__NAME = eINSTANCE.getGlobalVarExpression_Name();

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
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.Visibility <em>Visibility</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.Visibility
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getVisibility()
     * @generated
     */
    EEnum VISIBILITY = eINSTANCE.getVisibility();

    /**
     * The meta object literal for the '{@link org.eclipse.b3.beeLang.AssertionScope <em>Assertion Scope</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.b3.beeLang.AssertionScope
     * @see org.eclipse.b3.beeLang.impl.BeeLangPackageImpl#getAssertionScope()
     * @generated
     */
    EEnum ASSERTION_SCOPE = eINSTANCE.getAssertionScope();

  }

} //BeeLangPackage

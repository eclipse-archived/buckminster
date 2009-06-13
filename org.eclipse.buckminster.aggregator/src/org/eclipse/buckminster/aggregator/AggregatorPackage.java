/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

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
 * @see org.eclipse.buckminster.aggregator.AggregatorFactory
 * @model kind="package"
 * @generated
 */
public interface AggregatorPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "aggregator";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/buckminster/2009/aggregator";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "aggregator";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  AggregatorPackage eINSTANCE = org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl <em>Aggregator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorImpl
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getAggregator()
   * @generated
   */
  int AGGREGATOR = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATOR__TYPE = 0;

  /**
   * The feature id for the '<em><b>Build Root</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATOR__BUILD_ROOT = 1;

  /**
   * The feature id for the '<em><b>Configurations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATOR__CONFIGURATIONS = 2;

  /**
   * The feature id for the '<em><b>Contributions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATOR__CONTRIBUTIONS = 3;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATOR__LABEL = 4;

  /**
   * The feature id for the '<em><b>Buildmaster</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATOR__BUILDMASTER = 5;

  /**
   * The feature id for the '<em><b>Sendmail</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATOR__SENDMAIL = 6;

  /**
   * The feature id for the '<em><b>Contacts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATOR__CONTACTS = 7;

  /**
   * The number of structural features of the '<em>Aggregator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGGREGATOR_FEATURE_COUNT = 8;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.RepositoryImpl <em>Repository</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.impl.RepositoryImpl
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getRepository()
   * @generated
   */
  int REPOSITORY = 1;

  /**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY__LOCATION = 0;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY__LABEL = 1;

  /**
   * The feature id for the '<em><b>Products</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY__PRODUCTS = 2;

  /**
   * The feature id for the '<em><b>Bundles</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY__BUNDLES = 3;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY__FEATURES = 4;

  /**
   * The feature id for the '<em><b>Categories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY__CATEGORIES = 5;

  /**
   * The feature id for the '<em><b>Mapped</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY__MAPPED = 6;

  /**
   * The number of structural features of the '<em>Repository</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPOSITORY_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.ConfigurationImpl <em>Configuration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.impl.ConfigurationImpl
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getConfiguration()
   * @generated
   */
  int CONFIGURATION = 2;

  /**
   * The feature id for the '<em><b>Operating System</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__OPERATING_SYSTEM = 0;

  /**
   * The feature id for the '<em><b>Window System</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__WINDOW_SYSTEM = 1;

  /**
   * The feature id for the '<em><b>Architecture</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__ARCHITECTURE = 2;

  /**
   * The feature id for the '<em><b>Archive Format</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__ARCHIVE_FORMAT = 3;

  /**
   * The number of structural features of the '<em>Configuration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.CategoryImpl <em>Category</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.impl.CategoryImpl
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getCategory()
   * @generated
   */
  int CATEGORY = 3;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY__LABEL = 1;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY__DESCRIPTION = 2;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY__FEATURES = 3;

  /**
   * The feature id for the '<em><b>Mapped</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY__MAPPED = 4;

  /**
   * The number of structural features of the '<em>Category</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORY_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl <em>Contribution</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.impl.ContributionImpl
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getContribution()
   * @generated
   */
  int CONTRIBUTION = 4;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTRIBUTION__LABEL = 0;

  /**
   * The feature id for the '<em><b>Repositories</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTRIBUTION__REPOSITORIES = 1;

  /**
   * The feature id for the '<em><b>Contacts</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTRIBUTION__CONTACTS = 2;

  /**
   * The number of structural features of the '<em>Contribution</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTRIBUTION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.ContactImpl <em>Contact</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.impl.ContactImpl
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getContact()
   * @generated
   */
  int CONTACT = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTACT__NAME = 0;

  /**
   * The feature id for the '<em><b>Email</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTACT__EMAIL = 1;

  /**
   * The number of structural features of the '<em>Contact</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTACT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.InstallationUnitImpl <em>Installation Unit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.impl.InstallationUnitImpl
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getInstallationUnit()
   * @generated
   */
  int INSTALLATION_UNIT = 8;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSTALLATION_UNIT__ID = 0;

  /**
   * The feature id for the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSTALLATION_UNIT__VERSION = 1;

  /**
   * The feature id for the '<em><b>Repository</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSTALLATION_UNIT__REPOSITORY = 2;

  /**
   * The feature id for the '<em><b>Mapped</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSTALLATION_UNIT__MAPPED = 3;

  /**
   * The number of structural features of the '<em>Installation Unit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INSTALLATION_UNIT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.FeatureImpl <em>Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.impl.FeatureImpl
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getFeature()
   * @generated
   */
  int FEATURE = 6;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__ID = INSTALLATION_UNIT__ID;

  /**
   * The feature id for the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__VERSION = INSTALLATION_UNIT__VERSION;

  /**
   * The feature id for the '<em><b>Repository</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__REPOSITORY = INSTALLATION_UNIT__REPOSITORY;

  /**
   * The feature id for the '<em><b>Mapped</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__MAPPED = INSTALLATION_UNIT__MAPPED;

  /**
   * The feature id for the '<em><b>Category</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__CATEGORY = INSTALLATION_UNIT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>In Product</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__IN_PRODUCT = INSTALLATION_UNIT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_FEATURE_COUNT = INSTALLATION_UNIT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.BundleImpl <em>Bundle</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.impl.BundleImpl
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getBundle()
   * @generated
   */
  int BUNDLE = 7;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUNDLE__ID = INSTALLATION_UNIT__ID;

  /**
   * The feature id for the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUNDLE__VERSION = INSTALLATION_UNIT__VERSION;

  /**
   * The feature id for the '<em><b>Repository</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUNDLE__REPOSITORY = INSTALLATION_UNIT__REPOSITORY;

  /**
   * The feature id for the '<em><b>Mapped</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUNDLE__MAPPED = INSTALLATION_UNIT__MAPPED;

  /**
   * The number of structural features of the '<em>Bundle</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BUNDLE_FEATURE_COUNT = INSTALLATION_UNIT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.ProductImpl <em>Product</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.impl.ProductImpl
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getProduct()
   * @generated
   */
  int PRODUCT = 9;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRODUCT__ID = INSTALLATION_UNIT__ID;

  /**
   * The feature id for the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRODUCT__VERSION = INSTALLATION_UNIT__VERSION;

  /**
   * The feature id for the '<em><b>Repository</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRODUCT__REPOSITORY = INSTALLATION_UNIT__REPOSITORY;

  /**
   * The feature id for the '<em><b>Mapped</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRODUCT__MAPPED = INSTALLATION_UNIT__MAPPED;

  /**
   * The number of structural features of the '<em>Product</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRODUCT_FEATURE_COUNT = INSTALLATION_UNIT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.AggregateType <em>Aggregate Type</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.AggregateType
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getAggregateType()
   * @generated
   */
  int AGGREGATE_TYPE = 10;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.OperatingSystem <em>Operating System</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.OperatingSystem
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getOperatingSystem()
   * @generated
   */
  int OPERATING_SYSTEM = 11;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.WindowSystem <em>Window System</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.WindowSystem
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getWindowSystem()
   * @generated
   */
  int WINDOW_SYSTEM = 12;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.Architecture <em>Architecture</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.Architecture
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getArchitecture()
   * @generated
   */
  int ARCHITECTURE = 13;

  /**
   * The meta object id for the '{@link org.eclipse.buckminster.aggregator.ArchiveFormat <em>Archive Format</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.buckminster.aggregator.ArchiveFormat
   * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getArchiveFormat()
   * @generated
   */
  int ARCHIVE_FORMAT = 14;


  /**
   * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Aggregator <em>Aggregator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Aggregator</em>'.
   * @see org.eclipse.buckminster.aggregator.Aggregator
   * @generated
   */
  EClass getAggregator();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Aggregator#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.eclipse.buckminster.aggregator.Aggregator#getType()
   * @see #getAggregator()
   * @generated
   */
  EAttribute getAggregator_Type();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Aggregator#getBuildRoot <em>Build Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Build Root</em>'.
   * @see org.eclipse.buckminster.aggregator.Aggregator#getBuildRoot()
   * @see #getAggregator()
   * @generated
   */
  EAttribute getAggregator_BuildRoot();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.buckminster.aggregator.Aggregator#getConfigurations <em>Configurations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Configurations</em>'.
   * @see org.eclipse.buckminster.aggregator.Aggregator#getConfigurations()
   * @see #getAggregator()
   * @generated
   */
  EReference getAggregator_Configurations();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.buckminster.aggregator.Aggregator#getContributions <em>Contributions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Contributions</em>'.
   * @see org.eclipse.buckminster.aggregator.Aggregator#getContributions()
   * @see #getAggregator()
   * @generated
   */
  EReference getAggregator_Contributions();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Aggregator#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see org.eclipse.buckminster.aggregator.Aggregator#getLabel()
   * @see #getAggregator()
   * @generated
   */
  EAttribute getAggregator_Label();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.buckminster.aggregator.Aggregator#getBuildmaster <em>Buildmaster</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Buildmaster</em>'.
   * @see org.eclipse.buckminster.aggregator.Aggregator#getBuildmaster()
   * @see #getAggregator()
   * @generated
   */
  EReference getAggregator_Buildmaster();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Aggregator#isSendmail <em>Sendmail</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sendmail</em>'.
   * @see org.eclipse.buckminster.aggregator.Aggregator#isSendmail()
   * @see #getAggregator()
   * @generated
   */
  EAttribute getAggregator_Sendmail();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.buckminster.aggregator.Aggregator#getContacts <em>Contacts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Contacts</em>'.
   * @see org.eclipse.buckminster.aggregator.Aggregator#getContacts()
   * @see #getAggregator()
   * @generated
   */
  EReference getAggregator_Contacts();

  /**
   * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Repository <em>Repository</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Repository</em>'.
   * @see org.eclipse.buckminster.aggregator.Repository
   * @generated
   */
  EClass getRepository();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Repository#getLocation <em>Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Location</em>'.
   * @see org.eclipse.buckminster.aggregator.Repository#getLocation()
   * @see #getRepository()
   * @generated
   */
  EAttribute getRepository_Location();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Repository#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see org.eclipse.buckminster.aggregator.Repository#getLabel()
   * @see #getRepository()
   * @generated
   */
  EAttribute getRepository_Label();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.buckminster.aggregator.Repository#getProducts <em>Products</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Products</em>'.
   * @see org.eclipse.buckminster.aggregator.Repository#getProducts()
   * @see #getRepository()
   * @generated
   */
  EReference getRepository_Products();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.buckminster.aggregator.Repository#getBundles <em>Bundles</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Bundles</em>'.
   * @see org.eclipse.buckminster.aggregator.Repository#getBundles()
   * @see #getRepository()
   * @generated
   */
  EReference getRepository_Bundles();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.buckminster.aggregator.Repository#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see org.eclipse.buckminster.aggregator.Repository#getFeatures()
   * @see #getRepository()
   * @generated
   */
  EReference getRepository_Features();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.buckminster.aggregator.Repository#getCategories <em>Categories</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Categories</em>'.
   * @see org.eclipse.buckminster.aggregator.Repository#getCategories()
   * @see #getRepository()
   * @generated
   */
  EReference getRepository_Categories();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Repository#isMapped <em>Mapped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mapped</em>'.
   * @see org.eclipse.buckminster.aggregator.Repository#isMapped()
   * @see #getRepository()
   * @generated
   */
  EAttribute getRepository_Mapped();

  /**
   * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Configuration <em>Configuration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Configuration</em>'.
   * @see org.eclipse.buckminster.aggregator.Configuration
   * @generated
   */
  EClass getConfiguration();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Configuration#getOperatingSystem <em>Operating System</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operating System</em>'.
   * @see org.eclipse.buckminster.aggregator.Configuration#getOperatingSystem()
   * @see #getConfiguration()
   * @generated
   */
  EAttribute getConfiguration_OperatingSystem();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Configuration#getWindowSystem <em>Window System</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Window System</em>'.
   * @see org.eclipse.buckminster.aggregator.Configuration#getWindowSystem()
   * @see #getConfiguration()
   * @generated
   */
  EAttribute getConfiguration_WindowSystem();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Configuration#getArchitecture <em>Architecture</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Architecture</em>'.
   * @see org.eclipse.buckminster.aggregator.Configuration#getArchitecture()
   * @see #getConfiguration()
   * @generated
   */
  EAttribute getConfiguration_Architecture();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Configuration#getArchiveFormat <em>Archive Format</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Archive Format</em>'.
   * @see org.eclipse.buckminster.aggregator.Configuration#getArchiveFormat()
   * @see #getConfiguration()
   * @generated
   */
  EAttribute getConfiguration_ArchiveFormat();

  /**
   * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Category <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Category</em>'.
   * @see org.eclipse.buckminster.aggregator.Category
   * @generated
   */
  EClass getCategory();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Category#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.eclipse.buckminster.aggregator.Category#getIdentifier()
   * @see #getCategory()
   * @generated
   */
  EAttribute getCategory_Identifier();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Category#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see org.eclipse.buckminster.aggregator.Category#getLabel()
   * @see #getCategory()
   * @generated
   */
  EAttribute getCategory_Label();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Category#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see org.eclipse.buckminster.aggregator.Category#getDescription()
   * @see #getCategory()
   * @generated
   */
  EAttribute getCategory_Description();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.buckminster.aggregator.Category#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Features</em>'.
   * @see org.eclipse.buckminster.aggregator.Category#getFeatures()
   * @see #getCategory()
   * @generated
   */
  EReference getCategory_Features();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Category#isMapped <em>Mapped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mapped</em>'.
   * @see org.eclipse.buckminster.aggregator.Category#isMapped()
   * @see #getCategory()
   * @generated
   */
  EAttribute getCategory_Mapped();

  /**
   * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Contribution <em>Contribution</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Contribution</em>'.
   * @see org.eclipse.buckminster.aggregator.Contribution
   * @generated
   */
  EClass getContribution();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Contribution#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see org.eclipse.buckminster.aggregator.Contribution#getLabel()
   * @see #getContribution()
   * @generated
   */
  EAttribute getContribution_Label();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.buckminster.aggregator.Contribution#getRepositories <em>Repositories</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Repositories</em>'.
   * @see org.eclipse.buckminster.aggregator.Contribution#getRepositories()
   * @see #getContribution()
   * @generated
   */
  EReference getContribution_Repositories();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.buckminster.aggregator.Contribution#getContacts <em>Contacts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Contacts</em>'.
   * @see org.eclipse.buckminster.aggregator.Contribution#getContacts()
   * @see #getContribution()
   * @generated
   */
  EReference getContribution_Contacts();

  /**
   * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Contact <em>Contact</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Contact</em>'.
   * @see org.eclipse.buckminster.aggregator.Contact
   * @generated
   */
  EClass getContact();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Contact#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.buckminster.aggregator.Contact#getName()
   * @see #getContact()
   * @generated
   */
  EAttribute getContact_Name();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Contact#getEmail <em>Email</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Email</em>'.
   * @see org.eclipse.buckminster.aggregator.Contact#getEmail()
   * @see #getContact()
   * @generated
   */
  EAttribute getContact_Email();

  /**
   * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Feature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature</em>'.
   * @see org.eclipse.buckminster.aggregator.Feature
   * @generated
   */
  EClass getFeature();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.buckminster.aggregator.Feature#getCategory <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Category</em>'.
   * @see org.eclipse.buckminster.aggregator.Feature#getCategory()
   * @see #getFeature()
   * @generated
   */
  EReference getFeature_Category();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Feature#isInProduct <em>In Product</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>In Product</em>'.
   * @see org.eclipse.buckminster.aggregator.Feature#isInProduct()
   * @see #getFeature()
   * @generated
   */
  EAttribute getFeature_InProduct();

  /**
   * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Bundle <em>Bundle</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Bundle</em>'.
   * @see org.eclipse.buckminster.aggregator.Bundle
   * @generated
   */
  EClass getBundle();

  /**
   * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.InstallationUnit <em>Installation Unit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Installation Unit</em>'.
   * @see org.eclipse.buckminster.aggregator.InstallationUnit
   * @generated
   */
  EClass getInstallationUnit();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.InstallationUnit#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.eclipse.buckminster.aggregator.InstallationUnit#getId()
   * @see #getInstallationUnit()
   * @generated
   */
  EAttribute getInstallationUnit_Id();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.InstallationUnit#getVersion <em>Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Version</em>'.
   * @see org.eclipse.buckminster.aggregator.InstallationUnit#getVersion()
   * @see #getInstallationUnit()
   * @generated
   */
  EAttribute getInstallationUnit_Version();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.buckminster.aggregator.InstallationUnit#getRepository <em>Repository</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Repository</em>'.
   * @see org.eclipse.buckminster.aggregator.InstallationUnit#getRepository()
   * @see #getInstallationUnit()
   * @generated
   */
  EReference getInstallationUnit_Repository();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.InstallationUnit#isMapped <em>Mapped</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mapped</em>'.
   * @see org.eclipse.buckminster.aggregator.InstallationUnit#isMapped()
   * @see #getInstallationUnit()
   * @generated
   */
  EAttribute getInstallationUnit_Mapped();

  /**
   * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Product <em>Product</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Product</em>'.
   * @see org.eclipse.buckminster.aggregator.Product
   * @generated
   */
  EClass getProduct();

  /**
   * Returns the meta object for enum '{@link org.eclipse.buckminster.aggregator.AggregateType <em>Aggregate Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Aggregate Type</em>'.
   * @see org.eclipse.buckminster.aggregator.AggregateType
   * @generated
   */
  EEnum getAggregateType();

  /**
   * Returns the meta object for enum '{@link org.eclipse.buckminster.aggregator.OperatingSystem <em>Operating System</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Operating System</em>'.
   * @see org.eclipse.buckminster.aggregator.OperatingSystem
   * @generated
   */
  EEnum getOperatingSystem();

  /**
   * Returns the meta object for enum '{@link org.eclipse.buckminster.aggregator.WindowSystem <em>Window System</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Window System</em>'.
   * @see org.eclipse.buckminster.aggregator.WindowSystem
   * @generated
   */
  EEnum getWindowSystem();

  /**
   * Returns the meta object for enum '{@link org.eclipse.buckminster.aggregator.Architecture <em>Architecture</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Architecture</em>'.
   * @see org.eclipse.buckminster.aggregator.Architecture
   * @generated
   */
  EEnum getArchitecture();

  /**
   * Returns the meta object for enum '{@link org.eclipse.buckminster.aggregator.ArchiveFormat <em>Archive Format</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Archive Format</em>'.
   * @see org.eclipse.buckminster.aggregator.ArchiveFormat
   * @generated
   */
  EEnum getArchiveFormat();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  AggregatorFactory getAggregatorFactory();

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
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl <em>Aggregator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorImpl
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getAggregator()
     * @generated
     */
    EClass AGGREGATOR = eINSTANCE.getAggregator();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AGGREGATOR__TYPE = eINSTANCE.getAggregator_Type();

    /**
     * The meta object literal for the '<em><b>Build Root</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AGGREGATOR__BUILD_ROOT = eINSTANCE.getAggregator_BuildRoot();

    /**
     * The meta object literal for the '<em><b>Configurations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AGGREGATOR__CONFIGURATIONS = eINSTANCE.getAggregator_Configurations();

    /**
     * The meta object literal for the '<em><b>Contributions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AGGREGATOR__CONTRIBUTIONS = eINSTANCE.getAggregator_Contributions();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AGGREGATOR__LABEL = eINSTANCE.getAggregator_Label();

    /**
     * The meta object literal for the '<em><b>Buildmaster</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AGGREGATOR__BUILDMASTER = eINSTANCE.getAggregator_Buildmaster();

    /**
     * The meta object literal for the '<em><b>Sendmail</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AGGREGATOR__SENDMAIL = eINSTANCE.getAggregator_Sendmail();

    /**
     * The meta object literal for the '<em><b>Contacts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AGGREGATOR__CONTACTS = eINSTANCE.getAggregator_Contacts();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.RepositoryImpl <em>Repository</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.impl.RepositoryImpl
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getRepository()
     * @generated
     */
    EClass REPOSITORY = eINSTANCE.getRepository();

    /**
     * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REPOSITORY__LOCATION = eINSTANCE.getRepository_Location();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REPOSITORY__LABEL = eINSTANCE.getRepository_Label();

    /**
     * The meta object literal for the '<em><b>Products</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REPOSITORY__PRODUCTS = eINSTANCE.getRepository_Products();

    /**
     * The meta object literal for the '<em><b>Bundles</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REPOSITORY__BUNDLES = eINSTANCE.getRepository_Bundles();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REPOSITORY__FEATURES = eINSTANCE.getRepository_Features();

    /**
     * The meta object literal for the '<em><b>Categories</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REPOSITORY__CATEGORIES = eINSTANCE.getRepository_Categories();

    /**
     * The meta object literal for the '<em><b>Mapped</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REPOSITORY__MAPPED = eINSTANCE.getRepository_Mapped();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.ConfigurationImpl <em>Configuration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.impl.ConfigurationImpl
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getConfiguration()
     * @generated
     */
    EClass CONFIGURATION = eINSTANCE.getConfiguration();

    /**
     * The meta object literal for the '<em><b>Operating System</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONFIGURATION__OPERATING_SYSTEM = eINSTANCE.getConfiguration_OperatingSystem();

    /**
     * The meta object literal for the '<em><b>Window System</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONFIGURATION__WINDOW_SYSTEM = eINSTANCE.getConfiguration_WindowSystem();

    /**
     * The meta object literal for the '<em><b>Architecture</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONFIGURATION__ARCHITECTURE = eINSTANCE.getConfiguration_Architecture();

    /**
     * The meta object literal for the '<em><b>Archive Format</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONFIGURATION__ARCHIVE_FORMAT = eINSTANCE.getConfiguration_ArchiveFormat();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.CategoryImpl <em>Category</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.impl.CategoryImpl
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getCategory()
     * @generated
     */
    EClass CATEGORY = eINSTANCE.getCategory();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATEGORY__IDENTIFIER = eINSTANCE.getCategory_Identifier();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATEGORY__LABEL = eINSTANCE.getCategory_Label();

    /**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATEGORY__DESCRIPTION = eINSTANCE.getCategory_Description();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CATEGORY__FEATURES = eINSTANCE.getCategory_Features();

    /**
     * The meta object literal for the '<em><b>Mapped</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATEGORY__MAPPED = eINSTANCE.getCategory_Mapped();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl <em>Contribution</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.impl.ContributionImpl
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getContribution()
     * @generated
     */
    EClass CONTRIBUTION = eINSTANCE.getContribution();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONTRIBUTION__LABEL = eINSTANCE.getContribution_Label();

    /**
     * The meta object literal for the '<em><b>Repositories</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTRIBUTION__REPOSITORIES = eINSTANCE.getContribution_Repositories();

    /**
     * The meta object literal for the '<em><b>Contacts</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTRIBUTION__CONTACTS = eINSTANCE.getContribution_Contacts();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.ContactImpl <em>Contact</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.impl.ContactImpl
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getContact()
     * @generated
     */
    EClass CONTACT = eINSTANCE.getContact();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONTACT__NAME = eINSTANCE.getContact_Name();

    /**
     * The meta object literal for the '<em><b>Email</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONTACT__EMAIL = eINSTANCE.getContact_Email();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.FeatureImpl <em>Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.impl.FeatureImpl
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getFeature()
     * @generated
     */
    EClass FEATURE = eINSTANCE.getFeature();

    /**
     * The meta object literal for the '<em><b>Category</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE__CATEGORY = eINSTANCE.getFeature_Category();

    /**
     * The meta object literal for the '<em><b>In Product</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE__IN_PRODUCT = eINSTANCE.getFeature_InProduct();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.BundleImpl <em>Bundle</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.impl.BundleImpl
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getBundle()
     * @generated
     */
    EClass BUNDLE = eINSTANCE.getBundle();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.InstallationUnitImpl <em>Installation Unit</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.impl.InstallationUnitImpl
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getInstallationUnit()
     * @generated
     */
    EClass INSTALLATION_UNIT = eINSTANCE.getInstallationUnit();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INSTALLATION_UNIT__ID = eINSTANCE.getInstallationUnit_Id();

    /**
     * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INSTALLATION_UNIT__VERSION = eINSTANCE.getInstallationUnit_Version();

    /**
     * The meta object literal for the '<em><b>Repository</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INSTALLATION_UNIT__REPOSITORY = eINSTANCE.getInstallationUnit_Repository();

    /**
     * The meta object literal for the '<em><b>Mapped</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INSTALLATION_UNIT__MAPPED = eINSTANCE.getInstallationUnit_Mapped();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.ProductImpl <em>Product</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.impl.ProductImpl
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getProduct()
     * @generated
     */
    EClass PRODUCT = eINSTANCE.getProduct();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.AggregateType <em>Aggregate Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.AggregateType
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getAggregateType()
     * @generated
     */
    EEnum AGGREGATE_TYPE = eINSTANCE.getAggregateType();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.OperatingSystem <em>Operating System</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.OperatingSystem
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getOperatingSystem()
     * @generated
     */
    EEnum OPERATING_SYSTEM = eINSTANCE.getOperatingSystem();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.WindowSystem <em>Window System</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.WindowSystem
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getWindowSystem()
     * @generated
     */
    EEnum WINDOW_SYSTEM = eINSTANCE.getWindowSystem();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.Architecture <em>Architecture</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.Architecture
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getArchitecture()
     * @generated
     */
    EEnum ARCHITECTURE = eINSTANCE.getArchitecture();

    /**
     * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.ArchiveFormat <em>Archive Format</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.buckminster.aggregator.ArchiveFormat
     * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getArchiveFormat()
     * @generated
     */
    EEnum ARCHIVE_FORMAT = eINSTANCE.getArchiveFormat();

  }

} //AggregatorPackage

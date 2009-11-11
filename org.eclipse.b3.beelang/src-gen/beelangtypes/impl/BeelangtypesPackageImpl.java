/**
 * <copyright>
 * </copyright>
 *
 */
package beelangtypes.impl;

import beelangtypes.BeelangtypesFactory;
import beelangtypes.BeelangtypesPackage;

import org.eclipse.b3.RadixInteger;
import org.eclipse.b3.RegularExpression;

import org.eclipse.b3.beeLang.BeeLangPackage;

import org.eclipse.b3.beeLang.impl.BeeLangPackageImpl;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BeelangtypesPackageImpl extends EPackageImpl implements BeelangtypesPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType radixIntegerEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType regularExpressionEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType uriEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType versionRangeEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType versionEDataType = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see beelangtypes.BeelangtypesPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private BeelangtypesPackageImpl()
  {
    super(eNS_URI, BeelangtypesFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link BeelangtypesPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static BeelangtypesPackage init()
  {
    if (isInited) return (BeelangtypesPackage)EPackage.Registry.INSTANCE.getEPackage(BeelangtypesPackage.eNS_URI);

    // Obtain or create and register package
    BeelangtypesPackageImpl theBeelangtypesPackage = (BeelangtypesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BeelangtypesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BeelangtypesPackageImpl());

    isInited = true;

    // Obtain or create and register interdependencies
    BeeLangPackageImpl theBeeLangPackage = (BeeLangPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BeeLangPackage.eNS_URI) instanceof BeeLangPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BeeLangPackage.eNS_URI) : BeeLangPackage.eINSTANCE);

    // Create package meta-data objects
    theBeelangtypesPackage.createPackageContents();
    theBeeLangPackage.createPackageContents();

    // Initialize created meta-data
    theBeelangtypesPackage.initializePackageContents();
    theBeeLangPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theBeelangtypesPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(BeelangtypesPackage.eNS_URI, theBeelangtypesPackage);
    return theBeelangtypesPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getRadixInteger()
  {
    return radixIntegerEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getRegularExpression()
  {
    return regularExpressionEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getURI()
  {
    return uriEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getVersionRange()
  {
    return versionRangeEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getVersion()
  {
    return versionEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BeelangtypesFactory getBeelangtypesFactory()
  {
    return (BeelangtypesFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create data types
    radixIntegerEDataType = createEDataType(RADIX_INTEGER);
    regularExpressionEDataType = createEDataType(REGULAR_EXPRESSION);
    uriEDataType = createEDataType(URI);
    versionRangeEDataType = createEDataType(VERSION_RANGE);
    versionEDataType = createEDataType(VERSION);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Initialize data types
    initEDataType(radixIntegerEDataType, RadixInteger.class, "RadixInteger", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(regularExpressionEDataType, RegularExpression.class, "RegularExpression", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(uriEDataType, java.net.URI.class, "URI", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(versionRangeEDataType, VersionRange.class, "VersionRange", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(versionEDataType, Version.class, "Version", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //BeelangtypesPackageImpl

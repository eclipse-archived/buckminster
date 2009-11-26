/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import java.util.Collection;

import java.util.regex.Pattern;
import org.eclipse.buckminster.cspec.Action;
import org.eclipse.buckminster.cspec.ActionAttribute;
import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.cspec.PathGroup;
import org.eclipse.buckminster.cspec.UpToDatePolicy;

import org.eclipse.buckminster.model.common.PropertyConstant;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Action</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ActionImpl#getProperties <em>Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ActionImpl#getActorProperties <em>Actor Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ActionImpl#getActor <em>Actor</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ActionImpl#getProduct <em>Product</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ActionImpl#getProducts <em>Products</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ActionImpl#getPrerequisitesAlias <em>Prerequisites Alias</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ActionImpl#getPrerequisitesRebase <em>Prerequisites Rebase</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ActionImpl#getProductAlias <em>Product Alias</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ActionImpl#getUpToDatePolicy <em>Up To Date Policy</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ActionImpl#getProductFileCount <em>Product File Count</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ActionImpl#getPattern <em>Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ActionImpl#getReplacement <em>Replacement</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ActionImpl extends GroupImpl implements Action
{
	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyConstant> properties;

	/**
	 * The cached value of the '{@link #getActorProperties() <em>Actor Properties</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getActorProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyConstant> actorProperties;

	/**
	 * The default value of the '{@link #getActor() <em>Actor</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getActor()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActor() <em>Actor</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getActor()
	 * @generated
	 * @ordered
	 */
	protected String actor = ACTOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProduct() <em>Product</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getProduct()
	 * @generated
	 * @ordered
	 */
	protected PathGroup product;

	/**
	 * The cached value of the '{@link #getProducts() <em>Products</em>}' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getProducts()
	 * @generated
	 * @ordered
	 */
	protected EList<ActionAttribute> products;

	/**
	 * The default value of the '{@link #getPrerequisitesAlias() <em>Prerequisites Alias</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPrerequisitesAlias()
	 * @generated
	 * @ordered
	 */
	protected static final String PREREQUISITES_ALIAS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrerequisitesAlias() <em>Prerequisites Alias</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPrerequisitesAlias()
	 * @generated
	 * @ordered
	 */
	protected String prerequisitesAlias = PREREQUISITES_ALIAS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrerequisitesRebase() <em>Prerequisites Rebase</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPrerequisitesRebase()
	 * @generated
	 * @ordered
	 */
	protected static final IPath PREREQUISITES_REBASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrerequisitesRebase() <em>Prerequisites Rebase</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPrerequisitesRebase()
	 * @generated
	 * @ordered
	 */
	protected IPath prerequisitesRebase = PREREQUISITES_REBASE_EDEFAULT;

	/**
	 * The default value of the '{@link #getProductAlias() <em>Product Alias</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getProductAlias()
	 * @generated
	 * @ordered
	 */
	protected static final String PRODUCT_ALIAS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProductAlias() <em>Product Alias</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getProductAlias()
	 * @generated
	 * @ordered
	 */
	protected String productAlias = PRODUCT_ALIAS_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpToDatePolicy() <em>Up To Date Policy</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getUpToDatePolicy()
	 * @generated
	 * @ordered
	 */
	protected static final UpToDatePolicy UP_TO_DATE_POLICY_EDEFAULT = UpToDatePolicy.DEFAULT;

	/**
	 * The cached value of the '{@link #getUpToDatePolicy() <em>Up To Date Policy</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getUpToDatePolicy()
	 * @generated
	 * @ordered
	 */
	protected UpToDatePolicy upToDatePolicy = UP_TO_DATE_POLICY_EDEFAULT;

	/**
	 * The default value of the '{@link #getProductFileCount() <em>Product File Count</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProductFileCount()
	 * @generated
	 * @ordered
	 */
	protected static final int PRODUCT_FILE_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getProductFileCount() <em>Product File Count</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProductFileCount()
	 * @generated
	 * @ordered
	 */
	protected int productFileCount = PRODUCT_FILE_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getPattern() <em>Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected static final Pattern PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPattern() <em>Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected Pattern pattern = PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getReplacement() <em>Replacement</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getReplacement()
	 * @generated
	 * @ordered
	 */
	protected static final String REPLACEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReplacement() <em>Replacement</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getReplacement()
	 * @generated
	 * @ordered
	 */
	protected String replacement = REPLACEMENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ActionImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetProduct(PathGroup newProduct, NotificationChain msgs)
	{
		PathGroup oldProduct = product;
		product = newProduct;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					CspecPackage.ACTION__PRODUCT, oldProduct, newProduct);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch(featureID)
		{
		case CspecPackage.ACTION__PROPERTIES:
			return getProperties();
		case CspecPackage.ACTION__ACTOR_PROPERTIES:
			return getActorProperties();
		case CspecPackage.ACTION__ACTOR:
			return getActor();
		case CspecPackage.ACTION__PRODUCT:
			return getProduct();
		case CspecPackage.ACTION__PRODUCTS:
			return getProducts();
		case CspecPackage.ACTION__PREREQUISITES_ALIAS:
			return getPrerequisitesAlias();
		case CspecPackage.ACTION__PREREQUISITES_REBASE:
			return getPrerequisitesRebase();
		case CspecPackage.ACTION__PRODUCT_ALIAS:
			return getProductAlias();
		case CspecPackage.ACTION__UP_TO_DATE_POLICY:
			return getUpToDatePolicy();
		case CspecPackage.ACTION__PRODUCT_FILE_COUNT:
			return getProductFileCount();
		case CspecPackage.ACTION__PATTERN:
			return getPattern();
		case CspecPackage.ACTION__REPLACEMENT:
			return getReplacement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch(featureID)
		{
		case CspecPackage.ACTION__PRODUCTS:
			return ((InternalEList<InternalEObject>)(InternalEList<?>)getProducts()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch(featureID)
		{
		case CspecPackage.ACTION__PROPERTIES:
			return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
		case CspecPackage.ACTION__ACTOR_PROPERTIES:
			return ((InternalEList<?>)getActorProperties()).basicRemove(otherEnd, msgs);
		case CspecPackage.ACTION__PRODUCT:
			return basicSetProduct(null, msgs);
		case CspecPackage.ACTION__PRODUCTS:
			return ((InternalEList<?>)getProducts()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch(featureID)
		{
		case CspecPackage.ACTION__PROPERTIES:
			return properties != null && !properties.isEmpty();
		case CspecPackage.ACTION__ACTOR_PROPERTIES:
			return actorProperties != null && !actorProperties.isEmpty();
		case CspecPackage.ACTION__ACTOR:
			return ACTOR_EDEFAULT == null
					? actor != null
					: !ACTOR_EDEFAULT.equals(actor);
		case CspecPackage.ACTION__PRODUCT:
			return product != null;
		case CspecPackage.ACTION__PRODUCTS:
			return products != null && !products.isEmpty();
		case CspecPackage.ACTION__PREREQUISITES_ALIAS:
			return PREREQUISITES_ALIAS_EDEFAULT == null
					? prerequisitesAlias != null
					: !PREREQUISITES_ALIAS_EDEFAULT.equals(prerequisitesAlias);
		case CspecPackage.ACTION__PREREQUISITES_REBASE:
			return PREREQUISITES_REBASE_EDEFAULT == null
					? prerequisitesRebase != null
					: !PREREQUISITES_REBASE_EDEFAULT.equals(prerequisitesRebase);
		case CspecPackage.ACTION__PRODUCT_ALIAS:
			return PRODUCT_ALIAS_EDEFAULT == null
					? productAlias != null
					: !PRODUCT_ALIAS_EDEFAULT.equals(productAlias);
		case CspecPackage.ACTION__UP_TO_DATE_POLICY:
			return upToDatePolicy != UP_TO_DATE_POLICY_EDEFAULT;
		case CspecPackage.ACTION__PRODUCT_FILE_COUNT:
			return productFileCount != PRODUCT_FILE_COUNT_EDEFAULT;
		case CspecPackage.ACTION__PATTERN:
			return PATTERN_EDEFAULT == null
					? pattern != null
					: !PATTERN_EDEFAULT.equals(pattern);
		case CspecPackage.ACTION__REPLACEMENT:
			return REPLACEMENT_EDEFAULT == null
					? replacement != null
					: !REPLACEMENT_EDEFAULT.equals(replacement);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch(featureID)
		{
		case CspecPackage.ACTION__PROPERTIES:
			getProperties().clear();
			getProperties().addAll((Collection<? extends PropertyConstant>)newValue);
			return;
		case CspecPackage.ACTION__ACTOR_PROPERTIES:
			getActorProperties().clear();
			getActorProperties().addAll((Collection<? extends PropertyConstant>)newValue);
			return;
		case CspecPackage.ACTION__ACTOR:
			setActor((String)newValue);
			return;
		case CspecPackage.ACTION__PRODUCT:
			setProduct((PathGroup)newValue);
			return;
		case CspecPackage.ACTION__PRODUCTS:
			getProducts().clear();
			getProducts().addAll((Collection<? extends ActionAttribute>)newValue);
			return;
		case CspecPackage.ACTION__PREREQUISITES_ALIAS:
			setPrerequisitesAlias((String)newValue);
			return;
		case CspecPackage.ACTION__PREREQUISITES_REBASE:
			setPrerequisitesRebase((IPath)newValue);
			return;
		case CspecPackage.ACTION__PRODUCT_ALIAS:
			setProductAlias((String)newValue);
			return;
		case CspecPackage.ACTION__UP_TO_DATE_POLICY:
			setUpToDatePolicy((UpToDatePolicy)newValue);
			return;
		case CspecPackage.ACTION__PRODUCT_FILE_COUNT:
			setProductFileCount((Integer)newValue);
			return;
		case CspecPackage.ACTION__PATTERN:
			setPattern((Pattern)newValue);
			return;
		case CspecPackage.ACTION__REPLACEMENT:
			setReplacement((String)newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch(featureID)
		{
		case CspecPackage.ACTION__PROPERTIES:
			getProperties().clear();
			return;
		case CspecPackage.ACTION__ACTOR_PROPERTIES:
			getActorProperties().clear();
			return;
		case CspecPackage.ACTION__ACTOR:
			setActor(ACTOR_EDEFAULT);
			return;
		case CspecPackage.ACTION__PRODUCT:
			setProduct((PathGroup)null);
			return;
		case CspecPackage.ACTION__PRODUCTS:
			getProducts().clear();
			return;
		case CspecPackage.ACTION__PREREQUISITES_ALIAS:
			setPrerequisitesAlias(PREREQUISITES_ALIAS_EDEFAULT);
			return;
		case CspecPackage.ACTION__PREREQUISITES_REBASE:
			setPrerequisitesRebase(PREREQUISITES_REBASE_EDEFAULT);
			return;
		case CspecPackage.ACTION__PRODUCT_ALIAS:
			setProductAlias(PRODUCT_ALIAS_EDEFAULT);
			return;
		case CspecPackage.ACTION__UP_TO_DATE_POLICY:
			setUpToDatePolicy(UP_TO_DATE_POLICY_EDEFAULT);
			return;
		case CspecPackage.ACTION__PRODUCT_FILE_COUNT:
			setProductFileCount(PRODUCT_FILE_COUNT_EDEFAULT);
			return;
		case CspecPackage.ACTION__PATTERN:
			setPattern(PATTERN_EDEFAULT);
			return;
		case CspecPackage.ACTION__REPLACEMENT:
			setReplacement(REPLACEMENT_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getActor()
	{
		return actor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<PropertyConstant> getActorProperties()
	{
		if(actorProperties == null)
		{
			actorProperties = new EObjectContainmentEList<PropertyConstant>(PropertyConstant.class, this,
					CspecPackage.ACTION__ACTOR_PROPERTIES);
		}
		return actorProperties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Pattern getPattern()
	{
		return pattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getPrerequisitesAlias()
	{
		return prerequisitesAlias;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IPath getPrerequisitesRebase()
	{
		return prerequisitesRebase;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PathGroup getProduct()
	{
		return product;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getProductAlias()
	{
		return productAlias;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getProductFileCount()
	{
		return productFileCount;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<ActionAttribute> getProducts()
	{
		if(products == null)
		{
			products = new EObjectWithInverseResolvingEList<ActionAttribute>(ActionAttribute.class, this,
					CspecPackage.ACTION__PRODUCTS, CspecPackage.ACTION_ATTRIBUTE__ACTION);
		}
		return products;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<PropertyConstant> getProperties()
	{
		if(properties == null)
		{
			properties = new EObjectContainmentEList<PropertyConstant>(PropertyConstant.class, this,
					CspecPackage.ACTION__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getReplacement()
	{
		return replacement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public UpToDatePolicy getUpToDatePolicy()
	{
		return upToDatePolicy;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setActor(String newActor)
	{
		String oldActor = actor;
		actor = newActor;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ACTION__ACTOR, oldActor, actor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPattern(Pattern newPattern)
	{
		Pattern oldPattern = pattern;
		pattern = newPattern;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ACTION__PATTERN, oldPattern, pattern));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPrerequisitesAlias(String newPrerequisitesAlias)
	{
		String oldPrerequisitesAlias = prerequisitesAlias;
		prerequisitesAlias = newPrerequisitesAlias;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ACTION__PREREQUISITES_ALIAS,
					oldPrerequisitesAlias, prerequisitesAlias));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPrerequisitesRebase(IPath newPrerequisitesRebase)
	{
		IPath oldPrerequisitesRebase = prerequisitesRebase;
		prerequisitesRebase = newPrerequisitesRebase;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ACTION__PREREQUISITES_REBASE,
					oldPrerequisitesRebase, prerequisitesRebase));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setProduct(PathGroup newProduct)
	{
		if(newProduct != product)
		{
			NotificationChain msgs = null;
			if(product != null)
				msgs = ((InternalEObject)product).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- CspecPackage.ACTION__PRODUCT, null, msgs);
			if(newProduct != null)
				msgs = ((InternalEObject)newProduct).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- CspecPackage.ACTION__PRODUCT, null, msgs);
			msgs = basicSetProduct(newProduct, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ACTION__PRODUCT, newProduct, newProduct));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setProductAlias(String newProductAlias)
	{
		String oldProductAlias = productAlias;
		productAlias = newProductAlias;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ACTION__PRODUCT_ALIAS, oldProductAlias,
					productAlias));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setProductFileCount(int newProductFileCount)
	{
		int oldProductFileCount = productFileCount;
		productFileCount = newProductFileCount;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ACTION__PRODUCT_FILE_COUNT,
					oldProductFileCount, productFileCount));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setReplacement(String newReplacement)
	{
		String oldReplacement = replacement;
		replacement = newReplacement;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ACTION__REPLACEMENT, oldReplacement,
					replacement));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setUpToDatePolicy(UpToDatePolicy newUpToDatePolicy)
	{
		UpToDatePolicy oldUpToDatePolicy = upToDatePolicy;
		upToDatePolicy = newUpToDatePolicy == null
				? UP_TO_DATE_POLICY_EDEFAULT
				: newUpToDatePolicy;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ACTION__UP_TO_DATE_POLICY,
					oldUpToDatePolicy, upToDatePolicy));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString()
	{
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (actor: ");
		result.append(actor);
		result.append(", prerequisitesAlias: ");
		result.append(prerequisitesAlias);
		result.append(", prerequisitesRebase: ");
		result.append(prerequisitesRebase);
		result.append(", productAlias: ");
		result.append(productAlias);
		result.append(", upToDatePolicy: ");
		result.append(upToDatePolicy);
		result.append(", productFileCount: ");
		result.append(productFileCount);
		result.append(", pattern: ");
		result.append(pattern);
		result.append(", replacement: ");
		result.append(replacement);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return CspecPackage.Literals.ACTION;
	}

} // ActionImpl

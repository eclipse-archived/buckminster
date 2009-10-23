/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom.impl;

import org.eclipse.buckminster.aggregator.engine.maven.pom.MailingList;
import org.eclipse.buckminster.aggregator.engine.maven.pom.OtherArchivesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Mailing List</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.MailingListImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.MailingListImpl#getSubscribe <em>Subscribe</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.MailingListImpl#getUnsubscribe <em>Unsubscribe
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.MailingListImpl#getPost <em>Post</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.MailingListImpl#getArchive <em>Archive</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.MailingListImpl#getOtherArchives <em>Other
 * Archives</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MailingListImpl extends EObjectImpl implements MailingList
{
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSubscribe() <em>Subscribe</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getSubscribe()
	 * @generated
	 * @ordered
	 */
	protected static final String SUBSCRIBE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubscribe() <em>Subscribe</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getSubscribe()
	 * @generated
	 * @ordered
	 */
	protected String subscribe = SUBSCRIBE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUnsubscribe() <em>Unsubscribe</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getUnsubscribe()
	 * @generated
	 * @ordered
	 */
	protected static final String UNSUBSCRIBE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUnsubscribe() <em>Unsubscribe</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getUnsubscribe()
	 * @generated
	 * @ordered
	 */
	protected String unsubscribe = UNSUBSCRIBE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPost() <em>Post</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getPost()
	 * @generated
	 * @ordered
	 */
	protected static final String POST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPost() <em>Post</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getPost()
	 * @generated
	 * @ordered
	 */
	protected String post = POST_EDEFAULT;

	/**
	 * The default value of the '{@link #getArchive() <em>Archive</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getArchive()
	 * @generated
	 * @ordered
	 */
	protected static final String ARCHIVE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArchive() <em>Archive</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getArchive()
	 * @generated
	 * @ordered
	 */
	protected String archive = ARCHIVE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOtherArchives() <em>Other Archives</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getOtherArchives()
	 * @generated
	 * @ordered
	 */
	protected OtherArchivesType otherArchives;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MailingListImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetOtherArchives(OtherArchivesType newOtherArchives, NotificationChain msgs)
	{
		OtherArchivesType oldOtherArchives = otherArchives;
		otherArchives = newOtherArchives;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.MAILING_LIST__OTHER_ARCHIVES, oldOtherArchives, newOtherArchives);
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
		case PomPackage.MAILING_LIST__NAME:
			return getName();
		case PomPackage.MAILING_LIST__SUBSCRIBE:
			return getSubscribe();
		case PomPackage.MAILING_LIST__UNSUBSCRIBE:
			return getUnsubscribe();
		case PomPackage.MAILING_LIST__POST:
			return getPost();
		case PomPackage.MAILING_LIST__ARCHIVE:
			return getArchive();
		case PomPackage.MAILING_LIST__OTHER_ARCHIVES:
			return getOtherArchives();
		}
		return super.eGet(featureID, resolve, coreType);
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
		case PomPackage.MAILING_LIST__OTHER_ARCHIVES:
			return basicSetOtherArchives(null, msgs);
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
		case PomPackage.MAILING_LIST__NAME:
			return NAME_EDEFAULT == null
					? name != null
					: !NAME_EDEFAULT.equals(name);
		case PomPackage.MAILING_LIST__SUBSCRIBE:
			return SUBSCRIBE_EDEFAULT == null
					? subscribe != null
					: !SUBSCRIBE_EDEFAULT.equals(subscribe);
		case PomPackage.MAILING_LIST__UNSUBSCRIBE:
			return UNSUBSCRIBE_EDEFAULT == null
					? unsubscribe != null
					: !UNSUBSCRIBE_EDEFAULT.equals(unsubscribe);
		case PomPackage.MAILING_LIST__POST:
			return POST_EDEFAULT == null
					? post != null
					: !POST_EDEFAULT.equals(post);
		case PomPackage.MAILING_LIST__ARCHIVE:
			return ARCHIVE_EDEFAULT == null
					? archive != null
					: !ARCHIVE_EDEFAULT.equals(archive);
		case PomPackage.MAILING_LIST__OTHER_ARCHIVES:
			return otherArchives != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch(featureID)
		{
		case PomPackage.MAILING_LIST__NAME:
			setName((String)newValue);
			return;
		case PomPackage.MAILING_LIST__SUBSCRIBE:
			setSubscribe((String)newValue);
			return;
		case PomPackage.MAILING_LIST__UNSUBSCRIBE:
			setUnsubscribe((String)newValue);
			return;
		case PomPackage.MAILING_LIST__POST:
			setPost((String)newValue);
			return;
		case PomPackage.MAILING_LIST__ARCHIVE:
			setArchive((String)newValue);
			return;
		case PomPackage.MAILING_LIST__OTHER_ARCHIVES:
			setOtherArchives((OtherArchivesType)newValue);
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
		case PomPackage.MAILING_LIST__NAME:
			setName(NAME_EDEFAULT);
			return;
		case PomPackage.MAILING_LIST__SUBSCRIBE:
			setSubscribe(SUBSCRIBE_EDEFAULT);
			return;
		case PomPackage.MAILING_LIST__UNSUBSCRIBE:
			setUnsubscribe(UNSUBSCRIBE_EDEFAULT);
			return;
		case PomPackage.MAILING_LIST__POST:
			setPost(POST_EDEFAULT);
			return;
		case PomPackage.MAILING_LIST__ARCHIVE:
			setArchive(ARCHIVE_EDEFAULT);
			return;
		case PomPackage.MAILING_LIST__OTHER_ARCHIVES:
			setOtherArchives((OtherArchivesType)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getArchive()
	{
		return archive;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OtherArchivesType getOtherArchives()
	{
		return otherArchives;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getPost()
	{
		return post;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getSubscribe()
	{
		return subscribe;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getUnsubscribe()
	{
		return unsubscribe;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setArchive(String newArchive)
	{
		String oldArchive = archive;
		archive = newArchive;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.MAILING_LIST__ARCHIVE, oldArchive, archive));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.MAILING_LIST__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setOtherArchives(OtherArchivesType newOtherArchives)
	{
		if(newOtherArchives != otherArchives)
		{
			NotificationChain msgs = null;
			if(otherArchives != null)
				msgs = ((InternalEObject)otherArchives).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.MAILING_LIST__OTHER_ARCHIVES, null, msgs);
			if(newOtherArchives != null)
				msgs = ((InternalEObject)newOtherArchives).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.MAILING_LIST__OTHER_ARCHIVES, null, msgs);
			msgs = basicSetOtherArchives(newOtherArchives, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.MAILING_LIST__OTHER_ARCHIVES,
					newOtherArchives, newOtherArchives));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPost(String newPost)
	{
		String oldPost = post;
		post = newPost;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.MAILING_LIST__POST, oldPost, post));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSubscribe(String newSubscribe)
	{
		String oldSubscribe = subscribe;
		subscribe = newSubscribe;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.MAILING_LIST__SUBSCRIBE, oldSubscribe,
					subscribe));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setUnsubscribe(String newUnsubscribe)
	{
		String oldUnsubscribe = unsubscribe;
		unsubscribe = newUnsubscribe;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.MAILING_LIST__UNSUBSCRIBE, oldUnsubscribe,
					unsubscribe));
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
		result.append(" (name: ");
		result.append(name);
		result.append(", subscribe: ");
		result.append(subscribe);
		result.append(", unsubscribe: ");
		result.append(unsubscribe);
		result.append(", post: ");
		result.append(post);
		result.append(", archive: ");
		result.append(archive);
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
		return PomPackage.Literals.MAILING_LIST;
	}

} // MailingListImpl

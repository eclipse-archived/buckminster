/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.Category#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.Category#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.Category#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.Category#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.Category#isMapped <em>Mapped</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getCategory()
 * @model
 * @generated
 */
public interface Category extends EObject
{
  /**
   * Returns the value of the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identifier</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identifier</em>' attribute.
   * @see #setIdentifier(String)
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getCategory_Identifier()
   * @model
   * @generated
   */
  String getIdentifier();

  /**
   * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Category#getIdentifier <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Identifier</em>' attribute.
   * @see #getIdentifier()
   * @generated
   */
  void setIdentifier(String value);

  /**
   * Returns the value of the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Label</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Label</em>' attribute.
   * @see #setLabel(String)
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getCategory_Label()
   * @model
   * @generated
   */
  String getLabel();

  /**
   * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Category#getLabel <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' attribute.
   * @see #getLabel()
   * @generated
   */
  void setLabel(String value);

  /**
   * Returns the value of the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' attribute.
   * @see #setDescription(String)
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getCategory_Description()
   * @model
   * @generated
   */
  String getDescription();

  /**
   * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Category#getDescription <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' attribute.
   * @see #getDescription()
   * @generated
   */
  void setDescription(String value);

  /**
   * Returns the value of the '<em><b>Features</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.buckminster.aggregator.Feature}.
   * It is bidirectional and its opposite is '{@link org.eclipse.buckminster.aggregator.Feature#getCategory <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Features</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Features</em>' reference list.
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getCategory_Features()
   * @see org.eclipse.buckminster.aggregator.Feature#getCategory
   * @model opposite="category"
   * @generated
   */
  EList<Feature> getFeatures();

  /**
   * Returns the value of the '<em><b>Mapped</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mapped</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mapped</em>' attribute.
   * @see #setMapped(boolean)
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getCategory_Mapped()
   * @model
   * @generated
   */
  boolean isMapped();

  /**
   * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Category#isMapped <em>Mapped</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mapped</em>' attribute.
   * @see #isMapped()
   * @generated
   */
  void setMapped(boolean value);

} // Category

/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Build Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.BuildMethod#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildMethod#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildMethod#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildMethod#getProvidedCapabilities <em>Provided Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildMethod#getPreCondition <em>Pre Condition</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildMethod#getPostCondition <em>Post Condition</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildMethod#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildMethod#getAdvice <em>Advice</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildMethod#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildMethod#getLayout <em>Layout</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildMethod#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildMethod()
 * @model
 * @generated
 */
public interface BuildMethod extends EObject
{
  /**
   * Returns the value of the '<em><b>Visibility</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.b3.beeLang.Visibility}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Visibility</em>' attribute.
   * @see org.eclipse.b3.beeLang.Visibility
   * @see #setVisibility(Visibility)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildMethod_Visibility()
   * @model
   * @generated
   */
  Visibility getVisibility();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuildMethod#getVisibility <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Visibility</em>' attribute.
   * @see org.eclipse.b3.beeLang.Visibility
   * @see #getVisibility()
   * @generated
   */
  void setVisibility(Visibility value);

  /**
   * Returns the value of the '<em><b>Execution Mode</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.b3.beeLang.ExecutionMode}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Execution Mode</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Execution Mode</em>' attribute.
   * @see org.eclipse.b3.beeLang.ExecutionMode
   * @see #setExecutionMode(ExecutionMode)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildMethod_ExecutionMode()
   * @model
   * @generated
   */
  ExecutionMode getExecutionMode();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuildMethod#getExecutionMode <em>Execution Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Execution Mode</em>' attribute.
   * @see org.eclipse.b3.beeLang.ExecutionMode
   * @see #getExecutionMode()
   * @generated
   */
  void setExecutionMode(ExecutionMode value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildMethod_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuildMethod#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Provided Capabilities</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.ProvidedCapability}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Provided Capabilities</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Provided Capabilities</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildMethod_ProvidedCapabilities()
   * @model containment="true"
   * @generated
   */
  EList<ProvidedCapability> getProvidedCapabilities();

  /**
   * Returns the value of the '<em><b>Pre Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pre Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pre Condition</em>' containment reference.
   * @see #setPreCondition(PreCondition)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildMethod_PreCondition()
   * @model containment="true"
   * @generated
   */
  PreCondition getPreCondition();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuildMethod#getPreCondition <em>Pre Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pre Condition</em>' containment reference.
   * @see #getPreCondition()
   * @generated
   */
  void setPreCondition(PreCondition value);

  /**
   * Returns the value of the '<em><b>Post Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Post Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Post Condition</em>' containment reference.
   * @see #setPostCondition(PostCondition)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildMethod_PostCondition()
   * @model containment="true"
   * @generated
   */
  PostCondition getPostCondition();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuildMethod#getPostCondition <em>Post Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Post Condition</em>' containment reference.
   * @see #getPostCondition()
   * @generated
   */
  void setPostCondition(PostCondition value);

  /**
   * Returns the value of the '<em><b>Properties</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Properties</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Properties</em>' containment reference.
   * @see #setProperties(CompoundPropertyOperation)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildMethod_Properties()
   * @model containment="true"
   * @generated
   */
  CompoundPropertyOperation getProperties();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuildMethod#getProperties <em>Properties</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Properties</em>' containment reference.
   * @see #getProperties()
   * @generated
   */
  void setProperties(CompoundPropertyOperation value);

  /**
   * Returns the value of the '<em><b>Advice</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Advice</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Advice</em>' containment reference.
   * @see #setAdvice(Advice)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildMethod_Advice()
   * @model containment="true"
   * @generated
   */
  Advice getAdvice();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuildMethod#getAdvice <em>Advice</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Advice</em>' containment reference.
   * @see #getAdvice()
   * @generated
   */
  void setAdvice(Advice value);

  /**
   * Returns the value of the '<em><b>Group</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group</em>' containment reference.
   * @see #setGroup(Group)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildMethod_Group()
   * @model containment="true"
   * @generated
   */
  Group getGroup();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuildMethod#getGroup <em>Group</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group</em>' containment reference.
   * @see #getGroup()
   * @generated
   */
  void setGroup(Group value);

  /**
   * Returns the value of the '<em><b>Layout</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Layout</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Layout</em>' containment reference.
   * @see #setLayout(Layout)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildMethod_Layout()
   * @model containment="true"
   * @generated
   */
  Layout getLayout();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuildMethod#getLayout <em>Layout</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Layout</em>' containment reference.
   * @see #getLayout()
   * @generated
   */
  void setLayout(Layout value);

  /**
   * Returns the value of the '<em><b>Statements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statements</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statements</em>' containment reference.
   * @see #setStatements(Statements)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildMethod_Statements()
   * @model containment="true"
   * @generated
   */
  Statements getStatements();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuildMethod#getStatements <em>Statements</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Statements</em>' containment reference.
   * @see #getStatements()
   * @generated
   */
  void setStatements(Statements value);

} // BuildMethod

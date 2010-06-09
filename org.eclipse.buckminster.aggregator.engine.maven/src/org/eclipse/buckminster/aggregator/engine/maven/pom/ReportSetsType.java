/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Report Sets Type</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.ReportSetsType#getReportSet <em>Report Set</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getReportSetsType()
 * @model extendedMetaData="name='reportSets_._type' kind='elementOnly'"
 * @generated
 */
public interface ReportSetsType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Report Set</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ReportSet}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Report Set</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Report Set</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getReportSetsType_ReportSet()
	 * @model containment="true" extendedMetaData="kind='element' name='reportSet' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ReportSet> getReportSet();

} // ReportSetsType

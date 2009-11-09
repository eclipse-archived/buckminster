/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2view;

import org.eclipse.buckminster.aggregator.ChildrenProvider;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Products</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Products#getProducts <em>Products</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getProducts()
 * @model
 * @generated
 */
public interface Products extends ChildrenProvider<Product>
{
	/**
	 * Returns the value of the '<em><b>Products</b></em>' reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.p2view.Product}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Products</em>' reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Products</em>' reference list.
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getProducts_Products()
	 * @model
	 * @generated
	 */
	EList<Product> getProducts();

} // Products

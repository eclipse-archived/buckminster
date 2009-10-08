/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

import java.util.Comparator;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Label Provider</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.LabelProvider#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getLabelProvider()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface LabelProvider extends EObject
{

	static class LabelProviderComparator implements Comparator<LabelProvider>
	{

		public int compare(LabelProvider lp1, LabelProvider lp2)
		{
			if(lp1 != null)
				if(lp2 == null)
					return 1;
				else
				{
					String label1 = lp1.getLabel();
					String label2 = lp2.getLabel();

					if(label1 != null)
						if(label2 == null)
							return 1;
						else
							return label1.toLowerCase().compareTo(label2.toLowerCase());
					else if(label2 != null)
						return -1;
					else
						return 0;
				}
			else if(lp2 != null)
				return -1;
			else
				return 0;
		}
	}

	static Comparator<LabelProvider> COMPARATOR = new LabelProviderComparator();

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getLabelProvider_Label()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.LabelProvider#getLabel <em>Label</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

} // LabelProvider

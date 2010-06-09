/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.presentation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
public class UIUtils
{

	public static TreePath createTreePath(EObject eObject)
	{
		if(eObject == null)
			return null;

		List<EObject> segments = new ArrayList<EObject>();

		segments.add(eObject);

		while(eObject.eContainer() != null)
		{
			eObject = eObject.eContainer();
			segments.add(0, eObject);
		}

		return new TreePath(segments.toArray());
	}

	public static String trimmedValue(String string)
	{
		String value = null;

		if(string != null)
		{
			value = string.trim();
			if(value.length() == 0)
				value = null;
		}
		return value;
	}

	public static String trimmedValue(Text text)
	{
		String value = null;
		if(text != null)
		{
			value = text.getText().trim();
			if(value.length() == 0)
				value = null;
		}
		return value;
	}

}

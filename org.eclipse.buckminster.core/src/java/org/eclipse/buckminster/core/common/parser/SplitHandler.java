/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.parser;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.AbstractSplit;
import org.eclipse.buckminster.core.common.model.GroupSplit;
import org.eclipse.buckminster.core.common.model.Split;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class SplitHandler extends ValueFilterHandler
{
	static final String TAG = AbstractSplit.TAG;

	public SplitHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		String pattern = this.getStringValue(attrs, AbstractSplit.ATTR_PATTERN);
		String style = getOptionalStringValue(attrs, AbstractSplit.ATTR_STYLE);
		if("group".equals(style))
			this.setValueHolder(new GroupSplit(pattern));
		else
		{
			if(style == null || "quoted".equals(style))
				pattern = Pattern.quote(pattern);
			this.setValueHolder(new Split(pattern, getOptionalIntValue(attrs, Split.ATTR_LIMIT, 0)));
		}
	}
}


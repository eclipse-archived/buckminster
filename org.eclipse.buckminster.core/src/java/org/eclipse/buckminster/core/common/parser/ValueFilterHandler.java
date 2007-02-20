/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.parser;

import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.common.model.ValueHolderFilter;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public abstract class ValueFilterHandler extends ValueHandler implements ChildPoppedListener
{
	public ValueFilterHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		// We have to create new instances on the fly here to avoid endless
		// recursion.
		//
		ChildHandler ch;
		if(ConstantHandler.TAG.equals(localName))
			ch = new ConstantHandler(this);
		else if(Format.TAG.equals(localName))
			ch = new FormatHandler(this);
		else if(PropertyRefHandler.TAG.equals(localName))
			ch = new PropertyRefHandler(this);
		else if(ReplaceHandler.TAG.equals(localName))
			ch = new ReplaceHandler(this);
		else if(SplitHandler.TAG.equals(localName))
			ch = new SplitHandler(this);
		else if(ToLowerHandler.TAG.equals(localName))
			ch = new ToLowerHandler(this);
		else if(ToUpperHandler.TAG.equals(localName))
			ch = new ToUpperHandler(this);
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public void childPopped(ChildHandler child)
	{
		((ValueHolderFilter)this.getValueHolder()).addValueHolder(((ValueHandler)child).getValueHolder());
	}
}


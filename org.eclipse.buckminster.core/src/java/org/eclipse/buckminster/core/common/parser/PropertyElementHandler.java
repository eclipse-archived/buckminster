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

import java.util.Map;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.common.model.ValueHolder;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class PropertyElementHandler extends PropertyHandler implements ChildPoppedListener {
	static final String TAG = "propertyElement"; //$NON-NLS-1$

	private ConstantHandler constantHandler;

	private FormatHandler formatHandler;

	private PropertyRefHandler propertyRefHandler;

	private ReplaceHandler replaceHandler;

	private SplitHandler splitHandler;

	private ToLowerHandler toLowerHandler;

	private ToUpperHandler toUpperHandler;

	private ValueHolder<String> source;

	public PropertyElementHandler(AbstractHandler parent) {
		super(parent);
	}

	public void childPopped(ChildHandler child) {
		source = ((ValueHandler) child).getValueHolder();
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (ConstantHandler.TAG.equals(localName)) {
			if (constantHandler == null)
				constantHandler = new ConstantHandler(this);
			ch = constantHandler;
		} else if (Format.TAG.equals(localName)) {
			if (formatHandler == null)
				formatHandler = new FormatHandler(this);
			ch = formatHandler;
		} else if (PropertyRefHandler.TAG.equals(localName)) {
			if (propertyRefHandler == null)
				propertyRefHandler = new PropertyRefHandler(this);
			ch = propertyRefHandler;
		} else if (ReplaceHandler.TAG.equals(localName)) {
			if (replaceHandler == null)
				replaceHandler = new ReplaceHandler(this);
			ch = replaceHandler;
		} else if (SplitHandler.TAG.equals(localName)) {
			if (splitHandler == null)
				splitHandler = new SplitHandler(this);
			ch = splitHandler;
		} else if (ToLowerHandler.TAG.equals(localName)) {
			if (toLowerHandler == null)
				toLowerHandler = new ToLowerHandler(this);
			ch = toLowerHandler;
		} else if (ToUpperHandler.TAG.equals(localName)) {
			if (toUpperHandler == null)
				toUpperHandler = new ToUpperHandler(this);
			ch = toUpperHandler;
		} else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	void addYourself(Map<String, String> props) {
		String key = getKey();
		if (props instanceof ExpandingProperties<?>) {
			source.setMutable(getMutable());
			((ExpandingProperties<String>) props).setProperty(key, source);
		} else
			props.put(key, source.getValue(props));
	}
}

package org.eclipse.buckminster.core.common.parser;

import java.util.ArrayList;

import org.eclipse.buckminster.core.common.model.Flow;
import org.eclipse.buckminster.core.common.model.FlowWithAttributes;
import org.eclipse.buckminster.core.common.model.Text;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class FlowHandler extends ChildHandler implements ChildPoppedListener {
	private static final ISaxableElement[] noChildren = new ISaxableElement[0];

	private final String localName;

	private String[] keyValuePairs;

	private ArrayList<ISaxableElement> elements;

	private StringBuilder text;

	protected FlowHandler(AbstractHandler parentHandler, String localName) {
		super(parentHandler);
		this.localName = localName;
	}

	@Override
	public void characters(char[] chars, int start, int length) throws SAXException {
		if (length == 0)
			return;

		if (text == null)
			text = new StringBuilder();
		text.append(chars, start, length);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		addElement(((FlowHandler) child).createElement());
	}

	public Flow createElement() {
		addTextIfAny();
		ISaxableElement[] children = (elements == null) ? noChildren : elements.toArray(new ISaxableElement[elements.size()]);

		return createFlowElement(localName, keyValuePairs, children);
	}

	@Override
	public ChildHandler createHandler(String uri, String name, Attributes attrs) throws SAXException {
		addTextIfAny();
		return new FlowHandler(this, name);
	}

	@Override
	public String getTAG() {
		return localName;
	}

	@Override
	public void handleAttributes(Attributes attrs) {
		int numAttrs = attrs.getLength();
		if (elements != null)
			elements.clear();
		if (text != null)
			text.setLength(0);
		if (numAttrs > 0) {
			keyValuePairs = new String[numAttrs * 2];
			for (int idx = 0; idx < numAttrs; ++idx) {
				int kaIdx = idx * 2;
				keyValuePairs[kaIdx] = attrs.getLocalName(idx);
				keyValuePairs[kaIdx + 1] = attrs.getValue(idx);
			}
		} else
			keyValuePairs = Trivial.EMPTY_STRING_ARRAY;
	}

	Flow createFlowElement(String name, String[] keyValPairs, ISaxableElement[] children) {
		return (keyValPairs.length == 0) ? new Flow(name, children) : new FlowWithAttributes(name, children, keyValPairs);
	}

	private void addElement(ISaxableElement element) {
		if (elements == null)
			elements = new ArrayList<ISaxableElement>();
		elements.add(element);
	}

	private void addTextIfAny() {
		if (text == null)
			return;

		int textLen = text.length();
		if (textLen == 0)
			return;

		char[] buf = new char[textLen];
		text.getChars(0, textLen, buf, 0);
		addElement(new Text(buf));
		text.setLength(0);
	}
}

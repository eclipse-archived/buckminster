package org.eclipse.b3.coloring;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.xtext.ui.common.editor.syntaxcoloring.DefaultLexicalHighlightingConfiguration;
import org.eclipse.xtext.ui.common.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.core.editor.utils.TextStyle;

public class BeeLangLexicalHighlightConfiguration extends
		DefaultLexicalHighlightingConfiguration {

	public static final String DOCUMENTATION_ID = "documentation";
	public static final String REGEXP_ID = "regexp";
	public static final String BOOLEAN_ID = "boolean";
	public static final String NULL_ID = "null";
	
	@Override
	public void configure(IHighlightingConfigurationAcceptor acceptor) {
		super.configure(acceptor);
		acceptor.acceptDefaultHighlighting(DOCUMENTATION_ID, "Documentation", docTextStyle());
		acceptor.acceptDefaultHighlighting(REGEXP_ID, "Regular Expression", regexpTextStyle());
		acceptor.acceptDefaultHighlighting(BOOLEAN_ID, "Boolean Literal", italicKeywordLightStyle());
		acceptor.acceptDefaultHighlighting(NULL_ID, "Null Literal", nullTextStyle());
		
	}
	public TextStyle docTextStyle() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(63, 95, 191));
		return textStyle;
	}
	public TextStyle regexpTextStyle() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(0, 0, 192));
		return textStyle;
	}
	public TextStyle italicKeywordLightStyle() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(127, 0, 85));
		textStyle.setStyle(SWT.ITALIC);
		return textStyle;
	}
	public TextStyle nullTextStyle() {
		TextStyle textStyle = italicKeywordLightStyle().copy();
		return textStyle;
	}

}

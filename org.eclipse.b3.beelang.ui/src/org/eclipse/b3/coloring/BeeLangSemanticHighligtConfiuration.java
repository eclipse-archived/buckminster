package org.eclipse.b3.coloring;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.xtext.ui.common.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.common.editor.syntaxcoloring.ISemanticHighlightingConfiguration;
import org.eclipse.xtext.ui.core.editor.utils.TextStyle;

public class BeeLangSemanticHighligtConfiuration implements
		ISemanticHighlightingConfiguration {

	public static final String VERSION_ID = "version";
	public static final String PATH_ID = "path";
	public static final String REAL_ID = "real";

	public void configure(IHighlightingConfigurationAcceptor acceptor) {
		acceptor.acceptDefaultHighlighting(VERSION_ID, "Version", versionTextStyle());
		acceptor.acceptDefaultHighlighting(PATH_ID, "Path", pathTextStyle());
		acceptor.acceptDefaultHighlighting(REAL_ID, "Floating point", realTextStyle());
	}
	
	public TextStyle defaultTextStyle() {
		TextStyle textStyle = new TextStyle();
		textStyle.setBackgroundColor(new RGB(255, 255, 255));
		textStyle.setColor(new RGB(0, 0, 0));
		return textStyle;
	}
	public TextStyle realTextStyle() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(125, 125, 125));
		return textStyle;
	}
	
	public TextStyle versionTextStyle() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(63, 95, 191));
		textStyle.setStyle(SWT.ITALIC);
		return textStyle;
	}
	
	public TextStyle italicKeywordLightStyle() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(127, 0, 85));
		textStyle.setStyle(SWT.ITALIC);
		return textStyle;
	}
	public TextStyle pathTextStyle() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(63, 95, 191));
		textStyle.setStyle(SWT.ITALIC);
		return textStyle;
	}

}

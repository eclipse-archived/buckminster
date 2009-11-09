package org.eclipse.b3;

import org.eclipse.b3.coloring.BeeLangLexicalHighlightConfiguration;
import org.eclipse.b3.coloring.BeeLangSemanticHighlightingCalculator;
import org.eclipse.b3.coloring.BeeLangSemanticHighligtConfiuration;
import org.eclipse.b3.coloring.BeeLangTokenToAttributeIdMapper;
import org.eclipse.xtext.ui.common.editor.syntaxcoloring.ILexicalHighlightingConfiguration;
import org.eclipse.xtext.ui.common.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.ui.common.editor.syntaxcoloring.ISemanticHighlightingConfiguration;
import org.eclipse.xtext.ui.common.editor.syntaxcoloring.antlr.AbstractAntlrTokenToAttributeIdMapper;

/**
 * Use this class to register components to be used within the IDE.
 */
public class BeeLangUiModule extends org.eclipse.b3.AbstractBeeLangUiModule {
	
	public Class<? extends AbstractAntlrTokenToAttributeIdMapper> 
		bindTokenToAttributeIdMapper() {
		return BeeLangTokenToAttributeIdMapper.class;
	}
	public Class<? extends ILexicalHighlightingConfiguration> 
		bindILexicalHighlightingConfiguration() {
		return BeeLangLexicalHighlightConfiguration.class;
	}	
	public Class<? extends ISemanticHighlightingConfiguration> 
		bindISemanticHighlightingConfiguration() {
		return BeeLangSemanticHighligtConfiuration.class;
	}
	public Class<? extends ISemanticHighlightingCalculator> 
		bindISemanticHighlightingCalculator() {
		return BeeLangSemanticHighlightingCalculator.class;
	}
}

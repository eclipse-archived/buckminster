package org.eclipse.b3.coloring;

import org.eclipse.xtext.ui.common.editor.syntaxcoloring.DefaultLexicalHighlightingConfiguration;
import org.eclipse.xtext.ui.common.editor.syntaxcoloring.antlr.DefaultAntlrTokenToAttributeIdMapper;

public class BeeLangTokenToAttributeIdMapper extends
		DefaultAntlrTokenToAttributeIdMapper {
	@Override
	protected String calculateId(String tokenName, int tokenType) {

		// treat, 'true', 'false', and 'null' differently
		if(tokenName.startsWith("'")&&tokenName.endsWith("'")) {
			if(tokenName.equals( "'true'") || tokenName.equals( "'false'"))
				return BeeLangLexicalHighlightConfiguration.BOOLEAN_ID;
			if(tokenName.equals("'null'")) 
				return BeeLangLexicalHighlightConfiguration.NULL_ID;
		}
		if("RULE_DOCUMENTATION".equals(tokenName)) {
			return BeeLangLexicalHighlightConfiguration.DOCUMENTATION_ID;
		}
		if("RULE_REGEX".equals(tokenName)) {
			return BeeLangLexicalHighlightConfiguration.REGEXP_ID;
		}
		
//		if("RULE_REAL".equals(tokenName)) {
//			return DefaultLexicalHighlightingConfiguration.NUMBER_ID;
//		}
		if("RULE_HEX".equals(tokenName)) {
			return DefaultLexicalHighlightingConfiguration.NUMBER_ID;
		}

		return super.calculateId(tokenName, tokenType);
	}
}

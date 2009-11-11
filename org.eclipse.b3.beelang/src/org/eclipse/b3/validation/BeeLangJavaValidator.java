package org.eclipse.b3.validation;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Builder;
import org.eclipse.b3.beeLang.Function;
import org.eclipse.b3.beeLang.Method;

import org.eclipse.b3.beeLang.WithClause;
import org.eclipse.xtext.validation.Check;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
 

@SuppressWarnings("restriction")
public class BeeLangJavaValidator extends AbstractBeeLangJavaValidator {

	@Check
	public void checkFunctionDeclaredWithFunctionKeyword(Function func) {
		if (func.getFunc().getForm().equals("method")) {
			error("Methods can only be defined in a unit, or a context", func.getFunc(), BeeLangPackage.FUNCTION_OR_METHOD__FORM);
		}
	}
	@Check
	public void checkMethodDeclaredWithMethodKeyword(Method func) {
		if (func.getMethod().getForm().equals("function")) {
			error("Functions can only be defined in global scope, or in a concern",
					func.getMethod(),
					BeeLangPackage.FUNCTION_OR_METHOD__FORM);
		}
	}
	@Check
	public void checkWithClauseIsNotEmpty(WithClause withClause) {
		if (withClause.getReferences().size() == 0 
				&& withClause.getConcern().size() == 0
				&& withClause.getProperties().size() == 0
				) {
			error("with-clause must have at least one of: references, properties, or concern",
					withClause,
					BeeLangPackage.WITH_CLAUSE);
		}
	}
	@Check
	public void checkBuilderMustReturnSomething(Builder builder) {
		if (builder.getOutput() == null 
				&& builder.getInput() == null
				&& (builder.getExpressionList() == null || builder.getExpressionList().getExpressions().size()==0)
				) {
			error("a builder must have at least one of: input, output or expression",
					builder,
					BeeLangPackage.BUILDER__NAME);
		}
	}
//	@Check
//	public void checkVersion(org.eclipse.b3.beeLang.Version version) {
//		String versionString = version.getOriginal();
//		try {
//		Version.create(versionString);
//		}
//		catch(IllegalArgumentException e)
//			{
//			error("Version '"+versionString+"' is not a valid version: "+e.getMessage(),
//					version,
//					BeeLangPackage.VERSION__ORIGINAL);
//		}
//	}
//	@Check
//	public void checkVersionRange(org.eclipse.b3.beeLang.VersionRange versionRange) {
//		String versionString = versionRange.getOriginal();
//		
////
////		String versionString = versionRange.getMinLimit() +
////			versionRange.getMin().getOriginal() + "," + versionRange.getMax().getOriginal() + 
////			versionRange.getMaxLimit();
//		try {
//		new VersionRange(versionString);
//		}
//		catch(IllegalArgumentException e)
//			{
//			error("VersionRange '"+versionString+"' is not a valid range: "+e.getMessage(),
//					versionRange,
//					BeeLangPackage.VERSION_RANGE);
//		}
//	}

}

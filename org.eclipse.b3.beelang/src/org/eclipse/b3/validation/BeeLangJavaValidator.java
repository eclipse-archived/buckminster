package org.eclipse.b3.validation;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Function;
import org.eclipse.b3.beeLang.Method;
import org.eclipse.xtext.validation.Check;
 

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

}

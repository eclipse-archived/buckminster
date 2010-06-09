package org.eclipse.buckminster.model.common;

import org.eclipse.emf.ecore.EObject;

public interface BObject extends EObject {
	void toString(StringBuilder bld);
}

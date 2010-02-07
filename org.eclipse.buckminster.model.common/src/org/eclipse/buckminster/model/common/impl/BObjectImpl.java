package org.eclipse.buckminster.model.common.impl;

import org.eclipse.buckminster.model.common.BObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;

public abstract class BObjectImpl extends EObjectImpl implements BObject {
	public BObjectImpl() {
	}

	@Override
	public final String toString() {
		StringBuilder bld = new StringBuilder();
		toString(bld);
		return bld.toString();
	}

	public abstract void toString(StringBuilder bld);
}

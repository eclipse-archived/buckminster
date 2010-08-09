package org.eclipse.buckminster.core.cspec;

import org.eclipse.buckminster.model.common.ComponentIdentifier;

public interface IGenerator {
	String getAttribute();

	String getComponent();

	ComponentIdentifier getGeneratedIdentifier();
}

package org.eclipse.buckminster.pde.antscripts;

import org.eclipse.ant.core.IAntPropertyValueProvider;
import org.eclipse.buckminster.ant.actor.AntActor;
import org.eclipse.core.runtime.CoreException;

public class AntProperties implements IAntPropertyValueProvider {
	static private final String BUILD_SCRIPT = "buckminster.pdetasks"; //$NON-NLS-1$

	@Override
	public String getAntPropertyValue(String antPropertyName) {
		if (BUILD_SCRIPT.equals(antPropertyName)) {
			try {
				return AntActor.getBuildFileExtension(antPropertyName).toOSString();
			} catch (CoreException e) {
			}
		}
		return null;
	}
}

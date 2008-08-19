package org.eclipse.buckminster.core.cspec;

import org.eclipse.buckminster.core.version.IVersion;

public interface IComponentIdentifier extends IComponentName
{
	IVersion getVersion();
}

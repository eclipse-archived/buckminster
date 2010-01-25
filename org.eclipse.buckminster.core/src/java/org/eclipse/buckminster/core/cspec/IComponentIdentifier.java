package org.eclipse.buckminster.core.cspec;

import org.eclipse.equinox.p2.metadata.Version;

public interface IComponentIdentifier extends IComponentName
{
	Version getVersion();
}

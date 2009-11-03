package org.eclipse.buckminster.core.cspec;

import org.eclipse.equinox.internal.provisional.p2.metadata.Version;

@SuppressWarnings("restriction")
public interface IComponentIdentifier extends IComponentName
{
	Version getVersion();
}

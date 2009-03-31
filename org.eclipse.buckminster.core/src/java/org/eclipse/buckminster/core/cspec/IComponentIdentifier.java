package org.eclipse.buckminster.core.cspec;

import org.eclipse.equinox.internal.provisional.p2.core.Version;

@SuppressWarnings("restriction")
public interface IComponentIdentifier extends IComponentName
{
	Version getVersion();
}

package org.eclipse.buckminster.maven.internal;

import java.net.URI;

import org.eclipse.buckminster.core.resolver.IResolverBackchannel;

public interface ILocationResolver extends IResolverBackchannel {
	URI getURI();
}

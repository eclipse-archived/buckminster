package org.eclipse.buckminster.maven.internal;

import java.net.URI;

import org.eclipse.buckminster.core.resolver.IResolverBackchannel;
import org.eclipse.ecf.core.security.IConnectContext;

public interface ILocationResolver extends IResolverBackchannel
{
	IConnectContext getConnectContext();

	URI getURI();
}

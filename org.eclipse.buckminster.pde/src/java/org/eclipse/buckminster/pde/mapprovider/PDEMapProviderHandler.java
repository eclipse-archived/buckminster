/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.mapprovider;

import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.parser.ProviderHandler;
import org.eclipse.buckminster.sax.AbstractHandler;


public class PDEMapProviderHandler extends ProviderHandler
{
	public PDEMapProviderHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public Provider getProvider()
	{
		return new PDEMapProvider(
				getSearchPath(),
				getReaderType(),
				getComponentTypes(),
				getVersionConverter(),
				getUriFormat(),
				getSpace(),
				isMutable(),
				isSource(),
				getDocumentation());
	}
}

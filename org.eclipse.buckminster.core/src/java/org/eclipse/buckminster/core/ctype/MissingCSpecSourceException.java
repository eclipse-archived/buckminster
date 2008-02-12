/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.ctype;

import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.buckminster.core.version.ProviderMatch;

/**
 * @author Thomas Hallgren
 */
public class MissingCSpecSourceException extends LocalizedException
{
	private static final long serialVersionUID = 1626761403125431482L;

	public MissingCSpecSourceException(ProviderMatch providerMatch)
	{
		super("Provider %s(%s): Missing CSpec source required by component type %s",
				providerMatch.getProvider().getReaderTypeId(),
				providerMatch.getProvider().getURI(providerMatch.getNodeQuery().getProperties()),
				providerMatch.getComponentType().getId());
	}
}

/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.ctype;

import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;

/**
 * @author Thomas Hallgren
 */
public class MissingCSpecSourceException extends LocalizedException
{
	private static final long serialVersionUID = -6362812835485581154L;
	private final String[] m_arguments;
	public MissingCSpecSourceException(ProviderMatch providerMatch)
	{
		super("Provider {0}({1}): Missing CSpec source required by component type {2}");
		Provider provider = providerMatch.getProvider();
		m_arguments = new String[] { provider.getReaderTypeId(), provider.getURI(providerMatch.getNodeQuery().getProperties()),
				providerMatch.getComponentType().getId() };
		assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_arguments;
	}
}

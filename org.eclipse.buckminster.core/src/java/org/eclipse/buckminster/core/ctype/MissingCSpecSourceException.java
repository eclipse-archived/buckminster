/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.ctype;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class MissingCSpecSourceException extends LocalizedException
{
	private static final long serialVersionUID = 1626761403125431482L;

	public MissingCSpecSourceException(ProviderMatch providerMatch)
	{
		super(NLS.bind(
				Messages.MissingCSpecSourceException_Provider_0_1_Missing_CSpec_source_required_by_component_type_2,
				new Object[] { providerMatch.getProvider().getReaderTypeId(),
						providerMatch.getProvider().getURI(providerMatch.getNodeQuery().getProperties()),
						providerMatch.getComponentType().getId() }));
	}
}

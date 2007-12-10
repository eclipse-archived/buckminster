/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cmdline.parser;

import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;

public class AnyOptionDescriptor extends OptionDescriptor
{
	public AnyOptionDescriptor()
	{
		super(null, null, OptionValueType.OPTIONAL);
	}

	@Override
	public boolean isAcceptableName(String name, boolean isLongName, boolean exact)
	{
		// we accept anything!
		//
		return true;
	}
}


/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ui.editor;

import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Widget;

public class VersionDesignatorEvent extends TypedEvent
{
	private static final long serialVersionUID = 3880730801886666166L;

	public VersionDesignatorEvent(VersionDesignator vd, Widget w, Object d)
	{
		super(vd);
		this.display = vd.getDisplay();
		this.widget = w;
		this.data = d;
	}

	public VersionRange getVersionDesignator()
	{
		return ((VersionDesignator)this.getSource()).getVersionDesignator();
	}

	public String getVersionType()
	{
		return ((VersionDesignator)this.getSource()).getVersionType();
	}
}

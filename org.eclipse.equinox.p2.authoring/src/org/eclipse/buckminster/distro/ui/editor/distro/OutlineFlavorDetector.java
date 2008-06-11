/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.buckminster.distro.ui.editor.distro;

import org.eclipse.buckminster.opml.IOutline;
import org.eclipse.buckminster.opml.OutlineType;

/**
 * @author Henrik Lindberg
 *
 */
public class OutlineFlavorDetector
{
	enum Flavor {
		FEED,
		LINK,
		FOLDER,
		;
	}
	public static Flavor getType(IOutline outline)
	{
		// simplified interpretation...
		// All unknown (missing type) outlines are folders (or if it has children)
		// Then, something is either a link (specified with type="link"), or is one of the
		// types that means it is a feed (or at least that it has both XML URL, and HTML URL).
		//
		if(outline.getType() == OutlineType.UNKNOWN || outline.getOutlines().size() > 0 )
			return Flavor.FOLDER;
		if(outline.getType() == OutlineType.LINK)
			return Flavor.LINK;
		return Flavor.FEED;
	}
}

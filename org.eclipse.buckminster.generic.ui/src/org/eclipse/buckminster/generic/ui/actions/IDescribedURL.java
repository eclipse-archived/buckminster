/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.generic.ui.actions;

import java.net.URI;
import java.net.URL;

/**
 * Interface implemented by something that can be viewed in a browser.
 * 
 * @author Henrik Lindberg
 * 
 */
public interface IDescribedURL
{
	/**
	 * Get the URI (same as the URL, but in URI form).
	 * 
	 * @return
	 */
	URI getBrowseableURI();

	/**
	 * The URL that can be viewed in a browser.
	 * 
	 * @return
	 */
	URL getBrowseableURL();

	/**
	 * The name shown for the browser instance.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * The tooltip shown for the browser instance.
	 * 
	 * @return
	 */
	String getTooltip();
}

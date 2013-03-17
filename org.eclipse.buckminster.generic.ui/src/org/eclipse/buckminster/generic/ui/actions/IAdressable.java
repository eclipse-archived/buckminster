/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.generic.ui.actions;

import java.net.URI;

/**
 * Interface implemented by something that is adressable. The URI does not have
 * to be a URL.
 * 
 * @author Henrik Lindberg
 * 
 */
public interface IAdressable {
	URI getURI();
}

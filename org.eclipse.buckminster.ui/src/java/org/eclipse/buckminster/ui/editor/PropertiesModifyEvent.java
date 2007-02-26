/*******************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.editor;

import org.eclipse.swt.events.TypedEvent;

/**
 * @author Karel Brezina
 * 
 */
public class PropertiesModifyEvent extends TypedEvent
{

	private static final long serialVersionUID = 7472952304329698726L;

	public PropertiesModifyEvent(Properties prop)
	{
		super(prop);
		this.display = prop.getDisplay();
		this.widget = prop;
		this.data = prop.getProperties();
	}
}

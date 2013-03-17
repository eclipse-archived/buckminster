/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;

/**
 * Not a true adapter, but used to carry data that is otherwise in just a Map.
 * 
 * @author Henrik Lindberg
 * 
 */
public class ComponentReference {
	public enum Mode {
		IN, OUT, ;
	}

	private String componentName;

	private ComponentRequest componentRequest;

	private Mode mode;

	public ComponentReference(String name, ComponentRequest request, Mode mode) {
		this.componentName = name;
		this.componentRequest = request;
		this.mode = mode;
	}

	public String getComponentName() {
		return componentName;
	}

	public ComponentRequest getComponentRequest() {
		return componentRequest;
	}

	public Mode getMode() {
		return mode;
	}
}

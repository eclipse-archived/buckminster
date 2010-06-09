/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

/**
 * @author Thomas Hallgren
 */
public abstract class NamedElementBuilder {
	private String name;

	public void clear() {
		name = null;
	}

	public String getName() {
		return name;
	}

	public void initFrom(String n) {
		clear();
		this.name = n;
	}

	public void setName(String name) {
		this.name = name;
	}
}

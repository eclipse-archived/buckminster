/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cmdline;

public class Option {
	private final OptionDescriptor optionDescriptor;

	private final String name;

	private final String value;

	private final boolean isLongName;

	public Option(OptionDescriptor optionDescriptor, String name, String value, boolean isLongName) {
		this.optionDescriptor = optionDescriptor;
		this.name = name;
		this.value = value;
		this.isLongName = isLongName;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public boolean is(OptionDescriptor descriptor) {
		return optionDescriptor == descriptor;
	}

	public boolean isLongName() {
		return isLongName;
	}

	@Override
	public String toString() {
		StringBuffer bld = new StringBuffer();
		bld.append('-');
		if (isLongName)
			bld.append('-');
		bld.append(name);
		if (value != null) {
			bld.append(' ');
			bld.append(value);
		}
		return bld.toString();
	}
}

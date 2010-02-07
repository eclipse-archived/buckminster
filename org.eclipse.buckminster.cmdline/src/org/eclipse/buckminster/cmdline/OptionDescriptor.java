/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cmdline;

public class OptionDescriptor {
	private final Character shortName;

	private final String longName;

	private final int type;

	public OptionDescriptor(char shortName, String longName, int type) {
		this(shortName == 0 ? null : Character.valueOf(shortName), longName, type);
	}

	public OptionDescriptor(Character shortName, String longName, int type) {
		this.shortName = shortName;
		this.longName = longName;
		this.type = type;
	}

	public OptionDescriptor(String longName, int type) {
		this(null, longName, type);
	}

	public String getLongName() {
		return longName;
	}

	public Character getShortName() {
		return shortName;
	}

	public int getType() {
		return type;
	}

	public boolean isAcceptableName(String name, boolean isLongName, boolean exact) {
		// short names have simple testing
		//
		if (!isLongName)
			return (shortName == null ? false : shortName.charValue() == name.charAt(0));

		// long names are sensitive to exact or non-exact matching
		//
		if (longName == null)
			return false;

		return (exact ? longName.equals(name) : longName.startsWith(name));
	}
}

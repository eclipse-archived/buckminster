/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.io.File;

/**
 * 
 * @author Thomas Hallgren
 * 
 */
public class FileHandle {
	private final String name;

	private final File file;

	private final boolean isTemporary;

	public FileHandle(String name, File file, boolean isTemporary) {
		this.name = name;
		this.file = file;
		this.isTemporary = isTemporary;
	}

	public File getFile() {
		return file;
	}

	public String getName() {
		return name;
	}

	public boolean isTemporary() {
		return isTemporary;
	}
}

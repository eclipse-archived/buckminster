/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor;

/**
 * @author Karel Brezina
 * 
 */
public enum ArtifactType {
	CQUERY("bmqtmp-", ".cquery"), CSPEC("bmstmp-", ".cspec"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	private String tempPrefix;

	private String tempExtension;

	private ArtifactType(String tempPrefix, String tempExtension) {
		this.tempPrefix = tempPrefix;
		this.tempExtension = tempExtension;
	}

	public String getTempExtension() {
		return tempExtension;
	}

	public String getTempPrefix() {
		return tempPrefix;
	}
}

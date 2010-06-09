/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ui.internal;

import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.Version;

public class CSpecEditorInput extends SaxableEditorInput {
	private final CSpec cspec;

	public CSpecEditorInput(CSpec cspec) {
		this.cspec = cspec;
	}

	@Override
	public boolean equals(Object other) {
		return other == this || (other instanceof CSpecEditorInput && ((CSpecEditorInput) other).cspec.equals(cspec));
	}

	@Override
	public boolean exists() {
		return true;
	}

	public ICSpecData getCSpec() {
		return cspec;
	}

	@Override
	public String getName() {
		StringBuilder bld = new StringBuilder();
		bld.append(cspec.getName());
		Version version = cspec.getVersion();
		if (version != null) {
			bld.append(':');
			bld.append(VersionHelper.getHumanReadable(version));
		}
		bld.append(".cspec"); //$NON-NLS-1$
		return bld.toString();
	}

	@Override
	public String getToolTipText() {
		return this.getName();
	}

	@Override
	public int hashCode() {
		return cspec.hashCode();
	}

	@Override
	protected ISaxable getContent() throws CoreException {
		return cspec;
	}
}

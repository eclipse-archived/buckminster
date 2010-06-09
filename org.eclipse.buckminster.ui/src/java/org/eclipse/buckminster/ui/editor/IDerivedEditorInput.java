/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor;

import org.eclipse.ui.IEditorInput;

/**
 * It is used to access the original <code>IEditorInput</code> in case of
 * derived editor inputs.
 * 
 * @author Karel Brezina
 * 
 */
public interface IDerivedEditorInput {
	/**
	 * Returns original <code>IEditorInput</code>
	 * 
	 * @return original <code>IEditorInput</code>
	 */
	public IEditorInput getOriginalInput();
}

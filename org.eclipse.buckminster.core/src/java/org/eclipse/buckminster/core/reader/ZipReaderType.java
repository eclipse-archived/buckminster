/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class ZipReaderType extends URLReaderType {
	@Override
	public void addMaterializationNode(MaterializationSpecBuilder bld, Resolution res) throws CoreException {
		MaterializationNodeBuilder node = bld.addNodeBuilder();
		node.setNamePattern(Pattern.compile(Pattern.quote(res.getName())));
		node.setUnpack(true);
	}
}

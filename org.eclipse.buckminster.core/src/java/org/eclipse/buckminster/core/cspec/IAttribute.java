/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec;

import java.util.List;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.osgi.filter.Filter;

public interface IAttribute {
	AttributeBuilder getAttributeBuilder(CSpecBuilder specBuilder);

	Documentation getDocumentation();

	Filter getFilter();

	String getName();

	List<? extends IPrerequisite> getPrerequisites();

	String getQualifiedName();

	boolean isPublic();
}

package org.eclipse.buckminster.core.mspec;

import java.util.regex.Pattern;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.core.runtime.IPath;

public interface IMaterializationNode extends IMaterializationDirective
{
	Pattern getBindingNamePattern();

	String getBindingNameReplacement();

	String getComponentTypeID();

	Filter getFilter();

	IPath getLeafArtifact();

	Pattern getNamePattern();

	IPath getResourcePath();

	String getSuffix();

	boolean isExclude();

	boolean isExpand();

	boolean isUnpack();
}

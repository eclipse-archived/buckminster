package org.eclipse.buckminster.core.mspec;

import java.net.URL;
import java.util.List;

import org.eclipse.buckminster.core.cspec.IComponentName;
import org.eclipse.buckminster.sax.ISaxable;

public interface IMaterializationSpec extends IMaterializationDirective, ISaxable
{
	URL getContextURL();

	IMaterializationNode getMatchingNode(IComponentName cName);

	String getName();

	List<? extends IMaterializationNode> getNodes();

	URL getResolvedURL();

	String getShortDesc();

	String getURL();
}

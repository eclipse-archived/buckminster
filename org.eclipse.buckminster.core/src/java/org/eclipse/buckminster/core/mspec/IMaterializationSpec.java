package org.eclipse.buckminster.core.mspec;

import java.net.URL;
import java.util.List;

import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.sax.ISaxable;

public interface IMaterializationSpec extends IMaterializationDirective, ISaxable
{
	IMaterializationNode getMatchingNode(ComponentName cName);

	String getName();

	List<? extends IMaterializationNode> getNodes();

	String getShortDesc();

	String getURL();

	URL getContextURL();

	URL getResolvedURL();
}

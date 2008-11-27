package org.eclipse.buckminster.core.query;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.IComponentRequest;

public interface IComponentQuery
{
	List<? extends IAdvisorNode> getAdvisoryNodes();

	URL getContextURL();

	Map<String, String> getDeclaredProperties();

	Documentation getDocumentation();

	IAdvisorNode getNodeByPattern(String pattern, String componentTypeID);

	String getPropertiesURL();

	String getResourceMapURL();

	IComponentRequest getRootRequest();

	String getShortDesc();
}

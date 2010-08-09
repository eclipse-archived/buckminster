package org.eclipse.buckminster.core.query;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.osgi.filter.Filter;

public interface IComponentQuery {
	List<? extends IAdvisorNode> getAdvisoryNodes();

	URL getContextURL();

	Map<String, String> getDeclaredProperties();

	Documentation getDocumentation();

	IAdvisorNode getNodeByCriteria(Pattern pattern, String componentTypeID, Filter filter);

	String getPropertiesURL();

	String getResourceMapURL();

	ComponentRequest getRootRequest();

	String getShortDesc();
}

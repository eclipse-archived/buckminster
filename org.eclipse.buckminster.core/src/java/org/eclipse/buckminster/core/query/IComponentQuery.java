package org.eclipse.buckminster.core.query;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.osgi.filter.Filter;

public interface IComponentQuery
{
	List<? extends IAdvisorNode> getAdvisoryNodes();

	URL getContextURL();

	Map<String, String> getDeclaredProperties();

	Documentation getDocumentation();

	IAdvisorNode getNodeByCriteria(Pattern pattern, String componentTypeID, Filter filter);

	/**
	 * @deprecated Use {@link #getNodeByCriteria(Pattern, String, Filter)}
	 */
	@Deprecated
	IAdvisorNode getNodeByPattern(String pattern, String componentTypeID);

	String getPropertiesURL();

	String getResourceMapURL();

	IComponentRequest getRootRequest();

	String getShortDesc();
}

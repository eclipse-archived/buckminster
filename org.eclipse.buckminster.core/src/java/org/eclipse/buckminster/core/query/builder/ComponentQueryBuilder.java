/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.query.builder;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.query.IAdvisorNode;
import org.eclipse.buckminster.core.query.IComponentQuery;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.Trivial;

/**
 * @author Thomas Hallgren
 */
public class ComponentQueryBuilder implements IComponentQuery {
	private final ArrayList<AdvisorNodeBuilder> advisorNodes = new ArrayList<AdvisorNodeBuilder>();

	private final ComponentRequestBuilder rootRequest = new ComponentRequestBuilder();

	private Documentation documentation;

	private Map<String, String> properties;

	private URL contextURL;

	private String propertiesURL;

	private String resourceMapURL;

	private String shortDesc;

	public AdvisorNodeBuilder addAdvisorNode() {
		AdvisorNodeBuilder node = new AdvisorNodeBuilder();
		advisorNodes.add(node);
		return node;
	}

	public void addAdvisorNode(AdvisorNodeBuilder node) {
		advisorNodes.add(node);
	}

	public void clear() {
		rootRequest.clear();
		advisorNodes.clear();
		contextURL = null;
		properties = null;
		propertiesURL = null;
		resourceMapURL = null;
		documentation = null;
		shortDesc = null;
	}

	public ComponentQuery createComponentQuery() {
		return new ComponentQuery(this);
	}

	@Override
	public List<AdvisorNodeBuilder> getAdvisoryNodes() {
		return advisorNodes;
	}

	@Override
	public URL getContextURL() {
		return contextURL;
	}

	@Override
	public Map<String, String> getDeclaredProperties() {
		if (properties == null)
			properties = new HashMap<String, String>();
		return properties;
	}

	@Override
	public Documentation getDocumentation() {
		return documentation;
	}

	@Override
	public AdvisorNodeBuilder getNodeByCriteria(Pattern pattern, String componentType, Filter filter) {
		for (AdvisorNodeBuilder node : advisorNodes)
			if (Trivial.equalsAllowNull(node.getNamePattern(), pattern) && Trivial.equalsAllowNull(node.getComponentTypeID(), componentType)
					&& Trivial.equalsAllowNull(node.getFilter(), filter))
				return node;
		return null;
	}

	@Override
	public String getPropertiesURL() {
		return propertiesURL;
	}

	@Override
	public String getResourceMapURL() {
		return resourceMapURL;
	}

	@Override
	public ComponentRequest getRootRequest() {
		return rootRequest.createComponentRequest();
	}

	public ComponentRequestBuilder getRootRequestBuilder() {
		return rootRequest;
	}

	@Override
	public String getShortDesc() {
		return shortDesc;
	}

	public void initFrom(IComponentQuery query) {
		this.clear();
		for (IAdvisorNode node : query.getAdvisoryNodes()) {
			AdvisorNodeBuilder bld = new AdvisorNodeBuilder();
			bld.initFrom(node);
			advisorNodes.add(bld);
		}

		Map<String, String> props = query.getDeclaredProperties();
		if (props.size() > 0)
			properties = new HashMap<String, String>(props);

		contextURL = query.getContextURL();
		propertiesURL = query.getPropertiesURL();
		resourceMapURL = query.getResourceMapURL();
		rootRequest.initFrom(query.getRootRequest());
		documentation = query.getDocumentation();
		shortDesc = query.getShortDesc();
	}

	public void removeAdvisorNode(AdvisorNodeBuilder node) {
		advisorNodes.remove(node);
	}

	public final void setContextURL(URL contextURL) {
		this.contextURL = contextURL;
	}

	public void setDocumentation(Documentation documentation) {
		this.documentation = documentation;
	}

	public void setPlatformAgnostic(boolean flag) {
		if (flag == false) {
			if (properties == null)
				return;
			properties.remove(TargetPlatform.TARGET_OS);
			properties.remove(TargetPlatform.TARGET_WS);
			properties.remove(TargetPlatform.TARGET_ARCH);
			properties.remove(TargetPlatform.TARGET_NL);
			if (properties.size() == 0)
				properties = null;
		} else {
			Map<String, String> props = getDeclaredProperties();
			props.put(TargetPlatform.TARGET_OS, FilterUtils.MATCH_ALL);
			props.put(TargetPlatform.TARGET_WS, FilterUtils.MATCH_ALL);
			props.put(TargetPlatform.TARGET_ARCH, FilterUtils.MATCH_ALL);
			props.put(TargetPlatform.TARGET_NL, FilterUtils.MATCH_ALL);
		}
	}

	public final void setPropertiesURL(String propertiesURL) {
		this.propertiesURL = propertiesURL;
	}

	public final void setResourceMapURL(String resourceMapURL) {
		this.resourceMapURL = resourceMapURL;
	}

	public final void setRootRequest(IComponentRequest rootRequest) {
		this.rootRequest.initFrom(rootRequest);
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
}

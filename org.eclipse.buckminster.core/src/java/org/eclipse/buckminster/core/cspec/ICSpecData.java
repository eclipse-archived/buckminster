package org.eclipse.buckminster.core.cspec;

import java.net.URL;
import java.util.Map;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.model.MissingDependencyException;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.core.runtime.IAdaptable;
import org.osgi.framework.Filter;

public interface ICSpecData extends IAdaptable
{
	IAttribute getAttribute(String name);

	Map<String, ? extends IAttribute> getAttributes();

	String getComponentTypeID();

	IComponentIdentifier getComponentIdentifier();

	Map<String, ? extends IComponentRequest> getDependencies();

	Filter getFilter();

	Map<String, ? extends IGenerator> getGenerators();

	IComponentRequest getDependency(String dependencyName) throws MissingDependencyException;

	Documentation getDocumentation();

	String getName();

	URL getProjectInfo();

	String getShortDesc();

	IVersion getVersion();
}

package org.eclipse.buckminster.core.cspec;

import java.net.URL;
import java.util.Map;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.model.MissingDependencyException;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.equinox.internal.provisional.p2.core.Version;

@SuppressWarnings("restriction")
public interface ICSpecData extends IAdaptable
{
	IAttribute getAttribute(String name);

	Map<String, ? extends IAttribute> getAttributes();

	IComponentIdentifier getComponentIdentifier();

	String getComponentTypeID();

	Map<String, ? extends IComponentRequest> getDependencies();

	IComponentRequest getDependency(String dependencyName) throws MissingDependencyException;

	Documentation getDocumentation();

	Filter getFilter();

	Map<String, ? extends IGenerator> getGenerators();

	String getName();

	URL getProjectInfo();

	String getShortDesc();

	Version getVersion();
}

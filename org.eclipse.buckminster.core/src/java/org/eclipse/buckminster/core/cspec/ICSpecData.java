package org.eclipse.buckminster.core.cspec;

import java.net.URL;
import java.util.Collection;
import java.util.Map;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.model.MissingDependencyException;
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;

public interface ICSpecData extends IAdaptable {
	IAttribute getAttribute(String name);

	Map<String, ? extends IAttribute> getAttributes();

	ComponentIdentifier getComponentIdentifier();

	String getComponentTypeID();

	Collection<? extends ComponentRequest> getDependencies();

	ComponentRequest getDependency(String dependencyName, String componentType, VersionRange range) throws MissingDependencyException;

	Documentation getDocumentation();

	Filter getFilter();

	Collection<? extends IGenerator> getGeneratorList();

	String getName();

	URL getProjectInfo();

	String getShortDesc();

	Version getVersion();
}

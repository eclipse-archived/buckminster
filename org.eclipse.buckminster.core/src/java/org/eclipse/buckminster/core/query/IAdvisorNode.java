package org.eclipse.buckminster.core.query;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.query.model.MutableLevel;
import org.eclipse.buckminster.core.query.model.SourceLevel;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.equinox.p2.metadata.VersionRange;

public interface IAdvisorNode {

	public static final int PRIO_VERSION_DESIGNATOR = 1;

	public static final int PRIO_BRANCHTAG_PATH_INDEX = 2;

	public static final int[] DEFAULT_RESOLUTION_PRIO = { PRIO_BRANCHTAG_PATH_INDEX, PRIO_VERSION_DESIGNATOR };

	boolean allowCircularDependency();

	List<String> getAttributes();

	VersionSelector[] getBranchTagPath();

	String getComponentTypeID();

	Documentation getDocumentation();

	Filter getFilter();

	MutableLevel getMutableLevel();

	Pattern getNamePattern();

	URL getOverlayFolder();

	Map<String, String> getProperties();

	int[] getResolutionPrio();

	String getRevision();

	SourceLevel getSourceLevel();

	Date getTimestamp();

	VersionRange getVersionOverride();

	boolean isPrune();

	boolean isSystemDiscovery();

	boolean isUseMaterialization();

	boolean isUseRemoteResolution();

	boolean isUseTargetPlatform();

	boolean isUseWorkspace();

	boolean skipComponent();

}

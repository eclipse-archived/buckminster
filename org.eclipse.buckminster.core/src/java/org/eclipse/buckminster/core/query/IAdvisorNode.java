package org.eclipse.buckminster.core.query;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.query.model.MutableLevel;
import org.eclipse.buckminster.core.query.model.SourceLevel;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.VersionSelector;

public interface IAdvisorNode
{

	public static final int PRIO_VERSION_DESIGNATOR = 1;

	public static final int PRIO_BRANCHTAG_PATH_INDEX = 2;

	public static final int PRIO_SPACE_PATH_INDEX = 3;

	public static final int[] DEFAULT_RESOLUTION_PRIO = { PRIO_BRANCHTAG_PATH_INDEX, PRIO_VERSION_DESIGNATOR,
			PRIO_SPACE_PATH_INDEX };

	boolean allowCircularDependency();

	List<String> getAttributes();

	VersionSelector[] getBranchTagPath();

	String getComponentTypeID();

	Documentation getDocumentation();

	MutableLevel getMutableLevel();

	Pattern getNamePattern();

	URL getOverlayFolder();

	Map<String, String> getProperties();

	int[] getResolutionPrio();

	String[] getSpacePath();

	long getRevision();

	Date getTimestamp();

	SourceLevel getSourceLevel();

	IVersionDesignator getVersionOverride();

	boolean isPrune();

	boolean isSystemDiscovery();

	boolean isUseTargetPlatform();

	boolean isUseMaterialization();

	boolean isUseWorkspace();

	boolean isUseRemoteResolution();

	boolean skipComponent();

}

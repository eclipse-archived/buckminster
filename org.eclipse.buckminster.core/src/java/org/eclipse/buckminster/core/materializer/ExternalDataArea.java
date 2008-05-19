package org.eclipse.buckminster.core.materializer;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;

public class ExternalDataArea
{
	private static final String META_AREA = ".metadata"; //$NON-NLS-1$

	private static final String PLUGIN_DATA = ".plugins"; //$NON-NLS-1$

	private static final String PREFERENCES_FILE_NAME = "pref_store.ini"; //$NON-NLS-1$

	private final IPath m_location; // The location of the instance data

	public ExternalDataArea(IPath location, ConflictResolution strategy) throws CoreException
	{
		m_location = location;
		FileUtils.prepareDestination(m_location.append(META_AREA).toFile(), strategy, new NullProgressMonitor());
	}

	public IPath getMetadataLocation()
	{
		return m_location.append(META_AREA);
	}

	public IPath getInstanceDataLocation()
	{
		return m_location;
	}

	public IPath getStateLocation(String bundleName)
	{
		return getMetadataLocation().append(PLUGIN_DATA).append(bundleName);
	}

	public IPath getPreferenceLocation(String bundleName, boolean create) throws IllegalStateException
	{
		IPath result = getStateLocation(bundleName);
		if(create)
			result.toFile().mkdirs();
		return result.append(PREFERENCES_FILE_NAME);
	}
}

package org.eclipse.buckminster.download.prefs;

import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.IBuckminsterPreferenceConstants;
import org.eclipse.core.runtime.CoreException;
import org.osgi.service.prefs.BackingStoreException;

public class ConnectionRetryCount extends BasicPreferenceHandler
{
	@Override
	public String get(String defaultValue) throws CoreException
	{
		return Integer.toString(BuckminsterPreferences.getConnectionRetryCount());
	}

	@Override
	public void set(String prefValue) throws BackingStoreException
	{
		try
		{
			int retryCount = Integer.parseInt(prefValue);
			BuckminsterPreferences.setConnectionRetryCount(retryCount);
			BuckminsterPreferences.getNode().flush();
			return;
		}
		catch(NumberFormatException e)
		{
		}
		throw new IllegalArgumentException(String.format(
				"%s is an illegal value for connectionRetryCount. It must be an integer value.", prefValue));

	}

	@Override
	public void unset() throws BackingStoreException
	{
		BuckminsterPreferences.setConnectionRetryCount(IBuckminsterPreferenceConstants.CONNECTION_RETRY_COUNT_DEFAULT);
		BuckminsterPreferences.getNode().flush();
	}

}

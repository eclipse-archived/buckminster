package org.eclipse.buckminster.download.prefs;

import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.IBuckminsterPreferenceConstants;
import org.eclipse.core.runtime.CoreException;
import org.osgi.service.prefs.BackingStoreException;

public class ConnectionRetryDelay extends BasicPreferenceHandler
{
	@Override
	public String get(String defaultValue) throws CoreException
	{
		return Integer.toString(BuckminsterPreferences.getConnectionRetryDelay());
	}

	@Override
	public void set(String prefValue) throws BackingStoreException
	{
		try
		{
			int retryDelay = Integer.parseInt(prefValue);
			BuckminsterPreferences.setConnectionRetryDelay(retryDelay);
			BuckminsterPreferences.getNode().flush();
			return;
		}
		catch(NumberFormatException e)
		{
		}
		throw new IllegalArgumentException(String.format(
				"%s is an illegal value for connectionRetryDelay. It must be an integer value.", prefValue));

	}

	@Override
	public void unset() throws BackingStoreException
	{
		BuckminsterPreferences.setConnectionRetryDelay(IBuckminsterPreferenceConstants.CONNECTION_RETRY_DELAY_DEFAULT);
		BuckminsterPreferences.getNode().flush();
	}
}

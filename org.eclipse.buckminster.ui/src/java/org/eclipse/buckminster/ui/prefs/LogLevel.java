/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.prefs;

import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.ui.LabeledCombo;

public enum LogLevel
{
	DEBUG
	{
		@Override
		public int getLogLevel()
		{
			return Logger.DEBUG;
		}
	},
	INFO
	{
		@Override
		public int getLogLevel()
		{
			return Logger.INFO;
		}
	},
	WARNING
	{
		@Override
		public int getLogLevel()
		{
			return Logger.WARNING;
		}
	},
	ERROR
	{
		@Override
		public int getLogLevel()
		{
			return Logger.ERROR;
		}
	};
	public abstract int getLogLevel();

	public static void setComboLogLevel(LabeledCombo logLevelCombo, int logLevel)
	{
		LogLevel[] levels = LogLevel.values();
		int idx = levels.length;
		while(--idx >= 0)
		{
			if(levels[idx].getLogLevel() == logLevel)
			{
				logLevelCombo.select(idx);
				return;
			}
		}
		logLevelCombo.select(0);
	}
}
package org.eclipse.buckminster.executor.actor;

import org.junit.Assert;
import org.junit.Test;

public class EnvironnementVariablesTest
{
	/**
	 * Verifies the environment variables set in the CSPEC is split correctly regarding the quoted parts.
	 */
	@Test
	public void testEnvironnementVariables()
	{
		final String[] variables = new String[] {
				"DevEnvDir=%VSINSTALLDIR%\\Common7\\IDE",
				"PATH=\"%DevEnvDir%;%VCINSTALLDIR%\\BIN;%VSINSTALLDIR%\\Common7\\Tools;%VSINSTALLDIR%\\Common7\\Tools\\bin;%VCINSTALLDIR%\\PlatformSDK\\bin;%FrameworkSDKDir%\\bin;%FrameworkDir%\\%FrameworkVersion%;%VCINSTALLDIR%\\VCPackages;%PATH%\"",
				"INCLUDE=\"%VCINSTALLDIR%\\ATLMFC\\INCLUDE;%VCINSTALLDIR%\\INCLUDE;%VCINSTALLDIR%\\PlatformSDK\\include;%FrameworkSDKDir%\\include;%INCLUDE%\"",
				"LIB=\"%VCINSTALLDIR%\\ATLMFC\\LIB;%VCINSTALLDIR%\\LIB;%VCINSTALLDIR%\\PlatformSDK\\lib;%FrameworkSDKDir%\\lib;%LIB%\"",
				"LIBPATH=\"%FrameworkDir%\\%FrameworkVersion%;%VCINSTALLDIR%\\ATLMFC\\LIB\"" };
		String concat = "";
		for(String string : variables)
			concat += concat.length() == 0
					? string
					: ';' + string;

		Assert.assertArrayEquals(variables, ExecutorActor.splitEnvironnementVariables(concat));
	}
}

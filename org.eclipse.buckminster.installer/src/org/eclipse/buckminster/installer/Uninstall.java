/*****************************************************************************
 * Copyright (c) 2007-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.installer;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.internal.p2.console.ProvisioningHelper;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.director.ProfileChangeRequest;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfile;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.osgi.util.NLS;

@SuppressWarnings("restriction")
public class Uninstall extends AbstractCommand
{
	private Version m_version;

	private String m_feature;

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		int len = unparsed.length;
		if(len > 2)
			throw new SimpleErrorExitException(Messages.too_many_arguments);
		if(len > 0)
			m_feature = unparsed[0];
		if(len > 1)
			try
			{
				m_version = Version.parseVersion(unparsed[1]);
			}
			catch(IllegalArgumentException e)
			{
				throw new SimpleErrorExitException(NLS
						.bind("Unable to parse version: {0}", unparsed[1], e.getMessage()));
			}
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		if(m_feature == null)
			throw new SimpleErrorExitException(Messages.no_feature_id_provided);

		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		IProfile profile = ProvisioningHelper.getProfile(IProfileRegistry.SELF);
		IInstallableUnit[] rootArr = Install.getRootIUs(null, profile, m_feature, m_version, monitor);

		// Add as root IU's to a request
		ProfileChangeRequest request = new ProfileChangeRequest(profile);
		for(IInstallableUnit rootIU : rootArr)
			request.setInstallableUnitProfileProperty(rootIU, IInstallableUnit.PROP_PROFILE_ROOT_IU, Boolean.TRUE
					.toString());
		request.removeInstallableUnits(rootArr);
		return Install.planAndExecute(profile, request, null, monitor);
	}
}

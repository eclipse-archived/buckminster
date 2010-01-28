/*****************************************************************************
 * Copyright (c) 2007-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.installer;

import java.util.Iterator;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.internal.provisional.p2.director.ProfileChangeRequest;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.IProvisioningAgentProvider;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.query.IQueryResult;
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
				throw new SimpleErrorExitException(
						NLS.bind("Unable to parse version: {0}", unparsed[1], e.getMessage()));
			}
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		if(m_feature == null)
			throw new SimpleErrorExitException(Messages.no_feature_id_provided);

		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		Buckminster bucky = Buckminster.getDefault();
		IProvisioningAgentProvider agentProvider = bucky.getService(IProvisioningAgentProvider.class);
		IProvisioningAgent agent = agentProvider.createAgent(null);
		try
		{
			IProfileRegistry profileRegistry = (IProfileRegistry)agent.getService(IProfileRegistry.SERVICE_NAME);
			IProfile profile = profileRegistry.getProfile(IProfileRegistry.SELF);
			IQueryResult<IInstallableUnit> rootArr = Install.getRootIUs(agent, null, profile, m_feature, m_version,
					monitor);

			// Add as root IU's to a request
			ProfileChangeRequest request = new ProfileChangeRequest(profile);
			for(Iterator<IInstallableUnit> iter = rootArr.iterator(); iter.hasNext();)
				request.setInstallableUnitProfileProperty(iter.next(), IProfile.PROP_PROFILE_ROOT_IU,
						Boolean.TRUE.toString());
			request.removeInstallableUnits(rootArr);
			return Install.planAndExecute(agent, profile, request, null, monitor);
		}
		finally
		{
			agent.stop();
			bucky.ungetService(agentProvider);
		}
	}
}

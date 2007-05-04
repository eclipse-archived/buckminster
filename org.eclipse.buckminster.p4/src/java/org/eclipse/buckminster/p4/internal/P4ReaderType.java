/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.internal;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.TimedHashMap;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.core.reader.AbstractReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * @author thhal
 */
public class P4ReaderType extends AbstractReaderType
{
	private final Map<String, ClientSpec> m_clients = new TimedHashMap<String, ClientSpec>(20000, null);

	public synchronized ClientSpec getClient(DepotURI depotLocation) throws CoreException
	{
		String clientName = depotLocation.getClientName();
		ClientSpec clientSpec = m_clients.get(clientName);
		if(clientSpec == null)
		{
			Connection conn = new Connection(depotLocation);
			clientSpec = conn.getClientSpec();
			m_clients.put(clientName, clientSpec);
		}
		return clientSpec;
	}

	@Override
	public IPath getMaterializationLocation(Resolution ci, MaterializationContext context, boolean[] optional)
	throws CoreException
	{
		Map<String,String> properties = context.getProperties(ci.getRequest());
		DepotURI depotLocation = getDepotLocation(ci, properties);
		String localRoot = depotLocation.getLocalRoot();
		IPath root = null;
		if(localRoot != null)
		{
			localRoot = ExpandingProperties.expand(properties, localRoot, 0);
			root = new Path(localRoot);
		}
		IPath depotPath = depotLocation.getDepotPath();
		IPath result = depotLocation.getMappingForDepot(depotPath);

		if(result == null)
		{
			IVersionSelector vs = ci.getVersionMatch().getFixedVersionSelector();
			String branch = vs.isDefaultBranch() ? depotLocation.getDefaultBranch() : vs.getBranchName();

			result = new Path(ci.getName());
			if(branch != null)
				result = result.append(branch);
		}

		if(root != null)
			result = root.append(result);

		if(!result.isAbsolute())
			result = null;
		else
		{
			optional[0] = false;
		}
		return result;
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, NodeQuery nodeQuery, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		String urlString = provider.getURI(nodeQuery.getProperties());
		URI uri = DepotURI.createURI(urlString);

		DepotURI depotURI = new DepotURI(uri, null, nodeQuery.getProperties());
		return new VersionFinder(depotURI);
	}

	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new P4RemoteReader(this, providerMatch);
	}

	@Override
	public void prepareMaterialization(List<Materialization> mtr, MaterializationContext context, IProgressMonitor monitor)
	throws CoreException
	{
		List<ClientSpec> modifiedClients = new ArrayList<ClientSpec>();
		for(Materialization mi : mtr)
		{
			Resolution cr = mi.getResolution();
			ComponentRequest request = cr.getRequest();
			DepotURI depotLocation = getDepotLocation(cr, context.getProperties(request));
			ClientSpec client = this.getClient(depotLocation);
			client.addLocation(depotLocation.getDepotPath(), mi.getComponentLocation());
			if(context.getMaterializationSpec().getConflictResolution(request) == ConflictResolution.REPLACE)
				client.setClobber(true);
			modifiedClients.add(client);
		}
		for(ClientSpec modified : modifiedClients)
			modified.commitChanges();
	}

	@Override
	public void shareProject(IProject project, Resolution cr, MaterializationContext context, IProgressMonitor monitor)
	throws CoreException
	{
		if(P4WSADBridge.isPresent())
			P4WSADBridge.shareProject(project, getDepotLocation(cr, context.getProperties(cr.getRequest())));
	}

	public static DepotURI getDepotLocation(Resolution cr, Map<String,String> properties) throws CoreException
	{
		return new DepotURI(cr.getRepository(),
			cr.getVersionMatch().getFixedVersionSelector().getBranchName(),
			properties);
	}
}

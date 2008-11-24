/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Code 9 - ongoing development
 *     Cloudsmith - ongoing development
 *******************************************************************************/
package org.eclipse.buckminster.jnlp.p2.installer;

import java.net.URL;
import java.util.*;

import org.eclipse.buckminster.jnlp.p2.JNLPPlugin;
import org.eclipse.core.runtime.*;
import org.eclipse.equinox.internal.p2.core.helpers.ServiceHelper;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.director.IDirector;
import org.eclipse.equinox.internal.provisional.p2.director.ProfileChangeRequest;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfile;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.Query;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.*;

/**
 * This operation performs installation or update of an Eclipse-based product.
 */
@SuppressWarnings("restriction")
public class InstallUpdateProductOperation implements IInstallOperation {

	private IArtifactRepositoryManager m_artifactRepoMan;
	private BundleContext m_bundleContext;
	private IDirector m_director;
	private final InstallDescription m_installDescription;
	private boolean m_isInstall = true;
	private IMetadataRepositoryManager m_metadataRepoMan;
	private IProfileRegistry m_profileRegistry;
	private IStatus m_result;

	private ArrayList<ServiceReference> m_serviceReferences = new ArrayList<ServiceReference>();

	public InstallUpdateProductOperation(BundleContext context, InstallDescription description) {
		this.m_bundleContext = context;
		this.m_installDescription = description;
	}

	/**
	 * Determine what top level installable units should be installed by the director
	 */
	private IInstallableUnit[] computeUnitsToInstall() throws CoreException {
		ArrayList<IInstallableUnit> result = new ArrayList<IInstallableUnit>();
		VersionedName roots[] = m_installDescription.getRoots();
		for (int i = 0; i < roots.length; i++) {
			VersionedName root = roots[i];
			IInstallableUnit iu = findUnit(root);
			if (iu != null)
				result.add(iu);
		}
		return result.toArray(new IInstallableUnit[result.size()]);
	}

	/**
	 * This profile is being updated; return the units to uninstall from the profile.
	 */
	private IInstallableUnit[] computeUnitsToUninstall(IProfile p) {
		return (IInstallableUnit[]) p.query(InstallableUnitQuery.ANY, new Collector(), null).toArray(IInstallableUnit.class);
	}

	/**
	 * Create and return the profile into which units will be installed.
	 */
	private IProfile createProfile() throws ProvisionException {
		IProfile profile = getProfile();
		if (profile == null) {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put(IProfile.PROP_INSTALL_FOLDER, m_installDescription.getInstallLocation().toString());
			EnvironmentInfo info = (EnvironmentInfo) ServiceHelper.getService(JNLPPlugin.getDefault().getContext(), EnvironmentInfo.class.getName());
			String env = "osgi.os=" + info.getOS() + ",osgi.ws=" + info.getWS() + ",osgi.arch=" + info.getOSArch(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			properties.put(IProfile.PROP_ENVIRONMENTS, env);
			properties.putAll(m_installDescription.getProfileProperties());
			IPath location = m_installDescription.getBundleLocation();
			if (location != null)
				properties.put(IProfile.PROP_CACHE, location.toOSString());
			profile = m_profileRegistry.addProfile(getProfileId(), properties);
		}
		return profile;
	}

	/**
	 * Performs the actual product install or update.
	 */
	private void doInstall(SubMonitor monitor) throws CoreException {
		prepareMetadataRepositories();
		prepareArtifactRepositories();
		IProfile p = createProfile();
		IInstallableUnit[] toInstall = computeUnitsToInstall();
		monitor.worked(5);

		IStatus s;
		ProfileChangeRequest request = new ProfileChangeRequest(p);
		if (m_isInstall) {
			monitor.setTaskName(NLS.bind(Messages.Op_Installing, m_installDescription.getProductName()));
			request.addInstallableUnits(toInstall);
			s = m_director.provision(request, null, monitor.newChild(90));
		} else {
			monitor.setTaskName(NLS.bind(Messages.Op_Updating, m_installDescription.getProductName()));
			IInstallableUnit[] toUninstall = computeUnitsToUninstall(p);
			request.removeInstallableUnits(toUninstall);
			request.addInstallableUnits(toInstall);
			s = m_director.provision(request, null, monitor.newChild(90));
		}
		if (!s.isOK())
			throw new CoreException(s);
	}

	/**
	 * Returns an exception of severity error with the given error message.
	 */
	private CoreException fail(String message) {
		return fail(message, null);
	}

	/**
	 * Returns an exception of severity error with the given error message.
	 */
	private CoreException fail(String message, Throwable throwable) {
		return new CoreException(new Status(IStatus.ERROR, JNLPPlugin.JNLP_P2, message, throwable));
	}

	/**
	 * Finds and returns the installable unit with the given id, and optionally the
	 * given version.
	 */
	private IInstallableUnit findUnit(VersionedName spec) throws CoreException {
		String id = spec.getId();
		if (id == null)
			throw fail(Messages.Op_NoId);
		Version version = spec.getVersion();
		VersionRange range = VersionRange.emptyRange;
		if (version != null && !version.equals(Version.emptyVersion))
			range = new VersionRange(version, true, version, true);
		Query query = new InstallableUnitQuery(id, range);
		Collector collector = new Collector();
		Iterator<?> matches = m_metadataRepoMan.query(query, collector, null).iterator();
		// pick the newest match
		IInstallableUnit newest = null;
		while (matches.hasNext()) {
			IInstallableUnit candidate = (IInstallableUnit) matches.next();
			if (newest == null || (newest.getVersion().compareTo(candidate.getVersion()) < 0))
				newest = candidate;
		}
		if (newest == null)
			throw fail(Messages.Op_IUNotFound + id);
		return newest;
	}

	/**
	 * Returns the profile being installed into.
	 */
	private IProfile getProfile() {
		return m_profileRegistry.getProfile(getProfileId());
	}

	/**
	 * Returns the id of the profile to use for install/update based on this operation's install description.
	 */
	private String getProfileId() {
		IPath location = m_installDescription.getInstallLocation();
		if (location != null)
			return location.toString();
		return m_installDescription.getProductName();
	}

	/**
	 * Returns the result of the install operation, or <code>null</code> if
	 * no install operation has been run.
	 */
	public IStatus getResult() {
		return m_result;
	}

	private Object getService(String name) throws CoreException {
		ServiceReference ref = m_bundleContext.getServiceReference(name);
		if (ref == null)
			throw fail(Messages.Op_NoService + name);
		Object service = m_bundleContext.getService(ref);
		if (service == null)
			throw fail(Messages.Op_NoServiceImpl + name);
		m_serviceReferences.add(ref);
		return service;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.internal.provisional.p2.installer.IInstallOperation#install(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public IStatus install(IProgressMonitor pm) {
		SubMonitor monitor = SubMonitor.convert(pm, Messages.Op_Preparing, 100);
		try {
			try {
				preInstall();
				m_isInstall = getProfile() == null;
				doInstall(monitor);
				m_result = new Status(IStatus.OK, JNLPPlugin.JNLP_P2, m_isInstall ? Messages.Op_InstallComplete : Messages.Op_UpdateComplete, null);
				monitor.setTaskName(Messages.Op_Cleanup);
			} finally {
				postInstall();
			}
		} catch (CoreException e) {
			m_result = e.getStatus();
		} finally {
			monitor.done();
		}
		return m_result;
	}

	/**
	 * Returns whether this operation represents the product being installed
	 * for the first time, in a new profile.
	 */
	public boolean isFirstInstall() {
		return m_isInstall;
	}

	private void postInstall() {
		for (Iterator<ServiceReference> it = m_serviceReferences.iterator(); it.hasNext();) {
			ServiceReference sr = it.next();
			m_bundleContext.ungetService(sr);
		}
		m_serviceReferences.clear();
	}

	private void preInstall() throws CoreException {
		//obtain required services
		m_serviceReferences.clear();
		m_director = (IDirector) getService(IDirector.class.getName());
		m_metadataRepoMan = (IMetadataRepositoryManager) getService(IMetadataRepositoryManager.class.getName());
		m_artifactRepoMan = (IArtifactRepositoryManager) getService(IArtifactRepositoryManager.class.getName());
		m_profileRegistry = (IProfileRegistry) getService(IProfileRegistry.class.getName());
	}

	private void prepareArtifactRepositories() throws ProvisionException {
		URL[] repos = m_installDescription.getArtifactRepositories();
		if (repos == null)
			return;
		for (int i = 0; i < repos.length; i++)
			m_artifactRepoMan.loadRepository(repos[i], null);
	}

	private void prepareMetadataRepositories() throws ProvisionException {
		URL[] repos = m_installDescription.getMetadataRepositories();
		if (repos == null)
			return;
		for (int i = 0; i < repos.length; i++)
			m_metadataRepoMan.loadRepository(repos[i], null);
	}
}

/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.File;

import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.internal.provisional.frameworkadmin.BundleInfo;
import org.eclipse.equinox.internal.provisional.frameworkadmin.ConfigData;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;
import org.eclipse.equinox.p2.publisher.PublisherInfo;
import org.eclipse.equinox.p2.publisher.PublisherResult;
import org.eclipse.equinox.p2.publisher.eclipse.ConfigAdvice;
import org.eclipse.equinox.p2.publisher.eclipse.EquinoxLauncherCUAction;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.internal.build.IPDEBuildConstants;

/**
 * Action that generates version adjusted products
 */
@SuppressWarnings("restriction")
public class ProductAction extends org.eclipse.equinox.p2.publisher.eclipse.ProductAction implements IPDEBuildConstants {
	public ProductAction(String src, IProductDescriptor productDesc, String flvor, File exeFeatureLocation) {
		super(src, new ProductVersionPatcher(productDesc), flvor, exeFeatureLocation);
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor) {
		((ProductVersionPatcher) product).setQueryable(results);

		IPublisherResult innerResult = new PublisherResult();
		PublisherInfo innerInfo = new PublisherInfo();
		String[] configs = publisherInfo.getConfigurations();
		if (executablesFeatureLocation == null) {
			// We can only create one single configuration and that's the one of
			// the running platform
			//
			Logger log = Buckminster.getLogger();
			String availableConfig = Platform.getWS() + '.' + Platform.getOS() + '.' + Platform.getOSArch();
			boolean warningsPrinted = false;
			boolean availConfigFound = false;
			for (String config : configs) {
				if (config.equals(availableConfig)) {
					availConfigFound = true;
					continue;
				}
				log.warning(NLS.bind(Messages.Missing_exe_launcher_for_config_0, config));
				warningsPrinted = true;
			}
			if (warningsPrinted)
				log.warning(Messages.Suggest_install_launchers_feature);

			if (!availConfigFound)
				return Status.OK_STATUS;

			innerInfo.setConfigurations(new String[] { availableConfig });
			if (Platform.inDevelopmentMode()) {
				String ilProp = System.getProperty("eclipse.host.location"); //$NON-NLS-1$
				if (ilProp == null) {
					log.warning(Messages.Please_use_selfhost_vmargs);
				} else
					source = ilProp;
			} else
				source = TargetPlatform.getPlatformInstallLocation().getAbsolutePath();
		} else
			innerInfo.setConfigurations(configs);

		innerInfo.setArtifactOptions(publisherInfo.getArtifactOptions());
		innerInfo.setArtifactRepository(publisherInfo.getArtifactRepository());
		innerInfo.setMetadataRepository(publisherInfo.getMetadataRepository());
		innerInfo.setContextArtifactRepository(publisherInfo.getContextArtifactRepository());
		innerInfo.setContextMetadataRepository(innerInfo.getContextMetadataRepository());

		// The inner result must see the launchers since the
		// EquinoxLauncherCUAction created by the
		// ApplicationLauncherAction must find them in order to generate the
		// correct CU's
		//
		ConfigData dfltStartInfos = new ConfigData(null, null, null, null);
		for (Object iu : results.getIUs(null, IPublisherResult.ROOT)) {
			IInstallableUnit tmp = (IInstallableUnit) iu;
			if (tmp.getId().startsWith(EquinoxLauncherCUAction.ORG_ECLIPSE_EQUINOX_LAUNCHER)) {
				innerResult.addIU(tmp, IPublisherResult.ROOT);
				continue;
			}

			for (BundleInfo bi : product.getBundleInfos()) {
				if (tmp.getId().equals(bi.getSymbolicName())) {
					innerResult.addIU(tmp, IPublisherResult.ROOT);
					break;
				}
			}

			for (BundleInfo bi : getDefaultStartInfo()) {
				if (tmp.getId().equals(bi.getSymbolicName())) {
					innerResult.addIU(tmp, IPublisherResult.ROOT);
					dfltStartInfos.addBundle(bi);
					break;
				}
			}
		}
		if (dfltStartInfos.getBundles().length > 0)
			for (String configSpec : innerInfo.getConfigurations())
				innerInfo.addAdvice(new ConfigAdvice(dfltStartInfos, configSpec));

		IStatus status = super.perform(innerInfo, innerResult, monitor);
		if (status.getSeverity() != IStatus.ERROR)
			results.merge(innerResult, IPublisherResult.MERGE_MATCHING);
		return status;
	}

	@Override
	protected IPublisherAction[] createActions(IPublisherResult results) {
		IPublisherAction[] actions = super.createActions(results);
		if (getExecutablesLocation() == null) {
			IPublisherAction[] newActions = new IPublisherAction[actions.length + 1];
			System.arraycopy(actions, 0, newActions, 1, actions.length);
			actions = newActions;
			actions[0] = createApplicationExecutableAction(info.getConfigurations());
		}
		return actions;
	}

	@Override
	protected IPublisherAction createApplicationExecutableAction(String[] configSpecs) {
		return new ApplicationLauncherAction(id, version, flavor, executableName, getExecutablesLocation(), configSpecs);
	}

	private BundleInfo[] getDefaultStartInfo() {
		BundleInfo[] defaults = new BundleInfo[6];
		defaults[0] = new BundleInfo(BUNDLE_SIMPLE_CONFIGURATOR, null, null, 1, true);
		defaults[1] = new BundleInfo(BUNDLE_EQUINOX_COMMON, null, null, 2, true);
		defaults[2] = new BundleInfo(BUNDLE_OSGI, null, null, -1, true);
		defaults[3] = new BundleInfo(BUNDLE_UPDATE_CONFIGURATOR, null, null, 4, true);
		defaults[4] = new BundleInfo(BUNDLE_CORE_RUNTIME, null, null, 4, true);
		defaults[5] = new BundleInfo(BUNDLE_DS, null, null, 1, true);
		return defaults;
	}
}

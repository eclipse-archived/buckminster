/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.internal.actor;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.internal.model.EditableFeatureModel;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.pde.internal.core.ifeature.IFeature;

@SuppressWarnings("restriction")
public class OwnerVersionExtractor extends AbstractActor {
	public static final String ID = "ownerVersionExtractor"; //$NON-NLS-1$

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException {
		CSpec bundle = ctx.getAction().getCSpec();
		for (CSpec cspec : ctx.getAllFoundCSpecs()) {
			if (!IComponentType.ECLIPSE_FEATURE.equals(cspec.getComponentTypeID()))
				// We're only interested in features
				continue;

			// We need to see if this feature has our plugin as the branding
			// plugin
			IPath location = cspec.getComponentLocation();
			if (location == null || !location.hasTrailingSeparator())
				// Not a folder so we can't really examine it
				continue;

			boolean found = false;
			for (ComponentRequest dep : cspec.getDependencies()) {
				if (dep.designates(bundle.getComponentIdentifier())) {
					found = true;
					break;
				}
			}
			if (!found)
				// We're only interested if the feature has a requirement on our
				// bundle.
				continue;

			// Is our plugin the branding plugin of this feature?
			EditableFeatureModel featureModel = new EditableFeatureModel(location.append(IPDEConstants.FEATURE_MANIFEST).toFile());
			featureModel.load();
			IFeature feature = featureModel.getFeature();
			if (!bundle.getName().equals(feature.getPlugin()))
				// No, our plugin wasn't the branding plugin.
				continue;

			// Looks like we found what we were looking for.
			Version v = cspec.getVersion();
			if (v == null)
				continue;

			// Strip qualifier
			v = VersionHelper.replaceQualifier(v, null);
			ctx.getGlobalContext().addProperty(bundle.getName() + ".unqualified.owner.version", v.toString()); //$NON-NLS-1$
			break;
		}
		return Status.OK_STATUS;
	}
}

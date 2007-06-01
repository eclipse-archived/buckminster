/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.internal.actor;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.pde.internal.build.IPDEBuildConstants;
import org.eclipse.pde.internal.build.IXMLConstants;
import org.eclipse.pde.internal.build.builder.AbstractBuildScriptGenerator;
import org.eclipse.pde.internal.build.site.BuildTimeSiteFactory;
import org.eclipse.pde.internal.core.PDEState;
import org.eclipse.pde.internal.core.TargetPlatformHelper;

/**
 * This abstract actor implements functionality common to all PDE script generating
 * actors.
 *
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public abstract class ScriptGenerator extends AbstractActor implements IXMLConstants,  IPDEBuildConstants
{
	public static final String PROPERTY_BUILDING_OSGI = "buildingOSGi";
	public static final String PROPERTY_SIGN_JARS = "signJars";

	/**
	 * Extracts the full path of the generated build script from the products of the
	 * invoking action.
	 *
	 * @param ctx The context used for the action invocation
	 * @return The full filesystem path of the build script
	 * @throws CoreException
	 */
	protected IPath getScriptPath(IActionContext ctx) throws CoreException
	{
		Action action = ctx.getAction();
		PathGroup[] groups = action.getPathGroups(ctx);
		if(!(groups.length == 1 || groups[0].getPaths().length == 1))
			throw new BuckminsterException("The " + this.getId() + " must produce exactly one path");

		PathGroup product = groups[0];
		IPath fullPath = product.getBase().append(product.getPaths()[0]);

		// Ensure that the directory exists
		//
		fullPath.removeLastSegments(1).toFile().mkdirs();
		return fullPath;
	}

	/**
	 * Perform basic build script generator initialization. This involves assigning a correctly
	 * initialized {@link BuildTimeSiteFactory} that reflects the current {@link PDEState} and
	 * setting some sane defaults.
	 *
	 * @param generator The generator to initialize
	 * @param ctx The context used for the action invocation
	 * @throws CoreException
	 */
	protected void initScript(AbstractBuildScriptGenerator generator, IActionContext ctx) throws CoreException
	{
		PDEState pdeState = TargetPlatformHelper.getPDEState();
		generator.setWorkingDirectory("somedummy"); // Avoid NPE
		generator.setStateExtraData(TargetPlatformHelper.getBundleClasspaths(pdeState), TargetPlatformHelper.getPatchMap(pdeState));
		generator.setPluginPath(TargetPlatformHelper.getFeaturePaths());
		generator.setPDEState(TargetPlatformHelper.getState());
		generator.getSite(true);

		generator.setBuildingOSGi(getBooleanProperty(ctx.getProperties(), PROPERTY_BUILDING_OSGI, true));
		generator.setReportResolutionErrors(true);
		generator.setIgnoreMissingPropertiesFile(true);
		generator.includePlatformIndependent(true);
		generator.setCompiledElements(generator.getCompiledElements());
	}
}

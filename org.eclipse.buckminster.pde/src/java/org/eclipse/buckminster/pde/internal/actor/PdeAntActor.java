/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.internal.actor;

import java.io.File;
import java.util.Map;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.jdt.internal.actor.JdtAntActor;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.build.IPDEBuildConstants;
import org.eclipse.pde.internal.build.IXMLConstants;
import org.eclipse.pde.internal.core.exports.FeatureExportInfo;
import org.eclipse.pde.internal.core.exports.FeatureExportOperation;

/**
 * The PdeAntActor must always have a prerequisite to a script generator. That prerequisite must
 * have the alias {@link #ALIAS_GENERATED_SCRIPT}. The artifact of the referenced attribute will be
 * used as build script for this actor.
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class PdeAntActor extends JdtAntActor implements IXMLConstants, IPDEBuildConstants
{
	@SuppressWarnings("hiding")
	public static final String ACTOR_ID = "pde.ant";

	public static final String ALIAS_GENERATED_SCRIPT = "generated.script";

	public static final String PROPERTY_CATEGORY = "category";

	/**
	 * A subclass that enables us to set some important information and obtain build properties from
	 * the otherwise protected createAntBuildProperties of the super class.
	 */
	static class AntPropertySetter extends FeatureExportOperation
	{
		public AntPropertySetter(FeatureExportInfo info)
		{
			super(info);
		}

		@SuppressWarnings("unchecked")
		public Map<String, String> getAntProperties()
		{
			return this.createAntBuildProperties(TargetPlatform.getOS(), TargetPlatform.getWS(),
				TargetPlatform.getOSArch());
		}
	}

	@Override
	protected String getBuildFileProperty(IActionContext ctx) throws CoreException
	{
		Action action = ctx.getAction();
		Prerequisite scriptPrereq = null;

		for(Prerequisite pq : action.getPrerequisiteGroup().getPrerequisites())
		{
			if(ALIAS_GENERATED_SCRIPT.equals(pq.getAlias()))
			{
				scriptPrereq = pq;
				break;
			}
		}

		if(scriptPrereq == null)
			throw new BuckminsterException("Actor " + this.getId() + " requires a prerequisite with alias "
				+ ALIAS_GENERATED_SCRIPT);

		Attribute scriptAttrib = scriptPrereq.getReferencedAttribute(action.getCSpec(), ctx);
		PathGroup[] groups = scriptAttrib.getPathGroups(ctx);
		if(!(groups.length == 1 || groups[0].getPaths().length == 1))
			throw new BuckminsterException("The " + this.getId() + " requires a prerequisite with alias "
				+ ALIAS_GENERATED_SCRIPT + " that has exactly one path");

		PathGroup scriptProd = groups[0];
		return scriptProd.getBase().append(scriptProd.getPaths()[0]).toOSString();
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		Map<String, String> props = ctx.getProperties();
		AntPropertySetter aps = new AntPropertySetter(new FeatureExportInfo());
		Map<String, String> genProps = aps.getAntProperties();

		String buildTempDirStr = props.get(KeyConstants.ACTION_TEMP);
		File buildTempDir = ctx.makeAbsolute(new File(buildTempDirStr));
		genProps.put(PROPERTY_BUILD_TEMP, buildTempDirStr);
		buildTempDir.mkdirs();

		IPath productBase = ctx.getAction().getProductBase();
		if(productBase == null)
			throw new BuckminsterException("Actor " + this.getId() + " requires a product that has a base");

		File outputDir = ctx.makeAbsolute(new File(ExpandingProperties.expand(ctx.getProperties(), productBase.toOSString(), 0)));
		String outputDirStr = outputDir.getPath();

		File tempDir = new File(outputDir, PROPERTY_TEMP_FOLDER);
		String tempDirStr = tempDir.getPath();
		genProps.put(PROPERTY_TEMP_FOLDER, tempDirStr);
		tempDir.mkdirs();

		String category = ctx.getAction().getCSpec().getCategory();
		if(KeyConstants.PLUGIN_CATEGORY.equalsIgnoreCase(category))
		{
			genProps.put(PROPERTY_PLUGIN_DESTINATION, outputDirStr);
			outputDir.mkdirs();
		}

		if(KeyConstants.FEATURE_CATEGORY.equalsIgnoreCase(category))
		{
			genProps.put(PROPERTY_FEATURE_DESTINATION, outputDirStr);
			genProps.put(PROPERTY_FEATURE_TEMP_FOLDER, tempDirStr);
			outputDir.mkdirs();
		}

		props.putAll(genProps);

		monitor.beginTask(null, 100);
		try
		{
			return super.internalPerform(ctx, MonitorUtils.subMonitor(monitor, 95));
		}
		finally
		{
			FileUtils.deleteRecursive(tempDir, MonitorUtils.subMonitor(monitor, 5));
			ctx.scheduleRemoval(new Path(buildTempDir.toString()));
			monitor.done();
		}
	}
}

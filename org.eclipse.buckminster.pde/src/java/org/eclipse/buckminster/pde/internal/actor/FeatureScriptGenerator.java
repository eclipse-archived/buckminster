/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.internal.actor;

import java.util.Map;

import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.pde.internal.build.AbstractScriptGenerator;
import org.eclipse.pde.internal.build.AssemblyInformation;
import org.eclipse.pde.internal.build.Config;
import org.eclipse.pde.internal.build.SourceFeatureInformation;
import org.eclipse.pde.internal.build.builder.FeatureBuildScriptGenerator;

/**
 * This actor generates an ant script that builds various aspecs of a feature.
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class FeatureScriptGenerator extends ScriptGenerator
{
	public static final String ACTOR_ID = "feature.script.generator";

	public static final String ATTRIBUTE_GENERATED_FEATURE_SCRIPT = "generated.feature.script";

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		Map<String, String> properties = ctx.getProperties();
		IPath scriptPath = getScriptPath(ctx);
		final String scriptName = scriptPath.lastSegment();
		final IPath buildFolder = scriptPath.removeLastSegments(1);

		AbstractScriptGenerator.setConfigInfo(Config.genericConfig().toString(","));
		ComponentIdentifier cid = ctx.getAction().getCSpec().getComponentIdentifier();
		FeatureBuildScriptGenerator generator = new FeatureBuildScriptGenerator(cid.getName(),
			cid.getVersion().toString(), new AssemblyInformation())
		{
			// We override this method since a) we don't want the generated file to end up
			// in the feature source directory and b) we don't want it to be called "build.xml"
			//
			@Override
			public void openScript(String oldLocation, String oldName) throws CoreException
			{
				super.openScript(buildFolder.toOSString(), scriptName);
			}

			// We need to trick the FeatureBuildScript into believing that it doesn't
			// have any children even if we set the setAnalyzeChildren to false. This
			// method will get called anyway. All we want is an empty but callable
			// target.
			//
			@Override
			protected void generateAllPluginsTarget() throws CoreException
			{
				script.println();
				script.printTargetDeclaration(TARGET_ALL_PLUGINS, TARGET_INIT, null, null, null);
				script.printTargetEnd();
			}
		};

		initScript(generator, ctx, createStateFromPrerequisites(ctx, monitor));
		generator.setSignJars(getBooleanProperty(properties, PROPERTY_SIGN_JARS, false));
		generator.setGenerateIncludedFeatures(false);
		generator.setAnalyseChildren(false);
		generator.setSourceFeatureGeneration(false);
		generator.setBinaryFeatureGeneration(true);
		generator.setScriptGeneration(true);
		generator.setSourceToGather(new SourceFeatureInformation());
		generator.setGenerateVersionSuffix(false);
		generator.setWorkingDirectory(buildFolder.toOSString());

		boolean success = false;
		try
		{
			generator.generate();
			success = true;
		}
		finally
		{
			if(!success)
				scriptPath.toFile().delete();
		}
		return Status.OK_STATUS;
	}
}

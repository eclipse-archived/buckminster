/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.pde.test;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Map;

import org.eclipse.buckminster.ant.commands.Ant;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.internal.build.AssemblyInformation;
import org.eclipse.pde.internal.build.IPDEBuildConstants;
import org.eclipse.pde.internal.build.IXMLConstants;
import org.eclipse.pde.internal.build.PDEUIStateWrapper;
import org.eclipse.pde.internal.build.SourceFeatureInformation;
import org.eclipse.pde.internal.build.builder.FeatureBuildScriptGenerator;
import org.eclipse.pde.internal.build.builder.ModelBuildScriptGenerator;
import org.eclipse.pde.internal.build.site.BuildTimeSiteFactory;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PDEState;
import org.eclipse.pde.internal.core.TargetPlatform;
import org.eclipse.pde.internal.core.exports.FeatureExportInfo;
import org.eclipse.pde.internal.core.exports.FeatureExportOperation;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class ScriptGenTest extends PDETestCase implements IXMLConstants, IPDEBuildConstants
{
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

	public void testFeatureBuilder() throws Exception
	{
		PDEState pdeState = PDECore.getDefault().getModelManager().getState();
		BuildTimeSiteFactory factory = new BuildTimeSiteFactory();
		PDEUIStateWrapper wrapper = new PDEUIStateWrapper();
		wrapper.setState(pdeState.getState());
		wrapper.setNextId(pdeState.getNextId());
		wrapper.setExtraData(TargetPlatform.getBundleClasspaths(pdeState),
			TargetPlatform.getPatchMap(pdeState));
		factory.setSitePaths(TargetPlatform.getFeaturePaths());
		factory.setInitialState(wrapper);

		AssemblyInformation assemblyInfo = new AssemblyInformation();
		FeatureBuildScriptGenerator generator = new FeatureBuildScriptGenerator(
			"org.eclipse.buckminster.pde.feature", "1.0.0.v20061006", assemblyInfo)
		{
			// We override this method since a) we don't want the generated file to end up
			// in the feature source directory and b) we don't want it to be called "build.xml"
			//
			@Override
			public void openScript(String oldLocation, String oldName) throws CoreException
			{
				super.openScript("c:\\temp\\testbuild", "build-feature.xml");
			}
		};

		generator.setGenerateIncludedFeatures(false);
		generator.setAnalyseChildren(false);
		generator.setSourceFeatureGeneration(false);
		generator.setBinaryFeatureGeneration(true);
		generator.setScriptGeneration(true);
		generator.setBuildSiteFactory(factory);
		generator.setSourceToGather(new SourceFeatureInformation());
		generator.setCompiledElements(generator.getCompiledElements());
		generator.setBuildingOSGi(true);
		generator.includePlatformIndependent(false);
		generator.setReportResolutionErrors(true);
		generator.setIgnoreMissingPropertiesFile(true);
		generator.setSignJars(false);
		generator.setGenerateJnlp(false);
		generator.setGenerateVersionSuffix(false);
		generator.setWorkingDirectory("c:\\temp\\testbuild");
		generator.generate();
	}

	public void testPluginBuilder() throws Exception
	{
		PDEState pdeState = PDECore.getDefault().getModelManager().getState();
		BuildTimeSiteFactory factory = new BuildTimeSiteFactory();
		PDEUIStateWrapper wrapper = new PDEUIStateWrapper();
		wrapper.setState(pdeState.getState());
		wrapper.setNextId(pdeState.getNextId());
		wrapper.setExtraData(TargetPlatform.getBundleClasspaths(pdeState),
			TargetPlatform.getPatchMap(pdeState));
		factory.setSitePaths(TargetPlatform.getFeaturePaths());
		factory.setInitialState(wrapper);

		String scriptFile = "build-plugin.xml";

		ModelBuildScriptGenerator generator = new ModelBuildScriptGenerator();
		generator.setReportResolutionErrors(true);
		generator.setIgnoreMissingPropertiesFile(true);
		generator.setBuildScriptFileName(scriptFile);
		generator.setCompiledElements(generator.getCompiledElements());
		generator.setBuildingOSGi(true);
		generator.setSignJars(false);
		generator.setBuildSiteFactory(factory);
		generator.setModelId("org.eclipse.buckminster.pde");
		generator.generate();

		// Move generated files into a new location
		//
		File baseDir = new Path(generator.getModel().getLocation()).toFile();
		File outputDir = new File("c:\\temp\\testbuild");
		File buildFolder = new File(outputDir, "build");

		for(String rootFile : baseDir.list())
		{
			if(rootFile.startsWith("javaCompiler.") && rootFile.endsWith(".args"))
			{
				File source = new File(baseDir, rootFile);
				FileUtils.copyFile(source, buildFolder, rootFile, null);
				source.delete();
			}
		}

		// While moving the script, we also replace pointers to moved files
		//
		LineNumberReader reader = null;
		PrintWriter writer = null;
		File movedScriptFile = new File(baseDir, scriptFile);
		File targetScriptFile = new File(buildFolder, scriptFile);
		try
		{
			reader = new LineNumberReader(new FileReader(movedScriptFile));
			writer = new PrintWriter(targetScriptFile);
			String line;
			while((line = reader.readLine()) != null)
				writer.println(line.replace("\"@${basedir}/javaCompiler.", "\"@${" + PROPERTY_BUILD_TEMP
					+ "}/javaCompiler."));
		}
		finally
		{
			IOUtils.close(reader);
			IOUtils.close(writer);
			movedScriptFile.delete();
		}

		FeatureExportInfo einfo = new FeatureExportInfo();
		AntPropertySetter aps = new AntPropertySetter(einfo);
		Ant cmd = new Ant();
		cmd.setBaseDir(new Path(baseDir.toString()));
		cmd.setScriptFile(new Path(targetScriptFile.toString()));
		cmd.addProperties(aps.getAntProperties());
		cmd.addProperty(PROPERTY_BUILD_TEMP, buildFolder.getAbsolutePath());
		cmd.addProperty(PROPERTY_TEMP_FOLDER, new File(outputDir, PROPERTY_TEMP_FOLDER).getAbsolutePath());

		File pluginsFolder = new File(outputDir, DEFAULT_PLUGIN_LOCATION);
		pluginsFolder.mkdirs();
		cmd.addProperty(PROPERTY_PLUGIN_DESTINATION, pluginsFolder.getAbsolutePath());
		cmd.addBuildTarget("build.update.jar");
		cmd.run("ant");
	}
}

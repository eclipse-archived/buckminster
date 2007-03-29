/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.internal;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.resolver.State;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.build.AbstractScriptGenerator;
import org.eclipse.pde.internal.build.BrandingIron;
import org.eclipse.pde.internal.build.PDEUIStateWrapper;
import org.eclipse.pde.internal.build.ProductGenerator;
import org.eclipse.pde.internal.build.site.BuildTimeSiteFactory;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.TargetPlatformHelper;
import org.eclipse.pde.internal.core.iproduct.ILauncherInfo;
import org.eclipse.pde.internal.core.iproduct.IProduct;
import org.eclipse.pde.internal.core.product.ProductModel;

/**
 * Ant task that creates a product base. The base in this case is a product stripped from all features and plugins.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CreateProductBase
{
	private static final String LAUNCHER_FEATURE_NAME = "org.eclipse.platform.launchers";

	private static final String ROOT = "root";

	private final String m_arch;

	private Map<String,String> m_hints;

	private final String m_os;

	private final IPath m_outputDir;

	private final IProduct m_product;

	private final File m_productFile;

	private final IPath m_productRootFiles;

	private final IPath m_targetLocation;

	private final IPath m_generatedDir;

	private final String m_ws;

	public CreateProductBase(String os, String ws, String arch, File productFile, IPath outputDir, IPath targetLocation)
	throws CoreException
	{
		if(outputDir == null)
			throw new IllegalArgumentException("outputDir cannot be null");
		m_outputDir = outputDir.addTrailingSeparator();

		if(targetLocation == null)
			throw new IllegalArgumentException("targetLocation cannot be null");
		m_targetLocation = targetLocation.addTrailingSeparator();

		if(productFile == null)
			throw new IllegalArgumentException("productFile cannot be null");

		InputStream pfInput = null;
		try
		{
			m_productFile = productFile;
			pfInput = new BufferedInputStream(new FileInputStream(productFile));
			ProductModel productModel = new ProductModel();
			productModel.load(pfInput, false);
			m_product = productModel.getProduct();
		}
		catch(IOException e)
		{
			throw new BuckminsterException("Unable to read file " + productFile);
		}
		finally
		{
			IOUtils.close(pfInput);
		}


		m_os = os == null ? TargetPlatform.getOS() : os;
		m_ws = ws == null ? TargetPlatform.getWS() : ws;
		m_arch = arch == null ? TargetPlatform.getOSArch() : arch;

		m_productRootFiles = m_outputDir.append("productRootFiles").addTrailingSeparator();
		m_generatedDir = m_productRootFiles.append(m_os + '.' + m_ws + '.' + m_arch).addTrailingSeparator();
	}

	public void execute() throws Exception
	{
		m_hints = null;

		File outputDir = m_outputDir.toFile();
		if(!outputDir.isDirectory())
			throw new BuckminsterException(outputDir + "is not a directory");

		// Check for a launchers feature (included in the delta-pack)
		//
		File launcherFeatureDir = null;
		File features = m_targetLocation.append(IPDEConstants.FEATURES_FOLDER).toFile();
		String[] featureFolders = features.list();
		if(featureFolders != null)
		{
			for(String featureFolder : featureFolders)
			{
				if(featureFolder.startsWith(LAUNCHER_FEATURE_NAME + '_'))
				{
					File featureDir = new File(features, featureFolder);
					File buildScript = new File(featureDir, IPDEConstants.BUILD_PROPERTIES_FILE);
					if(buildScript.exists())
						launcherFeatureDir = featureDir;
					break;
				}
			}
		}

		AbstractScriptGenerator.setConfigInfo(m_os + ',' + m_ws + ',' + m_arch);

		if(launcherFeatureDir == null)
			this.getInstalledRootFiles();
		else
			this.processFeatureBuildProperties(launcherFeatureDir);

		// What this has to do with UI I don't know...
		//
		PDEUIStateWrapper stateWrapper = new PDEUIStateWrapper();
		stateWrapper.setState(this.getState());

		// Initialize a BuildTimeSiteFactory
		//
		BuildTimeSiteFactory siteFactory = new BuildTimeSiteFactory();
		siteFactory.setSitePaths(TargetPlatformHelper.getFeaturePaths());
		siteFactory.setInitialState(stateWrapper);

		// Generate the configuration/config.ini, .eclipseproduct, <launcher>.ini
		//
		ProductGenerator pd = new ProductGenerator();
		pd.setBuildingOSGi(true);
		pd.setBuildProperties(System.getProperties());
		pd.setRoot(m_outputDir.toOSString());
		pd.setWorkingDirectory(new Path(m_productFile.getParent()).addTrailingSeparator().toOSString());
		pd.setProduct(m_productFile.toString());
		pd.setBuildSiteFactory(siteFactory);
		pd.generate();

		// Copy the generated files to their final destination and delete
		// the temporary used by the ProductGenerator
		//
		IProgressMonitor nullMonitor = new NullProgressMonitor();
		FileUtils.deepCopyUnchecked(m_generatedDir.toFile(), m_outputDir.toFile(), nullMonitor);
		FileUtils.deleteRecursive(m_productRootFiles.toFile(), nullMonitor);

		ILauncherInfo info = m_product.getLauncherInfo();
		String launcherName = info.getLauncherName();

		// We have special filename used by headless launchers that doesn't need
		// a binary launcher with spashscreen and all.
		//
		if(!"_removethisfile".equals(launcherName))
			this.createLauncher(info);

		if(!"eclipse".equals(launcherName))
			//
			// Remove the default launcher
			//
			m_outputDir.append(getPlatformLaucherName("eclipse")).toFile().delete();
	}

	private void createLauncher(ILauncherInfo info) throws Exception
	{
		BrandingIron bi = new BrandingIron();
		bi.setName(info.getLauncherName());
		String images = null;
		if(m_os.equals("win32"))
		{
			images = getWin32Images(info);
		}
		else if(m_os.equals("solaris"))
		{
			images = getSolarisImages(info);
		}
		else if(m_os.equals("linux"))
		{
			images = getExpandedPath(info.getIconPath(ILauncherInfo.LINUX_ICON));
		}
		else if(m_os.equals("macosx"))
		{
			images = getExpandedPath(info.getIconPath(ILauncherInfo.MACOSX_ICON));
		}

		bi.setOS(m_os);
		bi.setRoot(m_outputDir.toOSString());
		if(images != null)
			bi.setIcons(images);
		bi.brand();

		addChmodHint("755", getPlatformLaucherName(info.getLauncherName()));
	}

	private String getPlatformLaucherName(String launcherName)
	{
		if("win32".equals(m_os))
			launcherName += ".exe";
		return launcherName;
	}

	public Map<String,String> getHints()
	{
		if(m_hints == null || m_hints.size() == 0)
			return Collections.emptyMap();

		Map<String,String> hints = new HashMap<String,String>();
		StringBuilder bld = new StringBuilder(100);
		bld.append(Attribute.INSTALLER_HINT_PREFIX);
		int pfLen = Attribute.INSTALLER_HINT_PREFIX.length();
		for(Map.Entry<String, String> hint : m_hints.entrySet())
		{
			bld.setLength(pfLen);
			bld.append(hint.getKey());
			bld.append('.');
			bld.append(m_product.getName());
			hints.put(bld.toString(), hint.getValue());
		}
		return hints;
	}

	private static void appendExpandedPath(StringBuilder builder, String path)
	{
		path = getExpandedPath(path);
		if(path != null)
		{
			if(builder.length() > 0)
				builder.append(',');
			builder.append(path);
		}
	}

	private static String getExpandedPath(String path)
	{
		String fullPath = null;
		if(path != null && path.length() > 0)
		{
			IResource resource = PDECore.getWorkspace().getRoot().findMember(new Path(path));
			if(resource != null)
				fullPath = resource.getLocation().toOSString();
		}
		return fullPath;
	}

	private static String getSolarisImages(ILauncherInfo info)
	{
		StringBuilder buffer = new StringBuilder();
		appendExpandedPath(buffer, info.getIconPath(ILauncherInfo.SOLARIS_LARGE));
		appendExpandedPath(buffer, info.getIconPath(ILauncherInfo.SOLARIS_MEDIUM));
		appendExpandedPath(buffer, info.getIconPath(ILauncherInfo.SOLARIS_SMALL));
		appendExpandedPath(buffer, info.getIconPath(ILauncherInfo.SOLARIS_TINY));
		return buffer.length() > 0
				? buffer.toString()
				: null;
	}

	@SuppressWarnings("unchecked")
	private static Dictionary<String, String>[] getStatePlatformProperties(State state)
	{
		return state.getPlatformProperties();
	}

	private static String getWin32Images(ILauncherInfo info)
	{
		StringBuilder buffer = new StringBuilder();
		if(info.usesWinIcoFile())
		{
			appendExpandedPath(buffer, info.getIconPath(ILauncherInfo.P_ICO_PATH));
		}
		else
		{
			appendExpandedPath(buffer, info.getIconPath(ILauncherInfo.WIN32_16_LOW));
			appendExpandedPath(buffer, info.getIconPath(ILauncherInfo.WIN32_16_HIGH));
			appendExpandedPath(buffer, info.getIconPath(ILauncherInfo.WIN32_32_LOW));
			appendExpandedPath(buffer, info.getIconPath(ILauncherInfo.WIN32_32_HIGH));
			appendExpandedPath(buffer, info.getIconPath(ILauncherInfo.WIN32_48_LOW));
			appendExpandedPath(buffer, info.getIconPath(ILauncherInfo.WIN32_48_HIGH));
		}
		return buffer.length() > 0
				? buffer.toString()
				: null;
	}

	private void addChmodHint(String perm, String filesAndFolders)
	{
		if(m_hints == null)
			m_hints = new HashMap<String, String>();
		FeatureBuilder.addRootsPermissions(m_hints, perm, filesAndFolders);
	}

	private void copyFileOrFolderToRoot(String propVal, File featureDir) throws CoreException
	{
		boolean folder = false;
		File src;
		if(propVal.startsWith("absolute:file:"))
		{
			src = new File(propVal.substring(14)).getAbsoluteFile();
		}
		else if(propVal.startsWith("file:"))
		{
			src = new File(featureDir, propVal.substring(5));
		}
		else if(propVal.startsWith("absolute:"))
		{
			src = new File(propVal.substring(9)).getAbsoluteFile();
			folder = true;
		}
		else
		{
			src = new File(featureDir, propVal);
			folder = true;
		}
		if(folder)
			FileUtils.deepCopyUnchecked(src, m_outputDir.toFile(), new NullProgressMonitor());
		else
		{
			FileUtils.copyFile(src, m_outputDir.toFile(), src.getName(), new NullProgressMonitor());
		}
	}

	private void copyFilesAndFoldersToRoot(String propVal, File featureDir) throws CoreException
	{
		StringTokenizer tokenizer = new StringTokenizer(propVal, ",");
		while(tokenizer.hasMoreTokens())
			this.copyFileOrFolderToRoot(tokenizer.nextToken().trim(), featureDir);
	}

	private static final Pattern s_launcherPattern = Pattern.compile("^org\\.eclipse\\.equinox\\.launcher_(.+)\\.jar$");

	private void getInstalledRootFiles() throws CoreException
	{
		File targetRoot = m_targetLocation.toFile();
		String originalLauncher = "win32".equals(m_os)
				? "eclipse.exe"
				: "eclipse";
		File launcherFile = new File(targetRoot, originalLauncher);
		if(!launcherFile.exists())
		{
			originalLauncher = "win32".equals(m_os)
					? "launcher.exe"
					: "launcher";
			launcherFile = new File(targetRoot, originalLauncher);
		}

		File outputDirFile = m_outputDir.toFile();
		IProgressMonitor nullMonitor = new NullProgressMonitor();
		FileUtils.copyFile(launcherFile, outputDirFile, originalLauncher, nullMonitor);
		
		// Eclipse 3.3 no longer have a startup.jar in the root. Instead, they have a
		// org.eclipse.equinox.launcher_xxxx.jar file under plugins. Let's find
		// it.
		//
		File pluginsDir = new File(targetRoot, "plugins");
		String[] names = pluginsDir.list();
		if(names == null)
			throw BuckminsterException.wrap(new IOException(pluginsDir + " is not a directory"));

		String found = null;
		IVersion foundVer = null;
		int idx = names.length;
		while(--idx >= 0)
		{
			String name = names[idx];
			Matcher matcher = s_launcherPattern.matcher(name);
			if(matcher.matches())
			{
				IVersion version = VersionFactory.OSGiType.fromString(matcher.group(1));
				if(foundVer == null || foundVer.compareTo(version) > 0)
				{
					found = name;
					foundVer = version;
				}
			}
		}
		if(found == null)
			throw BuckminsterException.wrap(new FileNotFoundException(pluginsDir + "org.eclipse.equinox.launcher_<version>.jar"));
		FileUtils.copyFile(new File(pluginsDir, found), outputDirFile, "startup.jar", nullMonitor);
	}

	private State getState()
	{
		State main = TargetPlatformHelper.getState();
		if(m_os.equals(TargetPlatform.getOS()) && m_ws.equals(TargetPlatform.getWS())
				&& m_arch.equals(TargetPlatform.getOSArch()))
			return main;

		State stateCopy = main.getFactory().createState(main);
		stateCopy.setResolver(Platform.getPlatformAdmin().getResolver());
		stateCopy.setPlatformProperties(main.getPlatformProperties());
		for(Dictionary<String, String> properties : getStatePlatformProperties(stateCopy))
		{
			properties.put("osgi.os", m_os);
			properties.put("osgi.ws", m_ws);
			properties.put("osgi.arch", m_arch);
		}
		stateCopy.resolve(false);
		return stateCopy;
	}

	private void processFeatureBuildProperties(File featureDir) throws CoreException, IOException
	{
		String targetKey = ROOT + '.' + m_os + '.' + m_ws + '.' + m_arch;

		BMProperties props = null;
		InputStream propInput = null;
		try
		{
			propInput = new BufferedInputStream(new FileInputStream(new File(featureDir, "build.properties")));
			props = new BMProperties(propInput);
		}
		finally
		{
			IOUtils.close(propInput);
		}

		ExpandingProperties exProps = new ExpandingProperties(props);
		exProps.put("launcherName", "win32".equals(m_os)
				? "launcher.exe"
				: "launcher");
		for(Map.Entry<String, String> prop : exProps.entrySet())
		{
			String keyName = prop.getKey();
			if(keyName.equals(ROOT) || keyName.equals(targetKey))
				copyFilesAndFoldersToRoot(prop.getValue(), featureDir);
		}

		if("win32".equals(m_os))
			//
			// Skip the chmod in case of windows
			//
			return;

		String rootPermissions = ROOT + ".permissions.";
		String targetPermissions = targetKey + ".permissions.";
		for(Map.Entry<String, String> prop : exProps.entrySet())
		{
			String key = prop.getKey();
			int permIndex;
			if(key.startsWith(rootPermissions))
				permIndex = rootPermissions.length();
			else if(key.startsWith(targetPermissions))
				permIndex = targetPermissions.length();
			else
				continue;

			addChmodHint(key.substring(permIndex), prop.getValue());
		}
	}
}

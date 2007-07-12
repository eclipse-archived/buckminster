/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.tasks;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.cspecgen.feature.FeatureBuilder;
import org.eclipse.buckminster.runtime.BuckminsterException;
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
	private final String m_arch;

	private Map<String,String> m_hints;

	private final boolean m_copyJavaLauncher;

	private final String m_os;

	private final IPath m_outputDir;

	private final IProduct m_product;

	private final File m_productFile;

	private final IPath m_productRootFiles;

	private final IPath m_targetLocation;

	private final IPath m_generatedDir;

	private final String m_ws;

	public CreateProductBase(String os, String ws, String arch, File productFile, IPath outputDir, IPath targetLocation, boolean copyJavaLauncher)
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
		m_copyJavaLauncher = copyJavaLauncher;
	}

	public void execute() throws Exception
	{
		m_hints = null;

		File outputDir = m_outputDir.toFile();
		if(!outputDir.isDirectory())
			throw new BuckminsterException(outputDir + "is not a directory");

		if(m_copyJavaLauncher)
			copyJavaLauncherToRoot();

		AbstractScriptGenerator.setConfigInfo(m_os + ',' + m_ws + ',' + m_arch);

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
		// a binary launcher with splash screen and all.
		//
		if(!"_removethisfile".equals(launcherName))
			this.createLauncher(info);

		if(!"eclipse".equals(launcherName))
			//
			// Remove the default launcher
			//
			m_outputDir.append(getPlatformLauncherName("eclipse")).toFile().delete();
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

		addChmodHint("755", getPlatformLauncherName(info.getLauncherName()));
	}

	private String getPlatformLauncherName(String launcherName)
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

	private static final Pattern s_launcherPattern = Pattern.compile("^org\\.eclipse\\.equinox\\.launcher_(.+)\\.jar$");

	private void copyJavaLauncherToRoot() throws CoreException
	{
		// Eclipse 3.3 no longer have a startup.jar in the root. Instead, they have a
		// org.eclipse.equinox.launcher_xxxx.jar file under plugins. Let's find it.
		//
		File targetRoot = m_targetLocation.toFile();
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
		File startupJar;
		if(found == null)
		{
			// Are we building against an older platform perhaps?
			//
			startupJar = new File(targetRoot, "startup.jar");
			if(!startupJar.exists())
				throw BuckminsterException.wrap(new FileNotFoundException(pluginsDir + "org.eclipse.equinox.launcher_<version>.jar"));
		}
		else
			startupJar = new File(pluginsDir, found);
		FileUtils.copyFile(startupJar, m_outputDir.toFile(), "startup.jar", new NullProgressMonitor());
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
}

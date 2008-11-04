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
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.IPDEConstants;
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
import org.eclipse.jdt.launching.ExecutionArguments;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.State;
import org.eclipse.osgi.util.ManifestElement;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.build.BrandingIron;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.TargetPlatformHelper;
import org.eclipse.pde.internal.core.iproduct.IArgumentsInfo;
import org.eclipse.pde.internal.core.iproduct.IConfigurationFileInfo;
import org.eclipse.pde.internal.core.iproduct.ILauncherInfo;
import org.eclipse.pde.internal.core.iproduct.IProduct;
import org.eclipse.pde.internal.core.iproduct.IProductPlugin;
import org.eclipse.pde.internal.core.iproduct.ISplashInfo;
import org.eclipse.pde.internal.core.product.ProductModel;
import org.eclipse.pde.internal.core.util.CoreUtility;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;

/**
 * Ant task that creates a product base. The base in this case is a product stripped from all features and plugins.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CreateProductBase
{
	private final IActionContext m_actionContext;

	private final String m_arch;

	private Map<String, String> m_hints;

	private final boolean m_copyJavaLauncher;

	private final String m_os;

	private final IPath m_outputDir;

	private final IProduct m_product;

	private final IPath m_targetLocation;

	private final String m_ws;

	private final String m_nl;

	private final List<File> m_files;

	public CreateProductBase(File productFile, List<File> files,
			IPath outputDir, IPath targetLocation, boolean copyJavaLauncher) throws CoreException
	{
		if(outputDir == null)
			throw new IllegalArgumentException("outputDir cannot be null");
		m_outputDir = outputDir.addTrailingSeparator();

		if(targetLocation == null)
			throw new IllegalArgumentException("targetLocation cannot be null");
		m_targetLocation = targetLocation.addTrailingSeparator();

		if(productFile == null)
			throw new IllegalArgumentException("productFile cannot be null");

		m_files = files;
		m_actionContext = AbstractActor.getActiveContext();

		InputStream pfInput = null;
		try
		{
			pfInput = new BufferedInputStream(new FileInputStream(productFile));
			ProductModel productModel = new ProductModel();
			productModel.load(pfInput, false);
			m_product = productModel.getProduct();
		}
		catch(IOException e)
		{
			throw BuckminsterException.fromMessage("Unable to read file %s", productFile);
		}
		finally
		{
			IOUtils.close(pfInput);
		}

		Map<String,String> props = m_actionContext.getProperties();
		String os = props.get(org.eclipse.buckminster.core.TargetPlatform.TARGET_OS);
		if(os == null || FilterUtils.MATCH_ALL.equals(os))
			os = TargetPlatform.getOS();
		m_os = os;

		String ws = props.get(org.eclipse.buckminster.core.TargetPlatform.TARGET_WS);
		if(ws == null || FilterUtils.MATCH_ALL.equals(ws))
			ws = TargetPlatform.getWS();
		m_ws = ws;

		String arch = props.get(org.eclipse.buckminster.core.TargetPlatform.TARGET_ARCH);
		if(arch == null || FilterUtils.MATCH_ALL.equals(arch))
			arch = TargetPlatform.getOSArch();
		m_arch = arch;

		String nl = props.get(org.eclipse.buckminster.core.TargetPlatform.TARGET_NL);
		if(nl == null || FilterUtils.MATCH_ALL.equals(nl))
			nl = TargetPlatform.getNL();
		m_nl = nl;

		m_copyJavaLauncher = copyJavaLauncher;
	}

	public String execute() throws Exception
	{
		m_hints = null;

		File outputDir = m_outputDir.toFile();
		if(!outputDir.isDirectory())
			throw BuckminsterException.fromMessage("%s is not a directory", outputDir);

		if(m_copyJavaLauncher)
			copyJavaLauncherToRoot();

		// Generate the configuration/config.ini, .eclipseproduct, <launcher>.ini
		//
		IProgressMonitor monitor = new NullProgressMonitor();
		createConfigIniFile(new File(outputDir, "configuration"), monitor);
		createEclipseProductFile(outputDir, monitor);
		createLauncherIniFile(outputDir, monitor);
		return createLauncher();
	}

	public static final String MACOSX_LAUNCHER_FOLDER = "Eclipse.app/Contents/MacOS";

	public static final String DEFAULT_LAUNCHER = "launcher";

	public static final String DEFAULT_LAUNCHER_WIN32 = DEFAULT_LAUNCHER + ".exe";

	private String createLauncher() throws Exception
	{
		String launcherName = getLauncherName();
		boolean hasDeltaPack = hasDeltaPackFeature();

		if(hasDeltaPack)
		{
			boolean nameOK = m_os.equals(Platform.OS_WIN32)
					? DEFAULT_LAUNCHER_WIN32.equalsIgnoreCase(launcherName)
					: DEFAULT_LAUNCHER.equals(launcherName);

			if(!nameOK && !(m_os.equals(Platform.OS_MACOSX) || m_os.equals(Platform.OS_WIN32)))
			{
				// Launcher name will change so we need to add a new chmod
				// if this isn't on a Windows (in which case it doesn't matter)
				// or on a MacOS (in which case the BrandingIron will do it
				// for us).
				//
				addChmodHint("755", launcherName);
			}
		}
		else
		{
			// Simulate delta pack from the current platform installation
			//
			copyLauncherExecutable();
		}

		ILauncherInfo info = m_product.getLauncherInfo();
		if(info == null)
			return launcherName;

		String images = null;
		if(Platform.OS_WIN32.equals(m_os))
		{
			images = getWin32Images(info);
		}
		else if(Platform.OS_SOLARIS.equals(m_os))
		{
			images = getSolarisImages(info);
		}
		else if(Platform.OS_LINUX.equals(m_os))
		{
			images = getExpandedPath(info.getIconPath(ILauncherInfo.LINUX_ICON));
		}
		else if(Platform.OS_MACOSX.equals(m_os))
		{
			images = getExpandedPath(info.getIconPath(ILauncherInfo.MACOSX_ICON));
		}

		BrandingIron bi = new BrandingIron();
		bi.setName(launcherName);
		bi.setOS(m_os);
		bi.setRoot(m_outputDir.toOSString());
		if(images != null)
			bi.setIcons(images);
		bi.brand();
		return launcherName;
	}

	private boolean hasDeltaPackFeature() throws CoreException
	{
		CSpec cspec = m_actionContext.getCSpec();
		Attribute attr = cspec.getAttribute(IPDEConstants.ATTRIBUTE_PRODUCT_ROOT_FILES);
		if(attr == null)
			return false;

		for(Prerequisite pq : attr.getPrerequisites())
		{
			String cname = pq.getComponentName();
			if(cname == null)
				continue;

			if(cname.equals("org.eclipse.equinox.executable")
			|| cname.equals("org.eclipse.platform.launchers"))
			{
				return (pq.getReferencedAttribute(cspec, m_actionContext) != null);
			}
		}
		return false;
	}

	private void copyLauncherExecutable() throws CoreException
	{
		File homeDir = new File(TargetPlatform.getLocation());
		File[] rootFiles = homeDir.listFiles();
		if(rootFiles == null)
			return;

		String targetOs = TargetPlatform.getOS();
		boolean isWin32 = Platform.OS_WIN32.equals(targetOs);
		boolean isMac = Platform.OS_MACOSX.equals(targetOs);
		IProgressMonitor monitor = new NullProgressMonitor();
		File dest = m_outputDir.toFile();
		for(File rootFile : rootFiles)
		{
			if(!rootFile.isFile())
				continue;

			String name = rootFile.getName();
			boolean copyFile = false;
			if("startup.jar".equals(name))
				copyFile = true;
			else
			{
				if(isMac)
				{
					if("Eclipse.app".equals(name))
						FileUtils.deepCopy(rootFile, new File(dest, name), ConflictResolution.REPLACE, new NullProgressMonitor());
					continue;
				}

				if(isWin32)
				{
					if("eclipse.exe".equals(name))
					{
						copyFile = true;
						name = DEFAULT_LAUNCHER_WIN32;
					}
				}
				else
				{
					if("eclipse".equals(name))
					{
						copyFile = true;
						name = DEFAULT_LAUNCHER;
						addChmodHint("755", getLauncherName());
					}
					else if(name.startsWith("libXm.so") || name.startsWith("libcairo-swt.so"))
					{
						copyFile = true;
						addChmodHint("755", name);
					}
				}
			}
			if(copyFile)
				FileUtils.copyFile(rootFile, dest, name, monitor);
		}
	}

	public Map<String, String> getHints()
	{
		if(m_hints == null || m_hints.size() == 0)
			return Collections.emptyMap();

		Map<String, String> hints = new HashMap<String, String>();
		StringBuilder bld = new StringBuilder(100);
		bld.append(TopLevelAttribute.INSTALLER_HINT_PREFIX);
		int pfLen = TopLevelAttribute.INSTALLER_HINT_PREFIX.length();
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
		FeatureBuilder.addRootsPermissions(m_hints, perm, filesAndFolders, null);
	}

	private static final Pattern s_launcherPattern = Pattern.compile("^org\\.eclipse\\.equinox\\.launcher_(.+)\\.jar$");

	private void copyJavaLauncherToRoot() throws CoreException, IOException
	{
		// Eclipse 3.3 no longer have a startup.jar in the root. Instead, they have a
		// org.eclipse.equinox.launcher_xxxx.jar file under plugins. Let's find it.
		//
		File targetRoot = m_targetLocation.toFile();
		File pluginsDir = new File(targetRoot, "plugins");
		String[] names = pluginsDir.list();
		if(names == null)
			throw new IOException(pluginsDir + " is not a directory");

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
				throw new FileNotFoundException(pluginsDir + "org.eclipse.equinox.launcher_<version>.jar");
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

	private File getCustomIniFile()
	{
		IConfigurationFileInfo info = m_product.getConfigurationFileInfo();
		if(info != null && "custom".equals(info.getUse(m_os)))
		{
			String path = getExpandedPath(info.getPath(m_os));
			if(path != null)
			{
				File file = new File(path);
				if(file.exists() && file.isFile())
					return file;
			}
		}
		return null;
	}

	private void createConfigIniFile(File outputDir, IProgressMonitor monitor) throws CoreException, IOException
	{
		FileUtils.prepareDestination(outputDir, ConflictResolution.UPDATE, monitor);
		File custom = getCustomIniFile();
		if(custom != null)
		{
			FileUtils.copyFile(custom, outputDir, "config.ini", monitor);
			return;
		}

		String lineDelimiter = getLineDelimiter();
		Writer writer = null;
		try
		{
			writer = new FileWriter(new File(outputDir, "config.ini"));
			String location = getSplashLocation();
			writer.write("#Product Runtime Configuration File");
			writer.write(lineDelimiter);
			writer.write(lineDelimiter);
			if(location != null)
			{
				writer.write("osgi.splashPath=");
				writer.write(location);
				writer.write(lineDelimiter);
			}
			writer.write("eclipse.product=");
			writer.write(m_product.getId());
			writer.write(lineDelimiter);

			writer.write("osgi.bundles=");
			printBundleList(writer, TargetPlatform.getBundleList());
			writer.write(lineDelimiter);

			writer.write("osgi.bundles.defaultStartLevel=4");
			writer.write(lineDelimiter);
		}
		finally
		{
			IOUtils.close(writer);
		}
	}

	private String getBrandingPlugin()
	{
		String id = m_product.getId();
		int lastDot = id.lastIndexOf('.');
		return (lastDot < 0)
				? id.substring(0, lastDot)
				: null;
	}

	private String getSplashLocation()
	{
		ISplashInfo info = m_product.getSplashInfo();
		String plugin = null;
		if(info != null)
			plugin = info.getLocation();

		if(plugin == null || plugin.trim().length() == 0)
			plugin = getBrandingPlugin();

		if(plugin == null)
			return null;

		StringBuilder buffer = new StringBuilder("platform:/base/plugins/");
		buffer.append(plugin.trim());

		State state = getState();
		BundleDescription bundle = state.getBundle(plugin, null);
		if(bundle != null)
		{
			for(BundleDescription fragment : bundle.getFragments())
			{
				String id = fragment.getSymbolicName();
				if(m_product.containsPlugin(id))
				{
					buffer.append(",platform:/base/plugins/");
					buffer.append(id);
				}
			}
		}
		return buffer.toString();
	}

	private void printBundleList(Writer writer, String bundleList) throws IOException, CoreException
	{
		Dictionary<String, String> environment = new Hashtable<String, String>(4);
		environment.put("osgi.os", m_os);
		environment.put("osgi.ws", m_ws);
		environment.put("osgi.arch", m_arch);
		environment.put("osgi.nl", m_nl);
		List<BundleDescription> pluginModels = getPluginModels();

		// include only bundles that are actually in this product configuration
		//
		boolean first = true;
		Set<String> includedBundles = new HashSet<String>();
		for(BundleDescription bundle : pluginModels)
			includedBundles.add(bundle.getSymbolicName());

		Set<String> bundles = new HashSet<String>();
		bundles.add("org.eclipse.osgi");
		for(String token : TextUtils.split(bundleList, ","))
		{
			int delimIdx = token.indexOf('@');
			String id = delimIdx >= 0
					? token.substring(0, delimIdx)
					: token;

			// Don't include unless it's listed among the pluginModels
			//
			if(bundles.contains(id) || !includedBundles.contains(id))
				continue;

			bundles.add(id);
			if(first)
				first = false;
			else
				writer.write(',');

			writer.write(id);
			if(delimIdx >= 0 && delimIdx < token.length() - 1)
				writer.write(token, delimIdx, token.length() - delimIdx);
		}

		for(BundleDescription bundle : pluginModels)
		{
			try
			{
				String id = bundle.getSymbolicName();
				if(bundles.contains(id))
					continue;

				bundles.add(id);
				String filterSpec = bundle.getPlatformFilter();
				if(filterSpec == null || FilterUtils.createFilter(filterSpec).match(environment))
				{
					if(first)
						first = false;
					else
						writer.write(",");

					writer.write(id);
					if("org.eclipse.equinox.app".equals(id))
						writer.write("@start");
					else if("org.eclipse.equinox.common".equals(id))
						writer.write("@start:2");
				}
			}
			catch(InvalidSyntaxException e)
			{
				throw BuckminsterException.wrap(e);
			}
		}
	}

	private String getLineDelimiter()
	{
		return Platform.OS_WIN32.equals(m_os)
				? "\r\n"
				: "\n";
	}

	private void createLauncherIniFile(File outputDir, IProgressMonitor monitor) throws CoreException, IOException
	{
		String programArgs = getProgramArguments(m_os);
		String vmArgs = getVMArguments(m_os);

		if(programArgs.length() == 0 && vmArgs.length() == 0)
			//
			// Don't need a launcher.ini
			//
			return;

		// need to place launcher.ini file in special directory for MacOSX (bug 164762)
		//
		if(Platform.OS_MACOSX.equals(m_os))
			outputDir = new File(outputDir, MACOSX_LAUNCHER_FOLDER);

		FileUtils.prepareDestination(outputDir, ConflictResolution.UPDATE, monitor);
		String lineDelimiter = getLineDelimiter();
		Writer writer = null;
		try
		{
			writer = new FileWriter(new File(outputDir, getLauncherName() + ".ini"));
			ExecutionArguments args = new ExecutionArguments(vmArgs, programArgs);

			// add program arguments
			//
			for(String arg : args.getProgramArgumentsArray())
			{
				writer.write(arg);
				writer.write(lineDelimiter);
			}

			// add VM arguments
			//
			String[] array = args.getVMArgumentsArray();
			if(array.length > 0)
			{
				writer.write("-vmargs");
				writer.write(lineDelimiter);
				for(String arg : array)
				{
					writer.write(arg);
					writer.write(lineDelimiter);
				}
			}
		}
		finally
		{
			IOUtils.close(writer);
		}
	}

	private String getProgramArguments(String os)
	{
		IArgumentsInfo info = m_product.getLauncherArguments();
		return info != null
				? CoreUtility.normalize(info.getCompleteProgramArguments(os))
				: "";
	}

	private String getVMArguments(String os)
	{
		IArgumentsInfo info = m_product.getLauncherArguments();
		return (info != null)
				? CoreUtility.normalize(info.getCompleteVMArguments(os))
				: "";
	}

	private String getLauncherName()
	{
		ILauncherInfo info = m_product.getLauncherInfo();
		if(info != null)
		{
			String name = info.getLauncherName();
			if(name != null && name.length() > 0)
			{
				name = name.trim();
				if(name.endsWith(".exe"))
					name = name.substring(0, name.length() - 4);
				return name;
			}
		}
		return DEFAULT_LAUNCHER;
	}

	private void createEclipseProductFile(File outputDir, IProgressMonitor monitor) throws CoreException, IOException
	{
		FileUtils.prepareDestination(outputDir, ConflictResolution.UPDATE, monitor);
		Map<String, String> properties = new HashMap<String, String>();
		IPluginModelBase model = PluginRegistry.findModel(getBrandingPlugin());
		if(model != null)
			properties.put("name", model.getResourceString(m_product.getName()));
		else
			properties.put("name", m_product.getName());
		properties.put("id", m_product.getId());
		if(model != null)
			properties.put("version", model.getPluginBase().getVersion());

		OutputStream out = null;
		try
		{
			out = new FileOutputStream(new File(outputDir, ".eclipseproduct"));
			BMProperties.store(properties, out, "Eclipse Product File");
		}
		finally
		{
			IOUtils.close(out);
		}
	}

	private List<BundleDescription> getPluginModels() throws CoreException
	{
		ArrayList<BundleDescription> list = new ArrayList<BundleDescription>();
		State state = getState();
		if(m_files.size() == 0)
		{
			for(IProductPlugin plugin : m_product.getPlugins())
			{
				BundleDescription bundle = state.getBundle(plugin.getId(), null);
				if(bundle != null)
					list.add(bundle);
			}
		}
		else
		{
			for(File file : m_files)
			{
				String fileName = file.getName();
				if(!fileName.endsWith(".jar"))
					continue;

				File folder = file.getParentFile();
				if(folder == null || !IPDEConstants.PLUGINS_FOLDER.equals(folder.getName()))
					continue;

				try
				{
					JarFile jf = new JarFile(file);
					try
					{
						Manifest mf = jf.getManifest();
						Attributes attrs = mf.getMainAttributes();

						String value = attrs.getValue(Constants.BUNDLE_SYMBOLICNAME);
						if(value == null)
							continue;

						ManifestElement[] elements = ManifestElement.parseHeader(Constants.BUNDLE_SYMBOLICNAME, value);
						if(elements.length == 0)
							continue;

						ManifestElement elem = elements[0];
						BundleDescription bundle = state.getBundle(elem.getValue(), null);
						if(bundle != null)
							list.add(bundle);
					}
					finally
					{
						jf.close();
					}
				}
				catch(BundleException e)
				{
					throw BuckminsterException.wrap(e);
				}
				catch(IOException e)
				{
					throw BuckminsterException.wrap(e);
				}
			}
		}

		IPluginModelBase launcherPlugin = PluginRegistry.findModel("org.eclipse.equinox.launcher");
		if(launcherPlugin != null)
		{
			BundleDescription bundle = launcherPlugin.getBundleDescription();
			if(!(bundle == null || list.contains(bundle)))
			{
				list.add(bundle);
				for(BundleDescription fragment : bundle.getFragments())
					if(!list.contains(fragment))
						list.add(fragment);
			}
		}
		return list;
	}
}

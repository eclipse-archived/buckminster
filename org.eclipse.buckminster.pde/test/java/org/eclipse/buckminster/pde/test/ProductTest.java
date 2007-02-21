package org.eclipse.buckminster.pde.test;

import java.io.File;

import junit.framework.TestCase;

import org.apache.tools.ant.util.FileUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.build.AbstractScriptGenerator;
import org.eclipse.pde.internal.build.BrandingIron;
import org.eclipse.pde.internal.build.ProductFile;
import org.eclipse.pde.internal.build.ProductGenerator;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.PDECore;

@SuppressWarnings("restriction")
public class ProductTest extends TestCase
{
	/**
	 * Returns the location of the target platform
	 */
	public IPath getTargetLocation()
	{
		PDECore pdePlugin = PDECore.getDefault();
		Preferences preferences = pdePlugin.getPluginPreferences();
		IPath targetPath = null;
		if(ICoreConstants.VALUE_USE_OTHER.equals(preferences.getString(ICoreConstants.TARGET_MODE)))
		{
			String targetPlatform = preferences.getString(ICoreConstants.PLATFORM_PATH);
			if(targetPlatform != null)
				targetPath = new Path(targetPlatform);
		}

		if(targetPath == null)
			targetPath = new Path(TargetPlatform.getDefaultLocation());
		return targetPath;
	}

	public void testProductGeneration() throws Exception
	{
		String config = TargetPlatform.getOS() + ',' + TargetPlatform.getWS() + ',' + TargetPlatform.getOSArch();
		AbstractScriptGenerator.setConfigInfo(config);  //This needs to be set before we set the format
		ProductGenerator pd = new ProductGenerator(); pd.setBuildingOSGi(true);
		pd.setBuildProperties(System.getProperties());
		pd.setRoot("C:/temp/");
		pd.setWorkingDirectory("c:/Home/thhal/workspaces/Buckminster/org.eclipse.buckminster.product/");
		pd.setProduct("c:/Home/thhal/workspaces/Buckminster/org.eclipse.buckminster.product/buckminster.product");
		pd.generate();
		
		File targetRoot = this.getTargetLocation().toFile();
		File genRoot = new File("C:/temp/productRootFiles/win32.win32.x86");
		FileUtils fs = FileUtils.newFileUtils();
		fs.copyFile(new File(targetRoot, "eclipse.exe"), new File(genRoot, "eclipse.exe"));
		fs.copyFile(new File(targetRoot, "startup.jar"), new File(genRoot, "startup.jar"));
		
		ProductFile pf = new ProductFile("c:/Home/thhal/workspaces/Buckminster/org.eclipse.buckminster.product/buckminster.product", "win32");
		BrandingIron bi = new BrandingIron();
		bi.setName(pf.getLauncherName());
		bi.setOS("win32");
		bi.setRoot(genRoot.toString());
		String[] icons = pf.getIcons();
		if(icons != null && icons.length > 0)
			bi.setIcons(TextUtils.toCommaSeparatedList(pf.getIcons()));
		bi.brand();
	}
}

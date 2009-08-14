package org.eclipse.buckminster.pde.tasks;

import java.io.File;

import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.equinox.internal.p2.publisher.eclipse.ExecutablesDescriptor;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.p2.publisher.eclipse.IBrandingAdvice;
import org.eclipse.equinox.p2.publisher.eclipse.ProductFileAdvice;
import org.eclipse.pde.internal.build.BrandingIron;

@SuppressWarnings( { "restriction", "hiding" })
public class EquinoxExecutableAction extends org.eclipse.equinox.p2.publisher.eclipse.EquinoxExecutableAction
{
	// This method is a somewhat ugly fix for bug 280721
	private static String peruseIcons(IBrandingAdvice advice)
	{
		String[] iconsArr = advice.getIcons();
		if(iconsArr == null || iconsArr.length == 0)
			return null;

		if(!(advice instanceof ProductFileAdvice))
			return TextUtils.concat(iconsArr, ","); //$NON-NLS-1$

		IProductDescriptor productFile = ((ProductFileAdvice)advice).getProductFile();
		File root = productFile.getLocation().getParentFile();
		if(root != null)
			root = root.getParentFile();
		if(root == null)
			return TextUtils.concat(iconsArr, ","); //$NON-NLS-1$

		int idx = iconsArr.length;
		while(--idx >= 0)
		{
			File iconFile = new File(iconsArr[idx]);
			if(!iconFile.isFile())
			{
				iconFile = new File(root, iconFile.getPath());
				if(iconFile.isFile())
					iconsArr[idx] = iconFile.getAbsolutePath();
			}
		}
		return TextUtils.concat(iconsArr, ","); //$NON-NLS-1$
	}

	public EquinoxExecutableAction(ExecutablesDescriptor executables, String configSpec, String idBase,
			Version version, String flavor)
	{
		super(executables, configSpec, idBase, version, flavor);
	}

	@Override
	protected void fullBrandExecutables(ExecutablesDescriptor descriptor, IBrandingAdvice advice)
	{
		BrandingIron iron = new BrandingIron();
		String icons = peruseIcons(advice);
		if(icons != null)
			iron.setIcons(icons);

		String name = advice.getExecutableName();
		if(name == null)
			name = "eclipse"; //$NON-NLS-1$
		iron.setName(name);
		iron.setOS(advice.getOS());
		iron.setRoot(descriptor.getLocation().getAbsolutePath());
		try
		{
			iron.brand();
			descriptor.setExecutableName(name, true);
		}
		catch(RuntimeException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}

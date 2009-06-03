package org.eclipse.buckminster.pde.tasks;

import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.equinox.internal.p2.publisher.eclipse.ExecutablesDescriptor;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.p2.publisher.eclipse.IBrandingAdvice;
import org.eclipse.pde.internal.build.BrandingIron;

@SuppressWarnings( { "restriction", "hiding" })
public class EquinoxExecutableAction extends org.eclipse.equinox.p2.publisher.eclipse.EquinoxExecutableAction
{
	public EquinoxExecutableAction(ExecutablesDescriptor executables, String configSpec, String idBase,
			Version version, String flavor)
	{
		super(executables, configSpec, idBase, version, flavor);
	}

	@Override
	protected void fullBrandExecutables(ExecutablesDescriptor descriptor, IBrandingAdvice advice)
	{
		BrandingIron iron = new BrandingIron();
		String icons = TextUtils.concat(advice.getIcons(), ","); //$NON-NLS-1$
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

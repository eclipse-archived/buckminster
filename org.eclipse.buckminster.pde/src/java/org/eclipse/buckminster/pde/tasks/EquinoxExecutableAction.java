package org.eclipse.buckminster.pde.tasks;

import java.io.File;

import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.internal.p2.publisher.eclipse.ExecutablesDescriptor;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.publisher.eclipse.IBrandingAdvice;
import org.eclipse.equinox.p2.publisher.eclipse.ProductFileAdvice;
import org.eclipse.osgi.service.environment.Constants;

@SuppressWarnings("restriction")
public class EquinoxExecutableAction extends org.eclipse.equinox.p2.publisher.eclipse.EquinoxExecutableAction {
	// This method is a somewhat ugly fix for bug 280721
	private static String peruseIcons(IBrandingAdvice advice) {
		String[] iconsArr = advice.getIcons();
		if (iconsArr == null || iconsArr.length == 0)
			return null;

		if (!(advice instanceof ProductFileAdvice))
			return TextUtils.concat(iconsArr, ","); //$NON-NLS-1$

		IProductDescriptor productFile = ((ProductFileAdvice) advice).getProductFile();
		File root = productFile.getLocation().getParentFile();
		if (root != null)
			root = root.getParentFile();
		if (root == null)
			return TextUtils.concat(iconsArr, ","); //$NON-NLS-1$

		IPath rootPath = Path.fromOSString(root.getAbsolutePath());
		int idx = iconsArr.length;
		while (--idx >= 0) {
			File iconFile = new File(iconsArr[idx]);
			if (!iconFile.isFile()) {
				IPath iconPath = Path.fromOSString(iconFile.getPath());
				if (rootPath.isPrefixOf(iconPath)) {
					// The iconPath is bogus since it is relative to the project
					// files parent rather than to the project parent (the
					// workspace). This either happens on Windows platforms or
					// if the project containing the icon is not directly in the
					// workspace root (e.g. inside an intermediate "plugins"-
					// folder when it has been materialized using Buckminster.
					iconPath = iconPath.removeFirstSegments(rootPath.segmentCount() + 1).setDevice(null).makeRelative();

					// First check, if the path references a project that is
					// not directly in the workspace's root
					IProject prj = ResourcesPlugin.getWorkspace().getRoot().getProject(iconPath.segment(0));
					IResource iconRes = prj.findMember(iconPath.removeFirstSegments(1));
					if (iconRes != null && iconRes.exists()) {
						iconPath = iconRes.getLocation();
					} else {
						// Windows: we strip one more segment then the root has
						// and then we prepend the root again.
						//
						iconPath = rootPath.append(iconPath);
					}
				} else {
					// The iconPath was considered absolute and hence not
					// altered. The problem
					// is that it should be relative to the workspace root. This
					// happens on
					// *nix systems.
					//
					iconPath = rootPath.append(iconPath.makeRelative());
				}
				iconFile = iconPath.toFile();
				if (iconFile.isFile())
					iconsArr[idx] = iconFile.getAbsolutePath();
			}
		}
		return TextUtils.concat(iconsArr, ","); //$NON-NLS-1$
	}

	public EquinoxExecutableAction(ExecutablesDescriptor executables, String configSpec, String idBase, Version version, String flavor) {
		super(executables, configSpec, idBase, version, flavor);
	}

	@Override
	protected void fullBrandExecutables(ExecutablesDescriptor descriptor, IBrandingAdvice advice) {
		BrandingIron iron = new BrandingIron();
		String icons = peruseIcons(advice);
		if (icons != null)
			iron.setIcons(icons);

		String name = advice.getExecutableName();
		if (name == null)
			name = "eclipse"; //$NON-NLS-1$

		if (Constants.OS_MACOSX.equals(advice.getOS())) {
			// First character of the name should be in upper case.
			StringBuilder appNameBld = new StringBuilder();
			appNameBld.append(Character.toUpperCase(name.charAt(0)));
			appNameBld.append(name, 1, name.length());
			name = appNameBld.toString();
		}

		iron.setName(name);
		iron.setOS(advice.getOS());
		try {
			iron.brand(descriptor);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

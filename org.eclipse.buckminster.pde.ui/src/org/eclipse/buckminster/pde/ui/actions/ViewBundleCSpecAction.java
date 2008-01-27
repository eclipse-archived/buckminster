package org.eclipse.buckminster.pde.ui.actions;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.ui.actions.ViewCSpecAction;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.pde.core.plugin.IPluginModelBase;

public class ViewBundleCSpecAction extends ViewCSpecAction
{
	@Override
	public void selectionChanged(IAction action, ISelection selection)
	{
		setSelectedComponent(null);
		if(!(selection instanceof IStructuredSelection))
			return;

		IStructuredSelection s = (IStructuredSelection)selection;
		if(s.size() != 1)
			return;

		Object first = s.getFirstElement();
		if(!(first instanceof IPluginModelBase))
			return;

		BundleDescription bundleDesc = ((IPluginModelBase)first).getBundleDescription();
		if(bundleDesc == null)
			return;

		ComponentIdentifier ci = new ComponentIdentifier(
			bundleDesc.getSymbolicName(),
			IComponentType.OSGI_BUNDLE,
			VersionFactory.OSGiType.coerce(bundleDesc.getVersion()));

		try
		{
			Resolution res = WorkspaceInfo.getResolution(ci);
			if(res != null)
				setSelectedComponent(res.getCSpec());
		}
		catch(CoreException e)
		{
			CorePlugin.getLogger().warning(e, e.getMessage());
		}
	}
}

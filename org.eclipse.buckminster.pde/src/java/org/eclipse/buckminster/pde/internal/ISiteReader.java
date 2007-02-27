package org.eclipse.buckminster.pde.internal;

import java.util.List;

import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IPluginModelBase;

public interface ISiteReader extends IComponentReader
{
	IPluginModelBase getPluginModelBase(String pluginId, String version) throws CoreException;

	List<IFragmentModel> getFragmentsFor(String pluginId) throws CoreException;
}

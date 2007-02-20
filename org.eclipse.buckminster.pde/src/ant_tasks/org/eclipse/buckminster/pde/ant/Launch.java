/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.pde.ant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginImport;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ModelEntry;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PluginModelManager;
import org.eclipse.pde.internal.core.TargetPlatform;

public class Launch extends Task
{
	private boolean m_includeOptional;

	private String m_plugins;

	private final Set<IPluginModelBase> m_includedModels = new HashSet<IPluginModelBase>();

	public void setPlugins(String plugins)
	{
		m_plugins = plugins.trim();
	}

	@Override
	public void execute() throws BuildException
	{
		PluginModelManager manager = PDECore.getDefault().getModelManager();
		if(m_plugins != null)
		{
			for(String pluginName : m_plugins.split(","))
			{
				pluginName = pluginName.trim();
				if(pluginName.length() == 0)
					continue;

				ModelEntry entry = manager.findEntry(pluginName);
				if(entry == null)
					throw new BuildException("No such plugin: " + pluginName, this.getLocation());

				m_includedModels.add(entry.getActiveModel());
			}
		}

		if(m_includedModels.isEmpty())
			throw new BuildException("Missing or empty required attribute \"plugins\"", this.getLocation());

		HashMap<String, IPluginModelBase> allPlugins = new HashMap<String, IPluginModelBase>();
		for(IPluginModelBase model : m_includedModels)
			this.addPluginAndDependencies(model, allPlugins);


	}

	private void addPluginAndDependencies(IPluginModelBase model, Map<String, IPluginModelBase> map)
	{
		if(model == null)
			return;

		String id = model.getPluginBase().getId();
		if(map.containsKey(id))
			return;

		map.put(id, model);
		if(model instanceof IFragmentModel)
		{
			IPluginModelBase parent = this.findPlugin(((IFragmentModel)model).getFragment().getPluginId());
			addPluginAndDependencies(parent, map);
		}
		else
		{
			for(IFragmentModel fragment : findFragments(model.getPluginBase()))
				addPluginAndDependencies(fragment, map);
		}

		for(IPluginImport imp : model.getPluginBase().getImports())
		{
			if(imp.isOptional() && !m_includeOptional)
				continue;
			addPluginAndDependencies(this.findPlugin(imp.getId()), map);
		}
	}

	private IPluginModelBase findPlugin(String id)
	{
		PluginModelManager manager = PDECore.getDefault().getModelManager();
		ModelEntry entry = manager.findEntry(id);
		if(entry != null)
		{
			IPluginModelBase model = entry.getActiveModel();
			if(m_includedModels.contains(model))
				return model;

			model = entry.getExternalModel();
			if(model != null && m_includedModels.contains(model))
				return model;
			return entry.getActiveModel();
		}
		return null;
	}

	private ArrayList<IFragmentModel> findFragments(IPluginBase plugin)
	{
		ArrayList<IFragmentModel> result = new ArrayList<IFragmentModel>();
		for(ModelEntry entry : PDECore.getDefault().getModelManager().getEntries())
		{
			IPluginModelBase model = entry.getActiveModel();
			if(!(model instanceof IFragmentModel))
				continue;

			IFragmentModel fm = (IFragmentModel)model;
			if("org.eclipse.ui.workbench.compatibility".equals(fm.getPluginBase().getId())) //$NON-NLS-1$
				continue;

			if(!TargetPlatform.matchesCurrentEnvironment(fm))
				continue;

			String id = fm.getFragment().getPluginId();
			if(!id.equals(plugin.getId()))
				continue;

			if(m_includedModels.contains(fm))
			{
				result.add(fm);
				continue;
			}

			model = entry.getExternalModel();
			if(!(model instanceof IFragmentModel))
				continue;

			fm = (IFragmentModel)model;
			if(m_includedModels.contains(fm))
				result.add(fm);
			else
				result.add((IFragmentModel)entry.getActiveModel());
		}
		return result;
	}
}

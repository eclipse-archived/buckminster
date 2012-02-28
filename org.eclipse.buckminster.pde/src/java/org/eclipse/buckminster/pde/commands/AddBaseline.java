/*****************************************************************************
 * Copyright (c) 2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.core.commands.WorkspaceCommand;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.prefs.BaselineHandler;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.api.tools.internal.model.ApiModelFactory;
import org.eclipse.pde.api.tools.internal.provisional.ApiPlugin;
import org.eclipse.pde.api.tools.internal.provisional.IApiBaselineManager;
import org.eclipse.pde.api.tools.internal.provisional.model.IApiBaseline;
import org.eclipse.pde.api.tools.internal.provisional.model.IApiComponent;
import org.eclipse.pde.core.target.ITargetDefinition;
import org.eclipse.pde.core.target.ITargetHandle;
import org.eclipse.pde.core.target.ITargetPlatformService;
import org.eclipse.pde.core.target.TargetBundle;

@SuppressWarnings("restriction")
public class AddBaseline extends WorkspaceCommand {
	private String targetDefinitionName;

	private boolean addAsActive;

	static private final OptionDescriptor OPTION_ACTIVE = new OptionDescriptor('A', "active", OptionValueType.NONE); //$NON-NLS-1$

	public String getTargetDefinitionName() {
		return targetDefinitionName;
	}

	public boolean isAddAsActive() {
		return addAsActive;
	}

	public void setAddAsActive(boolean importAsActive) {
		this.addAsActive = importAsActive;
	}

	public void setTargetDefinitionName(String targetDefinitionName) {
		this.targetDefinitionName = targetDefinitionName;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		super.getOptionDescriptors(appendHere);
		appendHere.add(OPTION_ACTIVE);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		if (option.is(OPTION_ACTIVE))
			setAddAsActive(true);
		else
			super.handleOption(option);
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception {
		if (unparsed.length > 1)
			throw new SimpleErrorExitException(org.eclipse.buckminster.core.Messages.Too_many_arguments);
		if (unparsed.length < 1)
			throw new SimpleErrorExitException(org.eclipse.buckminster.core.Messages.Too_few_arguments);
		setTargetDefinitionName(unparsed[0]);
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		IApiBaselineManager baselineManager = ApiPlugin.getDefault().getApiBaselineManager();
		for (IApiBaseline baseline : baselineManager.getApiBaselines())
			if (baseline.getName().equals(targetDefinitionName))
				throw new SimpleErrorExitException(NLS.bind(Messages.Baseline_already_exists_0, targetDefinitionName));

		SubMonitor submon = SubMonitor.convert(monitor, 10);
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = bucky.getService(ITargetPlatformService.class);
		ITargetDefinition foundTarget = null;
		for (ITargetHandle handle : service.getTargets(submon.newChild(1))) {
			ITargetDefinition target = handle.getTargetDefinition();
			String name = ListTargetDefinitions.getTargetName(target);
			if (name.equals(targetDefinitionName)) {
				foundTarget = target;
				break;
			}
		}
		if (foundTarget == null)
			throw new SimpleErrorExitException(NLS.bind(Messages.Found_no_target_definition_named_0, targetDefinitionName));

		IApiBaseline baseline = createBaseline(foundTarget, submon.newChild(3));
		baselineManager.addApiBaseline(baseline);
		if (addAsActive) {
			baselineManager.setDefaultApiBaseline(targetDefinitionName);
			BaselineHandler.rebuildApiProjects();
		}
		return 0;
	}

	/**
	 * Creates an API baseline from a target definition.
	 * 
	 * @param target
	 * @param monitor
	 *            progress monitor
	 */
	private IApiBaseline createBaseline(ITargetDefinition target, IProgressMonitor monitor) throws CoreException {
		SubMonitor submon = SubMonitor.convert(monitor, 10);
		target.resolve(submon.newChild(2));
		TargetBundle[] bundles = target.getBundles();
		List<IApiComponent> components = new ArrayList<IApiComponent>();
		IApiBaseline baseline = ApiModelFactory.newApiBaseline(ListTargetDefinitions.getTargetName(target));
		submon.setWorkRemaining(bundles.length);
		for (int i = 0; i < bundles.length; i++) {
			submon.worked(1);
			if (submon.isCanceled())
				throw new OperationCanceledException();
			if (bundles[i].getStatus().isOK() && !bundles[i].isSourceBundle()) {
				IApiComponent component = ApiModelFactory.newApiComponent(baseline, URIUtil.toFile(bundles[i].getBundleInfo().getLocation())
						.getAbsolutePath());
				if (component != null) {
					components.add(component);
				}
			}
		}
		baseline.addApiComponents(components.toArray(new IApiComponent[components.size()]));
		return baseline;
	}
}

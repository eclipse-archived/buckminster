/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.wizards;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializationJob;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

/**
 * @author Kenneth Olwing
 * @author Thomas Hallgren
 */
public class QueryWizard extends Wizard implements INewWizard {
	public static void openWizard(IWorkbenchPart targetPart, IStructuredSelection selection) {
		final QueryWizard wizard = new QueryWizard();
		IWorkbenchPartSite site = targetPart.getSite();
		wizard.init(site.getWorkbenchWindow().getWorkbench(), selection);
		WizardDialog dialog = new WizardDialog(site.getShell(), wizard);
		dialog.setPageSize(300, 480);
		dialog.open();
	}

	public static void openWizard(IWorkbenchPartSite site, ResolutionContext context, BillOfMaterials bom) {
		final QueryWizard wizard = new QueryWizard(context, bom);
		wizard.init(PlatformUI.getWorkbench(), null);
		WizardDialog dialog = new WizardDialog(site.getShell(), wizard);
		dialog.setPageSize(300, 480);
		dialog.open();
	}

	private final MaterializationSpecBuilder mspec;

	private MaterializationContext materializationContext;

	private ResolutionContext context;

	private BillOfMaterials bom;

	public QueryWizard() {
		this(null, null);
	}

	private QueryWizard(ResolutionContext context, BillOfMaterials bom) {
		this.context = context;
		this.mspec = new MaterializationSpecBuilder();
		if (bom != null) {
			try {
				setBOM(bom);
			} catch (CoreException e) {
				CorePlugin.getLogger().error(e, e.toString());
			}
		}
	}

	public MaterializationContext getMaterializationContext() {
		if (materializationContext == null) {
			materializationContext = new MaterializationContext(getBOM(), mspec.createMaterializationSpec(), getContext());
			materializationContext.setContinueOnError(context.isContinueOnError());

		}
		return materializationContext;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		String title = Messages.buckminster_component_query;

		setNeedsProgressMonitor(true);

		WizardPage page;
		if (bom == null) {
			page = new SelectBOMPage(selection);
			page.setTitle(title);
			addPage(page);
		}

		page = new ResolverNodePage();
		page.setTitle(title);
		addPage(page);

		page = new RetrieveAndBindPage();
		page.setTitle(title);
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		if (getBOM() == null)
			return false;

		try {
			MaterializationJob job = new MaterializationJob(getMaterializationContext());
			job.schedule();
			return true;
		} catch (Exception e) {
			Throwable t = BuckminsterException.unwind(e);
			ByteArrayOutputStream bld = new ByteArrayOutputStream();
			PrintStream p = new PrintStream(bld);
			BuckminsterException.deeplyPrint(t, p, false);
			p.flush();
			String msg = new String(bld.toByteArray());
			CorePlugin.getLogger().error(t, msg);
			((WizardPage) getContainer().getCurrentPage()).setErrorMessage(msg);
		}
		return false;
	}

	public void resetBOM() {
		bom = null;
	}

	BillOfMaterials getBOM() {
		if (bom == null)
			throw new IllegalStateException(Messages.wizard_not_yet_initialized_with_bom);
		return bom;
	}

	ResolutionContext getContext() {
		if (context == null)
			throw new IllegalStateException(Messages.wizard_not_yet_initialized_with_bom);
		return context;
	}

	MaterializationSpecBuilder getMaterializationSpec() {
		return mspec;
	}

	boolean hasBOM() {
		return bom != null;
	}

	void invalidateMaterializationContext() {
		materializationContext = null;
	}

	void setBOM(BillOfMaterials bom) throws CoreException {
		this.bom = bom;
		if (context == null)
			context = new ResolutionContext(bom.getQuery());
		mspec.setName(bom.getViewName());
		bom.addMaterializationNodes(mspec);
	}
}

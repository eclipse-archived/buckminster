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
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.internal.MaterializeAndBindRunnable;
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
public class QueryWizard extends Wizard implements INewWizard
{
	public static void openWizard(IWorkbenchPartSite site, ResolutionContext context, BillOfMaterials bom)
	{
		final QueryWizard wizard = new QueryWizard(context, bom);
		wizard.init(PlatformUI.getWorkbench(), null);
		WizardDialog dialog = new WizardDialog(site.getShell(), wizard);
		dialog.setPageSize(300, 480);
		dialog.open();
	}

	public static void openWizard(IWorkbenchPart targetPart, IStructuredSelection selection)
	{
		final QueryWizard wizard = new QueryWizard();
		IWorkbenchPartSite site = targetPart.getSite();
		wizard.init(site.getWorkbenchWindow().getWorkbench(), selection);
		WizardDialog dialog = new WizardDialog(site.getShell(), wizard);
		dialog.setPageSize(300, 480);
		dialog.open();
	}

	private final MaterializationSpecBuilder m_mspec;
	private MaterializationContext m_materializationContext;
	private ResolutionContext m_context;
	private BillOfMaterials m_bom;

	public QueryWizard()
	{
		this(null, null);
	}

	private QueryWizard(ResolutionContext context, BillOfMaterials bom)
	{
		m_context = context;
		m_mspec = new MaterializationSpecBuilder();
		if(bom != null)
		{
			try
			{
				setBOM(bom);
			}
			catch(CoreException e)
			{
				CorePlugin.getLogger().error(e, e.toString());
			}
		}
	}

	public void init(IWorkbench workbench, IStructuredSelection selection)
	{
		String title = UiPlugin.getResourceString("NewQueryWizard.title"); //$NON-NLS-1$

		setNeedsProgressMonitor(true);

		WizardPage page;
		if(m_bom == null)
		{
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

	public MaterializationContext getMaterializationContext()
	{
		if(m_materializationContext == null)
			m_materializationContext = new MaterializationContext(getBOM(), m_mspec.createMaterializationSpec(), getContext());
		return m_materializationContext;
	}

	@Override
	public boolean performFinish()
	{
		BillOfMaterials bom = getBOM();
		if(bom == null)
			return false;

		try
		{
			getContainer().run(true, true, new MaterializeAndBindRunnable(getMaterializationContext()));
			return true;
		}
		catch(InterruptedException e)
		{
			((WizardPage)getContainer().getCurrentPage()).setErrorMessage("Operation cancelled");
		}
		catch(Exception e)
		{
			Throwable t = BuckminsterException.unwind(e);
			ByteArrayOutputStream bld = new ByteArrayOutputStream();
			PrintStream p = new PrintStream(bld);
			BuckminsterException.deeplyPrint(t, p, false);
			p.flush();
			String msg = new String(bld.toByteArray());
			CorePlugin.getLogger().error(t, msg);
			((WizardPage)getContainer().getCurrentPage()).setErrorMessage(msg);
		}
		return false;
	}

	BillOfMaterials getBOM()
	{
		if(m_bom == null)
			throw new IllegalStateException("Wizard not yet initialized with BOM");
		return m_bom;
	}

	ResolutionContext getContext()
	{
		if(m_context == null)
			throw new IllegalStateException("Wizard not yet initialized with BOM");
		return m_context;
	}

	MaterializationSpecBuilder getMaterializationSpec()
	{
		return m_mspec;
	}

	void invalidateMaterializationContext()
	{
		m_materializationContext = null;
	}

	boolean hasBOM()
	{
		return m_bom != null;
	}

	void setBOM(BillOfMaterials bom) throws CoreException
	{
		m_bom = bom;
		if(m_context == null)
			m_context = new ResolutionContext(bom.getQuery());
		m_mspec.setName(bom.getViewName());
		bom.addMaterializationNodes(m_mspec);
	}

	public void resetBOM()
	{
		m_bom = null;
	}
}

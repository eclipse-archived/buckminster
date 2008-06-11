/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.equinox.p2.authoring.forms;

import org.eclipse.equinox.p2.authoring.P2AuthoringImages;
import org.eclipse.equinox.p2.authoring.P2AuthoringUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * An Abstract Master Details form block that handles a button bar and defines the contract for more specific master
 * details constructs.
 * 
 * @author Henrik Lindberg
 * 
 */
public abstract class AbstractMasterDetailsBlock extends MasterDetailsSubBlock
{
	protected FormPage m_formPage;

	protected String m_name;

	protected String m_description;

	public AbstractMasterDetailsBlock(FormPage page, Object layoutData)
	{
		super(layoutData);
		m_formPage = page;
		m_name = "undefined";
		m_description = null; // is undefined
	}

	/**
	 * @return an ILabelProvider for the Master data.
	 */
	public abstract IBaseLabelProvider getMasterLabelProvider();

	/**
	 * @return the title/name of the section
	 */
	public String getName()
	{
		return m_name;
	}

	public void setName(String name)
	{
		m_name = name;
	}

	public String getDescription()
	{
		return m_description;
	}

	public void setDescription(String description)
	{
		m_description = description;
	}

	/**
	 * @return the content provider
	 */
	public abstract IStructuredContentProvider getMasterContentProvider();

	/**
	 * @return a master detail controller to deal with add, remove, up, down
	 */
	public abstract IMasterDetailsController getMasterDetailsController();

	/**
	 * @return a page details provider for selected details
	 */
	public abstract IDetailsPageProvider getDetailsPageProvider();

	/**
	 * Sets the selection. The intended use is for undo/redo operations to be able to set the
	 * context for the operation (i.e. to make the correct detail page display).
	 * @param selection
	 */
	public abstract void setSelected(ISelection selection);

	/**
	 * Creates a Button bar with Add, Remove, Up, Down buttons and attaches listeners that will call the
	 * MasterDetailsController methods for the corresponding operations.
	 * 
	 * @param toolkit
	 * @param client
	 */
	protected final StandardButtons createStandardButtonBar(FormToolkit toolkit, Composite client)
	{
		Composite buttonBar = toolkit.createComposite(client);
		buttonBar.setLayoutData(new GridData(SWT.BEGINNING, SWT.TOP, false, false));
		// DEBUG LAYOUT:
		// buttonBar.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_YELLOW));
		// TODO: Top of Add button does not align with top of buttonBar
		GridLayout gl = new GridLayout(1, false);
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		gl.horizontalSpacing = 0;
		gl.verticalSpacing = 0;
		gl.marginTop = 0;
		buttonBar.setLayout(gl);

		Button add = toolkit.createButton(buttonBar, "Add...", SWT.PUSH);
		GridData gd = new GridData(SWT.FILL, SWT.TOP, false, false);
		gd.verticalIndent = 0;
		add.setLayoutData(gd);
		add.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_MAGENTA));

		configureAddButton(add);
		Button remove = toolkit.createButton(buttonBar, "Remove", SWT.PUSH);
		remove.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		configureRemoveButton(remove);
		Button up = toolkit.createButton(buttonBar, "Up", SWT.PUSH);
		up.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		configureUpButton(up);
		Button down = toolkit.createButton(buttonBar, "Down", SWT.PUSH);
		down.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		configureDownButton(down);
		buttonBar.layout();
		return new StandardButtons(add, remove, up, down);
	}

	/**
	 * This implementation configures the Add Button to call IMasterDetailsController.add() method. A derived class may
	 * override and add a menu to the button.
	 * 
	 * @param b
	 */
	protected void configureAddButton(final Button b)
	{
		b.addSelectionListener(new SimpleSelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				getMasterDetailsController().add();
			}

		});
	}

	/**
	 * This implementation configures the Remove Button to call IMasterDetailsController.remove() method. A derived
	 * class may override and add a menu to the button.
	 * 
	 * @param b
	 */
	protected void configureRemoveButton(final Button b)
	{	
		b.setEnabled(false);
		b.addSelectionListener(new SimpleSelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				getMasterDetailsController().remove();
			}

		});
	}

	/**
	 * This implementation configures the Up Button to call IMasterDetailsController.up() method. A derived class may
	 * override and add a menu to the button.
	 * 
	 * @param b
	 */
	protected void configureUpButton(final Button b)
	{
		b.setEnabled(false);
		b.addSelectionListener(new SimpleSelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				getMasterDetailsController().up();
			}

		});
	}

	/**
	 * This implementation configures the Down Button to call IMasterDetailsController.down() method. A derived class
	 * may override and add a menu to the button.
	 * 
	 * @param b
	 */
	protected void configureDownButton(final Button b)
	{
		b.setEnabled(false);
		b.addSelectionListener(new SimpleSelectionListener()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				getMasterDetailsController().down();
			}
		});
	}

	private abstract static class SimpleSelectionListener implements SelectionListener
	{
		public void widgetDefaultSelected(SelectionEvent e)
		{
			widgetSelected(e);
		}

		public abstract void widgetSelected(SelectionEvent e);
	}

	@Override
	protected void createToolBarActions(IManagedForm managedForm)
	{

		final ScrolledForm form = managedForm.getForm();

		Action haction = new Action("hor", Action.AS_RADIO_BUTTON){ //$NON-NLS-1$

			@Override
			public void run()
			{
				sashForm.setOrientation(SWT.HORIZONTAL);
				form.reflow(true);
			}
		};

		haction.setChecked(true);
		haction.setToolTipText("horizontal");
		haction.setImageDescriptor(P2AuthoringUIPlugin.getDefault().getImageRegistry().getDescriptor(
				P2AuthoringImages.IMG_HORIZONTAL));

		Action vaction = new Action("ver", Action.AS_RADIO_BUTTON){ //$NON-NLS-1$

			@Override
			public void run()
			{
				sashForm.setOrientation(SWT.VERTICAL);
				form.reflow(true);
			}
		};

		vaction.setChecked(false);
		vaction.setToolTipText("vertical");
		vaction.setImageDescriptor(P2AuthoringUIPlugin.getDefault().getImageRegistry().getDescriptor(
				P2AuthoringImages.IMG_VERTICAL));

		form.getToolBarManager().add(haction);
		form.getToolBarManager().add(vaction);

	}

	/**
	 * Registers the IDetailsPageProvider returned from {@link #getDetailsPageProvider()} as the page provider. A
	 * derived class may want to register individual pages for individual classes instead in which case this method
	 * should be overridden.
	 */
	@Override
	protected void registerPages(DetailsPart theDetailsPart)
	{
		theDetailsPart.setPageProvider(getDetailsPageProvider());
	}
	protected static class StandardButtons
	{
		public final Button add;
		public final Button remove;
		public final Button up;
		public final Button down;
		protected StandardButtons(Button add, Button remove, Button up, Button down)
		{
			this.add = add;
			this.remove = remove;
			this.up = up;
			this.down = down;
		}
	}
}

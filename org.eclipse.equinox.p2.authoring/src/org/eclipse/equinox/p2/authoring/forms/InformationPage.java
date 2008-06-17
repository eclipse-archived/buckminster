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

import org.eclipse.equinox.internal.p2.metadata.InstallableUnit;
import org.eclipse.equinox.p2.authoring.InstallableUnitEditor;
import org.eclipse.equinox.p2.authoring.P2AuthoringImages;
import org.eclipse.equinox.p2.authoring.forms.validators.NullValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.URIEditValidator;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.CopyrightBuilder;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.LicenseBuilder;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * An Information Page for editing Copyright, License, and Description of an Installable Unit.
 * 
 * @author Henrik Lindberg
 */
@SuppressWarnings("restriction")
public class InformationPage extends RichFormPage implements IPageMementoProvider
{
	public static final String PAGE_ID = "info.id"; //$NON-NLS-1$

	private CTabFolder m_tabFolder;

	private EditAdapterFormPart m_editAdapters = new EditAdapterFormPart();

	private SwitchedAdapter m_currentAdapter;

	/**
	 * Base class used to adapt between the shared text fields for URL and text and the data model.
	 * 
	 * @author Henrik Lindberg
	 * 
	 */
	private abstract class SwitchedAdapter
	{
		protected IMutator m_urlMutator;

		protected IMutator m_textMutator;

		IMutator getUrlMutator()
		{
			return m_urlMutator;
		}

		IMutator getTextMutator()
		{
			return m_textMutator;
		}

		protected String checkedString(String val)
		{
			return val == null
					? "" : val; //$NON-NLS-1$
		}

	}

	/**
	 * Switched adapter for adapting to License. TODO: this could probably be simplified more - but one of the types
	 * being adapted two has very different access as Description info is set as properties, and the other two types are
	 * via builders.
	 * 
	 * @author henrik
	 * 
	 */
	private class LicenseAdapter extends SwitchedAdapter
	{
		private LicenseBuilder getLicenseBuilder()
		{
			InstallableUnitBuilder iu = getIU();
			if(iu == null)
				return null; //$NON-NLS-1$
			return iu.getLicense();
		}

		LicenseAdapter()
		{
			m_urlMutator = new Mutator()
			{
				@Override
				public String getValue()
				{
					LicenseBuilder license = getLicenseBuilder();
					if(license == null)
						return ""; //$NON-NLS-1$
					return checkedString(license.getUrl());
				}

				@Override
				public void setValue(String input) throws Exception
				{
					InstallableUnitBuilder iu = getIU();
					if(iu == null)
						return;
					LicenseBuilder license = iu.getLicense();
					if(license == null)
					{
						license = new LicenseBuilder();
						iu.setLicense(license);
					}
					license.setUrl(input);
				}
			};
			m_textMutator = new Mutator()
			{
				@Override
				public String getValue()
				{
					LicenseBuilder license = getLicenseBuilder();
					if(license == null)
						return ""; //$NON-NLS-1$
					return checkedString(license.getBody());
				}

				@Override
				public void setValue(String input) throws Exception
				{
					InstallableUnitBuilder iu = getIU();
					if(iu == null)
						return;
					LicenseBuilder license = iu.getLicense();
					if(license == null)
					{
						license = new LicenseBuilder();
						iu.setLicense(license);
					}
					license.setBody(input);
				}
			};
		}
	}

	/**
	 * Switched adapter for adapting to Copyright.
	 * 
	 * @author Henrik Lindberg
	 * 
	 */
	private class CopyrightAdapter extends SwitchedAdapter
	{
		private CopyrightBuilder getCopyrightBuilder()
		{
			InstallableUnitBuilder iu = getIU();
			if(iu == null)
				return null;
			return iu.getCopyright();
		}

		CopyrightAdapter()
		{
			m_urlMutator = new Mutator()
			{
				@Override
				public String getValue()
				{
					CopyrightBuilder cpyr = getCopyrightBuilder();
					if(cpyr == null)
						return ""; //$NON-NLS-1$
					return checkedString(cpyr.getUrl());
				}

				@Override
				public void setValue(String input) throws Exception
				{
					InstallableUnitBuilder iu = getIU();
					if(iu == null)
						return;
					CopyrightBuilder cpyr = iu.getCopyright();
					if(cpyr == null)
					{
						cpyr = new CopyrightBuilder();
						iu.setCopyright(cpyr);
					}
					cpyr.setUrl(input);
				}
			};
			m_textMutator = new Mutator()
			{
				@Override
				public String getValue()
				{
					CopyrightBuilder cpyr = getCopyrightBuilder();
					if(cpyr == null)
						return ""; //$NON-NLS-1$
					return checkedString(cpyr.getBody());
				}

				@Override
				public void setValue(String input) throws Exception
				{
					InstallableUnitBuilder iu = getIU();
					if(iu == null)
						return;
					CopyrightBuilder cpyr = iu.getCopyright();
					if(cpyr == null)
					{
						cpyr = new CopyrightBuilder();
						iu.setCopyright(cpyr);
					}
					cpyr.setBody(input);
				}
			};
		}
	}

	/**
	 * Switched adapter for adapting to Description.
	 * 
	 * @author Henrik Lindberg
	 * 
	 */
	private class DescAdapter extends SwitchedAdapter
	{
		DescAdapter()
		{
			m_urlMutator = new Mutator()
			{
				@Override
				public String getValue()
				{
					InstallableUnitBuilder iu = getIU();
					if(iu == null)
						return ""; //$NON-NLS-1$
					return checkedString(iu.getProperty(InstallableUnit.PROP_DESCRIPTION_URL));
				}

				@Override
				public void setValue(String input) throws Exception
				{
					InstallableUnitBuilder iu = getIU();
					if(iu == null)
						return;
					iu.setProperty(InstallableUnit.PROP_DESCRIPTION_URL, input);
				}
			};
			m_textMutator = new Mutator()
			{
				@Override
				public String getValue()
				{
					InstallableUnitBuilder iu = getIU();
					if(iu == null)
						return ""; //$NON-NLS-1$
					return checkedString(iu.getProperty(InstallableUnit.PROP_DESCRIPTION));
				}

				@Override
				public void setValue(String input) throws Exception
				{
					InstallableUnitBuilder iu = getIU();
					if(iu == null)
						return;
					iu.setProperty(InstallableUnit.PROP_DESCRIPTION, input);
				}
			};
		}
	}

	public InformationPage(FormEditor editor)
	{
		super(editor, PAGE_ID, "Information");
		m_header = "Information";
		m_headerImage = P2AuthoringImages.getImage(P2AuthoringImages.IMG_FILE); // TODO: INFO/DOC IMAGE
		m_makeColumnsEqualWidth = false;
		m_numColumns = 1;
	}

	@Override
	protected void addFormContent(IManagedForm managedForm)
	{
		// Page uses one edit adapters form part to manage the lifecycle of fields.
		managedForm.addPart(m_editAdapters);

		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();

		// Modify the default table wrap layout and use a grid layout instead (this to make sure
		// controls gets the full height available).
		GridLayout layout = new GridLayout(m_numColumns, false);
		layout.marginWidth = 10;
		form.getBody().setLayout(layout);

		m_tabFolder = new CTabFolder(form.getBody(), SWT.FLAT | SWT.TOP);
		m_tabFolder.setSimple(true);
		// m_tabFolder.setBorderVisible(true); // for visual debug of its size..
		toolkit.adapt(m_tabFolder, true, true);

		// Since there is no data in the tabfolder, set a small height as the default is quite big.
		// (Note if a table wrap layout is used, this height needs to be 20 pixels)
		GridData tabFolderData = new GridData(SWT.FILL, SWT.TOP, true, false);
		tabFolderData.heightHint = 2;
		m_tabFolder.setLayoutData(tabFolderData);

		// TODO: make the gradient look nice
		Color selectedColor = toolkit.getColors().getColor(IFormColors.TB_BG);
		m_tabFolder.setSelectionBackground(new Color[] { selectedColor, toolkit.getColors().getBackground() },
				new int[] { 50 });

		toolkit.paintBordersFor(m_tabFolder);

		// setup for reuse
		FormColors colors = toolkit.getColors();
		Color headerColor = colors.getColor(IFormColors.TITLE);
		Label label = null;

		// --LICENSE TAB
		CTabItem item = new CTabItem(m_tabFolder, SWT.NULL);
		item.setText("License Agreement");
		item.setData(m_currentAdapter = new LicenseAdapter());

		Composite tabComposite = toolkit.createComposite(form.getBody());
		// tabComposite.setBackground(colors.getColor(IFormColors.H_GRADIENT_START)); // debug layout using color

		GridData tabCompositeData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tabComposite.setLayoutData(tabCompositeData);

		GridLayout glayout = new GridLayout();
		glayout.numColumns = 2;
		tabComposite.setLayout(glayout);

		// License URL
		label = toolkit.createLabel(tabComposite, "Optional URL:");
		label.setForeground(headerColor);
		final Text urlText = toolkit.createText(tabComposite, "");
		urlText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("lurlText", urlText, //$NON-NLS-1$
				URIEditValidator.instance(), new Mutator()
				{
					@Override
					public String getValue()
					{
						return m_currentAdapter.m_urlMutator.getValue();
					}

					@Override
					public void setValue(String input) throws Exception
					{
						m_currentAdapter.m_urlMutator.setValue(input);
					}
				});

		// License Body
		label = toolkit.createLabel(tabComposite, "Text:");
		label.setForeground(headerColor);
		label.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));

		final Composite rect = new Composite(tabComposite, SWT.NONE);
		rect.setLayout(new GridLayout(0, false)); // prevent layout from setting size
		rect.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		final Text licenseText = toolkit.createText(rect, "", SWT.MULTI | SWT.V_SCROLL); //$NON-NLS-1$
		rect.addControlListener(new ControlAdapter()
		{
			@Override
			public void controlResized(ControlEvent e)
			{
				licenseText.setBounds(0, 0, rect.getSize().x, rect.getSize().y);
			}

		});

		licenseText.setFont(JFaceResources.getTextFont());
		m_editAdapters.createEditAdapter("licText", licenseText, //$NON-NLS-1$
				NullValidator.instance(), new Mutator()
				{
					@Override
					public String getValue()
					{
						return m_currentAdapter.m_textMutator.getValue();
					}

					@Override
					public void setValue(String input) throws Exception
					{
						m_currentAdapter.m_textMutator.setValue(input);
					}
				});

		// --COPYRIGHT
		item = new CTabItem(m_tabFolder, SWT.NULL);
		item.setText("Copyright Notice");
		item.setData(new CopyrightAdapter());

		// --DESCRIPTION
		item = new CTabItem(m_tabFolder, SWT.NULL);
		item.setText("Unit Description");
		item.setData(new DescAdapter());

		m_tabFolder.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				updateSelection();
			}
		});
		m_tabFolder.setSelection(0);
	}

	private InstallableUnitBuilder getIU()
	{
		return ((InstallableUnitEditor)getEditor()).getInstallableUnit();
	}

	private void updateSelection()
	{
		// commit current values
		boolean dirty = m_editAdapters.isDirty();
		m_editAdapters.commit(false);
		// make the switch
		CTabItem item = m_tabFolder.getSelection();
		m_currentAdapter = (SwitchedAdapter)item.getData();
		// set stale data
		m_editAdapters.refresh();
		// restate if it was dirty because the commit/refresh otherwise marks the form part as
		// 'clean' and save has no effect
		if(dirty)
			m_editAdapters.markDirty();
	}
	public Object getPageMemento()
	{
		return new Integer(m_tabFolder.getSelectionIndex());
	}

	public void setPageMemento(Object memento)
	{
		m_tabFolder.setSelection(((Integer)memento).intValue());
	}

}

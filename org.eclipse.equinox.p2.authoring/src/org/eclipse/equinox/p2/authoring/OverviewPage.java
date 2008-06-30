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

package org.eclipse.equinox.p2.authoring;

import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.authoring.forms.EditAdapterFormPart;
import org.eclipse.equinox.p2.authoring.forms.Mutator;
import org.eclipse.equinox.p2.authoring.forms.RichFormPage;
import org.eclipse.equinox.p2.authoring.forms.validators.LDAPFilterValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.NullValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.RequiredValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.StructuredNameValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.VersionValidator;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * This is an Overview page with the most basic information for an installable unit such as namespace, name, id, etc.
 * 
 * @author Henrik Lindberg
 * 
 */
@SuppressWarnings("restriction")
class OverviewPage extends RichFormPage
{
	public static final String PAGE_ID = "overview.id"; //$NON-NLS-1$

	private EditAdapterFormPart m_editAdapters = new EditAdapterFormPart();

	public OverviewPage(FormEditor editor)
	{
		super(editor, PAGE_ID, "Overview");
		m_header = "Overview";
		m_headerImage = P2AuthoringImages.getImage(P2AuthoringImages.IMG_OVERVIEW);
		m_makeColumnsEqualWidth = true;
	}

	@Override
	public void setInput(IEditorInput input)
	{
		super.setInput(input);
	}

	/**
	 * @see org.eclipse.equinox.p2.authoring.forms.RichFormPage#addFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void addFormContent(IManagedForm managedForm)
	{
		// Page uses one edit adapters form part to manage the lifecycle of fields.
		managedForm.addPart(m_editAdapters);

		final ScrolledForm scrolledForm = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		// create two columns

		Composite leftColumn = toolkit.createComposite(scrolledForm.getBody());
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 1;
		TableWrapData twd = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP);
		leftColumn.setLayout(layout);
		leftColumn.setLayoutData(twd);

		Composite rightColumn = toolkit.createComposite(scrolledForm.getBody());
		layout = new TableWrapLayout();
		layout.numColumns = 1;
		twd = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP);
		rightColumn.setLayout(layout);
		rightColumn.setLayoutData(twd);

		// General Info
		Section section = createGeneralSection(toolkit, leftColumn);
		managedForm.addPart(new SectionPart(section));
		section.addExpansionListener(getReflowListener());

		// Content Section
		section = createContentSection(toolkit, rightColumn);
		section.addExpansionListener(getReflowListener());

		// Filters
		section = createFilterSection(toolkit, leftColumn);
		section.addExpansionListener(getReflowListener());

		// Testing
		section = createTestingSection(toolkit, rightColumn);
		section.addExpansionListener(getReflowListener());

		// Export/Publish/Use
		section = createPublishingSection(toolkit, rightColumn);
		section.addExpansionListener(getReflowListener());
	}

	private InstallableUnitBuilder getIU()
	{
		return ((InstallableUnitEditor)getEditor()).getInstallableUnit();
	}

	private Section createGeneralSection(FormToolkit toolkit, Composite parent)
	{
		Section section = toolkit.createSection(parent, //
				Section.DESCRIPTION | //
						Section.TITLE_BAR | //
						// Section.TWISTIE | // Expandable by user
						Section.EXPANDED);
		TableWrapData td = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP);
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("General information");
		section.setDescription("This section describes the general information about this installable unit.");
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);

		// setup for reuse
		FormColors colors = toolkit.getColors();
		Color headerColor = colors.getColor("org.eclipse.ui.forms.TITLE"); //$NON-NLS-1$
		Label label = null;

		// Namespace
		label = toolkit.createLabel(sectionClient, "Namespace:");
		label.setForeground(headerColor);
		final Text nsText = toolkit.createText(sectionClient, "");
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd.minimumWidth = 200;
		nsText.setLayoutData(gd);
		m_editAdapters.createEditAdapter("nsText", nsText, //$NON-NLS-1$
				new RequiredValidator(StructuredNameValidator.instance()), new Mutator()
				{
					@Override
					public String getValue()
					{
						InstallableUnitBuilder iu = getIU();
						if(iu == null)
							return ""; //$NON-NLS-1$
						String val = iu.getProperty(IInstallableUnit.NAMESPACE_FLAVOR);
						return val != null
								? val
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						InstallableUnitBuilder iu = getIU();
						if(iu != null)
							iu.setProperty(IInstallableUnit.NAMESPACE_FLAVOR, input);
					}
				});

		// ID
		label = toolkit.createLabel(sectionClient, "ID:");
		label.setForeground(colors.getColor("org.eclipse.ui.forms.TITLE"));
		final Text idText = toolkit.createText(sectionClient, "");
		idText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("idText", idText, //$NON-NLS-1$
				new RequiredValidator(NullValidator.instance()), // TODO: Add validator for id
				new Mutator()
				{
					@Override
					public String getValue()
					{
						InstallableUnitBuilder iu = getIU();
						if(iu == null)
							return ""; //$NON-NLS-1$
						String val = iu.getId();
						return val != null
								? val
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						InstallableUnitBuilder iu = getIU();
						if(iu != null)
							iu.setId(input);
					}
				});

		// Name
		label = toolkit.createLabel(sectionClient, "Name:");
		label.setForeground(headerColor);
		final Text nameText = toolkit.createText(sectionClient, "");
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("nameText", nameText, //$NON-NLS-1$
				new RequiredValidator(StructuredNameValidator.instance()), //
				new Mutator()
				{
					@Override
					public String getValue()
					{
						InstallableUnitBuilder iu = getIU();
						if(iu == null)
							return ""; //$NON-NLS-1$
						String val = iu.getProperty(IInstallableUnit.PROP_NAME);
						return val != null
								? val
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						InstallableUnitBuilder iu = getIU();
						if(iu != null)
							iu.setProperty(IInstallableUnit.PROP_NAME, input);
					}
				});

		// Version
		label = toolkit.createLabel(sectionClient, "Version:");
		label.setForeground(headerColor);
		final Text versionText = toolkit.createText(sectionClient, "");
		versionText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("versionText", versionText,
				new RequiredValidator(VersionValidator.instance()), //
				new Mutator()
				{
					@Override
					public String getValue()
					{
						InstallableUnitBuilder iu = getIU();
						if(iu == null)
							return ""; //$NON-NLS-1$
						String val = iu.getVersion();
						return val != null
								? val
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						InstallableUnitBuilder iu = getIU();
						if(iu != null)
							iu.setVersion(input);
					}
				});

		// Provider
		label = toolkit.createLabel(sectionClient, "Provider:");
		label.setForeground(colors.getColor("org.eclipse.ui.forms.TITLE"));
		final Text providerText = toolkit.createText(sectionClient, "");
		providerText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("providerText", providerText, //$NON-NLS-1$
				NullValidator.instance(), //
				new Mutator()
				{
					@Override
					public String getValue()
					{
						InstallableUnitBuilder iu = getIU();
						if(iu == null)
							return ""; //$NON-NLS-1$
						String val = iu.getProperty(IInstallableUnit.PROP_PROVIDER);
						return val != null
								? val
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						InstallableUnitBuilder iu = getIU();
						if(iu != null)
							iu.setProperty(IInstallableUnit.PROP_PROVIDER, input);
					}
				});
		
		// -- SINGLETON
		label = toolkit.createLabel(sectionClient, "");
		label.setForeground(headerColor);
		final Button greedyButton = toolkit.createButton(sectionClient, "Singleton", SWT.CHECK);
		greedyButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("singleton", greedyButton, //$NON-NLS-1$
				null, // validator of boolean not needed
				new Mutator()
				{
					@Override
					public boolean getBooleanValue()
					{
						InstallableUnitBuilder iu = getIU();
						return iu == null ? false : iu.isSingleton();
					}

					@Override
					public void setValue(boolean input) throws Exception
					{
						InstallableUnitBuilder iu = getIU();
						if(iu != null)
							iu.setSingleton(input);
					}
				});

		//
		section.setClient(sectionClient);
		return section;

	}

	private Section createFilterSection(FormToolkit toolkit, Composite parent)
	{
		Section section = toolkit.createSection(parent, //
				Section.DESCRIPTION | //
						Section.TITLE_BAR | //
						Section.TWISTIE | // Expandable by user
						Section.EXPANDED);

		TableWrapData td = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP);
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("Profile filter");
		section
				.setDescription("This section describes a filter for the profile where this installable unit can be installed.");
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);

		FormColors colors = toolkit.getColors();
		Color headerColor = colors.getColor("org.eclipse.ui.forms.TITLE"); //$NON-NLS-1$

		Label label = toolkit.createLabel(sectionClient, "Filter:");
		label.setForeground(headerColor);
		Text text = toolkit.createText(sectionClient, ""); //$NON-NLS-1$
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("filterText", text, //$NON-NLS-1$
				LDAPFilterValidator.instance(), //
				new Mutator()
				{
					@Override
					public String getValue()
					{
						InstallableUnitBuilder iu = getIU();
						if(iu == null)
							return ""; //$NON-NLS-1$
						String val = iu.getFilter();
						return val != null
								? val
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						InstallableUnitBuilder iu = getIU();
						if(iu != null)
							iu.setFilter(input);
					}
				});

		section.setClient(sectionClient);
		return section;
	}

	private Section createContentSection(FormToolkit toolkit, Composite parent)
	{
		Section section = toolkit.createSection(parent, //
				Section.DESCRIPTION | //
						Section.TITLE_BAR | //
						// Section.TWISTIE | // Expandable by user
						Section.EXPANDED);

		TableWrapData td = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP);
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("Content");
		section.setDescription("The content of this installable unit is made up of:");
		Composite sectionClient = toolkit.createComposite(section);
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 1;

		sectionClient.setLayout(layout);

		// Form text describing the content -
		// TODO: the first part should be a hyperlink that takes user to correct tab
		// TODO: the bullet should be replaced with an image
		StringBuffer buf = new StringBuffer();
		buf.append("<form>"); //$NON-NLS-1$
		buf.append("<li>Required: the capabilities required from other installable units.</li>");
		buf.append("<li>Provided: the capabilities this installable unit provides.</li>");
		buf.append("<li>Information: copyright, license, and documentation for this installable unit.</li>");
		buf.append("<li>Touchpoint: installation details.</li>");
		buf.append("<li>Update: information about what this installable unit is an update of.</li>");
		buf.append("</form>"); //$NON-NLS-1$
		FormText ftext = toolkit.createFormText(sectionClient, true);
		ftext.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP));
		ftext.setWhitespaceNormalized(true);
		ftext.setText(buf.toString(), true, false);

		section.setClient(sectionClient);
		return section;
	}

	private Section createTestingSection(FormToolkit toolkit, Composite parent)
	{
		Section section = toolkit.createSection(parent, //
				Section.DESCRIPTION | //
						Section.TITLE_BAR | //
						// Section.TWISTIE | // Expandable by user
						Section.EXPANDED);

		TableWrapData td = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP);
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("Testing");
		section.setDescription("TBD: This installable unit can be tested by:");
		Composite sectionClient = toolkit.createComposite(section);
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 1;

		sectionClient.setLayout(layout);

		// Form text describing how IU can be tested -
		// TODO: define how it can be tested - text is just placeholder...
		// TODO: the first part should be a hyperlink that takes user to correct tab
		// TODO: the bullet should be replaced with an image
		StringBuffer buf = new StringBuffer();
		buf.append("<form>"); //$NON-NLS-1$
		buf.append("<li>Resolve: run a resolution of all required capabilities.</li>");
		buf.append("<li>Install: run self hosted and install it.</li>");
		buf.append("</form>"); //$NON-NLS-1$
		FormText ftext = toolkit.createFormText(sectionClient, true);
		ftext.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP));
		ftext.setWhitespaceNormalized(true);
		ftext.setText(buf.toString(), true, false);

		section.setClient(sectionClient);
		return section;
	}

	private Section createPublishingSection(FormToolkit toolkit, Composite parent)
	{
		Section section = toolkit.createSection(parent, //
				Section.DESCRIPTION | //
						Section.TITLE_BAR | //
						// Section.TWISTIE | // Expandable by user
						Section.EXPANDED);

		TableWrapData td = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP);
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("Publishing / Exporting / Using");
		section.setDescription("TBD: This installable unit can be exported/published/used by:");
		Composite sectionClient = toolkit.createComposite(section);
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 1;

		sectionClient.setLayout(layout);

		// Form text describing how the IU can be exported/published/used -
		// TODO: define how it can be used - text is just placeholder...
		// TODO: the first part should be a hyperlink that takes user to correct tab
		// TODO: the bullet should be replaced with an image
		StringBuffer buf = new StringBuffer();
		buf.append("<form>"); //$NON-NLS-1$
		buf.append("<li>?Self hosted: installed in self hosted mode?</li>");
		buf.append("<li>?Published: published to a meta data repository?</li>");
		buf
				.append("<li>?Exported: exported (optionally with other installable units) to a local meta data repository?</li>");
		buf.append("</form>"); //$NON-NLS-1$
		FormText ftext = toolkit.createFormText(sectionClient, true);
		ftext.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP));
		ftext.setWhitespaceNormalized(true);
		ftext.setText(buf.toString(), true, false);

		section.setClient(sectionClient);
		return section;
	}

}

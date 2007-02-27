/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.wizards;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.ExportedBillOfMaterials;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Thomas Hallgren
 */
public class SelectBOMPage extends AbstractQueryPage
{
	private Label m_topComponentLabel;

	private Text m_topComponent;

	private Button m_loadButton;

	private URL m_bomURL;

	public SelectBOMPage()
	{
		super("");
	}

	@Override
	public void setErrorMessage(String message)
	{
		QueryWizard qw = getQueryWizard();
		if(message != null)
		{
			m_bomURL = null;
			qw.resetBOM();
		}

		super.setErrorMessage(message);
		if(qw.hasBOM())
		{
			if(!isPageComplete())
			{
				try
				{
					m_topComponent.setText(qw.getBOM().getViewName());
					m_topComponentLabel.setVisible(true);
					m_topComponent.setVisible(true);
					setPageComplete(true);
				}
				catch(CoreException e)
				{
					displayException(e);
					qw.resetBOM();
				}
			}
		}
		else
		{
			if(isPageComplete())
			{
				m_topComponentLabel.setVisible(false);
				m_topComponent.setVisible(false);
				setPageComplete(false);
			}
		}
	}

	@Override
	protected Composite createControls(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1));
		lbl.setText("Select a Bill of Materials (use a URL or a file system path)");

		final Text fileName = new Text(composite, SWT.BORDER);
		fileName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		fileName.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				setBOMFile(UiUtils.trimmedValue((Text)me.widget));
			}
		});

		Button browseButton = UiUtils.createPushButton(composite, "Browse...", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				FileDialog dlg = new FileDialog(getShell());
				dlg.setFilterExtensions(new String[] { "*.bom" });
				String name = dlg.open();
				if(name != null)
					fileName.setText(name);
			}
		});
		browseButton.setLayoutData(new GridData(SWT.TRAIL, SWT.TOP, false, false));

		m_loadButton = UiUtils.createPushButton(composite, "Load", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				loadBOM();
			}
		});
		m_loadButton.setLayoutData(new GridData(SWT.TRAIL, SWT.TOP, false, false));
		m_loadButton.setEnabled(false);

		m_topComponentLabel = new Label(composite, SWT.NONE);
		m_topComponentLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 3, 1));
		m_topComponentLabel.setText("Top component of loaded Bill of Materials");
		m_topComponentLabel.setVisible(false);

		m_topComponent = new Text(composite, SWT.BORDER);
		m_topComponent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));
		m_topComponent.setEditable(false);
		m_topComponent.setVisible(false);

		setControl(composite);
		setPageComplete(false);
		return composite;
	}

	void loadBOM()
	{
		try
		{
			getContainer().run(true, true, new IRunnableWithProgress()
			{
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
				{
					InputStream input = null;
					try
					{
						input = URLUtils.openStream(m_bomURL, monitor);
						BillOfMaterials bom = CorePlugin.getDefault().getParserFactory().getBillOfMaterialsParser(true).parse(m_bomURL.toString(), input);
						bom = BillOfMaterials.importGraph((ExportedBillOfMaterials)bom);
						getQueryWizard().setBOM(bom);
					}
					catch(Throwable e)
					{
						throw new InvocationTargetException(e);
					}
					finally
					{
						IOUtils.close(input);
					}
				}	
			});
			setErrorMessage(null);
		}
		catch(InterruptedException e)
		{
		}
		catch(Exception e)
		{
			displayException(BuckminsterException.wrap(e));
		}
	}

	void setBOMFile(final String bomFile)
	{
		m_bomURL = null;
		if(bomFile == null)
		{
			setErrorMessage(null);
			m_loadButton.setEnabled(false);
			return;
		}

		URL url;
		try
		{
			url = URLUtils.normalizeToURL(bomFile);
			url = FileLocator.toFileURL(url);
		}
		catch(IOException e)
		{
			setErrorMessage("Invalid URL or filename");
			m_loadButton.setEnabled(false);
			return;
		}

		File file = FileUtils.getFile(url);
		if(file == null)
		{
			// URL is remote. Let's assume it's OK for now
			//
			String path = url.getPath();
			if(path == null || path.length() == 0 || path.endsWith("/"))
			{
				setErrorMessage("URL path is not valid");
				m_loadButton.setEnabled(false);
			}
			else
			{
				m_bomURL = url;
				m_loadButton.setEnabled(true);
				setErrorMessage(null);
			}
		}
		else
		{
			m_loadButton.setEnabled(false);
			if(file.isFile() && file.canRead())
			{
				m_bomURL = url;
				loadBOM();
			}
			else
				setErrorMessage("File does not exist");
		}
	}
}

/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.progress;

import java.net.URL;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * @author Karel Brezina
 *
 */
public class MaterializationSubProgressMonitor implements IProgressMonitor
{
	private static final String PROGRESS_STOP = "progress_stop.gif";
	private static final int MAX_LABEL_LENGTH = 90;

	private Composite m_parentComposite;
	private Composite m_composite;
	private Label m_subTaskLabel;
	private ProgressBar m_progressBar;
	private ToolItem m_cancelButton;
	
	private boolean m_canceled = false;
	
	public MaterializationSubProgressMonitor(Composite parent)
	{	
		m_parentComposite = parent;
	}
	
	private Control createControl(Composite parent)
	{
		m_composite = new Composite(parent, SWT.NONE);
		m_composite.setLayout(new GridLayout(2, false));
		m_composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		m_subTaskLabel = new Label(m_composite, SWT.NONE);
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		m_subTaskLabel.setLayoutData(layoutData);

		m_progressBar = new ProgressBar(m_composite, SWT.NONE);
		layoutData = new GridData(GridData.FILL_HORIZONTAL);
		m_progressBar.setLayoutData(layoutData);
		
		ToolBar cancelToolBar = new ToolBar (m_composite, SWT.FLAT);
		m_cancelButton = new ToolItem (cancelToolBar, SWT.PUSH);
		//cancelToolBar.pack ();
	
		//m_cancelButton = new Button(m_composite, SWT.TOGGLE);
		m_cancelButton.setImage(getImage(PROGRESS_STOP));
		cancelToolBar.setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_ARROW));

		m_cancelButton.addSelectionListener(new SelectionAdapter(){

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				setCanceled(true);
				m_cancelButton.setEnabled(false);			
			}});
		
		m_parentComposite.layout(new Control[]{m_composite});
		
		return m_composite;
	}
	
	private Image getImage(String imageName)
	{
		Class<?> myClass = this.getClass();
		String imageResource = "/icons/" + imageName;
		URL imageUrl = myClass.getResource(imageResource);
		return ImageDescriptor.createFromURL(imageUrl).createImage();
	}

	public void beginTask(final String name, final int totalWork)
	{
		Display.getDefault().asyncExec(new Runnable()
		{
			public void run()
			{
				createControl(m_parentComposite);
				setTaskName(name);
				m_progressBar.setMinimum(0);
				m_progressBar.setMaximum(totalWork);
			}
		});
	}

	public void done()
	{
		Display.getDefault().asyncExec(new Runnable()
		{
			public void run()
			{
				m_composite.dispose();
				m_parentComposite.layout();
			}
		});
	}

	public void internalWorked(final double work)
	{
		Display.getDefault().asyncExec(new Runnable()
		{
			public void run()
			{
				m_progressBar.setSelection(m_progressBar.getSelection() + Double.valueOf(work).intValue());	
				//System.out.println("JOB: " + m_subTaskLabel.getText() + " " + m_progressBar.getSelection() + "/" + m_progressBar.getMaximum() + " - " + work);
			}
		});
	}

	public boolean isCanceled()
	{
		return m_canceled;
	}

	public void setCanceled(boolean value)
	{
		m_canceled = value;	
	}

	public void setTaskName(String name)
	{
		// nothing
	}

	public void subTask(final String name)
	{
		Display.getDefault().asyncExec(new Runnable()
		{
			public void run()
			{
				m_subTaskLabel.setText(name == null ? "" : (name.length() > MAX_LABEL_LENGTH ? name.substring(0, MAX_LABEL_LENGTH - 3) + "..." : name));
				m_composite.layout(new Control[] {m_subTaskLabel});
			}
		});
	}

	public void worked(final int work)
	{
		internalWorked(work);
	}
}

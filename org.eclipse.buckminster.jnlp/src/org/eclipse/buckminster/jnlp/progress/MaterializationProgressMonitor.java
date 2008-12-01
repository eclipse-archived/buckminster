/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.progress;

import java.net.URL;

import org.eclipse.buckminster.core.helpers.IJobInfo;
import org.eclipse.buckminster.jnlp.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
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
public class MaterializationProgressMonitor implements IProgressMonitor
{
	private static final String PROGRESS_STOP = "progress_stop.gif"; //$NON-NLS-1$

	private static final long DONE_SLEEP = 2000;
	
	private static final String AVAILABLE = "available"; //$NON-NLS-1$

	private MaterializationProgressProvider m_provider;
	
	private Composite m_parentComposite;

	private Composite m_composite;
	
	private String m_jobName;

	private Label m_subTaskLabel;

	private ProgressBar m_progressBar;

	private ToolBar m_cancelBar;

	private ToolItem m_cancelButton;

	private boolean m_canceled = false;

	private boolean m_done = false;
	
	private double m_totalWork = 0;

	public MaterializationProgressMonitor(MaterializationProgressProvider provider, Composite parent, Job job)
	{
		m_provider = provider;
		m_parentComposite = parent;
		
		if(job == null)
		{
			m_jobName = Messages.operation;
		}
		else
		{
			if(job instanceof IJobInfo)
			{
				m_jobName = ((IJobInfo)job).getOperationName();
			}
			else
			{
				m_jobName = job.getName();
			}
		}
		//System.out.println("Create");
	}

	private Control createControl(Composite parent)
	{
		// Try to reuse Composite to avoid flickering
		m_composite = null;
		for(Control progressComposite : parent.getChildren())
		{
			if(AVAILABLE.equals(progressComposite.getData()))
			{
				m_composite = (Composite)progressComposite;
				m_composite.setData(null);
				
				for(Control control : m_composite.getChildren())
				{
					control.dispose();
				}
				break;
			}
		}
		
		if(m_composite == null)
		{
			m_composite = new Composite(parent, SWT.NONE);
		} else
		{
			// hide the last progress monitors
			for(int i = parent.getChildren().length; i > 0; i--)
			{
				Control control = parent.getChildren()[i-1];
				if(AVAILABLE.equals(control.getData()))
				{
					control.setVisible(false);
					control.update();
					continue;
				}
				break;
			}
		}
		m_composite.setLayout(new GridLayout(2, false));
		m_composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// labelComposite enables m_subTaskLabel WRAP functionality
		Composite labelComposite = new Composite(m_composite, SWT.NONE);
		FillLayout layout = new FillLayout();
		layout.marginWidth = layout.marginHeight = 0;
		labelComposite.setLayout(layout);
		GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
		layoutData.horizontalSpan = 2;
		labelComposite.setLayoutData(layoutData);
		
		m_subTaskLabel = new Label(labelComposite, SWT.WRAP);

		m_progressBar = new ProgressBar(m_composite, SWT.NONE);
		layoutData = new GridData(GridData.FILL_HORIZONTAL);
		m_progressBar.setLayoutData(layoutData);

		m_cancelBar = new ToolBar(m_composite, SWT.FLAT);
		m_cancelButton = new ToolItem(m_cancelBar, SWT.PUSH);
		// cancelToolBar.pack ();

		// m_cancelButton = new Button(m_composite, SWT.TOGGLE);
		m_cancelButton.setImage(getImage(PROGRESS_STOP));
		m_cancelButton.setEnabled(!isCanceled());
		
		m_cancelButton.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				setCanceled(true);
			}
		});

		m_composite.setVisible(true);

		m_parentComposite.layout(new Control[] { m_composite });

		return m_composite;
	}

	private Image getImage(String imageName)
	{
		Class<?> myClass = this.getClass();
		String imageResource = "/icons/" + imageName; //$NON-NLS-1$
		URL imageUrl = myClass.getResource(imageResource);
		return ImageDescriptor.createFromURL(imageUrl).createImage();
	}

	public void beginTask(final String name, final int totalWork)
	{
		if(!m_provider.isEnabled() || m_done)
		{
			return;
		}

		Display.getDefault().syncExec(new Runnable()
		{
			public void run()
			{
				createControl(m_parentComposite);
				setTaskName(name);
				m_progressBar.setMinimum(0);
				m_progressBar.setMaximum(totalWork);
			}
		});
		//System.out.println("Begin");
	}

	public void done()
	{
		if(!m_provider.isEnabled() || m_done)
		{
			return;
		}

		if(m_cancelButton != null)
		{
			Display.getDefault().syncExec(new Runnable()
			{
				public void run()
				{
					m_progressBar.setSelection(m_progressBar.getMaximum());
					m_cancelButton.setEnabled(false);
				}
			});
		}

		subTask(m_jobName + " " + (isCanceled() ? Messages.canceled : Messages.canceled)); //$NON-NLS-1$

		final boolean[] visible = new boolean[1];
		
		Display.getDefault().syncExec(new Runnable()
		{
			public void run()
			{
				visible[0] = m_composite.isVisible();
			}
		});
		
		if(visible[0])
		{
			try
			{
				Thread.sleep(DONE_SLEEP);
			}
			catch(InterruptedException e)
			{
				// nothing
			}
		}

		m_done = true;
		
		Display.getDefault().syncExec(new Runnable()
		{
			public void run()
			{
				m_composite.setData(AVAILABLE);
				//m_composite.setVisible(false);
				//m_composite.update();
			}
		});

		//System.out.println("Done");
	}

	public void internalWorked(final double work)
	{
		m_totalWork += work;
		
		if(!m_provider.isEnabled() || m_done)
		{
			return;
		}

		Display.getDefault().syncExec(new Runnable()
		{
			public void run()
			{
				m_progressBar.setSelection(Double.valueOf(m_totalWork).intValue());
				//System.out.println("JOB: " + m_subTaskLabel.getText() + " " + m_totalWork + "(" + m_progressBar.getSelection() + ")" + "/" + m_progressBar.getMaximum() + " - " + work);
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

		if(!m_provider.isEnabled() || m_done)
		{
			return;
		}

		if(m_cancelButton != null)
		{
			Display.getDefault().syncExec(new Runnable()
			{
				public void run()
				{
					m_cancelButton.setEnabled(false);
				}
			});
		}
		//System.out.println("Cancel");
	}

	public void setTaskName(String name)
	{
		// nothing
	}

	public void subTask(final String name)
	{
		if(!m_provider.isEnabled() || m_done)
		{
			return;
		}
		
		Display.getDefault().syncExec(new Runnable()
		{
			public void run()
			{
				m_subTaskLabel.setText(name == null ? "" : name); //$NON-NLS-1$
				
				// because label can be wrapped we should call this
				m_parentComposite.layout(true, true);
			}
		});
	}

	public void worked(final int work)
	{
		internalWorked(work);
	}
}

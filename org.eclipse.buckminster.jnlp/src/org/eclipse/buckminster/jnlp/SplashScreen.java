package org.eclipse.buckminster.jnlp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SplashScreen
{
	private final String CLOSE_TEXT = "splash will close after %d sec - but you can click to close";
	
	private final Shell m_splashShell;
	
	private final Label m_textLabel;

	public SplashScreen(Image image, final int closeTimeout)
	{
		Display display = Display.getDefault();
		
		m_splashShell = new Shell(SWT.ON_TOP);
		m_splashShell.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseDown(MouseEvent e)
			{
				splashClose();
			}
		});
		
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginHeight = gridLayout.marginWidth = gridLayout.verticalSpacing = 0;
		m_splashShell.setLayout(gridLayout);
		m_splashShell.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		m_splashShell.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Label imageLabel = new Label(m_splashShell, SWT.NONE);
		imageLabel.setImage(image);
		imageLabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseDown(MouseEvent e)
			{
				splashClose();
			}
		});
		imageLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));

		Composite textComposite = new Composite(m_splashShell, SWT.NONE);
		textComposite.setLayout(new GridLayout(1, false));
		textComposite.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
		
		m_textLabel = new Label(textComposite, SWT.NONE);
		m_textLabel.setText(String.format(CLOSE_TEXT, Integer.valueOf(closeTimeout)));
		m_textLabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseDown(MouseEvent e)
			{
				splashClose();
			}
		});

		m_splashShell.pack();
		
		Rectangle splashRect = m_splashShell.getBounds();
		Rectangle displayRect = display.getBounds();
		int x = (displayRect.width - splashRect.width) / 2;
		int y = (displayRect.height - splashRect.height) / 2;
		m_splashShell.setLocation(x, y);
		
		m_splashShell.open();
	
		Thread timer = new Thread(new Runnable()
		{

			public void run()
			{
				for(int i = closeTimeout; i > 0; i--)
				{
					if(m_splashShell.isDisposed())
					{
						return;
					}
					
					displayClosingText(i);
					
					try
					{
						Thread.sleep(1000);
					}
					catch(InterruptedException e)
					{					
						break;
					}
				}
				splashClose();
			}
		});
		
		timer.start();
		
		m_splashShell.update();
	}

	private void displayClosingText(final int secToClose)
	{
		Display.getDefault().asyncExec(new Runnable()
		{
			public void run()
			{
				m_textLabel.setText(String.format(CLOSE_TEXT, Integer.valueOf(secToClose)));
			}
		});
	}
	
	private void splashClose()
	{
		Display.getDefault().asyncExec(new Runnable()
		{
			public void run()
			{
				if(!m_splashShell.isDisposed())
				{
					m_splashShell.close();
					m_splashShell.dispose();
				}
				//m_nextShell.forceActive();
			}
		});
	}
}

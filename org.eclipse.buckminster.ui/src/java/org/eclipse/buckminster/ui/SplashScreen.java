package org.eclipse.buckminster.ui;

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

/**
 * Implementation of Splash Screen
 * 
 * @author Karel Brezina
 */
public class SplashScreen
{
	private final String CLOSE_TEXT = Messages.splashScreen_splash_will_close_after_X_sec_but_you_can_click_to_close;

	private final Shell m_splashShell;

	private final Label m_textLabel;

	public SplashScreen(final Image image, final int closeTimeout, final boolean closeTextFlag, final boolean waitFlag)
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

		if(closeTextFlag)
		{
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
		}
		else
		{
			m_textLabel = null;
		}

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

					if(closeTextFlag)
					{
						displayClosingText(i);
					}

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

		if(waitFlag)
		{
			while(!m_splashShell.isDisposed())
			{
				if(!display.readAndDispatch())
				{
					display.sleep();
				}
			}
		}
	}

	private void displayClosingText(final int secToClose)
	{
		if(m_textLabel == null)
		{
			return;
		}

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
			}
		});
	}
}

/*
 * @(#)SplashWindow.java  2.2.1  2006-05-27
 *
 * Copyright (c) 2003-2006 Werner Randelshofer
 * Staldenmattweg 2, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * This software is in the public domain.
 */
package org.eclipse.buckminster.jnlp.bootstrap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A Splash window.
 * <p>
 * Usage: MyApplication is your application class. Create a Splasher class which opens the splash window, invokes the
 * main method of your Application class, and disposes the splash window afterwards. Please note that we want to keep
 * the Splasher class and the SplashWindow class as small as possible. The less code and the less classes must be loaded
 * into the JVM to open the splash screen, the faster it will appear.
 * 
 * <pre>
 * class Splasher
 * {
 * 	public static void main(String[] args)
 * 	{
 * 		SplashWindow.splash(Startup.class.getResource(&quot;splash.gif&quot;));
 * 		MyApplication.main(args);
 * 		SplashWindow.disposeSplash();
 * 	}
 * }
 * </pre>
 * 
 * @author Werner Randelshofer
 * @version 2.2.1 2006-05-27 Abort when splash image can not be loaded. (original version)
 * @author Henrik Lindberg
 */

// NOTE: Please keep commented code - it is used for debugging, but should not be used in production
// It is kind of tricky to debug via JWS!
//
@SuppressWarnings("serial")
public class SplashWindow extends Frame
{
	private static final int PROGRESS_XMARGIN = 4;

	private static final int PROGRESS_YMARGIN = 2;

	private static final int PROGRESS_TICK_HEIGHT = 6;

	private static final int PROGRESS_TICK_WIDTH = 4;

	private static final int PROGRESS_TICK_GAP = 2;

	/**
	 * The current instance of the splash window. (Singleton design pattern).
	 */
	private static SplashWindow s_instance;

	/**
	 * The current download listener. (Singleton design pattern).
	 */
	private static ProgressFacade s_listener;

	public static final int SPLASH_IMAGE_BOOT_ID = 0;
	public static final int SPLASH_IMAGE_ID = 1;

	private static final int WINDOW_ICON_ID = 2;

	/**
	 * The two splash images which is displayed on the splash window.
	 */
	private final Image[] m_images = new Image[2];
	private int m_imageId = SPLASH_IMAGE_BOOT_ID;

	/* private static StringBuffer s_debugInfo = new StringBuffer(); */
	// Please keep this variable, even if it is not read - future functionality will make use
	// of it
	@SuppressWarnings("unused")
	private static String s_taskName = "Run";

	/**
	 * This attribute indicates whether the method paint(Graphics) has been called at least once since the construction
	 * of this window.<br>
	 * This attribute is used to notify method splash(Image) that the window has been drawn at least once by the AWT
	 * event dispatcher thread.<br>
	 * This attribute acts like a latch. Once set to true, it will never be changed back to false again.
	 * 
	 * @see #paint
	 * @see #splash
	 */
	private boolean m_paintCalled = false;

	/**
	 * This attribute indicates how much progress that should be reported. Leave it at 0 to only get a splash image.
	 */
	private int m_progress = 0;

	private Color m_progressColor;

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            the parent of the window.
	 * @param splashImage
	 *            the splash image.
	 * @param windowIconImage
	 *            the taskbar icon image
	 */
	private SplashWindow(Image splashImageBoot, Image splashImage, Image windowIconImage)
	{
		// Load the images
		MediaTracker mt = new MediaTracker(this);
		if(splashImageBoot != null)
			mt.addImage(splashImageBoot, SPLASH_IMAGE_BOOT_ID);
		if(splashImage != null)
			mt.addImage(splashImage, SPLASH_IMAGE_ID);
		if(windowIconImage != null)
			mt.addImage(windowIconImage, WINDOW_ICON_ID);

		try
		{
			if(splashImageBoot != null)
				mt.waitForID(SPLASH_IMAGE_BOOT_ID);
			if(splashImage != null)
				mt.waitForID(SPLASH_IMAGE_ID);
			if(windowIconImage != null)
				mt.waitForID(WINDOW_ICON_ID);
		}
		catch(InterruptedException ie)
		{
		}

		setUndecorated(true);
		setTitle("Configuring Materializer Infrastructure");

		if(windowIconImage != null)
		{
			if(!mt.isErrorID(WINDOW_ICON_ID))
				setIconImage(windowIconImage);
			else
				System.err.println("Warning: SplashWindow couldn't load window icon.");
		}

		if(splashImageBoot != null && mt.isErrorID(SPLASH_IMAGE_BOOT_ID))
		{
			System.err.println("Warning: SplashWindow couldn't load splash boot image.");
			splashImageBoot = null;
		}

		if(splashImage != null && mt.isErrorID(SPLASH_IMAGE_ID))
		{
			System.err.println("Warning: SplashWindow couldn't load splash image.");
			splashImage = null;
		}

		// Abort on failure
		if(splashImageBoot == null && splashImage == null)
		{
			setSize(0, 0);
			synchronized(this)
			{
				m_paintCalled = true;
				notifyAll();
			}
			return;
		}

		// Users shall be able to close the splash window by
		// clicking on its display area. This mouse listener
		// listens for mouse clicks and disposes the splash window.
		MouseAdapter disposeOnClick = new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent evt)
			{
				// Note: To avoid that method splash hangs, we
				// must set paintCalled to true and call notifyAll.
				// This is necessary because the mouse click may
				// occur before the contents of the window
				// has been painted.
				synchronized(SplashWindow.this)
				{
					SplashWindow.this.m_paintCalled = true;
					SplashWindow.this.notifyAll();
				}

				// Dispose was changed to just ICONIFIED so that the window would not completely disappear
				// (it is registered on the task bar)
				// dispose();
				setExtendedState(Frame.ICONIFIED);

			}
		};
		addMouseListener(disposeOnClick);
		m_progressColor = new Color(0xd8e5ee);

		m_images[SPLASH_IMAGE_BOOT_ID] = splashImageBoot;
		m_images[SPLASH_IMAGE_ID] = splashImage;
		setImageId((splashImageBoot == null) ? SPLASH_IMAGE_ID : SPLASH_IMAGE_BOOT_ID);
	}

	public static void setSplashImage(int imageId)
	{
		s_instance.setImageId(imageId);
	}

	private void setImageId(int imageId)
	{
		if(!(imageId == SPLASH_IMAGE_BOOT_ID || imageId == SPLASH_IMAGE_ID))
			throw new IllegalArgumentException("Splash imageId is out of range");

		Image image = m_images[imageId];
		if(image == null)
			//
			// We don't permit this since the image is null
			//
			return;

		m_imageId = imageId;

		// Center the window on the screen
		int imgWidth = image.getWidth(this);
		int imgHeight = image.getHeight(this);
		setSize(imgWidth, imgHeight);
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenDim.width - imgWidth) / 2, (screenDim.height - imgHeight) / 2);
		repaint();
	}

	/**
	 * Updates the display area of the window.
	 */
	@Override
	public void update(Graphics g)
	{
		// Note: Since the paint method is going to draw an
		// image that covers the complete area of the component we
		// do not fill the component with its background color
		// here. This avoids flickering.
		paint(g);
	}

	/**
	 * Paints the image on the window.
	 */
	@Override
	public void paint(Graphics g)
	{
		g.drawImage(m_images[m_imageId], 0, 0, this);

		// Notify method splash that the window
		// has been painted.
		// Note: To improve performance we do not enter
		// the synchronized block unless we have to.
		if(!m_paintCalled)
		{
			m_paintCalled = true;
			synchronized(this)
			{
				notifyAll();
			}
		}

		// Continue with painting progress
		int y = getHeight() - PROGRESS_YMARGIN - PROGRESS_TICK_HEIGHT;
		int x = PROGRESS_XMARGIN;
		int tickw = PROGRESS_TICK_WIDTH + PROGRESS_TICK_GAP;
		int w = getWidth() - PROGRESS_XMARGIN * 2;
		int n = w / tickw;
		// if, by skipping the last gap, there is room for one more...
		if(w - n * tickw >= PROGRESS_TICK_WIDTH)
			n++;
		int j = m_progress == 0
				? 0
				: (int)(((m_progress / 100.0) * n + 0.5));

		g.setColor(m_progressColor);
		for(int i = 0; i < j; i++)
		{
			g.fillRoundRect(x, y, PROGRESS_TICK_WIDTH, PROGRESS_TICK_HEIGHT, 2, 2);
			x += PROGRESS_TICK_WIDTH + PROGRESS_TICK_GAP;
		}
	}

	/**
	 * Sets the name of the task for progress monitoring
	 * 
	 * @param taskName
	 *            The task name
	 */
	public static void setTaskName(String taskName)
	{
		s_taskName = taskName;
	}

	/**
	 * Open's a splash window using the specified image.
	 * 
	 * @param splashImage
	 *            The splash image.
	 */
	public static void splash(Image splashImageBoot, Image splashImage, Image windowIconImage)
	{
// s_debugInfo.append("Splash; ");
		if(s_instance == null && splashImage != null)
		{
			// Create the splash image
			s_instance = new SplashWindow(splashImageBoot, splashImage, windowIconImage);

			// Show the window.
			s_instance.setVisible(true);

			// Note: To make sure the user gets a chance to see the
			// splash window we wait until its paint method has been
			// called at least once by the AWT event dispatcher thread.
			// If more than one processor is available, we don't wait,
			// and maximize CPU throughput instead.
			if(!EventQueue.isDispatchThread() && Runtime.getRuntime().availableProcessors() == 1)
			{
				synchronized(s_instance)
				{
					while(!s_instance.m_paintCalled)
					{
						try
						{
							s_instance.wait();
						}
						catch(InterruptedException e)
						{
						}
					}
				}
			}
		}
	}

	/**
	 * Open's a splash window using the specified image.
	 * 
	 */
	public static void splash(byte[] splashImageBootBytes, byte[] splashImageBytes, byte[] windowIconBytes)
	{
		if(splashImageBytes != null)
		{
			Image splashImageBoot = splashImageBootBytes != null
					? Toolkit.getDefaultToolkit().createImage(splashImageBootBytes)
					: null;

			Image splashImage = splashImageBytes != null
					? Toolkit.getDefaultToolkit().createImage(splashImageBytes)
					: null;

			Image windowIconImage = windowIconBytes != null
					? Toolkit.getDefaultToolkit().createImage(windowIconBytes)
					: null;
			splash(splashImageBoot, splashImage, windowIconImage);
		}
	}

	public static void windowToFront()
	{
		if(s_instance != null)
		{
			s_instance.setAlwaysOnTop(true);
			s_instance.setAlwaysOnTop(false);
		}
	}

	/**
	 * Closes the splash window.
	 */
	public static void disposeSplash()
	{
// s_debugInfo.append("Disposed; ");
		if(s_instance != null)
		{
// logProgress(0, s_instance.m_progress);
			// s_instance.getOwner().dispose();
			s_instance.dispose();
			s_instance = null;
		}
	}

	public static boolean splashIsUp()
	{
		return s_instance != null;
	}

	public static String getDebugString()
	{
		return "";
// return s_debugInfo.toString();
	}

// private static void logProgress(int from, int to)
// {
// s_debugInfo.append(s_taskName);
// s_debugInfo.append(": ");
// s_debugInfo.append(from);
// s_debugInfo.append("-");
// s_debugInfo.append(to);
// s_debugInfo.append("; \n");
// }
	public static void setProgress(int percentageDone)
	{
		if(s_instance != null)
		{
			if(percentageDone > 100)
				percentageDone = 100;
			if(percentageDone < 0)
			{
// s_debugInfo.append("*");
				percentageDone = 0;
			}

// // if percentageDone is 0, it is considered to start a new "run" - log this
// if(percentageDone == 0)
// {
// logProgress(0,s_instance.m_progress);
// }
			if(percentageDone == 0 && s_instance.m_progress > 0 && s_instance.m_progress < 95)
			{
				// progress did not go to (close to) 100 before it went to 0
				// set it to 100 first, and repaint, then wait, and continue.
				setProgressChecked(100);
				try
				{
					Thread.sleep(50); // give user the chance to see it
				}
				catch(InterruptedException e)
				{
					// just ignore
				}
			}
			setProgressChecked(percentageDone);
		}
	}

	private static void setProgressChecked(int percentageDone)
	{
		// set the progress in splash window

		int tickw = PROGRESS_TICK_WIDTH + PROGRESS_TICK_GAP;
		int w = s_instance.getWidth() - PROGRESS_XMARGIN * 2;
		int n = w / tickw;
		// if, by skipping the last gap, there is room for one more...
		if(w - n * tickw >= PROGRESS_TICK_WIDTH)
			n++;

		int n1 = s_instance.m_progress == 0
				? 0
				: (int)(((s_instance.m_progress / 100.0) * n + 0.5));
		int n2 = percentageDone == 0
				? 0
				: (int)(((percentageDone / 100.0) * n + 0.5));

		// in case progress goes in the reverse...
		if(s_instance.m_progress > percentageDone)
		{
			int tmp = n2;
			n2 = n1;
			n1 = tmp;
		}

		// set the new progress value
		s_instance.m_progress = percentageDone;

		// repaint the progressbar area - include only the area m1-m2 to reduce flicker
		s_instance.repaint(PROGRESS_XMARGIN + n1 * (PROGRESS_TICK_GAP + PROGRESS_TICK_WIDTH), s_instance.getHeight()
				- PROGRESS_TICK_HEIGHT - PROGRESS_YMARGIN, (n2 - n1) * (PROGRESS_TICK_GAP + PROGRESS_TICK_WIDTH),
				PROGRESS_TICK_HEIGHT);
	}

	public static ProgressFacade getDownloadServiceListener()
	{
		if(s_listener == null)
			s_listener = new ProgressFacade();
		return s_listener;
	}
}

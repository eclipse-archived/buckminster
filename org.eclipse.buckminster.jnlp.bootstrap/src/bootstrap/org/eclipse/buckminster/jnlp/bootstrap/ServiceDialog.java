/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.bootstrap;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author kaja
 *
 */
public class ServiceDialog extends Dialog
{
	private static final long serialVersionUID = 0L;

	private static final String SERVICE_TITLE = "Service Message";

	private static final String SERVICE_ICON = "warning.png";

	private static final String SERVICE_NOT_AVAILABLE = "SERVICE IS NOT AVAILABLE NOW!!";
	
	private static final int MIN_H_SIZE = 400;

	private static final int MIN_V_SIZE = 200;
	
	public ServiceDialog(String message, boolean serviceAvailable)
	{
		super(new Frame(), SERVICE_TITLE, true);

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				finish();
			}
		});

		setLayout(new BorderLayout());
		
		Panel p = new Panel(new FlowLayout(FlowLayout.LEFT, 15, 15));
		add("West", p);

		Label c = new ImageLabel(SERVICE_ICON);
		c.setPreferredSize(new Dimension(48, 48));
		p.add(c);

		Panel tp = new Panel(new BorderLayout());
		add("Center", tp);

		p = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 15));
		tp.add("North", p);
		Panel mp = new Panel(new BorderLayout());
		p.add(mp);
		
		mp.add("North", new Label(SERVICE_TITLE + ":"));
		
		p = new Panel(new FlowLayout(FlowLayout.LEFT));
		mp.add("Center", p);
		
		TextArea ta = new TextArea(message, 5, 70);
		ta.setEditable(false);
		ta.setFocusable(false);
		p.add(ta);
		
		if(! serviceAvailable)
		{
			tp.add("South", new Label(SERVICE_NOT_AVAILABLE));
		}
		
		p = new Panel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		final Button b = new Button("OK");
		b.setPreferredSize(new Dimension(73, 20));

		b.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e)
			{
				finish();
			}});
		
		b.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					finish();
				}
			}
		});
		p.add(b);
		add("South", p);

		pack();

		int width = Math.max(getWidth(), MIN_H_SIZE);
		int height = Math.max(getHeight(), MIN_V_SIZE);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		setBounds((screen.width - width) / 2, (screen.height - height) / 2, width, height);
	}

	private void finish()
	{
		getOwner().dispose();
	}
}


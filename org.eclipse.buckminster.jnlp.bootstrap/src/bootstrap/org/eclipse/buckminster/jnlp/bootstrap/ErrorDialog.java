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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author kaja
 *
 */
public class ErrorDialog extends Frame
{
	private static final long serialVersionUID = 0L;

	private static final String ERROR_TITLE = "Materialization Error";

	private static final String ERROR_ICON = "error.png";

	private static final int MIN_H_SIZE = 400;

	private static final int MIN_V_SIZE = 200;
	
	private Button m_okButton;
	
	private boolean m_focusRepaired = false;
	
	public ErrorDialog(String title, String problem, String solution, String helpURL, Image windowIconImage)
	{
		super(ERROR_TITLE);

		if(windowIconImage != null)
			setIconImage(windowIconImage);

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				finish();
			}
		});

		setLayout(new BorderLayout());
		setBackground(null);
		
		Panel tp = new Panel(new BorderLayout(0, 0));

		add("Center", tp);
		
		Panel p = new Panel(new FlowLayout(FlowLayout.LEFT, 15, 15));
		add("West", p);

		Label c = new ImageLabel(ERROR_ICON);
		c.setPreferredSize(new Dimension(48, 48));
		p.add(c);

		p = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 15));
		tp.add("North", p);
		p.add(new Label(title));
		
		Panel cp = new Panel(new BorderLayout(0, 5));
		tp.add("Center", cp);
		
		Panel pp = new Panel(new BorderLayout());
		cp.add("North", pp);
		pp.add("North", new Label("Problem:"));
		
		p = new Panel(new FlowLayout(FlowLayout.LEFT));
		pp.add("South", p);
		TextArea ta = new TextArea(problem, 10, 70);
		ta.setEditable(false);
		ta.setFocusable(true);
		p.add(ta);
		
		Panel sp = new Panel(new BorderLayout());
		cp.add("South", sp);
		sp.add("North", new Label("Solution:"));

		p = new Panel(new FlowLayout(FlowLayout.LEFT));
		sp.add("South", p);
		ta = new TextArea(solution, 3, 70);
		ta.setEditable(false);
		ta.setFocusable(true);
		p.add(ta);
		
		if(helpURL != null)
		{
			p = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 15));
			p.add(new Label("Read more at:"));
			final TextField tf = new TextField(helpURL, 55);
			
			tf.addFocusListener(new FocusAdapter(){

				@Override
				public void focusGained(FocusEvent e)
				{
					if(! m_focusRepaired) // OK button should be focused first
					{
						m_okButton.requestFocus();
					}
				}});
				
			tf.setEditable(false);
			p.add(tf);
			tp.add("South", p);
		}
		
		p = new Panel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		m_okButton = new Button("OK");
		m_okButton.setPreferredSize(new Dimension(73, 20));

		m_okButton.addFocusListener(new FocusAdapter(){

			@Override
			public void focusGained(FocusEvent e)
			{
				m_focusRepaired = true;
			}});

		m_okButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e)
			{
				finish();
			}});
		
		m_okButton.addKeyListener(new KeyAdapter()
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
		p.add(m_okButton);
		add("South", p);

		pack();

		int width = Math.max(getWidth(), MIN_H_SIZE);
		int height = Math.max(getHeight(), MIN_V_SIZE);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		setBounds((screen.width - width) / 2, (screen.height - height) / 2, width, height);
	}
	
	private void finish()
	{
		dispose();
		System.exit(-1);
	}
}


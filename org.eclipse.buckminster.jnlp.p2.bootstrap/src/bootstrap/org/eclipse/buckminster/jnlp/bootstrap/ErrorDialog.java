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
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;

/**
 * @author Karel Brezina
 * 
 */
public class ErrorDialog extends JNLPDialog
{
	private static final long serialVersionUID = 0L;

	private static final String ERROR_TITLE = Messages.getString("materialization_error"); //$NON-NLS-1$

	private static final String ERROR_ICON = "error.png"; //$NON-NLS-1$

	private static final int MIN_H_SIZE = 400;

	private static final int MIN_V_SIZE = 200;

	private Button m_okButton;

	private boolean m_focusRepaired = false;

	public ErrorDialog(Image windowIconImage, String title, String problem, String solution, String helpURL)
	{
		super(windowIconImage, ERROR_TITLE);

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				finish();
			}
		});

		setLayout(new BorderLayout());
		setBackground(SystemColor.control);

		Panel tp = new Panel(new BorderLayout(0, 0));

		add("Center", tp); //$NON-NLS-1$

		Panel p = new Panel(new FlowLayout(FlowLayout.LEFT, 15, 15));
		add("West", p); //$NON-NLS-1$

		Label c = new ImageLabel(ERROR_ICON);
		c.setPreferredSize(new Dimension(48, 48));
		p.add(c);

		p = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 15));
		tp.add("North", p); //$NON-NLS-1$
		Label titleLabel = new Label(title);
		p.add(titleLabel);

		Panel cp = new Panel();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		tp.add(cp); //$NON-NLS-1$

		Panel pp = new Panel(new BorderLayout());
		cp.add("North", pp); //$NON-NLS-1$
		Label problemLabel = new Label(Messages.getString("problem_with_colon"));
		pp.add("North", problemLabel); //$NON-NLS-1$ //$NON-NLS-2$

		p = new Panel(new FlowLayout(FlowLayout.LEFT));
		pp.add("Center", p); //$NON-NLS-1$
		final TextArea ta = new TextArea(problem, 10, 70);
		ta.setEditable(false);
		ta.setFocusable(true);

		ta.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if(!m_focusRepaired) // OK button should be focused first
				{
					m_okButton.requestFocus();
				}
			}
		});

		ta.addKeyListener(new KeyAdapter()
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

		p.add(ta);

		Panel sp = new Panel(new BorderLayout());
		cp.add(sp); //$NON-NLS-1$
		Label solutionLabel = new Label(Messages.getString("solution"));
		sp.add("North", solutionLabel); //$NON-NLS-1$ //$NON-NLS-2$

		p = new Panel(new FlowLayout(FlowLayout.LEFT));
		sp.add("Center", p); //$NON-NLS-1$
		final TextArea tb = new TextArea(solution, 3, 70);
		tb.setEditable(false);
		tb.setFocusable(true);

		tb.addKeyListener(new KeyAdapter()
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

		p.add(tb);

		final Label readMoreLabel;
		final TextField tf;
		if(helpURL == null)
		{
			readMoreLabel = null;
			tf = null;
		}
		else
		{
			p = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 15));
			readMoreLabel = new Label(Messages.getString("read_more_at_with_colon"));
			p.add(readMoreLabel); //$NON-NLS-1$
			tf = new TextField(helpURL, 55);

			tf.addKeyListener(new KeyAdapter()
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

			tf.setEditable(false);
			p.add(tf);
			tp.add("South", p); //$NON-NLS-1$
		}

		p = new Panel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		m_okButton = new Button(Messages.getString("ok")); //$NON-NLS-1$
		m_okButton.setPreferredSize(new Dimension(73, 20));

		m_okButton.addFocusListener(new FocusAdapter()
		{

			@Override
			public void focusGained(FocusEvent e)
			{
				m_focusRepaired = true;
			}
		});

		m_okButton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				finish();
			}
		});

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
		add("South", p); //$NON-NLS-1$

		pack();

		// set fonts - needs to be here, after pack() sets Font for the top container
		titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
		problemLabel.setFont(problemLabel.getFont().deriveFont(Font.ITALIC));
		solutionLabel.setFont(solutionLabel.getFont().deriveFont(Font.ITALIC));
		if(readMoreLabel != null)
			readMoreLabel.setFont(readMoreLabel.getFont().deriveFont(Font.ITALIC));

		int width = Math.max(getWidth(), MIN_H_SIZE);
		int height = Math.max(getHeight(), MIN_V_SIZE);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		setBounds((screen.width - width) / 2, (screen.height - height) / 2, width, height);

		addComponentListener(new ComponentListener()
		{

			public void componentHidden(ComponentEvent e)
			{
				// not needed
			}

			public void componentMoved(ComponentEvent e)
			{
				// not needed
			}

			// dynamic resizing
			public void componentResized(ComponentEvent e)
			{
				ta.setSize(ta.getParent().getWidth() - 10, ta.getParent().getHeight() - 10);
				tb.setSize(tb.getParent().getWidth() - 10, tb.getParent().getHeight() - 10);
				if(tf != null)
					tf.setSize(tf.getParent().getWidth() - readMoreLabel.getWidth() - 5, tf.getSize().height);
			}

			public void componentShown(ComponentEvent e)
			{
				// not needed
			}
		});
	}
}

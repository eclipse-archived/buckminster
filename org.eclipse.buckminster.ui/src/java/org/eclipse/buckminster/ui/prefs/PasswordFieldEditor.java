/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.prefs;

import org.eclipse.buckminster.ui.Messages;
import org.eclipse.core.runtime.Assert;
import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * A field editor for a password type preference. The key ring is used for storing the editor value.
 * 
 * @author Karel Brezina
 * 
 */
public class PasswordFieldEditor extends StringFieldEditor
{
	// fake URL - need it for saving to the key ring
	private final static String BUCKMINSTER_NODE = "buckminster"; //$NON-NLS-1$

	private String m_keyRingRealm;

	/**
	 * Old text value.
	 */
	private String m_oldValue;

	private Text m_textField;

	/**
	 * Text limit of text field in characters; initially unlimited.
	 */
	private int m_textLimit = UNLIMITED;

	/**
	 * The validation strategy; <code>VALIDATE_ON_KEY_STROKE</code> by default.
	 */
	private int m_validateStrategy = VALIDATE_ON_KEY_STROKE;

	/**
	 * Creates a password field editor of unlimited width. Use the method <code>setTextLimit</code> to limit the text.
	 * 
	 * @param name
	 *            the name of the preference this field editor works on
	 * @param labelText
	 *            the label text of the field editor
	 * @param parent
	 *            the parent of the field editor's control
	 */
	public PasswordFieldEditor(String name, String labelText, Composite parent, String keyRingRealm)
	{
		this(name, labelText, UNLIMITED, parent, keyRingRealm);
	}

	/**
	 * Creates a password field editor. Use the method <code>setTextLimit</code> to limit the text.
	 * 
	 * @param name
	 *            the name of the preference this field editor works on
	 * @param labelText
	 *            the label text of the field editor
	 * @param width
	 *            the width of the text input field in characters, or <code>UNLIMITED</code> for no limit
	 * @param parent
	 *            the parent of the field editor's control
	 */
	public PasswordFieldEditor(String name, String labelText, int width, Composite parent, String keyRingRealm)
	{
		this(name, labelText, width, VALIDATE_ON_KEY_STROKE, parent, keyRingRealm);
	}

	/**
	 * Creates a password field editor. Use the method <code>setTextLimit</code> to limit the text.
	 * 
	 * @param name
	 *            the name of the preference this field editor works on
	 * @param labelText
	 *            the label text of the field editor
	 * @param width
	 *            the width of the text input field in characters, or <code>UNLIMITED</code> for no limit
	 * @param strategy
	 *            either <code>VALIDATE_ON_KEY_STROKE</code> to perform on the fly checking (the default), or
	 *            <code>VALIDATE_ON_FOCUS_LOST</code> to perform validation only after the text has been typed in
	 * @param parent
	 *            the parent of the field editor's control
	 * @since 2.0
	 */
	public PasswordFieldEditor(String name, String labelText, int width, int strategy, Composite parent,
			String keyRingRealm)
	{
		super(name, labelText, width, strategy, parent);
		m_keyRingRealm = keyRingRealm;
	}

	/**
	 * Returns the field editor's value.
	 * 
	 * @return the current value
	 */
	@Override
	public String getStringValue()
	{
		if(m_textField != null)
		{
			return m_textField.getText();
		}

		return getPasswordFromKeyRing();
	}

	@Override
	public Text getTextControl(Composite parent)
	{
		if(m_textField == null)
		{
			m_textField = new Text(parent, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD);
			m_textField.setFont(parent.getFont());
			switch(m_validateStrategy)
			{
			case VALIDATE_ON_KEY_STROKE:
				m_textField.addKeyListener(new KeyAdapter()
				{

					/*
					 * (non-Javadoc)
					 * 
					 * @see org.eclipse.swt.events.KeyAdapter#keyReleased(org.eclipse.swt.events.KeyEvent)
					 */
					@Override
					public void keyReleased(KeyEvent e)
					{
						valueChanged();
					}
				});

				break;
			case VALIDATE_ON_FOCUS_LOST:
				m_textField.addKeyListener(new KeyAdapter()
				{
					@Override
					public void keyPressed(KeyEvent e)
					{
						clearErrorMessage();
					}
				});
				m_textField.addFocusListener(new FocusAdapter()
				{
					@Override
					public void focusGained(FocusEvent e)
					{
						refreshValidState();
					}

					@Override
					public void focusLost(FocusEvent e)
					{
						valueChanged();
						clearErrorMessage();
					}
				});
				break;
			default:
				Assert.isTrue(false, "Unknown validate strategy");//$NON-NLS-1$
			}
			m_textField.addDisposeListener(new DisposeListener()
			{
				public void widgetDisposed(DisposeEvent event)
				{
					m_textField = null;
				}
			});
			if(m_textLimit > 0)
			{// Only set limits above 0 - see SWT spec
				m_textField.setTextLimit(m_textLimit);
			}
		}
		else
		{
			checkParent(m_textField, parent);
		}
		return m_textField;
	}

	/**
	 * Sets this text field's text limit.
	 * 
	 * @param limit
	 *            the limit on the number of character in the text input field, or <code>UNLIMITED</code> for no limit
	 */
	@Override
	public void setTextLimit(int limit)
	{
		m_textLimit = limit;
		if(m_textField != null)
		{
			m_textField.setTextLimit(limit);
		}
	}

	/**
	 * Sets the strategy for validating the text.
	 * <p>
	 * Calling this method has no effect after <code>createPartControl</code> is called. Thus this method is really only
	 * useful for subclasses to call in their constructor. However, it has public visibility for backward compatibility.
	 * </p>
	 * 
	 * @param value
	 *            either <code>VALIDATE_ON_KEY_STROKE</code> to perform on the fly checking (the default), or
	 *            <code>VALIDATE_ON_FOCUS_LOST</code> to perform validation only after the text has been typed in
	 */
	@Override
	public void setValidateStrategy(int value)
	{
		Assert.isTrue(value == VALIDATE_ON_FOCUS_LOST || value == VALIDATE_ON_KEY_STROKE);
		m_validateStrategy = value;
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	@Override
	protected void doLoad()
	{
		if(m_textField != null)
		{
			String value = getPasswordFromKeyRing();
			m_textField.setText(value);
			m_oldValue = value;
		}
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	@Override
	protected void doLoadDefault()
	{
		if(m_textField != null)
		{
			String value = ""; //$NON-NLS-1$
			m_textField.setText(value);
		}
		valueChanged();
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	@Override
	protected void doStore()
	{
		setPasswordToKeyRing(m_textField.getText());
	}

	/**
	 * Informs this field editor's listener, if it has one, about a change to the value (<code>VALUE</code> property)
	 * provided that the old and new values are different.
	 * <p>
	 * This hook is <em>not</em> called when the text is initialized (or reset to the default value) from the preference
	 * store.
	 * </p>
	 */
	@Override
	protected void valueChanged()
	{
		setPresentsDefaultValue(false);
		boolean oldState = isValid();
		refreshValidState();

		if(isValid() != oldState)
		{
			fireStateChanged(IS_VALID, oldState, isValid());
		}

		String newValue = m_textField.getText();
		if(!newValue.equals(m_oldValue))
		{
			fireValueChanged(VALUE, m_oldValue, newValue);
			m_oldValue = newValue;
		}
	}

	private String getPasswordFromKeyRing()
	{
		ISecurePreferences info = SecurePreferencesFactory.getDefault().node(BUCKMINSTER_NODE).node(m_keyRingRealm);
		try
		{
			return info.get(getPreferenceName(), ""); //$NON-NLS-1$
		}
		catch(StorageException e)
		{
			return ""; //$NON-NLS-1$
		}
	}

	private void setPasswordToKeyRing(String password)
	{
		ISecurePreferences info = SecurePreferencesFactory.getDefault().node(BUCKMINSTER_NODE).node(m_keyRingRealm);
		try
		{
			info.put(getPreferenceName(), password, true);
			info.flush();
		}
		catch(Exception e)
		{
			throw new RuntimeException(Messages.cannot_save_password, e);
		}
	}

}

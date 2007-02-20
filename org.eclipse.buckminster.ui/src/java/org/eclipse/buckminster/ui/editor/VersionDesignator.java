/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.editor;

import java.util.ArrayList;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.ui.ChangeAdapter;
import org.eclipse.buckminster.ui.LabeledCombo;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * Mini editor for the VersionDesignator
 * @author Thomas Hallgren
 */
public class VersionDesignator extends Composite
{
	enum DesignatorType
	{
		EQUALS
		{
			@Override
			IVersionDesignator createDesignator(String versionType, Text version, Text from, Text to)
			throws CoreException
			{
				String tmp = UiUtils.trimmedValue(version);
				return (tmp == null) ? null : VersionFactory.createExplicitDesignator(versionType, tmp);
			}

			@Override
			public String toString()
			{
				return "? == Version";
			}
		},
		GREATER_OR_EQUAL
		{
			@Override
			IVersionDesignator createDesignator(String versionType, Text version, Text from, Text to)
			throws CoreException
			{
				String tmp = UiUtils.trimmedValue(version);
				return (tmp == null) ? null : VersionFactory.createDesignator(versionType, tmp);
			}

			@Override
			public String toString()
			{
				return "? >= Version";
			}
		},
		RANGE_IE
		{
			@Override
			IVersionDesignator createDesignator(String versionType, Text version, Text from, Text to)
			throws CoreException
			{
				return createRange(from, to, versionType, '[', ')');
			}

			@Override
			public String toString()
			{
				return "From <= ? < To";
			}
		},
		RANGE_II
		{
			@Override
			IVersionDesignator createDesignator(String versionType, Text version, Text from, Text to)
			throws CoreException
			{
				return createRange(from, to, versionType, '[', ']');
			}

			@Override
			public String toString()
			{
				return "From <= ? <= To";
			}
		},
		RANGE_EE
		{
			@Override
			IVersionDesignator createDesignator(String versionType, Text version, Text from, Text to)
			throws CoreException
			{
				return createRange(from, to, versionType, '(', ')');
			}

			@Override
			public String toString()
			{
				return "From < ? < To";
			}
		},
		RANGE_EI
		{
			@Override
			IVersionDesignator createDesignator(String versionType, Text version, Text from, Text to)
			throws CoreException
			{
				return createRange(from, to, versionType, '(', ']');
			}

			@Override
			public String toString()
			{
				return "From < ? <= To";
			}
		};

		abstract IVersionDesignator createDesignator(String versionType, Text version, Text from, Text to)
		throws CoreException;

		static String[] getStrings()
		{
			DesignatorType[] dsTypes = values();
			int idx = dsTypes.length;
			String[] strings = new String[idx];
			while(--idx >= 0)
				strings[idx] = dsTypes[idx].toString();
			return strings;
		}

		private static IVersionDesignator createRange(Text from, Text to, String versionType, char start,
			char end) throws CoreException
		{
			String tmp = UiUtils.trimmedValue(from);
			String tmp2 = UiUtils.trimmedValue(to);
			return (tmp == null || tmp2 == null) ? null : VersionFactory.createDesignator(versionType, start
				+ tmp + ',' + tmp2 + end);
		}
	}

	private final LabeledCombo m_versionDsType;

	private final Text m_version;

	private final Text m_fromVersion;

	private final Text m_toVersion;

	private final LabeledCombo m_versionType;

	private final Composite m_dsParent;

	private final StackLayout m_dsStack;

	private final Composite m_simpleVersion;

	private final Composite m_rangeVersion;

	private final ArrayList<VersionDesignatorListener> m_listeners = new ArrayList<VersionDesignatorListener>();

	@Override
	public void setEnabled(boolean flag)
	{
		m_versionDsType.setEnabled(flag);
		m_version.setEnabled(flag);
		m_fromVersion.setEnabled(flag);
		m_toVersion.setEnabled(flag);
		m_versionType.setEnabled(flag);
		super.setEnabled(flag);
	}

	ChangeAdapter m_notifier = new ChangeAdapter()
	{
		@Override
		protected void onChange(TypedEvent te)
		{
			VersionDesignatorEvent e = new VersionDesignatorEvent(VersionDesignator.this, te.widget, te.data);
			for(VersionDesignatorListener listener : m_listeners)
				listener.modifyVersionDesignator(e);
		}
	};

	public void addVersionDesignatorListener(VersionDesignatorListener listener)
	{
		if(!m_listeners.contains(listener))
			m_listeners.add(listener);
	}

	public void removeVersionDesignatorListener(VersionDesignatorListener listener)
	{
		m_listeners.remove(listener);
	}

	public VersionDesignator(Composite parent, int style)
	{
		super(parent, style);
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = layout.marginHeight = 0;
		this.setLayout(layout);

		m_versionDsType = UiUtils.createEnumCombo(this, "Version designator", DesignatorType.values(),
			new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					dsTypeIndexChanged(((Combo)e.getSource()).getSelectionIndex());
				}
			});
		m_versionDsType.addSelectionListener(m_notifier);

		m_dsParent = new Composite(this, SWT.NONE);
		m_dsStack = new StackLayout();
		m_dsParent.setLayout(m_dsStack);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, false);
		gd.minimumWidth = 160;
		m_dsParent.setLayoutData(gd);

		m_simpleVersion = new Composite(m_dsParent, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginWidth = layout.marginHeight = 0;
		m_simpleVersion.setLayout(layout);
		m_version = UiUtils.createLabeledText(m_simpleVersion, "Version:", 0, m_notifier);

		m_rangeVersion = new Composite(m_dsParent, SWT.NONE);
		layout = new GridLayout(4, false);
		layout.marginWidth = layout.marginHeight = 0;
		m_rangeVersion.setLayout(layout);
		m_fromVersion = UiUtils.createLabeledText(m_rangeVersion, "From:", 0, m_notifier);
		m_toVersion = UiUtils.createLabeledText(m_rangeVersion, "To:", 0, m_notifier);

		String[] versionTypes = CorePlugin.getDefault().getExtensionIds(CorePlugin.VERSION_TYPES_POINT);

		m_versionType = new LabeledCombo(this, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);
		m_versionType.setLabel("Version type:");
		m_versionType.setItems(versionTypes);
		m_versionType.select(m_versionType.indexOf("OSGi"));
		m_versionType.addSelectionListener(m_notifier);
		dsTypeIndexChanged(DesignatorType.GREATER_OR_EQUAL.ordinal());
	}

	private void dsTypeIndexChanged(int idx)
	{
		if(idx < 0)
			return;

		Control current = m_dsStack.topControl;
		Control toBe = idx < 2 ? m_simpleVersion : m_rangeVersion;
		if(current != toBe)
		{
			if(current != null)
			{
				if(idx < 2)
					m_version.setText(m_fromVersion.getText());
				else
					m_fromVersion.setText(m_version.getText());
			}
			m_dsStack.topControl = toBe;
			m_dsParent.layout();
		}
	}

	public void refreshValues(IVersionDesignator versionDesignator)
	{
		if(versionDesignator != null)
		{
			DesignatorType dsType;
			if(versionDesignator.isExplicit())
				dsType = DesignatorType.EQUALS;
			else if(!versionDesignator.hasUpperBound())
				dsType = DesignatorType.GREATER_OR_EQUAL;
			else if(versionDesignator.includesLowerBound())
				dsType = versionDesignator.includesUpperBound() ? DesignatorType.RANGE_II
					: DesignatorType.RANGE_IE;
			else
				dsType = versionDesignator.includesUpperBound() ? DesignatorType.RANGE_EI
					: DesignatorType.RANGE_EE;
			m_versionDsType.select(dsType.ordinal());
			dsTypeIndexChanged(dsType.ordinal());

			String version = versionDesignator.getVersion().toString();
			m_version.setText(version);
			m_fromVersion.setText(version);
			m_toVersion.setText(TextUtils.notNullString(versionDesignator.getToVersion()));
			m_versionType.select(m_versionType.indexOf(versionDesignator.getVersion().getType().getId()));
		}
		else
		{
			m_version.setText("");
			m_fromVersion.setText("");
			m_toVersion.setText("");
			m_versionType.select(-1);
		}
	}

	public IVersionDesignator getVersionDesignator()
	{
		int vdIndex = m_versionDsType.getSelectionIndex();
		if(vdIndex < 0)
			return null;

		try
		{
			return DesignatorType.values()[vdIndex].createDesignator(this.getVersionType(), m_version,
				m_fromVersion, m_toVersion);
		}
		catch(CoreException e)
		{
			CorePlugin.getLogger().error(e.getMessage(), e);
			ErrorDialog.openError(this.getShell(), null, null, e.getStatus());
			return null;
		}
	}

	public String getVersionType()
	{
		int vtIndex = m_versionType.getSelectionIndex();
		return vtIndex < 0 ? null : m_versionType.getItem(vtIndex);
	}
}

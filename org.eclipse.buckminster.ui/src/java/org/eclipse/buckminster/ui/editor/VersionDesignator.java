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
import java.util.List;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.core.version.VersionType;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.general.editor.simple.Widgetin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionFormat;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Mini editor for the VersionDesignator
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class VersionDesignator extends Widgetin
{
	enum DesignatorType
	{
		EQUALS
		{
			@Override
			public String toString()
			{
				return Messages.equal_to_version;
			}

			@Override
			VersionRange createDesignator(String versionType, Text version, Text from, Text to) throws CoreException
			{
				Version tmp = VersionHelper.createVersion(versionType, UiUtils.trimmedValue(version));
				return (tmp == null)
						? null
						: VersionHelper.exactRange(tmp);
			}
		},
		GREATER_OR_EQUAL
		{
			@Override
			public String toString()
			{
				return Messages.grater_or_equal_to_version;
			}

			@Override
			VersionRange createDesignator(String versionType, Text version, Text from, Text to) throws CoreException
			{
				Version tmp = VersionHelper.createVersion(versionType, UiUtils.trimmedValue(version));
				return (tmp == null)
						? null
						: VersionHelper.greaterOrEqualRange(tmp);
			}
		},
		RANGE_IE
		{
			@Override
			public String toString()
			{
				return Messages.from_incl_to_excl_version;
			}

			@Override
			VersionRange createDesignator(String versionType, Text version, Text from, Text to) throws CoreException
			{
				return createRange(from, to, versionType, '[', ')');
			}
		},
		RANGE_II
		{
			@Override
			public String toString()
			{
				return Messages.from_incl_to_incl_version;
			}

			@Override
			VersionRange createDesignator(String versionType, Text version, Text from, Text to) throws CoreException
			{
				return createRange(from, to, versionType, '[', ']');
			}
		},
		RANGE_EE
		{
			@Override
			public String toString()
			{
				return Messages.from_excl_to_excl_version;
			}

			@Override
			VersionRange createDesignator(String versionType, Text version, Text from, Text to) throws CoreException
			{
				return createRange(from, to, versionType, '(', ')');
			}
		},
		RANGE_EI
		{
			@Override
			public String toString()
			{
				return Messages.from_excl_to_incl_version;
			}

			@Override
			VersionRange createDesignator(String versionType, Text version, Text from, Text to) throws CoreException
			{
				return createRange(from, to, versionType, '(', ']');
			}
		};

		static String[] getStrings()
		{
			DesignatorType[] dsTypes = values();
			int idx = dsTypes.length;
			String[] strings = new String[idx];
			while(--idx >= 0)
				strings[idx] = dsTypes[idx].toString();
			return strings;
		}

		private static VersionRange createRange(Text from, Text to, String versionType, char start, char end)
				throws CoreException
		{
			String tmp = UiUtils.trimmedValue(from);
			String tmp2 = UiUtils.trimmedValue(to);
			if(tmp == null || tmp2 == null)
				return null;

			return VersionHelper.createRange(versionType, start + tmp + ',' + tmp2 + end);
		}

		abstract VersionRange createDesignator(String versionType, Text version, Text from, Text to)
				throws CoreException;
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

	private final Composite m_parentComposite;

	private final Label m_versionDsTypeLabel;

	private final Combo m_versionDsType;

	private final Label m_rangeLabel;

	private final Text m_fromVersion;

	private final Composite m_toComposite;

	private final StackLayout m_toStackLayout;

	private final Label m_toEmptyLabel;

	private final Text m_toVersion;

	private final Combo m_versionType;

	private final ArrayList<VersionDesignatorListener> m_listeners = new ArrayList<VersionDesignatorListener>();

	public VersionDesignator(Composite parent)
	{
		this(parent, false);
	}

	public VersionDesignator(Composite parent, final boolean readOnly)
	{
		m_parentComposite = parent;

		m_versionDsTypeLabel = UiUtils.createGridLabel(m_parentComposite, Messages.designator_with_colon, 1, 0,
				SWT.NONE);

		SelectionListener versionDSListener = new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				dsTypeIndexChanged(((Combo)e.getSource()).getSelectionIndex());
			}
		};

		m_versionDsType = UiUtils.createGridEnumCombo(m_parentComposite, 1, 0, DesignatorType.values(), readOnly,
				readOnly
						? null
						: versionDSListener, null, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);

		m_versionDsType.addSelectionListener(m_notifier);
		UiUtils.createEmptyLabel(m_parentComposite);

		m_rangeLabel = UiUtils.createGridLabel(m_parentComposite, Messages.version_with_colon, 1, 0, SWT.NONE);

		m_fromVersion = UiUtils.createGridText(m_parentComposite, 1, 0, readOnly, SWT.NONE, m_notifier);

		m_toComposite = new Composite(m_parentComposite, SWT.NONE);
		m_toComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		m_toStackLayout = new StackLayout();
		m_toComposite.setLayout(m_toStackLayout);

		m_toEmptyLabel = UiUtils.createEmptyLabel(m_toComposite);

		m_toVersion = UiUtils.createGridText(m_toComposite, 1, 0, readOnly, SWT.NONE, m_notifier);

		UiUtils.createGridLabel(m_parentComposite, Messages.type_with_colon, 1, 0, SWT.NONE);
		m_versionType = UiUtils.createGridCombo(m_parentComposite, 1, 0, readOnly, null, null, SWT.DROP_DOWN
				| SWT.READ_ONLY | SWT.SIMPLE);

		List<VersionType> knownTypes = VersionHelper.getKnownTypes();
		int idx = knownTypes.size();
		String[] versionTypes = new String[idx];
		while(--idx >= 0)
			versionTypes[idx] = knownTypes.get(idx).getId();

		m_versionType.setItems(versionTypes);
		m_versionType.select(m_versionType.indexOf("OSGi")); //$NON-NLS-1$
		m_versionType.addSelectionListener(m_notifier);
		UiUtils.createEmptyLabel(m_parentComposite);

		dsTypeIndexChanged(DesignatorType.GREATER_OR_EQUAL.ordinal());
	}

	public void addVersionDesignatorListener(VersionDesignatorListener listener)
	{
		if(!m_listeners.contains(listener))
			m_listeners.add(listener);
	}

	public VersionRange getDirectVersionDesignator() throws CoreException
	{
		int vdIndex = m_versionDsType.getSelectionIndex();
		if(vdIndex < 0)
			return null;

		return DesignatorType.values()[vdIndex].createDesignator(this.getVersionType(), m_fromVersion, m_fromVersion,
				m_toVersion);
	}

	public Display getDisplay()
	{
		return m_parentComposite.getDisplay();
	}

	public VersionRange getVersionDesignator()
	{
		try
		{
			return getDirectVersionDesignator();
		}
		catch(CoreException e)
		{
			CorePlugin.getLogger().error(e, e.getMessage());
			ErrorDialog.openError(m_parentComposite.getShell(), null, null, e.getStatus());
			return null;
		}
	}

	// need to get size of this Combo
	public Combo getVersionDsTypeCombo()
	{
		return m_versionDsType;
	}

	// need to change width of the Label
	public Label getVersionDsTypeLabel()
	{
		return m_versionDsTypeLabel;
	}

	public String getVersionType()
	{
		int vtIndex = m_versionType.getSelectionIndex();
		return vtIndex < 0
				? null
				: m_versionType.getItem(vtIndex);
	}

	public void refreshValues(VersionRange versionDesignator)
	{
		if(versionDesignator != null)
		{
			DesignatorType dsType;
			if(versionDesignator.getMinimum().equals(versionDesignator.getMaximum()))
				dsType = DesignatorType.EQUALS;
			else
			{
				String vds = versionDesignator.toString();
				if(!(vds.startsWith("[") || vds.startsWith("(") || vds.startsWith("raw:[") || vds.startsWith("raw:(")))
					dsType = DesignatorType.GREATER_OR_EQUAL;
				else if(versionDesignator.getIncludeMinimum())
					dsType = versionDesignator.getIncludeMaximum()
							? DesignatorType.RANGE_II
							: DesignatorType.RANGE_IE;
				else
					dsType = versionDesignator.getIncludeMaximum()
							? DesignatorType.RANGE_EI
							: DesignatorType.RANGE_EE;
			}
			m_versionDsType.select(dsType.ordinal());
			dsTypeIndexChanged(dsType.ordinal());

			if(versionDesignator.getFormat() == VersionFormat.OSGI_FORMAT)
			{
				m_fromVersion.setText(versionDesignator.getMinimum().toString());
				m_toVersion.setText(TextUtils.notNullString(versionDesignator.getMaximum()));
			}
			else
			{
				m_fromVersion.setText(versionDesignator.getMinimum().getOriginal());
				Version maxVer = versionDesignator.getMaximum();
				if(maxVer == null)
					m_toVersion.setText(""); //$NON-NLS-1$
				else
					m_toVersion.setText(maxVer.getOriginal());
			}
			m_versionType.select(m_versionType.indexOf(VersionHelper.getVersionType(versionDesignator).getId()));
		}
		else
		{
			m_fromVersion.setText(""); //$NON-NLS-1$
			m_toVersion.setText(""); //$NON-NLS-1$
			m_versionType.select(-1);
		}
	}

	public void removeVersionDesignatorListener(VersionDesignatorListener listener)
	{
		m_listeners.remove(listener);
	}

	public void setEnabled(boolean flag)
	{
		m_versionDsType.setEnabled(flag);
		m_fromVersion.setEnabled(flag);
		m_toVersion.setEnabled(flag);
		m_versionType.setEnabled(flag);
	}

	private void dsTypeIndexChanged(int idx)
	{
		if(idx < 0)
			return;

		Control current = m_toStackLayout.topControl;
		Control toBe = idx < 2
				? m_toEmptyLabel
				: m_toVersion;
		if(current != toBe)
		{
			if(current != null)
			{
				if(idx < 2)
				{
					m_rangeLabel.setText(Messages.version_with_colon);
				}
				else
				{
					m_rangeLabel.setText(Messages.from_to_with_colon);
				}
			}
			m_toStackLayout.topControl = toBe;
			m_toComposite.layout();
		}

	}
}

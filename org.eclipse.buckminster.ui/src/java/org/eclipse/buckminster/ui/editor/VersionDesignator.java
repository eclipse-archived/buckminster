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
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
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
public class VersionDesignator extends Widgetin {
	enum DesignatorType {
		EQUALS {
			@Override
			public String toString() {
				return Messages.equal_to_version;
			}

			@Override
			VersionRange createDesignator(String versionType, Text version, Text from, Text to) throws CoreException {
				Version tmp = VersionHelper.createVersion(versionType, UiUtils.trimmedValue(version));
				return (tmp == null) ? null : VersionHelper.exactRange(tmp);
			}
		},
		GREATER_OR_EQUAL {
			@Override
			public String toString() {
				return Messages.grater_or_equal_to_version;
			}

			@Override
			VersionRange createDesignator(String versionType, Text version, Text from, Text to) throws CoreException {
				Version tmp = VersionHelper.createVersion(versionType, UiUtils.trimmedValue(version));
				return (tmp == null) ? null : VersionHelper.greaterOrEqualRange(tmp);
			}
		},
		RANGE_IE {
			@Override
			public String toString() {
				return Messages.from_incl_to_excl_version;
			}

			@Override
			VersionRange createDesignator(String versionType, Text version, Text from, Text to) throws CoreException {
				return createRange(from, to, versionType, '[', ')');
			}
		},
		RANGE_II {
			@Override
			public String toString() {
				return Messages.from_incl_to_incl_version;
			}

			@Override
			VersionRange createDesignator(String versionType, Text version, Text from, Text to) throws CoreException {
				return createRange(from, to, versionType, '[', ']');
			}
		},
		RANGE_EE {
			@Override
			public String toString() {
				return Messages.from_excl_to_excl_version;
			}

			@Override
			VersionRange createDesignator(String versionType, Text version, Text from, Text to) throws CoreException {
				return createRange(from, to, versionType, '(', ')');
			}
		},
		RANGE_EI {
			@Override
			public String toString() {
				return Messages.from_excl_to_incl_version;
			}

			@Override
			VersionRange createDesignator(String versionType, Text version, Text from, Text to) throws CoreException {
				return createRange(from, to, versionType, '(', ']');
			}
		};

		static String[] getStrings() {
			DesignatorType[] dsTypes = values();
			int idx = dsTypes.length;
			String[] strings = new String[idx];
			while (--idx >= 0)
				strings[idx] = dsTypes[idx].toString();
			return strings;
		}

		private static VersionRange createRange(Text from, Text to, String versionType, char start, char end) throws CoreException {
			String tmp = UiUtils.trimmedValue(from);
			String tmp2 = UiUtils.trimmedValue(to);
			if (tmp == null || tmp2 == null)
				return null;

			return VersionHelper.createRange(versionType, start + tmp + ',' + tmp2 + end);
		}

		abstract VersionRange createDesignator(String versionType, Text version, Text from, Text to) throws CoreException;
	}

	ChangeAdapter notifier = new ChangeAdapter() {
		@Override
		protected void onChange(TypedEvent te) {
			VersionDesignatorEvent e = new VersionDesignatorEvent(VersionDesignator.this, te.widget, te.data);
			for (VersionDesignatorListener listener : listeners)
				listener.modifyVersionDesignator(e);
		}
	};

	private final Composite parentComposite;

	private final Label versionDsTypeLabel;

	private final Combo versionDsType;

	private final Label rangeLabel;

	private final Text fromVersion;

	private final Composite toComposite;

	private final StackLayout toStackLayout;

	private final Label toEmptyLabel;

	private final Text toVersion;

	private final Combo versionType;

	private final ArrayList<VersionDesignatorListener> listeners = new ArrayList<VersionDesignatorListener>();

	public VersionDesignator(Composite parent) {
		this(parent, false);
	}

	public VersionDesignator(Composite parent, final boolean readOnly) {
		parentComposite = parent;

		versionDsTypeLabel = UiUtils.createGridLabel(parentComposite, Messages.designator_with_colon, 1, 0, SWT.NONE);

		SelectionListener versionDSListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dsTypeIndexChanged(((Combo) e.getSource()).getSelectionIndex());
			}
		};

		versionDsType = UiUtils.createGridEnumCombo(parentComposite, 1, 0, DesignatorType.values(), readOnly,
				readOnly ? null : versionDSListener, null, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);

		versionDsType.addSelectionListener(notifier);
		UiUtils.createEmptyLabel(parentComposite);

		rangeLabel = UiUtils.createGridLabel(parentComposite, Messages.version_with_colon, 1, 0, SWT.NONE);

		fromVersion = UiUtils.createGridText(parentComposite, 1, 0, readOnly, SWT.NONE, notifier);

		toComposite = new Composite(parentComposite, SWT.NONE);
		toComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		toStackLayout = new StackLayout();
		toComposite.setLayout(toStackLayout);

		toEmptyLabel = UiUtils.createEmptyLabel(toComposite);

		toVersion = UiUtils.createGridText(toComposite, 1, 0, readOnly, SWT.NONE, notifier);

		UiUtils.createGridLabel(parentComposite, Messages.type_with_colon, 1, 0, SWT.NONE);
		versionType = UiUtils.createGridCombo(parentComposite, 1, 0, readOnly, null, null, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);

		List<VersionType> knownTypes = VersionHelper.getKnownTypes();
		int idx = knownTypes.size();
		String[] versionTypes = new String[idx];
		while (--idx >= 0)
			versionTypes[idx] = knownTypes.get(idx).getId();

		versionType.setItems(versionTypes);
		versionType.select(versionType.indexOf("OSGi")); //$NON-NLS-1$
		versionType.addSelectionListener(notifier);
		UiUtils.createEmptyLabel(parentComposite);

		dsTypeIndexChanged(DesignatorType.GREATER_OR_EQUAL.ordinal());
	}

	public void addVersionDesignatorListener(VersionDesignatorListener listener) {
		if (!listeners.contains(listener))
			listeners.add(listener);
	}

	public VersionRange getDirectVersionDesignator() throws CoreException {
		int vdIndex = versionDsType.getSelectionIndex();
		if (vdIndex < 0)
			return null;

		return DesignatorType.values()[vdIndex].createDesignator(this.getVersionType(), fromVersion, fromVersion, toVersion);
	}

	public Display getDisplay() {
		return parentComposite.getDisplay();
	}

	public VersionRange getVersionDesignator() {
		try {
			return getDirectVersionDesignator();
		} catch (CoreException e) {
			CorePlugin.getLogger().error(e, e.getMessage());
			ErrorDialog.openError(parentComposite.getShell(), null, null, e.getStatus());
			return null;
		}
	}

	// need to get size of this Combo
	public Combo getVersionDsTypeCombo() {
		return versionDsType;
	}

	// need to change width of the Label
	public Label getVersionDsTypeLabel() {
		return versionDsTypeLabel;
	}

	public String getVersionType() {
		int vtIndex = versionType.getSelectionIndex();
		return vtIndex < 0 ? null : versionType.getItem(vtIndex);
	}

	public void refreshValues(VersionRange versionDesignator) {
		if (versionDesignator != null) {
			DesignatorType dsType;
			if (versionDesignator.getMinimum().equals(versionDesignator.getMaximum()))
				dsType = DesignatorType.EQUALS;
			else {
				String vds = versionDesignator.toString();
				if (!(vds.startsWith("[") || vds.startsWith("(") || vds.startsWith("raw:[") || vds.startsWith("raw:("))) //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$//$NON-NLS-4$
					dsType = DesignatorType.GREATER_OR_EQUAL;
				else if (versionDesignator.getIncludeMinimum())
					dsType = versionDesignator.getIncludeMaximum() ? DesignatorType.RANGE_II : DesignatorType.RANGE_IE;
				else
					dsType = versionDesignator.getIncludeMaximum() ? DesignatorType.RANGE_EI : DesignatorType.RANGE_EE;
			}
			versionDsType.select(dsType.ordinal());
			dsTypeIndexChanged(dsType.ordinal());

			if (versionDesignator.getFormat() == VersionHelper.getOSGiFormat()) {
				fromVersion.setText(versionDesignator.getMinimum().toString());
				toVersion.setText(TextUtils.notNullString(versionDesignator.getMaximum()));
			} else {
				fromVersion.setText(versionDesignator.getMinimum().getOriginal());
				Version maxVer = versionDesignator.getMaximum();
				if (maxVer == null)
					toVersion.setText(""); //$NON-NLS-1$
				else
					toVersion.setText(TextUtils.notNullString(maxVer.getOriginal()));
			}
			versionType.select(versionType.indexOf(VersionHelper.getVersionType(versionDesignator).getId()));
		} else {
			fromVersion.setText(""); //$NON-NLS-1$
			toVersion.setText(""); //$NON-NLS-1$
			versionType.select(-1);
		}
	}

	public void removeVersionDesignatorListener(VersionDesignatorListener listener) {
		listeners.remove(listener);
	}

	public void setEnabled(boolean flag) {
		versionDsType.setEnabled(flag);
		fromVersion.setEnabled(flag);
		toVersion.setEnabled(flag);
		versionType.setEnabled(flag);
	}

	private void dsTypeIndexChanged(int idx) {
		if (idx < 0)
			return;

		Control current = toStackLayout.topControl;
		Control toBe = idx < 2 ? toEmptyLabel : toVersion;
		if (current != toBe) {
			if (current != null) {
				if (idx < 2) {
					rangeLabel.setText(Messages.version_with_colon);
				} else {
					rangeLabel.setText(Messages.from_to_with_colon);
				}
			}
			toStackLayout.topControl = toBe;
			toComposite.layout();
		}

	}
}

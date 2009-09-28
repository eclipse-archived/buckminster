/*******************************************************************************
 * Copyright (c) 2009 Johannes Utzig.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Johannes Utzig - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.ui.dependency.visualizer.controls;

import org.eclipse.buckminster.ui.dependency.visualizer.Messages;
import org.eclipse.buckminster.ui.dependency.visualizer.controls.listener.ViewerSettingChangeEvent;
import org.eclipse.buckminster.ui.dependency.visualizer.controls.listener.ViewerSettingType;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.filter.PlatformComponentsFilter;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.filter.RegExFilter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * A control to select what filters should be applied to the viewer
 * 
 * @author Johannes Utzig
 * 
 */
public class FilterControl extends AbstractViewerSettingControl
{

	class RegExText implements Runnable, ModifyListener
	{

		private ViewerFilter regexFilter;

		private boolean isProcessing;

		private Text regex;

		private boolean invert;

		public RegExText(boolean invert)
		{
			super();
			this.invert = invert;
		}

		public void createControl(Composite parent)
		{
			if(invert)
			{
				getWidgetToolkit().createLabel(parent, Messages.Blacklist);
			}
			else
			{
				getWidgetToolkit().createLabel(parent, Messages.Whilelist);
			}

			regex = getWidgetToolkit().createText(parent, "", SWT.BORDER); //$NON-NLS-1$
			regex.addModifyListener(this);
		}

		public void modifyText(ModifyEvent e)
		{
			if(!isProcessing)
			{
				e.display.timerExec(1000, this);
				isProcessing = true;
			}

		}

		public void run()
		{
			if(regexFilter != null)
				fireViewerSettingsChangedEvent(new ViewerSettingChangeEvent(FilterControl.this,
						ViewerSettingType.FILTER_REMOVED, regexFilter, null));
			if(regex.getText() == null || regex.getText().length() == 0)
			{
				return;
			}
			regexFilter = new RegExFilter(regex.getText(), invert);
			fireViewerSettingsChangedEvent(new ViewerSettingChangeEvent(FilterControl.this,
					ViewerSettingType.FILTER_ADDED, regexFilter, null));
			isProcessing = false;
		}
	}

	private ViewerFilter platformFilter = new PlatformComponentsFilter();

	public FilterControl(FormToolkit toolkit)
	{
		super(toolkit);
	}

	@Override
	public Control createControl(Composite parent)
	{
		Composite filterComposite = getWidgetToolkit().createComposite(parent);
		filterComposite.setLayout(new GridLayout(2, true));

		final Button filterPlatform = getWidgetToolkit().createButton(filterComposite, Messages.FilterTargetPlatform,
				SWT.CHECK);
		GridDataFactory.fillDefaults().span(2, 1).applyTo(filterPlatform);
		filterPlatform.setSelection(true);
		filterPlatform.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{

				if(filterPlatform.getSelection())
				{
					fireViewerSettingsChangedEvent(new ViewerSettingChangeEvent(this, ViewerSettingType.FILTER_ADDED,
							platformFilter, null));

				}
				else
				{
					fireViewerSettingsChangedEvent(new ViewerSettingChangeEvent(this, ViewerSettingType.FILTER_REMOVED,
							platformFilter, null));
				}
			}
		});
		// new RegExText(false).createControl(filterComposite);
		// new RegExText(true).createControl(filterComposite);

		// fire defaults
		fireViewerSettingsChangedEvent(new ViewerSettingChangeEvent(this, ViewerSettingType.FILTER_ADDED,
				platformFilter, null));
		return filterComposite;
	}

}

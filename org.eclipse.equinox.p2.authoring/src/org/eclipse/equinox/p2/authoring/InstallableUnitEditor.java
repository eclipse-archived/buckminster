/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.equinox.p2.authoring;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.p2.authoring.forms.RichFormEditor;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitEditorInput;
import org.eclipse.equinox.p2.authoring.internal.ModelChangeEvent;
import org.eclipse.equinox.p2.authoring.internal.P2MetadataReader;
import org.eclipse.equinox.p2.authoring.internal.SaveIURunnable;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.TouchpointTypeBuilder;
import org.eclipse.equinox.p2.authoring.internal.touchpoints.UnknownTouchpoint;
import org.eclipse.equinox.p2.authoring.spi.ITouchpointTypeDescriptor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.ILocationProvider;

/**
 * A multi page Eclipse form based editor for p2 Installable Unit.
 * 
 * The editor keeps track of two types of "dirty" state - a) the dirty state in the editor pages (and sub editors),
 * which reflect the dirty state in relation to the model object b) the model's dirty state. Editor pages are marked as
 * "clean" when the data in the editor has been "comitted" to the model object. This happens frequently when the user
 * switches between pages in the editor, or when switching between details in a master detail setup.
 * 
 * Changes to the model marks the model as "changed" (i.e. dirty), until the changed-state is cleared. The editor clears
 * this state when the model is saved to file.
 * 
 * The Editor provides an event bus see {@link RichFormEditor#getEventBus()}. This event bus is also given to the model
 * object, and listeners interested in model change events can subscribe to these via the bus. The model emits
 * {@link ModelChangeEvent} event object instances.
 * 
 * @author Henrik Lindberg
 * 
 */
public class InstallableUnitEditor extends RichFormEditor
{
	private static final String EXTENSION_FILTER = "*.iu"; //$NON-NLS-1$

	private static final String TMP_SUFFIX = "iu"; //$NON-NLS-1$

	private static final String TMP_PREFIX = "p2iu-"; //$NON-NLS-1$

	private InstallableUnitBuilder m_iu;

	private boolean m_readOnly = false;

	private TouchpointTypeBuilder m_originalTouchpointType;

	private ITouchpointTypeDescriptor[] m_touchpointTypes;

	public InstallableUnitEditor()
	{
		setTmpPrefix(TMP_PREFIX); 
		setTmpSuffix(TMP_SUFFIX); 
	}

	@Override
	protected void addPages()
	{
		createActions();
		try
		{
			addPage(new OverviewPage(this));
			addPage(new RequiredCapabilitiesPage(this));
			addPage(new ProvidedCapabilitiesPage(this));
			addPage(new ArtifactsPage(this));
			addPage(new InformationPage(this));
			addPage(new TouchpointPage(this));
			addPage(new UpdatePage(this));
		}
		catch(PartInitException e)
		{
			// TODO: Proper logging
			e.printStackTrace();
		}
	}

	public void doExternalSaveAs()
	{
		if(!commitChanges())
			return;
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { EXTENSION_FILTER }); 
		final String location = dlg.open();
		if(location == null)
			return;
		saveToPath(new Path(location));
	}

	public InstallableUnitBuilder getInstallableUnit()
	{
		return m_iu;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException
	{
		// super version sets the site, sets the input (without notify), and then installs a selection provider.
		//
		super.init(site, input);
		if(!(input instanceof ILocationProvider || input instanceof IPathEditorInput || //
				input instanceof IURIEditorInput || input instanceof InstallableUnitEditorInput))
			throw new PartInitException("Invalid Input");

		if(input instanceof IURIEditorInput)
		{
			try
			{
				input = getExternalFileEditorInput((IURIEditorInput)input);
			}
			catch(Exception e)
			{
				throw new PartInitException("Unable to open editor", e);
			}
		}

		InputStream stream = null;
		try
		{
			if(input instanceof InstallableUnitEditorInput)
			{
				m_readOnly = true;
				// make mutable copy
				m_iu = new InstallableUnitBuilder((((InstallableUnitEditorInput)input).getInstallableUnit()));
			}
			else
			{
				IPath path = (input instanceof ILocationProvider)
						? ((ILocationProvider)input).getPath(input)
						: ((IPathEditorInput)input).getPath();

				// Always allow edit of a file TODO: should perhaps check for extension = ".iu"
				m_readOnly = false;

				File file = path.toFile();
				if(file.length() != 0)
				{
					stream = new FileInputStream(file);
					// note url passed is only for information - creates mutable copy for editing
					m_iu = new InstallableUnitBuilder(P2MetadataReader.readInstallableUnit(file.toURL(), stream, site
							.getActionBars().getStatusLineManager().getProgressMonitor()));
				}
			}
			// If we got a model object, set the event bus to use to send model change events.
			//
			if(m_iu != null)
			{
				m_iu.setEventBus(getEventBus());
				m_originalTouchpointType = m_iu.getTouchpointType();
			}
			setInputWithNotify(input);
			setPartName(input.getName() + (m_readOnly
					? " (read only)"
					: "")); //$NON-NLS-1$
		}
		catch(Exception e)
		{
			// TODO: Uses Buckminster exception wrapper
			throw new PartInitException(BuckminsterException.wrap(e).getMessage());
		}
		finally
		{
			try
			{
				stream.close();
			}
			catch(IOException e)
			{
			}
		}
	}

	/**
	 * Returns the original TouchpointType from the IU when read from the .iu file. This type is preserved as it may
	 * represent an unknown type, and the user should be able to select this (i.e. keep it after changing to some other
	 * type when editing).
	 */
	public TouchpointTypeBuilder getOriginalTouchpointType()
	{
		return m_originalTouchpointType;
	}

	/**
	 * Overrides the default implementation by checking if the model object (the installable unit) is dirty. The default
	 * implementation only checks if the editor pages are dirty, and they are marked as clean as soon as the model
	 * object has been updated (comitted).
	 */
	@Override
	public boolean isDirty()
	{
		if(m_iu != null && m_iu.isChanged())
			return true;
		return super.isDirty();
	}

	public boolean isReadOnly()
	{
		return m_readOnly;
	}

	@Override
	public final void saveToPath(IPath path)
	{
		try
		{
			SaveIURunnable sr = new SaveIURunnable(m_iu.createInstallableUnit(), path);
			getSite().getWorkbenchWindow().run(true, true, sr);
			setInputWithNotify(sr.getSavedInput());
			// mark the model object as unchanged
			m_iu.setChanged(false);
			setPartName(path.lastSegment());
			firePropertyChange(IWorkbenchPart.PROP_TITLE);
			editorDirtyStateChanged();
		}
		catch(InvocationTargetException e)
		{
			CoreException t = BuckminsterException.wrap(e);
			String msg = "Unable to save file " + path;
			CorePlugin.getLogger().error(t, msg);
			ErrorDialog.openError(getSite().getShell(), null, msg, t.getStatus());
		}
		catch(InterruptedException e)
		{
		}
	}

	/**
	 * Returns the available touchpoint type descriptors from the plugin plus an Unknown type descriptor with the
	 * original typeId and version from the IU when read from file.
	 * 
	 * @return
	 */
	public ITouchpointTypeDescriptor[] getTouchpointTypes()
	{
		if(m_touchpointTypes == null)
		{
			m_touchpointTypes = P2AuthoringUIPlugin.getDefault().getTouchpointTypes();
			if(m_originalTouchpointType != null)
			{
				ITouchpointTypeDescriptor desc = P2AuthoringUIPlugin.getDefault().getTouchpointType(
						m_originalTouchpointType);
				if(desc == null)
				{
					ITouchpointTypeDescriptor[] result2 = new ITouchpointTypeDescriptor[m_touchpointTypes.length + 1];
					System.arraycopy(m_touchpointTypes, 0, result2, 1, m_touchpointTypes.length);
					result2[0] = new UnknownTouchpoint(m_originalTouchpointType);
					m_touchpointTypes = result2;
				}
			}
		}
		return m_touchpointTypes;
	}

	/**
	 * Returns the touchpoint type descriptor for the type.
	 * 
	 * @param type
	 * @return
	 */
	public ITouchpointTypeDescriptor getTouchpointType(TouchpointTypeBuilder type)
	{
		// null/none type, or a known type
		ITouchpointTypeDescriptor ttd = P2AuthoringUIPlugin.getDefault().getTouchpointType(type);
		// If not found, it must be the unknown type.
		if(ttd == null)
			return getTouchpointTypes()[0];
		return ttd;
	}
}

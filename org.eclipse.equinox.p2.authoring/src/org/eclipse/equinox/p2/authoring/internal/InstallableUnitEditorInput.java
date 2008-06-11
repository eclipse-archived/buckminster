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

package org.eclipse.equinox.p2.authoring.internal;

import java.io.InputStream;

import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.equinox.internal.p2.metadata.InstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;
import org.osgi.framework.Version;

/**
 * Editor input for an InstallableUnit instance. This creates read only input.
 * 
 * TODO: This class now requires the impl object InstallableUnit since the meta data writer operates on it instead of
 * IInstallableUnit. Should be changed.
 * 
 * @author Henrik Lindberg
 * 
 */
@SuppressWarnings("restriction")
public class InstallableUnitEditorInput implements IStorageEditorInput
{
	private final InstallableUnit m_iu;

	public InstallableUnitEditorInput(InstallableUnit installable)
	{
		m_iu = installable;
	}

	@Override
	public boolean equals(Object other)
	{
		return other == this
				|| (other instanceof InstallableUnitEditorInput && ((InstallableUnitEditorInput)other).m_iu
						.equals(m_iu));
	}

	public boolean exists()
	{
		return true;
	}

	public InstallableUnit getInstallableUnit()
	{
		return m_iu;
	}

	public String getName()
	{
		StringBuilder bld = new StringBuilder();
		bld.append(m_iu.getProperty(IInstallableUnit.PROP_NAME));
		Version version = m_iu.getVersion();
		if(version != null)
		{
			bld.append(':');
			bld.append(version);
		}
		bld.append(".iu");
		return bld.toString();
	}

	public String getToolTipText()
	{
		return this.getName();
	}

	@Override
	public int hashCode()
	{
		return m_iu.hashCode();
	}

	protected InstallableUnit getContent() throws CoreException
	{
		return m_iu;
	}

	public IStorage getStorage() throws CoreException
	{
		return new IStorage()
		{
			public InputStream getContents() throws CoreException
			{
				AccessibleByteArrayOutputStream bld = new AccessibleByteArrayOutputStream();
				P2MetadataWriter.writeInstallableUnit(InstallableUnitEditorInput.this.getContent(), bld);
				return bld.getInputStream();
			}

			public IPath getFullPath()
			{
				return null;
			}

			public String getName()
			{
				return InstallableUnitEditorInput.this.getName();
			}

			public boolean isReadOnly()
			{
				return true;
			}

			@SuppressWarnings("unchecked")
			public Object getAdapter(Class adapter)
			{
				return null;
			}

		};
	}

	public ImageDescriptor getImageDescriptor()
	{
		// Not needed
		return null;
	}

	public IPersistableElement getPersistable()
	{
		// not needed
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter)
	{
		if(adapter == InstallableUnitEditorInput.class)
			return this;
		return null;
	}
}

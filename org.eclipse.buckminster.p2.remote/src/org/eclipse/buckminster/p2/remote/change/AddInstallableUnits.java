/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.change;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.equinox.internal.provisional.p2.core.repository.IRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.jabsorb.JSONRPCBridge;
import org.jabsorb.serializer.MarshallException;
import org.jabsorb.serializer.UnmarshallException;

/**
 * @author Thomas Hallgren
 */
public class AddInstallableUnits extends RepositoryChange
{
	private static final long serialVersionUID = -5299894549955645829L;

	private transient IInstallableUnit[] m_additions;

	@Override
	public void apply(IRepository repository)
	{
		((IMetadataRepository)repository).addInstallableUnits(m_additions);
	}

	public IInstallableUnit[] getAdditions()
	{
		return m_additions;
	}

	public void setAdditions(IInstallableUnit[] additions)
	{
		m_additions = additions;
	}

	private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		int entryCount = in.readInt();
		m_additions = new IInstallableUnit[entryCount];
		for(int idx = 0; idx < entryCount; ++idx)
		{
			try
			{
				m_additions[idx] = (IInstallableUnit)JSONRPCBridge.getSerializer().fromJSON(in.readUTF());
			}
			catch(UnmarshallException e)
			{
				IOException ioe = new IOException(e.getMessage());
				ioe.initCause(e);
				throw ioe;
			}
		}
	}

	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.writeInt(m_additions.length);
		for(int idx = 0; idx < m_additions.length; ++idx)
		{
			try
			{
				out.writeUTF(JSONRPCBridge.getSerializer().toJSON(m_additions[idx]));
			}
			catch(MarshallException e)
			{
				IOException ioe = new IOException(e.getMessage());
				ioe.initCause(e);
				throw ioe;
			}
		}
	}
}

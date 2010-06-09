package org.eclipse.buckminster.p2.remote.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.List;

import org.eclipse.buckminster.p2.remote.change.RepositoryChange;
import org.eclipse.buckminster.p2.remote.change.SynchronizationBlock;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;

public class ChangeLog
{
	private static final long CHANGELOG_MAGIC = 0x4368616E654C6F67L;

	private final File m_logFile;

	public ChangeLog(File logFile, long sequenceStart, RepositoryServer server) throws ProvisionException
	{
		ObjectInputStream input = null;
		try
		{
			input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(logFile), 0x8000));
			assertMagic(input);
		}
		catch(FileNotFoundException e)
		{
			ObjectOutputStream output = null;
			try
			{
				output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(logFile), 26));
				output.writeLong(CHANGELOG_MAGIC);
				output.writeLong(sequenceStart);
				output.writeInt(0);
			}
			catch(IOException e2)
			{
				throw new ProvisionException(BuckminsterException.createStatus(e2));
			}
			finally
			{
				IOUtils.close(output);
			}
		}
		catch(IOException e)
		{
			throw new ProvisionException(BuckminsterException.createStatus(e));
		}
		finally
		{
			IOUtils.close(input);
		}
		m_logFile = logFile;
	}

	public void addChange(RepositoryChange change)
	{
		try
		{
			ObjectOutputStream output = null;
			try
			{
				change.setTimestamp(System.currentTimeMillis());
				output = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(m_logFile, true), 0x8000))
				{
					@Override
					protected void writeStreamHeader() throws IOException
					{
						super.reset();
					}
				};
				output.writeObject(change);
			}
			finally
			{
				IOUtils.close(output);
			}

			// Bump sequence number
			RandomAccessFile updater = null;
			try
			{
				updater = new RandomAccessFile(m_logFile, "rw");
				updater.seek(22);
				int entryCount = updater.readInt();
				updater.seek(22);
				updater.writeInt(entryCount + 1);
			}
			finally
			{
				IOUtils.close(updater);
			}
		}
		catch(RuntimeException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			Buckminster.getLogger().error(e, e.getMessage());
		}
	}

	public SynchronizationBlock getChangesSince(long sequenceNumber) throws ProvisionException
	{
		ObjectInputStream input = null;
		try
		{
			input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(m_logFile), 0x8000));
			assertMagic(input);
			long sequenceStart = input.readLong();
			int entryCount = input.readInt();
			SynchronizationBlock syncBlock = new SynchronizationBlock();
			if(sequenceStart + entryCount < sequenceNumber)
			{
				syncBlock.setLastChangeNumber(sequenceStart + entryCount);
				return syncBlock;
			}

			List<RepositoryChange> changes = syncBlock.getChanges();
			long currentNumber;
			for(currentNumber = sequenceStart;; ++currentNumber)
			{
				try
				{
					RepositoryChange change = (RepositoryChange)input.readObject();
					if(currentNumber > sequenceNumber)
						changes.add(change);
				}
				catch(EOFException e)
				{
					break;
				}
				catch(ClassNotFoundException e)
				{
					throw new ProvisionException(BuckminsterException.createStatus(e));
				}
			}
			syncBlock.setLastChangeNumber(currentNumber);
			return syncBlock;
		}
		catch(IOException e)
		{
			throw new ProvisionException(BuckminsterException.createStatus(e));
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	public long getLastChangeNumber() throws IOException, ProvisionException
	{
		ObjectInputStream input = null;
		try
		{
			input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(m_logFile), 0x8000));
			assertMagic(input);
			final long sequenceStart = input.readLong();
			final int entryCount = input.readInt();
			return sequenceStart + entryCount;
		}
		catch(FileNotFoundException e)
		{
			return 0L;
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	private void assertMagic(ObjectInputStream input) throws ProvisionException, IOException
	{
		if(input.readLong() != CHANGELOG_MAGIC)
			throw new ProvisionException(BuckminsterException.createStatus("Not a changelog file: %s",
				m_logFile));
	}
}

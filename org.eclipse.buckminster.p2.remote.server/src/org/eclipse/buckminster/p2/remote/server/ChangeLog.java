package org.eclipse.buckminster.p2.remote.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.buckminster.p2.remote.change.RepositoryChange;
import org.eclipse.buckminster.p2.remote.change.SynchronizationBlock;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.jabsorb.JSONRPCBridge;
import org.jabsorb.JSONSerializer;
import org.jabsorb.serializer.UnmarshallException;

public class ChangeLog
{
	private static final long CHANGELOG_MAGIC = 0x4368616E654C6F67L;

	private final File m_logFile;

	public ChangeLog(File logFile, long sequenceStart) throws CoreException
	{
		DataInputStream input = null;
		try
		{
			input = new DataInputStream(new BufferedInputStream(new FileInputStream(logFile), 0x8000));
			if(input.readLong() != CHANGELOG_MAGIC)
				throw BuckminsterException.fromMessage("Not a changelog file: %s", logFile);

			short major = input.readShort();
			short minor = input.readShort();
			if(!(major == 1 && minor == 0))
				throw BuckminsterException.fromMessage(
					"Unsupported version %d.%d in changelog file: %s. Only 1.0 is supported", new Integer(
						major), new Integer(minor), logFile);
		}
		catch(FileNotFoundException e)
		{
			DataOutputStream output = null;
			try
			{
				output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(logFile), 16));
				output.writeLong(CHANGELOG_MAGIC);
				output.writeShort(1);
				output.writeShort(0);
				output.writeLong(sequenceStart);
				output.writeLong(0);
			}
			catch(IOException e2)
			{
				throw BuckminsterException.wrap(e2);
			}
			finally
			{
				IOUtils.close(output);
			}
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}
		m_logFile = logFile;
	}

	public void addChange(RepositoryChange change)
	{
		RandomAccessFile log = null;
		try
		{
			change.setTimestamp(System.currentTimeMillis());
			String entryString = JSONRPCBridge.getSerializer().toJSON(change);
			log = new RandomAccessFile(m_logFile, "rw");
			log.seek(log.length());
			byte[] entryBytes = entryString.getBytes("US-ASCII");
			log.writeInt(entryBytes.length);
			log.write(entryBytes);
			log.seek(20);
			long entryCount = log.readLong();
			log.seek(20);
			log.writeLong(entryCount + 1);
		}
		catch(RuntimeException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			Buckminster.getLogger().error(e, e.getMessage());
		}
		finally
		{
			IOUtils.close(log);
		}
	}

	public SynchronizationBlock getChangesSince(long sequenceNumber) throws CoreException
	{
		DataInputStream input = null;
		try
		{
			input = new DataInputStream(new BufferedInputStream(new FileInputStream(m_logFile), 0x8000));
			input.skip(12);
			final long sequenceStart = input.readLong();
			SynchronizationBlock syncBlock = new SynchronizationBlock();
			if(sequenceStart >= sequenceNumber)
			{
				syncBlock.setLastChangeNumber(sequenceStart);
				syncBlock.setChanges(Collections.<RepositoryChange> emptyList());
				return syncBlock;
			}

			final long entryCount = input.readLong();
			if(sequenceNumber > sequenceStart + entryCount)
			{
				syncBlock.setLastChangeNumber(sequenceStart + entryCount);
				syncBlock.setChanges(Collections.<RepositoryChange> emptyList());
			}

			ArrayList<RepositoryChange> changes = new ArrayList<RepositoryChange>();
			JSONSerializer serializer = JSONRPCBridge.getSerializer();
			byte[] buffer = new byte[0x200];
			long currentNumber = sequenceStart - 1;
			try
			{
				int entrySize = input.readInt();
				if(++currentNumber > sequenceNumber)
				{
					if(entrySize > buffer.length)
						buffer = new byte[entrySize];
					input.readFully(buffer, 0, entrySize);
					changes.add((RepositoryChange)serializer.fromJSON(new String(buffer, 0, entrySize)));
				}
				else
					input.skip(entrySize);
			}
			catch(EOFException e)
			{
			}
			catch(UnmarshallException e)
			{
				throw BuckminsterException.wrap(e);
			}
			syncBlock.setLastChangeNumber(currentNumber);
			syncBlock.setChanges(changes);
			return syncBlock;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	public long getLastChangeNumber() throws IOException
	{
		DataInputStream input = null;
		try
		{
			input = new DataInputStream(new BufferedInputStream(new FileInputStream(m_logFile), 0x8000));
			input.skip(12);
			final long sequenceStart = input.readLong();
			final long entryCount = input.readLong();
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
}

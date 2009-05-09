package org.eclipse.buckminster.jarprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;

public class RecursivePacker extends RecursivePack200
{
	public RecursivePacker(List<String> defaultArgs)
	{
		super(defaultArgs);
	}

	public boolean pack(File jarFile) throws CoreException
	{
		Logger log = Buckminster.getLogger();
		String fileName = jarFile.getAbsolutePath();
		InputStream input = null;
		OutputStream output = null;
		try
		{
			input = new ZipInputStream(new FileInputStream(jarFile));
			JarInfo jarInfo = JarInfo.getJarInfo(null, fileName, (ZipInputStream)input);
			if(!(jarInfo.hasClasses() || jarInfo.isNested()))
			{
				log.debug("Packer: Skipping %s since it contains no classes and no nested jars", fileName); //$NON-NLS-1$
				return false;
			}
			if(jarInfo.isExcludePack())
			{
				log.debug("Packer: Skipping %s since is excluded", fileName); //$NON-NLS-1$
				return false;
			}
			if(jarInfo.isSigned() && !jarInfo.isConditioned())
			{
				log.debug("Packer: Skipping %s since is signed but not conditioned", fileName); //$NON-NLS-1$
				return false;
			}

			IOUtils.close(input);
			input = new FileInputStream(jarFile);
			output = new FileOutputStream(fileName + PACK_GZ_SUFFIX);
			output = new GZIPOutputStream(output);
			if(!jarInfo.hasClasses())
				output = new MagicChangerOutputStream(output);

			if(jarInfo.isNested())
				nestedPack(input, jarInfo, output);
			else
				pack(jarInfo, input, output);
			return true;
		}
		catch(IOException e)
		{
			throw BuckminsterException.fromMessage(e, "Unable to pack %s", fileName); //$NON-NLS-1$
		}
		finally
		{
			IOUtils.close(input);
			IOUtils.close(output);
		}
	}

	private void nestedPack(final InputStream input, final JarInfo jarInfo, OutputStream packedOut)
			throws CoreException
	{
		ProducerThread pumper = new ProducerThread("Pack200 pumper") //$NON-NLS-1$
		{
			@Override
			protected void internalRun(OutputStream writer) throws Exception
			{
				ZipInputStream jarIn = new ZipInputStream(input);
				processNestedJars(jarIn, jarInfo, writer);
			}
		};
		pumper.start();
		try
		{
			pack(jarInfo, pumper.getReaderStream(), packedOut);
		}
		catch(IOException e)
		{
			pumper.drain(jarInfo, e);
		}
		pumper.drain(jarInfo, null);
	}

	private void processNestedJars(ZipInputStream jarIn, JarInfo jarInfo, OutputStream output) throws Exception
	{
		ZipOutputStream jarOut = new ZipOutputStream(output);
		ZipEntry entry;
		while((entry = jarIn.getNextEntry()) != null)
		{
			String name = entry.getName();
			if(name.endsWith(JAR_SUFFIX))
			{
				JarInfo nested = jarInfo.getNestedInfo(name);
				if(nested != null)
				{
					if(nested.hasClasses() && !(nested.isSigned() && !nested.isConditioned()))
					{
						jarOut.putNextEntry(createEntry(entry, name + PACK_SUFFIX));
						nestedPack(jarIn, nested, jarOut);
					}
					else
					{
						jarOut.putNextEntry(createEntry(entry));
						processNestedJars(jarIn, nested, jarOut);
					}
					continue;
				}
			}
			jarOut.putNextEntry(createEntry(entry));
			if(!entry.isDirectory())
				IOUtils.copy(jarIn, jarOut, null);
		}
		jarOut.finish();
	}
}

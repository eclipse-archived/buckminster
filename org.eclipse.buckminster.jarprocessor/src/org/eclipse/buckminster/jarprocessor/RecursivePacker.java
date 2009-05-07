package org.eclipse.buckminster.jarprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.jar.Pack200.Packer;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;

public class RecursivePacker extends RecursivePack200
{
	public RecursivePacker(List<String> defaultArgs)
	{
		super(defaultArgs);
	}

	public boolean pack(File jarFile) throws CoreException
	{
		InputStream input = null;
		OutputStream output = null;
		try
		{
			input = new ZipInputStream(new FileInputStream(jarFile));
			JarInfo jarInfo = JarInfo.getJarInfo(null, jarFile.getAbsolutePath(), (ZipInputStream)input);
			if(jarInfo.isSigned() && !jarInfo.isConditioned())
				// No way we can pack this one
				return false;

			IOUtils.close(input);
			input = new FileInputStream(jarFile);
			output = new FileOutputStream(jarFile.getAbsolutePath() + PACK_GZ_SUFFIX);
			output = new GZIPOutputStream(output);
			if(!jarInfo.hasClasses())
				output = new MagicChangerOutputStream(output);
			if(jarInfo.isNested())
				nestedPack(input, jarInfo, output);
			else
			{
				Packer packer = getPacker(jarInfo);
				input = new FileInputStream(jarFile);
				packer.pack(new NonClosingJarInputStream(input), output);
			}
			return true;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
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
				processNestedJars(jarIn, jarInfo, writer, this);
			}
		};
		pumper.start();
		try
		{
			Packer packer = getPacker(jarInfo);
			packer.pack(new NonClosingJarInputStream(pumper.getReaderStream()), packedOut);
		}
		catch(IOException e)
		{
			pumper.drain(jarInfo, e);
		}
		pumper.drain(jarInfo, null);
	}

	private void processNestedJars(ZipInputStream jarIn, JarInfo jarInfo, OutputStream output, ProducerThread top)
			throws Exception
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
					if(nested.hasClasses())
					{
						jarOut.putNextEntry(createEntry(entry, name + PACK_SUFFIX));
						nestedPack(jarIn, nested, jarOut);
					}
					else
					{
						jarOut.putNextEntry(createEntry(entry, name));
						processNestedJars(jarIn, nested, jarOut, null);
					}
					continue;
				}
			}
			jarOut.putNextEntry(createEntry(entry, name));
			if(!entry.isDirectory())
				IOUtils.copy(jarIn, jarOut, null);
		}
		if(top != null)
			top.okToDrainReader();
		jarOut.finish();
	}
}

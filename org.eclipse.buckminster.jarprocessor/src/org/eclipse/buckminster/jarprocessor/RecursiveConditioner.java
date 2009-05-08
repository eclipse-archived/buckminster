package org.eclipse.buckminster.jarprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200.Packer;
import java.util.jar.Pack200.Unpacker;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;

public class RecursiveConditioner extends RecursivePack200
{
	private static void emitEclipseInf(JarOutputStream jarOut, JarInfo jarInfo, ZipEntry entry) throws IOException
	{
		jarOut.putNextEntry(entry);
		Map<String, String> eclipseInf = jarInfo.getEclipseInf();
		eclipseInf.put(JarInfo.PROP_PACK200_CONDITIONED, "true"); //$NON-NLS-1$
		BMProperties.store(eclipseInf, jarOut, "Processed using Jarprocessor"); //$NON-NLS-1$		
	}

	public RecursiveConditioner(List<String> defaultArgs)
	{
		super(defaultArgs);
	}

	public boolean condition(File jarFile, File conditionedJarFile) throws CoreException
	{
		InputStream input = null;
		OutputStream output = null;
		try
		{
			input = new ZipInputStream(new FileInputStream(jarFile));
			JarInfo jarInfo = JarInfo.getJarInfo(null, jarFile.getAbsolutePath(), (ZipInputStream)input);
			IOUtils.close(input);

			if(jarInfo.isConditioned() || jarInfo.isSigned() || jarInfo.isExcludeSign())
			{
				input = new FileInputStream(jarFile);
				output = new FileOutputStream(conditionedJarFile);
				IOUtils.copy(input, output, null);
				return false;
			}

			input = new FileInputStream(jarFile);
			output = new FileOutputStream(conditionedJarFile);
			nestedConditioning(input, jarInfo, output);
			return true;
		}
		catch(IOException e)
		{
			throw BuckminsterException.fromMessage(e, "Unable to condition %s", jarFile.getAbsolutePath()); //$NON-NLS-1$
		}
		finally
		{
			IOUtils.close(input);
			IOUtils.close(output);
		}
	}

	private void nestedConditioning(final InputStream input, final JarInfo jarInfo, OutputStream conditioned)
			throws CoreException
	{
		final ProducerThread jarPumper = new ProducerThread("Pack200 jarPumper") //$NON-NLS-1$
		{
			@Override
			protected void internalRun(OutputStream writer) throws Exception
			{
				JarOutputStream jarOut = new JarOutputStream(writer);
				ZipInputStream jarIn = new ZipInputStream(input);

				boolean hasMetaInf = false;
				boolean hasEclipseInf = false;
				if(!jarInfo.hasEclipseInf())
				{
					// Create the eclipse.inf here so that it ends up
					// at the start of the file.
					jarOut.putNextEntry(new ZipEntry(META_INF));
					hasMetaInf = true;
					emitEclipseInf(jarOut, jarInfo, new ZipEntry(META_INF + ECLIPSE_INF));
					hasEclipseInf = true;
				}

				ZipEntry entry;
				while((entry = jarIn.getNextEntry()) != null)
				{
					String name = entry.getName();
					entry = createEntry(entry);
					if(name.equals(META_INF + ECLIPSE_INF))
					{
						if(hasEclipseInf)
							continue;
						emitEclipseInf(jarOut, jarInfo, entry);
						hasEclipseInf = true;
						continue;
					}

					if(entry.isDirectory())
					{
						if(name.equals(META_INF))
						{
							if(hasMetaInf)
								continue;
							hasMetaInf = true;
						}
						jarOut.putNextEntry(entry);
						continue;
					}

					if(name.endsWith(JAR_SUFFIX) && !jarInfo.isExcludeChildrenSign())
					{
						JarInfo nested = jarInfo.getNestedInfo(name);
						if(nested != null && !(nested.isConditioned() || nested.isSigned() || nested.isExcludeSign()))
						{
							jarOut.putNextEntry(entry);
							nestedConditioning(jarIn, nested, jarOut);
							continue;
						}
					}
					jarOut.putNextEntry(entry);
					IOUtils.copy(jarIn, jarOut, null);
				}
				jarOut.finish();
			}
		};

		jarPumper.start();
		ProducerThread packPumper = new ProducerThread("Pack200 packPumper") //$NON-NLS-1$
		{
			@Override
			protected void internalRun(OutputStream writer) throws Exception
			{
				try
				{
					JarInputStream jarIn = new NonClosingJarInputStream(jarPumper.getReaderStream());
					Packer packer = getPacker(jarInfo);
					packer.pack(jarIn, writer);
					writer.flush();
				}
				catch(IOException e)
				{
					jarPumper.drain(jarInfo, e);
				}
				jarPumper.drain(jarInfo, null);
			}
		};

		packPumper.start();
		try
		{
			JarOutputStream jarOut = new JarOutputStream(conditioned);
			Unpacker unpacker = getUnpacker();
			unpacker.unpack(new NonClosingInputStream(packPumper.getReaderStream()), jarOut);
			jarOut.finish();
		}
		catch(IOException e)
		{
			packPumper.drain(jarInfo, e);
		}
		packPumper.drain(jarInfo, null);
	}
}

package org.eclipse.buckminster.jarprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200.Unpacker;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
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
		Logger log = Buckminster.getLogger();
		InputStream input = null;
		OutputStream output = null;
		String fileName = jarFile.getAbsolutePath();
		try
		{
			input = new ZipInputStream(new FileInputStream(jarFile));
			JarInfo jarInfo = JarInfo.getJarInfo(null, fileName, (ZipInputStream)input);
			IOUtils.close(input);

			boolean condition = true;
			if(jarInfo.isConditioned())
			{
				log.debug("Conditioner: skipping %s, it is already conditioned", fileName); //$NON-NLS-1$
				condition = false;
			}
			else if(jarInfo.isSigned())
			{
				log.debug("Conditioner: skipping %s, it is already signed", fileName); //$NON-NLS-1$
				condition = false;
			}
			else if(jarInfo.isExcludeSign())
			{
				log.debug("Conditioner: skipping %s, it is excluded from signing", fileName); //$NON-NLS-1$
				condition = false;
			}
			else if(!(jarInfo.hasClasses() || jarInfo.isNested()))
			{
				log.debug("Conditioner: skipping %s, it has no classes and is not nested", fileName); //$NON-NLS-1$
				condition = false;
			}

			input = new FileInputStream(jarFile);
			output = new FileOutputStream(conditionedJarFile);
			if(condition)
				nestedConditioning(input, jarInfo, output);
			else
				IOUtils.copy(input, output, null);
			return condition;
		}
		catch(IOException e)
		{
			throw BuckminsterException.fromMessage(e, "Unable to condition %s", fileName); //$NON-NLS-1$
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

				boolean metaAddingDone = false;
				boolean hasEclipseInf = false;

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
					if(!metaAddingDone && name.equals(JarFile.MANIFEST_NAME))
					{
						metaAddingDone = true;
						if(!jarInfo.hasEclipseInf())
						{
							// Create the eclipse.inf here so that it ends up
							emitEclipseInf(jarOut, jarInfo, new ZipEntry(META_INF + ECLIPSE_INF));
							hasEclipseInf = true;
						}
					}
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
					pack(jarInfo, jarPumper.getReaderStream(), writer);
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
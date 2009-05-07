package org.eclipse.buckminster.jarprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
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
	private static void emitEclipseInf(JarOutputStream jarOut, JarInfo jarInfo) throws IOException
	{
		jarOut.putNextEntry(new JarEntry(META_INF + ECLIPSE_INF));
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
		ProducerThread pumper = new ProducerThread("Pack200 jarPumper") //$NON-NLS-1$
		{
			@Override
			protected void internalRun(OutputStream writer) throws Exception
			{
				ZipEntry entry;
				JarOutputStream jarOut = new JarOutputStream(writer);
				ZipInputStream jarIn = new ZipInputStream(input);

				boolean hasMetaInf = false;
				boolean hasEclipseInf = false;
				if(!jarInfo.hasEclipseInf())
				{
					// Create the eclipse.inf here so that it ends up
					// at the start of the file.
					jarOut.putNextEntry(new JarEntry(META_INF));
					emitEclipseInf(jarOut, jarInfo);
					hasEclipseInf = true;
					hasMetaInf = true;
				}

				while((entry = jarIn.getNextEntry()) != null)
				{
					String name = entry.getName();
					if(name.equals(META_INF + ECLIPSE_INF))
					{
						if(!hasEclipseInf)
							emitEclipseInf(jarOut, jarInfo);
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
							jarOut.putNextEntry(new JarEntry(name));
							nestedConditioning(jarIn, nested, jarOut);
							continue;
						}
					}
					jarOut.putNextEntry(entry);
					IOUtils.copy(jarIn, jarOut, null);
				}
				if(!hasMetaInf)
					if(!hasEclipseInf)
						emitEclipseInf(jarOut, jarInfo);

				okToDrainReader();
				jarOut.finish();
			}
		};
		pumper.start();
		try
		{
			simpleConditioning(pumper.getReaderStream(), jarInfo, conditioned);
		}
		catch(IOException e)
		{
			pumper.drain(jarInfo, e);
		}
		pumper.drain(jarInfo, null);
	}

	private void simpleConditioning(final InputStream input, final JarInfo jarInfo, OutputStream conditioned)
			throws CoreException
	{
		ProducerThread pumper = new ProducerThread("Pack200 packPumper") //$NON-NLS-1$
		{
			@Override
			protected void internalRun(OutputStream writer) throws IOException
			{
				JarInputStream jarIn = new NonClosingJarInputStream(input);
				Packer packer = getPacker(jarInfo);
				packer.pack(jarIn, writer);
			}
		};
		pumper.start();
		try
		{
			JarOutputStream jarOut = new JarOutputStream(conditioned);
			Unpacker unpacker = getUnpacker();
			unpacker.unpack(new NonClosingInputStream(pumper.getReaderStream()), jarOut);
			jarOut.finish();
		}
		catch(IOException e)
		{
			pumper.drain(jarInfo, e);
		}
		pumper.drain(jarInfo, null);
	}
}

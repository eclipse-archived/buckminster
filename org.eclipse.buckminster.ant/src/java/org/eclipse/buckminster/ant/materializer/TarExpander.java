package org.eclipse.buckminster.ant.materializer;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.eclipse.buckminster.ant.Messages;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.helpers.NullOutputStream;
import org.eclipse.buckminster.download.IExpander;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 * @author Guillaume CHATELET
 */
public class TarExpander extends AbstractExtension implements IExpander {
	private FileFilter filter;

	private boolean flatten = false;

	public void expand(InputStream inputs, File destinationFolder, IProgressMonitor monitor) throws CoreException {
		TarEntry entry;
		TarInputStream input = null;

		int ticksLeft = 600;
		MonitorUtils.begin(monitor, ticksLeft);
		if (destinationFolder != null) {
			if (!(destinationFolder.isDirectory() || destinationFolder.mkdirs()))
				throw BuckminsterException.fromMessage(NLS.bind(Messages.TarExpander_unable_to_unzip_into_directory_0, destinationFolder));

			MonitorUtils.worked(monitor, 10);
			ticksLeft -= 10;
		}

		try {
			input = new TarInputStream(inputs);
			while ((entry = input.getNextEntry()) != null) {
				if (entry.isDirectory() && flatten)
					continue;
				String name = getName(entry);
				if (entry.isDirectory()) {
					if (destinationFolder == null)
						continue;

					File subDir = new File(destinationFolder, name);
					if (!(subDir.isDirectory() || subDir.mkdirs()))
						throw BuckminsterException.fromMessage(NLS.bind(Messages.TarExpander_unable_to_unzip_into_directory_0, destinationFolder));

					if (ticksLeft >= 10) {
						MonitorUtils.worked(monitor, 10);
						ticksLeft -= 10;
					}
					continue;
				}

				OutputStream output = null;
				try {
					if (destinationFolder == null)
						output = NullOutputStream.INSTANCE;
					else {
						// TarEntry can contain e.g.
						// "exo-enterprise-webos-r20927-tomcat\webapps\ROOT\build.xml"
						// -
						// folders need to be created
						File subDir = new File(destinationFolder, name).getParentFile();
						if (subDir != null && !(subDir.isDirectory() || subDir.mkdirs()))
							throw BuckminsterException
									.fromMessage(NLS.bind(Messages.TarExpander_unable_to_unzip_into_directory_0, destinationFolder));

						if (filter == null || filter.accept(new File(entry.getName())))
							output = new FileOutputStream(new File(destinationFolder, name));
						else
							output = NullOutputStream.INSTANCE;
					}

					IProgressMonitor subMon = null;
					if (ticksLeft >= 20) {
						subMon = MonitorUtils.subMonitor(monitor, 10);
						ticksLeft -= 10;
					}
					IOUtils.copy(input, output, subMon);
				} finally {
					IOUtils.close(output);
				}
			}
			if (ticksLeft > 0)
				MonitorUtils.worked(monitor, ticksLeft);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	public void setFilter(FileFilter filter) {
		this.filter = filter;
	}

	public void setFlattenHierarchy(boolean shouldFlatten) {
		flatten = shouldFlatten;
	}

	private String getName(TarEntry entry) {
		final String name = entry.getName();
		return flatten ? new File(name).getName() : name;
	}
}

/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.materializer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.osgi.service.datalocation.Location;

/**
 * Materializes each component to the local filesystem.
 * 
 * @author Thomas Hallgren
 */
public class FileSystemMaterializer extends AbstractMaterializer
{
	public IPath getDefaultInstallRoot(MaterializationContext context) throws CoreException
	{
		Location userLocation = Platform.getUserLocation();
		if(userLocation != null)
		{
			File userDir = FileUtils.getFile(userLocation.getURL());
			if(userDir != null)
			{
				if(Platform.OS_WIN32.equals(Platform.getOS()))
					userDir = new File(userDir, "Application Data\\Buckminster");
				else
					userDir = new File(userDir, "buckminster");
				return Path.fromOSString(new File(userDir, "downloads").toString());
			}
		}
		throw BuckminsterException.fromMessage("Unable to determine users home directory");
	}

	public List<Materialization> materialize(List<Resolution> resolutions, MaterializationContext context, IProgressMonitor monitor) throws CoreException
	{
		ArrayList<Materialization> adjustedMinfos = new ArrayList<Materialization>(resolutions.size());

		Logger logger = CorePlugin.getLogger();
		monitor.beginTask(null, 1000);
		try
		{
			// Group materialization infos per reader. Some readers use a common "view"
			// for all materializations. They need to be initialized using all
			// entries.
			//
			int totCount = 0;
			Map<String, List<Materialization>> perReader = new TreeMap<String, List<Materialization>>();
			MaterializationSpec mspec = context.getMaterializationSpec();
			IProgressMonitor prepMon = MonitorUtils.subMonitor(monitor, 100);
			prepMon.beginTask(null, resolutions.size() * 10);
			for(Resolution cr : resolutions)
			{
				try
				{
					cr.store();
					Materialization mat = WorkspaceInfo.getMaterialization(cr);
					if(mat != null)
					{
						// The resolution is already materialized.
						//
						adjustedMinfos.add(mat);
						continue;
					}
	
					ComponentIdentifier ci = cr.getComponentIdentifier();
					ConflictResolution conflictRes = mspec.getConflictResolution(ci);
					IPath installLocation = context.getInstallLocation(cr);
					mat = new Materialization(installLocation, ci);
	
					File file = installLocation.toFile();
					if(file.exists() && conflictRes == ConflictResolution.KEEP)
					{
						boolean pathTypeOK = installLocation.hasTrailingSeparator() ? file.isDirectory() : !file.isDirectory();
	
						if(!pathTypeOK)
							throw new FileFolderMismatchException(ci, installLocation);
	
						// Don't materialize this one. Instead, pretend that we
						// just did.
						//
						logger.info("Skipping materialization of " + ci + ". Instead reusing what's already at "
										+ installLocation);
	
						mat.store();
						adjustedMinfos.add(mat);
						MonitorUtils.worked(prepMon, 10);
						continue;
					}
	
					// Ensure that the destination exists and that it is empty. This might cause a
					// DestinationNotEmpty exception to be thrown.
					//
					File folder = installLocation.hasTrailingSeparator()
							? file
							: file.getParentFile();
					FileUtils.prepareDestination(folder, conflictRes != ConflictResolution.FAIL, MonitorUtils
							.subMonitor(prepMon, 10));
	
					String readerType = cr.getProvider().getReaderTypeId();
					List<Materialization> readerGroup = perReader.get(readerType);
					if(readerGroup == null)
					{
						readerGroup = new ArrayList<Materialization>();
						perReader.put(readerType, readerGroup);
					}
					readerGroup.add(mat);
					totCount++;
				}
				catch(CoreException e)
				{
					if(!context.isContinueOnError())
						throw e;
					context.addException(e.getStatus());
				}
			}
			prepMon.done();

			CorePlugin plugin = CorePlugin.getDefault();
			IProgressMonitor matMon = MonitorUtils.subMonitor(monitor, 900);

			matMon.beginTask(null, perReader.size() * 10 + totCount * 100);
			for(Map.Entry<String, List<Materialization>> entry : perReader.entrySet())
			{
				List<Materialization> rg = entry.getValue();

				// Prepare the reader type - i.e. set up a view if necessary.
				//
				IReaderType readerType = plugin.getReaderType(entry.getKey());

				matMon.subTask("Preparing type " + readerType.getId());
				readerType.prepareMaterialization(rg, context, MonitorUtils.subMonitor(matMon, 8));
				for(Materialization mi : rg)
				{
					Resolution cr = mi.getResolution();
					matMon.subTask(cr.getName());
					IComponentReader reader = readerType
							.getReader(cr, context, MonitorUtils.subMonitor(matMon, 20));
					try
					{
						IPath location = mi.getComponentLocation();
						reader.materialize(location, MonitorUtils.subMonitor(matMon, 80));

						// Remove any duplicates for the given location and then make
						// sure the materialization is persisted.
						//
						if(!location.hasTrailingSeparator() && location.toFile().isDirectory())
							mi = new Materialization(location.addTrailingSeparator(), cr.getComponentIdentifier());
						mi.store();
						adjustedMinfos.add(mi);
					}
					finally
					{
						reader.close();
					}
				}
			}

			for(String readerTypeId : perReader.keySet())
			{
				IReaderType readerType = plugin.getReaderType(readerTypeId);
				readerType.postMaterialization(context, new SubProgressMonitor(matMon, 2));
			}
			matMon.done();
			return adjustedMinfos;
		}
		finally
		{
			monitor.done();
		}
	}
}

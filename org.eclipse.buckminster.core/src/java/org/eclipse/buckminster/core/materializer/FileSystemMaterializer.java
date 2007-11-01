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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.FileUtils.DeleteException;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IFileReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Materializes each component to the local filesystem.
 * 
 * @author Thomas Hallgren
 */
public class FileSystemMaterializer extends AbstractMaterializer
{
	@Override
	public String getMaterializerRootDir()
	{
		return "downloads";
	}

	public List<Materialization> materialize(List<Resolution> resolutions, MaterializationContext context,
			IProgressMonitor monitor) throws CoreException
	{
		ArrayList<Materialization> adjustedMinfos = new ArrayList<Materialization>(resolutions.size());
		HashMap<ComponentIdentifier, Resolution> resolutionPerID = new HashMap<ComponentIdentifier, Resolution>();

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
					ComponentIdentifier ci = cr.getComponentIdentifier();
					ConflictResolution conflictRes = mspec.getConflictResolution(ci);
					IPath artifactLocation = context.getArtifactLocation(cr);

					Materialization mat = WorkspaceInfo.getMaterialization(cr);
					if(mat != null)
					{
						if(mat.getComponentLocation().equals(artifactLocation))
						{
							if(conflictRes == ConflictResolution.KEEP
							|| conflictRes == ConflictResolution.UPDATE)
							{
								// The same component (name, version, and type) is already materialized to
								// the same location.
								//
								adjustedMinfos.add(mat);
								continue;
							}
						}
						mat.remove();
					}

					mat = new Materialization(artifactLocation, ci);
					resolutionPerID.put(ci, cr);

					File file = artifactLocation.toFile();
					boolean fileExists = file.exists();
					if(fileExists && conflictRes == ConflictResolution.KEEP)
					{
						boolean pathTypeOK = artifactLocation.hasTrailingSeparator()
								? file.isDirectory()
								: !file.isDirectory();

						if(!pathTypeOK)
							throw new FileFolderMismatchException(ci, artifactLocation);

						// Don't materialize this one. Instead, pretend that we
						// just did.
						//
						logger.info("Skipping materialization of " + ci + ". Instead reusing what's already at "
								+ artifactLocation);

						mat.store();
						adjustedMinfos.add(mat);
						MonitorUtils.worked(prepMon, 10);
						continue;
					}

					// Ensure that the destination exists and that it is empty. This might cause a
					// DestinationNotEmpty exception to be thrown.
					//
					if(artifactLocation.hasTrailingSeparator())
					{
						// We are installing into folder. That folder must be empty
						// out prior to the installation.
						//
						FileUtils.prepareDestination(file, conflictRes, MonitorUtils
								.subMonitor(prepMon, 10));
					}
					else
					{
						// Assume that we are downloading a file and that the file should
						// be given this name.
						//
						if(fileExists)
						{
							if(conflictRes == ConflictResolution.FAIL)
								throw new FileUtils.DestinationNotEmptyException(file);
							if(!file.delete() && file.exists())
								throw new DeleteException(file);
						}
					}
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
					context.addException(cr.getRequest(), e.getStatus());
				}
			}
			prepMon.done();

			CorePlugin plugin = CorePlugin.getDefault();
			IProgressMonitor matMon = MonitorUtils.subMonitor(monitor, 900);

			matMon.beginTask(null, perReader.keySet().size() * 2 + perReader.entrySet().size() * 100);
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
					Resolution cr = resolutionPerID.get(mi.getComponentIdentifier());
					matMon.subTask(cr.getName());
					IComponentReader reader = readerType.getReader(cr, context, MonitorUtils.subMonitor(matMon, 20));
					try
					{
						IPath location = mi.getComponentLocation();
						IProgressMonitor matSubMon = MonitorUtils.subMonitor(matMon, 80);

						if(!location.hasTrailingSeparator() && location.toFile().isDirectory())
							mi = new Materialization(location.addTrailingSeparator(), cr.getComponentIdentifier());
						mi.store();

						if(reader instanceof IFileReader)
							((IFileReader)reader).materialize(MaterializerEndPoint.create(location, cr, context),
									matSubMon);
						else
							((ICatalogReader)reader).materialize(location, matSubMon);

						adjustedMinfos.add(mi);
					}
					finally
					{
						reader.close();
					}
				}
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

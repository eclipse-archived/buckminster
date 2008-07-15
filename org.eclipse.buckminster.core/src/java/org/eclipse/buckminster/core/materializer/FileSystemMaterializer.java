/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.materializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.FileUtils.DeleteException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.core.mspec.IMaterializationNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

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
		StorageManager sm = StorageManager.getDefault();
		Set<ComponentIdentifier> updateCandidates = new HashSet<ComponentIdentifier>();

		MaterializationStatistics statistics = context.getMaterializationStatistics();
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

			// Obtain some locations where we can feel it is OK to remove a subfolder.
			//
			IPath filesRoot = mspec.getInstallLocation();
			IPath workspaceRoot = mspec.getWorkspaceLocation();
			if(workspaceRoot == null)
				workspaceRoot = ResourcesPlugin.getWorkspace().getRoot().getLocation();

			IPath userTemp = Path.fromOSString(System.getProperty("java.io.tmpdir"));
			IPath userHome = Path.fromOSString(System.getProperty("user.home"));

			IProgressMonitor prepMon = MonitorUtils.subMonitor(monitor, 100);
			prepMon.beginTask(null, resolutions.size() * 10);
			for(Resolution cr : resolutions)
			{
				ComponentIdentifier ci = null;

				try
				{
					cr.store(sm);
					ci = cr.getComponentIdentifier();
					ConflictResolution conflictRes = mspec.getConflictResolution(ci);
					IPath artifactLocation = getArtifactLocation(context, cr);
					String syncLock = artifactLocation.toOSString().intern();
					synchronized(syncLock)
					{
						Materialization mat = WorkspaceInfo.getMaterialization(cr);
						if(mat != null)
						{
							if(mat.getComponentLocation().equals(artifactLocation))
							{
								if(conflictRes == ConflictResolution.KEEP)
								{
									// The same component (name, version, and type) is already materialized to
									// the same location.
									//
									statistics.addKept(ci);
									adjustedMinfos.add(mat);
									continue;
								}
							}
							mat.remove(sm);
						}

						mat = new Materialization(artifactLocation, ci);
						resolutionPerID.put(ci, cr);

						File file = artifactLocation.toFile().getAbsoluteFile();
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
							statistics.addKept(ci);
							logger.info("Skipping materialization of %s. Instead reusing what's already at %s", ci,
									artifactLocation);

							mat.store(sm);
							adjustedMinfos.add(mat);
							MonitorUtils.worked(prepMon, 10);
							continue;
						}

						// Ensure that the destination exists and that it is empty. This might cause a
						// DestinationNotEmpty exception to be thrown.
						//
						if(artifactLocation.hasTrailingSeparator())
						{
							// We are installing into folder
							//
							IMaterializationNode node = mspec.getMatchingNode(ci);
							if(node != null && node.isUnpack())
							{
								// An unpack must never clear the folder that it uses as parent for the unpack
								// unless that parent has been explicitly stated.
								//
								if(node.getLeafArtifact() == null)
									conflictRes = ConflictResolution.UPDATE;
							}

							if(conflictRes.equals(ConflictResolution.REPLACE))
							{
								// Some precaution is needed here. We don't just remove folders.
								//
								int alCount = artifactLocation.segmentCount();
								if(!((userHome.isPrefixOf(artifactLocation) && userHome.segmentCount() < alCount)
										|| (userTemp.isPrefixOf(artifactLocation) && userTemp.segmentCount() < alCount)
										|| (workspaceRoot.isPrefixOf(artifactLocation) && workspaceRoot.segmentCount() < alCount) || (filesRoot != null
										&& filesRoot.isPrefixOf(artifactLocation) && filesRoot.segmentCount() < alCount)))
									conflictRes = ConflictResolution.UPDATE;
							}

							FileUtils.prepareDestination(file, conflictRes, MonitorUtils.subMonitor(prepMon, 10));

							// Make sure the destination is not completely empty.
							//
							if(file.list().length == 0)
							{
								File mtFile = new File(file, ".mtlock");
								try
								{
									mtFile.createNewFile();
								}
								catch(IOException e)
								{
									throw BuckminsterException.wrap(e);
								}
							}

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

						if(fileExists && conflictRes == ConflictResolution.UPDATE)
							updateCandidates.add(ci);

						IReaderType readerType = getMaterializationReaderType(cr);
						List<Materialization> readerGroup = perReader.get(readerType.getId());
						if(readerGroup == null)
						{
							readerGroup = new ArrayList<Materialization>();
							perReader.put(readerType.getId(), readerGroup);
						}
						readerGroup.add(mat);
						totCount++;
					}
				}
				catch(CoreException e)
				{
					if(ci != null)
						statistics.addFailed(ci);

					if(!context.isContinueOnError())
						throw e;

					context.addRequestStatus(cr.getRequest(), e.getStatus());
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
					ComponentIdentifier ci = mi.getComponentIdentifier();
					Resolution cr = resolutionPerID.get(ci);
					matMon.subTask(ci.getName());

					boolean success = false;
					IComponentReader reader = readerType.getReader(cr, context, MonitorUtils.subMonitor(matMon, 20));
					try
					{
						IPath location = mi.getComponentLocation();
						IProgressMonitor matSubMon = MonitorUtils.subMonitor(matMon, 80);

						if(!location.hasTrailingSeparator() && location.toFile().isDirectory())
							mi = new Materialization(location.addTrailingSeparator(), ci);
						mi.store(sm);
						reader.materialize(location, cr, context, matSubMon);
						adjustedMinfos.add(mi);
						success = true;
					}
					catch(CoreException e)
					{
						if(!context.isContinueOnError())
							throw e;
						context.addRequestStatus(cr.getRequest(), e.getStatus());
					}
					finally
					{
						IOUtils.close(reader);
						IPath location = mi.getComponentLocation();
						if(location.hasTrailingSeparator())
							location.append(".mtlock").toFile().delete();

						if(success)
						{
							if(updateCandidates.contains(ci))
								statistics.addUpdated(ci);
							else
								statistics.addReplaced(ci);
						}
						else
						{
							statistics.addFailed(ci);
							mi.remove(sm);
						}
					}
				}
			}
			matMon.done();
			return adjustedMinfos;
		}
		finally
		{
			for(Resolution res : resolutions)
			{
				ComponentIdentifier ci = res.getComponentIdentifier();

				if(!statistics.isIncluded(ci))
					statistics.addFailed(ci);
			}

			monitor.done();
		}
	}

	protected IPath getArtifactLocation(MaterializationContext context, Resolution resolution) throws CoreException
	{
		return context.getArtifactLocation(resolution);
	}
}

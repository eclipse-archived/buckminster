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
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.LocalResolver;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

/**
 * Materializes each component to the local filesystem.
 * 
 * @author Thomas Hallgren
 */
public class FileSystemMaterializer extends AbstractMaterializer
{
	public List<Materialization> materialize(BillOfMaterials bom, Set<Resolution> excludes, RMContext context,
			IProgressMonitor monitor) throws CoreException
	{
		List<Materialization> minfos = bom.createMaterializations(context, excludes);
		ArrayList<Materialization> adjustedMinfos = new ArrayList<Materialization>(minfos.size());

		Logger logger = CorePlugin.getLogger();
		monitor.beginTask(null, 1000);
		try
		{
			// Group materialization infos per reader. Some readers use a common
			// "view"
			// for all materializations. They need to be initialized using all
			// entries.
			//
			int totCount = 0;
			Map<String, List<Materialization>> perReader = new TreeMap<String, List<Materialization>>();
			IProgressMonitor prepMon = MonitorUtils.subMonitor(monitor, 100);
			prepMon.beginTask(null, minfos.size() * 10);
			for(Materialization mi : minfos)
			{
				Resolution cr = mi.getResolution();
				ConflictResolution notEmptyAction = context.getNotEmptyAction(cr);
				if(notEmptyAction != ConflictResolution.UPDATE && mi.isPersisted())
				{
					// Already materialized.
					//
					adjustedMinfos.add(mi);
					continue;
				}

				ComponentIdentifier ci = cr.getComponentIdentifier();
				IPath location = mi.getComponentLocation();
				File file = location.toFile();
				if(file.exists() && notEmptyAction == ConflictResolution.KEEP)
				{
					// Don't materialize this one. Instead, pretend that we
					// just did.
					//
					logger
							.info("Skipping materialization of " + ci + ". Instead reusing what's already at "
									+ location);
					if(!location.hasTrailingSeparator() && file.isDirectory())
					{
						Resolution current = LocalResolver.fromPath(location, ci.getName());
						mi = new Materialization(location.addTrailingSeparator(), new Resolution(current.getCSpec(), mi
								.getResolution()));
					}
					mi.store();
					adjustedMinfos.add(mi);
					MonitorUtils.worked(prepMon, 10);
					continue;
				}

				// Ensure that the destination exists and that it is empty. This might cause a
				// DestinationNotEmpty exception to be thrown.
				//
				File folder = location.hasTrailingSeparator()
						? file
						: file.getParentFile();
				FileUtils.prepareDestination(folder, notEmptyAction == ConflictResolution.UPDATE, MonitorUtils
						.subMonitor(prepMon, 10));

				String readerType = cr.getProvider().getReaderTypeId();
				List<Materialization> readerGroup = perReader.get(readerType);
				if(readerGroup == null)
				{
					readerGroup = new ArrayList<Materialization>();
					perReader.put(readerType, readerGroup);
				}
				readerGroup.add(mi);
				totCount++;
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
						reader.materialize(mi.getComponentLocation(), MonitorUtils.subMonitor(matMon, 80));

						// Remove any duplicates for the given location and then make
						// sure the materialization is persisted.
						//
						if(!location.hasTrailingSeparator() && location.toFile().isDirectory())
							mi = new Materialization(location.addTrailingSeparator(), mi.getResolution());
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
				readerType.postMaterialization(new SubProgressMonitor(matMon, 2));
			}
			matMon.done();
			bom.store();
			return adjustedMinfos;
		}
		finally
		{
			monitor.done();
		}
	}
}

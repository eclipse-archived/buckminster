/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import org.eclipse.buckminster.core.cspec.WellKnownExports;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.resolver.CategoryMismatchException;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractComponentType extends AbstractExtension implements IComponentType
{
	public boolean hasProjectDescription() throws BuckminsterException
	{
		return false;
	}

	public final DepNode getResolution(ProviderMatch rInfo, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 2000);
		IComponentReader[] reader = new IComponentReader[1];
		try
		{
			reader[0] = rInfo.getReader(MonitorUtils.subMonitor(monitor, 200));
			IResolutionBuilder builder = this.getResolutionBuilder(reader[0],MonitorUtils.subMonitor(monitor, 800));
			ComponentRequest request = rInfo.getNodeQuery().getComponentRequest();
			String category = request.getCategory();
			if(category != null && !category.equals(builder.getCategory()))
				throw new CategoryMismatchException(request.getName(), category, builder.getCategory());
			return builder.build(reader, MonitorUtils.subMonitor(monitor, 1000));
		}
		finally
		{
			if(reader[0] != null)
				reader[0].close();
			monitor.done();
		}
	}

	/**
	 * Helper methods used by component types that manifest themselfs as one single jar file.
	 * 
	 * @param cspec
	 * @return An export where other jars that this component depends on can be added
	 */
	public static GroupBuilder addSelfAsJarArtifactGroups(CSpecBuilder cspec) throws PrerequisiteAlreadyDefinedException, AttributeAlreadyDefinedException
	{
		GroupBuilder archives = cspec.createGroupBuilder();
		archives.setName(WellKnownExports.JAVA_BINARY_ARCHIVES);
		archives.setPublic(true);
		archives.addSelfRequirement();
		cspec.addAttribute(archives);

		GroupBuilder generic = cspec.createGroupBuilder();
		generic.setName(WellKnownExports.JAVA_BINARIES);
		generic.setPublic(true);
		generic.addLocalPrerequisite(archives);
		cspec.addAttribute(generic);
		return generic;
	}
}

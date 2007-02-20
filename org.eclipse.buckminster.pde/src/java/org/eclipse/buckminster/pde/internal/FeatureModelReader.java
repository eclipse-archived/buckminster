/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.internal;

import java.io.File;
import java.io.InputStream;

import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.pde.internal.model.ExternalEditableFeatureModel;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.internal.core.feature.ExternalFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

/**
 * A CSpec builder that creates a cspec from a Eclipse plugin.xml file.
 * 
 * @author thhal
 */
@SuppressWarnings("restriction")
public class FeatureModelReader implements IStreamConsumer<IFeatureModel>
{
	public static ExternalEditableFeatureModel readEditableFeatureModel(File featureFile) throws CoreException
	{
		ExternalEditableFeatureModel featureModel = new ExternalEditableFeatureModel(featureFile);
		featureModel.load();
		return featureModel;
	}

	public static IFeatureModel readFeatureModel(InputStream stream) throws CoreException
	{
		ExternalFeatureModel featureModel = new ExternalFeatureModel();
		featureModel.load(stream, true);
		return featureModel;
	}

	public IFeatureModel consumeStream(IComponentReader fileReader, String streamName, InputStream stream,
			IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 1);
		try
		{
			IFeatureModel featureModel = readFeatureModel(stream);
			MonitorUtils.worked(monitor, 1);
			return featureModel;
		}
		finally
		{
			monitor.done();
		}
	}
}

package org.eclipse.buckminster.core.site;

import java.io.File;
import java.util.List;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.core.runtime.CoreException;

public interface ISiteFeatureConverter
{
	List<Resolution> convertToSiteFeatures(RMContext context, File siteFolder, List<Resolution> features,
			List<Resolution> plugins) throws CoreException;
}
